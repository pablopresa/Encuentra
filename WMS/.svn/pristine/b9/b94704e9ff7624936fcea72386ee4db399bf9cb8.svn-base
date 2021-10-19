package logica;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import beans.ArticuloMatrizHTML;
import beans.TalleMatrizRepo;
import beans.Usuario;
import beans.encuentra.DataPicking;
import beans.encuentra.MovimientoMatrizDis;
import dataTypes.DataIDDescripcion;
import main.EcommerceProcessOrders;
import persistencia._EncuentraConexion;

public class Utilidad 
{
	public Utilidad()
	{}
	
	
	
	
	
	public List<List<DataPicking>> max200(List<List<DataPicking>> in)
	{
		List<List<DataPicking>> out = new ArrayList<List<DataPicking>>();
		int largoMax = 199;
		
		for (List<DataPicking> list : in) 
		{
			if(list.size()>largoMax)
			{
				List<List<DataPicking>> outII = new ArrayList<List<DataPicking>>();
				int largo = list.size();
				int tope = largoMax;
				int desde = 0;
				while (tope<largo) 
				{
					outII.add(list.subList(desde, tope));
					desde +=largoMax;
					tope +=largoMax;
				}
				//agrega el resto
				outII.add(list.subList(desde, largo));
				
				for (List<DataPicking> list2 : outII) 
				{
					out.add(list2);
				}
				
			}
			else
			{
				out.add(list);
			}
		}
		
		return out;
	}
	
	
	
	
	
