package integraciones.mains.tata;

import java.util.ArrayList;
import java.util.HashMap;
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
import logica.LogicaAPI;

public class ClienteBASFenicioSecundario 
{

	private static final String ENVIO_NORMAL ="800102000";
	private static final String ENVIO_INTERIOR ="800103000";
	private static final String ENVIO_OTRO ="800106000";
	
	public static void main(String[] args) 
	{
		 FenicioBAS f = new FenicioBAS();
				
		 Map<Integer, CanalMarketPlace> canales = f.getCanales();
		 List<CanalMarketPlace> canalesL = new ArrayList<> (canales.values());
		 
		 LogicaAPI logica = new LogicaAPI();
		 String token = logica.darToken(f.getIdEmpresa());
		 
//		 Agrego los pedidos pendientes de facturar
		 List<Long> pedidosSinFacturar = new ArrayList<>();
		 pedidosSinFacturar.add(Long.valueOf("271604"));		 
		 
		 List<Cliente> clientes = new ArrayList<>();
		 int diasBusqueda = 6;
		 
		 for (CanalMarketPlace canal : canalesL) 
		 {
			 List<Ordenes> pedidosFenicio = f.getPedidosAPI(canal.getId(),diasBusqueda);
			 List<EncuentraPedido> pedidosALL = f.getPedidos(canal.getId(),"",diasBusqueda, null, null);
			 
			 Map<String, Ordenes> ordenesF = new HashMap<>();
			 
			 for (Ordenes o : pedidosFenicio) {
				 ordenesF.put(o.getIdOrden(), o);
			 }
			 
//			Map<String, String> pedidosEncuentra = wms.PedidosID(token, diasBusqueda, "");			 
			List<EncuentraPedido> pedidos = new ArrayList<>();		 
			
			int in = 0;
			 for (EncuentraPedido p : pedidosALL) {
				 
				 p.setTrackingNumber(p.getIdPedido()+"");
				 
//				Cuando agrega a la lista de pedidos para guardar las ordenes,
//				agrego solo los pendientes de facturar 
				if(pedidosSinFacturar.contains(p.getIdPedido())) {
					 pedidos.add(p);
				}
				if(in==1) {
					break;
				}
			 }
			 
			 for (EncuentraPedido p : pedidos) 
			 {
				 if(p.getIdPedido().equals(new Long("268793")))
	        		{
	        			System.out.println("F");
	        		}
				 Ordenes ovf = ordenesF.get(p.getIdPedido()+"");
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
							if(l.getNombre().equals("Envío Normal"))
							{
								l.setSku(ENVIO_NORMAL);
							}
							else if(l.getNombre().equals("Envío Interior"))
							{
								l.setSku(ENVIO_INTERIOR);
							} 
							else if (l.getNombre().equals("¡Recibí tu pedido en 24 horas!"))
							{
								l.setSku(ENVIO_OTRO);
							}
							else if (l.getNombre().contains("Envío"))
							{
								l.setSku(ENVIO_OTRO);
							}
							//si el nombre es peya hay que ponerle el SKU
							else
							{
								l.setSku(ENVIO_NORMAL);
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
				 }
				 catch (Exception e) 
				 {
					 e.printStackTrace();
				 }
			 }
			 
			 //SINCRO ETIQUETAS
			 System.out.println("esperando 60 segundos para sincronizar etiquetas nuevamente");
			    try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 
			//CONSULTO ESTADO DE DESPACHADOS
		}
	}
}

