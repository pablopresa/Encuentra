package web.expedicion;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Fecha;
import beans.Usuario;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;


public class _EncuentraMatrizUnidadesXDeposito extends Action 
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
					Hashtable<Integer,List<DataIDDescripcion>> listadoMatriz= new Hashtable<Integer, List<DataIDDescripcion>>();
					List<DataIDDescripcion> listaFechas = new ArrayList<>();
					List<DataIDDescripcion> listarazones = (List<DataIDDescripcion>) session.getAttribute("razones");
					
					
					int columnas=1;
					
					String fechas = request.getParameter("fini");
					String fechaI ="";
					String fechaF = "";
					
					if(listarazones==null){
						List<DataIDDescripcion> razones = Logica.darListaDataIdDescripcion("razonesdoc", idEmpresa);
						session.setAttribute("razones", razones);
						return mapping.findForward("pedirFechas");
					}
					else{
					if(fechas!=null)
					{
						String []fechaIF = fechas.split(" - ");
						
						fechaI = fechaIF[0];
						fechaF = fechaIF[1];					
						
						DataIDDescripcion fch=new DataIDDescripcion(0,fechaI);
						Date fchString = darFecha(fechaI);  
						Date fchF = darFecha(fechaF);  
						
						fch.setDescripcionB(darNombreDia(fchString));
						listaFechas.add(fch);
						
						Date fechaContador= fchString;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",new Locale("ES"));
						
						while (!fechaContador.equals(fchF)){							
							fechaContador = fechaSumaDate(fechaContador,1, 0, 0);
							fch=new DataIDDescripcion(0,formatter.format(fechaContador));
							fchString = darFecha(fch.getDescripcion());  						
							fch.setDescripcionB(darNombreDia(fchString));
							listaFechas.add(fch);
							columnas++;
						}
						
						/*if(columnas==1){
							listaFechas.add(fi.darFechaAnio_Mes_Dia());
							listaFechas.add(ff.darFechaAnio_Mes_Dia());
						}*/
					}
					else{
						request.setAttribute("menError", "Debe ingresar un rango de fechas");
						return mapping.findForward("pedirFechas");
					}
					
					String req = request.getQueryString();
					session.setAttribute("REQ", req);
					String[] values = req.split("&");
					System.out.println(req);					
					String razDocs = buscarFiltro("razones", values);
					
					List<DataIDDescDescripcion> listaUnidades = Logica.DarMatrizDepositosUnidades(fechaI,fechaF,razDocs, idEmpresa);
					
					List<Integer> listaDepositos = new ArrayList<>();
					List<DataIDDescripcion> lista_fecha_unidades=new ArrayList<>();
					DataIDDescripcion fecha_unidades;
					int destAnterior = 0;
					boolean pri = true;
					
					for(DataIDDescDescripcion item:listaUnidades){
						
						if(item.getId()==destAnterior){
							//cantidad //fecha
							fecha_unidades= new DataIDDescripcion(Integer.parseInt(item.getDesc()),item.getDescripcion());
							lista_fecha_unidades.add(fecha_unidades);
						}
						else{
							if(pri){
																						//cantidad //fecha
								fecha_unidades= new DataIDDescripcion(Integer.parseInt(item.getDesc()),item.getDescripcion());
								lista_fecha_unidades= new ArrayList<>();
								lista_fecha_unidades.add(fecha_unidades);
								pri=false;
							}
							else{
								listadoMatriz.put(destAnterior, lista_fecha_unidades);
								listaDepositos.add(destAnterior);
																						//cantidad //fecha
								fecha_unidades= new DataIDDescripcion(Integer.parseInt(item.getDesc()),item.getDescripcion());
								lista_fecha_unidades= new ArrayList<>();
								lista_fecha_unidades.add(fecha_unidades);
							}
							
						}
						destAnterior=item.getId();
						
					}
					
					if(lista_fecha_unidades.size() > 0){
						listadoMatriz.put(destAnterior, lista_fecha_unidades);
						listaDepositos.add(destAnterior);
					}
					
					
					String html="<table class='table table-striped table-bordered table-hover' style='padding: 1px; text-align:center'>" +
							"	<tr>";
					html+="<thead>";
					html+="<td style='padding: 2px;'>---</td>";
					
															
					for(DataIDDescripcion date:listaFechas){
						String[] arr =date.getDescripcion().split("-");
						html+="<td style='padding: 2px;'><strong>"+date.getDescripcionB()+arr[2]+"/"+arr[1]+"</strong></td>";						
					}
					html+="<td style='padding: 2px;'><strong>TOTAL</strong></td>";					
					html+="</tr>";					
					html+="</thead>";
					html+="<tbody>";
					
					lista_fecha_unidades=new ArrayList<>();
					for(Integer dep:listaDepositos){
						lista_fecha_unidades = listadoMatriz.get(dep);
						html+="<tr>";
						html+="<td style='padding: 2px;'><strong>"+dep+"</strong></td>";
						int i=0;
						int paresMes=0;
						for(DataIDDescripcion date:listaFechas){
							if(lista_fecha_unidades.size()==0){
								html+="<td style='padding: 2px;'>"+0+"</td>";
							}
							else{
								if(lista_fecha_unidades.size()==i){
									html+="<td style='padding: 2px;'>"+0+"</td>";
								}
								else{
									if(date.getDescripcion().equals(lista_fecha_unidades.get(i).getDescripcion())){
										html+="<td style='padding: 2px;'>"+lista_fecha_unidades.get(i).getId()+"</td>";
										paresMes+=lista_fecha_unidades.get(i).getId();
										date.setId(date.getId()+lista_fecha_unidades.get(i).getId());
										i++;									
									}
									else{
										html+="<td style='padding: 2px;'>"+0+"</td>";
								
										}
									}
								}
							}
						
						html+="<td style='padding: 2px;'><strong>"+paresMes+"</strong></td>";
						html+="</tr>";
					}
					
					html+="<tr>";
					html+="<td style='padding: 2px;'><strong>TOTAL</strong></td>";
					
					int total=0;
					for(DataIDDescripcion date:listaFechas){
						html+="<td style='padding: 2px;'><strong>"+date.getId()+"</strong></td>";
						total+=date.getId();
					}
					html+="<td style='padding: 2px;'><strong>"+total+"</strong></td>";	
					html+="</tr>";
					html+="</tbody>";
					
					session.setAttribute("matriz", html);
					
				}
					
				}	
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
	public String buscarFiltro (String buscado, String[]values)
	{
		 String retorno= "";
		 System.out.println(values);
		 for (int i = 0; i < values.length; i++) 
		 {
			 if(values[i].contains(buscado))
			 {
				 try
				 {
					 String valor = values[i].split("=")[1];
					 retorno+=valor+",";
				 }
				 catch (Exception e)
				 {
					 
				 }
				 
			 }
		 }
		
		 if(!retorno.equals(""))
		 {
			 return retorno.substring(0, retorno.length()-1);
		 }
		 return retorno;
	}
	
	public static String darNombreDia(Date fecha)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE",new Locale("ES"));
		
		String nombreDia = formatter.format(fecha.getTime());
		
		return nombreDia.substring(0,1).toUpperCase();
	}
	
	public static Date darFecha (String in)
	{
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",new Locale("ES"));
	      try 
	      {
			return formatter.parse(in);
	      } 
	      catch (ParseException e) 
	      {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
	      }
	     
	}
	
	public static Date fechaSumaDate(Date fecha, int masDias,int masMes, int masAnio) 
	{
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		
		c.add(Calendar.DATE, masDias);
		c.add(Calendar.YEAR, masAnio);
		c.add(Calendar.MONTH, masMes);
		
		return c.getTime();
	}
	
	
		
}

