package beans.api.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class JSONbarriosDAC
{
    
	 private barriosDAC[] barrio;
    
    public JSONbarriosDAC(){
    	
    }

	public barriosDAC[] getBarrio() {
		return barrio;
	}

	public void setBarrio(barriosDAC[] barrio) {
		this.barrio = barrio;
	}
    
}