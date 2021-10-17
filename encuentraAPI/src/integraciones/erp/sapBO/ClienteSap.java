package integraciones.erp.sapBO;

import java.io.BufferedInputStream;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.gson.Gson;

import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.ArticuloRepoFromLoad;
import beans.encuentra.DepositoMayorista;
import beans.helper.PropertiesHelper;
import integraciones.erp.sapBO.beansSL.BPAddress;
import integraciones.erp.sapBO.beansSL.BarCode;
import integraciones.erp.sapBO.beansSL.DocumentLine;
import integraciones.erp.sapBO.beansSL.ITMSResponse;
import integraciones.erp.sapBO.beansSL.Login;
import integraciones.erp.sapBO.beansSL.Value;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClienteSap {

//	CONSTANTES

	private static final String DEPOSITO_CLIENTE = "100";
	private static final int DEPOSITO_CENTRAL_PARAM = 4;

//	URLS

	private static String urlBase = "";
	
	private static final String URL_ARTICULOS = "Items?"
			+ "$select=ItemCode, ItemName, ItemsGroupCode, Manufacturer, "
			+ "SalesUnit, Mainsupplier, U_SEI_SubGrupos, U_SEI_Grupo, ItemBarCodeCollection";

	private static final String URL_GENEROS = "ItemGroups?$select=Number, GroupName";

	private static final String URL_UNIDADES_MEDIDA = "UnitOfMeasurements?$select=AbsEntry, Name";

	private static final String URL_CATEGORIAS = "U_SEI_GRUPOSINV?$select=Code, Name";

	private static final String URL_CLASES = "U_SEI_SUBGRUPOSINV?$select=Code, Name";

	private static final String URL_BARRAS = "BarCodes?$select=ItemNo, Barcode";
	
	private static final String URL_CLIENTES = "BusinessPartners?$select=CardCode, CardName, BPAddresses&$filter=";

	private static final String URL_MARCAS = "Manufacturers?$select=Code, ManufacturerName";

	private static final String URL_ORDENES = "Orders?"
			+ "$select=DocNum, DiscountPercent, Address, CardName, "
			+ "DocTotal, CardCode,  DocumentLines, TaxExtension"
			+ "&$filter=DocumentStatus eq 'bost_Open'";

	private static final String URL_ORDENES_COMPRA = "PurchaseOrders?"
			+ "$select = ";
	
	private Integer iSkip = 0;
	private String sSkip = "&$skip=";
	private static String sessionId;
	private static int empresaId;
	private static String nombreBdd;
	private static LogicaAPI logica;
	private Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();

	public ClienteSap(int idEmpresa) {

		PropertiesHelper propHelper = new PropertiesHelper("sap");
		try {
			propHelper.loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String usuario = propHelper.getValue("usuario" + idEmpresa);
		String password = propHelper.getValue("password" + idEmpresa);
		String bd = propHelper.getValue("bd" + idEmpresa);
		  urlBase = propHelper.getValue("url" + idEmpresa);
		
		empresaId = idEmpresa;
		nombreBdd = bd;
		sessionId = new Login(usuario, password, bd).doLogin(urlBase);
		logica = new LogicaAPI();
	}

	public List<Value> consultaGenerica(String url) {

		ITMSResponse items = new ITMSResponse();
		List<Value> ret = new ArrayList<>();
		Gson gson = new Gson();
		try {
			do {
				OkHttpClient client = getUnsafeOkHttpClient();
				Request request = new Request.Builder().url(url + getSkip()).method("GET", null)
						.addHeader("Content-Type", "application/json")
						.addHeader("Cookie", "B1SESSION=" + sessionId + "; CompanyDB=" + nombreBdd).build();
				
				Response response = client.newCall(request).execute();
				
				int bytesRead = 0;
				BufferedInputStream bis = new BufferedInputStream(response.body().byteStream());
				byte[] contents = new byte[1024];
				StringBuilder strFileContents = new StringBuilder();
				while ((bytesRead = bis.read(contents)) != -1) {
					strFileContents.append(new String(contents, 0, bytesRead));
				}
				response.close();
				items = gson.fromJson(strFileContents.toString(), ITMSResponse.class);
				ret.addAll(items.getValue());
				if (items.getValue().size() != 20) {
					int cantItems = iSkip + items.getValue().size() - 20;
					System.out.println("Se procesaron " + cantItems + " items.");
					iSkip = 0;
				} else if (iSkip % 100 == 0) {
					System.out.println("Se procesaron " + iSkip + " items.");
				}
			} while (items.getValue().size() == 20);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private String getSkip() {
		String ret = sSkip + iSkip;
		iSkip += 20;
		return ret;
	}

	public List<DataIDDescripcion> obtenerDatos(String nombreMetodo) {
		switch (nombreMetodo) {
		case "generos":
			return obtenerGeneros();
		case "categorias":
			return obtenerCategorias();
		case "clases":
			return obtenerClases();
		case "unidad_medida":
			return obtenerUnidadesMedida();
		case "barras":
			return obtenerCodigosDeBarra();
		case "marcas":
			return obtenerMarcas();
		default:
			return new ArrayList<>();
		}
	}

	private List<DataIDDescripcion> obtenerMarcas() {
		List<Value> values = consultaGenerica(urlBase + URL_MARCAS);
		List<DataIDDescripcion> ret = new ArrayList<>();
		DataIDDescripcion item;
		for (Value marca : values) {
			item = new DataIDDescripcion(marca.getCode(), marca.getManufacturerName());
			ret.add(item);
		}
		return ret;
	}

	private List<DataIDDescripcion> obtenerCodigosDeBarra() {
		List<Value> values = consultaGenerica(urlBase + URL_BARRAS);
		List<DataIDDescripcion> ret = new ArrayList<>();
		DataIDDescripcion item;
		for (Value genero : values) {
			item = new DataIDDescripcion(genero.getNumber(), genero.getGroupName());
			ret.add(item);
		}
		return ret;
	}

	private List<DataIDDescripcion> obtenerGeneros() {
		List<Value> values = consultaGenerica(urlBase + URL_GENEROS);
		List<DataIDDescripcion> ret = new ArrayList<>();
		DataIDDescripcion item;
		for (Value barra : values) {
			item = new DataIDDescripcion(barra.getItemNo(), barra.getBarcode());
			ret.add(item);
		}
		return ret;
	}

	private List<DataIDDescripcion> obtenerClases() {
		List<Value> values = consultaGenerica(urlBase + URL_CLASES);
		List<DataIDDescripcion> ret = new ArrayList<>();
		DataIDDescripcion item;
		for (Value clase : values) {
			item = new DataIDDescripcion(clase.getCode(), clase.getName());
			ret.add(item);
		}
		return ret;
	}

	private List<DataIDDescripcion> obtenerCategorias() {
		List<Value> values = consultaGenerica(urlBase + URL_CATEGORIAS);
		List<DataIDDescripcion> ret = new ArrayList<>();
		DataIDDescripcion item;
		for (Value categoria : values) {
			item = new DataIDDescripcion(categoria.getCode(), categoria.getName());
			ret.add(item);
		}
		return ret;
	}

	private List<DataIDDescripcion> obtenerUnidadesMedida() {
		List<Value> values = consultaGenerica(urlBase + URL_UNIDADES_MEDIDA);
		List<DataIDDescripcion> ret = new ArrayList<>();
		DataIDDescripcion item;
		for (Value unidad : values) {
			item = new DataIDDescripcion(unidad.getAbsEntry(), unidad.getName());
			ret.add(item);
		}
		return ret;
	}

	public List<DataDescDescripcion> obtenerBarras() {
		List<Value> values = consultaGenerica(urlBase + URL_BARRAS);
		List<DataDescDescripcion> barras = new ArrayList<>();
		for (Value val : values) {
			DataDescDescripcion item = new DataDescDescripcion();
			item.setId(val.getItemNo());
			item.setDescripcion(val.getBarcode());
			barras.add(item);
		}
		return barras;
	}

	public List<DTO_Articulo> obtenerArticulos() {
		List<Value> values = consultaGenerica(urlBase + URL_ARTICULOS);
		List<DTO_Articulo> articulos = new ArrayList<>();
		for (Value val : values) {
			DTO_Articulo articulo = new DTO_Articulo(val.getItemCode(), val.getItemName(), "", "", "", "", "0",
					val.getItemsGroupCode() + "", val.getU_SEI_SubGrupos(), val.getU_SEI_Grupo());
			if (val.getItemBarCodeCollection() != null && val.getItemBarCodeCollection().length != 0) {
				List<String> codigos = new ArrayList<>();
				for (BarCode b : val.getItemBarCodeCollection())
					codigos.add(b.getBarcode());

				articulo.setCodigoBarras(codigos);
			}
			articulo.setUnidadMedida(val.getSalesUnit());
			articulos.add(articulo);
		}
		return articulos;
	}

	public void obtenerOrdenesDeVenta() {

		List<OrdenVenta> ordenes = new ArrayList<>();
		List<String> clientes = new ArrayList<>();
		List<DepositoMayorista> depositos = new ArrayList<>();
		List<ArticuloRepoFromLoad> articulosReposicion = new ArrayList<>();
		Map<Integer, ArticuloRepoFromLoad> pickingDestinos = new HashMap<>();

//		Obtengo el id del depósito central de la empresa
		String tokenParam = logica.darToken(empresaId);
		Map<Integer, String> parametrosE = cwa.darParametros(tokenParam, "");
		int depositoCentral = Integer.parseInt(parametrosE.get(DEPOSITO_CENTRAL_PARAM));

//		Traigo lar órdenes del web service /Orders como values
//		Filtrando por fecha mínima
		List<Value> values = consultaGenerica(armarUrlOrdenesDeVenta());

//		Recorro la lista de values
		for (Value orden : values) {

//			Por cada uno, creo un objeto OrdenVenta
			OrdenVenta ov = new OrdenVenta();

			ov.setIdCarrito((long) orden.getDocNum());
			ov.setPorcDescuento(orden.getDiscountPercent());
			ov.setCliMail("");
			ov.setCliDireccion(orden.getAddress());
			ov.setCliRuc(orden.getDocEntry() + "");/* TODO: Fijarse si esto está bien */
			ov.setCliCedula("");
			ov.setCliNombre(orden.getCardName());
			ov.setCliTelefono("");
			ov.setMl(0);
			ov.setFormaPago("");
			ov.setImportePago(orden.getDocTotal());
			ov.setSerie("");
			ov.setTipodoc("");
			ov.setDepartamento(orden.getTaxExtension().getCountyB());
			ov.setCiudad(orden.getTaxExtension().getCityB());
			ov.setCourrier("");

//			Agrego el cliente a la lista clientes para poder guardarlo como depósito
			String clienteNuevo = orden.getCardCode();
			if (!clientes.contains(clienteNuevo))
				clientes.add(clienteNuevo);

//			Creo una lista de lineas de la orden
			List<OrdenVentaLinea> ordenVentaLineas = new ArrayList<>();

//			Recorro la lista de lineas de la orden que traje del web service
			for (DocumentLine dl : orden.getDocumentLines()) {
//				(Redondeo las cantidades en double)
				int quantityInteger = (int) Math.round(dl.getQuantity());
//				Por cada entrada traida del web service, creo un objeto OrdenVentaLinea
				OrdenVentaLinea ovl = new OrdenVentaLinea(dl.getPrice(), quantityInteger + "", dl.getItemCode());
				ovl.setLinea(dl.getLineNum());

				DataIDDescripcion d = new DataIDDescripcion();
				d.setDescripcion(dl.getItemCode());

//				TODO: MANEJAR DOUBLES PARA GARRIDO

				d.setId(quantityInteger);
				d.setIdLong((long) orden.getDocNum());
				d.setIdB(orden.getDocNum());
				d.setDescripcionB("");

				if (pickingDestinos.get(Integer.parseInt(orden.getCardCode())) == null) {
					List<DataIDDescripcion> articulosCantidad = new ArrayList<>();
					articulosCantidad.add(d);
					ArticuloRepoFromLoad ar = new ArticuloRepoFromLoad(Integer.parseInt(orden.getCardCode()), true, 10,
							depositoCentral, 3, articulosCantidad);
					pickingDestinos.put(Integer.parseInt(orden.getCardCode()), ar);
				} else {
					ArticuloRepoFromLoad ar = pickingDestinos.get(Integer.parseInt(orden.getCardCode()));
					ar.getArticulosCantidad().add(d);
					pickingDestinos.put(Integer.parseInt(orden.getCardCode()), ar);
				}
				articulosReposicion.addAll(pickingDestinos.values());
				ordenVentaLineas.add(ovl);
			}

			ov.setOrdenVentaLineas(ordenVentaLineas);

			ordenes.add(ov);
		}

//		Armo una url para el web service de clientes, con los clientes de la lista que armé antes
//		con la información de la orden de venta traída del web service y traigo los clientes
//		como values

		for (String urlClientes : armarUrlClientes(clientes)) {

			values = consultaGenerica(urlClientes);

//			Recorro la lista de clientes como values
			for (Value cliente : values) {

//				Agrego un prefijo para el id de depósito, para especificar que es 
				int idDepositoBase = empresaId * 100;

//				Por cada cliente en la lista recorro las diracciones
				for (BPAddress clienteDeposito : cliente.getBPAddresses()) {

//					Si son direcciones de envío
					if (clienteDeposito.getAddressType().equals("bo_ShipTo")) {

//						Creo un depósito con los datos de la dirección
						DepositoMayorista dp = new DepositoMayorista(idDepositoBase + cliente.getCardCode(),
								cliente.getCardName(), clienteDeposito.getStreet(), DEPOSITO_CLIENTE);
						dp.setDepartamento(clienteDeposito.getCounty());
						dp.setCiudad(clienteDeposito.getCity());

//						y lo agrego a la lista de depósitos
						depositos.add(dp);
						idDepositoBase++;
					}
				}
			}
		}

//		Recorro las órdenes de venta
		for (OrdenVenta ov : ordenes) {
//			Y las guardo en la base de datos
			ov.save("", empresaId);
		}

		String token = logica.darToken(empresaId);

		List<ArticuloRepoFromLoad> lista = new ArrayList<>(pickingDestinos.values());
		cwa.savePickingOrder(token, lista);

		cwa.putDeposM(token, depositos);
	}
	
	public void obtenerOrdenesDeCompra() {

	}
	
	private String armarUrlOrdenesDeCompra(int cantidadDias) {
		String url = urlBase + URL_ORDENES_COMPRA;
		
		int ultimoId = logica.darUltimoIdOrdenCompra(empresaId);
		if(ultimoId!=-1) {
			url+="&$filter=DocNum gt "+ultimoId;
		}
		
		return url;		
	}

	private String armarUrlOrdenesDeVenta() {

		String url = urlBase + URL_ORDENES;
		
		int ultimoId = logica.darUltimoIdOrdenVenta(empresaId);
		if(ultimoId!=-1) {
			url+="&$filter=DocNum gt "+ultimoId;
		}
		
		return url;
	}

	private List<String> armarUrlClientes(List<String> clientes) {
		StringBuilder url = new StringBuilder(urlBase + URL_CLIENTES);
		List<String> ret = new ArrayList<>();
		boolean primero = true;
		int contador = 0;
		for (String cliente : clientes) {
			contador++;
			url.append(agregarCliente(cliente, primero));
			if (primero)
				primero = false;
			if (contador % 20 == 0) {
				ret.add(url.toString());
				url = new StringBuilder(urlBase + URL_CLIENTES);
				primero = true;
			}
		}
		return ret;
	}

	private String agregarCliente(String cliente, boolean primero) {
		String cardCode = "CardCode eq '" + cliente + "' ";
		if (primero)
			return cardCode;
		return " or " + cardCode;
	}

	private OkHttpClient getUnsafeOkHttpClient() {
		try {
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}
			} };

			final SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);

			builder.hostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			return builder.build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}