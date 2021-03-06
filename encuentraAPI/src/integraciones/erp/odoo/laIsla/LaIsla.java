package integraciones.erp.odoo.laIsla;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import beans.Usuario;
import beans.api.MovStock;
import beans.api.pedidoFactura;
import beans.api.json.SendMail;
import beans.api.json.SendMailSpooler;
import beans.datatypes.BillingEntities;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.MetodoPago;
import beans.datatypes.Remito;
import beans.datatypes.StockDeposito;
import beans.derivation.OrderDerivator;
import beans.encuentra.Cliente;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import beans.helper.PropertiesHelperAPI;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.marketplaces.fenicio.Descuento;
import integraciones.marketplaces.fenicio.Documento;
import integraciones.marketplaces.fenicio.Lineas;
import integraciones.marketplaces.fenicio.Ordenes;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import persistencia._EncuentraConexionAPI2;

public class LaIsla 
{
	private final LogicaAPI lo = new LogicaAPI();
	private final String token = lo.darToken(6,6000);
	private final Usuario u = LogicaAPI.loginEncuentraAPI2(token);
	private final int idEmpresa = u.getIdEmpresa();
	private final ClienteOdoo_LaIsla coli = new ClienteOdoo_LaIsla(true);
	private String msj ="", idMail="";

	public LaIsla()
	{
		
	}
	
