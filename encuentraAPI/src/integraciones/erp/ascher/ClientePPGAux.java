package integraciones.erp.ascher;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;

import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.DataIDDescripcionList;
import beans.datatypes.StockDeposito;
import beans.encuentra.ArticuloRepoFromLoad;
import beans.encuentra.ArticuloReposicion;
import beans.encuentra.DepositoMayorista;
import integraciones.erp.odoo.laIsla.SincroLaIsla;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;
import logica.Util;

public class ClientePPGAux
{
 

 
 
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
		
		String msj = respC.getConssdtcabezalpreparacionpedidoencuentra().getRespuestaMsg();
		 
			 
		 
			 	
			 	
		return msj;
			 	
	}
 
 
 	public DataDeposOrdenes darOrdenes() 
 	{
 		
 				
 		Hashtable<Integer, OrdenVenta> ordens = new Hashtable<>();
 		Hashtable<Integer, List<SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon>> renglonesOrdenes = new Hashtable<Integer, List<SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon>>();
 		DataDeposOrdenes retorno = new DataDeposOrdenes();
 		List<ArticuloReposicion> articulos = new ArrayList<>();
 		
 		Hashtable<String, DepositoMayorista> depositos = new Hashtable<>();
 		List<SDTPedidoEncuentra> cabezales = new ArrayList<>();
 			 	
 		CargaListaPedidosEncuentra  serviceC = new CargaListaPedidosEncuentra();
 		CargaListaPedidosEncuentraSoapPort portC = serviceC.getCargaListaPedidosEncuentraSoapPort();
 		CargaListaPedidosEncuentraExecute careC = new CargaListaPedidosEncuentraExecute();
 		CargaListaPedidosEncuentraExecuteResponse respC =  portC.execute(careC);
 		 
 			 
 		CargaPedidoEncuentra  serviceCA = new CargaPedidoEncuentra();
 		CargaPedidoEncuentraSoapPort portCA = serviceCA.getCargaPedidoEncuentraSoapPort();
 		
 		
 		for (SDTPedidosEncuentraListaSDTPedidosEncuentraListaItem aConsulta : respC.getSdtpedidos().getSDTPedidosEncuentraListaSDTPedidosEncuentraListaItem()) 
 		{
 			
 			
			
			CargaPedidoEncuentraExecute careCA = new CargaPedidoEncuentraExecute();
			SDTPedidoEncuentra cab = new SDTPedidoEncuentra();
			 	
			
			cab.setNro(aConsulta.getNro());
			cab.setSerie(aConsulta.getSerie());
			cab.setTipoDeDocumento(aConsulta.getTipoDeDocumento());
			cab.setPrioridad(aConsulta.getPrioridad());
			
			cab.setRespuestaOk("");
			 	
			careCA.setSdtpedidocabezal(cab);
			 	
			//if(cab.getNro()==210224)
			if(true)
			 {
				 CargaPedidoEncuentraExecuteResponse respCA = portCA.execute(careCA);
					cabezales.add(cab);
					 
					
					 int idDepositoDestino = new HashCodeBuilder(1, 1).
				       append(respCA.getSdtpedidocabezal().getClienteNombre()).
				        toHashCode();
					if(idDepositoDestino<0)
					{
						idDepositoDestino=idDepositoDestino*-1;
					}
					
					String nombreDepoDestino = respCA.getSdtpedidocabezal().getClienteNombre();
					
					DepositoMayorista dp = new DepositoMayorista(idDepositoDestino+"", nombreDepoDestino.replaceAll(";", ""), respCA.getSdtpedidocabezal().getDireccion(), "100");
					dp.setDepartamento(respCA.getSdtpedidocabezal().getDepartamento());
					dp.setCiudad(respCA.getSdtpedidocabezal().getLocalidad());
					
					OrdenVenta ov = new OrdenVenta();
					/*
					 * " VALUES ("+this.getIdCarrito()+", "+this.getPorcDescuento()+", '"+this.getCliMail()+"', '"+this.getCliDireccion()+"', '"+this.getCliRuc()+"', '"+this.getCliCedula()+"', '"+this.getCliNombre()+"', 
					 * '"+this.getCliTelefono()+"', '"+urlEtiqueta+"', "+cantUnidades+", 0, "+this.getMl()+",'"+this.getFormaPago()+"',"+this.getImportePago()+",'"+this.getCliMail()+"', "+idEmpresa+")";*/
					
					
					ov.setIdCarrito(Long.parseLong(cab.getNro()+""));
					ov.setPorcDescuento(0.0);
					ov.setCliMail("");
					ov.setCliDireccion(dp.getDireccion());
					ov.setCliRuc(dp.getIdDeposito());
					ov.setCliCedula("");
					ov.setCliNombre(dp.getNombre());
					ov.setCliTelefono("");
					ov.setMl(0);
					ov.setFormaPago("");
					ov.setImportePago(0.0);
					ov.setSerie(cab.getSerie());
					ov.setTipodoc(cab.getTipoDeDocumento());
					ov.setDepartamento(respCA.getSdtpedidocabezal().getDepartamento());
					//ov.setAgregarLocalidad(cab.getLocalidad());
					ov.setCiudad(respCA.getSdtpedidocabezal().getLocalidad());
					ov.setCourrier(respCA.getSdtpedidocabezal().getEnvioDescr());
					
					
					
					depositos.put(idDepositoDestino+"",dp);
					List<OrdenVentaLinea> ordenVentaLineas = new ArrayList<>();
					 	
					 	
					 	System.out.println("Pedido "+respCA.getSdtpedidocabezal().getNro()); 
					 	System.out.println("Lineas ");
					 	
					 	String observacionVenta = cab.observaciones +"-" +cab.getObservacionesF();
					 	
					 	renglonesOrdenes.put(cab.getNro(), respCA.getSdtpedidoencuentrarenglones().getSDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon());
					 	for (SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon l : respCA.getSdtpedidoencuentrarenglones().getSDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon()) 
					 	
					 	{
					 		String descrVenta = l.getObservaciones() +"-"+ observacionVenta ;
					 		descrVenta = descrVenta.replace("null", "");
					 		Double d = l.getCantidad();
					 		int cantidad = d.intValue();
					 		ArticuloReposicion ar = new ArticuloReposicion(l.getArtId(), l.getArtId(), l.getArtId(), 0, Long.parseLong(respCA.getSdtpedidocabezal().getNro()+""), 0, cantidad, 0, 0, 0, 0, idDepositoDestino, 0, 0, 0, 3,descrVenta);
					 		ar.setNombreRegla(l.getArtDescripcion());
					 		ar.setSolicitud(respCA.getSdtpedidocabezal().getNro());
					 		ar.setIdLineaSAP(l.getId());

					 		System.out.println(aConsulta.prioridad);
					 		ar.setPrioridad(Boolean.parseBoolean(aConsulta.prioridad));
					 		

					

					 		
					 		System.out.println("");
					 		System.out.println("PRIORIDAD: "+aConsulta.getPrioridad());
					 		ar.setPrioridad(aConsulta.getPrioridad().equalsIgnoreCase("A"));
					

					 		OrdenVentaLinea ovl = new OrdenVentaLinea(0.0, (""+l.getCantidad()).replace(".0", ""), l.getArtId());
					 		ovl.setLinea(l.getId());
					 		ordenVentaLineas.add(ovl);
					 		
					 		
					 		articulos.add(ar);
					 		
						}
					 	
					 	ov.setOrdenVentaLineas(ordenVentaLineas);
					 	ov.save("", 5);
					 	
					 	ordens.put(cab.getNro(),ov);
					 	
					 
		 		}
		 		
		 		
		 		
		 		
		 		retorno.setRenglones(renglonesOrdenes);
		 		retorno.setArticulos(articulos);
		 		retorno.setDepositos(new ArrayList<>(depositos.values()));
		 		retorno.setCabezales(cabezales);	 
		 		retorno.setOrdenes(ordens);
			 }
			

 		return retorno;
 			 	
 	}


	public ClientePPGAux() {}
 	
	public List<DTO_Articulo> darArticulos() 
	{
		
		Util u = new Util();
		List<DTO_Articulo> retorno = new ArrayList<DTO_Articulo>();
		
		CargaArticulosPPyK serviceA = new CargaArticulosPPyK();
		
		CargaArticulosPPyKSoapPort portA = serviceA.getCargaArticulosPPyKSoapPort();
		
		CargaArticulosPPyKExecute careA = new CargaArticulosPPyKExecute();
		SDTArticulosWebxGrupo sdta;
	 	
	 	
	 	boolean hayMas = true;
	 	short pagina = 1;
	 	int cantidadporPagina = 300;
	 	
	 	PrintWriter writer = null;
		try {
			writer = new PrintWriter("C:/Program Files/apache-tomcat-7.0.64/webapps/encuentraAPI/pdf/filenamePrueba.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 	while (hayMas) 
		{
	 		sdta = new SDTArticulosWebxGrupo();
	 		sdta.setCantidad(cantidadporPagina);
	 		sdta.setParte(pagina);
	 		sdta.setConsumidor("Encuentra");
	 		sdta.setEmpresa("P");
	 		
	 		
	 		System.out.println("whiling pagina "+pagina);
	 		
	 		careA.setSdtarticuloswebxgrupo(sdta);
	 		CargaArticulosPPyKExecuteResponse  respA = portA.execute(careA);
	 		
	 		int largo = 0;
	 		
	 		
            
            
	 		for (SDTArticulosWebxGrupoArticulo a : respA.getSdtarticuloswebxgrupo().getArticulos().getArticulo()) 
		 	{
	 			
	 			try
	 			{	
	 				writer.println(a.getArtId()+"	"+a.getArtCodBar()+"	"+a.getArtDescripCatalogo());
	 				System.out.println(a.getArtId()+" - "+a.getArtDescripCatalogo());
	 				
	 				String idArticulo = a.getArtId();
	 				if(idArticulo.equals("E1113") || idArticulo.equals("E1635") || idArticulo.equals("E0591") || idArticulo.equals("HV0820") || idArticulo.equals("QF65DF"))
	 				{
	 					System.out.println("");
	 				}
	 				
	 				
	 				String descripcion = u.validarString(a.getArtDescripCatalogo());
	 				
	 				String talle = a.getTalle();
	 				String codProveedor = a.getArtCodPro();
	 				if(codProveedor==null || codProveedor.equals(""));
	 				{
	 					codProveedor="0";
	 				}
	 				int marca = a.getMarcaId();
	 				int familia =  a.getFamiliaId();
	 				int idCategoria =0;
	 				
	 				if(a.getSdtCategorias()!=null)
	 				{
	 					idCategoria = a.getSdtCategorias().getSdtCategoriasSdtCategoria().get(0).getCategoriaId();
	 				}
	 				
					DTO_Articulo ar = new DTO_Articulo(idArticulo, descripcion,talle ,"", "", "",codProveedor , marca+"",familia+"", idCategoria+"");

					//ar.setIdGenero((a.getSubFamiliaId()+""));
					ar.setIdTemporada("0");
					ar.setStock(a.getStockActual());
					ar.getCodigoBarras().add(u.validarString(a.getArtCodBar()));
					ar.setIdFamilia(a.getFamiliaId());
					ar.setIdSubfamilia(a.getSubFamiliaId());
					ar.setCantMinDeVenta(a.getArtCantUnidades());

					try
					{
						ar.setEmpaque(Integer.parseInt(a.getCantidadPorCaja()));
					}
					catch (Exception e) 
					{
						
					}
					
					
					try {
						for (SdtArtFotosSdtArtFoto foto : a.getSdtArtFotos().getSdtArtFotosSdtArtFoto()) 
						{
							System.out.println(foto.getFoto());
							ar.setImagen(foto.getFoto());
						}   
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
					
					retorno.add(ar);
	 				
	 			}
	 			catch (Exception e) 
	 			{
					e.printStackTrace();
				}
	 			
				largo++;
			}
	 		if(largo==0)
	 		{
	 			hayMas=false;
	 		}
	 		pagina ++;
	 		
	 		
		}
	 	writer.close();
	 	
		return retorno;
		
	}

	public List<DataIDDescripcion> darCategorias() 
	{
		List<DataIDDescripcion> retorno = new ArrayList<>();
		CargaZafras serviceZ = new CargaZafras();
		
	 	CargaZafrasSoapPort portZ = serviceZ.getCargaZafrasSoapPort();
		
	 	CargaZafrasExecute careZ = new CargaZafrasExecute();
	 	careZ.setSdtcategorias(new ArrayOfSdtCategoriasSdtCategoria());
	 	
	 	CargaZafrasExecuteResponse respZ =  portZ.execute(careZ);
	 	
	 	
	 	for (SdtCategoriasSdtCategoria m : respZ.getSdtcategorias().getSdtCategoriasSdtCategoria()) 
	 	{
			System.out.println(m.getCategoriaId()+" - "+ m.getCategoriaDescrip());
			retorno.add(new DataIDDescripcion(m.getCategoriaId(),m.getCategoriaDescrip()));
		}
	 	
	 	return retorno;
	}

	public List<DataIDDescripcionList> darFamilias() 
	{
		
		List<DataIDDescripcionList> retorno = new ArrayList<>();
		
		
		CargaFamilias serviceFam = new CargaFamilias();
		
	 	CargaFamiliasSoapPort portFam = serviceFam.getCargaFamiliasSoapPort();
		
	 	CargaFamiliasExecute carexF = new CargaFamiliasExecute();
	 	carexF.setSdtlineassubflias(new ArrayOfSdtLineasSubFliasSdtLineaSubFlias());
	 	
	 	CargaFamiliasExecuteResponse respF =  portFam.execute(carexF);
	 	
	 	
	 	for (SdtLineasSubFliasSdtLineaSubFlias m : respF.getSdtlineassubflias().getSdtLineasSubFliasSdtLineaSubFlias()) 
	 	{

	 		try {
	 			System.out.println(m.getLinId()+" - "+ m.getLinDsc());

				
				DataIDDescripcionList familia = new DataIDDescripcionList(m.getLinId(), m.getLinDsc());
				List<DataIDDescripcion> subF = new ArrayList<DataIDDescripcion>();
				
				if(m.getSdtSubFlias() != null) {
					for (SdtSubFliasSdtSubFlia mi : m.getSdtSubFlias().getSdtSubFliasSdtSubFlia()) 
					{
						System.out.println("\t "+mi.getSubFliaId()+" - "+ mi.getSubFliaDsc());
						subF.add(new DataIDDescripcion(mi.getSubFliaId(), mi.getSubFliaDsc()));
						
					}
				}
				familia.setLista(subF);
				retorno.add(familia);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	 	
	 	return retorno;
	
	}

	public List<DataIDDescripcion> darMarcas() 
	{
		CargaMarcas service = new CargaMarcas();
		
		List<DataIDDescripcion> retorno = new ArrayList<>();
		
	 	CargaMarcasSoapPort port = service.getCargaMarcasSoapPort();
		
	 	CargaMarcasExecute carex = new CargaMarcasExecute();
	 	carex.setSdtmarcas(new ArrayOfSdtMarcasSdtMarca());
	 	
	 	CargaMarcasExecuteResponse resp =  port.execute(carex);
	 	
	 	
	 	for (SdtMarcasSdtMarca m : resp.getSdtmarcas().getSdtMarcasSdtMarca()) 
	 	{
			System.out.println(m.getMarcaId()+" - "+ m.getMarcaDescrip());
			retorno.add(new DataIDDescripcion(m.getMarcaId(), m.getMarcaDescrip()));
		}
		return retorno;
	}
}
