
package integraciones.erp.taface.clienteWSTAFACE;

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
 *         &lt;element name="Pdfserializado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Perrorreturn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Perrormessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "pdfserializado", "perrorreturn", "perrormessage" })
@XmlRootElement(name = "WSIntegracionFullDescargarPDF_0300.ExecuteResponse")
public class WSIntegracionFullDescargarPDF0300ExecuteResponse {

	@XmlElement(name = "Pdfserializado", required = true)
	protected String pdfserializado;
	@XmlElement(name = "Perrorreturn")
	protected boolean perrorreturn;
	@XmlElement(name = "Perrormessage", required = true)
	protected String perrormessage;

	/**
	 * Gets the value of the pdfserializado property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPdfserializado() {
		return pdfserializado;
	}

	/**
	 * Sets the value of the pdfserializado property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPdfserializado(String value) {
		this.pdfserializado = value;
	}

	/**
	 * Gets the value of the perrorreturn property.
	 * 
	 */
	public boolean isPerrorreturn() {
		return perrorreturn;
	}

	/**
	 * Sets the value of the perrorreturn property.
	 * 
	 */
	public void setPerrorreturn(boolean value) {
		this.perrorreturn = value;
	}

	/**
	 * Gets the value of the perrormessage property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPerrormessage() {
		return perrormessage;
	}

	/**
	 * Sets the value of the perrormessage property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPerrormessage(String value) {
		this.perrormessage = value;
	}

}
