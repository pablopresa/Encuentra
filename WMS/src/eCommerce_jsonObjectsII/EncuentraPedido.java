package eCommerce_jsonObjectsII;

import java.sql.Connection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import beans.Usuario;
import clientesVisual_Store.Std.clienteWSVS_new.OrdenVenta;
import logica.Logica;
import dataTypes.DataIDDescripcion;

import persistencia._EncuentraConexion;
@JsonIgnoreProperties(ignoreUnknown = true)
public class EncuentraPedido 
{
	private int cantidad, cerrado, ml, idDepositoEnvio;
	private String descripcion, estado,urlEtiqueta, sucursalPick, formaPago,fecha;
	private Double descuento,precio, montoEnvio;;
	private List <EncuentraPedidoArticulo> articulosPedido;
	private List <EncuentraPedidoArticuloReq> articulosPedidoReq;
	private DataIDDescripcion canalMercadoLibre;
	private DataIDDescripcion shippingType;
	private int canalAnaloga;
	private String personaRetira;
	private Long idPedido;
	private String idPedidoSTR;
	private String idEcommerce;
	private String ticketNumber;
	private String trackingNumber;
	private String subpedido;
	private OrdenVenta orden;
	private boolean freshipping;
	private int idEstado;
	private Cliente cliente;
	

	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	private String horarioD,horarioH;	
	
	
	
	public String getHorarioD() {
		return horarioD;
	}
	public void setHorarioD(String horarioD) {
		this.horarioD = horarioD;
	}
	public String getHorarioH() {
		return horarioH;
	}
	public void setHorarioH(String horarioH) {
		this.horarioH = horarioH;
	}
	
	
	
	
	
