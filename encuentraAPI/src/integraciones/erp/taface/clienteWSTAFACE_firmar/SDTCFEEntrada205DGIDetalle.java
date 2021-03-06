
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI.Detalle complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI.Detalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DETNroLinDet" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="DETCodItems">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DETCodItem" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETCodItem" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DETIndFact" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="DETIndAgenteResp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DETNomItem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DETDscItem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DETCantidad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DETUniMed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DETPrecioUnitario" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DETDescuentoPct" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DETDescuentoMonto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DETSubDescuentos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DETSubDescuento" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETSubDescuento" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DETRecargoPct" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DETRecargoMnt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DETSubRecargos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DETSubRecargo" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETSubRecargo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DETRetencPerceps">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DETRetencPercep" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETRetencPercep" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DETMontoItem" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI.Detalle", propOrder = { "detNroLinDet", "detCodItems", "detIndFact",
		"detIndAgenteResp", "detNomItem", "detDscItem", "detCantidad", "detUniMed", "detPrecioUnitario",
		"detDescuentoPct", "detDescuentoMonto", "detSubDescuentos", "detRecargoPct", "detRecargoMnt", "detSubRecargos",
		"detRetencPerceps", "detMontoItem" })
public class SDTCFEEntrada205DGIDetalle {

	@XmlElement(name = "DETNroLinDet")
	protected short detNroLinDet;
	@XmlElement(name = "DETCodItems", required = true)
	protected SDTCFEEntrada205DGIDetalle.DETCodItems detCodItems;
	@XmlElement(name = "DETIndFact")
	protected byte detIndFact;
	@XmlElement(name = "DETIndAgenteResp", required = true)
	protected String detIndAgenteResp;
	@XmlElement(name = "DETNomItem", required = true)
	protected String detNomItem;
	@XmlElement(name = "DETDscItem", required = true)
	protected String detDscItem;
	@XmlElement(name = "DETCantidad")
	protected double detCantidad;
	@XmlElement(name = "DETUniMed", required = true)
	protected String detUniMed;
	@XmlElement(name = "DETPrecioUnitario")
	protected double detPrecioUnitario;
	@XmlElement(name = "DETDescuentoPct")
	protected double detDescuentoPct;
	@XmlElement(name = "DETDescuentoMonto")
	protected double detDescuentoMonto;
	@XmlElement(name = "DETSubDescuentos", required = true)
	protected SDTCFEEntrada205DGIDetalle.DETSubDescuentos detSubDescuentos;
	@XmlElement(name = "DETRecargoPct")
	protected double detRecargoPct;
	@XmlElement(name = "DETRecargoMnt")
	protected double detRecargoMnt;
	@XmlElement(name = "DETSubRecargos", required = true)
	protected SDTCFEEntrada205DGIDetalle.DETSubRecargos detSubRecargos;
	@XmlElement(name = "DETRetencPerceps", required = true)
	protected SDTCFEEntrada205DGIDetalle.DETRetencPerceps detRetencPerceps;
	@XmlElement(name = "DETMontoItem")
	protected double detMontoItem;

	/**
	 * Gets the value of the detNroLinDet property.
	 * 
	 */
	public short getDETNroLinDet() {
		return detNroLinDet;
	}

	/**
	 * Sets the value of the detNroLinDet property.
	 * 
	 */
	public void setDETNroLinDet(short value) {
		this.detNroLinDet = value;
	}

	/**
	 * Gets the value of the detCodItems property.
	 * 
	 * @return possible object is
	 *         {@link SDTCFEEntrada205DGIDetalle.DETCodItems }
	 * 
	 */
	public SDTCFEEntrada205DGIDetalle.DETCodItems getDETCodItems() {
		return detCodItems;
	}

	/**
	 * Sets the value of the detCodItems property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link SDTCFEEntrada205DGIDetalle.DETCodItems }
	 * 
	 */
	public void setDETCodItems(SDTCFEEntrada205DGIDetalle.DETCodItems value) {
		this.detCodItems = value;
	}

