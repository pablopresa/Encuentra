package integraciones.couriers.ues;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class eventosTrackingUES {
	private List<trackingUES> trackings;

	public List<trackingUES> getTrackings() {
		return trackings;
	}

	public void setTrackings(List<trackingUES> trackings) {
		this.trackings = trackings;
	}
	
	public eventosTrackingUES() {
		
	}
}
