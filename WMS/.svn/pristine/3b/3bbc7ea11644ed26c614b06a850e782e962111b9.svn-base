package beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import logica.Logica;

public class ProcesadorRepo 
{
	private Hashtable<String, List<RepoDimension>> articulos;
	private Hashtable <String, Hashtable<String, StockReposicion>>stocks;
	
	private Hashtable<String, List<RepoDimension>> baseColor;
	private Hashtable<String, List<RepoDimension>> base;
	private Hashtable<Integer, List<RepoDimension>> marca;
	private Hashtable<String, List<RepoDimension>> seccion;
	private Hashtable<String, List<RepoDimension>> clase;
	private Hashtable<Integer, List<RepoDimension>> importe; // no es el importe del articulo es la franja en la que se ubica si es 200 esta en la franja 1 si es 2000 esta en la franja 10
	private Hashtable<Integer, List<RepoDimension>> sucursal;
	private Hashtable<Integer, List<RepoDimension>> genero;
	private Hashtable<Integer, List<RepoDimension>> categoria;
	
	Logica Logica = new Logica();
	
	
	 public Hashtable<Integer, List<RepoDimension>> getSucursal() {
		return sucursal;
	}



	public void setSucursal(Hashtable<Integer, List<RepoDimension>> sucursal) {
		this.sucursal = sucursal;
	}

	public List<RepoDimension> darListaConPorcentaje(List<RepoDimension> lista)
	 {
		 Double total = 0.0;
		 for (RepoDimension r : lista) 
		 {
			total += r.getCantidadVendida();
		 }
		 for (RepoDimension r : lista) 
		 {
			Double porcentaje = (r.getCantidadVendida()*100)/total;
			
			double finalValue = Math.round( porcentaje * 100.0 ) / 100.0;
			
			r.setPorcentajeDelTotal(finalValue);
		 }
		 return lista;
	 }



	public Hashtable<String, Hashtable<String, StockReposicion>> getStocks() {
		return stocks;
	}


	public void setStocks(
			Hashtable<String, Hashtable<String, StockReposicion>> stocks) {
		this.stocks = stocks;
	}


	public Hashtable<String, List<RepoDimension>> getArticulos() {
		return articulos;
	}





	public void setArticulos(Hashtable<String, List<RepoDimension>> articulos) {
		this.articulos = articulos;
	}





	public Hashtable<String, List<RepoDimension>> getBaseColor() {
		return baseColor;
	}





	public void setBaseColor(Hashtable<String, List<RepoDimension>> baseColor) {
		this.baseColor = baseColor;
	}





	public Hashtable<String, List<RepoDimension>> getBase() {
		return base;
	}





	public void setBase(Hashtable<String, List<RepoDimension>> base) {
		this.base = base;
	}





	public Hashtable<Integer, List<RepoDimension>> getMarca() {
		return marca;
	}





	public void setMarca(Hashtable<Integer, List<RepoDimension>> marca) {
		this.marca = marca;
	}




	




	





	public Hashtable<String, List<RepoDimension>> getSeccion() {
		return seccion;
	}





	public void setSeccion(Hashtable<String, List<RepoDimension>> seccion) {
		this.seccion = seccion;
	}





	public Hashtable<String, List<RepoDimension>> getClase() {
		return clase;
	}





	public void setClase(Hashtable<String, List<RepoDimension>> clase) {
		this.clase = clase;
	}





	public Hashtable<Integer, List<RepoDimension>> getImporte() {
		return importe;
	}





	public void setImporte(Hashtable<Integer, List<RepoDimension>> importe) {
		this.importe = importe;
	}





	public Hashtable<Integer, List<RepoDimension>> getGenero() {
		return genero;
	}



	public void setGenero(Hashtable<Integer, List<RepoDimension>> genero) {
		this.genero = genero;
	}



	public Hashtable<Integer, List<RepoDimension>> getCategoria() {
		return categoria;
	}



	public void setCategoria(Hashtable<Integer, List<RepoDimension>> categoria) {
		this.categoria = categoria;
	}



