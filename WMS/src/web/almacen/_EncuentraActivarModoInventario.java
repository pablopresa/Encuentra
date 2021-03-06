package web.almacen;

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

public class _EncuentraActivarModoInventario extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		try{
			String activado = request.getParameter("activo");
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			int bit = 0;
			
			if (activado.equals("true")){ 
				bit = 1;
				
				String queryInventON = "INSERT INTO invent (idInvent, idDeposito, fecha, conteo, porcentajeojos, activo, idEmpresa) VALUES (1,'"+uLog.getDeposito()+"',CURRENT_TIMESTAMP(),0,0,1,"+idEmpresa+") "
						+"ON DUPLICATE KEY "
						+"UPDATE activo=1,fecha= CURRENT_TIMESTAMP(),conteo=0,porcentajeojos=0;";
				Logica.persistir(queryInventON);
				
				uLog.setInventario(true);
				uLog.registrarEventoMin(session.getId(), "Usuario Activa Modo Inventario");
			}
			else{
				Logica.persistir("update invent set activo=0 where idEmpresa="+idEmpresa+" AND idinvent=1 and iddeposito='"+uLog.getDeposito()+"'");
				uLog.setInventario(false);
				uLog.registrarEventoMin(session.getId(), "Usuario Desactiva Modo Inventario");
			}
			session.setAttribute("uLogeado", uLog);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
			
	}
	

}
