
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
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroUsuario" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdEquipo" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdTienda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NroEmpresa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdDepOrigen" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Hora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdDepDestino" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdRazonDocumento" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = { "mensaje", "nroUsuario", "idEquipo", "idTienda", "nroEmpresa", "idDepOrigen", "fecha",
		"hora", "comentario", "idDepDestino", "idRazonDocumento", "xmlDetalle" })
@XmlRootElement(name = "GrabarTransferencia")
public class GrabarTransferencia {

	@XmlElement(name = "Mensaje")
	protected String mensaje;
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
	@XmlElement(name = "Fecha", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar fecha;
	@XmlElement(name = "Hora")
	protected String hora;
	@XmlElement(name = "Comentario")
	protected String comentario;
	@XmlElement(name = "IdDepDestino")
	protected short idDepDestino;
	@XmlElement(name = "IdRazonDocumento")
	protected int idRazonDocumento;
	protected String xmlDetalle;

	/**
	 * Gets the value of the mensaje property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the value of the mensaje property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMensaje(String value) {
		this.mensaje = value;
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
	 * Gets the value of the fecha property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFecha() {
		return fecha;
	}

	/**
	 * Sets the value of the fecha property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFecha(XMLGregorianCalendar value) {
		this.fecha = value;
	}

	/**
	 * Gets the value of the hora property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the value of the hora property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHora(String value) {
		this.hora = value;
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
	 * Gets the value of the idDepDestino property.
	 * 
	 */
	public short getIdDepDestino() {
		return idDepDestino;
	}

	/**
	 * Sets the value of the idDepDestino property.
	 * 
	 */
	public void setIdDepDestino(short value) {
		this.idDepDestino = value;
	}

	/**
	 * Gets the value of the idRazonDocumento property.
	 * 
	 */
	public int getIdRazonDocumento() {
		return idRazonDocumento;
	}

	/**
	 * Sets the value of the idRazonDocumento property.
	 * 
	 */
	public void setIdRazonDocumento(int value) {
		this.idRazonDocumento = value;
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
