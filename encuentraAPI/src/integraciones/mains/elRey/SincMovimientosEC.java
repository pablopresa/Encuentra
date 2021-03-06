package integraciones.mains.elRey;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import beans.Usuario;
import beans.api.MovStock;
import beans.api.pedidoFactura;
import beans.api.json.SendMail;
import beans.api.json.SendMailSpooler;
import beans.datatypes.DataIDDescripcion;
import logica.LogicaAPI;
import beans.encuentra.Cliente;
import integraciones.erp.visualStore.elrey.central.ClienteWSELREY;
import integraciones.erp.visualStore.elrey.comercioElectronico.ClienteWSfacturacion;
import integraciones.erp.visualStore.elrey.ecommerce.ClienteWS;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.wms.Call_WS_APIENCUENTRA;



public class SincMovimientosEC 
{
	static LogicaAPI lo = new LogicaAPI();
	static ClienteWSELREY ws = new ClienteWSELREY();
	static ClienteWS wsEC = new ClienteWS();
	static ClienteWSfacturacion wsF = new ClienteWSfacturacion();
	static Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
	static String msj, idMail;
	
	public static void main(String[] args) 
	{	
		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(4,4000);
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		int idEmpresa = u.getIdEmpresa();
				
		while (true) {
			msj = "";
			idMail = "";
			//putClientes(idEmpresa);
			try {
				putMovimientosStocks(idEmpresa);
			} catch (Exception e) {System.out.println("Error grabando movimientos de stock");}
			
			try {
				Hashtable<Integer,pedidoFactura> pedidos = putFacturacion(idEmpresa);
				BuscarFacturas(pedidos, token);
			} catch (Exception e) {System.out.println("Error grabando facturas");}
			
			try {
				if(!msj.equals("")) {
					MailErrores(idMail, msj);
				}				
			} catch (Exception e) {System.out.println("Error enviando mail de errores");}
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				 
	}
	private static void putMovimientosStocks(int idEmpresa)
	{
		List<MovStock> movs = lo.queuePendingMovsStock(idEmpresa,0);
		
		DataIDDescripcion respVisual;
		for(MovStock mov:movs) {
			try {
				respVisual = wsEC.GrabarTransferencia(mov.getOrigen(), mov.getDestino(), 0, mov.getDetalle(),
					"Transferencia realizada desde encuentra");
				if(respVisual.getId()==0) {
					lo.RegistrarDocMovimientoStock(0,  mov.getId(), mov.getDoc(), respVisual.getDescripcion(),
							idEmpresa,0,respVisual.getIdB());
					//notificacion de errores
					msj += respVisual.getDescripcion()+" <br>";
					idMail = mov.getOrigenDoc()+"";
				}
				else {
					lo.RegistrarDocMovimientoStock(1,  mov.getId(), respVisual.getId(), respVisual.getDescripcion(),
							idEmpresa,0,respVisual.getIdB());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}

	private static Hashtable<Integer,pedidoFactura> putFacturacion(int idEmpresa) {
		Hashtable<Integer,pedidoFactura> pedidos = new Hashtable<>();
		List<OrdenVenta> ordenes = lo.darOrdensSinFacturar(idEmpresa,0L);
		
		DataIDDescripcion respVisual;
		for(OrdenVenta o:ordenes) {
			try {
				Cliente c= lo.darCliente(o.getIdCarrito(), idEmpresa);
				respVisual = wsF.GrabarFactura(o, c);
				
				lo.RegistrarFactura(o.getIdCarrito(), respVisual.getId(), idEmpresa,respVisual.getDescripcion());
				if(respVisual.getId()!=0) {
					lo.SincronizarCliente(c.getDocumentoNro(), idEmpresa);
					
					pedidos.put(respVisual.getId(), new pedidoFactura(o.getIdCarrito(), respVisual.getId()));
				}
				else {
					
					if(respVisual.getDescripcion().contains("Cliente")){
						c = lo.darClienteProducteca(o.getIdCarrito());
						if(c != null) {
							lo.saveImport1Customer(c, idEmpresa);
						}
						
					}
					//notificacion de errores
					//msj += respVisual.getDescripcion()+" \r\n";
					//idMail = o.getIdCarrito()+"";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		return pedidos;
	}
	
	private static void putClientes(int idEmpresa) {
		List<Cliente> clientes = lo.darClientesSinGrabar(idEmpresa);
		
		for(Cliente c:clientes) {
			ws.AltaCliente(c);
			lo.SincronizarCliente(c.getDocumentoNro(), idEmpresa);
		}
	}

	public static void BuscarFacturas(Map<Integer,pedidoFactura> pedidos, String token) {
		
		//miro si hay facturas sin pdf
		pedidos = lo.pedidosSinFactura(pedidos);
		
		//voy a buscar las facturas a visual
		pedidos = lo.facturasVisual(pedidos);
		
		//persisto pdfs en bd
		List<pedidoFactura> listaPedidos= new ArrayList<>(pedidos.values());
		lo.guardarFacturas(listaPedidos);
		
		//envio facturas a WMS
		api.putFacturas(token, listaPedidos);
	}

	public static void MailErrores(String idMail,String msj) {
		String bodyName = msj;
		SendMail mail = new SendMail("SMREY"+idMail, "gdelisa@200.com.uy,onviera@200.com.uy,gmonzon@200.com.uy,emely.rodriguez@elreydelentretenimiento.com", "Error en sincro de pedidos - EL REY", bodyName, "encuentra@200.com.uy", null);
		SendMailSpooler mails = new SendMailSpooler();
		mails.setMails(new SendMail[]{mail});
		
		LogicaAPI.PutMailSpooler(mails.getMails(), 4);
	}
}
