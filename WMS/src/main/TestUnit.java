package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import dataTypes.DataDepositoSAP;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import dataTypes.DataReglaReposicion;

import logica.Logica;

import beans.ArticuloReposicion;
import beans.ArticuloReposicionMaster;
import beans.DepositoDimension;
import beans.Fecha;
import beans.ProcesadorRepo;
import beans.RepoDimension;
import beans.StockReposicion;
import beans.Usuario;

public class TestUnit 
{
	
	Logica Logica = new Logica();
	List<DepositoDimension> depositosElegibles = new ArrayList<>();
	List<DepositoDimension> depositosElegiblesII = new ArrayList<>();
	
	public TestUnit() 
	{
		Logica logica = new Logica();
		Usuario u = logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");
		
		int idEmpresa = u.getIdEmpresa();
		
		List<DataIDDescripcion> promedios = Logica.darPromediosPondRepo(idEmpresa);
		for (DataIDDescripcion d : promedios) 
		{
			Double val = Double.parseDouble(d.getDescripcion());
			switch (d.getId()) 
			{
			case 1:
				this.articulo1 = val;
				break;
			case 2:
				this.baseColor2 = val;
				break;
			case 3:
				this.base3 = val;
				break;
			case 4:
				this.marca4 = val;
				break;
			case 5:
				this.seccion5 = val;
				break;
			case 6:
				this.genero6 = val;
				break;
			case 7:
				this.categoria7 = val;
				break;
			case 8:
				this.clase8 = val;
				break;
			case 9:
				this.importe9 = val;
				break;
			case 10:
				this.deposito10 = val;
				break;
			default:
				break;
			}
			
		}
	}
	Hashtable<Integer, DepositoDimension> depositos = new Hashtable<>();
	
	public void SincronizarVentasM(int idEmpresa)
	{
		int idSinc = Logica.darNextSincRepo(idEmpresa);
		Hashtable<String, DataDepositoSAP>  depositosCliente = Logica.encuentraDarDepositosSAP(idEmpresa);
		List<ArticuloReposicion> listaArticulosaClientesMayoristas = null;//Logica.darVentaLineasMayorista(depositosCliente,idEmpresa);
		
		if(listaArticulosaClientesMayoristas.isEmpty())
		{
			Logica.actualizarSincRepo(0,idSinc,idEmpresa);
		}
		else
		{
			Logica.actualizarSincRepo(1,idSinc,idEmpresa);
		}
		if(!listaArticulosaClientesMayoristas.isEmpty())
		{
			for (ArticuloReposicion articulo : listaArticulosaClientesMayoristas) 
			{
				String justificacion = "Solicitud de pedido por Venta por mayor";
				articulo.setOrigen(99);
				Logica.guardarLineaReposicion(articulo, justificacion, idSinc,1,idEmpresa);
			}
		}
	}
	
	
	
