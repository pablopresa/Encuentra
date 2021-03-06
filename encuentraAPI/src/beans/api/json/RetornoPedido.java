package beans.api.json;

public class RetornoPedido
{
    private String[] Ubicaciones;

    private String Pedido;

    private String Cliente;

    private String Disponible_Retiro;

    public String[] getUbicaciones ()
    {
        return Ubicaciones;
    }

    public void setUbicaciones (String[] Ubicaciones)
    {
        this.Ubicaciones = Ubicaciones;
    }

    public String getPedido ()
    {
        return Pedido;
    }

    public void setPedido (String Pedido)
    {
        this.Pedido = Pedido;
    }

    public String getCliente ()
    {
        return Cliente;
    }

    public void setCliente (String Cliente)
    {
        this.Cliente = Cliente;
    }

    public String getDisponible_Retiro ()
    {
        return Disponible_Retiro;
    }

    public void setDisponible_Retiro (String Disponible_Retiro)
    {
        this.Disponible_Retiro = Disponible_Retiro;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Ubicaciones = "+Ubicaciones+", Pedido = "+Pedido+", Cliente = "+Cliente+", Disponible_Retiro = "+Disponible_Retiro+"]";
    }
}