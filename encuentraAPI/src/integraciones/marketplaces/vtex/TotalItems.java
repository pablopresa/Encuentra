package integraciones.marketplaces.vtex;
public class TotalItems
{
    private int Count;

    private int Max;

    private int Mean;

    private int Min;

    private int Missing;

    private int StdDev;

    private int Sum;

    private int SumOfSquares;

    private Facets Facets;

    public void setCount(int Count){
        this.Count = Count;
    }
    public int getCount(){
        return this.Count;
    }
    public void setMax(int Max){
        this.Max = Max;
    }
    public int getMax(){
        return this.Max;
    }
    public void setMean(int Mean){
        this.Mean = Mean;
    }
    public int getMean(){
        return this.Mean;
    }
    public void setMin(int Min){
        this.Min = Min;
    }
    public int getMin(){
        return this.Min;
    }
    public void setMissing(int Missing){
        this.Missing = Missing;
    }
    public int getMissing(){
        return this.Missing;
    }
    public void setStdDev(int StdDev){
        this.StdDev = StdDev;
    }
    public int getStdDev(){
        return this.StdDev;
    }
    public void setSum(int Sum){
        this.Sum = Sum;
    }
    public int getSum(){
        return this.Sum;
    }
    public void setSumOfSquares(int SumOfSquares){
        this.SumOfSquares = SumOfSquares;
    }
    public int getSumOfSquares(){
        return this.SumOfSquares;
    }
    public void setFacets(Facets Facets){
        this.Facets = Facets;
    }
    public Facets getFacets(){
        return this.Facets;
    }
}


