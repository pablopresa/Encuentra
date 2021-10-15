
package integraciones.erp.ascher;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfSDTPedidosEncuentraLista.SDTPedidosEncuentraListaItem complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSDTPedidosEncuentraLista.SDTPedidosEncuentraListaItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SDTPedidosEncuentraLista.SDTPedidosEncuentraListaItem" type="{PpGg}SDTPedidosEncuentraLista.SDTPedidosEncuentraListaItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSDTPedidosEncuentraLista.SDTPedidosEncuentraListaItem", propOrder = {
    "sdtPedidosEncuentraListaSDTPedidosEncuentraListaItem"
})
public class ArrayOfSDTPedidosEncuentraListaSDTPedidosEncuentraListaItem {

    @XmlElement(name = "SDTPedidosEncuentraLista.SDTPedidosEncuentraListaItem")
    protected List<SDTPedidosEncuentraListaSDTPedidosEncuentraListaItem> sdtPedidosEncuentraListaSDTPedidosEncuentraListaItem;

    /**
     * Gets the value of the sdtPedidosEncuentraListaSDTPedidosEncuentraListaItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sdtPedidosEncuentraListaSDTPedidosEncuentraListaItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSDTPedidosEncuentraListaSDTPedidosEncuentraListaItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SDTPedidosEncuentraListaSDTPedidosEncuentraListaItem }
     * 
     * 
     */
    public List<SDTPedidosEncuentraListaSDTPedidosEncuentraListaItem> getSDTPedidosEncuentraListaSDTPedidosEncuentraListaItem() {
        if (sdtPedidosEncuentraListaSDTPedidosEncuentraListaItem == null) {
            sdtPedidosEncuentraListaSDTPedidosEncuentraListaItem = new ArrayList<>();
        }
        return this.sdtPedidosEncuentraListaSDTPedidosEncuentraListaItem;
    }

}
