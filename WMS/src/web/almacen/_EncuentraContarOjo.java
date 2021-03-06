package web.almacen;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;

import beans.Usuario;

public class _EncuentraContarOjo extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		List<String> queries = new ArrayList<>();
		
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String error ="ATENCION"; 
		String idOjo = request.getParameter("idOjo");
		
		
		try {
			boolean inventario = Logica.loginEncuentra_depoInvent(uLog, idEmpresa).isInventario();
			if(uLog.isInventario()!=inventario){
				uLog.setInventario(inventario);
				String comentario = "DESACTIVADO";
				if(inventario){
					comentario = "ACTIVADO";
				}
				uLog.registrarEventoMin(session.getId(), "Se actualizo el modo inventario a: "+comentario);
			}
		} catch (Exception e) {
			request.setAttribute("menError", "Sucedio un error verificando el modo inventario");
			return mapping.findForward("ok");
		}
		
		 
		idOjo = idOjo.toUpperCase();
		
		
		//Hashtable<String, String> artBarra = (Hashtable<String, String>) session.getAttribute("barras");
	
		ServletContext context = request.getSession().getServletContext();
		
		Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
		
		int deposito = Integer.parseInt(uLog.getDeposito());
		int existe = Logica.encuentraExisteUbica(idOjo, idEmpresa, deposito);		
		if(existe==1)
		{
			String articulosComa = request.getParameter("articulos");
			
			
			articulosComa = articulosComa.replace(" ", "");
			articulosComa = articulosComa.replaceAll("[\n\r]","");
			
			String [] desorden = articulosComa.split(",");
			
			
			List<DataIDDescripcion> ordenable = new ArrayList<>();
			
			
			for (String s : desorden) 
			{
				
				
				if(s.contains("__"))
				{
					String qr = "";
					qr = s;
					String[]ar = s.split("__");
					
					String ser = ar[0].substring(0,2);
					for (int ie = 1; ie < ar.length; ie++) 
					{
						try
						{
							String []ar2 = ar[ie].split("/");
							String conSer = ar2[1].replace(ser, "---");
							String [] ar3 = conSer.split("---");
							int cant = Integer.parseInt(ar3[0]);
							qr = ar2[0];
							for (int i = 0; i < cant; i++) 
							{
								DataIDDescripcion d = new DataIDDescripcion(0,qr);
								ordenable.add(d);
							}
							
						}
						catch (Exception e)
						{
							
						}
						
					}
					
					
				}
				else
				{
					try {
						String[]data = s.split(":");
						int cant = Integer.parseInt(data[1]);
						
						DataIDDescripcion d = new DataIDDescripcion(cant,data[0]);
						ordenable.add(d);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
				
			}
			
			
			List<DataIDDescripcion> articulosCant = Logica.EncuentraSortearListaDICant(ordenable);
						
			List<DataIDDescripcion> articulosReales = new ArrayList<>();
			
			int linea = 0;
			int lineaOk = 0;
			
			for (DataIDDescripcion d : articulosCant) 
			{
				if(!d.getDescripcion().equals(""))
				{
					linea ++;
					String artReal= null;
					try
					{
						artReal = artBarra.get(d.getDescripcion()).toUpperCase();
					}
					catch(Exception e)
					{
						try
						{
							artReal = Logica.encuentraCodArticulo(d.getDescripcion(),idEmpresa).getId();
						}
						
						catch(Exception e2)
						{
							error += ". Articulo desconocido en linea "+linea +" ("+d.getDescripcion()+")";
						}
					}
					
					if(artReal!=null)
					{
						for (int i = 0; i < d.getId(); i++) 
						{
							lineaOk ++;
							DataIDDescripcion da = new DataIDDescripcion(0,artReal);
							articulosReales.add(da);
						}
					}
					else
					{
						error += ". Articulo desconocido en linea "+linea +" ("+d.getDescripcion()+")"; 
					}
				}
				
			}
			
			
			
			
			
			
			String operacion = request.getParameter("opperation");
			if(operacion==null)
			{
				operacion = "act";
			}
			List<DataIDDescripcion> articulosCantReales = Logica.EncuentraSortearListaDI(articulosReales);
			switch (operacion) 
			{
				case "act":
				{
					
					error += ". Se actualizaron "+lineaOk+" lineas para el CONTEO de la ubicaci?n "+ idOjo;
					if(articulosReales.size()==0)
					{
						queries.addAll(Logica.encuentraUpdateCountOjos(idOjo, "",0,uLog.getNumero(),idEmpresa));
					}
					else
					{
					
						queries.addAll(Logica.encuentraUpdateCountOjos(idOjo, "",0, uLog.getNumero(),idEmpresa));
					
						for (DataIDDescripcion d : articulosCantReales)
						{
							//System.out.println(d.getId()+" "+d.getDescripcion());
							queries.addAll(Logica.encuentraUpdateCountOjos(idOjo, d.getDescripcion(), d.getId(),uLog.getNumero(),idEmpresa));
							
							
						}
					}
					
					break;
				}
	
				case "add":
				{
					error += ". Se agregaron "+lineaOk+" lineas para el CONTEO de la ubicaci?n "+ idOjo;
					
					for (DataIDDescripcion d : articulosCantReales)
					{
						
						queries.addAll(Logica.encuentraUpdateCountOjos(idOjo, d.getDescripcion(), d.getId(),uLog.getNumero(),idEmpresa));
					}
					break;
				}
				
				case "del":
				{
				
					
					for (DataIDDescripcion d : articulosCantReales)
					{
						queries.addAll(Logica.encuentraBajarOjosCount(idOjo, d.getDescripcion(), d.getId(), uLog.getNumero(),"DEL",idEmpresa));
					}
					error += ". Se eliminaron "+lineaOk+" lineas para el CONTEO de la ubicaci?n "+ idOjo;
					break;
				}
			}
			
		}
		else
		{
			//request.setAttribute("menError", "No existe la ubicacion en el sistema");
			error += " .No existe la ubicacion "+idOjo+" en el sistema";
		}
		Logica.getEper().persistirTransacciones(queries);
		
		uLog.registrarEventoMin(session.getId(), "(Count_OJO)"+error);
		
		request.setAttribute("menError", error);
		return mapping.findForward("ok");
	}

}
