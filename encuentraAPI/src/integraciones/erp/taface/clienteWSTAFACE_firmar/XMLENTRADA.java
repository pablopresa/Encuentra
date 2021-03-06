
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for XMLENTRADA complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="XMLENTRADA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DGI" type="{TAFACE}SDTCFE_Entrada_205.DGI"/>
 *         &lt;element name="DATOSCONTINGENCIA" type="{TAFACE}SDTCFE_Entrada_205.DATOSCONTINGENCIA"/>
 *         &lt;element name="DATOSADICIONALES" type="{TAFACE}SDTCFE_Entrada_205.DATOSADICIONALES"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLENTRADA", propOrder = { "dgi", "datoscontingencia", "datosadicionales" })
public class XMLENTRADA {

	@XmlElement(name = "DGI", required = true)
	protected SDTCFEEntrada205DGI dgi;
	@XmlElement(name = "DATOSCONTINGENCIA", required = true)
	protected SDTCFEEntrada205DATOSCONTINGENCIA datoscontingencia;
	@XmlElement(name = "DATOSADICIONALES", required = true)
	protected SDTCFEEntrada205DATOSADICIONALES datosadicionales;

	/**
	 * Gets the value of the dgi property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DGI }
	 * 
	 */
	public SDTCFEEntrada205DGI getDGI() {
		return dgi;
	}

	/**
	 * Sets the value of the dgi property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DGI }
	 * 
	 */
	public void setDGI(SDTCFEEntrada205DGI value) {
		this.dgi = value;
	}

	/**
	 * Gets the value of the datoscontingencia property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DATOSCONTINGENCIA }
	 * 
	 */
	public SDTCFEEntrada205DATOSCONTINGENCIA getDATOSCONTINGENCIA() {
		return datoscontingencia;
	}

	/**
	 * Sets the value of the datoscontingencia property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DATOSCONTINGENCIA }
	 * 
	 */
	public void setDATOSCONTINGENCIA(SDTCFEEntrada205DATOSCONTINGENCIA value) {
		this.datoscontingencia = value;
	}

	/**
	 * Gets the value of the datosadicionales property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DATOSADICIONALES }
	 * 
	 */
	public SDTCFEEntrada205DATOSADICIONALES getDATOSADICIONALES() {
		return datosadicionales;
	}

	/**
	 * Sets the value of the datosadicionales property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DATOSADICIONALES }
	 * 
	 */
	public void setDATOSADICIONALES(SDTCFEEntrada205DATOSADICIONALES value) {
		this.datosadicionales = value;
	}

}
