
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
 *         &lt;element name="GrabarVentaDistIntResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = { "grabarVentaDistIntResult", "mensError", "xmlArtDep" })
@XmlRootElement(name = "GrabarVentaDistIntResponse")
public class GrabarVentaDistIntResponse {

	@XmlElement(name = "GrabarVentaDistIntResult")
	protected int grabarVentaDistIntResult;
	@XmlElement(name = "MensError")
	protected String mensError;
	protected String xmlArtDep;

	/**
	 * Gets the value of the grabarVentaDistIntResult property.
	 * 
	 */
	public int getGrabarVentaDistIntResult() {
		return grabarVentaDistIntResult;
	}

	/**
	 * Sets the value of the grabarVentaDistIntResult property.
	 * 
	 */
	public void setGrabarVentaDistIntResult(int value) {
		this.grabarVentaDistIntResult = value;
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
