package web.informes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.ConsolidarSku;
import beans.Usuario;
import beans.reportes.AjustesDiferencias;
import beans.reportes.BultosRangoFechaDestino;
import beans.reportes.CumplimientoOrdenes;
import beans.reportes.ExpedicionMovimiento;
import beans.reportes.FrecuenciaUbicacionesProductos;
import beans.reportes.InventarioXUbicacion;
import beans.reportes.MovsXArticulo;
import beans.reportes.PedidosProcesadosXOperario;
import beans.reportes.PedidosRetrasados;
import beans.reportes.Picking2;
import beans.reportes.ProductividadPicking;
import beans.reportes.ReporTEST;
import beans.reportes.StockEncuentraVisual;
import beans.reportes.inventDisponible;
import logica.Logica;
import logica.Utilidades;

public class FiltrarTEST extends Action 
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

		int desde = util.tryParse(request.getParameter("vDesde"));
		int hasta = util.tryParse(request.getParameter("vHasta"));
		
		if(desde==-0 || hasta==0)
		{
			request.setAttribute("menError", "los rangos deben ser superiores a 0");
			return mapping.findForward("ok");
		}
		
		
		List<ReporTEST> retorno = Logica.darReporteTEST(desde,hasta); 
		
		
		session.setAttribute("tabla", retorno);
		


		return mapping.findForward("ok");
	}


}
