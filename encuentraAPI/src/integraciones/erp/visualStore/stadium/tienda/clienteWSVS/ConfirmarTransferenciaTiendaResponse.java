
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
 *         &lt;element name="ConfirmarTransferenciaTiendaResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "confirmarTransferenciaTiendaResult", "mensaje" })
@XmlRootElement(name = "ConfirmarTransferenciaTiendaResponse")
public class ConfirmarTransferenciaTiendaResponse {

	@XmlElement(name = "ConfirmarTransferenciaTiendaResult")
	protected boolean confirmarTransferenciaTiendaResult;
	@XmlElement(name = "Mensaje")
	protected String mensaje;

	/**
	 * Gets the value of the confirmarTransferenciaTiendaResult property.
	 * 
	 */
	public boolean isConfirmarTransferenciaTiendaResult() {
		return confirmarTransferenciaTiendaResult;
	}

	/**
	 * Sets the value of the confirmarTransferenciaTiendaResult property.
	 * 
	 */
	public void setConfirmarTransferenciaTiendaResult(boolean value) {
		this.confirmarTransferenciaTiendaResult = value;
	}

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

}
