
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
 *         &lt;element name="Depositos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BaseDatos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xmlCarrito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "idEmpresa", "depositos", "baseDatos", "xmlCarrito" })
@XmlRootElement(name = "ConsDisponibilidad")
public class ConsDisponibilidad {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "IdEmpresa")
	protected int idEmpresa;
	@XmlElement(name = "Depositos")
	protected String depositos;
	@XmlElement(name = "BaseDatos")
	protected String baseDatos;
	protected String xmlCarrito;

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
	 * Gets the value of the depositos property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDepositos() {
		return depositos;
	}

	/**
	 * Sets the value of the depositos property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDepositos(String value) {
		this.depositos = value;
	}

	/**
	 * Gets the value of the baseDatos property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBaseDatos() {
		return baseDatos;
	}

	/**
	 * Sets the value of the baseDatos property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBaseDatos(String value) {
		this.baseDatos = value;
	}

	/**
	 * Gets the value of the xmlCarrito property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXmlCarrito() {
		return xmlCarrito;
	}

	/**
	 * Sets the value of the xmlCarrito property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXmlCarrito(String value) {
		this.xmlCarrito = value;
	}

}
