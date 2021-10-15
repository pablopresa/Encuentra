
package main.process_ecommerce.mercadoLibreObjects;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Sale {

    private long id;
    private String date_created;
    private boolean fulfilled;
    private String rating;
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sale withId(long id) {
        this.id = id;
        return this;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public Sale withDate_created(String date_created) {
        this.date_created = date_created;
        return this;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public Sale withFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Sale withRating(String rating) {
        this.rating = rating;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sale withStatus(String status) {
        this.status = status;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Sale withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("date_created", date_created).append("fulfilled", fulfilled).append("rating", rating).append("status", status).append("additionalProperties", additionalProperties).toString();
    }

}
