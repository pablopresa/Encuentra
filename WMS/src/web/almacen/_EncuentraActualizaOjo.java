package web.almacen;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
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
import dataTypes.DataIDIDDescripcion;

import beans.Usuario;

public class _EncuentraActualizaOjo extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		Logica logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String error ="ATENCION"; 
		String idOjo = request.getParameter("idOjo");
		int email = 1;
		/*
		try
		{
			email = Integer.parseInt(request.getParameter("email"));
		}
		catch (Exception e)
		{
			
		}
		*/
		
		try {
			boolean inventario = logica.loginEncuentra_depoInvent(uLog, idEmpresa).isInventario();
			if(uLog.isInventario()!=inventario){
				uLog.setInventario(inventario);
				String comentario = "DESACTIVADO";
				if(inventario){
					comentario = "ACTIVADO";
				}
				uLog.registrarEventoMin(session.getId(), "Se actualizo el modo inventario a: "+comentario);
			}
		} catch (Exception e) {
			request.setAttribute("menError", "Sucedio un error verificando el modo inventario");
			return mapping.findForward("ok");
		}
		
		 
		idOjo = idOjo.toUpperCase();
		
		
		//Hashtable<String, String> artBarra = (Hashtable<String, String>) session.getAttribute("barras");
	
		ServletContext context = request.getSession().getServletContext();
		
		Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
		
		int deposito = Integer.parseInt(uLog.getDeposito());
		int existe = logica.encuentraExisteUbica(idOjo, idEmpresa, deposito);		
		if(existe==1)
		{
			String articulosComa = request.getParameter("articulos");
			
			
			articulosComa = articulosComa.replace(" ", "");
			articulosComa = articulosComa.replaceAll("[\n\r]","");
			
			String [] desorden = articulosComa.split(",");
			
			
			List<DataIDDescripcion> ordenable = new ArrayList<>();
			
			
			for (String s : desorden) 
			{
				
				
				if(s.contains("__"))
				{
					String qr = "";
					qr = s;
					String[]ar = s.split("__");
					
					String ser = ar[0].substring(0,2);
					for (int ie = 1; ie < ar.length; ie++) 
					{
						try
						{
							String []ar2 = ar[ie].split("/");
							String conSer = ar2[1].replace(ser, "---");
							String [] ar3 = conSer.split("---");
							int cant = Integer.parseInt(ar3[0]);
							qr = ar2[0];
							for (int i = 0; i < cant; i++) 
							{
								DataIDDescripcion d = new DataIDDescripcion(0,qr);
								ordenable.add(d);
							}
							
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
						
					}
					
					
				}
				else
				{
					try {
						String[]data = s.split(":");
						int cant = Integer.parseInt(data[1]);
						
						DataIDDescripcion d = new DataIDDescripcion(cant,data[0]);
						ordenable.add(d);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
				
			}
			
			
			List<DataIDDescripcion> articulosCant = logica.EncuentraSortearListaDICant(ordenable);
						
			List<DataIDDescripcion> articulosReales = new ArrayList<>();
			
			int linea = 0;
			int lineaOk = 0;
			
			for (DataIDDescripcion d : articulosCant) 
			{
				if(!d.getDescripcion().equals(""))
				{
					linea ++;
					String artReal= null;
					try
					{
						artReal = artBarra.get(d.getDescripcion()).toUpperCase();
					}
					catch(Exception e)
					{
					
						try
						{
							artReal = logica.encuentraCodArticulo(d.getDescripcion(),idEmpresa).getId();
						}
						catch(Exception e2)
						{
							error += ". Articulo desconocido en linea "+linea +" ("+d.getDescripcion()+")";
						}
						
						
					}
					
					
					if(artReal!=null)
					{
						for (int i = 0; i < d.getId(); i++) 
						{
							lineaOk ++;
							DataIDDescripcion da = new DataIDDescripcion(0,artReal);
							articulosReales.add(da);
						}
					}
					else
					{
						try
						{
							artReal = logica.encuentraCodArticulo(d.getDescripcion(),idEmpresa).getId();
						}
						catch(Exception e2)
						{
							error += ". Articulo desconocido en linea "+linea +" ("+d.getDescripcion()+")";
						} 
					}
				}
				
			}
			
			error += ". Se actualizaron "+lineaOk+" lineas para la ubicaci?n "+ idOjo;
			
			int cantidadContada = logica.encuentraDarCantidadOJO(idOjo, idEmpresa);
			
			
			
			
			
			
			if(articulosReales.isEmpty())
			{
				logica.encuentraUpdateOjos(idOjo, "",0,true,uLog.getNumero(),false,"UIN",idEmpresa);
			}
			else
			{
				
				List<DataIDDescripcion> articulosCantReales = logica.EncuentraSortearListaDI(articulosReales);
				//String path = LeerFicheroTexto.LeerProperties("PathInvent.txt").get(0);
				logica.encuentraUpdateOjos(idOjo, "",0, true,uLog.getNumero(),false,"UIN",idEmpresa);
				//Escribir.borrar(path,est.getId()+"-"+est.getIid()+"-"+est.getDescripcion()+"-"+idOjo);
				
				int cantidadOJO =0;
				
				for (DataIDDescripcion d : articulosCantReales) 
				{
					cantidadOJO += d.getId();
				}
				
				for (DataIDDescripcion d : articulosCantReales)
				{
					//System.out.println(d.getId()+" "+d.getDescripcion());
					logica.encuentraUpdateOjos(idOjo, d.getDescripcion(), d.getId(), false,uLog.getNumero(),uLog.isInventario(),"UIN",idEmpresa);
					try//escribimos el fichero de la repo
					{
						
						Formatter fmt = new Formatter();
						String texto = "00000,"+d.getDescripcion()+","+fmt.format("%06d",d.getId());
						//Escribir.escribir(path,est.getId()+"-"+est.getIid()+"-"+est.getDescripcion()+"-"+idOjo, texto);
						//Escribir.escribir(path,fmt.format("%03d",est.getId())+"-"+fmt.format("%02d",est.getIid())+"-"+fmt.format("%03d",(Integer.parseInt(est.getDescripcion())))+"-"+idOjo+"-"+uLog.getNumero(), texto);	
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					
				}
				
				if(uLog.isInventario())
				{
					String fecha = logica.darListaDataIdDescripcionConsulMYSQL("SELECT 0, fecha FROM invent WHERE idDeposito = '"+uLog.getDeposito()+"' AND idEmpresa="+idEmpresa).get(0).getDescripcion();
					logica.encuentraUpdateAvanceInvent(fecha, uLog.getDeposito(), idEmpresa);
					logica.encuentraUpdateOjoInvent(idOjo, idEmpresa);
					
					
				}
				
				
				if(cantidadContada!=cantidadOJO && cantidadContada!=0 && email!=0)
				{
					error = "ATENCION: SE CONTARON "+cantidadContada + " UNIDADES Y ACTUALIZ?  POR " + cantidadOJO + " UNIDADES";
					/*
					String[] destinos = {"palvez@stadium.local","GFleitas@stadium.local","FHernandez@stadium.local","RHerrera@stadium.local"};
					EnviaMail.enviarMailLista(destinos, "Estanteria "+est.getId()+" Estante "+est.getIid()+" Modulo "+est.getDescripcion()+" ojo "+idOjo+" USUARIO "+uLog.getNumero()+" SE CONTARON "+cantidadContada + " UNIDADES Y ACUTALIZ?  POR" + cantidadOJO + " UNIDADES" , "");
					*/
				}
			
				
			}
		}
		else
		{
			//request.setAttribute("menError", "No existe la ubicacion en el sistema");
			error += " .No existe la ubicacion "+idOjo+" en el sistema";
		}
		
		uLog.registrarEventoMin(session.getId(), "(UPDATE_OJO)"+error);
		
		request.setAttribute("menError", error);
		return mapping.findForward("ok");
	}

}
