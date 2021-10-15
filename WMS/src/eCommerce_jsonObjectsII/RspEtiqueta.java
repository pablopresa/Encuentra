package eCommerce_jsonObjectsII;

public class RspEtiqueta 
{
	private String status;

    private String etiqueta;

    private String msg;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getEtiqueta ()
    {
        return etiqueta;
    }

    public void setEtiqueta (String etiqueta)
    {
        this.etiqueta = etiqueta;
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
        return "ClassPojo [status = "+status+", etiqueta = "+etiqueta+", msg = "+msg+"]";
    }
}
