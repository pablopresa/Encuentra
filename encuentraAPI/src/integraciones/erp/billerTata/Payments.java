package integraciones.erp.billerTata;

public class Payments
{
private String totalAmount;

private String code;

private String name;

private String currencyCode;

private String paymentNumber;

public String getTotalAmount ()
{
return totalAmount;
}

public void setTotalAmount (String totalAmount)
{
this.totalAmount = totalAmount;
}



public Payments(String totalAmount, String code, String name, String currencyCode, String paymentNumber) {
	super();
	this.totalAmount = totalAmount;
	this.code = code;
	this.name = name;
	this.currencyCode = currencyCode;
	this.paymentNumber = paymentNumber;
}

public String getCode ()
{
return code;
}

public void setCode (String code)
{
this.code = code;
}

public String getName ()
{
return name;
}

public void setName (String name)
{
this.name = name;
}

public String getCurrencyCode ()
{
return currencyCode;
}

public void setCurrencyCode (String currencyCode)
{
this.currencyCode = currencyCode;
}

public String getPaymentNumber ()
{
return paymentNumber;
}

public void setPaymentNumber (String paymentNumber)
{
this.paymentNumber = paymentNumber;
}

@Override
public String toString()
{
return "ClassPojo [totalAmount = "+totalAmount+", code = "+code+", name = "+name+", currencyCode = "+currencyCode+", paymentNumber = "+paymentNumber+"]";
}
}

