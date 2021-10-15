
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.MedioPago complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.MedioPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MPNroLinMP" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="MPCodMP" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="MPGlosaMP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MPOrdenMP" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="MPValorPago" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.MedioPago", propOrder = { "mpNroLinMP", "mpCodMP", "mpGlosaMP", "mpOrdenMP",
		"mpValorPago" })
public class SDTCFEEntrada205DGIMedioPago {

	@XmlElement(name = "MPNroLinMP")
	protected byte mpNroLinMP;
	@XmlElement(name = "MPCodMP")
	protected short mpCodMP;
	@XmlElement(name = "MPGlosaMP", required = true)
	protected String mpGlosaMP;
	@XmlElement(name = "MPOrdenMP")
	protected byte mpOrdenMP;
	@XmlElement(name = "MPValorPago")
	protected double mpValorPago;

	/**
	 * Gets the value of the mpNroLinMP property.
	 * 
	 */
	public byte getMPNroLinMP() {
		return mpNroLinMP;
	}

	/**
	 * Sets the value of the mpNroLinMP property.
	 * 
	 */
	public void setMPNroLinMP(byte value) {
		this.mpNroLinMP = value;
	}

	/**
	 * Gets the value of the mpCodMP property.
	 * 
	 */
	public short getMPCodMP() {
		return mpCodMP;
	}

	/**
	 * Sets the value of the mpCodMP property.
	 * 
	 */
	public void setMPCodMP(short value) {
		this.mpCodMP = value;
	}

	/**
	 * Gets the value of the mpGlosaMP property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMPGlosaMP() {
		return mpGlosaMP;
	}

	/**
	 * Sets the value of the mpGlosaMP property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMPGlosaMP(String value) {
		this.mpGlosaMP = value;
	}

	/**
	 * Gets the value of the mpOrdenMP property.
	 * 
	 */
	public byte getMPOrdenMP() {
		return mpOrdenMP;
	}

	/**
	 * Sets the value of the mpOrdenMP property.
	 * 
	 */
	public void setMPOrdenMP(byte value) {
		this.mpOrdenMP = value;
	}

	/**
	 * Gets the value of the mpValorPago property.
	 * 
	 */
	public double getMPValorPago() {
		return mpValorPago;
	}

	/**
	 * Sets the value of the mpValorPago property.
	 * 
	 */
	public void setMPValorPago(double value) {
		this.mpValorPago = value;
	}

}
