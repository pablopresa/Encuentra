package integraciones.erp.oracle.tata.bean;
public class Stocks
{
    private String loc;

    private Items[] items;

    public String getLoc ()
    {
        return loc;
    }

    public void setLoc (String loc)
    {
        this.loc = loc;
    }

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [loc = "+loc+", items = "+items+"]";
    }
}