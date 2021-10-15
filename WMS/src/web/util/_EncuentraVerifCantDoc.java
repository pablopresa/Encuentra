package web.util;


import java.util.ArrayList;
import java.util.Hashtable;
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





public class _EncuentraVerifCantDoc extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();

	
				
				
				List<DataArtMovS> articulosLista = (List<DataArtMovS>) session.getAttribute("artiVerifica");
				Hashtable<String, DataArtMovS> articulosTODOS = new Hashtable<>();
				
				for (DataArtMovS d : articulosLista) 
				{
					articulosTODOS.put(d.getArticulo(), d);
				}
				
				
				
				String articulosStr = request.getParameter("lista");
				
				String [] articulosArr = articulosStr.split(",");
				
				Hashtable<String, DataArtMovS> articulosH = new Hashtable<>();
				
				
				for (String a : articulosArr) 
				{
					if(!a.equals(""))
					{
						if(articulosH.get(a)==null)
						{
							DataArtMovS in = articulosTODOS.get(a);
							in.setCantidadDoc(in.getCantidad());
							in.setCantidad(1);
							in.setIn(true);
							articulosH.put(a, in);
							
						}
						else
						{
							DataArtMovS in = articulosH.get(a);
							in.setCantidad(in.getCantidad()+1);
							articulosH.put(a, in);
						}
					}
					
				}
				
				
				
				
				List<DataArtMovS> articulosToodos = new ArrayList<>(articulosTODOS.values());
				
				for (DataArtMovS d : articulosToodos) 
				{
					if(articulosH.get(d.getArticulo())==null)//no se scaneo
					{
						d.setIn(false);
						d.setCantidadDoc(d.getCantidad());
						d.setCantidad(0);
						articulosH.put(d.getArticulo(), d);
						
					}
					else
					{
						DataArtMovS enH = articulosH.get(d.getArticulo());
						if(d.getCantidad()!=enH.getCantidad())//se escaneo pero las cantidades no son las mismas
						{
							enH.setIn(false);
							articulosH.put(enH.getArticulo(), enH);
						}
						
					}
				}
				
				
				
				List<DataArtMovS> articulosVerificados = new ArrayList<>(articulosH.values());
				session.setAttribute("verificados", articulosVerificados);
				// hay que garrarlo del otro lado o comprobarlo.
				

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


