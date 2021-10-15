
package main.process_ecommerce.mercadoLibreObjects;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Phone_ {

    private Object area_code;
    private String extension;
    private String number;
    private boolean verified;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getArea_code() {
        return area_code;
    }

    public void setArea_code(Object area_code) {
        this.area_code = area_code;
    }

    public Phone_ withArea_code(Object area_code) {
        this.area_code = area_code;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Phone_ withExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Phone_ withNumber(String number) {
        this.number = number;
        return this;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Phone_ withVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Phone_ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("area_code", area_code).append("extension", extension).append("number", number).append("verified", verified).append("additionalProperties", additionalProperties).toString();
    }

}
