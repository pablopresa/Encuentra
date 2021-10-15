
package integraciones.erp.visualStore.elrey.comercioElectronico;

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
 *         &lt;element name="MensError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdEmpresa" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IdDeposito" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Serie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroDoc" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "idEmpresa", "idDeposito", "idTipoDocumento", "serie", "numeroDoc" })
@XmlRootElement(name = "ConsultarVentaCFE")
public class ConsultarVentaCFE {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "IdEmpresa")
	protected int idEmpresa;
	@XmlElement(name = "IdDeposito")
	protected short idDeposito;
	@XmlElement(name = "IdTipoDocumento")
	protected String idTipoDocumento;
	@XmlElement(name = "Serie")
	protected String serie;
	@XmlElement(name = "NumeroDoc")
	protected int numeroDoc;

	/**
	 * Gets the value of the mensError property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMensError() {
		return mensError;
	}

	/**
	 * Sets the value of the mensError property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMensError(String value) {
		this.mensError = value;
	}

	/**
	 * Gets the value of the idEmpresa property.
	 * 
	 */
	public int getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * Sets the value of the idEmpresa property.
	 * 
	 */
	public void setIdEmpresa(int value) {
		this.idEmpresa = value;
	}

	/**
	 * Gets the value of the idDeposito property.
	 * 
	 */
	public short getIdDeposito() {
		return idDeposito;
	}

	/**
	 * Sets the value of the idDeposito property.
	 * 
	 */
	public void setIdDeposito(short value) {
		this.idDeposito = value;
	}

	/**
	 * Gets the value of the idTipoDocumento property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * Sets the value of the idTipoDocumento property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdTipoDocumento(String value) {
		this.idTipoDocumento = value;
	}

	/**
	 * Gets the value of the serie property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * Sets the value of the serie property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSerie(String value) {
		this.serie = value;
	}

	/**
	 * Gets the value of the numeroDoc property.
	 * 
	 */
	public int getNumeroDoc() {
		return numeroDoc;
	}

	/**
	 * Sets the value of the numeroDoc property.
	 * 
	 */
	public void setNumeroDoc(int value) {
		this.numeroDoc = value;
	}

}
