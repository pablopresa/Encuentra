package integraciones.couriers.correoUY;


public class Calle
{
private String idCalle;

private String nombre_normalizado;

public String getIdCalle ()
{
return idCalle;
}

public void setIdCalle (String idCalle)
{
this.idCalle = idCalle;
}

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
return "ClassPojo [idCalle = "+idCalle+", nombre_normalizado = "+nombre_normalizado+"]";
}
}