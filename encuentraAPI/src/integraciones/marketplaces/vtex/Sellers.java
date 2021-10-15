package integraciones.marketplaces.vtex;
public class Sellers
{
    private String id;

    private String name;

    private String logo;

    private String fulfillmentEndpoint;

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
    public void setLogo(String logo){
        this.logo = logo;
    }
    public String getLogo(){
        return this.logo;
    }
    public void setFulfillmentEndpoint(String fulfillmentEndpoint){
        this.fulfillmentEndpoint = fulfillmentEndpoint;
    }
    public String getFulfillmentEndpoint(){
        return this.fulfillmentEndpoint;
    }
}


