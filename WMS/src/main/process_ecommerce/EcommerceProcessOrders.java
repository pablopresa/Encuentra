package main.process_ecommerce;

import helper.PropertiesHelper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import beans.Fecha;
import beans.Usuario;
import persistencia._EncuentraConexion;
import persistencia._EncuentraPersistir;

import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIdDescListado;
import logica.EnviaMail;
import logica.Logica;

public class EcommerceProcessOrders {

	private String ok="";
	
	
	
	public EcommerceProcessOrders() 
	{
	}

	/***********************NEGAR PAR 
	 * @throws Exception *****************************/
	public void negarSKU(String idArticulo, Long idPedido, int iddepoCancela, String motivo, int cantidad, int idEmpresa) throws Exception
	{
		Logica Logica = new Logica();
		Logica.rechazarArticuloReq(idArticulo,idPedido,iddepoCancela,motivo,idEmpresa,false,false);
		
			Logica.logPedido(idPedido, 0, 1, "Rechazando pedido de articulo "+idArticulo+" por parte del deposito "+iddepoCancela+". Motivo: "+motivo,0,idEmpresa); 
		
		
		
		
	}
	 
	/***********************Confirmar par *****************************/
	public void confirmarSKU(String idArticulo, int iddepoConfirma, int cantidad, Long idPedido, int idEmpresa, Usuario u)
	{
		Logica Logica = new Logica();
		Logica.CambioSubestadoEcommercePedido(idPedido, idArticulo, 2,iddepoConfirma,idEmpresa);
		Logica.confirmarArticuloReq(idArticulo,idPedido,iddepoConfirma,idEmpresa);
		
		Logica.logPedido(idPedido, 0, 2, "Confirmando envio de articulo "+idArticulo+" por parte del deposito "+iddepoConfirma,0,idEmpresa);
		if(todosConfirmados(idPedido,idEmpresa))
		{
			Logica.updateEcommerceEstado(idPedido, 2,idEmpresa,u);
			
		}
		
		
	}
	/***********************Confirmar par sin idPedido *****************************/
	public void confirmarSKU(String idArticulo, int iddepoConfirma, int cantidad, int idEmpresa, Usuario u)
	{
		Logica Logica = new Logica();
		List<Long> pedidosConfirmados =  Logica.confirmarArticuloReq(cantidad,idArticulo,iddepoConfirma,idEmpresa);
		
		
		for (Long idPedido : pedidosConfirmados) 
		{
			Logica.logPedido(idPedido, 0, 2, "Confirmando envio de articulo "+idArticulo+" por parte del deposito "+iddepoConfirma,0,idEmpresa);
			if(todosConfirmados(idPedido,idEmpresa))
			{
				Logica.updateEcommerceEstado(idPedido, 2,idEmpresa,u);
				
			}
		}
		
		
		
	}
	
	public boolean todosConfirmados(Long idPedido, int idEmpresa)
	{
		Logica Logica = new Logica();
		return Logica.confirmarArticuloReq(idPedido,null,idEmpresa);
		
		
	}
	
	
	 
	
	
	
	
	
	
	
	
