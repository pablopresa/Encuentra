
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
 *         &lt;element name="Sdtlineassubflias" type="{PpGg}ArrayOfSdtLineasSubFlias.SdtLineaSubFlias"/>
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
    "sdtlineassubflias"
})
@XmlRootElement(name = "CargaFamilias.ExecuteResponse")
public class CargaFamiliasExecuteResponse {

    @XmlElement(name = "Sdtlineassubflias", required = true)
    protected ArrayOfSdtLineasSubFliasSdtLineaSubFlias sdtlineassubflias;

    /**
     * Obtiene el valor de la propiedad sdtlineassubflias.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSdtLineasSubFliasSdtLineaSubFlias }
     *     
     */
    public ArrayOfSdtLineasSubFliasSdtLineaSubFlias getSdtlineassubflias() {
        return sdtlineassubflias;
    }

    /**
     * Define el valor de la propiedad sdtlineassubflias.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSdtLineasSubFliasSdtLineaSubFlias }
     *     
     */
    public void setSdtlineassubflias(ArrayOfSdtLineasSubFliasSdtLineaSubFlias value) {
        this.sdtlineassubflias = value;
    }

}
