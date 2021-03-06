
package main.process_ecommerce.mercadoLibreObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class MLPagos {

    private long id;
    private String site_id;
    private String date_created;
    private String date_approved;
    private String money_release_date;
    private String last_modified;
    private Object sponsor_id;
    private long collector_id;
    private Payer payer;
    private String order_id;
    private String external_reference;
    private Object merchant_order_id;
    private String reason;
    private String currency_id;
    private long transaction_amount;
    private long mercadopago_fee;
    private double net_received_amount;
    private long total_paid_amount;
    private long shipping_cost;
    private long coupon_amount;
    private long coupon_fee;
    private long finance_fee;
    private long discount_fee;
    private Object coupon_id;
    private String status;
    private String status_detail;
    private String status_code;
    private long installments;
    private long issuer_id;
    private double installment_amount;
    private Object deferred_period;
    private long account_money_amount;
    private String payment_type;
    private String payment_method_id;
    private String marketplace;
    private String operation_type;
    private String transaction_order_id;
    private String statement_descriptor;
    private Cardholder cardholder;
    private String authorization_code;
    private String last_four_digits;
    private double marketplace_fee;
    private String released;
    private Object deduction_schema;
    private List<Object> tags = null;
    private List<Object> refunds = null;
    private long amount_refunded;
    private Object notification_url;
    private Object concept_id;
    private long concept_amount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MLPagos withId(long id) {
        this.id = id;
        return this;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public MLPagos withSite_id(String site_id) {
        this.site_id = site_id;
        return this;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public MLPagos withDate_created(String date_created) {
        this.date_created = date_created;
        return this;
    }

    public String getDate_approved() {
        return date_approved;
    }

    public void setDate_approved(String date_approved) {
        this.date_approved = date_approved;
    }

    public MLPagos withDate_approved(String date_approved) {
        this.date_approved = date_approved;
        return this;
    }

    public String getMoney_release_date() {
        return money_release_date;
    }

    public void setMoney_release_date(String money_release_date) {
        this.money_release_date = money_release_date;
    }

    public MLPagos withMoney_release_date(String money_release_date) {
        this.money_release_date = money_release_date;
        return this;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public MLPagos withLast_modified(String last_modified) {
        this.last_modified = last_modified;
        return this;
    }

    public Object getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(Object sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public MLPagos withSponsor_id(Object sponsor_id) {
        this.sponsor_id = sponsor_id;
        return this;
    }

    public long getCollector_id() {
        return collector_id;
    }

    public void setCollector_id(long collector_id) {
        this.collector_id = collector_id;
    }

    public MLPagos withCollector_id(long collector_id) {
        this.collector_id = collector_id;
        return this;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public MLPagos withPayer(Payer payer) {
        this.payer = payer;
        return this;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public MLPagos withOrder_id(String order_id) {
        this.order_id = order_id;
        return this;
    }

    public String getExternal_reference() {
        return external_reference;
    }

    public void setExternal_reference(String external_reference) {
        this.external_reference = external_reference;
    }

    public MLPagos withExternal_reference(String external_reference) {
        this.external_reference = external_reference;
        return this;
    }

    public Object getMerchant_order_id() {
        return merchant_order_id;
    }

    public void setMerchant_order_id(Object merchant_order_id) {
        this.merchant_order_id = merchant_order_id;
    }

    public MLPagos withMerchant_order_id(Object merchant_order_id) {
        this.merchant_order_id = merchant_order_id;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public MLPagos withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public MLPagos withCurrency_id(String currency_id) {
        this.currency_id = currency_id;
        return this;
    }

    public long getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(long transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public MLPagos withTransaction_amount(long transaction_amount) {
        this.transaction_amount = transaction_amount;
        return this;
    }

    public long getMercadopago_fee() {
        return mercadopago_fee;
    }

    public void setMercadopago_fee(long mercadopago_fee) {
        this.mercadopago_fee = mercadopago_fee;
    }

    public MLPagos withMercadopago_fee(long mercadopago_fee) {
        this.mercadopago_fee = mercadopago_fee;
        return this;
    }

    public double getNet_received_amount() {
        return net_received_amount;
    }

    public void setNet_received_amount(double net_received_amount) {
        this.net_received_amount = net_received_amount;
    }

    public MLPagos withNet_received_amount(double net_received_amount) {
        this.net_received_amount = net_received_amount;
        return this;
    }

    public long getTotal_paid_amount() {
        return total_paid_amount;
    }

    public void setTotal_paid_amount(long total_paid_amount) {
        this.total_paid_amount = total_paid_amount;
    }

    public MLPagos withTotal_paid_amount(long total_paid_amount) {
        this.total_paid_amount = total_paid_amount;
        return this;
    }

    public long getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(long shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public MLPagos withShipping_cost(long shipping_cost) {
        this.shipping_cost = shipping_cost;
        return this;
    }

    public long getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(long coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public MLPagos withCoupon_amount(long coupon_amount) {
        this.coupon_amount = coupon_amount;
        return this;
    }

    public long getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(long coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public MLPagos withCoupon_fee(long coupon_fee) {
        this.coupon_fee = coupon_fee;
        return this;
    }

    public long getFinance_fee() {
        return finance_fee;
    }

    public void setFinance_fee(long finance_fee) {
        this.finance_fee = finance_fee;
    }

    public MLPagos withFinance_fee(long finance_fee) {
        this.finance_fee = finance_fee;
        return this;
    }

    public long getDiscount_fee() {
        return discount_fee;
    }

    public void setDiscount_fee(long discount_fee) {
        this.discount_fee = discount_fee;
    }

    public MLPagos withDiscount_fee(long discount_fee) {
        this.discount_fee = discount_fee;
        return this;
    }

    public Object getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Object coupon_id) {
        this.coupon_id = coupon_id;
    }

    public MLPagos withCoupon_id(Object coupon_id) {
        this.coupon_id = coupon_id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MLPagos withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getStatus_detail() {
        return status_detail;
    }

    public void setStatus_detail(String status_detail) {
        this.status_detail = status_detail;
    }

    public MLPagos withStatus_detail(String status_detail) {
        this.status_detail = status_detail;
        return this;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public MLPagos withStatus_code(String status_code) {
        this.status_code = status_code;
        return this;
    }

    public long getInstallments() {
        return installments;
    }

    public void setInstallments(long installments) {
        this.installments = installments;
    }

    public MLPagos withInstallments(long installments) {
        this.installments = installments;
        return this;
    }

    public long getIssuer_id() {
        return issuer_id;
    }

    public void setIssuer_id(long issuer_id) {
        this.issuer_id = issuer_id;
    }

    public MLPagos withIssuer_id(long issuer_id) {
        this.issuer_id = issuer_id;
        return this;
    }

    public double getInstallment_amount() {
        return installment_amount;
    }

    public void setInstallment_amount(double installment_amount) {
        this.installment_amount = installment_amount;
    }

    public MLPagos withInstallment_amount(double installment_amount) {
        this.installment_amount = installment_amount;
        return this;
    }

    public Object getDeferred_period() {
        return deferred_period;
    }

    public void setDeferred_period(Object deferred_period) {
        this.deferred_period = deferred_period;
    }

    public MLPagos withDeferred_period(Object deferred_period) {
        this.deferred_period = deferred_period;
        return this;
    }

    public long getAccount_money_amount() {
        return account_money_amount;
    }

    public void setAccount_money_amount(long account_money_amount) {
        this.account_money_amount = account_money_amount;
    }

    public MLPagos withAccount_money_amount(long account_money_amount) {
        this.account_money_amount = account_money_amount;
        return this;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public MLPagos withPayment_type(String payment_type) {
        this.payment_type = payment_type;
        return this;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public MLPagos withPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
        return this;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public MLPagos withMarketplace(String marketplace) {
        this.marketplace = marketplace;
        return this;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public MLPagos withOperation_type(String operation_type) {
        this.operation_type = operation_type;
        return this;
    }

    public String getTransaction_order_id() {
        return transaction_order_id;
    }

    public void setTransaction_order_id(String transaction_order_id) {
        this.transaction_order_id = transaction_order_id;
    }

    public MLPagos withTransaction_order_id(String transaction_order_id) {
        this.transaction_order_id = transaction_order_id;
        return this;
    }

    public String getStatement_descriptor() {
        return statement_descriptor;
    }

    public void setStatement_descriptor(String statement_descriptor) {
        this.statement_descriptor = statement_descriptor;
    }

    public MLPagos withStatement_descriptor(String statement_descriptor) {
        this.statement_descriptor = statement_descriptor;
        return this;
    }

    public Cardholder getCardholder() {
        return cardholder;
    }

    public void setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
    }

    public MLPagos withCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
        return this;
    }

    public String getAuthorization_code() {
        return authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }

    public MLPagos withAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
        return this;
    }

    public String getLast_four_digits() {
        return last_four_digits;
    }

    public void setLast_four_digits(String last_four_digits) {
        this.last_four_digits = last_four_digits;
    }

    public MLPagos withLast_four_digits(String last_four_digits) {
        this.last_four_digits = last_four_digits;
        return this;
    }

    public double getMarketplace_fee() {
        return marketplace_fee;
    }

    public void setMarketplace_fee(double marketplace_fee) {
        this.marketplace_fee = marketplace_fee;
    }

    public MLPagos withMarketplace_fee(double marketplace_fee) {
        this.marketplace_fee = marketplace_fee;
        return this;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public MLPagos withReleased(String released) {
        this.released = released;
        return this;
    }

    public Object getDeduction_schema() {
        return deduction_schema;
    }

    public void setDeduction_schema(Object deduction_schema) {
        this.deduction_schema = deduction_schema;
    }

    public MLPagos withDeduction_schema(Object deduction_schema) {
        this.deduction_schema = deduction_schema;
        return this;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public MLPagos withTags(List<Object> tags) {
        this.tags = tags;
        return this;
    }

    public List<Object> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<Object> refunds) {
        this.refunds = refunds;
    }

    public MLPagos withRefunds(List<Object> refunds) {
        this.refunds = refunds;
        return this;
    }

    public long getAmount_refunded() {
        return amount_refunded;
    }

    public void setAmount_refunded(long amount_refunded) {
        this.amount_refunded = amount_refunded;
    }

    public MLPagos withAmount_refunded(long amount_refunded) {
        this.amount_refunded = amount_refunded;
        return this;
    }

    public Object getNotification_url() {
        return notification_url;
    }

    public void setNotification_url(Object notification_url) {
        this.notification_url = notification_url;
    }

    public MLPagos withNotification_url(Object notification_url) {
        this.notification_url = notification_url;
        return this;
    }

    public Object getConcept_id() {
        return concept_id;
    }

    public void setConcept_id(Object concept_id) {
        this.concept_id = concept_id;
    }

    public MLPagos withConcept_id(Object concept_id) {
        this.concept_id = concept_id;
        return this;
    }

    public long getConcept_amount() {
        return concept_amount;
    }

    public void setConcept_amount(long concept_amount) {
        this.concept_amount = concept_amount;
    }

    public MLPagos withConcept_amount(long concept_amount) {
        this.concept_amount = concept_amount;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public MLPagos withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("site_id", site_id).append("date_created", date_created).append("date_approved", date_approved).append("money_release_date", money_release_date).append("last_modified", last_modified).append("sponsor_id", sponsor_id).append("collector_id", collector_id).append("payer", payer).append("order_id", order_id).append("external_reference", external_reference).append("merchant_order_id", merchant_order_id).append("reason", reason).append("currency_id", currency_id).append("transaction_amount", transaction_amount).append("mercadopago_fee", mercadopago_fee).append("net_received_amount", net_received_amount).append("total_paid_amount", total_paid_amount).append("shipping_cost", shipping_cost).append("coupon_amount", coupon_amount).append("coupon_fee", coupon_fee).append("finance_fee", finance_fee).append("discount_fee", discount_fee).append("coupon_id", coupon_id).append("status", status).append("status_detail", status_detail).append("status_code", status_code).append("installments", installments).append("issuer_id", issuer_id).append("installment_amount", installment_amount).append("deferred_period", deferred_period).append("account_money_amount", account_money_amount).append("payment_type", payment_type).append("payment_method_id", payment_method_id).append("marketplace", marketplace).append("operation_type", operation_type).append("transaction_order_id", transaction_order_id).append("statement_descriptor", statement_descriptor).append("cardholder", cardholder).append("authorization_code", authorization_code).append("last_four_digits", last_four_digits).append("marketplace_fee", marketplace_fee).append("released", released).append("deduction_schema", deduction_schema).append("tags", tags).append("refunds", refunds).append("amount_refunded", amount_refunded).append("notification_url", notification_url).append("concept_id", concept_id).append("concept_amount", concept_amount).append("additionalProperties", additionalProperties).toString();
    }

}
