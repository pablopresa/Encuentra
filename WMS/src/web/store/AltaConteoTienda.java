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

public class AltaConteoTienda extends Action 
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

		List<ArticuloConteo> articulos = (List<ArticuloConteo>) session.getAttribute("articuloC");
		List<ArticuloConteo> articulosIn = new ArrayList<>();
		String filtro = (String) session.getAttribute("filtros");
		int totalArticulos = 0;
		int totalUnidades = 0;
		if(articulos==null || articulos.isEmpty()) {
			request.setAttribute("menError", "ATENCION: debe seleccionar al menos un articulo");
			return mapping.findForward("ok");
		}
		
		for (ArticuloConteo a : articulos) 
		{
			String in = request.getParameter(a.getArticulo());
			if(in!=null && in.equals("on"))
			{
				articulosIn.add(a);
				totalArticulos++;
				totalUnidades = totalUnidades+a.getStock();
			}
		}
		
		ConteoTiendas conteo = new ConteoTiendas();
		int idDepo = Integer.parseInt(uLog.getDeposito());
		conteo.setDeposito(idDepo);
		conteo.setArticulos(articulosIn);
		conteo.setUsuarioPide(new DataIDDescripcion(uLog.getNumero(), uLog.getNick()));
		conteo.setDescripcion("Solicitado por: "+uLog.getNick()+ " "+ filtro);
		conteo.setTotalUnidades(totalUnidades);
		conteo.setTotalArticulos(totalArticulos);
		
		int iConteo = log.altaConteo(conteo, idEmpresa);
		if(iConteo!=0)
		{
			request.setAttribute("menError", "Se guardo el conteo con el ID "+iConteo+" Solicitado por: "+uLog.getNick()+" "+ filtro);
		}
		else
		{
			request.setAttribute("menError", "ATENCION: No se guardo el conteo");
		}
		
		session.setAttribute("articuloC", null);
		
		
		return mapping.findForward("ok");
	}

}
