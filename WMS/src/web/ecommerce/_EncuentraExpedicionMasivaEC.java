package web.ecommerce;

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

import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import beans.Usuario;
import beans.encuentra.DepositoEnvio;

public class _EncuentraExpedicionMasivaEC extends Action 
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
		
		List<DepositoEnvio> depositos = (List<DepositoEnvio>) session.getAttribute("depositosSel");
		String exp = request.getParameter("exp");
		List<DataIDDescripcion> pedidosTracking = (List<DataIDDescripcion>) session.getAttribute("pedidosT");
		String destino = "";
		System.out.println("");
		
		if(depositos==null){
			depositos = Logica.darDepositosenvio(idEmpresa);
			session.setAttribute("depositosSel", depositos);
			return mapping.findForward("ok");
		}
		
		if(exp!=null){
			destino = (String) session.getAttribute("destinoExpEC");
			PropertiesHelper pH=new PropertiesHelper("configuracion");
			pH.loadProperties();		  
		    int depoEC = Integer.parseInt(pH.getValue("depositoWEB"));
		    List<DataIDDescripcion> expedicion = new ArrayList<>();
		    
		    for(DataIDDescripcion pt:pedidosTracking){
		    	String valor = request.getParameter(String.valueOf(pt.getId()));
		    	if(valor!=null){
		    		DataIDDescripcion data = new DataIDDescripcion(1,pt.getId()+"");
		    		expedicion.add(data);
		    	}
		    }
		    
		    //GENERO DOCUMENTO DE DE ENVIO
		    if(expedicion.size()>0){
		    	Logica.guardarEnvioEcommerce(Integer.parseInt(destino),expedicion,uLog,depoEC,2,idEmpresa);		
			    
			    uLog.registrarEventoMin(session.getId(), "Generando documento masivo para expedicion");
		    }
		    request.setAttribute("menError", "Se genero un documento por "+expedicion.size()+" paquetes.");
		    session.setAttribute("pedidosT", new ArrayList<>());
		    return mapping.findForward("ok");
		    
		}
		
		String fechas = request.getParameter("fini");

		String fechaI ="";
		String fechaF = "";
		
		if(fechas!=null)
		{
			String []fechaIF = fechas.split(" - ");
		
			fechaI = fechaIF[0];
			fechaF = fechaIF[1];
	
			fechaI = fechaI+" 00:00:00";
			fechaF = fechaF+" 23:59:59";						
		}	
		destino = request.getParameter("depo");
		session.setAttribute("destinoExpEC", destino);
		
		pedidosTracking = Logica.PedidosDarTracking(fechaI, fechaF,destino,true, idEmpresa);	
		session.setAttribute("pedidosT", pedidosTracking);
		
		
		
			return mapping.findForward("ok");

		
	
	}

}
