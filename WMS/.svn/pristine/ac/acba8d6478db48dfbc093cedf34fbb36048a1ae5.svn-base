package models;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.google.gson.Gson;

import beans.elementoPicking;
import beans.encuentra.DataLineaRepo;

public class PickingBulto {

	private Hashtable<String, List<DataLineaRepo>> articulo_lineasRepo;
	private Hashtable<String, elementoPicking> elementos;
	private List<elementoPicking> listaElementos;
	private Hashtable<String, String> barras;	
	private int cantidadTotal;
	private int count;
	private int idPicking;
	private String idBulto;
	private String ubicacion;
	
	//JSON
	private String barrasJson;
	private String elementos_json;
	
	public Hashtable<String, List<DataLineaRepo>> getArticulo_lineasRepo() {
		return articulo_lineasRepo;
	}
	public void setArticulo_lineasRepo(Hashtable<String, List<DataLineaRepo>> articulo_lineasRepo) {
		this.articulo_lineasRepo = articulo_lineasRepo;
	}
	public Hashtable<String, elementoPicking> getElementos() {
		return elementos;
	}
	public void setElementos(Hashtable<String, elementoPicking> elementos) {
		this.elementos = elementos;
		this.listaElementos = new ArrayList<>(elementos.values());
		this.elementos_json = new Gson().toJson(elementos);
	}
	public List<elementoPicking> getListaElementos() {
		return listaElementos;
	}
	public void setListaElementos(List<elementoPicking> listaElementos) {
		this.listaElementos = listaElementos;
	}
	public Hashtable<String, String> getBarras() {
		return barras;
	}
	public void setBarras(Hashtable<String, String> barras) {
		this.barras = barras;
		this.barrasJson = new Gson().toJson(barras);
	}
	public int getCantidadTotal() {
		return cantidadTotal;
	}
	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBarrasJson() {
		return barrasJson;
	}
	public void setBarrasJson(String barrasJson) {
		this.barrasJson = barrasJson;
	}
	public String getElementos_json() {
		return elementos_json;
	}
	public void setElementos_json(String elementos_json) {
		this.elementos_json = elementos_json;
	}
	public int getIdPicking() {
		return idPicking;
	}
	public void setIdPicking(int idPicking) {
		this.idPicking = idPicking;
	}
	
	
	public void refreshListaElementos() {
		this.listaElementos = new ArrayList<>(elementos.values());
		this.elementos_json = new Gson().toJson(elementos);
	}
	public String getIdBulto() {
		return idBulto;
	}
	public void setIdBulto(String idBulto) {
		this.idBulto = idBulto;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
