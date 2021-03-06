package web.ecommerce;

import helper.PropertiesHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import beans.Usuario;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.EncuentraPedido;

public class _EcommerceAltaFileDestino extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession(true);
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario usu = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(usu);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		List<DataIDDescripcion> estadosEncuentra = Logica.darListaDataIdDescripcionMYSQLConsulta("select id,descripcion from ecommerce_estado_encuentra where idEmpresa="+idEmpresa);
		
		String nombreArchivo = null;
		PropertiesHelper pH=new PropertiesHelper("paths");
		pH.loadProperties();
		
		
		//Ruta donde se guardara el fichero
		File destino=new File(pH.getValue("pdf"));
		
	
		//destino.delete();
		//File destino=new File("C:/");
		// Convertimos el HTTPRequest en un ContextRequest,
		// este paso es necesario en la ultima version,
		// ya que los metodos de las versiones anteriores
		// se han quedado desfasados.
		ServletRequestContext src=new ServletRequestContext(request);
		
		//Si el formulario es enviado con Multipart
		if(FileUploadBase.isMultipartContent(src))
		{
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
			List <String> strings = new ArrayList<>();
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
				else
				{
					//Si no, es un fichero y lo subimos al servidor.
					//Primero creamos un objeto file a partir del nombre del fichero.
					file=new File(item.getName());
					nombreArchivo = item.getName();
					//Lo escribimos en el disco
					// usando la ruta donde se guardara el fichero
					// y cogiendo el nombre del file
					// Nota: Se podria hacer usando el objeto item en vez del file directamente
					// Pero esto puede causar incompatibilidades segun que navegador, ya que 
					// algunos solo pasan el nombre del fichero subido, pero otros
					// como Iexplorer, pasan la ruta absoluta, y esto crea un peque?o problema al escribir
					// el fichero en el servidor.
					try
					{
						item.write(new File(destino,file.getName()));
					}
					catch(Exception e)
					{
						System.out.println("no habia archivo");
					}
					
					
					
					
				}
				
				
				
			}
		
			
			
			String urlRedirect = (String) session.getAttribute("urlRedirect");
			
			Long idPedido = Long.parseLong(strings.get(0));
			int des  = Integer.parseInt(strings.get(2));
			String estado =strings.get(1);
			
			EncuentraPedido p = new EncuentraPedido();
			p.setIdPedido(idPedido);
			
			int estadoInt=-1;
			for(DataIDDescripcion e: estadosEncuentra){
				if (estado.equals(e.getDescripcion()))
				{
					estadoInt=e.getId();
				}
			}
			
			if(nombreArchivo!=null && !nombreArchivo.equals(""))
			{
				String httpPath = pH.getValue("HTTP_pdf");
				
				nombreArchivo=httpPath+"/"+nombreArchivo;
				
				p.setUrlEtiqueta(nombreArchivo);
				p.updateEtiqueta(des, idEmpresa);
				
				if(estadoInt==25){
					Logica.updateEcommerceEstado(idPedido, 3,idEmpresa,usu);
				}
				
				Logica.logPedido(idPedido,usu.getNumero(),estadoInt,"Cambio de Destino ==> "+des,0,idEmpresa);
				Logica.logPedido(idPedido,usu.getNumero(),estadoInt,"Cambio de Etiqueta, se asoci? nueva etiqueta",0,idEmpresa);
			}
			else
			{
				
				//p.updateDestino(des);
				
				
				//NICO
				String url = "";
				int pick = Integer.parseInt(Logica.Consulta("select pickup from ecommerce_envioml where idEmpresa="+idEmpresa+" AND idDeposito =" + des));
				
				
				
				if (pick == 1){
					String local = String.format("%02d", des);
					String eti = "https://www.stadium.com.uy/public/ctm/"+local+".pdf";
					p.setUrlEtiqueta(eti);
					p.updateEtiqueta(des, idEmpresa);
					
					if(estadoInt==25){
						Logica.updateEcommerceEstado(idPedido, 3,idEmpresa,usu);
					}
					
					Logica.logPedido(idPedido,usu.getNumero(),estadoInt,"Cambio de Destino ==> "+des,0,idEmpresa);
					Logica.logPedido(idPedido,usu.getNumero(),estadoInt,"Cambio de Etiqueta ==> "+eti,0,idEmpresa);
					
				}
				else{
					//p.updateDestino(des);
					p.setUrlEtiqueta("");
					p.updateEtiqueta(des, idEmpresa);
					
					Logica.logPedido(idPedido,usu.getNumero(),estadoInt,"Cambio de Destino ==> "+des+", pendiente de etiqueta",0,idEmpresa);
					
				}
				
				
				
				
			}
			
			request.setAttribute("URL", urlRedirect);
			
		
			
		}
		return mapping.findForward("ok");
	}
		
}
