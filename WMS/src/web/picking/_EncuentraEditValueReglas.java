package web.picking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.EnviaMail;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;
import dataTypes.DataReglaReposicion;




import beans.Articulo;


import beans.Estado;

import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class _EncuentraEditValueReglas extends Action 
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
				
				
				int idRegla = Integer.parseInt(request.getParameter("idRegla"));
				int valor = Integer.parseInt(request.getParameter("valor"));
				String field = request.getParameter("field");
				
				
				Logica.updateValueRegla(idRegla,valor,field,idEmpresa);
						
				
				
				
				
				
				
				
				List<DataReglaReposicion> reglas = Logica.DarReglasReposicion(idEmpresa);
				List<DataIDDescripcion> filtros = Logica.darListaDataIdDescripcionMYSQLConsulta("select IdFiltro,Nombre from reposicion_reglas_filtro where idEmpresa="+idEmpresa);
				filtros.remove(0);
				
								
				
				
				
				session.setAttribute("reglas", reglas);
				session.setAttribute("filtros", reglas);
				
				
				
				
				return mapping.findForward("ok");
				
			}
	
	
		
		}


