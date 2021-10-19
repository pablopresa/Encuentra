
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Shipping_item {

    private String id;
    private String description;
    private long quantity;
    private Object dimensions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shipping_item withId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shipping_item withDescription(String description) {
        this.description = description;
        return this;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Shipping_item withQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Object getDimensions() {
        return dimensions;
    }

    public void setDimensions(Object dimensions) {
        this.dimensions = dimensions;
    }

    public Shipping_item withDimensions(Object dimensions) {
        this.dimensions = dimensions;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Shipping_item withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("description", description).append("quantity", quantity).append("dimensions", dimensions).append("additionalProperties", additionalProperties).toString();
    }

}
