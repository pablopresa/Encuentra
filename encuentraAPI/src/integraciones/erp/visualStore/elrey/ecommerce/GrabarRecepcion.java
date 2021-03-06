
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
 *         &lt;element name="TipoAfecta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdDepOrden" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NumeroDoc" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IdDepRecepcion" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="FechaRec" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="HoraRec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroProveedor" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="SerieRemito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroRemito" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = { "mensaje", "nroUsuario", "idEquipo", "idTienda", "nroEmpresa", "tipoAfecta",
		"idDepOrden", "numeroDoc", "idDepRecepcion", "fechaRec", "horaRec", "comentario", "nroProveedor", "serieRemito",
		"nroRemito", "xmlDetalle" })
@XmlRootElement(name = "GrabarRecepcion")
public class GrabarRecepcion {

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
	@XmlElement(name = "TipoAfecta")
	protected String tipoAfecta;
	@XmlElement(name = "IdDepOrden")
	protected short idDepOrden;
	@XmlElement(name = "NumeroDoc")
	protected int numeroDoc;
	@XmlElement(name = "IdDepRecepcion")
	protected short idDepRecepcion;
	@XmlElement(name = "FechaRec", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar fechaRec;
	@XmlElement(name = "HoraRec")
	protected String horaRec;
	@XmlElement(name = "Comentario")
	protected String comentario;
	@XmlElement(name = "NroProveedor")
	protected long nroProveedor;
	@XmlElement(name = "SerieRemito")
	protected String serieRemito;
	@XmlElement(name = "NroRemito")
	protected int nroRemito;
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
	 * Gets the value of the tipoAfecta property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTipoAfecta() {
		return tipoAfecta;
	}

	/**
	 * Sets the value of the tipoAfecta property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTipoAfecta(String value) {
		this.tipoAfecta = value;
	}

	/**
	 * Gets the value of the idDepOrden property.
	 * 
	 */
	public short getIdDepOrden() {
		return idDepOrden;
	}

	/**
	 * Sets the value of the idDepOrden property.
	 * 
	 */
	public void setIdDepOrden(short value) {
		this.idDepOrden = value;
	}

	/**
	 * Gets the value of the numeroDoc property.
	 * 
	 */
	public int getNumeroDoc() {
		return numeroDoc;
	}

	/**
	 * Sets the value of the numeroDoc property.
	 * 
	 */
	public void setNumeroDoc(int value) {
		this.numeroDoc = value;
	}

	/**
	 * Gets the value of the idDepRecepcion property.
	 * 
	 */
	public short getIdDepRecepcion() {
		return idDepRecepcion;
	}

	/**
	 * Sets the value of the idDepRecepcion property.
	 * 
	 */
	public void setIdDepRecepcion(short value) {
		this.idDepRecepcion = value;
	}

	/**
	 * Gets the value of the fechaRec property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFechaRec() {
		return fechaRec;
	}

	/**
	 * Sets the value of the fechaRec property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFechaRec(XMLGregorianCalendar value) {
		this.fechaRec = value;
	}

	/**
	 * Gets the value of the horaRec property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHoraRec() {
		return horaRec;
	}

	/**
	 * Sets the value of the horaRec property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHoraRec(String value) {
		this.horaRec = value;
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
	 * Gets the value of the nroProveedor property.
	 * 
	 */
	public long getNroProveedor() {
		return nroProveedor;
	}

	/**
	 * Sets the value of the nroProveedor property.
	 * 
	 */
	public void setNroProveedor(long value) {
		this.nroProveedor = value;
	}

	/**
	 * Gets the value of the serieRemito property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSerieRemito() {
		return serieRemito;
	}

	/**
	 * Sets the value of the serieRemito property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSerieRemito(String value) {
		this.serieRemito = value;
	}

	/**
	 * Gets the value of the nroRemito property.
	 * 
	 */
	public int getNroRemito() {
		return nroRemito;
	}

	/**
	 * Sets the value of the nroRemito property.
	 * 
	 */
	public void setNroRemito(int value) {
		this.nroRemito = value;
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
