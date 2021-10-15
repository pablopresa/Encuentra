package web.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.DataArticulo;

public class _EncuentraAltaArticulo extends Action {
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
		
		String id=request.getParameter("Identificador");
		String descripcion=request.getParameter("Descripcion");
		String ancho=request.getParameter("Ancho");
		String alto=request.getParameter("Alto");
		String prof=request.getParameter("Profundidad");
		String stock=request.getParameter("Stock");
		String skuType=request.getParameter("skuType");
		String funcion=request.getParameter("function");
		
	
		DataArticulo da=new DataArticulo();
		da.setId(id);
		da.setDescripcion(descripcion);
		da.setAnchoCaja(Integer.parseInt(ancho));
		da.setAltoCaja(Integer.parseInt(alto));
		da.setProfCaja(Integer.parseInt(prof));
		da.setCantidad(Integer.parseInt(stock));
		da.setIdTypeSKU(Integer.parseInt(skuType));
		da.setFuncion(funcion);
		
		List<DataArticulo> articulos=(List<DataArticulo>) session.getAttribute("articulos");
		
		if(Logica.encuentraAltaUpdateArticulo(da,idEmpresa)){
			if(articulos==null){
				articulos = new ArrayList<>();
			}
			if(funcion.equals("100000")){
				DataArticulo da2;
				for(int i=0;i<articulos.size();i++){
					if(articulos.get(i).getId().equals(id)){
						articulos.remove(i);
					}
				}
			}
			articulos.add(da);
		}
		session.setAttribute("articulos", articulos);
		request.setAttribute("idEdita", "-86");
		
		return mapping.findForward("ok");
	
	
	}

}
