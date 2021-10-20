package integraciones.marketplaces.vtex;
public class Slas
{
    private String id;
    private String name;
    private String shippingEstimate;
    private DeliveryWindow deliveryWindow;
    private int price;
    private String deliveryChannel;
    private PickupStoreInfo pickupStoreInfo;
    private String polygonName;
    private String lockTTL;
    private String pickupPointId;
    private String transitTime;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setShippingEstimate(String shippingEstimate){
        this.shippingEstimate = shippingEstimate;
    }
    public String getShippingEstimate(){
        return this.shippingEstimate;
    }
    public void setDeliveryWindow(DeliveryWindow deliveryWindow){
        this.deliveryWindow = deliveryWindow;
    }
    public DeliveryWindow getDeliveryWindow(){
        return this.deliveryWindow;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public void setDeliveryChannel(String deliveryChannel){
        this.deliveryChannel = deliveryChannel;
    }
    public String getDeliveryChannel(){
        return this.deliveryChannel;
    }
    public void setPickupStoreInfo(PickupStoreInfo pickupStoreInfo){
        this.pickupStoreInfo = pickupStoreInfo;
    }
    public PickupStoreInfo getPickupStoreInfo(){
        return this.pickupStoreInfo;
    }
    public void setPolygonName(String polygonName){
        this.polygonName = polygonName;
    }
    public String getPolygonName(){
        return this.polygonName;
    }
    public void setLockTTL(String lockTTL){
        this.lockTTL = lockTTL;
    }
    public String getLockTTL(){
        return this.lockTTL;
    }
    public void setPickupPointId(String pickupPointId){
        this.pickupPointId = pickupPointId;
    }
    public String getPickupPointId(){
        return this.pickupPointId;
    }
    public void setTransitTime(String transitTime){
        this.transitTime = transitTime;
    }
    public String getTransitTime(){
        return this.transitTime;
    }
}


