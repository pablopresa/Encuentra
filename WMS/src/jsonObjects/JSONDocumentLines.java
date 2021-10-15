package jsonObjects;

public class JSONDocumentLines 
{
	 private String SalesPersonCode;

	   

	    private boolean PriceSpecified;
	    private boolean LineNumSpecified;
	    private boolean TaxLiableSpecified;
	    private boolean SalesPersonCodeSpecified;
	    private boolean QuantitySpecified;
	    private boolean UnitPriceSpecified;
	    private int TaxLiable;
	    private boolean ShipDateSpecified;

	    private int LineNum;
	    private Double Price;
	    private Double UnitPrice;
	    private String ShipDate;
	    private String ItemCode;
	    private String WarehouseCode;
	    private int Quantity;
	    private String TaxCode;

	    
	  

		public boolean isUnitPriceSpecified() {
			return UnitPriceSpecified;
		}




		public void setUnitPriceSpecified(boolean unitPriceSpecified) {
			UnitPriceSpecified = unitPriceSpecified;
		}




		public Double getUnitPrice() {
			return UnitPrice;
		}




		public void setUnitPrice(Double unitPrice) {
			UnitPrice = unitPrice;
		}




		public String getSalesPersonCode() {
			return SalesPersonCode;
		}




		public void setSalesPersonCode(String salesPersonCode) {
			SalesPersonCode = salesPersonCode;
		}




		public boolean isPriceSpecified() {
			return PriceSpecified;
		}




		public void setPriceSpecified(boolean priceSpecified) {
			PriceSpecified = priceSpecified;
		}




		public boolean isLineNumSpecified() {
			return LineNumSpecified;
		}




		public void setLineNumSpecified(boolean lineNumSpecified) {
			LineNumSpecified = lineNumSpecified;
		}




		public boolean isTaxLiableSpecified() {
			return TaxLiableSpecified;
		}




		public void setTaxLiableSpecified(boolean taxLiableSpecified) {
			TaxLiableSpecified = taxLiableSpecified;
		}




		public boolean isSalesPersonCodeSpecified() {
			return SalesPersonCodeSpecified;
		}




		public void setSalesPersonCodeSpecified(boolean salesPersonCodeSpecified) {
			SalesPersonCodeSpecified = salesPersonCodeSpecified;
		}




		public boolean isQuantitySpecified() {
			return QuantitySpecified;
		}




		public void setQuantitySpecified(boolean quantitySpecified) {
			QuantitySpecified = quantitySpecified;
		}




		



		public int getTaxLiable() {
			return TaxLiable;
		}




		public void setTaxLiable(int taxLiable) {
			TaxLiable = taxLiable;
		}




		public boolean isShipDateSpecified() {
			return ShipDateSpecified;
		}




		public void setShipDateSpecified(boolean shipDateSpecified) {
			ShipDateSpecified = shipDateSpecified;
		}




		public int getLineNum() {
			return LineNum;
		}




		public void setLineNum(int lineNum) {
			LineNum = lineNum;
		}




		public Double getPrice() {
			return Price;
		}




		public void setPrice(Double price) {
			Price = price;
		}




		public String getShipDate() {
			return ShipDate;
		}




		public void setShipDate(String shipDate) {
			ShipDate = shipDate;
		}




		public String getItemCode() {
			return ItemCode;
		}




		public void setItemCode(String itemCode) {
			ItemCode = itemCode;
		}




		public String getWarehouseCode() {
			return WarehouseCode;
		}




		public void setWarehouseCode(String warehouseCode) {
			WarehouseCode = warehouseCode;
		}




		public int getQuantity() {
			return Quantity;
		}




		public void setQuantity(int quantity) {
			Quantity = quantity;
		}




		public String getTaxCode() {
			return TaxCode;
		}




		public void setTaxCode(String taxCode) {
			TaxCode = taxCode;
		}




		public JSONDocumentLines(String salesPersonCode, String itemCode, String warehouseCode, int quantity, int lineNum, Double price, String shipDate) 
		{
			SalesPersonCode = salesPersonCode;
			ItemCode = itemCode;
			WarehouseCode = warehouseCode;
			Quantity = quantity;
			LineNum = lineNum;
			Price = price;
			UnitPrice = price;
			ShipDate = shipDate;
			TaxCode = "C16";
			PriceSpecified = true;
			LineNumSpecified = true;
			TaxLiableSpecified = true;
			SalesPersonCodeSpecified = true;
			QuantitySpecified = true;
			UnitPriceSpecified = true;
			TaxLiable = 1;
			ShipDateSpecified = true;
			
			
		}

	    

}
