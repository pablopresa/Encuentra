package web.recepcion;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import dataTypes.DataIDDescripcion;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;


public class _EncuentraPrintBarrasRecepcionII extends Action 
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
		
		
		List<DataIDDescripcion> listaBarrasRecepcion = (List<DataIDDescripcion>) session.getAttribute("listaBarrasRecepcion");
		
		List<DataIDDescripcion> listaPrint = new ArrayList<>();
		String inns = "";
		boolean pri = true;
		
		for (DataIDDescripcion ar : listaBarrasRecepcion) 
		{
			if(request.getParameter(ar.getDescripcion())!=null &&  request.getParameter(ar.getDescripcion()).equals("on"))
			{
				try
				{
					int qty = Integer.parseInt(request.getParameter(ar.getDescripcion()+"Qty"));
					if(qty>0)
					{
						if(pri)
						{
							pri=false;
							inns+="'"+ar.getDescripcion()+"'";
						}
						else
						{
							inns+=",'"+ar.getDescripcion()+"'";
						}
						listaPrint.add(new DataIDDescripcion(qty, ar.getDescripcion()));
					}
					
				}
				catch (Exception e) 
				{
		
				}
				
			}
		}
		
		Hashtable<String, String> barrasIn = Logica.darBarrasHTArt(inns,idEmpresa);
		
		for (DataIDDescripcion a : listaPrint) 
		{
			String arrayglo = "";
			if(barrasIn.containsKey(a.getDescripcion()))
			{
				//0"RETAIL"	1"146.W0220 BL"	2"BOT MUJ 146.W0220 BL WHITE 35"	3"2998818672091"	4"WHITE"	5"35"	6"Sintetico PU"	7"Textil Poliester"	8"Sintetico PVC"	9"RUT COMPRADOR: 77261280-K"	10"1"
				arrayglo = "0,1,2,"+barrasIn.get(a.getDescripcion())+",4,"+a.getDescripcion()+",6,7,8,9,10";
				
				
			}
			else
			{
				arrayglo = "0,1,2,"+a.getDescripcion()+",4,"+a.getDescripcion()+",6,7,8,9,10";
				
			}
			a.setDescripcion(arrayglo);
		}
		
		
		String path = ImpresionesPDF.ImprimirEtiquetasarticulos3L(listaPrint,idEmpresa);
		
		request.setAttribute("URL", path);
			
		return mapping.findForward("redir");
		
	}

}