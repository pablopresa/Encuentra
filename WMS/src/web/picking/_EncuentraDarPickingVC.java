package web.picking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import beans.Usuario;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataPicking;



public class _EncuentraDarPickingVC extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		try 
		{

			
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			String destino = request.getParameter("accion");
			int idPick = Integer.parseInt(request.getParameter("idPick"));
			List<DataPicking> pickings = new ArrayList<>();
			if(!destino.equals("verifica") && !destino.equals("clasifica")) 
			{
				pickings = Logica.encuentraDarPicking(idPick,idEmpresa);
				
				int totalSol = 0;
				int totalEnc = 0;
				int totalVer = 0;
				
				Hashtable<String, Integer> stockOrigenSAP = new Hashtable<>();// Logica.darStockOrigenSAP(pickings); 
				Hashtable<String, Integer> stockOrigen = new Hashtable<>();//Logica.darStockOrigenSAP(pickings); 
				
				for (DataPicking p : pickings) 
				{
				
					totalSol+=p.getSol();
					totalEnc+=p.getPick();
					totalVer+=p.getVerificada();
					try
					{
						p.setStockOSAP(stockOrigenSAP.get(p.getArticulo()));
					}
					catch (Exception e)
					{
						p.setStockOSAP(0);
					}
					
					try
					{
						p.setStockOrigen(stockOrigen.get(p.getArticulo()));
					}
					catch (Exception e)
					{
						p.setStockOrigen(0);
					}
					
				}
			
				request.setAttribute("totSol",totalSol);
				request.setAttribute("totEnc",totalEnc);
				request.setAttribute("totVer",totalVer);	
				session.setAttribute("pickings", pickings);
				
				/*if(destino.equals("verifica"))
				{
					List <DataPicking> pickingsToVer = new ArrayList<>();
					for (DataPicking d : pickings) 
					{
						if(d.getSol()-d.getVerificada()>0)
						{
							pickingsToVer.add(d);						
						}
						
					}
					session.setAttribute("pickings", pickingsToVer);
					
					uLog.registrarEventoHilo(session.getId(), "Seleccionando picking para verificar",idPick,112);
					return mapping.findForward("Verif");
					
				}
				else*/ if (destino.equals("Diferencia"))
				{
					uLog.registrarEventoHilo(session.getId(), "Seleccionando picking para ver diferencias",idPick,112);
					return mapping.findForward("Direrencia");
				}
				else if(destino.equals("ver"))
				{
					uLog.registrarEventoHilo(session.getId(), "Seleccionando picking para ver diferencias en PC",idPick,112);
					return mapping.findForward("VerifDSKTP");
				}
				else //if(destino.equals("rem"))
				{
					uLog.registrarEventoHilo(session.getId(), "Seleccionando picking para remitir",idPick,112);
					return mapping.findForward("RemitDSKTP");
				}
			}			
			else
			{
				pickings = Logica.encuentraDarPickingCBulto(idPick,idEmpresa);
				session.setAttribute("pickings", pickings);
				
				String redirect = "Clasif";
				switch (destino) {
				case "verifica":
					redirect = "Verif";
					break;
				case "clasifica":
					redirect = "Clasif";
					break;	
				default:
					break;
				}
				StringBuilder arregloArts = new StringBuilder();
				StringBuilder arregloDestino = new StringBuilder();
				StringBuilder arregloDestinosQty = new StringBuilder();
				
				List<Hashtable<String, List<DataPicking>>> a = new ArrayList<Hashtable<String,List<DataPicking>>>();
				List <DataPicking> pickingsToVer = new ArrayList<>();
				Hashtable<String, Hashtable<String, DataPicking[]>> clasificador = new Hashtable<String, Hashtable<String,DataPicking[]>>();
				Hashtable<String, DataPicking[]> htDestino = null;
				Boolean paramVerificacion = (Boolean) session.getAttribute("paramVerificacion");
				if(paramVerificacion==null){
					paramVerificacion = util.darParametroEmpresaBool(idEmpresa, 52);
					session.setAttribute("paramVerificacion", paramVerificacion);
				}
				for (DataPicking d : pickings) 
				{
					int cantidadTope = d.getSol();
					if(paramVerificacion)
					{
						cantidadTope = d.getPick();						
					}
					if(cantidadTope-d.getVerificada()>0)
					{
						/*
						arregloArts.append("'"+d.getArticulo()+"',");
						arregloDestino.append("'"+d.getPosSort()+"',");
						arregloDestinosQty.append("'"+d.getDestinosQty()+"',");
						pickingsToVer.add(d);
						*/
						try 
						{	//TENGO EL ARTICULO EN LA COLECCION Y TENGO EL DESTINO
							
							DataPicking[] arr = d.addX(clasificador.get(d.getArticulo()).get(d.getPosSort()),d);
							clasificador.get(d.getArticulo()).put(d.getPosSort(),arr);
						} 
						catch (Exception e) 
						{
							try 
							{	//TENGO EL ARTICULO EN LA COLECCION PERO NO TENGO EL DESTINO
								DataPicking[] arr0 = {};
								DataPicking[] arr = d.addX(arr0,d);
								clasificador.get(d.getArticulo()).put(d.getPosSort(),arr);
								
								
							} 
							catch (Exception e2) 
							{	//NO TENGO NI ARTICULO NI DESTINO
								DataPicking[] arr0 = {};
								htDestino = new Hashtable<String, DataPicking[]>();
								htDestino.put(d.getPosSort(), arr0);
								DataPicking[] arr = d.addX(arr0,d);
								htDestino.put(d.getPosSort(), arr);
								clasificador.put(d.getArticulo(), htDestino);
							}
						}
						
					}
					
				}
				if(clasificador.size()>0)
				{
					/*
					int corteA = arregloArts.toString().length()-1;
					int corteB = arregloDestino.toString().length()-1;
					int corteC = arregloDestinosQty.toString().length()-1;
					
					session.setAttribute("arregloArticulos", arregloArts.toString().substring(0, corteA));
					session.setAttribute("arregloDestinos", arregloDestino.toString().substring(0, corteB));
					session.setAttribute("arregloDestinosQty", arregloDestinosQty.toString().substring(0, corteC));
					*/
					//PARAMETROS DE VERIFICACION
					Boolean paramCantidad = util.darParametroEmpresaBool(idEmpresa, 46);
					
					Boolean paramDetalle = false;
					
					if(destino.equals("verifica")) {
						paramDetalle = util.darParametroEmpresaBool(idEmpresa, 47);
					}
					
					if(destino.equals("clasifica")) {
						paramDetalle = util.darParametroEmpresaBool(idEmpresa, 58);
					}
					

					session.setAttribute("paramCantidad", paramCantidad);
					session.setAttribute("paramDetalle", paramDetalle);
					Gson gson = new Gson();
					String clasificador_json = gson.toJson(clasificador);
					session.setAttribute("clasificador_json", clasificador_json);
					session.setAttribute("redirectVerificacion", redirect);
					return mapping.findForward(redirect);
				}
				else
				{
					request.setAttribute("menError", "ya se clasificaron todos los articulos del picking "+idPick);
					//Si se termina la tarea, vuelve a pedir todas las tareas del usuario para cargarlas en la Session
					//int idDeposito = Integer.parseInt(uLog.getDeposito());
					List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
					session.setAttribute("tarMob", tarMob);
					return mapping.findForward("fin");
				}
			}
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("no");

		}

		
		
	}
	
}















