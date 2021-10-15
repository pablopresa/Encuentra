package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataTypes.DataIDDescripcion;

public class ArticuloAnysys 
{
	private String idArticulo,base,color,talle,descripcion,descripcionCorta,descripcionClase,descripcionSeccion,descripcionCategoria,descripcionMarca,descripcionGenero,baseColor;
	private int idClase,idSeccion,idCategoria,idMarca,idGenero,stock, posicion;
	
	private List<DataIDDescripcion> tags1,tags2,tags3,tags4,tags5;
	
	private List<List<String>> trs;
	
	
	
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(String baseColor) {
		this.baseColor = baseColor;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTalle() {
		return talle;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcionClase() {
		return descripcionClase;
	}

	public void setDescripcionClase(String descripcionClase) {
		this.descripcionClase = descripcionClase;
	}

	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}

	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
	}

	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	public String getDescripcionGenero() {
		return descripcionGenero;
	}

	public void setDescripcionGenero(String descripcionGenero) {
		this.descripcionGenero = descripcionGenero;
	}

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	

	

	public ArticuloAnysys(String idArticulo, String base, String color, String talle, String descripcion,
			String descripcionCorta, String descripcionClase, String descripcionSeccion, String descripcionCategoria,
			String descripcionMarca, String descripcionGenero, int idClase, int idSeccion, int idCategoria, int idMarca,
			int idGenero, int stock, List<DataIDDescripcion> tags) {
		this.idArticulo = idArticulo;
		this.base = base;
		this.color = color;
		this.talle = talle;
		this.descripcion = descripcion;
		this.descripcionCorta = descripcionCorta;
		this.descripcionClase = descripcionClase;
		this.descripcionSeccion = descripcionSeccion;
		this.descripcionCategoria = descripcionCategoria;
		this.descripcionMarca = descripcionMarca;
		this.descripcionGenero = descripcionGenero;
		this.idClase = idClase;
		this.idSeccion = idSeccion;
		this.idCategoria = idCategoria;
		this.idMarca = idMarca;
		this.idGenero = idGenero;
		this.stock = stock;
		
	}
	
	
	
	public ArticuloAnysys() {}

	public List<DataIDDescripcion> getTags1() {
		return tags1;
	}

	public void setTags1(List<DataIDDescripcion> tags1) {
		this.tags1 = tags1;
	}

	public List<DataIDDescripcion> getTags2() {
		return tags2;
	}

	public void setTags2(List<DataIDDescripcion> tags2) {
		this.tags2 = tags2;
	}

	public List<DataIDDescripcion> getTags3() {
		return tags3;
	}

	public void setTags3(List<DataIDDescripcion> tags3) {
		this.tags3 = tags3;
	}

	public List<DataIDDescripcion> getTags4() {
		return tags4;
	}

	public void setTags4(List<DataIDDescripcion> tags4) {
		this.tags4 = tags4;
	}

	public List<DataIDDescripcion> getTags5() {
		return tags5;
	}

	public void setTags5(List<DataIDDescripcion> tags5) {
		this.tags5 = tags5;
	}

