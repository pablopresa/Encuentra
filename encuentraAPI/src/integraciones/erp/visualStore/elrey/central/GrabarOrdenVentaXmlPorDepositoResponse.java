
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
 *         &lt;element name="GrabarOrdenVentaXmlPorDepositoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xmlArtDep" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "grabarOrdenVentaXmlPorDepositoResult", "mensError", "xmlArtDep" })
@XmlRootElement(name = "GrabarOrdenVentaXmlPorDepositoResponse")
public class GrabarOrdenVentaXmlPorDepositoResponse {

	@XmlElement(name = "GrabarOrdenVentaXmlPorDepositoResult")
	protected String grabarOrdenVentaXmlPorDepositoResult;
	@XmlElement(name = "MensError")
	protected String mensError;
	protected String xmlArtDep;

	/**
	 * Gets the value of the grabarOrdenVentaXmlPorDepositoResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGrabarOrdenVentaXmlPorDepositoResult() {
		return grabarOrdenVentaXmlPorDepositoResult;
	}

	/**
	 * Sets the value of the grabarOrdenVentaXmlPorDepositoResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGrabarOrdenVentaXmlPorDepositoResult(String value) {
		this.grabarOrdenVentaXmlPorDepositoResult = value;
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
	 * Gets the value of the xmlArtDep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlArtDep() {
		return xmlArtDep;
	}

	/**
	 * Sets the value of the xmlArtDep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlArtDep(String value) {
		this.xmlArtDep = value;
	}

}
