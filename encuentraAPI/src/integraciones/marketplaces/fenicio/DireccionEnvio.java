package integraciones.marketplaces.fenicio;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DireccionEnvio
{
    private String latitud;

    private String longitud;

    private String estado;

    private String codigoPostal;

    private String numeroPuerta;

    private String calle;

    private String numeroApto;

    private String observaciones;

    private String localidad;

    private String pais;

    public String getLatitud ()
    {
        return latitud;
    }

    public void setLatitud (String latitud)
    {
        this.latitud = latitud;
    }

    public String getLongitud ()
    {
        return longitud;
    }

    public void setLongitud (String longitud)
    {
        this.longitud = longitud;
    }

    public String getEstado ()
    {
        return estado;
    }

    public void setEstado (String estado)
    {
        this.estado = estado;
    }

    public String getCodigoPostal ()
    {
        return codigoPostal;
    }

    public void setCodigoPostal (String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    public String getNumeroPuerta ()
    {
        return numeroPuerta;
    }

    public void setNumeroPuerta (String numeroPuerta)
    {
        this.numeroPuerta = numeroPuerta;
    }

    public String getCalle ()
    {
        return calle;
    }

    public void setCalle (String calle)
    {
        this.calle = calle;
    }

    public String getNumeroApto ()
    {
        return numeroApto;
    }

    public void setNumeroApto (String numeroApto)
    {
        this.numeroApto = numeroApto;
    }

    public String getObservaciones ()
    {
        return observaciones;
    }

    public void setObservaciones (String observaciones)
    {
        this.observaciones = observaciones;
    }

    public String getLocalidad ()
    {
        return localidad;
    }

    public void setLocalidad (String localidad)
    {
        this.localidad = localidad;
    }

    public String getPais ()
    {
        return pais;
    }

    public void setPais (String pais)
    {
        this.pais = pais;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [latitud = "+latitud+", longitud = "+longitud+", estado = "+estado+", codigoPostal = "+codigoPostal+", numeroPuerta = "+numeroPuerta+", calle = "+calle+", numeroApto = "+numeroApto+", observaciones = "+observaciones+", localidad = "+localidad+", pais = "+pais+"]";
    }
}