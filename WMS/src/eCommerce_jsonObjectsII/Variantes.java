package eCommerce_jsonObjectsII;

import java.util.List;

public class Variantes
{
    private String codigo;

    private List<Presentaciones> presentaciones;

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public String getCodigo(){
        return this.codigo;
    }
    public void setPresentaciones(List<Presentaciones> presentaciones){
        this.presentaciones = presentaciones;
    }
    public List<Presentaciones> getPresentaciones(){
        return this.presentaciones;
    }
}