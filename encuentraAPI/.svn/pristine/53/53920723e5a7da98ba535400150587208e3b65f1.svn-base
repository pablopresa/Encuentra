package integraciones.erp.ascher;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import beans.api.json.SendMail;
import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.DataIDDescripcionList;
import beans.datatypes.StockDeposito;
import beans.encuentra.ArticuloRepoFromLoad;
import beans.encuentra.ArticuloReposicion;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class ClientePPG
{
 public static void main(String[] args) 
 {
	 	ClientePPGAux cli = new ClientePPGAux();
	 	
	 	List<DataIDDescripcionList> listaFamiliasSubFamilias = cli.darFamilias();
	 	List<DataIDDescripcion> listaFamilias = new ArrayList<>();
	 	List<DataIDDescripcion> listaSubFamilias = new ArrayList<>();
	 	List<DataIDDescripcion> listaRelacionFamiliasSubFamilias = new ArrayList<>();
	 	
	 	for (DataIDDescripcionList ddl : listaFamiliasSubFamilias) 
	 	{
	 		listaFamilias.add(new DataIDDescripcion(ddl.getId(), ddl.getDescripcion()));
	 		for (DataIDDescripcion dd : ddl.getLista()) 
	 		{
				listaSubFamilias.add(dd);
				DataIDDescripcion RelacionFamiliasSubFamilias = new DataIDDescripcion(ddl.getId(), dd.getId());
				listaRelacionFamiliasSubFamilias.add(RelacionFamiliasSubFamilias);
			}
		}
	 	

	 	String[] nombresMetodos = {"categorias","marcas","generos","familias","subfamilias","productos"};
	 	//String[] nombresMetodos = {"ordenes"};
	 	
		
		Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
		LogicaAPI logica = new LogicaAPI();
		List<DataIDDescripcion> datos = null;
		List<DTO_Articulo> datosArt = null;
		
		String token = logica.darToken(5,5000);
		for (String tabla : nombresMetodos) 
		{
			switch (tabla) {
				case "categorias"://zafras
					//cwa.putMaestros(token, listaFamilias, "art_categoria");
					datos = cli.darCategorias();
					cwa.putMaestros(token, datos, "art_categoria");
					break;

				case "marcas":
					datos = cli.darMarcas();
					cwa.putMaestros(token, datos, "art_marca");
					break;

				/*
				case "temporadas"://zafras
					datos = cli.darCategorias();
					cwa.putMaestros(token, datos, "art_temporadas");
					break;
				
				case "generos"://subfamilias
					
					cwa.putMaestros(token, listaSubFamilias, "art_genero");
					cwa.putConexionSubfamilias(token, listaRelacionFamiliasSubFamilias, "art_categoria_subcategoria", "IdCategoria", "IdSubcategoria");
					break;
				*/	
					
				case "familias":
					cwa.putMaestros(token, listaFamilias, "art_familia");
					break;
					
				case "subfamilias":
					cwa.putMaestros(token, listaSubFamilias, "art_subfamilia");
					cwa.putConexionSubfamilias(token, listaRelacionFamiliasSubFamilias, "art_familia_subfamilia", "IdFamilia", "IdSubfamilia");
					break;
			
				case "productos":
					try {
						 
						datosArt = cli.darArticulos();
						cwa.putArticulos(token, datosArt);
						List<StockDeposito> stock = new ArrayList<>();
						for (DTO_Articulo a : datosArt) 
						{
							StockDeposito s = new StockDeposito("5",a.getIdArticulo(),a.getStock());
							stock.add(s);
						}
						
						cwa.putStk(token, stock);
						
					} catch (Exception e) {}
					break;
			
				case "ordenes":
					DataDeposOrdenes depositosOrdenes = cli.darOrdenes();
				 	int lineasEnArt = 0;
				 	int lineasEnReng = 0;
					
					
					for (SDTPedidoEncuentra d : depositosOrdenes.getCabezales()) 
				 	{
				 		
				 		if(d.getNro()==209659)
				 		{
				 			System.out.println("");
				 		}

				 		/*********ojo*********/

				 		List<DataIDDescripcion> lista = new ArrayList<>();
				 		List<SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon> listaR = depositosOrdenes.getRenglones().get(d.getNro());
				 		for (SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon a : listaR) 
				 		{
				 			lineasEnReng++;
				 			DataIDDescripcion did = new DataIDDescripcion();
				 			did.setId(a.getId());
				 			did.setIdB((int) Math.round(a.getCantidad()));
							lista.add(did);
						}
				 		
					}
				 	cwa.putDeposM(token, depositosOrdenes.getDepositos());
				 	
				 	
				 	
				 	Map<Integer, String> parametrosE = cwa.darParametros(token, "");
				 	int depositoCentral = Integer.parseInt(parametrosE.get(4));
				 	
				 	Map<Integer, ArticuloRepoFromLoad> pickingDestinos = new Hashtable<>();
				 	Map<Integer, Integer> depositos = new Hashtable<>();
				 	Map<String, DataIDDescripcion> articulosEnOrden = new Hashtable<>();
				 	for (ArticuloReposicion a : depositosOrdenes.getArticulos()) 
				 	{
				 		lineasEnArt++;
				 		depositos.put(a.getDestino(),a.getDestino());
				 		if(articulosEnOrden.get(a.getSolicitud()+"-"+a.getArticulo())==null)
				 		{
				 			DataIDDescripcion d = new  DataIDDescripcion();
						 	
					 		d.setDescripcion(a.getArticulo());
					 		d.setId(a.getVenta());
					 		d.setIdLong(a.getSeccion());
					 		d.setIdB(a.getSolicitud());
					 		d.setDescripcionB(a.getNombreRegla()+a.getDescripcionVenta());
					 		
					 		if(pickingDestinos.get(a.getSucursal())==null)
							{
								List<DataIDDescripcion> articulosCantidad = new ArrayList<>();
								articulosCantidad.add(d);
								ArticuloRepoFromLoad ar = new ArticuloRepoFromLoad(a.getSucursal(), true, 5, depositoCentral, 3, articulosCantidad);

								ar.setDescripcionVenta(a.getDescripcionVenta());

								ar.setPrioridad(a.isPrioridad());
								
								pickingDestinos.put(a.getSucursal(),ar);
							}
					 		else
					 		{
					 			ArticuloRepoFromLoad ar = pickingDestinos.get(a.getSucursal());
					 			ar.getArticulosCantidad().add(d);
								pickingDestinos.put(a.getSucursal(),ar);
					 		}
					 		
					 		articulosEnOrden.put(a.getSolicitud()+"-"+a.getArticulo(), d);
					 		
				 		}
				 		else
				 		{
				 			DataIDDescripcion d = articulosEnOrden.get(a.getSolicitud()+"-"+a.getArticulo());
				 			System.out.println(a.getArticulo() +" ya estaba");
				 			int cantAnt = d.getId();
					 		
					 		d.setId(cantAnt+a.getVenta());
					 		
					 		if(!d.getDescripcionB().equals(a.getNombreRegla()))
					 		{
					 			String descripcion = d.getDescripcionB()+ "("+cantAnt+")"+ " - "+ a.getNombreRegla() +"("+a.getVenta()+")";
					 			d.setDescripcionB(descripcion);
					 		}
				 			
					 		ArticuloRepoFromLoad ar = pickingDestinos.get(a.getSucursal());
				 			
					 		for (DataIDDescripcion ac : ar.getArticulosCantidad()) 
					 		{
					 			if(ac.getDescripcion().equals(d.getDescripcion()))
					 			{
					 				ac.setId(d.getId());
					 			}
					 				
							}
							
					 		pickingDestinos.put(a.getSucursal(),ar);
				 			
							articulosEnOrden.put(a.getSolicitud()+"-"+a.getArticulo(), d);
				 			
				 		}
				 		
					}
				 	
				 	if(lineasEnArt==lineasEnReng)
				 	{
				 		/*********ojo*********/
				 		//cli.confirmarOrden(d.getNro(), d.getSerie(), d.getTipoDeDocumento(), lista);
				 		//cli.accionOrden(d.getNro() , d.getSerie(), "RECIBIDO", (short)0, d.getTipoDeDocumento());
				 		System.out.println("coincides ");
				 	}
				 	else
				 	{
				 		System.out.println("no coincide");
				 		SendMail mail = darMailError();
				 		SendMail [] mails = {mail};
				 		LogicaAPI.PutMailSpooler(mails, 5);
				 	}
				 	
				 	List<ArticuloRepoFromLoad> lista2 = new ArrayList<>(pickingDestinos.values());
				 	List <DataIDDescripcion> respuesta = cwa.savePickingOrder(token,lista2);
				 	
				 	for (DataIDDescripcion r : respuesta) 
				 	{
				 		
				 		for (SDTPedidoEncuentra d : depositosOrdenes.getCabezales()) 
					 	{
					 	
				 			if(d.getNro()==r.getId())
				 			{
				 				if(r.getDescripcion().equals("OK"))
				 				{
				 					List<DataIDDescripcion> lista = new ArrayList<>();
							 		List<SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon> listaR = depositosOrdenes.getRenglones().get(d.getNro());
							 		for (SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon a : listaR) 
							 		{
							 			lineasEnReng++;
							 			DataIDDescripcion did = new DataIDDescripcion();
							 			did.setId(a.getId());
							 			did.setIdB((int) Math.round(a.getCantidad()));
										lista.add(did);
									}
							 		
							 		System.out.println("Confirmaria OK la orden "+d.getNro());
							 		cli.accionOrden(d.getNro() , d.getSerie(), "RECIBIDO", (short)0, d.getTipoDeDocumento());
							 		cli.confirmarOrden(d.getNro(), d.getSerie(), d.getTipoDeDocumento(), lista);
							 		
				 				}
				 				else
				 				{
				 					System.out.println("no entra el pedido "+d.getNro());
							 		SendMail mail = darMailError();
							 		mail.setId(d.getNro()+"");
							 		SendMail [] mails = {mail};
							 		LogicaAPI.PutMailSpooler(mails, 5);
				 				}
				 			}
				 		}
					}
				 	
				 	
				 	
				 	
				break;

			}
		}
 }

 
 

 	private static SendMail darMailError() 
 	{
 		return new SendMail("SINCPPG", "gmonzon@200.com.uy", "error en sincro PPG ", "", "gmonzon@200.com.uy",null);
 		
 	}




	public String accionOrden(int idPedido, String serie, String accion, short cantBultos,String tipoDoc) 
	{
			 	
		AccionesEncuentra  serviceC = new AccionesEncuentra();
		AccionesEncuentraSoapPort portC = serviceC.getAccionesEncuentraSoapPort();
		AccionesEncuentraExecute careC = new AccionesEncuentraExecute();
		SDTAccionEncuentra accE = new SDTAccionEncuentra();
		accE.setSerie(serie);
		accE.setNro(idPedido);
		accE.setAccion(accion);
		accE.setCantidadBultos(cantBultos);
		accE.setTipoDeDocumento(tipoDoc);
		
		careC.setConssdtaccionencuentra(accE);
		
		
		AccionesEncuentraExecuteResponse respC =  portC.execute(careC);
		
		String msj = respC.getConssdtaccionencuentra().getRespuestaMsg();
		System.out.println(idPedido+" mensaje "+msj);
			 	
		return msj;
			 	
	}
 

 	public String confirmarOrden(int idPedido, String serie, String tipoDoc, List<DataIDDescripcion> lista) 
	{
		
			 	
		CargaPreparacionPedidoEncuentra  serviceC = new CargaPreparacionPedidoEncuentra();
		CargaPreparacionPedidoEncuentraSoapPort portC = serviceC.getCargaPreparacionPedidoEncuentraSoapPort();
		CargaPreparacionPedidoEncuentraExecute careC = new CargaPreparacionPedidoEncuentraExecute();
		SDTCabezalPreparacionPedidoEncuentra accE = new SDTCabezalPreparacionPedidoEncuentra();
		accE.setSerie(serie);
		accE.setNro(idPedido);
		accE.setTipoDeDocumento(tipoDoc);
		
		ArrayOfSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra arreglo = new ArrayOfSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra();
		 
		 arreglo.getSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra();
		
		
		for (DataIDDescripcion di : lista) 
		{
			try
			{
				SDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra r = new SDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra();
				
				
				r.setId((short)di.getId());
				r.setCantidad(Double.parseDouble(di.getIdB()+""));
				
				arreglo.sdtRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra.add(r);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		
		careC.setConssdtcabezalpreparacionpedidoencuentra(accE);
		careC.setConssdtrenglonespreparacionpedidoencuentra(arreglo);
		
		CargaPreparacionPedidoEncuentraExecuteResponse respC =  portC.execute(careC);
		
		return respC.getConssdtcabezalpreparacionpedidoencuentra().getRespuestaMsg();
		 
	}
 
}
