package eCommerce_jsonObjectsII;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Compra
{
    private String importe;

    private String id;

    private String estado;

    private String fecha;

    private List<Items> items;

    private String moneda;
    
    private boolean cNc;
    private String sucursal;
    
    private String montoEnvio;


    private String metodoPago;
    
    private String origen;
    
    private String operador;
    
    private String serie;
    
    private String idVenta;
    
    private String idCanal;
    
    private String observaciones;
    
    
    
    
    public boolean iscNc() {
		return cNc;
	}

	public void setcNc(boolean cNc) {
		this.cNc = cNc;
	}

	public String getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(String idVenta) {
		this.idVenta = idVenta;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
    
    
    public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getMontoEnvio() {
		return montoEnvio;
	}

	public void setMontoEnvio(String montoEnvio) {
		this.montoEnvio = montoEnvio;
	}

    

    public String getImporte ()
    {
        return importe;
    }

    public void setImporte (String importe)
    {
        this.importe = importe;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEstado ()
    {
        return estado;
    }

    public void setEstado (String estado)
    {
        this.estado = estado;
    }

    public String getFecha ()
    {
        return fecha;
    }

    public void setFecha (String fecha)
    {
        this.fecha = fecha;
    }

    public List<Items> getItems ()
    {
        return items;
    }

    public void setItems (List<Items> items)
    {
        this.items = items;
    }

    public String getMoneda ()
    {
        return moneda;
    }

    public void setMoneda (String moneda)
    {
        this.moneda = moneda;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [importe = "+importe+", id = "+id+", estado = "+estado+", fecha = "+fecha+", items = "+items+", moneda = "+moneda+", observaciones = "+observaciones+"]";
    }

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
