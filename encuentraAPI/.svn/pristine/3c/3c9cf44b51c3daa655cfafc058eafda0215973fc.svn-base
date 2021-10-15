
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Feedback {

    private Sale sale;
    private Object purchase;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Feedback withSale(Sale sale) {
        this.sale = sale;
        return this;
    }

    public Object getPurchase() {
        return purchase;
    }

    public void setPurchase(Object purchase) {
        this.purchase = purchase;
    }

    public Feedback withPurchase(Object purchase) {
        this.purchase = purchase;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Feedback withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("sale", sale).append("purchase", purchase).append("additionalProperties", additionalProperties).toString();
    }

}
