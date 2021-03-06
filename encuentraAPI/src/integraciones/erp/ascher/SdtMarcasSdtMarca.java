
package integraciones.erp.ascher;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SdtMarcas.SdtMarca complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SdtMarcas.SdtMarca">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MarcaId" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="MarcaDescrip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MarcaEnUso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SdtMarcas.SdtMarca", propOrder = {
    "marcaId",
    "marcaDescrip",
    "marcaEnUso"
})
public class SdtMarcasSdtMarca {

    @XmlElement(name = "MarcaId")
    protected byte marcaId;
    @XmlElement(name = "MarcaDescrip", required = true)
    protected String marcaDescrip;
    @XmlElement(name = "MarcaEnUso", required = true)
    protected String marcaEnUso;

    /**
     * Obtiene el valor de la propiedad marcaId.
     * 
     */
    public byte getMarcaId() {
        return marcaId;
    }

    /**
     * Define el valor de la propiedad marcaId.
     * 
     */
    public void setMarcaId(byte value) {
        this.marcaId = value;
    }

    /**
     * Obtiene el valor de la propiedad marcaDescrip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcaDescrip() {
        return marcaDescrip;
    }

    /**
     * Define el valor de la propiedad marcaDescrip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcaDescrip(String value) {
        this.marcaDescrip = value;
    }

    /**
     * Obtiene el valor de la propiedad marcaEnUso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarcaEnUso() {
        return marcaEnUso;
    }

    /**
     * Define el valor de la propiedad marcaEnUso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarcaEnUso(String value) {
        this.marcaEnUso = value;
    }

}
