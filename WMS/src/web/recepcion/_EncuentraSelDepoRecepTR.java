package web.recepcion;

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

import beans.Usuario;
import beans.encuentra.Remito;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;

public class _EncuentraSelDepoRecepTR extends Action 
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
		
		
		List<Remito> remitosIn = new ArrayList<>(); 
		int idDepoSel =  util.tryParse(request.getParameter("idDc"));
		
		
		if(idDepoSel!=0)
		{
			Call_WS_APIENCUENTRA call = new Call_WS_APIENCUENTRA(); 
			List<Remito> remitos =  call.albaranesListosLaIsla(idDepoSel+"",null,true, idEmpresa);
			
			List<DataIDDescDescripcion> depos = (List<DataIDDescDescripcion>) session.getAttribute("deposTran");
			
			DataIDDescDescripcion dTRsel =null;
			for (DataIDDescDescripcion d : depos) 
			{
				if(d.getId()==idDepoSel)
				{
					dTRsel=d;
				}
			}
			
			
			session.setAttribute("depoTR", dTRsel);
			session.setAttribute("remitosTRPen", remitos);
			session.setAttribute("remitosTRIn", remitosIn);
			return mapping.findForward("ok");	
		}
		else //es int el ID de Depo
		{
			request.setAttribute("menError", "no se reconoce el id de Deposito");
			return mapping.findForward("no");	
		}
			
		
		
			
	
	
	}

}
