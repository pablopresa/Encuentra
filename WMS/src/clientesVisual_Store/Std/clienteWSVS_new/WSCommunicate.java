package clientesVisual_Store.Std.clienteWSVS_new;

import helper.PropertiesHelper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Response;

import logica.EnviaMail;
import logica.Logica;
import beans.Fecha;

import persistencia.MSSQL;

import dataTypes.DataBooleanDesc;
import dataTypes.DataIDDescripcion;

public class WSCommunicate {
	
	Logica Logica = new Logica();
	
	public boolean grabarCliente(Clientes cliente, int idEmpresa)
	{
		Logica lo = new Logica();
		if(lo.darIntegracionProductiva(2, idEmpresa))
		{
			try
			{
				SWStadium service = new SWStadium();
			    SWStadiumSoap port = service.getSWStadiumSoap();
			    
			    String ciOrigen="";
			    String ci="";
			    String di="";
			    
			    if(cliente.getMail().length() > 50){
			    	cliente.setMail("");
			    }
			    if(cliente.getcIOrigen().equals(""))
			    {
			    	ci="    <Numero>"+cliente.getNumero()+"</Numero> ";
			    	di="	<NumeroDig>"+cliente.getNumeroDig()+"</NumeroDig> ";
			    	ciOrigen="    <CIOrigen /> ";
			    }
			    else
			    {
			    	ci="    <Numero>"+cliente.getcIOrigen()+"</Numero>";
			    	di="	<NumeroDig />";
			    	ciOrigen="    <CIOrigen>"+cliente.getcIOrigen()+"</CIOrigen> ";
			    	
			    }
			    
			    String xml = "" +
						"<NewDataSet> "+
						"  <DatosEquipo> "+
						"    <IdEquipo>71</IdEquipo> "+
						"    <IdTienda>71</IdTienda> "+
						"    <IdUsuario>2</IdUsuario> "+
						"    <IdEmpresa>1</IdEmpresa> "+
						"    <IdDeposito>71</IdDeposito> "+
						"  </DatosEquipo> "+
						"  <PersonaCliente> "+
						"    <IdCliente>0</IdCliente> "+
						ci+
						di+
						"    <Nombre>"+cliente.getNombre()+"</Nombre> "+
						"    <Apellido>"+cliente.getApellido()+"</Apellido> "+
						"   <Empresa /> "+
						"    <Direccion>"+cliente.getDireccion()+"</Direccion> "+
						"    <Ciudad>"+cliente.getCiudad()+"</Ciudad> "+
						"    <Departamento>"+cliente.getDepartamento()+"</Departamento> "+
						"    <IdPais>1</IdPais> "+
						"    <CodigoPostal>"+cliente.getCodigoPostal()+"</CodigoPostal> "+
						"    <Telefono>"+cliente.getTelefono()+"</Telefono> "+
						"    <FechaNacimiento>1917-01-01T00:00:00-03:00</FechaNacimiento> "+
						"    <Mail>"+cliente.getMail()+"</Mail> "+
						"    <ClaveWeb /> "+
						"    <RecibirNoticiasWeb>false</RecibirNoticiasWeb> "+
						"    <RecibirOfertasWeb>false</RecibirOfertasWeb> "+
						"    <Sexo>"+cliente.getSexo()+"</Sexo> "+
						ciOrigen+
						"  </PersonaCliente> "+
						"</NewDataSet> ";
			    
			    
			    javax.xml.ws.Response <GrabarPersonaClienteXmlResponse> respuesta = port.grabarPersonaClienteXmlAsync("", xml);
			    
			    System.out.println(xml);
			    try 
			    {
			    	GrabarPersonaClienteXmlResponse gr = respuesta.get();
			    	
					System.out.println(gr.mensError);
					return true;
				} 
			    catch (InterruptedException e) 
			    {
					e.printStackTrace();
					return false;	
				}
			    catch (ExecutionException e) 
			    {
					e.printStackTrace();
					return false;
				}
			}
			catch (Exception eee) 
			{
				eee.printStackTrace();
				return false;
			}
		}
		else
		{
			return true;
		}
		
	}
	
