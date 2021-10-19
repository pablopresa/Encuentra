
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Collector {

    private long id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collector withId(long id) {
        this.id = id;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Collector withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("additionalProperties", additionalProperties).toString();
    }

}
