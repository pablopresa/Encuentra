package web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.SincronizaEncuentra;
import main.SincronizaEncuentraElRey;
import main.TestUnit2;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import logica.Logica;
import logica.Utilidades;

public class _EncuentraSincroManual extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		try{
			
			switch (idEmpresa) {
			case 2:
				SincronizaEncuentra se = new SincronizaEncuentra();
				SincronizaEncuentra.sincro();

				TestUnit2 tu = new TestUnit2();
							
				Logica Logica = new Logica();
				Usuario u = Logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");				
				tu.SincronizarDistribuciones(idEmpresa);
				break;
			case 4:
				SincronizaEncuentraElRey elRey = new SincronizaEncuentraElRey();
				elRey.main(null);
				break;	

			default:
				break;
			}			
			
			uLog.registrarEventoMin(session.getId(), "Usuario utiliza Sincronización Manual");
		}catch (Exception e) {
			uLog.registrarEventoMin(session.getId(), "Error al utilizar Sincronización Manual");
		}
		
		return mapping.findForward("ok");
	
	}

}


