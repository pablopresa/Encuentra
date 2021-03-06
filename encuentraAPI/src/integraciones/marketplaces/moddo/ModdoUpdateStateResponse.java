package integraciones.marketplaces.moddo;

import beans.datatypes.DataIDDescripcion;
import integraciones.marketplaces.objetos.UpdateStateResponse;

public class ModdoUpdateStateResponse {
	private String response;
	private String originalOrderNumber;
	private String orderNumber;
	private String message;
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getOriginalOrderNumber() {
		return originalOrderNumber;
	}
	public void setOriginalOrderNumber(String originalOrderNumber) {
		this.originalOrderNumber = originalOrderNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public UpdateStateResponse darRespuesta() {
		UpdateStateResponse data = new UpdateStateResponse();
		try {
			data.setId(this.getOrderNumber());
			data.setMsg(this.message);
			data.setStatus(this.response.equalsIgnoreCase("ok"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
}
