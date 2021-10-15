package integraciones.marketplaces.vtex;
public class Paging
{
    private int total;

    private int pages;

    private int currentPage;

    private int perPage;

    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setPages(int pages){
        this.pages = pages;
    }
    public int getPages(){
        return this.pages;
    }
    public void setCurrentPage(int currentPage){
        this.currentPage = currentPage;
    }
    public int getCurrentPage(){
        return this.currentPage;
    }
    public void setPerPage(int perPage){
        this.perPage = perPage;
    }
    public int getPerPage(){
        return this.perPage;
    }
}


