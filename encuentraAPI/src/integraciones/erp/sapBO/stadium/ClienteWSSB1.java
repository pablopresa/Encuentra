package integraciones.erp.sapBO.stadium;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


import org.apache.xerces.parsers.DOMParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import beans.Fecha;
import beans.Usuario;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.helper.PropertiesHelper;
import integraciones.erp.sapBO.stadium.ArrayOfUtilDesglose;
import integraciones.erp.sapBO.stadium.RespWSSAP;
import integraciones.erp.sapBO.stadium.UtilDesglose;
import integraciones.erp.sapBO.stadium.WMSSAPB1;
import integraciones.erp.sapBO.stadium.WMSSAPB1Soap;
import integraciones.erp.taface.clienteWSTAFACE_firmar._ClienteTAFACE_firma;
import integraciones.erp.visualStore.stadium.v1.WSCommunicate;

public class ClienteWSSB1 
{

	 private String dbServer;
	 private String dbName;
	 private String dbType;
	 private String compUser;
	 private String compPwd;
	 private String language;
	 private String licenseServer;
	 
	 
	 
	public ClienteWSSB1()
	{
		try 
		{
			PropertiesHelper pH=new PropertiesHelper("clienteWSSB1");
			pH.loadProperties();
			
			this.dbServer=pH.getValue("dbServer");
			this.dbName=pH.getValue("dbName");
			this.dbType=pH.getValue("dbType");
			this.compUser=pH.getValue("compUser");
			this.compPwd=pH.getValue("compPwd");
			this.language=pH.getValue("language");
			this.licenseServer=pH.getValue("licenseServer");
			
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String GrabarBarras(String articulo, String barra,String ssId)
	{
		WMSSAPB1 servicio = new WMSSAPB1();
		WMSSAPB1Soap cliente = servicio.getWMSSAPB1Soap();
		
		
		
		
		String log = cliente.login(dbServer, dbName, dbType, compUser, compPwd, language, licenseServer);
		String  token =  darValorClaveXML(log,"Detalle", ssId,"Iniciando Sesion en SAP");
		String respuesta = cliente.crearBarCode(token,articulo,barra);
		
		/***********************llamada WS Visual***************************/
		WSCommunicate ws = new WSCommunicate();
		//ws.vincular(99,50000, articulo, barra, false);
		//cliente.logout(token);
		/***********************FIN llamada WS Visual***************************/
		return darValorClaveXML(respuesta,"Detalle", ssId, "Grabando C?digo de barras");
		
	}
	
	
	public String darValorClaveXML(String xml, String tagname, String ssId, String accion)
	{
		
		System.out.println("\n \n \n\n Respuesta WS"); 
		System.out.println(xml);
		System.out.println("\n \n \n\n FIN Respuesta WS");
		String toSave ="";
		String valorBuscado = "";
		DOMParser parser = new DOMParser();
		try 
		{
		    parser.parse(new InputSource(new java.io.StringReader(xml)));
		    Document doc = parser.getDocument();
		    NodeList nodo = doc.getElementsByTagName(tagname);
		     valorBuscado = nodo.item(0).getTextContent();
		    toSave = valorBuscado;
		} 
		catch (Exception e) 
		{
			valorBuscado = "";
			toSave = xml;
		}    // handle SAXException
		
		Fecha fecha = new Fecha();
		
		//@@stadium@@
		//logica.guardarLogClienteWSSB1(toSave,ssId,accion,fecha.darFechaDia_Mes_Anio_HoraBarra());
		
		return valorBuscado;
		
		
		
		
		
	}
	/*
	
	public String guardarRecepOC(List<DataIDDescDescripcion> articulos, String doc, String coment, String proveedor, String depoRecep, String comentario, String ssId, boolean importa)
	{
		
		WMSSAPB1 servicio = new WMSSAPB1();
		WMSSAPB1Soap cliente = servicio.getWMSSAPB1Soap();
		
		
		int baseType = 22;
		if(importa)
		{
			baseType = 18;
		}
		
		String log = cliente.login(dbServer, dbName, dbType, compUser, compPwd, language, licenseServer);
		String  token =  darValorClaveXML(log,"Detalle",ssId,"Iniciando Sesion en SAP");
		
		ArrayOfUtilDesglose arreglo = new ArrayOfUtilDesglose();

		
		for (DataIDDescDescripcion acb : articulos) 
		{
			UtilDesglose articulo = new UtilDesglose();
			articulo.setItemCode(acb.getDescripcion());
			articulo.setQuantity(String.valueOf(acb.getId()));
			articulo.setWareHouseCode(depoRecep);
			articulo.setBaseType(String.valueOf(baseType));
			articulo.setBaseEntry(acb.getDesc());
			articulo.setBaseLine(String.valueOf(acb.getPorcentaje()));
			
			arreglo.getUtilDesglose().add(articulo);
		
		}
		
		
		Fecha fecha = new Fecha();
		String YYYYMMDD = fecha.darFechaAnio_Mes_Dia();
		
		
		System.out.println("");
		String message = darValorClaveXML(cliente.crearRecepcion(token, proveedor, YYYYMMDD, doc, arreglo, comentario),"Detalle",ssId, "Grabando Recepci?n");
		cliente.logout(token);
		
		return message;
		
	}
	*/
	/*
	public String guardarMovStock(List<List<DataPicking>> lista, boolean mayorista, Usuario u, int prioridad, int motivo, String ssId, boolean productivoSAP)
	{		
		Logica Logica = new Logica();
		WMSSAPB1 servicio = null;
		
		WMSSAPB1Soap cliente = null;
		String  token = "";
			
		try
		{
			servicio = new WMSSAPB1();
			
		//	servicio.g
			cliente = servicio.getWMSSAPB1Soap();
			
			
			String log = cliente.login(dbServer, dbName, dbType, compUser, compPwd, language, licenseServer);
			token =  darValorClaveXML(log,"Detalle",ssId,"Iniciando Sesion en SAP");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		Hashtable<Integer, DataDescDescripcion> depositosSAPENC = Logica.encuentraDarIdDepositos();
	
	
		
		for (List<DataPicking> list : lista) 
		{
			try
			{
				
				//hago un movimiento por cada destino
				ArrayOfUtilDesglose arreglo = new ArrayOfUtilDesglose();
				ArrayOfUtilDesglose arregloEntrega = new ArrayOfUtilDesglose();
				String idCliente = "";
				String depDest ="";
				String depOr ="";
				String nombreDest = "";
				String nombreOr = "";
				int lin= 0;
				int idSol =0;
				int idPedido = 0;
				
				int idDepVisualOrigen = 0;
				int idDepVisualDestino = 0;
				
				
				for (DataPicking p : list) 
				{
					if(p.getVerificada()>0)
					{
						UtilDesglose articulo = new UtilDesglose();
						UtilDesglose articuloEntrega = new UtilDesglose();
						
						if(true)
						{
							p.setRemitida(p.getVerificada());
							depDest = depositosSAPENC.get(p.getDestino().getId()).getId();
							depOr = depositosSAPENC.get(p.getOrigen().getId()).getId();
							
							nombreDest = depositosSAPENC.get(p.getDestino().getId()).getDescripcion();
							nombreOr = depositosSAPENC.get(p.getOrigen().getId()).getDescripcion();
							
							idDepVisualOrigen = p.getOrigen().getId();
							idDepVisualDestino = p.getDestino().getId();
							
							articulo.setItemCode(p.getArticulo());
							articulo.setQuantity(String.valueOf(p.getVerificada()));
							articulo.setWareHouseCode(depositosSAPENC.get(p.getOrigen().getId()).getId());
							if(!mayorista)
							{
								articulo.setToWareHouseCode(depDest);
							}
							else
							{
								articulo.setToWareHouseCode(depDest);
							}
							articulo.setAlmacenDestino(depDest);
							articulo.setBaseType("");
							articulo.setBaseEntry("");
							articulo.setBaseLine(String.valueOf(lin));
							idCliente = "";
						}
						if(mayorista)
						{
							idPedido = p.getIdPedido();
							depOr = depositosSAPENC.get(p.getOrigen().getId()).getId();
							
							p.setRemitida(p.getVerificada());
							depDest = p.getDestino().getDescripcion();
							
							
							articuloEntrega.setItemCode(p.getArticulo());
							articuloEntrega.setQuantity(String.valueOf(p.getVerificada()));
							articuloEntrega.setWareHouseCode(depositosSAPENC.get(p.getOrigen().getId()).getId());
							articuloEntrega.setToWareHouseCode("");
							//es para que primero haga un movimiento de stock
							articuloEntrega.setToWareHouseCode(depDest);
							articuloEntrega.setAlmacenDestino(depDest);
							
							articuloEntrega.setBaseType("17");
							articuloEntrega.setBaseEntry(String.valueOf(idPedido));
							articuloEntrega.setBaseLine(String.valueOf(p.getIdPosLineaSAP()));
							
							String nom = depositosSAPENC.get(p.getDestino().getId()).getDescripcion();
							String id = nom.split("-")[0];
							idCliente = id;
						}
							
						
						
						arreglo.getUtilDesglose().add(articulo);
						arregloEntrega.getUtilDesglose().add(articuloEntrega);
						lin++;
					}
					
					
					
				}
				RespWSSAP resp = null;
				String message="";
				if(!mayorista)
				{
					if(!token.equals("") && productivoSAP)
					{
						List<DataIDDescripcion> eRemito = new ArrayList<>();
						String jsonL = "";
						
						for (DataPicking p : list) 
						{
							if(p.getVerificada()>0)
							{
								eRemito.add(new DataIDDescripcion(p.getVerificada(), p.getArticulo(), p.getDescripcion()));
								
								jsonL+="{ "+
									   "      \"ItemCode\":\""+ p.getArticulo()+"\", "+
									   "      \"Quantity\":"+p.getVerificada()+", "+
									   "      \"QuantitySpecified\":true, "+
									   "      \"FromWarehouseCode\":\""+depOr+"\", "+
									   "      \"WarehouseCode\":\""+depDest+"\", "+
									   "	  \"U_AlmacenDestino\":\""+depDest+"\", "+
									   "      \"BaseType\":5, "+
									   "      \"BaseTypeSpecified\":false, "+
									   "      \"BaseLine\":0, "+
									   "      \"BaseLineSpecified\":false, "+
									   "      \"BaseEntry\":0, "+
									   "      \"BaseEntrySpecified\":false "+
									   "   },";
							}
						}
						
						jsonL = jsonL.substring(0, jsonL.length()-1);
						
						Call2 call=new Call2();
						try
						{
							Fecha fecha = new Fecha();
							String fechaJSON=fecha.darFecha_mes_dia_Anio_hhmmBarra_SM();
							
							String jsonO = "{ "+
											"   \"DocDate\":\""+fechaJSON+"\", "+
											"   \"DocDateSpecified\":true, "+
											"   \"Comments\":\""+"Generado en SAP por Encuentra "+u.getNick()+"\", "+
											"   \"JournalMemo\":\"Generado en SAP por Encuentra\", "+
											"   \"FromWarehouse\":\""+depOr+"\", "+
											"   \"ToWarehouse\":\""+depDest+"\", "+
											"   \"U_Motivo\":\"1\", "+
											"   \"U_Prioridad\":\"1\", "+
											"   \"U_AlmacenDestino\":\""+depDest+"\", "+
											"   \"U_DocNADCont\":\"0\", "+
											"   \"StockTransferLines\":[ ";
							String jsonP = "]}";
							PropertiesHelper pH=new PropertiesHelper("clienteWSSB1");
							pH.loadProperties();
							String host = pH.getValue("stadiumAPI");
							String token2 = call.loginWMS(host);
							String respuestaJSON = call.envioDatos(jsonO+jsonL+jsonP,token2,host,"InventoryTransfer");
							
							

							System.out.println(respuestaJSON);
							resp = JSONReader.readJsonRespWSSAP(respuestaJSON).get(0);
							
							
							
						
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
						
						_ClienteTAFACE_firma taface = new _ClienteTAFACE_firma();
						
						
						try
						{
							int idTras = Integer.parseInt(resp.getCode());
							if(idTras!=-1)
							{
								int idMovStock = Logica.encuentraDarMovStockSAP("OWTR", idTras);
								Fecha fecha = new Fecha();
								Logica.guardarLogClienteWSSB1("Transferencia grabada correctamente, Nro: "+idMovStock,ssId,"Transferencia de stock",fecha.darFechaDia_Mes_Anio_HoraBarra());
								
								String message2 = taface.firmar(Integer.parseInt(depOr),76, 39, "STADIUM Central", Integer.parseInt(depDest), eRemito, nombreDest, "Montevideo", idMovStock+"", "A");
								
								Logica.guardarLogClienteWSSB1("Resultado de TAFACE: "+message2,ssId,"Generando E-Remito",fecha.darFechaDia_Mes_Anio_HoraBarra());
								
								//@@stadium@@
								//Logica.encuentraUpdateRemirPickin(list,0, 0);
								
								
							}
						
						}
						catch (Exception e) 
						{
							//se cay? en el parseo
							
						}
						
					}
					
					if(!productivoSAP)
					{
						//***********************llamada WS Visual//
						List<DataIDDescripcion> artisCant = new ArrayList<>();
						for (UtilDesglose ar : arreglo.getUtilDesglose()) 
						{
							artisCant.add(new DataIDDescripcion(Integer.parseInt(ar.getQuantity()), ar.getItemCode()));
						}
						WSCommunicate ws = new WSCommunicate();
						//ws.movimientoDeStock(idDepVisualOrigen, idDepVisualDestino, motivo, artisCant,"Generado por Encuentra "+u.getNick());
						
						//***********************FIN llamada WS Visual//
					}
					
					
					if(!token.equals(""))
					{
						//cliente.logout(token);
					}
					
					
				}
				else//***********mayorista//
				{
					if(!token.equals("") && productivoSAP)
					{
						//hago el traslado
						
						*/
						/*
						String comentarioI = "Cliente:"+nombreDest+" OC: "+idPedido;
						String comentario = "Usuario: "+u.getNombre() + " Cliente:"+nombreDest+" OC: "+idPedido;
						for (UtilDesglose a : arreglo.getUtilDesglose()) 
						{
							System.out.println(a.getItemCode()+" "+a.getQuantity());
						}
						
						message = darValorClaveXML(cliente.crearTraslado(token, "", "", arreglo, "POS",depDest,comentarioI,prioridad,motivo, depOr),"Detalle",ssId, "Creando Movimiento de Stock ORIGEN:"+depOr+" DESTINO:"+depDest);
						*/
						/*
						
						
						
						
						int idTras = 0;
						String comentarioI = "Cliente:"+nombreDest+" OC: "+idPedido;
						String comentario = "Usuario: "+u.getNombre() + " Cliente:"+nombreDest+" OC: "+idPedido;
						
						
						String comments = "\" "+comentario+" Encuentra "+u.getNick()+"\"";
						String jrnlmmo = "\" "+comentarioI+" Encuentra "+u.getNick()+"\"";
						
						List<DataIDDescripcion> eRemito = new ArrayList<>();
						String jsonL = "";
						
						for (DataPicking p : list) 
						{
							if(p.getVerificada()>0)
							{
								eRemito.add(new DataIDDescripcion(p.getVerificada(), p.getArticulo(), p.getDescripcion()));
								
								jsonL+="{ "+
									   "      \"ItemCode\":\""+ p.getArticulo()+"\", "+
									   "      \"Quantity\":"+p.getVerificada()+", "+
									   "      \"QuantitySpecified\":true, "+
									   "      \"FromWarehouseCode\":\""+depOr+"\", "+
									   "      \"WarehouseCode\":\"070\", "+
									   "	  \"U_AlmacenDestino\":\""+depDest+"\", "+
									   "      \"BaseType\":5, "+
									   "      \"BaseTypeSpecified\":false, "+
									   "      \"BaseLine\":0, "+
									   "      \"BaseLineSpecified\":false, "+
									   "      \"BaseEntry\":0, "+
									   "      \"BaseEntrySpecified\":false "+
									   "   },";
							}
						}
						
						jsonL = jsonL.substring(0, jsonL.length()-1);
						
						Call2 call=new Call2();
						try
						{
							Fecha fecha = new Fecha();
							String fechaJSON=fecha.darFecha_mes_dia_Anio_hhmmBarra_SM();
							
							
							
							
							if(comments.length()>64)
							{
								comments = comments.substring(0,64)+"\"";
							}
							
							if(jrnlmmo.length()>48)
							{
								jrnlmmo = jrnlmmo.substring(0,48)+"\"";
							}
							
						
							
							String jsonO = "{ "+
											"   \"DocDate\":\""+fechaJSON+"\", "+
											"   \"DocDateSpecified\":true, "+
											"   \"Comments\":"+comments+", "+
											"   \"JournalMemo\":"+jrnlmmo+", "+
											"   \"FromWarehouse\":\""+depOr+"\", "+
											"   \"ToWarehouse\":\"070\", "+
											"   \"U_Motivo\":\"1\", "+
											"   \"U_Prioridad\":\"1\", "+
											"   \"U_AlmacenDestino\":\"070\", "+
											"   \"U_DocNADCont\":\"0\", "+
											"   \"StockTransferLines\":[ ";
							String jsonP = "]}";
							PropertiesHelper pH=new PropertiesHelper("clienteWSSB1");
							pH.loadProperties();
							String host = pH.getValue("stadiumAPI");
							String token2 = call.loginWMS(host);
							String respuestaJSON = call.envioDatos(jsonO+jsonL+jsonP,token2,host,"InventoryTransfer");
							
							

							System.out.println(respuestaJSON);
							resp = JSONReader.readJsonRespWSSAP(respuestaJSON).get(0);
							
							
							
						
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
			
						
						try
						{
							idTras = Integer.parseInt(resp.getCode());
							
							
						
							if(idTras!=-1)
							{
								
								idTras = Logica.encuentraDarMovStockSAP("OWTR", idTras);
								
								
								
								//les debo cambiar el origen para que la entrega se haga desde el deposito al que transfer? la mercaderia
								for (UtilDesglose articulo : arregloEntrega.getUtilDesglose()) 
								{
									articulo.setWareHouseCode(depDest);//origen es depDest
									articulo.setToWareHouseCode("");
									articulo.setAlmacenDestino("");
								}
								
								idCliente = Logica.darClientePedidoSAP(idPedido);
								System.out.println("entregando.");
								message= darValorClaveXML(cliente.crearEntrega(token, idCliente, idPedido+"", "",comments, arregloEntrega),"Detalle", ssId, "Creando Enrega a cliente");
								try
								{
									idTras = Integer.parseInt(message);
									for (DataPicking p : list) 
									{
										
									}
									
									int idPick = lista.get(0).get(0).getIdPicking();
									Logica.encuentraUpdateRemirPickinAllVerif(idPedido, idPick);
									
									System.out.println(message);
								}
								catch(Exception e)
								{
									for (UtilDesglose articulo : arreglo.getUtilDesglose()) 
									{
										String origen = articulo.getToWareHouseCode();
										String destino = articulo.getWareHouseCode();
										articulo.setWareHouseCode(origen);
										articulo.setToWareHouseCode(destino);
										articulo.setAlmacenDestino(destino);
									}
									
									message = darValorClaveXML(cliente.crearTraslado(token, "", String.valueOf(idPedido), arreglo, "POS",depOr,comentarioI,prioridad,motivo, depDest),"Detalle",ssId, "Revirtiendo Movimiento de Stock ORIGEN:"+depDest+" DESTINO:"+depOr);
								}
							}
							
							
							
							
						}
						catch (Exception e)
						{
							message = null;
						}
					}
				}
				
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//***********************SOLO PORQUE HICIMOS llamada WS Visual//
				//@@stadium@@
				//Logica.encuentraUpdateRemirPickin(list,0, 0);
			}
		}
		
		//cliente.logout(token);
		
		return "";
		
	}
	
	*/
	
	
	
	/*
	
	
	public String guardarMovStockEntrega(List<List<ArticuloPedido>> lista, Usuario u, int prioridad, int motivo, String ssId)
	{

		Logica Logica = new Logica();
		
		WMSSAPB1 servicio = new WMSSAPB1();
		WMSSAPB1Soap cliente = servicio.getWMSSAPB1Soap();
		String log = cliente.login(dbServer, dbName, dbType, compUser, compPwd, language, licenseServer);
		String  token =  darValorClaveXML(log,"Detalle",ssId,"Iniciando Sesion en SAP");
		Hashtable<Integer, DataDescDescripcion> depositosSAPENC = Logica.encuentraDarIdDepositos();
		Hashtable<Integer, List<ArticuloPedido>> pedidosPorOrden = new Hashtable<Integer, List<ArticuloPedido>>();
		
		
		
		

		for (List<ArticuloPedido> list : lista) 
		{
			int idDepVisualOrigen = 0;
			int idDepVisualDestino = 0;
			String coment = "";
			
			try
			{
				//hago un movimiento por cada destino
				ArrayOfUtilDesglose arreglo = new ArrayOfUtilDesglose();
				String idCliente = "";
				String depDest ="";
				String depOr ="";
				
				
				int lin= 0;
				int idSol =0;
				int idPedido = 0;
				for (ArticuloPedido a : list) 
				{
					UtilDesglose articulo = new UtilDesglose();
					
					idDepVisualOrigen = Integer.parseInt(a.getOrigen());
					idDepVisualDestino = Integer.parseInt(a.getDestino());
					
					depDest = a.getDestino();
					depOr = a.getOrigen();
					motivo = a.getRazon();
					coment = a.getComent();
						
					articulo.setItemCode(a.getArticulo());
					articulo.setQuantity(String.valueOf(a.getCantidadEntregada()));
					articulo.setWareHouseCode(a.getOrigen());
					articulo.setToWareHouseCode("500");
					articulo.setBaseType("1250000001");
					articulo.setBaseEntry(String.valueOf(a.getIdDist()));
					articulo.setBaseLine(String.valueOf(a.getLineaOrden()));
					articulo.setAlmacenDestino(a.getDestino());
					
					arreglo.getUtilDesglose().add(articulo);
					
				}
				
				String message="";
				
				//message= darValorClaveXML(cliente.crearTraslado(token,"",null,arreglo,"Primario",depDest, coment+" Generado por Encuentra "+u.getNick(),1,1, depOr),"Detalle", ssId, "Grabando Movimiento de Stock de entrega");
				
				/////////////////////////////ATENCION - ESTO DEBE IR DENTRO DEL TRY QUE SIGUE A ESTE BLOQUE//////////////////////////
				Logica.encuentraActualizarCantEntrega(arreglo);
				
				//////////////////////////////llamada WS Visual////////////////////////////////
				List<DataIDDescripcion> artisCant = new ArrayList<>();
				for (UtilDesglose ar : arreglo.getUtilDesglose()) 
				{
					artisCant.add(new DataIDDescripcion(Integer.parseInt(ar.getQuantity()), ar.getItemCode()));
				}
				WSCommunicate ws = new WSCommunicate();
				//ws.movimientoDeStock(idDepVisualOrigen, idDepVisualDestino, motivo, artisCant,coment+" Generado por Encuentra "+u.getNick());
				//cliente.logout(token);
				///////////////////////////FIN llamada WS Visual////////////////////////////////
				
				
				try
				{
					int idTras = Integer.parseInt(message);
					
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				//Logica.encuentraUpdateRemirPickin(list,idSol, idTras);
				
				System.out.println(message);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//cliente.logout(token);
		
		return "";
		
	}
	
	
	public String actualizarTipoCambioSAP()
	{
		

		WMSSAPB1 servicio = new WMSSAPB1();
		WMSSAPB1Soap cliente = servicio.getWMSSAPB1Soap();
		
		String log = cliente.login(dbServer, dbName, dbType, compUser, compPwd, language, licenseServer);
		String  token =  darValorClaveXML(log,"Detalle","NO","Iniciando Sesion en SAP");
		
		
		try
		{
		PropertiesHelper pH=new PropertiesHelper("monedasSAP");
		pH.loadProperties();
		String[] mons = pH.getValue("Monedas").split(",");
		
			for (int i = 0; i < mons.length; i++) 
			{
				boolean hayCotizacion = false;
				//Fecha fechaS = new Fecha();
				Fecha fechaS1 = new Fecha(1,0,0);
				//String fechaSAP = fechaS.darFechaAnio_Mes_Dia();
				String fechaSAPM = fechaS1.darFechaAnio_Mes_Dia();
				int diasAtras = 0;
				
				
				while (!hayCotizacion) 
				{
					//formato yyyy-mm-dd
					Fecha fecha = new Fecha(diasAtras,0,0);
					String fechaBCU = fecha.darFechaAnio_Mes_Dia();
					
					System.out.println("buscando cotizacion para "+mons[i]+" en la fecha "+fechaBCU);
					
					try
					{
						
						String msg = "";//cliente.actualizarTipoDeCambio(token, mons[i], fechaBCU,fechaSAP);
						msg = cliente.actualizarTipoDeCambio(token, mons[i], fechaBCU,fechaSAPM);
						
						if(msg.contains("ERROR"))
						{
							diasAtras--;
							System.out.println(msg);
						}
						else
						{
							hayCotizacion = true;
						}
							
						
					}
					catch (Exception e)
					{
						return e.printStackTrace();
					}
					if(diasAtras<=-10)
					{
						System.out.println("hay un error con la moneda "+mons[i]+" no se buscar? mas cotizaciones.");
						hayCotizacion = true;
					}
					
				}
			}
		}
		catch (Exception e)
		{
			return e.printStackTrace();
		}
		
		
		
		
		
		
		return "ok";
	}
	
	
	
	public String actualizarTipoCambioSAPTEST()
	{
		

		WMSSAPB1 servicio = new WMSSAPB1();
		WMSSAPB1Soap cliente = servicio.getWMSSAPB1Soap();
		
		String log = cliente.login("LABSAP", dbName, dbType, "manager", "1234", language, "LABSAP");
		String  token =  darValorClaveXML(log,"Detalle","NO","Iniciando Sesion en SAP");
		
		
		try
		{
		PropertiesHelper pH=new PropertiesHelper("monedasSAP");
		pH.loadProperties();
		String[] mons = pH.getValue("Monedas").split(",");
		
			for (int i = 0; i < mons.length; i++) 
			{
				boolean hayCotizacion = false;
				//Fecha fechaS = new Fecha();
				Fecha fechaS1 = new Fecha(1,0,0);
				//String fechaSAP = fechaS.darFechaAnio_Mes_Dia();
				String fechaSAPM = fechaS1.darFechaAnio_Mes_Dia();
				int diasAtras = 0;
				
				
				while (!hayCotizacion) 
				{
					//formato yyyy-mm-dd
					Fecha fecha = new Fecha(diasAtras,0,0);
					String fechaBCU = fecha.darFechaAnio_Mes_Dia();
					
					System.out.println("buscando cotizacion para "+mons[i]+" en la fecha "+fechaBCU);
					
					try
					{
						
						String msg = "";//cliente.actualizarTipoDeCambio(token, mons[i], fechaBCU,fechaSAP);
						msg = cliente.actualizarTipoDeCambio(token, mons[i], fechaBCU,fechaSAPM);
						
						if(msg.contains("ERROR"))
						{
							diasAtras--;
							System.out.println(msg);
						}
						else
						{
							hayCotizacion = true;
						}
							
						
					}
					catch (Exception e)
					{
						return e.printStackTrace();
					}
					if(diasAtras<=-10)
					{
						System.out.println("hay un error con la moneda "+mons[i]+" no se buscar? mas cotizaciones.");
						hayCotizacion = true;
					}
					
				}
			}
		}
		catch (Exception e)
		{
			return e.printStackTrace();
		}
		
		
		
		
		
		
		return "ok";
	}
	
	public Hashtable<Integer, Integer> guardarDistribucionSolMovStock(List <List<MovimientoMatrizDis>> origenes, boolean separaDestino,String comentario, String prioridad,String motivo, Hashtable<Integer, List<String>> htmls, String ssId)
	{
		Logica Logica = new Logica();
		Hashtable<Integer, Integer>  solicitudes = new Hashtable<>();
		
		
		WMSSAPB1 servicio = new WMSSAPB1();
		WMSSAPB1Soap cliente = servicio.getWMSSAPB1Soap();
		
		String log = cliente.login(dbServer, dbName, dbType, compUser, compPwd, language, licenseServer);
		String  token =  darValorClaveXML(log,"Detalle",ssId,"Iniciando Sesion en SAP");
		
		
		
	
		
		
		Hashtable<Integer, DataDescDescripcion> depositosSAPENC = Logica.encuentraDarIdDepositos();
		
		

		for (List<MovimientoMatrizDis> list : origenes) 
		{
			Hashtable<Integer, ArrayOfUtilDesglose> movsPorDestino = new Hashtable<>();
			//hago un movimiento por cada destino
			ArrayOfUtilDesglose arreglo = new ArrayOfUtilDesglose();
			String idCliente = "";
			String depDest ="";
			int depOrint = 0;
			int lin= 1;
			for (MovimientoMatrizDis mov : list) 
			{
				depDest = depositosSAPENC.get(mov.getDestino()).getId();
				depOrint = mov.getOrigen();
				UtilDesglose articulo = new UtilDesglose();
				articulo.setItemCode(mov.getArticulo()+mov.getTalle());
				articulo.setQuantity(String.valueOf(mov.getCantidad()));
				articulo.setWareHouseCode(depositosSAPENC.get(mov.getOrigen()).getId());
				articulo.setToWareHouseCode("500");
				articulo.setAlmacenDestino(depDest);
				articulo.setBaseType("");
				articulo.setBaseEntry("");
				articulo.setBaseLine("");
				idCliente = "";
			
				if(separaDestino)
				{
					if(movsPorDestino.get(depDest)==null)
					{
						ArrayOfUtilDesglose arre = new ArrayOfUtilDesglose();
						arre.getUtilDesglose().add(articulo);
						movsPorDestino.put(mov.getDestino(), arre);
					}
					else
					{
						ArrayOfUtilDesglose arre = movsPorDestino.get(depDest);
						arre.getUtilDesglose().add(articulo);
						movsPorDestino.put(mov.getDestino(), arre);
					}
				}
				else
				{
					arreglo.getUtilDesglose().add(articulo);
				}
				
				
				lin++;
			}
			if(separaDestino)
			{
				List<ArrayOfUtilDesglose> distribuciones = new ArrayList<>(movsPorDestino.values());
				for (ArrayOfUtilDesglose array : distribuciones) 
				{
					String message = darValorClaveXML(cliente.crearSolicitudDeTraslado(token, idCliente, "", array,depDest,comentario,Integer.parseInt(prioridad),Integer.parseInt(motivo)),"Detalle", ssId, "Grabando distribucion");
					
					System.out.println(message);
					try
					{
						int numSol = Integer.parseInt(message);
						solicitudes.put(depOrint, numSol);
						Logica.encuentraAltaHtmlDist(htmls.get(depOrint),numSol);
						
						
					}
					catch(Exception e)
					{
						solicitudes.put(depOrint, 0);
					}
				}
			}
			else
			{
				String message = darValorClaveXML(cliente.crearSolicitudDeTraslado(token, idCliente, "", arreglo,depDest,comentario,Integer.parseInt(prioridad),Integer.parseInt(motivo)),"Detalle",ssId, "Grabando distribucion");
				
				System.out.println(message);
				try
				{
					int numSol = Integer.parseInt(message);
					solicitudes.put(depOrint, numSol);
					Logica.encuentraAltaHtmlDist(htmls.get(depOrint),numSol);
				}
				catch(Exception e)
				{
					solicitudes.put(depOrint, 0);
				}
			}
		}
		
		
		//cliente.logout(token);
		return solicitudes;
		
	}

	public String verificarStock(List<DataIDDescripcion> movVerificarStockSAP, int depOrigen, String ssId) 
	{
		Logica Logica = new Logica();
		
		String artIn = "";
		Hashtable<String,Integer> tablaArticulos = new Hashtable<>();
		Hashtable<String,Integer> tablaArticulosSAP = new Hashtable<>();
		
		for (DataIDDescripcion a : movVerificarStockSAP) 
		{
			tablaArticulos.put(a.getDescripcion(),a.getId());
			artIn+="'"+a.getDescripcion()+"',";
		}
		
		
		artIn = artIn.substring(0,artIn.length()-1);
		String depo = String.format("%03d", depOrigen);
		
		String q = "select sum(ST.\"OnHand\"),ST.\"ItemCode\" from STADIUM_DEV2.OITW ST where ST.\"ItemCode\" in ("+artIn+") and \"WhsCode\" = '"+depo+"' group by  ST.\"ItemCode\"";
		
		List<DataIDDescripcion> stockSAP = HanaSQL.darListaIdDescripcionSAP(q);
		for (DataIDDescripcion a : stockSAP) 
		{
			tablaArticulosSAP.put(a.getDescripcion(),a.getId());
			
		}
		
		List<DataIDDescripcion> listaAjuste = new ArrayList<>();
		
		String in = "";
		for (DataIDDescripcion a : movVerificarStockSAP) 
		{
			int cantidadMov = tablaArticulos.get(a.getDescripcion());
			int stock_SAP = 0;
			
			try
			{
				stock_SAP=tablaArticulosSAP.get(a.getDescripcion());
			}
			catch (Exception e) 
			{
				
			}
			
			if(cantidadMov>stock_SAP)
			{
				int cantAjustar = cantidadMov-stock_SAP;
				listaAjuste.add(new DataIDDescripcion(cantAjustar, a.getDescripcion(),depo));
				in+="'"+a.getDescripcion()+"',";
				
			}
		}
		
		
		if(listaAjuste.isEmpty())
		{
			
			Fecha fecha = new Fecha();
			Logica.guardarLogClienteWSSB1("stock correcto, no se hicieron Ajustes",ssId,"verificacion de stock",fecha.darFechaDia_Mes_Anio_HoraBarra());
			return "stock correcto, no se hicieron Ajustes";
		}
		else
		{
			String menError = "";
			int tope = 200;
			int count=0;
			
			StringBuilder jsonSB = new StringBuilder();
			Hashtable<String, Double> htcostosSAP = new Hashtable<>();
			System.out.println("leyendo costos");
			Fecha fecha = new Fecha();
			Logica.guardarLogClienteWSSB1("se procede con Ajustes",ssId,"verificacion de Costos",fecha.darFechaDia_Mes_Anio_HoraBarra());
			in = in.substring(0, in.length()-1);
			List<DataDescDescripcion> costosSAP = HanaSQL.darIdDescripcionSAP("select * from STADIUM_DEV2.intuift_ult_costo where \"ItemCode\" in ("+in+")");
			
			for (DataDescDescripcion a : costosSAP) 
			{
				htcostosSAP.put(a.getId(), Double.parseDouble(a.getDescripcion()));
			}
			
			for (DataIDDescripcion gr : listaAjuste) 
			{
				count++;
				Double costo=1.0;
				
				
				if (htcostosSAP.get(gr.getDescripcion())!=null)
				{
					costo=htcostosSAP.get(gr.getDescripcion());
				}
				
				
				int cuenta=500803;
				String jsonS="{ "+
							    "    \"ItemCode\":\""+gr.getDescripcion()+"\","+
							    "    \"Quantity\":"+gr.getId()+","+
							    "     \"QuantitySpecified\":true,"+
							    "     \"ShipDateSpecified\":false,"+
							    "     \"WarehouseCode\":\""+gr.getDescripcionB()+"\","+
							    "     \"AccountCode\":\""+cuenta+"\"," +
							    "	  \"Price\": "+costo+","+
							    "     \"BaseEntrySpecified\":false,"+
							    "     \"BaseLineSpecified\":false"+
							    "  },";
				jsonSB.append(jsonS);
				
				
			}
			
			
			String fechaJSON=fecha.darFecha_mes_dia_Anio_hhmmBarra_SM();
			String json = jsonSB.toString().substring(0, jsonSB.toString().length()-1);
			System.out.println(fechaJSON);
			
			
			String jsonO=" "+
					"	{ "+
					"	   \"DocDate\":\""+fechaJSON+"\", "+
					"	   \"DocDateSpecified\":true, "+
					"	   \"DocumentLines\":[";
		
			String jsonP="]}";
			
			
		
			System.out.println("mandando ajustes");
				
				
				
				Call2 call=new Call2();
				try
				{
					PropertiesHelper pH=new PropertiesHelper("clienteWSSB1");
					pH.loadProperties();
					String host = pH.getValue("stadiumAPI");
					String token = call.loginWMS(host);
					//System.out.println(body);
					//System.out.println(token);
					String respuestaJSON = call.envioDatos(jsonO+json+jsonP,token,host,"GoodsReceipt");
					
					

					RespWSSAP resp = JSONReader.readJsonRespWSSAP(respuestaJSON).get(0);
					
					int idTras = Integer.parseInt(resp.getCode());
					if(idTras!=-1)
					{
						int idMovStock = Logica.encuentraDarMovStockSAP("OIGN", idTras);
						Logica.guardarLogClienteWSSB1("Ajuste grabado correctamente, Nro: "+idMovStock,ssId,"Ajuste de Inventario",fecha.darFechaDia_Mes_Anio_HoraBarra());
					}
					else
					{
						Logica.guardarLogClienteWSSB1("ERROR: "+resp.getDescription() +" Intente hacer ajustes en SAP",ssId,"Ajuste de Inventario",fecha.darFechaDia_Mes_Anio_HoraBarra());
					}
					
					
				return idTras+"";
					
				
				}
				catch (Exception e)
				{
					e.printStackTrace();
					return "-1";
				}
				
			
			
			
			
			
			
			
		}
		
		
		
		
	}
*/
	
	
}












































