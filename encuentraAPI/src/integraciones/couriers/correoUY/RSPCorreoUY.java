package integraciones.couriers.correoUY;

public class RSPCorreoUY
{
    private String idTipoClasificacion;

    private String codigoPostal;

    private String puntoY;

    private String zona_paquete;

    private String puntoX;

    private Direccion direccion;

    private String codigoPostalAmpliado;

    private String idPunto;

    private String error;

    private String srid;

    public String getIdTipoClasificacion ()
    {
        return idTipoClasificacion;
    }

    public void setIdTipoClasificacion (String idTipoClasificacion)
    {
        this.idTipoClasificacion = idTipoClasificacion;
    }

    public String getCodigoPostal ()
    {
        return codigoPostal;
    }

    public void setCodigoPostal (String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    public String getPuntoY ()
    {
        return puntoY;
    }

    public void setPuntoY (String puntoY)
    {
        this.puntoY = puntoY;
    }

    public String getZona_paquete ()
    {
        return zona_paquete;
    }

    public void setZona_paquete (String zona_paquete)
    {
        this.zona_paquete = zona_paquete;
    }

    public String getPuntoX ()
    {
        return puntoX;
    }

    public void setPuntoX (String puntoX)
    {
        this.puntoX = puntoX;
    }

    public Direccion getDireccion ()
    {
        return direccion;
    }

    public void setDireccion (Direccion direccion)
    {
        this.direccion = direccion;
    }

    public String getCodigoPostalAmpliado ()
    {
        return codigoPostalAmpliado;
    }

    public void setCodigoPostalAmpliado (String codigoPostalAmpliado)
    {
        this.codigoPostalAmpliado = codigoPostalAmpliado;
    }

    public String getIdPunto ()
    {
        return idPunto;
    }

    public void setIdPunto (String idPunto)
    {
        this.idPunto = idPunto;
    }

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    public String getSrid ()
    {
        return srid;
    }

    public void setSrid (String srid)
    {
        this.srid = srid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [idTipoClasificacion = "+idTipoClasificacion+", codigoPostal = "+codigoPostal+", puntoY = "+puntoY+", zona_paquete = "+zona_paquete+", puntoX = "+puntoX+", direccion = "+direccion+", codigoPostalAmpliado = "+codigoPostalAmpliado+", idPunto = "+idPunto+", error = "+error+", srid = "+srid+"]";
    }
}