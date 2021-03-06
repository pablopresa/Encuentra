package integraciones.erp.visualStore.elrey.central;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import beans.Fecha;
import beans.datatypes.DataBooleanDesc;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.Cliente;
import beans.encuentra.Clientes;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import logica.Util;

public class ClienteWSELREY 
{
	int idEquipo = 1;
	int idTienda = 1;
	int idUsuario = 200;
	int idEmpresa = 1;
	int idDeposito = 1;
	SWNadWeb service = new SWNadWeb();
	SWNadWebSoap port = service.getSWNadWebSoap();
	
	public void AltaCliente(Cliente c) {
		Holder<String> msj = new Holder<String>();
		Holder<String> result = new Holder<String>();
		String xml = "";
		try {
			Clientes cliente = c.transformar(c,4);
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
		    
		    xml = "" +
					"<NewDataSet> "+
					"  <DatosEquipo> "+
					"    <IdEquipo>"+idEquipo+"</IdEquipo> "+
					"    <IdTienda>"+idTienda+"</IdTienda> "+
					"    <IdUsuario>"+idUsuario+"</IdUsuario> "+
					"    <IdEmpresa>"+idEmpresa+"</IdEmpresa> "+
					"    <IdDeposito>"+idDeposito+"</IdDeposito> "+
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
		    
		    port.grabarPersonaClienteXml(msj, xml, result);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void GrabarOrden(OrdenVenta orden) {
		Util u = new Util();
		Holder<String> msj = new Holder<String>();
		Holder<Integer> result = new Holder<Integer>();
		String xml = "";
		try {
			int idPerSEmpresa = 2;
		    try
		    {
		    	//idPerSEmpresa = MSSQL.darListaIdDescripcionWEB("select IdPersonaEmpresa,'' from PersonaEmpresa where Numero = "+orden.getCliCedula()).get(0).getId();
		    }
		    catch (Exception e)
		    {
		    	idPerSEmpresa = 2;		    	
		    }		    
	    	
	    	for (OrdenVentaLinea ovl : orden.getOrdenVentaLineas()) 
	    	{
	    		if(!ovl.getIdArticulo().equals("0002000"))
	    		{
	    			String precioStr = "0";
	    			
	    			try {
	    				//precioStr = MSSQL.darListaIdDescripcion("select 0, Precio from ArticuloLP where IdListaPrecio = 1 and IdArticulo = '"+ovl.getIdArticulo()+"'").get(0).getDescripcion();
					} catch (Exception e) {
						e.printStackTrace();
					}
	    			
	    		    ovl.setPrecioLista(Double.parseDouble(precioStr)*ovl.getCantidad());
	    		    
	    		}
	    		
			}
	    	
		    Fecha fecha = new Fecha();
		    
			int idVendedor = 0;
		    if(orden.getIdVendedor()==2000)//shopify
		    {
		    	idVendedor = 20064299;
		    }
		    if(orden.getIdVendedor()==2003)//mercado libre
		    {
		    	idVendedor = 99025986;
		    }		    
		    
			xml = "" +
					"<NewDataSet>" +
					"  <DatosEquipo> "+
					"    <IdEquipo>"+idEquipo+"</IdEquipo> "+
					"    <IdTienda>"+idTienda+"</IdTienda> "+
					"    <IdUsuario>"+idUsuario+"</IdUsuario> "+
					"    <IdEmpresa>"+idEmpresa+"</IdEmpresa> "+
					"    <IdDeposito>"+idDeposito+"</IdDeposito> "+
					"  </DatosEquipo> "+
					"  <OrdenVenta>" +
					"    <Solicitada>false</Solicitada>" +
					"    <IdCliente>"+idPerSEmpresa+"</IdCliente>" +
					"    <PorcDescuento>0</PorcDescuento>" +
					"    <IdMoneda>1</IdMoneda>" +
					"    <Comentario> Pedido Num.:"+orden.getIdCarrito()+" FP: "+orden.getFormaPago()+" Importe pago:"+orden.getImportePago()+
																					" Etc.:"+orden.getComentario()+"</Comentario>" +
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
						if(!l.getIdArticulo().equals("0002000") &&  l.getPrecioImp()>=0)
						{
							Double difPrecio = (l.getPrecioImp()*l.getCantidad())-(l.getPrecioLista()*l.getCantidad());
					    	porcentaje = (difPrecio*100)/(l.getPrecioLista()*-1*l.getCantidad());
					    	
					    	if(porcentaje>0)
					    	{				    		
					    		porcentaje=u.round(porcentaje, 2);
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
						
						if(l.getPrecioImp()>=0){
							xml +=""+
									"  <OrdenVentaLinea> "+
									"    <IdArticulo>"+l.getIdArticulo()+"</IdArticulo>" +
									"    <Cantidad>"+l.getCantidad()+"</Cantidad>" +
									"    <PrecioImp>"+String.valueOf(l.getPrecioLista()/l.getCantidad())+"</PrecioImp>" +
									"    <PorcDesc>"+String.valueOf(porcentaje)+"</PorcDesc>" +
									"    <IdVendedor>"+idVendedor+"</IdVendedor>" +
									//"    <IdFormaPago>"+orden.getFormaPagoVisual()+"</IdFormaPago>" +
									"  </OrdenVentaLinea>";
						}
					
					}
										
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
		    
					port.grabarOrdenVentaXml(msj, xml, result);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void GrabarFactura() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataIDDescripcion GrabarTransferencia(int origen, int destino, int motivo,List<DataIDDescripcion>lista, String coments){
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		String xmlCabezal = this.ArmarXMLCabezal(origen+"", destino+"", motivo+"", coments, true);
		Holder<String> msj = new Holder<String>();
		Holder<Integer> result = new Holder<Integer>();
		String xml = "<NewDataSet>"+xmlCabezal+xmlDetalle+"</NewDataSet>";
		
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		int nvoDoc = 0;
		try 
	    {
			DataBooleanDesc comprobacion = new DataBooleanDesc();comprobacion.setBool(true);//ComprobacionStock(lista,origen);
			if(comprobacion.isBool()){
				Integer tiend = origen;
				  short tienda_s = tiend.shortValue();
				  System.out.println(tienda_s);
				  int intentos = 0;
				  boolean ok = false;
					  while (intentos < 3 && !ok) 
					  {						  
						  String fecha = getXMLGregorianCalendarNow().toString();
					  intentos++;
					  
					  port.grabarTransferenciaTiendaXml(msj, xml, result);
					  if(msj.value!=null && result.value!=0) {
						  ok = true;
					  }
				  }
				
							
				try {
					if(result.value!=null){
						System.out.println(result.value);
						nvoDoc = result.value;
						data.setId(nvoDoc);
					}
					if(msj.value!=null){
						System.out.println(msj.value);
						String menError = msj.value;
						menError = menError.substring(1,menError.length());
						data.setDescripcion(menError);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.setIdB(intentos);
			}
			else{
				data.setDescripcion(comprobacion.getDescricpion());
				data.setId(0);
				data.setIdB(1);
			}
			
			return data;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return data;
		}
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
	
	public String ArmarXMLTipoAfecta(List<DataIDDescripcion>lista){
		String xml="<NewDataSet>";
		for(int i=0;i<lista.size();i++)
		{
			  xml=xml+"<Detalle>";
			  xml=xml+"<OCC ? Orden de Compra>"+lista.get(i).getDescripcion()+"</OCC ? Orden de Compra>";
			  xml=xml+"<IMP - Importaci?n>"+lista.get(i).getDescripcionB()+"</IMP - Importaci?n>";
			  xml=xml+"</Detalle>";
		}
		xml=xml+"</NewDataSet>";
		return xml;
	}
	
	public String ArmarXMLCabezal(String origen, String destino, String motivo, String coment, boolean confirm){
		
		  String xml="<TransfCab>";
		  xml+="<IdEquipo>"+this.idEquipo+"</IdEquipo>";
		  xml+="<IdUsuario>"+this.idUsuario+"</IdUsuario>";
		  xml+="<IdEmpresa>"+this.idEmpresa+"</IdEmpresa>";
		  xml+="<IdDepOrigen>"+origen+"</IdDepOrigen>";
		  if(destino!=null) {
			  xml+="<IdDepDestino>"+destino+"</IdDepDestino>";
		  }		
		  if(motivo!=null) {
			  xml+="<IdMotivo>"+motivo+"</IdMotivo>";
		  }
		  if(coment!= null) {
			  xml+="<Comentario>"+coment+"</Comentario>";
		  }
		  if(confirm) {
			  xml+="<Confirmada>"+confirm+"</Confirmada>";
		  }
		  		  
		  xml+="</TransfCab>";
		
		return xml;
	}
	
	public String ArmarXMLDetalle(List<DataIDDescripcion>lista){
		//String xml="<NewDataSet>";
		String xml="";
		for(int i=0;i<lista.size();i++){
			  xml=xml+"<TransfDet>";
			  xml=xml+"<IdArticulo>"+lista.get(i).getDescripcion()+"</IdArticulo>";
			  xml=xml+"<Cantidad>"+lista.get(i).getId()+"</Cantidad>";
			  xml=xml+"</TransfDet>";
		}
		//xml=xml+"</NewDataSet>";
		return xml;
	}
	
	public String ArmarXMLDetalleEntrega(List<DataIDDescripcion>lista){
		String xml="<NewDataSet>";
		for(int i=0;i<lista.size();i++){
			  xml=xml+"<Detalle>";
			  xml=xml+"<IdArticulo>"+lista.get(i).getDescripcion()+"</IdArticulo>";
			  xml=xml+"<IdDepEntrega>"+lista.get(i).getIdB()+"</IdDepEntrega>";
			  xml=xml+"<Cantidad>"+lista.get(i).getId()+"</Cantidad>";
			  xml=xml+"</Detalle>";
		}
		xml=xml+"</NewDataSet>";
		return xml;
	}
	
	

	public DataBooleanDesc ComprobacionStock(List<DataIDDescripcion>lista, int origen){
		DataBooleanDesc data = new DataBooleanDesc();
		boolean comprobacion = true;
		String msj = "";
		try {
			/*Hashtable<String, Integer> stocks = MSSQL.darStocks(lista, origen);
			
			for(DataIDDescripcion l:lista){
				if(stocks.get(l.getDescripcion())!=null){
					if(stocks.get(l.getDescripcion())<l.getId()){
						msj += l.getDescripcion()+",";
						comprobacion = false;						
					}
				}
				else{
					msj += l.getDescripcion()+",";
					comprobacion = false;					
				}
			}
			*/
			if(!msj.equals("")){
				msj = msj.substring(0,msj.length()-1);
				msj = "No hay stock suficiente de los siguientes articulos: "+msj;
			}
			data.setBool(comprobacion);
			data.setDescricpion(msj);
			
		} catch (Exception e) {
			data.setBool(false);
			data.setDescricpion("No se pudo comprobar el stock");
		}
		
		return data;
		
	}
}
