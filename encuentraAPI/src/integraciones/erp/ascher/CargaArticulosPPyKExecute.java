
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
 *         &lt;element name="Sdtarticuloswebxgrupo" type="{PpGg}SDTArticulosWebxGrupo"/>
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
    "sdtarticuloswebxgrupo"
})
@XmlRootElement(name = "CargaArticulosPPyK.Execute")
public class CargaArticulosPPyKExecute {

    @XmlElement(name = "Sdtarticuloswebxgrupo", required = true)
    protected SDTArticulosWebxGrupo sdtarticuloswebxgrupo;

    /**
     * Obtiene el valor de la propiedad sdtarticuloswebxgrupo.
     * 
     * @return
     *     possible object is
     *     {@link SDTArticulosWebxGrupo }
     *     
     */
    public SDTArticulosWebxGrupo getSdtarticuloswebxgrupo() {
        return sdtarticuloswebxgrupo;
    }

    /**
     * Define el valor de la propiedad sdtarticuloswebxgrupo.
     * 
     * @param value
     *     allowed object is
     *     {@link SDTArticulosWebxGrupo }
     *     
     */
    public void setSdtarticuloswebxgrupo(SDTArticulosWebxGrupo value) {
        this.sdtarticuloswebxgrupo = value;
    }

}
