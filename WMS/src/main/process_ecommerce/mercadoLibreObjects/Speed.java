
package main.process_ecommerce.mercadoLibreObjects;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Speed {

    private long shipping;
    private Object handling;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public long getShipping() {
        return shipping;
    }

    public void setShipping(long shipping) {
        this.shipping = shipping;
    }

    public Speed withShipping(long shipping) {
        this.shipping = shipping;
        return this;
    }

    public Object getHandling() {
        return handling;
    }

    public void setHandling(Object handling) {
        this.handling = handling;
    }

    public Speed withHandling(Object handling) {
        this.handling = handling;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Speed withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("shipping", shipping).append("handling", handling).append("additionalProperties", additionalProperties).toString();
    }

}
