package web.almacen;

import java.util.ArrayList;
import java.util.Enumeration;

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

import beans.reportes.ConteoOjo;
import dataTypes.DataIDDescripcion;

import logica.Logica;
import logica.Utilidades;


public class UpdateCountOjos extends Action //CLASE QUE EJECUTA LA ACCION
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
		
		
		List<ConteoOjo> lista = (List<ConteoOjo>) session.getAttribute("lista");
		
		Hashtable<String, List<DataIDDescripcion>> updates = new Hashtable<String, List<DataIDDescripcion>>();
		for (ConteoOjo co : lista) 
		{
			if(updates.containsKey(co.getIdOjo()))
			{
				updates.get(co.getIdOjo()).add(new DataIDDescripcion(co.getCantidad_contada(),co.getIdArticulo()));
			}
			else
			{ 
				List<DataIDDescripcion> lnew = new ArrayList<>();
				lnew.add(new DataIDDescripcion(co.getCantidad_contada(),co.getIdArticulo()));
				updates.put(co.getIdOjo(), lnew);
			}
			
		}
		
		
		 Enumeration<String> e = updates.keys();
		 List<String> queries = new ArrayList<>();
		 String error = "Actualizando articulos en ojo";
		 String ojosIn = "";
		 boolean pri = true;
		 while (e.hasMoreElements()) 
	     {
			 
			String idOjo = e.nextElement();
			if(pri)
			{
				pri = false; 
				ojosIn +="'"+idOjo+"'";
			}
			else
			{
				ojosIn +=",'"+idOjo+"'";
			}
			
			error+=" "+idOjo;
			List<DataIDDescripcion> articulosCantReales = updates.get(idOjo);
			
			
			queries.addAll(Logica.query_encuentraUpdateOjos(idOjo, "",0, true,uLog.getNumero(),false,"UIN",idEmpresa));
			
			
					
			for (DataIDDescripcion d : articulosCantReales)
			{
				queries.addAll(Logica.query_encuentraUpdateOjos(idOjo, d.getDescripcion(), d.getId(), false,uLog.getNumero(),uLog.isInventario(),"UIN",idEmpresa));
			}
	     }
		
		Logica.getEper().persistirL(queries);
		uLog.registrarEventoMin(session.getId(), "(UPDATE_OJO)"+error);
		
		String fechas = (String) session.getAttribute("fechasReporte");
		String estanterias = (String) session.getAttribute("estanteriaReporte");
		String ojos  = (String) session.getAttribute("ojosReporte");
		String fechaI = "";
		String fechaF = "";
		
		
		if(fechas!=null)
		{
			if (!fechas.equals(""))
			{
				String []fechaIF = fechas.split(" - ");

				fechaI = fechaIF[0];
				fechaF = fechaIF[1];

				fechaI = fechaI+" 00:00:00";
				fechaF = fechaF+" 23:59:59";
			}

		}
		session.setAttribute("fechasReporte", fechas);
		List<ConteoOjo> listaN = Logica.darListaConteoOjo(fechaI,fechaF,idEmpresa,estanterias,ojos);
		session.setAttribute("lista", listaN);
		
		Logica.getEper().persistir("DELETE FROM ojostienenarticulos_conteo WHERE idOjo IN ("+ojosIn+")");
		session.setAttribute("corrige", "NO");
		
		request.setAttribute("menError", error);
		return mapping.findForward("ok");
		
			
		
	}

}
