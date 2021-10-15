package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;

import logica.Logica;

import beans.ArticuloMatrizHTML;
import beans.ArticuloMatrizReposicion;
import beans.ArticuloReposicion;
import beans.DepositoDimension;
import beans.ProcesadorRepo;
import beans.RepoDimension;
import beans.StockReposicion;
import beans.TalleMatrizRepo;
import beans.Usuario;

public class Distribuidor 
{
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
	
	
	
	
	private ProcesadorRepo procesadr;
	List<DepositoDimension> depositosElegibles = new ArrayList<>();
	List<DepositoDimension> depositosElegiblesII = new ArrayList<>();
	Hashtable<String, ArticuloMatrizReposicion> aticulosMatriz = new Hashtable<>();
	private Hashtable<String, String> aticulosBase;
	
	public Hashtable<String, String> getAticulosBase() {
		return aticulosBase;
	}


	public void setAticulosBase(Hashtable<String, String> aticulosBase) {
		this.aticulosBase = aticulosBase;
	}


	public Distribuidor() 
	{
		Logica Logica = new Logica();
		this.aticulosBase = new Hashtable<>();
		List<DataIDDescripcion> promedios = Logica.darPromediosPondRepo(1);
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
	
	
	public Distribuidor(boolean procesador) 
	{
		Logica Logica = new Logica();
		if(procesador)
		{
			this.procesadr = new ProcesadorRepo();
		}
		this.aticulosBase = new Hashtable<>();
		
		List<DataIDDescripcion> promedios = Logica.darPromediosPondRepo(1);
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
	
	@SuppressWarnings("unchecked")
	public Hashtable<String, ArticuloMatrizHTML> AddArtProcesador(List<DataIDDescripcion> lista, boolean analizar, Integer depoOrigen, List<ArticuloReposicion> arts, int idEmpresa) 
	{
		Logica Logica = new Logica();
		
		
		ProcesadorRepo procesador = procesadr;
		//articulo articuloBaseColor articuloBase marca seccion clase venta stockCentral stockLocal stockBaseColor precio sucursal
		
		
		
		int idSinc = 0;
		int porcentaje = 0;
		int pasada =0;
		String baseC = lista.get(0).getDescripcion();
		if(arts==null)
		{
			arts = new ArrayList<>();
			for (DataIDDescripcion l : lista) 
			{
				try
				{
					ArticuloReposicion ar = Logica.darListaArticulosToDist(l.getDescripcion(),idEmpresa).get(0); 
					arts.add(ar);
				}
				catch
				(Exception e)
				{
					
				}
				
			}
			
		}
		if(arts.isEmpty())
		{
			return null;
		}
		else
		{
			for (DataIDDescripcion aid : lista) 
			{
				ArticuloReposicion articulo =null;
				for (ArticuloReposicion a : arts) 
				{
					if(a.getArticuloBaseColor().equals(aid.getDescripcion()));
					{
						articulo = a;
						break;
					}
				}
				
				
				//001.00600000223.0
				String tall = articulo.getArticulo().substring(13);
				articulo.setArticulo(aid.getDescripcion()+tall);
				articulo.setArticuloBaseColor(aid.getDescripcion());
				aticulosBase.put(articulo.getArticuloBaseColor(), articulo.getArticuloBaseColor());
				
				ArticuloMatrizReposicion aM = new ArticuloMatrizReposicion();
				aM.setArticulo(articulo.getArticuloBaseColor());
				depositos = new Hashtable<>();
				
				
				procesador.agregarLinea(articulo, idSinc, porcentaje, null,analizar, depoOrigen, false,null);
				Hashtable<String, StockReposicion> stock = procesador.getStocks().get(articulo.getArticuloBaseColor());
				if(stock==null)
				{
					System.out.println("el null");
				}
				else
				{
					
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
					
					Hashtable<Integer, DepositoDimension> depositosMatriz = new Hashtable<>();
					for (DepositoDimension d : depositosList) 
					{
						// seteo stock para cada deposito
						List<StockReposicion> stoksAr = new ArrayList<>(stock.values());
						Hashtable<String, TalleMatrizRepo>  tallesMatriz = new Hashtable<>();
						List<DataIDDescripcion> talles = new ArrayList<>();
						
						for (StockReposicion s : stoksAr) // todos los talles del base color
						{
							//009.31648641328.0
							//aca deberia agregar el articulo a la lista
							DataIDDescripcion talle = new DataIDDescripcion(0, s.getArticulo().substring(13,17));
							talles.add(talle);
							int stk = 0;
							DataIDIDDescripcion stockDepo = s.getDepositos().get(d.getId());
							if(stockDepo==null)
							{
								stockDepo = new DataIDIDDescripcion(d.getId(), 0, null);
								stk = 0;
							}
							if(d.getArticulosStock()==null)
							{
								Hashtable<String, DataIDIDDescripcion> stocks = new Hashtable<>();
								stocks.put(s.getArticulo(), stockDepo);
								stk = stockDepo.getIid();
								d.setArticulosStock(stocks);
							}
							else
							{
								Hashtable<String, DataIDIDDescripcion> stocks = d.getArticulosStock();
								stocks.put(s.getArticulo(), stockDepo);
								d.setArticulosStock(stocks);
								stk = stockDepo.getIid();
							}
										
							TalleMatrizRepo talleM = new TalleMatrizRepo();
							talleM.setStock(stk);
							talleM.setPedido(0);
							talleM.setSugerido(0);
							talleM.setId(talle.getDescripcion());
							tallesMatriz.put(talle.getDescripcion(), talleM);
						}
						
						aM.setTalles(talles);
						d.setTallesMatrizHT(tallesMatriz);
						depositosMatriz.put(d.getId(), d);
								
						
					}
					
					Collections.sort(depositosList);
					
								
					aM.setDepositosHT(depositosMatriz);
					aticulosMatriz.put(aM.getArticulo(), aM);
					
					
				}
			}
		}
			
		
		
		
		
		
		
		
		
	
		Hashtable<String, ArticuloMatrizHTML> articulosHTML = new Hashtable<>();
		List<String>abases = new ArrayList<>(aticulosBase.values());
		
		
		Hashtable<String, Integer> totaleTalle = new Hashtable<>();
		Hashtable<String, Integer> totaleDeposito = new Hashtable<>();
		
		for (String ab : abases) 
		{
			ArticuloMatrizReposicion am = aticulosMatriz.get(ab);
			if(am!=null)
			{
				Collections.sort(am.getTalles());
				List<DepositoDimension> depos = new ArrayList<>(am.getDepositosHT().values());
				Collections.sort(depos);
				
				for (DepositoDimension de : depos) 
				{
					for (DataIDDescripcion t : am.getTalles())
					{
						TalleMatrizRepo ta = de.getTallesMatrizHT().get(t.getDescripcion());
						
						if(totaleTalle.get(ab+t.getDescripcion())==null)
						{
							totaleTalle.put(ab+t.getDescripcion(), ta.getStock());
						}
						else
						{
							int in = totaleTalle.get(ab+t.getDescripcion());
							totaleTalle.put(ab+t.getDescripcion(), in+ta.getStock());
						}
						
						
						if(totaleDeposito.get(ab+de.getId())==null)
						{
							totaleDeposito.put(ab+de.getId(), ta.getStock());
						}
						else
						{
							int in = totaleDeposito.get(ab+de.getId());
							totaleDeposito.put(ab+de.getId(), in+ta.getStock());
						}
					}
					
				}
				
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		for (String ab : abases) 
		{
			ArticuloMatrizHTML arti = new ArticuloMatrizHTML();
			arti.setArticulo(ab);
			System.out.println(ab);
			ArticuloMatrizReposicion am = aticulosMatriz.get(ab);
			List<String> trAntes = new ArrayList<>();
			List<List <String>> lineas = new ArrayList<List<String>>();
			
			if(am!=null)
			{
				
				//linea con los totales de los talles
				String trA = "<tr>";
				
				trA += "<td style='text-align: center; width:35px;'>&nbsp;</td>";
				
				
				Collections.sort(am.getTalles());
				
				for (DataIDDescripcion t : am.getTalles()) 
				{
					
					trA +="<td style='text-align: center; width:35px;'>"+totaleTalle.get(ab+t.getDescripcion())+"</td>";
					
				}
				trA += "<td style='text-align: center; width:35px;'>&nbsp;</td>";
				trA +="</tr>";
				
				trAntes.add(trA);
				
				//linea con los id de los talles
				trA = new String();
				List <String> linea = new ArrayList<>();
				
				trA = "<tr>";
				
				trA += "<td>&nbsp;</td>";
				linea.add("");
				
				Collections.sort(am.getTalles());
				
				for (DataIDDescripcion t : am.getTalles()) 
				{
					trA +="<td style='text-align: center; width:35px;'>"+t.getDescripcion()+"</td>";
					linea.add(t.getDescripcion());
					
				}
				trA += "<td style='text-align: center; width:35px;'>TTL.</td>";
				trA +="</tr>";
				linea.add("TTL");
				lineas.add(linea);
				trAntes.add(trA);
				
				List<DepositoDimension> depos = new ArrayList<>(am.getDepositosHT().values());
				Hashtable<String, TalleMatrizRepo> tallesM = new Hashtable<>();
				
				Collections.sort(depos);
				int posDepo = 0;
				for (DepositoDimension de : depos) 
				{
					
					trA = new String();
					linea = new ArrayList<>();
					trA = "<tr class='odd gradeX' style='padding: 1px;'>";
					
					if(depoOrigen!=null && de.getId()==depoOrigen)
					{
						trA += "<td style='padding: 1px;' ><table style='width:100%'><tr><td align='left'>"+de.getId()+"</td><td align='right'>&nbsp;</td></tr></table></td>";
						arti.setLineaOrigen(posDepo+1);//esto es porque el deposito tiene una linea 0 que es el cabezal 
					}
					else
					{
						trA += "<td style='padding: 1px;' ><table style='width:100%'><tr><td align='left'>"+de.getId()+"</td><td align='right'><input type='checkbox' id='"+posDepo+"-1000'/> </td></tr></table></td>";
					}
					linea.add(de.getId()+"");
					
					int posTalle = 0;
					for (DataIDDescripcion t : am.getTalles())
					{
						TalleMatrizRepo ta = de.getTallesMatrizHT().get(t.getDescripcion());
						ta.setPorcentaje(de.getPromGral());
						tallesM.put(posDepo+","+posTalle, ta);
						String valor = "";
						if(ta.getStock()>0)
						{
							valor=ta.getStock()+"";
						}
						
						trA+="<td style='padding: 1px; text-align: center; width:35px;'> <input type='text' style='width:30px; border: none; border-color: transparent; background-color: transparent;' value='"+valor+"' name='"+posDepo+"-"+posTalle+"' cajaTalle='1' id='"+posDepo+"-"+posTalle+"' onfocus=getBox('"+posDepo+"-"+posTalle+"') onkeyup='getfocus(event)' onblur=leaveBox('"+posDepo+"-"+posTalle+"') /></td>";
						
						linea.add(t.getDescripcion());
						posTalle++;
					}
					
					trA += "<td style='text-align: center; width:35px; padding: 1px;'>"+totaleDeposito.get(ab+de.getId())+"</td>";
					trA +="</tr>";
					linea.add(totaleDeposito.get(ab+de.getId())+"");
					trAntes.add(trA);
					lineas.add(linea);
					posDepo++;
				}
				
				arti.setTrsANTES(trAntes);
				arti.setLineas(lineas);
				arti.setTallesM(tallesM);
				
				articulosHTML.put(ab, arti);
				
			}
			else
			{
				System.out.println("no lo encontro");
			}
			
		}
	
		setAticulosBase(aticulosBase);
		return articulosHTML;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Hashtable<String, ArticuloMatrizHTML> AddArt(List<DataIDDescripcion> lista, boolean analizar, int depoOrigen, int idEmpresa) 
	{
		Logica Logica = new Logica();
		
		ProcesadorRepo procesador = new ProcesadorRepo();
		//articulo articuloBaseColor articuloBase marca seccion clase venta stockCentral stockLocal stockBaseColor precio sucursal
		
		Hashtable<String, Integer> stocksReservados = Logica.DarStocksReservadosRepo(1);
		Hashtable<String, Integer> stocksEnviados = Logica.DarStocksEnviadosRepo(1);
		
		int idSinc = 0;
		int porcentaje = 0;
		int pasada =0;
		String baseC = lista.get(0).getDescripcion();
		List<ArticuloReposicion> arts = Logica.darListaArticulosToDist(baseC,idEmpresa);
		if(arts.isEmpty())
		{
			return null;
		}
		else
		{
			for (DataIDDescripcion aid : lista) 
			{
				ArticuloReposicion articulo = arts.get(0);
				//001.00600000223.0
				String tall = articulo.getArticulo().substring(13);
				articulo.setArticulo(aid.getDescripcion()+tall);
				articulo.setArticuloBaseColor(aid.getDescripcion());
				aticulosBase.put(articulo.getArticuloBaseColor(), articulo.getArticuloBaseColor());
				
				ArticuloMatrizReposicion aM = new ArticuloMatrizReposicion();
				aM.setArticulo(articulo.getArticuloBaseColor());
				depositos = new Hashtable<>();
				
				
				procesador.agregarLinea(articulo, idSinc, porcentaje, stocksReservados,analizar, depoOrigen,false,stocksEnviados);
				Hashtable<String, StockReposicion> stock = procesador.getStocks().get(articulo.getArticuloBaseColor());
				if(stock==null)
				{
					System.out.println("el null");
				}
				
				
				
				//List<RepoDimension> articulos = procesador.getArticulos().get(articulo.getArticulo());
				List<RepoDimension> baseColor= procesador.getBaseColor().get(articulo.getArticuloBaseColor());
				List<RepoDimension> base= procesador.getBase().get(articulo.getArticuloBase());
				List<RepoDimension> marca= procesador.getMarca().get(articulo.getMarca());
				List<RepoDimension> seccion= procesador.getSeccion().get(articulo.getMarca()+"-"+articulo.getSeccion());
				List<RepoDimension> clase= procesador.getClase().get(articulo.getMarca()+"-"+articulo.getClase());
				List<RepoDimension> importe= procesador.getImporte().get(articulo.getPrecio());
				List<RepoDimension> sucursal= procesador.getSucursal().get(articulo.getSucursal());
				List<RepoDimension> genero= procesador.getGenero().get(articulo.getGenero());
				List<RepoDimension> categoria= procesador.getCategoria().get(articulo.getCategoria());
				
				
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
				List<DepositoDimension> depositosListInvert = new ArrayList<>();
				Hashtable<Integer, DepositoDimension> depositosMatriz = new Hashtable<>();
				for (DepositoDimension d : depositosList) 
				{
					// seteo stock para cada deposito
					List<StockReposicion> stoksAr = new ArrayList<>(stock.values());
					Hashtable<String, TalleMatrizRepo>  tallesMatriz = new Hashtable<>();
					List<DataIDDescripcion> talles = new ArrayList<>();
					
					for (StockReposicion s : stoksAr) // todos los talles del base color
					{
						//009.31648641328.0
						//aca deberia agregar el articulo a la lista
						DataIDDescripcion talle = new DataIDDescripcion(0, s.getArticulo().substring(13,17));
						talles.add(talle);
						int stk = 0;
						DataIDIDDescripcion stockDepo = s.getDepositos().get(d.getId());
						if(stockDepo==null)
						{
							stockDepo = new DataIDIDDescripcion(d.getId(), 0, null);
							stk = 0;
						}
						if(d.getArticulosStock()==null)
						{
							Hashtable<String, DataIDIDDescripcion> stocks = new Hashtable<>();
							stocks.put(s.getArticulo(), stockDepo);
							stk = stockDepo.getIid();
							d.setArticulosStock(stocks);
						}
						else
						{
							Hashtable<String, DataIDIDDescripcion> stocks = d.getArticulosStock();
							stocks.put(s.getArticulo(), stockDepo);
							d.setArticulosStock(stocks);
							stk = stockDepo.getIid();
						}
									
						TalleMatrizRepo talleM = new TalleMatrizRepo();
						talleM.setStock(stk);
						talleM.setPedido(0);
						talleM.setSugerido(0);
						talleM.setId(talle.getDescripcion());
						tallesMatriz.put(talle.getDescripcion(), talleM);
					}
					
					aM.setTalles(talles);
					d.setTallesMatrizHT(tallesMatriz);
					depositosMatriz.put(d.getId(), d);
							
					DepositoDimension dt = new DepositoDimension(articulo1,baseColor2,base3, marca4,seccion5, genero6, categoria7,clase8,importe9, deposito10);
					try
					{
						
						dt.setProm1(d.getProm1()*-1);
						dt.setProm2(d.getProm2()*-1);
						dt.setProm3(d.getProm3()*-1);
						dt.setProm4(d.getProm4()*-1);
						dt.setProm5(d.getProm5()*-1);
						dt.setProm6(d.getProm6()*-1);
						dt.setProm7(d.getProm7()*-1);
						dt.setProm8(d.getProm8()*-1);
						dt.setArticulosStock(d.getArticulosStock());
						dt.setId(d.getId());
						dt.setTallesDeposito(d.getTallesDeposito());
						dt.setTallesMatriz(d.getTallesMatriz());
						dt.setTallesMatrizHT(d.getTallesMatrizHT());
						dt.setUni1(d.getUni1());
						dt.setUni2(d.getUni2());
						dt.setUni3(d.getUni3());
						dt.setUni4(d.getUni4());
						dt.setUni5(d.getUni5());
						dt.setUni6(d.getUni6());
						dt.setUni7(d.getUni7());
						dt.setUni8(d.getUni8());
						depositosListInvert.add(dt);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				
				Collections.sort(depositosList);
				Collections.sort(depositosListInvert);
							
				aM.setDepositosHT(depositosMatriz);
				aticulosMatriz.put(aM.getArticulo(), aM);
			}
		}
			
		
		
		
		
		
		
		
		
	
		Hashtable<String, ArticuloMatrizHTML> articulosHTML = new Hashtable<>();
		List<String>abases = new ArrayList<>(aticulosBase.values());
		
		
		Hashtable<String, Integer> totaleTalle = new Hashtable<>();
		Hashtable<String, Integer> totaleDeposito = new Hashtable<>();
		
		for (String ab : abases) 
		{
			ArticuloMatrizReposicion am = aticulosMatriz.get(ab);
			if(am!=null)
			{
				Collections.sort(am.getTalles());
				List<DepositoDimension> depos = new ArrayList<>(am.getDepositosHT().values());
				Collections.sort(depos);
				
				for (DepositoDimension de : depos) 
				{
					for (DataIDDescripcion t : am.getTalles())
					{
						TalleMatrizRepo ta = de.getTallesMatrizHT().get(t.getDescripcion());
						
						if(totaleTalle.get(ab+t.getDescripcion())==null)
						{
							totaleTalle.put(ab+t.getDescripcion(), ta.getStock());
						}
						else
						{
							int in = totaleTalle.get(ab+t.getDescripcion());
							totaleTalle.put(ab+t.getDescripcion(), in+ta.getStock());
						}
						
						
						if(totaleDeposito.get(ab+de.getId())==null)
						{
							totaleDeposito.put(ab+de.getId(), ta.getStock());
						}
						else
						{
							int in = totaleDeposito.get(ab+de.getId());
							totaleDeposito.put(ab+de.getId(), in+ta.getStock());
						}
					}
					
				}
				
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		for (String ab : abases) 
		{
			ArticuloMatrizHTML arti = new ArticuloMatrizHTML();
			arti.setArticulo(ab);
			System.out.println(ab);
			ArticuloMatrizReposicion am = aticulosMatriz.get(ab);
			List<String> trAntes = new ArrayList<>();
			List<List <String>> lineas = new ArrayList<List<String>>();
			
			if(am!=null)
			{
				
				//linea con los totales de los talles
				String trA = "<tr>";
				
				trA += "<td style='text-align: center; width:35px;'>&nbsp;</td>";
				
				
				Collections.sort(am.getTalles());
				
				for (DataIDDescripcion t : am.getTalles()) 
				{
					
					trA +="<td style='text-align: center; width:35px;'>"+totaleTalle.get(ab+t.getDescripcion())+"</td>";
					
				}
				trA += "<td style='text-align: center; width:35px;'>&nbsp;</td>";
				trA +="</tr>";
				
				trAntes.add(trA);
				
				//linea con los id de los talles
				trA = new String();
				List <String> linea = new ArrayList<>();
				
				trA = "<tr>";
				
				trA += "<td>&nbsp;</td>";
				linea.add("");
				
				Collections.sort(am.getTalles());
				
				for (DataIDDescripcion t : am.getTalles()) 
				{
					trA +="<td style='text-align: center; width:35px;'>"+t.getDescripcion()+"</td>";
					linea.add(t.getDescripcion());
					
				}
				trA += "<td style='text-align: center; width:35px;'>TTL.</td>";
				trA +="</tr>";
				
				lineas.add(linea);
				trAntes.add(trA);
				
				List<DepositoDimension> depos = new ArrayList<>(am.getDepositosHT().values());
				Hashtable<String, TalleMatrizRepo> tallesM = new Hashtable<>();
				
				Collections.sort(depos);
				int posDepo = 0;
				for (DepositoDimension de : depos) 
				{
					
					trA = new String();
					linea = new ArrayList<>();
					trA = "<tr class='odd gradeX' style='padding: 1px;'>";
					
					if(de.getId()==depoOrigen)
					{
						trA += "<td style='padding: 1px;' ><table style='width:100%'><tr><td align='left'>"+de.getId()+"</td><td align='right'>&nbsp;</td></tr></table></td>";
						arti.setLineaOrigen(posDepo+1);//esto es porque el deposito tiene una linea 0 que es el cabezal 
					}
					else
					{
						trA += "<td style='padding: 1px;' ><table style='width:100%'><tr><td align='left'>"+de.getId()+"</td><td align='right'><input type='checkbox' id='"+posDepo+"-1000'/> </td></tr></table></td>";
					}
					linea.add(de.getId()+"");
					
					int posTalle = 0;
					for (DataIDDescripcion t : am.getTalles())
					{
						TalleMatrizRepo ta = de.getTallesMatrizHT().get(t.getDescripcion());
						ta.setPorcentaje(de.getPromGral());
						tallesM.put(posDepo+","+posTalle, ta);
						String valor = "";
						if(ta.getStock()>0)
						{
							valor=ta.getStock()+"";
						}
						
						trA+="<td style='padding: 1px; text-align: center; width:35px;'> <input type='text' style='width:30px; border: none; border-color: transparent; background-color: transparent;' value='"+valor+"' name='"+posDepo+"-"+posTalle+"' cajaTalle='1' id='"+posDepo+"-"+posTalle+"' onfocus=getBox('"+posDepo+"-"+posTalle+"') onkeyup='getfocus(event)' onblur=leaveBox('"+posDepo+"-"+posTalle+"') /></td>";
						
						linea.add(t.getDescripcion());
						posTalle++;
					}
					
					trA += "<td style='text-align: center; width:35px; padding: 1px;'>"+totaleDeposito.get(ab+de.getId())+"</td>";
					trA +="</tr>";
					
					trAntes.add(trA);
					lineas.add(linea);
					posDepo++;
				}
				
				arti.setTrsANTES(trAntes);
				arti.setLineas(lineas);
				arti.setTallesM(tallesM);
				
				articulosHTML.put(ab, arti);
				
			}
			else
			{
				System.out.println("no lo encontro");
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		for (String ab : abases) 
		{
			System.out.println(ab);
			ArticuloMatrizReposicion am = aticulosMatriz.get(ab);
			if(am!=null)
			{
				String cabezal = "\t ";
				Collections.sort(am.getTalles());
				for (DataIDDescripcion t : am.getTalles()) 
				{
					cabezal +="\t"+t.getDescripcion()+"\t";
				}
				System.out.println(cabezal);
				List<DepositoDimension> depos = new ArrayList<>(am.getDepositosHT().values());
				Collections.sort(depos);
				for (DepositoDimension de : depos) 
				{
					String lineaDepo = de.getId()+"\t";
					
					for (DataIDDescripcion t : am.getTalles())
					{
						TalleMatrizRepo ta = de.getTallesMatrizHT().get(t.getDescripcion());
						lineaDepo+="ST"+ta.getStock()+" PE"+ta.getPedido()+" SU"+ta.getSugerido()+"\t";
					}
					System.out.println(lineaDepo);
				}
			}
			else
			{
				System.out.println("no lo encontro");
			}
			
		}
				
		*/
		
		setAticulosBase(aticulosBase);
		return articulosHTML;
	}
	
	
	
	
	
	
	
	
	
	
	public void recorrer(List<RepoDimension> lista, int prom)
	{
		for (RepoDimension r : lista) 
		{
			DepositoDimension depo;
			
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
					depo.setProm1(r.getPorcentajeDelTotal());
					depo.setUni1(r.getCantidadVendida());
					break;
				}
				case 2:
				{
					depo.setProm2(r.getPorcentajeDelTotal());
					depo.setUni2(r.getCantidadVendida());
					break;
				}
				case 3:
				{
					depo.setProm3(r.getPorcentajeDelTotal());
					depo.setUni3(r.getCantidadVendida());
					break;
				}
				case 4:
				{
					depo.setProm4(r.getPorcentajeDelTotal());
					depo.setUni4(r.getCantidadVendida());
					break;
				}
				case 5:
				{
					depo.setProm5(r.getPorcentajeDelTotal());
					depo.setUni5(r.getCantidadVendida());
					break;
				}
				case 6:
				{
					depo.setProm6(r.getPorcentajeDelTotal());
					depo.setUni6(r.getCantidadVendida());
					break;
				}
				case 7:
				{
					depo.setProm7(r.getPorcentajeDelTotal());
					depo.setUni7(r.getCantidadVendida());
					break;
				}
				case 8:
				{
					depo.setProm8(r.getPorcentajeDelTotal());
					depo.setUni8(r.getCantidadVendida());
					break;
				}
				case 9:
				{
					depo.setProm9(r.getPorcentajeDelTotal());
					depo.setUni9(r.getCantidadVendida());
					break;
				}
				case 10:
				{
					depo.setProm10(r.getPorcentajeDelTotal());
					depo.setUni10(r.getCantidadVendida());
					break;
				}
				
			}
			
			depositos.put(r.getIdDeposito(), depo);
		}
	}
	
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
					//entré en los que tienen stock
					temp.add(d);
					pos++;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		if(pos!=-1)
		{
			retorno = temp.get(pos);
			temp.remove(pos);
			depositosElegibles = temp;
		}
		else
		{
			System.out.println(articulo.getArticulo());
		}
		
		
		return retorno;
	}
	
}






















