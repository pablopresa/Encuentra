package integraciones.couriers.pedidosYA;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Waypoints
{
    private String instructions;

    private String addressAdditional;

    private String addressStreet;

    private String city;

    private String phone;

    private String latitude;

    private String name;

    private String type;

    private String longitude;

    private String order;

    public String getInstructions ()
    {
        return instructions;
    }

    public void setInstructions (String instructions)
    {
        this.instructions = instructions;
    }

    public String getAddressAdditional ()
    {
        return addressAdditional;
    }

    public void setAddressAdditional (String addressAdditional)
    {
        this.addressAdditional = addressAdditional;
    }

    public String getAddressStreet ()
    {
        return addressStreet;
    }

    public void setAddressStreet (String addressStreet)
    {
        this.addressStreet = addressStreet;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getOrder ()
    {
        return order;
    }

    public void setOrder (String order)
    {
        this.order = order;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [instructions = "+instructions+", addressAdditional = "+addressAdditional+", addressStreet = "+addressStreet+", city = "+city+", phone = "+phone+", latitude = "+latitude+", name = "+name+", type = "+type+", longitude = "+longitude+", order = "+order+"]";
    }
}