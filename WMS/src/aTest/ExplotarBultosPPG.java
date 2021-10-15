package aTest;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.bulto;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataOjoArticulo;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class ExplotarBultosPPG extends Action 
{

	
	public static void main(String[] args) 
	{
		
		Logica Logica = new Logica();
		
		Utilidades util = new Utilidades();
		
		Usuario uLog = Logica.loginEncuentra("admin", "AdminPpGg",5);		
		int idEmpresa = util.darEmpresa(uLog);
		
		String q = "SELECT 0, B.idBulto, OT.idOjo FROM bulto B\r\n"
				+ "inner join ojostienenarticulos OT ON B.idBulto = OT.idArticulo AND B.IdEmpresa = OT.IdEmpresa\r\n"
				+ "INNER JOIN ojos O ON O.idOjo = OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n"
				+ "INNER JOIN estanterias E ON E.idEstanteria = O.idEstanteria AND E.idEmpresa = O.IdEmpresa\r\n"
				+ "WHERE  B.IdEmpresa = 5 AND E.AlmacenaPallet=1 order by B.idBulto  \r\n";
		
		
		List<DataIDDescripcion> bultosBaja = Logica.darListaDataIdDescripcionConsulMYSQL(q);
		String bultosIN = "";
		Hashtable<String, List<DataIDDescripcion>> contenidosBultos = new Hashtable<String, List<DataIDDescripcion>>();
		boolean pri = true;
		for (DataIDDescripcion bbj : bultosBaja) 
		{
			if(pri)
			{
				pri=false;
				bultosIN += "'"+bbj.getDescripcion()+"'";
				
			}
			else
			{
				bultosIN += ",'"+bbj.getDescripcion()+"'";
			}
				
			
		}
		
		String q2 = "SELECT cantidad,idArticulo,idBulto FROM bulto_contenido bc WHERE bc.idBulto IN ("+bultosIN+")";
		List<DataIDDescripcion> bultosBajaContenido = Logica.darListaDataIdDescripcionConsulMYSQL(q2);
		for (DataIDDescripcion bbc : bultosBajaContenido) 
		{
			List<DataIDDescripcion> contenido = null;
			if(contenidosBultos.containsKey(bbc.getDescripcionB()))
			{
				contenido = contenidosBultos.get(bbc.getDescripcionB());
			}
			else
			{
				contenido = new ArrayList<>();
				
			}
			contenido.add(bbc);
			contenidosBultos.put(bbc.getDescripcionB(), contenido);
			
		}
		
		
		List<String> consultas = new ArrayList<>();
		
		for (DataIDDescripcion bulBaj : bultosBaja) 
		{
			try
			{
				String error = "";
				String idOjo = bulBaj.getDescripcion();
				String  idOjoD = bulBaj.getDescripcionB();
				
				int deposito = 5;
				
				
				boolean origenEsBulto = true;
				boolean destinoEsBulto = false;
				boolean existeO = true;
				boolean existeD = true;
				

				if(existeO && existeD)
				{
					
					List<DataIDDescripcion> articulosEnOjo = contenidosBultos.get(bulBaj.getDescripcion());
					
					 
					if(articulosEnOjo==null || articulosEnOjo.isEmpty())
					{
						//request.setAttribute("menError", "imposible mover, la ubicacion "+idOjo+" esta vacia" );
						
						error += " .Imposible mover, la ubicacion "+idOjo+" esta vacia";
						uLog.registrarEventoMin("PROCESS", "(MOVE_OJO) "+error);
					}
					else
					{
						
						String articulosComa = "";
						articulosComa = articulosComa.replace(" ", "");
						articulosComa = articulosComa.replaceAll("[\n\r]","");
						
						String [] desorden = articulosComa.split(",");
						
						
						List<DataIDDescripcion> ordenable = new ArrayList<>();
						
						
						ordenable.addAll(articulosEnOjo);
						
						
						

						List<DataIDDescripcion> articulosCant = Logica.EncuentraSortearListaDICant(ordenable);
						
							
							
							
						int linea = 0;
						int lineaOk = 0;
						boolean seSigue = true;
						
							
						if(!seSigue)
						{
							uLog.registrarEventoMin("PROCESS", "(MOVE_OJO) "+error);
						}
						else
						{
							List<DataIDDescripcion> articulosCantReales = articulosCant;
							if(!articulosCantReales.isEmpty())
							{
								List<DataIDDescripcion> articulosBajar = new ArrayList<>();
								List<DataIDDescripcion> articulosSubir = new ArrayList<>();
								List<DataIDDescripcion> articulosNoPuedoBajar = new ArrayList<>();
								/********************recorro lo escaneado y me fijo si existe en origen para mover correctamente**********************************/
								
								
								
								
								for (DataIDDescripcion d : articulosCantReales) 
								{
								
									if(true)//pregunto si el escaneado está en el ojo de origen
									{
										 
										int cantEnOjo= d.getId();
										int cantReserv = 0;
										
										if(cantEnOjo>=d.getId() && cantEnOjo-d.getId()>=cantReserv)
										{
											d.setDescripcionB(idOjo);
											d.setIdB(cantReserv);
											articulosBajar.add(d);
											
											DataIDDescripcion destino = d.clone();
											destino.setDescripcionB(idOjoD);
											articulosSubir.add(destino);
										}
										else
										{
											int diferencia = d.getId()-(cantEnOjo-cantReserv);
											
											if(cantEnOjo-cantReserv>0) {
												d.setId(cantEnOjo-cantReserv);
												d.setDescripcionB(idOjo);
												d.setIdB(cantReserv);
												articulosBajar.add(d);
												
												DataIDDescripcion destino = d.clone();
												destino.setDescripcionB(idOjoD);
												destino.setId(cantEnOjo-cantReserv);
												articulosSubir.add(destino);
											}		
											
											DataIDDescripcion noPude = d.clone();
											noPude.setId(diferencia);
											articulosNoPuedoBajar.add(noPude);
											
										}
										
									}
									
								}
								
								
								//ahora hacemos las bajadas y las subidas
								for (DataIDDescripcion b : articulosBajar) 
								{
									if(!origenEsBulto)
									{
										 Logica.encuentraBajaArticulosOjos(b.getId(),b.getDescripcion(), b.getDescripcionB(), idEmpresa);
									}
									else//hay que bajar del bulto de origen
									{
										consultas.add(Logica.query_bajarSubirDeBulto(idOjo, articulosBajar, idEmpresa, uLog.getNumero(),-1, true));
									}
									
								}
								
								for (DataIDDescripcion s : articulosSubir) 
								{
									if(!destinoEsBulto)
									{
										consultas.add(Logica.query_encuentraMoverOjos(s.getDescripcionB(), s.getDescripcion(),s.getId(),uLog.getNumero(),idEmpresa));
									}
									else//hay que subir al bulto de destino
									{
										Logica.bajarSubirDeBulto(s.getDescripcionB(), articulosBajar, idEmpresa, uLog.getNumero(),1, true);
									}
									
									
									
									 consultas.add(Logica.query_IngresarMovimientoArticulo(idOjo, idOjoD, s.getDescripcion(), s.getId(), uLog.getNumero(),idEmpresa));
								}
								
								
								if(!articulosNoPuedoBajar.isEmpty())
								{
									error="Cuidado, no se pudieron mover todos los articulos que ingreso, consulte el detalle de errores";
									consultas.add(uLog.query_registrarEventoMin("PROCESS", "(MOVE_OJO) "+bulBaj.getDescripcion() +" "+ error));
									
								}
								else
								{
									error=  "Movimiento correcto";
									consultas.add(uLog.query_registrarEventoMin("PROCESS", "(MOVE_OJO) "+bulBaj.getDescripcion() +" "+ error));
								}
								
								
							}
							
							
							
							
						}
						
					}
				}
				else
				{
					//request.setAttribute("menError", "No existe la ubicacion en el sistema");
					error += ". No existe la ubicacion "+idOjo+" en el sistema";
				}
			}
			catch (Exception e) 
			{
				
			}
			
			
		}
		
		try
		{
			StringBuilder bigquery = new StringBuilder();
			int pasada=0;
			for (String qs : consultas) 
			{
				pasada++;
				bigquery.append(qs);
				if(pasada==1000)
				{
					Logica.getEper().persistirBatch(bigquery.toString());
					pasada=0;
					bigquery = new StringBuilder();
				}
			}
			
			Logica.getEper().persistirBatch(bigquery.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	

}
