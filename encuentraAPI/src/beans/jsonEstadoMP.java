package beans;

public class jsonEstadoMP {
	
	private String idMarketPlace;
	private int id;
	private Long idPedido;
	private String json;
	private int canal;
	private int idEmpresa;
	
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public int getCanal() {
		return canal;
	}
	public void setCanal(int canal) {
		this.canal = canal;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdMarketPlace() {
		return idMarketPlace;
	}
	public void setIdMarketPlace(String idMarketPlace) {
		this.idMarketPlace = idMarketPlace;
	}
	
	public jsonEstadoMP(){
		
	}
	
	public jsonEstadoMP(String idMarketPlace, int id, Long idPedido, String json, int canal, int idEmpresa) {
		super();
		this.idMarketPlace = idMarketPlace;
		this.id = id;
		this.idPedido = idPedido;
		this.json = json;
		this.canal = canal;
		this.idEmpresa = idEmpresa;
	}
	
}
