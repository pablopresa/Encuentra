
package integraciones.erp.visualStore.elrey.comercioElectronico;

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
 *         &lt;element name="EntregaPrepararyTerminarResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "", propOrder = { "entregaPrepararyTerminarResult", "mensError" })
@XmlRootElement(name = "EntregaPrepararyTerminarResponse")
public class EntregaPrepararyTerminarResponse {

	@XmlElement(name = "EntregaPrepararyTerminarResult")
	protected boolean entregaPrepararyTerminarResult;
	@XmlElement(name = "MensError")
	protected String mensError;

	/**
	 * Gets the value of the entregaPrepararyTerminarResult property.
	 * 
	 */
	public boolean isEntregaPrepararyTerminarResult() {
		return entregaPrepararyTerminarResult;
	}

	/**
	 * Sets the value of the entregaPrepararyTerminarResult property.
	 * 
	 */
	public void setEntregaPrepararyTerminarResult(boolean value) {
		this.entregaPrepararyTerminarResult = value;
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
