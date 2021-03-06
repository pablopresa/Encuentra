package web.almacen;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import logica.Logica;
import logica.Utilidades;

public class ExplotarBulto extends Action 
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
		
		String bulto = request.getParameter("bulto");		
		
		List<bultoContenido> detalle;
		try 
		{
			bulto b = Logica.BuscarBulto(bulto, idEmpresa);
			 if(b!=null && b.getIdBulto()!=null)
			 {
				detalle = new ArrayList<>(b.getContenido().values());
				
				String detalleMB = "";
				if(detalle.isEmpty())
				{
					request.setAttribute("menError", "El Bulto esta vacio, no se puede abrir");
					return mapping.findForward("no");
				}
				
				int cantidadReservada = 0;
				
				for (bultoContenido bu : detalle) 
				{
					detalleMB+=bu.getIdArticulo()+":"+bu.getCantidad()+",";
					cantidadReservada += bu.getCantidadReservada();
				}
				
				if(cantidadReservada > 0) {
					request.setAttribute("menError", "El Bulto contiene art�culos reservados, no se puede desarmar");
					return mapping.findForward("no");
				}
				
				detalleMB = detalleMB.substring(0, detalleMB.length()-1);
				request.setAttribute("detalleMB", detalleMB);
				request.setAttribute("bulto", bulto);
				session.setAttribute("desarmar_b", b);
			 }
			 else
			 {
				 request.setAttribute("menError", "El ID de bulto que ingreso no es correcto");
				 return mapping.findForward("no");
			 }
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
		
		
	
	}
		

}
