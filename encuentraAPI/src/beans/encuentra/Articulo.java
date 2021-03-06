package beans.encuentra;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Articulo {

	private String idArticulo;

    private String descripcion;

    private String especificaciones;

    private String peso;

    private String barras;

    private String ancho;

    private Familias[] familias;

    private String alto;

    private String prof;

    private String seriado;

    private String[] barrasL;

    public String getIdArticulo ()
    {
        return idArticulo;
    }

    public void setIdArticulo (String idArticulo)
    {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion ()
    {
        return descripcion;
    }

    public void setDescripcion (String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getEspecificaciones ()
    {
        return especificaciones;
    }

    public void setEspecificaciones (String especificaciones)
    {
        this.especificaciones = especificaciones;
    }

    public String getPeso ()
    {
        return peso;
    }

    public void setPeso (String peso)
    {
        this.peso = peso;
    }

    public String getBarras ()
    {
        return barras;
    }

    public void setBarras (String barras)
    {
        this.barras = barras;
    }

    public String getAncho ()
    {
        return ancho;
    }

    public void setAncho (String ancho)
    {
        this.ancho = ancho;
    }

    public Familias[] getFamilias ()
    {
        return familias;
    }

    public void setFamilias (Familias[] familias)
    {
        this.familias = familias;
    }

    public String getAlto ()
    {
        return alto;
    }

    public void setAlto (String alto)
    {
        this.alto = alto;
    }

    public String getProf ()
    {
        return prof;
    }

    public void setProf (String prof)
    {
        this.prof = prof;
    }

    public String getSeriado ()
    {
        return seriado;
    }

    public void setSeriado (String seriado)
    {
        this.seriado = seriado;
    }

    public String[] getBarrasL ()
    {
        return barrasL;
    }

    public void setBarrasL (String[] barrasL)
    {
        this.barrasL = barrasL;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [idArticulo = "+idArticulo+", descripcion = "+descripcion+", especificaciones = "+especificaciones+", peso = "+peso+", barras = "+barras+", ancho = "+ancho+", familias = "+familias+", alto = "+alto+", prof = "+prof+", seriado = "+seriado+", barrasL = "+barrasL+"]";
    }  
	
}
