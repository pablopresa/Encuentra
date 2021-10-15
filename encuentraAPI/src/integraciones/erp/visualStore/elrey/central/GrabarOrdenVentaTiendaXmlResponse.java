
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
 *         &lt;element name="GrabarOrdenVentaTiendaXmlResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = { "grabarOrdenVentaTiendaXmlResult", "mensError" })
@XmlRootElement(name = "GrabarOrdenVentaTiendaXmlResponse")
public class GrabarOrdenVentaTiendaXmlResponse {

	@XmlElement(name = "GrabarOrdenVentaTiendaXmlResult")
	protected int grabarOrdenVentaTiendaXmlResult;
	@XmlElement(name = "MensError")
	protected String mensError;

	/**
	 * Gets the value of the grabarOrdenVentaTiendaXmlResult property.
	 * 
	 */
	public int getGrabarOrdenVentaTiendaXmlResult() {
		return grabarOrdenVentaTiendaXmlResult;
	}

	/**
	 * Sets the value of the grabarOrdenVentaTiendaXmlResult property.
	 * 
	 */
	public void setGrabarOrdenVentaTiendaXmlResult(int value) {
		this.grabarOrdenVentaTiendaXmlResult = value;
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
