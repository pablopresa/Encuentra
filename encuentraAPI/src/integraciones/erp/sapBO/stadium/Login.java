
package integraciones.erp.sapBO.stadium;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="dbServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="licenseServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "dbServer", "dbName", "dbType", "compUser", "compPwd", "language", "licenseServer" })
@XmlRootElement(name = "Login")
public class Login {

	protected String dbServer;
	protected String dbName;
	protected String dbType;
	protected String compUser;
	protected String compPwd;
	protected String language;
	protected String licenseServer;

	/**
	 * Gets the value of the dbServer property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDbServer() {
		return dbServer;
	}

	/**
	 * Sets the value of the dbServer property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDbServer(String value) {
		this.dbServer = value;
	}

	/**
	 * Gets the value of the dbName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * Sets the value of the dbName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDbName(String value) {
		this.dbName = value;
	}

	/**
	 * Gets the value of the dbType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDbType() {
		return dbType;
	}

	/**
	 * Sets the value of the dbType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDbType(String value) {
		this.dbType = value;
	}

	/**
	 * Gets the value of the compUser property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCompUser() {
		return compUser;
	}

	/**
	 * Sets the value of the compUser property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCompUser(String value) {
		this.compUser = value;
	}

	/**
	 * Gets the value of the compPwd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCompPwd() {
		return compPwd;
	}

	/**
	 * Sets the value of the compPwd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCompPwd(String value) {
		this.compPwd = value;
	}

	/**
	 * Gets the value of the language property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the value of the language property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLanguage(String value) {
		this.language = value;
	}

	/**
	 * Gets the value of the licenseServer property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLicenseServer() {
		return licenseServer;
	}

	/**
	 * Sets the value of the licenseServer property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLicenseServer(String value) {
		this.licenseServer = value;
	}

}
