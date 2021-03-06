package integraciones.marketplaces.fenicio;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FenicioAPIorden
{
    private String msj;

    private Ordenes orden;

    private String error;

    public String getMsj ()
    {
        return msj;
    }

    public void setMsj (String msj)
    {
        this.msj = msj;
    }

    public Ordenes getOrden ()
    {
        return orden;
    }

    public void setOrden (Ordenes orden)
    {
        this.orden = orden;
    }

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    
    
    
    @Override
    public String toString()
    {
        return "ClassPojo [msj = "+msj+", orden = "+orden+", error = "+error+"]";
    }
}
