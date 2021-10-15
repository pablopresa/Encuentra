
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
 *         &lt;element name="EntregaTerminarResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "entregaTerminarResult", "mensError" })
@XmlRootElement(name = "EntregaTerminarResponse")
public class EntregaTerminarResponse {

	@XmlElement(name = "EntregaTerminarResult")
	protected boolean entregaTerminarResult;
	@XmlElement(name = "MensError")
	protected String mensError;

	/**
	 * Gets the value of the entregaTerminarResult property.
	 * 
	 */
	public boolean isEntregaTerminarResult() {
		return entregaTerminarResult;
	}

	/**
	 * Sets the value of the entregaTerminarResult property.
	 * 
	 */
	public void setEntregaTerminarResult(boolean value) {
		this.entregaTerminarResult = value;
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

}
