
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Order_request {

    private Object change;
    private Object _return;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getChange() {
        return change;
    }

    public void setChange(Object change) {
        this.change = change;
    }

    public Order_request withChange(Object change) {
        this.change = change;
        return this;
    }

    public Object getReturn() {
        return _return;
    }

    public void setReturn(Object _return) {
        this._return = _return;
    }

    public Order_request with_return(Object _return) {
        this._return = _return;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Order_request withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("change", change).append("_return", _return).append("additionalProperties", additionalProperties).toString();
    }

}
