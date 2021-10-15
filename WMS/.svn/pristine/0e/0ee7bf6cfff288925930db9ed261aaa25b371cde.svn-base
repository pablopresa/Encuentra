package beans;

import java.util.Hashtable;
import java.util.List;

import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;

public class DepositoDimension implements Comparable
{
	private int id;
	private Double prom1,prom2,prom3,prom4,prom5,prom6,prom7,prom8,prom9,prom10;
	private Double pond1,pond2,pond3,pond4,pond5,pond6,pond7,pond8,pond9,pond10;
	private int uni1,uni2,uni3,uni4,uni5,uni6,uni7,uni8,uni9,uni10;
	
	private Hashtable<String, DataIDIDDescripcion> articulosStock;
	private List<DataIDDescripcion> tallesDeposito;
	private List<TalleMatrizRepo> tallesMatriz;
	private Hashtable<String ,TalleMatrizRepo> tallesMatrizHT;
	
	public Hashtable<String, TalleMatrizRepo> getTallesMatrizHT() {
		return tallesMatrizHT;
	}
	public void setTallesMatrizHT(Hashtable<String, TalleMatrizRepo> tallesMatrizHT) {
		this.tallesMatrizHT = tallesMatrizHT;
	}
	public Double getPond1() {
		return pond1;
	}
	public Double getPond2() {
		return pond2;
	}
	public Double getPond3() {
		return pond3;
	}
	public Double getPond4() {
		return pond4;
	}
	public Double getPond5() {
		return pond5;
	}
	public Double getPond6() {
		return pond6;
	}
	public Double getPond7() {
		return pond7;
	}
	public Double getPond8() {
		return pond8;
	}
	public Double getPond9() {
		return pond9;
	}
	public Double getPond10() {
		return pond10;
	}
	
	
	
	
	public DepositoDimension(Double articulo1, Double baseColor2, Double base3, Double marca4,Double seccion5,Double categoria6, Double genero7,   Double clase8, Double importe9, Double deposito10) 
	{
		
		this.prom1 = 0.00;
		this.prom2 = 0.00;
		this.prom3 = 0.00;
		this.prom4 = 0.00;
		this.prom5 = 0.00;
		this.prom6 = 0.00;
		this.prom7 = 0.00;
		this.prom8 = 0.00;
		this.prom9 = 0.00;
		this.prom10 = 0.00;
		
		this.pond1 = articulo1;
		this.pond2 = baseColor2;
		this.pond3 = base3;
		this.pond4 = marca4;
		this.pond5 = seccion5;
		this.pond6 = categoria6;
		this.pond7 = genero7;
		this.pond8 = clase8;
		this.pond9 = importe9;
		this.pond10 = deposito10;
		
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getProm1() {
		return prom1*pond1;
	}
	public void setProm1(Double prom1) 
	{
		this.prom1 = prom1;
	}
	public Double getProm2() {
		return prom2*pond2;
	}
	public void setProm2(Double prom2) {
		this.prom2 = prom2;
	}
	public Double getProm3() {
		return prom3*pond3;
	}
	public void setProm3(Double prom3) {
		this.prom3 = prom3;
	}
	public Double getProm4() {
		return prom4*pond4;
	}
	public void setProm4(Double prom4) {
		this.prom4 = prom4;
	}
	public Double getProm5() {
		return prom5*pond5;
	}
	public void setProm5(Double prom5) {
		this.prom5 = prom5;
	}
	public Double getProm6() {
		return prom6*pond6;
	}
	public void setProm6(Double prom6) {
		this.prom6 = prom6;
	}
	public Double getProm7() {
		return prom7*pond7;
	}
	public void setProm7(Double prom7) {
		this.prom7 = prom7;
	}
	public Double getProm8() {
		return prom8*pond8;
	}
	public void setProm8(Double prom8) {
		this.prom8 = prom8;
	}
	
	
	public Double getProm9() {
		return prom9*pond9;
	}
	public void setProm9(Double prom9) {
		this.prom9 = prom9;
	}
	
	public Double getProm10() {
		return prom10*pond10;
	}
	public void setProm10(Double prom10) {
		this.prom10 = prom10;
	}
	
	
	
	
	
	
	
	
	
	
	public Double getPromGral() 
	{
		Double prom =(getProm1()+getProm2()+getProm3()+getProm4()+getProm5()+getProm6()+getProm7()+getProm8()+getProm9()+getProm10())/10;
		return Math.round( prom * 100.0 ) / 100.0;
	}
	
	
	
	public List<DataIDDescripcion> getTallesDeposito() {
		return tallesDeposito;
	}
	public void setTallesDeposito(List<DataIDDescripcion> tallesDeposito) {
		this.tallesDeposito = tallesDeposito;
	}
	public int getUni1() {
		return uni1;
	}
	public void setUni1(int uni1) {
		this.uni1 = uni1;
	}
	public int getUni2() {
		return uni2;
	}
	public void setUni2(int uni2) {
		this.uni2 = uni2;
	}
	public int getUni3() {
		return uni3;
	}
	public void setUni3(int uni3) {
		this.uni3 = uni3;
	}
	public int getUni4() {
		return uni4;
	}
	public void setUni4(int uni4) {
		this.uni4 = uni4;
	}
	public int getUni5() {
		return uni5;
	}
	public void setUni5(int uni5) {
		this.uni5 = uni5;
	}
	public int getUni6() {
		return uni6;
	}
	public void setUni6(int uni6) {
		this.uni6 = uni6;
	}
	public int getUni7() {
		return uni7;
	}
	public void setUni7(int uni7) {
		this.uni7 = uni7;
	}
	public int getUni8() {
		return uni8;
	}
	public void setUni8(int uni8) {
		this.uni8 = uni8;
	}
	
	public int getUni9() {
		return uni9;
	}
	public void setUni9(int uni9) {
		this.uni9 = uni9;
	}
	public int getUni10() {
		return uni10;
	}
	public void setUni10(int uni10) {
		this.uni10 = uni10;
	}
	
	@Override
	public int compareTo(Object o) 
	{
		DepositoDimension rd = (DepositoDimension)o;        
		if(this.getPromGral()==rd.getPromGral())
	    {
			return 0;
	    }
		else if(this.getPromGral()>rd.getPromGral())
		{
			return -1;
		}
	    else
	    {
	       return 1;
	    }
	        		
	}
	public Hashtable<String, DataIDIDDescripcion> getArticulosStock() {
		return articulosStock;
	}
	public void setArticulosStock(
			Hashtable<String, DataIDIDDescripcion> articulosStock) {
		this.articulosStock = articulosStock;
	}
	public List<TalleMatrizRepo> getTallesMatriz() {
		return tallesMatriz;
	}
	public void setTallesMatriz(List<TalleMatrizRepo> tallesMatriz) {
		this.tallesMatriz = tallesMatriz;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
