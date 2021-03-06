
package integraciones.erp.visualStore.elrey.comercioElectronico;

import java.math.BigDecimal;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "SWComercioElectronicoSoap", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico")
public interface SWComercioElectronicoSoap {

	/**
	 * Reservar Numero de Venta
	 * 
	 * @param rut
	 * @param idCarrito
	 * @param idEquipo
	 * @param generarNuevo
	 * @param idEmpresa
	 * @param reservaNroVentaWebResult
	 * @param mensError
	 */
	@WebMethod(operationName = "ReservaNroVentaWeb", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ReservaNroVentaWeb")
	@RequestWrapper(localName = "ReservaNroVentaWeb", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ReservaNroVentaWeb")
	@ResponseWrapper(localName = "ReservaNroVentaWebResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ReservaNroVentaWebResponse")
	public void reservaNroVentaWeb(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idEquipo,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "IdCarrito", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") long idCarrito,
			@WebParam(name = "GenerarNuevo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") boolean generarNuevo,
			@WebParam(name = "Rut", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String rut,
			@WebParam(name = "ReservaNroVentaWebResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> reservaNroVentaWebResult);

	/**
	 * Cancelar Numero de Venta
	 * 
	 * @param cancelarNroVentaWebResult
	 * @param idCarrito
	 * @param idEquipo
	 * @param idEmpresa
	 * @param mensError
	 */
	@WebMethod(operationName = "CancelarNroVentaWeb", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/CancelarNroVentaWeb")
	@RequestWrapper(localName = "CancelarNroVentaWeb", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.CancelarNroVentaWeb")
	@ResponseWrapper(localName = "CancelarNroVentaWebResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.CancelarNroVentaWebResponse")
	public void cancelarNroVentaWeb(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idEquipo,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "IdCarrito", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") long idCarrito,
			@WebParam(name = "CancelarNroVentaWebResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Boolean> cancelarNroVentaWebResult);

	/**
	 * Grabar Venta Creando el Cliente
	 * 
	 * @param idEquipo
	 * @param idEmpresa
	 * @param mensError
	 * @param xmlDatos
	 * @param grabarVentaClienteResult
	 */
	@WebMethod(operationName = "GrabarVentaCliente", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/GrabarVentaCliente")
	@RequestWrapper(localName = "GrabarVentaCliente", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarVentaCliente")
	@ResponseWrapper(localName = "GrabarVentaClienteResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarVentaClienteResponse")
	public void grabarVentaCliente(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idEquipo,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "xmlDatos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlDatos,
			@WebParam(name = "GrabarVentaClienteResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Integer> grabarVentaClienteResult);

	/**
	 * Grabar Venta
	 * 
	 * @param idEquipo
	 * @param grabarVentaResult
	 * @param idEmpresa
	 * @param mensError
	 * @param xmlDatos
	 */
	@WebMethod(operationName = "GrabarVenta", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/GrabarVenta")
	@RequestWrapper(localName = "GrabarVenta", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarVenta")
	@ResponseWrapper(localName = "GrabarVentaResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarVentaResponse")
	public void grabarVenta(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idEquipo,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "xmlDatos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlDatos,
			@WebParam(name = "GrabarVentaResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Integer> grabarVentaResult);

	/**
	 * Grabar Venta generando Distribucion Interna
	 * 
	 * @param idEquipo
	 * @param grabarVentaDistIntResult
	 * @param idEmpresa
	 * @param mensError
	 * @param xmlDatos
	 * @param xmlArtDep
	 */
	@WebMethod(operationName = "GrabarVentaDistInt", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/GrabarVentaDistInt")
	@RequestWrapper(localName = "GrabarVentaDistInt", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarVentaDistInt")
	@ResponseWrapper(localName = "GrabarVentaDistIntResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarVentaDistIntResponse")
	public void grabarVentaDistInt(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idEquipo,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "xmlDatos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlDatos,
			@WebParam(name = "xmlArtDep", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> xmlArtDep,
			@WebParam(name = "GrabarVentaDistIntResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Integer> grabarVentaDistIntResult);

	/**
	 * Grabar Venta generando Distribucion a Cliente
	 * 
	 * @param idEquipo
	 * @param idDepEntrega
	 * @param idEmpresa
	 * @param grabarVentaDistCliResult
	 * @param mensError
	 * @param xmlDatos
	 */
	@WebMethod(operationName = "GrabarVentaDistCli", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/GrabarVentaDistCli")
	@RequestWrapper(localName = "GrabarVentaDistCli", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarVentaDistCli")
	@ResponseWrapper(localName = "GrabarVentaDistCliResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarVentaDistCliResponse")
	public void grabarVentaDistCli(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idEquipo,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "xmlDatos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlDatos,
			@WebParam(name = "IdDepEntrega", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idDepEntrega,
			@WebParam(name = "GrabarVentaDistCliResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Integer> grabarVentaDistCliResult);

	/**
	 * Prepara y Termina una Entrega de Mercaderia a Cliente
	 * 
	 * @param idCarrito
	 * @param idEquipo
	 * @param mensError
	 * @param entregaPrepararyTerminarResult
	 */
	@WebMethod(operationName = "EntregaPrepararyTerminar", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/EntregaPrepararyTerminar")
	@RequestWrapper(localName = "EntregaPrepararyTerminar", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.EntregaPrepararyTerminar")
	@ResponseWrapper(localName = "EntregaPrepararyTerminarResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.EntregaPrepararyTerminarResponse")
	public void entregaPrepararyTerminar(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idEquipo,
			@WebParam(name = "IdCarrito", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") long idCarrito,
			@WebParam(name = "EntregaPrepararyTerminarResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Boolean> entregaPrepararyTerminarResult);

	/**
	 * StockSucursales
	 * 
	 * @param idArticulo
	 * @param depositos
	 * @param consStockResult
	 * @param mensError
	 */
	@WebMethod(operationName = "ConsStock", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsStock")
	@RequestWrapper(localName = "ConsStock", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsStock")
	@ResponseWrapper(localName = "ConsStockResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsStockResponse")
	public void consStock(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdArticulo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String idArticulo,
			@WebParam(name = "Depositos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String depositos,
			@WebParam(name = "ConsStockResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consStockResult);

	/**
	 * StockSucursalesEmpBd
	 * 
	 * @param idArticulo
	 * @param consStockEmpBdResult
	 * @param depositos
	 * @param idEmpresa
	 * @param baseDatos
	 * @param mensError
	 */
	@WebMethod(operationName = "ConsStockEmpBd", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsStockEmpBd")
	@RequestWrapper(localName = "ConsStockEmpBd", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsStockEmpBd")
	@ResponseWrapper(localName = "ConsStockEmpBdResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsStockEmpBdResponse")
	public void consStockEmpBd(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdArticulo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String idArticulo,
			@WebParam(name = "Depositos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String depositos,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "BaseDatos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String baseDatos,
			@WebParam(name = "ConsStockEmpBdResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consStockEmpBdResult);

	/**
	 * Devuelve los Depositos en los cuales hay Disponibilidad de Stock para el
	 * Carrito
	 * 
	 * @param consDisponibilidadResult
	 * @param depositos
	 * @param idEmpresa
	 * @param baseDatos
	 * @param mensError
	 * @param xmlCarrito
	 */
	@WebMethod(operationName = "ConsDisponibilidad", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsDisponibilidad")
	@RequestWrapper(localName = "ConsDisponibilidad", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsDisponibilidad")
	@ResponseWrapper(localName = "ConsDisponibilidadResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsDisponibilidadResponse")
	public void consDisponibilidad(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "Depositos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String depositos,
			@WebParam(name = "BaseDatos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String baseDatos,
			@WebParam(name = "xmlCarrito", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlCarrito,
			@WebParam(name = "ConsDisponibilidadResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consDisponibilidadResult);

	/**
	 * Consulta de Cliente
	 * 
	 * @param consClienteResult
	 * @param numero
	 * @param mensError
	 */
	@WebMethod(operationName = "ConsCliente", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsCliente")
	@RequestWrapper(localName = "ConsCliente", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsCliente")
	@ResponseWrapper(localName = "ConsClienteResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsClienteResponse")
	public void consCliente(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "Numero", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") long numero,
			@WebParam(name = "ConsClienteResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consClienteResult);

	/**
	 * Consulta de Cliente por RUT
	 * 
	 * @param rut
	 * @param consClientexRutResult
	 * @param mensError
	 */
	@WebMethod(operationName = "ConsClientexRut", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsClientexRut")
	@RequestWrapper(localName = "ConsClientexRut", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsClientexRut")
	@ResponseWrapper(localName = "ConsClientexRutResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsClientexRutResponse")
	public void consClientexRut(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "Rut", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String rut,
			@WebParam(name = "ConsClientexRutResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consClientexRutResult);

	/**
	 * Grabar PersonaCliente
	 * 
	 * @param grabarPersonaClienteXmlResult
	 * @param mensError
	 * @param xmlDatos
	 */
	@WebMethod(operationName = "GrabarPersonaClienteXml", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/GrabarPersonaClienteXml")
	@RequestWrapper(localName = "GrabarPersonaClienteXml", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarPersonaClienteXml")
	@ResponseWrapper(localName = "GrabarPersonaClienteXmlResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.GrabarPersonaClienteXmlResponse")
	public void grabarPersonaClienteXml(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "xmlDatos", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlDatos,
			@WebParam(name = "GrabarPersonaClienteXmlResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> grabarPersonaClienteXmlResult);

	/**
	 * Consultar si un Cliente debe un Articulo
	 * 
	 * @param idArticulo
	 * @param rut
	 * @param existeDeudaClienteResult
	 * @param idEmpresa
	 * @param mensError
	 */
	@WebMethod(operationName = "ExisteDeudaCliente", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ExisteDeudaCliente")
	@RequestWrapper(localName = "ExisteDeudaCliente", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ExisteDeudaCliente")
	@ResponseWrapper(localName = "ExisteDeudaClienteResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ExisteDeudaClienteResponse")
	public void existeDeudaCliente(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdArticulo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String idArticulo,
			@WebParam(name = "Rut", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String rut,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "ExisteDeudaClienteResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Boolean> existeDeudaClienteResult);

	/**
	 * Consultar Cantidad Disponible y Vencimiento de Articulos para un Cliente
	 * 
	 * @param idArticulo
	 * @param rut
	 * @param vencimiento
	 * @param consultaCantCompradaResult
	 * @param idEmpresa
	 * @param saldo
	 * @param mensError
	 */
	@WebMethod(operationName = "ConsultaCantComprada", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsultaCantComprada")
	@RequestWrapper(localName = "ConsultaCantComprada", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultaCantComprada")
	@ResponseWrapper(localName = "ConsultaCantCompradaResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultaCantCompradaResponse")
	public void consultaCantComprada(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdArticulo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String idArticulo,
			@WebParam(name = "Rut", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String rut,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "Saldo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<BigDecimal> saldo,
			@WebParam(name = "Vencimiento", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<XMLGregorianCalendar> vencimiento,
			@WebParam(name = "ConsultaCantCompradaResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Boolean> consultaCantCompradaResult);

	/**
	 * Consultar Articulo
	 * 
	 * @param idArticulo
	 * @param idEquipo
	 * @param idEmpresa
	 * @param consultaArticuloResult
	 * @param mensError
	 */
	@WebMethod(operationName = "ConsultaArticulo", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsultaArticulo")
	@RequestWrapper(localName = "ConsultaArticulo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultaArticulo")
	@ResponseWrapper(localName = "ConsultaArticuloResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultaArticuloResponse")
	public void consultaArticulo(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdArticulo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String idArticulo,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEquipo,
			@WebParam(name = "ConsultaArticuloResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consultaArticuloResult);

	/**
	 * Consultar Articulos Disponibles para un Cliente
	 * 
	 * @param idArticulo
	 * @param rut
	 * @param idEquipo
	 * @param idEmpresa
	 * @param mensError
	 * @param consultaArtComprarResult
	 */
	@WebMethod(operationName = "ConsultaArtComprar", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsultaArtComprar")
	@RequestWrapper(localName = "ConsultaArtComprar", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultaArtComprar")
	@ResponseWrapper(localName = "ConsultaArtComprarResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultaArtComprarResponse")
	public void consultaArtComprar(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdArticulo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String idArticulo,
			@WebParam(name = "Rut", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String rut,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "IdEquipo", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEquipo,
			@WebParam(name = "ConsultaArtComprarResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consultaArtComprarResult);

	/**
	 * Consultar Deuda de un Cliente
	 * 
	 * @param rut
	 * @param consultarDeudaResult
	 * @param idEmpresa
	 * @param login
	 * @param mensError
	 */
	@WebMethod(operationName = "ConsultarDeuda", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsultarDeuda")
	@RequestWrapper(localName = "ConsultarDeuda", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultarDeuda")
	@ResponseWrapper(localName = "ConsultarDeudaResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultarDeudaResponse")
	public void consultarDeuda(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "Rut", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String rut,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "Login", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String login,
			@WebParam(name = "ConsultarDeudaResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consultarDeudaResult);

	/**
	 * Consultar Datos de Factura Electronica de una Venta
	 * 
	 * @param idTipoDocumento
	 * @param numeroDoc
	 * @param serie
	 * @param idEmpresa
	 * @param idDeposito
	 * @param mensError
	 * @param consultarVentaCFEResult
	 */
	@WebMethod(operationName = "ConsultarVentaCFE", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ConsultarVentaCFE")
	@RequestWrapper(localName = "ConsultarVentaCFE", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultarVentaCFE")
	@ResponseWrapper(localName = "ConsultarVentaCFEResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ConsultarVentaCFEResponse")
	public void consultarVentaCFE(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "IdEmpresa", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int idEmpresa,
			@WebParam(name = "IdDeposito", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") short idDeposito,
			@WebParam(name = "IdTipoDocumento", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String idTipoDocumento,
			@WebParam(name = "Serie", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String serie,
			@WebParam(name = "NumeroDoc", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") int numeroDoc,
			@WebParam(name = "ConsultarVentaCFEResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<String> consultarVentaCFEResult);

	/**
	 * Cobranza de Cliente
	 * 
	 * @param cobranzaClienteResult
	 * @param mensError
	 * @param xmlCobranza
	 */
	@WebMethod(operationName = "CobranzaCliente", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/CobranzaCliente")
	@RequestWrapper(localName = "CobranzaCliente", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.CobranzaCliente")
	@ResponseWrapper(localName = "CobranzaClienteResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.CobranzaClienteResponse")
	public void cobranzaCliente(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "xmlCobranza", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlCobranza,
			@WebParam(name = "CobranzaClienteResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Boolean> cobranzaClienteResult);

	/**
	 * Reversar Cobranza de Cliente
	 * 
	 * @param reversoCobranzaResult
	 * @param xmlReverso
	 * @param mensError
	 */
	@WebMethod(operationName = "ReversoCobranza", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ReversoCobranza")
	@RequestWrapper(localName = "ReversoCobranza", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ReversoCobranza")
	@ResponseWrapper(localName = "ReversoCobranzaResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ReversoCobranzaResponse")
	public void reversoCobranza(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "xmlReverso", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlReverso,
			@WebParam(name = "ReversoCobranzaResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Boolean> reversoCobranzaResult);

	/**
	 * Anulacion Cobranza de Cliente
	 * 
	 * @param anulacionCobranzaResult
	 * @param xmlAnulacion
	 * @param mensError
	 */
	@WebMethod(operationName = "AnulacionCobranza", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/AnulacionCobranza")
	@RequestWrapper(localName = "AnulacionCobranza", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.AnulacionCobranza")
	@ResponseWrapper(localName = "AnulacionCobranzaResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.AnulacionCobranzaResponse")
	public void anulacionCobranza(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "xmlAnulacion", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlAnulacion,
			@WebParam(name = "AnulacionCobranzaResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Boolean> anulacionCobranzaResult);

	/**
	 * Reversar Anulacion Cobranza de Cliente
	 * 
	 * @param xmlRevAnu
	 * @param reversoAnulCobranzaResult
	 * @param mensError
	 */
	@WebMethod(operationName = "ReversoAnulCobranza", action = "http://tempuri.org/VSServicioWeb/SWComercioElectronico/ReversoAnulCobranza")
	@RequestWrapper(localName = "ReversoAnulCobranza", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ReversoAnulCobranza")
	@ResponseWrapper(localName = "ReversoAnulCobranzaResponse", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", className = "org.tempuri.vsservicioweb.swcomercioelectronico.ReversoAnulCobranzaResponse")
	public void reversoAnulCobranza(
			@WebParam(name = "MensError", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.INOUT) Holder<String> mensError,
			@WebParam(name = "xmlRevAnu", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico") String xmlRevAnu,
			@WebParam(name = "ReversoAnulCobranzaResult", targetNamespace = "http://tempuri.org/VSServicioWeb/SWComercioElectronico", mode = WebParam.Mode.OUT) Holder<Boolean> reversoAnulCobranzaResult);

}
