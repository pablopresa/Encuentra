
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.Detalle.DETCodItem complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.Detalle.DETCodItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DETTpoCod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DETCod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.Detalle.DETCodItem", propOrder = { "detTpoCod", "detCod" })
public class SDTCFEEntrada205DGIDetalleDETCodItem {

	@XmlElement(name = "DETTpoCod", required = true)
	protected String detTpoCod;
	@XmlElement(name = "DETCod", required = true)
	protected String detCod;

	/**
	 * Gets the value of the detTpoCod property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDETTpoCod() {
		return detTpoCod;
	}

	/**
	 * Sets the value of the detTpoCod property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDETTpoCod(String value) {
		this.detTpoCod = value;
	}

	/**
	 * Gets the value of the detCod property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDETCod() {
		return detCod;
	}

	/**
	 * Sets the value of the detCod property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDETCod(String value) {
		this.detCod = value;
	}

}
