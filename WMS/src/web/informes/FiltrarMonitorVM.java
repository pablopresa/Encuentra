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
import beans.reportes.MonitorVentaM;
import beans.reportes.MonitorVentaMArticulo;
import beans.reportes.MovsXArticulo;
import beans.reportes.PedidosProcesadosXOperario;
import beans.reportes.PedidosRetrasados;
import beans.reportes.Picking2;
import beans.reportes.ProductividadPicking;
import beans.reportes.StockEncuentraVisual;
import beans.reportes.inventDisponible;
import logica.Logica;
import logica.Utilidades;

public class FiltrarMonitorVM extends Action 
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


		String pedido = request.getParameter("idPedidoVM");
		
		

		Logica logica = new Logica();
		List<MonitorVentaMArticulo> lista = logica.darmonitorVtaMayoristaArticulos(pedido, idEmpresa);
		
		
		session.setAttribute("listaART", lista);
		

		return mapping.findForward("ok");
	}


}
