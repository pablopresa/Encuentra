package beans.api.json;

public class JSONRespuestaDACPegoteResponse 
{
	private String result;

    private JSONRespuestaDACPegote WS_pegote;

    private String xmlns;

    public String getResult ()
    {
        return result;
    }

    public void setResult (String result)
    {
        this.result = result;
    }

    public JSONRespuestaDACPegote getWS_pegote ()
    {
        return WS_pegote;
    }

    public void setWS_pegote (JSONRespuestaDACPegote WS_pegote)
    {
        this.WS_pegote = WS_pegote;
    }

    public String getXmlns ()
    {
        return xmlns;
    }

    public void setXmlns (String xmlns)
    {
        this.xmlns = xmlns;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", WS_pegote = "+WS_pegote+", xmlns = "+xmlns+"]";
    }
}
