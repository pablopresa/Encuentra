package web.util;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;



import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.encuentra.DataArtMovS;

import dataTypes.DataDocVisual;





public class _EncuentraArticulosDocs extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
 Logica Logica = new Logica();
	
				
				
				List<DataDocVisual>documentos = (List<DataDocVisual>) session.getAttribute("documentos");
				
				List<Integer> documentosSel = new ArrayList<>();
				
				for (DataDocVisual d : documentos) 
				{
					try
					{
						if(request.getParameter(String.valueOf(d.getIdInterno())).equals("on")) 
						{
							
							documentosSel.add(d.getIdInterno());
						}
					}
					catch(Exception e)
					{
						
					}
					
				}
				
				
				
				List<DataArtMovS> articulos = Logica.encuentraDarListaArticulosDoc(documentosSel);
				
				String articulosStr = "";
				String barrasStr = "";
				
				for (DataArtMovS d : articulos) 
				{
					
					for (String b : d.getBarras()) 
					{
						articulosStr += d.getArticulo()+",";
						barrasStr +=b+",";
					}
				}
				
				session.setAttribute("artiVerifica", articulos);
				session.setAttribute("articulos", articulosStr);
				session.setAttribute("codigos", barrasStr);
				
				String jq = (String) session.getAttribute("jquery");
				
				if(jq!=null)
				{
					return mapping.findForward("okJQM");
				}
				else
				{
					return mapping.findForward("ok");
				}
				
			}
	
	
		
		}


