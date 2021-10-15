
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
 *         &lt;element name="GrabarOrdenVentaXmlResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = { "grabarOrdenVentaXmlResult", "mensError" })
@XmlRootElement(name = "GrabarOrdenVentaXmlResponse")
public class GrabarOrdenVentaXmlResponse {

	@XmlElement(name = "GrabarOrdenVentaXmlResult")
	protected int grabarOrdenVentaXmlResult;
	@XmlElement(name = "MensError")
	protected String mensError;

	/**
	 * Gets the value of the grabarOrdenVentaXmlResult property.
	 * 
	 */
	public int getGrabarOrdenVentaXmlResult() {
		return grabarOrdenVentaXmlResult;
	}

	/**
	 * Sets the value of the grabarOrdenVentaXmlResult property.
	 * 
	 */
	public void setGrabarOrdenVentaXmlResult(int value) {
		this.grabarOrdenVentaXmlResult = value;
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
