
package integraciones.erp.taface.clienteWSTAFACE_firmar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for SDTCFE_Entrada_205.DGI complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SDTCFE_Entrada_205.DGI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDDocTipoCFE" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="IDDocFchEmis" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="IDDocTipoTraslado" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="IDDocIndPropiedad" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="IDDocTipoDocProp" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="IDDocCodPaisProp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDDocDocProp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDDocDocPropExt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDDocRznSocProp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDDocPeriodoDesde" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="IDDocPeriodoHasta" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="IDDocMntBruto" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="IDDocFmaPago" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="IDDocFchVenc" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="IDDocClausulaVenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDDocModalidadVenta" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="IDDocViaTransporte" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="IDDocInfoAdicionalDoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDDocIVAalDia" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="IDDocSecProf" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="EMIRUCEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMIRznSoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMINomComercial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMIGiroEmis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmiTelefonos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EmiTelefono" type="{TAFACE}SDTCFE_Entrada_205.DGI.EmiTelefono" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EMICorreoEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMIEmiSucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMICdgDGISucur" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="EMIDomFiscal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMICiudad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMIDepartamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EMIInfoAdicionalEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECTipoDocRecep" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="RECCodPaisRecep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECDocRecep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECDocRecepExtranjero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECRznSocRecep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECDirRecep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECCiudadRecep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECDeptoRecep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECPaisRecep" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECCP" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RECInfoAdicional" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECLugarDestinoEntrega" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RECNroOrdenCompra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TOTTpoMoneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TOTTpoCambio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntNoGrv" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntExpoyAsim" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntImpuestoPerc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntIVaenSusp" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntNetoIvaTasaMin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntNetoIVATasaBasica" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntNetoIVAOtra" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTIVATasaMin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTIVATasaBasica" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntIVATasaMin" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntIVATasaBasica" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntIVAOtra" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntTotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntTotRetenido" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntTotCredFisc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTCantLinDet" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="TOTRetencPerceps">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TOTRetencPercep" type="{TAFACE}SDTCFE_Entrada_205.DGI.TOTRetencPercep" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TOTMontoNF" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TOTMntPagar" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Detalles">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Detalle" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SubTotalInfos">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SubTotalInfo" type="{TAFACE}SDTCFE_Entrada_205.DGI.SubTotalInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DscRcgGlobales">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DscRcgGlobal" type="{TAFACE}SDTCFE_Entrada_205.DGI.DscRcgGlobal" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MediosPago">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MedioPago" type="{TAFACE}SDTCFE_Entrada_205.DGI.MedioPago" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Referencias">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Referencia" type="{TAFACE}SDTCFE_Entrada_205.DGI.Referencia" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CFRUCEmisor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CFTipoDocMdte" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="CFPais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CFDocMdte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CFNombreMdte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ADETextoADE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ADEOtroADE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTCFE_Entrada_205.DGI", propOrder = { "idDocTipoCFE", "idDocFchEmis", "idDocTipoTraslado",
		"idDocIndPropiedad", "idDocTipoDocProp", "idDocCodPaisProp", "idDocDocProp", "idDocDocPropExt",
		"idDocRznSocProp", "idDocPeriodoDesde", "idDocPeriodoHasta", "idDocMntBruto", "idDocFmaPago", "idDocFchVenc",
		"idDocClausulaVenta", "idDocModalidadVenta", "idDocViaTransporte", "idDocInfoAdicionalDoc", "idDocIVAalDia",
		"idDocSecProf", "emirucEmisor", "emiRznSoc", "emiNomComercial", "emiGiroEmis", "emiTelefonos",
		"emiCorreoEmisor", "emiEmiSucursal", "emiCdgDGISucur", "emiDomFiscal", "emiCiudad", "emiDepartamento",
		"emiInfoAdicionalEmisor", "recTipoDocRecep", "recCodPaisRecep", "recDocRecep", "recDocRecepExtranjero",
		"recRznSocRecep", "recDirRecep", "recCiudadRecep", "recDeptoRecep", "recPaisRecep", "reccp", "recInfoAdicional",
		"recLugarDestinoEntrega", "recNroOrdenCompra", "totTpoMoneda", "totTpoCambio", "totMntNoGrv", "totMntExpoyAsim",
		"totMntImpuestoPerc", "totMntIVaenSusp", "totMntNetoIvaTasaMin", "totMntNetoIVATasaBasica", "totMntNetoIVAOtra",
		"totivaTasaMin", "totivaTasaBasica", "totMntIVATasaMin", "totMntIVATasaBasica", "totMntIVAOtra", "totMntTotal",
		"totMntTotRetenido", "totMntTotCredFisc", "totCantLinDet", "totRetencPerceps", "totMontoNF", "totMntPagar",
		"detalles", "subTotalInfos", "dscRcgGlobales", "mediosPago", "referencias", "cfrucEmisor", "cfTipoDocMdte",
		"cfPais", "cfDocMdte", "cfNombreMdte", "adeTextoADE", "adeOtroADE" })
public class SDTCFEEntrada205DGI {

