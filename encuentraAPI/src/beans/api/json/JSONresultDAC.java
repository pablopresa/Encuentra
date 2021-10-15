package beans.api.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class JSONresultDAC
{
    
	private String result;

    private JSONdataDAC[] data;

    public String getResult ()
    {
        return result;
    }

    public void setResult (String result)
    {
        this.result = result;
    }

    public JSONdataDAC[] getData ()
    {
        return data;
    }

    public void setData (JSONdataDAC[] data)
    {
        this.data = data;
    }
    
    public JSONresultDAC(){
    	
    }
    
    
}