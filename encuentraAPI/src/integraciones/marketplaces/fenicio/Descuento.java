package integraciones.marketplaces.fenicio;

public class Descuento
{
    private String codigo;

    private String monto;

    private String origen;

    private String nombre;

    public String getCodigo ()
    {
        return codigo;
    }

    public void setCodigo (String codigo)
    {
        this.codigo = codigo;
    }

    public String getMonto ()
    {
        return monto;
    }

    public void setMonto (String monto)
    {
        this.monto = monto;
    }

    public String getOrigen ()
    {
        return origen;
    }

    public void setOrigen (String origen)
    {
        this.origen = origen;
    }

    public String getNombre ()
    {
        return nombre;
    }

    public void setNombre (String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [codigo = "+codigo+", monto = "+monto+", origen = "+origen+", nombre = "+nombre+"]";
    }
}