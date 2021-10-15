
package clientesVisual_Store.elRey;

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
 *         &lt;element name="IdArticulos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdDepositos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdMoneda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IdEmpresa" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IdTienda" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="ListaPrecio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "mensError", "idArticulos", "idDepositos", "idCliente", "idMoneda", "idEmpresa",
		"idTienda", "listaPrecio" })
@XmlRootElement(name = "ConsArtPrecVtaStk")
public class ConsArtPrecVtaStk {

	@XmlElement(name = "MensError")
	protected String mensError;
	@XmlElement(name = "IdArticulos")
	protected String idArticulos;
	@XmlElement(name = "IdDepositos")
	protected String idDepositos;
	@XmlElement(name = "IdCliente")
	protected long idCliente;
	@XmlElement(name = "IdMoneda")
	protected short idMoneda;
	@XmlElement(name = "IdEmpresa")
	protected long idEmpresa;
	@XmlElement(name = "IdTienda")
	protected short idTienda;
	@XmlElement(name = "ListaPrecio")
	protected boolean listaPrecio;

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
	 * Gets the value of the idArticulos property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdArticulos() {
		return idArticulos;
	}

	/**
	 * Sets the value of the idArticulos property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdArticulos(String value) {
		this.idArticulos = value;
	}

	/**
	 * Gets the value of the idDepositos property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIdDepositos() {
		return idDepositos;
	}

	/**
	 * Sets the value of the idDepositos property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIdDepositos(String value) {
		this.idDepositos = value;
	}

	/**
	 * Gets the value of the idCliente property.
	 * 
	 */
	public long getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the value of the idCliente property.
	 * 
	 */
	public void setIdCliente(long value) {
		this.idCliente = value;
	}

	/**
	 * Gets the value of the idMoneda property.
	 * 
	 */
	public short getIdMoneda() {
		return idMoneda;
	}

	/**
	 * Sets the value of the idMoneda property.
	 * 
	 */
	public void setIdMoneda(short value) {
		this.idMoneda = value;
	}

	/**
	 * Gets the value of the idEmpresa property.
	 * 
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * Sets the value of the idEmpresa property.
	 * 
	 */
	public void setIdEmpresa(long value) {
		this.idEmpresa = value;
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
	 * Gets the value of the listaPrecio property.
	 * 
	 */
	public boolean isListaPrecio() {
		return listaPrecio;
	}

	/**
	 * Sets the value of the listaPrecio property.
	 * 
	 */
	public void setListaPrecio(boolean value) {
		this.listaPrecio = value;
	}

}
