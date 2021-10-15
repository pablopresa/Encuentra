package eCommerce_jsonObjectsII;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Pedido 
{
    private String total;

    
    private String status;
    
    
	private Compras[] compras;

    private String msg;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Compras[] getCompras ()
    {
        return compras;
    }

    public void setCompras (Compras[] compras)
    {
        this.compras = compras;
    }

    
	public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", status = "+status+", compras = "+compras+", msg = "+msg+"]";
    }
}