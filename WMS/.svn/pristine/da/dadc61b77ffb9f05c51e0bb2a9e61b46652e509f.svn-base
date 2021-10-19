package web.almacen;

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

public class _EncuentraMoverOjo extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		Logica logica = new Logica();
		HttpSession session = request.getSession();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String error ="ATENCION";
		String idOjo = request.getParameter("idOjo");
		String idOjoD = request.getParameter("idOjoD");

		idOjo = idOjo.toUpperCase();
		idOjoD = idOjoD.toUpperCase();
		
		int deposito = 0;
		try {deposito = Integer.parseInt(uLog.getDeposito());}catch (Exception e) { }
		
		boolean origenEsBulto = false;
		boolean destinoEsBulto = false;
		boolean existeO = false;
		boolean existeD = false;
	    
		int tipoOjoO = logica.encuentraExisteUbica(idOjo, idEmpresa, deposito);
		int tipoOjoD = logica.encuentraExisteUbica(idOjoD, idEmpresa, deposito);
				
		if(tipoOjoO!=0)
		{
			existeO = true;
		}
		
		if(tipoOjoO==2)
		{
			origenEsBulto = true;
			
		}
		
		if(tipoOjoD!=0)
		{
			existeD = true;
		}
		
		if(tipoOjoD==2)
		{
			destinoEsBulto = true;
			
		}
		


		if(existeO && existeD)
		{
			
			List<DataOjoArticulo> articulosEnOjo;
			if(!origenEsBulto)
			{
				//comprobar si es bulto y si se puede mover
				articulosEnOjo= logica.encuentraDarOjosArticulos(idOjo, "","", "0, 1, 2, 3, 4, 5", "",false,false, idEmpresa, deposito, null);
				if (articulosEnOjo.size() == 0) {
					request.setAttribute("menError", "El ojo "+idOjo+" no tiene ningún artículo el cual pueda mover");
					return mapping.findForward("ok");
				}
				
			}
			else
			{
				//comprobar las reservadas
				articulosEnOjo = logica.encuentraArticulosBulto(idEmpresa, idOjo, false, deposito);
			}
			 
			if(articulosEnOjo.isEmpty())
			{
				//request.setAttribute("menError", "imposible mover, la ubicacion "+idOjo+" esta vacia" );
				error += " .Imposible mover, la ubicacion "+idOjo+" esta vacia";
				return mapping.findForward("ok");
			}
			else
			{
				
				String articulosComa = request.getParameter("destino");
				articulosComa = articulosComa.replace(" ", "");
				articulosComa = articulosComa.replaceAll("[\n\r]","");
				
				String [] desorden = articulosComa.split(","); // articulos
				
				
				List<DataIDDescripcion> ordenable = (List<DataIDDescripcion>) session.getAttribute("ordenable");
				if(ordenable==null)
				{
					ordenable = new ArrayList<>();
				}
				
				if(desorden[0].equals(""))
				{
					for (DataOjoArticulo doj : articulosEnOjo) 
					{
						DataIDDescripcion d = new DataIDDescripcion(doj.getCantidad(),doj.getArticulo());
						ordenable.add(d);
					}
				}
				else
				{
					for (String s : desorden) 
					{
						String[]data = s.split(":");
						int cant = Integer.parseInt(data[1]);
						
						DataIDDescripcion d = new DataIDDescripcion(cant,data[0]);
						ordenable.add(d);
					}
					
				}
				
				

				List<DataIDDescripcion> articulosCant = logica.EncuentraSortearListaDICant(ordenable);
				List<DataIDDescripcion> articulosReales = new ArrayList<>();
					
					
					
				int linea = 0;
				int lineaOk = 0;
				boolean seSigue = true;
				for (DataIDDescripcion d : articulosCant) 
				{
					linea ++;
					DataArticulo artReal = logica.encuentraCodArticulo(d.getDescripcion(), idEmpresa);
					
					String ojoBulto = artReal.getId();
					if(origenEsBulto){
						ojoBulto = idOjo;
					}
					
					if(logica.BultoMovible(idOjo, ojoBulto,artReal.getId(),d.getId(), idEmpresa)) {						
						
						if(artReal.getId()!=null)
						{
							for (int i = 0; i < d.getId(); i++) 
							{
								lineaOk ++;
								DataIDDescripcion da = new DataIDDescripcion(0,artReal.getId());
								articulosReales.add(da);
							}
						}
						else
						{
							error += ". Articulo desconocido en linea "+linea +" ("+d.getDescripcion()+")";
							seSigue = false;
						}
					} else {
						error += ". Bulto con reservas en linea "+linea +" ("+d.getDescripcion()+")";
						seSigue = false;
					}
				}
					
				if(!seSigue)
				{
					request.setAttribute("menError", error);
					return mapping.findForward("ok");
				}
				else
				{
					List<DataIDDescripcion> articulosCantReales = logica.EncuentraSortearListaDI(articulosReales);
					if(!articulosCantReales.isEmpty())
					{
						List<DataIDDescripcion> articulosBajar = new ArrayList<>();
						List<DataIDDescripcion> articulosSubir = new ArrayList<>();
						List<DataIDDescripcion> articulosNoPuedoBajar = new ArrayList<>();
						/********************recorro lo escaneado y me fijo si existe en origen para mover correctamente**********************************/
						Hashtable<String, DataOjoArticulo> articulosOJO = new Hashtable<>();
						
						for (DataOjoArticulo artEnOjo : articulosEnOjo) 
						{
							articulosOJO.put(artEnOjo.getArticulo(), artEnOjo);
						}
						
						
						
						for (DataIDDescripcion d : articulosCantReales) 
						{
						
							if(articulosOJO.get(d.getDescripcion())!=null)//pregunto si el escaneado está en el ojo de origen
							{
								DataOjoArticulo ojArt =articulosOJO.get(d.getDescripcion()); 
								int cantEnOjo= ojArt.getCantidad();
								int cantReserv = ojArt.getCantidadReservada();
								
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
							else//no estaba en origen
							{
								d.setDescripcionB(idOjo);
								articulosNoPuedoBajar.add(d);
								
							}
						}
						
						int cantNoBajados = articulosNoPuedoBajar.size();
						
						//ahora hacemos las bajadas y las subidas
						for (DataIDDescripcion b : articulosBajar) 
						{
							if(!origenEsBulto)
							{
								logica.encuentraBajaArticulosOjos(b.getId(),b.getDescripcion(), b.getDescripcionB(), idEmpresa);
							}
							else//hay que bajar del bulto de origen
							{
								logica.bajarSubirDeBulto(idOjo, articulosBajar, idEmpresa, uLog.getNumero(),-1, true);
							}
							
						}
						
						for (DataIDDescripcion s : articulosSubir) 
						{
							if(!destinoEsBulto)
							{
								logica.encuentraMoverOjos(s.getDescripcionB(), s.getDescripcion(),s.getId(),uLog.getNumero(),idEmpresa);
							}
							else//hay que subir al bulto de destino
							{
								logica.bajarSubirDeBulto(s.getDescripcionB(), articulosBajar, idEmpresa, uLog.getNumero(),1, true);
							}
							
							
							
							logica.IngresarMovimientoArticulo(idOjo, idOjoD, s.getDescripcion(), s.getId(), uLog.getNumero(),idEmpresa);
						}
						//REGISTRO LOS MOVIMIENTOS
						
						//quito bulto del context si es un bulto que se estaba preparando 
						if(origenEsBulto) {
							bulto b = (bulto) session.getAttribute("desarmar_b");
							try { //VER CON NICO MANANA

								b.Cerrar_Bulto(uLog.getNumero(), false);
								
								ServletContext context = request.getSession().getServletContext();
								Hashtable<String, bulto> cajasAbiertas = (Hashtable<String, bulto>) context.getAttribute(idEmpresa+"cajasAbiertas");
								if(cajasAbiertas.get(b.getEquipo_trabajo()+"-"+b.getDestino())!=null) {
									cajasAbiertas.remove(b.getEquipo_trabajo()+"-"+b.getDestino());
									context.setAttribute(idEmpresa+"cajasAbiertas", cajasAbiertas);
								}	
							} catch(Exception e){e.printStackTrace();}					
						}
						
						if(!articulosNoPuedoBajar.isEmpty())
						{
							request.setAttribute("menError", "Cuidado, no se pudieron mover todos los articulos que ingreso, consulte el detalle de errores");
						}
						else
						{
							request.setAttribute("menError", "Movimiento correcto");
						}
						
						request.setAttribute("movidos", articulosSubir);
						request.setAttribute("no_movidos", articulosNoPuedoBajar);
						request.setAttribute("cantNoBajados",cantNoBajados);
						return mapping.findForward("result");
					}
					else //muevo todo si no hay articulos?
					{
						//por ahora no. solo respondo con un mensaje de error
						request.setAttribute("menError", "no se ingresaron articulos validos para mover...");
						return mapping.findForward("ok");
					}
					
					
					
				}
				
			}
		}
		else
		{
			if (existeO) { //Si existe el ojo Orgien es porque no existe el ojo Destino
				error += ". No existe la ubicacion "+idOjoD+" en el sistema";
			} else {
				error += ". No existe la ubicacion "+idOjo+" en el sistema";
			}
		}
		
		
		uLog.registrarEventoMin(session.getId(), "(MOVE_OJO)"+error);
		
		
		request.setAttribute("menError", error);
		return mapping.findForward("ok");
	}

	

}
