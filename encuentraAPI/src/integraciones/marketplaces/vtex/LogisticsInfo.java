package integraciones.marketplaces.vtex;
import java.util.List;
public class LogisticsInfo
{

    private List<DeliveryIds> deliveryIds;
    private List<Slas> slas;
    private String deliveryChannel;
    
    public void setDeliveryIds(List<DeliveryIds> deliveryIds){
        this.deliveryIds = deliveryIds;
    }
    public List<DeliveryIds> getDeliveryIds(){
        return this.deliveryIds;
    }
	public String getDeliveryChannel() {
		return deliveryChannel;
	}
	public void setDeliveryChannel(String deliveryChannel) {
		this.deliveryChannel = deliveryChannel;
	}
	public List<Slas> getSlas() {
		return slas;
	}
	public void setSlas(List<Slas> slas) {
		this.slas = slas;
	}
	
	
}


