package web.picking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;
import logica.imprimir_caja;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.MovStock;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.IPrint;

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataIDDescripcion;

public class _EncuentraCerrarCaja extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		Usuario uLog = null;
		HttpSession session = null;
		String comentario = "";
		int pick = 0;
		bulto b = null;
		Logica Logica = new Logica();
		Hashtable<String, bulto> cajasAbiertas = null;
		try {
			session = request.getSession();
			
			uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			int equipo = uLog.getEquipo_trabajo();
			int idDepoCentral = util.darParametroEmpresaINT(idEmpresa,4);
			System.out.println("cerrando caja");
			
			if (uLog!=null){
				String idDestino = request.getParameter("idDestino"); //bulto
				ServletContext context = request.getSession().getServletContext();
				cajasAbiertas = (Hashtable<String, bulto>) context.getAttribute(idEmpresa+"cajasAbiertas"); //coleccion de bultos abiertos
				
				if(cajasAbiertas==null || cajasAbiertas.size()==0){
					cajasAbiertas = Logica.BultosAbiertos(idEmpresa);
				}
				if(cajasAbiertas==null || cajasAbiertas.size()==0){
					request.setAttribute("menError", "No hay cajas abiertas!!");
					return mapping.findForward("boxes");
				}
				else{
					if(cajasAbiertas.get(equipo+"-"+idDestino)==null){
						request.setAttribute("menError", "No se encontro ninguna caja abierta del equipo "+equipo+" para el destino "+idDestino);
						return mapping.findForward("boxes");
					}
					else{
						b = cajasAbiertas.get(equipo+"-"+idDestino);
						boolean moverStock = true;
						boolean pudeCerrar = b.Cerrar_Bulto(uLog.getNumero(),moverStock);
						
						//CONTROLO QUE NO SE HAYA CERRADO ANTES LA CAJA
						if(!pudeCerrar) {
							cajasAbiertas.remove(equipo+"-"+idDestino);
							context.setAttribute(idEmpresa+"cajasAbiertas", cajasAbiertas);
							request.setAttribute("menError", "No se encontro para cerrar esta caja, verifique si no la cerro anteriormente");
							return forw (mapping, request, session, uLog, cajasAbiertas, b);
						}
						
						boolean controlCantidades = Logica.VerifCantidadesPicking(new ArrayList<>(b.getContenido().values()), b.getDestino(), idEmpresa);
						
						if(!controlCantidades) {
							//REMUEVO LA CAJA DE LA COLECCION
							cajasAbiertas.remove(equipo+"-"+idDestino);
							context.setAttribute(idEmpresa+"cajasAbiertas", cajasAbiertas);
							request.setAttribute("menError", "Se encontro una inconsistencia en las cantidades verificadas con las solicitadas en el picking. SE CERRO LA CAJA PERO NO SE EMITIO REMITO.");
							return forw (mapping, request, session, uLog, cajasAbiertas, b);
						}
						
						Hashtable<String, DataIDDescripcion> articulosHT = new Hashtable<>();
						List<DataIDDescripcion> articulos = new ArrayList<>();
						
						Enumeration<String> elements = b.getContenido().keys();
						
						bultoContenido bc = null;
						String key = "";
						int unidades = 0;
						while(elements.hasMoreElements()){
							key=elements.nextElement();
							bc = b.getContenido().get(key);
							unidades += bc.getCantidad();
							
							if(articulosHT.get(bc.getIdArticulo())==null){
								articulosHT.put(bc.getIdArticulo(), new DataIDDescripcion(bc.getCantidad(),bc.getIdArticulo()));
							}
							else{
								int cant = articulosHT.get(bc.getIdArticulo()).getId() + bc.getCantidad();
								articulosHT.get(bc.getIdArticulo()).setId(cant);
							}
						}
						
						Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
						//VERIFICO COMO SE VA A REMITIR ESTA CAJA
						if(!b.isRemision_al_cerrar()) {
							//ETIQUETA DE BULTO
							String etiq = "";
							etiq = util.imprimirEtiqueta(b, unidades, idEmpresa);	
							
				            api.PutColaImpresion(b.getIdBulto(), etiq, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
				            //REMUEVO LA CAJA DE LA COLECCION
							cajasAbiertas.remove(equipo+"-"+idDestino);
							context.setAttribute(idEmpresa+"cajasAbiertas", cajasAbiertas);
				            return forw (mapping, request, session, uLog, cajasAbiertas, b);
						}					
						
						int destino = 0;
						try {
							 destino = Integer.parseInt(b.getDestino());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//MOVIMIENTO EN VISUAL
						List<DataIDDescripcion> listaRemitos = new ArrayList<>();
						DataIDDescripcion remito = new DataIDDescripcion();
						int tipoComanda = 2;
						String obsTicket = "Movimiento hacia tienda "+destino;
						if(Logica.tipoDestino(destino, idEmpresa) == 100) {	//VENTA MAYORISTA
							/*tipoComanda = 3;
							obsTicket = "Movimiento hacia cliente "+idDestino;
							Hashtable<Integer, List<DataIDDescripcion>> solicitudesMayoristas = Logica.SolicitudesMayoristas(b.getIdBulto(),destino,
									articulosHT,idEmpresa, false);
							Enumeration<Integer> elementsSolicitudes = solicitudesMayoristas.keys();
							while (elementsSolicitudes.hasMoreElements()) {
								List<DataIDDescripcion> artsSolicitud = new ArrayList<>();
								int solicitud = elementsSolicitudes.nextElement();
								
								//articulos = new ArrayList<>(articulosHT.values());
								artsSolicitud = solicitudesMayoristas.get(solicitud);
								Collections.sort(artsSolicitud);
								artsSolicitud = Logica.DescripcionArticulos(artsSolicitud,idEmpresa);
								
								remito = util.remitir(idDepoCentral, destino, uLog.getNumero(), artsSolicitud, new Long("0"), solicitud, 0, 0, 
										uLog.getNombre()+" "+uLog.getApellido(), "Transferencia realizada por "+uLog.getNumero()+" desde encuentra", 
										idEmpresa, b, true);
								listaRemitos.add(remito);
								articulos.addAll(artsSolicitud);
							}*/
						}
						else {
							articulos = new ArrayList<>(articulosHT.values());
							Collections.sort(articulos);
							articulos = Logica.DescripcionArticulos(articulos,idEmpresa);
							
							remito = util.remitir(idDepoCentral, destino, uLog.getNumero(), articulos, new Long("0"), 0, 0, 0, 
									uLog.getNombre()+" "+uLog.getApellido(), "Transferencia realizada por "+uLog.getNumero()+" desde encuentra", 
									idEmpresa, b, false);
							listaRemitos.add(remito);
						}
												
						//REMUEVO LA CAJA DE LA COLECCION
						cajasAbiertas.remove(equipo+"-"+idDestino);
						context.setAttribute(idEmpresa+"cajasAbiertas", cajasAbiertas);
						
						//SETEO NUMERO DE REMITO
						String msjRemito = "";
						String idRemitos = "";
						for(DataIDDescripcion r: listaRemitos) {
							b.Cargar_Remito(r.getId()+"", 1, unidades);
							idRemitos += r.getId()+",";
							if(r.getId()==0) {
								msjRemito += r.getDescripcion()+" <br/>";
							}
						}				
						
						//ETIQUETA DE BULTO
						String etiq = "";
						etiq = util.imprimirEtiqueta(b, unidades, idEmpresa);
			            api.PutColaImpresion(b.getIdBulto(), etiq, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
			            
						if(!msjRemito.equals("")){
							request.setAttribute("menError", msjRemito);
							return forw (mapping, request, session, uLog, cajasAbiertas, b);
						}
									
						try {
							idRemitos = idRemitos.substring(0,idRemitos.length()-1);
						} catch (Exception e) {idRemitos = "";}
						//REMITO DE ENCUENTRA
						ImpresionesPDF.imprimirTicketMovStock(idDepoCentral, destino, uLog.getNick(), obsTicket, 
								articulos, idRemitos, tipoComanda, uLog.getIdEquipo(),idEmpresa, 1);
						
			           
					}
				}
				
			}
			else{
				return mapping.findForward("log");
			}
			
			
		} catch (Exception e) {
			request.setAttribute("menError", "Sucedio un error queriendo cerrar la caja");
			System.out.println("");
		}
		
		return forw (mapping, request, session, uLog, cajasAbiertas, b);
		

		
	
	}
	
	public ActionForward forw (ActionMapping mapping, HttpServletRequest request, HttpSession session, Usuario uLog,
			Hashtable<String, bulto> cajasAbiertas, bulto b){
		String boxes = request.getParameter("boxes");
		if(boxes!= null){
			List<bulto> boxe = new ArrayList<>(cajasAbiertas.values());
			session.setAttribute("boxes", boxe);
			try {
				uLog.registrarEventoMin( session.getId(), "Cerrando caja "+b.getIdBulto());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return mapping.findForward("boxes");
		}
		else{
			//buscar picking
			int idPick = (Integer) session.getAttribute("cerrarPick");
			String act = (String) session.getAttribute("actPick");
			session.removeAttribute("cerrarPick");
			 //LOGUEAMOS EVENTO
			try {
				uLog.registrarEvento(idPick, 0,112,0,true, session.getId(), "Cerrando caja "+b.getIdBulto());
			} catch (Exception e) {
				e.printStackTrace();
			}
            
			request.setAttribute("idPick", idPick);
			request.setAttribute("accion", act);
			
			switch (act) {
			case "verifica":
				return mapping.findForward("verif");
			case "clasifica":
				return mapping.findForward("clasif");
			default :
				return mapping.findForward("clasif");
			}
		}
	}
	
	
}
