
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {

    private String seller_custom_field;
    private String condition;
    private String category_id;
    private long variation_id;
    private List<Variation_attribute> variation_attributes = null;
    private Object warranty;
    private String id;
    private String title;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private String seller_sku;
    
    

    public String getSeller_sku() {
		return seller_sku;
	}

	public void setSeller_sku(String seller_sku) {
		this.seller_sku = seller_sku;
	}

	public String getSeller_custom_field() {
        return seller_custom_field;
    }

    public void setSeller_custom_field(String seller_custom_field) {
        this.seller_custom_field = seller_custom_field;
    }

    public Item withSeller_custom_field(String seller_custom_field) {
        this.seller_custom_field = seller_custom_field;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Item withCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Item withCategory_id(String category_id) {
        this.category_id = category_id;
        return this;
    }

    public long getVariation_id() {
        return variation_id;
    }

    public void setVariation_id(long variation_id) {
        this.variation_id = variation_id;
    }

    public Item withVariation_id(long variation_id) {
        this.variation_id = variation_id;
        return this;
    }

    public List<Variation_attribute> getVariation_attributes() {
        return variation_attributes;
    }

    public void setVariation_attributes(List<Variation_attribute> variation_attributes) {
        this.variation_attributes = variation_attributes;
    }

    public Item withVariation_attributes(List<Variation_attribute> variation_attributes) {
        this.variation_attributes = variation_attributes;
        return this;
    }

    public Object getWarranty() {
        return warranty;
    }

    public void setWarranty(Object warranty) {
        this.warranty = warranty;
    }

    public Item withWarranty(Object warranty) {
        this.warranty = warranty;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item withId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item withTitle(String title) {
        this.title = title;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Item withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("seller_custom_field", seller_custom_field).append("condition", condition).append("category_id", category_id).append("variation_id", variation_id).append("variation_attributes", variation_attributes).append("warranty", warranty).append("id", id).append("title", title).append("additionalProperties", additionalProperties).toString();
    }

}
