package web.mantenimiento;

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
import beans.encuentra.SKUType;
import beans.encuentra.TipoSector;

public class _EncuentraAltaTipo extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		
		
		String isSKU=request.getParameter("esSKU");
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String idTipo = request.getParameter("idTipo"); // si es un alta el tipo es 999999
		String descripcion = request.getParameter("descripcion");
		
		if(isSKU==null){
			String altoStr = request.getParameter("alto");
			String anchoStr = request.getParameter("ancho");
			String profStr = request.getParameter("profundidad");

			//los cuelgo
			request.setAttribute("ancho", anchoStr);
			request.setAttribute("alto", altoStr);
			request.setAttribute("profundidad", profStr);
			request.setAttribute("descripcion", descripcion);
			request.setAttribute("idEdita", idTipo);


			int ancho = 0;
			int alto = 0;
			int profundidad = 0;
			int id;

			String fallo = "";

			//validamos
			try
			{
				fallo = anchoStr;
				ancho = Integer.parseInt(anchoStr);

				fallo = altoStr;
				alto = Integer.parseInt(altoStr);

				fallo = profStr;
				profundidad = Integer.parseInt(profStr);

				fallo = "Error en el ID";
				id = Integer.parseInt(idTipo);



			}
			catch (Exception e) 
			{
				request.setAttribute("menError", "Error en los Valores Numericos siguientes: "+fallo);

				return mapping.findForward("ok");

			}

			TipoSector ts = new TipoSector();

			ts.setIdTipo(id);
			ts.setAlto(alto);
			ts.setAncho(ancho);
			ts.setDescripcion(descripcion);
			ts.setProfundidad(profundidad);

			Logica.encuentraAltaUpdateTipoEstanteria(ts, idEmpresa);
			
			uLog.registrarEventoMin(session.getId(), "Alta/Update Tipo_Estanteria: "+ts.getIdTipo()+"|"+ts.getAlto()+"|"+ts.getAncho()+"|"+ts.getDescripcion()+"|"+ts.getProfundidad());

			request.setAttribute("idEdita", -86);
			return mapping.findForward("ok");
		}
		else
		{
			SKUType skuType=new SKUType();
			skuType.setId(Integer.parseInt(idTipo));
			skuType.setDescripcion(descripcion);
			boolean ok = Logica.encuentraAltaUpdateTipoSKU(skuType, idEmpresa);
			if(!ok)
			{
				request.setAttribute("menError", "Error al intentar modificar o dar de alta");
				uLog.registrarEventoMin(session.getId(), "Alta/Update Tipo_Estanteria: Error al intentar modificar o dar de alta");
			}
			request.setAttribute("idEdita", -86);
			return mapping.findForward("ok");
		}


	}

}
