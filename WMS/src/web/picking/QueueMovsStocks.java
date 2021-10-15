package web.picking;

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

import beans.MovStock;
import beans.Usuario;
import beans.encuentra.DataPicking;
import beans.encuentra.DepositoAdmin;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;

public class QueueMovsStocks extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String monitor = request.getParameter("monitor");
				
		//List<MovStock> MovStock = Logica.queueMovsStock(idEmpresa);
		List<MovStock> MovStock = api.QueueMovStock(idEmpresa);	

		Hashtable<Integer, DepositoAdmin> mapDepos = (Hashtable<Integer, DepositoAdmin>) session.getAttribute("mapDepos");
		Hashtable<Integer, Usuario> mapUsuarios = (Hashtable<Integer, Usuario>) session.getAttribute("mapUsuarios");
		if(mapDepos == null) {mapDepos = Logica.HTDepositos(idEmpresa); }
		if(mapUsuarios == null) {mapUsuarios = Logica.darUsuarios(idEmpresa); }
		Hashtable<Integer, MovStock> hashMovimientos = armoHashMovimientos(MovStock, mapDepos, mapUsuarios);
		
		session.setAttribute("hashMovimientos", hashMovimientos);	
		MovStock = new ArrayList<>(hashMovimientos.values());
		session.setAttribute("MovStock", MovStock);
		
		if(monitor!=null && !monitor.equals("")) {
			return mapping.findForward("forw");
		}
		else {
			return mapping.findForward("ok");
		}		
	
	}
	
	
	public Hashtable<Integer, MovStock> armoHashMovimientos(List<MovStock> lista, Hashtable<Integer, DepositoAdmin> mapDepos,
			Hashtable<Integer, Usuario> mapUsuarios) {
		Hashtable<Integer, MovStock> hashMovimientos = new Hashtable<>();
		for(MovStock m: lista) {		
			try {
				m.setUsuario(mapUsuarios.get(m.getIdUsuario()).getNombre() + " " + mapUsuarios.get(m.getIdUsuario()).getApellido());				
			} catch (Exception e) {
				m.setUsuario(m.getIdUsuario()+"");
			}
			
			try {
				m.setNombreDestino(mapDepos.get(m.getDestino()).getNombre());
			} catch (Exception e) {
				m.setNombreDestino(m.getDestino()+"");
			}
			hashMovimientos.put(m.getId(), m);
		}
		return hashMovimientos;
	}
	
	
}








































