package beans.api.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRespuestaDAC
{
    private JSONRespuestaDACPegoteResponse pegoteResponse;

    public JSONRespuestaDACPegoteResponse getPegoteResponse ()
    {
        return pegoteResponse;
    }

    public void setPegoteResponse (JSONRespuestaDACPegoteResponse pegoteResponse)
    {
        this.pegoteResponse = pegoteResponse;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pegoteResponse = "+pegoteResponse+"]";
    }
}



