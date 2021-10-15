package beans.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CallBackPedido {
	
		private int idLlamada,idEstado,idEmpresa, printer;
		private Long idPedido;
		private String estampa,fechaProcesado,mensaje;
		private boolean procesado;
		
	
		
	public CallBackPedido(int idLlamada, int idEstado, int idEmpresa, Long idPedido, String estampa,
				String fechaProcesado, String mensaje, boolean procesado) {
			super();
			this.idLlamada = idLlamada;
			this.idEstado = idEstado;
			this.idEmpresa = idEmpresa;
			this.idPedido = idPedido;
			this.estampa = estampa;
			this.fechaProcesado = fechaProcesado;
			this.mensaje = mensaje;
			this.procesado = procesado;
		}

	

	public int getIdLlamada() {
		return idLlamada;
	}



	public void setIdLlamada(int idLlamada) {
		this.idLlamada = idLlamada;
	}



	public int getIdEstado() {
		return idEstado;
	}



	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}



	public int getIdEmpresa() {
		return idEmpresa;
	}



	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}



	public Long getIdPedido() {
		return idPedido;
	}



	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}



	public String getEstampa() {
		return estampa;
	}



	public void setEstampa(String estampa) {
		this.estampa = estampa;
	}



	public String getFechaProcesado() {
		return fechaProcesado;
	}



	public void setFechaProcesado(String fechaProcesado) {
		this.fechaProcesado = fechaProcesado;
	}



	public String getMensaje() {
		return mensaje;
	}



	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	public boolean isProcesado() {
		return procesado;
	}



	public void setProcesado(boolean procesado) {
		this.procesado = procesado;
	}



	public CallBackPedido() {
		// TODO Auto-generated constructor stub
			
		
	}



	public int getPrinter() {
		return printer;
	}



	public void setPrinter(int printer) {
		this.printer = printer;
	}
	

}
