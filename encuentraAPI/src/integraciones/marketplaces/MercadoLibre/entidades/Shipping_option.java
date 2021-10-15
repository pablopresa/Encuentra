
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Shipping_option {

    private Object id;
    private Object shipping_method_id;
    private String name;
    private String currency_id;
    private long cost;
    private Speed speed;
    private Estimated_delivery estimated_delivery;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Shipping_option withId(Object id) {
        this.id = id;
        return this;
    }

    public Object getShipping_method_id() {
        return shipping_method_id;
    }

    public void setShipping_method_id(Object shipping_method_id) {
        this.shipping_method_id = shipping_method_id;
    }

    public Shipping_option withShipping_method_id(Object shipping_method_id) {
        this.shipping_method_id = shipping_method_id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shipping_option withName(String name) {
        this.name = name;
        return this;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public Shipping_option withCurrency_id(String currency_id) {
        this.currency_id = currency_id;
        return this;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Shipping_option withCost(long cost) {
        this.cost = cost;
        return this;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Shipping_option withSpeed(Speed speed) {
        this.speed = speed;
        return this;
    }

    public Estimated_delivery getEstimated_delivery() {
        return estimated_delivery;
    }

    public void setEstimated_delivery(Estimated_delivery estimated_delivery) {
        this.estimated_delivery = estimated_delivery;
    }

    public Shipping_option withEstimated_delivery(Estimated_delivery estimated_delivery) {
        this.estimated_delivery = estimated_delivery;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Shipping_option withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("shipping_method_id", shipping_method_id).append("name", name).append("currency_id", currency_id).append("cost", cost).append("speed", speed).append("estimated_delivery", estimated_delivery).append("additionalProperties", additionalProperties).toString();
    }

}
