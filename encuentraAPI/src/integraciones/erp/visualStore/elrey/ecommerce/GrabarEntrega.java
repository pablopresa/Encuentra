
package integraciones.erp.visualStore.elrey.ecommerce;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroUsuario" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdEquipo" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdTienda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NroEmpresa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdDepOrigen" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdMotivo" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NroCliente" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="SepararPorDepEnt" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FecPrometida" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IdTransporte" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="xmlDetalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "nroUsuario", "idEquipo", "idTienda", "nroEmpresa", "idDepOrigen",
		"comentario", "idMotivo", "nroCliente", "separarPorDepEnt", "fecPrometida", "idTransporte", "xmlDetalle" })
@XmlRootElement(name = "GrabarEntrega")
public class GrabarEntrega {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "NroUsuario")
	protected long nroUsuario;
	@XmlElement(name = "IdEquipo")
	protected short idEquipo;
	@XmlElement(name = "IdTienda")
	protected short idTienda;
	@XmlElement(name = "NroEmpresa")
	protected long nroEmpresa;
	@XmlElement(name = "IdDepOrigen")
	protected short idDepOrigen;
	@XmlElement(name = "Comentario")
	protected String comentario;
	@XmlElement(name = "IdMotivo")
	protected short idMotivo;
	@XmlElement(name = "NroCliente")
	protected long nroCliente;
	@XmlElement(name = "SepararPorDepEnt")
	protected boolean separarPorDepEnt;
	@XmlElement(name = "FecPrometida", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar fecPrometida;
	@XmlElement(name = "IdTransporte")
	protected short idTransporte;
	protected String xmlDetalle;

	/**
	 * Gets the value of the mensError property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMensError() {
		return mensError;
	}

	/**
	 * Sets the value of the mensError property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMensError(String value) {
		this.mensError = value;
	}

	/**
	 * Gets the value of the nroUsuario property.
	 * 
	 */
	public long getNroUsuario() {
		return nroUsuario;
	}

	/**
	 * Sets the value of the nroUsuario property.
	 * 
	 */
	public void setNroUsuario(long value) {
		this.nroUsuario = value;
	}

	/**
	 * Gets the value of the idEquipo property.
	 * 
	 */
	public short getIdEquipo() {
		return idEquipo;
	}

	/**
	 * Sets the value of the idEquipo property.
	 * 
	 */
	public void setIdEquipo(short value) {
		this.idEquipo = value;
	}

	/**
	 * Gets the value of the idTienda property.
	 * 
	 */
	public short getIdTienda() {
		return idTienda;
	}

	/**
	 * Sets the value of the idTienda property.
	 * 
	 */
	public void setIdTienda(short value) {
		this.idTienda = value;
	}

	/**
	 * Gets the value of the nroEmpresa property.
	 * 
	 */
	public long getNroEmpresa() {
		return nroEmpresa;
	}

	/**
	 * Sets the value of the nroEmpresa property.
	 * 
	 */
	public void setNroEmpresa(long value) {
		this.nroEmpresa = value;
	}

	/**
	 * Gets the value of the idDepOrigen property.
	 * 
	 */
	public short getIdDepOrigen() {
		return idDepOrigen;
	}

	/**
	 * Sets the value of the idDepOrigen property.
	 * 
	 */
	public void setIdDepOrigen(short value) {
		this.idDepOrigen = value;
	}

	/**
	 * Gets the value of the comentario property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Sets the value of the comentario property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setComentario(String value) {
		this.comentario = value;
	}

	/**
	 * Gets the value of the idMotivo property.
	 * 
	 */
	public short getIdMotivo() {
		return idMotivo;
	}

	/**
	 * Sets the value of the idMotivo property.
	 * 
	 */
	public void setIdMotivo(short value) {
		this.idMotivo = value;
	}

	/**
	 * Gets the value of the nroCliente property.
	 * 
	 */
	public long getNroCliente() {
		return nroCliente;
	}

	/**
	 * Sets the value of the nroCliente property.
	 * 
	 */
	public void setNroCliente(long value) {
		this.nroCliente = value;
	}

	/**
	 * Gets the value of the separarPorDepEnt property.
	 * 
	 */
	public boolean isSepararPorDepEnt() {
		return separarPorDepEnt;
	}

	/**
	 * Sets the value of the separarPorDepEnt property.
	 * 
	 */
	public void setSepararPorDepEnt(boolean value) {
		this.separarPorDepEnt = value;
	}

	/**
	 * Gets the value of the fecPrometida property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFecPrometida() {
		return fecPrometida;
	}

	/**
	 * Sets the value of the fecPrometida property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFecPrometida(XMLGregorianCalendar value) {
		this.fecPrometida = value;
	}

	/**
	 * Gets the value of the idTransporte property.
	 * 
	 */
	public short getIdTransporte() {
		return idTransporte;
	}

	/**
	 * Sets the value of the idTransporte property.
	 * 
	 */
	public void setIdTransporte(short value) {
		this.idTransporte = value;
	}

	/**
	 * Gets the value of the xmlDetalle property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlDetalle() {
		return xmlDetalle;
	}

	/**
	 * Sets the value of the xmlDetalle property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlDetalle(String value) {
		this.xmlDetalle = value;
	}

}
