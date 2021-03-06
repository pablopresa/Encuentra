
package integraciones.erp.ascher;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfSDTRenglonesPreparacionPedidoEncuentra.SDTRenglonPreparacionPedidoEncuentra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSDTRenglonesPreparacionPedidoEncuentra.SDTRenglonPreparacionPedidoEncuentra">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDTRenglonesPreparacionPedidoEncuentra.SDTRenglonPreparacionPedidoEncuentra" type="{PpGg}SDTRenglonesPreparacionPedidoEncuentra.SDTRenglonPreparacionPedidoEncuentra" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSDTRenglonesPreparacionPedidoEncuentra.SDTRenglonPreparacionPedidoEncuentra", propOrder = {
    "sdtRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra"
})
public class ArrayOfSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra {

    @XmlElement(name = "SDTRenglonesPreparacionPedidoEncuentra.SDTRenglonPreparacionPedidoEncuentra")
    protected List<SDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra> sdtRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra;

    /**
     * Gets the value of the sdtRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sdtRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra }
     * 
     * 
     */
    public List<SDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra> getSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra() {
        if (sdtRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra == null) {
            sdtRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra = new ArrayList<>();
        }
        return this.sdtRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra;
    }

}
