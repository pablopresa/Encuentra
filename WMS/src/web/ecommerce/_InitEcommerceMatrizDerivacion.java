package web.ecommerce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import beans.Usuario;
import beans.encuentra.DepositoAdmin;
import beans.encuentra.ParametrosPreparacion;
import beans.encuentra.ValueObjects.VOParametrosPreparacion;
import logica.Logica;
import logica.Utilidades;


public class _InitEcommerceMatrizDerivacion extends Action 
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
		
		String init = request.getParameter("init");
		
		String parametros = request.getParameter("parametrosDeposSet");
		System.out.println(parametros);
		
		
		List<DepositoAdmin> depositosMatrizDeriv = new ArrayList<>();
		int depoPrincipal = util.darParametroEmpresaINT(idEmpresa, 4);
		
		if(init.equals("false"))
		{
			Gson gson = new Gson();
		    Type type = new TypeToken<List<VOParametrosPreparacion>>(){}.getType();
		    List<VOParametrosPreparacion> listaParametros = gson.fromJson(parametros, type);
		    
		    //lista actualzada a persistir
		    List<DepositoAdmin> listaActualizada = new ArrayList<>();
		    for(VOParametrosPreparacion nodo :listaParametros)
		    {
		    	//Creo un nodo para la lista
		    	DepositoAdmin aux = new DepositoAdmin();
		    	
		    	aux.setId(Utilidades.parseStringIntStatic(nodo.getNumero()));
		    	aux.setNombre(nodo.getNombre());
		    	
		    	//Creo parametros
		    	ParametrosPreparacion param = new ParametrosPreparacion();
		    	param.setPrioridad(Utilidades.parseStringIntStatic(nodo.getPrioridad()));
		    	param.setPreparaPickup(Utilidades.parseStringToBoolean(nodo.getPreparaPickup()));
		    	param.setPreparaDelivery(Utilidades.parseStringToBoolean(nodo.getPreparaDelivery()));
		    	param.setPreparaEnvioCD(Utilidades.parseStringToBoolean(nodo.getPreparaEnvioCD()));
		    	param.setIdGrupo(Utilidades.parseStringIntStatic(nodo.getGrupo()));
		    	aux.setParametros(param);
		    	
		    	listaActualizada.add(aux);
		    }
		    Logica.updateParametrosMatrizDerivacion(listaActualizada, idEmpresa);
		}
		
		depositosMatrizDeriv = Logica.encuentraDarDepositosAdmin(depoPrincipal,idEmpresa); 
		session.setAttribute("depositosMatrizDeriv", depositosMatrizDeriv);
			
		return mapping.findForward("ok");
		
	}

}