	public boolean isFreshipping() {
		return freshipping;
	}
	public void setFreshipping(boolean freshipping) {
		this.freshipping = freshipping;
	}
	public String getIdEcommerce() {
		return idEcommerce;
	}
	public void setIdEcommerce(String idEcommerce) {
		this.idEcommerce = idEcommerce;
	}
	public String getIdPedidoSTR() {
		return idPedidoSTR;
	}
	public void setIdPedidoSTR(String idPedidoSTR) {
		this.idPedidoSTR = idPedidoSTR;
	}
	public String getPersonaRetira() {
		return personaRetira;
	}
	public void setPersonaRetira(String personaRetira) {
		this.personaRetira = personaRetira;
	}
	public int getCanalAnaloga() {
		return canalAnaloga;
	}
	public void setCanalAnaloga(int canalAnaloga) {
		this.canalAnaloga = canalAnaloga;
	}
	public DataIDDescripcion getCanalMercadoLibre() {
		return canalMercadoLibre;
	}
	public void setCanalMercadoLibre(DataIDDescripcion canalMercadoLibre) {
		this.canalMercadoLibre = canalMercadoLibre;
	}
	public int getMl() {
		return ml;
	}
	public void setMl(int ml) {
		this.ml = ml;
	}
	public String getSucursalPick() {
		return sucursalPick;
	}
	public void setSucursalPick(String sucursalPick) {
		this.sucursalPick = sucursalPick;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public List<EncuentraPedidoArticulo> getArticulosPedido() {
		return articulosPedido;
	}
	public void setArticulosPedido(List<EncuentraPedidoArticulo> articulosPedido) {
		this.articulosPedido = articulosPedido;
	}
	public List<EncuentraPedidoArticuloReq> getArticulosPedidoReq() {
		return articulosPedidoReq;
	}
	public void setArticulosPedidoReq(
			List<EncuentraPedidoArticuloReq> articulosPedidoReq) {
		this.articulosPedidoReq = articulosPedidoReq;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCerrado() {
		return cerrado;
	}
	public void setCerrado(int cerrado) {
		this.cerrado = cerrado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUrlEtiqueta() {
		return urlEtiqueta;
	}
	public void setUrlEtiqueta(String urlEtiqueta) {
		this.urlEtiqueta = urlEtiqueta;
	}
	public EncuentraPedido() {
	}
	
	
	
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	
	
	
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getMontoEnvio() {
		return montoEnvio;
	}
	public void setMontoEnvio(Double montoEnvio) {
		this.montoEnvio = montoEnvio;
	}
	public boolean save(int idEmpresa) 
	{
		Logica Logica = new Logica();
		boolean retorno = false;
		try 
		{
			int idCanal = 0;
			
			try
			{
				idCanal = this.getCanalMercadoLibre().getId();
			}
			catch (Exception e) 
			{
				idCanal = this.getCanalAnaloga();
			}
			
			String fe = "";
			
			if(this.getFecha()==null)
			{
				fe = "CURRENT_TIMESTAMP()";
			}
			else
			{
				fe = "'"+this.getFecha()+"'";
			}
			int shippingtype = 0;
			try
			{
				shippingtype = this.getShippingType().getId();
			}
			catch (Exception e) 
			{
			
			}
			if(this.idEstado == 0) { this.idEstado = 1;}
			
			retorno = Logica.persistir("INSERT INTO `ecommerce_pedido` (`idPedido`, `descripcion`, `UnidadesTotal`, `EstadoEcommerce`, `cerrado`, `URLetiqueta`, `FormaPago`,idCanalML,idFenicio,"
					+ "stamptime,ticketNumber,idEmpresa,facturado,subpedido, shippingType, estadoEncuentra) "
					+ "VALUES ('"+this.getIdPedido()+"', '"+this.getDescripcion().replace("'", "")+"', '"+this.getCantidad()+"', '"+this.getEstado()+"', '0', '"+this.getUrlEtiqueta()+"', "
							+ "'"+this.getFormaPago()+"',"+idCanal+",'"+this.getIdFenicio()+"',"+fe+",'"+this.getTicketNumber()+"',"+idEmpresa+","+this.precio+",'"+this.getSubpedido()+"', "
									+shippingtype+","+this.idEstado+");");
			
			if(retorno)
			{
				StringBuilder sb = new StringBuilder();
				sb.append("INSERT INTO `articulos` (`idArticulo`, `Descripcion`, `IdTipo`,`idEmpresa`) VALUES ('"+this.getIdPedido()+"', '"+this.getDescripcion().replace("'", "")+"', 2,"+idEmpresa+") "
						+ " on duplicate key update  Descripcion = CONCAT(Descripcion ,' ','"+this.getDescripcion().replace("'", "")+"');");
				
				for (EncuentraPedidoArticulo art : this.getArticulosPedido()) 
				{
					if(art.getVariacion()==null)
					{
						art.setVariacion("");
					}
					
					
					
					sb.append("INSERT INTO `ecommerce_pedido_articulos` (`idPedido`, `idArticulo`, `cantidadPedido`, `idEmpresa`,`NotaArticulo`,`CantidadRegalo`) VALUES ('"+this.getIdPedido()+"', '"+art.getArticulo()+"', '"+art.getCantidad()+"',"+idEmpresa+",'"+art.getVariacion()+"',"+art.getCantidadRegalo()+") on duplicate key update  NotaArticulo = CONCAT(NotaArticulo ,' ','"+art.getVariacion()+"') , `cantidadPedido`=`cantidadPedido`+'"+art.getCantidad()+"'; ");
					sb.append("INSERT INTO `articulos` (`idArticulo`, `Descripcion`, `IdTipo`,`idEmpresa`) VALUES ('"+this.getIdPedido()+"_"+art.getArticulo()+"', '"+this.getDescripcion().replace("'", "")+"', 2,"+idEmpresa+") on duplicate key update Descripcion ='"+this.getDescripcion().replace("'", "")+"';");
				}
				retorno = Logica.persistir(sb.toString());
			}
			else
			{
				System.out.println("pedido duplicado "+this.getIdPedido());
			}
		
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return retorno;
		
		
		
	}
	
	public boolean update(int idEmpresa) 
	{
		Logica Logica = new Logica();
		boolean retorno = false;
		try 
		{
			int idCanal = 0;
			
			try
			{
				idCanal = this.getCanalMercadoLibre().getId();
			}
			catch (Exception e) 
			{
				idCanal = this.getCanalAnaloga();
			}
			
			
			retorno = Logica.persistir("update ecommerce_pedido set UnidadesTotal ='"+this.getCantidad()+"' where idEmpresa = "+idEmpresa+" AND idpedido =" +this.getIdPedido());	
			
			for (EncuentraPedidoArticulo art : this.getArticulosPedido()) 
			{
				Logica.persistir("INSERT INTO `ecommerce_pedido_articulos` (`idPedido`, `idArticulo`, `cantidadPedido`,idEmpresa) VALUES ('"+this.getIdPedido()+"', '"+art.getArticulo()+"', '"+art.getCantidad()+"',"+idEmpresa+");");
			}
				
			
		
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return retorno;
		
		
		
	}
	
	
	public int getIdDepositoEnvio() {
		return idDepositoEnvio;
	}
	public void setIdDepositoEnvio(int idDepositoEnvio) {
		this.idDepositoEnvio = idDepositoEnvio;
	}
	public boolean updateEtiqueta(int destino, int idEmpresa) 
	{
		Logica Logica = new Logica();
		boolean retorno = false;
		try 
		{
			
			Logica.persistir("UPDATE `ecommerce_pedido` SET `URLetiqueta`='"+this.getUrlEtiqueta()+"' WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+this.getIdPedido()+";");
			
			if(this.getTrackingNumber()!= null && !this.trackingNumber.equals("")){
				Logica.persistir("update ecommerce_pedido_destino set tracking = '"+this.trackingNumber+"' where  idEmpresa="+idEmpresa+
						" AND  idPedido="+this.getIdPedido());
			}
			if(destino==0)
			{
				
			}
			else
			{
				updateDestino(destino,"",idEmpresa,0.0,false);
				Logica.persistir("update ecommerce_pedido_destino set manual = 1 where  idEmpresa="+idEmpresa+" AND  idPedido="+this.getIdPedido());
				Logica.persistir("update ecommerce_pedido_articulos_req set procesarEnPickup = 0 where idEmpresa="+idEmpresa+" AND idPedido="+this.getIdPedido());
			}
			
			
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return retorno;
		
		
		
	}
	public boolean updateDestino(int destino, String track, int idEmpresa,double costoEnvio, boolean freeshipping) 
	{
		Logica Logica = new Logica();
		boolean retorno = false;
		int freeshippingI = 0;
		if(freeshipping)
		{
			freeshippingI = 1;
		}
		
		try 
		{
			retorno= Logica.persistir("insert into ecommerce_pedido_destino (idPedido,idDestino,tracking,idEmpresa,CostoEnvio,isFreeShipping) values "
					+ "("+this.getIdPedido()+","+destino+",'"+track+"',"+idEmpresa+","+costoEnvio+","+freeshippingI+") "
							+ "on duplicate key update idDestino = "+destino+", tracking='"+track+"',CostoEnvio="+costoEnvio+",isFreeShipping="+freeshippingI);		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return retorno;
		
		
		
	}
	
	public boolean updateLabelShipping(int idDepositoDestino, String trackString, String url, int manual, int idEmpresa,boolean cancelarPickup, Usuario u) 
	{
		Logica Logica = new Logica();
		boolean retorno = false;		
		try 
		{
			_EncuentraConexion econ = new _EncuentraConexion();
			Connection cone;
			cone = econ.getConnection();
			String consulta = "select * from ecommerce_pedido_destino where  idPedido = "+this.getIdPedido()+" and Cerrado = 1 and idEmpresa="+idEmpresa;			
			if(!econ.hayReg(consulta))//es decir que no hay registros cerrados
			{
				retorno = true;
			}			  
		} 
		catch (Exception e) 
		{
			return false;
		}		
		if(retorno)
		{
			try 
			{				
				String insert = "";	
				insert = "INSERT INTO `ecommerce_pedido_destino` ( `idPedido`, `idDestino`,`tracking`,`manual`, `idEmpresa`) VALUES "
						+ "('"+this.getIdPedido()+"', ' "+idDepositoDestino+"', '"+trackString+"', "+manual+", "+idEmpresa+") "
								+ "on duplicate key update  `idDestino`="+idDepositoDestino+", `tracking`='"+trackString+"';";
				
				insert += "UPDATE `ecommerce_pedido` SET `URLetiqueta`='"+url+"' WHERE idEmpresa="+idEmpresa+
						" AND `idPedido`="+this.getIdPedido()+"; ";
				
				if(cancelarPickup) {
					insert += "update ecommerce_pedido_articulos_req set procesarEnPickup = 0 where idEmpresa="+idEmpresa+
							" AND idPedido="+this.getIdPedido()+"; ";	
				}
				if(manual==1) {
					String msj = "Modificacion de entrega: destino("+idDepositoDestino+") - tracking("+trackString+") - etiqueta("+url+")";
					Logica.logPedido(this.getIdPedido(), u.getNumero(), 0, msj, 0, idEmpresa);
				}
				
				if(!url.equals(""))
					Logica.updateEcommerceEstado(this.getIdPedido(), 3, idEmpresa, u);
							
				
				Logica.persistir(insert);
				System.out.println("insert:"+insert);		
				
				int shippingType = 1;
				
				if(Logica.depositoPickUp(idEmpresa, idDepositoDestino) == 1) {
					shippingType = 2;
				}
				
				String update = "UPDATE ecommerce_pedido e SET e.shippingType = "+shippingType+" WHERE idPedido = "+this.getIdPedido()+" AND idEmpresa = "+idEmpresa+";";
				Logica.persistir(update);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		
		return retorno;
		
	}
	
	public boolean updateML(int idEmpresa) 
	{
		Logica Logica = new Logica();
		boolean retorno = false;
		try 
		{
			Logica.persistir("UPDATE `ecommerce_pedido` SET `ML`=1 WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+this.getIdPedido()+";");
			
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return retorno;
		
		
		
	}
	
	public boolean updateShippingType(int idEmpresa) 
	{
		Logica Logica = new Logica();
		boolean retorno = false;
		try 
		{
			Logica.persistir("UPDATE `ecommerce_pedido` SET `shippingType`="+this.getShippingType().getId()+" WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+this.getIdPedido()+";");
			
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return retorno;
		
		
		
	}
	
	public boolean updateShipping(int idDepositoDestino, String trackString, String mailCliente, int idEmpresa) 
	{
		boolean retorno = false;
		Logica Logica = new Logica();
		try 
		{
			_EncuentraConexion econ = new _EncuentraConexion();
			Connection cone;
			cone = econ.getConnection();

			String consulta = "select * from ecommerce_pedido_destino where idEmpresa="+idEmpresa+" and  idPedido = "+this.getIdPedido()+" and Cerrado = 1";
			
			if(!econ.hayReg(consulta))//es decir que no hay registros cerrados
			{
				retorno = true;
			}
			  
		} 
		catch (Exception e) 
		{
			return false;
		}
		
		if(retorno)
		{
			try 
			{
				
				String insert = "";
				
				
				if(trackString==null)
				{
					insert="INSERT INTO `ecommerce_pedido_destino` (`idPedido`, `idDestino`,`mailCliente`, idEmpresa, destinoFinal) VALUES ('"+this.getIdPedido()+"', '"+idDepositoDestino+"','"+mailCliente+"',"+idEmpresa+","+this.getIdDepositoEnvio()+") on duplicate key update  `idDestino`="+idDepositoDestino+", `mailCliente`='"+mailCliente+"', destinoFinal="+this.getIdDepositoEnvio()+" ;";
				}
				else
				{
					insert="INSERT INTO `ecommerce_pedido_destino` (`idPedido`, `idDestino`,`tracking`,`mailCliente`, idEmpresa, destinoFinal) VALUES "
							+ "('"+this.getIdPedido()+"', '"+idDepositoDestino+"','"+trackString+"','"+mailCliente+"',"+idEmpresa+","+this.getIdDepositoEnvio()+") "
									+ "on duplicate key update `mailCliente`='"+mailCliente+"', `idDestino`="+idDepositoDestino+", `tracking`='"+trackString+"', `destinoFinal`="+this.getIdDepositoEnvio()+" ;";
				}
				
				
				Logica.persistir(insert);
				System.out.println("insert:"+insert);
				
			
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		
		return retorno;
		
	}
	
	public String getIdFenicio() {
		return idEcommerce;
	}
	public void setIdFenicio(String id) {
		this.idEcommerce = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	
	public boolean updateShipping(int idDepositoDestino, String trackString, int idEmpresa) 
	{
		boolean retorno = false;
		Logica Logica = new Logica();
		try 
		{
			_EncuentraConexion econ = new _EncuentraConexion();
			Connection cone;
			cone = econ.getConnection();

			String consulta = "select * from ecommerce_pedido_destino where  idPedido = "+this.getIdPedido()+" and Cerrado = 1 and idEmpresa="+idEmpresa;
			
			if(!econ.hayReg(consulta))//es decir que no hay registros cerrados
			{
				retorno = true;
			}
			  
		} 
		catch (Exception e) 
		{
			return false;
		}
		
		if(retorno)
		{
			try 
			{
				
				String insert = "";
				
				
				if(trackString==null)
				{
					insert="INSERT INTO `ecommerce_pedido_destino` (`idPedido`, `idDestino`, `idEmpresa`) VALUES ('"+this.getIdPedido()+"', '"+idDepositoDestino+"',"+idEmpresa+") on duplicate key update  `idDestino`="+idDepositoDestino+" ;";
				}
				else
				{
					insert="INSERT INTO `ecommerce_pedido_destino` ( `idPedido`, `idDestino`,`tracking`, `idEmpresa`) VALUES ('"+this.getIdPedido()+"', '"+idDepositoDestino+"','"+trackString+"',"+idEmpresa+") on duplicate key update  `idDestino`="+idDepositoDestino+", `tracking`='"+trackString+"' ;";
				}
				
				
				Logica.persistir(insert);
				System.out.println("insert:"+insert);
				
			
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		
		return retorno;
		
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getSubpedido() 
	{
		if(subpedido!=null)
		{
			return subpedido;
		}
		else
		{
			return idPedido+"";
		}
	}
	public void setSubpedido(String subpedido) {
		this.subpedido = subpedido;
	}
	public DataIDDescripcion getShippingType() {
		return shippingType;
	}
	public void setShippingType(DataIDDescripcion formaEnvio) {
		this.shippingType = formaEnvio;
	}
	public OrdenVenta getOrden() {
		return orden;
	}
	public void setOrden(OrdenVenta orden) {
		this.orden = orden;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	

	
	
}
