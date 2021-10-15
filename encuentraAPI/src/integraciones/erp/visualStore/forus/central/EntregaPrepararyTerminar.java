
package integraciones.erp.visualStore.forus.central;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
 *         &lt;element name="IdDepEnt" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NumeroDoc" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="GenerarNuevaConDif" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="xmlDetalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroEntregaPorDif" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "nroUsuario", "idEquipo", "idTienda", "nroEmpresa", "idDepEnt",
		"numeroDoc", "generarNuevaConDif", "xmlDetalle", "nroEntregaPorDif" })
@XmlRootElement(name = "EntregaPrepararyTerminar")
public class EntregaPrepararyTerminar {

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
	@XmlElement(name = "IdDepEnt")
	protected short idDepEnt;
	@XmlElement(name = "NumeroDoc")
	protected int numeroDoc;
	@XmlElement(name = "GenerarNuevaConDif")
	protected boolean generarNuevaConDif;
	protected String xmlDetalle;
	@XmlElement(name = "NroEntregaPorDif")
	protected int nroEntregaPorDif;

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
	 * Gets the value of the idDepEnt property.
	 * 
	 */
	public short getIdDepEnt() {
		return idDepEnt;
	}

	/**
	 * Sets the value of the idDepEnt property.
	 * 
	 */
	public void setIdDepEnt(short value) {
		this.idDepEnt = value;
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
	 * Gets the value of the generarNuevaConDif property.
	 * 
	 */
	public boolean isGenerarNuevaConDif() {
		return generarNuevaConDif;
	}

	/**
	 * Sets the value of the generarNuevaConDif property.
	 * 
	 */
	public void setGenerarNuevaConDif(boolean value) {
		this.generarNuevaConDif = value;
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

	/**
	 * Gets the value of the nroEntregaPorDif property.
	 * 
	 */
	public int getNroEntregaPorDif() {
		return nroEntregaPorDif;
	}

	/**
	 * Sets the value of the nroEntregaPorDif property.
	 * 
	 */
	public void setNroEntregaPorDif(int value) {
		this.nroEntregaPorDif = value;
	}

}
