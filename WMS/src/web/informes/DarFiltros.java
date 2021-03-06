package web.informes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.reportes.Filtro;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class DarFiltros extends Action 
{

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
		String reporte = request.getParameter("reporte");
		String nombreReporte = request.getParameter("nombre");
		session.setAttribute("reporteSel", reporte);
		session.setAttribute("nombreReporte", nombreReporte);
		List<Filtro> filtros = new ArrayList<>();

		session.setAttribute("lista", null);

		switch (reporte) 
		{
		case "movsXArt":
		{
			Filtro articulo = new Filtro("text", "articulo", "Indique Articulo", "",true, "Articulo");
			Filtro fechas = new Filtro("rango", "fechas", "", "",true, "Fechas");

			filtros.add(fechas);
			filtros.add(articulo);

			session.setAttribute("filtros", filtros);
			break;
		}
		case "conteos":
		{
			
			Filtro fechas = new Filtro("rango", "fechas", "", "",true, "Fechas");
			filtros.add(fechas);
			
			Filtro estanteriasF = new Filtro("multi", "estanteria", "Seleccione Estanteria", "",false, "Estanterias");
			List<DataIDDescripcion> estanterias = Logica.darEstanterias(idEmpresa);
			estanteriasF.setValues(estanterias);
			filtros.add(estanteriasF);
			
			Filtro ojosF = new Filtro("multi", "ojo", "Seleccione ojo", "",false, "Ojos");
			List<DataIDDescripcion> ojos = Logica.darOjosConteados(idEmpresa);
			ojos.remove(0);
			ojosF.setValues(ojos);
			filtros.add(ojosF);
			

			session.setAttribute("filtros", filtros);
			break;
		}
		case "reposicionamiento":
		{
			Filtro articulo = new Filtro("text", "articulo", "Indique Articulo", "",false, "Articulo");

			Filtro estanteriasF = new Filtro("multi", "estanteria", "Seleccione Estanteria", "",false, "Estanterias");
			List<DataIDDescripcion> estanterias = Logica.darEstanterias(idEmpresa);
			estanteriasF.setValues(estanterias);

			List<DataIDDescripcion> marcas = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_marca WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro marcasF = new Filtro("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			marcasF.setValues(marcas);

			List<DataIDDescripcion> generos = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_genero WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro generosF = new Filtro("multi", "genero", "Seleccione generos", "",false, "Generos");
			generosF.setValues(generos);

			List<DataIDDescripcion> categorias = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_categoria WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro categoriasF = new Filtro("multi", "categoria", "Seleccione categorias", "",false, "Categorias");
			categoriasF.setValues(categorias);

			List<DataIDDescripcion> clases = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_clase WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro clasesF = new Filtro("multi", "clases", "Seleccione clases", "",false, "Clases");
			clasesF.setValues(clases);

			filtros.add(articulo);
			filtros.add(estanteriasF);
			filtros.add(marcasF);
			filtros.add(generosF);
			filtros.add(categoriasF);
			filtros.add(clasesF);

			session.setAttribute("filtros", filtros);
			break;
		}
		case "inventXUbi":
		{
			Filtro articulo = new Filtro("text", "articulo", "Indique Articulo", "",false, "Articulo");

			Filtro estanteriasF = new Filtro("multi", "estanteria", "Seleccione Estanteria", "",false, "Estanterias");
			List<DataIDDescripcion> estanterias = Logica.darEstanterias(idEmpresa);
			estanteriasF.setValues(estanterias);

			List<DataIDDescripcion> marcas = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_marca WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro marcasF = new Filtro("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			marcasF.setValues(marcas);


			List<DataIDDescripcion> generos = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_genero WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro generosF = new Filtro("multi", "genero", "Seleccione generos", "",false, "Generos");
			generosF.setValues(generos);

			List<DataIDDescripcion> categorias = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_categoria WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro categoriasF = new Filtro("multi", "categoria", "Seleccione categorias", "",false, "Categorias");
			categoriasF.setValues(categorias);

			List<DataIDDescripcion> clases = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_clase WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro clasesF = new Filtro("multi", "clases", "Seleccione clases", "",false, "Clases");
			clasesF.setValues(clases);

			Filtro agruparArt = new Filtro("switch", "agruparArt", "Seleccione clases", "",false, "Agrupar Articulos");
			agruparArt.setId("switch-0");

			filtros.add(articulo);
			filtros.add(estanteriasF);
			filtros.add(marcasF);
			filtros.add(generosF);
			filtros.add(categoriasF);
			filtros.add(clasesF);
			filtros.add(agruparArt);

			session.setAttribute("filtros", filtros);
			break;
		}
		case "inventDisponible":
		{
			Filtro articulo = new Filtro("text", "articulo", "Indique Articulo", "",false, "Articulo");

			List<DataIDDescripcion> estanterias = Logica.darEstanterias(idEmpresa, Integer.parseInt(uLog.getDeposito()));
			Filtro estanteriasF = new Filtro("multi", "estanteria", "Seleccione Estanteria", "",false, "Estanterias");
			estanteriasF.setValues(estanterias);

			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<DataIDDescripcion> depositos = Logica.darListaDataIdDescripcionConsulMYSQL("select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000\r\n UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");
			Filtro depositosF = new Filtro("multi", "deposito", "Seleccione Deposito", "",false, "Depositos");
			depositosF.setValues(depositos);

			List<DataIDDescripcion> marcas = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_marca WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro marcasF = new Filtro("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			marcasF.setValues(marcas);


			List<DataIDDescripcion> generos = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_genero WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro generosF = new Filtro("multi", "genero", "Seleccione generos", "",false, "Generos");
			generosF.setValues(generos);

			List<DataIDDescripcion> categorias = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_categoria WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro categoriasF = new Filtro("multi", "categoria", "Seleccione categorias", "",false, "Categorias");
			categoriasF.setValues(categorias);

			List<DataIDDescripcion> clases = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_clase WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro clasesF = new Filtro("multi", "clases", "Seleccione clases", "",false, "Clases");
			clasesF.setValues(clases);

			filtros.add(articulo);
			filtros.add(estanteriasF);
			filtros.add(depositosF);
			filtros.add(marcasF);
			filtros.add(generosF);
			filtros.add(categoriasF);
			filtros.add(clasesF);


			session.setAttribute("filtros", filtros);
			break;
		}
		case "recepMovsXArt":
		{
			Filtro articulo = new Filtro("text", "articulo", "Indique Articulo", "",false, "Articulo");

			Filtro proveedor = new Filtro("text", "proveedor", "Indique Proveedor", "",false, "Proveedor");

			Filtro folio = new Filtro("text", "folio", "Indique Folio", "",false, "Folio");

			List<DataIDDescripcion> marcas = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_marca WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro marcasF = new Filtro("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			marcasF.setValues(marcas);

			List<DataIDDescripcion> clases = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_clase WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
			Filtro clasesF = new Filtro("multi", "clases", "Seleccione clases", "",false, "Clases");
			clasesF.setValues(clases);

			Filtro fechas = new Filtro("rango", "fechas", "", "",true, "Fechas");

			filtros.add(articulo);
			filtros.add(proveedor);
			filtros.add(folio);
			filtros.add(marcasF);
			filtros.add(clasesF);
			filtros.add(fechas);

			session.setAttribute("filtros", filtros);
			break;
		}
		case "productividadPicking":
		{
			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<? extends Object> params1 = Arrays.asList("multi", "deposito", "Seleccione Deposito", "",false, "Depositos");
			NodoArgs na1 = new NodoArgs("multiConsulta", (List<Object>) params1, "select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000 UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");

			List<? extends Object> params2 = Arrays.asList("multi", "usuario", "Seleccione Usuario", "",false, "Usuarios");
			NodoArgs na2 = new NodoArgs("multiConsulta", (List<Object>) params2, "SELECT idUsuario, CONCAT(u.Nombre,' ',u.apellido) AS nick FROM usuarios u WHERE u.idEmpresa = "+idEmpresa+" AND idUsuario NOT IN (-10,-1,0,99,100,1) AND baja=0 ORDER BY nick;");

			List<? extends Object> params3 = Arrays.asList("rango", "fechasPickeada", "", "",true, "Fechas inicio");
			NodoArgs na3 = new NodoArgs("text", (List<Object>) params3);

			List<? extends Object> params4 = Arrays.asList("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			NodoArgs na4 = new NodoArgs("multiGenerico", (List<Object>) params4);

			List<? extends Object> params5 = Arrays.asList("multi", "genero", "Seleccione generos", "",false, "Generos");
			NodoArgs na5 = new NodoArgs("multiGenerico", (List<Object>) params5);

			List<? extends Object> params6 = Arrays.asList("multi", "categoria", "Seleccione categorias", "",false, "Categorias");
			NodoArgs na6 = new NodoArgs("multiGenerico", (List<Object>) params6);

			List<? extends Object> params7 = Arrays.asList("multi", "clase", "Seleccione clases", "",false, "Clases");
			NodoArgs na7 = new NodoArgs("multiGenerico", (List<Object>) params7);

			List<NodoArgs> listPrueba = Arrays.asList(na1,na2,na3,na4,na5,na6,na7);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));

			break;
		}
		case "picking":
		{
			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<? extends Object> params1 = Arrays.asList("multi", "deposito", "Seleccione Deposito", "",false, "Depositos");
			NodoArgs na1 = new NodoArgs("multiConsulta", (List<Object>) params1, "select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000 UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");

			List<? extends Object> params2 = Arrays.asList("rango", "fechas", "", "",true, "Fechas inicio");
			NodoArgs na2 = new NodoArgs("text", (List<Object>) params2);

			List<? extends Object> params4 = Arrays.asList("text", "articulo", "Indique Articulo", "",false, "Articulo");
			NodoArgs na4 = new NodoArgs("text", (List<Object>) params4);

			List<? extends Object> params8 = Arrays.asList("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			NodoArgs na8 = new NodoArgs("multiGenerico", (List<Object>) params8);

			List<? extends Object> params5 = Arrays.asList("multi", "genero", "Seleccione generos", "",false, "Generos");
			NodoArgs na5 = new NodoArgs("multiGenerico", (List<Object>) params5);

			List<? extends Object> params6 = Arrays.asList("multi", "categoria", "Seleccione categorias", "",false, "Categorias");
			NodoArgs na6 = new NodoArgs("multiGenerico", (List<Object>) params6);

			List<? extends Object> params7 = Arrays.asList("multi", "clase", "Seleccione clases", "",false, "Clases");
			NodoArgs na7 = new NodoArgs("multiGenerico", (List<Object>) params7);
			
			List<? extends Object> params3 = Arrays.asList("text", "pickingNumber", "Indique Articulo", "",false, "Nro Picking");
			NodoArgs na3 = new NodoArgs("text", (List<Object>) params3);

			List<NodoArgs> listPrueba = Arrays.asList(na3,na1,na2,na4,na8,na5,na6,na7);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));

			break;
		}
		case "bultos":
		{
			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<? extends Object> params1 = Arrays.asList("rango", "fechas", "", "",true, "Fechas inicio");
			NodoArgs na1 = new NodoArgs("text", (List<Object>) params1);

			List<? extends Object> params3 = Arrays.asList("multi", "depositoD", "Seleccione Deposito Destino", "",false, "Deposito Destino");
			NodoArgs na3 = new NodoArgs("multiConsulta", (List<Object>) params3, "select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000 UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");

			List<? extends Object> params4 = Arrays.asList("switch", "agruparFech", "Seleccione fechas", "",false, "Agrupar Fechas","switch-0");
			NodoArgs na4 = new NodoArgs("switch", (List<Object>) params4);
			
			List<? extends Object> params5 = Arrays.asList("switch", "agruparDest", "Seleccione destino", "",false, "Agrupar Destino","switch-1");
			NodoArgs na5 = new NodoArgs("switch", (List<Object>) params5);
			
			List<NodoArgs> listPrueba = Arrays.asList(na1,na3,na4,na5);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));

			break;
		}
		case "monitorVtaMayorista":
		{
			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<? extends Object> params1 = Arrays.asList("rango", "fechas", "", "",true, "Fechas inicio");
			NodoArgs na1 = new NodoArgs("text", (List<Object>) params1);

			List<? extends Object> params3 = Arrays.asList("multi", "depositoD", "Seleccione Cliente", "",false, "Cliente");
			NodoArgs na3 = new NodoArgs("multiConsulta", (List<Object>) params3, "SELECT DE.idDeposito, DE.Nombre FROM reposicion_articulos RA INNER JOIN depositos DE ON DE.idDeposito = RA.Destino AND DE.IdEmpresa = RA.IdEmpresa WHERE RA.picked_at>= DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL -90 DAY) AND RA.IdEmpresa="+idEmpresa+" GROUP BY DE.idDeposito, DE.Nombre ORDER BY DE.Nombre ");

			List<? extends Object> params4 = Arrays.asList("multi", "pedido", "Seleccione pedidos", "",false, "Pedido");
			NodoArgs na4 = new NodoArgs("multiConsulta", (List<Object>) params4, " SELECT RA.Seccion, RA.Seccion FROM reposicion_articulos RA  WHERE RA.picked_at>= DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL -90 DAY) AND RA.IdEmpresa="+idEmpresa+" GROUP BY RA.Seccion ORDER BY RA.Seccion   ");
			
			
			List<NodoArgs> listPrueba = Arrays.asList(na1,na3,na4);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));

			break;
		}
		case "expedicionMovimientos":
		{
			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<? extends Object> params1 = Arrays.asList("multi", "depositoO", "Seleccione Deposito Origen", "",false, "Deposito origen");
			NodoArgs na1 = new NodoArgs("multiConsulta", (List<Object>) params1, "select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000 UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");

			List<? extends Object> params3 = Arrays.asList("multi", "depositoD", "Seleccione Deposito Destino", "",false, "Deposito Destino");
			NodoArgs na3 = new NodoArgs("multiConsulta", (List<Object>) params3, "select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000 UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");

			List<? extends Object> params2 = Arrays.asList("rango", "fechas", "", "",true, "Fechas inicio");
			NodoArgs na4 = new NodoArgs("text", (List<Object>) params2);
			
			List<NodoArgs> listPrueba = Arrays.asList(na1,na3,na4);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));
			break;
		}
		
		case "ajustesDiferencias":
		{
			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<? extends Object> params1 = Arrays.asList("multi", "deposito", "Seleccione Deposito", "",false, "Deposito");
			NodoArgs na1 = new NodoArgs("multiConsulta", (List<Object>) params1, "select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000 UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");

			List<? extends Object> params2 = Arrays.asList("rango", "fechas", "", "",true, "Fechas");
			NodoArgs na2 = new NodoArgs("text", (List<Object>) params2);
			
			List<? extends Object> params3 = Arrays.asList("text", "articulo", "Indique Articulo", "",false, "Articulo");
			NodoArgs na3 = new NodoArgs("text", (List<Object>) params3);

			List<? extends Object> params4 = Arrays.asList("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			NodoArgs na4 = new NodoArgs("multiGenerico", (List<Object>) params4);

			List<? extends Object> params5 = Arrays.asList("multi", "genero", "Seleccione generos", "",false, "Generos");
			NodoArgs na5 = new NodoArgs("multiGenerico", (List<Object>) params5);

			List<? extends Object> params6 = Arrays.asList("multi", "categoria", "Seleccione categorias", "",false, "Categorias");
			NodoArgs na6 = new NodoArgs("multiGenerico", (List<Object>) params6);

			List<? extends Object> params7 = Arrays.asList("multi", "clase", "Seleccione clases", "",false, "Clases");
			NodoArgs na7 = new NodoArgs("multiGenerico", (List<Object>) params7);
			
			List<NodoArgs> listPrueba = Arrays.asList(na1,na3,na2,na4,na5,na6,na7);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));
			break;	
		}
		
		case "frecuenciasUbicacionesArticulos":
		{
			List<? extends Object> params1 = Arrays.asList("switch", "agruparArt", "Agrupar por articulo", "",false, "Agrupar por Articulo (Bulto por defecto)","switch-0");
			NodoArgs na1 = new NodoArgs("switch", (List<Object>) params1);
			
			List<? extends Object> params3 = Arrays.asList("text", "articulo", "Indique Articulo", "Campo obligatorio",true, "Articulo");
			NodoArgs na3 = new NodoArgs("text", (List<Object>) params3);
			
			List<? extends Object> params2 = Arrays.asList("rango", "fechas", "", "",true, "Fechas");
			NodoArgs na2 = new NodoArgs("text", (List<Object>) params2);
			
			List<? extends Object> params4 = Arrays.asList("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			NodoArgs na4 = new NodoArgs("multiGenerico", (List<Object>) params4);

			List<? extends Object> params5 = Arrays.asList("multi", "genero", "Seleccione generos", "",false, "Generos");
			NodoArgs na5 = new NodoArgs("multiGenerico", (List<Object>) params5);

			List<? extends Object> params6 = Arrays.asList("multi", "categoria", "Seleccione categorias", "",false, "Categorias");
			NodoArgs na6 = new NodoArgs("multiGenerico", (List<Object>) params6);

			List<? extends Object> params7 = Arrays.asList("multi", "clase", "Seleccione clases", "",false, "Clases");
			NodoArgs na7 = new NodoArgs("multiGenerico", (List<Object>) params7);
			
			List<NodoArgs> listPrueba = Arrays.asList(na1,na3,na2,na4,na5,na6,na7);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));
			break;	
		}
		case "cumplimientoOrdenes":
		{
			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<? extends Object> params1 = Arrays.asList("multi", "deposito", "Seleccione Deposito", "",false, "Deposito");
			NodoArgs na1 = new NodoArgs("multiConsulta", (List<Object>) params1, "select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000 UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");

			List<? extends Object> params2 = Arrays.asList("rango", "fechas", "", "",true, "Fechas");
			NodoArgs na2 = new NodoArgs("text", (List<Object>) params2);
			
			List<? extends Object> params4 = Arrays.asList("multi", "marca", "Seleccione marcas", "",false, "Marcas");
			NodoArgs na4 = new NodoArgs("multiGenerico", (List<Object>) params4);

			List<? extends Object> params5 = Arrays.asList("multi", "genero", "Seleccione generos", "",false, "Generos");
			NodoArgs na5 = new NodoArgs("multiGenerico", (List<Object>) params5);

			List<? extends Object> params6 = Arrays.asList("multi", "categoria", "Seleccione categorias", "",false, "Categorias");
			NodoArgs na6 = new NodoArgs("multiGenerico", (List<Object>) params6);

			List<? extends Object> params7 = Arrays.asList("multi", "clase", "Seleccione clases", "",false, "Clases");
			NodoArgs na7 = new NodoArgs("multiGenerico", (List<Object>) params7);
			
			List<NodoArgs> listPrueba = Arrays.asList(na1,na2,na4,na5,na6,na7);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));
			break;	
		}
		case "pedidosProcesadosXOperario":
		{
			List<? extends Object> params1 = Arrays.asList("rango", "fechas", "", "",true, "Fechas");
			NodoArgs na1 = new NodoArgs("text", (List<Object>) params1);
			
			List<? extends Object> params2 = Arrays.asList("multi", "usuario", "Seleccione Usuario", "",false, "Usuarios");
			NodoArgs na2 = new NodoArgs("multiConsulta", (List<Object>) params2, "SELECT idUsuario, CONCAT(u.Nombre,' ',u.apellido) AS nick FROM usuarios u WHERE u.idEmpresa = "+idEmpresa+" AND idUsuario NOT IN (-10,-1,0,99,100,1) AND baja=0  ORDER BY nick;");

			List<NodoArgs> listPrueba = Arrays.asList(na1,na2);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));
			break;
		}
		case "pedidosRetrasados":
		{
			List<? extends Object> params1 = Arrays.asList("rango", "fechas", "", "",true, "Fechas");
			NodoArgs na1 = new NodoArgs("text", (List<Object>) params1);
			
			List<NodoArgs> listPrueba = Arrays.asList(na1);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));
			break;
		
		}
		case "stockEncuentraVisual":
		{
			String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
			List<? extends Object> params1 = Arrays.asList("multi", "deposito", "Seleccione Deposito", "Campo obligatorio", false, "Deposito");
			NodoArgs na1 = new NodoArgs("multiConsulta", (List<Object>) params1, "select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPrincipal+" AND idDeposito < 100000 UNION ALL select idDeposito,Nombre from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPrincipal+" AND idDeposito < 100000");
			
			List<? extends Object> params2 = Arrays.asList("text", "articulo", "Indique Articulo", "Campo obligatorio",true, "Articulo");
			NodoArgs na2 = new NodoArgs("text", (List<Object>) params2);
			
			List<NodoArgs> listPrueba = Arrays.asList(na1,na2);
			session.setAttribute("filtros", crearFiltros(listPrueba, idEmpresa));
			break;	
		}
		default:
			break;
		}
		return mapping.findForward("ok");
	}

	/*
	 * PRE: NodoArgs cargado con:
	 * clave (tipo de dato): 
	 * 		1) simple (text, rango)
	 * 		2) multiGenerico (multi que la consulta a la bd sea de sintaxis similar, ej: clase, marca, categoria)
	 * 		3) multiConsulta (multi que tiene una consulta diferente)
	 * 		4) switch (switch)
	 * 
	 * parametros - ArrayList: parametros que le voy a dar al filtro (en caso de ser switch, se pasa el values o el id como 
	 * 			7mo parametro)
	 * 
	 * consultaBD - String: string SQL (en caso de ser multiConsulta)
	 * 
	 * */

	public List<Filtro> crearFiltros(List<NodoArgs> filtros, int idEmpresa) { 
		List<Filtro> filtrosAgregados = new ArrayList<>();
		try {
			for (NodoArgs nodoArgs : filtros) { 
				System.out.println((String)nodoArgs.getParametros().get(1));
				System.out.println((String)nodoArgs.getParametros().get(2));
				Filtro filtro = new Filtro((String)nodoArgs.getParametros().get(0), (String)nodoArgs.getParametros().get(1), 
						(String)nodoArgs.getParametros().get(2), (String)nodoArgs.getParametros().get(3), 
						(boolean)nodoArgs.getParametros().get(4), (String)nodoArgs.getParametros().get(5));
				if (nodoArgs.getClave() == "multiGenerico") {
					Logica logica = new Logica();
					List<DataIDDescripcion> result = logica.darListaDataIdDescripcionConsulMYSQL("SELECT id,Descripcion FROM art_"+filtro.getName()+" WHERE idEmpresa="+idEmpresa+" ORDER BY descripcion");
					filtro.setValues(result);
				}
				if(nodoArgs.getClave() == "multiConsulta") {
					Logica logica = new Logica();
					List<DataIDDescripcion> result = logica.darListaDataIdDescripcionConsulMYSQL(nodoArgs.getConsultaBD());
					filtro.setValues(result);
				}
				if(nodoArgs.getClave() == "switch") {
					System.out.println(((String)nodoArgs.getParametros().get(6)).toString());
					filtro.setId((String)nodoArgs.getParametros().get(6));
				}
				filtrosAgregados.add(filtro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filtrosAgregados;
	}



}
