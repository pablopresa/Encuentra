package integraciones.couriers.correoUY;


public class Numero
{
private String nro_puerta;

public String getNro_puerta ()
{
return nro_puerta;
}

public void setNro_puerta (String nro_puerta)
{
this.nro_puerta = nro_puerta;
}

@Override
public String toString()
{
return "ClassPojo [nro_puerta = "+nro_puerta+"]";
}
}

