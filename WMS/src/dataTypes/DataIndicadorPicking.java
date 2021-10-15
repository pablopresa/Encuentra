package dataTypes;

import java.text.DecimalFormat;
import java.util.List;

public class DataIndicadorPicking 
{
	
	private String fecha,destinos,inicioPicking,finPicking,nickname,inicioVerificacion,finVerificacion;
	private int id,solicitada,pickeadas,verificadas,tiempoPicking,idUsuario,tiempoVerificacion;
	private double unidadesXHora,unidadesXHora_verif,tiempoPausa;
	private List<DataIndicadorPickingLinea> lineas;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDestinos() {
		return destinos;
	}
	public void setDestinos(String destinos) {
		this.destinos = destinos;
	}
	public String getInicioPicking() {
		return inicioPicking;
	}
	public void setInicioPicking(String inicioPicking) {
		this.inicioPicking = inicioPicking;
	}
	public String getFinPicking() {
		return finPicking;
	}
	public void setFinPicking(String finPicking) {
		this.finPicking = finPicking;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getInicioVerificacion() {
		return inicioVerificacion;
	}
	public void setInicioVerificacion(String inicioVerificacion) {
		this.inicioVerificacion = inicioVerificacion;
	}
	public String getFinVerificacion() {
		return finVerificacion;
	}
	public void setFinVerificacion(String finVerificacion) {
		this.finVerificacion = finVerificacion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSolicitada() {
		return solicitada;
	}
	public void setSolicitada(int solicitada) {
		this.solicitada = solicitada;
	}
	public int getPickeadas() {
		return pickeadas;
	}
	public void setPickeadas(int pickeadas) {
		this.pickeadas = pickeadas;
	}
	public int getVerificadas() {
		return verificadas;
	}
	public void setVerificadas(int verificadas) {
		this.verificadas = verificadas;
	}
	public int getTiempoPicking() {
		return tiempoPicking;
	}
	public void setTiempoPicking(int tiempoPicking) {
		this.tiempoPicking = tiempoPicking;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getTiempoVerificacion() {
		return tiempoVerificacion;
	}
	public void setTiempoVerificacion(int tiempoVerificacion) {
		this.tiempoVerificacion = tiempoVerificacion;
	}
	public double getUnidadesXHora() {
		return unidadesXHora;
	}
	public void setUnidadesXHora(double unidadesXHora) {
		this.unidadesXHora = unidadesXHora;
	}
	public double getUnidadesXHora_verif() {
		return unidadesXHora_verif;
	}
	public void setUnidadesXHora_verif(double unidadesXHora_verif) {
		this.unidadesXHora_verif = unidadesXHora_verif;
	}
	public DataIndicadorPicking(String fecha, String destinos, String inicioPicking, String finPicking, String nickname,
			String inicioVerificacion, String finVerificacion, int id, int solicitada, int pickeadas, int verificadas,
			int tiempoPicking, int idUsuario, int tiempoVerificacion, double unidadesXHora,
			double unidadesXHora_verif, double tiempoPausa) 
	{
		
		this.fecha = fecha;
		this.destinos = destinos;
		this.inicioPicking = inicioPicking;
		this.finPicking = finPicking;
		this.nickname = nickname;
		this.inicioVerificacion = inicioVerificacion;
		this.finVerificacion = finVerificacion;
		this.id = id;
		this.solicitada = solicitada;
		this.pickeadas = pickeadas;
		this.verificadas = verificadas;
		this.tiempoPicking = tiempoPicking;
		this.idUsuario = idUsuario;
		this.tiempoVerificacion = tiempoVerificacion;
		this.unidadesXHora = unidadesXHora;
		this.unidadesXHora_verif = unidadesXHora_verif;
		this.tiempoPausa = tiempoPausa;
		     
	}
	
	public List<DataIndicadorPickingLinea> getLineas() 
	{
		return lineas;
	}
	
	public void setLineas(List<DataIndicadorPickingLinea> lineas) 
	{
		this.lineas = lineas;
	}
	public double getTiempoPausa() {
		return tiempoPausa;
	}
	public void setTiempoPausa(double tiempoPausa) {
		this.tiempoPausa = tiempoPausa;
	}
	
	
	
}
