
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfSDTCFE_Entrada_205.DGI.Detalle.DETRetencPercep complex
 * type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSDTCFE_Entrada_205.DGI.Detalle.DETRetencPercep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDTCFE_Entrada_205.DGI.Detalle.DETRetencPercep" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETRetencPercep" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSDTCFE_Entrada_205.DGI.Detalle.DETRetencPercep", propOrder = {
		"sdtcfeEntrada205DGIDetalleDETRetencPercep" })
public class ArrayOfSDTCFEEntrada205DGIDetalleDETRetencPercep {

	@XmlElement(name = "SDTCFE_Entrada_205.DGI.Detalle.DETRetencPercep")
	protected List<SDTCFEEntrada205DGIDetalleDETRetencPercep> sdtcfeEntrada205DGIDetalleDETRetencPercep;

	/**
	 * Gets the value of the sdtcfeEntrada205DGIDetalleDETRetencPercep property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the sdtcfeEntrada205DGIDetalleDETRetencPercep
	 * property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSDTCFEEntrada205DGIDetalleDETRetencPercep().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SDTCFEEntrada205DGIDetalleDETRetencPercep }
	 * 
	 * 
	 */
	public List<SDTCFEEntrada205DGIDetalleDETRetencPercep> getSDTCFEEntrada205DGIDetalleDETRetencPercep() {
		if (sdtcfeEntrada205DGIDetalleDETRetencPercep == null) {
			sdtcfeEntrada205DGIDetalleDETRetencPercep = new ArrayList<>();
		}
		return this.sdtcfeEntrada205DGIDetalleDETRetencPercep;
	}

}
