package web.picking;

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

import com.google.gson.Gson;

import beans.Usuario;
import beans.encuentra.DataPicking;
import beans.encuentra.DataPickingCollection;



public class _EncuentraCorregirPickingVC extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		try 
		{
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			Gson gson = new Gson();
			//String destino = request.getParameter("accion");
			int idPick=0;
			List<DataPicking> pickings = (List<DataPicking>) session.getAttribute("pickings"); 
			//String articulo = request.getParameter("articulo");
			String arts = request.getParameter("datos");	
			
			//int idDepDestino = Integer.parseInt(request.getParameter("destino"));
			//int cantidad = Integer.parseInt(request.getParameter("cant"));
			DataPickingCollection pickCollection = new DataPickingCollection();
			List<DataPicking> pick = new ArrayList<>();
			pickCollection = gson.fromJson(arts, DataPickingCollection.class);
			pick = pickCollection.getCollection();
			idPick = pick.get(0).getIdPicking();
			int resta = 0;
			for(DataPicking change: pick){
				for (DataPicking p : pickings) 
				{					
					if(p.getArticulo().equals(change.getArticulo()) && p.getDestino().getId()==change.getDestino().getId() && p.getOrigen().getId()==change.getOrigen().getId()
							&& p.getIdPedido().equals(change.getIdPedido()) && p.getSolicitud()==change.getSolicitud())
					{
						resta += p.getVerificada()-change.getVerificada();
						p.setVerificada(change.getVerificada());
						//idPick = p.getIdPicking();
						
						//pick.add(p);
						break;
					}
				}
			}
			
			Logica.encuentraUpdateVerifPicking(pick,true,idEmpresa);
			
			
			uLog.registrarEvento(idPick,(resta*-1),112,0,true, session.getId(), "Corrigiendo unidades verificadas del picking");
			
			
			//pickings = Logica.encuentraDarPicking(idPick);
			session.setAttribute("pickings", pickings);
			
			
			return mapping.findForward("ok");
			
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		
	}
}















