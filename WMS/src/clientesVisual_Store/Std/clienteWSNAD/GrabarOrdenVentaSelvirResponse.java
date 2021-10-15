
package clientesVisual_Store.Std.clienteWSNAD;

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
 *         &lt;element name="GrabarOrdenVentaSelvirResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
 *         &lt;element name="dsDocGrabados" minOccurs="0">
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "grabarOrdenVentaSelvirResult", "mensaje", "dsArtSinStk", "dsDocGrabados" })
@XmlRootElement(name = "GrabarOrdenVentaSelvirResponse")
public class GrabarOrdenVentaSelvirResponse {

	@XmlElement(name = "GrabarOrdenVentaSelvirResult")
	protected boolean grabarOrdenVentaSelvirResult;
	protected String mensaje;
	protected GrabarOrdenVentaSelvirResponse.DsArtSinStk dsArtSinStk;
	protected GrabarOrdenVentaSelvirResponse.DsDocGrabados dsDocGrabados;

	/**
	 * Gets the value of the grabarOrdenVentaSelvirResult property.
	 * 
	 */
	public boolean isGrabarOrdenVentaSelvirResult() {
		return grabarOrdenVentaSelvirResult;
	}

	/**
	 * Sets the value of the grabarOrdenVentaSelvirResult property.
	 * 
	 */
	public void setGrabarOrdenVentaSelvirResult(boolean value) {
		this.grabarOrdenVentaSelvirResult = value;
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
	 * @return possible object is
	 *         {@link GrabarOrdenVentaSelvirResponse.DsArtSinStk }
	 * 
	 */
	public GrabarOrdenVentaSelvirResponse.DsArtSinStk getDsArtSinStk() {
		return dsArtSinStk;
	}

	/**
	 * Sets the value of the dsArtSinStk property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link GrabarOrdenVentaSelvirResponse.DsArtSinStk }
	 * 
	 */
	public void setDsArtSinStk(GrabarOrdenVentaSelvirResponse.DsArtSinStk value) {
		this.dsArtSinStk = value;
	}

	/**
	 * Gets the value of the dsDocGrabados property.
	 * 
	 * @return possible object is
	 *         {@link GrabarOrdenVentaSelvirResponse.DsDocGrabados }
	 * 
	 */
	public GrabarOrdenVentaSelvirResponse.DsDocGrabados getDsDocGrabados() {
		return dsDocGrabados;
	}

	/**
	 * Sets the value of the dsDocGrabados property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link GrabarOrdenVentaSelvirResponse.DsDocGrabados }
	 * 
	 */
	public void setDsDocGrabados(GrabarOrdenVentaSelvirResponse.DsDocGrabados value) {
		this.dsDocGrabados = value;
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
	public static class DsDocGrabados {

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
