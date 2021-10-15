
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
 *         &lt;element name="Numero" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CIOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "numero", "ciOrigen", "mensaje" })
@XmlRootElement(name = "ConsultaClientexNroCI")
public class ConsultaClientexNroCI {

	@XmlElement(name = "Numero")
	protected long numero;
	@XmlElement(name = "CIOrigen")
	protected String ciOrigen;
	protected String mensaje;

	/**
	 * Gets the value of the numero property.
	 * 
	 */
	public long getNumero() {
		return numero;
	}

	/**
	 * Sets the value of the numero property.
	 * 
	 */
	public void setNumero(long value) {
		this.numero = value;
	}

	/**
	 * Gets the value of the ciOrigen property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCIOrigen() {
		return ciOrigen;
	}

	/**
	 * Sets the value of the ciOrigen property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCIOrigen(String value) {
		this.ciOrigen = value;
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

}
