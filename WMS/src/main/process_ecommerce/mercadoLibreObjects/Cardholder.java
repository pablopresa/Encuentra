
package main.process_ecommerce.mercadoLibreObjects;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Cardholder {

    private String name;
    private Identification_ identification;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cardholder withName(String name) {
        this.name = name;
        return this;
    }

    public Identification_ getIdentification() {
        return identification;
    }

    public void setIdentification(Identification_ identification) {
        this.identification = identification;
    }

    public Cardholder withIdentification(Identification_ identification) {
        this.identification = identification;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Cardholder withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("identification", identification).append("additionalProperties", additionalProperties).toString();
    }

}
