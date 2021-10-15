package integraciones.erp.visualStore.objetos;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import logica.LogicaAPI;

@XmlRootElement(name = "OrdenVentaLinea")
public class OrdenVenta 
{
	private boolean solicitada,grabarContado; 
	private int idCliente,idMoneda,idVendedor,idPais,idMotivo,idDepEntrega,ml,printer; 
	private Double porcDescuento, importePago ;
	private String cliCedula,cliNombre,cliDireccion,cliRuc,cliTelefono,comentario, formaPago,cliMail,fecha, ciudad, departamento,serie,tipodoc, courrier;
	private List<OrdenVentaLinea> ordenVentaLineas;
	private Long idCarrito; 
	
	
	
	public String getCourrier() {
		if(this.courrier == null) {
			return "";
		}
		else {
			return courrier;
		}
		
	}

	public void setCourrier(String courrier) {
		this.courrier = courrier;
	}
	
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCliMail() {
		return cliMail;
	}

	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}

	@Override
	public String toString() {
		return "OrdenVenta [idCliente=" + idCliente + ", idMoneda=" + idMoneda
				+ ", idVendedor=" + idVendedor + ", idPais=" + idPais
				+ ", idCarrito=" + idCarrito + ", idMotivo=" + idMotivo
				+ ", idDepEntrega=" + idDepEntrega + ", ml=" + ml
				+ ", porcDescuento=" + porcDescuento + ", cliCedula="
				+ cliCedula + ", cliNombre=" + cliNombre + ", cliDireccion="
				+ cliDireccion + ", cliRuc=" + cliRuc + ", cliTelefono="
				+ cliTelefono + ", comentario=" + comentario + ", formaPago="
				+ formaPago + ", ordenVentaLineas=" + ordenVentaLineas.toString() + "]";
	}

	public static String darXML(OrdenVenta toGive) 
	{
	    String xmlString = "";
	    try 
	    {
	        JAXBContext context = JAXBContext.newInstance(OrdenVenta.class);
	        Marshaller m = context.createMarshaller();

	        
	        
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

	        StringWriter sw = new StringWriter();
	        m.marshal(toGive, sw);
	        xmlString = sw.toString();

	    } 
	    catch (JAXBException e) 
	    {
	        e.printStackTrace();
	    }

	    return xmlString;
	}
	
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public OrdenVenta() {
	}












	public OrdenVenta(int idCliente,Double porcDescuento,String idmoneda,String cedulaCli, String nombreCli, String direccion,String rut,
			String telefono,List<OrdenVentaLinea> ordenVentaLineas,Long idPedido, int idVendedor, String formaPago, int ml, double importePado)
	{
		this.solicitada=true;
		this.grabarContado=true;
		this.idCliente=idCliente;
		if(idmoneda.equals("$"))
		{
			this.idMoneda=1;
		}
		else
		{
			this.idMoneda=2;
		}
		
		this.idVendedor=idVendedor;
		this.idPais=1;
		this.idCarrito=idPedido;
		this.idMotivo=30;
		this.idDepEntrega=71;
		this.porcDescuento=porcDescuento;
		this.cliCedula=cedulaCli;
		this.cliNombre=nombreCli.replace("'", "");
		this.cliDireccion=direccion.replace("'", "");
		this.cliRuc=rut;
		this.cliTelefono=telefono;
		this.comentario="";
		this.ordenVentaLineas=ordenVentaLineas;
		this.formaPago = formaPago;
		this.ml=ml;
		this.importePago = importePado;
	}
	
	
	
	
	public int getMl() {
		return ml;
	}

	public void setMl(int ml) {
		this.ml = ml;
	}

	public boolean isSolicitada() {
		return solicitada;
	}
	public void setSolicitada(boolean solicitada) {
		this.solicitada = solicitada;
	}
	public boolean isGrabarContado() {
		return grabarContado;
	}
	public void setGrabarContado(boolean grabarContado) {
		this.grabarContado = grabarContado;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	public int getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public Long getIdCarrito() {
		return idCarrito;
	}
	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}
	public int getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}
	public int getIdDepEntrega() {
		return idDepEntrega;
	}
	public void setIdDepEntrega(int idDepEntrega) {
		this.idDepEntrega = idDepEntrega;
	}
	public Double getPorcDescuento() {
		return porcDescuento;
	}
	public void setPorcDescuento(Double porcDescuento) {
		this.porcDescuento = porcDescuento;
	}
	public String getCliCedula() {
		return cliCedula;
	}
	public void setCliCedula(String cliCedula) {
		this.cliCedula = cliCedula;
	}
	public String getCliNombre() {
		return cliNombre;
	}
	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}
	public String getCliDireccion() {
		return cliDireccion;
	}
	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}
	public String getCliRuc() {
		return cliRuc;
	}
	public void setCliRuc(String cliRuc) {
		this.cliRuc = cliRuc;
	}
	public String getCliTelefono() {
		return cliTelefono;
	}
	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public List<OrdenVentaLinea> getOrdenVentaLineas() {
		return ordenVentaLineas;
	}
	public void setOrdenVentaLineas(List<OrdenVentaLinea> ordenVentaLineas) {
		this.ordenVentaLineas = ordenVentaLineas;
	}
	
	public Double getImportePago() {
		return importePago;
	}

	public void setImportePago(Double importePago) {
		this.importePago = importePago;
	}
	
	public boolean save(String urlEtiqueta, int idEmpresa)
	{
		List<String> insertsVtaLineas = new ArrayList<>();
		int cantUnidades = 0;
		if(this.getOrdenVentaLineas()!=null) {
			for (OrdenVentaLinea ovl : this.getOrdenVentaLineas()) 
			{
				String descripcion = "";
				int cantidadRegalo = 0;
				
				try
				{
					cantidadRegalo = ovl.getCantidadRegalo();
					if(ovl.getCantidadRegalo()>0)
					{
						System.out.println("");
					}
				}
				catch (Exception e) 
				{
					
				}
				
				
				double descuento = 0.0;
				try
				{
					descripcion = ovl.getDescripcion();
				}
				catch (Exception e) {}
				
				try
				{
					descuento = ovl.getDescuento();
				}
				catch (Exception e) {}
				
				cantUnidades+=ovl.getCantidad();
				insertsVtaLineas.add("INSERT INTO `ecommerce_import_ventalinea` (`idVenta`, `idArticulo`, `cantidad`, `precioImp`,`idEmpresa`, `idLinea`, `Descripcion`, `Descuento`, `CantRegalo`) "
						+ "VALUES ("+this.getIdCarrito()+", '"+ovl.getIdArticulo()+"', '"+ovl.getCantidad()+"', '"+ovl.getPrecioImp()+"', "+idEmpresa+", "+ovl.getLinea()+",'"+descripcion+"',"+descuento+","+cantidadRegalo+") "
								+ "ON DUPLICATE KEY UPDATE `cantidad`="+ovl.getCantidad()+"; ");
			}
		}
		
		
		
		
		boolean retorno = false;
		try 
		{
			LogicaAPI logica = new LogicaAPI();
			
			
			String serie = "";
			String tipoDoc = "";
			
			try
			{
				serie = this.getSerie();
				tipoDoc = this.getTipodoc();
			}
			catch (Exception e) {
				
			}
			
			String query = "INSERT INTO `ecommerce_import_venta` (`idVenta`, `porcDescuento`, `Comentario`, `clidireccion`, `cliRuc`, `clicedula`, "
					+ "`clinombre`, `clitelefono`, `uletiqueta`, `totalunidades`, `Sincronizada`, `ML`, `FP` ,`importePago`, `mail`,`idEmpresa`,`msjERP`,`Serie`,`TipoDoc`, agencia) " +
					" VALUES ("+this.getIdCarrito()+", "+this.getPorcDescuento()+", '"+this.getCliMail()+"', '"+this.getCliDireccion()+"', "
							+ "'"+this.getCliRuc()+"', '"+this.getCliCedula()+"', '"+this.getCliNombre()+"', '"+this.getCliTelefono()+"', "
									+ "'"+urlEtiqueta+"', "+cantUnidades+", -1, "+this.getMl()+",'"+this.getFormaPago()+"',"+this.getImportePago()+","
											+ "'"+this.getCliMail()+"', "+idEmpresa+",'','"+serie+"','"+tipoDoc+"','"+this.getCourrier()+"') ";
			
			
			boolean hayUpdate = false;
			
			String onDup = " on duplicate key update";
			
			
			if(this.getPorcDescuento()!=null && !this.getPorcDescuento().equals(""))
			{
				hayUpdate=true;
				onDup += " porcDescuento = '"+this.getPorcDescuento()+"' ";
			}
			if(this.getComentario()!=null && !this.getComentario().equals(""))
			{
				hayUpdate=true;
				onDup += ", Comentario = '"+this.getComentario()+"' ";
			}
			if(this.getCliDireccion()!=null && !this.getCliDireccion().equals(""))
			{
				hayUpdate=true;
				onDup += ", clidireccion = '"+this.getCliDireccion()+"' ";
			}
			if(this.getCliRuc()!=null && !this.getCliRuc().equals(""))
			{
				hayUpdate=true;
				onDup += ", cliRuc = '"+this.getCliRuc()+"' ";
			}
			if(this.getCliCedula()!=null && !this.getCliCedula().equals(""))
			{
				hayUpdate=true;
				onDup += ", clicedula = '"+this.getCliCedula()+"' ";
			}
			if(this.getCliNombre()!=null && !this.getCliNombre().equals(""))
			{
				hayUpdate=true;
				onDup += ", clinombre = '"+this.getCliNombre()+"' ";
			}
			if(this.getCliTelefono()!=null && !this.getCliTelefono().equals(""))
			{
				hayUpdate=true;
				onDup += ", clitelefono = '"+this.getCliTelefono()+"' ";
			}
			if(urlEtiqueta!=null && !urlEtiqueta.equals(""))
			{
				hayUpdate=true;
				onDup += ", uletiqueta = '"+urlEtiqueta+"' ";
			}
			if(this.getFormaPago()!=null && !this.getFormaPago().equals(""))
			{
				hayUpdate=true;
				onDup += ", FP = '"+this.getFormaPago()+"' ";
			}
			
			if(this.getImportePago()!=null)
			{
				hayUpdate=true;
				onDup += ", importePago = "+this.getImportePago()+" ";
			}
			
			if(this.getCliMail()!=null && !this.getCliMail().equals(""))
			{
				hayUpdate=true;
				onDup += ", mail = '"+this.getCliMail()+"' ";
			}
		
					
			
			
			if(hayUpdate)
			{
				logica.persistir(query + onDup);
			}
			else
			{
				logica.persistir(query);
			}
			
			for (String q : insertsVtaLineas) 
			{
				logica.persistir(q);
			}
			
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return retorno;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public boolean update(int idEmpresa)
	{
		List<String> insertsVtaLineas = new ArrayList<>();
		int cantUnidades = 0;
		if(this.getOrdenVentaLineas()!=null) {
			for (OrdenVentaLinea ovl : this.getOrdenVentaLineas()) 
			{
				String descripcion = "";
				int cantidadRegalo = 0;
				
				try
				{
					cantidadRegalo = ovl.getCantidadRegalo();
					if(ovl.getCantidadRegalo()>0)
					{
						System.out.println("");
					}
				}
				catch (Exception e) 
				{
					
				}
				
				
				double descuento = 0.0;
				try
				{
					descripcion = ovl.getDescripcion();
				}
				catch (Exception e) {}
				
				try
				{
					descuento = ovl.getDescuento();
				}
				catch (Exception e) {}
				
				cantUnidades+=ovl.getCantidad();
				insertsVtaLineas.add("INSERT INTO `ecommerce_import_ventalinea` (`idVenta`, `idArticulo`, `cantidad`, `precioImp`,`idEmpresa`, `idLinea`, `Descripcion`, `Descuento`, `CantRegalo`) "
						+ "VALUES ("+this.getIdCarrito()+", '"+ovl.getIdArticulo()+"', '"+ovl.getCantidad()+"', '"+ovl.getPrecioImp()+"', "+idEmpresa+", "+ovl.getLinea()+",'"+descripcion+"',"+descuento+","+cantidadRegalo+") "
								+ "ON DUPLICATE KEY UPDATE `cantidad`="+ovl.getCantidad()+"; ");
			}
		}
		
		
		
		
		boolean retorno = false;
		try 
		{
			LogicaAPI logica = new LogicaAPI();
			
			
			String serie = "";
			String tipoDoc = "";
			
			try
			{
				serie = this.getSerie();
				tipoDoc = this.getTipodoc();
			}
			catch (Exception e) {
				
			}
			
			String query = "INSERT INTO `ecommerce_import_venta` (`idVenta`, `porcDescuento`, `Comentario`, `clidireccion`, `cliRuc`, `clicedula`, "
					+ "`clinombre`, `clitelefono`, `uletiqueta`, `totalunidades`, `Sincronizada`, `ML`, `FP` ,`importePago`, `mail`,`idEmpresa`,`msjERP`,`Serie`,`TipoDoc`) " +
					" VALUES ("+this.getIdCarrito()+", "+this.getPorcDescuento()+", '"+this.getCliMail()+"', '"+this.getCliDireccion()+"', "
							+ "'"+this.getCliRuc()+"', '"+this.getCliCedula()+"', '"+this.getCliNombre()+"', '"+this.getCliTelefono()+"', "
									+ "'', "+cantUnidades+", -1, "+this.getMl()+",'"+this.getFormaPago()+"',"+this.getImportePago()+","
											+ "'"+this.getCliMail()+"', "+idEmpresa+",'','"+serie+"','"+tipoDoc+"') ";
			
			
			boolean hayUpdate = false;
			
			String onDup = " on duplicate key update";
			
			
			if(this.getPorcDescuento()!=null && !this.getPorcDescuento().equals(""))
			{
				hayUpdate=true;
				onDup += " porcDescuento = '"+this.getPorcDescuento()+"' ";
			}
			if(this.getComentario()!=null && !this.getComentario().equals(""))
			{
				hayUpdate=true;
				onDup += ", Comentario = '"+this.getComentario()+"' ";
			}
			if(this.getCliDireccion()!=null && !this.getCliDireccion().equals(""))
			{
				hayUpdate=true;
				onDup += ", clidireccion = '"+this.getCliDireccion()+"' ";
			}
			if(this.getCliRuc()!=null && !this.getCliRuc().equals(""))
			{
				hayUpdate=true;
				onDup += ", cliRuc = '"+this.getCliRuc()+"' ";
			}
			if(this.getCliCedula()!=null && !this.getCliCedula().equals(""))
			{
				hayUpdate=true;
				onDup += ", clicedula = '"+this.getCliCedula()+"' ";
			}
			if(this.getCliNombre()!=null && !this.getCliNombre().equals(""))
			{
				hayUpdate=true;
				onDup += ", clinombre = '"+this.getCliNombre()+"' ";
			}
			if(this.getCliTelefono()!=null && !this.getCliTelefono().equals(""))
			{
				hayUpdate=true;
				onDup += ", clitelefono = '"+this.getCliTelefono()+"' ";
			}
			
			if(this.getFormaPago()!=null && !this.getFormaPago().equals(""))
			{
				hayUpdate=true;
				onDup += ", FP = '"+this.getFormaPago()+"' ";
			}
			
			if(this.getImportePago()!=null)
			{
				hayUpdate=true;
				onDup += ", importePago = "+this.getImportePago()+" ";
			}
			
			if(this.getCliMail()!=null && !this.getCliMail().equals(""))
			{
				hayUpdate=true;
				onDup += ", mail = '"+this.getCliMail()+"' ";
			}
		
			String qDelete = "; delete from ecommerce_import_ventalinea where idventa ="+this.getIdCarrito()+";";		
			
			
			if(hayUpdate)
			{
				logica.persistir(query + onDup + qDelete);
			}
			else
			{
				logica.persistir(query + qDelete);
			}
			
			for (String q : insertsVtaLineas) 
			{
				q = q.replace("NaN", "0");
				logica.persistir(q);
			}
			
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return retorno;
	}

	public int getPrinter() {
		return printer;
	}

	public void setPrinter(int printer) {
		this.printer = printer;
	}


}
