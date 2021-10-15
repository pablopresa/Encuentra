
package clientesVisual_Store.Std.clienteWSNAD;

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
 *         &lt;element name="IdArticulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdMoneda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdEmpresa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdTienda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="PrecioVta" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "idArticulo", "idCliente", "idMoneda", "idEmpresa", "idTienda",
		"precioVta" })
@XmlRootElement(name = "ConsArtPrecVta")
public class ConsArtPrecVta {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "IdArticulo")
	protected String idArticulo;
	@XmlElement(name = "IdCliente")
	protected long idCliente;
	@XmlElement(name = "IdMoneda")
	protected short idMoneda;
	@XmlElement(name = "IdEmpresa")
	protected long idEmpresa;
	@XmlElement(name = "IdTienda")
	protected short idTienda;
	@XmlElement(name = "PrecioVta", required = true)
	protected BigDecimal precioVta;

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
	 * Gets the value of the idArticulo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdArticulo() {
		return idArticulo;
	}

	/**
	 * Sets the value of the idArticulo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdArticulo(String value) {
		this.idArticulo = value;
	}

	/**
	 * Gets the value of the idCliente property.
	 * 
	 */
	public long getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the value of the idCliente property.
	 * 
	 */
	public void setIdCliente(long value) {
		this.idCliente = value;
	}

	/**
	 * Gets the value of the idMoneda property.
	 * 
	 */
	public short getIdMoneda() {
		return idMoneda;
	}

	/**
	 * Sets the value of the idMoneda property.
	 * 
	 */
	public void setIdMoneda(short value) {
		this.idMoneda = value;
	}

	/**
	 * Gets the value of the idEmpresa property.
	 * 
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * Sets the value of the idEmpresa property.
	 * 
	 */
	public void setIdEmpresa(long value) {
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
	 * Gets the value of the precioVta property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getPrecioVta() {
		return precioVta;
	}

	/**
	 * Sets the value of the precioVta property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setPrecioVta(BigDecimal value) {
		this.precioVta = value;
	}

}
