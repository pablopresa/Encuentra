package eCommerce_jsonObjectsII;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EncuentraPedidoArticulo 
{
	private int cantidad,procesada,origen,distribucionAfecta,esCupon,cantidadRegalo;
	private String articulo, distribucionAfectaSTR, variacion, subTracking;
	private Double importe;
	private boolean clickCollect;
	
	
	public int getCantidadRegalo() {
		return cantidadRegalo;
	}
	public void setCantidadRegalo(int cantidadRegalo) {
		this.cantidadRegalo = cantidadRegalo;
	}
	
	
	public String getSubTracking() {
		return subTracking;
	}
	public void setSubTracking(String subTracking) {
		this.subTracking = subTracking;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getProcesada() {
		return procesada;
	}
	public void setProcesada(int procesada) {
		this.procesada = procesada;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getDistribucionAfecta() {
		return distribucionAfecta;
	}
	public void setDistribucionAfecta(int distribucionAfecta) {
		this.distribucionAfecta = distribucionAfecta;
	}
	public String getDistribucionAfectaSTR() {
		return distribucionAfectaSTR;
	}
	public void setDistribucionAfectaSTR(String distribucionAfectaSTR) {
		this.distribucionAfectaSTR = distribucionAfectaSTR;
	}
	public String getVariacion() {
		return variacion;
	}
	public void setVariacion(String variacion) {
		this.variacion = variacion;
	}
	public int getEsCupon() {
		return esCupon;
	}
	public void setEsCupon(int esCupon) {
		this.esCupon = esCupon;
	}
	public boolean isClickCollect() {
		return clickCollect;
	}
	public void setClickCollect(boolean clickCollect) {
		this.clickCollect = clickCollect;
	}
	
	
	
	
}
