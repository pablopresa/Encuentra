package web.picking;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import logica.Logica;
import logica.Utilidades;
import models.PickingBulto;
import beans.elementoPicking;

public class PickingPackage extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Usuario uLog = null; 
		int idPicking = 0;
		
		try {
			uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}			
			
			int idDepoWEB = util.darParametroEmpresaINT(idEmpresa,5);
			boolean verific = util.darParametroEmpresaBool(idEmpresa,1);
			
			PickingBulto pickingBulto = (PickingBulto) session.getAttribute("modeloPickingBulto");
			boolean persistio = true;
			idPicking = pickingBulto.getIdPicking();
			
			String articulo = request.getParameter("idArticulo");
			int qty = 1;
			try {
				qty = Integer.parseInt(request.getParameter("qty"));
			} catch (Exception e) {
				session.setAttribute("menError", "Debe ingresar una cantidad valida");
				return mapping.findForward("pick");
			}
			
			if(articulo == null || articulo.equals("")) {
				session.setAttribute("menError", "No se reconocio ningun articulo");
				return mapping.findForward("pick");
			}
			else{
				if(pickingBulto.getArticulo_lineasRepo().get(articulo)!= null) {
					for (DataLineaRepo articuloEnBulto : pickingBulto.getArticulo_lineasRepo().get(articulo)) {
						if(articuloEnBulto.getSolicitada() >= articuloEnBulto.getPicked() + qty) {
							uLog.registrarEvento(pickingBulto.getIdPicking(), qty,107,0,true, session.getId(), " articulo pickeado "+articuloEnBulto.getIdArticulo()+" en bulto "+pickingBulto.getIdBulto());
							
							persistio = Logica.encuentraUpdateAvancePicking(pickingBulto.getIdPicking(),articuloEnBulto.getIdArticulo(), qty,articuloEnBulto.getIdDepOrigen(),
									articuloEnBulto.getIdDepDestino(),verific,articuloEnBulto.getPedido(),articuloEnBulto.getDocumento(),idEmpresa,idDepoWEB,articuloEnBulto.getAutoVerificacion());
							
							if(!persistio) {
								session.setAttribute("menError", "No se pudo marcar como pickeado este articulo");
								return mapping.findForward("pick");
							}
							else {
								//sumo uno a dataLineaRepo
								int pickedRepo = articuloEnBulto.getPicked() + qty;
								articuloEnBulto.setPicked(pickedRepo);								
								
								//sumo uno a la coleccion de articulos a mostrar
								int picked = pickingBulto.getElementos().get(articulo).getPick() + qty;
								pickingBulto.getElementos().get(articulo).setPick(picked);
								
								//refresoc info para la UI
								pickingBulto.refreshListaElementos();
								
								//sumo uno a cantidad contada
								int count = pickingBulto.getCount() + qty;
								pickingBulto.setCount(count);
							}
							break;
						}
						else {
							session.setAttribute("menError", "Este codigo de barras se reconoce para un articulo de los que debe verificar. Si no lo ha verificado, ingrese una cantidad menor.-");
						}
					}
				}
				else {
					session.setAttribute("menError", "Este codigo de barras no se reconoce para ningun articulo de los que debe verificar.-");
				}
							
				session.setAttribute("modeloPickingBulto", pickingBulto);				
			}
			
			
		} catch (Exception e) {
			uLog.registrarEvento(idPicking, 0,107,0,true, session.getId(), e.getMessage());
			session.setAttribute("menError", "Sucedio un error, comuniquese con soporte");
		}
				
		return mapping.findForward("pick");
	}
	
}
