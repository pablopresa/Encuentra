
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.TOTRetencPercep complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.TOTRetencPercep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TOTCodRet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TOTValRetPerc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.TOTRetencPercep", propOrder = { "totCodRet", "totValRetPerc" })
public class SDTCFEEntrada205DGITOTRetencPercep {

	@XmlElement(name = "TOTCodRet", required = true)
	protected String totCodRet;
	@XmlElement(name = "TOTValRetPerc")
	protected double totValRetPerc;

	/**
	 * Gets the value of the totCodRet property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTOTCodRet() {
		return totCodRet;
	}

	/**
	 * Sets the value of the totCodRet property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTOTCodRet(String value) {
		this.totCodRet = value;
	}

	/**
	 * Gets the value of the totValRetPerc property.
	 * 
	 */
	public double getTOTValRetPerc() {
		return totValRetPerc;
	}

	/**
	 * Sets the value of the totValRetPerc property.
	 * 
	 */
	public void setTOTValRetPerc(double value) {
		this.totValRetPerc = value;
	}

}
