package main.process_ecommerce.mercadoLibreObjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Pickup 
{
    private String buyer_id;

    private String id;

    private String date_ready;

    private String status;

    private Pickup_person pickup_person;

    private String item_id;

    private String store_id;

    private String date_created;

    private String order_id;

    private Store_info store_info;

    public String getBuyer_id ()
    {
        return buyer_id;
    }

    public void setBuyer_id (String buyer_id)
    {
        this.buyer_id = buyer_id;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDate_ready ()
    {
        return date_ready;
    }

    public void setDate_ready (String date_ready)
    {
        this.date_ready = date_ready;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Pickup_person getPickup_person ()
    {
        return pickup_person;
    }

    public void setPickup_person (Pickup_person pickup_person)
    {
        this.pickup_person = pickup_person;
    }

    public String getItem_id ()
    {
        return item_id;
    }

    public void setItem_id (String item_id)
    {
        this.item_id = item_id;
    }

    public String getStore_id ()
    {
        return store_id;
    }

    public void setStore_id (String store_id)
    {
        this.store_id = store_id;
    }

    public String getDate_created ()
    {
        return date_created;
    }

    public void setDate_created (String date_created)
    {
        this.date_created = date_created;
    }

    public String getOrder_id ()
    {
        return order_id;
    }

    public void setOrder_id (String order_id)
    {
        this.order_id = order_id;
    }

    public Store_info getStore_info ()
    {
        return store_info;
    }

    public void setStore_info (Store_info store_info)
    {
        this.store_info = store_info;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [buyer_id = "+buyer_id+", id = "+id+", date_ready = "+date_ready+", status = "+status+", pickup_person = "+pickup_person+", item_id = "+item_id+", store_id = "+store_id+", date_created = "+date_created+", order_id = "+order_id+", store_info = "+store_info+"]";
    }
}