
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
 *         &lt;element name="psIdSesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaDoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nroFactProv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaArticulos" type="{http://tempuri.org/}ArrayOfUtilDesglose" minOccurs="0"/>
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "psIdSesion", "codCliente", "fechaDoc", "nroFactProv", "listaArticulos",
		"comentario" })
@XmlRootElement(name = "CrearRecepcion")
public class CrearRecepcion {

	protected String psIdSesion;
	protected String codCliente;
	protected String fechaDoc;
	protected String nroFactProv;
	protected ArrayOfUtilDesglose listaArticulos;
	protected String comentario;

	/**
	 * Gets the value of the psIdSesion property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPsIdSesion() {
		return psIdSesion;
	}

	/**
	 * Sets the value of the psIdSesion property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPsIdSesion(String value) {
		this.psIdSesion = value;
	}

	/**
	 * Gets the value of the codCliente property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodCliente() {
		return codCliente;
	}

	/**
	 * Sets the value of the codCliente property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodCliente(String value) {
		this.codCliente = value;
	}

	/**
	 * Gets the value of the fechaDoc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFechaDoc() {
		return fechaDoc;
	}

	/**
	 * Sets the value of the fechaDoc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFechaDoc(String value) {
		this.fechaDoc = value;
	}

	/**
	 * Gets the value of the nroFactProv property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNroFactProv() {
		return nroFactProv;
	}

	/**
	 * Sets the value of the nroFactProv property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNroFactProv(String value) {
		this.nroFactProv = value;
	}

	/**
	 * Gets the value of the listaArticulos property.
	 * 
	 * @return possible object is {@link ArrayOfUtilDesglose }
	 * 
	 */
	public ArrayOfUtilDesglose getListaArticulos() {
		return listaArticulos;
	}

	/**
	 * Sets the value of the listaArticulos property.
	 * 
	 * @param value
	 *            allowed object is {@link ArrayOfUtilDesglose }
	 * 
	 */
	public void setListaArticulos(ArrayOfUtilDesglose value) {
		this.listaArticulos = value;
	}

	/**
	 * Gets the value of the comentario property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Sets the value of the comentario property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setComentario(String value) {
		this.comentario = value;
	}

}
