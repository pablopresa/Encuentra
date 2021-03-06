
package integraciones.erp.ascher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sdtarticuloswebpagina" type="{PpGg}SDTArticulosWebPagina"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sdtarticuloswebpagina"
})
@XmlRootElement(name = "CargaArticulosPaginado.Execute")
public class CargaArticulosPaginadoExecute {

    @XmlElement(name = "Sdtarticuloswebpagina", required = true)
    protected SDTArticulosWebPagina sdtarticuloswebpagina;

    /**
     * Obtiene el valor de la propiedad sdtarticuloswebpagina.
     * 
     * @return
     *     possible object is
     *     {@link SDTArticulosWebPagina }
     *     
     */
    public SDTArticulosWebPagina getSdtarticuloswebpagina() {
        return sdtarticuloswebpagina;
    }

    /**
     * Define el valor de la propiedad sdtarticuloswebpagina.
     * 
     * @param value
     *     allowed object is
     *     {@link SDTArticulosWebPagina }
     *     
     */
    public void setSdtarticuloswebpagina(SDTArticulosWebPagina value) {
        this.sdtarticuloswebpagina = value;
    }

}
