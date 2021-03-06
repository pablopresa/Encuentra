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
import beans.reportes.ConteoOjo;
import beans.reportes.CumplimientoOrdenes;
import beans.reportes.ExpedicionMovimiento;
import beans.reportes.Filtro;
import beans.reportes.FrecuenciaUbicacionesProductos;
import beans.reportes.InventarioXUbicacion;
import beans.reportes.MonitorVentaM;
import beans.reportes.MovsXArticulo;
import beans.reportes.PedidosProcesadosXOperario;
import beans.reportes.PedidosRetrasados;
import beans.reportes.Picking2;
import beans.reportes.ProductividadPicking;
import beans.reportes.StockEncuentraVisual;
import beans.reportes.inventDisponible;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.Presentaciones;
import logica.Logica;
import logica.Utilidades;

public class FiltrarReportes extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception 
	{
		HttpSession session = request.getSession();
		Logica logica = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}


		String reporte = (String) session.getAttribute("reporteSel");


		switch (reporte) 
		{
		case "movsXArt":
		{
			String articulo = request.getParameter("articulo");
			String fechas = request.getParameter("fechas");
			String fechaI = "";
			String fechaF = "";

			if(fechas!=null && !fechas.equals("")){
					String []fechaIF = fechas.split(" - ");

					fechaI = fechaIF[0];
					fechaF = fechaIF[1];

					fechaI = fechaI+" 00:00:00";
					fechaF = fechaF+" 23:59:59";
			}
			List<MovsXArticulo> lista = logica.darListaMovsXArticulo(articulo,fechaI,fechaF,idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}
		case "conteos":
		{
			String fechas = request.getParameter("fechas");
			String req = request.getQueryString();
			String[] values = req.split("&");
			String estanteriasIn = util.buscarFiltro("estanteria", values);
			StringBuilder ojosInq= new StringBuilder();
			String ojosIn = util.buscarFiltro("ojo", values);
			if(!ojosIn.equals(""))
			{
				String[] ojosArr = ojosIn.split(",");

				boolean pri = true;
				List<Filtro> filtros = (List<Filtro>) session.getAttribute("filtros");
				for (Filtro f : filtros) {
					if (f.getName().equals("ojo")) {
						for (DataIDDescripcion v : f.getValues()) {
							for (int i = 0; i < ojosArr.length; i++) {
								if (ojosArr[i].equals(v.getId() + "")) {
									if (pri) {
										pri = false;
										ojosInq.append("'" + v.getDescripcion() + "'");
									} else {
										ojosInq.append(",'" + v.getDescripcion() + "'");
									}
								}
							}
						}
					}

				}
			}

			String fechaI = "";
			String fechaF = "";

			if (fechas != null && !fechas.equals("")) {
				String[] fechaIF = fechas.split(" - ");

				fechaI = fechaIF[0];
				fechaF = fechaIF[1];

				fechaI = fechaI + " 00:00:00";
				fechaF = fechaF + " 23:59:59";

			}
			session.setAttribute("fechasReporte", fechas);
			session.setAttribute("estanteriaReporte", estanteriasIn);
			session.setAttribute("ojosReporte", ojosInq.toString());
			
			List<ConteoOjo> lista = logica.darListaConteoOjo(fechaI,fechaF,idEmpresa,estanteriasIn,ojosInq.toString());
			session.setAttribute("corrige", "SI");
			session.setAttribute("lista", lista);
			break;
		}
		case "reposicionamiento":
		{
			String articulo = request.getParameter("articulo");

			String req = request.getQueryString();
			String[] values = req.split("&");

			List<ConsolidarSku> lista = logica.ReporteConsolidacion(articulo ,util.buscarFiltro("estanteria", values) ,util.buscarFiltro("marca", values) , util.buscarFiltro("genero", values),util.buscarFiltro("categoria", values),util.buscarFiltro("clases", values),idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}
		case "inventXUbi":
		{
			String articulo = request.getParameter("articulo");
			String agruparArt = request.getParameter("agruparArt");

			String req = request.getQueryString();
			String[] values = req.split("&");

			List<InventarioXUbicacion> lista = logica.darListaInventXUbi(articulo ,util.buscarFiltro("estanteria", values) ,util.buscarFiltro("marca", values) , util.buscarFiltro("genero", values),util.buscarFiltro("categoria", values),util.buscarFiltro("clases", values),agruparArt,idEmpresa,uLog.getDeposito());
			session.setAttribute("agruparArt", agruparArt);
			session.setAttribute("lista", lista);
			break;
		}
		case "inventDisponible":
		{
			String articulo = request.getParameter("articulo");
			String deposito = request.getParameter("deposito");
			String estanteria = request.getParameter("estanteria");
			String marca = request.getParameter("marca");
			String genero = request.getParameter("genero");
			String categoria = request.getParameter("categoria");
			String clases = request.getParameter("clases");

			String req = request.getQueryString();
			String[] values = req.split("&");

			List<inventDisponible> lista = logica.darListainventDisponible(articulo,util.buscarFiltro(deposito, values), util.buscarFiltro(estanteria, values) ,util.buscarFiltro(marca, values) , util.buscarFiltro(genero, values),util.buscarFiltro(categoria, values),util.buscarFiltro(clases, values),idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}

		case "productividadPicking":
		{
//			String deposito = request.getParameter("deposito");
//			String usuario = request.getParameter("usuario");
			String fechasP = request.getParameter("fechasPickeada");
			String fechaPI = "";
			String fechaPF = "";

			if(fechasP!=null && !fechasP.equals("")){
					String []fechaPIF = fechasP.split(" - ");

					fechaPI = fechaPIF[0];
					fechaPF = fechaPIF[1];

					fechaPI = fechaPI+" 00:00:00";
					fechaPF = fechaPF+" 23:59:59";
			}

//			String categoria = request.getParameter("categoria");
//			String clases = request.getParameter("clase");

			String req = request.getQueryString();
			String[] values = req.split("&");
			List<ProductividadPicking> lista = logica.darListaReporteProductividadPicking(
					util.buscarFiltro("deposito", values), 
					util.buscarFiltro("usuario", values), fechaPI, fechaPF,
					util.buscarFiltro("categoria", values),
					util.buscarFiltro("clases", values),
					idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}

		case "picking":
		{
			String fechas = request.getParameter("fechas");
			String fechaI = "";
			String fechaF = "";

			if (fechas != null && !fechas.equals("")) {
				String[] fechaIF = fechas.split(" - ");

				fechaI = fechaIF[0];
				fechaF = fechaIF[1];

				fechaI = fechaI + " 00:00:00";
				fechaF = fechaF + " 23:59:59";

			}

			String req = request.getQueryString();
			String[] values = req.split("&");
			/*List<Picking> lista = logica.darListaReportePicking(util.buscarFiltro("pickingNumber", values),
					util.buscarFiltro("deposito", values), 
					util.buscarFiltro("usuario", values), fechaI, fechaF, util.buscarFiltro("marca", values),
					util.buscarFiltro("genero", values),
					util.buscarFiltro("categoria", values),
					util.buscarFiltro("clases", values),
					idEmpresa);
			*/
			List<Picking2> lista = logica.darListaReportePicking2(util.buscarFiltro("pickingNumber", values),
					util.buscarFiltro("deposito", values), 
					util.buscarFiltro("usuario", values), fechaI, fechaF, util.buscarFiltro("marca", values),
					util.buscarFiltro("genero", values),
					util.buscarFiltro("categoria", values),
					util.buscarFiltro("clases", values),
					idEmpresa,Integer.parseInt(uLog.getDeposito()));
			session.setAttribute("lista", lista);
			break;
		}
		case "bultos":
		{
//			String depositoO = request.getParameter("depositoO");
//			String depositoD = request.getParameter("depositoD");
			String fechas = request.getParameter("fechas");
			String fechaI = "";
			String fechaF = "";
			String agruparFech = request.getParameter("agruparFech");
			String agruparDest = request.getParameter("agruparDest");

			if (fechas != null && !fechas.equals("")) {
				String[] fechaIF = fechas.split(" - ");

				fechaI = fechaIF[0];
				fechaF = fechaIF[1];

				fechaI = fechaI + " 00:00:00";
				fechaF = fechaF + " 23:59:59";

			}

			String req = request.getQueryString();
			String[] values = req.split("&");
			List<BultosRangoFechaDestino> lista = logica.darListaBultosRangoDestino(util.buscarFiltro("depositoO", values), 
					util.buscarFiltro("depositoD", values), fechaI, fechaF,
					agruparFech, agruparDest, idEmpresa);
			session.setAttribute("agruparFech", agruparFech);
			session.setAttribute("agruparDest", agruparDest);
			session.setAttribute("lista", lista);
			break;
		}
		
		
		
		case "monitorVtaMayorista":
		{
			
//			String depositoD = request.getParameter("depositoD");
//			String pedido = request.getParameter("pedido");
			String fechas = request.getParameter("fechas");
			String fechaI = "";
			String fechaF = "";
			

			if(fechas!=null && !fechas.equals("")){
					String []fechaIF = fechas.split(" - ");

					fechaI = fechaIF[0];
					fechaF = fechaIF[1];

					fechaI = fechaI+" 00:00:00";
					fechaF = fechaF+" 23:59:59";
			}

			String req = request.getQueryString();
			String[] values = req.split("&");
			List<MonitorVentaM> lista = logica.darmonitorVtaMayorista(util.buscarFiltro("depositoD", values), fechaI, fechaF,  util.buscarFiltro("pedido", values), idEmpresa);
			
			
			session.setAttribute("lista", lista);
			break;
		}
		
		case "expedicionMovimientos":
		{
//			String depositoO = request.getParameter("depositoO");
//			String depositoD = request.getParameter("depositoD");
			String fechas = request.getParameter("fechas");
			String fechaI = "";
			String fechaF = "";

			if(fechas!=null && !fechas.equals("")){
					String []fechaIF = fechas.split(" - ");

					fechaI = fechaIF[0];
					fechaF = fechaIF[1];

					fechaI = fechaI+" 00:00:00";
					fechaF = fechaF+" 23:59:59";
			}

			String req = request.getQueryString();
			String[] values = req.split("&");
			List<ExpedicionMovimiento> lista = logica.darListaExpedicionMovimiento(util.buscarFiltro("depositoO", values), 
					util.buscarFiltro("depositoD", values), fechaI, fechaF,
					idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}
		
		case "pedidosProcesadosXOperario":
		{
//			String usuario = request.getParameter("usuario");
			String fechasP = request.getParameter("fechas");
			String fechaPI = "";
			String fechaPF = "";

			if(fechasP!=null && !fechasP.equals("")){
					String []fechaPIF = fechasP.split(" - ");

					fechaPI = fechaPIF[0];
					fechaPF = fechaPIF[1];

					fechaPI = fechaPI+" 00:00:00";
					fechaPF = fechaPF+" 23:59:59";
			}

			String req = request.getQueryString();
			String[] values = req.split("&");
			List<PedidosProcesadosXOperario> lista = logica.darListaPedidosProcesadosXOperario(
					util.buscarFiltro("usuario", values), fechaPI, fechaPF, idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}
		
		case "pedidosRetrasados":
		{
			String fechasP = request.getParameter("fechas");
			String fechaPI = "";
			String fechaPF = "";

			if(fechasP!=null && !fechasP.equals("")){
					String []fechaPIF = fechasP.split(" - ");

					fechaPI = fechaPIF[0];
					fechaPF = fechaPIF[1];

					fechaPI = fechaPI+" 00:00:00";
					fechaPF = fechaPF+" 23:59:59";

			}

			String req = request.getQueryString();
//			String[] values = req.split("&");
			List<PedidosRetrasados> lista = logica.darListaPedidosRetrasados(fechaPI, fechaPF, idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}
		
		case "ajustesDiferencias":
		{
//			String deposito = request.getParameter("deposito");
			String articulo = request.getParameter("articulo");
			String fechas = request.getParameter("fechas");
			String fechaI = "";
			String fechaF = "";

			if(fechas!=null && !fechas.equals("")){
					String []fechaIF = fechas.split(" - ");

					fechaI = fechaIF[0];
					fechaF = fechaIF[1];

					fechaI = fechaI+" 00:00:00";
					fechaF = fechaF+" 23:59:59";
			}

//			String marca = request.getParameter("marca");
//			String genero = request.getParameter("genero");
//			String categoria = request.getParameter("categoria");
//			String clases = request.getParameter("clase");
			
			String req = request.getQueryString();
			String[] values = req.split("&");
			List<AjustesDiferencias> lista = logica.darListaAjustesDiferencias(util.buscarFiltro("deposito", values), 
					fechaI, fechaF, articulo, util.buscarFiltro("marca", values),
					util.buscarFiltro("genero", values),
					util.buscarFiltro("categoria", values),
					util.buscarFiltro("clases", values), idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}
		
		case "frecuenciasUbicacionesArticulos":
		{

			String fechas = request.getParameter("fechas");
			String fechaI = "";
			String fechaF = "";

			if(fechas!=null && !fechas.equals("")){
					String []fechaIF = fechas.split(" - ");

					fechaI = fechaIF[0];
					fechaF = fechaIF[1];

					fechaI = fechaI+" 00:00:00";
					fechaF = fechaF+" 23:59:59";
			}

			String agruparArt = request.getParameter("agruparArt");
			String articulo = request.getParameter("articulo");
//			String marca = request.getParameter("marca");
//			String genero = request.getParameter("genero");
//			String categoria = request.getParameter("categoria");
//			String clases = request.getParameter("clase");
			
			String req = request.getQueryString();
			String[] values = req.split("&");
			List<FrecuenciaUbicacionesProductos> lista = logica.darListaFrecuenciasUbicacionesArticulos( 
					articulo, fechaI, fechaF, agruparArt,  util.buscarFiltro("marca", values),
					util.buscarFiltro("genero", values),
					util.buscarFiltro("categoria", values),
					util.buscarFiltro("clases", values), idEmpresa);
			session.setAttribute("agruparArt", agruparArt);
			session.setAttribute("lista", lista);
			break;
		}
		//
		case "cumplimientoOrdenes":
		{
//			String deposito = request.getParameter("deposito");
			String fechas = request.getParameter("fechas");
			String fechaI = "";
			String fechaF = "";

			if(fechas!=null && !fechas.equals("")){
					String []fechaIF = fechas.split(" - ");

					fechaI = fechaIF[0];
					fechaF = fechaIF[1];

					fechaI = fechaI+" 00:00:00";
					fechaF = fechaF+" 23:59:59";
			}

//			String marca = request.getParameter("marca");
//			String genero = request.getParameter("genero");
//			String categoria = request.getParameter("categoria");
//			String clases = request.getParameter("clase");
			
			String req = request.getQueryString();
			String[] values = req.split("&");
			List<CumplimientoOrdenes> lista = logica.darListaCumplimientoOrdenes(util.buscarFiltro("deposito", values), 
					fechaI, fechaF, util.buscarFiltro("marca", values),
					util.buscarFiltro("genero", values),
					util.buscarFiltro("categoria", values),
					util.buscarFiltro("clases", values), idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}
		case "stockEncuentraVisual":
		{

			String deposito = request.getParameter("deposito");
			String articulo = request.getParameter("articulo");
			
			List<StockEncuentraVisual> lista = logica.darListaStockEncuentraVisual(deposito, articulo, idEmpresa);
			session.setAttribute("lista", lista);
			break;
		}
		default:
			break;
		}

		return mapping.findForward("ok");
	}


}
