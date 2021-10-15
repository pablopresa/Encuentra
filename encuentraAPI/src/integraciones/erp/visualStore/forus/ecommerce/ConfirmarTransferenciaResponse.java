
package integraciones.erp.visualStore.forus.ecommerce;

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
 *         &lt;element name="ConfirmarTransferenciaResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "confirmarTransferenciaResult", "mensaje" })
@XmlRootElement(name = "ConfirmarTransferenciaResponse")
public class ConfirmarTransferenciaResponse {

	@XmlElement(name = "ConfirmarTransferenciaResult")
	protected boolean confirmarTransferenciaResult;
	@XmlElement(name = "Mensaje")
	protected String mensaje;

	/**
	 * Gets the value of the confirmarTransferenciaResult property.
	 * 
	 */
	public boolean isConfirmarTransferenciaResult() {
		return confirmarTransferenciaResult;
	}

	/**
	 * Sets the value of the confirmarTransferenciaResult property.
	 * 
	 */
	public void setConfirmarTransferenciaResult(boolean value) {
		this.confirmarTransferenciaResult = value;
	}

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

}
