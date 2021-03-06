package integraciones.couriers.pedidosYA;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import integraciones.couriers.soydelivery.ProductoSDL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DespachoPeYA
{
    private String volume;

    private String deliveryTime;

    private boolean isTest;

    private String notificationMail;

    private String weight;

    private Items[] items;

    private Waypoints[] waypoints;

    private String referenceId;

    public String getVolume ()
    {
        return volume;
    }

    public void setVolume (String volume)
    {
        this.volume = volume;
    }

    public String getDeliveryTime ()
    {
        return deliveryTime;
    }

    public void setDeliveryTime (String deliveryTime)
    {
        this.deliveryTime = deliveryTime;
    }

    public boolean getIsTest ()
    {
        return isTest;
    }

    public void setIsTest (boolean isTest)
    {
        this.isTest = isTest;
    }

    public String getNotificationMail ()
    {
        return notificationMail;
    }

    public void setNotificationMail (String notificationMail)
    {
        this.notificationMail = notificationMail;
    }

    public String getWeight ()
    {
        return weight;
    }

    public void setWeight (String weight)
    {
        this.weight = weight;
    }

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    public Waypoints[] getWaypoints ()
    {
        return waypoints;
    }

    public void setWaypoints (Waypoints[] waypoints)
    {
        this.waypoints = waypoints;
    }

    public String getReferenceId ()
    {
        return referenceId;
    }

    public void setReferenceId (String referenceId)
    {
        this.referenceId = referenceId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [volume = "+volume+", deliveryTime = "+deliveryTime+", isTest = "+isTest+", notificationMail = "+notificationMail+", weight = "+weight+", items = "+items+", waypoints = "+waypoints+", referenceId = "+referenceId+"]";
    }
}