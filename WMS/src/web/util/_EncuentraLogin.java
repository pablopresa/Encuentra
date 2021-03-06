package web.util;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Menu;
import beans.Usuario;
import beans.encuentra.Tarjeta;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import logica.Logica;

public class _EncuentraLogin extends Action {

	String Mensaje = "El nombre de usuario y la contraseņa no son correctos";
	String MensajeSuc = "no existe la tienda ";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();

		String nombre = request.getParameter("nombreUsuario");
		String pass = request.getParameter("password");
		String tienda=(String) session.getAttribute("idTienda");  // GUEST
		String idEmpresaSTR = (String) session.getAttribute("idEmpresa"); // HANDHELD
		if (idEmpresaSTR==null || idEmpresaSTR.equals(""))
			idEmpresaSTR = request.getParameter("idEmpresa"); // PC
		
		String pc = request.getParameter("pc");
		
		try 
		{
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			//List<DepositoParametros> dPar = (ArrayList<DepositoParametros>)session.getAttribute("dParametros");
			
			if(uLog!= null && pc.equals("1")){
				return mapping.findForward("ok");
			}
			else
			{
				
				int idEmpresa = Integer.parseInt(idEmpresaSTR);
				uLog = Logica.loginEncuentra(nombre, pass,idEmpresa);
				uLog = Logica.loginEncuentra_depoInvent(uLog,idEmpresa);
				//dPar = Logica.parametrosDeposito(uLog.getDeposito(), uLog.getIdEmpresa());//trae los parámetros del depósito
				
				String urlMonitorEC = Logica.darParametroEmpresa(idEmpresa, 13);
				session.setAttribute("urlMonitorEC", urlMonitorEC);
				
				
				List<DataIDDescripcion> equipos = Logica.encuentraDarEquiposDeposito(uLog.getDeposito(),uLog.getIdEmpresa());
				equipos.remove(0);				
				session.setAttribute("equipos", equipos);
				List<DataIDDescripcion> deposito = Logica.encuentraDarDepositosConLogin(idEmpresa);				
				session.setAttribute("depositoU", deposito);
				
				if (uLog.getNick() != null) 
				{
					uLog.registrarEventoMin(session.getId(), "Login en el sistema");
					//uLog.setSeguridadUI();
				}
					
				if (uLog.getNick() == null) 
				{
					request.setAttribute("nombre", nombre);
					request.setAttribute("pass", pass);
					request.setAttribute("mensaje", Mensaje);
					if(pc.equals("1"))
						return mapping.findForward("noPc");
					else
					{
						String urlRetorno = "";
						try
						{
							//obtengo prefijo url empresa
							PropertiesHelper pH=new PropertiesHelper("urlPrefijoEmpresas");
							pH.loadProperties();
							String prefijo = pH.getValue(idEmpresa+"");
							urlRetorno=prefijo+"m/login.jsp";
							
						}
						catch (FileNotFoundException e) // si no encuentra el archivo de configuracion
						{
							e.printStackTrace();
							urlRetorno="v3/util/login.jsp";
						}
						String mensaje = "El nombre de usuario y la contraseņa no son correctos";
						request.setAttribute("mensaje", mensaje);
						request.setAttribute("URL", urlRetorno);
						return mapping.findForward("redir");
					}
					
				}
				
				else if(pc.equals("0"))
				{
					session.setAttribute("uLogeado", uLog);
					//session.setAttribute("dParametros", dPar);//carga en la session los parámetros del depósito
					ServletContext context = request.getSession().getServletContext();
					Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
					
					artBarra = Logica.darBarrasTodas(idEmpresa);
					context.setAttribute("barras_"+idEmpresa, artBarra);
					
					int idGrupo = uLog.getGrupo();
					List<DataIDDescripcion> grupos = Logica.permisosDeGrupo(uLog.getNumero(),idEmpresa);
					String ids="";
					for(DataIDDescripcion g:grupos){
						ids += g.getId()+",";
					}
					ids=ids.substring(0,ids.length()-1);
					uLog.setSeguridadUI(ids);
					
					Logica.encuentraUpdateTarjetas(ids, idEmpresa);
					List<Tarjeta> tarjetas = Logica.darTarjetas(ids,idEmpresa);
					
					List<Menu> menu = Logica.darMenu(grupos,false,idEmpresa);
					
					for (Menu m : menu) 
					{
						String tags = "";
						
						tags += m.getDescripcion()+" "+m.getTags();
						for (Menu i : m.getHijos()) 
						{
							tags += " "+i.getDescripcion();
							
							for (Menu n : i.getHijos()) 
							{
								tags += " "+n.getDescripcion();
							}
							
						}
						m.setTags(tags);
					}
					
					session.setAttribute("menu", menu);
					session.setAttribute("tarjetas", tarjetas);
					
					return mapping.findForward("mob");
					
				}
				else 
				{
					session.setAttribute("uLogeado", uLog);
					//session.setAttribute("dParametros", dPar);//carga en la session los parámetros del depósito
					//trae la lista de reposiciones
					List<DataDescDescripcion> repos = Logica.darrepos(0,idEmpresa);
					int idGrupo = uLog.getGrupo();
					List<DataIDDescripcion> grupos = Logica.permisosDeGrupo(uLog.getNumero(),idEmpresa);
					grupos.remove(0);					
					
					//Logica.encuentraUpdateTarjetas(idGrupo);
					
					//Barras
					ServletContext context = request.getSession().getServletContext();
					Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
					artBarra = Logica.darBarrasTodas(idEmpresa);
					context.setAttribute("barras_"+idEmpresa, artBarra);
					
					//Menu
					List<Menu> menu = Logica.darMenu(grupos,false,idEmpresa);
					for (Menu m : menu) 
					{
						String tags = "";
						
						tags += m.getDescripcion()+" "+m.getTags();
						for (Menu i : m.getHijos()) 
						{
							tags += " "+i.getDescripcion();
							
							for (Menu n : i.getHijos()) 
							{
								tags += " "+n.getDescripcion();
							}
							
						}
						
						m.setTags(tags);
						
					}
					session.setAttribute("menu", menu);
					
					//Tarjetas
					String ids="";
					for(DataIDDescripcion g:grupos){
						ids += g.getId()+",";
					}
					ids=ids.substring(0,ids.length()-1);
					uLog.setSeguridadUI(ids);
					Logica.encuentraUpdateTarjetas(ids, idEmpresa);
					List<Tarjeta> tarjetas = Logica.darTarjetas(ids,idEmpresa);
					session.setAttribute("tarjetas", tarjetas);
					
					List<DataIDDescripcion> estadisticaVenta = Logica.darTarjetaEstadisticasVenta(idEmpresa);
					session.setAttribute("statsVenta", estadisticaVenta);
					
					//Matriz Estado Pedidos
					
					String cantDias = Logica.darParametroEmpresa(idEmpresa, 30);
					if (cantDias==null||cantDias.equals(""))
						cantDias = "30";
					String pedidosEstadoMatriz = Logica.darTotalizadores(idEmpresa,"1,2,3,25,34,4,6,99",cantDias);
				    session.setAttribute("tablaMatrizEstados", pedidosEstadoMatriz);
				    session.setAttribute("cantDiasMatriz", cantDias);
					
					//Destinos
					List<DataIDDescripcion> destinos = Logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito,nombre from ecommerce_envioml where idEmpresa="+idEmpresa);
					destinos.remove(0);
					session.setAttribute("listadtn", destinos);
					
					session.setAttribute("repos", repos);
					return mapping.findForward("ok");
					
				}
			}
		} catch (Exception e) {
			System.out.println("llegamos al catch");
			request.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("no");
		//comentario de prueba
		//otro
		}
		//comentario
	}
	//COMENTARIO
}
