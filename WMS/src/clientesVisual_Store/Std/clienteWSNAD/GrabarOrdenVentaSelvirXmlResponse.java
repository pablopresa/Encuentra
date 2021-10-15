
package clientesVisual_Store.Std.clienteWSNAD;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrabarOrdenVentaSelvirXmlResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xmlArtSinStk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xmlDocGrabados" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "grabarOrdenVentaSelvirXmlResult", "mensaje", "xmlArtSinStk", "xmlDocGrabados" })
@XmlRootElement(name = "GrabarOrdenVentaSelvirXmlResponse")
public class GrabarOrdenVentaSelvirXmlResponse {

	@XmlElement(name = "GrabarOrdenVentaSelvirXmlResult")
	protected boolean grabarOrdenVentaSelvirXmlResult;
	protected String mensaje;
	protected String xmlArtSinStk;
	protected String xmlDocGrabados;

	/**
	 * Gets the value of the grabarOrdenVentaSelvirXmlResult property.
	 * 
	 */
	public boolean isGrabarOrdenVentaSelvirXmlResult() {
		return grabarOrdenVentaSelvirXmlResult;
	}

	/**
	 * Sets the value of the grabarOrdenVentaSelvirXmlResult property.
	 * 
	 */
	public void setGrabarOrdenVentaSelvirXmlResult(boolean value) {
		this.grabarOrdenVentaSelvirXmlResult = value;
	}

	/**
	 * Gets the value of the mensaje property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the value of the mensaje property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMensaje(String value) {
		this.mensaje = value;
	}

	/**
	 * Gets the value of the xmlArtSinStk property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlArtSinStk() {
		return xmlArtSinStk;
	}

	/**
	 * Sets the value of the xmlArtSinStk property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlArtSinStk(String value) {
		this.xmlArtSinStk = value;
	}

	/**
	 * Gets the value of the xmlDocGrabados property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlDocGrabados() {
		return xmlDocGrabados;
	}

	/**
	 * Sets the value of the xmlDocGrabados property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlDocGrabados(String value) {
		this.xmlDocGrabados = value;
	}

}
