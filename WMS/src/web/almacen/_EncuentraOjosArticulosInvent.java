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
import beans._EncuentraTomaPedidosGEx;

public class _EncuentraOjosArticulosInvent extends Action 
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
		
		String view = request.getParameter("vista");
		List<DataIDDescripcion> lista =	null;
		String query = "";
		if(view.equals("show"))
		{
			uLog.registrarEventoMin(session.getId(), "Usuario exporta a Excel Inventario Gral.");
			query = "SELECT SUM(OT.Cantidad) cantidad,OT.idArticulo , AD.Descripcion FROM `ojostienenarticulos` OT "
					+ "INNER JOIN ojos o ON o.idOjo=OT.idOjo and o.idEmpresa=OT.idEmpresa "
					+ "INNER JOIN estanterias e on e.idestanteria=o.idestanteria and e.idEmpresa=o.idEmpresa "
					+ "INNER JOIN usos_estanteria ue on e.iduso=ue.iduso and e.idEmpresa=ue.idEmpresa "
					+ "LEFT outer JOIN art_descripcion AD ON AD.id = OT.idArticulo and AD.idEmpresa=OT.idEmpresa "
					+ "where o.idEstanteria NOT IN (107,108,109,110,111,112,113) AND OT.IdEmpresa ="+idEmpresa+" and ue.idUso=1  "
							+ "GROUP BY OT.idArticulo ORDER BY OT.idArticulo";
			lista = Logica.darListaDataIdDescripcionMYSQLConsulta(query);
		}
		else if(view.equals("invent"))
		{
			uLog.registrarEventoMin(session.getId(), "Usuario exporta a Excel ultimo conteo de inventario");
			query = "SELECT SUM(OT.Cantidad) cantidad,OT.idArticulo , AD.Descripcion FROM `ojostienenarticulos` OT "
					+ "INNER JOIN ojos o ON o.idOjo=OT.idOjo and o.idEmpresa=OT.idEmpresa "
					+ "INNER JOIN estanterias e on e.idestanteria=o.idestanteria and e.idEmpresa=o.idEmpresa "
					+ "INNER JOIN usos_estanteria ue on e.iduso=ue.iduso and e.idEmpresa=ue.idEmpresa "
					+ "LEFT outer JOIN art_descripcion AD ON AD.id = OT.idArticulo  and AD.idEmpresa=OT.idEmpresa  "
					+ "WHERE ot.InventUpdate=1 AND OT.Actualizado>=(SELECT fecha FROM invent WHERE idDeposito = "+uLog.getDeposito()+" AND IdEmpresa ="+idEmpresa+" ) "
							+ "AND o.idEstanteria NOT IN (107,108,109,110,111,112,113) AND OT.IdEmpresa="+idEmpresa+ " and ue.idUso=1 "
							+ "GROUP BY OT.idArticulo ORDER BY OT.idArticulo";
			lista = Logica.darListaDataIdDescripcionMYSQLConsulta(query);
		}
		
		
		int cantidad =0;
		
		for (DataIDDescripcion a : lista) 
		{
			cantidad+=a.getId();
		}
		
		
		_EncuentraTomaPedidosGEx geneExelClass = new _EncuentraTomaPedidosGEx();
		List <String> errores = geneExelClass.geneExel(lista);
		
		String path = errores.get(0);
		request.setAttribute("pathPlanilla", path);
		try
		{
			//lista.remove(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("listaInvent", lista);
		request.setAttribute("ttlInvent",cantidad);
		if(view.equals("txt"))
		{
			//escribir el TXT para visual
			
			return mapping.findForward("txt");
		}
		else
		{
			return mapping.findForward("ok");
		}
				
		
	
	}

}
