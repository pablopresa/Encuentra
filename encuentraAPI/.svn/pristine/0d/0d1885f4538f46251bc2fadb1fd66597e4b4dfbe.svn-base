
package integraciones.erp.visualStore.elrey.comercioElectronico;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

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
 *         &lt;element name="ConsultaCantCompradaResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Saldo" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Vencimiento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "consultaCantCompradaResult", "mensError", "saldo", "vencimiento" })
@XmlRootElement(name = "ConsultaCantCompradaResponse")
public class ConsultaCantCompradaResponse {

	@XmlElement(name = "ConsultaCantCompradaResult")
	protected boolean consultaCantCompradaResult;
	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "Saldo", required = true)
	protected BigDecimal saldo;
	@XmlElement(name = "Vencimiento", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar vencimiento;

	/**
	 * Gets the value of the consultaCantCompradaResult property.
	 * 
	 */
	public boolean isConsultaCantCompradaResult() {
		return consultaCantCompradaResult;
	}

	/**
	 * Sets the value of the consultaCantCompradaResult property.
	 * 
	 */
	public void setConsultaCantCompradaResult(boolean value) {
		this.consultaCantCompradaResult = value;
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
	 * Gets the value of the saldo property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * Sets the value of the saldo property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setSaldo(BigDecimal value) {
		this.saldo = value;
	}

	/**
	 * Gets the value of the vencimiento property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getVencimiento() {
		return vencimiento;
	}

	/**
	 * Sets the value of the vencimiento property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setVencimiento(XMLGregorianCalendar value) {
		this.vencimiento = value;
	}

}
