
package integraciones.erp.ascher;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfSDTArticulosWebPagina.Articulo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSDTArticulosWebPagina.Articulo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDTArticulosWebPagina.Articulo" type="{PpGg}SDTArticulosWebPagina.Articulo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSDTArticulosWebPagina.Articulo", propOrder = {
    "sdtArticulosWebPaginaArticulo"
})
public class ArrayOfSDTArticulosWebPaginaArticulo {

    @XmlElement(name = "SDTArticulosWebPagina.Articulo")
    protected List<SDTArticulosWebPaginaArticulo> sdtArticulosWebPaginaArticulo;

    /**
     * Gets the value of the sdtArticulosWebPaginaArticulo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sdtArticulosWebPaginaArticulo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSDTArticulosWebPaginaArticulo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SDTArticulosWebPaginaArticulo }
     * 
     * 
     */
    public List<SDTArticulosWebPaginaArticulo> getSDTArticulosWebPaginaArticulo() {
        if (sdtArticulosWebPaginaArticulo == null) {
            sdtArticulosWebPaginaArticulo = new ArrayList<>();
        }
        return this.sdtArticulosWebPaginaArticulo;
    }

}
