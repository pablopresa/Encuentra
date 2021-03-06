package integraciones.couriers.soydelivery;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class trackingSDL {

	private String Pedido_estado_id;

    private String Pedido_estado_desc;

    private String Delivery_location;

    private String Fecha_estimada_entrega;

    private String Error_code;

    private String Delivery_telefono;

    private String Fecha_entrega;

    private String Error_desc;

    private String Delivery_nombre_apellido;

    private String Franja_horaria;

    public String getPedido_estado_id ()
    {
        return Pedido_estado_id;
    }

    public void setPedido_estado_id (String Pedido_estado_id)
    {
        this.Pedido_estado_id = Pedido_estado_id;
    }

    public String getPedido_estado_desc ()
    {
        return Pedido_estado_desc;
    }

    public void setPedido_estado_desc (String Pedido_estado_desc)
    {
        this.Pedido_estado_desc = Pedido_estado_desc;
    }

    public String getDelivery_location ()
    {
        return Delivery_location;
    }

    public void setDelivery_location (String Delivery_location)
    {
        this.Delivery_location = Delivery_location;
    }

    public String getFecha_estimada_entrega ()
    {
        return Fecha_estimada_entrega;
    }

    public void setFecha_estimada_entrega (String Fecha_estimada_entrega)
    {
        this.Fecha_estimada_entrega = Fecha_estimada_entrega;
    }

    public String getError_code ()
    {
        return Error_code;
    }

    public void setError_code (String Error_code)
    {
        this.Error_code = Error_code;
    }

    public String getDelivery_telefono ()
    {
        return Delivery_telefono;
    }

    public void setDelivery_telefono (String Delivery_telefono)
    {
        this.Delivery_telefono = Delivery_telefono;
    }

    public String getFecha_entrega ()
    {
        return Fecha_entrega;
    }

    public void setFecha_entrega (String Fecha_entrega)
    {
        this.Fecha_entrega = Fecha_entrega;
    }

    public String getError_desc ()
    {
        return Error_desc;
    }

    public void setError_desc (String Error_desc)
    {
        this.Error_desc = Error_desc;
    }

    public String getDelivery_nombre_apellido ()
    {
        return Delivery_nombre_apellido;
    }

    public void setDelivery_nombre_apellido (String Delivery_nombre_apellido)
    {
        this.Delivery_nombre_apellido = Delivery_nombre_apellido;
    }

    public String getFranja_horaria ()
    {
        return Franja_horaria;
    }

    public void setFranja_horaria (String Franja_horaria)
    {
        this.Franja_horaria = Franja_horaria;
    }
    
    public trackingSDL() {}
}
