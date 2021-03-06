package integraciones.mains.tata;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import beans.jsonEstadoMP;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.Cliente;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import beans.encuentra.EncuentraPedidoArticuloReq;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.marketplaces.fenicio.Descuento;
import integraciones.marketplaces.fenicio.Lineas;
import integraciones.marketplaces.fenicio.Ordenes;
import integraciones.marketplaces.fenicioTrack.FenicioBAS;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class ClienteBASFenicio 
{
 
	public static void main(String[] args) 
	{
		FenicioBAS f = new FenicioBAS();
				
		 Map<Integer, CanalMarketPlace> canales = f.getCanales();
		 List<CanalMarketPlace> canalesL = new ArrayList<> (canales.values());
		 
		 Call_WS_APIENCUENTRA wms = new Call_WS_APIENCUENTRA();
		 LogicaAPI logica = new LogicaAPI();
		 String token = logica.darToken(f.getIdEmpresa());
		 
		 Map<Integer, String> parametros = wms.darParametros(token, "");
		 Map<Integer, String> integraciones = wms.darIntegraciones(token);
		 
		 String cambioEstado = integraciones.get(3);
		 
		 int idDepoOrigen = Integer.parseInt(parametros.get(4));
		 
		 List<Cliente> clientes = new ArrayList<>();
		 int diasBusqueda = 6;
		 
		 for (CanalMarketPlace canal : canalesL) 
		 {
			 List<Ordenes> pedidosFenicio = f.getPedidosAPI(canal.getId(),diasBusqueda);
			 List<EncuentraPedido> pedidosALL = f.getPedidos(canal.getId(),"",diasBusqueda, null, null);
			 
			 Map<String, Ordenes> ordenesF = new Hashtable<>();
			 
			 for (Ordenes o : pedidosFenicio) 
			 {
				 ordenesF.put(o.getIdOrden(), o);
			 }
			 
			 Map<String, String> pedidosEncuentra = wms.PedidosID(token, diasBusqueda, "");			 
			 List<EncuentraPedido> pedidos = new ArrayList<>();		 
			 
			int in = 0;
			 for (EncuentraPedido p : pedidosALL) 
			 {
				 
				 p.setTrackingNumber(p.getIdPedido()+"");
				 
				if(!pedidosEncuentra.containsKey(p.getIdPedido()+""))
				 {
					 pedidos.add(p);
					
				 }
				if(in==1)
				{
					break;
				}
			 }
			 
			 for (EncuentraPedido p : pedidos) 
			 {
				 if(p.getIdPedido().equals(new Long("268793")))
	        		{
	        			System.out.println("F");
	        		}
				 Ordenes ovf =  ordenesF.get(p.getIdPedido()+"");
				 if(ovf==null)
				 {
					 for (OrdenVentaLinea a : p.getOrden().getOrdenVentaLineas()) 
					 {
						a.setDescripcion("sku "+a.getIdArticulo());
					 }
				 }
				 else
				 {
					List<OrdenVentaLinea> ovls = new ArrayList<>();
					int linea = 0;
					 for (Lineas l : ovf.getLineas()) 
					 {
						linea++;
						if(l.getSku().equals(""))
						{
							/*/
							 * 800102000 -- "Env?o Normal"
							   800103000 -- Env?o Interior
							   800106000
							 */
							if(l.getNombre().equals("Env?o Normal"))
							{
								l.setSku("800102000");
							}
							else if(l.getNombre().equals("Env?o Interior"))
							{
								l.setSku("800103000");
							} 
							else if (l.getNombre().equals("?Recib? tu pedido en 24 horas!"))
							{
								l.setSku("800106000");
							}
							else if (l.getNombre().contains("Env?o"))
							{
								l.setSku("800106000");
							}
							//si el nombre es peya hay que ponerle el SKU
							else
							{
								l.setSku("800102000");
							}
						}
						
						/*if(l.getSku().startsWith("0"))
						{
							String articulo=l.getSku().substring(1, l.getSku().length());
							l.setSku(articulo);
						}*/
						
						OrdenVentaLinea li = new OrdenVentaLinea(Double.parseDouble(l.getPrecio()), l.getCantidad()+"", l.getSku());
						li.setDescripcion(l.getNombre());
						li.setLinea(linea);
						
						
						Double descuentoTotal = 0.0;
						if(l.getDescuentos()!=null)
						{
							for (Descuento d : l.getDescuentos()) 
							{
								/*
								linea++;
								OrdenVentaLinea lid = new OrdenVentaLinea(Double.parseDouble(d.getMonto())*-1, 1+"", d.getCodigo());
								lid.setDescripcion(d.getNombre());
								lid.setLinea(linea);
								ovls.add(lid); 
								 */
								descuentoTotal +=  Double.parseDouble(d.getMonto());
							}
						}
						
						li.setDescuento(descuentoTotal);
						ovls.add(li);
					 }
					 
					 p.getOrden().setOrdenVentaLineas(ovls);
				 }
				 
				 p.setCanalAnaloga(canal.getId());
				 p.setCanalMercadoLibre(new DataIDDescripcion(canal.getId(),canal.getNombre()));
				 p.setIdPedidoSTR(p.getIdPedido()+"");
				 if(p.getSucursalPick().equals(""))
				 {
					 p.setIdDepositoEnvio(0);
				 }
				 else
				 {
					 try
					 {
						 p.setIdDepositoEnvio(Integer.parseInt(p.getSucursalPick()));
					 }
					 catch (Exception e) 
					 {
						 p.setIdDepositoEnvio(0);
					 }
					 
				 }
				 p.getCliente().setIdPedido(p.getIdPedido()+"");
				 clientes.add(p.getCliente());
				
				 List<EncuentraPedidoArticulo> articulosPedido = new ArrayList<>();
				 int cantidadUnidades = 0;
				for (EncuentraPedidoArticulo ar : p.getArticulosPedido()) 
				{
					//String articulo=ar.getArticulo().substring(1, ar.getArticulo().length());
					ar.setOrigen(idDepoOrigen);
					/*if(ar.getArticulo().startsWith("0"))
					{
						ar.setArticulo(articulo);
					}*/
					int cantRegalo = 0;
					boolean found = false;
					for (Lineas l : ovf.getLineas()) 
					{
						if(l.getSku().equals(ar.getArticulo()))
						{
							found=true;
							try
							{
								cantRegalo = Integer.parseInt(l.getCantidadRegalo());
							}
							catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					
					if(found)
					{
						ar.setCantidadRegalo(cantRegalo);
						EncuentraPedidoArticuloReq are = new EncuentraPedidoArticuloReq();
						are.setArticulo(are.getArticulo());
						are.setCantReq(ar.getCantidad());
						are.setDeposito(idDepoOrigen);
						cantidadUnidades+=ar.getCantidad();
						p.getArticulosPedidoReq().add(are);
						articulosPedido.add(ar);
					}
					
					for (OrdenVentaLinea li : p.getOrden().getOrdenVentaLineas()) 
					{
						if(li.getIdArticulo().equals(ar.getArticulo()))
						{
							if(cantRegalo>0)
							{
								System.out.println("");
							}
								
							li.setCantidadRegalo(cantRegalo);
						}
					}
					
				}
				
				p.setArticulosPedido(articulosPedido);
				p.setCantidad(cantidadUnidades);
				
			 }
			
			 //cen.SaveCustomers(token, clientes);
			 wms.SaveOrders(token, pedidos);
			 wms.SaveOrdersArticulosReq(token, pedidos,canal.getId());
			
			 List<DataIDDescripcion> depositosDestino =wms.DarDatosPutOrders(token, 2);
			 
			 List<jsonEstadoMP> estadosMP = new ArrayList<>();
			 
			 for (EncuentraPedido p : pedidos) 
			 {
				 
				 System.out.println(p.getIdPedido());
				 if(p.getCliente().getTelefono().equals(""))
				 {
					 p.getCliente().setTelefono("0");
				 }
				 logica.saveImport1Customer(p.getCliente(),f.getIdEmpresa());
				 p.getOrden().save(token, f.getIdEmpresa());
				 
				 if(cambioEstado != null && cambioEstado.equals("1")){
					 jsonEstadoMP estado = new jsonEstadoMP(p.getIdPedido()+"",0,p.getIdPedido(),
							 f.JSONUpdateState(p.getIdPedido(), p.getIdPedido()+"", "", 1),canal.getId(),f.getIdEmpresa());
					 estadosMP.add(estado);
				 }
			 }
			 
			 if(!estadosMP.isEmpty()){
				 LogicaAPI.putColaEstadoMarketPlace(estadosMP);
			 }			 
			 
			 for (EncuentraPedido p : pedidos) 
			 {
				 try
				 {
					 boolean found = false;
					 if(!p.getSucursalPick().equals(""))
					 {
						 //es pickup
						 p.setIdDepositoEnvio(Integer.parseInt(p.getSucursalPick()));
						 p.setShippingType(new DataIDDescripcion(2, ""));
						 found=true;
					 }
					 else
					 {
						 
						 for (DataIDDescripcion d : depositosDestino) 
						 {
							if(d.getDescripcion().equals(p.getEmpresaEnvio()))
							{
								p.setShippingType(new DataIDDescripcion(1, ""));
								p.setIdDepositoEnvio(d.getId());
								found=true;
								break;
							}
						 }
					 }
					 
					 if(!found)
					 {
						 p.setShippingType(new DataIDDescripcion(1, ""));
						 if(p.getEmpresaEnvio().equals("")){
							 p.setIdDepositoEnvio(700000);
						 }
						 else{							 
							 System.out.println("No encontre "+p.getEmpresaEnvio());
							 p.setIdDepositoEnvio(0);
						 }
					 }
					 
					 wms.updateDestinoPedido(token, p, p.getIdDepositoEnvio(), p.getMontoEnvio());
				 }
				 catch (Exception e) 
				 {
					 e.printStackTrace();
					 wms.updateDestinoPedido(token, p, 0, p.getMontoEnvio());
				 }
			 }
			 
			 //SINCRO ETIQUETAS
			 System.out.println("esperando 60 segundos para sincronizar etiquetas nuevamente");
			    try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 f.buscarEtiquetas(pedidosFenicio, wms, token, canal.getId());
			 
			//CONSULTO ESTADO DE DESPACHADOS
			 Map<String, String> pedidosDespachados = wms.PedidosID(token, 30, "4");
			 List<DataIDDescripcion> entregados = f.tackPedidosDespachados(pedidosDespachados, canal.getId());
			 if(!entregados.isEmpty()) {
				 wms.updateOrdersStatus(token,entregados);
			 }
		}
	}
}

