package jsonObjects;


public class JSONFactDetalle
{
private String codigoArticulo;

private int cantidad;

private int numeroLinea;

private int unidades;

public String getCodigoArticulo ()
{
return codigoArticulo;
}

public void setCodigoArticulo (String codigoArticulo)
{
this.codigoArticulo = codigoArticulo;
}


public int getCantidad() {
	return cantidad;
}

public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

public int getNumeroLinea() {
	return numeroLinea;
}

public void setNumeroLinea(int numeroLinea) {
	this.numeroLinea = numeroLinea;
}

public int getUnidades() {
	return unidades;
}

public void setUnidades(int unidades) {
	this.unidades = unidades;
}




public JSONFactDetalle(String codigoArticulo, int cantidad, int numeroLinea,
		int unidades) {
	this.codigoArticulo = codigoArticulo;
	this.cantidad = cantidad;
	this.numeroLinea = numeroLinea;
	this.unidades = unidades;
}

@Override
public String toString()
{
return "ClassPojo [codigoArticulo = "+codigoArticulo+", cantidad = "+cantidad+", numeroLinea = "+numeroLinea+", unidades = "+unidades+"]";
}
}