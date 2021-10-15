
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfSDTCFE_Entrada_205.DGI.DscRcgGlobal complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSDTCFE_Entrada_205.DGI.DscRcgGlobal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDTCFE_Entrada_205.DGI.DscRcgGlobal" type="{TAFACE}SDTCFE_Entrada_205.DGI.DscRcgGlobal" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSDTCFE_Entrada_205.DGI.DscRcgGlobal", propOrder = { "sdtcfeEntrada205DGIDscRcgGlobal" })
public class ArrayOfSDTCFEEntrada205DGIDscRcgGlobal {

	@XmlElement(name = "SDTCFE_Entrada_205.DGI.DscRcgGlobal")
	protected List<SDTCFEEntrada205DGIDscRcgGlobal> sdtcfeEntrada205DGIDscRcgGlobal;

	/**
	 * Gets the value of the sdtcfeEntrada205DGIDscRcgGlobal property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the sdtcfeEntrada205DGIDscRcgGlobal property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSDTCFEEntrada205DGIDscRcgGlobal().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SDTCFEEntrada205DGIDscRcgGlobal }
	 * 
	 * 
	 */
	public List<SDTCFEEntrada205DGIDscRcgGlobal> getSDTCFEEntrada205DGIDscRcgGlobal() {
		if (sdtcfeEntrada205DGIDscRcgGlobal == null) {
			sdtcfeEntrada205DGIDscRcgGlobal = new ArrayList<>();
		}
		return this.sdtcfeEntrada205DGIDscRcgGlobal;
	}

}
