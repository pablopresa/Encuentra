package web.almacen;



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
import beans.encuentra.DataOjoArticulo;


public class _EncuentraArticulosSinMedidas extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				try{
					HttpSession session = request.getSession();
 Logica Logica = new Logica();
 Usuario uLog = (Usuario) session.getAttribute("uLogeado");
	Utilidades util = new Utilidades();
	int idEmpresa = util.darEmpresa(uLog);
	if(idEmpresa==0)
	{
		return mapping.findForward("LOGIN");
	}
	int deposito = 0;
	try {deposito = Integer.parseInt(uLog.getDeposito());}catch (Exception e) { }
					List<DataOjoArticulo> listaArt = new ArrayList<>();
					
					listaArt = Logica.DarArtsSinMedidas(idEmpresa, deposito);
					
					session.setAttribute("sinmedidas", listaArt);
				
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
	
		
		}

