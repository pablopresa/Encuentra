package web.pickup;

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
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import helper.PropertiesHelper;
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.ArticuloConteo;
import beans.encuentra.ConteoTiendas;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;
import cliente_rest_Invoke.Call_WS_analoga;
//import cliente_rest_Invoke.Call_WS_meli;

public class _EncuentraEntregarPedidoPickup extends Action 
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

		String nombreArchivo = null;
		//Ruta donde se guardara el fichero
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
		
		try {
			
			String idPedido = request.getParameter("idPedido");
			Long pedido = new Long(idPedido);
			String vendor = "";
			String doc = "";
			String firma = "";
			int i=0;
			for(String s:strings){
				if(i==0){
					vendor=s;
				}
				else if(i==1){
					doc=s;
				}
				else{
					firma=s;
				}
				i++;
			}
			
			log.RegistrarEntrega(pedido, doc, firma,idEmpresa, vendor);
			log.updateEcommerceEstado(pedido, 6,idEmpresa,uLog);
			log.vaciarOjoEcommerce(pedido,idEmpresa,true);
			if(log.esMLPedido(pedido,idEmpresa))
			{
				//Call_WS_meli ws = new Call_WS_meli();
				List<DataIDDescripcion> tokens = new ArrayList<>();
				DataIDDescripcion data;
				List<DataIDDescDescripcion> canales = log.EcommercedarCanalesML(idEmpresa);
						
				String usrCanal1 = canales.get(0).getDesc();
				String secretCanal1 = canales.get(0).getDescripcion();
				String seller1 = canales.get(0).getDescII();
				//String access_token1= ws.getToken(usrCanal1,secretCanal1);
				data= new DataIDDescripcion();
				data.setDescripcion(seller1);
				//data.setDescripcionB(access_token1);
				tokens.add(data);
				
				String usrCanal2 = canales.get(1).getDesc();
				String secretCanal2 = canales.get(1).getDescripcion();
				//String access_token2= ws.getToken(usrCanal2,secretCanal2);
				String seller2 = canales.get(1).getDescII();	
				data= new DataIDDescripcion();
				data.setDescripcion(seller2);
				//data.setDescripcionB(access_token2);
				tokens.add(data);
				
				//ws.PostFeedback(pedido, tokens);
			}
			else
			{
				//sino, llamar al WS de analoga para marcar como listo para retirar
				
				String jotason="[ "+
						 "     { "+
						 "        \"id\":\""+pedido+"\", "+
						 "        \"estado\":\"entregado\" "+
						 "     } "+
						 "]";
				
				if(idEmpresa != 4) {
					Call_WS_analoga call = new Call_WS_analoga();
					DataIDDescDescripcion canal = log.EcommercedarCanalAnaloga(pedido,idEmpresa);
					call.setPedidos(jotason,canal.getId(),idEmpresa);
				}
				
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("menError", "Sucedio un error mientras se entregaba el pedido, verifique en el sistema si el pedido se entrego correctamente");
			return mapping.findForward("ok");
		}
		request.setAttribute("menError", "Pedido entregado correctamente");
		return mapping.findForward("ok");
	}

}
