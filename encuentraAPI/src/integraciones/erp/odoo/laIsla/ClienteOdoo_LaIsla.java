package integraciones.erp.odoo.laIsla;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.wss.saml.internal.saml11.jaxb10.X509DataType.X509Certificate;

import beans.datatypes.BillingEntities;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.MetodoPago;
import beans.datatypes.StockDeposito;
import beans.encuentra.Cliente;
import integraciones.marketplaces.fenicio.Descuento;
import integraciones.marketplaces.fenicio.Lineas;
import integraciones.marketplaces.fenicio.Ordenes;

public class ClienteOdoo_LaIsla 
{
	ConfiguracionConexion_LaIsla ccls = null; 
	private int uid = 0;

	public ClienteOdoo_LaIsla(boolean productivo)
	{
		
		if(productivo)
		{
			this.ccls = ConfiguracionConexion_LaIsla.productivo();			
		}
		else
		{
			this.ccls = ConfiguracionConexion_LaIsla.test();
		}
		//this.ccls = ConfiguracionConexion_LaIsla.test_tcp();
	}
	
/**
 * @implNote Busca en Odoo el codigo de identificacion del usuario, pasandole como parametros bd, userName y password.
 * @return uid (Integer)
 */
	public int devolverUID() {
		if(this.uid <= 0) {
			final XmlRpcClient client = new XmlRpcClient();
			final XmlRpcClientConfigImpl commonConfig = new XmlRpcClientConfigImpl();
			int uid = -1;
			try {

				// Create a trust manager that does not validate certificate chains
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					@Override
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}

					@Override
					public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
							throws CertificateException {
						// TODO Auto-generated method stub

					}

					@Override
					public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
							throws CertificateException {
						// TODO Auto-generated method stub

					}
				} };

				// Install the all-trusting trust manager
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

