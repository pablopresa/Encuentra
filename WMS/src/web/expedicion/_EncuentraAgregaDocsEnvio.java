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


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;




import beans.Articulo;



import beans.Estado;

import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class _EncuentraAgregaDocsEnvio extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
 Logica Logica = new Logica();
				
				String depo = request.getParameter("depo");
				/*
				 	<td><input type="text" name="frmAgrNumero" /></td>
					<td><input type="text" name="frmAgrRazon" /></td>
					<td><input type="text" name="frmAgrUnidades"/></td>
					<select name="frmAgrDepOr" id="frmAgrDepOr" class="select">
					<td><input type="text" name="usur" value="${uLogeado.nombre} ${uLogeado.apellido}" readonly="readonly"/></td>
					<td><input type="text" name="frmAgrObservaciones"/></td>
						
				 */
				
				List <DocumentoEnvio> documentosSel =  (List<DocumentoEnvio>) session.getAttribute("documentosSel");
				
				
				//usuario es ulog
				
				if(documentosSel == null || documentosSel.isEmpty())
				{
					String error = "Seleccione el deposito primero";
					request.setAttribute("menError", error);
					return mapping.findForward("ok");
				}
				
				boolean repetido=false;
				
				for(DocumentoEnvio d:documentosSel){
					if(d.getRazon().getId()==100){
						repetido=true;
						break;
					}
				}
				
				if(repetido){
					request.setAttribute("menError", "Ya ingreso un documento de razon BULTOS para este deposito");
					return mapping.findForward("ok");
				}
				else
				{
					try
					{
						int idDoc = Integer.parseInt(request.getParameter("frmAgrNumero"));
						int razon = Integer.parseInt(request.getParameter("frmAgrRazon"));
						int unidades = Integer.parseInt(request.getParameter("frmAgrUnidades"));
						
						int dorigen = Integer.parseInt(request.getParameter("frmAgrDepOr"));
						
						List <DataIDDescripcion> razones = (List<DataIDDescripcion>) session.getAttribute("razonesDoc");
						Usuario u = (Usuario) session.getAttribute("uLogeado");
						DataIDDescripcion datUsuario = new DataIDDescripcion(u.getNumero(), u.getNombre()+" "+ u.getApellido());
						
						DataIDDescripcion depoDestino = new DataIDDescripcion();
						depoDestino = documentosSel.get(0).getDepositoD();
						
						DataIDDescripcion depositoO = new DataIDDescripcion(dorigen, "");
						
						DocumentoEnvio doc = new DocumentoEnvio();
						String comentario = request.getParameter("frmAgrObservaciones");
						
						doc.setCantidad(unidades);
						doc.setComentario(comentario);
						doc.setDepositoD(depoDestino);
						doc.setDepositoO(depositoO);
						doc.setUsuario(datUsuario);
						doc.setIncluir(true);
						
						
						for (DataIDDescripcion d: razones) 
						{
							if(d.getId()==razon)
							{
								doc.setRazon(d);
								break;
							}
						}
						
						doc.setNumeroDoc(idDoc);
						
						/*if(doc.getRazon().getId()==100){			//PARA GUARDAR CANTIDADES
							doc.setCustom(true);
						}*/
						doc.setCustom(true);
						
						int pos = 0;
						for (DocumentoEnvio d : documentosSel)
						{
							String on = request.getParameter(String.valueOf(d.getNumeroDoc()));
							if(on!=null && on.equals("on"))
							{
								documentosSel.get(pos).setIncluir(true);
								
							}
							pos ++;
						}
						
						
														
						documentosSel.add(doc);
		
						
					}
					catch (Exception e)
					{
						int pos = 0;
						for (DocumentoEnvio d : documentosSel)
						{
							String on = request.getParameter(String.valueOf(d.getNumeroDoc()));
							if(on!=null && on.equals("on"))
							{
								documentosSel.get(pos).setIncluir(true);
								
							}
							pos ++;
						}
						session.setAttribute("documentosSel", documentosSel);
						
						String error = "por favor complete los campos obligatorios";
						request.setAttribute("menError", error);
						return mapping.findForward("ok");
					}
					
					
					
					
					
					session.setAttribute("documentosSel", documentosSel);
					
					return mapping.findForward("ok");
				
			}
	
			}		
		
		}


