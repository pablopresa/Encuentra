package integraciones.couriers.correoUY;

public class Localidad
{
    private String nombre_normalizado;

    public String getNombre_normalizado ()
    {
        return nombre_normalizado;
    }

    public void setNombre_normalizado (String nombre_normalizado)
    {
        this.nombre_normalizado = nombre_normalizado;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nombre_normalizado = "+nombre_normalizado+"]";
    }
}