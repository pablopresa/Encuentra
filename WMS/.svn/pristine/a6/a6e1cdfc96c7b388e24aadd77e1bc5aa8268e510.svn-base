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

import dataTypes.DataBooleanDesc;
import dataTypes.DataRecepcion;

import beans.Usuario;


public class _EncuentraGuardarRecepcion extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		String idRecepcion=request.getParameter("idRecepcion");
		int parcial;
		if(idRecepcion!=null)
		{
			String serie=request.getParameter("serie");
			String remito=request.getParameter("remito");
			try
			{
				parcial=Integer.parseInt(request.getParameter("parcial"));
			}
			catch(Exception e)
			{
				parcial=0;
			}
			
			try
			{
				int nroRemito=Integer.parseInt(remito);
				DataBooleanDesc respuesta=null;
				if(serie!=null)
				{
					respuesta=Logica.confirmarRecepcion(Integer.parseInt(idRecepcion),serie,nroRemito,parcial,idEmpresa,null,9000,uLog.getNumero());
					if((respuesta==null) || (!respuesta.isBool()))
					{
						if(respuesta!=null)
						{
							session.setAttribute("mensajeE", respuesta.getDescricpion());
						}
						else
						{
							session.setAttribute("mensajeE","Error al persistir la recepción");
						}
					}
				}
				else
				{
					session.setAttribute("mensajeE","Escriba serie de remito");
				}
			}
			catch(Exception e)
			{
				session.setAttribute("mensajeE","Escriba Nº de remito");
			}
		}
		else
		{
			session.setAttribute("mensajeE",null);
		}
	
		List<DataRecepcion>recepciones=Logica.darRecepcionesCulminadas(idEmpresa);
		session.setAttribute("recepciones", recepciones);
		
		return mapping.findForward("ok");
		
	}

}