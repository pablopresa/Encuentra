
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfSDTCFE_Entrada_205.DGI.Detalle complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSDTCFE_Entrada_205.DGI.Detalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDTCFE_Entrada_205.DGI.Detalle" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSDTCFE_Entrada_205.DGI.Detalle", propOrder = { "sdtcfeEntrada205DGIDetalle" })
public class ArrayOfSDTCFEEntrada205DGIDetalle {

	@XmlElement(name = "SDTCFE_Entrada_205.DGI.Detalle")
	protected List<SDTCFEEntrada205DGIDetalle> sdtcfeEntrada205DGIDetalle;

	/**
	 * Gets the value of the sdtcfeEntrada205DGIDetalle property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the sdtcfeEntrada205DGIDetalle property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSDTCFEEntrada205DGIDetalle().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SDTCFEEntrada205DGIDetalle }
	 * 
	 * 
	 */
	public List<SDTCFEEntrada205DGIDetalle> getSDTCFEEntrada205DGIDetalle() {
		if (sdtcfeEntrada205DGIDetalle == null) {
			sdtcfeEntrada205DGIDetalle = new ArrayList<>();
		}
		return this.sdtcfeEntrada205DGIDetalle;
	}

}
