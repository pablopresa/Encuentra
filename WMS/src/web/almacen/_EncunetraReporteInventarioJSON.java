package web.almacen;

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

import beans.DatosInventario;
import beans.Usuario;

public class _EncunetraReporteInventarioJSON extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Usuario us = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(us);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		List<DatosInventario> datos = new ArrayList<>();
		
		try{		
			
			datos = Logica.DatosInventario(us.getDeposito(), idEmpresa);		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//{"data":[{"test":101},{"test":2},{"test":3},{"test":4}]}
		
		String jsonData = "{\"data\":[";
		String bodyDta="";
		for (DatosInventario d : datos) 
		{
			bodyDta+="{\"Estanteria\":\""+d.getEstanteria()+"\",\"Cod_Ubicacion\":\""+d.getIdOjo()+"\", \"Estante\":\""+d.getModulo()+"\",\"Modulo\":\""+d.getEstante()+"\",\"Cantidad\":\""+d.getCantidad()+"\",\"Actualizado\":\""+d.getFecha()+"\"},";
		}
		
		if(bodyDta.length()>0)
		{
			bodyDta = bodyDta.substring(0, bodyDta.length()-1);
		}
		
		jsonData = jsonData+bodyDta+"]}";
		
		System.out.println(jsonData);
		
		request.setAttribute("json", jsonData);
		
		session.setAttribute("inventario", datos);
		return mapping.findForward("ok");
			
	}
	

}
