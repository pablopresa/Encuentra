package web.util;

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

import dataTypes.DataIDDescripcion;

import beans.ArticuloReposicion;
import beans.Usuario;

public class _EncuentraLoadLineasRepo extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Usuario ulog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(ulog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String depo = request.getParameter("deposito");
		String tabla = request.getParameter("tabla");
		int sucursal = Integer.parseInt(depo);
		int origen = Integer.parseInt(ulog.getDeposito());
		List<DataIDDescripcion> articulosCantidad = new ArrayList<>();
		
		
		String[]lineas = tabla.split("\r\n");
		for (int i = 0; i < lineas.length; i++) 
		{
			try
			{
				String linea = lineas[i];
				String[]columnas = linea.split("\t");
				
				String cantidad = columnas[4];
				String articulo = columnas[2];
				int cant = 0;
				if(cantidad.contains(","))
				{
					cant = Integer.parseInt(cantidad.split(",")[0]);
				}
				else
				{
					
					cant = (int)Double.parseDouble(cantidad);
				}
				DataIDDescripcion dat = new DataIDDescripcion(cant,articulo );
				dat.setIdLong(new Long(0));
				dat.setIdB(0);
				articulosCantidad.add(dat);
			}
			catch(Exception e)
			{
				System.out.println("error en linea  "+lineas[i]);
			}
			
		}
		//le aviso que son lineas manuales
		boolean manual = true;
		List <ArticuloReposicion> artsRep = Logica.darArticuloRepoFromLoadForus(articulosCantidad,sucursal,manual,idEmpresa,origen,1,false);
		
		
		
		request.setAttribute("URL", "InitListaArtPendientesRepo.do?central=1&mayorista=0&ecommerce=0");
		return mapping.findForward("ok");
		
		
		
		
	
	
	}
	
	
	
	
	
}








































