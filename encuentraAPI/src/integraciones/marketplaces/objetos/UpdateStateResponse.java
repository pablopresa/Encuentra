package integraciones.marketplaces.objetos;

public class UpdateStateResponse {
	private String id;
	private boolean status;
	private String msg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public UpdateStateResponse() {
		this.id = "";
		this.status = false;
		this.msg = "";
	}
}
