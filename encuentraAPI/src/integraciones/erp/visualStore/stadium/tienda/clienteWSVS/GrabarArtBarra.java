
package integraciones.erp.visualStore.stadium.tienda.clienteWSVS;

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
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdTienda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="NroEmpresa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdArticulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NroBarra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Eliminar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensaje", "idTienda", "nroEmpresa", "idArticulo", "nroBarra", "eliminar" })
@XmlRootElement(name = "GrabarArtBarra")
public class GrabarArtBarra {

	@XmlElement(name = "Mensaje")
	protected String mensaje;
	@XmlElement(name = "IdTienda")
	protected short idTienda;
	@XmlElement(name = "NroEmpresa")
	protected long nroEmpresa;
	@XmlElement(name = "IdArticulo")
	protected String idArticulo;
	@XmlElement(name = "NroBarra")
	protected String nroBarra;
	@XmlElement(name = "Eliminar")
	protected boolean eliminar;

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
	 * Gets the value of the idArticulo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdArticulo() {
		return idArticulo;
	}

	/**
	 * Sets the value of the idArticulo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdArticulo(String value) {
		this.idArticulo = value;
	}

	/**
	 * Gets the value of the nroBarra property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNroBarra() {
		return nroBarra;
	}

	/**
	 * Sets the value of the nroBarra property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNroBarra(String value) {
		this.nroBarra = value;
	}

	/**
	 * Gets the value of the eliminar property.
	 * 
	 */
	public boolean isEliminar() {
		return eliminar;
	}

	/**
	 * Sets the value of the eliminar property.
	 * 
	 */
	public void setEliminar(boolean value) {
		this.eliminar = value;
	}

}
