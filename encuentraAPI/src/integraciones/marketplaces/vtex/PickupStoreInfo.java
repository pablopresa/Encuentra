package integraciones.marketplaces.vtex;
public class PickupStoreInfo
{
    private String additionalInfo;

    private Address address;

    private String dockId;

    private String friendlyName;

    private boolean isPickupStore;

    public void setAdditionalInfo(String additionalInfo){
        this.additionalInfo = additionalInfo;
    }
    public String getAdditionalInfo(){
        return this.additionalInfo;
    }
    public void setAddress(Address address){
        this.address = address;
    }
    public Address getAddress(){
        return this.address;
    }
    public void setDockId(String dockId){
        this.dockId = dockId;
    }
    public String getDockId(){
        return this.dockId;
    }
    public void setFriendlyName(String friendlyName){
        this.friendlyName = friendlyName;
    }
    public String getFriendlyName(){
        return this.friendlyName;
    }
    public void setIsPickupStore(boolean isPickupStore){
        this.isPickupStore = isPickupStore;
    }
    public boolean getIsPickupStore(){
        return this.isPickupStore;
    }
}


