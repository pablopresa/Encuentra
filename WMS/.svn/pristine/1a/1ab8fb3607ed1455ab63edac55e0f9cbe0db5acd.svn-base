package web.recepcion;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.ArticuloLineaReposicion;
import beans.Usuario;
import beans.bulto;
import beans.encuentra.IPrint;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import logica.Logica;
import logica.Utilidades;


public class _EncuentraPrintPackingRecepcionII extends Action 
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
		
		String verEnPDF = request.getParameter("verEnPDF");
		
		String prov = request.getParameter("proveedorSel");
		session.setAttribute("proveedorOC", prov);
		
		List<VORecepcionSinOrden> listaPacking = new LinkedList<VORecepcionSinOrden>();
		listaPacking = (List<VORecepcionSinOrden>) session.getAttribute("listaPacking");
		
		int idRece = 0;
		try
		{
			idRece = Integer.parseInt(request.getParameter("idRecepcion"));
		}
		catch (Exception e)
		{
			idRece = (Integer) session.getAttribute("idRecepcion");
		}
		
		StringBuilder seleccionadosToPrint = new StringBuilder();
		
		for (VORecepcionSinOrden ar : listaPacking) 
		{
			// PREGUNTO POR LOS CHECKS 
			String articulo = ar.getIdArticulo();
			String param = request.getParameter(articulo);
			if(param!=null && param.equals("on"))
			{					
				seleccionadosToPrint.append("'"+ar.getIdArticulo()+"',");
			}
		}
		//generar bultos para imprimir
		List<bulto> bultos = Logica.darBultosToPrintRecepcion(idRece, seleccionadosToPrint, idEmpresa);
		
		IPrint ip = new IPrint();
		Hashtable<String, Integer> totalesPorMark = new Hashtable<>();
		
		//obtengo los totales por mark
		for(VORecepcionSinOrden linea: listaPacking)
		{
			totalesPorMark.put(linea.getIdArticulo(), linea.getCantidadBultos());
		}
		
		String path = ip.ImprimirEtiquetasBultosRSinOC(bultos, "Recepcion_"+idRece+"_"+idEmpresa, totalesPorMark);
		
		if(!verEnPDF.equals("PDF"))
		{
			//llamando a api para imprimir etiquetas
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			Random r = new Random();
			int ran =r.nextInt((999 - 1) + 1) + 1;
			api.PutColaImpresion(ran+"Recepcion_"+idRece, path, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
		}
		
		request.setAttribute("menError", "Clic aquí para ver PDF");
		request.setAttribute("urlPDF", path);
		
		session.setAttribute("idRecepcion", idRece);
		session.setAttribute("listaPacking", listaPacking);
			
		return mapping.findForward("ok");
		
	}

}