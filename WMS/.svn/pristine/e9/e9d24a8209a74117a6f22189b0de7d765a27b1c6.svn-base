package web.ecommerce;

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
import dataTypes.DataDetallePedido;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import eCommerce_jsonObjectsII.EncuentraPedido;
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

public class UpdateDestinoEcommerce extends Action 
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
		
		String url = "";
		
		String nombreArchivo = null;
		//Ruta donde se guardara el fichero
		 PropertiesHelper pH=new PropertiesHelper("paths");
		 pH.loadProperties();
		 String path = pH.getValue("pdf");
		 String pathSalida = pH.getValue("HTTP_pdf")+"/";
		 
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
			else {
				try {
					file=new File(item.getName());
	                nombreArchivo = item.getName();
	                item.write(new File(destino,file.getName()));
	                path += item.getName();
	                pathSalida += item.getName();
	                url = pathSalida;
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			
			
		}		
		
		}		
		
		try {			
			String idPedido = "";
			Long pedido = new Long("0");
			List<DataDetallePedido> pedidos = null;
			
			int idDestino = 0;
			String tracking = "";
			int i=0;
			for(String s:strings){
				switch (i) {
				case 0:
					idPedido = s;
					pedido = new Long(idPedido);
					
					pedidos = log.darListaDetallePedidosEcommerce(idPedido, "", "", null, null, "", "",0,null,"","",idEmpresa,"", "", "",true);
					if(pedidos.isEmpty()) {
						request.setAttribute("menError", "No se encontro el pedido");
						return mapping.findForward("ok");
					}
				break;
				case 1:
					try{idDestino = Integer.parseInt(s);}catch(Exception ex) {System.out.println("sin destino");}
					break;
				case 2:
						tracking = s;			
					break;
				case 3:
					
					break;

				default:
					break;
				}
				i++;
			}
			
			//seteo pedido
			boolean cancelarPickup = false;
			if(pedidos.get(0).getDeposito()==pedidos.get(0).getIdDestino() 
					&&
					pedidos.get(0).getIdDestino()!=idDestino) {				
				cancelarPickup = true;
			}
			EncuentraPedido p = new EncuentraPedido();
			p.setIdPedido(pedido);
			boolean updatedestino = p.updateLabelShipping(idDestino, tracking, url, 1, idEmpresa, cancelarPickup,uLog);
			
			if(pedidos.get(0).getIdEstado()>2)
			{
				String pedidoSTR = pedido + "";
				String origenSTR = pedidos.get(0).getIdDestino()+"P";
				String destinoSTR = idDestino + "P";
				
				log.encuentraBajaArticulosOjos(1,pedidoSTR,origenSTR,idEmpresa);					//BAJAR DE LA ZONA DE PICKING
				log.encuentraMoverOjos(destinoSTR,pedidoSTR,1,uLog.getNumero(),idEmpresa);			//AGREGO ARTICULO A ZONA DE PICKING NUEVA
				log.IngresarMovimientoArticulo(origenSTR,destinoSTR, pedidoSTR, 1, uLog.getNumero(),idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA
			}
			
			if(updatedestino) {
				request.setAttribute("menError", "Informacion ingresada correctamente");
			}
			else {
				request.setAttribute("menError", "A este pedido ya no se le puede modificar el destino");
			}
				
			return mapping.findForward("ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("menError", "Sucedio un error mientras se ingresaba la informacion proporcionada");
			return mapping.findForward("ok");
		}		
	}

}
