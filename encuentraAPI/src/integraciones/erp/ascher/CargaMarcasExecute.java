
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
 *         &lt;element name="Sdtmarcas" type="{PpGg}ArrayOfSdtMarcas.SdtMarca"/>
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
    "sdtmarcas"
})
@XmlRootElement(name = "CargaMarcas.Execute")
public class CargaMarcasExecute {

    @XmlElement(name = "Sdtmarcas", required = true)
    protected ArrayOfSdtMarcasSdtMarca sdtmarcas;

    /**
     * Obtiene el valor de la propiedad sdtmarcas.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSdtMarcasSdtMarca }
     *     
     */
    public ArrayOfSdtMarcasSdtMarca getSdtmarcas() {
        return sdtmarcas;
    }

    /**
     * Define el valor de la propiedad sdtmarcas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSdtMarcasSdtMarca }
     *     
     */
    public void setSdtmarcas(ArrayOfSdtMarcasSdtMarca value) {
        this.sdtmarcas = value;
    }

}
