package integraciones.couriers.pedidosYA;

public class CallBackPeYA
{
    private Data data;

    private String generated;

    private String topic;

    private String id;

    private String transmitted;

    private String referenceId;

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    public String getGenerated ()
    {
        return generated;
    }

    public void setGenerated (String generated)
    {
        this.generated = generated;
    }

    public String getTopic ()
    {
        return topic;
    }

    public void setTopic (String topic)
    {
        this.topic = topic;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTransmitted ()
    {
        return transmitted;
    }

    public void setTransmitted (String transmitted)
    {
        this.transmitted = transmitted;
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
        return "ClassPojo [data = "+data+", generated = "+generated+", topic = "+topic+", id = "+id+", transmitted = "+transmitted+", referenceId = "+referenceId+"]";
    }
}
			