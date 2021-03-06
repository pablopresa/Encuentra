package web.util;

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
import beans.encuentra.DataOjoArticulo;

public class AltaBulto extends Action 
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
		Logica logica = new Logica();
		
		 int equipo = 0;
		 
		 if(uLog!=null)
		 {
			 equipo = uLog.getEquipo_trabajo();
		 }

		String articulosComa = request.getParameter("articulos");
		String ojo = request.getParameter("idOjoO");
		
		int deposito = 0;
		try {deposito = Integer.parseInt(uLog.getDeposito());}catch (Exception e) { }
		List<DataOjoArticulo> articulosEnOjo;
		articulosEnOjo= Logica.encuentraDarOjosArticulos(ojo, "", "","0, 1, 2, 3, 4, 5", "",false,false, idEmpresa,deposito,null);
		
		 
		if(articulosEnOjo.isEmpty())
		{
			//request.setAttribute("menError", "imposible mover, la ubicacion "+idOjo+" esta vacia" );
			error += " .Imposible crear bulto, la ubicacion de origen "+ojo+" esta vacia";
			request.setAttribute("menError", error);
			return mapping.findForward("no");
		}

		articulosComa = articulosComa.replace(" ", "");
		articulosComa = articulosComa.replaceAll("[\n\r]","");

		String [] desorden = articulosComa.split(",");


		List<DataIDDescripcion> ordenable = new ArrayList<>();

		for (String s : desorden) 
		{
			String[]data = s.split(":");
			int cant = Integer.parseInt(data[1]);

			DataIDDescripcion d = new DataIDDescripcion(cant,data[0]);
			ordenable.add(d);
			
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
		

		if(articulosReales.size()!=0)
		{
			List<DataIDDescripcion> articulosCantReales = Logica.EncuentraSortearListaDI(articulosReales);

			Hashtable<String, DataOjoArticulo> articulosOJO = new Hashtable<>();
			for (DataOjoArticulo artEnOjo : articulosEnOjo) 
			{
				articulosOJO.put(artEnOjo.getArticulo(), artEnOjo);
			}
			
			
			
			int posicion = 0;
			Fecha fecha = new Fecha(0,0,0);
			String id= "SA"+equipo+posicion+fecha.darFechaString();
			
			Fecha fecha2 = new Fecha();
			fecha2.now();
			
			bulto bul = new bulto(id, "Bulto:"+fecha2.darFechaDia_Mes_Anio_Barra(), true, 0, 0, 0, 0.0, false, "", uLog.getNumero(),"",idEmpresa);
			//bul.setRemision_al_cerrar(true);
			
			String articulosNo = "";
			
			for (DataIDDescripcion ar : articulosCantReales) 
			{
				
				if(articulosOJO.get(ar.getDescripcion())!=null)//pregunto si el escaneado est? en el ojo de origen
				{
					DataOjoArticulo ojArt =articulosOJO.get(ar.getDescripcion()); 
					int cantEnOjo= ojArt.getCantidad();
					
					if(cantEnOjo<ar.getId())
					{
						articulosNo +=ar.getDescripcion()+" hay "+cantEnOjo+" en ojo "+ojo+" y quiere usar "+ar.getId()+"\\n";
					}
					
					
					
				}
				else//no estaba en origen
				{
					
					articulosNo +=ar.getDescripcion()+" hay 0 en ojo = "+ojo+"\\n";
					
				}
				
				
				
			}
			
			
			
			if(articulosNo.equals(""))
			{
				for (DataIDDescripcion ar : articulosCantReales) 
				{
					bultoContenido bc = new bultoContenido(ar.getDescripcion(),ar.getId(), 0, uLog.getNumero());
					bul.Agregar_A_Bulto_NO_persist(bc);
				}
				
				
				session.setAttribute("currentBulto", bul);
				session.setAttribute("bultoSA", "true");
				session.setAttribute("articulosOJO", articulosOJO);
				session.setAttribute("articulosBulto", articulosCantReales);
				session.setAttribute("ojoOrigen", ojo);
				
				return mapping.findForward("bul");
				
			}
			else
			{
				request.setAttribute("menError", "no se puede crear bultos porque \\n"+articulosNo);
				return mapping.findForward("no");
			}
			
			
			
			

			
		}
		else
		{
			//request.setAttribute("menError", "No existe la ubicacion en el sistema");
			error += "Lista Vacia";
		}
		
		

		request.setAttribute("menError", error);
		return mapping.findForward("no");
	}
}



