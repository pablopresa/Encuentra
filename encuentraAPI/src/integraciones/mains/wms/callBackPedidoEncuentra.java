package integraciones.mains.wms;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import beans.CallBackPedido;
import beans.jsonEstadoMP;
import beans.api.ImpresionesPDF_API;
import beans.datatypes.DataIDDescripcion;
import integraciones.erp.billerTata.FacturadorBiller;
import integraciones.erp.billerTata.TicketCambio;
import integraciones.erp.visualStore.forus.ecommerce.ClienteWSVisualForus1200;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class callBackPedidoEncuentra {

	public callBackPedidoEncuentra() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		
		Threaddealo tr2 = new Threaddealo(2,60000);
		Threaddealo tr8 = new Threaddealo(8,1000);
		
		tr2.setDaemon(true);
		tr8.setDaemon(true);
		
		tr2.start();
		tr8.start();
		

	}
}
class Threaddealo extends Thread {
	   private Thread t;
	   private int idEmpresa;
	   private int milisegundos;
	   
	   Threaddealo( int idEmpresa, int milisegundos) 
	   {
		   this.idEmpresa = idEmpresa;
		   this.milisegundos = milisegundos;
	      System.out.println("Creando hilo empresa " +  idEmpresa );
	   }
	   
	   public void run() 
	   {
		   Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
		   LogicaAPI l = new LogicaAPI();
		   String token = l.darToken(idEmpresa);
		   System.out.println("Corriendo hilo empresa " +  idEmpresa );
		   while(true) 
		   {
			   try 
			   {
				   List<CallBackPedido> callBacks = LogicaAPI.getCallBackPedidoEncuentra(idEmpresa);
				   StringBuilder updates = new StringBuilder();
	
				   for(CallBackPedido cb : callBacks) {
	
					   int empresa = cb.getIdEmpresa();
					   DataIDDescripcion resultadoCalback = new DataIDDescripcion(1,"");
					   switch(empresa) {
	
					   case 1:
						   	System.out.println("RUNNING1");
						   break;
	
					   case 2:
	
						   if(cb.getIdEstado() == 3) {
							   List<DataIDDescripcion> DocumentosEntregas = LogicaAPI.ecommerceDocumentosEntregas(cb.getIdPedido(), 2);
	
							   if(DocumentosEntregas != null && DocumentosEntregas.size() > 0) {
								   int numeroDoc = Integer.parseInt(DocumentosEntregas.get(0).getDescripcionB());
	
	
								   ClienteWSVisualForus1200 VisualForus = new  ClienteWSVisualForus1200();
	
								   resultadoCalback = VisualForus.EntregaPrepararYTerminar(numeroDoc, DocumentosEntregas, 1200);
							   }else {
								   resultadoCalback.setId(0);
								   resultadoCalback.setDescripcion("No se encontraron documentos");
								   System.out.println("No se encontraron documentos");
							   }
						   }
						   break;
	
					   case 3:
						   break;
	
					   case 4:
						   
						   break;
	
					   case 5:
						   break;
	
					   case 6:
						   break;
	
					   case 7:
						   break;
	
					   case 8:
						   
						   //propnto para facturar en biller
						   if(cb.getIdEstado()==3)
						   {
							   try {
								   FacturadorBiller fb = new FacturadorBiller();
								   fb.liberarFactutra(cb.getIdPedido());
							} catch (Exception e) {
								// TODO: handle exception
							}
							   
							   try {
								 //imprimir ticket de cambio en ps
								   List<TicketCambio> tickets = LogicaAPI.darTicketCambioBAS(cb.getIdPedido(),idEmpresa);
								   if(!tickets.isEmpty()) {
									   ImpresionesPDF_API ip = new ImpresionesPDF_API(idEmpresa);
									   ip.ImprimirTicketCambioBAS(tickets, empresa, cb.getIdPedido(),cb.getPrinter());
								   }
							} catch (Exception e) {
								// TODO: handle exception
							}
							   
							   try {
								   List<jsonEstadoMP> estadosMP = new ArrayList<jsonEstadoMP>();
								   jsonEstadoMP estado = new jsonEstadoMP();
								   estado.setCanal(1);
								   estado.setIdEmpresa(idEmpresa);
								   estado.setIdMarketPlace(cb.getIdPedido()+"");
								   estado.setIdPedido(cb.getIdPedido());
								   estado.setJson("     {         \"id\":\""+cb.getIdPedido()+"\",         \"estado\":\"preparado\"      } ");
								   estadosMP.add(estado);
								   LogicaAPI.putColaEstadoMarketPlace(estadosMP);
									
							} catch (Exception e) {
								// TODO: handle exception
							}
							   
							   
						   }
						   
						   
						   
						   //DESPACHO PEDIDO??
						   if(cb.getIdEstado() == 34) {
							   List<jsonEstadoMP> estadosMP = new ArrayList<>();
							   jsonEstadoMP estado = new jsonEstadoMP();
							   estado.setCanal(1);
							   estado.setIdEmpresa(idEmpresa);
							   estado.setIdMarketPlace(cb.getIdPedido()+"");
							   estado.setIdPedido(cb.getIdPedido());
							   estado.setJson("     {         \"id\":\""+cb.getIdPedido()+"\",         \"estado\":\"despachado\"      } ");
							   estadosMP.add(estado);
								LogicaAPI.putColaEstadoMarketPlace(estadosMP);
								
								//cambio estado wms
								List<DataIDDescripcion> entregados = new ArrayList<DataIDDescripcion>();
								entregados.add(new DataIDDescripcion(4,cb.getIdPedido()+""));
								cen.updateOrdersStatus(token,entregados);
						   }
						   
						   break;
	
					   case 9:
						   break;
	
					   case 10:
						   break;
					   }
	
					   updates.append(LogicaAPI.UpdateEcommerceDocumentosEntregas(resultadoCalback.getId(), empresa, cb.getIdLlamada(),resultadoCalback.getDescripcion()));
				   }
				   
				   if(!updates.toString().isEmpty())
				   {
					   LogicaAPI.persistir(updates.toString());
				   }
				   
				   sleep(this.milisegundos);
				   
				   
				   
				   
	
			   } catch (Exception e) 
		      {
		         System.out.println("hilo " +  idEmpresa + " error.");
		      }
		   }
	      
	   }
	   
	   public void start () 
	   {
	      System.out.println("Iniciando " +  idEmpresa );
	      if (t == null) {
	         t = new Thread (this, idEmpresa+"");
	         t.start ();
	      }
	   }
	}

	   
	
