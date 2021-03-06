package integraciones.marketplaces.fenicio;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lineas
{
    private Descuento [] descuentos;

    private String precio;

    private String cantidad;

    private String cantidadRegalo;

    private String codigoPrecio;

    private String sku;

    private String nombre;

    private String atributos;
    
    

    public Descuento [] getDescuentos ()
    {
        return descuentos;
    }

    public void setDescuentos (Descuento [] descuentos)
    {
        this.descuentos = descuentos;
    }

    public String getPrecio ()
    {
        return precio;
    }

    public void setPrecio (String precio)
    {
        this.precio = precio;
    }

    public String getCantidad ()
    {
        return cantidad;
    }

    public void setCantidad (String cantidad)
    {
        this.cantidad = cantidad;
    }

    public String getCantidadRegalo ()
    {
        return cantidadRegalo;
    }

    public void setCantidadRegalo (String cantidadRegalo)
    {
        this.cantidadRegalo = cantidadRegalo;
    }

    public String getCodigoPrecio ()
    {
        return codigoPrecio;
    }

    public void setCodigoPrecio (String codigoPrecio)
    {
        this.codigoPrecio = codigoPrecio;
    }

    public String getSku ()
    {
        return sku;
    }

    public void setSku (String sku)
    {
        this.sku = sku;
    }

    public String getNombre ()
    {
        return nombre;
    }

    public void setNombre (String nombre)
    {
        this.nombre = nombre;
    }

    public String getAtributos ()
    {
        return atributos;
    }

    public void setAtributos (String atributos)
    {
        this.atributos = atributos;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [descuentos = "+descuentos+", precio = "+precio+", cantidad = "+cantidad+", cantidadRegalo = "+cantidadRegalo+", codigoPrecio = "+codigoPrecio+", sku = "+sku+", nombre = "+nombre+", atributos = "+atributos+"]";
    }
}