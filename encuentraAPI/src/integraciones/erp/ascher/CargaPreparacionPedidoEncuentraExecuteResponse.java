
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
 *         &lt;element name="Conssdtcabezalpreparacionpedidoencuentra" type="{PpGg}SDTCabezalPreparacionPedidoEncuentra"/>
 *         &lt;element name="Conssdtrenglonespreparacionpedidoencuentra" type="{PpGg}ArrayOfSDTRenglonesPreparacionPedidoEncuentra.SDTRenglonPreparacionPedidoEncuentra"/>
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
    "conssdtcabezalpreparacionpedidoencuentra",
    "conssdtrenglonespreparacionpedidoencuentra"
})
@XmlRootElement(name = "CargaPreparacionPedidoEncuentra.ExecuteResponse")
public class CargaPreparacionPedidoEncuentraExecuteResponse {

    @XmlElement(name = "Conssdtcabezalpreparacionpedidoencuentra", required = true)
    protected SDTCabezalPreparacionPedidoEncuentra conssdtcabezalpreparacionpedidoencuentra;
    @XmlElement(name = "Conssdtrenglonespreparacionpedidoencuentra", required = true)
    protected ArrayOfSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra conssdtrenglonespreparacionpedidoencuentra;

    /**
     * Obtiene el valor de la propiedad conssdtcabezalpreparacionpedidoencuentra.
     * 
     * @return
     *     possible object is
     *     {@link SDTCabezalPreparacionPedidoEncuentra }
     *     
     */
    public SDTCabezalPreparacionPedidoEncuentra getConssdtcabezalpreparacionpedidoencuentra() {
        return conssdtcabezalpreparacionpedidoencuentra;
    }

    /**
     * Define el valor de la propiedad conssdtcabezalpreparacionpedidoencuentra.
     * 
     * @param value
     *     allowed object is
     *     {@link SDTCabezalPreparacionPedidoEncuentra }
     *     
     */
    public void setConssdtcabezalpreparacionpedidoencuentra(SDTCabezalPreparacionPedidoEncuentra value) {
        this.conssdtcabezalpreparacionpedidoencuentra = value;
    }

    /**
     * Obtiene el valor de la propiedad conssdtrenglonespreparacionpedidoencuentra.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra }
     *     
     */
    public ArrayOfSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra getConssdtrenglonespreparacionpedidoencuentra() {
        return conssdtrenglonespreparacionpedidoencuentra;
    }

    /**
     * Define el valor de la propiedad conssdtrenglonespreparacionpedidoencuentra.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra }
     *     
     */
    public void setConssdtrenglonespreparacionpedidoencuentra(ArrayOfSDTRenglonesPreparacionPedidoEncuentraSDTRenglonPreparacionPedidoEncuentra value) {
        this.conssdtrenglonespreparacionpedidoencuentra = value;
    }

}
