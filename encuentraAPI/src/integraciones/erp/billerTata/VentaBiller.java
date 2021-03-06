package integraciones.erp.billerTata;


public class VentaBiller
{
private String documentNumber;

private Payments[] payments;

private Client client;

private String storeId;

private String currencyCode;

private Items[] items;

public String getDocumentNumber ()
{
return documentNumber;
}

public void setDocumentNumber (String documentNumber)
{
this.documentNumber = documentNumber;
}

public Payments[] getPayments ()
{
return payments;
}

public void setPayments (Payments[] payments)
{
this.payments = payments;
}

public Client getClient ()
{
return client;
}

public void setClient (Client client)
{
this.client = client;
}

public String getStoreId ()
{
return storeId;
}

public void setStoreId (String storeId)
{
this.storeId = storeId;
}

public String getCurrencyCode ()
{
return currencyCode;
}

public void setCurrencyCode (String currencyCode)
{
this.currencyCode = currencyCode;
}

public Items[] getItems ()
{
return items;
}

public void setItems (Items[] items)
{
this.items = items;
}




public VentaBiller(String documentNumber, Payments[] payments, Client client, String storeId, String currencyCode,
		Items[] items) {
	super();
	this.documentNumber = documentNumber;
	this.payments = payments;
	this.client = client;
	this.storeId = storeId;
	this.currencyCode = currencyCode;
	this.items = items;
}

@Override
public String toString()
{
return "ClassPojo [documentNumber = "+documentNumber+", payments = "+payments+", client = "+client+", storeId = "+storeId+", currencyCode = "+currencyCode+", items = "+items+"]";
}
}

