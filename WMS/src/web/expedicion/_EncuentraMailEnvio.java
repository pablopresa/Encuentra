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
import logica.LeerFicheroTexto;
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
import beans.encuentra.DataDocTipoEnvio;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class _EncuentraMailEnvio extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
 Logica Logica = new Logica();
				
				
				
				Envio envio = (Envio) session.getAttribute("envio");
				if(envio==null){
					int idEnvio = Integer.parseInt(request.getParameter("idEnvio"));
					
					List<Envio> envios = (List<Envio>) session.getAttribute("envios");
					
					for(Envio e:envios){
						if (e.getIdEnvio()==idEnvio){
							envio=e;
							break;
						}
					}
				}
				
				
				String HTML_prev =LeerFicheroTexto.LeerArchivoTXT("HTML_Prev.txt").toString(); 

				
				
		
				
				for (DepositoEnvio depo : envio.getDepositos()) 
				{
					
					String HTML_in = "<div>Detalle de unidades de remito de entrega "+envio.getIdEnvio()+" a deposito "+ depo.getIdDeposito()+" del "+envio.getFechaVis()+"</div>"+
					"	<div align='center'>"+
					"		<p>Datos del transporte</p>"+
					"		"+
					"	    <table id='hor-zebra' style='width: 700px; '>"+
					"	    	<tr>"+
					"	        	<td style='width: 50%; '>"+
					"	            	<table cellpadding='4' cellspacing='4'>"+
					"	                	<tr class='odd'>"+
					"	                    	<td>"+
					"	                        	Transporte"+
					"	                        </td>"+
					"	                        <td>"+
					"	                        	"+envio.getTransporte().getDescripcion()+""+
					"	                        </td>"+
					"	                    </tr>"+
					"	                   <tr class='odd'>"+
					"	                    	<td>"+
					"	                        	Marca"+
					"	                        </td>"+
					"	                        <td>"+
					"	                        	"+envio.getTransporte().getMarca()+""+
					"	                        </td>"+
					"	                   </tr>"+
					"	                   <tr class='odd'>"+
					"	                    	<td>"+
					"	                        	Matricula"+
					"	                        </td>"+
					"	                        <td>"+
					"	                        	"+envio.getTransporte().getMatricula()+
					"	                        </td>"+
					"	                    </tr>"+
					"	                </table>"+
					"	            </td>"+
					"	           "+
					"	        	<td style='width: 50%; '>"+
					"	               <table cellpadding='4' cellspacing='4'>"+
					"	                	<tr class='odd'>"+
					"	                    	<td>"+
					"	                        	Chofer"+
					"	                        </td>"+
					"	                        <td>"+
					"	                        	"+envio.getChofer().getDescripcion()+
					"	                        </td>"+
					"	                    </tr>"+
					"	                   <tr class='odd'>"+
					"	                    	<td>"+
					"	                        	Acompañante"+
					"	                        </td>"+
					"	                        <td>"+
					"	                        	"+envio.getAcompaniante().getDescripcion()+
					"	                        </td>"+
					"	                   </tr>"+
					"	                   <tr class='odd'>"+
					"	                    	<td>"+
					"	                        	&nbsp;"+
					"	                        </td>"+
					"	                        <td>"+
					"	                        	&nbsp;"+                        	
					"	                        </td>"+
					"	                    </tr>"+
					"	                </table>"+
					"	            </td>"+
					"	        </tr>"+
					"	    </table>"+
					"	    <p>Detalle de la entrega</p>"+
					"	   <table style='border:1px solid; width: 600px;'>"+
					"		<thead>"+
					"			<tr style='border:1px solid;'>"+
					"				<th scope='col' style='background: #d0dafd'>Tipo</th>"+
					"				<th scope='col' style='background: #d0dafd'>Numero</th>"+
					"				<th scope='col' style='background: #d0dafd'>Cantidad de unidades</th>"+
					"				<th scope='col' style='background: #d0dafd'>observaciones</th>"+
					""+				
					"			</tr>"+
					"		</thead>"+
					"		<tbody>";
							
					
					String bucle = "";
					
					for (DataDocTipoEnvio tipo : depo.getDocumentos()) 
					{
						boolean prende = true;
						
						for (DocumentoEnvio doc : tipo.getDocumentos()) 
						{
							
							bucle += "			<tr style='border:1px solid'>"+
									"				<td style='background: #d0dafd'><div align='center'>"+doc.getRazon().getDescripcion()+"</div></td>"+
									"				<td style='background: #d0dafd'>"+doc.getNumeroDoc()+"</td>"+
									"				<td style='background: #d0dafd'><div align='center'>"+doc.getCantidad()+"</div></td>"+
									"				<td style='background: #d0dafd'><div align='center'>"+doc.getComentario()+"</div></td>"+
									"			</tr>";
							
						}
						
						bucle +="			<tr style='border:1px solid'>"+
								"				<td style='background: #d0dafd'>&nbsp;</td>"+
								"				<td style='background: #d0dafd'>SUB- TOTAL </td>"+
								"				<td style='background: #d0dafd'><div align='center'>"+tipo.getCantidad()+"</div></td>"+
								"				<td style='background: #d0dafd' ><div align='center'>&nbsp;</div></td>"+
								"			</tr>"+
								"			<tr style='border:1px solid'>"+
								"				"+
								"				<td style='background: #d0dafd'><div align='center'>&nbsp;</div></td>"+
								"				<td style='background: #d0dafd' ><div align='center'>&nbsp;</div></td>"+
								"				<td style='background: #d0dafd'><div align='center'>&nbsp;</div></td>"+
								"				<td style='background: #d0dafd' ><div align='center'>&nbsp;</div></td>"+
								"			</tr>";
						
					}
					bucle +="		</tbody>"+
					
							"		<tfoot>"+
							"    		<tr style='border:1px solid'>"+
							"        		<td colspan='4' style='background: #d0dafd'  align='right'><strong style='font-size:16px'>Total Entrega "+depo.getTotalU()+" Unidades</strong></td>"+
							"	        </tr>"+
							"			<tr style='border:1px solid'>"+
							"        		<td colspan='4' style='background: #d0dafd'  align='right'><strong style='font-size:16px'>Total Entrega "+depo.getTotalBultos()+" Bultos</strong></td>"+
							"	        </tr>"+
							"  	  </tfoot>"+
							"		</table>"+
							"		<br/>" +
							" <p>Entrega NO CALZADO</p>";
					
					
					
					
					String bucle2 ="";
					if(depo.getDocumentosExtra()!=null && depo.getDocumentosExtra().size()!=0)
					{
						for (DataDocTipoEnvio tipo : depo.getDocumentosExtra())
						{
							
							
							for (DocumentoEnvio doc : tipo.getDocumentos()) 
							{
								
								bucle2 += "			<tr style='border:1px solid'>"+
										"				<td style='background: #d0dafd'><div align='center'>"+doc.getRazon().getDescripcion()+"</div></td>"+
										"				<td style='background: #d0dafd'>"+doc.getNumeroDoc()+"</td>"+
										"				<td style='background: #d0dafd'><div align='center'>"+doc.getCantidad()+"</div></td>"+
										"				<td style='background: #d0dafd'><div align='center'>"+doc.getComentario()+"</div></td>"+
										"			</tr>";
								
							}
							
							bucle2 +="			<tr style='border:1px solid'>"+
									"				<td style='background: #d0dafd'>&nbsp;</td>"+
									"				<td style='background: #d0dafd'>SUB- TOTAL </td>"+
									"				<td style='background: #d0dafd'><div align='center'>"+tipo.getCantidad()+"</div></td>"+
									"				<td style='background: #d0dafd' ><div align='center'>&nbsp;</div></td>"+
									"			</tr>"+
									"			<tr style='border:1px solid'>"+
									"				"+
									"				<td style='background: #d0dafd'><div align='center'>&nbsp;</div></td>"+
									"				<td style='background: #d0dafd' ><div align='center'>&nbsp;</div></td>"+
									"				<td style='background: #d0dafd'><div align='center'>&nbsp;</div></td>"+
									"				<td style='background: #d0dafd' ><div align='center'>&nbsp;</div></td>"+
									"			</tr>";
							
						}
						bucle2 +="		</tbody>"+
								"		</table>"+
								"		<br/>";
					
					}
						
					String fin = "</div>" +
							"</div>"+
							"</div>"+
							"</div>" +
							"<p> Notificación generada por sistema Encuentra</p>"+
							"</body>"+
							"</html>";
					
					
					
					String cuerpoMail = HTML_prev+" "+HTML_in+" "+bucle+" "+bucle2+" "+fin;
							
					String mailDestino = "stadium";
					String mailDominio ="@stadium.local";
					// si quiesieramos hacer esto administrable podemos traer de la base de datos el mail del responsable con copia de los envios.
					String cc = "gmonzon@stadium.local";
					if(depo.getIdDeposito()==40 || depo.getIdDeposito()==41)
					{
						mailDestino = "clarks"+depo.getIdDeposito();
					}
					else if(depo.getIdDeposito()<10)
					{
						mailDestino+="0"+depo.getIdDeposito();
					}
					else if(depo.getIdDeposito()==51 || depo.getIdDeposito()==52)
					{
						mailDestino="misscarol"+depo.getIdDeposito();
					}
					else
					{
						mailDestino+=depo.getIdDeposito();
					}
					
					EnviaMail.enviarMailHTML(mailDestino+mailDominio, "Expedición comunica detalle de remito de entrega "+envio.getIdEnvio()+" a la sucursal "+depo.getIdDeposito(), cuerpoMail);
					//EnviaMail.enviarMailHTML(cc, "Expedicion comunica detalle de remito de entrega "+envio.getIdEnvio(), cuerpoMail);
					
				}
				
				request.setAttribute("menError", "envio de mails correcto");
				return mapping.findForward("ok");
				
			}
	
	
		
		}


