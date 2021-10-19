package web.expedicion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.FactoryLogica;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.DepositoAdmin;
import beans.encuentra.Ruta;
import dataTypes.DataIDDescripcion;

public class DarRutasABM extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		
		
		
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String para = request.getParameter("paraR");
		
		
		if(para==null)
		{
			para="0";
			
		}
		
		
		
		String descripcion = request.getParameter("descripcion");
		int id= Integer.parseInt(request.getParameter("id"));
		
		Ruta rutaN = new Ruta(id, descripcion);
		boolean borrar = false;
		if(para.equals("-1"))
		{
			borrar=true;
		}else if(para.equals("5") || para.equals("6")) {}
		
		rutaN.ABM(idEmpresa,borrar);
		
		List<Ruta> rutas = null;
		
		
		FactoryLogica  Logica = new FactoryLogica();
		Logica Log = new Logica();
		List<DataIDDescripcion> depositosAll = Log.encuentraDarDepositos(idEmpresa);
		rutas = Logica.darRutas(idEmpresa, 0);
		
		
		
		
		
		
		
		
		
		session.setAttribute("paraR", "0");
		session.setAttribute("rutas", rutas);
		session.setAttribute("deposAll", depositosAll);
		
		return mapping.findForward("ok");
	
	
	}

}