	@XmlElement(name = "IDDocTipoCFE")
	protected short idDocTipoCFE;
	@XmlElement(name = "IDDocFchEmis", required = true, nillable = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar idDocFchEmis;
	@XmlElement(name = "IDDocTipoTraslado")
	protected byte idDocTipoTraslado;
	@XmlElement(name = "IDDocIndPropiedad")
	protected byte idDocIndPropiedad;
	@XmlElement(name = "IDDocTipoDocProp")
	protected byte idDocTipoDocProp;
	@XmlElement(name = "IDDocCodPaisProp", required = true)
	protected String idDocCodPaisProp;
	@XmlElement(name = "IDDocDocProp", required = true)
	protected String idDocDocProp;
	@XmlElement(name = "IDDocDocPropExt", required = true)
	protected String idDocDocPropExt;
	@XmlElement(name = "IDDocRznSocProp", required = true)
	protected String idDocRznSocProp;
	@XmlElement(name = "IDDocPeriodoDesde", required = true, nillable = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar idDocPeriodoDesde;
	@XmlElement(name = "IDDocPeriodoHasta", required = true, nillable = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar idDocPeriodoHasta;
	@XmlElement(name = "IDDocMntBruto")
	protected byte idDocMntBruto;
	@XmlElement(name = "IDDocFmaPago")
	protected byte idDocFmaPago;
	@XmlElement(name = "IDDocFchVenc", required = true, nillable = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar idDocFchVenc;
	@XmlElement(name = "IDDocClausulaVenta", required = true)
	protected String idDocClausulaVenta;
	@XmlElement(name = "IDDocModalidadVenta")
	protected byte idDocModalidadVenta;
	@XmlElement(name = "IDDocViaTransporte")
	protected byte idDocViaTransporte;
	@XmlElement(name = "IDDocInfoAdicionalDoc", required = true)
	protected String idDocInfoAdicionalDoc;
	@XmlElement(name = "IDDocIVAalDia")
	protected byte idDocIVAalDia;
	@XmlElement(name = "IDDocSecProf")
	protected byte idDocSecProf;
	@XmlElement(name = "EMIRUCEmisor", required = true)
	protected String emirucEmisor;
	@XmlElement(name = "EMIRznSoc", required = true)
	protected String emiRznSoc;
	@XmlElement(name = "EMINomComercial", required = true)
	protected String emiNomComercial;
	@XmlElement(name = "EMIGiroEmis", required = true)
	protected String emiGiroEmis;
	@XmlElement(name = "EmiTelefonos", required = true)
	protected SDTCFEEntrada205DGI.EmiTelefonos emiTelefonos;
	@XmlElement(name = "EMICorreoEmisor", required = true)
	protected String emiCorreoEmisor;
	@XmlElement(name = "EMIEmiSucursal", required = true)
	protected String emiEmiSucursal;
	@XmlElement(name = "EMICdgDGISucur")
	protected short emiCdgDGISucur;
	@XmlElement(name = "EMIDomFiscal", required = true)
	protected String emiDomFiscal;
	@XmlElement(name = "EMICiudad", required = true)
	protected String emiCiudad;
	@XmlElement(name = "EMIDepartamento", required = true)
	protected String emiDepartamento;
	@XmlElement(name = "EMIInfoAdicionalEmisor", required = true)
	protected String emiInfoAdicionalEmisor;
	@XmlElement(name = "RECTipoDocRecep")
	protected byte recTipoDocRecep;
	@XmlElement(name = "RECCodPaisRecep", required = true)
	protected String recCodPaisRecep;
	@XmlElement(name = "RECDocRecep", required = true)
	protected String recDocRecep;
	@XmlElement(name = "RECDocRecepExtranjero", required = true)
	protected String recDocRecepExtranjero;
	@XmlElement(name = "RECRznSocRecep", required = true)
	protected String recRznSocRecep;
	@XmlElement(name = "RECDirRecep", required = true)
	protected String recDirRecep;
	@XmlElement(name = "RECCiudadRecep", required = true)
	protected String recCiudadRecep;
	@XmlElement(name = "RECDeptoRecep", required = true)
	protected String recDeptoRecep;
	@XmlElement(name = "RECPaisRecep", required = true)
	protected String recPaisRecep;
	@XmlElement(name = "RECCP")
	protected int reccp;
	@XmlElement(name = "RECInfoAdicional", required = true)
	protected String recInfoAdicional;
	@XmlElement(name = "RECLugarDestinoEntrega", required = true)
	protected String recLugarDestinoEntrega;
	@XmlElement(name = "RECNroOrdenCompra", required = true)
	protected String recNroOrdenCompra;
	@XmlElement(name = "TOTTpoMoneda", required = true)
	protected String totTpoMoneda;
	@XmlElement(name = "TOTTpoCambio")
	protected double totTpoCambio;
	@XmlElement(name = "TOTMntNoGrv")
	protected double totMntNoGrv;
	@XmlElement(name = "TOTMntExpoyAsim")
	protected double totMntExpoyAsim;
	@XmlElement(name = "TOTMntImpuestoPerc")
	protected double totMntImpuestoPerc;
	@XmlElement(name = "TOTMntIVaenSusp")
	protected double totMntIVaenSusp;
	@XmlElement(name = "TOTMntNetoIvaTasaMin")
	protected double totMntNetoIvaTasaMin;
	@XmlElement(name = "TOTMntNetoIVATasaBasica")
	protected double totMntNetoIVATasaBasica;
	@XmlElement(name = "TOTMntNetoIVAOtra")
	protected double totMntNetoIVAOtra;
	@XmlElement(name = "TOTIVATasaMin")
	protected double totivaTasaMin;
	@XmlElement(name = "TOTIVATasaBasica")
	protected double totivaTasaBasica;
	@XmlElement(name = "TOTMntIVATasaMin")
	protected double totMntIVATasaMin;
	@XmlElement(name = "TOTMntIVATasaBasica")
	protected double totMntIVATasaBasica;
	@XmlElement(name = "TOTMntIVAOtra")
	protected double totMntIVAOtra;
	@XmlElement(name = "TOTMntTotal")
	protected double totMntTotal;
	@XmlElement(name = "TOTMntTotRetenido")
	protected double totMntTotRetenido;
	@XmlElement(name = "TOTMntTotCredFisc")
	protected double totMntTotCredFisc;
	@XmlElement(name = "TOTCantLinDet")
	protected short totCantLinDet;
	@XmlElement(name = "TOTRetencPerceps", required = true)
	protected SDTCFEEntrada205DGI.TOTRetencPerceps totRetencPerceps;
	@XmlElement(name = "TOTMontoNF")
	protected double totMontoNF;
	@XmlElement(name = "TOTMntPagar")
	protected double totMntPagar;
	@XmlElement(name = "Detalles", required = true)
	protected SDTCFEEntrada205DGI.Detalles detalles;
	@XmlElement(name = "SubTotalInfos", required = true)
	protected SDTCFEEntrada205DGI.SubTotalInfos subTotalInfos;
	@XmlElement(name = "DscRcgGlobales", required = true)
	protected SDTCFEEntrada205DGI.DscRcgGlobales dscRcgGlobales;
	@XmlElement(name = "MediosPago", required = true)
	protected SDTCFEEntrada205DGI.MediosPago mediosPago;
	@XmlElement(name = "Referencias", required = true)
	protected SDTCFEEntrada205DGI.Referencias referencias;
	@XmlElement(name = "CFRUCEmisor", required = true)
	protected String cfrucEmisor;
	@XmlElement(name = "CFTipoDocMdte")
	protected byte cfTipoDocMdte;
	@XmlElement(name = "CFPais", required = true)
	protected String cfPais;
	@XmlElement(name = "CFDocMdte", required = true)
	protected String cfDocMdte;
	@XmlElement(name = "CFNombreMdte", required = true)
	protected String cfNombreMdte;
	@XmlElement(name = "ADETextoADE", required = true)
	protected String adeTextoADE;
	@XmlElement(name = "ADEOtroADE", required = true)
	protected String adeOtroADE;

	/**
	 * Gets the value of the idDocTipoCFE property.
	 * 
	 */
	public short getIDDocTipoCFE() {
		return idDocTipoCFE;
	}

	/**
	 * Sets the value of the idDocTipoCFE property.
	 * 
	 */
	public void setIDDocTipoCFE(short value) {
		this.idDocTipoCFE = value;
	}

	/**
	 * Gets the value of the idDocFchEmis property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getIDDocFchEmis() {
		return idDocFchEmis;
	}

	/**
	 * Sets the value of the idDocFchEmis property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setIDDocFchEmis(XMLGregorianCalendar value) {
		this.idDocFchEmis = value;
	}

	/**
	 * Gets the value of the idDocTipoTraslado property.
	 * 
	 */
	public byte getIDDocTipoTraslado() {
		return idDocTipoTraslado;
	}

	/**
	 * Sets the value of the idDocTipoTraslado property.
	 * 
	 */
	public void setIDDocTipoTraslado(byte value) {
		this.idDocTipoTraslado = value;
	}

	/**
	 * Gets the value of the idDocIndPropiedad property.
	 * 
	 */
	public byte getIDDocIndPropiedad() {
		return idDocIndPropiedad;
	}

	/**
	 * Sets the value of the idDocIndPropiedad property.
	 * 
	 */
	public void setIDDocIndPropiedad(byte value) {
		this.idDocIndPropiedad = value;
	}

	/**
	 * Gets the value of the idDocTipoDocProp property.
	 * 
	 */
	public byte getIDDocTipoDocProp() {
		return idDocTipoDocProp;
	}

	/**
	 * Sets the value of the idDocTipoDocProp property.
	 * 
	 */
	public void setIDDocTipoDocProp(byte value) {
		this.idDocTipoDocProp = value;
	}

	/**
	 * Gets the value of the idDocCodPaisProp property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIDDocCodPaisProp() {
		return idDocCodPaisProp;
	}

	/**
	 * Sets the value of the idDocCodPaisProp property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIDDocCodPaisProp(String value) {
		this.idDocCodPaisProp = value;
	}

	/**
	 * Gets the value of the idDocDocProp property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIDDocDocProp() {
		return idDocDocProp;
	}

	/**
	 * Sets the value of the idDocDocProp property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIDDocDocProp(String value) {
		this.idDocDocProp = value;
	}

	/**
	 * Gets the value of the idDocDocPropExt property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIDDocDocPropExt() {
		return idDocDocPropExt;
	}

	/**
	 * Sets the value of the idDocDocPropExt property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIDDocDocPropExt(String value) {
		this.idDocDocPropExt = value;
	}

	/**
	 * Gets the value of the idDocRznSocProp property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIDDocRznSocProp() {
		return idDocRznSocProp;
	}

	/**
	 * Sets the value of the idDocRznSocProp property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIDDocRznSocProp(String value) {
		this.idDocRznSocProp = value;
	}

	/**
	 * Gets the value of the idDocPeriodoDesde property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getIDDocPeriodoDesde() {
		return idDocPeriodoDesde;
	}

	/**
	 * Sets the value of the idDocPeriodoDesde property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setIDDocPeriodoDesde(XMLGregorianCalendar value) {
		this.idDocPeriodoDesde = value;
	}

	/**
	 * Gets the value of the idDocPeriodoHasta property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getIDDocPeriodoHasta() {
		return idDocPeriodoHasta;
	}

	/**
	 * Sets the value of the idDocPeriodoHasta property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setIDDocPeriodoHasta(XMLGregorianCalendar value) {
		this.idDocPeriodoHasta = value;
	}

	/**
	 * Gets the value of the idDocMntBruto property.
	 * 
	 */
	public byte getIDDocMntBruto() {
		return idDocMntBruto;
	}

	/**
	 * Sets the value of the idDocMntBruto property.
	 * 
	 */
	public void setIDDocMntBruto(byte value) {
		this.idDocMntBruto = value;
	}

	/**
	 * Gets the value of the idDocFmaPago property.
	 * 
	 */
	public byte getIDDocFmaPago() {
		return idDocFmaPago;
	}

	/**
	 * Sets the value of the idDocFmaPago property.
	 * 
	 */
	public void setIDDocFmaPago(byte value) {
		this.idDocFmaPago = value;
	}

	/**
	 * Gets the value of the idDocFchVenc property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getIDDocFchVenc() {
		return idDocFchVenc;
	}

	/**
	 * Sets the value of the idDocFchVenc property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setIDDocFchVenc(XMLGregorianCalendar value) {
		this.idDocFchVenc = value;
	}

	/**
	 * Gets the value of the idDocClausulaVenta property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIDDocClausulaVenta() {
		return idDocClausulaVenta;
	}

	/**
	 * Sets the value of the idDocClausulaVenta property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIDDocClausulaVenta(String value) {
		this.idDocClausulaVenta = value;
	}

	/**
	 * Gets the value of the idDocModalidadVenta property.
	 * 
	 */
	public byte getIDDocModalidadVenta() {
		return idDocModalidadVenta;
	}

	/**
	 * Sets the value of the idDocModalidadVenta property.
	 * 
	 */
	public void setIDDocModalidadVenta(byte value) {
		this.idDocModalidadVenta = value;
	}

	/**
	 * Gets the value of the idDocViaTransporte property.
	 * 
	 */
	public byte getIDDocViaTransporte() {
		return idDocViaTransporte;
	}

	/**
	 * Sets the value of the idDocViaTransporte property.
	 * 
	 */
	public void setIDDocViaTransporte(byte value) {
		this.idDocViaTransporte = value;
	}

	/**
	 * Gets the value of the idDocInfoAdicionalDoc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIDDocInfoAdicionalDoc() {
		return idDocInfoAdicionalDoc;
	}

	/**
	 * Sets the value of the idDocInfoAdicionalDoc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIDDocInfoAdicionalDoc(String value) {
		this.idDocInfoAdicionalDoc = value;
	}

	/**
	 * Gets the value of the idDocIVAalDia property.
	 * 
	 */
	public byte getIDDocIVAalDia() {
		return idDocIVAalDia;
	}

	/**
	 * Sets the value of the idDocIVAalDia property.
	 * 
	 */
	public void setIDDocIVAalDia(byte value) {
		this.idDocIVAalDia = value;
	}

	/**
	 * Gets the value of the idDocSecProf property.
	 * 
	 */
	public byte getIDDocSecProf() {
		return idDocSecProf;
	}

	/**
	 * Sets the value of the idDocSecProf property.
	 * 
	 */
	public void setIDDocSecProf(byte value) {
		this.idDocSecProf = value;
	}

	/**
	 * Gets the value of the emirucEmisor property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMIRUCEmisor() {
		return emirucEmisor;
	}

	/**
	 * Sets the value of the emirucEmisor property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMIRUCEmisor(String value) {
		this.emirucEmisor = value;
	}

	/**
	 * Gets the value of the emiRznSoc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMIRznSoc() {
		return emiRznSoc;
	}

	/**
	 * Sets the value of the emiRznSoc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMIRznSoc(String value) {
		this.emiRznSoc = value;
	}

	/**
	 * Gets the value of the emiNomComercial property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMINomComercial() {
		return emiNomComercial;
	}

	/**
	 * Sets the value of the emiNomComercial property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMINomComercial(String value) {
		this.emiNomComercial = value;
	}

	/**
	 * Gets the value of the emiGiroEmis property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMIGiroEmis() {
		return emiGiroEmis;
	}

	/**
	 * Sets the value of the emiGiroEmis property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMIGiroEmis(String value) {
		this.emiGiroEmis = value;
	}

	/**
	 * Gets the value of the emiTelefonos property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DGI.EmiTelefonos }
	 * 
	 */
	public SDTCFEEntrada205DGI.EmiTelefonos getEmiTelefonos() {
		return emiTelefonos;
	}

	/**
	 * Sets the value of the emiTelefonos property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DGI.EmiTelefonos }
	 * 
	 */
	public void setEmiTelefonos(SDTCFEEntrada205DGI.EmiTelefonos value) {
		this.emiTelefonos = value;
	}

	/**
	 * Gets the value of the emiCorreoEmisor property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMICorreoEmisor() {
		return emiCorreoEmisor;
	}

	/**
	 * Sets the value of the emiCorreoEmisor property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMICorreoEmisor(String value) {
		this.emiCorreoEmisor = value;
	}

	/**
	 * Gets the value of the emiEmiSucursal property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMIEmiSucursal() {
		return emiEmiSucursal;
	}

	/**
	 * Sets the value of the emiEmiSucursal property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMIEmiSucursal(String value) {
		this.emiEmiSucursal = value;
	}

	/**
	 * Gets the value of the emiCdgDGISucur property.
	 * 
	 */
	public short getEMICdgDGISucur() {
		return emiCdgDGISucur;
	}

	/**
	 * Sets the value of the emiCdgDGISucur property.
	 * 
	 */
	public void setEMICdgDGISucur(short value) {
		this.emiCdgDGISucur = value;
	}

	/**
	 * Gets the value of the emiDomFiscal property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMIDomFiscal() {
		return emiDomFiscal;
	}

	/**
	 * Sets the value of the emiDomFiscal property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMIDomFiscal(String value) {
		this.emiDomFiscal = value;
	}

	/**
	 * Gets the value of the emiCiudad property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMICiudad() {
		return emiCiudad;
	}

	/**
	 * Sets the value of the emiCiudad property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMICiudad(String value) {
		this.emiCiudad = value;
	}

	/**
	 * Gets the value of the emiDepartamento property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMIDepartamento() {
		return emiDepartamento;
	}

	/**
	 * Sets the value of the emiDepartamento property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMIDepartamento(String value) {
		this.emiDepartamento = value;
	}

	/**
	 * Gets the value of the emiInfoAdicionalEmisor property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEMIInfoAdicionalEmisor() {
		return emiInfoAdicionalEmisor;
	}

	/**
	 * Sets the value of the emiInfoAdicionalEmisor property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEMIInfoAdicionalEmisor(String value) {
		this.emiInfoAdicionalEmisor = value;
	}

	/**
	 * Gets the value of the recTipoDocRecep property.
	 * 
	 */
	public byte getRECTipoDocRecep() {
		return recTipoDocRecep;
	}

	/**
	 * Sets the value of the recTipoDocRecep property.
	 * 
	 */
	public void setRECTipoDocRecep(byte value) {
		this.recTipoDocRecep = value;
	}

	/**
	 * Gets the value of the recCodPaisRecep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECCodPaisRecep() {
		return recCodPaisRecep;
	}

	/**
	 * Sets the value of the recCodPaisRecep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECCodPaisRecep(String value) {
		this.recCodPaisRecep = value;
	}

	/**
	 * Gets the value of the recDocRecep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECDocRecep() {
		return recDocRecep;
	}

	/**
	 * Sets the value of the recDocRecep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECDocRecep(String value) {
		this.recDocRecep = value;
	}

	/**
	 * Gets the value of the recDocRecepExtranjero property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECDocRecepExtranjero() {
		return recDocRecepExtranjero;
	}

	/**
	 * Sets the value of the recDocRecepExtranjero property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECDocRecepExtranjero(String value) {
		this.recDocRecepExtranjero = value;
	}

	/**
	 * Gets the value of the recRznSocRecep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECRznSocRecep() {
		return recRznSocRecep;
	}

	/**
	 * Sets the value of the recRznSocRecep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECRznSocRecep(String value) {
		this.recRznSocRecep = value;
	}

	/**
	 * Gets the value of the recDirRecep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECDirRecep() {
		return recDirRecep;
	}

	/**
	 * Sets the value of the recDirRecep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECDirRecep(String value) {
		this.recDirRecep = value;
	}

	/**
	 * Gets the value of the recCiudadRecep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECCiudadRecep() {
		return recCiudadRecep;
	}

	/**
	 * Sets the value of the recCiudadRecep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECCiudadRecep(String value) {
		this.recCiudadRecep = value;
	}

	/**
	 * Gets the value of the recDeptoRecep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECDeptoRecep() {
		return recDeptoRecep;
	}

	/**
	 * Sets the value of the recDeptoRecep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECDeptoRecep(String value) {
		this.recDeptoRecep = value;
	}

	/**
	 * Gets the value of the recPaisRecep property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECPaisRecep() {
		return recPaisRecep;
	}

	/**
	 * Sets the value of the recPaisRecep property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECPaisRecep(String value) {
		this.recPaisRecep = value;
	}

	/**
	 * Gets the value of the reccp property.
	 * 
	 */
	public int getRECCP() {
		return reccp;
	}

	/**
	 * Sets the value of the reccp property.
	 * 
	 */
	public void setRECCP(int value) {
		this.reccp = value;
	}

	/**
	 * Gets the value of the recInfoAdicional property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECInfoAdicional() {
		return recInfoAdicional;
	}

	/**
	 * Sets the value of the recInfoAdicional property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECInfoAdicional(String value) {
		this.recInfoAdicional = value;
	}

	/**
	 * Gets the value of the recLugarDestinoEntrega property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECLugarDestinoEntrega() {
		return recLugarDestinoEntrega;
	}

	/**
	 * Sets the value of the recLugarDestinoEntrega property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECLugarDestinoEntrega(String value) {
		this.recLugarDestinoEntrega = value;
	}

	/**
	 * Gets the value of the recNroOrdenCompra property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRECNroOrdenCompra() {
		return recNroOrdenCompra;
	}

	/**
	 * Sets the value of the recNroOrdenCompra property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRECNroOrdenCompra(String value) {
		this.recNroOrdenCompra = value;
	}

	/**
	 * Gets the value of the totTpoMoneda property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTOTTpoMoneda() {
		return totTpoMoneda;
	}

	/**
	 * Sets the value of the totTpoMoneda property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTOTTpoMoneda(String value) {
		this.totTpoMoneda = value;
	}

	/**
	 * Gets the value of the totTpoCambio property.
	 * 
	 */
	public double getTOTTpoCambio() {
		return totTpoCambio;
	}

	/**
	 * Sets the value of the totTpoCambio property.
	 * 
	 */
	public void setTOTTpoCambio(double value) {
		this.totTpoCambio = value;
	}

	/**
	 * Gets the value of the totMntNoGrv property.
	 * 
	 */
	public double getTOTMntNoGrv() {
		return totMntNoGrv;
	}

	/**
	 * Sets the value of the totMntNoGrv property.
	 * 
	 */
	public void setTOTMntNoGrv(double value) {
		this.totMntNoGrv = value;
	}

	/**
	 * Gets the value of the totMntExpoyAsim property.
	 * 
	 */
	public double getTOTMntExpoyAsim() {
		return totMntExpoyAsim;
	}

	/**
	 * Sets the value of the totMntExpoyAsim property.
	 * 
	 */
	public void setTOTMntExpoyAsim(double value) {
		this.totMntExpoyAsim = value;
	}

	/**
	 * Gets the value of the totMntImpuestoPerc property.
	 * 
	 */
	public double getTOTMntImpuestoPerc() {
		return totMntImpuestoPerc;
	}

	/**
	 * Sets the value of the totMntImpuestoPerc property.
	 * 
	 */
	public void setTOTMntImpuestoPerc(double value) {
		this.totMntImpuestoPerc = value;
	}

	/**
	 * Gets the value of the totMntIVaenSusp property.
	 * 
	 */
	public double getTOTMntIVaenSusp() {
		return totMntIVaenSusp;
	}

	/**
	 * Sets the value of the totMntIVaenSusp property.
	 * 
	 */
	public void setTOTMntIVaenSusp(double value) {
		this.totMntIVaenSusp = value;
	}

	/**
	 * Gets the value of the totMntNetoIvaTasaMin property.
	 * 
	 */
	public double getTOTMntNetoIvaTasaMin() {
		return totMntNetoIvaTasaMin;
	}

	/**
	 * Sets the value of the totMntNetoIvaTasaMin property.
	 * 
	 */
	public void setTOTMntNetoIvaTasaMin(double value) {
		this.totMntNetoIvaTasaMin = value;
	}

	/**
	 * Gets the value of the totMntNetoIVATasaBasica property.
	 * 
	 */
	public double getTOTMntNetoIVATasaBasica() {
		return totMntNetoIVATasaBasica;
	}

	/**
	 * Sets the value of the totMntNetoIVATasaBasica property.
	 * 
	 */
	public void setTOTMntNetoIVATasaBasica(double value) {
		this.totMntNetoIVATasaBasica = value;
	}

	/**
	 * Gets the value of the totMntNetoIVAOtra property.
	 * 
	 */
	public double getTOTMntNetoIVAOtra() {
		return totMntNetoIVAOtra;
	}

	/**
	 * Sets the value of the totMntNetoIVAOtra property.
	 * 
	 */
	public void setTOTMntNetoIVAOtra(double value) {
		this.totMntNetoIVAOtra = value;
	}

	/**
	 * Gets the value of the totivaTasaMin property.
	 * 
	 */
	public double getTOTIVATasaMin() {
		return totivaTasaMin;
	}

	/**
	 * Sets the value of the totivaTasaMin property.
	 * 
	 */
	public void setTOTIVATasaMin(double value) {
		this.totivaTasaMin = value;
	}

	/**
	 * Gets the value of the totivaTasaBasica property.
	 * 
	 */
	public double getTOTIVATasaBasica() {
		return totivaTasaBasica;
	}

	/**
	 * Sets the value of the totivaTasaBasica property.
	 * 
	 */
	public void setTOTIVATasaBasica(double value) {
		this.totivaTasaBasica = value;
	}

	/**
	 * Gets the value of the totMntIVATasaMin property.
	 * 
	 */
	public double getTOTMntIVATasaMin() {
		return totMntIVATasaMin;
	}

	/**
	 * Sets the value of the totMntIVATasaMin property.
	 * 
	 */
	public void setTOTMntIVATasaMin(double value) {
		this.totMntIVATasaMin = value;
	}

	/**
	 * Gets the value of the totMntIVATasaBasica property.
	 * 
	 */
	public double getTOTMntIVATasaBasica() {
		return totMntIVATasaBasica;
	}

	/**
	 * Sets the value of the totMntIVATasaBasica property.
	 * 
	 */
	public void setTOTMntIVATasaBasica(double value) {
		this.totMntIVATasaBasica = value;
	}

	/**
	 * Gets the value of the totMntIVAOtra property.
	 * 
	 */
	public double getTOTMntIVAOtra() {
		return totMntIVAOtra;
	}

	/**
	 * Sets the value of the totMntIVAOtra property.
	 * 
	 */
	public void setTOTMntIVAOtra(double value) {
		this.totMntIVAOtra = value;
	}

	/**
	 * Gets the value of the totMntTotal property.
	 * 
	 */
	public double getTOTMntTotal() {
		return totMntTotal;
	}

	/**
	 * Sets the value of the totMntTotal property.
	 * 
	 */
	public void setTOTMntTotal(double value) {
		this.totMntTotal = value;
	}

	/**
	 * Gets the value of the totMntTotRetenido property.
	 * 
	 */
	public double getTOTMntTotRetenido() {
		return totMntTotRetenido;
	}

	/**
	 * Sets the value of the totMntTotRetenido property.
	 * 
	 */
	public void setTOTMntTotRetenido(double value) {
		this.totMntTotRetenido = value;
	}

	/**
	 * Gets the value of the totMntTotCredFisc property.
	 * 
	 */
	public double getTOTMntTotCredFisc() {
		return totMntTotCredFisc;
	}

	/**
	 * Sets the value of the totMntTotCredFisc property.
	 * 
	 */
	public void setTOTMntTotCredFisc(double value) {
		this.totMntTotCredFisc = value;
	}

	/**
	 * Gets the value of the totCantLinDet property.
	 * 
	 */
	public short getTOTCantLinDet() {
		return totCantLinDet;
	}

	/**
	 * Sets the value of the totCantLinDet property.
	 * 
	 */
	public void setTOTCantLinDet(short value) {
		this.totCantLinDet = value;
	}

	/**
	 * Gets the value of the totRetencPerceps property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DGI.TOTRetencPerceps }
	 * 
	 */
	public SDTCFEEntrada205DGI.TOTRetencPerceps getTOTRetencPerceps() {
		return totRetencPerceps;
	}

	/**
	 * Sets the value of the totRetencPerceps property.
	 * 
	 * @param value
	 *            allowed object is
	 *            {@link SDTCFEEntrada205DGI.TOTRetencPerceps }
	 * 
	 */
	public void setTOTRetencPerceps(SDTCFEEntrada205DGI.TOTRetencPerceps value) {
		this.totRetencPerceps = value;
	}

	/**
	 * Gets the value of the totMontoNF property.
	 * 
	 */
	public double getTOTMontoNF() {
		return totMontoNF;
	}

	/**
	 * Sets the value of the totMontoNF property.
	 * 
	 */
	public void setTOTMontoNF(double value) {
		this.totMontoNF = value;
	}

	/**
	 * Gets the value of the totMntPagar property.
	 * 
	 */
	public double getTOTMntPagar() {
		return totMntPagar;
	}

	/**
	 * Sets the value of the totMntPagar property.
	 * 
	 */
	public void setTOTMntPagar(double value) {
		this.totMntPagar = value;
	}

	/**
	 * Gets the value of the detalles property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DGI.Detalles }
	 * 
	 */
	public SDTCFEEntrada205DGI.Detalles getDetalles() {
		return detalles;
	}

	/**
	 * Sets the value of the detalles property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DGI.Detalles }
	 * 
	 */
	public void setDetalles(SDTCFEEntrada205DGI.Detalles value) {
		this.detalles = value;
	}

	/**
	 * Gets the value of the subTotalInfos property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DGI.SubTotalInfos }
	 * 
	 */
	public SDTCFEEntrada205DGI.SubTotalInfos getSubTotalInfos() {
		return subTotalInfos;
	}

	/**
	 * Sets the value of the subTotalInfos property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DGI.SubTotalInfos }
	 * 
	 */
	public void setSubTotalInfos(SDTCFEEntrada205DGI.SubTotalInfos value) {
		this.subTotalInfos = value;
	}

	/**
	 * Gets the value of the dscRcgGlobales property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DGI.DscRcgGlobales }
	 * 
	 */
	public SDTCFEEntrada205DGI.DscRcgGlobales getDscRcgGlobales() {
		return dscRcgGlobales;
	}

	/**
	 * Sets the value of the dscRcgGlobales property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DGI.DscRcgGlobales }
	 * 
	 */
	public void setDscRcgGlobales(SDTCFEEntrada205DGI.DscRcgGlobales value) {
		this.dscRcgGlobales = value;
	}

	/**
	 * Gets the value of the mediosPago property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DGI.MediosPago }
	 * 
	 */
	public SDTCFEEntrada205DGI.MediosPago getMediosPago() {
		return mediosPago;
	}

	/**
	 * Sets the value of the mediosPago property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DGI.MediosPago }
	 * 
	 */
	public void setMediosPago(SDTCFEEntrada205DGI.MediosPago value) {
		this.mediosPago = value;
	}

	/**
	 * Gets the value of the referencias property.
	 * 
	 * @return possible object is {@link SDTCFEEntrada205DGI.Referencias }
	 * 
	 */
	public SDTCFEEntrada205DGI.Referencias getReferencias() {
		return referencias;
	}

	/**
	 * Sets the value of the referencias property.
	 * 
	 * @param value
	 *            allowed object is {@link SDTCFEEntrada205DGI.Referencias }
	 * 
	 */
	public void setReferencias(SDTCFEEntrada205DGI.Referencias value) {
		this.referencias = value;
	}

	/**
	 * Gets the value of the cfrucEmisor property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCFRUCEmisor() {
		return cfrucEmisor;
	}

	/**
	 * Sets the value of the cfrucEmisor property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCFRUCEmisor(String value) {
		this.cfrucEmisor = value;
	}

	/**
	 * Gets the value of the cfTipoDocMdte property.
	 * 
	 */
	public byte getCFTipoDocMdte() {
		return cfTipoDocMdte;
	}

	/**
	 * Sets the value of the cfTipoDocMdte property.
	 * 
	 */
	public void setCFTipoDocMdte(byte value) {
		this.cfTipoDocMdte = value;
	}

	/**
	 * Gets the value of the cfPais property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCFPais() {
		return cfPais;
	}

	/**
	 * Sets the value of the cfPais property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCFPais(String value) {
		this.cfPais = value;
	}

	/**
	 * Gets the value of the cfDocMdte property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCFDocMdte() {
		return cfDocMdte;
	}

	/**
	 * Sets the value of the cfDocMdte property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCFDocMdte(String value) {
		this.cfDocMdte = value;
	}

	/**
	 * Gets the value of the cfNombreMdte property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCFNombreMdte() {
		return cfNombreMdte;
	}

	/**
	 * Sets the value of the cfNombreMdte property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCFNombreMdte(String value) {
		this.cfNombreMdte = value;
	}

	/**
	 * Gets the value of the adeTextoADE property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getADETextoADE() {
		return adeTextoADE;
	}

	/**
	 * Sets the value of the adeTextoADE property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setADETextoADE(String value) {
		this.adeTextoADE = value;
	}

	/**
	 * Gets the value of the adeOtroADE property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getADEOtroADE() {
		return adeOtroADE;
	}

	/**
	 * Sets the value of the adeOtroADE property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setADEOtroADE(String value) {
		this.adeOtroADE = value;
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
	 *         &lt;element name="Detalle" type="{TAFACE}SDTCFE_Entrada_205.DGI.Detalle" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "detalle" })
	public static class Detalles {

		@XmlElement(name = "Detalle")
		protected List<SDTCFEEntrada205DGIDetalle> detalle;

		/**
		 * Gets the value of the detalle property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the detalle property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getDetalle().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIDetalle }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIDetalle> getDetalle() {
			if (detalle == null) {
				detalle = new ArrayList<>();
			}
			return this.detalle;
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
	 *         &lt;element name="DscRcgGlobal" type="{TAFACE}SDTCFE_Entrada_205.DGI.DscRcgGlobal" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "dscRcgGlobal" })
	public static class DscRcgGlobales {

		@XmlElement(name = "DscRcgGlobal")
		protected List<SDTCFEEntrada205DGIDscRcgGlobal> dscRcgGlobal;

		/**
		 * Gets the value of the dscRcgGlobal property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the dscRcgGlobal property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getDscRcgGlobal().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIDscRcgGlobal }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIDscRcgGlobal> getDscRcgGlobal() {
			if (dscRcgGlobal == null) {
				dscRcgGlobal = new ArrayList<>();
			}
			return this.dscRcgGlobal;
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
	 *         &lt;element name="EmiTelefono" type="{TAFACE}SDTCFE_Entrada_205.DGI.EmiTelefono" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "emiTelefono" })
	public static class EmiTelefonos {

		@XmlElement(name = "EmiTelefono")
		protected List<SDTCFEEntrada205DGIEmiTelefono> emiTelefono;

		/**
		 * Gets the value of the emiTelefono property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the emiTelefono property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getEmiTelefono().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIEmiTelefono }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIEmiTelefono> getEmiTelefono() {
			if (emiTelefono == null) {
				emiTelefono = new ArrayList<>();
			}
			return this.emiTelefono;
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
	 *         &lt;element name="MedioPago" type="{TAFACE}SDTCFE_Entrada_205.DGI.MedioPago" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "medioPago" })
	public static class MediosPago {

		@XmlElement(name = "MedioPago")
		protected List<SDTCFEEntrada205DGIMedioPago> medioPago;

		/**
		 * Gets the value of the medioPago property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the medioPago property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getMedioPago().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIMedioPago }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIMedioPago> getMedioPago() {
			if (medioPago == null) {
				medioPago = new ArrayList<>();
			}
			return this.medioPago;
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
	 *         &lt;element name="Referencia" type="{TAFACE}SDTCFE_Entrada_205.DGI.Referencia" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "referencia" })
	public static class Referencias {

		@XmlElement(name = "Referencia")
		protected List<SDTCFEEntrada205DGIReferencia> referencia;

		/**
		 * Gets the value of the referencia property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the referencia property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getReferencia().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGIReferencia }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGIReferencia> getReferencia() {
			if (referencia == null) {
				referencia = new ArrayList<>();
			}
			return this.referencia;
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
	 *         &lt;element name="SubTotalInfo" type="{TAFACE}SDTCFE_Entrada_205.DGI.SubTotalInfo" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "subTotalInfo" })
	public static class SubTotalInfos {

		@XmlElement(name = "SubTotalInfo")
		protected List<SDTCFEEntrada205DGISubTotalInfo> subTotalInfo;

		/**
		 * Gets the value of the subTotalInfo property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the subTotalInfo property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getSubTotalInfo().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGISubTotalInfo }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGISubTotalInfo> getSubTotalInfo() {
			if (subTotalInfo == null) {
				subTotalInfo = new ArrayList<>();
			}
			return this.subTotalInfo;
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
	 *         &lt;element name="TOTRetencPercep" type="{TAFACE}SDTCFE_Entrada_205.DGI.TOTRetencPercep" maxOccurs="unbounded" minOccurs="0"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "totRetencPercep" })
	public static class TOTRetencPerceps {

		@XmlElement(name = "TOTRetencPercep")
		protected List<SDTCFEEntrada205DGITOTRetencPercep> totRetencPercep;

		/**
		 * Gets the value of the totRetencPercep property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the totRetencPercep property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getTOTRetencPercep().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SDTCFEEntrada205DGITOTRetencPercep }
		 * 
		 * 
		 */
		public List<SDTCFEEntrada205DGITOTRetencPercep> getTOTRetencPercep() {
			if (totRetencPercep == null) {
				totRetencPercep = new ArrayList<>();
			}
			return this.totRetencPercep;
		}

	}

}
