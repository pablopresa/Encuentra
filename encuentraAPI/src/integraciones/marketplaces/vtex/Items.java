package integraciones.marketplaces.vtex;
public class Items
{
    private String uniqueId;

    private String id;

    private String productId;

    private String ean;

    private int quantity;

    private String seller;

    private String name;

    private String refId;

    private int price;

    private int listPrice;

    private String manualPrice;

    private String sellerSku;

    private int sellingPrice;

    private boolean isGift;

    private String shippingPrice;

    private PriceDefinition priceDefinition;

    private int costPrice;

    public void setUniqueId(String uniqueId){
        this.uniqueId = uniqueId;
    }
    public String getUniqueId(){
        return this.uniqueId;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductId(){
        return this.productId;
    }
    public void setEan(String ean){
        this.ean = ean;
    }
    public String getEan(){
        return this.ean;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void setSeller(String seller){
        this.seller = seller;
    }
    public String getSeller(){
        return this.seller;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setRefId(String refId){
        this.refId = refId;
    }
    public String getRefId(){
        return this.refId;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public void setListPrice(int listPrice){
        this.listPrice = listPrice;
    }
    public int getListPrice(){
        return this.listPrice;
    }
    public void setManualPrice(String manualPrice){
        this.manualPrice = manualPrice;
    }
    public String getManualPrice(){
        return this.manualPrice;
    }
    public void setSellerSku(String sellerSku){
        this.sellerSku = sellerSku;
    }
    public String getSellerSku(){
        return this.sellerSku;
    }
    public void setSellingPrice(int sellingPrice){
        this.sellingPrice = sellingPrice;
    }
    public int getSellingPrice(){
        return this.sellingPrice;
    }
    public void setIsGift(boolean isGift){
        this.isGift = isGift;
    }
    public boolean getIsGift(){
        return this.isGift;
    }
    public void setShippingPrice(String shippingPrice){
        this.shippingPrice = shippingPrice;
    }
    public String getShippingPrice(){
        return this.shippingPrice;
    }
    public void setPriceDefinition(PriceDefinition priceDefinition){
        this.priceDefinition = priceDefinition;
    }
    public PriceDefinition getPriceDefinition(){
        return this.priceDefinition;
    }
    public void setCostPrice(int costPrice){
        this.costPrice = costPrice;
    }
    public int getCostPrice(){
        return this.costPrice;
    }
}


