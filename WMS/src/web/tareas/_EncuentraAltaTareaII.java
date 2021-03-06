package web.tareas;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.ArticuloLineaReposicion;
import beans.OrdenAlmacen;
import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataPickingS;
import beans.encuentra.Tarea;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.LogicaBulto;
import logica.LogicaBultoGeneral;
import logica.Utilidades;



public class _EncuentraAltaTareaII extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		LogicaBulto logicaB = new LogicaBulto();
		try 
		{
			int idPicking = 0;

			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			int contadorElevadoristas = 0;
			List<Usuario> operariosSelect = null;
				
			String listaW =(String) session.getAttribute("listaWMS");
			if(listaW!=null && listaW.equals("1"))
			{
				Usuario u = new Usuario();
				u.setNumero(0);
				u.setNick("Integraciones");
				operariosSelect = new ArrayList<>();
				operariosSelect.add(u);
			}
			else
			{
				operariosSelect = darListaOperarios(request); 
			}
			
			
			
			
			
			Tarea tarea = (Tarea) session.getAttribute("tarea");
			if (operariosSelect.isEmpty())
		     {
				request.setAttribute("menError", "Seleccione almenos un usuario para la tarea");
		        System.out.println("Seleccione almenos un usuario para la tarea");
		        return mapping.findForward("no");
		      }	else {
		    	  for(Usuario usu : operariosSelect) {   // cuento cuantos elevadoristas hay en el caso que sea de PPG
		    		  int perfil = usu.getPerfil();
		    		  if (usu.getPerfil() == 11 && idEmpresa==5) {  
		    			  contadorElevadoristas ++; 
		    		  }
		    	  }		    	  
		      }
			
			if (contadorElevadoristas > 0 && contadorElevadoristas < operariosSelect.size()) { // SI HAY ELEVADORISTAS PERO NO SON TODOS
				request.setAttribute("menError", "Si un usuario es elevadorista, todos deben serlo");
				System.out.println("Si un usuario es elevadorista, todos deben serlo");
		        return mapping.findForward("no");
			}
			
			boolean elevadoristas = false;
			if (contadorElevadoristas == 0) { // SI NINGUNO ES ELEVADORISTA
				elevadoristas = false;
			}
			if (contadorElevadoristas == operariosSelect.size()) {  // SI SON TODOS ELEVADORISTAS
				elevadoristas = true;
			}
			
					

			
				if(tarea==null) {		//******TAREA DE PICKING******\\
									
					Boolean paramRemision = (Boolean) session.getAttribute("paramRemision");//parametro remitir al cerrar
					Boolean paramPickingBulto = (Boolean) session.getAttribute("paramPickingBulto");// param picking por bulto
					Boolean paramBultosParciales = (Boolean) session.getAttribute("paramPickingBultosGenerales"); // si usa el algoritmo de bultos parciales o no
					Boolean paramTomaDesdeAlmacen = (Boolean) session.getAttribute("paramPickingArticulosAlmacen"); // si toma articulos desde almacen o los deja sin asignar
					int paramBultoCantMinxSku = (int) session.getAttribute("paramBultoCantMinxSku");//param cant min por bulto
					Boolean paramPrioridadBulto = (Boolean) session.getAttribute("paramPrioridadBulto");	//prioridad de reserva Bulto=true - Almacen=false
					
					//tipo reposicion
					String tipoRe = (String) session.getAttribute("tipoRe");
					
					
					//VERIFICACION DE PARAMETROS				
					
					String thisPick = request.getParameter("checkParametro");
					String thisPickPB = request.getParameter("checkParametroPB");
					String thisPickCantMin = request.getParameter("CantMinxSku");
					String thisBultosParciales = request.getParameter("checkParametroPBG");
					String thisTomoDesdeAlmacen = request.getParameter("checkParametroPAA");
					String thisPrioridadReserva = request.getParameter("prioridadReserva");
					
					boolean paramThisPick = LeerBoolean(thisPick, paramRemision, idEmpresa, Logica, 22);
					boolean paramThisPickPB = LeerBoolean(thisPickPB, paramPickingBulto, idEmpresa, Logica, 27);
					int paramThisPickCantMin = LeerInt(thisPickCantMin, paramBultoCantMinxSku, idEmpresa, Logica, 28);
					boolean paramUsoBultoParcial =  LeerBoolean(thisBultosParciales, paramBultosParciales, idEmpresa, Logica, 37);
					boolean paramTomaArticulosAlmacen =  LeerBoolean(thisTomoDesdeAlmacen, paramTomaDesdeAlmacen, idEmpresa, Logica, 38);
					boolean prioridadReservaBultos =  LeerBoolean(thisPrioridadReserva, paramPrioridadBulto, idEmpresa, Logica, 42);
					
					
					if(tipoRe.equals("3"))
						paramThisPick = false;
					
					String singleTar = (String) session.getAttribute("singleTar");
					List<ArticuloLineaReposicion> articulosIn = (List<ArticuloLineaReposicion>) session.getAttribute("articulosIn");
					Boolean verifECauto = util.darParametroEmpresaBool(idEmpresa, 15);
					
					Hashtable<Integer, DataIDDescripcion> depositosHT = (Hashtable<Integer, DataIDDescripcion>) session.getAttribute("depositosHTinRepo");
					int indiceDestinos = 0;
					if(singleTar==null || singleTar.equals("1")) //******UN SOLO PICKING******\\
					{	
						tarea = TaskInit(uLog, operariosSelect);	
						if(tarea.getTipo().getId()==1 && session.getAttribute("dipick")!=null)
						{
							int cantidades = (int) session.getAttribute("cantiInRepo");
							String destinos = "";
							List<DataIDDescripcion> destinosLST = new ArrayList<>(depositosHT.values());
							 
							for (DataIDDescripcion d : destinosLST) 
							{
								destinos+=d.getDescripcion()+" - ";
								indiceDestinos++;
								if(indiceDestinos == 10 ){
									destinos += " y mas ";
									break;
								}
							}						
							//String observacion = "Picking "+destinos+" "+uLog.getNick();
							String observacion = destinos;
											
							idPicking = Logica.encuentraAltaPicking(articulosIn, cantidades, "Sin filtros aplicados",idEmpresa,verifECauto,paramThisPick);						
							tarea = util.CompleteTask(tarea, idPicking, observacion, articulosIn, cantidades);
							util.guardarTarea(tarea, uLog, idEmpresa,session, Logica, idPicking, paramThisPickPB, paramThisPickCantMin, paramUsoBultoParcial, 
									paramTomaArticulosAlmacen, prioridadReservaBultos, elevadoristas); //Cambiar booleano 29/1
						}				
					}
					else										//******VARIOS PICKINGS******\\
					{
						Hashtable<String, List<ArticuloLineaReposicion>> htPickPedidos = new Hashtable<String, List<ArticuloLineaReposicion>>();
						
						for (ArticuloLineaReposicion ar : articulosIn) 
						{
							if(htPickPedidos.get(ar.getPedido()+"")!=null)
							{
								htPickPedidos.get(ar.getPedido()+"").add(ar);
							}
							else
							{
								List<ArticuloLineaReposicion> lista = new ArrayList<>();
								lista.add(ar);
								htPickPedidos.put(ar.getPedido()+"", lista);
							}
						}
						
						List<List<ArticuloLineaReposicion>> pickingsPedidos = null;
						pickingsPedidos = new ArrayList<List<ArticuloLineaReposicion>>(htPickPedidos.values());
									
						int cantidadTarea = 0;
						for (List<ArticuloLineaReposicion> articulosInL : pickingsPedidos) 
						{
							tarea = TaskInit(uLog, operariosSelect);
							cantidadTarea = 0;
							for(ArticuloLineaReposicion a:articulosInL) {
								cantidadTarea += a.getCantidad();
								depositosHT.put(a.getDestino().getId(), a.getDestino());
							}					
							
							String destinos = "";
							List<DataIDDescripcion> destinosLST = new ArrayList<>(depositosHT.values());
							indiceDestinos = 0;
							for (DataIDDescripcion d : destinosLST) 
							{
								destinos+=d.getDescripcion()+" - ";
								indiceDestinos++;
								if(indiceDestinos == 10 ){
									destinos += " y mas ";
									break;
								}
							}
							
							idPicking = Logica.encuentraAltaPicking(articulosInL, cantidadTarea, "Sin filtros aplicados",idEmpresa,verifECauto,paramThisPick);											
							tarea = util.CompleteTask(tarea, idPicking, destinos, articulosIn, cantidadTarea);											
							util.guardarTarea(tarea, uLog, idEmpresa,session, Logica, idPicking, paramThisPickPB, paramThisPickCantMin, paramUsoBultoParcial, 
									paramTomaArticulosAlmacen, prioridadReservaBultos, elevadoristas);		 //Cambiar booleano 29/1			
						}
					}
					
					session.setAttribute("tarea", null);
					session.setAttribute("dipick", null);
					session.setAttribute("cantiInRepo", null);
					session.setAttribute("depositosHTinRepo", null);
					session.setAttribute("articulosIn", null);
					
					
					if(listaW!=null && listaW.equals("1"))
					{
						session.setAttribute("listaWMS", null);
						
						
						int idDeposito = Integer.parseInt(uLog.getDeposito());
						List<DataPickingS> pickings = Logica.encuentraDarPickingS("externos",idEmpresa,idDeposito);
						session.setAttribute("pickingsS", pickings);
						
						return mapping.findForward("listaWMS");
					}
					session.setAttribute("listaWMS", null);
					return mapping.findForward("ok");
				}
				else {
					tarea.setUsuarios(operariosSelect);
					if(tarea.getUsuarios().size()>1)
					{	
						tarea.setParcial(false);
					}
					else
					{
						tarea.setParcial(true);
					}
					
					switch (tarea.getId()) {
						case 5:	/*********tarea sort**********/
							session.setAttribute("tarea", tarea);
							return mapping.findForward("sorter");
					case 106:/*********tarea almacen**********/
							List<OrdenAlmacen> almacen = Logica.getOrdenesAlmacen(idEmpresa);
							session.setAttribute("tarea", tarea);
							session.setAttribute("almacen", almacen);
							return mapping.findForward("alm");
					default:
							break;
					}
				}								
		} catch (Exception e) {
			System.out.println("llegamos al catch");
			e.printStackTrace();
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		return mapping.findForward("ok");
	}

	


	
	private List<Usuario> darListaOperarios(HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		List<DataIDDescripcion> operarios = (List<DataIDDescripcion>) session.getAttribute("operarios");
		List<Usuario> operariosSelect = new ArrayList<>();
		for (DataIDDescripcion da: operarios) 
		{
			String on = request.getParameter(String.valueOf(da.getId()));
			if(on!=null &&  on.equals("on"))
			{
				Usuario u = new Usuario();
				u.setNumero(da.getId());
				u.setNick(da.getDescripcion());
				u.setPerfil(da.getIdB()); // le asigne al dataiddescripcion el IdB como perfil
				operariosSelect.add(u);
			}
		}
		
		 return operariosSelect;
	}
	
	
	private Tarea TaskInit(Usuario uLog, List<Usuario> operariosSelect) {
		Tarea tarea = new Tarea();			
		
		DataIDDescripcion estado = new DataIDDescripcion();
		estado.setId(0);
		
		tarea.setEstado(estado);		
		tarea.setPorcentaje(0);
		tarea.setResponsable(new DataIDDescripcion(uLog.getNumero(),uLog.getNick()));
		tarea.setTipo(new DataIDDescripcion(1,"Picking"));
		tarea.setUsuarios(operariosSelect);
		if(tarea.getUsuarios().size()>1)
		{	
			tarea.setParcial(false);
		}
		else
		{
			tarea.setParcial(true);
		}
		
		return tarea;
	}
	
	
	
	private boolean LeerBoolean(String paramUsuario, boolean paramBase, int idEmpresa, Logica Logica, int parametro) {
		boolean salida = false;
		try {
			try {
				salida = paramUsuario.equals("1");
			} catch (Exception e) {
				System.out.println("parametro de remision nulo");
				salida = paramBase;
			}
			
			
			if(paramBase!=salida) {
				Logica.updateParametroEmpresa(idEmpresa, parametro, salida);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return salida;
	}
	
	private int LeerInt(String paramUsuario, int paramBase, int idEmpresa, Logica Logica, int parametro) {
		int salida = 0;
		try {
			try {
				salida = Integer.parseInt(paramUsuario) - 1;
			} catch (Exception e) {
				System.out.println("parametro de remision nulo");
				salida = paramBase;
			}
			
			
			if(paramBase!=salida) {
				Logica.updateParametroEmpresa(idEmpresa, parametro, salida);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return salida;
	}
}
