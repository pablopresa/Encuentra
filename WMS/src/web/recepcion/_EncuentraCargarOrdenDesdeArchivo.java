package web.recepcion;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataOC;
import dataTypes.DataRecepcion;
import helper.PropertiesHelper;


public class _EncuentraCargarOrdenDesdeArchivo extends Action{
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
			
			String texto = request.getParameter("Submit");
			
			DataRecepcion recepcion = new DataRecepcion();
			
			
			
			boolean importa = false;
			if(request.getParameter("recTipo")==null)
			{
				if(session.getAttribute("recTipo")!=null)
				{
					if(session.getAttribute("recTipo").equals("IMP"))
					{
						importa = true;
					}
				}
				
			}
			else if(request.getParameter("recTipo").equals("IMP"))
			{
				importa = true;
				session.setAttribute("recTipo", "IMP");
				
				
			}
			
			List<DataDescDescripcion> proveedores=Logica.darProveedoresParaOrdenes(importa);
			
			session.setAttribute("proveedores", proveedores);
				
			if(texto!=null) 
			{
				if(texto.equals("Buscar"))
				{
					String provedor = request.getParameter("provedor");
					DataDescDescripcion objProvedor=null;
					for (DataDescDescripcion d: proveedores) {
						if(d.getId().equals(provedor))
						{
							objProvedor=d;
							recepcion.setProveedor(objProvedor);
							break;
						}
					}
					List<DataOC>ordenes=Logica.darOrdenesDeCompra(objProvedor, importa,idEmpresa,null);
					for (DataOC oc : ordenes) 
					{
						oc.setImportada(importa);
					}
					recepcion.setImporta(importa);
					session.setAttribute("ordenes",ordenes);
					session.setAttribute("recepcion", recepcion);
					session.setAttribute("recTipo", null);
					request.setAttribute("provedor", provedor);
					
				}
				else
				{
					if(texto.equals("Excel"))
					{
						String provedor = (String) session.getAttribute("provedor");
						DataDescDescripcion objProvedor=null;
						for (DataDescDescripcion d: proveedores) {
							if(d.getId().equals(provedor))
							{
								objProvedor=d;
								recepcion.setProveedor(objProvedor);
								break;
							}
						}
						
						List<DataIDDescripcion> ordenesAux = (List<DataIDDescripcion>) session.getAttribute("ordenesAux");
						//List<DataOC>ordenes= new ArrayList<>();
						//ordenes = Logica.armarLineasOrdenesDeCompra(ordenesAux,objProvedor);
						List<DataOC>ordenes=Logica.darOrdenesDeCompra(objProvedor, importa,idEmpresa,ordenesAux);
						ordenes = Logica.ControlarOCExcel(ordenes, ordenesAux);
						
						for (DataOC oc : ordenes) 
						{
							oc.setImportada(importa);
						}
						recepcion.setImporta(importa);
						session.setAttribute("ordenes",ordenes);
						session.setAttribute("recepcion", recepcion);
						session.setAttribute("recTipo", null);
						session.setAttribute("provedor", null);
						request.setAttribute("provedor", provedor);
						
					}
				}
					
			}
			if(importa)
			{
				request.setAttribute("recTipo", "IMP");
			}
			else
			{
				request.setAttribute("recTipo", null);
				session.setAttribute("recTipo", null);
			}
			
			return mapping.findForward("ok");
		}
}