	/**
	 * Gets the value of the detIndFact property.
	 * 
	 */
	public byte getDETIndFact() {
		return detIndFact;
	}

	/**
	 * Sets the value of the detIndFact property.
	 * 
	 */
	public void setDETIndFact(byte value) {
		this.detIndFact = value;
	}

	/**
	 * Gets the value of the detIndAgenteResp property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDETIndAgenteResp() {
		return detIndAgenteResp;
	}

	/**
	 * Sets the value of the detIndAgenteResp property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDETIndAgenteResp(String value) {
		this.detIndAgenteResp = value;
	}

	/**
	 * Gets the value of the detNomItem property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDETNomItem() {
		return detNomItem;
	}

	/**
	 * Sets the value of the detNomItem property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDETNomItem(String value) {
		this.detNomItem = value;
	}

	/**
	 * Gets the value of the detDscItem property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDETDscItem() {
		return detDscItem;
	}

	/**
	 * Sets the value of the detDscItem property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDETDscItem(String value) {
		this.detDscItem = value;
	}

	/**
	 * Gets the value of the detCantidad property.
	 * 
	 */
	public double getDETCantidad() {
		return detCantidad;
	}

	/**
	 * Sets the value of the detCantidad property.
	 * 
	 */
	public void setDETCantidad(double value) {
		this.detCantidad = value;
	}

	/**
	 * Gets the value of the detUniMed property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDETUniMed() {
		return detUniMed;
	}

	/**
	 * Sets the value of the detUniMed property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDETUniMed(String value) {
		this.detUniMed = value;
	}

	/**
	 * Gets the value of the detPrecioUnitario property.
	 * 
	 */
	public double getDETPrecioUnitario() {
		return detPrecioUnitario;
	}

	/**
	 * Sets the value of the detPrecioUnitario property.
	 * 
	 */
	public void setDETPrecioUnitario(double value) {
		this.detPrecioUnitario = value;
	}

	/**
	 * Gets the value of the detDescuentoPct property.
	 * 
	 */
	public double getDETDescuentoPct() {
		return detDescuentoPct;
	}

	/**
	 * Sets the value of the detDescuentoPct property.
	 * 
	 */
	public void setDETDescuentoPct(double value) {
		this.detDescuentoPct = value;
	}

	/**
	 * Gets the value of the detDescuentoMonto property.
	 * 
	 */
	public double getDETDescuentoMonto() {
		return detDescuentoMonto;
	}

	/**
	 * Sets the value of the detDescuentoMonto property.
	 * 
	 */
	public void setDETDescuentoMonto(double value) {
		this.detDescuentoMonto = value;
	}

	/**
	 * Gets the value of the detSubDescuentos property.
	 * 
	 * @return possible object is
	 *         {@link SDTCFEEntrada205DGIDetalle.DETSubDescuentos }
	 * 
	 */
	public SDTCFEEntrada205DGIDetalle.DETSubDescuentos getDETSubDescuentos() {
		return detSubDescuentos;
	}

	/**
	 * Sets the value of the detSubDescuentos property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link SDTCFEEntrada205DGIDetalle.DETSubDescuentos }
	 * 
	 */
	public void setDETSubDescuentos(SDTCFEEntrada205DGIDetalle.DETSubDescuentos value) {
		this.detSubDescuentos = value;
	}

	/**
	 * Gets the value of the detRecargoPct property.
	 * 
	 */
	public double getDETRecargoPct() {
		return detRecargoPct;
	}

	/**
	 * Sets the value of the detRecargoPct property.
	 * 
	 */
	public void setDETRecargoPct(double value) {
		this.detRecargoPct = value;
	}

	/**
	 * Gets the value of the detRecargoMnt property.
	 * 
	 */
	public double getDETRecargoMnt() {
		return detRecargoMnt;
	}

	/**
	 * Sets the value of the detRecargoMnt property.
	 * 
	 */
	public void setDETRecargoMnt(double value) {
		this.detRecargoMnt = value;
	}

