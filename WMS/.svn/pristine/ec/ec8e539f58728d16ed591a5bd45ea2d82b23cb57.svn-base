package dataTypes;

import java.text.DecimalFormat;
import java.util.List;

public class DataIndicador 
{
	private int idInt1,idInt2,idInt3;
	private String idString1,idString2,idInt1S,idInt2S,valorDouble1S,valorDouble2S,idInt3S,foto;
	private Double valorDouble1,valorDouble2;
	
	public int getIdInt1() {
		return idInt1;
	}
	public void setIdInt1(int idInt1) {
		this.idInt1 = idInt1;
	}
	public int getIdInt2() {
		return idInt2;
	}
	public void setIdInt2(int idInt2) {
		this.idInt2 = idInt2;
	}
	public String getIdString1() {
		return idString1;
	}
	public void setIdString1(String idString1) {
		this.idString1 = idString1;
	}
	public String getIdString2() {
		return idString2;
	}
	public void setIdString2(String idString2) {
		this.idString2 = idString2;
	}
	public Double getValorDouble1() {
		return valorDouble1;
	}
	public void setValorDouble1(Double valorDouble1) {
		this.valorDouble1 = valorDouble1;
	}
	public Double getValorDouble2() {
		return valorDouble2;
	}
	public void setValorDouble2(Double valorDouble2) {
		this.valorDouble2 = valorDouble2;
	}
	public DataIndicador() {
	
	}
	public DataIndicador(int idInt1, int idInt2, String idString1, String idString2, Double valorDouble1,Double valorDouble2) {
		this.idInt1 = idInt1;
		this.idInt2 = idInt2;
		this.idString1 = idString1;
		this.idString2 = idString2;
		this.valorDouble1 = valorDouble1;
		this.valorDouble2 = valorDouble2;
	}
	
	public String getIdInt1S() 
	{
		DecimalFormat f = new DecimalFormat("###,###.###");
		
		try
		{
			return f.format(Double.parseDouble(getIdInt1()+""));
		}
		catch (Exception e) 
		{
			return "";
		}
		
	}
	
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getIdInt3S() 
	{
		DecimalFormat f = new DecimalFormat("###,###.###");
		
		try
		{
			return f.format(Double.parseDouble(getIdInt3()+""));
		}
		catch (Exception e) 
		{
			return "";
		}
		
	}
	
	public String getIdInt2S() 
	{
		DecimalFormat f = new DecimalFormat("###,###.###");
		try
		{
			return f.format(Double.parseDouble(getIdInt2()+""));
		}
		catch (Exception e) 
		{
			return "";
		}
	}
	
	public String getValorDouble1S() 
	{
		DecimalFormat f = new DecimalFormat("###,###.###");
		
		try
		{
			return f.format(getValorDouble1());
		}
		catch (Exception e) 
		{
			return "";
		}
	}
	public String getValorDouble2S() 
	{
		DecimalFormat f = new DecimalFormat("###,###.###");
		try
		{
			return f.format(getValorDouble2());
		}
		catch (Exception e) 
		{
			return "";
		}
	}
	public int getIdInt3() {
		return idInt3;
	}
	public void setIdInt3(int idInt3) {
		this.idInt3 = idInt3;
	}
	
	
	
	
	
}
