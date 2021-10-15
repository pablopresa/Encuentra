
package integraciones.erp.visualStore.elrey.ecommerce;

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
 *         &lt;element name="EntregaConsEstadoResult" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "entregaConsEstadoResult", "mensError", "descEstado" })
@XmlRootElement(name = "EntregaConsEstadoResponse")
public class EntregaConsEstadoResponse {

	@XmlElement(name = "EntregaConsEstadoResult")
	protected short entregaConsEstadoResult;
	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "DescEstado")
	protected String descEstado;

	/**
	 * Gets the value of the entregaConsEstadoResult property.
	 * 
	 */
	public short getEntregaConsEstadoResult() {
		return entregaConsEstadoResult;
	}

	/**
	 * Sets the value of the entregaConsEstadoResult property.
	 * 
	 */
	public void setEntregaConsEstadoResult(short value) {
		this.entregaConsEstadoResult = value;
	}

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
	 * Gets the value of the descEstado property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescEstado() {
		return descEstado;
	}

	/**
	 * Sets the value of the descEstado property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescEstado(String value) {
		this.descEstado = value;
	}

}
