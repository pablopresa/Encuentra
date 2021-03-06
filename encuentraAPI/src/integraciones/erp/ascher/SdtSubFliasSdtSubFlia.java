
package integraciones.erp.ascher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SdtSubFlias.SdtSubFlia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SdtSubFlias.SdtSubFlia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubFliaId" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="SubFliaDsc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SdtSubFlias.SdtSubFlia", propOrder = {
    "subFliaId",
    "subFliaDsc"
})
public class SdtSubFliasSdtSubFlia {

    @XmlElement(name = "SubFliaId")
    protected short subFliaId;
    @XmlElement(name = "SubFliaDsc", required = true)
    protected String subFliaDsc;

    /**
     * Obtiene el valor de la propiedad subFliaId.
     * 
     */
    public short getSubFliaId() {
        return subFliaId;
    }

    /**
     * Define el valor de la propiedad subFliaId.
     * 
     */
    public void setSubFliaId(short value) {
        this.subFliaId = value;
    }

    /**
     * Obtiene el valor de la propiedad subFliaDsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubFliaDsc() {
        return subFliaDsc;
    }

    /**
     * Define el valor de la propiedad subFliaDsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubFliaDsc(String value) {
        this.subFliaDsc = value;
    }

}
