package integraciones.erp.billerTata;


public class Totals
{
private String totalAmount;

private String sellingPrice;

public String getTotalAmount ()
{
return totalAmount;
}

public void setTotalAmount (String totalAmount)
{
this.totalAmount = totalAmount;
}

public String getSellingPrice ()
{
return sellingPrice;
}

public void setSellingPrice (String sellingPrice)
{
this.sellingPrice = sellingPrice;
}




public Totals(String totalAmount, String sellingPrice) {
	this.totalAmount = totalAmount;
	this.sellingPrice = sellingPrice;
}

@Override
public String toString()
{
return "ClassPojo [totalAmount = "+totalAmount+", sellingPrice = "+sellingPrice+"]";
}
}
