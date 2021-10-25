package web.recepcion;

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

import beans.Usuario;
import beans.encuentra.Remito;
import beans.encuentra.RemitoLinea;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class _EncuentraDarRemitos extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		Logica logica = new  Logica();
		HttpSession session = request.getSession();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		
		
		int transitos =  util.tryParse(request.getParameter("TR"));
		
		List<Remito> remitosIn = new ArrayList<>();
		if(transitos==0)
		{
			Hashtable<Integer,DataIDDescripcion> depositosWEB = util.darDeposWEB(idEmpresa);
			boolean esEcommerce = (depositosWEB.containsKey(Integer.parseInt(uLog.getDeposito()))) ? true : false;
			
			List<Remito> remitos = new ArrayList<>();
			Call_WS_APIENCUENTRA call = new Call_WS_APIENCUENTRA();
			
			remitos = call.albaranesListosLaIsla(uLog.getDeposito(),null,false, idEmpresa, esEcommerce);
			
			Hashtable<Integer, DataIDDescripcion> aliasDepositos = logica.darAliasDepositos(idEmpresa);
			for(Remito r: remitos) {
				if(aliasDepositos.get(r.getIdOrigen())!=null) {
					r.setDescOrigen(aliasDepositos.get(r.getIdOrigen()).getDescripcion());
				}
				else {
					r.setDescOrigen(r.getIdOrigen()+"");
				}				
			}
			
			session.setAttribute("remitosPen", remitos);
			session.setAttribute("remitosIn", remitosIn);
			return mapping.findForward("ok");	
		}
		else //es transito
		{
			List<DataIDDescDescripcion> depositos = logica.darDeposMovPend(idEmpresa);
			session.setAttribute("deposTran", depositos);
			return mapping.findForward("TR");	
		}
			
		
		
			
	
	
	}

}
