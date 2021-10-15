
package integraciones.erp.visualStore.stadium.v1;

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
 *         &lt;element name="IdUsuario" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdEquipo" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdTienda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NroEmpresa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="TipoAfecta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdDeposito" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IdDepRecepcion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FechaRecep" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Hora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroDoc" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IdProveedor" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "", propOrder = { "mensaje", "idUsuario", "idEquipo", "idTienda", "nroEmpresa", "tipoAfecta",
		"idDeposito", "idDepRecepcion", "fechaRecep", "hora", "comentario", "numeroDoc", "idProveedor", "serieRemito",
		"nroRemito", "xmlDetalle" })
@XmlRootElement(name = "GrabarRecepcion")
public class GrabarRecepcion {

	@XmlElement(name = "Mensaje")
	protected String mensaje;
	@XmlElement(name = "IdUsuario")
	protected long idUsuario;
	@XmlElement(name = "IdEquipo")
	protected short idEquipo;
	@XmlElement(name = "IdTienda")
	protected short idTienda;
	@XmlElement(name = "NroEmpresa")
	protected long nroEmpresa;
	@XmlElement(name = "TipoAfecta")
	protected String tipoAfecta;
	@XmlElement(name = "IdDeposito")
	protected int idDeposito;
	@XmlElement(name = "IdDepRecepcion")
	protected int idDepRecepcion;
	@XmlElement(name = "FechaRecep", required = true)
	@XmlSchemaType(name = "dateTime")
	protected String fechaRecep;
	@XmlElement(name = "Hora")
	protected String hora;
	@XmlElement(name = "Comentario")
	protected String comentario;
	@XmlElement(name = "NumeroDoc")
	protected int numeroDoc;
	@XmlElement(name = "IdProveedor")
	protected long idProveedor;
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
	 * Gets the value of the idUsuario property.
	 * 
	 */
	public long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Sets the value of the idUsuario property.
	 * 
	 */
	public void setIdUsuario(long value) {
		this.idUsuario = value;
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
	 * Gets the value of the idDeposito property.
	 * 
	 */
	public int getIdDeposito() {
		return idDeposito;
	}

	/**
	 * Sets the value of the idDeposito property.
	 * 
	 */
	public void setIdDeposito(int value) {
		this.idDeposito = value;
	}

	/**
	 * Gets the value of the idDepRecepcion property.
	 * 
	 */
	public int getIdDepRecepcion() {
		return idDepRecepcion;
	}

	/**
	 * Sets the value of the idDepRecepcion property.
	 * 
	 */
	public void setIdDepRecepcion(int value) {
		this.idDepRecepcion = value;
	}

	/**
	 * Gets the value of the fechaRecep property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public String getFechaRecep() {
		return fechaRecep;
	}

	/**
	 * Sets the value of the fechaRecep property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFechaRecep(String value) {
		this.fechaRecep = value;
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
	 * Gets the value of the idProveedor property.
	 * 
	 */
	public long getIdProveedor() {
		return idProveedor;
	}

	/**
	 * Sets the value of the idProveedor property.
	 * 
	 */
	public void setIdProveedor(long value) {
		this.idProveedor = value;
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