	/**
	 * Gets the value of the detSubRecargos property.
	 * 
	 * @return possible object is
	 *         {@link SDTCFEEntrada205DGIDetalle.DETSubRecargos }
	 * 
	 */
	public SDTCFEEntrada205DGIDetalle.DETSubRecargos getDETSubRecargos() {
		return detSubRecargos;
	}

	/**
	 * Sets the value of the detSubRecargos property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link SDTCFEEntrada205DGIDetalle.DETSubRecargos }
	 * 
	 */
	public void setDETSubRecargos(SDTCFEEntrada205DGIDetalle.DETSubRecargos value) {
		this.detSubRecargos = value;
	}

	/**
	 * Gets the value of the detRetencPerceps property.
	 * 
	 * @return possible object is
	 *         {@link SDTCFEEntrada205DGIDetalle.DETRetencPerceps }
	 * 
	 */
	public SDTCFEEntrada205DGIDetalle.DETRetencPerceps getDETRetencPerceps() {
		return detRetencPerceps;
	}

	/**
	 * Sets the value of the detRetencPerceps property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link SDTCFEEntrada205DGIDetalle.DETRetencPerceps }
	 * 
	 */
	public void setDETRetencPerceps(SDTCFEEntrada205DGIDetalle.DETRetencPerceps value) {
		this.detRetencPerceps = value;
	}

	/**
	 * Gets the value of the detMontoItem property.
	 * 
	 */
	public double getDETMontoItem() {
		return detMontoItem;
	}

	/**
	 * Sets the value of the detMontoItem property.
	 * 
	 */
	public void setDETMontoItem(double value) {
		this.detMontoItem = value;
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
	 *         &lt;element name="DETCodItem" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETCodItem" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "detCodItem" })
	public static class DETCodItems {

		@XmlElement(name = "DETCodItem")
		protected List<SDTCFEEntrada205DGIDetalleDETCodItem> detCodItem;

		/**
		 * Gets the value of the detCodItem property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the detCodItem property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getDETCodItem().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIDetalleDETCodItem }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIDetalleDETCodItem> getDETCodItem() {
			if (detCodItem == null) {
				detCodItem = new ArrayList<>();
			}
			return this.detCodItem;
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
	 *         &lt;element name="DETRetencPercep" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETRetencPercep" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "detRetencPercep" })
	public static class DETRetencPerceps {

		@XmlElement(name = "DETRetencPercep")
		protected List<SDTCFEEntrada205DGIDetalleDETRetencPercep> detRetencPercep;

		/**
		 * Gets the value of the detRetencPercep property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the detRetencPercep property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getDETRetencPercep().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIDetalleDETRetencPercep }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIDetalleDETRetencPercep> getDETRetencPercep() {
			if (detRetencPercep == null) {
				detRetencPercep = new ArrayList<>();
			}
			return this.detRetencPercep;
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
	 *         &lt;element name="DETSubDescuento" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETSubDescuento" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "detSubDescuento" })
	public static class DETSubDescuentos {

		@XmlElement(name = "DETSubDescuento")
		protected List<SDTCFEEntrada205DGIDetalleDETSubDescuento> detSubDescuento;

		/**
		 * Gets the value of the detSubDescuento property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the detSubDescuento property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getDETSubDescuento().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIDetalleDETSubDescuento }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIDetalleDETSubDescuento> getDETSubDescuento() {
			if (detSubDescuento == null) {
				detSubDescuento = new ArrayList<>();
			}
			return this.detSubDescuento;
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
	 *         &lt;element name="DETSubRecargo" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle.DETSubRecargo" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "detSubRecargo" })
	public static class DETSubRecargos {

		@XmlElement(name = "DETSubRecargo")
		protected List<SDTCFEEntrada205DGIDetalleDETSubRecargo> detSubRecargo;

		/**
		 * Gets the value of the detSubRecargo property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the detSubRecargo property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getDETSubRecargo().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIDetalleDETSubRecargo }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIDetalleDETSubRecargo> getDETSubRecargo() {
			if (detSubRecargo == null) {
				detSubRecargo = new ArrayList<>();
			}
			return this.detSubRecargo;
		}

	}

}
