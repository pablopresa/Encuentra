package jsonObjects;

import org.codehaus.jackson.map.ObjectMapper;

import beans.Fecha;

public class JSONSalesOrder 
{
	

    

    private String Comments;
    private String SalesPersonCode;
    private String DocDueDate;
    private String Address;
    private String CardCode;
    private String ShipToCode;
    private String JournalMemo;
    private String DocCurrency;
    private String DocDate;
    private boolean SalesPersonCodeSpecified;
    private boolean DocDueDateSpecified;
    private boolean DocDateSpecified;
    

    

    public JSONSalesOrder(String comments, String salesPersonCode, String docDueDate, String cardCode, jsonObjects.JSONDocumentLines[] jSONDocumentLines, String shipToCode) 
    {
		Comments = comments;
		SalesPersonCode = salesPersonCode;
		DocDueDate = docDueDate;
		ShipToCode = shipToCode;
		
		CardCode = cardCode;
		JSONDocumentLines = jSONDocumentLines;
		
		
		JournalMemo = comments;
		DocCurrency = "MXP";
		Fecha fecha = new Fecha();
		DocDate = fecha.darFechaAnio_Mes_Dia()+"T00:00:00";
		SalesPersonCodeSpecified = true;
		DocDueDateSpecified  = true;
		DocDateSpecified =  true;
		
	}

	private JSONDocumentLines[] JSONDocumentLines;

	public JSONSalesOrder() {
	}

	public String getSalesPersonCode() {
		return SalesPersonCode;
	}

	public void setSalesPersonCode(String salesPersonCode) {
		SalesPersonCode = salesPersonCode;
	}

	public boolean isDocDateSpecified() {
		return DocDateSpecified;
	}

	public void setDocDateSpecified(boolean docDateSpecified) {
		DocDateSpecified = docDateSpecified;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public String getDocDueDate() {
		return DocDueDate;
	}

	public void setDocDueDate(String docDueDate) {
		DocDueDate = docDueDate;
	}

	public String getJournalMemo() {
		return JournalMemo;
	}

	public void setJournalMemo(String journalMemo) {
		JournalMemo = journalMemo;
	}

	

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public boolean isSalesPersonCodeSpecified() {
		return SalesPersonCodeSpecified;
	}

	public void setSalesPersonCodeSpecified(boolean salesPersonCodeSpecified) {
		SalesPersonCodeSpecified = salesPersonCodeSpecified;
	}

	public String getDocCurrency() {
		return DocCurrency;
	}

	public void setDocCurrency(String docCurrency) {
		DocCurrency = docCurrency;
	}

	public boolean isDocDueDateSpecified() {
		return DocDueDateSpecified;
	}

	public void setDocDueDateSpecified(boolean docDueDateSpecified) {
		DocDueDateSpecified = docDueDateSpecified;
	}

	public String getDocDate() {
		return DocDate;
	}

	public void setDocDate(String docDate) {
		DocDate = docDate;
	}

	public String getCardCode() {
		return CardCode;
	}

	public void setCardCode(String cardCode) {
		CardCode = cardCode;
	}

	public JSONDocumentLines[] getJSONDocumentLines() {
		return JSONDocumentLines;
	}

	public void setJSONDocumentLines(JSONDocumentLines[] jSONDocumentLines) {
		JSONDocumentLines = jSONDocumentLines;
	}

   

	@Override
	public String toString() 
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(this);
			return  "["+jsonInString.replace("jsondocumentLines", "documentLines")+"]";
		}
		catch (Exception e)
		{
			return "Error";
		}
		
	}
}
