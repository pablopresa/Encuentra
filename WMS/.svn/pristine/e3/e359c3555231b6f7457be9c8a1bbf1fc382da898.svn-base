package web.expedicion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.EnviaMail;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;




import beans.Articulo;

import beans.Estado;
import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DataDocTipoEnvio;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Ruta;
import beans.encuentra.RutaDeposito;
import beans.encuentra.Transporte;

public class _EncuentraDarDocsEnvio extends Action 
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
				List <DocumentoEnvio> documentosSel = null;
				
				String depo = request.getParameter("depo");
				String fechas = request.getParameter("fini");
				Boolean esRuta = (Boolean) session.getAttribute("rutas");
				String fechaI = "";
				String fechaF = "";
				
				if(fechas!= null && !fechas.equals("")){
					fechaI = fechas.split(" - ")[0]+" 00:00:00";
					fechaF = fechas.split(" - ")[1]+" 23:59:59";
				}
				
				int idDepo = Integer.parseInt(depo);
				
				
				
				
				Ruta ruta = new Ruta(0,"");
				if(!esRuta) {
					List<RutaDeposito> depositos = new ArrayList<RutaDeposito>();
					RutaDeposito unDepo = new RutaDeposito(idDepo,0,"");
					depositos.add(unDepo);
					ruta.setDepositos(depositos);
				}else {
					Hashtable<Integer, Ruta> rutas = (Hashtable<Integer, Ruta>) session.getAttribute("RutasHT");
					ruta = rutas.get(idDepo);
				}
				
				
				if(ruta == null) {
					if(esRuta) {
						return mapping.findForward("rutas");
					}else {
						return mapping.findForward("ok");
					}
				}
				
				if (fechaI.equals("") || fechaF.equals(""))
				{
					documentosSel =  Logica.darEnvios(ruta,null,null,0, idEmpresa);			
				}
				else
				{					
					documentosSel =  Logica.darEnvios(ruta,fechaI,fechaF,0, idEmpresa);	
				}
				
				
				
				Envio envio = (Envio) session.getAttribute("envio");
				if(envio!=null)
				{
					if(envio.getDepositos()!=null && !envio.getDepositos().isEmpty())
					{
						for (DepositoEnvio depE : envio.getDepositos()) 
						{
							if(depE.getIdDeposito()==Integer.parseInt(depo))
							{
								for (DocumentoEnvio d : documentosSel) 
								{
									for (DataDocTipoEnvio Din : depE.getDocumentos()) 
									{
										for (DocumentoEnvio d2 : Din.getDocumentos()) 
										{
											if(d.getNumeroDoc()==d2.getNumeroDoc())
											{
												d.setIncluir(true);
												break;
											}
										}
									}
									if(depE.getDocumentosExtra()!=null)
									{
										for (DataDocTipoEnvio Din : depE.getDocumentosExtra()) 
										{
											for (DocumentoEnvio d2 : Din.getDocumentos()) 
											{
												if(d.getNumeroDoc()==d2.getNumeroDoc())
												{
													d.setIncluir(true);
													break;
												}
											}
										}
									}
								
								}
							}
						}
					}
				}
				
				
				session.setAttribute("depositoSelected", depo);
				session.setAttribute("documentosSel", documentosSel);
				
				if(esRuta) {
					return mapping.findForward("rutas");
				}else {
					return mapping.findForward("ok");
				}
				
			}
	
	
		
		}


