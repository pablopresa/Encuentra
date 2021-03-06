package web.recepcion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import dataTypes.DataIDDescripcion;
import dataTypes.DataRecepcion;
import logica.Logica;
import logica.Utilidades;
import persistencia.MSSQL;


public class _EncuentraRecepcionesSFborrar extends Action {
	
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
		
		try {
			System.out.println("");
			String id = request.getParameter("idRecepcion");
			/*List<DataIDDescripcion> docs = Logica.darRecepcionesABorrar(id,idEmpresa);
			String query="";
			for(DataIDDescripcion d: docs){
				query += "update opor set DocStatus='C' where DocEntry="+d.getId();
			}
			//MSSQL.persistirAnysys(query);*/
			Logica.persistir("update articulosarecepcionar set cantidadFacturada=(pendientesFacturar*-1) where idEmpresa="+idEmpresa+" AND iddetalle in (select iddetalle from detallerecepcion where idEmpresa ="+idEmpresa+" and idrecepcion="+id+")");
			
			List<DataRecepcion>recepciones=Logica.darRecepcionessf(idEmpresa);
			
			session.setAttribute("sf", "1");
			session.setAttribute("recepciones", recepciones);
			request.setAttribute("sf", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
		
	}

}



























