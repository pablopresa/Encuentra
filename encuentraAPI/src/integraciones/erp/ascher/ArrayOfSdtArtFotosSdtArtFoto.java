
package integraciones.erp.ascher;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfSdtArtFotos.SdtArtFoto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSdtArtFotos.SdtArtFoto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SdtArtFotos.SdtArtFoto" type="{PpGg}SdtArtFotos.SdtArtFoto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSdtArtFotos.SdtArtFoto", propOrder = {
    "sdtArtFotosSdtArtFoto"
})
public class ArrayOfSdtArtFotosSdtArtFoto {

    @XmlElement(name = "SdtArtFotos.SdtArtFoto")
    protected List<SdtArtFotosSdtArtFoto> sdtArtFotosSdtArtFoto;

    /**
     * Gets the value of the sdtArtFotosSdtArtFoto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sdtArtFotosSdtArtFoto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSdtArtFotosSdtArtFoto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SdtArtFotosSdtArtFoto }
     * 
     * 
     */
    public List<SdtArtFotosSdtArtFoto> getSdtArtFotosSdtArtFoto() {
        if (sdtArtFotosSdtArtFoto == null) {
            sdtArtFotosSdtArtFoto = new ArrayList<>();
        }
        return this.sdtArtFotosSdtArtFoto;
    }

}
