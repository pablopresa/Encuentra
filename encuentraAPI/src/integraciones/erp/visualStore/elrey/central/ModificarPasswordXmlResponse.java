
package integraciones.erp.visualStore.elrey.central;

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
 *         &lt;element name="ModificarPasswordXmlResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "modificarPasswordXmlResult", "mensaje" })
@XmlRootElement(name = "ModificarPasswordXmlResponse")
public class ModificarPasswordXmlResponse {

	@XmlElement(name = "ModificarPasswordXmlResult")
	protected boolean modificarPasswordXmlResult;
	protected String mensaje;

	/**
	 * Gets the value of the modificarPasswordXmlResult property.
	 * 
	 */
	public boolean isModificarPasswordXmlResult() {
		return modificarPasswordXmlResult;
	}

	/**
	 * Sets the value of the modificarPasswordXmlResult property.
	 * 
	 */
	public void setModificarPasswordXmlResult(boolean value) {
		this.modificarPasswordXmlResult = value;
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
