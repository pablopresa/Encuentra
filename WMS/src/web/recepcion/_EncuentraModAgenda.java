package web.recepcion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import dataTypes.DataIDDescripcion;
import dataTypes.DataOC;
import dataTypes.DataRecepcion;




import beans.Articulo;

import beans.Estado;

import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.ColorOC;
import beans.encuentra.DataArticuloOC;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.TalleOC;
import beans.encuentra.Transporte;

public class _EncuentraModAgenda extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				
		
				HttpSession session = request.getSession();
				
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");
				Utilidades util = new Utilidades();
				int idEmpresa = util.darEmpresa(uLog);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				Logica Logica = new Logica();
				
				String json = request.getParameter("agendas");
				JSONParser parser = new JSONParser();
				List<DataRecepcion> recepciones = (List<DataRecepcion>) session.getAttribute("recepciones");
				List<DataIDDescripcion> recepcionesJSON = new ArrayList<>();
				
				Object obj = parser.parse(json);
				
				org.json.simple.JSONArray arreglo = (org.json.simple.JSONArray) obj;
				
				Iterator <org.json.simple.JSONObject> iterador = arreglo.iterator();
				while (iterador.hasNext()) 
				{
					
					org.json.simple.JSONObject agenda = iterador.next();
					String keyAgenda = "idAgenda";
					String keyFecha = "startsAt";
					
					String idAgenda = (String)agenda.get(keyAgenda);
					String fecha = (String)agenda.get(keyFecha);
					
					String fechaSQL = "";
					String fechaVis = "";
					try
					{
						
						int idAgen = Integer.parseInt(idAgenda);
						DataIDDescripcion ag = new DataIDDescripcion(idAgen, "");
						recepcionesJSON.add(ag);
						//2016-06-07T15:04:00
						String[]fech = fecha.split("T");
						String[]ddmmyyyy= fech[0].split("-");
						String dd = ddmmyyyy[2];
						String mm = ddmmyyyy[1];
						String yyyy = ddmmyyyy[0];
						String hora = fech[1];
						if(hora.contains("."))
						{
							hora = hora.substring(0, 8);
							
							String [] numes = hora.split(":");
							int horaNum = Integer.parseInt(numes[0]);
							horaNum = horaNum-3;
							String horaNumS ="";
							if(horaNum<10)
							{
								horaNumS = "0"+horaNum;
							}
							else
							{
								horaNumS = horaNum+"";
							}
							
							
							hora = horaNumS+":"+numes[1]+":"+numes[2];
							
							
							
						}
						
						
						fechaSQL = yyyy+"-"+mm+"-"+dd + " "+hora;
						
						Logica.encuentraActualizaFechaAgenda(idAgen,fechaSQL,idEmpresa);
						
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					
					
					System.out.println(idAgenda);
					
					
				}
				
				
				String MenError = "";
				
				for (DataRecepcion r : recepciones) 
				{
					boolean esta = false;
					for (DataIDDescripcion d : recepcionesJSON) 
					{
						if(r.getId()==d.getId())
						{
							esta = true;
						}
						
					}
					if(!esta)
					{
						int afectados = Logica.DeleteReception(r.getId(),idEmpresa);
						/*if(afectados<=0){
							MenError += "La recepcion "+r.getId()+" no se puede eliminar, ya se empezo a controlar las unidades. \n";
						}*/
					}
				
				
			}
	
			request.setAttribute("menError", MenError);
			return mapping.findForward("ok");
				
		
		}
}


