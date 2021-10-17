package integraciones.erp.sapBO.stadium;

import java.util.List;

import integraciones.erp.sapBO.ClienteSap;
import integraciones.erp.sapBO.beansSL.Value;

public class TEST 
{
	
	static String rlU = "https://10.108.0.119:50000//b1s/v1/BusinessPartners?$select=CardCode, CardName&$filter=CardType eq 'S' and GroupCode eq 120";
	
	
	public static void main(String[] args) {
		ClienteSap cls = new ClienteSap(1);
		List<Value> valores =cls.consultaGenerica(rlU);
		for (Value v : valores) 
		{
			System.out.println(v.getCardCode());
			System.out.println(v.getCardName());
		}
	}

}
