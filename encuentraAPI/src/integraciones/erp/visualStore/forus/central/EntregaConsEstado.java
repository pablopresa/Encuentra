
package integraciones.erp.visualStore.forus.central;

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
 *         &lt;element name="IdTienda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NroEmpresa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdDeposito" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NumeroDoc" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DescEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "idTienda", "nroEmpresa", "idDeposito", "numeroDoc", "descEstado" })
@XmlRootElement(name = "EntregaConsEstado")
public class EntregaConsEstado {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "IdTienda")
	protected short idTienda;
	@XmlElement(name = "NroEmpresa")
	protected long nroEmpresa;
	@XmlElement(name = "IdDeposito")
	protected short idDeposito;
	@XmlElement(name = "NumeroDoc")
	protected int numeroDoc;
	@XmlElement(name = "DescEstado")
	protected String descEstado;

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
	 * Gets the value of the idTienda property.
	 * 
	 */
	public short getIdTienda() {
		return idTienda;
	}

	/**
	 * Sets the value of the idTienda property.
	 * 
	 */
	public void setIdTienda(short value) {
		this.idTienda = value;
	}

	/**
	 * Gets the value of the nroEmpresa property.
	 * 
	 */
	public long getNroEmpresa() {
		return nroEmpresa;
	}

	/**
	 * Sets the value of the nroEmpresa property.
	 * 
	 */
	public void setNroEmpresa(long value) {
		this.nroEmpresa = value;
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

	/**
	 * Gets the value of the descEstado property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescEstado() {
		return descEstado;
	}

	/**
	 * Sets the value of the descEstado property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDescEstado(String value) {
		this.descEstado = value;
	}

}
