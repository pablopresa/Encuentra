
package main.process_ecommerce.mercadoLibreObjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class MLUitem {

	 private String official_store_id;

	    private String id;

	    public String getOfficial_store_id ()
	    {
	        return official_store_id;
	    }

	    public void setOfficial_store_id (String official_store_id)
	    {
	        this.official_store_id = official_store_id;
	    }

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [official_store_id = "+official_store_id+", id = "+id+"]";
	    }

}