	public static List<DataIDDescDescripcion> process(String idArticulo,Long idPedidoNO,List<DataIDDescripcion> iddepoCancela, int cantidad, int idEmpresa) 
	{
		Logica Logica = new Logica();
		
		/*****************************************html de mails a locales***********************************************/
		String bodyM_1= ""+
		"<p>&nbsp;</p> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='padding: 10px 0 30px 0;'> "+
			"	<table style='border: 1px solid #cccccc; border-collapse: collapse;' border='0' width='600' cellspacing='0' cellpadding='0' align='center'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;' align='center' bgcolor='#70bbd9'> "+
			"	<p>Pedido de articulos facturados por la web</p> "+
			"	</td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td style='padding: 40px 30px 40px 30px;' bgcolor='#ffffff'> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'><strong>Hola @mail@, estamos necesitando ciertos articulos que hemos vendido y se encuentran en su tienda.</strong></td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td style='padding: 20px 0px 30px; color: #153643; font-family: Arial,sans-serif; font-size: 16px; line-height: 20px; text-align: center;'> "+
			"	<p>A continuaci&oacute;n encontrar&aacute; una lista con los items que se vendieron.<br /> <span style='color: #ff0000;'><strong>El cliente ya los pag&oacute;</strong></span>, solamente necesitamos que nos confirmen que los los envian.</p> "+
			"	<a href='@URL_Confirm@' style='text-decoration: none; color: black'> "+
			"	<div style='padding:9px;background-color: #ee4c50;width: 50%; height: 60px; margin: auto;'> "+
			"	<strong>Confirmar Envio de Articulos</strong> "+
			"	</div> "+
			"	</a> "+
			"	</td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td valign='top' width='260'> "+
			"	<table style='text-align: center; border-collapse: collapse;' border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='border: 1px solid silver;'>Imagen del articulo</td> "+
			"	<td style='border: 1px solid silver;'>Nombre del articulo</td> "+
			"	<td style='border: 1px solid silver;'>Cantidad Solicitada</td> "+
			"	<td style='border: 1px solid silver;'>Pedido</td> "+
			"	</tr>";
		
		
		String bodyM_b = "</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"<tr> "+
					"<td style='padding: 30px 30px 30px 30px;' bgcolor='#ee4c50'> "+
					"<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
					"<tbody> "+
					"<tr> "+
					"<td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>Notificaci&oacute;n generada automaticamente por encuentra<br />No es necesario que responda a esta direcci&oacute;n de correo.</td> "+
					"<td align='right' width='25%'> "+
					"<table border='0' cellspacing='0' cellpadding='0'> "+
					"<tbody> "+
					"<tr> "+
					"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
					"<td style='font-size: 0; line-height: 0;' width='20'>&nbsp;</td> "+
					"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"<!--analytics--> "+
					"<p>&nbsp;</p>";
		/*******************************************fin html*********************************************/
		
		
		/*son los mail de cada local a los que se le envia las notificaciones*/
		Hashtable<Integer, String> contactosLocales = new Hashtable<>();
		/*son los articulos que se vendieron y no se pidieron*/
		List<DataIDDescripcion> articulos = new ArrayList<>();
		/*********
			CAMpos del obj:Id		descripcion			DescripcionB
		  	CANTIDAD	idArticulo			PEDIDOS(separados por coma)
			1			009.04307662435.0	59386
		 */
		
		/****************son los depositos a los cuales pedir mercaderias**********************/
		List <DataIDDescripcion> prioridades = new ArrayList<>();
		
		List<DataIDDescDescripcion> articulosPedido = new ArrayList<>();
		if(idArticulo!=null)
		{
			articulos.add(new DataIDDescripcion(cantidad,idArticulo));
			articulos.get(0).setDescripcionB(idPedidoNO+"");
			articulosPedido.add(new DataIDDescDescripcion(cantidad, idPedidoNO+"", idArticulo));
			prioridades = Logica.darDepositosProcessEcommerce(iddepoCancela,idEmpresa);
		}
		else
		{
			articulos = Logica.darArticulosProcessEcommerce(idEmpresa);//articulos sin pedir agrupados en articulos
			articulos.remove(0);
			prioridades = Logica.darDepositosProcessEcommerce(iddepoCancela,idEmpresa); //locales donde pedir
			articulosPedido=Logica.darArticulosOrderProcessEcommerce(idEmpresa); //pedidos sin pedir sin agrupar
		}
				
		List <DataIDDescripcion> ArtsPedidosNoConfirmados = Logica.darStocksPedidosProcessEcommerce(null,idEmpresa);
		Hashtable<String, Integer> stockPedidos = new Hashtable<>();
		
		 for (DataIDDescripcion s : ArtsPedidosNoConfirmados) 
			{
										   //articulo:deposito
				stockPedidos.put(s.getDescripcion()+":"+s.getDescripcionB(), s.getId());
			}
		
		//id = cantidad Pendiente
		//descB = pedido
		//descripcion=articulo
		
		
		
		/*********se usa para la consulta de stock contra Visual Store******/
		String articulosIn = "";
		
		List<DataIDDescDescripcion> articulosPedidoWHS = new ArrayList<>();
		
		for (DataIDDescripcion a : articulos) 
		{
			articulosIn+="'"+a.getDescripcion()+"',";
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
			
			List<DataIDDescDescripcion> stocks = Logica.darStockOrderItems(consultaStock, stockPedidos);
			//id = stock
			//descripcion = deposito
			//desc=articulo
			
			
			
			/*******por cada articulo, te hace una lista de dataIdDesc que representa los depositos y cantidades a los que pidio*/
			Hashtable<String, List<DataIDDescripcion>> articulosPedidos = new Hashtable<String, List<DataIDDescripcion>>();
			
			
			/**********estoy recorriendo los articulos de los pedidos*********/
			for (DataIDDescripcion art : articulos) 
			{
				if(art.getDescripcionB().contains("2472903704"))
				{
					System.out.println("2472903704");
				}
				if(art.getDescripcion().equals("201.B1911015737.0"))
				{
					System.out.println("debagueame");
				}
				/**************son los id de pedido de ese articulo, porque el mismo se pudo haber vendido en 2 pedidos, por eso pregunta si contiene coma***********************************/
				List<String> pedidosArt = new ArrayList<>();
				
				if(art.getDescripcionB()!=null && art.getDescripcionB().contains(","))
				{
					String [] arreglo = art.getDescripcionB().split(",");
					for (int i = 0; i < arreglo.length; i++) 
					{
						pedidosArt.add(arreglo[i]);
						
					}
				}
				else
				{
					pedidosArt.add(art.getDescripcionB());
				}
				
				for (String p : pedidosArt) 
				{
					try
					{
						Logica.logPedido(Long.parseLong(p), 0, 0, " Pidiendo articulo "+art.getDescripcion(),0,idEmpresa);
					}
					catch(Exception e){}
				}
				
				int cantPedida=art.getId();
				String arti = art.getDescripcion();
				
				/***
				 * lista de depositos a los que se le debe pedir ese articulo
				 */
				List<DataIDDescripcion> depositosPedir = new ArrayList<>();
				
				int cantidadPendiente=cantPedida;
				
				
				for (DataIDDescDescripcion s : stocks) 
				{
					if(cantidadPendiente==0)
					{
						break;
					}
					else if(s.getDesc().equals(arti))//si son el mismo articulo
					{
						if(s.getId()>=cantidadPendiente)//preginto si tiene mas stock del que le pido
						{													//cant pedida,	id de tienda			
							DataIDDescripcion depoPido = new DataIDDescripcion(cantidadPendiente,s.getDescripcion());
							depositosPedir.add(depoPido);
							
							for (String p : pedidosArt) 
							{
								try
								{
									Logica.logPedido(Long.parseLong(p), 0, 0, " Pidiendo articulo "+art.getDescripcion()+" a deposito "+depoPido.getDescripcion(),0,idEmpresa);
								}
								catch(Exception e){}
							}
							
							
							cantidadPendiente=0;
							break;
						}
						else if(s.getId()>0)//no alcanza para todo lo que pidio pero hay mas de 0
						{
							DataIDDescripcion depoPido = new DataIDDescripcion(s.getId(),s.getDescripcion());//le saco todo lo que tiene
							depositosPedir.add(depoPido);
							cantidadPendiente-=s.getId();
							
							for (String p : pedidosArt) 
							{
								try
								{
									Logica.logPedido(Long.parseLong(p), 0, 0, " SUPUESTAMENTE - Pidiendo articulo "+art.getDescripcion()+" a deposito "+depoPido.getDescripcion()+ " porque el deposito no tiene stock suficiente",0,idEmpresa);
								}
								catch(Exception e){}
							}
						}
					}
				}
				
				
				
					articulosPedidos.put(arti, depositosPedir);
				
				
				
				
			}//cierro el for de articulos en pedidos
			
			
			/************recorro los pedidos uno por uno sin agrupar por articulo******************/
			for (DataIDDescDescripcion ap : articulosPedido) 
			{
				
				try
				{
					if(ap.getDesc().contains("2472903704"))
					{
						System.out.println("2472903704");
					}
					//id = cantidad Pendiente
					//desc = pedido
					//descripcion=articulo
					//descII= depositoPedido
					
					String arti=ap.getDescripcion();
					int cantR = ap.getId();
					String idPedido = ap.getDesc();
					
					/****************articulos a los que le debo pedir******************/
					List<DataIDDescripcion> artsWhs = articulosPedidos.get(arti);
					
					
					/****************EN CASO QUE NO HAYA STOCK EN NINGUN LADO******************/
					if(artsWhs==null){
						DataIDDescDescripcion pedir = new DataIDDescDescripcion();
						pedir.setId(cantR);
						pedir.setDesc(idPedido);
						pedir.setDescII("0");//depopsito al cual lo pide cuando no hay stock
						pedir.setDescripcion(arti);
						
						articulosPedidoWHS.add(pedir);
					}
					/*******************************************************************/
					
					
					List<DataIDDescripcion> artsWhsSobran = new ArrayList<>();
					int cantPend=cantR;
					
					for (DataIDDescripcion ad : artsWhs) 
					{
						System.out.println();
						if(cantPend>0)
						{
							if(ad.getId()==cantPend)
							{
								DataIDDescDescripcion pedir = new DataIDDescDescripcion();
								pedir.setId(cantPend);
								pedir.setDesc(idPedido);
								pedir.setDescII(ad.getDescripcion());//depopsito al cual lo pide
								pedir.setDescripcion(arti);
								
								articulosPedidoWHS.add(pedir);
								cantPend=0;
							}
							else if(ad.getId()>cantPend)
							{
								DataIDDescDescripcion pedir = new DataIDDescDescripcion();
								pedir.setId(cantPend);
								pedir.setDesc(idPedido);
								pedir.setDescII(ad.getDescripcion());
								pedir.setDescripcion(arti);
								
								articulosPedidoWHS.add(pedir);
								
								artsWhsSobran.add(new DataIDDescripcion(ad.getId()-cantPend,ad.getDescripcion()));
								cantPend=0;
							}
							else if(ad.getId()<cantPend)
							{
								DataIDDescDescripcion pedir = new DataIDDescDescripcion();
								pedir.setId(ad.getId());//cantidad
								pedir.setDesc(idPedido);//pedido
								pedir.setDescII(ad.getDescripcion());//depPedido
								pedir.setDescripcion(arti);//articulo
								
								articulosPedidoWHS.add(pedir);
								
								cantPend-=ad.getId();
							}
						}
						else
						{
							artsWhsSobran.add(ad);
						}
						
						
					}
					
					if(cantPend > 0)
					{
						/**************si cantidad pendiente es mas que 0, es que no hay donde pedir ese articulo, lo guardamos como pedido al deposito 0***********************/
						DataIDDescDescripcion pedir = new DataIDDescDescripcion();
						pedir.setId(cantPend);
						pedir.setDesc(idPedido);
						pedir.setDescII("0");//depopsito al cual lo pide cuando no hay stock
						pedir.setDescripcion(arti);
						
						articulosPedidoWHS.add(pedir);
					}
					
					articulosPedidos.put(arti, artsWhsSobran);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				
				
			}/*********fin de for sobre articulos del pedido***/
			
			
			
			try 
			{
				
				Hashtable<Integer, Integer> htDepositosPedidos = new Hashtable<>();
				
				List<DataIDDescripcion> pedido99 = new ArrayList<>();
				
				List<DataIDDescripcion> pedido13 = new ArrayList<>();
					
				for (DataIDDescDescripcion pe : articulosPedidoWHS) 
				{
					/**if(pe.getDescripcion().equals("001.06003000240.0"))
					{
						System.out.println("debagueame");
					}**/
					
					try
					{
						htDepositosPedidos.put(Integer.parseInt(pe.getDescII()), Integer.parseInt(pe.getDescII()));
						//id = cantidad Pendiente
						//desc = pedido
						//descripcion=articulo
						//descII= depositoPedido
						
						if(pe.getDescII().equals("99") || pe.getDescII().equals("13"))
						{
							DataIDDescripcion dat = new DataIDDescripcion(pe.getId(), pe.getDescripcion());
							dat.setIdLong(new Long(pe.getDesc()));
							pedido99.add(dat);
						}
						
						/*if(pe.getDescII().equals("13"))
						{
							pedido99.add(new DataIDDescripcion(pe.getId(), pe.getDescripcion()));
						}*/
						
						System.out.println(pe.getDesc() +"\t"+ pe.getDescripcion() +"\t"+ pe.getId() +"\t"+ pe.getDescII());
						
						Logica.logPedido(Long.parseLong(pe.getDesc()), 0, 0, " Guardando pedido de articulo "+pe.getDescripcion()+" a deposito "+pe.getDescII(),0,idEmpresa);
						
						
						
						Logica.persistir("INSERT INTO `ecommerce_pedido_articulos_req` (`idPedido`, `idArticulo`, `Deposito`, `CantidadRequerida`, `CantidadProcesasa`, `Confirmado`) " +
								" VALUES ('"+pe.getDesc()+"', '"+pe.getDescripcion()+"', '"+pe.getDescII()+"', '"+pe.getId()+"', '0', '0');");
					}
					catch(Exception e)
					{
						
					}
					
					
				}
				
				
				
				
				/***************************************agrego los de la 99 al encuentra***********************************/
				if(!pedido99.isEmpty())
				{
					boolean manual = false;
					Logica.darArticuloRepoFromLoad(pedido99,71,manual,idEmpresa);
					int last = Logica.darNextSincRepo(idEmpresa)-1;
					Logica.actualizarOKSincRepo(0,last,idEmpresa);//la dejo en OK 0 para que no interfiera con el picking
				}
				
				/***************************************agrego los de la 13 al encuentra***********************************/
				/*if(!pedido13.isEmpty())
				{
					boolean manual = false;
					Logica.darArticuloRepoFromLoad(pedido13,71,manual);
					int last = Logica.darNextSincRepo()-1;
					Logica.actualizarOKSincRepo(0,last);//la dejo en OK 0 para que no interfiera con el picking
				}*/
				
				
				
				
			/**************************************NOTIFICACIONES****************************************/
				//voy a buscar los ariculos que no me confirmaron aun
				
				String deposINN = "";
				for (Integer idDepo : new ArrayList<>(htDepositosPedidos.values())) 
				{
					
					List<String> mailsDestino = new ArrayList<>();
						
					
					String []contactos = contactosLocales.get(idDepo).split(",");
					for (int i = 0; i < contactos.length; i++) 
					{
						mailsDestino.add(contactos[i]);
					}
						
					String primerDestino = mailsDestino.get(0).split("@")[0];
					bodyM_1 = bodyM_1.replace("@mail@", primerDestino);
						
					PropertiesHelper pH=new PropertiesHelper("urlConfirmEcommerce");
					pH.loadProperties();
					String url_confirm = pH.getValue("url");
						
						
					bodyM_1 = bodyM_1.replace("@URL_Confirm@", url_confirm+idDepo);
						
					
					String htmlTablasMedio="";
					
					
					htmlTablasMedio+="<!-- ac? iban los Articulos--> "+
							"	<tr> "+
							"		<td  colspan='4' style='border: 1px solid silver;'>" +
							"  			 ************YA NO SE MUESTRA MAS EL DETALLE************* <br/> POR FAVOR SIGA EL VINCULO PARA VER LOS ARTICULOS" +
							"		</td> "+
							"	</tr> "+
							"	<!--Fin Articulos-->";
						
						EnviaMail.enviarMailHTMLOD("NotificacionesEncuentra@stadium.local", mailsDestino, "Venta Web Solicita articulos a deposito "+idDepo, bodyM_1+htmlTablasMedio+bodyM_b,idEmpresa);
						
						bodyM_1 = bodyM_1.replace(primerDestino, "@mail@");
						bodyM_1 = bodyM_1.replace(url_confirm+idDepo, "@URL_Confirm@");
						
					}
					
				
				
				
				
				
			
			
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
				
			}	
		}
		
		
		
		return articulosPedidoWHS;

	}
	
	
	
	
	
	
	
	
	

}
