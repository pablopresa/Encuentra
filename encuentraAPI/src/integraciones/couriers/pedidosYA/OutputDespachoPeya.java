package integraciones.couriers.pedidosYA;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputDespachoPeya
{
    private String deliveryTime;

    private String notificationMail;

    private String weight;

    private String shareLocationUrl;

    private String cancelCode;

    private Waypoints[] waypoints;

    private String referenceId;

    private String expiresAt;

    private String volume;

    private String lastUpdated;

    private String createdAt;

    private String isTest;

    private Price price;

    private String proofOfDelivery;

    private String id;

    private String cancelReason;

    private Items[] items;

    private String status;

    public String getDeliveryTime ()
    {
        return deliveryTime;
    }

    public void setDeliveryTime (String deliveryTime)
    {
        this.deliveryTime = deliveryTime;
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

    public String getShareLocationUrl ()
    {
        return shareLocationUrl;
    }

    public void setShareLocationUrl (String shareLocationUrl)
    {
        this.shareLocationUrl = shareLocationUrl;
    }

    public String getCancelCode ()
    {
        return cancelCode;
    }

    public void setCancelCode (String cancelCode)
    {
        this.cancelCode = cancelCode;
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

    public String getExpiresAt ()
    {
        return expiresAt;
    }

    public void setExpiresAt (String expiresAt)
    {
        this.expiresAt = expiresAt;
    }

    public String getVolume ()
    {
        return volume;
    }

    public void setVolume (String volume)
    {
        this.volume = volume;
    }

    public String getLastUpdated ()
    {
        return lastUpdated;
    }

    public void setLastUpdated (String lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getIsTest ()
    {
        return isTest;
    }

    public void setIsTest (String isTest)
    {
        this.isTest = isTest;
    }

    public Price getPrice ()
    {
        return price;
    }

    public void setPrice (Price price)
    {
        this.price = price;
    }

    public String getProofOfDelivery ()
    {
        return proofOfDelivery;
    }

    public void setProofOfDelivery (String proofOfDelivery)
    {
        this.proofOfDelivery = proofOfDelivery;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCancelReason ()
    {
        return cancelReason;
    }

    public void setCancelReason (String cancelReason)
    {
        this.cancelReason = cancelReason;
    }

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [deliveryTime = "+deliveryTime+", notificationMail = "+notificationMail+", weight = "+weight+", shareLocationUrl = "+shareLocationUrl+", cancelCode = "+cancelCode+", waypoints = "+waypoints+", referenceId = "+referenceId+", expiresAt = "+expiresAt+", volume = "+volume+", lastUpdated = "+lastUpdated+", createdAt = "+createdAt+", isTest = "+isTest+", price = "+price+", proofOfDelivery = "+proofOfDelivery+", id = "+id+", cancelReason = "+cancelReason+", items = "+items+", status = "+status+"]";
    }
}