package beans.encuentra;

import java.text.DecimalFormat;

public class Tarjeta 
{
	private String titulo, bgcolor, icon, href, texto, footcolor, cantidadSTR, extra;
	private int idTarjeta, tipo, porcentaje, padre;
	private Double cantidad;
	private boolean decimales;
	
	
	
	public int getPadre() {
		return padre;
	}
	public void setPadre(int padre) {
		this.padre = padre;
	}
	public boolean isDecimales() {
		return decimales;
	}
	public void setDecimales(boolean decimales) {
		this.decimales = decimales;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getIdTarjeta() {
		return idTarjeta;
	}
	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Tarjeta() {
	}
	public String getFootcolor() {
		return footcolor;
	}
	public void setFootcolor(String footcolor) {
		this.footcolor = footcolor;
	}
	public String getCantidadSTR() 
	{
		if (this.decimales) {
		DecimalFormat formatea = new DecimalFormat("###,###.##");
		 
		return formatea.format(this.getCantidad());
		} else {
			return this.getCantidad().intValue()+"";
		}
	}
	public void setCantidadSTR(String cantidadSTR) {
		this.cantidadSTR = cantidadSTR;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
