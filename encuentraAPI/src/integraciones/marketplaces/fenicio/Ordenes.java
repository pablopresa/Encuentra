package integraciones.marketplaces.fenicio;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ordenes
{
    private String numeroOrden;

    private Lineas[] lineas;

    private String fechaRecuperada;

    private String estado;

    private String fechaAbandono;

    private String origen;

    private String fechaFin;

    private Pago pago;

    private String idOrden;

    private String motivoCancelacion;

    private String codigoTributario;

    private String razonSocial;

    private String importeTotal;

    private String fechaInicio;

    private String impuestos;

    private String observaciones;

    private String moneda;

    private Comprador comprador;

    private DireccionEnvio direccionFacturacion;

    private Entrega entrega;

    private String referencia;

    private String fechaCancelada;

    public String getNumeroOrden ()
    {
        return numeroOrden;
    }

    public void setNumeroOrden (String numeroOrden)
    {
        this.numeroOrden = numeroOrden;
    }

    public Lineas[] getLineas ()
    {
        return lineas;
    }

    public void setLineas (Lineas[] lineas)
    {
        this.lineas = lineas;
    }

    public String getFechaRecuperada ()
    {
        return fechaRecuperada;
    }

    public void setFechaRecuperada (String fechaRecuperada)
    {
        this.fechaRecuperada = fechaRecuperada;
    }

    public String getEstado ()
    {
        return estado;
    }

    public void setEstado (String estado)
    {
        this.estado = estado;
    }

    public String getFechaAbandono ()
    {
        return fechaAbandono;
    }

    public void setFechaAbandono (String fechaAbandono)
    {
        this.fechaAbandono = fechaAbandono;
    }

    public String getOrigen ()
    {
        return origen;
    }

    public void setOrigen (String origen)
    {
        this.origen = origen;
    }

    public String getFechaFin ()
    {
        return fechaFin;
    }

    public void setFechaFin (String fechaFin)
    {
        this.fechaFin = fechaFin;
    }

    public Pago getPago ()
    {
        return pago;
    }

    public void setPago (Pago pago)
    {
        this.pago = pago;
    }

    public String getIdOrden ()
    {
        return idOrden;
    }

    public void setIdOrden (String idOrden)
    {
        this.idOrden = idOrden;
    }

    public String getMotivoCancelacion ()
    {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion (String motivoCancelacion)
    {
        this.motivoCancelacion = motivoCancelacion;
    }

    public String getCodigoTributario ()
    {
        return codigoTributario;
    }

    public void setCodigoTributario (String codigoTributario)
    {
        this.codigoTributario = codigoTributario;
    }

    public String getRazonSocial ()
    {
        return razonSocial;
    }

    public void setRazonSocial (String razonSocial)
    {
        this.razonSocial = razonSocial;
    }

    public String getImporteTotal ()
    {
        return importeTotal;
    }

    public void setImporteTotal (String importeTotal)
    {
        this.importeTotal = importeTotal;
    }

    public String getFechaInicio ()
    {
        return fechaInicio;
    }

    public void setFechaInicio (String fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }

    public String getImpuestos ()
    {
        return impuestos;
    }

    public void setImpuestos (String impuestos)
    {
        this.impuestos = impuestos;
    }

    public String getObservaciones ()
    {
        return observaciones;
    }

    public void setObservaciones (String observaciones)
    {
        this.observaciones = observaciones;
    }

    public String getMoneda ()
    {
        return moneda;
    }

    public void setMoneda (String moneda)
    {
        this.moneda = moneda;
    }

    public Comprador getComprador ()
    {
        return comprador;
    }

    public void setComprador (Comprador comprador)
    {
        this.comprador = comprador;
    }

    public DireccionEnvio getDireccionFacturacion ()
    {
        return direccionFacturacion;
    }

    public void setDireccionFacturacion (DireccionEnvio direccionFacturacion)
    {
        this.direccionFacturacion = direccionFacturacion;
    }

    public Entrega getEntrega ()
    {
        return entrega;
    }

    public void setEntrega (Entrega entrega)
    {
        this.entrega = entrega;
    }

    public String getReferencia ()
    {
        return referencia;
    }

    public void setReferencia (String referencia)
    {
        this.referencia = referencia;
    }

    public String getFechaCancelada ()
    {
        return fechaCancelada;
    }

    public void setFechaCancelada (String fechaCancelada)
    {
        this.fechaCancelada = fechaCancelada;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [numeroOrden = "+numeroOrden+", lineas = "+lineas+", fechaRecuperada = "+fechaRecuperada+", estado = "+estado+", fechaAbandono = "+fechaAbandono+", origen = "+origen+", fechaFin = "+fechaFin+", pago = "+pago+", idOrden = "+idOrden+", motivoCancelacion = "+motivoCancelacion+", codigoTributario = "+codigoTributario+", razonSocial = "+razonSocial+", importeTotal = "+importeTotal+", fechaInicio = "+fechaInicio+", impuestos = "+impuestos+", observaciones = "+observaciones+", moneda = "+moneda+", comprador = "+comprador+", direccionFacturacion = "+direccionFacturacion+", entrega = "+entrega+", referencia = "+referencia+", fechaCancelada = "+fechaCancelada+"]";
    }
}