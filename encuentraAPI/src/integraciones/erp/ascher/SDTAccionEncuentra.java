
package integraciones.erp.ascher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SDTAccionEncuentra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SDTAccionEncuentra">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoDeDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Serie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Nro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Accion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CantidadBultos" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="RespuestaOk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RespuestaMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTAccionEncuentra", propOrder = {
    "tipoDeDocumento",
    "serie",
    "nro",
    "accion",
    "cantidadBultos",
    "respuestaOk",
    "respuestaMsg"
})
public class SDTAccionEncuentra {

    @XmlElement(name = "TipoDeDocumento", required = true)
    protected String tipoDeDocumento;
    @XmlElement(name = "Serie", required = true)
    protected String serie;
    @XmlElement(name = "Nro")
    protected int nro;
    @XmlElement(name = "Accion", required = true)
    protected String accion;
    @XmlElement(name = "CantidadBultos")
    protected short cantidadBultos;
    @XmlElement(name = "RespuestaOk", required = true)
    protected String respuestaOk;
    @XmlElement(name = "RespuestaMsg", required = true)
    protected String respuestaMsg;

    /**
     * Obtiene el valor de la propiedad tipoDeDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    /**
     * Define el valor de la propiedad tipoDeDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDeDocumento(String value) {
        this.tipoDeDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad serie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Define el valor de la propiedad serie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerie(String value) {
        this.serie = value;
    }

    /**
     * Obtiene el valor de la propiedad nro.
     * 
     */
    public int getNro() {
        return nro;
    }

    /**
     * Define el valor de la propiedad nro.
     * 
     */
    public void setNro(int value) {
        this.nro = value;
    }

    /**
     * Obtiene el valor de la propiedad accion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Define el valor de la propiedad accion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccion(String value) {
        this.accion = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadBultos.
     * 
     */
    public short getCantidadBultos() {
        return cantidadBultos;
    }

    /**
     * Define el valor de la propiedad cantidadBultos.
     * 
     */
    public void setCantidadBultos(short value) {
        this.cantidadBultos = value;
    }

    /**
     * Obtiene el valor de la propiedad respuestaOk.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespuestaOk() {
        return respuestaOk;
    }

    /**
     * Define el valor de la propiedad respuestaOk.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespuestaOk(String value) {
        this.respuestaOk = value;
    }

    /**
     * Obtiene el valor de la propiedad respuestaMsg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespuestaMsg() {
        return respuestaMsg;
    }

    /**
     * Define el valor de la propiedad respuestaMsg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespuestaMsg(String value) {
        this.respuestaMsg = value;
    }

}
