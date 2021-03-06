package web.mantenimiento;

import java.util.ArrayList;
import java.util.Hashtable;
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

import dataTypes.DataIDDescripcion;


import beans.Usuario;

public class _EncuentraAltaUsuario extends Action 
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
			
			
			
			String numero = request.getParameter("numero");//numero
			String nombre = request.getParameter("nombre");//nombre
			String apellido = request.getParameter("apellido");//apellido
			String tipo = request.getParameter("tipo");//tipo
			String deposito = request.getParameter("deposito");//deposito
			String login = request.getParameter("login");//login
			String p1 = request.getParameter("p1");//p1
			String p2 = request.getParameter("p2");//p2
			String grPermiso = request.getParameter("grPermiso");//p2
			
			String mensaje = "";
			
			
			request.setAttribute("numero", numero);
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellido",apellido);
			request.setAttribute("tipo", tipo);
			request.setAttribute("deposito", deposito);
			request.setAttribute("login", login);
			request.setAttribute("p1", p1);
			request.setAttribute("p2", p2);
			
			int numeroU;
			int tipoU;
			
			//validaciones de numero
			try
			{
				numeroU = Integer.parseInt(numero);
				tipoU = Integer.parseInt(tipo);
			}
			catch (Exception e) 
			{
				mensaje = "Los Valores numericos no est?n correctamente expresados";
				request.setAttribute("mensajeE", mensaje);
				uLog.registrarEventoMin(session.getId(), "(AltaU)Error al dar de alta usuario: "+numero+"|"+nombre+"|"+apellido+"|"+tipo+"|"+login+"|"+p1+"|"+p2+"|");
				return mapping.findForward("no");
			}
			//validaciones de largo
			if(nombre.length()<3 || apellido.length()<3 || login.length()<3 || p1.length()<3 || p2.length()<3)
			{
				mensaje = "Los Valores alfanumericos no pueden ser vacios y deben tener u minimo de 3 caracteres";
				request.setAttribute("mensajeE", mensaje);
				uLog.registrarEventoMin(session.getId(), "(AltaU)Error. "+mensaje);
				return mapping.findForward("no");
			}
			
			//validaciones de pass
			if(!p2.equals(p1))
			{
				mensaje = "Las contrase?as no coinciden";
				request.setAttribute("mensajeE", mensaje);
				uLog.registrarEventoMin(session.getId(), "(AltaU)Error. "+mensaje);
				return mapping.findForward("ok");
				
			}
			
			Usuario u = new Usuario();
			
			u.setNombre(nombre);
			u.setApellido(apellido);
			u.setNick(login);
			u.setNumero(numeroU);
			u.setPerfil(tipoU);
			u.setDeposito(deposito);
			u.setPass(p1);
			u.setIdEmpresa(idEmpresa);
			
			boolean altaOk = false;
			altaOk = Logica.encuentraAltaUsuario(u);
			
			if (altaOk) 
			{
				//Asignaci?n de permisos
				if(grPermiso!=null && !grPermiso.equals(""))
				{
					try {
						List<Integer> usuario = new ArrayList<>();
						usuario.add(u.getNumero());
						Logica.AsignarQuitarUsuarioAGrupo(usuario, util.parseStringInt(grPermiso) , "add", idEmpresa);
					} catch (Exception e) {
						System.out.println("Fallo al asignar permisos");
					}
				}
				
				List<DataIDDescripcion> perfiles = (List<DataIDDescripcion>) session.getAttribute("tiposU");
				for(DataIDDescripcion p :perfiles){
					if( p.getId()==u.getPerfil()){
						u.setPerfilDesc(p.getDescripcion());
						break;
					}
				}
				
				uLog.registrarEventoMin(session.getId(), "(AltaU) Correcta: "+numero+"|"+nombre+"|"+apellido+"|"+tipo+"|"+login+"|"+p1+"|"+p2+"|");
				mensaje = "Se procesaron los datos correctamente";
				request.setAttribute("numero", "");
				request.setAttribute("nombre", "");
				request.setAttribute("apellido","");
				request.setAttribute("tipo", "");
				request.setAttribute("deposito", "");
				request.setAttribute("login", "");
				request.setAttribute("p1", "");
				request.setAttribute("p2", "");
				request.setAttribute("menError", mensaje);
				
				Hashtable<Integer, Usuario> usersHT = (Hashtable<Integer, Usuario>) session.getAttribute("usersHT");
				if(usersHT==null)
				{
					usersHT = Logica.darUsuarios(idEmpresa);
					usersHT.put(u.getNumero(), u);
					List<Usuario> users = new ArrayList<>(usersHT.values());
					request.setAttribute("users", users);
					session.setAttribute("usersHT", usersHT);
				}
				else
				{
					usersHT.put(u.getNumero(), u);
					List<Usuario> users = new ArrayList<>(usersHT.values());
					request.setAttribute("users", users);
					session.setAttribute("usersHT", usersHT);
				}
				
				return mapping.findForward("ok");
				
			} //fin if altaOk
			else 
			{
				mensaje = "Ha ocurrido un error al dar de alta el usuario";
				request.setAttribute("mensajeE", mensaje);
				uLog.registrarEventoMin(session.getId(), "(AltaU)Error. "+mensaje);
				return mapping.findForward("no");
			}
			
		}

}
