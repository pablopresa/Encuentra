
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
 *         &lt;element name="IdEquipo" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdEmpresa" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IdCarrito" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "idEquipo", "idEmpresa", "idCarrito" })
@XmlRootElement(name = "CancelarNroVentaWeb")
public class CancelarNroVentaWeb {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "IdEquipo")
	protected short idEquipo;
	@XmlElement(name = "IdEmpresa")
	protected int idEmpresa;
	@XmlElement(name = "IdCarrito")
	protected long idCarrito;

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
	 * Gets the value of the idEquipo property.
	 * 
	 */
	public short getIdEquipo() {
		return idEquipo;
	}

	/**
	 * Sets the value of the idEquipo property.
	 * 
	 */
	public void setIdEquipo(short value) {
		this.idEquipo = value;
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
	 * Gets the value of the idCarrito property.
	 * 
	 */
	public long getIdCarrito() {
		return idCarrito;
	}

	/**
	 * Sets the value of the idCarrito property.
	 * 
	 */
	public void setIdCarrito(long value) {
		this.idCarrito = value;
	}

}
