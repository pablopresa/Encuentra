package web.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerHTML;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.IPrint;
import beans.encuentra.RecepcionExpedicion;
import beans.encuentra.RemitoLinea;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class _EncuentraDarBarrasRecep extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Logica log = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		

		List<RecepcionExpedicion> recepcionablesSEL = (List<RecepcionExpedicion>) session.getAttribute("recepcionablesS");
		
		///List<Remito> remitosIn =  (List<Remito>) session.getAttribute("remitosIn");
		
		String tipoSubm = request.getParameter("accion");
		
		String tempInsert = "";
		for (RecepcionExpedicion r : recepcionablesSEL) 
		{
			List<DocumentoEnvio> documentosIn = new ArrayList<>();
			
			for (DocumentoEnvio d : r.getDocumentos()) 
			{
				//CHK19-22764
				String strON =  request.getParameter("CHK"+d.getDepositoO().getId()+"-"+d.getNumeroDoc());
				
				if(strON!=null && strON.equals("on"))
				{
					tempInsert+= "("+d.getDepositoO().getId()+","+d.getNumeroDoc()+"),";
					documentosIn.add(d);
				}
			}
			r.setDocumentos(documentosIn);
			
		}
		
		if(!tipoSubm.equals("Verificarlos"))
		{
			session.setAttribute("recepcionablesS", recepcionablesSEL);
			return mapping.findForward("auth");
		}
		
		try {
			tempInsert = tempInsert.substring(0, tempInsert.length()-1);
		}
		catch (Exception e) {
			String error = "Ha ocurrido un error, vuelva a seleccionar documentos";
			request.setAttribute("menError", error);
			return mapping.findForward("error");
		}
		
		List<RecepcionExpedicion> recepcionablesConArticulos = log.darArtRecepcionables(recepcionablesSEL,tempInsert,idEmpresa);

		
		String artsIN = "";
		
		for (RecepcionExpedicion r : recepcionablesConArticulos) 
		{
			for (DocumentoEnvio d : r.getDocumentos()) 
			{
				for (RemitoLinea l : d.getLineas()) 
				{
					artsIN+="'"+l.getIdArticulo()+"',";
				}
			}
		}
		
		artsIN = artsIN.substring(0, artsIN.length()-1);
		List<DataDescDescripcion> barrasR =  log.darBarrasInn(artsIN,idEmpresa);
		
		session.setAttribute("barrasR", barrasR);
		
		return mapping.findForward("ok");
	}

}
