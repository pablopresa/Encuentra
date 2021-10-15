
package main.process_ecommerce.mercadoLibreObjects;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Order_item {

    private Item item;
    private Object differential_pricing_id;
    private long quantity;
    private double sale_fee;
    private String listing_type_id;
    private Object base_currency_id;
    private Double unit_price;
    private Object base_exchange_rate;
    private Object full_unit_price;
    private String currency_id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order_item withItem(Item item) {
        this.item = item;
        return this;
    }

    public Object getDifferential_pricing_id() {
        return differential_pricing_id;
    }

    public void setDifferential_pricing_id(Object differential_pricing_id) {
        this.differential_pricing_id = differential_pricing_id;
    }

    public Order_item withDifferential_pricing_id(Object differential_pricing_id) {
        this.differential_pricing_id = differential_pricing_id;
        return this;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Order_item withQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getSale_fee() {
        return sale_fee;
    }

    public void setSale_fee(double sale_fee) {
        this.sale_fee = sale_fee;
    }

    public Order_item withSale_fee(double sale_fee) {
        this.sale_fee = sale_fee;
        return this;
    }

    public String getListing_type_id() {
        return listing_type_id;
    }

    public void setListing_type_id(String listing_type_id) {
        this.listing_type_id = listing_type_id;
    }

    public Order_item withListing_type_id(String listing_type_id) {
        this.listing_type_id = listing_type_id;
        return this;
    }

    public Object getBase_currency_id() {
        return base_currency_id;
    }

    public void setBase_currency_id(Object base_currency_id) {
        this.base_currency_id = base_currency_id;
    }

    public Order_item withBase_currency_id(Object base_currency_id) {
        this.base_currency_id = base_currency_id;
        return this;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public Order_item withUnit_price(Double unit_price) {
        this.unit_price = unit_price;
        return this;
    }

    public Object getBase_exchange_rate() {
        return base_exchange_rate;
    }

    public void setBase_exchange_rate(Object base_exchange_rate) {
        this.base_exchange_rate = base_exchange_rate;
    }

    public Order_item withBase_exchange_rate(Object base_exchange_rate) {
        this.base_exchange_rate = base_exchange_rate;
        return this;
    }

    public Object getFull_unit_price() {
        return full_unit_price;
    }

    public void setFull_unit_price(Object full_unit_price) {
        this.full_unit_price = full_unit_price;
    }

    public Order_item withFull_unit_price(Object full_unit_price) {
        this.full_unit_price = full_unit_price;
        return this;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public Order_item withCurrency_id(String currency_id) {
        this.currency_id = currency_id;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Order_item withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("item", item).append("differential_pricing_id", differential_pricing_id).append("quantity", quantity).append("sale_fee", sale_fee).append("listing_type_id", listing_type_id).append("base_currency_id", base_currency_id).append("unit_price", unit_price).append("base_exchange_rate", base_exchange_rate).append("full_unit_price", full_unit_price).append("currency_id", currency_id).append("additionalProperties", additionalProperties).toString();
    }

}
