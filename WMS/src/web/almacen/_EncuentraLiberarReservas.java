package web.almacen;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
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
import beans.encuentra.DataOjoArticuloCantidad;

public class _EncuentraLiberarReservas extends Action 
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
		//String idOjo = request.getParameter("idOjo");
		//idOjo = idOjo.toUpperCase();
		String ojoReservado= "";
		ojoReservado = request.getParameter("ojoReservado");
		ojoReservado = ojoReservado.toUpperCase();
 
		int deposito = Integer.parseInt(uLog.getDeposito());
		int existe = Logica.encuentraExisteUbica(ojoReservado, idEmpresa, deposito);
		
		if(existe==1)
		{
			List<DataOjoArticuloCantidad> articulosCantidad = new ArrayList<>();
			articulosCantidad = Logica.encuentraCantidadReservada(ojoReservado,idEmpresa);
			if (articulosCantidad.size() == 0){
				String mensaje = "Esta ubicacion no contiene mercaderia reservada";
				request.setAttribute("mensaje",mensaje);
			} else {
				request.setAttribute("lista",articulosCantidad);
			}
			//request.setAttribute("menError", "Se guardó la cantidad correctamente");
			
 		}
		else
		{
			request.setAttribute("menError", "No existe la ubicacion en el sistema");
		}
		
		request.setAttribute("ojoSeleccionado",ojoReservado);
		return mapping.findForward("ok");
	}

}
