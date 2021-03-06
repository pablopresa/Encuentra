package web.recepcion;

import helper.PropertiesHelper;

import java.sql.Connection;
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

import persistencia.MSSQL;
import persistencia._EncuentraPersistir;



import dataTypes.DataIDDescripcion;
import dataTypes.DataOC;
import dataTypes.DataRecepcion;




import beans.Articulo;

import beans.Estado;

import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.ColorOC;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataArticuloOC;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.TalleOC;
import beans.encuentra.Transporte;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;

public class _EncuentraAgendarEntregaIII extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
				Logica Logica = new Logica();
				
				DataRecepcion recepcion = (DataRecepcion) session.getAttribute("recepcion");
				
				String MenError;
								
				String tipo = "";
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");
				Utilidades util = new Utilidades();
				int idEmpresa = util.darEmpresa(uLog);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				
				List <DataIDDescripcion> dataArToSort = new ArrayList<>();
				
				
				
				
				
				try
				{
					String etiquetado="";
					String distribucion="";
					String unidad="";
					
					for(DataOC ord:recepcion.getOrdenes())
					{
						for(DataArticuloOC art :ord.getArticulos())
						{
							for(ColorOC col:art.getColores())
							{
								unidad=ord.getNumeroDocumento()+"-"+art.getIdArticulo()+"-"+col.getDescripcion()+"-";
								col.setEtiqueta(0);
								col.setDistribuir(0);
								etiquetado="";
								distribucion="";
								unidad="";
							}
						}
					}
				}
				catch(Exception e){
					
				}
				
				int idRecepcion = Logica.encuentraAltaRecepcion(recepcion,tipo,idEmpresa);
				
				boolean seConocePacking = util.darParametroEmpresaBool(idEmpresa,29);
				String depoCentral = util.darParametroEmpresaSTR(idEmpresa, 4);
				if(seConocePacking)
				{
					List<VORecepcionSinOrden> articulosEnOrden = (List<VORecepcionSinOrden>) session.getAttribute("ordenesAux");
					Logica.encuentraAltaPackingRecepcion(idRecepcion, articulosEnOrden, idEmpresa);
					Logica.crearBultosARecepcionarDesdePacking(idRecepcion, articulosEnOrden, uLog.getNumero(), depoCentral, idEmpresa);
				}
				
				if(idRecepcion!=0)
				{
					MenError = "Agendado Exitoso el numero de agenda es "+idRecepcion;
				}
				else
				{
					MenError = "la recepcion se guard? con errores, no todos los articulos est?n en la BD de encuentra";
				}
				
				
				request.setAttribute("menError", MenError);
				return mapping.findForward("ok");
				
			}
	
	
		
		}


