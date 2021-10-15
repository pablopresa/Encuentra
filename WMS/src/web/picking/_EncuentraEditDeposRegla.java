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

import dataTypes.DataIDDescDescripcion;
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

public class _EncuentraEditDeposRegla extends Action 
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
				
				
				
				List<DataReglaReposicion> reglas = (List<DataReglaReposicion>) session.getAttribute("reglas");
				int idRegla = Integer.parseInt(request.getParameter("regSel"));
				List<Integer> depositos = new ArrayList<>();
				
				for (DataReglaReposicion regla : reglas) 
				{
					if(regla.getIdRegla()==idRegla)
					{
						for (DataIDDescDescripcion dd : regla.getDepositosValues())
						{
							if(request.getParameter(dd.getId()+"")!=null) {
								depositos.add(dd.getId());
							}
							//Logica.updateDepoRegla(idRegla,dd.getId(),request.getParameter(dd.getId()+""),idEmpresa);							
						}
					}
				}
					
				Logica.updateDepoRegla2(idRegla,depositos,idEmpresa);	
				
				return mapping.findForward("ok");
				
			}
	
	
		
		}


