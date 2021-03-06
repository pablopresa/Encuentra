
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for XMLRESPUESTA complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="XMLRESPUESTA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ERRORMSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WARNINGMSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FIRMADOOK" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="FIRMADOFCHHORA" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CAENA" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CAENROINICIAL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CAENROFINAL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CAEVENCIMIENTO" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CAESERIE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CAENRO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CODSEGURIDAD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="URLPARAVERIFICARTEXTO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="URLPARAVERIFICARQR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RESOLUCIONIVA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CORRESPONDESOBRE" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLRESPUESTA", propOrder = { "errormsg", "warningmsg", "firmadook", "firmadofchhora", "caena",
		"caenroinicial", "caenrofinal", "caevencimiento", "caeserie", "caenro", "codseguridad", "urlparaverificartexto",
		"urlparaverificarqr", "resolucioniva", "correspondesobre" })
public class XMLRESPUESTA {

	@XmlElement(name = "ERRORMSG", required = true)
	protected String errormsg;
	@XmlElement(name = "WARNINGMSG", required = true)
	protected String warningmsg;
	@XmlElement(name = "FIRMADOOK")
	protected short firmadook;
	@XmlElement(name = "FIRMADOFCHHORA", required = true, nillable = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar firmadofchhora;
	@XmlElement(name = "CAENA")
	protected long caena;
	@XmlElement(name = "CAENROINICIAL")
	protected int caenroinicial;
	@XmlElement(name = "CAENROFINAL")
	protected int caenrofinal;
	@XmlElement(name = "CAEVENCIMIENTO", required = true, nillable = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar caevencimiento;
	@XmlElement(name = "CAESERIE", required = true)
	protected String caeserie;
	@XmlElement(name = "CAENRO")
	protected int caenro;
	@XmlElement(name = "CODSEGURIDAD", required = true)
	protected String codseguridad;
	@XmlElement(name = "URLPARAVERIFICARTEXTO", required = true)
	protected String urlparaverificartexto;
	@XmlElement(name = "URLPARAVERIFICARQR", required = true)
	protected String urlparaverificarqr;
	@XmlElement(name = "RESOLUCIONIVA", required = true)
	protected String resolucioniva;
	@XmlElement(name = "CORRESPONDESOBRE")
	protected short correspondesobre;

	/**
	 * Gets the value of the errormsg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getERRORMSG() {
		return errormsg;
	}

	/**
	 * Sets the value of the errormsg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setERRORMSG(String value) {
		this.errormsg = value;
	}

	/**
	 * Gets the value of the warningmsg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWARNINGMSG() {
		return warningmsg;
	}

	/**
	 * Sets the value of the warningmsg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWARNINGMSG(String value) {
		this.warningmsg = value;
	}

	/**
	 * Gets the value of the firmadook property.
	 * 
	 */
	public short getFIRMADOOK() {
		return firmadook;
	}

	/**
	 * Sets the value of the firmadook property.
	 * 
	 */
	public void setFIRMADOOK(short value) {
		this.firmadook = value;
	}

	/**
	 * Gets the value of the firmadofchhora property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getFIRMADOFCHHORA() {
		return firmadofchhora;
	}

	/**
	 * Sets the value of the firmadofchhora property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setFIRMADOFCHHORA(XMLGregorianCalendar value) {
		this.firmadofchhora = value;
	}

	/**
	 * Gets the value of the caena property.
	 * 
	 */
	public long getCAENA() {
		return caena;
	}

	/**
	 * Sets the value of the caena property.
	 * 
	 */
	public void setCAENA(long value) {
		this.caena = value;
	}

	/**
	 * Gets the value of the caenroinicial property.
	 * 
	 */
	public int getCAENROINICIAL() {
		return caenroinicial;
	}

	/**
	 * Sets the value of the caenroinicial property.
	 * 
	 */
	public void setCAENROINICIAL(int value) {
		this.caenroinicial = value;
	}

	/**
	 * Gets the value of the caenrofinal property.
	 * 
	 */
	public int getCAENROFINAL() {
		return caenrofinal;
	}

	/**
	 * Sets the value of the caenrofinal property.
	 * 
	 */
	public void setCAENROFINAL(int value) {
		this.caenrofinal = value;
	}

	/**
	 * Gets the value of the caevencimiento property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCAEVENCIMIENTO() {
		return caevencimiento;
	}

	/**
	 * Sets the value of the caevencimiento property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCAEVENCIMIENTO(XMLGregorianCalendar value) {
		this.caevencimiento = value;
	}

	/**
	 * Gets the value of the caeserie property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCAESERIE() {
		return caeserie;
	}

	/**
	 * Sets the value of the caeserie property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCAESERIE(String value) {
		this.caeserie = value;
	}

	/**
	 * Gets the value of the caenro property.
	 * 
	 */
	public int getCAENRO() {
		return caenro;
	}

	/**
	 * Sets the value of the caenro property.
	 * 
	 */
	public void setCAENRO(int value) {
		this.caenro = value;
	}

	/**
	 * Gets the value of the codseguridad property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCODSEGURIDAD() {
		return codseguridad;
	}

	/**
	 * Sets the value of the codseguridad property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCODSEGURIDAD(String value) {
		this.codseguridad = value;
	}

	/**
	 * Gets the value of the urlparaverificartexto property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getURLPARAVERIFICARTEXTO() {
		return urlparaverificartexto;
	}

	/**
	 * Sets the value of the urlparaverificartexto property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setURLPARAVERIFICARTEXTO(String value) {
		this.urlparaverificartexto = value;
	}

	/**
	 * Gets the value of the urlparaverificarqr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getURLPARAVERIFICARQR() {
		return urlparaverificarqr;
	}

	/**
	 * Sets the value of the urlparaverificarqr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setURLPARAVERIFICARQR(String value) {
		this.urlparaverificarqr = value;
	}

	/**
	 * Gets the value of the resolucioniva property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRESOLUCIONIVA() {
		return resolucioniva;
	}

	/**
	 * Sets the value of the resolucioniva property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRESOLUCIONIVA(String value) {
		this.resolucioniva = value;
	}

	/**
	 * Gets the value of the correspondesobre property.
	 * 
	 */
	public short getCORRESPONDESOBRE() {
		return correspondesobre;
	}

	/**
	 * Sets the value of the correspondesobre property.
	 * 
	 */
	public void setCORRESPONDESOBRE(short value) {
		this.correspondesobre = value;
	}

}
