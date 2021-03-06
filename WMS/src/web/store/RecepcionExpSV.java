package web.store;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerHTML;
import logica.Logica;
import logica.Utilidades;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import helper.PropertiesHelper;
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
import clientesVisual_Store.Std.clienteWSVS.WSCommunicate;


public class RecepcionExpSV extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		//Logica log = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		

		String error ="ATENCION"; 
		
		try
		{
			String auth = "";
			 PropertiesHelper pH=new PropertiesHelper("paths");
			 pH.loadProperties();
			 String path = pH.getValue("img")+"comprobante";
			 String pathSalida = pH.getValue("HTTP_pdf")+"comprobante";
			 
			File destino=new File(path);	
			
			ServletRequestContext src=new ServletRequestContext(request);
			List <String> strings = new ArrayList<>();
			
			//Si el formulario es enviado con Multipart
			if(FileUploadBase.isMultipartContent(src)){
			//Necesario para evitar errores de NullPointerException
			DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
			//Creamos un FileUpload
			ServletFileUpload upload=new  ServletFileUpload(factory);
			//Procesamos el request para que nos devuelva una lista
			//con los parametros y ficheros.
			List lista = upload.parseRequest(src);
			File file= null;
			//Recorremos la lista.
			Iterator it = lista.iterator();
			
			while(it.hasNext())
			{
				//Rescatamos el fileItem
				FileItem item=(FileItem)it.next();
				//Comprobamos si es un campo de formulario
				if(item.isFormField())
				{
					//Hacemos lo que queramos con el.
									
					strings.add(item.getString());
				}					
				
			}		
			
			}	
			
			for(String s:strings)
			{
				auth = s;
			}
			
			if(auth!=null && !auth.equals("")) 
			{
				session.setAttribute("sec", "M.E");
				
				Logica lo = new Logica();
				
				List<RecepcionExpedicion> recepcionablesSEL = (List<RecepcionExpedicion>) session.getAttribute("recepcionablesS");
				
				///List<Remito> remitosIn =  (List<Remito>) session.getAttribute("remitosIn");
				
				
				String tempInsert = "";
				for (RecepcionExpedicion r : recepcionablesSEL) 
				{
					for (DocumentoEnvio d : r.getDocumentos()) 
					{
						tempInsert+= "("+d.getDepositoO().getId()+","+d.getNumeroDoc()+"),";
					}
					
					
				}
				
				tempInsert = tempInsert.substring(0, tempInsert.length()-1);
				List<RecepcionExpedicion> recepcionablesConArticulos = lo.darArtRecepcionables(recepcionablesSEL,tempInsert,idEmpresa);
						
				String artsIN = "";
				
				WSCommunicate cl = new WSCommunicate();
				int tienda = Integer.parseInt(uLog.getDeposito());
				int idEquipo = 2000+tienda;
				
				String resultado = "";
				
				for (RecepcionExpedicion r : recepcionablesConArticulos) 
				{
					boolean todoOK = true;
					for (DocumentoEnvio d : r.getDocumentos()) 
					{
						List<DataIDDescripcion> listaArts = new ArrayList<>();
						for (RemitoLinea l : d.getLineas()) 
						{
							listaArts.add(new DataIDDescripcion(l.getCantidad(), l.getIdArticulo()));
							
							
						}
						boolean ok = cl.ConfirmarTransferenciaTienda(d.getDepositoO().getId(), d.getDepositoD().getId(), d.getNumeroDoc(), listaArts, "Recibido por Encuentra", (short)Integer.parseInt(uLog.getDeposito()),(short) idEquipo, (short)tienda, (long)uLog.getNumero());
						if(!ok)
						{
							resultado +=" EL documento "+d.getNumeroDoc()+" no fue recepcionado";
							todoOK = false;
						}
						else
						{
							lo.actualizarDocRecepcionado(r.getIdEnvio(),d.getNumeroDoc(),idEmpresa);
							lo.registrarRecepcionDoc(uLog.getNumero(),d.getNumeroDoc(),auth,idEmpresa);
						}
						
					}
					
					if(todoOK)
					{
						//registrar la recepcion de la expedicion en OK
						lo.confirmarRecepcionExpedicion(r.getIdEnvio(), Integer.parseInt(uLog.getDeposito()),idEmpresa);
					}
					
				}
				
				
				
				
				
				request.setAttribute("menError", "Recepcion grabada correctamente");		
				return mapping.findForward("ok");
			}
			else {						
				return mapping.findForward("auth");
			}
			
		}
		catch (Exception e) 
		{
			return mapping.findForward("auth");
		}
	}

}
