package eCommerce_jsonObjectsII;
public class PrecioLista
{
    private String UYU;

    public String getUYU ()
    {
        return UYU;
    }

    public void setUYU (String UYU)
    {
        this.UYU = UYU;
    }

    @Override
    public String toString()
    {
    	return (UYU==null || UYU.isEmpty())?"":UYU;
    }
}