
package main.process_ecommerce.mercadoLibreObjects;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Alternative_phone {

    private String area_code;
    private String number;
    private String extension;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public Alternative_phone withArea_code(String area_code) {
        this.area_code = area_code;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Alternative_phone withNumber(String number) {
        this.number = number;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Alternative_phone withExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Alternative_phone withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("area_code", area_code).append("number", number).append("extension", extension).append("additionalProperties", additionalProperties).toString();
    }

}
