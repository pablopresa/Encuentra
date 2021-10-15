package web.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerHTML;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.ArticuloConteo;
import beans.encuentra.ConteoTiendas;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class UpdateConteo extends Action 
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

		String error ="ATENCION"; 
		String idOjo = request.getParameter("idOjo");
		 
		idOjo = idOjo.toUpperCase();
		
		
		ServletContext context = request.getSession().getServletContext();
		
		Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
		String articulosComa = request.getParameter("articulos");
		
		
		articulosComa = articulosComa.replace(" ", "");
		articulosComa = articulosComa.replaceAll("[\n\r]","");
		
		String [] desorden = articulosComa.split(",");
		
		
		List<DataIDDescripcion> ordenable = new ArrayList<>();
		
		
		for (String s : desorden) 
		{
			try 
			{
				String[]data = s.split(":");
				int cant = Integer.parseInt(data[1]);
				
				DataIDDescripcion d = new DataIDDescripcion(cant,data[0]);
				ordenable.add(d);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
		}
		
		
		List<DataIDDescripcion> articulosCant = log.EncuentraSortearListaDICant(ordenable);
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
					error += ". Articulo desconocido en linea "+linea +" ("+d.getDescripcion()+")";
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
		
		error += ". Se guardaron "+lineaOk+" lineas del conteo ";
		ConteoTiendas conteo = (ConteoTiendas) session.getAttribute("ConteoSel"); 
		
		List<DataIDDescripcion> articulosCantReales = log.EncuentraSortearListaDI(articulosReales);
		
		for (DataIDDescripcion d : articulosCantReales)
		{
			int enLista = 0;
			for (ArticuloConteo ac : conteo.getArticulos()) 
			{
				if(ac.getArticulo().equals(d.getDescripcion()))
				{
					enLista = 1;
					break;
				}
			}
			//`idConteo`, `idArticulo`, `CantidadContada`, `usuarioCuenta`, `enLista`, `idOjo`
			log.AltaLineaConteo(conteo.getIdConteo(),d.getDescripcion(),d.getId(),uLog.getNumero(),enLista,idOjo,idEmpresa);
			
			
		}		
		
		request.setAttribute("menError", error);
		return mapping.findForward("ok");
	}

}
