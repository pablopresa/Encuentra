package web.almacen;

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

import beans.Usuario;

public class _EncuentraActualizaOjoPC extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		Logica Logica = new Logica();		
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String error ="ATENCION";
		String idOjo = (String) session.getAttribute("cUbiE");
		String actualizar = request.getParameter("update");
		idOjo = idOjo.toUpperCase();
		List<DataIDDescripcion> articulosCantReales = (List<DataIDDescripcion>) session.getAttribute("articulos");
		
		int deposito = Integer.parseInt(uLog.getDeposito());
		int existe = Logica.encuentraExisteUbica(idOjo,idEmpresa, deposito);
		
		if(existe==1)
		{
			
				
				if(actualizar.equals("1"))
				{
					Logica.encuentraUpdateOjos(idOjo, "",0, true,uLog.getNumero(),false,"UIN",idEmpresa);
					
					for (DataIDDescripcion d : articulosCantReales)
					{
						System.out.println(d.getId()+" "+d.getDescripcion());
						Logica.encuentraUpdateOjos(idOjo, d.getDescripcion(),d.getId(),false,uLog.getNumero(),false,"UIN",idEmpresa);
					}
					uLog.registrarEventoMin(session.getId(), "(UPDATE_OJO) Se actualizo ojo("+ idOjo +") desde un archivo");
				}
				else
				{
					for (DataIDDescripcion d : articulosCantReales)
					{
						System.out.println(d.getId()+" "+d.getDescripcion());
						Logica.encuentraUpdateOjos(idOjo, d.getDescripcion(),d.getId(),false,uLog.getNumero(),false,"UIN",idEmpresa);
					}
					
					uLog.registrarEventoMin(session.getId(), "(ADD_OJO) Se agrego mercaderia al ojo("+ idOjo +") desde un archivo");
					
				}
				
				
				
			
		}
		else
		{
			request.setAttribute("menError", "No existe la ubicacion en el sistema");
			uLog.registrarEventoMin(session.getId(), "Error al agregar o actualizar ojo desde archivo. No existe la ubicacion en el sistema");
		}
		
		request.setAttribute("menError", error);
		return mapping.findForward("ok");
	}

}
