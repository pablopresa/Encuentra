package web.almacen;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class _EncuentraBajaOjo extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		
		Logica logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String error ="ATENCION";
		String idOjo = request.getParameter("idOjo");
		int email = 0;
		try
		{
			email = Integer.parseInt(request.getParameter("email"));
		}
		catch (Exception e)
		{
			
		}
		
		 
		idOjo = idOjo.toUpperCase();
		
		
		//Hashtable<String, String> artBarra = (Hashtable<String, String>) session.getAttribute("barras");
	
		ServletContext context = request.getSession().getServletContext();
		
		Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
		
		int deposito = Integer.parseInt(uLog.getDeposito());
		int existe = logica.encuentraExisteUbica(idOjo, idEmpresa, deposito);
		
		if(existe==1)
		{
			String articulosComa = request.getParameter("articulos");
			
			if(!articulosComa.equals("")) {
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
					String[]data = s.split(":");
					int cant = Integer.parseInt(data[1]);
					
					DataIDDescripcion d = new DataIDDescripcion(cant,data[0]);
					ordenable.add(d);
				}
				
				
			}
			
			
			List<DataIDDescripcion> articulosCant = logica.EncuentraSortearListaDICant(ordenable);
			
			List<DataIDDescripcion> articulosReales = new ArrayList<>();
			
			int linea = 0;
			int lineaOk = 0;
			
			for (DataIDDescripcion d : articulosCant) 
			{
				linea ++;
				String artReal= null;
				
				artReal = artBarra.get(d.getDescripcion());
				
				if(artReal==null)
				{
					try
					{
						artReal = logica.encuentraCodArticulo(d.getDescripcion(),idEmpresa).getId();
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
			
			//error += ". Se bajaron "+lineaOk+" lineas para la ubicaci?n "+ idOjo;
			
			
			List<DataIDDescripcion> articulosCantReales = logica.EncuentraSortearListaDI(articulosReales);
			
			lineaOk = 0;
			
			for (DataIDDescripcion d : articulosCantReales)
			{
				//System.out.println(d.getId()+" "+d.getDescripcion());
				int pude= 0;
				pude = logica.encuentraBajarOjos(idOjo, d.getDescripcion(), d.getId(), uLog.getNumero(),"DEL",idEmpresa);
				
				if (pude==0)
					error += " El art?culo / bulto "+d.getDescripcion()+" no pudo ser bajado debido a que no se puede cumplir con la cantidad o tiene unidades reservadas\\n ";
				else
					lineaOk++;
					
			}
				
			error += "\\nSe bajaron "+lineaOk+" lineas para la ubicaci?n "+ idOjo;
		}
		}
		else
		{
			//request.setAttribute("menError", "No existe la ubicacion en el sistema");
			error += " .No existe la ubicacion "+idOjo+" en el sistema";
		}
		
		uLog.registrarEventoMin(session.getId(), "(BAJAR_OJO)"+error);
		
		request.setAttribute("menError", error);
		return mapping.findForward("ok");
	}

}
