package jsonObjects;

public class RespuestaMIR
{
    private String etiqueta;

    private String codigoBarra;

    public String getEtiqueta ()
    {
        return etiqueta;
    }

    public void setEtiqueta (String etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    public String getCodigoBarra ()
    {
        return codigoBarra;
    }

    public void setCodigoBarra (String codigoBarra)
    {
        this.codigoBarra = codigoBarra;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [etiqueta = "+etiqueta+", codigoBarra = "+codigoBarra+"]";
    }
}
	