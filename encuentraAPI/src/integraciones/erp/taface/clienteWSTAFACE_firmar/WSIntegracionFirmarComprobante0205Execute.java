
package integraciones.erp.taface.clienteWSTAFACE_firmar;

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
 *         &lt;element name="Pempruc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Psucid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Pcajaid" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Pxmlentrada" type="{TAFACE}XMLENTRADA"/>
 *         &lt;element name="Pcaenroautorizacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Pcaeserie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pcaenroreservado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "pempruc", "psucid", "pcajaid", "pxmlentrada", "pcaenroautorizacion", "pcaeserie",
		"pcaenroreservado" })
@XmlRootElement(name = "WSIntegracionFirmarComprobante_0205.Execute")
public class WSIntegracionFirmarComprobante0205Execute {

	@XmlElement(name = "Pempruc", required = true)
	protected String pempruc;
	@XmlElement(name = "Psucid")
	protected int psucid;
	@XmlElement(name = "Pcajaid")
	protected short pcajaid;
	@XmlElement(name = "Pxmlentrada", required = true)
	protected XMLENTRADA pxmlentrada;
	@XmlElement(name = "Pcaenroautorizacion")
	protected long pcaenroautorizacion;
	@XmlElement(name = "Pcaeserie", required = true)
	protected String pcaeserie;
	@XmlElement(name = "Pcaenroreservado")
	protected int pcaenroreservado;

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
	 * Gets the value of the psucid property.
	 * 
	 */
	public int getPsucid() {
		return psucid;
	}

	/**
	 * Sets the value of the psucid property.
	 * 
	 */
	public void setPsucid(int value) {
		this.psucid = value;
	}

	/**
	 * Gets the value of the pcajaid property.
	 * 
	 */
	public short getPcajaid() {
		return pcajaid;
	}

	/**
	 * Sets the value of the pcajaid property.
	 * 
	 */
	public void setPcajaid(short value) {
		this.pcajaid = value;
	}

	/**
	 * Gets the value of the pxmlentrada property.
	 * 
	 * @return possible object is {@link XMLENTRADA }
	 * 
	 */
	public XMLENTRADA getPxmlentrada() {
		return pxmlentrada;
	}

	/**
	 * Sets the value of the pxmlentrada property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLENTRADA }
	 * 
	 */
	public void setPxmlentrada(XMLENTRADA value) {
		this.pxmlentrada = value;
	}

	/**
	 * Gets the value of the pcaenroautorizacion property.
	 * 
	 */
	public long getPcaenroautorizacion() {
		return pcaenroautorizacion;
	}

	/**
	 * Sets the value of the pcaenroautorizacion property.
	 * 
	 */
	public void setPcaenroautorizacion(long value) {
		this.pcaenroautorizacion = value;
	}

	/**
	 * Gets the value of the pcaeserie property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPcaeserie() {
		return pcaeserie;
	}

	/**
	 * Sets the value of the pcaeserie property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPcaeserie(String value) {
		this.pcaeserie = value;
	}

	/**
	 * Gets the value of the pcaenroreservado property.
	 * 
	 */
	public int getPcaenroreservado() {
		return pcaenroreservado;
	}

	/**
	 * Sets the value of the pcaenroreservado property.
	 * 
	 */
	public void setPcaenroreservado(int value) {
		this.pcaenroreservado = value;
	}

}
