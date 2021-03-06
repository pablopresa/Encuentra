
package integraciones.erp.ascher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SDTPedidoEncuentraRenglones.SDTPedidoEncuentraRenglon complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SDTPedidoEncuentraRenglones.SDTPedidoEncuentraRenglon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="ArtId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ArtDescripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UnidadesEnPack" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Cantidad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTPedidoEncuentraRenglones.SDTPedidoEncuentraRenglon", propOrder = {
    "id",
    "artId",
    "artDescripcion",
    "unidadesEnPack",
    "cantidad",
    "observaciones"
})
public class SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon {

    @XmlElement(name = "Id")
    protected short id;
    @XmlElement(name = "ArtId", required = true)
    protected String artId;
    @XmlElement(name = "ArtDescripcion", required = true)
    protected String artDescripcion;
    @XmlElement(name = "UnidadesEnPack")
    protected short unidadesEnPack;
    @XmlElement(name = "Cantidad")
    protected double cantidad;
    @XmlElement(name = "Observaciones", required = true)
    protected String observaciones;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public short getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(short value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad artId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtId() {
        return artId;
    }

    /**
     * Define el valor de la propiedad artId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtId(String value) {
        this.artId = value;
    }

    /**
     * Obtiene el valor de la propiedad artDescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtDescripcion() {
        return artDescripcion;
    }

    /**
     * Define el valor de la propiedad artDescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtDescripcion(String value) {
        this.artDescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadesEnPack.
     * 
     */
    public short getUnidadesEnPack() {
        return unidadesEnPack;
    }

    /**
     * Define el valor de la propiedad unidadesEnPack.
     * 
     */
    public void setUnidadesEnPack(short value) {
        this.unidadesEnPack = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(double value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad observaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define el valor de la propiedad observaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

}
