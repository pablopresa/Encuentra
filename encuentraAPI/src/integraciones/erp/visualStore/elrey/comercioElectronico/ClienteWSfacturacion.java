package integraciones.erp.visualStore.elrey.comercioElectronico;


import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import javax.xml.ws.Response;

import com.google.gson.Gson;

import beans.Fecha;
import beans.datatypes.DataBooleanDesc;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.Cliente;
import beans.encuentra.Clientes;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import logica.Util;

public class ClienteWSfacturacion
{

	private short equipo = 50;
	private int empresa =  1;
	private short tienda = 1;
	private int deposito = 1200;
	private Long usuario =(long) 200;
	
	SWComercioElectronico service = new SWComercioElectronico();
	SWComercioElectronicoSoap port = service.getSWComercioElectronicoSoap();
	
	public DataIDDescripcion GrabarFactura(OrdenVenta orden, Cliente cliente){
		
		Holder<Integer> tr = new Holder<Integer>();
		Holder<String> msj = new Holder<String>();
		orden.setDepartamento(cliente.getDepartamento());
		orden.setCiudad(cliente.getCiudad());
		String xml = "<NewDataSet>" + ArmarXMLVenta(orden) + ArmarXMLCliente(cliente,orden.getImportePago()) + "</NewDataSet>";
		System.out.println("");
		System.out.println(xml);
		System.out.println("");
		
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		int nvoDoc = 0;
		try 
	    {
			  int intentos = 0;
			  boolean ok = false;
				  while (intentos < 3 && !ok) 
				  {						  
					  intentos++;
					  port.grabarVenta(msj, equipo, empresa, xml, tr);
					  if(tr.value!=null && tr.value!=0) {
						  ok = true;
					  }
				  }
			
						
			try {
				if(tr.value!=null){
					System.out.println(tr.value);
					nvoDoc = tr.value;
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
			
			
			return data;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return data;
		}
	}
	
	public double precioArticulo(String art) {
		double precio = 0.0;
		Holder<String> xml = new Holder<String>();
		Holder<String> msj = new Holder<String>();
		try {
			port.consultaArticulo(msj, art, empresa, 1, xml);
			System.out.println(xml.value);
			if(xml.value.contains("<Precio>")) {
				String precioStr = xml.value.split("<Precio>")[1].split("</Precio>")[0];
				precio = Double.parseDouble(precioStr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return precio;
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
	
	
	public String ArmarXMLCliente (Cliente c, Double Monto) {
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
		    	
		    	if(cliente.getcIOrigen().length()>8) {
		    		ci="    <Numero>"+cliente.getcIOrigen()+"</Numero>";
			    	di="	<NumeroDig />";
		    		ciOrigen="	 <Rut>"+cliente.getcIOrigen()+"</Rut>"+
			    			"    <CIOrigen></CIOrigen> ";
		    	}
		    	else {
		    		ci="    <Numero>"+cliente.getcIOrigen()+"</Numero>";
			    	di="	<NumeroDig />";
		    		ciOrigen="	 <Rut></Rut>"+
			    			"    <CIOrigen>"+cliente.getcIOrigen()+"</CIOrigen> ";
		    	}
		    	
		    	
		    }
		    
		    xml = 
					"  <PersonaCliente> "+
					//"    <IdCliente>0</IdCliente> "+
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
					"    <FechaNacimiento>19130101</FechaNacimiento> "+
					"    <Mail>"+cliente.getMail()+"</Mail> "+
					"    <Sexo>F</Sexo> "+
					"	 <NroVendedorEmp>200</NroVendedorEmp>"+
					//"	 <Rut></Rut>"+
					"	 <RazonSocial></RazonSocial>"+
					//"    <Sexo>"+cliente.getSexo()+"</Sexo> "+
					ciOrigen+
					"  </PersonaCliente> "+
					"<MedioPago>"+
						"<IdFormaPago>50</IdFormaPago>"+
						"<Monto>"+Monto+"</Monto>"+
					"</MedioPago>";
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	public String ArmarXMLVenta (OrdenVenta orden) {
		Util u = new Util();
		String xml = "";
		try {		    
	    	
	    	for (OrdenVentaLinea ovl : orden.getOrdenVentaLineas()) 
	    	{
	    		if(!ovl.getIdArticulo().startsWith("AAA"))
	    		{
	    			double precio = 0.0;
	    			
	    			try {
	    				//precio = this.precioArticulo(ovl.getIdArticulo());
	    				precio = ovl.getPrecioImp();
					} catch (Exception e) {
						e.printStackTrace();
					}
	    			
	    		    //ovl.setPrecioLista(precio*ovl.getCantidad());
	    			ovl.setPrecioLista(precio);
	    		    
	    		}
	    		
			}
	    	
		    Fecha fecha = new Fecha();
		    
			int idVendedor = 200;	    
		    
			xml = "" +
					
					"  <DatosEquipo> "+
					"    <IdEquipo>"+equipo+"</IdEquipo> "+
					"    <IdTienda>"+tienda+"</IdTienda> "+
					"    <IdUsuario>"+usuario+"</IdUsuario> "+
					"    <IdEmpresa>"+empresa+"</IdEmpresa> "+
					"    <IdDeposito>"+deposito+"</IdDeposito> "+
					"  </DatosEquipo> "+
					"  <Venta>" +
					//"    <Solicitada>false</Solicitada>" +
					//"    <NroCliente>"+idPerSEmpresa+"</NroCliente>" + 
					"    <PorcDescuento>0</PorcDescuento>" +
					"    <IdMoneda>1</IdMoneda>" +
					"    <Comentario> Pedido Num.:"+orden.getIdCarrito()+" FP: "+orden.getFormaPago()+" Importe pago:"+orden.getImportePago()+
																					" Etc.:"+orden.getComentario()+"</Comentario>" +
					"    <NroVendedor>"+idVendedor+"</NroVendedor>";
					//"    <CliDireccion>"+orden.getCliDireccion()+"</CliDireccion>" +
					if(orden.getCliRuc()!= null && !orden.getCliRuc().equals("0")) {
						xml += "    <CliRuc>"+orden.getCliRuc()+"</CliRuc>";
					}
					else {
						xml += "    <CliCedula>"+orden.getCliCedula()+"</CliCedula>";
					}
					
					xml +=
					"	 <RazonSocial>"+""+"</RazonSocial>"+
					
					"    <CliNombre>"+orden.getCliNombre()+"</CliNombre>" +
					"    <CliTelefono>"+orden.getCliTelefono()+"</CliTelefono>" +
					//"    <IdPais>1</IdPais>" +
					"    <IdCarrito>"+orden.getIdCarrito()+"</IdCarrito>" +
					//"    <GrabarContado>true</GrabarContado>" +
					//"    <IdMotivo>42</IdMotivo>" +
					//"    <IdDepEntrega>71</IdDepEntrega>" +
					//" 	 variable"+ 	
					"  </Venta>";
					
			
					for (OrdenVentaLinea l : orden.getOrdenVentaLineas()) 
					{						
						Double porcentaje =0.0;
						if(!l.getIdArticulo().startsWith("AAA") &&  l.getPrecioImp()>=0)
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
									"  <VentaLinea> "+
									"    <IdArticulo>"+l.getIdArticulo()+"</IdArticulo>" +
									"    <Cantidad>"+l.getCantidad()+"</Cantidad>" +
									//"    <PrecioImp>"+String.valueOf(l.getPrecioLista()/l.getCantidad())+"</PrecioImp>" +
									"    <PrecioImp>"+String.valueOf(l.getPrecioLista())+"</PrecioImp>" +
									"    <PorcDescuento>"+String.valueOf(porcentaje)+"</PorcDescuento>" +
									//"    <IdVendedor>"+idVendedor+"</IdVendedor>" +
									//"    <IdFormaPago>"+orden.getFormaPagoVisual()+"</IdFormaPago>" +
									"  </VentaLinea>";
						}
					
					}
										
					xml += 
					"  <VentaEntrega>" +
					"    <Direccion>"+orden.getCliDireccion()+"</Direccion>" +
					"    <Ciudad>"+orden.getCiudad()+"</Ciudad>" +
					"    <Departamento>"+orden.getDepartamento()+"</Departamento>" +
					"    <CodigoPostal />" +
					"    <Comentario>"+orden.getComentario()+"</Comentario>" +
					"    <IdTransporte>0</IdTransporte>" +
					"    <IdPais>1</IdPais>" +
					"    <Fecha>"+fecha.darFechaAnioMesDia()+"</Fecha>" +
					"    <IdCiudad>0</IdCiudad>" +
					"    <IdDepartamento>0</IdDepartamento>" +
					"    <CliNombre>"+orden.getCliNombre()+"</CliNombre>" +
					"    <CliTelefono>"+orden.getCliTelefono()+"</CliTelefono>" +
					"  </VentaEntrega>";
										
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return xml;
		
	}
}
