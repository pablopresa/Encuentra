import java.util.ArrayList;
import java.util.List;

import persistencia.MSSQL;

import logica.Logica;

import dataTypes.DataIDDescripcion;


public class EcommerceSincroStock {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		List<DataIDDescripcion> stock = new  ArrayList<DataIDDescripcion>();
		
		stock = MSSQL.darListaIdDescripcionWEB("select Stock,IdArticulo from movStockTotal where IdDeposito=71 and stock>0");
		
		Logica logica = new Logica();
		//logica.persistir("truncate table ecommerce_stock");
		for (DataIDDescripcion d : stock) 
		{
			//logica.persistir("INSERT INTO `ecommerce_stock` (`IdArticulo`, `Stock`) VALUES ('"+d.getDescripcion()+"', '"+d.getId()+"');");
		}
		
		
		
		

	}

}
