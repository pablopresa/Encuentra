
package integraciones.erp.ascher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SdtCategorias.SdtCategoria complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SdtCategorias.SdtCategoria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CategoriaId" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="CategoriaDescrip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SdtCategorias.SdtCategoria", propOrder = {
    "categoriaId",
    "categoriaDescrip"
})
public class SdtCategoriasSdtCategoria {

    @XmlElement(name = "CategoriaId")
    protected short categoriaId;
    @XmlElement(name = "CategoriaDescrip", required = true)
    protected String categoriaDescrip;

    /**
     * Obtiene el valor de la propiedad categoriaId.
     * 
     */
    public short getCategoriaId() {
        return categoriaId;
    }

    /**
     * Define el valor de la propiedad categoriaId.
     * 
     */
    public void setCategoriaId(short value) {
        this.categoriaId = value;
    }

    /**
     * Obtiene el valor de la propiedad categoriaDescrip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaDescrip() {
        return categoriaDescrip;
    }

    /**
     * Define el valor de la propiedad categoriaDescrip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaDescrip(String value) {
        this.categoriaDescrip = value;
    }

}
