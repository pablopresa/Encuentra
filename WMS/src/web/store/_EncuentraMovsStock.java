package web.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.ImpresionesPDF;
import logica.LeerHTML;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;
import clientesVisual_Store.Std.clienteWSVS.WSCommunicate;


public class _EncuentraMovsStock extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Logica log = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		

		String error ="ATENCION"; 
		String idDestino = request.getParameter("idDestino");
			
		ServletContext context = request.getSession().getServletContext();
		
		if(idDestino == null || idDestino.equals("")){
			List<DataIDDescripcion> destinos = (List<DataIDDescripcion>) session.getAttribute("destinosMS");
			List<DataIDDescripcion> razonesMS = (List<DataIDDescripcion>) session.getAttribute("razonesMS");
			if(destinos==null){
				destinos = log.darListaDataIdDescripcionMYSQLConsulta("select iddeposito,nombre from depositos where iddeposito between 1 and 99 and iddeposito !="
						+uLog.getDeposito()+" and idEmpresa="+idEmpresa);
				destinos.remove(0);
				session.setAttribute("destinosMS", destinos);
			}			
			if(razonesMS==null){
				razonesMS = log.darListaDataIdDescripcionMYSQLConsulta("select idrazon,r.desc from razonesdoc r where idRazon in (1,2,4,5,6,7,10,11,15,19,21,30,33)");
				razonesMS.remove(0);
				session.setAttribute("razonesMS", razonesMS);
			}
			return mapping.findForward("ok");
		}
		else
		{
			String idRazon = request.getParameter("idRaz");
			if(idRazon==null || idRazon.equals("")) {
				request.setAttribute("menError", "Debe escoger un motivo");
				return mapping.findForward("ok");
			}
			
			int razon = Integer.parseInt(idRazon);
			
			Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras");
			
			int destino = Integer.parseInt(idDestino);
			String articulosComa = request.getParameter("articulos");
			
			
			articulosComa = articulosComa.replace(" ", "");
			articulosComa = articulosComa.replaceAll("[\n\r]","");
			
			String [] desorden = articulosComa.split(",");
			
			
			List<DataIDDescripcion> ordenable = new ArrayList<>();
			
			
			for (String s : desorden) 
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
			
			
			List<DataIDDescripcion> articulosCant = log.EncuentraSortearListaDICant(ordenable);
			
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
						
					}
					
					if(artReal!=null)
					{
						if(d.getId()>=0) {
							lineaOk = d.getId();
							DataIDDescripcion da = new DataIDDescripcion(d.getId(),artReal);
							articulosReales.add(da);
						}else {
							error += ". No se pueden mover cantidades negativas ("+d.getDescripcion()+")"; 
							request.setAttribute("menError", error);
							return mapping.findForward("ok");
						}
					}
					else
					{
						error += ". Articulo desconocido en linea "+linea +" ("+d.getDescripcion()+")"; 
					}
				}
				
			}
			
			
			WSCommunicate cl = new WSCommunicate();
			int tienda = Integer.parseInt(uLog.getDeposito());
			int idEquipo = 2000+tienda;
			//int razon = 42;
			DataIDDescripcion sVisual;
			int numeroDoc = 0;
			String descVisual = "";
			if(tienda==99)
			{
				numeroDoc= cl.GrabarTransferencia(tienda, destino, articulosReales, "Generado por encuentra", (short)99, (short)1, (short)99, Long.parseLong(uLog.getNumero()+""), false,  "Generado por encuentra", razon);
			}
			else
			{
				sVisual= cl.GrabarTransferenciaTienda(tienda, destino, articulosReales, "Generado por encuentra", (short)tienda, (short)idEquipo, (short)tienda, Long.parseLong(uLog.getNumero()+""), false,  "Generado por encuentra", razon);
				numeroDoc = sVisual.getId();
			}
			
			
			
			if(numeroDoc!=0)
			{
				articulosReales = log.DescripcionArticulos(articulosReales, idEmpresa);
				ImpresionesPDF.imprimirTicketMovStock(tienda, destino, uLog.getNombre()+" "+uLog.getApellido(), "Generado por encuentra", articulosReales, 
						numeroDoc+"",2,uLog.getIdEquipo(),idEmpresa, 1);
				error += ". Movimiento numero:"+numeroDoc+" al deposito: "+idDestino+" por "+lineaOk+" unidades";
			}
			else
			{
				error += ". Movimiento no generado, Revise que disponga del stock";
			}			
			
			request.setAttribute("menError", error);
			return mapping.findForward("ok");
		}
	}

}
