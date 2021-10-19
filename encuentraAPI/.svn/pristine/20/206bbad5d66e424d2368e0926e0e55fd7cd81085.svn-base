
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Payer {

    private long id;
    private Object email;
    private Phone phone;
    private Identification identification;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Payer withId(long id) {
        this.id = id;
        return this;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Payer withEmail(Object email) {
        this.email = email;
        return this;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Payer withPhone(Phone phone) {
        this.phone = phone;
        return this;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public Payer withIdentification(Identification identification) {
        this.identification = identification;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Payer withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("email", email).append("phone", phone).append("identification", identification).append("additionalProperties", additionalProperties).toString();
    }

}
