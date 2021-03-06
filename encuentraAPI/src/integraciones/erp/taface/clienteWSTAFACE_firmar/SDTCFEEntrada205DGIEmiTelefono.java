
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.EmiTelefono complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.EmiTelefono">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EMITelefono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.EmiTelefono", propOrder = { "emiTelefono" })
public class SDTCFEEntrada205DGIEmiTelefono {

	@XmlElement(name = "EMITelefono", required = true)
	protected String emiTelefono;

	/**
	 * Gets the value of the emiTelefono property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMITelefono() {
		return emiTelefono;
	}

	/**
	 * Sets the value of the emiTelefono property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMITelefono(String value) {
		this.emiTelefono = value;
	}

}
