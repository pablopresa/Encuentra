package integraciones.marketplaces.fenicio;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Documento
{
    private String tipo;

    private String numero;

    private String pais;

    public String getTipo ()
    {
        return tipo;
    }

    public void setTipo (String tipo)
    {
        this.tipo = tipo;
    }

    public String getNumero ()
    {
        return numero;
    }

    public void setNumero (String numero)
    {
        this.numero = numero;
    }

    public String getPais ()
    {
        return pais;
    }

    public void setPais (String pais)
    {
        this.pais = pais;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tipo = "+tipo+", numero = "+numero+", pais = "+pais+"]";
    }
}