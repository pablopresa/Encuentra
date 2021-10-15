
package integraciones.erp.taface.clienteWSTAFACE;

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
 *         &lt;element name="Plicencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pempruc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Piddoctipocfe" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Piddocnro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Piddocserie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "plicencia", "pempruc", "piddoctipocfe", "piddocnro", "piddocserie" })
@XmlRootElement(name = "WSIntegracionFullDescargarPDF_0300.Execute")
public class WSIntegracionFullDescargarPDF0300Execute {

	@XmlElement(name = "Plicencia", required = true)
	protected String plicencia;
	@XmlElement(name = "Pempruc", required = true)
	protected String pempruc;
	@XmlElement(name = "Piddoctipocfe")
	protected short piddoctipocfe;
	@XmlElement(name = "Piddocnro")
	protected int piddocnro;
	@XmlElement(name = "Piddocserie", required = true)
	protected String piddocserie;

	/**
	 * Gets the value of the plicencia property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPlicencia() {
		return plicencia;
	}

	/**
	 * Sets the value of the plicencia property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlicencia(String value) {
		this.plicencia = value;
	}

	/**
	 * Gets the value of the pempruc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPempruc() {
		return pempruc;
	}

	/**
	 * Sets the value of the pempruc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPempruc(String value) {
		this.pempruc = value;
	}

	/**
	 * Gets the value of the piddoctipocfe property.
	 * 
	 */
	public short getPiddoctipocfe() {
		return piddoctipocfe;
	}

	/**
	 * Sets the value of the piddoctipocfe property.
	 * 
	 */
	public void setPiddoctipocfe(short value) {
		this.piddoctipocfe = value;
	}

	/**
	 * Gets the value of the piddocnro property.
	 * 
	 */
	public int getPiddocnro() {
		return piddocnro;
	}

	/**
	 * Sets the value of the piddocnro property.
	 * 
	 */
	public void setPiddocnro(int value) {
		this.piddocnro = value;
	}

	/**
	 * Gets the value of the piddocserie property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPiddocserie() {
		return piddocserie;
	}

	/**
	 * Sets the value of the piddocserie property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPiddocserie(String value) {
		this.piddocserie = value;
	}

}
