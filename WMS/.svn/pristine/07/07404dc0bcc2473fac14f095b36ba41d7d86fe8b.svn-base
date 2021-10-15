package web.almacen;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import logica.Logica;
import logica.Utilidades;
import main.utilidades.CallWSRFID;

public class _EncuentraValidateRealTime extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		Utilidades util = new Utilidades();
		Logica logica = new Logica();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		ServletContext context = request.getSession().getServletContext();
		Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
		
		try{
			String cod = request.getParameter("codigo");
			boolean valido = false;
			String descripcion="";
	
			try
			{
				cod = artBarra.get(cod.toUpperCase()).toUpperCase();
				String codComillas="'"+cod+"'"; 
				Map<String, String> articuloHash = logica.darDescripcionArticulos(codComillas, idEmpresa);
				descripcion = util.validarComillas(articuloHash.get(cod));
				valido = true;
			}
			catch(Exception e)
			{
				if(idEmpresa == 8) {
					CallWSRFID call = new CallWSRFID();
					String devuelve = call.darEan(cod);
					if(devuelve != null && !devuelve.equals("404") ) {
						valido = true;
					}
				}
			}
			
			
			
			List<String> ubicaciones = logica.darUbicacionesArticulo(cod, idEmpresa);
			
			StringBuilder ubicacionesString = new StringBuilder();
			
			if(!ubicaciones.isEmpty()) {
				int cont=0;
				for(String ubicacion : ubicaciones) {
					ubicacionesString.append(ubicacion);
					if(cont!=ubicaciones.size()-1)
						ubicacionesString.append(", ");
					cont++;
				}
			}

			if ( descripcion.equals("")) {
				descripcion = "SIN DESCRIPCION";
			}	else {
				descripcion = descripcion.replaceAll("\"","");
				descripcion = descripcion.replaceAll(",",";");
			}
			
			String existeDescripcion=
					 "     { "+
					 "        \"existe\":\""+valido+"\", "+
					 "        \"descripcion\":\""+descripcion+"\", "+
					 "        \"ubicaciones\":\""+ubicacionesString.toString()+"\" "+
					 "     } "
					 ;	
			System.out.println(existeDescripcion);
			session.setAttribute("html", existeDescripcion);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
			
	}
	

}
