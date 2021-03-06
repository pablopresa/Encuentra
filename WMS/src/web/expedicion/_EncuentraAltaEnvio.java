package web.expedicion;

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
import logica.FactoryLogica;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;




import beans.Articulo;

import beans.Estado;
import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Ruta;
import beans.encuentra.Transporte;

public class _EncuentraAltaEnvio extends Action 
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
				/*
				<select name="trans" id="trans" class="select">
			  	<select name="chofer" id="chofer" class="select">
			  	<select name="acom" id="acom" class="select">
			  	<input type="text" name="fini" readonly="readonly" style="width: 270px;"></input>
				*/
				
				String transporte = request.getParameter("trans");
				String chofer = request.getParameter("chofer");
				String acompaniante = request.getParameter("acom");
				String fecha = request.getParameter("fini");
				String rutas = request.getParameter("rutas");
				
				
				Hashtable<Integer, Transporte> htransportes = (Hashtable<Integer, Transporte>) session.getAttribute("Htransportes");
				Hashtable<Integer, DataIDDescripcion> hchoferes = (Hashtable<Integer, DataIDDescripcion>) session.getAttribute("Hchoferes");
				Hashtable<Integer, DataIDDescripcion> hacompaniantes = (Hashtable<Integer, DataIDDescripcion>) session.getAttribute("Hacompaniantes");
				
				String fechaSQL = "";
				String fechaVis = "";
				try
				{
					String[]fech = fecha.split(" ");
					String[]ddmmyyyy= fech[0].split("-");
					String dd = ddmmyyyy[2];
					String mm = ddmmyyyy[1];
					String yyyy = ddmmyyyy[0];
					fechaSQL = yyyy+"-"+mm+"-"+dd + " "+fech[1];
					fechaVis = dd+"/"+mm+"/"+yyyy+" "+fech[1];
				}
				catch(Exception e)
				{
					String error = "";
					error = "Por favor seleccione una fecha valida";
					request.setAttribute("menError", error);
					return mapping.findForward("no");
				}
				
				
				
				
				//validar tipo transporte
				
				boolean transPropio = false;
				if(htransportes.get(Integer.parseInt(transporte)).isPropio())
				{
					transPropio = true;
					
				}
				if((transPropio && chofer.equals("-10"))||(transPropio && acompaniante.equals("-10")))
				{
					String error = "";
					error = "Por favor seleccione un chofer y acompa?ante valido";
					request.setAttribute("menError", error);
					return mapping.findForward("no");
					
				}
				
				Envio envio = new Envio();
				DataIDDescripcion acom = new DataIDDescripcion();
				DataIDDescripcion chof = new DataIDDescripcion();
				Transporte trans = new Transporte();
				
				if(transPropio)
				{
					acom = hacompaniantes.get(Integer.parseInt(acompaniante));
					chof = hchoferes.get(Integer.parseInt(chofer));
					trans = htransportes.get(Integer.parseInt(transporte));
				}
				else
				{
					acom.setId(-10);
					acom.setDescripcion("Encomienda");
					
					chof.setId(-10);
					chof.setDescripcion("Encomienda");
					
					trans = htransportes.get(Integer.parseInt(transporte));
				}
								
				envio.setChofer(chof);
				envio.setAcompaniante(acom);
				envio.setTransporte(trans);
				envio.setFecha(fechaSQL);
				envio.setFechaVis(fechaVis);
				
				
				List<DepositoEnvio> depositos = Logica.darDepositosenvio(idEmpresa);
				session.setAttribute("envio", envio);
				session.setAttribute("depositosSel", depositos);
				
				if(rutas.equals("Rutas")) {
					FactoryLogica factory = new FactoryLogica();
					Hashtable<Integer, Ruta> rutasHT = factory.darRutasHT(idEmpresa, 0);
					List <Ruta> listaRutas = new ArrayList<>(rutasHT.values());
					session.setAttribute("RutasHT", rutasHT);
					session.setAttribute("RutasLista", listaRutas);
					session.setAttribute("rutas", true);
					//int variable = 1;
					//Ruta r = rutasHT.get(variable);
					
					return mapping.findForward("rutas");
				}else {
					session.setAttribute("rutas", false);
					return mapping.findForward("ok");
				}
				
				
			}
	
	
		
		}