	public ArticuloMatrizHTML reconstruirMatrizFromMovs(ArticuloMatrizHTML articulo, List<MovimientoMatrizDis> movimientos)
	{
		String depoOr ="";
		articulo.setMovimientos(new ArrayList<>());
		List<String> reqs = new ArrayList<>();
		for (MovimientoMatrizDis movs : movimientos) 
		{
			reqs.add(movs.getRequest());
		}
		
		
		for (String re : reqs) 
		{
			
			
			int origenx;
			int origeny;
			int destinox;
			int destinoy;
			
			try
			{
				String[] values = re.split(","); 
				
				origenx = Integer.parseInt(values[5].split(":")[1]);
				origeny = Integer.parseInt(values[6].split(":")[1]);
				destinox = Integer.parseInt(values[2].split(":")[1]);
				destinoy = Integer.parseInt(values[3].split(":")[1]);
				String cantidadOriginal = values[1].split(":")[1];
				String cantidad = values[0].split(":")[1];
				
				
				MovimientoMatrizDis movimiento = new MovimientoMatrizDis();
				movimiento.setRequest(re);
				
				
				int cantOriginal = 0;
				int cant =0;
				
				try
				{
					cant = Integer.parseInt(cantidad);
					cantOriginal = Integer.parseInt(cantidadOriginal);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				cant = cant-cantOriginal;
				
				
				
				
				
				
				
				List<String> linea0 =  articulo.getLineas().get(0);
				
				List<String> lineaOrigen =  articulo.getLineas().get(origenx+1);
				
				  	
				
				depoOr = lineaOrigen.get(0);
				String talleOr = linea0.get(origeny+1);
				
				List<String> lineaDestino =  articulo.getLineas().get(destinox+1);
				
				String depoDe = lineaDestino.get(0);
				//String talleDe = linea0.get(destinoy);
				
				
				TalleMatrizRepo origen = articulo.getTallesM().get((origenx)+","+(origeny));
				
				try
				{
					origen.setPedido(origen.getPedido()+cant);
					origen.setOrigen(Integer.parseInt(depoOr));
					origen.setDestino(Integer.parseInt(depoDe));
					if(origen.getSugerido()>0)
					{
						origen.setSugerido(origen.getSugerido()-cant);
					}
					articulo.getTallesM().put((origenx)+","+(origeny), origen);
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				//System.out.println((destinox)+","+(destinoy));
				TalleMatrizRepo destino = articulo.getTallesM().get((destinox)+","+(destinoy));
				
				destino.setOrigen(Integer.parseInt(depoOr));
				destino.setDestino(Integer.parseInt(depoDe));
				destino.setSugerido(cant);
				articulo.getTallesM().put((destinox)+","+(destinoy), destino);
				
				
				
				
				System.out.println("Mover "+cant+" unidad del talle "+talleOr+" desde"+depoOr+" hasta "+depoDe);
				movimiento.setCantidad(cant);
				movimiento.setTalle(talleOr);
				movimiento.setDestino(Integer.parseInt(depoDe));
				movimiento.setOrigen(Integer.parseInt(depoOr));
				
				articulo.getMovimientos().add(movimiento);
				//artis.remove(posicion);
				
				//artis.add(posicion,articulo);
				
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("error");
			}
			
			
			
		}
		
		return articulo;
		
	}
	
	
	
	public Hashtable<String, ArticuloMatrizHTML> darMatrizFromX(ArticuloMatrizHTML am, String depoOrigen)
	{
		
		Hashtable<String, ArticuloMatrizHTML> articulosHTML = new Hashtable<>();
		Hashtable<String, Integer> totaleTalle = new Hashtable<>();
		Hashtable<String, Integer> totaleDeposito = new Hashtable<>();
		
			
			if(am!=null)
			{
				
				
				
				List<String> depos = new ArrayList<>();
				for (List<String> linea : am.getLineas()) 
				{
					if(!linea.get(0).equals("") && !linea.get(0).equals("TTL."))
					{
						depos.add(linea.get(0));
					}
				}
				List<TalleMatrizRepo> tallesTodos = new ArrayList<>(am.getTallesM().values());
				Hashtable<String, String> tallesHT = new Hashtable<>();
				for (TalleMatrizRepo t : tallesTodos) 
				{
					tallesHT.put(t.getId(), t.getId());
				}
				
				List<String> talles = new ArrayList<>(tallesHT.values());
				Collections.sort(talles);
				
				int posDep = 0;
				for (String de : depos) 
				{
					int posTal = 0;
					for (String t : talles)
					{
						TalleMatrizRepo ta = am.getTallesM().get(posDep+","+posTal);
						if(totaleTalle.get(am.getArticulo()+ta.getId())==null)
						{
							totaleTalle.put(am.getArticulo()+ta.getId(), ta.getStock());
						}
						else
						{
							int in = totaleTalle.get(am.getArticulo()+ta.getId());
							totaleTalle.put(am.getArticulo()+ta.getId(), in+ta.getStock());
						}
						
						
						if(totaleDeposito.get(am.getArticulo()+de)==null)
						{
							totaleDeposito.put(am.getArticulo()+de, ta.getStock());
						}
						else
						{
							int in = totaleDeposito.get(am.getArticulo()+de);
							totaleDeposito.put(am.getArticulo()+de, in+ta.getStock());
						}
						
						posTal++;
					}
				 posDep++;	
				}
				
				
			}
		
			
			ArticuloMatrizHTML arti = am;
			
			arti.setArticulo(am.getArticulo());
			
			
			List<String> trAntes = new ArrayList<>();
			List<List <String>> lineas = new ArrayList<List<String>>();
			
			if(am!=null)
			{
				
				//linea con los totales de los talles
				String trA = "<tr>";
				
				trA += "<td style='text-align: center; width:35px;'>&nbsp;</td>";
				
				List<TalleMatrizRepo> tallesTodos = new ArrayList<>(am.getTallesM().values());
				Hashtable<String, String> tallesHT = new Hashtable<>();
				for (TalleMatrizRepo t : tallesTodos) 
				{
					tallesHT.put(t.getId(), t.getId());
				}
				
				List<String> talles = new ArrayList<>(tallesHT.values());
				Collections.sort(talles);
				
				
				
				for (String t : talles)
				{
					
					trA +="<td style='text-align: center; width:35px;'>"+totaleTalle.get(am.getArticulo()+t)+"</td>";
					
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
				
				
				
				for (String t : talles)
				{
					trA +="<td style='text-align: center; width:35px;'>"+t+"</td>";
					linea.add(t);
					
				}
				trA += "<td style='text-align: center; width:35px;'>TTL.</td>";
				trA +="</tr>";
				
				lineas.add(linea);
				trAntes.add(trA);
				
				
				Hashtable<String, TalleMatrizRepo> tallesM = new Hashtable<>();
				
				
				int posDepo = 0;
				
				List<String> depos = new ArrayList<>();
				for (List<String> li : am.getLineas()) 
				{
					if(!li.get(0).equals("") && !li.get(0).equals("TTL."))
					{
						depos.add(li.get(0));
					}
				}
				
				
				
				for (String de : depos) 
				{
					
					trA = new String();
					linea = new ArrayList<>();
					trA = "<tr class='odd gradeX' style='padding: 1px;'>";
					
					if(depoOrigen!=null && de.equals(depoOrigen))
					{
						trA += "<td style='padding: 1px;' ><table style='width:100%'><tr><td align='left'>"+de+"</td><td align='right'>&nbsp;</td></tr></table></td>";
						arti.setLineaOrigen(posDepo+1);//esto es porque el deposito tiene una linea 0 que es el cabezal 
					}
					else
					{
						trA += "<td style='padding: 1px;' ><table style='width:100%'><tr><td align='left'>"+de+"</td><td align='right'><input type='checkbox' id='"+posDepo+"-1000'/> </td></tr></table></td>";
					}
					linea.add(de);
					
					int posTalle = 0;
					for (String t : talles)
					{
						
						TalleMatrizRepo ta = am.getTallesM().get(posDepo+","+posTalle);
						if(ta.getId().equals("10.0"))
						{
							//System.out.println("");
						}
						tallesM.put(posDepo+","+posTalle, ta);
						String valor = "";
						if(ta.getStock()+ta.getSugerido()-ta.getPedido()>0)
						{
							valor=(ta.getStock()+ta.getSugerido()-ta.getPedido())+"";
						}
						
						trA+="<td style='padding: 1px; text-align: center; width:35px;'> <input type='text' style='width:30px; border: none; border-color: transparent; background-color: transparent;' value='"+valor+"' name='"+posDepo+"-"+posTalle+"' cajaTalle='1' id='"+posDepo+"-"+posTalle+"' onfocus=getBox('"+posDepo+"-"+posTalle+"') onkeyup='getfocus(event)' onblur=leaveBox('"+posDepo+"-"+posTalle+"') /></td>";
						
						linea.add(ta.getId());
						posTalle++;
					}
					
					trA += "<td style='text-align: center; width:35px; padding: 1px;'>"+totaleDeposito.get(arti.getArticulo()+de)+"</td>";
					trA +="</tr>";
					
					trAntes.add(trA);
					lineas.add(linea);
					posDepo++;
				}
				
				arti.setTrsANTES(trAntes);
				arti.setLineas(lineas);
				arti.setTallesM(tallesM);
				
				articulosHTML.put(arti.getArticulo(), arti);
				
			}
			else
			{
				System.out.println("no lo encontro");
			}
			
			return articulosHTML;
			
		
		}
	
	public Hashtable<String, String> buscarArticulos(List<String> datos, int idEmpresa) 
	{
		_EncuentraConexion econ = new _EncuentraConexion();
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			Hashtable<String, String> retorno = new Hashtable<>();
		
			for (String a : datos) 
			{
				//168618C:38
				//168618C:385
				//LANDVO-01NNA:16
				String base = a.split(":")[0];
				String q = "SELECT * FROM articulos WHERE idArticulo LIKE '"+base+"%' AND idTipo = 1 AND idEmpresa = "+idEmpresa;

				List<String> matches = econ.BuscarArticulo(q);

				if(!matches.isEmpty())
				{
					if(matches.size()==1)
					{
						retorno.put(a,matches.get(0));
					}
					else
					{
						buscar_talle(a, base, econ, retorno, idEmpresa);
					}
					
				}
				else
				{
					base = base.replace("-", "");
					q = "SELECT * FROM articulos WHERE idArticulo LIKE '"+base+"%' AND idTipo = 1 AND idEmpresa = "+idEmpresa;
					matches = econ.BuscarArticulo(q);
					if(!matches.isEmpty())
					{
						if(matches.size()==1)
						{
							retorno.put(a,matches.get(0));
						}
						else
						{
							buscar_talle(a, base, econ, retorno, idEmpresa);
						}
						
					}
					else
					{
						retorno.put(a,"");
					}
				}
				
				
			}
			return retorno;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public void buscar_talle(String a, String base, _EncuentraConexion econ, Hashtable<String, String> retorno, int idEmpresa) {
		try {
			String var = a.split(":")[1].replace(".", "");
			var = var.replace(".", "");
			if(var.contains("-"))
			{
				var = var.replaceAll("\\-", "");
			}

			String q = "SELECT * FROM articulos WHERE idArticulo LIKE '"+base+"%%-"+var+"%' AND idTipo = 1 AND idEmpresa = "+idEmpresa;
			List<String> matches = econ.BuscarArticulo(q);
			if(!matches.isEmpty())
			{
				if(matches.size()==1)
				{
					retorno.put(a,matches.get(0));
				}
				else
				{
					//hay >1 que coinciden con la variable
					if(!var.endsWith("5"))
					{
						for (String ar : matches) 
						{
							if(!ar.endsWith("5"))
							{
								retorno.put(a,ar);
								break;
							}
						}
					}
					else
					{
						for (String ar : matches) 
						{
							if(ar.endsWith("5"))
							{
								retorno.put(a,ar);
								break;
							}
						}
					}
				}
				
			}
			else
			{
				//todo lo mismo pero pruebo sin el guion
				q = "SELECT * FROM articulos WHERE idArticulo LIKE '"+base+"%%"+var+"%' AND idTipo = 1 AND idEmpresa = "+idEmpresa;
				matches = econ.BuscarArticulo(q);
				if(!matches.isEmpty())
				{
					if(matches.size()==1)
					{
						retorno.put(a,matches.get(0));
					}
					else
					{
						//hay >1 que coinciden con la variable
						if(!var.endsWith("5"))
						{
							for (String ar : matches) 
							{
								if(!ar.endsWith("5"))
								{
									retorno.put(a,ar);
									break;
								}
							}
						}
						else
						{
							for (String ar : matches) 
							{
								if(ar.endsWith("5"))
								{
									retorno.put(a,ar);
									break;
								}
							}
						}



					}

				}
				else if(var.equalsIgnoreCase("uni"))
				{
					var = "U";
					//todo lo mismo pero pruebo sin el guion
					q = "SELECT * FROM articulos WHERE idArticulo LIKE '"+base+"%%"+var+"%' AND idTipo = 1 AND idEmpresa = "+idEmpresa;
					matches = econ.BuscarArticulo(q);
					if(matches.isEmpty())
					{
						q = "SELECT * FROM articulos WHERE idArticulo LIKE '"+base+"%%-"+var+"%' AND idTipo = 1 AND idEmpresa = "+idEmpresa;
						matches = econ.BuscarArticulo(q);
						if(matches.isEmpty())
						{
							q = "SELECT * FROM articulos WHERE idArticulo = '"+base+"' AND idTipo = 1 AND idEmpresa = "+idEmpresa;
							matches = econ.BuscarArticulo(q);
						}
					}
					if(!matches.isEmpty())
					{
						if(matches.size()==1)
						{
							retorno.put(a,matches.get(0));
						}
						else
						{
							//hay >1 que coinciden con la variable
							if(!var.endsWith("5"))
							{
								for (String ar : matches) 
								{
									if(!ar.endsWith("5"))
									{
										retorno.put(a,ar);
										break;
									}
								}
							}
							else
							{
								for (String ar : matches) 
								{
									if(ar.endsWith("5"))
									{
										retorno.put(a,ar);
										break;
									}
								}
							}



						}

					}
					else
					{
						retorno.put(a,"");
					}
				}
				else
				{
					retorno.put(a,"");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
