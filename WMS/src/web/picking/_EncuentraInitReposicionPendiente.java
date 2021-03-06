package web.picking;

import java.util.List;

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

public class _EncuentraInitReposicionPendiente extends Action{

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
		
 		String filtro = "";
		boolean pasaraPick = false;
		
		String central = request.getParameter("central");
		String mayorista = request.getParameter("mayorista");
		String tipoRe = request.getParameter("tipoRe");
		
		session.setAttribute("articulosRepo", null);
		
		if(central==null)
		{
			central = (String) session.getAttribute("central");
			
		}
		if(mayorista==null)
		{
			mayorista = (String) session.getAttribute("mayorista");
			if(mayorista==null)
			{
				mayorista="0";
			}
			
		}
		
		if(tipoRe == null) {
			tipoRe=(String) session.getAttribute("tipoRe");
		}
		
		
		
		if(tipoRe==null){
			tipoRe="0";
		}
		session.setAttribute("mayorista",mayorista);
		session.setAttribute("central",central);
		session.setAttribute("tipoRe", tipoRe);
		boolean centr = false;
		boolean mayo = false;
		if(central.equals("1"))
		{
			centr=true;
		}
		else
		{
			centr=false;
		}
		if(mayorista.equals("10"))
		{
			mayo=true;
			pasaraPick = true;
		}
		else if(mayorista.equals("1"))
		{
			mayo=true;
			filtro+=" Mayorista ";
		}
		else
		{
			mayo = false;
			filtro+=" Reposiciones ";
		}
		
		
		List<DataIDDescripcion> depositos= null;
		if(mayo)
		{
			depositos= Logica.darListaDepositos(100, true, pasaraPick,false,idEmpresa);
		}
		else
		{
			depositos= Logica.darListaDepositos(0,true,pasaraPick, false,idEmpresa);
		}
		
		depositos.remove(0); //saco todos
		
		List<DataIDDescripcion> marcas = Logica.darListaDataIdDescripcion("art_marca",idEmpresa);
		List<DataIDDescripcion> secciones = Logica.darListaDataIdDescripcion("art_seccion",idEmpresa);
		List<DataIDDescripcion> clases = Logica.darListaDataIdDescripcion("art_clase",idEmpresa);
		List<DataIDDescripcion> generos = Logica.darListaDataIdDescripcion("art_genero",idEmpresa);
		List<DataIDDescripcion> categorias = Logica.darListaDataIdDescripcion("art_categoria",idEmpresa);
		List<DataIDDescripcion> distribuciones = Logica.darListaDataIdDescripcion("vista_rep_art_distribucion",idEmpresa);
		List<DataIDDescripcion> canales = Logica.darListaDataIdDescripcionConsulMYSQL ("SELECT id, nombre FROM ecommerce_canal_ml WHERE idEmpresa="+idEmpresa);
		String couriersS = "";
		if(tipoRe.equals("2"))
		{
			couriersS ="SELECT ECE.idDeposito, ECE.nombre FROM ecommerce_envioml ECE INNER JOIN ecommerce_pedido_destino EPD ON ECE.idDeposito = EPD.idDestino AND ECE.IdEmpresa=EPD.IdEmpresa INNER JOIN ecommerce_pedido EP ON EP.idPedido = EPD.idPedido AND EP.IdEmpresa = EPD.IdEmpresa AND EP.EstadoEncuentra = 1 WHERE ECE.idEmpresa="+idEmpresa+" GROUP BY ECE.idDeposito, ECE.nombre;";
		}
		else 
		{
			int depoPrincipal = util.darParametroEmpresaINT(idEmpresa, 4);
			couriersS = "SELECT DISTINCT(destino) AS idDeposito, d.Nombre "+
					"FROM reposicion_articulos ra "+
					"INNER JOIN depositos d ON d.idDeposito=ra.Destino AND d.IdEmpresa=ra.IdEmpresa "+
					"WHERE origen = "+depoPrincipal+" and estado = 1 and ra.idempresa = "+idEmpresa ; 
		}
			
		List<DataIDDescripcion> couriers = Logica.darListaDataIdDescripcionConsulMYSQL (couriersS);
		
		if(tipoRe.equals("3"))
		{
			String query = "SELECT idZona, Descripcion FROM zonas WHERE idempresa = "+idEmpresa+";";
			List<DataIDDescripcion> zonas = Logica.darListaDataIdDescripcionMYSQLConsulta(query);
			zonas.remove(0);
			session.setAttribute("lstZonas", zonas);
		}
		

		session.setAttribute("marcas", marcas);
		session.setAttribute("distribuciones", distribuciones);
		session.setAttribute("secciones", secciones);
		session.setAttribute("clases", clases);
		session.setAttribute("generos", generos);
		session.setAttribute("categorias", categorias);
		session.setAttribute("canales", canales);
		
		session.setAttribute("couriers", couriers);
		
		//parametro seleccion de lineas
		boolean param_seleccion_lineas = util.darParametroEmpresaBool(idEmpresa, 45);
		session.setAttribute("param_seleccion_lineas", param_seleccion_lineas);
		
		
		uLog.registrarEventoMin(session.getId(), "Solicitando lista de picking pendiente "+ filtro);
			
		session.setAttribute("depositos", depositos);
		
		return mapping.findForward("ok");
		
		
		
		
	
	
	}
	
	
	
	
	
}








































