package beans;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import logica.Logica;

import beans.encuentra.MovimientoMatrizDis;

import dataTypes.DataIDDescripcion;

public class ArticuloMatrizHTML implements Comparable, Cloneable
{
	Logica Logica = new Logica();
	private String articulo, descPrecio;
	private List<String> trsANTES;
	private List<String> trsAHORA;
	private List<List<String>> lineas;
	private List<MovimientoMatrizDis> movimientos;
	private List<String> marks;
	private Hashtable<String, TalleMatrizRepo> tallesM;
	private List<DataIDDescripcion> stockPorCoords;
	private List<DataIDDescripcion> stockPorCoordsOriginal;
	private int pos, lineaOrigen;
	
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	
	public ArticuloMatrizHTML() 
	{
		this.movimientos = new ArrayList<>();
	}
	public List<String> getTrsANTES() {
		return trsANTES;
	}
	public void setTrsANTES(List<String> trsANTES) {
		this.trsANTES = trsANTES;
	}
	public List<String> getTrsAHORA() {
		return trsAHORA;
	}
	public void setTrsAHORA(List<String> trsAHORA) {
		this.trsAHORA = trsAHORA;
	}
	public List<String> getMarks() {
		return marks;
	}
	public void setMarks(List<String> marks) {
		this.marks = marks;
	}
	public List<List<String>> getLineas() {
		return lineas;
	}
	public void setLineas(List<List<String>> lineas) {
		this.lineas = lineas;
	}
	public Hashtable<String, TalleMatrizRepo> getTallesM() {
		return tallesM;
	}
	public void setTallesM(Hashtable<String, TalleMatrizRepo> tallesM) {
		this.tallesM = tallesM;
	}

	
	
	public String getDescPrecio() 
	{
		try
		{
			String retorno = "";//Logica.darListaDataIdDescripcionConsulSAP("SELECT 0, CONCAT(COL.U_ColDesc,',',MPL.U_Price,',', MPC.U_Price,',', MDL.U_ModDesc ) basecol from [@ARGNS_MODEL] MDL INNER JOIN [@ARGNS_MODEL_COLOR] ACOL ON ACOL.Code = MDL.Code INNER JOIN [@ARGNS_COLOR] COL ON COL.U_ColCode = ACOL.U_ColCode INNER JOIN [@ARGNS_MODLIST] MPL ON MPL.U_ModCode = MDL.U_ModCode and MPL.U_ListNum = 1 INNER JOIN [@ARGNS_MODLIST] MPC ON MPC.U_ModCode = MDL.U_ModCode and MPC.U_ListNum = 5 WHERE CONCAT(MDL.U_ModCode, ACOL.U_ColCode) = '"+getArticulo()+"'").get(0).getDescripcion();
			String [] array = retorno.split(",");
			String col = array[0];
			String [] pre =array[1].split("\\.");
			String precioV = pre[0]+"."+pre[1].substring(0,2);
			
			String [] preC =array[2].split("\\.");
			String precioC = preC[0]+"."+preC[1].substring(0,2);
			
			String desc = array[3];
			
			return " "+desc + " "+col + " Precio Venta: $"+precioV+" Precio Costo: $"+precioC; 
			
		}
		catch (Exception e)
		{
			return "";
		}
		
	}
	public void setDescPrecio(String descPrecio) {
		this.descPrecio = descPrecio;
	}
	public List<MovimientoMatrizDis> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoMatrizDis> movimientos) {
		this.movimientos = movimientos;
	}
	@Override
	public int compareTo(Object o) 
	{
		ArticuloMatrizHTML dl = (ArticuloMatrizHTML)o;        

		  if(this.articulo.compareToIgnoreCase(dl.getArticulo()) == 0) 
          { 
              return 0;
          } 
          else if(this.articulo.compareToIgnoreCase(dl.getArticulo()) > 0)
          { 
          	
              return 1; 
          }
          else
          {
          	return -1;
          }
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getLineaOrigen() {
		return lineaOrigen;
	}
	public void setLineaOrigen(int lineaOrigen) {
		this.lineaOrigen = lineaOrigen;
	}
	
	
	
	public List<DataIDDescripcion> getStockPorCoords() 
	{
		
		if(this.tallesM!=null)
		{
			List<DataIDDescripcion> retorno = new ArrayList<>();
			Set<String> keys = this.tallesM.keySet();
			for (String key : keys) 
			{
				TalleMatrizRepo tal = this.tallesM.get(key);
				key = key.replaceAll(",","-");
				retorno.add(new DataIDDescripcion(tal.getStock()-tal.getPedido(),key));
				
				
			}
			return retorno;
			
		}
		
		return stockPorCoords;
	}
	
	public List<DataIDDescripcion> getStockPorCoordsOriginal() 
	{
		if(this.tallesM!=null)
		{
			List<DataIDDescripcion> retorno = new ArrayList<>();
			Set<String> keys = this.tallesM.keySet();
			for (String key : keys) 
			{
				TalleMatrizRepo tal = this.tallesM.get(key);
				key = key.replaceAll(",","-");
				retorno.add(new DataIDDescripcion(tal.getStock(),key));
				
				
			}
			return retorno;
			
		}
		
		
		
		return stockPorCoordsOriginal;
	}
	
	@Override
	public ArticuloMatrizHTML clone() {
		ArticuloMatrizHTML clone = null;
        try{
            clone = (ArticuloMatrizHTML) super.clone();
            
            Set<String> keys = clone.getTallesM().keySet();
            
            Hashtable<String, TalleMatrizRepo> tallesCloned = new Hashtable<>();
            for(String key: keys)
            {
            	TalleMatrizRepo t = clone.getTallesM().get(key).clone();
            	
                tallesCloned.put(key, t);
            }
    
            clone.setTallesM(tallesCloned);            
            
           
        }catch(CloneNotSupportedException e){
            throw new RuntimeException(e); // won't happen
        }
       
        return clone;
       
    }


}



























	
