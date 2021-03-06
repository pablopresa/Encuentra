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
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;

public class _EncuentraCerrarBC extends Action 
{


	bulto b = null;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		Usuario uLog = null;
		HttpSession session = null;
		String comentario = "";
		int pick = 0;
		Logica Logica = new Logica();
		String redirect = "";
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
			redirect = (String) session.getAttribute("redirectVerificacion");
			 
			System.out.println("cerrando caja");
			
			
			if (uLog!=null){
				b = (bulto)session.getAttribute("remitirBulto"); //bulto
				ServletContext context = request.getSession().getServletContext();

				if(true){
					if(b == null){
						session.setAttribute("menError", "No se encontro el bulto a remitir");
						session.removeAttribute("remitirBulto");
						return mapping.findForward(redirect);
					}
					else{
						boolean moverStock = true;
						
						boolean controlCantidades = Logica.VerifCantidadesPicking(new ArrayList<>(b.getContenido().values()), b.getDestino(), idEmpresa);
						
						if(!controlCantidades) {
							session.setAttribute("menError", "Se encontro una inconsistencia en las cantidades verificadas con las solicitadas en el picking. SE CERRO LA CAJA PERO NO SE EMITIO REMITO.");
							return forw (mapping, request, session, uLog, b, redirect);
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
							b.Cargar_Remito(b.getIdBulto(), 1, unidades);
				            api.PutColaImpresion("BC"+b.getIdBulto(), etiq, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
				            return forw (mapping, request, session, uLog, b, redirect);
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
							obsTicket = "Movimiento hacia cliente "+destino;
							Hashtable<Integer, List<DataIDDescripcion>> solicitudesMayoristas = Logica.SolicitudesMayoristas(b.getIdBulto(),destino,
									articulosHT,idEmpresa, true);
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
			            api.PutColaImpresion("BC"+b.getIdBulto(), etiq, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
			            
						if(!msjRemito.equals("")){
							session.setAttribute("menError", msjRemito);
							return forw (mapping, request, session, uLog, b, redirect);
						}
								
						try {
							idRemitos = idRemitos.substring(0,idRemitos.length()-1);
						} catch (Exception e) {idRemitos = "";}
						//REMITO DE ENCUENTRA
						ImpresionesPDF.imprimirTicketMovStock(idDepoCentral, Integer.parseInt(b.getDestino()), uLog.getNick(), obsTicket, 
								articulos, idRemitos,tipoComanda, uLog.getIdEquipo(),idEmpresa, 1);
						
			           
					}
				}
				
			}
			else{
				return mapping.findForward("log");
			}
			
			
		} catch (Exception e) {
			session.setAttribute("menError", "Sucedio un error queriendo verificar el bulto");
			System.out.println("");
		}
		
		return forw (mapping, request, session, uLog, b, redirect);
		

		
	
	}
	
	public ActionForward forw (ActionMapping mapping, HttpServletRequest request, HttpSession session, Usuario uLog,
			bulto b, String redirect){
			
			//buscar picking
			int idPick = (Integer) session.getAttribute("cerrarPick");
			String act = (String) session.getAttribute("actPick");
			session.removeAttribute("cerrarPick");
			 //LOGUEAMOS EVENTO
			try {
				uLog.registrarEvento(idPick, 0,112,0,true, session.getId(), "Remitiendo caja cerrada "+b.getIdBulto());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
            session.removeAttribute("remitirBulto");
            session.setAttribute("idPick", idPick);
            session.setAttribute("accion", act);
			return mapping.findForward(redirect);
		
	}
}
