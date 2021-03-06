
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.SubTotalInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.SubTotalInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SUBNroSTI" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="SUBGlosaSTI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SUBOrdenSTI" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="SUBValSubtotSTI" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.SubTotalInfo", propOrder = { "subNroSTI", "subGlosaSTI", "subOrdenSTI",
		"subValSubtotSTI" })
public class SDTCFEEntrada205DGISubTotalInfo {

	@XmlElement(name = "SUBNroSTI")
	protected byte subNroSTI;
	@XmlElement(name = "SUBGlosaSTI", required = true)
	protected String subGlosaSTI;
	@XmlElement(name = "SUBOrdenSTI")
	protected byte subOrdenSTI;
	@XmlElement(name = "SUBValSubtotSTI")
	protected double subValSubtotSTI;

	/**
	 * Gets the value of the subNroSTI property.
	 * 
	 */
	public byte getSUBNroSTI() {
		return subNroSTI;
	}

	/**
	 * Sets the value of the subNroSTI property.
	 * 
	 */
	public void setSUBNroSTI(byte value) {
		this.subNroSTI = value;
	}

	/**
	 * Gets the value of the subGlosaSTI property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSUBGlosaSTI() {
		return subGlosaSTI;
	}

	/**
	 * Sets the value of the subGlosaSTI property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSUBGlosaSTI(String value) {
		this.subGlosaSTI = value;
	}

	/**
	 * Gets the value of the subOrdenSTI property.
	 * 
	 */
	public byte getSUBOrdenSTI() {
		return subOrdenSTI;
	}

	/**
	 * Sets the value of the subOrdenSTI property.
	 * 
	 */
	public void setSUBOrdenSTI(byte value) {
		this.subOrdenSTI = value;
	}

	/**
	 * Gets the value of the subValSubtotSTI property.
	 * 
	 */
	public double getSUBValSubtotSTI() {
		return subValSubtotSTI;
	}

	/**
	 * Sets the value of the subValSubtotSTI property.
	 * 
	 */
	public void setSUBValSubtotSTI(double value) {
		this.subValSubtotSTI = value;
	}

}
