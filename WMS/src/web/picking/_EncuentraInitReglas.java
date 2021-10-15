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

public class _EncuentraInitReglas extends Action 
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
				
				
				
				List<DataReglaReposicion> reglas = Logica.DarReglasReposicion(idEmpresa);
				List<DataIDDescripcion> filtros = Logica.darListaDataIdDescripcionMYSQLConsulta("select IdFiltro,Nombre from reposicion_reglas_filtro where idEmpresa="+idEmpresa);
				List<DataIDDescripcion> deposDestinos = Logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito, Nombre from depositos where tipo = 0 AND idEmpresa="+idEmpresa);
				deposDestinos.remove(0);
				
				
				for (DataReglaReposicion regla : reglas) 
				{
					for (DataIDDescripcion fil : filtros) 
					{
						if(regla.getFiltros().get(fil.getId())==null)
						{
							regla.getFiltros().put(fil.getId(), new DataIDDescripcion(fil.getId(),""));
						}
					}
					
					List<DataIDDescripcion> temp = new ArrayList<>(regla.getFiltros().values());
					List<DataIDDescDescripcion> filtrosReg = new ArrayList<>();;
					for (DataIDDescripcion ft: temp) 
					{
						String nombreFil = "";
						for (DataIDDescripcion f : filtros) 
						{
							if(f.getId()==ft.getId())
							{
								nombreFil = f.getDescripcion();
								break;
							}
						}
						DataIDDescDescripcion d = new DataIDDescDescripcion(ft.getId(),nombreFil,ft.getDescripcion());
						filtrosReg.add(d);
					}
					
					
					Collections.sort(filtrosReg);
					regla.setFiltrosValues(filtrosReg);
					
					
					List<Integer> tempDep = new ArrayList<>(regla.getDestinos().values());
					List<DataIDDescDescripcion> deposReg = new ArrayList<>();;
					for (DataIDDescripcion d : deposDestinos) 
					{
						String nombreDep = d.getDescripcion();
						int asociado = 0;
						for (int in: tempDep) 
						{
							nombreDep = d.getDescripcion();
							if(in==d.getId())
							{
								asociado = 1;
							}
						}
						
						DataIDDescDescripcion dd = new DataIDDescDescripcion(d.getId(),d.getDescripcion(),String.valueOf(asociado));
						deposReg.add(dd);
					}
										
					Collections.sort(deposReg);
					regla.setDepositosValues(deposReg);
					
					
				}					
				
				filtros.remove(0);				
				
				List<DataIDDescripcion> marcas = Logica.darListaDataIdDescripcion("art_marca",idEmpresa);
				List<DataIDDescripcion> secciones = Logica.darListaDataIdDescripcion("art_seccion",idEmpresa);
				List<DataIDDescripcion> clases = Logica.darListaDataIdDescripcion("art_clase",idEmpresa);
				List<DataIDDescripcion> generos = Logica.darListaDataIdDescripcion("art_genero",idEmpresa);
				List<DataIDDescripcion> categorias = Logica.darListaDataIdDescripcion("art_categoria",idEmpresa);
				List<DataIDDescripcion> distribuciones = Logica.darListaDataIdDescripcion("vista_rep_art_distribucion",idEmpresa);

				session.setAttribute("marcas", marcas);
				session.setAttribute("distribuciones", distribuciones);
				session.setAttribute("secciones", secciones);
				session.setAttribute("clases", clases);
				session.setAttribute("generos", generos);
				session.setAttribute("categorias", categorias);
				
				session.setAttribute("reglas", reglas);				
				
				return mapping.findForward("ok");
				
			}
	
	
		
		}


