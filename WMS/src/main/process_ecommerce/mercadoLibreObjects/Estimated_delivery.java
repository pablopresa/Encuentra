
package main.process_ecommerce.mercadoLibreObjects;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Estimated_delivery {

    private Object date;
    private Object time_from;
    private Object time_to;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Estimated_delivery withDate(Object date) {
        this.date = date;
        return this;
    }

    public Object getTime_from() {
        return time_from;
    }

    public void setTime_from(Object time_from) {
        this.time_from = time_from;
    }

    public Estimated_delivery withTime_from(Object time_from) {
        this.time_from = time_from;
        return this;
    }

    public Object getTime_to() {
        return time_to;
    }

    public void setTime_to(Object time_to) {
        this.time_to = time_to;
    }

    public Estimated_delivery withTime_to(Object time_to) {
        this.time_to = time_to;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Estimated_delivery withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("date", date).append("time_from", time_from).append("time_to", time_to).append("additionalProperties", additionalProperties).toString();
    }

}
