package web.util;


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

import beans.Usuario;
import beans.encuentra.DataArtMovS;
import beans.encuentra.DataDocTipoEnvio;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;





public class _EncuentraArticulosEnvio extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
				Logica Logica = new Logica();
				
				Utilidades util = new Utilidades();
				
				Usuario usu = (Usuario) session.getAttribute("uLogeado");		
				int idEmpresa = util.darEmpresa(usu);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				String listaEnvios = "";
				List<Envio> envios = (List<Envio>) session.getAttribute("envios");
				
				List<Integer> documentosSel = new ArrayList<>();
				List<Envio> enviosSel = new ArrayList<>();
				
				for (Envio e : envios) 
				{
					try
					{
						if(request.getParameter(String.valueOf(e.getIdEnvio())).equals("on")) 
						{
							enviosSel.add(e);
							for (DepositoEnvio depo : e.getDepositos()) //VA A SER UNO SOLO
							{
								for (DataDocTipoEnvio docsEn : depo.getDocumentos()) 
								{
									for (DocumentoEnvio doc : docsEn.getDocumentos()) 
									{
										documentosSel.add(doc.getNumeroDoc());
										listaEnvios+=doc.getNumeroDoc()+", ";
									}
								}
								for (DataDocTipoEnvio docsEn : depo.getDocumentosExtra()) 
								{
									for (DocumentoEnvio doc : docsEn.getDocumentos()) 
									{
										documentosSel.add(doc.getNumeroDoc());
										listaEnvios+=doc.getNumeroDoc()+", ";
									}
								}
							}
							
						}
					}
					catch(Exception eX)
					{
						
					}
					
				}
				
				
				session.setAttribute("enviosSel", enviosSel);
				List<DataArtMovS> articulos = Logica.encuentraDarListaArticulosEntrega(documentosSel,idEmpresa);
				session.setAttribute("listaEnvios", listaEnvios);
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


