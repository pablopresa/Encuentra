
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfSDTCFE_Entrada_205.DGI.Referencia complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSDTCFE_Entrada_205.DGI.Referencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDTCFE_Entrada_205.DGI.Referencia" type="{TAFACE}SDTCFE_Entrada_205.DGI.Referencia" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSDTCFE_Entrada_205.DGI.Referencia", propOrder = { "sdtcfeEntrada205DGIReferencia" })
public class ArrayOfSDTCFEEntrada205DGIReferencia {

	@XmlElement(name = "SDTCFE_Entrada_205.DGI.Referencia")
	protected List<SDTCFEEntrada205DGIReferencia> sdtcfeEntrada205DGIReferencia;

	/**
	 * Gets the value of the sdtcfeEntrada205DGIReferencia property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the sdtcfeEntrada205DGIReferencia property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSDTCFEEntrada205DGIReferencia().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SDTCFEEntrada205DGIReferencia }
	 * 
	 * 
	 */
	public List<SDTCFEEntrada205DGIReferencia> getSDTCFEEntrada205DGIReferencia() {
		if (sdtcfeEntrada205DGIReferencia == null) {
			sdtcfeEntrada205DGIReferencia = new ArrayList<>();
		}
		return this.sdtcfeEntrada205DGIReferencia;
	}

}
