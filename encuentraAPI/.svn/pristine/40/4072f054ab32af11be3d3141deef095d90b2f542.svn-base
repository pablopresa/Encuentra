
package integraciones.marketplaces.MercadoLibre.entidades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class MLPedidos {

    private String query;
    private String display;
    private Paging paging;
    private List<Result> results = null;
    private Sort sort;
    private List<Available_sort> available_sorts = null;
    private List<Object> filters = null;
    private Object complete;
    private List<Available_filter> available_filters = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public MLPedidos withQuery(String query) {
        this.query = query;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public MLPedidos withDisplay(String display) {
        this.display = display;
        return this;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public MLPedidos withPaging(Paging paging) {
        this.paging = paging;
        return this;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public MLPedidos withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public MLPedidos withSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public List<Available_sort> getAvailable_sorts() {
        return available_sorts;
    }

    public void setAvailable_sorts(List<Available_sort> available_sorts) {
        this.available_sorts = available_sorts;
    }

    public MLPedidos withAvailable_sorts(List<Available_sort> available_sorts) {
        this.available_sorts = available_sorts;
        return this;
    }

    public List<Object> getFilters() {
        return filters;
    }

    public void setFilters(List<Object> filters) {
        this.filters = filters;
    }

    public MLPedidos withFilters(List<Object> filters) {
        this.filters = filters;
        return this;
    }

    public Object getComplete() {
        return complete;
    }

    public void setComplete(Object complete) {
        this.complete = complete;
    }

    public MLPedidos withComplete(Object complete) {
        this.complete = complete;
        return this;
    }

    public List<Available_filter> getAvailable_filters() {
        return available_filters;
    }

    public void setAvailable_filters(List<Available_filter> available_filters) {
        this.available_filters = available_filters;
    }

    public MLPedidos withAvailable_filters(List<Available_filter> available_filters) {
        this.available_filters = available_filters;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public MLPedidos withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("query", query).append("display", display).append("paging", paging).append("results", results).append("sort", sort).append("available_sorts", available_sorts).append("filters", filters).append("complete", complete).append("available_filters", available_filters).append("additionalProperties", additionalProperties).toString();
    }

}
