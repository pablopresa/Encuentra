package web.almacen;

import java.util.ArrayList;
import java.util.Hashtable;
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

import com.google.gson.Gson;

import beans.GraphLinkData;
import beans.GraphNodeData;
import beans.MovArticulo;
import beans.Usuario;

public class _MovsArticulos extends Action 
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
		String accion = request.getParameter("action");
		
		String articulo = request.getParameter("articulo"); 
		String ubicacion = request.getParameter("ubicacion");
		List<MovArticulo> movimientos = new ArrayList<>();
		//articulo="0000102MA0090";
		//ubicacion="C1";
		Hashtable<String, String> grafosExistentes = new Hashtable<>();
		
		movimientos = Logica.MovimientosArticulo(articulo, ubicacion,grafosExistentes, idEmpresa);
		
		session.setAttribute("movimientos", movimientos);
		
		return mapping.findForward("ok");
	
	}
}
/*
	public Hashtable<String,GraphNodeData> darNodos(List<MovArticulo> movs,Hashtable<String,GraphNodeData> nodos, boolean primero, Hashtable<String,GraphLinkData> links){
		
		try {
			for(MovArticulo m:movs){
				GraphNodeData nod = new GraphNodeData();
				if(primero){
					nod.setKey(m.getCodDestino());
					nod.setGroup("Lane1");
					nodos.put(nod.getKey(), nod);
					nod = new GraphNodeData();
					nod.setKey(m.getCodOrigen());
					nod.setGroup("Lane1");
					nodos.put(nod.getKey(), nod);
				}
				else{
					nod.setKey(m.getCodOrigen());
					nod.setGroup("Lane1");
					nodos.put(nod.getKey(), nod);
				}				
				GraphLinkData ldata = new GraphLinkData();
				ldata.setFrom(m.getCodOrigen());
				ldata.setTo(m.getCodDestino());
				links.put(ldata.getFrom()+"-"+ldata.getTo(),ldata);
				darNodos(m.getOrigenes(),nodos,false,links);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodos;
	}
*/

