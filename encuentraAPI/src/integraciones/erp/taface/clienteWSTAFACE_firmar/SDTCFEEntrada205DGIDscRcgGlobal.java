
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.DscRcgGlobal complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.DscRcgGlobal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DRGNroLinDR" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="DRGTpoMovDR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DRGTpoDR" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="DRGCodDR" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="DRGGlosaDR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DRGValorDR" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DRGIndFactDR" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.DscRcgGlobal", propOrder = { "drgNroLinDR", "drgTpoMovDR", "drgTpoDR",
		"drgCodDR", "drgGlosaDR", "drgValorDR", "drgIndFactDR" })
public class SDTCFEEntrada205DGIDscRcgGlobal {

	@XmlElement(name = "DRGNroLinDR")
	protected byte drgNroLinDR;
	@XmlElement(name = "DRGTpoMovDR", required = true)
	protected String drgTpoMovDR;
	@XmlElement(name = "DRGTpoDR")
	protected byte drgTpoDR;
	@XmlElement(name = "DRGCodDR")
	protected short drgCodDR;
	@XmlElement(name = "DRGGlosaDR", required = true)
	protected String drgGlosaDR;
	@XmlElement(name = "DRGValorDR")
	protected double drgValorDR;
	@XmlElement(name = "DRGIndFactDR")
	protected byte drgIndFactDR;

	/**
	 * Gets the value of the drgNroLinDR property.
	 * 
	 */
	public byte getDRGNroLinDR() {
		return drgNroLinDR;
	}

	/**
	 * Sets the value of the drgNroLinDR property.
	 * 
	 */
	public void setDRGNroLinDR(byte value) {
		this.drgNroLinDR = value;
	}

	/**
	 * Gets the value of the drgTpoMovDR property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDRGTpoMovDR() {
		return drgTpoMovDR;
	}

	/**
	 * Sets the value of the drgTpoMovDR property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDRGTpoMovDR(String value) {
		this.drgTpoMovDR = value;
	}

	/**
	 * Gets the value of the drgTpoDR property.
	 * 
	 */
	public byte getDRGTpoDR() {
		return drgTpoDR;
	}

	/**
	 * Sets the value of the drgTpoDR property.
	 * 
	 */
	public void setDRGTpoDR(byte value) {
		this.drgTpoDR = value;
	}

	/**
	 * Gets the value of the drgCodDR property.
	 * 
	 */
	public short getDRGCodDR() {
		return drgCodDR;
	}

	/**
	 * Sets the value of the drgCodDR property.
	 * 
	 */
	public void setDRGCodDR(short value) {
		this.drgCodDR = value;
	}

	/**
	 * Gets the value of the drgGlosaDR property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDRGGlosaDR() {
		return drgGlosaDR;
	}

	/**
	 * Sets the value of the drgGlosaDR property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDRGGlosaDR(String value) {
		this.drgGlosaDR = value;
	}

	/**
	 * Gets the value of the drgValorDR property.
	 * 
	 */
	public double getDRGValorDR() {
		return drgValorDR;
	}

	/**
	 * Sets the value of the drgValorDR property.
	 * 
	 */
	public void setDRGValorDR(double value) {
		this.drgValorDR = value;
	}

	/**
	 * Gets the value of the drgIndFactDR property.
	 * 
	 */
	public byte getDRGIndFactDR() {
		return drgIndFactDR;
	}

	/**
	 * Sets the value of the drgIndFactDR property.
	 * 
	 */
	public void setDRGIndFactDR(byte value) {
		this.drgIndFactDR = value;
	}

}
