
package integraciones.erp.ascher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SDTArticulosWebPagina complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SDTArticulosWebPagina">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Parte" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="Cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Articulos" type="{PpGg}ConsPagSDTArticulosWebPagina.Articulos"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDTArticulosWebPagina", propOrder = {
    "parte",
    "cantidad",
    "articulos"
})
public class SDTArticulosWebPagina {

    @XmlElement(name = "Parte")
    protected short parte;
    @XmlElement(name = "Cantidad")
    protected int cantidad;
    @XmlElement(name = "Articulos", required = true)
    protected ConsPagSDTArticulosWebPaginaArticulos articulos;

    /**
     * Obtiene el valor de la propiedad parte.
     * 
     */
    public short getParte() {
        return parte;
    }

    /**
     * Define el valor de la propiedad parte.
     * 
     */
    public void setParte(short value) {
        this.parte = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad articulos.
     * 
     * @return
     *     possible object is
     *     {@link ConsPagSDTArticulosWebPaginaArticulos }
     *     
     */
    public ConsPagSDTArticulosWebPaginaArticulos getArticulos() {
        return articulos;
    }

    /**
     * Define el valor de la propiedad articulos.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsPagSDTArticulosWebPaginaArticulos }
     *     
     */
    public void setArticulos(ConsPagSDTArticulosWebPaginaArticulos value) {
        this.articulos = value;
    }

}