	public List<List<String>> getTrs() 
	{
		List<List<String>> trs = new ArrayList<List<String>>();
		
		List<DataIDDescripcion> tags1 = getTags1();
		List<DataIDDescripcion> tags2 = getTags2();
		List<DataIDDescripcion> tags3 = getTags3();
		List<DataIDDescripcion> tags4 = getTags4();
		List<DataIDDescripcion> tags5 = getTags5();
		
		int largo1 = tags1.size();
		int largo2 = tags2.size();
		int largo3 = tags3.size();
		int largo4 = tags4.size();
		int largo5 = tags5.size();
		
		int largoMax = 0;
		int[] arreglo = {largo1,largo2,largo3,largo4,largo5};
		Arrays.sort(arreglo);
		for (int i = 0; i < arreglo.length; i++) 
		{
			largoMax = arreglo[i];
		}
		
		
		
		
		
		for (int i = 0; i < largoMax+1; i++) 
		{
			/*aca hay que agregar elcodigo HTML del switch*/
			List<String> tr = new ArrayList<>();
			try
			{
				if(tags1.get(i).getIdB()==1)
				{
					//tr.add("<input checked data-toggle=\"toggle\"  name=\"T1"+tags1.get(i).getId()+"\"  type=\"checkbox\">"+tags1.get(i).getDescripcion());
					
					tr.add("<label class=\"containerCHK\">"+tags1.get(i).getDescripcion()+"<input type=\"checkbox\" checked=\"checked\" onchange=\"modificaTag('"+getBaseColor()+"',1,'"+tags1.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
				else
				{
					//tr.add("<input data-toggle=\"toggle\"  name=\"T1"+tags1.get(i).getId()+"\"  type=\"checkbox\">"+tags1.get(i).getDescripcion());
					tr.add("<label class=\"containerCHK\">"+tags1.get(i).getDescripcion()+"<input type=\"checkbox\" onchange=\"modificaTag('"+getBaseColor()+"',1,'"+tags1.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
				
			}
			catch (Exception e) 
			{
				tr.add("");
			}
			try
			{
			
				
				if(tags2.get(i).getIdB()==1)
				{
					//tr.add("<input checked data-toggle=\"toggle\"  name=\"T1"+tags2.get(i).getId()+"\"  type=\"checkbox\">"+tags2.get(i).getDescripcion());
					
					tr.add("<label class=\"containerCHK\">"+tags2.get(i).getDescripcion()+"<input type=\"checkbox\" checked=\"checked\" onchange=\"modificaTag('"+getBaseColor()+"',2,'"+tags2.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
				else
				{
					//tr.add("<input data-toggle=\"toggle\"  name=\"T1"+tags2.get(i).getId()+"\"  type=\"checkbox\">"+tags2.get(i).getDescripcion());
					tr.add("<label class=\"containerCHK\">"+tags2.get(i).getDescripcion()+"<input type=\"checkbox\" onchange=\"modificaTag('"+getBaseColor()+"',2,'"+tags2.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
				
			}
			catch (Exception e) 
			{
				tr.add("");
			}
			try
			{
				
				
				
				if(tags3.get(i).getIdB()==1)
				{
					//tr.add("<input checked data-toggle=\"toggle\"  name=\"T1"+tags3.get(i).getId()+"\"  type=\"checkbox\">"+tags3.get(i).getDescripcion());
					
					tr.add("<label class=\"containerCHK\">"+tags3.get(i).getDescripcion()+"<input type=\"checkbox\" checked=\"checked\" onchange=\"modificaTag('"+getBaseColor()+"',3,'"+tags3.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
				else
				{
					//tr.add("<input data-toggle=\"toggle\"  name=\"T1"+tags3.get(i).getId()+"\"  type=\"checkbox\">"+tags3.get(i).getDescripcion());
					tr.add("<label class=\"containerCHK\">"+tags3.get(i).getDescripcion()+"<input type=\"checkbox\" onchange=\"modificaTag('"+getBaseColor()+"',3,'"+tags3.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
			}
			catch (Exception e) 
			{
				tr.add("");
			}
			try
			{
				
				if(tags4.get(i).getIdB()==1)
				{
					//tr.add("<input checked data-toggle=\"toggle\"  name=\"T1"+tags4.get(i).getId()+"\"  type=\"checkbox\">"+tags4.get(i).getDescripcion());
					
					tr.add("<label class=\"containerCHK\">"+tags4.get(i).getDescripcion()+"<input type=\"checkbox\" checked=\"checked\" onchange=\"modificaTag('"+getBaseColor()+"',4,'"+tags4.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
				else
				{
					//tr.add("<input data-toggle=\"toggle\"  name=\"T1"+tags4.get(i).getId()+"\"  type=\"checkbox\">"+tags4.get(i).getDescripcion());
					tr.add("<label class=\"containerCHK\">"+tags4.get(i).getDescripcion()+"<input type=\"checkbox\" onchange=\"modificaTag('"+getBaseColor()+"',4,'"+tags4.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
			}
			catch (Exception e) 
			{
				tr.add("");
			}
			try
			{
				
				if(tags5.get(i).getIdB()==1)
				{
					//tr.add("<input checked data-toggle=\"toggle\"  name=\"T1"+tags5.get(i).getId()+"\"  type=\"checkbox\">"+tags5.get(i).getDescripcion());
					
					tr.add("<label class=\"containerCHK\">"+tags5.get(i).getDescripcion()+"<input type=\"checkbox\" checked=\"checked\" onchange=\"modificaTag('"+getBaseColor()+"',5,'"+tags5.get(i).getDescripcion()+"')\"><span class=\"checkmark\"></span></label>");
				}
				else
				{
					//tr.add("<input data-toggle=\"toggle\"  name=\"T1"+tags5.get(i).getId()+"\"  type=\"checkbox\">"+tags5.get(i).getDescripcion());
					tr.add("<label class=\"containerCHK\">"+tags5.get(i).getDescripcion()+"<input type=\"checkbox\" onchange=\"modificaTag('"+getBaseColor()+"',5,'"+tags5.get(i).getDescripcion()+"')\" ><span class=\"checkmark\"></span></label>");
				}
			}
			
			catch (Exception e) 
			{
				tr.add("");
			}
			
			
			trs.add(tr);
			
				
			
		}
		
		
		return trs;
	}

	public void setTrs(List<List<String>> trs) {
		this.trs = trs;
	}

	
	
	
	

}
