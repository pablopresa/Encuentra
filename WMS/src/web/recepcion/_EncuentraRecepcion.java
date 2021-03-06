package web.recepcion;

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
import dataTypes.DataIDIDDescripcion;
import beans.Fecha;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataArticulo;

public class _EncuentraRecepcion extends Action 
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
		String error ="ATENCION";
		String idOjo = request.getParameter("idOjo");
		idOjo = idOjo.toUpperCase();
		Logica logica = new Logica();
		
		
		 int equipo = 0;
		 
		 if(uLog!=null)
		 {
			 equipo = uLog.getEquipo_trabajo();
		 }

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

					}

				}


			}
			else
			{
				String[]data = s.split(":");
				int cant = Integer.parseInt(data[1]);

				DataIDDescripcion d = new DataIDDescripcion(cant,data[0]);
				ordenable.add(d);
			}
		}


		List<DataIDDescripcion> articulosCant = Logica.EncuentraSortearListaDICant(ordenable);


		List<DataIDDescripcion> articulosReales = new ArrayList<>();

		int linea = 0;
		int lineaOk = 0;

		for (DataIDDescripcion d : articulosCant) 
		{
			linea ++;
			DataArticulo artReal = logica.encuentraCodArticulo(d.getDescripcion(),idEmpresa);
			if(artReal.getId()!=null)
			{
				for (int i = 0; i < d.getId(); i++) 
				{
					lineaOk ++;
					DataIDDescripcion da = new DataIDDescripcion(0,artReal.getId());
					articulosReales.add(da);
				}
			}
			else
			{
				artReal = logica.encuentraCodArticuloT(d.getDescripcion(),idEmpresa);
				if(artReal.getId()!=null)
				{
					for (int i = 0; i < d.getId(); i++) 
					{
						lineaOk ++;
						DataIDDescripcion da = new DataIDDescripcion(0,artReal.getId());
						articulosReales.add(da);
					}
				}
				else
				{

					error += ". Articulo desconocido en linea "+linea +" ("+d.getDescripcion()+")"; 
				} 
			}
		}
		error += ". Se Agregaron "+lineaOk+" lineas para la ubicaci?n "+ idOjo;

		if(articulosReales.size()!=0)
		{
			List<DataIDDescripcion> articulosCantReales = Logica.EncuentraSortearListaDI(articulosReales);

			int esBulto = Integer.parseInt(request.getParameter("AltaBulto"));
			if(esBulto==0)
			{
				String inns = "";
				String idRecepcion = (String) session.getAttribute("recepcionID");
				Hashtable<String, DataIDIDDescripcion> hashArt = new Hashtable<>();
				for (DataIDDescripcion d : articulosCantReales)
				{
					System.out.println(d.getId()+" "+d.getDescripcion());
					if(idOjo.equals("0"))
					{
						logica.encuentraUpdateOjos(idOjo, d.getDescripcion(),d.getId(), false, uLog.getNumero(),uLog.isInventario(),"REC",idEmpresa);
					}
					else //si el destino es distindo de recepcion (0) hago el movimiento de 0 al destino
					{
						logica.encuentraUpdateOjos("0", d.getDescripcion(),d.getId(), false, uLog.getNumero(),uLog.isInventario(),"REC",idEmpresa);
						logica.encuentraBajaArticulosOjos(d.getId(),d.getDescripcion(), "0", idEmpresa);	//MOVIMIENTO DE MERCADERIA
						logica.encuentraMoverOjos(idOjo,d.getDescripcion(),d.getId(),uLog.getNumero(), idEmpresa);		
						logica.IngresarMovimientoArticuloTipo("0", idOjo, d.getDescripcion(), d.getId(), uLog.getNumero(), "REC", idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA
					}
					
					inns+="'"+d.getDescripcion()+"',";
					hashArt.put(d.getDescripcion(), new DataIDIDDescripcion(d.getId(), d.getDescripcion()));
					
				}
				
				inns = inns.substring(0,inns.length()-1);
				
				Logica.updateRecepcionMob(Integer.parseInt(idRecepcion),inns,hashArt, uLog.getNumero(),idEmpresa);
				
			}
			else
			{
				int posicion = 0;
				Fecha fecha = new Fecha(0,0,0);
				fecha.now();
				String id= "R"+equipo+posicion+fecha.darFechaString();
				
				String idRecepcion = (String) session.getAttribute("recepcionID");
				String descRecepcion = (String) session.getAttribute("recepcionDesc");
				
				
				bulto bul = new bulto(id, "Recepcion "+descRecepcion, true, 0, 0, 0, 0.0, false, "", uLog.getNumero(),uLog.getDeposito(),idEmpresa);
				bul.setRemision_al_cerrar(true);
				
				
				
				
				for (DataIDDescripcion ar : articulosCantReales) 
				{
					bultoContenido bc = new bultoContenido(ar.getDescripcion(),ar.getId(), Integer.parseInt(idRecepcion), uLog.getNumero());
					bul.Agregar_A_Bulto_NO_persist(bc);
				}
				
				
				session.setAttribute("currentBulto", bul);
				session.setAttribute("ojoRecepcion", idOjo);
				
				session.setAttribute("bultoSA", null);
				return mapping.findForward("bul");
				
				
			}

			
		}
		else
		{
			//request.setAttribute("menError", "No existe la ubicacion en el sistema");
			error += "No existe la ubicacion "+idOjo+" en el sistema";
		}
		
		uLog.registrarEventoMin(session.getId(), "(RECEPCION)"+error);

		request.setAttribute("menError", error);
		return mapping.findForward("ok");
	}
}



