
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
 *         &lt;element name="GrabarTransferenciaResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = { "grabarTransferenciaResult", "mensaje" })
@XmlRootElement(name = "GrabarTransferenciaResponse")
public class GrabarTransferenciaResponse {

	@XmlElement(name = "GrabarTransferenciaResult")
	protected int grabarTransferenciaResult;
	@XmlElement(name = "Mensaje")
	protected String mensaje;

	/**
	 * Gets the value of the grabarTransferenciaResult property.
	 * 
	 */
	public int getGrabarTransferenciaResult() {
		return grabarTransferenciaResult;
	}

	/**
	 * Sets the value of the grabarTransferenciaResult property.
	 * 
	 */
	public void setGrabarTransferenciaResult(int value) {
		this.grabarTransferenciaResult = value;
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
