
package integraciones.erp.visualStore.elrey.central;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
 *         &lt;element name="GrabarOrdenVentaResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dsArtSinStk" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EntregaDemorada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "grabarOrdenVentaResult", "mensaje", "dsArtSinStk", "entregaDemorada" })
@XmlRootElement(name = "GrabarOrdenVentaResponse")
public class GrabarOrdenVentaResponse {

	@XmlElement(name = "GrabarOrdenVentaResult")
	protected int grabarOrdenVentaResult;
	protected String mensaje;
	protected GrabarOrdenVentaResponse.DsArtSinStk dsArtSinStk;
	@XmlElement(name = "EntregaDemorada")
	protected boolean entregaDemorada;

	/**
	 * Gets the value of the grabarOrdenVentaResult property.
	 * 
	 */
	public int getGrabarOrdenVentaResult() {
		return grabarOrdenVentaResult;
	}

	/**
	 * Sets the value of the grabarOrdenVentaResult property.
	 * 
	 */
	public void setGrabarOrdenVentaResult(int value) {
		this.grabarOrdenVentaResult = value;
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

	/**
	 * Gets the value of the dsArtSinStk property.
	 * 
	 * @return possible object is {@link GrabarOrdenVentaResponse.DsArtSinStk }
	 * 
	 */
	public GrabarOrdenVentaResponse.DsArtSinStk getDsArtSinStk() {
		return dsArtSinStk;
	}

	/**
	 * Sets the value of the dsArtSinStk property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link GrabarOrdenVentaResponse.DsArtSinStk }
	 * 
	 */
	public void setDsArtSinStk(GrabarOrdenVentaResponse.DsArtSinStk value) {
		this.dsArtSinStk = value;
	}

	/**
	 * Gets the value of the entregaDemorada property.
	 * 
	 */
	public boolean isEntregaDemorada() {
		return entregaDemorada;
	}

	/**
	 * Sets the value of the entregaDemorada property.
	 * 
	 */
	public void setEntregaDemorada(boolean value) {
		this.entregaDemorada = value;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;any/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "any" })
	public static class DsArtSinStk {

		@XmlAnyElement(lax = true)
		protected Object any;

		/**
		 * Gets the value of the any property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getAny() {
			return any;
		}

		/**
		 * Sets the value of the any property.
		 * 
		 * @param value
		 *            allowed object is {@link Object }
		 * 
		 */
		public void setAny(Object value) {
			this.any = value;
		}

	}

}
