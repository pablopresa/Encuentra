
package clientesVisual_Store.elRey;

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
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambio" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Arbitraje" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IdMoneda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "tipoCambio", "arbitraje", "idMoneda", "fecha" })
@XmlRootElement(name = "ConsTipoCambio")
public class ConsTipoCambio {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "TipoCambio", required = true)
	protected BigDecimal tipoCambio;
	@XmlElement(name = "Arbitraje", required = true)
	protected BigDecimal arbitraje;
	@XmlElement(name = "IdMoneda")
	protected short idMoneda;
	@XmlElement(name = "Fecha", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar fecha;

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
	 * Gets the value of the tipoCambio property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * Sets the value of the tipoCambio property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setTipoCambio(BigDecimal value) {
		this.tipoCambio = value;
	}

	/**
	 * Gets the value of the arbitraje property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getArbitraje() {
		return arbitraje;
	}

	/**
	 * Sets the value of the arbitraje property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setArbitraje(BigDecimal value) {
		this.arbitraje = value;
	}

	/**
	 * Gets the value of the idMoneda property.
	 * 
	 */
	public short getIdMoneda() {
		return idMoneda;
	}

	/**
	 * Sets the value of the idMoneda property.
	 * 
	 */
	public void setIdMoneda(short value) {
		this.idMoneda = value;
	}

	/**
	 * Gets the value of the fecha property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFecha() {
		return fecha;
	}

	/**
	 * Sets the value of the fecha property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFecha(XMLGregorianCalendar value) {
		this.fecha = value;
	}

}
