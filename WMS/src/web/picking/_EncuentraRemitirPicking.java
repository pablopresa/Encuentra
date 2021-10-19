package web.picking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.MovStock;
import beans.Usuario;
import beans.encuentra.DataPicking;

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import jsonObjects.SendMail;
import logica.EnviaMail;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;



public class _EncuentraRemitirPicking extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		String menError = "";
		try 
		{			
			System.out.println("-");
			
			List<DataPicking> pickings = (List<DataPicking>) session.getAttribute("pickings"); 
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();			
			
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
						
			/**/
			Hashtable<Integer,DataIDDescripcion> depositosWEB = util.darDeposWEB(idEmpresa);
			
			
			
			//int idDepoCentral = util.darParametroEmpresaINT(idEmpresa,4);
			int idDepoCentral = Integer.parseInt(uLog.getDeposito());
			
			if(idDepoCentral==-1)
			{
				request.setAttribute("menError", "Atencion, falta parametrizar depositos");
				return mapping.findForward("LOGIN");
			}
			
			boolean integracionActiva = false;
			integracionActiva = Logica.darIntegracionProductiva(2, idEmpresa);
			
			Hashtable<Integer, List<DataPicking>> picksLocales = new Hashtable<Integer, List<DataPicking>>();
			Hashtable<Integer, List<DataPicking>> picksEcommerce = new Hashtable<Integer, List<DataPicking>>();
			Hashtable<Integer, List<DataPicking>> picksPedidoMayo = new Hashtable<Integer, List<DataPicking>>();
						
			for (DataPicking p : pickings) 
			{
				if(!p.isMayorista() )
				{
					p.setSolicitud(p.getDestino().getId());
					picksLocales = util.clasificarOrdenes(p,picksLocales);
					p.setSolicitud(0);
				}
				else
				{					
					if(depositosWEB.containsKey(p.getDestino().getId())){
						picksEcommerce = util.clasificarOrdenes(p,picksEcommerce);
					}
					else{
						picksPedidoMayo = util.clasificarOrdenes(p,picksPedidoMayo);
					}					
				}				
			}
			
			System.out.println("llamando a la API");
			
			List<List<DataPicking>> listaLC = new ArrayList<List<DataPicking>>(picksLocales.values());
			List<List<DataPicking>> listaEC = new ArrayList<List<DataPicking>>(picksEcommerce.values());
			List<List<DataPicking>> listaMayo = new ArrayList<List<DataPicking>>(picksPedidoMayo.values());
			
			List<DataPicking> noEncontrados = new ArrayList<>();
			
			List<List<DataPicking>> allDataPickings = new ArrayList<List<DataPicking>>();
			allDataPickings.addAll(listaLC);
			allDataPickings.addAll(listaEC);
			allDataPickings.addAll(listaMayo);
			
			
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			List<DataIDDescripcion> list;
			List<DataIDDescripcion> remitoEC = new ArrayList<>();
			List<String> ArtsSinAfectar;
			DataIDDescripcion data;
			int destino = 0;
			int idDepoWEB=0;
			
			
			if(!allDataPickings.isEmpty())
			{				
				list = null;
				ArtsSinAfectar = null;
				data = null;
				for (List<DataPicking> l : allDataPickings) 
				{					
					list = new ArrayList<>();
					ArtsSinAfectar = new ArrayList<>();
					for (DataPicking p : l) 
						{
							destino = p.getDestino().getId();
							try
							{
								idDepoWEB = depositosWEB.get(destino).getId();
							}
							catch (Exception e) {}
							
							
							if(p.getVerificada()-p.getRemitida()>0)
							{
								data = new DataIDDescripcion(p.getVerificada()-p.getRemitida(),p.getArticulo());
								//data.setIdB(idDepoWEB);
								data.setIdB(destino);
								if(depositosWEB.containsKey(p.getDestino().getId())) {
									data.setDescripcionB(p.getIdPedido()+"");
								}else {
									data.setDescripcionB(p.getSolicitud()+"");
								}
								
								list.add(data);
							}
							else{
								ArtsSinAfectar.add(p.getArticulo());
							}							
							
							if(p.getVerificada()<p.getSol())
							{								
								noEncontrados.add(p);								
							}
							
						}
					try {
						System.out.println("");
						System.out.println("");
						System.out.println("Enviando info a visual");
						
						
						if(true)//aca decia integracion activa
						{
							boolean afectarEstadoEC = false;
							
							if(list.size()>0)
							{
								String [] retorno = {""};
								
								if(integracionActiva)
								{
									retorno = api.prepararRemito(idDepoCentral, destino, uLog, l, idEmpresa, idDepoWEB, list);
									
									if(!retorno[0].equals(""))
									{
										menError += "DOC "+l.get(0).getSolicitud()+" - "+retorno[0] + " <br/>";
									}
									else 
									{
										afectarEstadoEC=true;								
									}
									if(!retorno[1].equals(""))
										{
											//MODIFICO NUMERO DE DOCUMENTO EN ARTICULOS QUE NO SE AFECTARON EN ESTA DISTRIBUCION
										if(depositosWEB.containsKey(destino))
											{
												Logica.updateDocVisual(l.get(0).getSolicitud(), ArtsSinAfectar, 
													Integer.parseInt(retorno[1]),idEmpresa);
											}
											menError +="Se genero un nuevo documento ("+retorno[1]+") por la diferencia del documento "
											+l.get(0).getSolicitud()+" <br/>";
										}
								}
								else // si la integracion no esta activa igual marco los cambios de estado con la verificacion.
								{
									afectarEstadoEC=true;
								}
								
								
								
									
								if(afectarEstadoEC)
								{
									for(DataIDDescripcion d:list) 
									{
										remitoEC.add(d);
										if(depositosWEB.containsKey(destino))
										{
											util.ConfirmoMovimientoEcommerce(d, idDepoCentral, idDepoWEB, idEmpresa, uLog, Logica);
										}
									}
								}
								
							}
						}
						
					} catch (Exception e) {
						System.out.println("*************************ERROR AL ENVIAR A VISUAL*************************");
					}					
					
					System.out.println("REMITIENDO");
					Logica.encuentraUpdateRemirPickinEC(l,idEmpresa);
				}
			}
			
			if(!remitoEC.isEmpty()){
				int tipoComanda = 3;
				String obsTicket = "Movimiento hacia cliente "+destino;
				if(depositosWEB.containsKey(destino)) {
					tipoComanda = 1;
					obsTicket = "Remito del picking "+pickings.get(0).getIdPicking();
				}
				if(!picksLocales.isEmpty()) {
					tipoComanda = 2;
					obsTicket = "Remito del picking "+pickings.get(0).getIdPicking();
				}
				ImpresionesPDF.imprimirTicketMovStock(idDepoCentral, idDepoWEB, uLog.getNick(), obsTicket,remitoEC, pickings.get(0).getIdPicking()+"",tipoComanda, uLog.getIdEquipo(),idEmpresa, 1);
			}
						 
			
			if(!noEncontrados.isEmpty())
			{
				util.EnviarMailNoEncontrados(util, noEncontrados, idEmpresa, api);
			}
									
			session.setAttribute("mensajeVS", menError);
			return mapping.findForward("ok");		

		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			request.setAttribute("mensaje", menError);
			return mapping.findForward("ok");
		}	
		
	}
	
	
	
	
	
	
	
	
}















