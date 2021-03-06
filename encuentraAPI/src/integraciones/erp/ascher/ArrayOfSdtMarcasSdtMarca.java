
package integraciones.erp.ascher;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfSdtMarcas.SdtMarca complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSdtMarcas.SdtMarca">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SdtMarcas.SdtMarca" type="{PpGg}SdtMarcas.SdtMarca" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSdtMarcas.SdtMarca", propOrder = {
    "sdtMarcasSdtMarca"
})
public class ArrayOfSdtMarcasSdtMarca {

    @XmlElement(name = "SdtMarcas.SdtMarca")
    protected List<SdtMarcasSdtMarca> sdtMarcasSdtMarca;

    /**
     * Gets the value of the sdtMarcasSdtMarca property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sdtMarcasSdtMarca property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSdtMarcasSdtMarca().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SdtMarcasSdtMarca }
     * 
     * 
     */
    public List<SdtMarcasSdtMarca> getSdtMarcasSdtMarca() {
        if (sdtMarcasSdtMarca == null) {
            sdtMarcasSdtMarca = new ArrayList<>();
        }
        return this.sdtMarcasSdtMarca;
    }

}
