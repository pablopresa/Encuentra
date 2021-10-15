package eCommerce_jsonObjectsII;

import java.util.List;

public class Productos
{
    private String codigo;

    private List<Variantes> variantes;

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public String getCodigo(){
        return this.codigo;
    }
    public void setVariantes(List<Variantes> variantes){
        this.variantes = variantes;
    }
    public List<Variantes> getVariantes(){
        return this.variantes;
    }
}