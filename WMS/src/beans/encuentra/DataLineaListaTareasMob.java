package beans.encuentra;

import beans.Usuario;
import dataTypes.DataIDDescripcion;

public class DataLineaListaTareasMob implements Comparable
{
	private int main, tEstimado, tejecucion;
	private int tarea;
	private double velocidad;
	private DataIDDescripcion estado;
	private DataIDDescripcion tipo;
	private DataIDDescripcion area;
	private DataIDDescripcion responsable;
	private int porcentaje;
	private String descripcion;
	private int cantidad;
	private int idRepo;
	private int resolucion;
	private String desResolucion;
	private String tiempoTranscurrido;
	private String dia; 
	private String mes;
	private String anio;
	private int indiceU;
	private int indiceM;
	private double indice;
	private Usuario usuario;
	
	
	
	public double getIndice() {
		return indice;
	}
	public void setIndice(double indice) {
		this.indice = indice;
	}
	public int getMain() {
		return main;
	}
	public void setMain(int main) {
		this.main = main;
	}
	public int getTarea() {
		return tarea;
	}
	public void setTarea(int tarea) {
		this.tarea = tarea;
	}
	public DataIDDescripcion getEstado() {
		return estado;
	}
	public void setEstado(DataIDDescripcion estado) {
		this.estado = estado;
	}
	public DataIDDescripcion getTipo() {
		return tipo;
	}
	public void setTipo(DataIDDescripcion tipo) {
		this.tipo = tipo;
	}
	public int getResolucion() {
		return resolucion;
	}
	public void setResolucion(int resolucion) {
		this.resolucion = resolucion;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getIdRepo() {
		return idRepo;
	}
	public void setIdRepo(int idRepo) {
		this.idRepo = idRepo;
	}
	public DataLineaListaTareasMob() {
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	public void setTiempoTranscurrido(String tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}
	public String getDesResolucion() {
		return desResolucion;
	}
	public void setDesResolucion(String desResolucion) {
		this.desResolucion = desResolucion;
	}
	public int getIndiceU() {
		return indiceU;
	}
	public void setIndiceU(int indiceU) {
		this.indiceU = indiceU;
	}
	public int getIndiceM() {
		return indiceM;
	}
	public void setIndiceM(int indiceM) {
		this.indiceM = indiceM;
	}
	public DataIDDescripcion getArea() {
		return area;
	}
	public void setArea(DataIDDescripcion area) {
		this.area = area;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int gettEstimado() {
		return tEstimado;
	}
	public void settEstimado(int tEstimado) {
		this.tEstimado = tEstimado;
	}
	public int getTejecucion() {
		return tejecucion;
	}
	public void setTejecucion(int tejecucion) {
		this.tejecucion = tejecucion;
	}
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	
	
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	@Override
	public int compareTo(Object o) 
	{
		DataLineaListaTareasMob dl = (DataLineaListaTareasMob)o;        

		  if((this.dia+this.mes+this.anio).compareToIgnoreCase(dl.getDia()+dl.getMes()+dl.getAnio()) == 0) 
          { 
              return 0;
          } 
          else if((this.dia+this.mes+this.anio).compareToIgnoreCase(dl.getDia()+dl.getMes()+dl.getAnio()) > 0)
          { 
          	
              return 1; 
          }
          else
          {
          	return -1;
          }
	        /*
	        	if(this.getId()==dl.getId())
	        	{
	        		if(this.descripcion.compareToIgnoreCase(dl.getDescripcion()) == 0) 
		            { 
		                return 0;
		            } 
		            else if(this.descripcion.compareToIgnoreCase(dl.getDescripcion()) > 0)
		            { 
		            	
		                return 1; 
		            }
		            else
		            {
		            	return -1;
		            }

	        	}
	        	
	        	else if(this.getId()>dl.getId())
	        	{
	        		return 1;
	        	}
	        	else
	        	{
	        		return -1;
	        	}
	        	*/	
	}
	public DataIDDescripcion getResponsable() {
		return responsable;
	}
	public void setResponsable(DataIDDescripcion responsable) {
		this.responsable = responsable;
	}
	

}
