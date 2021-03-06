package main.process_ecommerce;

import helper.PropertiesHelper;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import com.itextpdf.text.DocumentException;
import com.sun.xml.ws.policy.privateutil.PolicyUtils.Collections;

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import beans.Fecha;
import persistencia._EncuentraConexion;
import persistencia._EncuentraPersistir;
import dataTypes.DataArtPedidoPickup;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIdDescListado;
import dataTypes.DataPedidoPickup;
import logica.EnviaMail;
import logica.ImpresionesPDF;
import logica.Logica;

public class EcommerceProcessOrdersPickup {

	private String ok="";
	
	
	
	public EcommerceProcessOrdersPickup() 
	{
	}

	
	
	public static void process(int idDepositoPickup, int idEmpresa) 
	{
		Logica Logica = new Logica();
		
		
		String bodyM_1= "";
		
		
		/*son los mail de cada local a los que se le envia las notificaciones*/
		Hashtable<Integer, String> contactosLocales = new Hashtable<>();
		/*son los articulos que se vendieron y no se pidieron*/
	
		/*********
			CAMpos del obj:Id		descripcion			DescripcionB
		  	CANTIDAD	idArticulo			PEDIDOS(separados por coma)
			1			009.04307662435.0	59386
		 */
		
		/****************son los depositos a los cuales pedir mercaderias**********************/
		List <DataIDDescripcion> prioridades = new ArrayList<>();
		
		List<DataArtPedidoPickup> articulosPedido = new ArrayList<>();
		
		prioridades = Logica.darDepositosProcessEcommercePickup(idDepositoPickup,idEmpresa); //locales donde pedir
		
		articulosPedido=Logica.darArticulosOrderProcessEcommercePickup(idDepositoPickup,idEmpresa); //pedidos sin pedir sin agrupar
		
		
		Hashtable<String, Integer> stockPedidos = new Hashtable<>();
		
		/*********se usa para la consulta de stock contra Visual Store******/
		List <DataIDDescripcion> ArtsPedidosNoConfirmados = Logica.darStocksPedidosProcessEcommerce(null,idEmpresa);
		 for (DataIDDescripcion s : ArtsPedidosNoConfirmados) 
			{
				stockPedidos.put(s.getDescripcion()+":"+s.getDescripcionB(), s.getId());
			}
		 
		String articulosIn = "";
		
		List<DataIDDescDescripcion> articulosPedidoWHS = new ArrayList<>();
		
		for (DataArtPedidoPickup a : articulosPedido) 
		{
			articulosIn+="'"+a.getIdArticulo()+"',";
		}
		
		
		
		if(articulosIn.equals(""))
		{
			
		}
		else
		{
			
			articulosIn=articulosIn.substring(0,articulosIn.length()-1);
			
			
			/*********se usa para la consulta de stock contra Visual Store******/
			String depositosIn = "";
			
			for (DataIDDescripcion d : prioridades) 
			{
				depositosIn+=""+d.getDescripcion()+",";
				contactosLocales.put(Integer.parseInt(d.getDescripcion()), d.getDescripcionB());
			}
			
			depositosIn=depositosIn.substring(0,depositosIn.length()-1);
			
			
			String priodidadesIn = " case IdDeposito ";
			
			for (DataIDDescripcion d : prioridades) 
			{
				priodidadesIn+=" when "+d.getDescripcion() +" then "+d.getId() ;
			}
			priodidadesIn+=" end ";
			
			String consultaStock = " select * from "+  
									" (select stock+seniado stk, IdDeposito,idArticulo, "+
										priodidadesIn+" prioridad from MovStockTotal ms where ms.Stock>0 AND IdDeposito in ("+depositosIn+") and IdArticulo in ("+articulosIn+")) t0 "+
									" order by t0.prioridad ";
					
			
			System.out.println(consultaStock);
			
			
			
			
			
			
			Hashtable<String, List<DataIDDescripcion>> articulosPedidos = new Hashtable<String, List<DataIDDescripcion>>();
			
			
			for (DataArtPedidoPickup ap : articulosPedido) 
			{
				DataIDDescripcion articuloPedido = new DataIDDescripcion(ap.getCant(), ap.getIdArticulo());
				
				if(articulosPedidos.get(ap.getIdPedido())==null)
				{
					List<DataIDDescripcion> artiEnPedido = new ArrayList<>();
					artiEnPedido.add(articuloPedido);
					
					articulosPedidos.put(ap.getIdPedido(), artiEnPedido);
				}
				else
				{
					List<DataIDDescripcion> artiEnPedido = articulosPedidos.get(ap.getIdPedido());
					artiEnPedido.add(articuloPedido);
					
					articulosPedidos.put(ap.getIdPedido(), artiEnPedido);
				}
				
			}
			
			
			List<String> pedidosCompletos = new ArrayList<>();
			
			List<DataIDDescDescripcion> stocks = Logica.darStockOrderItems(consultaStock, stockPedidos);
			//id = stock
			//descripcion = deposito
			//desc=articulo
			
			Iterator<Map.Entry<String, List<DataIDDescripcion>>> it = articulosPedidos.entrySet().iterator();
			while (it.hasNext()) 
			{
			  
			  List<DataIDDescripcion> listaArticulosPediriaTienda = new ArrayList<>();
			  
			  Map.Entry<String, List<DataIDDescripcion>> entry = it.next();

			  String idPedido = entry.getKey();
			  
			  List<DataIDDescripcion> listaArticulosPedido = entry.getValue();
			  
			  for (DataIDDescripcion a : listaArticulosPedido) 
			  {
				 for (DataIDDescDescripcion astock : stocks) 
				 {
					if(a.getDescripcion().equals(astock.getDesc()) && a.getId()<=astock.getId())//mismo articulo y la tienda tiene lo mismo o mas de lo que preciso. 
					{
						int stockRestante = astock.getId()-a.getId();
						
						astock.setId(stockRestante);
						
						listaArticulosPediriaTienda.add(a);
						break;
					}
					
				 }//for de stock
			  }// for de articulos del pedido
			  
			  
			  
			  boolean foundAll = false;
			  for (DataIDDescripcion ape : listaArticulosPedido) 
			  {
				 boolean found = false;
				 for (DataIDDescripcion apia : listaArticulosPediriaTienda) 
				 {
					 
					 if(ape.getDescripcion().equals(apia.getDescripcion()) && ape.getId()==apia.getId())
					 {
						 found=true;
					 }
				 }
				 if(!found)
				 {
					 foundAll = false;
					 break;
				 }
				 else
				 {
					 foundAll=true;
				 }
			  }
			  
			  if(foundAll)
			  {
				  
				  pedidosCompletos.add(idPedido);
				  System.out.println("puedo pedir el 100% del pedido "+idPedido+" al deposito "+idDepositoPickup+", que coincide con el del pickup");
				  
				  Logica.logPedido(Long.parseLong(idPedido), 0, 0, "Encuentra va a pedir el 100% del pedido "+idPedido+" al deposito "+idDepositoPickup+", que coincide con el del pickup",0,idEmpresa);
				  
				  for (DataIDDescripcion ape : listaArticulosPedido) 
				  {
					  Logica.logPedido(Long.parseLong(idPedido), 0, 0, " Guardando pedido de articulo "+ape.getDescripcion()+" a deposito "+idDepositoPickup,0,idEmpresa);
					  Logica.persistir("INSERT INTO `ecommerce_pedido_articulos_req` (`idPedido`, `idArticulo`, `Deposito`, `CantidadRequerida`, `CantidadProcesasa`, `Confirmado`,`procesarEnPickup`,`idEmpresa`) " +
																		" VALUES ('"+idPedido+"', '"+ape.getDescripcion()+"', '"+idDepositoPickup+"', '"+ape.getId()+"', '0', '0',1,"+idEmpresa+");");
				  }
				  
				  
				  
			  }
			  else
			  {
				  System.out.println("NO puedo pedir el 100% del pedido "+idPedido+" al deposito "+idDepositoPickup+", del pickup");
				  Logica.logPedido(Long.parseLong(idPedido), 0, 0, "NO puedo pedir el 100% del pedido "+idPedido+" al deposito "+idDepositoPickup+", del pickup",0,idEmpresa);
			  }
			  
			 
			}			
			
			
			
			if(!pedidosCompletos.isEmpty())
			{
				//armar el PDF
				List<DataPedidoPickup> pedidosPickup = Logica.darDatosPedidosPickup(pedidosCompletos,idEmpresa);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String htmlTable = "";
				
				for (DataPedidoPickup pi : pedidosPickup) 
				{
					List<DataArtPedidoPickup> articulosP = new ArrayList<>();
					 List<DataIDDescripcion> artPe =  articulosPedidos.get(pi.getIdPedido());
					 for (DataIDDescripcion a : artPe) 
					 {
						 DataArtPedidoPickup ap = new DataArtPedidoPickup(pi.getIdPedido(), a.getDescripcion(), idDepositoPickup, a.getId(),0,0);
						 Logica.CambioSubestadoEcommercePedido(Long.parseLong(pi.getIdPedido()),a.getDescripcion(), 1,idDepositoPickup,idEmpresa);
						 articulosP.add(ap);
						 
						 htmlTable+="	<tr> "+
									"	<td style=\"width: 177.533px;\">"+pi.getFecha()+"</td> "+
									"	<td style=\"width: 177.533px;\">"+pi.getIdPedido()+"</td> "+
									"	<td style=\"width: 177.533px;\">"+a.getDescripcion()+"</td> "+
									"	<td style=\"width: 177.533px;\">"+a.getId()+"</td> "+
									"	</tr> ";
						 
					 }
					 
					 pi.setArticulos(articulosP);
					 
					 
					 
				}
				
				try 
				{
					String path = ImpresionesPDF.ImprimirPedidosArticuloReq(pedidosPickup,idEmpresa);
					
					Call_WS_APIENCUENTRA call = new Call_WS_APIENCUENTRA();
					
					String id = (int)((Math.random() * 9000000)+1000000)+"";
					
					//Ponerlo en la cola de impresion
					call.PutColaImpresion(id, path, 0, idDepositoPickup,1,idEmpresa,1);
					
					
					
				} 
				catch (FileNotFoundException | DocumentException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//enviar mail al local
				
				String html = "<h2 style=\"text-align: center;\"><strong>Pedidos para Pickup</strong></h2> "+
							"	<p>&nbsp;</p> "+
							"	<p>Atenci&oacute;n, los siguientes pedidos son de ventas WEB que deben ser procesadas por su local ya que los clientes han elegido hacer el pickup en la tienda.</p> "+
							"	<p>La mercader&iacute;a se debe procesar con el colector, una vez aprontado el pedido no enviar a central.</p> "+
							"	<table style=\"height: 48px; background-color: silver; border-color: black; margin-left: auto; margin-right: auto;\" border=\"1\" width=\"553\"><caption>&nbsp;</caption> "+
							"	<tbody> "+
							"	<tr> "+
							"	<td style=\"width: 177.533px;\">Fecha</td> "+
							"	<td style=\"width: 177.533px;\">Pedido</td> "+
							"	<td style=\"width: 177.533px;\">Articulo</td> "+
							"	<td style=\"width: 177.533px;\">Cantidad</td> "+
							"	</tr> ";
							
				String html3=		"	</tbody> "+
							"	</table> "+
							"	<p>No responder, notificaci&oacute;n generada automaticamente por encuentra</p>";
					
				
				String body = html+htmlTable+html3;
				
				
				
				
				List<String> mailsDestino = new ArrayList<>();
				
				
				String []contactos = contactosLocales.get(idDepositoPickup).split(",");
				for (int i = 0; i < contactos.length; i++) 
				{
					mailsDestino.add(contactos[i]);
				}
					
				EnviaMail.enviarMailHTMLOD("NotificacionesEncuentra@stadium.local", mailsDestino, "Debes procesar Venta Web Pickup en Tienda "+idDepositoPickup, body, idEmpresa);
			}
			
			
				
			
			
			
		}
		
		
		
		
		

	}
	
	
	
	
	
	
	

}
