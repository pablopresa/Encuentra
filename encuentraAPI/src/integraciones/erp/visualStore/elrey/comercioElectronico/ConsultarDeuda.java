
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
 *         &lt;element name="Rut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdEmpresa" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Login" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "rut", "idEmpresa", "login" })
@XmlRootElement(name = "ConsultarDeuda")
public class ConsultarDeuda {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "Rut")
	protected String rut;
	@XmlElement(name = "IdEmpresa")
	protected int idEmpresa;
	@XmlElement(name = "Login")
	protected String login;

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
	 * Gets the value of the rut property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * Sets the value of the rut property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRut(String value) {
		this.rut = value;
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
	 * Gets the value of the login property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the value of the login property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLogin(String value) {
		this.login = value;
	}

}