	public void Sincronizar(boolean central, int idEmpresa) 
	{
		ProcesadorRepo procesador = new ProcesadorRepo();
		Hashtable<String, Integer> stocksReservados = Logica.DarStocksReservadosRepo(idEmpresa);
		Hashtable<String, Integer> stocksEnviados = Logica.DarStocksEnviadosRepo(idEmpresa);
		
		Fecha fecha = Logica.darUltimaSincronizacionRepo(central,idEmpresa);
		
		
		List<DataIDIDDescripcion> filtrosReglas = Logica.darFiltrosReglasReposicion(idEmpresa);
		List<DataReglaReposicion> reglas = Logica.DarReglasReposicion(idEmpresa);
		
		//articulo articuloBaseColor articuloBase marca seccion clase venta stockCentral stockLocal stockBaseColor precio sucursal
		String fechaCons = fecha.darFechaToMSSQL();
		int humbral = 1;
		Hashtable<String, ArticuloReposicionMaster> articulosVendidos = new Hashtable<>();
		Hashtable<String, ArticuloReposicionMaster> articulosVendidosNOCentral = new Hashtable<>();
		int idSinc = Logica.darNextSincRepo(idEmpresa);
		int porcentaje = 0;
		
		
		//List<ArticuloReposicion> listaArticulosVendidos = Logica.darVentaLineasReposicion(fechaCons, idSinc,porcentaje, central);		
		/***************************************************************************/
		/********************************SOLO PARA DEBUG*******************************************/

		List<ArticuloReposicion> listaArticulosVendidos = new ArrayList<>();//va a ser siempre vacia
		/**************************************************************************/
		// hay que traer los depositos de clientes en hash
		Hashtable<String, DataDepositoSAP>  depositosCliente = Logica.encuentraDarDepositosSAP(idEmpresa);
		List<ArticuloReposicion> listaArticulosaClientesMayoristas = null;//Logica.darVentaLineasMayorista(depositosCliente);
		
		if(listaArticulosaClientesMayoristas.isEmpty() && listaArticulosVendidos.isEmpty())
		{
			Logica.actualizarSincRepo(0,idSinc,idEmpresa);
		}
		else
		{
			
			/***************************************************************************/
			/********************************SOLO PARA DEBUG*******************************************/
			Logica.actualizarSincRepo(0,idSinc,idEmpresa);
			/**************************************************************************/
			//Logica.actualizarSincRepo(1,idSinc);
		}
		
		if(!listaArticulosaClientesMayoristas.isEmpty())
		{
			for (ArticuloReposicion articulo : listaArticulosaClientesMayoristas) 
			{
				String justificacion = "Solicitud de pedido por Venta por mayor";
				articulo.setOrigen(99);
				Logica.guardarLineaReposicion(articulo, justificacion, idSinc,1,idEmpresa);
			}
		}
		
		
		if(listaArticulosVendidos.isEmpty())
		{
			
			Logica.actualizarLogSincRepo(idSinc, "INFO: No se han obtenido ventas.",porcentaje,idEmpresa);
			Logica.actualizarLogSincRepo(idSinc, "INFO: El proceso Finalizara...",porcentaje,idEmpresa);
			
		}
		else
		{
			
			Logica.actualizarLogSincRepo(idSinc, "INFO: Se han obtenido "+listaArticulosVendidos.size()+" Lineas de ventas.", porcentaje,idEmpresa);
			
			
			
			
			
			
			
			/*
			 * deberia asegurarme que la sumatoria del mismo articulo vendido no sea superior al humbral
			 * */
			
			int pasada =0;
			for (ArticuloReposicion articulo : listaArticulosVendidos) 
			{
				try
				{
					Hashtable<Integer,DataReglaReposicion> reglasArticulo = new Hashtable<>();
					
					Hashtable<Integer, String> valoresParaFiltro = new Hashtable<>();
					valoresParaFiltro.put(0, articulo.getArticulo());
					valoresParaFiltro.put(1, articulo.getArticuloBaseColor());
					valoresParaFiltro.put(2, articulo.getArticuloBase());
					valoresParaFiltro.put(3, articulo.getTemporada()+"");//temporada
					valoresParaFiltro.put(4, articulo.getClase()+"");
					valoresParaFiltro.put(5, articulo.getMarca()+"");
					valoresParaFiltro.put(6, articulo.getCategoria()+""); 
					valoresParaFiltro.put(7, articulo.getGenero()+"");
					valoresParaFiltro.put(8, articulo.getSeccion()+"");
					valoresParaFiltro.put(10, "---");
					
					
					/*verifico que filtros tiene*/
					
					
					
					
					for (DataReglaReposicion regla : reglas) 
					{
						Hashtable<Integer, DataIDDescripcion> filtros = regla.getFiltros();
						boolean agregala = false;
						boolean todasIn = true;
						for (int i = 0; i < 11; i++)// porque tengo 10 filtros 
						{
							if(filtros.get(i)!=null)
							{
								
								if(filtros.get(i).getDescripcion().equals(valoresParaFiltro.get(i)))
								{
									if(regla.getDestinos().get(articulo.getSucursal())!=null && regla.getMinCentral()<=articulo.getStockCentral())
									{
										/*si la regla tiene a ese local*/
										//reglasArticulo.put(regla.getIdRegla(), regla);
										agregala = true;
									}
									
								}
								else
								{
									todasIn = false;
								}
									
							}
							
							
						}
						if(todasIn && agregala)
						{
							/*si la regla tiene a ese local*/
							reglasArticulo.put(regla.getIdRegla(), regla);
						}
						
					}
					
					List<DataReglaReposicion> reglasArticuloList = new ArrayList<>(reglasArticulo.values());
					Collections.sort(reglasArticuloList);
					try
					{
						DataReglaReposicion regla = reglasArticuloList.get(0);
						System.out.println(articulo.getArticulo() +" Regla primera: "+ regla.getIdRegla());
						articulo.setMaxLocal(regla.getMaxLocal());
						articulo.setMinCentral(regla.getMinCentral());
					}
					catch (Exception e)
					{
						articulo.setMaxLocal(0);
						articulo.setMinCentral(0);
						System.out.println("no hay reglas para el articulo:"+articulo.getArticulo());
					}
					
					pasada++;
					porcentaje = (pasada * 100)/listaArticulosVendidos.size();
					//total------100
					//pasada----x
					depositos = new Hashtable<>();
						
					procesador.agregarLinea(articulo, idSinc, porcentaje, stocksReservados,true, 0,true,stocksEnviados);
						
						List<RepoDimension> articulos = procesador.getArticulos().get(articulo.getArticulo());
						List<RepoDimension> baseColor= procesador.getBaseColor().get(articulo.getArticuloBaseColor());
						List<RepoDimension> base= procesador.getBase().get(articulo.getArticuloBase());
						List<RepoDimension> marca= procesador.getMarca().get(articulo.getMarca());
						List<RepoDimension> seccion= procesador.getSeccion().get(articulo.getMarca()+"-"+articulo.getSeccion());
						List<RepoDimension> clase= procesador.getClase().get(articulo.getMarca()+"-"+articulo.getClase());
						List<RepoDimension> importe= procesador.getImporte().get(articulo.getPrecio());
						List<RepoDimension> sucursal= procesador.getSucursal().get(articulo.getSucursal());
						List<RepoDimension> genero= procesador.getGenero().get(articulo.getGenero());
						List<RepoDimension> categoria= procesador.getCategoria().get(articulo.getCategoria());
						StockReposicion stock = procesador.getStocks().get(articulo.getArticuloBaseColor()).get(articulo.getArticulo());
						
						//Collections.sort(articulos);
						Collections.sort(baseColor);
						Collections.sort(base);
						Collections.sort(marca);
						Collections.sort(seccion);
						Collections.sort(clase);
						Collections.sort(importe);
						Collections.sort(sucursal);
						Collections.sort(genero);
						Collections.sort(categoria);
						
						//recorrer(articulos,1);
						recorrer(baseColor,2);
						recorrer(base,3);
						recorrer(marca,4);
						recorrer(seccion,5);
						recorrer(categoria,6);
						recorrer(genero,7);
						recorrer(clase,8);
						recorrer(importe,9);
						recorrer(sucursal,10);
						
						List<DepositoDimension> depositosList = new ArrayList<>(depositos.values());
						for (DepositoDimension d : depositosList) 
						{
							// seteo stock para cada deposito
							
							if(d.getId()==11)
							{
								System.out.println("once");
							}
							DataIDIDDescripcion stockDepo = null;
							try
							{
								stockDepo =  stock.getDepositos().get(d.getId());
							}
							catch (Exception e)
							{
								System.out.println("error con el depo"+d.getId());
							}
							
							
							if(stockDepo==null)
							{
								stockDepo = new DataIDIDDescripcion(d.getId(), 0, null);
							}
							
							if(d.getArticulosStock()==null)
							{
								Hashtable<String, DataIDIDDescripcion> stocks = new Hashtable<>();
								stocks.put(articulo.getArticulo(), stockDepo);
								d.setArticulosStock(stocks);
								
							}
							else
							{
								d.getArticulosStock().put(articulo.getArticulo(), stockDepo);
							}
							
							
							
						}
						
						Collections.sort(depositosList);
						articulo.setDepositos(depositosList);
						/*
						System.out.println("\n\n Promedios ponderados articulo"+articulo.getArticulo());
						for (DepositoDimension d : depositosList) 
						{
							String proms = "articulo1 =("+d.getUni1()+") " +d.getProm1()+"\n" +
									" baseColor2 ("+d.getUni2()+") "+d.getProm2()+"\n" +
									" base3 ("+d.getUni3()+") = "+d.getProm3()+"\n" +
									" marca4= ("+d.getUni4()+")"+d.getProm4()+"\n" +
									" seccion5= ("+d.getUni5()+")"+d.getProm5()+"\n" +
									" clase6= ("+d.getUni6()+") "+d.getProm6()+"\n " +
									" Importe7= ("+d.getUni7()+") "+d.getProm7()+"\n" +
									" deposito8= ("+d.getUni8()+") "+d.getProm8() +"\n";
							System.out.println("Deposito: "+d.getId()+" "+proms+"  \n "+d.getPromGral());
						}
				
					*/
					
					if(articulosVendidos.get(articulo.getArticulo())==null)
					{
						List<ArticuloReposicion> lista = new ArrayList<>();
						ArticuloReposicionMaster am = new ArticuloReposicionMaster();
						lista.add(articulo);
						am.setArticulo(articulo.getArticulo());
						am.setCantidad(articulo.getVenta());
						am.setCantidadCentral(articulo.getStockCentral());
						am.setArticulosVendidos(lista);
						am.setMinCentral(articulo.getMinCentral());
						articulosVendidos.put(articulo.getArticulo(), am);
						
					}
					else
					{
						ArticuloReposicionMaster am = articulosVendidos.get(articulo.getArticulo());
						am.getArticulosVendidos().add(articulo);
						am.setCantidad(am.getCantidad()+articulo.getVenta());
						articulosVendidos.put(articulo.getArticulo(), am);
					}
				
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
			
			List<ArticuloReposicionMaster> artsVendMaster = new ArrayList<>(articulosVendidos.values());
			//ahora recorro todos los vendidos de esta lista que ya tiene las cantidades que se vendieron de cada articulo;
			int maxLocal = 2;
			
			humbral = 10;
			for (ArticuloReposicionMaster art : artsVendMaster) 
			{
				humbral = art.getMinCentral();
				int cantidadCentral = art.getCantidadCentral();
				if(art.getCantidadCentral()<humbral)
				{
					if(art.getCantidadCentral()>0)
					{
						// decidir a quienes repone desde central
					
						int pos = 1;
						for (DepositoDimension d : art.getArticulosVendidos().get(0).getDepositos())// recorro los depositos el orden es para ese articulo 
						{
							for (ArticuloReposicion articulo : art.getArticulosVendidos()) 
							{
								int stkenviado = 0;
								try
								{
									stkenviado = stocksEnviados.get(articulo.getArticulo()+"-"+articulo.getSucursal());
								}
								catch (Exception e)
								{
								}
								
								
								articulo.setStockLocal(articulo.getStockLocal()+stkenviado);
								
								if(d.getId()==articulo.getSucursal())
								{
									System.out.println("el articulo "+articulo.getArticulo()+" que vendio la suc "+d.getId()+" cae en la posicion "+pos);
									if(pos<=humbral && cantidadCentral>0)
									{
										int cantidadEnviar = articulo.getMaxLocal()-articulo.getStockLocal();
										if(cantidadEnviar>0 && cantidadEnviar<=cantidadCentral)
										{
											
											String justificacion = "Stock en Central:" +articulo.getStockCentral()+"<br/> Stock en el Local "+articulo.getStockLocal()+"<br/> Max en el local "+articulo.getMaxLocal()+" <br/> Vendi? "+articulo.getVenta()+" y tiene la posici?n "+pos+" en el ranking";
											articulo.setVenta(cantidadEnviar);
											cantidadCentral+=cantidadCentral-articulo.getVenta();
											articulo.setOrigen(99);
											Logica.guardarLineaReposicion(articulo, justificacion, idSinc,0,idEmpresa);
											
										}
										
										
										
									}
									/*
									else
									{
										//lo agrego a la lista de articulos que no hay en central
										
										if(articulosVendidosNOCentral.get(articulo.getArticulo())==null)
										{
											List<ArticuloReposicion> lista = new ArrayList<>();
											ArticuloReposicionMaster am = new ArticuloReposicionMaster();
											lista.add(articulo);
											am.setArticulo(articulo.getArticulo());
											am.setCantidad(articulo.getVenta());
											am.setCantidadCentral(articulo.getStockCentral());
											am.setArticulosVendidos(lista);
											
											articulosVendidosNOCentral.put(articulo.getArticulo(), am);
											
										}
										else
										{
											ArticuloReposicionMaster am = articulosVendidosNOCentral.get(articulo.getArticulo());
											am.getArticulosVendidos().add(articulo);
											am.setCantidad(am.getCantidad()+articulo.getVenta());
											articulosVendidosNOCentral.put(articulo.getArticulo(), am);
										}
										
									}
									*/
								}
								
							}
							
							
							pos++;
						}
					}
					/*************************************************************************************
					else
					{
						for (ArticuloReposicion articulo : art.getArticulosVendidos()) 
						{
							//lo agrego a la lista de articulos que no hay en central
							if(articulosVendidosNOCentral.get(articulo.getArticulo())==null)
							{
								List<ArticuloReposicion> lista = new ArrayList<>();
								ArticuloReposicionMaster am = new ArticuloReposicionMaster();
								lista.add(articulo);
								am.setArticulo(articulo.getArticulo());
								am.setCantidad(articulo.getVenta());
								am.setCantidadCentral(articulo.getStockCentral());
								am.setArticulosVendidos(lista);
								
								articulosVendidosNOCentral.put(articulo.getArticulo(), am);
								
							}
							else
							{
								ArticuloReposicionMaster am = articulosVendidosNOCentral.get(articulo.getArticulo());
								am.getArticulosVendidos().add(articulo);
								am.setCantidad(am.getCantidad()+articulo.getVenta());
								articulosVendidosNOCentral.put(articulo.getArticulo(), am);
							}
						}
					}
					*/
				}
				else if (art.getCantidad()< art.getCantidadCentral())
				{
					
					for (ArticuloReposicion articulo : art.getArticulosVendidos()) 
					{
						int stkenviado = 0;
						try
						{
							stkenviado = stocksEnviados.get(articulo.getArticulo()+"-"+articulo.getSucursal());
						}
						catch (Exception e)
						{
						}
						
						articulo.setStockLocal(articulo.getStockLocal()+stkenviado);
						
						int cantidadEnviar = articulo.getMaxLocal()-articulo.getStockLocal();
						if(cantidadEnviar>0 && cantidadEnviar<=cantidadCentral)
						{
							
							String justificacion = "Stock en Central:" +articulo.getStockCentral()+"<br/> Stock en el Local "+articulo.getStockLocal()+"<br/> Max en el local "+articulo.getMaxLocal()+" <br/> Vendi? "+articulo.getVenta()+"";
							Logica.actualizarLogSincRepo(idSinc, "INFO: Reponer desde central "+cantidadEnviar+" unidad/es del articulo "+articulo.getArticulo()+" al deposito "+articulo.getSucursal(), porcentaje,idEmpresa);
							cantidadCentral+=cantidadCentral-cantidadEnviar;
							articulo.setOrigen(99);
							articulo.setVenta(cantidadEnviar);
							Logica.guardarLineaReposicion(articulo, justificacion, idSinc,0,idEmpresa);
						}
						
						
						
						
						
						
						
					}
					
				}
				else
				{
					int pos = 1;
					for (DepositoDimension d : art.getArticulosVendidos().get(0).getDepositos())// recorro los depositos el orden es para ese articulo 
					{
						for (ArticuloReposicion articulo : art.getArticulosVendidos()) 
						{
							int stkenviado = 0;
							try
							{
								stkenviado = stocksEnviados.get(articulo.getArticulo()+"-"+articulo.getSucursal());
							}
							catch (Exception e)
							{
							}
							
							articulo.setStockLocal(articulo.getStockLocal()+stkenviado);
							
							if(d.getId()==articulo.getSucursal())
							{
								System.out.println("el articulo "+articulo.getArticulo()+" que vendio la suc "+d.getId()+" cae en la posicion "+pos);
								int cantidadEnviar = articulo.getMaxLocal()-articulo.getStockLocal();
								if(pos<=humbral && cantidadCentral>0)
								{
									String justificacion = "Stock en Central:" +articulo.getStockCentral()+"<br/> Stock en el Local "+articulo.getStockLocal()+"<br/> Vendi? "+articulo.getVenta()+" y tiene la posici?n "+pos+" en el ranking";
									
									articulo.setOrigen(99);
									articulo.setVenta(cantidadEnviar);
									cantidadCentral+=cantidadCentral-articulo.getVenta();
									Logica.guardarLineaReposicion(articulo, justificacion, idSinc,0,idEmpresa);
									
								}
								else
								{
									//lo agrego a la lista de articulos que no hay en central
									
									if(articulosVendidosNOCentral.get(articulo.getArticulo())==null)
									{
										List<ArticuloReposicion> lista = new ArrayList<>();
										ArticuloReposicionMaster am = new ArticuloReposicionMaster();
										lista.add(articulo);
										am.setArticulo(articulo.getArticulo());
										am.setCantidad(articulo.getVenta());
										am.setCantidadCentral(articulo.getStockCentral());
										am.setArticulosVendidos(lista);
										
										articulosVendidosNOCentral.put(articulo.getArticulo(), am);
										
									}
									else
									{
										ArticuloReposicionMaster am = articulosVendidosNOCentral.get(articulo.getArticulo());
										am.getArticulosVendidos().add(articulo);
										am.setCantidad(am.getCantidad()+articulo.getVenta());
										articulosVendidosNOCentral.put(articulo.getArticulo(), am);
									}
									
								}
							}
							
						}
						
						
						pos++;
					}
					
					
				}
			}
			
			
			/*
			
			List<ArticuloReposicionMaster> artsVendMasterNoCentral = new ArrayList<>(articulosVendidosNOCentral.values());
			//ahora recorro todos los vendidos de esta lista que ya tiene las cantidades que se vendieron de cada articulo que no habia en central 
			for (ArticuloReposicionMaster art : artsVendMasterNoCentral) 
			{
				int pos = 1;
				depositosElegibles = new ArrayList<>();
				depositosElegibles = art.getArticulosVendidos().get(0).getDepositos();
				depositosElegiblesII = new ArrayList<>();
				depositosElegiblesII = art.getArticulosVendidos().get(0).getDepositos();
				for (DepositoDimension d : art.getArticulosVendidos().get(0).getDepositos())// recorro los depositos el orden es para ese articulo 
				{
					for (ArticuloReposicion articulo : art.getArticulosVendidos()) 
					{
						if(d.getId()==articulo.getSucursal())
						{
							DepositoDimension peorDeposito = darPeorSacarDeLista(articulo);
							if(peorDeposito!=null)
							{
								int posPeor = darPosicionDepo(peorDeposito);
								
								int mejor = 0;
								System.out.println("Datos del articulo "+articulo.getArticulo());
								String justin="";
								justin+="El deposito que lo vendio fue "+articulo.getSucursal()+" y su stock actual es "+articulo.getStockLocal()+" <br/> ";
								for (int i = 0; i < posPeor; i++) 
								{
									DepositoDimension mejorDeposito = depositosElegiblesII.get(i);
									if(mejorDeposito.getArticulosStock().get(articulo.getArticulo()).getIid()>0)
									{
										//el mejor deposito tiene stock
										justin+=" el "+(i+1)+" deposito que mejor lo vende es "+mejorDeposito.getId()+ " y su stock es "+mejorDeposito.getArticulosStock().get(articulo.getArticulo()).getIid() + "<br/> "; 
									}
									else
									{
										justin+=" el "+(i+1)+" deposito que mejor lo vende es "+mejorDeposito.getId()+ " y no tiene stock" + "<br/> ";
										
										justin+="el deposito que peor lo vende es "+peorDeposito.getId()+ " y su stock es "+peorDeposito.getArticulosStock().get(articulo.getArticulo()).getIid();
										articulo.setOrigen(peorDeposito.getId());
										articulo.setSucursal(mejorDeposito.getId());
										
										Logica.guardarLineaReposicion(articulo, justin, idSinc,0);
										break;//corto el for
									}
									
								}
							}
							
							
						}
						
					 
					}
					
					pos++;
				}
				
				
				
				
				
				
				
				
			}
			*/
			
			
		}
	}
	Double articulo1;
	Double baseColor2; 
	Double base3;
	Double marca4;
	Double seccion5;
	Double genero6;
	Double categoria7;
	Double clase8;
	Double importe9;
	Double deposito10;
	
	
	public DepositoDimension darPeorSacarDeLista(ArticuloReposicion articulo)
	{
		List<DepositoDimension> temp = new ArrayList<>();
		DepositoDimension retorno = null;
		int pos = -1;
		for (DepositoDimension d : depositosElegibles) 
		{
			try
			{
				if(d.getArticulosStock().get(articulo.getArticulo()).getIid()>0)
				{
					//entr? en los que tienen stock
					temp.add(d);
					pos++;
				}
			}
			catch(Exception e)
			{
				
			}
		}
		
		if(pos!=-1)
		{
			retorno = temp.get(pos);
			temp.remove(pos);
		}
		
		depositosElegibles = temp;
		return retorno;
	}
	
	
	public int darPosicionDepo(DepositoDimension d)
	{
		int pos = 0;
		for (DepositoDimension dep : depositosElegiblesII) 
		{
			if(dep.getId()==d.getId())
			{
				return pos;
			}
			pos++;
		}
		return pos;
	}
	
	
	
	
	
	
	
	
	
	
	
	public void recorrer(List<RepoDimension> lista, int prom)
	{
		for (RepoDimension r : lista) 
		{
			DepositoDimension depo;
			int multiplo = 1;
			if(r.getIdDeposito()==40 || r.getIdDeposito()==41)
			{
				multiplo = 30;
			}
			
			if(depositos.get(r.getIdDeposito())==null)
			{
				depo = new DepositoDimension(articulo1,baseColor2,base3, marca4,seccion5, genero6, categoria7,clase8,importe9, deposito10);
				depo.setId(r.getIdDeposito());
			}
			else
			{
				depo = depositos.get(r.getIdDeposito());
			}
			
			
			switch (prom) 
			{
				case 1:
				{
					depo.setProm1(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni1(r.getCantidadVendida()*multiplo);
					break;
				}
				case 2:
				{
					depo.setProm2(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni2(r.getCantidadVendida()*multiplo);
					break;
				}
				case 3:
				{
					depo.setProm3(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni3(r.getCantidadVendida()*multiplo);
					break;
				}
				case 4:
				{
					depo.setProm4(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni4(r.getCantidadVendida()*multiplo);
					break;
				}
				case 5:
				{
					depo.setProm5(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni5(r.getCantidadVendida()*multiplo);
					break;
				}
				case 6:
				{
					depo.setProm6(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni6(r.getCantidadVendida()*multiplo);
					break;
				}
				case 7:
				{
					depo.setProm7(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni7(r.getCantidadVendida()*multiplo);
					break;
				}
				case 8:
				{
					depo.setProm8(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni8(r.getCantidadVendida()*multiplo);
					break;
				}
				case 9:
				{
					depo.setProm9(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni9(r.getCantidadVendida()*multiplo);
					break;
				}
				case 10:
				{
					depo.setProm10(r.getPorcentajeDelTotal()*multiplo);
					depo.setUni10(r.getCantidadVendida()*multiplo);
					break;
				}
				
			}
			
			depositos.put(r.getIdDeposito(), depo);
		}
	}
	
}






















