package integraciones.marketplaces.vtex;

import java.util.List;

public class VtexResponseList
{
    private List<OrderList> list;

    private List<String> facets;

    private Paging paging;

    private Stats stats;

    private int reportRecordsLimit;

    public void setList(List<OrderList> list){
        this.list = list;
    }
    public List<OrderList> getList(){
        return this.list;
    }
    public void setFacets(List<String> facets){
        this.facets = facets;
    }
    public List<String> getFacets(){
        return this.facets;
    }
    public void setPaging(Paging paging){
        this.paging = paging;
    }
    public Paging getPaging(){
        return this.paging;
    }
    public void setStats(Stats stats){
        this.stats = stats;
    }
    public Stats getStats(){
        return this.stats;
    }
    public void setReportRecordsLimit(int reportRecordsLimit){
        this.reportRecordsLimit = reportRecordsLimit;
    }
    public int getReportRecordsLimit(){
        return this.reportRecordsLimit;
    }
}
