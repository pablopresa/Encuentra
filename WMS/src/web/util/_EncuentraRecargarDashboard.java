package web.util;

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
import beans.encuentra.DataPickingS;
import beans.encuentra.Tarjeta;
import dataTypes.DataIDDescripcion;



public class _EncuentraRecargarDashboard extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		try 
		{
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			List<DataIDDescripcion> grupos = Logica.permisosDeGrupo(uLog.getNumero(),idEmpresa);
			grupos.remove(0);	
			
			//Tarjetas
			String ids="";
			for(DataIDDescripcion g:grupos){
				ids += g.getId()+",";
			}
			ids=ids.substring(0,ids.length()-1);
			
			Logica.encuentraUpdateTarjetas(ids, idEmpresa);
			List<Tarjeta> tarjetas = Logica.darTarjetas(ids,idEmpresa);
			session.setAttribute("tarjetas", tarjetas);
			
			List<DataIDDescripcion> estadisticaVenta = Logica.darTarjetaEstadisticasVenta(idEmpresa);
			session.setAttribute("statsVenta", estadisticaVenta);

			String cantDias = Logica.darParametroEmpresa(idEmpresa, 30);
			if (cantDias==null||cantDias.equals(""))
				cantDias = "30";
			String pedidosEstadoMatriz = Logica.darTotalizadores(idEmpresa,"1,2,3,25,34,4,6,99",cantDias);
		    session.setAttribute("tablaMatrizEstados", pedidosEstadoMatriz);
			
			
			return mapping.findForward("ok");
		
		} 
		
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			return mapping.findForward("ok");

		}
	}
		
}
