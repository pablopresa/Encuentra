
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.Detalle.DETSubRecargo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.Detalle.DETSubRecargo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DETRecargoTipo" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="DETRecargoVal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.Detalle.DETSubRecargo", propOrder = { "detRecargoTipo", "detRecargoVal" })
public class SDTCFEEntrada205DGIDetalleDETSubRecargo {

	@XmlElement(name = "DETRecargoTipo")
	protected byte detRecargoTipo;
	@XmlElement(name = "DETRecargoVal")
	protected double detRecargoVal;

	/**
	 * Gets the value of the detRecargoTipo property.
	 * 
	 */
	public byte getDETRecargoTipo() {
		return detRecargoTipo;
	}

	/**
	 * Sets the value of the detRecargoTipo property.
	 * 
	 */
	public void setDETRecargoTipo(byte value) {
		this.detRecargoTipo = value;
	}

	/**
	 * Gets the value of the detRecargoVal property.
	 * 
	 */
	public double getDETRecargoVal() {
		return detRecargoVal;
	}

	/**
	 * Sets the value of the detRecargoVal property.
	 * 
	 */
	public void setDETRecargoVal(double value) {
		this.detRecargoVal = value;
	}

}
