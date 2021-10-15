package integraciones.marketplaces.vtex;

import java.io.BufferedInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import beans.Fecha;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import integraciones.marketplaces.fenicioTrack.ColectionUpdateState;
import integraciones.marketplaces.objetos.UpdateStateResponse;
import integraciones.marketplaces.objetos.marketPlace;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Vtex extends marketPlace {

	private static final int VTEX_MARKETPLACE = 4;

	public Vtex() {
		this.setIdMarketPlace(VTEX_MARKETPLACE);
	}

	//PREPARA EL JSON QUE SE DEBE ENVIAR PARA CAMBIO DE ESTADO
	@Override
	public String JSONUpdateState (Long idPedido, String idEcommerce, String track, int estado) {
		String json = "";
			json=
					 "     { "+
					 "        \"id\":\""+idEcommerce+"\", "+
					 "        \"estado\":\""+estado+"\" "+
					 "     } "
					 ;
		
		return json;
	}
	
	// CAMBIO DE ESTADO
	@Override
	public List<UpdateStateResponse> UpdateState(String json, int canal) {
		ColectionUpdateState salida = new ColectionUpdateState();
		try {
			String urlBase = this.canales.get(canal).getHost();
			String funcion = "/tracking";
			String retorno = callHttpPut(json, urlBase + funcion);
			Gson gson = new Gson();
			salida = gson.fromJson(retorno, ColectionUpdateState.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida.getCompras();
	}

//		private String callHttpPatch(String json, String url) {
//			return callHttp(json, url, "PATCH");
//		}

	private String callHttpPut(String json, String url) {
		return callHttp(json, url, "PUT");
	}

	private String callHttpGet(String url) {
		return callHttp(null, url, "GET");
	}

//		private String callHttpPost(String json, String url) {
//			return callHttp(json, url, "POST");
//		}

	private String callHttp(String json, String url, String metodo) {

		OkHttpClient client = new OkHttpClient().newBuilder().build();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = (metodo.equals("GET")) ? null : RequestBody.create(mediaType, json);
		Request request = new Request.Builder()
				  .url(url)
				  .method(metodo, body)
				  .addHeader("x-vtex-api-appkey", "vtexappkey-qaforusuy-DRTBTH")
				  .addHeader("x-vtex-api-apptoken", "ZMGTGJNEXOGVZTPSBNFVGAHUOAZHBKUUIUXVKUKKMICFLCNCRELBVILZELFFJFDMULNEWUIHCUJXXBJMNWQXZSZAJXQYOFBGFPKZPIGWVDZAZNBCCTRNOFNCMMUZDJSX")
				  .addHeader("Cookie", "janus_sid=ad31c170-1a56-4d65-a833-7066fffbfab4")
				  .build();
		try {
			Response response = client.newCall(request).execute();
			
			int bytesRead = 0;
	        BufferedInputStream bis = new BufferedInputStream(response.body().byteStream());
	        byte[] contents = new byte[1024];
	        StringBuilder strFileContents = new StringBuilder();
	        while((bytesRead = bis.read(contents)) != -1) 
	        { 
	            strFileContents.append(new String(contents, 0, bytesRead));              
	        }
	        response.close();
	        
	        return strFileContents.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EncuentraPedido> getPedidos(int canal, String status, int dias) {

			Gson gson = new Gson();

			String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String fechaDesde = new Fecha(-dias, 0, 0).darFechaAnio_Mes_Dia();
			String urlBase = this.getCanales().get(canal).getHost();
			String filtroFecha = "?f_creationDate=creationDate%3A%5B"+ fechaDesde +"T02%3A00%3A00.000Z%20TO%20"+ fechaActual +"T01%3A59%3A59.999Z%5D&f_hasInputInvoice=false";
			String funcion = "orders";
			String url = urlBase + funcion + filtroFecha;
			
			System.out.println("URL: "+url);
			String retorno = this.callHttpGet(url);
			System.out.println("Retorno: "+retorno);
			
			VtexResponseList responseList = gson.fromJson(retorno, VtexResponseList.class);
			
			List<EncuentraPedido> pedidos = new ArrayList<>();
	
			for(OrderList o : responseList.getList()) {
				
				url = urlBase + funcion + "/" + o.getOrderId();
				
				System.out.println("URL: " + url);
				retorno = this.callHttpGet(url);
				System.out.println("Retorno: " + retorno);
				
				VtexResponse order = gson.fromJson(retorno, VtexResponse.class);
				
				ClientProfileData cliente = order.getClientProfileData();

				EncuentraPedido p = new EncuentraPedido();

				p.setIdFenicio(order.getOrderId());
				
                p.setDescripcion(cliente.getFirstName()+" "+cliente.getLastName());
                            
                p.setTicketNumber("");
                p.setEstado("");
                p.setUrlEtiqueta("");
                
                try {
                	Slas courier = order.getShippingData().getLogisticsInfo().get(0).getSlas().get(0);
                	
                	p.setEmpresaEnvio(courier.getName());
                	p.setEmpresaEnvioCod(courier.getId());
                }
                catch (Exception e) {
					p.setEmpresaEnvio("");
					p.setEmpresaEnvioCod("");
				}
                
                List<Items> articulosWs = order.getItems();
                List<EncuentraPedidoArticulo> articulos = new ArrayList<>();
                EncuentraPedidoArticulo articulo;
                for(Items artWs : articulosWs) {
                	articulo = new EncuentraPedidoArticulo();
                	articulo.setArticulo(artWs.getProductId());
                	articulo.setCantidad(artWs.getQuantity());
                	articulo.setCodFenicio(artWs.getId());
                	articulo.setImporte((double)artWs.getCostPrice());
                	articulo.setSKUFenicio(artWs.getSellerSku());
                	articulos.add(articulo);
                }
                p.setArticulosPedido(articulos);
                pedidos.add(p);
			}
			
			return pedidos;
			
		}

	public Map<String, DataIDDescripcion> DestinoPedidos(int canal, int dias,
			Map<String, DataIDDescripcion> retornable) {
		List<EncuentraPedido> pedidos = getPedidos(canal, "", dias);
		for (EncuentraPedido p : pedidos) {
			System.out.println(p.getIdPedido());
			retornable.put(p.getIdPedido() + "", p.getDestino());
		}
		return retornable;
	}

}
