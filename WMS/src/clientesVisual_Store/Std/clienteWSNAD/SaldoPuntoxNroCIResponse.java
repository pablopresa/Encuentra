
package clientesVisual_Store.Std.clienteWSNAD;

import java.math.BigDecimal;
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
 *         &lt;element name="SaldoPuntoxNroCIResult" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPuntos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "saldoPuntoxNroCIResult", "mensError", "saldoPuntos" })
@XmlRootElement(name = "SaldoPuntoxNroCIResponse")
public class SaldoPuntoxNroCIResponse {

	@XmlElement(name = "SaldoPuntoxNroCIResult", required = true)
	protected BigDecimal saldoPuntoxNroCIResult;
	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "SaldoPuntos", required = true)
	protected BigDecimal saldoPuntos;

	/**
	 * Gets the value of the saldoPuntoxNroCIResult property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getSaldoPuntoxNroCIResult() {
		return saldoPuntoxNroCIResult;
	}

	/**
	 * Sets the value of the saldoPuntoxNroCIResult property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setSaldoPuntoxNroCIResult(BigDecimal value) {
		this.saldoPuntoxNroCIResult = value;
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
	 * Gets the value of the saldoPuntos property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getSaldoPuntos() {
		return saldoPuntos;
	}

	/**
	 * Sets the value of the saldoPuntos property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setSaldoPuntos(BigDecimal value) {
		this.saldoPuntos = value;
	}

}
