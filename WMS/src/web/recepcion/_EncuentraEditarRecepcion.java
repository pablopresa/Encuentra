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

import beans.Usuario;
import dataTypes.DataDetalleRecepcion;
import dataTypes.DataIDDescDescripcion;;

public class _EncuentraEditarRecepcion extends Action {

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
		
		String articuloBase = request.getParameter("articuloBase");
		String modificacion=request.getParameter("talles2");
		List<DataDetalleRecepcion> recepciones=null;
		if(modificacion!=null){
			String[]talles=modificacion.split("/");
			if(talles.length>1){
				String idRecepcion=(String) session.getAttribute("idRecepcion");
				List<DataIDDescDescripcion>lista=new ArrayList<>();
				for(int i=1;i<talles.length;i++){
					DataIDDescDescripcion data = new DataIDDescDescripcion();
					data.setDescripcion(session.getAttribute("idEdita")+talles[i]);
					data.setId(Integer.parseInt(request.getParameter(talles[i])));
					lista.add(data);
				}
				Logica.ModificarRecepcion(Integer.parseInt(idRecepcion),lista,idEmpresa);
				//Logica.ActualizarCantidadRecepciones(Integer.parseInt(idRecepcion));
				if(idRecepcion!=null){
					recepciones=Logica.DetalleRecepcion(Integer.parseInt(idRecepcion),idEmpresa);
				}
				session.setAttribute("recepciones", recepciones);
			}
		}
	
		session.setAttribute("idEdita", articuloBase);
		return mapping.findForward("ok");
	
	
	}
}
