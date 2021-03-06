package web.recepcion;

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

import dataTypes.DataDescDescripcion;
import dataTypes.DataRecepcion;

import beans.Usuario;



public class _EncuentraSeleccionarRecepciones extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica log = new Logica();
		Utilidades util = new Utilidades();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
			
		if(uLog!=null)
		{
			List<DataRecepcion>recepciones=(List<DataRecepcion>) session.getAttribute("recepciones");
			try
			{
				int idR = Integer.parseInt(request.getParameter("idR"));
				for (DataRecepcion r : recepciones) 
				{
					if(r.getId()==idR)
					{
						session.setAttribute("recepcionID",""+idR);
						session.setAttribute("recepcionDesc",""+r.getProveedor().getDescripcion()+" - ("+r.getCantidadEsperada()+")");
						List<DataDescDescripcion> barrasRecepcionables =  log.darBarrasARecepcionar(idEmpresa, idR);
						session.setAttribute("barrasRecepcionables", barrasRecepcionables);
						return mapping.findForward("rec");
					}
				}
				return mapping.findForward("no");
			}
			catch (Exception e) 
			{
				return mapping.findForward("no");
			}
				
		}
		else
		{
			return mapping.findForward("LOGIN");
		}
			
			
	}
}
