
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
 *         &lt;element name="EntregaPrepararResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "", propOrder = { "entregaPrepararResult", "mensError", "nroEntregaPorDif" })
@XmlRootElement(name = "EntregaPrepararResponse")
public class EntregaPrepararResponse {

	@XmlElement(name = "EntregaPrepararResult")
	protected boolean entregaPrepararResult;
	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "NroEntregaPorDif")
	protected int nroEntregaPorDif;

	/**
	 * Gets the value of the entregaPrepararResult property.
	 * 
	 */
	public boolean isEntregaPrepararResult() {
		return entregaPrepararResult;
	}

	/**
	 * Sets the value of the entregaPrepararResult property.
	 * 
	 */
	public void setEntregaPrepararResult(boolean value) {
		this.entregaPrepararResult = value;
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
