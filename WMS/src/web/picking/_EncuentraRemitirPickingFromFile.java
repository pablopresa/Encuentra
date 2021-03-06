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

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Request;

import beans.MovStock;
import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataPicking;
import beans.encuentra.DataPickingS;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import jsonObjects.SendMail;
import logica.EnviaMail;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;
import persistencia._EncuentraPersistir;



public class _EncuentraRemitirPickingFromFile extends Action 
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
						
			//String tipoMov = util.darParametroEmpresaSTR(idEmpresa, 9);
			int idDepoTRANSITOWEB = util.darParametroEmpresaINT(idEmpresa,5);
			int idDepoWEB = (idDepoTRANSITOWEB != 0 ? idDepoTRANSITOWEB : util.darParametroEmpresaINT(idEmpresa,5));
			int idDepoCentral = util.darParametroEmpresaINT(idEmpresa,4);
			boolean remiteAPI = util.darParametroEmpresaBool(idEmpresa, 39);
			
			
			
			
			if(idDepoWEB==-1 || idDepoCentral==-1)
			{
				request.setAttribute("menError", "Atencion, falta parametrizar depositos");
				return mapping.findForward("LOGIN");
			}
			
			boolean integracionActiva = false;
			boolean esEcommerce = false;
			integracionActiva = Logica.darIntegracionProductiva(2, idEmpresa);
			
			;
			
			List<String> queries = new ArrayList<>();
			
			
			/*1 afectar pickeados RA*/
			/*2 terminar tarea*/
			/*3 afectar verificados RA*/
			/*4 remitir*/
			/*5 afectar remitidos RA*/
			/*6 afectar otras tablas*/
				/*6.1 afectar pedidos de ecommerce*/
					/*6.1.1 ecommerce articulos req*/
					/*6.1.2 ecommerce pedido*/
				/*6.2 afectar solicitudes de traslado*/
				
			
			
			
			
			int pasadas = 0;
						
			for (DataPicking p : pickings) 
			{
				pasadas+=p.getSol();
			}
			
			
			
			List<DataPicking> noEncontrados = new ArrayList<>();
			
			
			
			
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			List<DataIDDescripcion> list;
			List<DataIDDescripcion> remitoEC = new ArrayList<>();
			List<String> ArtsSinAfectar;
			DataIDDescripcion data;
			int destino = 0;
			int origen = 0;
			
			if(!pickings.isEmpty())
			{				
				list = new ArrayList<>();;
				ArtsSinAfectar = new ArrayList<>();
				data = null;
				int idPicking = 0,idTarea = 0;
				int porcentaje = 0;
				
				
				for (DataPicking p : pickings) 
				{
					porcentaje+=p.getSol();
					/*1 afectar pickeados RA*/
					
					idTarea = p.getIdTarea();
					idPicking = p.getIdPicking();
					
					queries.add(Logica.query_encuentraUpdateAvanceTarea(p.getIdTarea(), (porcentaje*pasadas)/100, porcentaje,idEmpresa));
					
					DataLineaRepo dl = new DataLineaRepo();
					dl.setIdArticulo(p.getArticulo());
					dl.setSolicitada(p.getPick());
					
					boolean encontrado = false;
					if(p.getPick()>0)
					{
						encontrado = true;
						dl.setCubi("0");
						queries.add( uLog.query_registrarEvento(p.getIdPacking(), 1,107,0,true, session.getId(), " articulo pickeado "+p.getArticulo()));
						queries.add(Logica.query_encuentraAltaNoncontrados(idTarea, dl, 1,p.getIdPosLineaSAP(),idEmpresa));
						queries.add(Logica.query_MovimientosAlmacen(dl, p.getArticulo(), idEmpresa, uLog));
						
						queries.add(Logica.query_encuentraUpdateAvancePicking(idPicking,p.getArticulo(), p.getPick(),p.getOrigen().getId(),p.getDestino().getId(),false,p.getIdPedido(), p.getSolicitud(),idEmpresa,idDepoWEB,0));	//AVANCE DE LA REPO
						
						
					}
					else
					{
						p.setIdPosLineaSAP(porcentaje);
						
						queries.add(uLog.query_registrarEvento(idPicking, 0,107,0,true, session.getId(), "No encontro el articulo "+p.getArticulo()));
						queries.add(Logica.query_justificarNoEncontrado(idPicking,p.getArticulo(),p.getOrigen().getId(),p.getDestino().getId(),p.getIdPedido(),idEmpresa));
						dl.setCubi("0");
						queries.add(Logica.query_encuentraAltaNoncontrados(idTarea, dl, 0,p.getIdPosLineaSAP(),idEmpresa));
						noEncontrados.add(p);
					}
					
					/*3 afectar verificados RA*/
					queries.add(Logica.query_encuentraUpdateVerifPicking(p,false,idEmpresa));
					
					
						
						
				}//fin for pickings
				
				
				
				/*2 terminar tarea*/
				
				
				
				
				queries.add(Logica.query_encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(),0,idEmpresa));
				queries.add(uLog.query_registrarEventoHilo(session.getId(), "Tarea Finalizada", idPicking,101));
				/*4 remitir*/
				System.out.println("REMITIENDO");
				queries.add(Logica.query_encuentraUpdateRemirPickinEC(pickings,idEmpresa));
				
				queries.add(Logica.query_encuentraUpdateLineasPicking(idPicking,idEmpresa));
				queries.add(Logica.query_encuentraUpdateAvanceLineaTarea(idTarea,porcentaje,0,idEmpresa ));
				
				
				
				/*6 afectar otras tablas*/
					/*6.1 afectar pedidos de ecommerce*/
						/*6.1.1 ecommerce articulos req*/
						/*6.1.2 ecommerce pedido*/
					/*6.2 afectar solicitudes de traslado*/
				
				
				
				_EncuentraPersistir pe = new _EncuentraPersistir();
				pe.persistirTransacciones(queries);
				

				Hashtable<Integer, List<DataPicking>> picksEcommerce = new Hashtable<Integer, List<DataPicking>>();
				Hashtable<Integer, List<DataPicking>> picksPedidoMayo = new Hashtable<Integer, List<DataPicking>>();
							
				for (DataPicking p : pickings) 
				{
					if(p.getRemitida()>0)
					{
						if(!p.isMayorista() )
						{
						}
						else
						{
							if(p.getDestino().getId()==idDepoWEB)
							{
								picksEcommerce = util.clasificarOrdenes(p,picksEcommerce);
							}
							else
							{
								picksPedidoMayo = util.clasificarOrdenes(p,picksPedidoMayo);
							}					
						}
					}
				}
				
				List<List<DataPicking>> listaEC = new ArrayList<List<DataPicking>>(picksEcommerce.values());
				List<List<DataPicking>> listaMayo = new ArrayList<List<DataPicking>>(picksPedidoMayo.values());
				
				List<List<DataPicking>> allDataPickings = new ArrayList<List<DataPicking>>();
				
				allDataPickings.addAll(listaEC);
				allDataPickings.addAll(listaMayo);
				
				
				
				
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
								origen = p.getOrigen().getId();
								if(p.getRemitida()>0)
								{
									data = new DataIDDescripcion(p.getRemitida(),p.getArticulo());
									
									data.setIdB(destino);
									if(destino == idDepoWEB) {
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
							queries = new ArrayList<>();
							List<DataIDDescripcion> estados = Logica.darListaDataIdDescripcionConsulMYSQL("select id,descripcion from ecommerce_estado_encuentra where idEmpresa="+idEmpresa);
							estados.remove(0);
							if(remiteAPI)
							{
								System.out.println("Enviando info a visual");
								
								if(list.size()>0)
								{
									if(idEmpresa != 6) {
										String [] retorno = api.prepararRemito(idDepoCentral, destino, uLog, l, idEmpresa, idDepoWEB, list);
										if(!retorno[0].equals(""))
										{
											menError += "DOC "+l.get(0).getSolicitud()+" - "+retorno[0] + " <br/>";
										}
										
										if(retorno[1].equals(""))
											{
												//MODIFICO NUMERO DE DOCUMENTO EN ARTICULOS QUE NO SE AFECTARON EN ESTA DISTRIBUCION
												if(destino == idDepoWEB) 
												{
													Logica.updateDocVisual(l.get(0).getSolicitud(), ArtsSinAfectar, 
														Integer.parseInt(retorno[1]),idEmpresa);
												}
												menError +="Se genero un nuevo documento ("+retorno[1]+") por la diferencia del documento "
												+l.get(0).getSolicitud()+" <br/>";
											}
											
									}
									
									
								}
								pe.persistirTransacciones(queries);
							}
							if(destino == idDepoWEB) 
							{
								String inns = "";
								boolean pri = true;
								
								EcommerceProcessOrders pro = new EcommerceProcessOrders();
								Hashtable<Long, Long> pedidosSalenCentral = pro.darPedidosSinglDepo(origen, idEmpresa, false);
								boolean saleCentral = false;
								
								for(DataIDDescripcion d:list) 
								{
									if(pedidosSalenCentral.get(new Long(d.getDescripcionB()))!= null) {
										saleCentral = true;
									}
									else {
										if(pri)
										{
											inns = d.getDescripcionB();
											pri = false;
										}
										else 
										{
											inns += ","+d.getDescripcionB();
										}										
										if(destino == idDepoWEB) 
										{
											queries.add(util.query_ConfirmoMovimientoEcommerce(d, idDepoCentral, idDepoWEB, idEmpresa, uLog, Logica,estados));
										}
										remitoEC.add(d);
									}
									
									
								}
								if(!saleCentral) {
									/********************muevo los articulos y confirmo las unidades***********************/
									pe.persistirTransacciones(queries);
									
									
									
									/********************actualizo las lineas que CantidadRequerida=cantConfirmada***********************/
									Logica.persistir("UPDATE ecommerce_pedido_articulos_req SET Confirmado=1, subestado=2 WHERE CantidadRequerida=cantConfirmada AND idEmpresa="+idEmpresa+" AND IdPedido IN ("+inns+")");

									
									
									/********************me traigo los pedidos que debo dejar en estado 2 y los cambio***********************/
									queries = new ArrayList<>();
									List<DataIDDescripcion> pedidosConfirmados = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT 0, R.IdPedido FROM ecommerce_pedido_articulos_req R WHERE idEmpresa="+idEmpresa+" AND R.IdPedido IN ("+inns+") group by R.idpedido HAVING SUM(R.CantidadRequerida)=SUM(R.cantConfirmada)");
									for (DataIDDescripcion p : pedidosConfirmados) 
									{
										queries.add(Logica.query_updateEcommerceEstado(new Long(p.getDescripcion()) , 2, idEmpresa, uLog.getNumero(), estados));
									}
									pe.persistirTransacciones(queries);
								}
								
								
							}
							
						} catch (Exception e) 
						{
							System.out.println("*************************ERROR AL ENVIAR A VISUAL*************************");
						}					
						
						
					}
				}
				
				if(!remitoEC.isEmpty())
				{
					int tipoComanda = 3;
					String obsTicket = "Movimiento hacia cliente "+destino;
					if(destino == idDepoWEB) 
					{
						tipoComanda = 1;
						obsTicket = "Remito del picking "+pickings.get(0).getIdPicking();
					}
					
					int cantidad_remitos = util.darParametroEmpresaINT(idEmpresa, 59);
					if(cantidad_remitos > 0) {
						ImpresionesPDF.imprimirTicketMovStock(idDepoCentral, idDepoWEB, uLog.getNick(), obsTicket,remitoEC, pickings.get(0).getIdPicking()+"",
								tipoComanda, uLog.getIdEquipo(),idEmpresa, cantidad_remitos);
					}					
				}
							 
				
				if(!noEncontrados.isEmpty())
				{
					util.EnviarMailNoEncontrados(util, noEncontrados, idEmpresa, api);
				}
										
				session.setAttribute("mensajeVS", menError);
					
					
					
					
					
					

			}
		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			request.setAttribute("mensaje", menError);
			return mapping.findForward("ok");
		}
		return mapping.findForward("ok");	
		
	}

	
	
}