	public ProcesadorRepo() 
	{
		this.articulos = new Hashtable<String, List<RepoDimension>>();
		this.stocks = new Hashtable<String,Hashtable<String, StockReposicion>>();
		this.base =new Hashtable<String, List<RepoDimension>>();
		this.baseColor = new Hashtable<String, List<RepoDimension>>();
		this.clase = new Hashtable<String, List<RepoDimension>>();
		this.importe = new Hashtable<Integer, List<RepoDimension>>();
		this.marca = new Hashtable<Integer, List<RepoDimension>>();
		this.seccion = new Hashtable<String, List<RepoDimension>>();
		this.sucursal = new Hashtable<Integer, List<RepoDimension>>();
		this.categoria = new Hashtable<Integer, List<RepoDimension>>();
		this.genero = new Hashtable<Integer, List<RepoDimension>>();
	}





	public void agregarLinea(ArticuloReposicion articulo, int idSinc, int porcentaje, Hashtable<String, Integer> stocksReservados, boolean analizar, Integer distOrigen, boolean log,Hashtable<String, Integer> stocksEnviados) 
	{
		Usuario u = Logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");
		
		int idEmpresa = u.getIdEmpresa();
		
		List<RepoDimension> articulos = null;
		List<RepoDimension> baseColor= null;
		List<RepoDimension> base= null;
		List<RepoDimension> marca= null;
		List<RepoDimension> seccion= null;
		List<RepoDimension> clase= null;
		List<RepoDimension> importe= null;
		List<RepoDimension> sucursal= null;
		List<RepoDimension> genero= null;
		List<RepoDimension> categoria= null;
		Hashtable<String, StockReposicion>  stocks =null;
		
		
	
		if(distOrigen==null)
		{
			distOrigen = 0;
		}
		
		/*****************************************************DIMENSION 1 ARTICULO*********************************************************/
		/*
		if(this.getArticulos()!=null)//si los articulos no estan en null
		{
			if(this.getArticulos().get(articulo.getArticulo())!=null)
			{
				articulos = this.getArticulos().get(articulo.getArticulo());
			}
			else
			{
				String consulta ="Select 	VL.IdArticulo, "+    
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion 			 "+
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito 		 "+
								"	where (Doc.IdListaEmpresa = 1) And  (Doc.IdDeposito Between  0 And 201) "+ 
								"	And (Doc.Fecha Between DATEADD(DAY,-30,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	and VL.IdArticulo = '"+articulo.getArticulo()+"' "+
								"	GROUP BY VL.IdArticulo, doc.IdDeposito";  
				
				
				Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Cod. ARTICULO "+articulo.getArticulo());
				articulos = Logica.darListaRepoDimension(consulta, "Articulos", idSinc);
				
				articulos = darListaConPorcentaje(articulos);
			}
			
		}
		*/
		/*****************************************************DIMENSION 2 BASE COLOR*********************************************************/
		if(this.getBaseColor()!=null)//si los articulos no estan en null
		{
			
			
			if(this.getBaseColor().get(articulo.getArticuloBaseColor())!=null)
			{
				baseColor = this.getBaseColor().get(articulo.getArticuloBaseColor());
			}
			else
			{
				String consulta ="Select 	SUBSTRING(VL.IdArticulo,0,14),"+   
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion "+ 			
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito "+ 		
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99))"+ 
								"	And (Doc.Fecha Between DATEADD(DAY,-60,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	and VL.IdArticulo LIKE '"+articulo.getArticuloBaseColor()+"%' and cantidad>0 "+
								"	GROUP BY SUBSTRING(VL.IdArticulo,0,14), doc.IdDeposito";  
				
				if(analizar)
				{
					if(log)
					Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Cod. ARTICULO BASE + COLOR "+articulo.getArticuloBaseColor(), porcentaje,idEmpresa);
					
					
					
					baseColor = null;//darListaConPorcentaje(Logica.darListaRepoDimension(consulta, "Articulo base color",idSinc, porcentaje, analizar, distOrigen,idEmpresa));
						
				}
				else
				{
					List<RepoDimension>  gen = null;//Logica.darListaRepoDimensionSinAnalisis(articulo, "Articulo base color",idSinc, porcentaje, analizar,idEmpresa);
					
					RepoDimension rd = new RepoDimension();
					rd.setCantidadVendida(0);
					rd.setIdDeposito(distOrigen);
					
					gen.add(rd);
					
					baseColor = darListaConPorcentaje(gen);
					base = new ArrayList<>(baseColor);
					marca = new ArrayList<>(baseColor);
					seccion = new ArrayList<>(baseColor);
					clase=new ArrayList<>(baseColor);
					seccion=new ArrayList<>(baseColor);
					importe=new ArrayList<>(baseColor);
					sucursal=new ArrayList<>(baseColor);
					genero=new ArrayList<>(baseColor);
					categoria = new ArrayList<>(baseColor);
					
				}
				
				
				 
			}
			
		}
		
		
		/*****************************************************DIMENSION 3 BASE *********************************************************/
		if(this.getBase()!=null)//si los articulos no estan en null
		{
			if(this.getBase().get(articulo.getArticuloBase())!=null)
			{
				base = this.getBase().get(articulo.getArticuloBase());
			}
			else
			{
				String consulta ="Select 	SUBSTRING(VL.IdArticulo,0,10), "+ 
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion "+ 			
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito "+ 		
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99))"+ 
								"	And (Doc.Fecha Between DATEADD(DAY,-30,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	and VL.IdArticulo LIKE '"+articulo.getArticuloBase()+"%' and cantidad>0 "+
								"	GROUP BY SUBSTRING(VL.IdArticulo,0,10),doc.IdDeposito";  
				
				if(analizar)
				{
					if(log)
					{
						Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Cod. BASE "+articulo.getArticuloBase(), porcentaje,idEmpresa);
					}
					base = darListaConPorcentaje(null);//Logica.darListaRepoDimension(consulta, "Articulo base", idSinc, porcentaje, analizar, distOrigen,idEmpresa));
				}
				 
			}
			
		}
		
		
		
		
		/*****************************************************DIMENSION 4 MARCA *********************************************************/
		if(this.getMarca()!=null)
		{
			if(this.getMarca().get(articulo.getMarca())!=null)
			{
				marca = this.getMarca().get(articulo.getMarca());
			}
			else
			{
				String consulta ="Select 	AM.IdMarca, "+   
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion "+ 			
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito "+ 		
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99))"+ 
								"	and AM.IdMarca = "+articulo.getMarca()+" and cantidad>0 "+
								"	And (Doc.Fecha Between DATEADD(DAY,-90,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	GROUP BY AM.IdMarca, doc.IdDeposito";  
				if(analizar)
				{
					if(log)
					{
						Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Marca "+articulo.getMarca(),porcentaje,idEmpresa);
					}
					marca = darListaConPorcentaje(null);//Logica.darListaRepoDimension(consulta, "Marca", idSinc,porcentaje, analizar, distOrigen,idEmpresa));
				}
				 
			}
			
		}
		
		
		/*****************************************************DIMENSION 5 seccion *********************************************************/
		if(this.getSeccion()!=null)
		{
			if(this.getSeccion().get(articulo.getMarca()+"-"+articulo.getSeccion())!=null)
			{
				seccion = this.getSeccion().get(articulo.getMarca()+"-"+articulo.getSeccion());
			}
			else
			{
				
				String consulta ="Select 	AR.IdSeccion, "+
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion "+ 			
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito "+ 		
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99))"+ 
								"	and AM.IdMarca = "+articulo.getMarca()+" "+
								"	AND AR.IdSeccion = "+articulo.getSeccion()+" and cantidad>0 "+
								"	And (Doc.Fecha Between DATEADD(DAY,-15,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	GROUP BY AR.IdSeccion, doc.IdDeposito";  
				
				if(analizar)
				{
					if(log)
					{
						Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Seccion "+articulo.getSeccion()+" y marca "+articulo.getMarca(),porcentaje,idEmpresa);
					}
					seccion = darListaConPorcentaje(null);//Logica.darListaRepoDimension(consulta, "Seccion", idSinc,porcentaje, analizar, distOrigen,idEmpresa));
				}
					
			}
			
		}
		/*****************************************************DIMENSION 6 CLASE *********************************************************/
		if(this.getClase()!=null)
		{
			if(this.getClase().get(articulo.getMarca()+"-"+articulo.getClase())!=null)
			{
				clase = this.getClase().get(articulo.getMarca()+"-"+articulo.getClase());
			}
			else
			{
				String consulta ="Select 	AR.IdClase,  "+
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion "+ 			
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito "+ 		
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99))"+ 
								"	and AM.IdMarca = "+articulo.getMarca()+" "+
								"	AND AR.IdClase = "+articulo.getClase()+" and cantidad>0 "+
								"	And (Doc.Fecha Between DATEADD(DAY,-30,CURRENT_TIMESTAMP) And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	GROUP BY AR.IdClase, doc.IdDeposito";  
				
				if(analizar)
				{
					if(log)
					{
						Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Clase "+articulo.getClase()+" y marca "+articulo.getMarca(),porcentaje,idEmpresa);
					}
					clase = darListaConPorcentaje(null);//Logica.darListaRepoDimension(consulta, "Clase", idSinc, porcentaje,analizar, distOrigen,idEmpresa));
				}
				 
			}
			
		}
		
		/*****************************************************DIMENSION 7 IMPORTE *********************************************************/
		if(this.getImporte()!=null)
		{
			if(this.getImporte().get(articulo.getPrecio())!=null)
			{
				importe = this.getImporte().get(articulo.getPrecio());
			}
			else
			{
				String consulta ="Select 	DEP.Descripcion as IdDeposito, "+  
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion 			 "+
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito 		 "+
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99)) "+
								"	AND VL.Precio Between ("+articulo.getPrecio()+"*200) AND ("+articulo.getPrecio()+"*200) + 200 and cantidad>0 "+
								"	And (Doc.Fecha Between DATEADD(DAY,-30,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	GROUP BY DEP.Descripcion,doc.IdDeposito "+
								"	order by Cantidad desc";  
				
				if(analizar)
				{
					if(log)
					{
						Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Importe para franja "+ articulo.getPrecio()+" (X200)",porcentaje,idEmpresa);
					}
					importe = darListaConPorcentaje(null);//Logica.darListaRepoDimension(consulta, "importe", idSinc, porcentaje, analizar, distOrigen,idEmpresa));
				}
				 
			}
			
		}
		
		
		
		
		
		
		
		
		/*****************************************************DIMENSION 8 deposito *********************************************************/
		if(this.getSucursal()!=null)
		{
			if(this.getSucursal().get(articulo.getSucursal())!=null)
			{
				sucursal = this.getSucursal().get(articulo.getSucursal());
			}
			else
			{
				String consulta ="Select 	 DEP.Descripcion as IdDeposito, "+  
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion "+ 			
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito "+ 		
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99)) and cantidad>0 "+ 
								"	And (Doc.Fecha Between DATEADD(DAY,-30,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	GROUP BY DEP.Descripcion, doc.IdDeposito";  
				
				if(analizar)
				{
					if(log)
					{
						Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por deposito", porcentaje,idEmpresa);
					}
					sucursal = darListaConPorcentaje(null);//Logica.darListaRepoDimension(consulta, "Deposito", idSinc, porcentaje, analizar, distOrigen,idEmpresa));
				}
				 
			}
			
		}
		
		
		
		
		
		
		
		/*****************************************************DIMENSION 9 GENERO *********************************************************/
		if(this.getGenero()!=null)
		{
			if(this.getGenero().get(articulo.getGenero())!=null)
			{
				genero = this.getGenero().get(articulo.getGenero());
			}
			else
			{
				String consulta ="Select 	DEP.Descripcion as IdDeposito, "+  
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion 			 "+
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito 		 "+
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99)) "+
								"	AND AR.IdGenero = "+articulo.getGenero()+" and cantidad>0 "+
								"	And (Doc.Fecha Between DATEADD(DAY,-30,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	GROUP BY DEP.Descripcion,doc.IdDeposito "+
								"	order by Cantidad desc";  
				
				if(analizar)
				{
					if(log)
					{
						Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Genero "+ articulo.getGenero()+"",porcentaje,idEmpresa);
					}
					genero = darListaConPorcentaje(null);//Logica.darListaRepoDimension(consulta, "genero", idSinc, porcentaje, analizar, distOrigen,idEmpresa));
				}
				 
			}
			
		}
		
		/*****************************************************DIMENSION 10 Categoria *********************************************************/
		if(this.getCategoria()!=null)
		{
			if(this.getCategoria().get(articulo.getCategoria())!=null)
			{
				categoria = this.getCategoria().get(articulo.getCategoria());
			}
			else
			{
				String consulta ="Select 	DEP.Descripcion as IdDeposito, "+  
								"	SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo "+
								"	From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento	INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo "+
								"	INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion 			 "+
								"	INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento "+ 			
								"	INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito 		 "+
								"	where (Doc.IdListaEmpresa = 1) And  ((Doc.IdDeposito Between  0 And 50)  OR  (Doc.IdDeposito = 99)) "+
								"	AND AR.IdCategoria = "+articulo.getCategoria()+" and cantidad>0 "+
								"	And (Doc.Fecha Between DATEADD(DAY,-30,CURRENT_TIMESTAMP)      And CURRENT_TIMESTAMP)  And Doc.IdTipoDocumento not in ('REC','RDC') "+  
								"	GROUP BY DEP.Descripcion,doc.IdDeposito "+
								"	order by Cantidad desc";  
				
				if(analizar)
				{
					if(log)
					{
						Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Ranking de ventas por Categoria "+ articulo.getCategoria()+"",porcentaje,idEmpresa);
					}
					categoria = darListaConPorcentaje(null);//Logica.darListaRepoDimension(consulta, "Categoria", idSinc, porcentaje, analizar, distOrigen,idEmpresa));
				}
				 
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/***************************************************************TRAIGO LOS STOCKS**************************************************************************************/
		
		if(this.getStocks()!=null)//si los articulos no estan en null
		{
			if(this.getStocks().get(articulo.getArticuloBaseColor())!=null)
			{
				stocks = this.getStocks().get(articulo.getArticuloBaseColor());
			}
			else
			{
				
				String consulta ="select IdDeposito, IdArticulo, Stock from MovStockTotal where IdArticulo like '"+articulo.getArticuloBase()+"%' order by IdArticulo,IdDeposito";  
				if(log)
				{
					Logica.actualizarLogSincRepo(idSinc, "INFO: Consultando Stock Cod. ARTICULO "+articulo.getArticulo(), porcentaje,idEmpresa);
				}
				
				Hashtable<String, StockReposicion> hash = Logica.darDeposStock(consulta, "Articulos", idSinc, porcentaje, articulo, stocksReservados, stocksEnviados,idEmpresa);
				stocks = hash;
				List<Hashtable<String, StockReposicion>> stockTodosLosColores = new ArrayList<Hashtable<String,StockReposicion>>();
				
				List<StockReposicion> stocksLista = new  ArrayList<StockReposicion>(stocks.values());
				Hashtable<String, StockReposicion> hashColor = new Hashtable<>();
				String baseAntCol = "";
				boolean pri = true;
				Collections.sort(stocksLista);
				for (StockReposicion s : stocksLista) 
				{
					String baseCurrentCol = s.getArticulo().substring(0,13);
					if(pri)
					{
						pri = false;
						hashColor = new Hashtable<>();
						hashColor.put(s.getArticulo(), s);
					}
					else 
					{
						if(baseAntCol.equals(baseCurrentCol))
						{
							hashColor.put(s.getArticulo(), s);
						}
						else
						{
							this.stocks.put(baseAntCol, hashColor);
							hashColor = new Hashtable<>();
							hashColor.put(s.getArticulo(), s);
						}
					}
					baseAntCol = s.getArticulo().substring(0,13);
				}
				this.stocks.put(baseAntCol, hashColor);
				
				
			}
			
		}
		
		
		//this.articulos.put(articulo.getArticulo(), articulos);
		this.baseColor.put(articulo.getArticuloBaseColor(), baseColor);
		
		this.base.put(articulo.getArticuloBase(), base);
		this.marca.put(articulo.getMarca(),marca);
		this.seccion.put(articulo.getMarca()+"-"+articulo.getSeccion(), seccion);
		this.clase.put(articulo.getMarca()+"-"+articulo.getClase(), clase);
		this.importe.put(articulo.getPrecio(), importe);
		this.sucursal.put(articulo.getSucursal(), sucursal);
		this.genero.put(articulo.getGenero(), genero);
		this.categoria.put(articulo.getCategoria(), categoria);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	 
	 
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
