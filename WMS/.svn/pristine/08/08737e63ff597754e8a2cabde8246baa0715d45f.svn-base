package eCommerce_jsonObjectsII;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Compras 
{
    private Compra compra;

    private Cliente cliente;

    private String etiqueta, horaDesde,horaHasta;
    
    private Envio envio;
    
    



	public String getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}

	public String getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}

	public Compra getCompra ()
    {
        return compra;
    }

    public void setCompra (Compra compra)
    {
        this.compra = compra;
    }

    public Cliente getCliente ()
    {
        return cliente;
    }

    public void setCliente (Cliente cliente)
    {
        this.cliente = cliente;
    }

    public String getEtiqueta ()
    {
        return etiqueta;
    }

    public void setEtiqueta (String etiqueta)
    {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [compra = "+compra+", cliente = "+cliente+", etiqueta = "+etiqueta+"]";
    }

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
	
}