package web.recepcion;

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
import dataTypes.DataArticuloCantBarra;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDIDDescripcion;
import dataTypes.DataRecepcion;
import logica.Logica;
import logica.Utilidades;


public class _EncuentraAsociarDocRecep extends Action 
{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		String ssId = session.getId();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		DataRecepcion recSel = (DataRecepcion) session.getAttribute("recSel");
		
		int idRece = 0;
		try
		{
			idRece = Integer.parseInt(request.getParameter("idRecepcion"));
		}
		catch (Exception e)
		{
			idRece = (Integer) session.getAttribute("idRecepcion");
		}
		
		String prov = (String) session.getAttribute("proveedorOC");
		String serie = request.getParameter("serie");
		
		int numDoc = Integer.parseInt(request.getParameter("factura"));
		
		
		List<DataArticuloCantBarra> articulosCount = (List<DataArticuloCantBarra>) session.getAttribute("artFacturables");
		List<DataIDIDDescripcion> datas = new ArrayList<>();
		
		for (DataArticuloCantBarra a : articulosCount) 
		{
			try
			{
				int cant = 0;
				int idOC =Integer.parseInt(a.getIdOC());
				String cantidad = request.getParameter(idOC+"_"+a.getArticulo());
				try
				{
					cant = Integer.parseInt(cantidad);
					
				}
				catch (Exception e) 
				{
					request.setAttribute("menError", "No se entiende la cantidad a recepcionar "+cant+" del articulo "+a.getArticulo());
					session.setAttribute("artFacturables", articulosCount);
					return mapping.findForward("ok");
				}
				
				if(cant>a.getCantidadContada())
				{
					request.setAttribute("menError", "No puede recepcionar "+cant+" del articulo "+a.getArticulo()+ " que se conto por "+a.getCantidadContada());
					session.setAttribute("artFacturables", articulosCount);
					return mapping.findForward("ok");
					
				}
				a.setCantidadContada(cant);
				
				
				String folio = a.getFolio();
				// id = idOC
				//Iid =  cantidad
				// descripcion = articulo
				// descripcionB = folio
				if(cant>0)
				{
					DataIDIDDescripcion dat = new DataIDIDDescripcion();
					dat.setId(idOC);
					dat.setIid(cant);
					dat.setDescripcion(a.getArticulo());
					dat.setDescripcionB(folio);
					
					
					datas.add(dat);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		if(!datas.isEmpty())
		{
			
			/**************************llamada a WS*****************************/
			int parcial = 0;
			Logica.confirmarRecepcion(idRece, serie, numDoc, parcial,idEmpresa,datas,9000,uLog.getNumero());
		
			//Cliente
		}
		else
		{
			request.setAttribute("menError", "Agrega las cantidades de la factura - no pueden ser todos 0");
			return mapping.findForward("ok");
		}
		
		int sigue = Integer.parseInt(request.getParameter("terminar"));
		
		
		
		if(sigue==0)
		{
			List<DataArticuloCantBarra> artFacturables = Logica.encuentraDarListaArticulosRecepcionados(idRece,idEmpresa);
			session.setAttribute("artFacturables", artFacturables);
			return mapping.findForward("ok");
		}
		else
		{
			return mapping.findForward("ini");
		}
		
		
		
		
		
		
		
	}

}
