	public Integer abrirSessionVentas() {
		int idSession = 0;
		try {
			idSession = this.coli.abrirSession();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return idSession;
	}
	
	public void cerrarSessionVentas(int idSession) {
		try {
			this.coli.cerrarSesion(idSession);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int recibirAlbaran(int idAlbaran, boolean pickiDup) {
		return this.coli.recibirAlbaran(idAlbaran, pickiDup);
	}
	
	public EstadoAlbaranes verEstadoAlbaranesFiltrados(int filtro, int id) {
		return this.coli.verEstadoAlbaranesFiltrados(filtro, id);
	}

	public List<StockArticulos> buscarStockArticulo(List<ArticuloCantidadEncuentra> articulos, Map<Integer, Integer> excluir_deps) {
		return this.coli.buscarStockArticulo(articulos, excluir_deps);
	}
	
	public int revertir_transferencia_parcial(int idAlbaran, Map<String, Integer> articulosCancelados) {
		return this.coli.revertir_transferencia_parcial(idAlbaran, articulosCancelados);
	}
	
	public int obtenerIDArticuloOdoo(String defaultCode) {
		return this.coli.obtenerIDArticuloOdoo(defaultCode);
	}
	
	public String obtenerDatos(String nombreMetodo) throws Exception {
		return this.coli.obtenerDatos(nombreMetodo);
	}
	
	public List<StockDeposito> obtenerStockArticulos(){
		return this.coli.obtenerStockArticulos();
	}
	
	public List<Integer> crearAlbaranInterno(PedidoAlbaran pa, Long idPedido){
		return this.coli.crearAlbaranInterno(pa, idPedido);
	}
	
	public List<Remito> albaranesListosParaRecibir(int idDeposito, int idEmpresa) {
		List<EstadoAlbaranes> albaranes = new ArrayList<>();
		List<Remito> remitos = new ArrayList<>();
		try {
			albaranes = this.coli.albaranesListosParaRecibir(idDeposito);
			
			StringBuilder albaranesBuscados = new StringBuilder();
			
			for(EstadoAlbaranes albs : albaranes) {
				albaranesBuscados.append("" + albs.getId() + ",");
			}
			String sAlbaranesBuscados = albaranesBuscados.toString();
			
			if(!sAlbaranesBuscados.equalsIgnoreCase("")) {
				sAlbaranesBuscados = sAlbaranesBuscados.substring(0, sAlbaranesBuscados.length() - 1);
			}
			
			remitos = _EncuentraConexionAPI2.darRemitos(sAlbaranesBuscados, idEmpresa);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return remitos;
	}
	
	public void stat(int journal_id , int pos_session_id) {
		 coli.account_bank_statement(journal_id , pos_session_id);
	}
	
	public void mover ()
	{
		int idSession = -1;
		try {
			idSession = generarIDSession();
		} catch (Exception e) {System.out.println("Error al generar id session");}

		
		msj = "";
		idMail = "";
		try 
		{
			putMovimientosStocksATransito(idEmpresa);
		} 
		catch (Exception e) {System.out.println("Error grabando movimientos de stock");}
		try 
		{
			putMovimientosStocksAEncuentra(idEmpresa);

		}
		catch (Exception e) {System.out.println("Error transfiriendo albaranes a encuentra");}
		try 
		{
			
			Hashtable<Integer,pedidoFactura> pedidos = putFacturacion(idEmpresa, idSession);
			BuscarFacturas(pedidos);
		} 
		catch (Exception e) {System.out.println("Error grabando facturas");}

		try {
			if(!msj.equals("")) {
				//MailErrores(idMail, msj);
			}				
		} catch (Exception e) {System.out.println("Error enviando mail de errores");}
		
		try 
		{
			Thread.sleep(60000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void putMovimientosStocksATransito(int idEmpresa)
	{//verEstadoAlbaranesFiltrados(int filtro, ArrayList<Integer> ids)
		try {
			List<MovStock> movs = lo.queuePendingMovsStock(idEmpresa,0);
			
			for(MovStock mov:movs) {
					if(mov.getOrigen() == 145) {
						revertirAlbaran(mov);						
					}
					else {
						moverAlbaran(mov);
					}					
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public DataIDDescripcion revertirAlbaran(MovStock mov) {
		int new_albaran = 0;
		Hashtable<String, Integer> articulosCancelados = new Hashtable<>();
		Hashtable<String, ArticuloCantidadEncuentra> hashartCantEnc = new Hashtable<>();; 
		List<DataIDDescripcion> arts = new ArrayList<>();
		List<EncuentraPedidoArticulo> pedidoArticulo = new ArrayList<>();
		Hashtable<Integer, Integer> excluir_deps;
		MovStock movOrigial;
		try {
			// depositos a los cuales ya pedi
			excluir_deps = lo.solicitud_a_origenes(idEmpresa, mov.getOrigenDoc(), mov.getDetalle());
			// contenido original del movimiento
			movOrigial = lo.MovStock_articulo(idEmpresa, mov.getOrigenDoc(), mov.getDestino(), mov.getDetalle());						
			
			//reversion			
			for(DataIDDescripcion d: mov.getDetalle()) {
				articulosCancelados.put(d.getDescripcion(), d.getId());
				
				arts.add(new DataIDDescripcion(1, d.getDescripcion()));
				ArticuloCantidadEncuentra ace = new ArticuloCantidadEncuentra(1.0, 0, d.getDescripcion());								 
				hashartCantEnc.put(d.getDescripcion(), ace);
				EncuentraPedidoArticulo pa = new EncuentraPedidoArticulo();
				pa.setArticulo(d.getDescripcion());
				pa.setCantidad(d.getId());
				pa.setDistribucionAfecta(mov.getOrigenDoc()+"");
				pedidoArticulo.add(pa);
			}
						
			if(movOrigial.getDetalle().size() == mov.getDetalle().size()) {
				//new_albaran = coli.revertir_transferencia_parcial(movOrigial.getDoc(), articulosCancelados);
				new_albaran = coli.revertir_transferencia_parcial(movOrigial.getDocSolicitud(), articulosCancelados);				
			}
			else {							
				//new_albaran = coli.revertir_transferencia_parcial(movOrigial.getDoc(), articulosCancelados);
				new_albaran = coli.revertir_transferencia_parcial(movOrigial.getDocSolicitud(), articulosCancelados);				
			}
		} catch (Exception e) {
			lo.RegistrarDocMovimientoStock(0,  mov.getId(), new_albaran, "Fallo al generar albaran", idEmpresa,0, 1);
			return null;
		}
		
		//new_albaran = 1;
		if(new_albaran != 0) {
			lo.RegistrarDocMovimientoStock(1,  mov.getId(), new_albaran, "OK", idEmpresa,0, 1);
			lo.RegistrarDocMovimientoStock(2,  movOrigial.getId(), movOrigial.getDoc(), "CANCELADO", idEmpresa,0, 1);	//cancelado
		}
		else {
			lo.RegistrarDocMovimientoStock(0,  mov.getId(), new_albaran, "Fallo al generar albaran", idEmpresa,0, 1);
		}
		
		//new_albaran = 0;
		//rederivacion
		if(new_albaran != 0) {
			List<EncuentraPedido> pedidos = new ArrayList<>();
			EncuentraPedido p = new EncuentraPedido();
			p.setIdPedido(mov.getOrigenDoc());
			p.setArticulosPedido(pedidoArticulo);
			p.setIdDepositoEnvio(-1);
			pedidos.add(p);
			
			Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
			List<DataIDDescripcion> depositosDestino =cen.DarDatosPutOrders(token, 2);
			 
			 Hashtable<Integer, DataIDDescripcion> mailsDepositos = new Hashtable<Integer, DataIDDescripcion>();
			 for(DataIDDescripcion d: depositosDestino) 
			 {
				 mailsDepositos.put(d.getId(), d);
			 }
			 
			DataIDDescripcion datosMail= mailsDepositos.get(mov.getDestino());
			datosMail.setDescripcionB(mailsDepositos.get(0).getDescripcionB());
			
			OrderDerivator od = new OrderDerivator();
			List<SendMail> mails = new ArrayList<SendMail>();
			mails.add(od.mailNoEncontrados(pedidoArticulo, datosMail));
			LogicaAPI.PutMailSpooler(mails.toArray(new SendMail[mails.size()]), idEmpresa);
			
			od.operaciones(coli, hashartCantEnc, arts, pedidos, idEmpresa, false, true, excluir_deps, mailsDepositos);
		}
		return null;
	}
	
	public DataIDDescripcion moverAlbaran(MovStock mov) {
		DataIDDescripcion salida = new DataIDDescripcion(0,"");
		salida.setDescripcionB("");
		try {
			int idAlbaran = 0;
			int intento = 0;
			
			if(mov.getDoc() == 0) {
				intento = 1;
				List<ArticuloCantidadEncuentra> arts = new ArrayList<>();
				for(DataIDDescripcion det : mov.getDetalle()) {
					Cliente cli= lo.darCliente(mov.getOrigenDoc(), idEmpresa);
					ArticuloCantidadEncuentra ace = new ArticuloCantidadEncuentra(det.getId(), mov.getOrigen(), det.getDescripcion(), cli);
					arts.add(ace);
				}
				PedidoAlbaran pa = new PedidoAlbaran(arts, mov.getDestino());
				List<Integer> idsAlbaranes = coli.crearAlbaranInterno(pa, mov.getOrigenDoc());
				if(idsAlbaranes.size() == 0) {
					salida.setId(0);
					salida.setDescripcion("Fallo al generar albaran");
					lo.RegistrarDocMovimientoStock(0,  mov.getId(), mov.getDoc(), "Fallo al generar albaran", idEmpresa,0, intento);
					//notificacion de errores
					msj += "Fallo al generar albaran, id movimiento: "+ mov.getId() + " \r\n";
					idMail = mov.getOrigenDoc()+"";
				} else {
					idAlbaran = idsAlbaranes.get(0);
				}
			} else {
				idAlbaran = mov.getDoc();
			}
			//comprobar lo de la entrega = 1
			if(idAlbaran > 0) {
				intento = intento == 1 ?  0 : 1;
				EstadoAlbaranes estadoConfirmado = coli.verEstadoAlbaranesFiltrados(2, idAlbaran);
				if(estadoConfirmado != null) {	
					//guardar como confirmado
					lo.RegistrarDocMovimientoStock(0,  mov.getId(), idAlbaran, "Listo para transferir",
							idEmpresa,0,intento);
					//transferir a transito
					int idNuevo = coli.transferir_aTransito_nuevo(idAlbaran);
					EstadoAlbaranes estado = coli.verEstadoAlbaranesFiltrados(3, idAlbaran);
					if(estado == null && idNuevo <=0) {
						salida.setId(0);
						salida.setDescripcion("Fallo al intentar transferir a transito");
						lo.RegistrarDocMovimientoStock(0,  mov.getId(), idAlbaran, "Fallo al intentar transferir a transito", idEmpresa,0, intento);
						//notificacion de errores
						msj += "Fallo al intentar transferir a transito, id movimiento: "+ mov.getId() + ", id del albaran: " + idAlbaran + " \r\n";
						idMail = mov.getOrigenDoc()+"";
					} else {
						salida.setId(1);
						salida.setDescripcion("");
						lo.RegistrarDocMovimientoStock(1,  mov.getId(), idNuevo, "OK",
								idEmpresa,0,intento, idAlbaran);
						//insertar en la otra tabla con estado 0 si entrega es = 1
						if(mov.isEntrega()) {
							lo.RegistrarConfirmacionTransferencia(mov.getOrigen(), mov.getDestino(), mov.getIdUsuario(), mov.getDetalle(), idEmpresa, mov.getOrigenDoc(), 
									mov.getDocSolicitud(), mov.getRazon(), idNuevo, true); //no iria idNuevo?
						}
						mov.setEstado(1);
					}
				} else {
					EstadoAlbaranes estadoSinConfirmar = coli.verEstadoAlbaranesFiltrados(1, idAlbaran);
					if(estadoSinConfirmar != null) {
						salida.setId(0);
						salida.setDescripcion("No se pudieron reservar los articulos");
						lo.RegistrarDocMovimientoStock(0,  mov.getId(), idAlbaran, "No se pudieron reservar los articulos",
								idEmpresa,0,intento);
						coli.marcarPorHacer(idAlbaran);
						coli.comprobarDisponibilidad(idAlbaran);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public void putMovimientosStocksAEncuentra(int idEmpresa) 
	{
		List<MovStock> movs = lo.queueConfirmacionTransferencia(idEmpresa); 
		try {
			for(MovStock mov:movs) {
				int idAlbaran = mov.getDoc();
				int idAlbaranTransferido = coli.recibirAlbaran(idAlbaran, true);
				if(idAlbaranTransferido > 0) {
					EstadoAlbaranes estadoREC = coli.verEstadoAlbaranesFiltrados(3, idAlbaranTransferido);
					if(estadoREC == null) {
						lo.RegistrarEstadoConfirmacionTransferencia(false, mov.getId(), "Fallo al intentar transferir a Encuentra", idEmpresa, 0, 1);
						msj += "Fallo al intentar transferir a encuentra, id movimiento: "+ mov.getId() + ", id del albaran: " + idAlbaran + " \r\n";
						idMail = mov.getOrigenDoc()+"";
					} else {
						lo.RegistrarEstadoConfirmacionTransferencia(true, mov.getId(), "OK", idEmpresa, 0, 1);
						System.out.println("Se hizo la transeferencia a Encuentra");
					}
				}
				else {
					lo.RegistrarEstadoConfirmacionTransferencia(false, mov.getId(), "Fallo al intentar transferir a Encuentra", idEmpresa, 0, 1);
					msj += "Fallo al intentar transferir a encuentra, id movimiento: "+ mov.getId() + ", id del albaran: " + idAlbaran + " \r\n";
					idMail = mov.getOrigenDoc()+"";
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validarCliente (Ordenes of, Cliente c) {
		try {
			if(of.getComprador().getDocumento().getTipo().equalsIgnoreCase("PASAPORTE")) {
				of.getComprador().getDocumento().setNumero(c.getDocumentoNro());
			}
			if(of.getComprador().getNombre().equalsIgnoreCase("Usuario") && of.getComprador().getApellido().equalsIgnoreCase("Gen?rico")) {
				of.getComprador().setNombre(c.getNombre());
				of.getComprador().setApellido(c.getApellido());
				of.getComprador().setEmail(c.getEmail());
				of.getComprador().setTelefono(c.getTelefono());
			}
		} catch (Exception e) {
			System.out.println("Cliente sin documento");
			
			Documento d = new Documento();
			of.getComprador().setDocumento(d);
			of.getComprador().getDocumento().setTipo("DOCUMENTO_IDENTIDAD");
			of.getComprador().getDocumento().setNumero(c.getDocumentoNro());
			of.getComprador().getDocumento().setPais("UY");
			
			if(of.getComprador().getNombre().equalsIgnoreCase("Usuario") && of.getComprador().getApellido().equalsIgnoreCase("Gen?rico")) {
				of.getComprador().setNombre(c.getNombre());
				of.getComprador().setApellido(c.getApellido());
				of.getComprador().setEmail(c.getEmail());
				of.getComprador().setTelefono(c.getTelefono());							
			}
		}
	}

	public  Hashtable<Integer,pedidoFactura> putFacturacion(int idEmpresa, int idSession) 
	{
		Hashtable<Integer,pedidoFactura> pedidos = new Hashtable<>();
		List<OrdenVenta> ordenes = lo.darOrdensSinFacturar(idEmpresa,0L);
		//List<pedidoFactura> facturas = new ArrayList<>();
		DataIDDescripcion respLaIsla;
		beans.api.json.PrintObject p = null;
		for(OrdenVenta o:ordenes) 
		{
			try 
			{
				Cliente c= lo.darCliente(o.getIdCarrito(), idEmpresa);
				
				if(idSession <= 0) 
				{
					idSession = generarIDSession();
				}
				
				if(idSession > 0) {
					//TRAIGO ORDEN DE FENICIO
					Ordenes of = OrdenVentaFenicio(o.getIdCarrito());
					validarCliente (of, c);
					
					
					//BUSCO FORMAS DE PAGO Y DESCUENTOS
					BillingEntities billing_entities = obtenerPagoOdoo(of);			
					//MAPEO LOS ARTICULOS
					lo.getMapeoArticulosfenicio(of.getLineas(), idEmpresa);
					
					//FACTURO
					respLaIsla = coli.facturarPedido(of, billing_entities, idSession);					
					
					lo.RegistrarFactura(o.getIdCarrito(), respLaIsla.getId(), idEmpresa,respLaIsla.getDescripcion());
					if(respLaIsla.getId()!=0) {							
						lo.SincronizarCliente(c.getDocumentoNro(), idEmpresa);
						//pedidoFactura pf = new pedidoFactura(o.getIdCarrito(), respLaIsla.getId());
						pedidoFactura pf = new pedidoFactura(o.getIdCarrito(), respLaIsla.getId());
						
						String factura = descargarFactura(
								"https://pos.laisla.com.uy/report/pdf/point_of_sale_invoice.report_venta_document/"+respLaIsla.getId(), 
								respLaIsla.getId()+"");
						
						pf.setPdf(factura);
						//facturas.add(pf);
						pedidos.put(respLaIsla.getId(), pf);
						
						int vias = 1;
						if(o.getPrinter() == 180) {
							vias = 2;
						}
						
						
						p = new beans.api.json.PrintObject();
						p.setId("LI"+pf.getIdPedido());
						p.setUrl(pf.getPdf());
						p.setPorait(0+"");
						p.setPrinterId("2");
						p.setIdEquipo(o.getPrinter()+"");
						p.setVias(vias);
			            
						LogicaAPI.PutPrintSpooler(p,idEmpresa);
					}
					else {						
						//notificacion de errores
						msj += respLaIsla.getDescripcion()+" \r\n"; //YO LE CARGO EL TICKET STR
						idMail = o.getIdCarrito()+"";
					}
					
					
					
				} else {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return pedidos;
	}
	

	public void BuscarFacturas(Hashtable<Integer,pedidoFactura> pedidos) {
		
		//miro si hay facturas sin pdf
		//pedidos = lo.pedidosSinFactura(pedidos);
		
		//voy a buscar las facturas a visual
		//pedidos = lo.facturasVisual(pedidos);
		
		//persisto pdfs en bd
		List<pedidoFactura> listaPedidos= new ArrayList<>(pedidos.values());
		//lo.guardarFacturas(listaPedidos);
		
		//envio facturas a WMS
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		api.putFacturas(token, listaPedidos);
	}


	public void MailErrores(String idMail,String msj) {
		String bodyName = msj;
		SendMail mail = new SendMail("LA ISLA"+idMail, "onviera@200.com.uy,gmonzon@200.com.uy", "Error en sincro de pedidos - LA ISLA", bodyName, "encuentra@200.com.uy",null);
		SendMailSpooler mails = new SendMailSpooler();
		mails.setMails(new SendMail[]{mail});
		
		LogicaAPI.PutMailSpooler(mails.getMails(), 6);
	}
	
	public Ordenes OrdenVentaFenicio(Long idOrden){
		Ordenes of = null;
		try 
		{
			//PropertiesHelper ph = new PropertiesHelper("paths");
			LogicaAPI log = new LogicaAPI();
			Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
	    	//ph.loadProperties();
	    	//String api_host = ph.getValue("api");
			String token_API=log.darToken(6,6000);		
			//String host = api_host;
			//String jsonEP = cwa.callWSGET("http://"+host+"/encuentraAPI/Integraciones/another/getFenicioOrder?token="+token_API+"&orden="+idOrden+"&canal=0",false);
			String jsonEP = cwa.callWSGET("https://laisla.com.uy/API_V1/ordenes/"+idOrden, false);
			
			
			Gson gson = new Gson();
			
			String[] parts = jsonEP.split("\"orden\":");
			String orden = parts[1].substring(0, parts[1].length()-1);
		
			of = gson.fromJson(orden , Ordenes.class);				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();				
		}
		return of;
	}
	
	public BillingEntities obtenerPagoOdoo(Ordenes of) 
	{
		try 
		{
			BillingEntities billing_entities = pagoFenicio(of);
			_EncuentraConexionAPI2.darMapeoFormaPago(billing_entities.getMetodos_pagos(), 6);
			if(!billing_entities.getDescuentos().isEmpty()) {
				_EncuentraConexionAPI2.darMapeoDescuento(billing_entities.getDescuentos());
			}			
			return billing_entities;			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public BillingEntities pagoFenicio(Ordenes of) {
		String tarjetaUsa = "error";
		boolean esSantander = false;
		int descuento = 0;
		BillingEntities billing_entities = new BillingEntities();
		try {
			if(of != null && of.getPago() != null) {				
				
				
				String formaPago = of.getPago().getCodigo().toLowerCase();
				String conector = of.getPago().getConector().toLowerCase();
				int nroCuotas = Integer.parseInt(of.getPago().getCuotas());
				String origen = of.getOrigen().toLowerCase();
				String claveComp = formaPago+":"+conector;
				
				
				Lineas[] lineas = of.getLineas();
				//Descuento[] array_descuentos = (lineas[0].getDescuentos() != null ? lineas[0].getDescuentos() : null);
				/*List<Descuento> descuentos = Arrays.asList(array_descuentos);
				DataIDDescripcion salidaLambda = new DataIDDescripcion();
				
				descuentos.stream()
				.filter((x) -> x.getNombre().toLowerCase().contains("santander"))
				.forEach((n) -> 
				{
					salidaLambda.setIn(true);	// esSantander?
					try {
						salidaLambda.setId(Integer.parseInt(n.getNombre().split("%")[0]));	//descuento
					} catch (Exception e) {
						salidaLambda.setId(0);
					}					
				});
				
				esSantander = salidaLambda.isIn();
				descuento = salidaLambda.getId();*/
				Double amount = Double.parseDouble(of.getImporteTotal());
				Double amount_gift = 0.0;
				
				try {
					Hashtable<String, Descuento> descuentosHT = new Hashtable<>();
					for(Lineas l: lineas) {	
						if(l.getDescuentos( )!= null) {
							List<Descuento> descuentoProducto = new ArrayList<>();
							for(Descuento d: l.getDescuentos()) {
								if (d.getOrigen().equalsIgnoreCase("PRODUCTO")) {
									descuentoProducto.add(d);
								}
								else {
									if (d.getOrigen().equalsIgnoreCase("CUPON") && d.getNombre().contains("%")){	//cupones que aplican 
																												//descuento en linea de producto											
										descuentoProducto.add(d);										
									}
									else {
										if(descuentosHT.get(d.getNombre()) == null){
											descuentosHT.put(d.getNombre(), d);
										}
										else {						
											Double monto = Double.parseDouble(d.getMonto()) + Double.parseDouble(descuentosHT.get(d.getNombre()).getMonto());
											descuentosHT.get(d.getNombre()).setMonto(monto+"");
										}
									}									
								}								
							}
							l.setDescuentos(descuentoProducto.toArray(new Descuento[descuentoProducto.size()]));
						}						
					}	
					
					List<Descuento> descuentos = new ArrayList<>(descuentosHT.values());				
					for(Descuento d: descuentos) {
						if(d.getOrigen().equalsIgnoreCase("MEDIO_DE_PAGO")) {	//es un metodo de pago 
							if(d.getNombre().toLowerCase().contains("santander")) {
								esSantander = true;
								descuento = Integer.parseInt(d.getNombre().split("%")[0]);
								switch(claveComp) {
								case "master:plexo": 
									//esSantander = primera.getNombre().toLowerCase().contains("santander"); //Tengo que verificar que 
									if(esSantander && nroCuotas > 1) {								//tenga descuento santander ya que el mapeo es distinto
										tarjetaUsa = "MASTER SANTANDER CREDITO";
									} else if (esSantander && nroCuotas == 1) {
										tarjetaUsa = "MASTER SANTANDER DEBITO";
									}
									break;
								case "visa:plexo":
									//esSantander = primera.getNombre().toLowerCase().contains("santander");
									if(esSantander && nroCuotas > 1) {
										tarjetaUsa = "VISA SANTANDER CREDITO";
									} else if (esSantander && nroCuotas == 1) {
										tarjetaUsa = "VISA SANTANDER DEBITO";
									}
									break;
								}
								billing_entities.getMetodos_pagos().add(new MetodoPago(0, tarjetaUsa, null, amount));
								d.setCodigo(descuento+" - "+tarjetaUsa);
								billing_entities.getDescuentos().add(d);
							}
						}
						else if (d.getOrigen().equalsIgnoreCase("CUPON")){	//puede ser un cupon o una gift card (las gift card se computan como metodo de pago)
							if(d.getNombre().contains("%")) {	//es un cupon
								/*d.setCodigo("Diferencia de Precio");
								billing_entities.getDescuentos().add(d);*/
							}
							else {		//es una gift card
								billing_entities.getMetodos_pagos().add(new MetodoPago(0, "GIFT-CARD", d.getCodigo(), Double.parseDouble(d.getMonto())));
							}
						}
					}
				} catch (Exception e) {
					System.out.println("Error buscando metodos de pagos y descuentos");
				}
				
				
				tarjetaUsa = "";
				if(!esSantander) {
					switch(claveComp) { //Concateno la forma de pago con el conector, en base a esto filtro con el switch
					case "amex:amex": 
						tarjetaUsa = "AMEX";
						break;
					case "redpagos:cobrosya":  //No los hago genericos por si cambia con alguna otra forma de pago el conector
						tarjetaUsa = "COBROS YA";
						break;
					case "ebrou:cobrosya": 
						tarjetaUsa = "COBROS YA";
						break;
					case "santandersupernet:cobrosya":
					case "bbvanet:cobrosya": 
						tarjetaUsa = "COBROS YA";
						break;
					case "master:plexo": 
						//esSantander = primera.getNombre().toLowerCase().contains("santander"); //Tengo que verificar que 
						if (nroCuotas > 1) { // De acuerdo a las cuotas cambia la forma de pago
							tarjetaUsa = "MASTER CREDITO";
						} else if (nroCuotas == 1) {
							tarjetaUsa = "MASTER DEBITO";
						}
						break;
					case "redpagos:mercadopago_api":
					case "creditel:mercadopago_api":
					case "abitab:mercadopago_api": 
					case "mercadopago:mercadopago": 
						tarjetaUsa = "MERCADOPAGO";
						break;
					case "pagopedido:fenicio":
					case ":fenicio":
						tarjetaUsa = origen.contains("mercadolibre") ? "MERCADOLIBRE" : "error";
						break;
					case "oca:plexo":
						tarjetaUsa = "OCA";
						break;
					case "visa:plexo":
						//esSantander = primera.getNombre().toLowerCase().contains("santander");
						tarjetaUsa = "VISA";
						
						break;
						
					default:
						//mandar mail cuando entre aca
						tarjetaUsa = "PAGO WEB";
						break;
						
						
					//******** Faltan los mapeos de las ordenes viejas
					
					}
				}				
				
				if(tarjetaUsa.equalsIgnoreCase("error")) {
					System.out.println("No se encontro forma de pago para esta orden.");
					//persistir en bd en el comentario de la orden
				}
				if(!tarjetaUsa.equalsIgnoreCase("")) {
					billing_entities.getMetodos_pagos().add(new MetodoPago(0, tarjetaUsa, null,amount));
				}
			}
		} catch(Exception e) {
			tarjetaUsa = "PAGO WEB";
			e.printStackTrace();
		}
		
		return billing_entities;
	}
	
	public int generarIDSession() {
		int idSession = -1;
		String fecha = "";
		try {
			DataIDDescripcion data = _EncuentraConexionAPI2.darDataIdDescripcion("SELECT 0,obtenido,token from apitoken_cliente where idCliente = 99 and idEmpresa = 6 and token <> 0 and token <> -1;");
			fecha = data.getDescripcion();
			try {
				idSession = Integer.parseInt(data.getDescripcionB());
			} catch (Exception e) {
				System.out.println("No hay sessionId");
			}
			
			
			if(fecha != null) {
				String[] fechaHora = fecha.split(" ");
				String[] fechas = fechaHora[0].split("-");
				LocalDate fechaCreado = LocalDate.of(Integer.parseInt(fechas[0]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[2]));
				String[] horas = fechaHora[1].split(":");
				LocalTime horaCreado = LocalTime.of(Integer.parseInt(horas[0]), Integer.parseInt(horas[1]));
				LocalDateTime seCreoToken = LocalDateTime.of(fechaCreado, horaCreado);
				
				int diaDespues = Integer.parseInt(fechas[2]) + 1;
				int mesDespues = Integer.parseInt(fechas[1]);
				int anioDespues = Integer.parseInt(fechas[0]);
				switch (mesDespues) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					if(diaDespues > 31) {
						diaDespues = diaDespues - 31;
						mesDespues = mesDespues + 1;
					}
					if(mesDespues == 12) {
						mesDespues = 1;
						anioDespues = anioDespues +1;
					}
					
					break;
				case 4:				
				case 6:				
				case 9:				
				case 11:		
					if(diaDespues > 30) {
						diaDespues = diaDespues - 30;
						mesDespues = mesDespues + 1;
					}
					break;
				case 2:
					if(diaDespues > 28) {
						diaDespues = diaDespues -28;
						mesDespues = mesDespues + 1;
					}
					break;

				default:
					break;
				}
				LocalDate diaVencimiento = LocalDate.of(anioDespues, mesDespues, diaDespues);
				LocalTime horaVencimiento = LocalTime.of(1, 30);
				LocalDateTime vencimiento = LocalDateTime.of(diaVencimiento, horaVencimiento);
				
				System.out.println("diferencia minutos: "+ChronoUnit.MINUTES.between(vencimiento, LocalDateTime.now()));
				if(ChronoUnit.MINUTES.between(vencimiento, LocalDateTime.now()) > 0) {
					//idSession = Integer.parseInt(_EncuentraConexionAPI2.dartokenApi("SELECT token from apitoken_cliente where idCliente = 99 and idEmpresa = 6;"));
					coli.cerrarSesion(idSession);
					_EncuentraConexionAPI2.persistir("DELETE FROM apitoken_cliente WHERE idCliente = 99 and idEmpresa = 6;");
					System.out.println("Cerre session");
					System.out.println("Abriendo nueva session");
					idSession = coli.abrirSession();
					if(idSession!=0 && idSession!=-1) {
						_EncuentraConexionAPI2.persistir("INSERT INTO apitoken_cliente (idCliente, token, idEmpresa) VALUES (99, '"+idSession+"', 6) ON DUPLICATE KEY UPDATE token = '"+idSession+"';");
						System.out.println("Abriendo session de pos");
					}
					else {
						System.out.println("No se pudo abrir session de pos");
					}
				} else {
					//idSession = Integer.parseInt(_EncuentraConexionAPI2.dartokenApi("SELECT token from apitoken_cliente where idCliente = 99 and idEmpresa = 6;"));
					System.out.println("Session valida");
				}
			} else {
				idSession = coli.abrirSession();
				if(idSession!=0 && idSession!=-1) {
					_EncuentraConexionAPI2.persistir("INSERT INTO apitoken_cliente (idCliente, token, idEmpresa) VALUES (99, '"+idSession+"', 6) ON DUPLICATE KEY UPDATE token = '"+idSession+"';");
					System.out.println("Abriendo session de pos");
				}
				else {
					System.out.println("No se pudo abrir session de pos");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idSession;
	}
	
	/*public void LimpiarListaArticulos (Orden o) {
		for(Lineas l: o.getLineas())
		{
			System.out.println("OLD SKU ==> "+o.getLineas()[i].getSku());
			o.getLineas()[i].setSku(ov.getOrdenVentaLineas().get(i).getIdArticulo());
			System.out.println("NEW SKU ==> "+o.getLineas()[i].getSku());
		}
	}*/
	
	public void testRecepcion(int idAlbaran){
		coli.testRecepcion(idAlbaran);
	}
	
	public void cerrarSession (int idSession) {
		coli.cerrarSesion(idSession);
	}
	
	public String descargarFactura(String url, String fileName) {
		String factura = "";
		try {
			OkHttpClient client = new OkHttpClient().newBuilder()
					  .build();
					MediaType mediaType = MediaType.parse("text/plain");
					RequestBody body = new okhttp3.MultipartBody.Builder().setType(okhttp3.MultipartBody.FORM)
					  .addFormDataPart("password","1234ws_encuentra.1")
					  .addFormDataPart("login","ws_encuentra")
					  .build();
					Request request = new Request.Builder()
					  .url("https://pos.laisla.com.uy/web/login")
					  .method("POST", body)
					  .addHeader("Cookie", "session_id=4564fd30c8c51b66bdff5088e56268f0d10d296a")
					  .build();
					try {
						Response response = client.newCall(request).execute();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
					
					client = new OkHttpClient().newBuilder()
							  .build();
							request = new Request.Builder()
							  .url(url)
							  .method("GET", null)
							  .addHeader("Cookie", "session_id=4564fd30c8c51b66bdff5088e56268f0d10d296a")
							  .build();
							try 
							{
								Response response = client.newCall(request).execute();
								InputStream in = response.body().byteStream();
								PropertiesHelperAPI pHa= new PropertiesHelperAPI("paths");
								pHa.loadProperties();
								String path = pHa.getValue("pdf");
								String filePath = path+"/r"+fileName+".pdf";
								
								
										  FileOutputStream fileOutputStream = new FileOutputStream(filePath); 
										    byte dataBuffer[] = new byte[1024];
										    int bytesRead;
										    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) 
										    {
										        fileOutputStream.write(dataBuffer, 0, bytesRead);
										    }
										
								factura = pHa.getValue("HTTP_pdf")+"/r"+fileName+".pdf";	
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		} catch (Exception e) {
			return "";
		}
		return factura;
				
	}
}
