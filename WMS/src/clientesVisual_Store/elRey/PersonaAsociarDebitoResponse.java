
package clientesVisual_Store.elRey;

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
 *         &lt;element name="PersonaAsociarDebitoResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "", propOrder = { "personaAsociarDebitoResult", "mensError" })
@XmlRootElement(name = "PersonaAsociarDebitoResponse")
public class PersonaAsociarDebitoResponse {

	@XmlElement(name = "PersonaAsociarDebitoResult")
	protected boolean personaAsociarDebitoResult;
	@XmlElement(name = "MensError")
	protected String mensError;

	/**
	 * Gets the value of the personaAsociarDebitoResult property.
	 * 
	 */
	public boolean isPersonaAsociarDebitoResult() {
		return personaAsociarDebitoResult;
	}

	/**
	 * Sets the value of the personaAsociarDebitoResult property.
	 * 
	 */
	public void setPersonaAsociarDebitoResult(boolean value) {
		this.personaAsociarDebitoResult = value;
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
