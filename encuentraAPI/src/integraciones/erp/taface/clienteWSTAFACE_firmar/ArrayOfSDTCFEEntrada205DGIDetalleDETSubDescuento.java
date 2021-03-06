
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfSDTCFE_Entrada_205.DGI.Detalle.DETSubDescuento complex
 * type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSDTCFE_Entrada_205.DGI.Detalle.DETSubDescuento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDTCFE_Entrada_205.DGI.Detalle.DETSubDescuento" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETSubDescuento" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSDTCFE_Entrada_205.DGI.Detalle.DETSubDescuento", propOrder = {
		"sdtcfeEntrada205DGIDetalleDETSubDescuento" })
public class ArrayOfSDTCFEEntrada205DGIDetalleDETSubDescuento {

	@XmlElement(name = "SDTCFE_Entrada_205.DGI.Detalle.DETSubDescuento")
	protected List<SDTCFEEntrada205DGIDetalleDETSubDescuento> sdtcfeEntrada205DGIDetalleDETSubDescuento;

	/**
	 * Gets the value of the sdtcfeEntrada205DGIDetalleDETSubDescuento property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the sdtcfeEntrada205DGIDetalleDETSubDescuento
	 * property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSDTCFEEntrada205DGIDetalleDETSubDescuento().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SDTCFEEntrada205DGIDetalleDETSubDescuento }
	 * 
	 * 
	 */
	public List<SDTCFEEntrada205DGIDetalleDETSubDescuento> getSDTCFEEntrada205DGIDetalleDETSubDescuento() {
		if (sdtcfeEntrada205DGIDetalleDETSubDescuento == null) {
			sdtcfeEntrada205DGIDetalleDETSubDescuento = new ArrayList<>();
		}
		return this.sdtcfeEntrada205DGIDetalleDETSubDescuento;
	}

}
