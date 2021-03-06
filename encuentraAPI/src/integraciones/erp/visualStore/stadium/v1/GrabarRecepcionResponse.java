
package integraciones.erp.visualStore.stadium.v1;

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
 *         &lt;element name="GrabarRecepcionResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = { "grabarRecepcionResult", "mensaje" })
@XmlRootElement(name = "GrabarRecepcionResponse")
public class GrabarRecepcionResponse {

	@XmlElement(name = "GrabarRecepcionResult")
	protected int grabarRecepcionResult;
	@XmlElement(name = "Mensaje")
	protected String mensaje;

	/**
	 * Gets the value of the grabarRecepcionResult property.
	 * 
	 */
	public int getGrabarRecepcionResult() {
		return grabarRecepcionResult;
	}

	/**
	 * Sets the value of the grabarRecepcionResult property.
	 * 
	 */
	public void setGrabarRecepcionResult(int value) {
		this.grabarRecepcionResult = value;
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
