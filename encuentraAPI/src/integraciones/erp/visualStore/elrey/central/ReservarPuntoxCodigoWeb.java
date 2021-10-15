
package integraciones.erp.visualStore.elrey.central;

import java.math.BigDecimal;
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
 *         &lt;element name="IdEmpresa" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IdTienda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdDeposito" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="CodigoWeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PuntosReservar" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IdCarrito" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "idEmpresa", "idTienda", "idDeposito", "codigoWeb", "puntosReservar",
		"idCarrito" })
@XmlRootElement(name = "ReservarPuntoxCodigoWeb")
public class ReservarPuntoxCodigoWeb {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "IdEmpresa")
	protected int idEmpresa;
	@XmlElement(name = "IdTienda")
	protected short idTienda;
	@XmlElement(name = "IdDeposito")
	protected short idDeposito;
	@XmlElement(name = "CodigoWeb")
	protected String codigoWeb;
	@XmlElement(name = "PuntosReservar", required = true)
	protected BigDecimal puntosReservar;
	@XmlElement(name = "IdCarrito")
	protected long idCarrito;

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
	 * Gets the value of the idEmpresa property.
	 * 
	 */
	public int getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * Sets the value of the idEmpresa property.
	 * 
	 */
	public void setIdEmpresa(int value) {
		this.idEmpresa = value;
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
	 * Gets the value of the idDeposito property.
	 * 
	 */
	public short getIdDeposito() {
		return idDeposito;
	}

	/**
	 * Sets the value of the idDeposito property.
	 * 
	 */
	public void setIdDeposito(short value) {
		this.idDeposito = value;
	}

	/**
	 * Gets the value of the codigoWeb property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodigoWeb() {
		return codigoWeb;
	}

	/**
	 * Sets the value of the codigoWeb property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodigoWeb(String value) {
		this.codigoWeb = value;
	}

	/**
	 * Gets the value of the puntosReservar property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getPuntosReservar() {
		return puntosReservar;
	}

	/**
	 * Sets the value of the puntosReservar property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setPuntosReservar(BigDecimal value) {
		this.puntosReservar = value;
	}

	/**
	 * Gets the value of the idCarrito property.
	 * 
	 */
	public long getIdCarrito() {
		return idCarrito;
	}

	/**
	 * Sets the value of the idCarrito property.
	 * 
	 */
	public void setIdCarrito(long value) {
		this.idCarrito = value;
	}

}
