package integraciones.marketplaces.vtex;
public class DeliveryIds
{
    private String courierId;

    private String courierName;

    private String dockId;

    private int quantity;

    private String warehouseId;

    private String accountCarrierName;

    public void setCourierId(String courierId){
        this.courierId = courierId;
    }
    public String getCourierId(){
        return this.courierId;
    }
    public void setCourierName(String courierName){
        this.courierName = courierName;
    }
    public String getCourierName(){
        return this.courierName;
    }
    public void setDockId(String dockId){
        this.dockId = dockId;
    }
    public String getDockId(){
        return this.dockId;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void setWarehouseId(String warehouseId){
        this.warehouseId = warehouseId;
    }
    public String getWarehouseId(){
        return this.warehouseId;
    }
    public void setAccountCarrierName(String accountCarrierName){
        this.accountCarrierName = accountCarrierName;
    }
    public String getAccountCarrierName(){
        return this.accountCarrierName;
    }
}


