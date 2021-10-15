
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Buyer {

    private long id;
    private String nickname;
    private String first_name;
    private String last_name;
    private String email;
    private Phone phone;
    private Alternative_phone alternative_phone;
    private Billing_info billing_info;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Buyer withId(long id) {
        this.id = id;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Buyer withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Buyer withFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Buyer withLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Buyer withEmail(String email) {
        this.email = email;
        return this;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Buyer withPhone(Phone phone) {
        this.phone = phone;
        return this;
    }

    public Alternative_phone getAlternative_phone() {
        return alternative_phone;
    }

    public void setAlternative_phone(Alternative_phone alternative_phone) {
        this.alternative_phone = alternative_phone;
    }

    public Buyer withAlternative_phone(Alternative_phone alternative_phone) {
        this.alternative_phone = alternative_phone;
        return this;
    }

    public Billing_info getBilling_info() {
        return billing_info;
    }

    public void setBilling_info(Billing_info billing_info) {
        this.billing_info = billing_info;
    }

    public Buyer withBilling_info(Billing_info billing_info) {
        this.billing_info = billing_info;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Buyer withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("nickname", nickname).append("first_name", first_name).append("last_name", last_name).append("email", email).append("phone", phone).append("alternative_phone", alternative_phone).append("billing_info", billing_info).append("additionalProperties", additionalProperties).toString();
    }

}
