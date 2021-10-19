package web.picking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import logica.Logica;
import logica.Utilidades;
import models.PickingBulto;
import beans.elementoPicking;

public class PickingPackagePrueba extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		try {
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}			
			
			
			
			Hashtable<String, elementoPicking> articulos = new Hashtable<>();
			elementoPicking e = new elementoPicking(2,"0000441880440",0);
			articulos.put("0000441880440", e);
			e = new elementoPicking(2,"0001037OR0400",0);
			articulos.put("0001037OR0400", e);
			e = new elementoPicking(2,"0002214500000",0);
			articulos.put("0002214500000", e);
			e = new elementoPicking(2,"0002210NE0000",0);
			articulos.put("0002210NE0000", e);
			
			Hashtable<String, List<DataLineaRepo>> articulo_lineasRepo = new Hashtable<String, List<DataLineaRepo>>();
			List<DataLineaRepo> lista = null;
			DataLineaRepo d = null;
			lista=new ArrayList<>(); d = new DataLineaRepo(); d.setIdArticulo("0000441880440"); d.setSolicitada(2); lista.add(d); articulo_lineasRepo.put("0000441880440", lista);
			lista=new ArrayList<>(); d = new DataLineaRepo(); d.setIdArticulo("0001037OR0400"); d.setSolicitada(2); lista.add(d); articulo_lineasRepo.put("0001037OR0400", lista);
			lista=new ArrayList<>(); d = new DataLineaRepo(); d.setIdArticulo("0002214500000"); d.setSolicitada(2); lista.add(d); articulo_lineasRepo.put("0002214500000", lista);
			lista=new ArrayList<>(); d = new DataLineaRepo(); d.setIdArticulo("0002210NE0000"); d.setSolicitada(2); lista.add(d); articulo_lineasRepo.put("0002210NE0000", lista);
			
			String articulosStr = "'0000441880440','0001037OR0400','0002214500000','0002210NE0000'";
			
			Hashtable<String, String> barras = Logica.darBarrasHT(articulosStr, idEmpresa);
			
			PickingBulto pickingBulto = new PickingBulto();
			pickingBulto.setArticulo_lineasRepo(articulo_lineasRepo);
			pickingBulto.setElementos(articulos);
			pickingBulto.setBarras(barras);
			pickingBulto.setCantidadTotal(8);
			pickingBulto.setCount(0);			
			pickingBulto.setIdPicking(1000);
			pickingBulto.setIdBulto("hola123");
			pickingBulto.setUbicacion("A25");
			
			
			session.setAttribute("modeloPickingBulto", pickingBulto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return mapping.findForward("pick");
	}
	
}
