
package integraciones.erp.visualStore.elrey.central;

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
 *         &lt;element name="SaldoPuntoxCIExtResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "", propOrder = { "saldoPuntoxCIExtResult", "mensError", "saldoPuntos" })
@XmlRootElement(name = "SaldoPuntoxCIExtResponse")
public class SaldoPuntoxCIExtResponse {

	@XmlElement(name = "SaldoPuntoxCIExtResult")
	protected boolean saldoPuntoxCIExtResult;
	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "SaldoPuntos", required = true)
	protected BigDecimal saldoPuntos;

	/**
	 * Gets the value of the saldoPuntoxCIExtResult property.
	 * 
	 */
	public boolean isSaldoPuntoxCIExtResult() {
		return saldoPuntoxCIExtResult;
	}

	/**
	 * Sets the value of the saldoPuntoxCIExtResult property.
	 * 
	 */
	public void setSaldoPuntoxCIExtResult(boolean value) {
		this.saldoPuntoxCIExtResult = value;
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
