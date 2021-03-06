
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
 *         &lt;element name="Conssdtaccionencuentra" type="{PpGg}SDTAccionEncuentra"/>
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
    "conssdtaccionencuentra"
})
@XmlRootElement(name = "AccionesEncuentra.ExecuteResponse")
public class AccionesEncuentraExecuteResponse {

    @XmlElement(name = "Conssdtaccionencuentra", required = true)
    protected SDTAccionEncuentra conssdtaccionencuentra;

    /**
     * Obtiene el valor de la propiedad conssdtaccionencuentra.
     * 
     * @return
     *     possible object is
     *     {@link SDTAccionEncuentra }
     *     
     */
    public SDTAccionEncuentra getConssdtaccionencuentra() {
        return conssdtaccionencuentra;
    }

    /**
     * Define el valor de la propiedad conssdtaccionencuentra.
     * 
     * @param value
     *     allowed object is
     *     {@link SDTAccionEncuentra }
     *     
     */
    public void setConssdtaccionencuentra(SDTAccionEncuentra value) {
        this.conssdtaccionencuentra = value;
    }

}
