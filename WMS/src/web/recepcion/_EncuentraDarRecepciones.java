package web.recepcion;

import java.util.ArrayList;
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
import dataTypes.ArticuloPedido;
import dataTypes.DataDescDescripcion;
import dataTypes.DataDistribucion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataRecepcion;

import beans.ArticuloLineaReposicion;
import beans.OrdenAlmacen;
import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.Tarea;



public class _EncuentraDarRecepciones extends Action 
{

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
			
			if(uLog!=null)
			{
				List<DataRecepcion>recepciones=Logica.getRecepciones(idEmpresa);
				
				session.setAttribute("recepciones", recepciones);
				return mapping.findForward("rec");
			}
			else
			{
				return mapping.findForward("log");
			}
			
			
	}
}