				// Create all-trusting host name verifier
				HostnameVerifier allHostsValid = new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};

				// Install the all-trusting host verifier
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

				commonConfig.setServerURL(new URL(String.format("%s/xmlrpc/2/common", ccls.getUrl())));
				

				uid = (int) client.execute(commonConfig, "authenticate",
						Arrays.asList(ccls.getDb(), ccls.getUsername(), ccls.getPassword(), Collections.emptyList()));
				
				this.uid = uid;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.uid;
	}

	public boolean callMethods(int uid) throws XmlRpcException, MalformedURLException {
		boolean retorno = false;
		final String url = "https://laisla.quanam.com", db = "laisla", username = "ws_encuentra", password = "1234";
		List<String> miLista = new ArrayList<>(Arrays.asList("read"));
		final XmlRpcClient models = new XmlRpcClient() {
			{
				setConfig(new XmlRpcClientConfigImpl() {
					{
						setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
					}
				});
			}
		};
		retorno = (boolean) models.execute("execute_kw",
				Arrays.asList(db, uid, password, "res.partner", "check_access_rights", miLista, new HashMap() {
					{
						put("raise_exception", false);
					}
				}));
		return retorno;
	}

	public String obtenerGeneros() {
		Gson gson = new Gson();
		List<DatosGenericos> datos = new ArrayList<>();
		List<String> generosAgregados = new ArrayList<>();
		List<DatosGenericos> datosDevolver = new ArrayList<>();
		try {
			int uid = devolverUID();

			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final List<Object> arts = Arrays.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(),
					uid, ccls.getPassword(), "product.template", "search_read", Collections.emptyList(), new HashMap() {
						{
							put("fields", Arrays.asList("sexo")); // "attribute_line_ids"
							// put("limit", 1);
						}
					})));

			String generosJson = gson.toJson(arts);
			System.out.println(generosJson);

			datos = gson.fromJson(generosJson, new TypeToken<List<DatosGenericos>>() {
			}.getType());
			for (DatosGenericos dg : datos) {
				if (!dg.getSexo().equalsIgnoreCase("null") && !dg.getSexo().equalsIgnoreCase("false")
						&& !generosAgregados.contains(dg.getSexo())) {
					generosAgregados.add(dg.getSexo());
					dg.setName(dg.getSexo());
					datosDevolver.add(dg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(datosDevolver);
	}

	public void obtenerGeneros2() throws Exception {

		int uid = devolverUID();

		final XmlRpcClient models = getModels();

		@SuppressWarnings("unchecked")
		final Map<String, Map<String, Object>> arts = (Map<String, Map<String, Object>>) models.execute("execute_kw",
				Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "product.product", "fields_get",
						Arrays.asList("sexo"), new HashMap() {
							{
								put("attributes", Arrays.asList("selection"));
							}
						}

				));

		Gson gson = new Gson();
		String json = gson.toJson(arts.values());

		List<DataIDDescripcion> generos = new ArrayList<>();

	}

	public String obtenerCategorias() {
		String categoriasJson = "";
		try {
			int uid = devolverUID();

			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final List<Object> arts = Arrays.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(),
					uid, ccls.getPassword(), "product.category", "search_read", Collections.emptyList(), new HashMap() {
						{
							put("fields", Arrays.asList("name"));
						}
					})));

			Gson gson = new Gson();
			categoriasJson = gson.toJson(arts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoriasJson;
	}

	public String obtenerMarcas() {
		String marcasJson = "";
		try {
			int uid = devolverUID();

			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final List<Object> arts = Arrays.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(),
					uid, ccls.getPassword(), "product.marca", "search_read", Collections.emptyList(), new HashMap() {
						{
							put("fields", Arrays.asList("name"));
						}
					})));

			Gson gson = new Gson();
			marcasJson = gson.toJson(arts);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcasJson;
	}

	public String obtenerTemporadas() {
		String temporadasJson = "";
		try {
			int uid = devolverUID();

			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final List<Object> arts = Arrays
					.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
							"product.temporada", "search_read", Collections.emptyList(), new HashMap() {
								{
									put("fields", Arrays.asList("name"));
								}
							})));

			Gson gson = new Gson();
			temporadasJson = gson.toJson(arts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temporadasJson;
	}

	public String obtenerAlmacenes() {
		String almacenesJson = "";
		try {
			int uid = devolverUID();
			// List<Almacen> almacenes = new ArrayList<>();

			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final List<Object> arts = Arrays.asList((Object[]) models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.location", "search_read",
							Arrays.asList(Arrays.asList(Arrays.asList("usage", "=", "internal"))), new HashMap() {
								{
									put("fields", Arrays.asList("name"));
								}
							})));

			Gson gson = new Gson();
			almacenesJson = gson.toJson(arts);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(almacenesJson);
		return almacenesJson;
	}

	public String obtenerTalles() {
		List<DatosGenericos> datosDevolver = new ArrayList<>();
		Gson gson = new Gson();
		try {
			int uid = devolverUID();

			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final List<Object> arts = Arrays
					.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
							"product.attribute.value", "search_read", Collections.emptyList(), new HashMap() {
								{
									put("fields", Arrays.asList("name", "attribute_id"));
								}
							})));

			String tallesJson = gson.toJson(arts);
			System.out.println(tallesJson);
			List<DatosGenericos> datos = gson.fromJson(tallesJson, new TypeToken<List<DatosGenericos>>() {
			}.getType());

			for (DatosGenericos dg : datos) {
				String atb = gson.toJson(dg.getAttribute_id());
				System.out.println(atb);
				if (atb.contains("\"2\",\"Talles Vestimenta\"") || atb.contains("\"3\",\"Talles Calzado\"")) {
					datosDevolver.add(dg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(datosDevolver);
	}

	public String obtenerColores() {
		Gson gson = new Gson();
		List<DatosGenericos> datosDevolver = new ArrayList<>();
		try {
			int uid = devolverUID();

			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final List<Object> arts = Arrays
					.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
							"product.attribute.value", "search_read", Collections.emptyList(), new HashMap() {
								{
									put("fields", Arrays.asList("name", "attribute_id"));
								}
							})));

			String coloresJson = gson.toJson(arts);
			List<DatosGenericos> datos = gson.fromJson(coloresJson, new TypeToken<List<DatosGenericos>>() {
			}.getType());
			for (DatosGenericos dg : datos) {
				String atb = gson.toJson(dg.getAttribute_id());
				System.out.println(atb);
				if (atb.contains("\"1\",\"Colores\"")) {
					datosDevolver.add(dg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(datosDevolver);
	}

	public String obtenerProductos() {

		Gson gson = new Gson();
		List<Articulo_Encuentra> datosDevolver = new ArrayList<>();

		try {
			int uid = devolverUID();
			List<Articulo_Odoo> articulos = new ArrayList<>();

			final XmlRpcClient models = getModels();
			//
			@SuppressWarnings("unchecked")
			final List<Object> arts = Arrays.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(),
					uid, ccls.getPassword(), "product.product", "search_read", Collections.emptyList(), new HashMap() {
						{
							put("fields", Arrays.asList("name", "sexo", "temporada", "ean13", "marca", "categoria",
									"default_code", "talle_proveedor", "color_proveedor", "seller_ids")); // "attribute_line_ids"
						}
					})));

			List<Articulo_Odoo> datos = darDatos(arts);
			for (Articulo_Odoo art : datos) {
				int marca = 0;
				int sellerIds = 0;
				int categoria = 0;
				int temporada = 0;
				int id = 0;
				String ean13 = "";
				String name = "";
				String talleProveedor = "";
				String sexo = "";
				String colorProveedor = "";
				String defaultCode = "";
				if (art.getMarca().getClass() != Boolean.class) {
					ArrayList<Object> marcaB = (ArrayList<Object>) art.getMarca();
					if (!marcaB.isEmpty()) {
						marca = (int) ((double) marcaB.get(0));
					}
				}
				if (art.getSeller_ids().getClass() != Boolean.class) {
					ArrayList<Object> seller = (ArrayList<Object>) art.getSeller_ids();
					if (!seller.isEmpty()) {
						sellerIds = (int) ((double) seller.get(0));
					}
				}
				if (art.getCategoria().getClass() != Boolean.class) {
					ArrayList<Object> cat = (ArrayList<Object>) art.getCategoria();
					if (!cat.isEmpty()) {
						categoria = (int) ((double) cat.get(0));
					}
				}
				if (art.getTemporada().getClass() != Boolean.class) {
					ArrayList<Object> temp = (ArrayList<Object>) art.getTemporada();
					if (!temp.isEmpty()) {
						temporada = (int) ((double) temp.get(0));
					}
				}
				if (art.getId().getClass() != Boolean.class && art.getId().getClass() == Double.class) {
					id = (int) ((double) art.getId());
				}
				if (art.getEan13().getClass() == String.class) {
					ean13 = (String) art.getEan13();
				}
				if (art.getName().getClass() == String.class) {
					name = (String) art.getName();
				}
				if (art.getTalle_proveedor().getClass() == String.class) {
					talleProveedor = (String) art.getTalle_proveedor();
				}
				if (art.getSexo().getClass() == String.class) {
					sexo = (String) art.getSexo();
				}
				if (art.getColor_proveedor().getClass() == String.class) {
					colorProveedor = (String) art.getColor_proveedor();
				}
				if (art.getDefault_code().getClass() == String.class) {
					defaultCode = (String) art.getDefault_code();
				}

				Articulo_Encuentra ae = new Articulo_Encuentra(id, name, sexo, ean13, defaultCode, talleProveedor,
						colorProveedor, marca, categoria, temporada, sellerIds);
				datosDevolver.add(ae);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(datosDevolver);
	}

	private List<Articulo_Odoo> darDatos(List<Object> arts) {

		List<Articulo_Odoo> retorno = new ArrayList<>();
		Gson gson = new Gson();

		try {
			for (Object o : arts) {
				try {
					HashMap<String, Arrays> coso = (HashMap<String, Arrays>) o;

					JSONObject jotason = new JSONObject(coso);
					Articulo_Odoo aodo = null;
					// System.out.println(jotason);
					try {
						aodo = gson.fromJson(jotason.toString(), Articulo_Odoo.class);
					} catch (Exception e) {
						e.printStackTrace();
						// Articulo_Odoo_1 aodo1 =
						// gson.fromJson(jotason.toString(),Articulo_Odoo_1.class);
						// aodo = new Articulo_Odoo(aodo1);

						// System.out.println("#################### "+gson.toJson(aodo1));
					}
					retorno.add(aodo);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public List<StockArticulos> buscarStockArticulo(List<ArticuloCantidadEncuentra> articulos, 
			Map<Integer, Integer> excluir_deps) { // HashMap<String, List<String>>
		int uid = devolverUID();
		List<StockArticulos> listStock = new ArrayList<>();
		List<Integer> idsUbicaciones = new ArrayList<>();
		try {
			idsUbicaciones = obtenerIDsUbicaciones(uid);
			final XmlRpcClient models = getModels();

			Gson gson = new Gson();

			for (ArticuloCantidadEncuentra artCE : articulos) 
			{
				if(!artCE.getIdArticulo().equals(""))
				{
					System.out.println("consulto por articulo"+artCE.getIdArticulo());
					List<Object> stockArts = new ArrayList<>();
					if (artCE.getIdDeposito() > 0) {
						stockArts = Arrays
								.asList((Object[]) models.execute("execute_kw",
										Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.quant", "search_read",
												Arrays.asList(Arrays.asList(
														Arrays.asList("location_id", "=", artCE.getIdDeposito()),
														Arrays.asList("product_id", "=", artCE.getIdArticulo()))),
												new HashMap() {
													{
														put("fields", Arrays.asList("product_id", "qty", "location_id"));
													}
												})));
					} else {

						//idsUbicaciones.add(139);
						
						stockArts = Arrays.asList((Object[]) models.execute("execute_kw",
								Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.quant", "search_read",
										Arrays.asList(
												Arrays.asList(Arrays.asList("product_id", "=", artCE.getIdArticulo()))),

										new HashMap() {
											{
												put("fields", Arrays.asList("product_id", "qty", "location_id"));
											}
										})));
					}
					String json = gson.toJson(stockArts);
					List<HashMap<String, Object>> artCant = new ArrayList<>();
					artCant = gson.fromJson(json, new TypeToken<List<HashMap<String, Object>>>() {
					}.getType());
					
					
					
					Map<Integer, Integer> locationQty = new HashMap<>();
					double totalCant = 0;
					int idDep = 0;
					String producto = "";
					for (HashMap<String, Object> art : artCant) {
						int deposito = (int)((double) ((ArrayList<Object>)art.get("location_id")).get(0));
						String[] parts = ((String) ((ArrayList<Object>)art.get("product_id")).get(1)).split("]");
						producto = parts[0].replace("[", "");
						int cantidad = (int)((double) art.get("qty"));
						if(idsUbicaciones.contains(deposito)) {
							if(locationQty.containsKey(deposito)) {
								int qtyN = locationQty.get(deposito) + cantidad;
								locationQty.put(deposito, qtyN);
							} else {
								locationQty.put(deposito, cantidad);
							}
						}
					}
					
					for(Map.Entry<Integer, Integer> entry : locationQty.entrySet()) {
						if(entry.getValue() != null) {
							if(excluir_deps.get(entry.getKey()) == null) {
								StockArticulos sa = new StockArticulos(entry.getKey(), producto, (int)artCE.getCantidad(), entry.getValue());
								listStock.add(sa);
							}							
						}
						
					}
					/*if(artCant.size() > 0 && idDep != 0) {
						StockArticulos sa = new StockArticulos(idDep, artCE.getIdArticulo(), (int)artCE.getCantidad(), (int)totalCant);
						listStock.add(sa);
					}*/
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listStock;
	}
	
	public StockArticulos buscarStockArticulo(ArticuloCantidadEncuentra articulo, int uid) { // HashMap<String, List<String>>
		
		StockArticulos sa = null;
		try {
			final XmlRpcClient models = getModels();

			Gson gson = new Gson();

				List<Object> stockArts;
				if (articulo.getIdDeposito() > 0) {
					stockArts = Arrays
							.asList((Object[]) models.execute("execute_kw",
									Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.quant", "search_read",
											Arrays.asList(Arrays.asList(
													Arrays.asList("location_id", "=", articulo.getIdDeposito()),
													Arrays.asList("product_id", "=", articulo.getIdArticulo()))),
											new HashMap() {
												{
													put("fields", Arrays.asList("product_id", "qty", "location_id"));
												}
											})));
				} else {

					stockArts = Arrays.asList((Object[]) models.execute("execute_kw",
							Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.quant", "search_read",
									Arrays.asList(
											Arrays.asList(Arrays.asList("product_id", "=", articulo.getIdArticulo()))),

									new HashMap() {
										{

											put("fields", Arrays.asList("product_id", "qty", "location_id"));
										}
									})));
				}
				String json = gson.toJson(stockArts);
				List<ArticuloCantidadOdoo> artCant = gson.fromJson(json, new TypeToken<List<ArticuloCantidadOdoo>>() {
				}.getType());

				//Map<String, Double> articulosLocationQty = new HashMap<String, Double>();
				double totalCant = 0;
				for (ArticuloCantidadOdoo ac : artCant) {
					totalCant += (double)ac.getQty();
				}
				if(!artCant.isEmpty()) {
					sa = new StockArticulos(articulo.getIdDeposito(), articulo.getIdArticulo(), (int)articulo.getCantidad(), (int)totalCant);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sa;
	}
	

	public List<StockDeposito> obtenerStockArticulos() { // HashMap<String, List<String>>
		int uid = devolverUID();
		List<StockDeposito> artCantE = new ArrayList<>();
		Map<String, Double> articulosLocationQty = new HashMap<>();
		List<ArticuloCantidadOdoo> artCant = new ArrayList<>();

		try {
			final XmlRpcClient models = getModels();

			Gson gson = new Gson();

			final List ids = Arrays.asList((Object[]) models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.location", "search",
							Arrays.asList(Arrays.asList(Arrays.asList("usage", "=", "internal"))), new HashMap() {
								{
								}
							})));

			final List<Object> stockArts = Arrays.asList((Object[]) models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.quant", "search_read",
							Arrays.asList(Arrays.asList(Arrays.asList("location_id", "in", ids))), new HashMap() {
								{
									put("fields", Arrays.asList("product_id", "qty", "location_id"));
								}
							})));

			String json = gson.toJson(stockArts);
			System.out.println(json);
			artCant = gson.fromJson(json, new TypeToken<List<ArticuloCantidadOdoo>>() {
			}.getType());

			for (ArticuloCantidadOdoo ac : artCant) {
				if ((Double) ac.getQty() > 0) {
					String desc = ac.getProduct_id().toString();
					String[] parts = desc.split(", ");
					String[] parts2 = parts[1].split("]");
					String idArticulo = validarIdArticuloIsla(parts2[0].substring(1, parts2[0].length()));
					String parts3[] = ac.getLocation_id().toString().split(",");
					String idDeposito = parts3[0].substring(1, parts3[0].length());

					String clave = idArticulo + "#Enc#" + idDeposito;

					if (articulosLocationQty.containsKey(clave)) {
						double qty = articulosLocationQty.get(clave);
						articulosLocationQty.replace(clave, qty, qty += Double.parseDouble(ac.getQty().toString()));
					} else {
						articulosLocationQty.put(clave, Double.parseDouble(ac.getQty().toString()));
					}
				}

			}

			for (Map.Entry<String, Double> entry : articulosLocationQty.entrySet()) {
				String[] parts = entry.getKey().split("#Enc#");
				int deposito = (int) Double.parseDouble(parts[1]);
				StockDeposito ace = new StockDeposito(deposito + "", parts[0], entry.getValue());
				System.out.println(gson.toJson(ace));
				artCantE.add(ace);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return artCantE;
	}

	public String validarIdArticuloIsla(String idArticulo) {
		try {
			if (idArticulo.contains("\"")) {
				idArticulo = idArticulo.replace("\"", "\\\"");
			}
			if (idArticulo.contains("'")) {
				idArticulo = idArticulo.replace("'", "\\\'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idArticulo;
	}

	public int buscarPickingType(int origen, int destino, int uid) {
		int id = 0;
		try {
			final XmlRpcClient models = getModels();
			final List ids = Arrays.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid,
					ccls.getPassword(), "stock.picking.type", "search_read", Arrays.asList(Arrays.asList(

							Arrays.asList("default_location_src_id", "=", Arrays.asList(origen)),
							Arrays.asList("default_location_dest_id", "=", Arrays.asList(destino)))),
					new HashMap() {
						{
							put("fields", Arrays.asList("id"));
						}
					})));
			Gson gson = new Gson();
			if(!ids.isEmpty()) {
				String[] parts = (gson.toJson(ids.get(0))).split(":");
				parts[1] = parts[1].replace("}", "");
				id = Integer.parseInt(parts[1]);
			} /*else {
				String[] parts = (gson.toJson(ids)).split(":");
				parts[1] = parts[1].replace("}", "");
				parts[1] = parts[1].replace("]", "");
				id = Integer.parseInt(parts[1]);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public Integer buscarClientePorCI(String documento, int uid) {
		int id = 0;
		try {
			final XmlRpcClient models = getModels();

			final Object obj = models
					.execute(
							"execute_kw", Arrays
									.asList(ccls.getDb(), uid, ccls.getPassword(), "res.partner", "search_read",
											Arrays.asList(Arrays.asList(

													//Arrays.asList("identification_id", "=", documento))),
													Arrays.asList("numero_doc", "=", documento))),
											new HashMap() {
												{
													put("fields", Arrays.asList("id"));
												}
											}));
			Gson gson = new Gson();
			String json = gson.toJson(obj);
			String[] parts = json.split(":");
			parts[1] = parts[1].replace("}", "");
			parts[1] = parts[1].replace("]", "");
			parts[1] = parts[1].replace(",{\"id\"", "");

			id = Integer.parseInt(parts[1]);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public List<Integer> crearAlbaranInterno(PedidoAlbaran pa, Long idPedido) {

		int uid = devolverUID();
		List<Integer> idsAlbaranes = new ArrayList<>();
		try {
			final XmlRpcClient models = getModels();
			boolean articuloSinStock = false;
			// Arrays.asList(227383)) -> id partner
			while (!articuloSinStock && !pa.getAec().isEmpty()) { // voy a recorrer la lista de articulos e ir sacando los que
												// tienen mismo cliente, para crear albaranes diferentes.

				ArticuloCantidadEncuentra primerArt = pa.getAec().get(0); // me guardo el primer elemento si la lista no esta vacia me guardo el documento del primer cliente y el deposito al que se pidio para compararlo con el resto
				String cliente = primerArt.getCliente().getDocumentoNro();
				int depOrigen = primerArt.getIdDeposito();

				List<ArrayList<Object>> movelinesPrueba = new ArrayList<>();

				// Agrego el primer elemento al hash
				ArrayList<Object> moveline1 = new ArrayList<>();
				moveline1.add(0);
				moveline1.add(false);
				HashMap<Object, Object> moveLine = new HashMap<>();

				moveLine.put("product_id", obtenerIDArticuloOdoo(primerArt.getIdArticulo(), uid));
				moveLine.put("product_uom_qty", primerArt.getCantidad());
				moveLine.put("product_uom", 1);
				moveLine.put("product_uos_qty", primerArt.getCantidad()); 
				moveLine.put("location_id", primerArt.getIdDeposito());
				moveLine.put("location_dest_id", pa.getDestino());
				moveLine.put("procure_method", "make_to_stock");
				moveLine.put("name", "[" + primerArt.getIdArticulo() + "]");
				moveline1.add(moveLine);
				movelinesPrueba.add(moveline1);
				pa.getAec().remove(primerArt);

				List<ArticuloCantidadEncuentra> articulosCantidadDestino2 = new ArrayList<>(
						pa.getAec());
				for (ArticuloCantidadEncuentra artCant : articulosCantidadDestino2) { // recorro todos los articulos y
																						// busco los que tengan el mismo
																						// cliente
					StockArticulos sk = buscarStockArticulo(artCant, uid);
					if(sk != null && sk.getStockDisponible() >= sk.getStockSolicitado()) {
						if (artCant.getCliente().getDocumentoNro().equalsIgnoreCase(cliente) && artCant.getIdDeposito() == depOrigen) {
							ArrayList<Object> moveline2 = new ArrayList<>();
							moveline2.add(0);
							moveline2.add(false);
							HashMap<Object, Object> moveLine2 = new HashMap<>();
							moveLine2.put("product_id", obtenerIDArticuloOdoo(artCant.getIdArticulo(), uid));
							moveLine2.put("product_uom_qty", artCant.getCantidad()); // me manda siempre en 1
							moveLine2.put("product_uom", 1);
							moveLine2.put("product_uos_qty", artCant.getCantidad());
							moveLine2.put("location_id", artCant.getIdDeposito());
							moveLine2.put("location_dest_id", pa.getDestino());
							moveLine2.put("procure_method", "make_to_stock");
							moveLine2.put("name", "[" + artCant.getIdArticulo() + "]");
							moveline2.add(moveLine2);
							movelinesPrueba.add(moveline2);
							pa.getAec().remove(artCant);
						}
					} else {
						articuloSinStock = true;
						System.out.println("SIN STOCK");
					}
				}
				if(articuloSinStock == false) {
					int idPersona = buscarClientePorCI(primerArt.getCliente().getDocumentoNro(), uid);
					if(idPersona == 0) {
						LaIsla li = new LaIsla();						
						Ordenes ov = li.OrdenVentaFenicio(idPedido);
						li.validarCliente (ov, primerArt.getCliente());
						idPersona = AltaCliente(ov, uid);
					}
					int idCliente = idPersona;
					@SuppressWarnings("unchecked")
					final Integer id = (Integer) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid,
							ccls.getPassword(), "stock.picking", "create", Arrays.asList(new HashMap() {
								{
									put("partner_id", idCliente);
									put("picking_type_id", buscarPickingType(primerArt.getIdDeposito(), pa.getDestino(), uid)); // metodo para  buscar el type id
									put("picking_location_id", primerArt.getIdDeposito());
									put("picking_location_dest_id", pa.getDestino());
									put("move_lines", movelinesPrueba);
								}
							})

					));
					idsAlbaranes.add(id);
					marcarPorHacer(id);
					int idAlb = comprobarDisponibilidad(id);
					if(idAlb > 0) {
						idsAlbaranes = new ArrayList<>();
						idsAlbaranes.add(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idsAlbaranes;
	}
		

	public void marcarPorHacer(int id) {
		try {
			int uid = devolverUID();
			final XmlRpcClient models = getModels();
				models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
						"action_confirm", Arrays.asList(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	public void reIntentarDisponibilidad(List<Integer> ids) {

		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();
			for (Integer id : ids) {
				models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
						"rereserve_pick", Arrays.asList(id)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Integer comprobarDisponibilidad(int id) {
		int idAlbaran = 0;
		try {
			int uid = devolverUID();
			final XmlRpcClient models = getModels();
				models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
						"action_assign", Arrays.asList(id)));
				
				Object obj = models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
						"read", Arrays.asList(id),
						new HashMap() {
							{
								put("fields", Arrays.asList("id"));
							}
						}));
				Gson gson = new Gson();
				String[] result = (gson.toJson(obj)).split(":");
				
				String resultId = result[1].replace("}", "");
				idAlbaran = Integer.parseInt(resultId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return idAlbaran;
	}

	@SuppressWarnings("unchecked")
	public void realizarTransferencia(int id) {

		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();
			List<Object> transf_details = Arrays.asList((Object[]) models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.transfer_details", "search",
							Arrays.asList(Arrays.asList(Arrays.asList("picking_id", "=", id))))));
			
			//for (Integer id : ids) {
				final Integer idTransf = (Integer) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid,
						ccls.getPassword(), "stock.transfer_details", "do_detailed_transfer",  transf_details));
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void realizarTransferencia2(int id) {

		@SuppressWarnings("unchecked")
		int uid = devolverUID();
		try {
			final XmlRpcClient models = new XmlRpcClient() {
				{
					setConfig(new XmlRpcClientConfigImpl() {
						{
							setServerURL(new URL(String.format("%s/xmlrpc/2/object", ccls.getUrl())));
						}
					});
				}
			};
			

			// 579081
			//for (Integer id : ids) {
				Object do_enter_transfer_details = models.execute("execute_kw",
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
								"do_enter_transfer_details", Arrays.asList(Arrays.asList(id))));
				System.out.println(do_enter_transfer_details + "---");

				List<Object> transf_details = Arrays.asList((Object[]) models.execute("execute_kw",
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.transfer_details", "search",
								Arrays.asList(Arrays.asList(Arrays.asList("picking_id", "=", id))))));

				System.out.println(transf_details);
				/*
				 * final Object idTransf = models.execute("execute_kw", Arrays.asList( db, uid,
				 * password, "stock.transfer_details", "do_detailed_transfer",
				 * Arrays.asList(transf_details)));
				 */
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EstadoAlbaranes> verEstadoAlbaranes(List<Integer> ids) {
		List<EstadoAlbaranes> estados = new ArrayList<>();
		int uid = devolverUID();
		Gson gson = new Gson();
		try {
			final XmlRpcClient models = getModels();
			for (Integer id : ids) {
				final List objects = Arrays.asList((Object[]) models.execute("execute_kw",
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking", "search_read",
								Arrays.asList(Arrays.asList(

										Arrays.asList("id", "=", id))))));
				String json = gson.toJson(objects);
				json = json.replace("[", "");
				json = json.replace("]", "");
				EstadoAlbaranes ea = gson.fromJson(json, EstadoAlbaranes.class);
				if (ea != null) {
					estados.add(ea);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estados;
	}
	
	public List<EstadoAlbaranes> albaranesListosParaRecibir(int idDeposito) {
		List<Integer> pickingsType = new ArrayList<>();
		List<EstadoAlbaranes> estados = new ArrayList<>();
		int uid = devolverUID();
		Gson gson = new Gson();
		try {
			final XmlRpcClient models = getModels();
				final List objects = Arrays.asList((Object[]) models.execute("execute_kw",
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking", "search_read",
								Arrays.asList(Arrays.asList(
										Arrays.asList("picking_location_dest_id", "=", idDeposito), Arrays.asList("state", "=", "assigned"), Arrays.asList("create_uid", "=", uid)),
										new HashMap() {
									{
										put("fields", Arrays.asList("id"));
									}
								}))));
				String json = gson.toJson(objects);
				System.out.println(json);
				estados = gson.fromJson(json, new TypeToken<List<EstadoAlbaranes>>(){}.getType());
				for (EstadoAlbaranes estadoAlbaranes : estados) {
					estadoAlbaranes.setState("Listo para transferir");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estados;
	}
	//buscarPickingType

	public EstadoAlbaranes verEstadoAlbaranesFiltrados(int filtro, int id) {
		String stateStr = "";
		EstadoAlbaranes ea = null;
		switch (filtro) {
		case 1:
			stateStr = "confirmed";
			break;
		case 2:
			stateStr = "assigned";
			break;
		case 3:
			stateStr = "done";
			break;
		case 4:
			stateStr = "cancel";
			break;
		default:
			break;
		}
		//List<EstadoAlbaranes> estados = new ArrayList<>();
		int uid = devolverUID();
		Gson gson = new Gson();
		try {
			final XmlRpcClient models = getModels();
			//for (Integer id : ids) {
				final Object objects = models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
						"stock.picking", "search_read",
						Arrays.asList(
								Arrays.asList(Arrays.asList("state", "=", stateStr), Arrays.asList("id", "=", id))),
						new HashMap() {
							{
								put("fields", Arrays.asList("id", "state"));
							}
						}));
				String json = gson.toJson(objects);
				json = json.replace("[", "");
				json = json.replace("]", "");
				ea = gson.fromJson(json, EstadoAlbaranes.class);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ea;
	}

	public void borrarAlbaran(ArrayList<Integer> ids) {
		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();
			for (Integer id : ids) {
				models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
						"unlink", Arrays.asList(Arrays.asList(id))));
				// check if the deleted record is still in the database
				Arrays.asList((Object[]) models.execute("execute_kw",
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking", "search",
								Arrays.asList(Arrays.asList(Arrays.asList("id", "=", id))))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancelarAlbaran(List<Integer> ids) {

		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();
			for (Integer id : ids) {
				models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
						"action_cancel", Arrays.asList(id)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void retomarTransferencia( int id ) {

		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();

			models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
					"action_back_to_draft", Arrays.asList(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public XmlRpcClient getModels() {
		try {
			final XmlRpcClient models = new XmlRpcClient() {
				{
					setConfig(new XmlRpcClientConfigImpl() {
						{
							setServerURL(new URL(String.format("%s/xmlrpc/2/object", ccls.getUrl())));
							
						}
					});
				}
			};
			return models;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@SuppressWarnings({ "unchecked", "finally" })
	public int recibirAlbaran(int idAlbaran, boolean pickiDup) {

		int uid = devolverUID();
		int pdInt = 0;
		try {
			final XmlRpcClient models = getModels();
				
				  @SuppressWarnings("unchecked")
				final Object transf_details = models.execute("execute_kw",
				 Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
				 "search_read", Arrays.asList(Arrays.asList(Arrays.asList("id", "=", idAlbaran))),
							new HashMap() {
						{
							put("fields",
									Arrays.asList("picking_duplication"));
						}
					}));
				  
				  Gson gson = new Gson();
				  String result = gson.toJson(transf_details);
				  result = result.replace("[{", "{");
				  result = result.replace("}]", "}");
				  System.out.println(result);
				  PickingDuplication pd = gson.fromJson(result, PickingDuplication.class);
				  pdInt = (int)((double) pd.getPicking_duplication().get(0));
				  System.out.println(pdInt);
				  if(pickiDup) {
					  pdInt = idAlbaran;
				  }
						
				models.execute("execute_kw",
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking", "transfer_picking_by_api",
								Arrays.asList(Arrays.asList(pdInt),
										new HashMap() {
									{
										put("lang", "es_UY");
										put("tz", "America/Montevideo");
										put("uid", uid);
									}
								})));
				  
		} catch (Exception e) {
			e.printStackTrace();
			pdInt = 0;
		} finally {
			return pdInt;
		}
	}

	public int obtenerIDArticuloOdoo(String defaultCode, int uid) {
		Articulo_Odoo articulos = null;
		int id = 0;
		try {
			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final Object arts = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "product.product", "search_read",
							Arrays.asList(Arrays.asList(Arrays.asList("default_code", "=", defaultCode))),
							new HashMap() {
								{
									put("fields",
											Arrays.asList("name", "sexo", "temporada", "ean13", "marca", "categoria",
													"default_code", "talle_proveedor", "color_proveedor",
													"seller_ids"));
								}
							}));

			Gson gson = new Gson();
			String json = gson.toJson(arts);
			List<Articulo_Odoo> datosList = gson.fromJson(json, new TypeToken<List<Articulo_Odoo>>() {
			}.getType());
			Articulo_Odoo datos = datosList.get(0);

			if (datos.getId().getClass() != Boolean.class && datos.getId().getClass() == Double.class) {
				id = (int) ((double) datos.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("");
			System.out.println("");
			System.out.println("ARTICULO NO ENCONTRADO ==> "+defaultCode);
			System.out.println("");
			System.out.println("");
		}
		return id;
	}
	
	public Articulo_Odoo obtenerArticuloOdoo(String defaultCode, int uid) {
		Articulo_Odoo datos = null;
		try {
			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final Object arts = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "product.product", "search_read",
							Arrays.asList(Arrays.asList(Arrays.asList("default_code", "=", defaultCode))),
							new HashMap() {
								{
									put("fields",
											Arrays.asList("name", "sexo", "temporada", "ean13", "marca", "categoria",
													"default_code", "talle_proveedor", "color_proveedor",
													"seller_ids", "list_price"));
								}
							}));

			Gson gson = new Gson();
			String json = gson.toJson(arts);
			List<Articulo_Odoo> datosList = gson.fromJson(json, new TypeToken<List<Articulo_Odoo>>() {
			}.getType());
			datos = datosList.get(0);

			if (datos.getId().getClass() != Boolean.class && datos.getId().getClass() == Double.class) {
				return datos;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("");
			System.out.println("");
			System.out.println("ARTICULO NO ENCONTRADO ==> "+defaultCode);
			System.out.println("");
			System.out.println("");
			return null;
		}
		
	}

	public int obtenerIDArticuloOdoo(String defaultCode) {
		int uid = devolverUID();
		Articulo_Odoo articulos = null;
		int id = 0;
		try {
			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final Object arts = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "product.product", "search_read",
							Arrays.asList(Arrays.asList(Arrays.asList("default_code", "=", defaultCode))),
							new HashMap() {
								{
									put("fields",
											Arrays.asList("name", "sexo", "temporada", "ean13", "marca", "categoria",
													"default_code", "talle_proveedor", "color_proveedor",
													"seller_ids"));
								}
							}));

			Gson gson = new Gson();
			String json = gson.toJson(arts);
			List<Articulo_Odoo> datosList = gson.fromJson(json, new TypeToken<List<Articulo_Odoo>>() {
			}.getType());
			Articulo_Odoo datos = datosList.get(0);

			if (datos.getId().getClass() != Boolean.class && datos.getId().getClass() == Double.class) {
				id = (int) ((double) datos.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public Articulo_Encuentra obtenerDatosProducto(String defaultCode) {
		int uid = devolverUID();
		Articulo_Odoo articulos = null;
		Articulo_Encuentra ae = null;
		try {
			final XmlRpcClient models = getModels();

			@SuppressWarnings("unchecked")
			final Object arts = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "product.product", "search_read",
							Arrays.asList(Arrays.asList(Arrays.asList("default_code", "=", defaultCode))),
							new HashMap() {
								{
									put("fields",
											Arrays.asList("name", "sexo", "temporada", "ean13", "marca", "categoria",
													"default_code", "talle_proveedor", "color_proveedor",
													"seller_ids"));
								}
							}));

			Gson gson = new Gson();
			String json = gson.toJson(arts);
			List<Articulo_Odoo> datosList = gson.fromJson(json, new TypeToken<List<Articulo_Odoo>>() {
			}.getType());
			Articulo_Odoo datos = datosList.get(0);
			int marca = 0;
			int sellerIds = 0;
			int categoria = 0;
			int temporada = 0;
			int id = 0;
			String ean13 = "";
			String name = "";
			String talleProveedor = "";
			String sexo = "";
			String colorProveedor = "";
			String default_code = "";
			if (datos.getMarca().getClass() != Boolean.class) {
				ArrayList<Object> marcaB = (ArrayList<Object>) datos.getMarca();
				if (!marcaB.isEmpty()) {
					marca = (int) ((double) marcaB.get(0));
				}
			}
			if (datos.getSeller_ids().getClass() != Boolean.class) {
				ArrayList<Object> seller = (ArrayList<Object>) datos.getSeller_ids();
				if (!seller.isEmpty()) {
					sellerIds = (int) ((double) seller.get(0));
				}
			}
			if (datos.getCategoria().getClass() != Boolean.class) {
				ArrayList<Object> cat = (ArrayList<Object>) datos.getCategoria();
				if (!cat.isEmpty()) {
					categoria = (int) ((double) cat.get(0));
				}
			}
			if (datos.getTemporada().getClass() != Boolean.class) {
				ArrayList<Object> temp = (ArrayList<Object>) datos.getTemporada();
				if (!temp.isEmpty()) {
					temporada = (int) ((double) temp.get(0));
				}
			}
			if (datos.getId().getClass() != Boolean.class && datos.getId().getClass() == Double.class) {
				id = (int) ((double) datos.getId());
			}
			if (datos.getEan13().getClass() == String.class) {
				ean13 = (String) datos.getEan13();
			}
			if (datos.getName().getClass() == String.class) {
				name = (String) datos.getName();
			}
			if (datos.getTalle_proveedor().getClass() == String.class) {
				talleProveedor = (String) datos.getTalle_proveedor();
			}
			if (datos.getSexo().getClass() == String.class) {
				sexo = (String) datos.getSexo();
			}
			if (datos.getColor_proveedor().getClass() == String.class) {
				colorProveedor = (String) datos.getColor_proveedor();
			}
			if (datos.getDefault_code().getClass() == String.class) {
				default_code = (String) datos.getDefault_code();
			}

			ae = new Articulo_Encuentra(id, name, sexo, ean13, default_code, talleProveedor, colorProveedor, marca,
					categoria, temporada, sellerIds);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ae;
	}

	public void obtenerVendedorEncuentra() {
		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();
			final List<Object> arts = Arrays.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(),
					uid, ccls.getPassword(), "pos.config", "search_read", Collections.emptyList(), new HashMap() {
						{
							put("fields", Arrays.asList("pos_config_id", "name"));
						}
					})));
			Gson gson = new Gson();
			System.out.println(gson.toJson(arts));
			CrearArchivo ca = new CrearArchivo();
			ca.imprimirJson(gson.toJson(arts));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void obtenerCaja() {
		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();
			final List<Object> arts = Arrays.asList((Object[]) models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "pos.session", "search_read",

							Arrays.asList(Arrays.asList(
									Arrays.asList("state", "not in", Arrays.asList("closed", "closing_control")))),
							/*
							 * Arrays.asList(Arrays.asList(Arrays.asList("user_id", "=", "Encuentra"),
							 * Arrays.asList("state", "in", Arrays.asList("opening_control")))),
							 */
							new HashMap() {
								{
									put("fields", Arrays.asList("id"));
									// opening_control
								}
							})));
			Gson gson = new Gson();
			System.out.println(gson.toJson(arts));
			CrearArchivo ca = new CrearArchivo();
			ca.imprimirJson(gson.toJson(arts));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer abrirSession() {
		int uid = devolverUID();
		int idSession = 0;
		try {
			final XmlRpcClient models = getModels();
			idSession = (int) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
					"pos.session", "create", Arrays.asList(new HashMap() {
						{
							put("config_id", 32);
						}
					})));
			validarSession(idSession, uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idSession;
	}

	public void validarSession(int idSession, int uid) {
		try {
			final XmlRpcClient models = getModels();
			final Object idSessionV = models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
					"pos.session", "open_cb", Arrays.asList(idSession), new HashMap() {
						{
						}
					}));
			Gson gson = new Gson();
			System.out.println(gson.toJson(idSessionV));
			CrearArchivo ca = new CrearArchivo();
			ca.imprimirJson(gson.toJson(idSessionV));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer crearCliente(Cliente c) {
		Integer idCliente = 0;
		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();

			System.out.println(c.getDocumentoNro());
			System.out.println(c.getRut());
			System.out.println(c.getNombre());
			System.out.println(c.getCalle());
			System.out.println(c.getTelefono());
			System.out.println(c.getEmail());
			
			idCliente = (Integer) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
					"res.partner", "create_from_ui", Arrays.asList(new HashMap() {
						{
							if (c.getRut() == null || c.getRut().equalsIgnoreCase("") || c.getRut().equalsIgnoreCase("0")) {
								put("is_company", false);
								put("tipo_documento", 3);
								put("numero_doc", c.getDocumentoNro());
								System.out.println(c.getDocumentoNro());
							} else {
								put("is_company", true);
								put("tipo_documento", 2);
								put("numero_doc", c.getRut());
								System.out.println(c.getRut());
							}
							put("name", c.getNombre() + (c.getApellido() != null ? " " + c.getApellido() : ""));
							put("street", c.getCalle() + (c.getApellido() != null ? " - " + c.getNroPuerta() : ""));
							if(c.getEmail() != null) {
								put("mobile", c.getTelefono());
							}
							if(c.getEmail() != null) {
								put("email", c.getEmail());
							}
							put("consumidor_final", true);
						}
					})));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idCliente;
	}
	
	public Integer crearCliente(Cliente c, int uid) {
		Integer idCliente = 0;
		try {
			final XmlRpcClient models = getModels();

			System.out.println(c.getDocumentoNro());
			System.out.println(c.getRut());
			System.out.println(c.getNombre()+" "+c.getApellido());
			System.out.println(c.getCalle());
			System.out.println(c.getTelefono());
			System.out.println(c.getEmail());
			
			idCliente = (Integer) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
					"res.partner", "create_from_ui", Arrays.asList(new HashMap() {
						{
							if (c.getDocumentoTipo().equalsIgnoreCase("DOCUMENTO_IDENTIDAD")) {
								put("is_company", false);
								put("tipo_documento", 3);
								put("numero_doc", c.getDocumentoNro());
								System.out.println(c.getDocumentoNro());
							}
							else if(c.getDocumentoTipo().equalsIgnoreCase("RUT")) {
								put("is_company", true);
								put("tipo_documento", 2);
								put("numero_doc", c.getRut());
								System.out.println(c.getRut());
							}
							else {
								put("is_company", false);
								put("tipo_documento", 5);
								put("numero_doc", c.getDocumentoNro());
								System.out.println(c.getDocumentoNro());
							}
							put("name", c.getNombre() + (c.getApellido() != null ? " " + c.getApellido() : ""));
							put("street", c.getCalle());
							if(c.getTelefono() != null) {
								put("mobile", c.getTelefono());
							}
							if(c.getEmail() != null) {
								put("email", c.getEmail());
							}
							put("consumidor_final", true);
						}
					})));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idCliente;
	}

	public void cerrarSesion(int idSession) {
		int uid = devolverUID();
		final XmlRpcClient models = getModels();
		try {
			final Object id = models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
					"pos.session.opening", "create", Arrays.asList(new HashMap() {
						{
							put("pos_session_id", idSession);
						}
					})));

			models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "pos.session.opening",
							"open_existing_session_cb_close", Arrays.asList(Arrays.asList(id),

									new HashMap() {
										{
											put("lang", "es_UY");
											put("tz", "America/Montevideo");
											put("uid", uid);
										}
									})));

			models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "pos.session", "write",
							Arrays.asList(Arrays.asList(idSession),

									new HashMap() {
										{
											put("close_in_background", true);
										}
									})));
			

			models.execute(
				    "exec_workflow", Arrays.asList(
				    		ccls.getDb(), uid, ccls.getPassword(),
				        "pos.session", "close", idSession));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * models.execute("execute_kw", asList( db, uid, password, "res.partner",
	 * "write", asList( asList(id), new HashMap() {{ put("name", "Newer Partner");
	 * }} ) )); // get record name after having changed it
	 * asList((Object[])models.execute("execute_kw", asList( db, uid, password,
	 * "res.partner", "name_get", asList(asList(id)) )));
	 */

	public FormaPago formasDePago(int id) {

		int uid = devolverUID();
		FormaPago fpDev = null;
		try {
		List<Object> idsObtenidos = new ArrayList<>();
		final XmlRpcClient models = getModels();
			final Object fp = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "account.journal", "search_read",
							Arrays.asList(Arrays.asList(Arrays.asList("id", "=", id))), new HashMap() {
								{
									put("fields", Arrays.asList("id", "default_credit_account_id", "name"));
								}
							}));
			idsObtenidos.add(fp);
		
		Gson gson = new Gson();
		String idsObtenidosJSON = gson.toJson(idsObtenidos);
		List<ArrayList<Object>> json = gson.fromJson(idsObtenidosJSON, new TypeToken<ArrayList<ArrayList<Object>>>() {
		}.getType());

		for (ArrayList<Object> al : json) {
			String jsonParse = gson.toJson(al.get(0));
			fpDev = gson.fromJson(jsonParse, FormaPago.class);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fpDev;
	}
	
	public int account_bank_statement(int journal_id , int pos_session_id) {

		int statement_id = 0;
		int uid = devolverUID();
		FormaPago fpDev = null;
		try {
		//List<Object> idsObtenidos = new ArrayList<>();
		final XmlRpcClient models = getModels();
		Object idsObtenidos =  models.execute("execute_kw",
				Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "account.bank.statement", "search_read",
						Arrays.asList(Arrays.asList(Arrays.asList("journal_id", "=", journal_id),
													Arrays.asList("pos_session_id", "=", pos_session_id))), new HashMap() {
							{
								put("fields", Arrays.asList("id"));
							}
						}));
		
		Gson gson = new Gson();
		String idsObtenidosJSON = gson.toJson(idsObtenidos);
		
		List<Account_bank_statement> statements = gson.fromJson(idsObtenidosJSON,new TypeToken<ArrayList<Account_bank_statement>>() {}.getType());
		statement_id = statements.get(0).getId();
		} catch(Exception e) {
			e.printStackTrace();		
		}
		return statement_id;
	}
	

	public Object obtenerNumeroTicket(int idCliente, int idSession) {
		Object arts = null;
		try {
			int uidO = devolverUID();
			final XmlRpcClient models = getModels();

			ArrayList<Object> args = new ArrayList<>();
			args.add(0);
			args.add(145);
			args.add(false);
			args.add(true);
			args.add(new HashMap() {
				{
					put("lang", "es_UY");	
					put("tz", "America/Montevideo");
					put("uid", uidO);
					put("partner_id", idCliente); // buscar
					put("pos_session_id", idSession);
					//put("order_uid", idSession + "-001-0001");
					put("mandante_partner_id", false);
				}
			});

			arts = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uidO, ccls.getPassword(), "pos.order", "get_ticket_number", args));
			
			Gson gson = new Gson();
			System.out.println(gson.toJson(arts));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arts;
	}
	
	public int AltaCliente (Ordenes ov, int uidO) {
		try {
			Cliente c = new Cliente();
			c.setNombre(ov.getComprador().getNombre());
			c.setApellido(ov.getComprador().getApellido());
			
			String direccion = "";
			if(ov.getEntrega().getDireccionEnvio() != null){
				direccion = ov.getEntrega().getDireccionEnvio().getCalle() + " " + ov.getEntrega().getDireccionEnvio().getNumeroPuerta();
				if(ov.getEntrega().getDireccionEnvio().getNumeroApto()!= null){
					direccion += " / "+ ov.getEntrega().getDireccionEnvio().getNumeroApto();
				}
			}
			else if (ov.getDireccionFacturacion() != null){
				direccion = ov.getDireccionFacturacion().getCalle() + " " + ov.getDireccionFacturacion().getNumeroPuerta();
				if(ov.getDireccionFacturacion().getNumeroApto()!= null){
					direccion += " / "+ ov.getDireccionFacturacion().getNumeroApto();
				}
			}
			c.setCalle(direccion);
			
			/*if(ov.getCliRuc() != null && ov.getCliRuc().equalsIgnoreCase("") && ov.getCliRuc().equalsIgnoreCase("0")) {
				c.setRut(ov.getCliRuc());
			} else {
				c.setDocumentoNro(ov.getCliCedula());
			}*/
			if(ov.getComprador().getDocumento().getTipo().equalsIgnoreCase("DOCUMENTO_IDENTIDAD")){
				String documento = ov.getComprador().getDocumento().getNumero().length() > 7 ? ov.getComprador().getDocumento().getNumero(): "0"+ov.getComprador().getDocumento().getNumero(); 
				c.setDocumentoNro(documento);	
				if(ov.getComprador().getDocumento().getPais().equalsIgnoreCase("UY")) {
					c.setDocumentoTipo("DOCUMENTO_IDENTIDAD");
				}
				else {
					c.setDocumentoTipo("PASAPORTE");
				}				
			}
			else if(ov.getComprador().getDocumento().getTipo().equalsIgnoreCase("PASAPORTE")) {
				c.setDocumentoNro(ov.getComprador().getDocumento().getNumero());
				c.setDocumentoTipo("PASAPORTE");
			}
			else{
				c.setRut(ov.getComprador().getDocumento().getNumero());
				c.setDocumentoTipo("RUT");
			}
			c.setTelefono(ov.getComprador().getTelefono());
			c.setEmail(ov.getComprador().getEmail());
			
			return crearCliente(c, uidO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	public DataIDDescripcion facturarPedido(Ordenes ov, BillingEntities billing_entities, int idSession) { //, String uidRecibido, int caeID
		DataIDDescripcion resp = new DataIDDescripcion();
		int uidO = devolverUID();
		String info_line = "";
		
		try {

			final XmlRpcClient models = getModels();
			Gson gson = new Gson();
			String documento = "";
			
			int idCliente = 0;
			//idCliente = buscarClientePorCI(ov.getComprador().getDocumento().getNumero(), uidO);
			
			idCliente = AltaCliente(ov, uidO);
			if(idCliente == 0) {
				//idCliente = buscarClientePorCI("593", uidO);
				System.out.println("CREANDO FACTURA - Error buscando cliente");
				resp.setDescripcion("CREANDO FACTURA - Error buscando cliente");
				return resp;
			}			

			
			double amount_paid = Double.parseDouble(ov.getImporteTotal());
			double amount_total = Double.parseDouble(ov.getImporteTotal());
			double amount_tax = Double.parseDouble(ov.getImporteTotal()) * 0.1803;
			int amount_return = 0;
			int pos_session_id = idSession; // sesion del dia
			int partner_id = idCliente; // id cliente
			int seller_id = 449; // vendedor
			String note = "IdOrden: "+ov.getIdOrden();
			int user_id = uidO; // usuario ws_encuentra
			
			int sequence_number = 1; // no se sabe
			boolean ref_order_returned = false; // siempre false
			boolean fe_mandante_partner_id = false; // siempre false
			
			boolean to_invoice = false; // siempre false

			ArrayList<Object> artsList = new ArrayList<>();
			double descuentoMP = 0.0; //descuento por metodo de pago
			try {
				for (Lineas ovl : ov.getLineas()) {
					info_line = ovl.getSku();
					ArrayList<Object> lines = new ArrayList<>();
					lines.add(0);
					lines.add(false);
					HashMap<String, Object> articulo = new HashMap<>();
					Articulo_Odoo art_oddo = obtenerArticuloOdoo(ovl.getSku(), uidO);
					
					if(ovl.getSku().equalsIgnoreCase("Costo Env?o")){
						articulo.put("discount", 0);
					}
					else{
						Double discountPrice = 0.0;
						if(ovl.getDescuentos() != null) {
							for(Descuento d: ovl.getDescuentos()) {
								try {
									discountPrice += Double.parseDouble(d.getMonto());
								} catch (Exception e) {}
							}
						}						
						
						/*discountPrice += ((Double)art_oddo.getList_price() - Double.parseDouble(ovl.getPrecio()));
						articulo.put("discount", 
								(discountPrice * 100) / (Double)art_oddo.getList_price());*/						
						articulo.put("discount", 
								 (discountPrice * 100) / Double.parseDouble(ovl.getPrecio()));		
					}
					articulo.put("qty", ovl.getCantidad());
					articulo.put("price_unit", ovl.getPrecio());
					articulo.put("product_id", art_oddo.getId());
					articulo.put("disc_product_ids_char", false);
					
					
					// articulo.put("discount_id", 195);
					lines.add(articulo);
					artsList.add(lines);
				}
			} catch (Exception e) {
				System.out.println("CREANDO FACTURA - Error buscando id articulo en Oddo --> "+info_line);
				resp.setDescripcion("CREANDO FACTURA - Error buscando id articulo en Oddo --> "+info_line);
				return resp;
			}
			
			
			//DESCUENTOS			
			try {
				for(Descuento d: billing_entities.getDescuentos()) {
					info_line = d.getNombre();
					ArrayList<Object> lineDescuento = new ArrayList<>();
					lineDescuento.add(0);
					lineDescuento.add(false);
					HashMap<String, Object> articulo = new HashMap<>();
					articulo.put("qty", 1);
					articulo.put("price_unit", "-"+Math.floor(Double.parseDouble(d.getMonto())));
					articulo.put("product_id", d.getCodigo());
					articulo.put("disc_product_ids_char", false);
					lineDescuento.add(articulo);
					artsList.add(lineDescuento);
				}
			} catch (Exception e) {
				System.out.println("CREANDO FACTURA - Error seteando descuentos --> "+info_line);
				resp.setDescripcion("CREANDO FACTURA - Error seteando descuentos --> "+info_line);
				return resp;
			}
			
			
			java.util.Date fecha = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String strDate = formatter.format(fecha);
			System.out.println(strDate);

			//FORMAS DE PAGO
			ArrayList<Object> formasPago = new ArrayList<>();
			try {
				for (MetodoPago pago : billing_entities.getMetodos_pagos()) {
					info_line = pago.getDescripcion();
					FormaPago fp = formasDePago(pago.getIdMetodoPago());
						fp.setAmount(pago.getMonto()); // seteo el monto total de la factura, asumiendo que viene 1 solo metodo de
													// pago
						ArrayList<Object> lines = new ArrayList<>();
						lines.add(0);
						lines.add(false);
						HashMap<String, Object> articulo = new HashMap<>();
						articulo.put("account_id", fp.getDefault_credit_account_id().get(0));
						articulo.put("journal_id", fp.getId());
						if (fp.getAuth_number() == null || fp.getAuth_number().equalsIgnoreCase("")) {
							articulo.put("auth_number", false);
						} else {
							articulo.put("auth_number", fp.getAuth_number());
						}
						int statement_id =  account_bank_statement(fp.getId(), pos_session_id);
						if(statement_id <= 0) {
							System.out.println("CREANDO FACTURA - No existe stamente_id --> "+info_line);
							resp.setDescripcion("CREANDO FACTURA -  No existe stamente_id --> "+info_line);
							return resp;
						}
						articulo.put("statement_id", statement_id);
						articulo.put("name", strDate);
						articulo.put("amount", Math.floor(fp.getAmount()));
						articulo.put("order_number", pago.getGiftcard_order()!=null ? pago.getGiftcard_order() : false);
						
						//articulo.put("gen_giftcard_order", pago.getGiftcard_order()!=null ? pago.getGiftcard_order() : false);
						articulo.put("gen_giftcard_order", false);
						//articulo.put("gen_giftcard_used", pago.getGiftcard_order()!=null ? pago.getGiftcard_order() : false);
						articulo.put("gen_giftcard_used", false);
						lines.add(articulo);
						formasPago.add(lines);
					}
			} catch (Exception e) {
				System.out.println("CREANDO FACTURA - Error seteando formas de pago --> "+info_line);
				resp.setDescripcion("CREANDO FACTURA - Error seteando formas de pago --> "+info_line);
				return resp;
			}
			
			//GENERO TICKET NUMBER
			String ticketData = gson.toJson(obtenerNumeroTicket(idCliente, idSession));
			List<Object> tn = gson.fromJson(ticketData, ArrayList.class);
			
			String id = tn.get(0).toString();
			String name = "Pedido " + tn.get(0).toString();
			resp.setDescripcion(name);
			String idDev = tn.get(2).toString();
			String uid = tn.get(0).toString(); //uidRecibido;  ticket number
			int fe_cae_id = (int)((double) tn.get(1)); // respuesta del get ticket num
			
			//ENVIO FACTURA
			@SuppressWarnings("unchecked")
			final Object arts = models.execute("execute_kw", Arrays.asList(ccls.getDb(), uidO, ccls.getPassword(),
					"pos.order", "create_from_ui", Arrays.asList(Arrays.asList(new HashMap() {
						{
							put("id", "");
							put("data", new HashMap() {
								{
									put("name", name);
									put("amount_paid", amount_paid);
									put("amount_total", amount_total);
									put("amount_tax", amount_tax);
									put("amount_return", amount_return);
									put("lines", artsList); /* lista de listas con params y obj articulos */
									put("statement_ids", formasPago);
									put("pos_session_id", pos_session_id);
									put("partner_id", partner_id);
									put("seller_id", 449);
									put("note", note);
									put("user_id", user_id);
									put("uid", uid);
									put("sequence_number", sequence_number);
									put("ref_order_returned", ref_order_returned);
									put("fe_mandante_partner_id", fe_mandante_partner_id);
									put("fe_cae_id", fe_cae_id);
								}
							});
							put("to_invoice", "");
						}
					}))));
				System.out.println(gson.toJson(arts));
				String idFact = (gson.toJson(arts)).replace("[", "");
				idFact = idFact.replace("]", "");
				
				
				
				editarAdenda(Integer.parseInt(idFact), uidO, ov.getIdOrden());
			resp.setId(Integer.parseInt(idFact));
		} catch (Exception e) {
			System.out.println("CREANDO FACTURA - Error al enviar factura");
			resp.setDescripcion("CREANDO FACTURA - Error al enviar factura");
		}
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	public void editarAdenda(int nroFact, int uid, String textoAgregado) {

		try {
			final XmlRpcClient models = getModels();
			int uidO = uid;

			models.execute("execute_kw", Arrays.asList(ccls.getDb(), uidO, ccls.getPassword(), "pos.order", "write", 
					Arrays.asList(Arrays.asList(nroFact), new HashMap() {
				{
					put("extra_adenda", "Pedido: " + textoAgregado);
				}
			})));
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public void transferirPicking(int idAlbaran) {
		int uid = devolverUID();

		try {
			final XmlRpcClient models = getModels();

			final Object ps = models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
					"stock.picking", "search", Arrays.asList(Arrays.asList(Arrays.asList("id", "=", idAlbaran)))));
			

			Gson gson = new Gson();
			System.out.println(gson.toJson(ps));

			final Object ts = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking", "do_transfer", ps));
			
			System.out.println(gson.toJson(ts));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void transferirParcialPicking(int idAlbaran) {
		int uid = devolverUID();

		try {
			final XmlRpcClient models = getModels();

			final Object ps = models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(),
					"stock.picking", "search", Arrays.asList(Arrays.asList(Arrays.asList("id", "=", idAlbaran)))));

			final Object ts = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking", "do_prepare_partial", Arrays.asList(Arrays.asList(idAlbaran, idAlbaran)))); //Arrays.asList((Integer) ps.get(0)
			Gson gson = new Gson();
			System.out.println(gson.toJson(ts));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> obtenerIDsUbicaciones(int uid){
		List<Integer> ids = new ArrayList<>();
		try {
			String almacenesJson = "";
				// List<Almacen> almacenes = new ArrayList<>();

				final XmlRpcClient models = getModels();

				@SuppressWarnings("unchecked")
				final List<Object> arts = Arrays.asList((Object[]) models.execute("execute_kw",
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.location", "search_read",
								Arrays.asList(Arrays.asList(Arrays.asList("usage", "=", "internal"))), new HashMap() {
									{
										put("fields", Arrays.asList("id"));
									}
								})));

				Gson gson = new Gson();
				almacenesJson = gson.toJson(arts);
				List<Almacen> idsAlm = gson.fromJson(almacenesJson, new TypeToken<List<Almacen>>(){}.getType());
				for (Almacen almacen : idsAlm) {
					ids.add(Integer.parseInt(almacen.getId()));
				}
		} catch(Exception e) {
			
		}
		return ids;
		
	}
	
	public int transferir_aTransito_nuevo(int id) {
		int idAlbaran = 0;
		try {
			int uid = devolverUID();

			final XmlRpcClient models = getModels();
		
			models.execute("execute_kw",Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), 
					"stock.picking", "transfer_picking_by_api",
					Arrays.asList(id)));
			
			Object obj = models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking",
					"read", Arrays.asList(id),
					new HashMap() {
						{
							put("fields", Arrays.asList("picking_duplication"));
						}
					}));
			Gson gson = new Gson();
			String[] result = (gson.toJson(obj)).split(",");
			
			String resultId = result[0].replace("[", "");
			String[] resultId2 = resultId.split(":");
			idAlbaran = Integer.parseInt(resultId2[1]); 
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return idAlbaran;
	}
	
	public void revertirTransferencia(int idTransf) {
		try {

			int uid = devolverUID();

			final XmlRpcClient models = getModels();
			
			HashMap<String, HashMap<String, Object>> kwargs = new HashMap<>();
			kwargs.put("context", new HashMap() {{
				put("lang", "es_UY");
				put("tz", "America/Montevideo");
				put("uid", uid);
				put("active_model", "stock.picking");
				put("search_disable_custom_filters", true);
				put("active_id", idTransf);
				put("active_ids", Arrays.asList(idTransf));
			}});
						
		
			HashMap<String, Object> obj = (HashMap<String, Object>) models.execute("execute_kw",Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), 
					"stock.return.picking", "default_get", Arrays.asList(Arrays.asList("product_return_moves",
							"move_dest_exists", "invoice_state")), kwargs));
			
			
			Gson gson = new Gson();
			String[] parts =  (gson.toJson(obj)).split(",");
			String move_idStr = parts[1].replace("\"move_id\":", "");
			int move_id = Integer.parseInt(move_idStr);
			System.out.println(move_id);
			
			HashMap<String, Object> args = new HashMap<>();
			
			int idProducto = obtenerIDArticuloOdoo("VX18PA02KA34", uid);
			
			List<Object> contenido = new ArrayList<>();
			ArrayList<Object> params = new ArrayList<>();
			params.add(5);
			params.add(false);
			params.add(false);
			ArrayList<Object> productoCantidad_aDevolver = new ArrayList<>();
			productoCantidad_aDevolver.add(0);
			productoCantidad_aDevolver.add(false);
			HashMap<String, Object> infoProds = new HashMap<>();
			infoProds.put("product_id", idProducto);
			infoProds.put("move_id", move_id);
			infoProds.put("quantity", 1);
			infoProds.put("location_usage", false);
			infoProds.put("location_dest_usage", false);
			infoProds.put("lot_id", false);
			productoCantidad_aDevolver.add(infoProds);
			contenido.add(params);
			contenido.add(productoCantidad_aDevolver);
			
			args.put("product_return_moves", contenido);
			args.put("invoice_state", "none");
			
			
			HashMap<String, HashMap<String, Object>> kwargs_Baja = new HashMap<>();
			
			HashMap<String, Object> contenido_kwargs = new HashMap<>();
			contenido_kwargs.put("lang", "es_UY");
			contenido_kwargs.put("tz", "America/Montevideo");
			contenido_kwargs.put("uid", uid);
			HashMap<String, Object> hs = new HashMap<>();
			hs.put("id", idTransf);
			hs.put("view_type", "form");
			hs.put("model", "stock.picking");
			hs.put("action", 373);
			hs.put("active_id", 112);
			hs.put("_push_me", false);
			contenido_kwargs.put("params", hs);
			contenido_kwargs.put("contact_display", "partner_address");
			contenido_kwargs.put("search_disable_custom_filters", true);
			contenido_kwargs.put("active_model", "stock.picking");
			contenido_kwargs.put("active_id", idTransf);
			contenido_kwargs.put("active_ids", Arrays.asList(idTransf));
			kwargs_Baja.put("context", contenido_kwargs);

			
			Object obj2 = models.execute("execute_kw",Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), 
					"stock.return.picking", "create", Arrays.asList(Arrays.asList(args)), kwargs_Baja));
			
			System.out.println(gson.toJson(obj));
			System.out.println(gson.toJson(obj2));
			
			
			contenido_kwargs.remove("params");
			
			Object obj3 = models.execute("execute_kw",Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), 
					"stock.return.picking", "create_returns", Arrays.asList(Arrays.asList(obj2), contenido_kwargs)));
			
		
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	public FormaPago formasDePago2(int id) {

		int uid = devolverUID();
		FormaPago fpDev = null;
		try {
		List<Object> idsObtenidos = new ArrayList<>();
		final XmlRpcClient models = getModels();
			final Object fp = models.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "account.journal", "search_read",
							Arrays.asList(Arrays.asList(Arrays.asList("id", ">", 0))), new HashMap() {
								{
									put("fields", Arrays.asList("id", "name"));
								}
							}));
			idsObtenidos.add(fp);
		
		Gson gson = new Gson();
		String idsObtenidosJSON = gson.toJson(idsObtenidos);
		List<ArrayList<Object>> json = new ArrayList<>();
		json = gson.fromJson(idsObtenidosJSON, new TypeToken<ArrayList<ArrayList<Object>>>() {
		}.getType());

		for (ArrayList<Object> al : json) {
			String jsonParse = gson.toJson(al.get(0));
			fpDev = gson.fromJson(jsonParse, FormaPago.class);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fpDev;
	}
	
	public int revertir_transferencia(int idAlbaran) {
		int new_albaran = 0;
		try {			
		
		int uid = devolverUID();
		final XmlRpcClient models = getModels();
		
		int product_revert_id = 0;
		int cantidad_a_devolver = 1;
		
		HashMap<String, HashMap<String, Object>> kwargs = new HashMap<>();
		kwargs.put("context", new HashMap() {{
			put("active_model", "stock.picking");
			put("active_id", idAlbaran);
			put("active_ids", Arrays.asList(idAlbaran));
		}});
		
		HashMap<Object, Object> values = (HashMap<Object, Object>) models.execute("execute_kw", 
				Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.return.picking", "default_get", 
				Arrays.asList(Arrays.asList("product_return_moves", "move_dest_exists", "invoice_state")), kwargs));
		
		Object[] product_return_moves = null;
		if(values != null) {
			product_return_moves = (Object[]) values.get("product_return_moves");
			List<Object> newContent = new ArrayList<>();
			if(product_return_moves.length > 0) {
				for(Object art : product_return_moves) {
					HashMap<Object, Object> artDetalle = (HashMap<Object, Object>) art;
					List<Object> content = new ArrayList<>();
					content.add(0);
					content.add(false);
					content.add(artDetalle);
					
					newContent.add(content);
				}
				
				values.put("product_return_moves", newContent);
				
				Object wizard_id = models.execute("execute_kw", 
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.return.picking", "create", 
						Arrays.asList(values), kwargs));
				
				Object result = models.execute("execute_kw", 
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.return.picking", "create_returns", 
						Arrays.asList(Arrays.asList(wizard_id)), kwargs));
				
				Gson gson = new Gson();
				System.out.println(gson.toJson(result));
				try {
					String arr1 = gson.toJson(result).split(",")[2].replace("[", "").replace("]", "").replace(")", "").replace("\"", "").replace(" ", "").replace("\"", "").replace(" ", "");
					
					new_albaran = Integer.parseInt(gson.toJson(arr1));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new_albaran;
		
	}

	
	public int revertir_transferencia_parcial(int idAlbaran, Map<String, Integer> articulosCancelados) {
		int new_albaran = 0;
		try {
			int uid = devolverUID();
			
			Map<Integer, Integer> idsOdoo = new HashMap<>();
			
			for(String key : articulosCancelados.keySet()) {
				int idArt = obtenerIDArticuloOdoo(key, uid);
				if(idArt > 0) {
					idsOdoo.put(idArt, articulosCancelados.get(key));
				}
			}
		
		final XmlRpcClient models = getModels();
		
		int product_revert_id = 0;
		int cantidad_a_devolver = 1;
		
		HashMap<String, HashMap<String, Object>> kwargs = new HashMap<>();
		kwargs.put("context", new HashMap() {{
			put("active_model", "stock.picking");
			put("active_id", idAlbaran);
			put("active_ids", Arrays.asList(idAlbaran));
		}});
		
		HashMap<Object, Object> values = (HashMap<Object, Object>) models.execute("execute_kw", 
				Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.return.picking", "default_get", 
				Arrays.asList(Arrays.asList("product_return_moves", "move_dest_exists", "invoice_state")), kwargs));
		
		Object[] product_return_moves = null;
		if(values != null) {
			product_return_moves = (Object[]) values.get("product_return_moves");
			List<Object> newContent = new ArrayList<>();
			if(product_return_moves.length > 0) {
				for(Object art : product_return_moves) {
					HashMap<Object, Object> artDetalle = (HashMap<Object, Object>) art;
					for(int idOdoo : idsOdoo.keySet()) {
						if(idOdoo == (int) artDetalle.get("product_id")) {	

							List<Object> content = new ArrayList<>();
							content.add(0);
							content.add(false);
							artDetalle.put("quantity", idsOdoo.get(idOdoo));
							content.add(artDetalle);
						 
							newContent.add(content);
						}
					}
				}
				
				values.put("product_return_moves", newContent);
				
				Object wizard_id = models.execute("execute_kw", 
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.return.picking", "create", 
						Arrays.asList(values), kwargs));
				
				Object result = models.execute("execute_kw", 
						Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.return.picking", "create_returns", 
						Arrays.asList(Arrays.asList(wizard_id)), kwargs));
				
				Gson gson = new Gson();
				System.out.println(gson.toJson(result)); //Nuevo id albaran
				try {
					String arr1 = gson.toJson(result).split(",")[2].replace("[", "").replace("]", "").replace(")", "").replace("\"", "").replace("\"", "").replace(" ", "");
					
					new_albaran = Integer.parseInt(arr1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new_albaran;
	}
	

	public String obtenerDatos(String nombreMetodo) throws Exception {

		String[] nombresMetodos = { "almacenes", "categorias", "marcas", "talles", "colores", "temporadas", "generos",
				"productos", "stockProductos" };

		switch (nombreMetodo) {
		case "almacenes":
			return obtenerAlmacenes();

		case "categorias":
			return obtenerCategorias();

		case "marcas":
			return obtenerMarcas();

		case "talles":
			return obtenerTalles();

		case "colores":
			return obtenerColores();

		case "temporadas":
			return obtenerTemporadas();

		case "productos":
			return obtenerProductos();

		case "generos":
			return obtenerGeneros();

		default:
			return "";
		}
	}
	
	
	
/*	//prueba
	public void buscar() {
		int uid = devolverUID();
		try {
			final XmlRpcClient models = getModels();
			final List ids = Arrays.asList((Object[]) models.execute("execute_kw", Arrays.asList(ccls.getDb(), uid,
					ccls.getPassword(), "pos.order", "search_read", Arrays.asList(Arrays.asList(

							Arrays.asList("id", "=", 570843) )),
					new HashMap() {
						{
							put("fields", Arrays.asList("id","fe_adenda_xml"));
						}
					})));
			Gson gson = new Gson();
			System.out.println(gson.toJson(ids));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	
	@SuppressWarnings({ "unchecked", "finally" })
	public int testRecepcion(int idAlbaran) {

		int uid = devolverUID();
		int pdInt = idAlbaran;
		try {
			final XmlRpcClient models2 = new XmlRpcClient() {
				{
					setConfig(new XmlRpcClientConfigImpl() {
						{
							setServerURL(new URL(String.format("%s/xmlrpc/2/object", ccls.getUrl())));
						}
					});
				}
			};
			models2.execute("execute_kw",
					Arrays.asList(ccls.getDb(), uid, ccls.getPassword(), "stock.picking", "transfer_picking_by_api",
							Arrays.asList(Arrays.asList(pdInt),
									new HashMap() {
								{
									put("lang", "es_UY");
									put("tz", "America/Montevideo");
									put("uid", uid);
								}
							})));
		} catch (Exception e) {
			e.printStackTrace();
			pdInt = 0;
		} finally {
			return pdInt;
		}
	}	

}
