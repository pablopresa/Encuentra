package integraciones.marketplaces.fenicio;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entrega
{
    private String etiqueta;

    private String tipo;

    private String estado;

    private String codigoTracking;

    private Horario horario;

    private DireccionEnvio direccionEnvio;

    private ServicioEntrega servicioEntrega;

    private String local;

    private String destinatario;

    public String getEtiqueta ()
    {
        return etiqueta;
    }

    public void setEtiqueta (String etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    public String getTipo ()
    {
        return tipo;
    }

    public void setTipo (String tipo)
    {
        this.tipo = tipo;
    }

    public String getEstado ()
    {
        return estado;
    }

    public void setEstado (String estado)
    {
        this.estado = estado;
    }

    public String getCodigoTracking ()
    {
        return codigoTracking;
    }

    public void setCodigoTracking (String codigoTracking)
    {
        this.codigoTracking = codigoTracking;
    }

    public Horario getHorario ()
    {
        return horario;
    }

    public void setHorario (Horario horario)
    {
        this.horario = horario;
    }

    public DireccionEnvio getDireccionEnvio ()
    {
        return direccionEnvio;
    }

    public void setDireccionEnvio (DireccionEnvio direccionEnvio)
    {
        this.direccionEnvio = direccionEnvio;
    }

    public ServicioEntrega getServicioEntrega ()
    {
        return servicioEntrega;
    }

    public void setServicioEntrega (ServicioEntrega servicioEntrega)
    {
        this.servicioEntrega = servicioEntrega;
    }

    public String getLocal ()
    {
        return local;
    }

    public void setLocal (String local)
    {
        this.local = local;
    }

    public String getDestinatario ()
    {
        return destinatario;
    }

    public void setDestinatario (String destinatario)
    {
        this.destinatario = destinatario;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [etiqueta = "+etiqueta+", tipo = "+tipo+", estado = "+estado+", codigoTracking = "+codigoTracking+", horario = "+horario+", direccionEnvio = "+direccionEnvio+", servicioEntrega = "+servicioEntrega+", local = "+local+", destinatario = "+destinatario+"]";
    }
}