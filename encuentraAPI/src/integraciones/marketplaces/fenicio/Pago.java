package integraciones.marketplaces.fenicio;

public class Pago
{
    private String fechaCancelacion;

    private String codigo;

    private String estado;

    private String autorizacion;

    private String fechaVencimiento;

    private String bin;

    private String conector;

    private String importe;

    private String fechaPago;

    private String idExterno;

    private String moneda;

    private String id;

    private String cuotas;

    public String getFechaCancelacion ()
    {
        return fechaCancelacion;
    }

    public void setFechaCancelacion (String fechaCancelacion)
    {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getCodigo ()
    {
        return codigo;
    }

    public void setCodigo (String codigo)
    {
        this.codigo = codigo;
    }

    public String getEstado ()
    {
        return estado;
    }

    public void setEstado (String estado)
    {
        this.estado = estado;
    }

    public String getAutorizacion ()
    {
        return autorizacion;
    }

    public void setAutorizacion (String autorizacion)
    {
        this.autorizacion = autorizacion;
    }

    public String getFechaVencimiento ()
    {
        return fechaVencimiento;
    }

    public void setFechaVencimiento (String fechaVencimiento)
    {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getBin ()
    {
        return bin;
    }

    public void setBin (String bin)
    {
        this.bin = bin;
    }

    public String getConector ()
    {
        return conector;
    }

    public void setConector (String conector)
    {
        this.conector = conector;
    }

    public String getImporte ()
    {
        return importe;
    }

    public void setImporte (String importe)
    {
        this.importe = importe;
    }

    public String getFechaPago ()
    {
        return fechaPago;
    }

    public void setFechaPago (String fechaPago)
    {
        this.fechaPago = fechaPago;
    }

    public String getIdExterno ()
    {
        return idExterno;
    }

    public void setIdExterno (String idExterno)
    {
        this.idExterno = idExterno;
    }

    public String getMoneda ()
    {
        return moneda;
    }

    public void setMoneda (String moneda)
    {
        this.moneda = moneda;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCuotas ()
    {
        return cuotas;
    }

    public void setCuotas (String cuotas)
    {
        this.cuotas = cuotas;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [fechaCancelacion = "+fechaCancelacion+", codigo = "+codigo+", estado = "+estado+", autorizacion = "+autorizacion+", fechaVencimiento = "+fechaVencimiento+", bin = "+bin+", conector = "+conector+", importe = "+importe+", fechaPago = "+fechaPago+", idExterno = "+idExterno+", moneda = "+moneda+", id = "+id+", cuotas = "+cuotas+"]";
    }
}