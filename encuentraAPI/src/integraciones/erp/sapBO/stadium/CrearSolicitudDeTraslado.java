
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
 *         &lt;element name="nroSolTraslado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaArticulos" type="{http://tempuri.org/}ArrayOfUtilDesglose" minOccurs="0"/>
 *         &lt;element name="almacenDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prioridad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="motivo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "psIdSesion", "codCliente", "nroSolTraslado", "listaArticulos", "almacenDestino",
		"comentario", "prioridad", "motivo" })
@XmlRootElement(name = "CrearSolicitudDeTraslado")
public class CrearSolicitudDeTraslado {

	protected String psIdSesion;
	protected String codCliente;
	protected String nroSolTraslado;
	protected ArrayOfUtilDesglose listaArticulos;
	protected String almacenDestino;
	protected String comentario;
	protected int prioridad;
	protected int motivo;

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
	 * Gets the value of the nroSolTraslado property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNroSolTraslado() {
		return nroSolTraslado;
	}

	/**
	 * Sets the value of the nroSolTraslado property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNroSolTraslado(String value) {
		this.nroSolTraslado = value;
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
	 * Gets the value of the almacenDestino property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAlmacenDestino() {
		return almacenDestino;
	}

	/**
	 * Sets the value of the almacenDestino property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAlmacenDestino(String value) {
		this.almacenDestino = value;
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

	/**
	 * Gets the value of the prioridad property.
	 * 
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * Sets the value of the prioridad property.
	 * 
	 */
	public void setPrioridad(int value) {
		this.prioridad = value;
	}

	/**
	 * Gets the value of the motivo property.
	 * 
	 */
	public int getMotivo() {
		return motivo;
	}

	/**
	 * Sets the value of the motivo property.
	 * 
	 */
	public void setMotivo(int value) {
		this.motivo = value;
	}

}
