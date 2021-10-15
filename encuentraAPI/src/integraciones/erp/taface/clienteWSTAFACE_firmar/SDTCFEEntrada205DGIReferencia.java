
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.Referencia complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.Referencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="REFNroLinRef" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="REFIndGlobal" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="REFTpoDocRef" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="REFSerie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REFNroCFERef" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="REFRazonRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REFFechaCFEref" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.Referencia", propOrder = { "refNroLinRef", "refIndGlobal", "refTpoDocRef",
		"refSerie", "refNroCFERef", "refRazonRef", "refFechaCFEref" })
public class SDTCFEEntrada205DGIReferencia {

	@XmlElement(name = "REFNroLinRef")
	protected byte refNroLinRef;
	@XmlElement(name = "REFIndGlobal")
	protected byte refIndGlobal;
	@XmlElement(name = "REFTpoDocRef")
	protected short refTpoDocRef;
	@XmlElement(name = "REFSerie", required = true)
	protected String refSerie;
	@XmlElement(name = "REFNroCFERef")
	protected int refNroCFERef;
	@XmlElement(name = "REFRazonRef", required = true)
	protected String refRazonRef;
	@XmlElement(name = "REFFechaCFEref", required = true, nillable = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar refFechaCFEref;

	/**
	 * Gets the value of the refNroLinRef property.
	 * 
	 */
	public byte getREFNroLinRef() {
		return refNroLinRef;
	}

	/**
	 * Sets the value of the refNroLinRef property.
	 * 
	 */
	public void setREFNroLinRef(byte value) {
		this.refNroLinRef = value;
	}

	/**
	 * Gets the value of the refIndGlobal property.
	 * 
	 */
	public byte getREFIndGlobal() {
		return refIndGlobal;
	}

	/**
	 * Sets the value of the refIndGlobal property.
	 * 
	 */
	public void setREFIndGlobal(byte value) {
		this.refIndGlobal = value;
	}

	/**
	 * Gets the value of the refTpoDocRef property.
	 * 
	 */
	public short getREFTpoDocRef() {
		return refTpoDocRef;
	}

	/**
	 * Sets the value of the refTpoDocRef property.
	 * 
	 */
	public void setREFTpoDocRef(short value) {
		this.refTpoDocRef = value;
	}

	/**
	 * Gets the value of the refSerie property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getREFSerie() {
		return refSerie;
	}

	/**
	 * Sets the value of the refSerie property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setREFSerie(String value) {
		this.refSerie = value;
	}

	/**
	 * Gets the value of the refNroCFERef property.
	 * 
	 */
	public int getREFNroCFERef() {
		return refNroCFERef;
	}

	/**
	 * Sets the value of the refNroCFERef property.
	 * 
	 */
	public void setREFNroCFERef(int value) {
		this.refNroCFERef = value;
	}

	/**
	 * Gets the value of the refRazonRef property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getREFRazonRef() {
		return refRazonRef;
	}

	/**
	 * Sets the value of the refRazonRef property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setREFRazonRef(String value) {
		this.refRazonRef = value;
	}

	/**
	 * Gets the value of the refFechaCFEref property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getREFFechaCFEref() {
		return refFechaCFEref;
	}

	/**
	 * Sets the value of the refFechaCFEref property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setREFFechaCFEref(XMLGregorianCalendar value) {
		this.refFechaCFEref = value;
	}

}
