package integraciones.marketplaces.vtex;

public class DeliveryWindow {
	
	private String startDateUtc;
	private String endDateUtc;
	private Integer price;
	
	public String getStartDateUtc() {
		return startDateUtc;
	}
	public void setStartDateUtc(String startDateUtc) {
		this.startDateUtc = startDateUtc;
	}
	public String getEndDateUtc() {
		return endDateUtc;
	}
	public void setEndDateUtc(String endDateUtc) {
		this.endDateUtc = endDateUtc;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
