package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


import beans.api.Utilidades;


import beans.Fecha;

import beans.api.pedidoFactura;
import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.Remito;
import beans.datatypes.RemitoLinea;
import beans.encuentra.ArticuloRepoFromLoad;
import beans.encuentra.Cliente;
import beans.encuentra.Compra;
import beans.encuentra.Compras;
import beans.encuentra.DepositoMayorista;
import beans.encuentra.Items;
import beans.helper.PropertiesHelperAPI;

import integraciones.erp.odoo.laIsla.StockArticulos;
import logica.LogicaAPI;
import logica.Util;




public class MSSQL_API 
{
	private static Connection con = null;
	
	
	
	
	public static void closeFullConnection(Connection con,ResultSet rs,Statement s) throws SQLException{
		rs.close();
		s.close();
		con.close();
	}
	
	public static List <DataDescDescripcion> darIdDescripcionSAP(String consulta) 
	{	
		List <DataDescDescripcion> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;databaseName=STADIUM_DEV2;";
        try(Statement s = con.createStatement();) {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            
            //
            	
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			 DataDescDescripcion d = new DataDescDescripcion(rs.getString(1), rs.getString(2));
	  			 retorno.add(d);
	  			  
	  					
	  		   }
	  		
	  	
	  		closeFullConnection(con, rs, s);
          
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
      return retorno;
      
	}
	
	
	private static void darConexion(int idEmpresa)
	{
		PropertiesHelperAPI pH = null;
		switch (idEmpresa) 
		{
			case 1:
				pH = new PropertiesHelperAPI("SQLSTADIUM");
			break;
			case 2:
				pH = new PropertiesHelperAPI("SQLFORUS");
				break;
			case 4:
				pH = new PropertiesHelperAPI("SQLELREY");
			break;
			default:
				pH = new PropertiesHelperAPI("");
			break;
		}
		
		try 
		{
			pH.loadProperties();
			
			String usuario = pH.getValue("u");
			String pass = pH.getValue("p");
			String connectionUrl = pH.getValue("connectionUrl");
			connectionUrl+=" sendStringParametersAsUnicode=false;";
					        
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl,usuario,pass);
			
		} catch (Exception e) 
			        {
			e.printStackTrace();
		}
		
		
	}
	
	public static List <DataIDDescripcion> darIDDescripcion(String consulta, int idEmpresa) 
	{	
		
		List <DataIDDescripcion> retorno = new ArrayList<>();
				
		
		darConexion(idEmpresa);

		try(Statement s = con.createStatement();) {
        	
            // Establish the connection.
            
            //
            	
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			 DataIDDescripcion d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
	  			 try
	  			 {
	  				 d.setDescripcionB(rs.getString(4));
	  			 }
	  			 catch (Exception e) 
	  			 {
	  				
	  			 }
	  			try
	  			 {
	  				 d.setIdB(rs.getInt(3));
	  			 }
	  			 catch (Exception e) 
	  			 {
					
	  			 }
	  			try
	  			 {
	  				 d.setDescripcionC(rs.getString(5));
	  			 }
	  			 catch (Exception e) 
	  			 {
					
	  			 }
	  			 retorno.add(d);
	  			  
	  					
	  		   }
	  		
	  	
	  		closeFullConnection(con, rs, s);
          
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
      return retorno;
      
	}
	
	
	public static List <DataDescDescripcion> darDescDescripcion(String consulta, int idEmpresa) 
	{	
		
		List <DataDescDescripcion> retorno = new ArrayList<>();
				
		
		darConexion(idEmpresa);

		try(Statement s = con.createStatement();) {
        	
            // Establish the connection.
            
            //
            	
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			DataDescDescripcion d = new DataDescDescripcion(rs.getString(1), rs.getString(2));
	  			
	  			 retorno.add(d);
	  			  
	  					
	  		   }
	  		
	  	
	  		closeFullConnection(con, rs, s);
          
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
      return retorno;
      
	}
	

	
	public static Map<Integer,pedidoFactura> darFacturasVisual(String consulta, Map<Integer,pedidoFactura> pedidos) 
	{	
				
		// Create a variable for the connection string.
		String connectionUrl="jdbc:sqlserver://200.40.45.126:1433;databaseName=VsReyEntreteDoc;";
        try (Statement s = con.createStatement();){
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"encuentra","sqlencuentra");
            
	  		ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			try {
	  				//String base64 = rs.getString(2);
	  				 byte[] base64b = rs.getBytes(2);
		  			 String pdf = "";
		  			 pdf = Util.bytesToPdf(base64b, "VCO"+rs.getInt(1));
		  			 pedidos.get(rs.getInt(1)).setPdf(pdf);	
				} catch (Exception e) {e.printStackTrace();}
	  			 			
	  		   }		
	  		 
	  		closeFullConnection(con, rs, s);          
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
      return pedidos;
      
	}
	
	
	public static Hashtable<Integer,ArticuloRepoFromLoad> darPickingsOB() 
	{	
		Hashtable<Integer,ArticuloRepoFromLoad> retorno = new Hashtable<Integer, ArticuloRepoFromLoad>();
		
		try
		{
			String consulta = "select AR.skuName, TOR.idTienda,TI.idTienda, sum(cast (SL.replenishmentQuantity AS int)) CANTIDAD\r\n"
					+ "from Symphony_ReplenishmentDistributionLog SL\r\n"
					+ "inner join\r\n"
					+ "(\r\n"
					+ "	select  TRY_CAST([Stock Location Name] as INT) idTienda,[Stock Location ID],[SL Description] from BI_StockLocations where TRY_CAST([Stock Location Name] as INT)>0 \r\n"
					+ ") TI\r\n"
					+ "ON TI.[Stock Location ID]=SL.stockLocationID\r\n"
					+ "inner join\r\n"
					+ "(\r\n"
					+ "	select  TRY_CAST([Stock Location Name] as INT) idTienda,[Stock Location ID],[SL Description] from BI_StockLocations where TRY_CAST([Stock Location Name] as INT)>0 \r\n"
					+ ") TOR\r\n"
					+ "ON TOR.[Stock Location ID]=SL.originStockLocation\r\n"
					+ "INNER JOIN\r\n"
					+ "(\r\n"
					+ "	SELECT skuId, skuName FROM Symphony_SKUs\r\n"
					+ ") AR\r\n"
					+ "\r\n"
					+ "ON AR.skuID = SL.skuID\r\n"
					+ "GROUP BY AR.skuName, TOR.idTienda,TI.idTienda\r\n"
					+ "	\r\n"
					+ "";
			
			// Create a variable for the connection string.
			//String connectionUrl="jdbc:sqlserver://200.58.149.126:5435;databaseName=Stadium_OneBeat;";
			String connectionUrl="jdbc:sqlserver://10.108.0.111:1433;databaseName=Stadium_OneBeat;";
			 // Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			
			
	        try (Statement s = con.createStatement();){
	        	
	           
	            
		  		ResultSet rs = s.executeQuery (consulta);
		  		 
		  		 
		  		 while (rs.next())
		  		   {   
		  			 int destino = rs.getInt(3);
		  			 int origen = rs.getInt(2);
		  			 int cantidad = rs.getInt(4);
		  			 String articulo = rs.getString(1);
		  				
		  			try 
		  			{
		  				if(retorno.containsKey(destino))
		  				{
		  					ArticuloRepoFromLoad ar = retorno.get(destino);
		  					ar.getArticulosCantidad().add(new DataIDDescripcion(cantidad, articulo));
		  					
		  					retorno.put(destino, ar);
		  				}
		  				else
		  				{
		  					ArticuloRepoFromLoad ar = new ArticuloRepoFromLoad(destino, false, 1, origen, 1, new ArrayList<DataIDDescripcion>());
		  					ar.getArticulosCantidad().add(new DataIDDescripcion(cantidad, articulo));
		  					
		  					retorno.put(destino, ar);
		  					
		  				}
		  				
					} 
		  			catch (Exception e) {e.printStackTrace();}
		  			 			
		  		   }		
		  		 
		  		closeFullConnection(con, rs, s);          
	      }
	      // Handle any errors that may have occurred.
	      catch (Exception e) {
	          e.printStackTrace();
	      }
		}
		catch (Exception e) 
		{
			
		}
		
     
      return retorno;
      
	}
	
	
	public static List <DataIDDescripcion> darEcommerceDocumentosEntregas(String consulta, int idEmpresa) 
	{	
		List <DataIDDescripcion> retorno = new ArrayList<>();
				
		 // Establish the connection.
        darConexion(idEmpresa);
        
        //
        try (Statement s = con.createStatement();){
        	            		 	  		 
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			 DataIDDescripcion d = new DataIDDescripcion(rs.getInt(3), rs.getString(2));
	  		
	  			try
	  			 {
	  				 d.setDescripcionB(rs.getString(1));
	  			 }
	  			 catch (Exception e) 
	  			 {
					e.printStackTrace();
	  			 }
	  			 retorno.add(d);
	  			  
	  					
	  		   }
	  		
	  	
	  		closeFullConnection(con, rs, s);
          
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
      return retorno;
      
	}
	
	public static Map<String, Double> darArtPrecioVisual(String idArticulo, Integer idEmpresa) {
		Map<String, Double> retorno =  new HashMap<>();
		String consulta="SELECT idArticulo, isnull(Precio,0.0) FROM ArticuloLP WHERE idArticulo IN ('"+idArticulo+"')";
		
		darConexion(idEmpresa);
		
		try(Statement s = con.createStatement();)
		{
	  		ResultSet rs = s.executeQuery (consulta);
	  		
	  		while (rs.next())
	  		{   
	  			String sku = rs.getString(1);
	  			Double precio = rs.getDouble(2);
	  			try 
	  			{
	  				retorno.put(sku, precio);
				} 
	  			catch (Exception e) 
	  			{
					e.printStackTrace();
				}	  			
	  		}
	  			  		
      }
      catch (Exception e) 
		{
          e.printStackTrace();
		}
		return retorno;
	}

	public static List<DTO_Articulo> darArticulosVSS(String consultaArticulos, int idEmpresa) 
	{
		darConexion(idEmpresa);
		List<DTO_Articulo> retorno = new ArrayList<DTO_Articulo>();
		
		try(Statement s = con.createStatement();)
		{
	  		ResultSet rs = s.executeQuery (consultaArticulos);
	  		
	  		while (rs.next())
	  		{   
	  			DTO_Articulo art = new DTO_Articulo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getString(9), rs.getString(10));
	  			art.setIdGenero(rs.getString(11));
	  			art.setImagen(rs.getString(12));
	  			art.setCodigoBarras(new ArrayList<String>());
	  			retorno.add(art);
	  		}
	  			  		
      }
      catch (Exception e) 
		{
          e.printStackTrace();
		}
		return retorno;
	}

	public static List<DepositoMayorista> darDepositosVSS(String consultaDepositos, int idEmpresa) 
	{
		darConexion(idEmpresa);
		List<DepositoMayorista> retorno = new ArrayList<DepositoMayorista>();
		
		try(Statement s = con.createStatement();)
		{
	  		ResultSet rs = s.executeQuery (consultaDepositos);
	  		
	  		while (rs.next())
	  		{   
	  			
	  			DepositoMayorista dep = new DepositoMayorista(rs.getString(1), rs.getString(2), rs.getString(3), "LOCAL");
	  			dep.setDepartamento(rs.getString(4));
	  			dep.setCiudad(rs.getString(5));
	  			retorno.add(dep);
	  		}
	  			  		
      }
      catch (Exception e) 
		{
          e.printStackTrace();
		}
		return retorno;
	}

	public List<StockArticulos> darStockVS(String consultaStock, int idEmpresa) 
	{
		darConexion(idEmpresa);
		List<StockArticulos> retorno = new ArrayList<StockArticulos>();
		
		try(Statement s = con.createStatement();)
		{
	  		ResultSet rs = s.executeQuery (consultaStock);
	  		
	  		while (rs.next())
	  		{   
	  			
	  			retorno.add(new StockArticulos(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
	  		}
	  			  		
      }
      catch (Exception e) 
		{
          e.printStackTrace();
		}
		return retorno;
	}

	
	
	public static List<Compras> darComprasWeb(String query,Hashtable<String, String> depositosPickHT,int ultimaVenta, int idEmpresa, Hashtable<String, DataIDDescripcion> destinoPedidos, int idCanal) 
	{
		LogicaAPI Logica = new LogicaAPI();
		List<Compras> pedidos = new ArrayList<>();
		Compras compra;
		Compra c;
		Cliente cli;
		List<Items> items;
		int ultimoId=ultimaVenta;
		
		
		
		String consulta = query;
		if(ultimaVenta!=0)
		{
			consulta += " > "+ultimaVenta;
			
			
		}
		
		
		
		consulta+=" order by  DocV_NumeroDoc";
		
		 String connectionUrl = 
		           "jdbc:sqlserver://179.27.145.162:2833;" +
		           "databaseName=VsECommerce;";
		        // Declare the JDBC objects.
		        try {
		        	
		            // Establish the connection.
		            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
		            			        
			        Statement s = con.createStatement();
			        ResultSet rs = s.executeQuery (consulta);
					
					while (rs.next())
					{
						try {
							compra = new Compras();
							c = new Compra();
							String comentarioPick = rs.getString(10);
							String[] arreglo = null;
							String canal = "";
							boolean cNc = false;
							if(comentarioPick.contains(" PickUP: "))
							{
								
								if(comentarioPick.contains("(Click and Collect)"))
								{
									comentarioPick = comentarioPick.replace(" (Click and Collect)", "");
									cNc = true;
								}
								arreglo = comentarioPick.split(" PickUP: ");
								comentarioPick = arreglo[1].replace(" ", "");
								arreglo = arreglo[0].split("NRO: ");
								c.setIdVenta(arreglo[1].replace(" ", ""));
								c.setcNc(cNc);
								//canal = arreglo[0].split("Compra web en ")[1].replace(" ", "");
								try {
									canal = destinoPedidos.get(c.getIdVenta()).getDescripcion();									
								} catch (Exception e) {
									canal = "0";
								}
								try {									
									c.setSucursal(""+destinoPedidos.get(c.getIdVenta()).getId());
								} catch (Exception e) {
									c.setSucursal("");
								}
							}
							else
							{
								try
								{
									arreglo = rs.getString(10).split("NRO: ");
									c.setIdVenta(arreglo[1].replace(" ",""));
								}
								catch (Exception e) {}
								try
								{
									canal = destinoPedidos.get(c.getIdVenta()).getDescripcion();
								}
								catch (Exception e) {canal="0";}
								//canal = arreglo[0].split("Compra web en ")[1].replace(" ", "");
							}
							if(Integer.parseInt(canal)==idCanal)
							{
								c.setIdCanal(canal);
								cli = new  Cliente();	
								ultimoId = rs.getInt(2);
								c.setImporte(rs.getString(1));
								c.setId(rs.getString(2));
								c.setEstado("preparando");
								c.setFecha(rs.getString(4));
								c.setMoneda(rs.getString(5));
								c.setOrigen(rs.getString(6));
								c.setMontoEnvio(rs.getString(7));
								c.setMetodoPago(rs.getString(8));
								c.setSerie(rs.getString(9));
								c.setFecha(rs.getString(25).substring(0,rs.getString(25).length()-2));
								
								items = darComprasWeb_items(c.getId(), c.getSerie());   
						        c.setItems(items);
							
						        Utilidades utilidades = new Utilidades();
								cli.setApellido(utilidades.validarComillas(rs.getString(11))); //Cambio nuevo - Andres
								cli.setNombre(utilidades.validarComillas(rs.getString(12))); //Cambio nuevo - Andres
								cli.setLocalidad(rs.getString(13));
								cli.setEmail(rs.getString(14));
								cli.setDepartamento(rs.getString(15));
								cli.setLatitud(rs.getString(16));
								cli.setTelefono(rs.getString(17));
								cli.setLongitud(rs.getString(18));
								cli.setCalle(utilidades.validarComillas(rs.getString(19))); //Cambio nuevo - Andres
								String calle = cli.getCalle();
								String[]direccion = calle.split(",");
								cli.setCalle(direccion[0]);
								for(String d:direccion){
									if(d.contains("nro. ")){
										cli.setNroPuerta(d.replace("nro. ", ""));
									}
									if(d.contains("apto. ")){
										cli.setNroApto(d.replace("apto. ", ""));					
									}
								}
								
								
								cli.setCiudad(rs.getString(20));
								cli.setCp(rs.getString(21));
								cli.setObs(rs.getString(22));						
								cli.setDocumentoTipo(rs.getString(23));
								cli.setDocumentoNro(rs.getString(24));
								
								compra.setCompra(c);
								compra.setCliente(cli);
								pedidos.add(compra);
													
								System.out.println(" vta "+ultimoId);
							}
							
							
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						
					}
					
					
					if(ultimoId>ultimaVenta && ultimaVenta!=0){
						Logica.persistir("insert into ecommerce_sincro (ultimaVenta, idEmpresa, idCanal) values ("+ultimoId+","+idEmpresa+","+idCanal+")");
					}
					
					
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
					if(ultimoId>ultimaVenta  && ultimaVenta!=0){
						Logica.persistir("insert into ecommerce_sincro (ultimaVenta, idEmpresa, idCanal) values ("+ultimoId+","+idEmpresa+","+idCanal+")");
					}
				}
			
				return pedidos;
	}
	
	public static List<Items> darComprasWeb_items(String vta,String serie) 
	{
		
		Items i;
		List<Items> items = new ArrayList<>(); ;
		
		 String connectionUrl = 
		           "jdbc:sqlserver://179.27.145.162:2833;" +
		           "databaseName=VsHushPuppies;";
		        // Declare the JDBC objects.
		        try {
		        	
		            // Establish the connection.
		            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
		          
		            String query =	"SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; "
		            		+ " SELECT a.idarticulo,a.\"Cantidad pendiente\",origen,NumeroDoc from [VsHushPuppies].[dbo].[encuentra_distribucion] a "
		            		+ "where origen != 1200 and Comentario like CONCAT('Trans.Venta Dep. 1200 Doc. ','"
							+serie+"','-','"+vta+"','%') "+
		            		"UNION ALL "+
							"SELECT a.idarticulo,a.\"Cantidad pendiente\",origen,NumeroDoc from [VsHushPuppies].[dbo].[encuentra_distribucion_terminada] a "
							+ "where origen != 1200 and Comentario like CONCAT('Trans.Venta Dep. 1200 Doc. ','"
							+serie+"','-','"+vta+"','%') ";		        
			        Statement s = con.createStatement();
			        ResultSet rs = s.executeQuery (query);
					
					while (rs.next())
					{
			        	i = new Items();
			        	i.setSku(rs.getString(1));
			        	i.setCod(rs.getString(1));
			        	i.setCant(String.valueOf(rs.getInt(2)));
			        	i.setImporte("0.0");
			        	i.setOrigen(rs.getString(3));
			        	i.setDocVisual(rs.getString(4));
			        	items.add(i);				
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return items;
	}
	
	

	
	public static List<Remito> DarRemitosForus(String depo, String depoO, boolean TR, int idEmpresa) 
	{
		String qDepoOrigen = "";
		String qTR = "";
		if(depoO!=null && depoO.equals(""))
		{
			qDepoOrigen = " and Origen = "+depoO+" ";
		}
		if(TR)
		{
			qTR = " AND  Origen not in (9000, 1200) ";
		}
		
		String q1 = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT t0.IdDocumento, t0.EntregaAfecta, t0.Comentario, "
				+ "t0.NumeroDoc, t0.origen, t0.Destino, t0.Fecha, t0.cantidad, t0.IdArticulo, t1.cantidadTotal\r\n" + 
				"FROM dbo.encuentra_transferencias_pendientes t0\r\n" + 
				"INNER JOIN \r\n" + 
				"(\r\n" + 
				"SELECT NumeroDoc, SUM(cantidad) cantidadTotal\r\n" + 
				"FROM dbo.encuentra_transferencias_pendientes\r\n" + 
				"WHERE Destino = "+depo+"\r\n" + 
				"GROUP BY NumeroDoc\r\n" + 
				") t1 ON t1.NumeroDoc = t0.NumeroDoc\r\n" + 
				"WHERE  t0.Destino = "+depo;
		String q2 = "ORDER BY t0.IdDocumento;";
			
		System.out.println(q1+qDepoOrigen+qTR+q2);
		String query = q1+qDepoOrigen+qTR+q2;
		List<Remito> retorno = new ArrayList<>();		
		        try {		        	
		        	darConexion(idEmpresa);
		           	        
			        Statement s = con.createStatement();
			        ResultSet rs = s.executeQuery (query);
					
			        
			        List<RemitoLinea> lineas = null;
			        Remito remitoAnt = null;
			        int idDocAnt = 0;
			        boolean pri = true;
			        Hashtable<String, String> entregas = null;
			        
					while (rs.next())
					{
						if(rs.getInt(4)==14989) {
							System.out.println("caso");
						}
						int idDocAct = rs.getInt(1);
						Fecha fecha = new Fecha(rs.getString(7));
						if(pri)
						{
							pri = false;
							remitoAnt = new Remito(rs.getInt(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(10), rs.getString(3), fecha.darFechaDia_Mes_Anio_Barra());
							lineas = new ArrayList<>();
							lineas.add(new RemitoLinea(rs.getString(9), rs.getInt(8), rs.getString(2)));
							entregas = new Hashtable<>();
							entregas.put(rs.getString(2),rs.getString(2));
							
							
							
						}
						else if(idDocAct==idDocAnt)
						{
							lineas.add(new RemitoLinea(rs.getString(9), rs.getInt(8), rs.getString(2)));
							entregas.put(rs.getString(2),rs.getString(2));
						}
						else
						{
							remitoAnt.setLineas(lineas);
							remitoAnt.setEntregasAfecta(new ArrayList<>(entregas.values()));
							retorno.add(remitoAnt);
							
							remitoAnt = new Remito(rs.getInt(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(10), rs.getString(3), fecha.darFechaDia_Mes_Anio_Barra());
							lineas = new ArrayList<>();
							lineas.add(new RemitoLinea(rs.getString(9), rs.getInt(8), rs.getString(2)));
							entregas = new Hashtable<>();
							entregas.put(rs.getString(2),rs.getString(2));
							
							
						}
						
						idDocAnt=idDocAct;
					}
					closeFullConnection(con, rs, s);
					
					remitoAnt.setLineas(lineas);
					remitoAnt.setEntregasAfecta(new ArrayList<>(entregas.values()));
					retorno.add(remitoAnt);		
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return retorno;
	}

}