	public boolean testGrabarCliente()
	{
		String xml = "" +
				"<NewDataSet> "+
				"  <DatosEquipo> "+
				"    <IdEquipo>71</IdEquipo> "+
				"    <IdTienda>71</IdTienda> "+
				"    <IdUsuario>2</IdUsuario> "+
				"    <IdEmpresa>1</IdEmpresa> "+
				"    <IdDeposito>71</IdDeposito> "+
				"  </DatosEquipo> "+
				"  <PersonaCliente> "+
				"    <IdCliente>0</IdCliente> "+
				"    <Numero>3818911</Numero> "+
		    	"	<NumeroDig>7</NumeroDig> "+
		    	"	<Ruc></Ruc> "+
				"    <Nombre>Adriana</Nombre> "+
				"    <Apellido>Rolan</Apellido> "+
				"   <Empresa /> "+
				"    <Direccion>direcc</Direccion> "+
				"    <Ciudad>Montevideo</Ciudad> "+
				"    <Departamento>Montevideo</Departamento> "+
				"    <IdPais>1</IdPais> "+
				"    <CodigoPostal>11000</CodigoPostal> "+
				"    <Telefono>24665556</Telefono> "+
				"    <FechaNacimiento>2017-11-03T00:00:00-03:00</FechaNacimiento> "+
				"    <Mail></Mail> "+
				"    <ClaveWeb /> "+
				"    <RecibirNoticiasWeb>false</RecibirNoticiasWeb> "+
				"    <RecibirOfertasWeb>false</RecibirOfertasWeb> "+
				"    <Sexo>0</Sexo> "+
				"    <CIOrigen /> "+
				"  </PersonaCliente> "+
				"</NewDataSet> ";

		SWStadium service = new SWStadium();
	    SWStadiumSoap port = service.getSWStadiumSoap();
	    javax.xml.ws.Response <GrabarPersonaClienteXmlResponse> respuesta = port.grabarPersonaClienteXmlAsync("", xml);
	    
	    System.out.println(xml);
	    try 
	    {
	    	GrabarPersonaClienteXmlResponse gr = respuesta.get();
	    	
			System.out.println(gr.mensError);
			return true;
		} 
	    catch (InterruptedException e) 
	    {
			e.printStackTrace();
			return false;	
		}
	    catch (ExecutionException e) 
	    {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public boolean testGrabarOV()
	{
		int idCliente = 4216880;
		
		int idPerSEmpresa = MSSQL.darListaIdDescripcion("select IdPersonaEmpresa,'' from PersonaEmpresa where Numero = "+idCliente).get(0).getId();
		
		
		String xml = "" +
				"<NewDataSet>" +
				"  <DatosEquipo> "+
				"    <IdEquipo>1</IdEquipo> "+
				"    <IdTienda>71</IdTienda> "+
				"    <IdUsuario>2</IdUsuario> "+
				"    <IdEmpresa>1</IdEmpresa> "+
				"    <IdDeposito>71</IdDeposito> "+
				"  </DatosEquipo> "+
				"  <OrdenVenta>" +
				"    <Solicitada>true</Solicitada>" +
				"    <IdCliente>"+idPerSEmpresa+"</IdCliente>" +
				"    <PorcDescuento>10</PorcDescuento>" +
				"    <IdMoneda>1</IdMoneda>" +
				"    <Comentario>Prueba de Orden</Comentario>" +
				"    <IdVendedor>20064299</IdVendedor>" +
				"    <CliDireccion>BR ARTIGAS 1825 LOC 19</CliDireccion>" +
				"    <CliRuc>214737710015</CliRuc>" +
				//"    <CliCedula>4216880</CliCedula>" +
				"    <CliNombre>Gonzalo Monzón</CliNombre>" +
				"    <CliTelefono>402 1920</CliTelefono>" +
				"    <IdPais>1</IdPais>" +
				"    <IdCarrito>1016</IdCarrito>" +
				"    <GrabarContado>true</GrabarContado>" +
				"    <IdMotivo>42</IdMotivo>" +
				"    <IdDepEntrega>71</IdDepEntrega>" +
				"  </OrdenVenta>" +
				"  <OrdenVentaLinea>" +
				"    <IdArticulo>146.LOTTY100038.0</IdArticulo>" +
				"    <Cantidad>1</Cantidad>" +
				"    <Precio>500</Precio>" +
				"    <PrecioImp>650</PrecioImp>" +
				"    <IdVendedor>20064299</IdVendedor>" +
				"    <IdFormaPago>20064299</IdFormaPago>" +
				"  </OrdenVentaLinea>" +
				"  <OrdenVentaEntrega>" +
				"    <Direccion>BR ARTIGAS 1825 LOC 19</Direccion>" +
				"    <Ciudad>MONTEVIDEO</Ciudad>" +
				"    <Departamento>MONTEVIDEO</Departamento>" +
				"    <CodigoPostal />" +
				"    <Comentario>es una prueba</Comentario>" +
				"    <IdTransporte>1</IdTransporte>" +
				"    <IdPais>1</IdPais>" +
				"    <Fecha>2017-11-05T15:38:05.5613589-03:00</Fecha>" +
				"    <IdCiudad>0</IdCiudad>" +
				"    <IdDepartamento>0</IdDepartamento>" +
				"    <CliNombre>CORPORACION OMNIBUS SA</CliNombre>" +
				"    <CliTelefono>402 1920</CliTelefono>" +
				"  </OrdenVentaEntrega>" +
				"</NewDataSet>";

		SWStadium service = new SWStadium();
	    SWStadiumSoap port = service.getSWStadiumSoap();
	    javax.xml.ws.Response <GrabarOrdenVentaXmlResponse> respuesta = port.grabarOrdenVentaXmlAsync("", xml);
	    
	    System.out.println(xml);
	    try 
	    {
	    	GrabarOrdenVentaXmlResponse gr = respuesta.get();
	    	
			System.out.println(gr.mensError);
			return true;
		} 
	    catch (InterruptedException e) 
	    {
			e.printStackTrace();
			return false;	
		}
	    catch (ExecutionException e) 
	    {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean vincular(int dep,int empresa,String articulo,String barra,boolean eliminar){
		SWStadium service = new SWStadium();
	    SWStadiumSoap port = service.getSWStadiumSoap();
	    javax.xml.ws.Response <GrabarArtBarraResponse> respuesta = port.grabarArtBarraAsync("", (short) dep,empresa,articulo,barra, eliminar);
	    
	    try 
	    {
	    	GrabarArtBarraResponse gr = respuesta.get();
			System.out.println(gr.getMensaje());
			return true;
		} 
	    catch (InterruptedException e) 
	    {
			e.printStackTrace();
			return false;	
		}
	    catch (ExecutionException e) 
	    {
			e.printStackTrace();
			return false;
		}
	}
	public boolean generarArticulo(){
		return true;
	}
	public DataBooleanDesc descontarDeOC(List<DataIDDescripcion>lista,int numeroDoc,long idProveedor,String COD,String serie,int remito){
		SWStadium service = new SWStadium();
		String xmlDetalle=this.ArmarXML(lista);
	    SWStadiumSoap port = service.getSWStadiumSoap();
	    DataBooleanDesc response=new DataBooleanDesc();
	    try 
	    {
	    	long numeroProveedor= MSSQL.darNumeroProveedor(idProveedor);
	    	if(COD.equals("IMP")){
	    		int numeroDocImp=MSSQL.darNumeroImportacion(idProveedor,numeroDoc);
	    		numeroDoc=numeroDocImp;
	    	}
	    	PropertiesHelper ph=new PropertiesHelper("configuracion");
			ph.loadProperties();
			int depositoRecepcion=Integer.parseInt(ph.getValue("depositoRecepcion"));
	    	int deposito=Integer.parseInt(ph.getValue("deposito"));
	    	javax.xml.ws.Response <GrabarRecepcionResponse> respuesta = port.grabarRecepcionAsync("",0,(short)1,(short)99,50000,COD,deposito,depositoRecepcion, getXMLGregorianCalendarNow().toString(),"","Prueba",numeroDoc,numeroProveedor,serie,remito,xmlDetalle);
	    	GrabarRecepcionResponse gr = respuesta.get();
	    	System.out.println(gr.getMensaje());
			if(gr.getGrabarRecepcionResult()==0){
				response.setBool(false);
				response.setDescricpion(gr.getMensaje());
			}
			else 
				response.setBool(true);
		} 
	    catch (InterruptedException e) 
	    {
			e.printStackTrace();
			response.setBool(false);
			response.setDescricpion("WS Error. Error de comunicación");
		
		}
	    catch (ExecutionException e) 
	    {
			e.printStackTrace();
			response.setBool(false);
			response.setDescricpion("WS Error. Error de comunicación");
		} 
	    catch (DatatypeConfigurationException e) {		
			e.printStackTrace();
			response.setBool(false);
			response.setDescricpion("WS Error. Error de comunicación");
		
	} catch (Exception e) {
			e.printStackTrace();
			response.setBool(false);
			response.setDescricpion("WS Error. Error de comunicación");
	}
	    return response;
	    
	}
	public boolean movimientoDeStock(int origen, int destino, int razon,List<DataIDDescripcion>lista, String coments)
	{
		SWStadium service = new SWStadium();
		SWStadiumSoap port = service.getSWStadiumSoap();
		String xmlDetalle=this.ArmarXML(lista);
		try 
	    {
			Integer tienda = origen;
			  short tienda_s = tienda.shortValue();
			  System.out.println(tienda_s);
			Response<GrabarTransferenciaResponse> respuesta = port.grabarTransferenciaAsync("", 1119, (short)4,(short)3,50000, 99, getXMLGregorianCalendarNow().toString(), "", coments, destino, razon, xmlDetalle);
	    
	    	GrabarTransferenciaResponse gr = respuesta.get();
			System.out.println(gr.getMensaje());
			return true;
		} 
	    catch (InterruptedException e) 
	    {
			e.printStackTrace();
			return false;	
		}
	    catch (ExecutionException e) 
	    {
			e.printStackTrace();
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	    
	}
	
	
	
	public boolean movimientoDeStockTienda(int origen, int destino, int razon,List<DataIDDescripcion>lista, String coments, int idEquipo)
	{
		SWStadium service = new SWStadium();
		SWStadiumSoap port = service.getSWStadiumSoap();
		String xmlDetalle=this.ArmarXML(lista);
		try 
	    {
			Short or = new Short((short)origen);
			Long usLong = Long.parseLong(""+1119);
			Long empresa = Long.parseLong("50000");
			Response<GrabarTransferenciaTiendaResponse> respuesta = port.grabarTransferenciaTiendaAsync("", usLong, (short)idEquipo,(short)99,empresa, origen, getXMLGregorianCalendarNow().toString(), "", coments, destino, razon, xmlDetalle);
	    
	    	GrabarTransferenciaTiendaResponse gr = respuesta.get();
			System.out.println(gr.getMensaje());
			return true;
		} 
	    catch (InterruptedException e) 
	    {
			e.printStackTrace();
			return false;	
		}
	    catch (ExecutionException e) 
	    {
			e.printStackTrace();
			return false;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	    
	}
	
	
	/*public boolean nuevoArticulo(){
		SWStadium service = new SWStadium();
	    SWStadiumSoap port = service.getSWStadiumSoap();
	  
	    javax.xml.ws.Response <GrabarArtBarraResponse> respuesta = port.grabarArticuloAsync(mensaje, idTienda, nroEmpresa, 
	    		codNadisa, codParaProv, coleccion, comentario, compraMinimo, descDespa, descIdioma1, descIdioma2, descIdioma3, 
	    		descripcion, diasEntrega, fecAlta, fecModif, idArticulo, idCategoria, idClase, idCostoImp, idEnvase, idEscala, 
	    		idGarantiaCli, idGarantiaProv, idGenero, idImpCom1, idImpCom2, idImpVta1, idImpVta2, idMarca, idPaisOrigen, idProvAlter, 
	    		idProveedor, idProvOri, idRubro, idSeccion, idTemporada, idTipoArticulo, idUniMed, importado, inactivo, 
	    		mantieneStock, porcGanancia, stockMaximo, stockMinimo, sujDescuento, unidadEmpaque, usuAlta, usuModif);
	    try{
	    	GrabarArtBarraResponse gr = respuesta.get();
			System.out.println(gr.getMensaje());
			return true;
		}   catch (InterruptedException e) 
	    {
			e.printStackTrace();
			return false;	
		}
	    catch (ExecutionException e) 
	    {
			e.printStackTrace();
			return false;
		}
		}
	*/
	public String ArmarXML(List<DataIDDescripcion>lista){
		String xml="<NewDataSet>";
		for(int i=0;i<lista.size();i++){
			  xml=xml+"<Detalle>";
			  xml=xml+"<IdArticulo>"+lista.get(i).getDescripcion()+"</IdArticulo>";
			  xml=xml+"<Cantidad>"+lista.get(i).getId()+"</Cantidad>";
			  xml=xml+"</Detalle>";
		}
		xml=xml+"</NewDataSet>";
		return xml;
	}
    public XMLGregorianCalendar getXMLGregorianCalendarNow() 
            throws DatatypeConfigurationException
    {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now = 
            datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }




    

    


    public Double darDescuento(OrdenVenta orden) 
    {
    	
    	Double precioTotalVS =0.0;
    	Double precioTotalOV =0.0;
    	
    	for (OrdenVentaLinea ovl : orden.getOrdenVentaLineas()) 
    	{
    		if(!ovl.getIdArticulo().equals("0002000"))
    		{
    			precioTotalOV+=ovl.getPrecioImp();
    			
    		    String precioStr = MSSQL.darListaIdDescripcion("select 0, Precio from ArticuloLP where IdListaPrecio = 1 and IdArticulo = '"+ovl.getIdArticulo()+"'").get(0).getDescripcion();
    		    
    		    precioTotalVS+=Double.parseDouble(precioStr);
    		}
    		
		}
    	
    	Double difPrecio = precioTotalVS-precioTotalOV;
    	
    	//1000 100    	
    	//10 X
 
    	Double porcentaje = (difPrecio*100)/precioTotalVS;
    	
    	if(porcentaje>0)
    	{
    		int porcEntero = porcentaje.intValue();
    		porcentaje=Double.parseDouble(String.valueOf(porcEntero));
    		System.out.println("precio diferente "+porcentaje);
    	}
    	
    	return porcentaje;
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

	public boolean grabarOV(OrdenVenta orden, int idEmpresa) 
	{
		SWStadium service = new SWStadium();
	    SWStadiumSoap port = service.getSWStadiumSoap();
	    
	    
	    int idPerSEmpresa = 2;
	    try
	    {
	    	idPerSEmpresa = MSSQL.darListaIdDescripcionWEB("select IdPersonaEmpresa,'' from PersonaEmpresa where Numero = "+orden.getCliCedula()).get(0).getId();
	    }
	    catch (Exception e)
	    {
	    	Logica.logPedido(orden.getIdCarrito(), 0, 0, "Grabando Orden de Venta sin cliente",0,idEmpresa);
	    	
	    	idPerSEmpresa = 2;
	    	EnviaMail em = new EnviaMail();
	    	String tabla = "";
	    	for (OrdenVentaLinea vl : orden.getOrdenVentaLineas()) 
	    	{
	    		tabla+="<!--Articulos--> "+ 	
				"                                                      <tr> "+
				"                                                         <td style='border: 1px solid silver;'>"+orden.getIdCarrito()+"</td> "+
				"                                                         <td style='border: 1px solid silver;'>"+vl.getIdArticulo()+" - "+vl.getCantidad()+"</td> "+
				"                                                      </tr> "+
				"                                                      <!--Fin Articulos-->";
				
			}
	    	
	    	/*********************************mai************************************/
				String boby = ""+
				"<p>&nbsp;</p> "+
				"<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
				"   <tbody> "+
				"      <tr> "+
				"         <td style='padding: 10px 0 30px 0;'> "+
				"            <table style='border: 1px solid #cccccc; border-collapse: collapse;' border='0' width='600' cellspacing='0' cellpadding='0' align='center'> "+
				"               <tbody> "+
				"                  <tr> "+
				"                     <td style='padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;' align='center' bgcolor='#70bbd9'> "+
				"                        <p>Ordenes grabadas sin cliente, la orden no se grabó porque el cliente no estaba en la base de Visual</p> "+
				"                     </td> "+
				"					  <td style='padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;' align='center'> "+
				"                        <p>Pedido: "+orden.getIdCarrito()+"</p> "+
				"                        <p>Cliente: "+orden.getCliNombre()+" "+orden.getCliCedula()+"</p> "+
				"                        <p>Comentario: "+orden.getComentario()+" Importe pago:"+orden.getImportePago()+"</p> "+
				"                        <p>Otros datos: "+orden.getCliDireccion()+" - "+orden.getCliTelefono()+" - "+orden.getFormaPago()+" Importe pago:"+orden.getImportePago()+"</p> "+
				"                     </td> "+
				"                  </tr> "+
				"                  <tr> "+
				"                     <td style='padding: 40px 30px 40px 30px;' bgcolor='#ffffff'> "+
				"                        <table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
				"                           <tbody> "+
				"                              <tr> "+
				"                                 <td style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'><strong>Hola a continuación un detalle de las ordenes que se grabaron sin cliente (cliente 0)</strong></td> "+
				"                              </tr> "+
				"                              <tr> "+
				"                                 <td style='padding: 20px 0px 30px; color: #153643; font-family: Arial,sans-serif; font-size: 16px; line-height: 20px; text-align: center;'> "+
				"                                    <p>&nbsp;</p> "+
				"                                    "+                                   
				"                                 </td> "+
				"                              </tr> "+
				"                              <tr> "+
				"                                 <td> "+
				"                                    <table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
				"                                       <tbody> "+
				"                                          <tr> "+
				"                                             <td valign='top' width='260'> "+
				"                                                <table style='text-align: center; border-collapse: collapse;' border='0' width='100%' cellspacing='0' cellpadding='0'> "+
				"                                                   <tbody> "+
				"                                                     <tr> "+
				"                                                         <td style='border: 1px solid silver;'>Pedido</td> "+
				"                                                         <td style='border: 1px solid silver;'>Articulo</td> "+
				"                                                      </tr> "
																			+tabla+ 	
				"                                                   </tbody> "+
				"                                                </table> "+
				"                                             </td> "+
				"                                          </tr> "+
				"                                       </tbody> "+
				"                                    </table> "+
				"                                 </td> "+
				"                              </tr> "+
				"                           </tbody> "+
				"                        </table> "+
				"                     </td> "+
				"                  </tr> "+
				"               </tbody> "+
				"            </table> "+
				"         </td> "+
				"      </tr> "+
				"      <tr> "+
				"         <td style='padding: 30px 30px 30px 30px;' bgcolor='#ee4c50'> "+
				"            <table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
				"               <tbody> "+
				"                  <tr> "+
				"                     <td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>Notificaci&oacute;n generada automaticamente por encuentra<br />No es necesario que responda a esta direcci&oacute;n de correo.</td> "+
				"                     <td align='right' width='25%'> "+
				"                        <table border='0' cellspacing='0' cellpadding='0'> "+
				"                           <tbody> "+
				"                              <tr> "+
				"                                 <td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
				"                                 <td style='font-size: 0; line-height: 0;' width='20'>&nbsp;</td> "+
				"                                 <td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
				"                              </tr> "+
				"                           </tbody> "+
				"                        </table> "+
				"                     </td> "+
				"                  </tr> "+
				"               </tbody> "+
				"            </table> "+
				"         </td> "+
				"      </tr> "+
				"   </tbody> "+
				"</table> "+
				"<!--analytics--> "+ 
				"<p>&nbsp;</p> ";
				
				
				List<String> mailDestinos = new ArrayList<>();
				mailDestinos.add("stadium71@stadium.local");
				mailDestinos.add("gmonzon@stadium.local");
				EnviaMail.enviarMailHTMLOD("NotificacionesEncuentra@stadium.local", mailDestinos, "Orden grabada sin cliente", boby,idEmpresa);

	    	/*********************************end mai************************************/
	    	
	    		
	    	
	    }
	    
	    
	    //Double precioTotalVS =0.0;
    	//Double precioTotalOV =0.0;
    	
    	for (OrdenVentaLinea ovl : orden.getOrdenVentaLineas()) 
    	{
    		if(!ovl.getIdArticulo().equals("0002000") && 
    				ovl.getEsCupon()!=1)
    		{
    			//precioTotalOV+=ovl.getPrecioImp();
    			String precioStr = "0";
    			
    			try {
    				precioStr = MSSQL.darListaIdDescripcion("select 0, Precio from ArticuloLP where IdListaPrecio = 1 and IdArticulo = '"+ovl.getIdArticulo()+"'").get(0).getDescripcion();
				} catch (Exception e) {
					e.printStackTrace();
				}
    			
    		    //String precioStr2=precioStr.substring(0, precioStr.length()-4);
    		    ovl.setPrecioLista(Double.parseDouble(precioStr)*ovl.getCantidad());
    		    
    		    //precioTotalVS+=Double.parseDouble(precioStr);
    		}
    		
		}
    	
    	
	    
	  
	    
	    
		
	    Fecha fecha = new Fecha();
	    
		int idVendedor = 0;
	    if(orden.getIdVendedor()==2000)//web
	    {
	    	idVendedor = 20064299;
	    }
	    if(orden.getIdVendedor()==2003)//mercado libre
	    {
	    	idVendedor = 99025986;
	    }
	    if(orden.getIdVendedor()==2051){//missCarol
	    	idVendedor = 99026074;
	    }
	    
	    
		String xml = "" +
				"<NewDataSet>" +
				"  <DatosEquipo> "+
				"    <IdEquipo>1</IdEquipo> "+
				"    <IdTienda>71</IdTienda> "+
				"    <IdUsuario>2</IdUsuario> "+
				"    <IdEmpresa>1</IdEmpresa> "+
				"    <IdDeposito>71</IdDeposito> "+
				"  </DatosEquipo> "+
				"  <OrdenVenta>" +
				"    <Solicitada>false</Solicitada>" +
				"    <IdCliente>"+idPerSEmpresa+"</IdCliente>" +
				"    <PorcDescuento>0</PorcDescuento>" +
				"    <IdMoneda>1</IdMoneda>" +
				"    <Comentario> Pedido Num.:"+orden.getIdCarrito()+" FP: "+orden.getFormaPago()+" Importe pago:"+orden.getImportePago()+" Etc.:"+orden.getComentario()+"</Comentario>" +
				"    <IdVendedor>"+idVendedor+"</IdVendedor>" +
				"    <CliDireccion>"+orden.getCliDireccion()+"</CliDireccion>" +
				"    <CliRuc>"+orden.getCliRuc()+"</CliRuc>" +
				"    <CliCedula>"+orden.getCliCedula()+"</CliCedula>" +
				"    <CliNombre>"+orden.getCliNombre()+"</CliNombre>" +
				"    <CliTelefono>"+orden.getCliTelefono()+"</CliTelefono>" +
				"    <IdPais>1</IdPais>" +
				"    <IdCarrito>"+orden.getIdCarrito()+"</IdCarrito>" +
				"    <GrabarContado>true</GrabarContado>" +
				"    <IdMotivo>42</IdMotivo>" +
				"    <IdDepEntrega>71</IdDepEntrega>" +
				" 	 variable"+ 	
				"  </OrdenVenta>";
				
		
				for (OrdenVentaLinea l : orden.getOrdenVentaLineas()) 
				{
					
					Double porcentaje =0.0;
					if(!l.getIdArticulo().equals("0002000") && !l.getIdArticulo().equals("000.07171000001.0") 
							&& l.getEsCupon()!=1 && l.getPrecioImp()>=0)
					{
						Double difPrecio = (l.getPrecioImp()*l.getCantidad())-(l.getPrecioLista()*l.getCantidad());
				    	porcentaje = (difPrecio*100)/(l.getPrecioLista()*-1*l.getCantidad());
				    	
				    	if(porcentaje>0)
				    	{				    		
				    		porcentaje=round(porcentaje, 2);
				    		System.out.println("precio diferente "+porcentaje);				    		
				    	}
				    	else{
				    		porcentaje = 0.0;
				    	}
					}
					else
					{
						l.setPrecioLista(l.getPrecioImp());
					}
					l.setDescuento(porcentaje);
					
					if(l.getEsCupon()!=1 && l.getPrecioImp()>=0){
						xml +=""+
								"  <OrdenVentaLinea> "+
								"    <IdArticulo>"+l.getIdArticulo()+"</IdArticulo>" +
								"    <Cantidad>"+l.getCantidad()+"</Cantidad>" +
								//"    <Precio>"+String.valueOf(l.getPrecio()).replace(".", ",")+"</Precio>" +
								"    <PrecioImp>"+String.valueOf(l.getPrecioLista()/l.getCantidad())+"</PrecioImp>" +
								"    <PorcDesc>"+String.valueOf(porcentaje)+"</PorcDesc>" +
								"    <IdVendedor>"+idVendedor+"</IdVendedor>" +
								"$$FACTURAAUTOMATICA$$" +
								"  </OrdenVentaLinea>";
					}
				
				}
				
				//CONTROL SI SE DEBE FACTURAR AUTOMATICAMENTE
				boolean facturaManual = false;
				for(OrdenVentaLinea l : orden.getOrdenVentaLineas()){
					if(l.getEsCupon()==1 || l.getPrecioImp()<0){
						facturaManual = true;
						break;
					}
					if(l.getIdArticulo().equals("000.07171000001.0")){
						facturaManual = true;
						break;
					}
					if(!l.getIdArticulo().equals("0002000")){
						if(l.getPrecioImp()<=0.0){
							facturaManual = true;
							break;
						}
						if(l.getIdArticulo().startsWith("001."))
						{
							if(l.getDescuento()>32.0 )
							{
								facturaManual = true;
								break;
							}
						}
						else if(l.getDescuento()>22.0){
							facturaManual = true;
							break;
						}
					}
					
				}
				if(orden.getImportePago()>=8000.0){
					facturaManual = true;
				}
								
				String idFormaPAgo = "" ;
				if(facturaManual){
					xml = xml.replace("$$FACTURAAUTOMATICA$$", "");					
				}
				else
				{
					if(!orden.getFormaPagoVisual().equals(""))
					{
						idFormaPAgo = "    <IdFormaPago>"+orden.getFormaPagoVisual()+"</IdFormaPago>";
					}	
					xml = xml.replace("$$FACTURAAUTOMATICA$$", idFormaPAgo);
				}
				
				/////////////////////////////////////////////////////////////////////
				
				xml += 
				"  <OrdenVentaEntrega>" +
				"    <Direccion>"+orden.getCliDireccion()+"</Direccion>" +
				"    <Ciudad></Ciudad>" +
				"    <Departamento></Departamento>" +
				"    <CodigoPostal />" +
				"    <Comentario>"+orden.getComentario()+"</Comentario>" +
				"    <IdTransporte>1</IdTransporte>" +
				"    <IdPais>1</IdPais>" +
				"    <Fecha>"+fecha.darFechaAnio_Mes_Dia()+"T12:00:00.5613589-03:00</Fecha>" +
				"    <IdCiudad>0</IdCiudad>" +
				"    <IdDepartamento>0</IdDepartamento>" +
				"    <CliNombre>"+orden.getCliNombre()+"</CliNombre>" +
				"    <CliTelefono>"+orden.getCliTelefono()+"</CliTelefono>" +
				"  </OrdenVentaEntrega>" +
				"</NewDataSet>";
	    
		Logica.logPedido(orden.getIdCarrito(), 0, 0, "Grabando Orden de Venta",0,idEmpresa);
		
		String xmlFecha=xml.replace("variable", "<FecEntrega>"+orden.getFecha()+"T12:00:00.5613589-03:00</FecEntrega>");
	    
		try{
		    javax.xml.ws.Response <GrabarOrdenVentaXmlResponse> respuesta = port.grabarOrdenVentaXmlAsync("", xmlFecha);
		    
		    System.out.println(xml);
		    try 
		    {
		    	GrabarOrdenVentaXmlResponse gr = respuesta.get();
		    	
				System.out.println(gr.mensError);
				return true;
			} 
		    catch (InterruptedException e) 
		    {
				e.printStackTrace();
				return false;	
			}
		    catch (ExecutionException e) 
		    {
				e.printStackTrace();
				return false;
			}
		}
		catch (Exception e) {
			
			xml=xml.replace("variable", "");
			javax.xml.ws.Response <GrabarOrdenVentaXmlResponse> respuesta = port.grabarOrdenVentaXmlAsync("", xml);
		    
		    System.out.println(xml);
		    try 
		    {
		    	GrabarOrdenVentaXmlResponse gr = respuesta.get();
		    	
				System.out.println(gr.mensError);
				return true;
			} 
		    catch (InterruptedException ex) 
		    {
				e.printStackTrace();
				return false;	
			}
		    catch (ExecutionException ex) 
		    {
				e.printStackTrace();
				return false;
			}
		}
		
	}
	
	
	
}
