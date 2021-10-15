package integraciones.marketplaces.fenicio.apiComercios;

public class Atributos
{
    private String descripcion;

    private String nuevo;

    private String categoria;

    private String genero;

    private String composicion;

    public String getDescripcion ()
    {
        return descripcion;
    }

    public void setDescripcion (String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getNuevo ()
    {
        return nuevo;
    }

    public void setNuevo (String nuevo)
    {
        this.nuevo = nuevo;
    }

    public String getCategoria ()
    {
        return categoria;
    }

    public void setCategoria (String categoria)
    {
        this.categoria = categoria;
    }

    public String getGenero ()
    {
        return genero;
    }

    public void setGenero (String genero)
    {
        this.genero = genero;
    }

    public String getComposicion ()
    {
        return composicion;
    }

    public void setComposicion (String composicion)
    {
        this.composicion = composicion;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [descripcion = "+descripcion+", nuevo = "+nuevo+", categoria = "+categoria+", genero = "+genero+", composicion = "+composicion+"]";
    }
}