package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import beans.Articulo;
import beans.ArticuloAnysys;
import beans.ArticuloForus;
import beans.ArticuloReposicion;
import beans.Fecha;
import beans.Proveedor;
import beans.RepoDimension;
import beans.StockReposicion;
import beans.encuentra.ArticuloConteo;
import beans.encuentra.ColorOC;
import beans.encuentra.DataArtMovS;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataArticuloConsulta;
import beans.encuentra.DataArticuloOC;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.RecepcionExpedicion;
import beans.encuentra.Remito;
import beans.encuentra.RemitoLinea;
import beans.encuentra.TalleOC;
import dataTypes.ArticuloPedido;
import dataTypes.DataClarksParametroTienda;
import dataTypes.DataDepositoSAP;
import dataTypes.DataDescDescripcion;
import dataTypes.DataDocVisual;
import dataTypes.DataEtiquetaComponentes;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import dataTypes.DataIDIDID;
import dataTypes.DataIRotaFT;
import dataTypes.DataListaTRE;
import dataTypes.DataOC;
import dataTypes.DataOVOsoneca;
import dataTypes.DataOVOsonecaLineas;
import dataTypes.DataRowSincAnalizer;
import dataTypes.DataSalesO;
import dataTypes.DataTalleCantidad;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.Compra;
import eCommerce_jsonObjectsII.Compras;
import eCommerce_jsonObjectsII.Items;
import helper.PropertiesHelper;
import logica.EnviaMail;
import logica.Logica;
import logica.Utilidades;



public class MSSQL 
{
	private static Connection con = null;
	
	
	public static List <DataDescDescripcion> darIdDescripcion(String consulta) 
	{	
		List <DataDescDescripcion> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
        String connectionUrl = 
          "jdbc:sqlserver://10.108.0.1:1433;" +
        			
           "databaseName=VsStadium;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            
            //
            	
        		
	  		Statement s = con.createStatement();
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
	
	
	
	public static List <DataDescDescripcion> darIdDescripcionSAP(String consulta) 
	{	
		List <DataDescDescripcion> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;databaseName=STADIUM_DEV2;";
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            
            //
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 	  		 
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
	
	public static List <DataDescDescripcion> darIdDescripcionOSO(String consulta) 
	{	
		List <DataDescDescripcion> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
        String connectionUrl = 
          "jdbc:sqlserver://osonecasap:1433;" +
        			
           "databaseName=Productiva_Osoneca;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","Sql0sonecaS4p");
            
            //
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 	  		 
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
	
	
	
	public static List <DataIDIDID> darIdIdId(String consulta) 
	{	
		List <DataIDIDID> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
        String connectionUrl = 
          "jdbc:sqlserver://10.108.0.1:1433;" +
        			
           "databaseName=VsStadium;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            
            //
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 	  		 
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			DataIDIDID d = new DataIDIDID();
	  			d.setId1(rs.getInt(1));
	  			d.setId2(rs.getInt(2));
	  			d.setId3(rs.getInt(3));
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
	
	

	
	
	
	public static List <DataIDDescripcion> darIDDescripcion(String consulta, int idEmpresa) 
	{	
		List <DataIDDescripcion> retorno = new ArrayList<>();
				
		
        try {
        	
            // Establish the connection.
            darConexion(idEmpresa);
            
            //
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 	  		 
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			 DataIDDescripcion d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
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
	
	
	public static List <DataIDDescripcion> darIDDescripcionSAP(String consulta) 
	{	
		List <DataIDDescripcion> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;databaseName=STADIUM_DEV2;";
	    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
		// Establish the connection.
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            
            //
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 	  		 
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			 DataIDDescripcion d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
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
	
	
	
	
	public static List <DataIDDescDescripcion> darIDDescDescripcion(String consulta) 
	{	
		List <DataIDDescDescripcion> retorno = new ArrayList<>();
				
		
            
            String connectionUrl = "jdbc:sqlserver://179.27.145.162:2833;" +
        "databaseName=VsHushPuppies;";
     // Declare the JDBC objects.
     try {
     	
         // Establish the connection.
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
         
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 	  		 
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			DataIDDescDescripcion d = new DataIDDescDescripcion(rs.getInt(1), rs.getString(3),rs.getString(2));
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

	
	
	public static List <DataDescDescripcion> darIdDescripcionListaRepos(String consulta) 
	{	
		List <DataDescDescripcion> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://10.108.0.1:1433; databaseName=VsStadium;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            
            //
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 	  		 
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {
	  			 String fechaSQL = rs.getString(3).substring(0,11);
	  			 
	  			 
	  			 DataDescDescripcion d = new DataDescDescripcion(rs.getString(1), rs.getString(2));
	  			 d.setFecha(fechaSQL);
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
	public static List<DataOC> darOrdenesDeCompra(String consulta,DataDescDescripcion provedor,int idEmpresa){

		
		
		String ordenesIn = "";
		
		List<String> ordenesNro = darNroOrdenesDeCompra(Integer.parseInt(provedor.getId()));
		
		for (String o : ordenesNro) 
		{
			ordenesIn+= o+",";
		}
		
		System.out.println(ordenesIn);
		ordenesIn = ordenesIn.substring(0,ordenesIn.length()-1);
		
		Logica logica = new Logica();
		
		Hashtable<String, Integer> unidadesAgendadas =  logica.darCantidadRecibida(ordenesIn,idEmpresa);
		
		
		List<DataOC>ordenes=new ArrayList<>();
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://179.27.145.162:2833; databaseName=VsHushPuppies;";
					    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
				
			  // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
            
            //
		
            
             Statement s = con.createStatement();
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 //variables varias
	  		boolean pri = true;
	  		String colAnt = null;
	  		String colAct;
	  		String ArtAnt = null;
	  		String ArtAct;
	  		int idOCAnt = 0;
	  		int idOCAct;
	  		DataOC ordenCAnt = null;
	  		DataArticuloOC articuloAnt = null;
	  		ColorOC colorAnt = null;
	  		int uRecibidas = 0;
	  		int uSolicitadas = 0;
	  		Hashtable<String, DataIDDescripcion> curva= null;
	  		List<ColorOC>colores = null;
	  		List<TalleOC>talles = null;
	  		List <DataArticuloOC> articulos = new ArrayList<>();
	  			  		 
	  		while (rs.next())
	  		   {
	  			idOCAct = rs.getInt(1);
	  			ArtAct = rs.getString(6);
	  			colAct = rs.getString(7);
	  			if(pri)
	  				{
	  					pri = false;
	  					
	  					
	  					//SETEO LA OC
	  					ordenCAnt  = new DataOC();
	  					uSolicitadas = 0;
	  					uRecibidas = 0;
	  					ordenCAnt.setComentario(rs.getString(5));
	  					ordenCAnt.setEstado(rs.getString(12));
	  					
	  					String fechaLarga = rs.getString(3);
	  		  			
	  		  			String mes = fechaLarga.substring(5,7);
	  		  			String anio = fechaLarga.substring(0, 4);
	  		  			String dia = fechaLarga.substring(8, 10);
	  		  			ordenCAnt.setFecha(dia + "/"+mes+"/"+anio);
	  		  			
		  		  		String fechaLargaR = rs.getString(3);
	  		  			
	  		  			String mesR = fechaLargaR.substring(5,7);
	  		  			String anioR = fechaLargaR.substring(0, 4);
	  		  			String diaR = fechaLargaR.substring(8, 10);
	  		  			ordenCAnt.setFechaEntrega(diaR + "/"+mesR+"/"+anioR);
	  		  			ordenCAnt.setIdDocumento(rs.getInt(1));
	  		  			ordenCAnt.setNumeroDocumento(rs.getInt(2));
	  		  			ordenCAnt.setProveedor(provedor);
	  		  			
	  		  				  		  			
	  		  			//creo el articulo
	  		  			articuloAnt = new DataArticuloOC();
	  		  			articuloAnt.setIdArticulo(rs.getString(6));
	  		  		    articuloAnt.setDescripcionCorta(rs.getString(17));
	  		  			articuloAnt.setIdLinea(rs.getInt(14));
	  		  			colores = new ArrayList<>();
	  		  			curva = new Hashtable<>();
	  		  			
	  		  			
	  		  			ArtAnt  = rs.getString(6);
	  		  			//creo el color
	  		  			colorAnt = new ColorOC();
	  		  			colorAnt.setId(rs.getString(7));
	  		  			colorAnt.setDescripcion(rs.getString(13));
	  		  			talles = new ArrayList<>();
	  		  			
	  		  			
	  		  			
	  		  			//creo el talle y lo agrego a la curva y al color
	  		  			TalleOC talle = new TalleOC();
	  		  			talle.setEntregada(rs.getInt(11));
	  		  			talle.setSolicitada(rs.getInt(10));
	  		  			talle.setTalle(rs.getString(8));
	  		  			talle.setIdLinea(rs.getInt(14));
	  		  			
	  		  			//sumo las unidades al recien creado
	  		  			
	  		  			
	  		  			uRecibidas += rs.getInt(11);
	  		  			uSolicitadas += rs.getInt(10);
	  		  			
	  		  			
	  		  			DataIDDescripcion tal = new DataIDDescripcion(1,rs.getString(8));
	  		  			talles.add(talle);
	  		  			if(curva.get(rs.getString(8))==null)//pregunto si no tiene el talle ya adentro
	  		  			{
	  		  				curva.put(rs.getString(8), tal);
	  		  			}
	  		  			
	  		  			
  		  			
	  		  			
	  				}
	  				else
	  				{
	  					if(idOCAct!=idOCAnt)//cambio la OC
	  					{
	  						ordenCAnt.setUnidadesRecibidas(uRecibidas);
	  						ordenCAnt.setUnidadesSolicitadas(uSolicitadas);
	  						//no se si va aca
	  						articuloAnt.setCurvaH(curva);
  							//agrego la curva al color ant
  							colorAnt.setTalles(talles);
  							
  							colores.add(colorAnt);
  							
  							
  							articuloAnt.setColores(colores);
  							
  							articulos.add(articuloAnt);
  							//no se si va
	  						
	  						ordenCAnt.setArticulos(articulos);
	  						
	  						uRecibidas = 0;
	  						uSolicitadas = 0;
	  						articulos = new ArrayList<>();
	  							
	  						
	  						ordenes.add(ordenCAnt);
	  						//SETEO LA OC
		  					ordenCAnt  = new DataOC();
		  					uSolicitadas = 0;
		  					uRecibidas = 0;
		  					ordenCAnt.setComentario(rs.getString(5));
		  					ordenCAnt.setEstado(rs.getString(12));
		  					
		  					String fechaLarga = rs.getString(3);
		  		  			
		  		  			String mes = fechaLarga.substring(5,7);
		  		  			String anio = fechaLarga.substring(0, 4);
		  		  			String dia = fechaLarga.substring(8, 10);
		  		  			ordenCAnt.setFecha(dia + "/"+mes+"/"+anio);
		  		  			
			  		  		String fechaLargaR = rs.getString(3);
		  		  			
		  		  			String mesR = fechaLargaR.substring(5,7);
		  		  			String anioR = fechaLargaR.substring(0, 4);
		  		  			String diaR = fechaLargaR.substring(8, 10);
		  		  			ordenCAnt.setFechaEntrega(diaR + "/"+mesR+"/"+anioR);
		  		  			ordenCAnt.setIdDocumento(rs.getInt(1));
		  		  			ordenCAnt.setNumeroDocumento(rs.getInt(2));
		  		  			ordenCAnt.setProveedor(provedor);
		  		  			
		  		  			
		  		  		
		  		  			
		  		  			//creo el articulo
		  		  			articuloAnt = new DataArticuloOC();
		  		  			articuloAnt.setIdArticulo(rs.getString(6));
		  		  			articuloAnt.setDescripcionCorta(rs.getString(17));
		  		  			articuloAnt.setIdLinea(rs.getInt(14));
		  		  			colores = new ArrayList<>();
		  		  			curva = new Hashtable<>();
		  		  			
		  		  			
		  		  			ArtAnt  = rs.getString(6);
		  		  			//creo el color
		  		  			colorAnt = new ColorOC();
		  		  			colorAnt.setId(rs.getString(7));
		  		  			colorAnt.setDescripcion(rs.getString(13));
		  		  			talles = new ArrayList<>();
		  		  			
		  		  			
		  		  			
		  		  			//creo el talle y lo agrego a la curva y al color
		  		  			TalleOC talle = new TalleOC();
		  		  			talle.setEntregada(rs.getInt(11));
		  		  			talle.setSolicitada(rs.getInt(10));
		  		  			talle.setTalle(rs.getString(8));
		  		  			talle.setIdLinea(rs.getInt(14));
		  		  			
		  		  			//sumo las unidades al recien creado
		  		  			uRecibidas += rs.getInt(11);
		  		  			uSolicitadas += rs.getInt(10);
		  		  			
		  		  			
		  		  			DataIDDescripcion tal = new DataIDDescripcion(1,rs.getString(8));
		  		  			talles.add(talle);
		  		  			if(curva.get(rs.getString(8))==null)//pregunto si no tiene el talle ya adentro
		  		  			{
		  		  				curva.put(rs.getString(8), tal);
		  		  			}
	  						
	  						
	  					}
	  					else//no cambio la OC
	  					{
	  						if(!ArtAnt.equals(rs.getString(6)))//cambio el articulo
	  						{
	  							
	  							articuloAnt.setCurvaH(curva);
	  							//agrego la curva al color ant
	  							colorAnt.setTalles(talles);
	  							Hashtable<String, TalleOC> tallesH = new Hashtable<>();
	  							for (TalleOC t : talles) 
	  							{
	  								tallesH.put(t.getTalle(), t);
								}
	  							colorAnt.setTallesH(tallesH);
	  							colores.add(colorAnt);
	  							
	  							
	  							articuloAnt.setColores(colores);
	  							
	  							articulos.add(articuloAnt);
	  							
	  							
	  							//creo el articulo
	  		  		  			articuloAnt = new DataArticuloOC();
	  		  		  			articuloAnt.setIdArticulo(rs.getString(6));
	  		  		  			articuloAnt.setDescripcionCorta(rs.getString(17));
	  		  		  			articuloAnt.setIdLinea(rs.getInt(14));
	  		  		  			colores = new ArrayList<>();
	  		  		  			curva = new Hashtable<>();
	  		  		  			
	  		  		  			
	  		  		  			ArtAnt  = rs.getString(6);
	  		  		  			//creo el color
	  		  		  			colorAnt = new ColorOC();
	  		  		  			colorAnt.setId(rs.getString(7));
	  		  		  			colorAnt.setDescripcion(rs.getString(13));
	  		  		  			talles = new ArrayList<>();
	  		  		  			
	  		  		  			
	  		  		  			
	  		  		  			//creo el talle y lo agrego a la curva y al color
	  		  		  			TalleOC talle = new TalleOC();
	  		  		  			talle.setEntregada(rs.getInt(11));
	  		  		  			talle.setSolicitada(rs.getInt(10));
	  		  		  			talle.setTalle(rs.getString(8));
	  		  		  			talle.setIdLinea(rs.getInt(14));
	  		  		  			
	  		  		  			//sumo las unidades al recien creado
	  		  		  			uRecibidas += rs.getInt(11);
	  		  		  			uSolicitadas += rs.getInt(10);
	  		  		  			
	  		  		  			
	  		  		  			DataIDDescripcion tal = new DataIDDescripcion(1,rs.getString(8));
	  		  		  			talles.add(talle);
	  		  		  			if(curva.get(rs.getString(8))==null)//pregunto si no tiene el talle ya adentro
	  		  		  			{
	  		  		  				curva.put(rs.getString(8), tal);
	  		  		  			}
	  							
	  							
	  							
	  							
	  						}
	  						else
	  						{
	  							if(!colAnt.equals(rs.getString(7)))//cambio el color
	  							{
	  								//agrego la curva al color ant
	  								colorAnt.setTalles(talles);
	  								Hashtable<String, TalleOC> tallesH = new Hashtable<>();
	  								for (TalleOC t : talles) 
	  								{
	  									tallesH.put(t.getTalle(), t);
									}
	  								colorAnt.setTallesH(tallesH);
	  								colores.add(colorAnt);
	  								
	  								//creo el color
	  			  		  			colorAnt = new ColorOC();
	  			  		  			colorAnt.setId(rs.getString(7));
	  			  		  			colorAnt.setDescripcion(rs.getString(13));
	  			  		  			talles = new ArrayList<>();
	  								
	  			  		  			//creo el talle y lo agrego a la curva y al color
	  			  		  			TalleOC talle = new TalleOC();
	  			  		  			talle.setEntregada(rs.getInt(11));
	  			  		  			talle.setSolicitada(rs.getInt(10));
	  			  		  			talle.setTalle(rs.getString(8));
	  			  		  			talle.setIdLinea(rs.getInt(14));
	  			  		  			//sumo las unidades al recien creado
	  			  		  			uRecibidas += rs.getInt(11);
	  			  		  			uSolicitadas += rs.getInt(10);
	  			  		  			
	  			  		  			
	  			  		  			DataIDDescripcion tal = new DataIDDescripcion(1,rs.getString(8));
	  			  		  			talles.add(talle);
	  			  		  			if(curva.get(rs.getString(8))==null)//pregunto si no tiene el talle ya adentro
	  			  		  			{
	  			  		  				curva.put(rs.getString(8), tal);
	  			  		  			}
	  								
	  								
	  								
	  								
	  							}
	  							else
	  							{
	  								//cambio el talle
	  								//creo el talle y lo agrego a la curva y al color
	  			  		  			TalleOC talle = new TalleOC();
	  			  		  			talle.setEntregada(rs.getInt(11));
	  			  		  			talle.setSolicitada(rs.getInt(10));
	  			  		  			talle.setTalle(rs.getString(8));
	  			  		  			talle.setIdLinea(rs.getInt(14));
	  			  		  			
	  			  		  			DataIDDescripcion tal = new DataIDDescripcion(1,rs.getString(8));
	  			  		  			talles.add(talle);
	  			  		  			if(curva.get(rs.getString(8))==null)//pregunto si no tiene el talle ya adentro
	  			  		  			{
	  			  		  				curva.put(rs.getString(8), tal);
	  			  		  			}
	  			  		  			
	  			  		  			//sumo las unidades al recien creado
	  			  		  			uRecibidas += rs.getInt(11);
	  			  		  			uSolicitadas += rs.getInt(10);
	  			  		  			
	  								
	  							}
	  						}
	  						
	  					}
	  				}
	  			colAnt = colAct;
	  			ArtAnt = ArtAct;
	  			idOCAnt = idOCAct;
	  		   }
	  		
	  		//agrego el ultimo fuera del rs
	  		articuloAnt.setCurvaH(curva);
			//agrego la curva al color ant
			colorAnt.setTalles(talles);
			Hashtable<String, TalleOC> tallesH = new Hashtable<>();
			for (TalleOC t : talles) 
			{
				tallesH.put(t.getTalle(), t);
			}
			colorAnt.setTallesH(tallesH);
			colores.add(colorAnt);
			articuloAnt.setColores(colores);
			articulos.add(articuloAnt);
	  		
	  		ordenCAnt.setUnidadesRecibidas(uRecibidas);
			ordenCAnt.setUnidadesSolicitadas(uSolicitadas);
			ordenCAnt.setArticulos(articulos);
				
			ordenes.add(ordenCAnt); 
			closeFullConnection(con, rs, s);
	  		
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          //e.printStackTrace();
          List<DataOC> listVacia = new ArrayList<>();
          return listVacia;
      }
        
        for (DataOC d : ordenes) 
        {
        	
        	for (DataArticuloOC a : d.getArticulos()) 
        	{
					System.out.println("**************ARTICULO "+a.getIdArticulo());
					for (ColorOC co : a.getColores()) 
					{
						int cantidadColor = 0;
						System.out.println("***************************COLOR "+ co.getId());
						for (TalleOC ta : co.getTalles()) 
						{
							int cantidadAgenda = 0;
							try
							{
								//idDocumento-idArticulo
								cantidadAgenda = unidadesAgendadas.get(d.getNumeroDocumento()+"-"+a.getIdArticulo()+co.getId()+ta.getTalle());
								
								ta.setSolicitada(ta.getSolicitada()-cantidadAgenda);
							}
							catch (Exception e) 
							{
								
							}
							cantidadColor = ta.getSolicitada()-ta.getEntregada();
							
							System.out.println("****************************************** TALLE "+ta.getTalle() + " SOL: "+ta.getSolicitada() +" ENT: " + ta.getEntregada());
						}
						if(cantidadColor<1)
						{
							co.setMostrar(false);
						}
						else
						{
							co.setMostrar(true);
						}
					}
					
			}
		}
        
        
        return ordenes;
     
	}
	
	
	
	
	
	
	
	
	
	public static List<String> darNroOrdenesDeCompra(int idProveedor)
	{

		List<String> retorno =new ArrayList<>();
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://179.27.145.162:2833; databaseName=VsHushPuppies;";
		try 
		{
			 // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
            Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery ("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select distinct OC.NumeroDoc from dbo.encuentra_orden_compra OC where OC.Numero ="+idProveedor+" and ESTADO!='Terminado'");
	  		while (rs.next())
	  		{
	  			retorno.add(rs.getString(1));
	  		}
	  		closeFullConnection(con, rs, s);
		}
	    catch (Exception e) 
		{
          e.printStackTrace();
		}
        return retorno;
     
	}
	
	
	
	
	
	public static List <DataLineaRepo> darListaArtiRepo(String consulta) 
	{	
		List <DataLineaRepo> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
        String connectionUrl = 
           "jdbc:sqlserver://10.108.0.1:1433;" +
           "databaseName=VsStadium;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            
             Statement s = con.createStatement();
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {
	  			 
	  			 DataLineaRepo d = new DataLineaRepo();
	  			 
	  			 
	  			 d.setDescripcion(rs.getString(1));
	  			 d.setDescDeposito(rs.getString(5));
	  			 d.setEntregada(rs.getInt(4));
	  			 d.setIdArticulo(rs.getString(2));
	  			 d.setIdDepDestino(rs.getInt(6));
	  			 d.setIdDepOrigen(rs.getInt(8));
	  			 d.setPreparada(rs.getInt(7));
	  			 d.setSolicitada(rs.getInt(3));
	  			 d.setSotck(rs.getInt(10));
	  			  
	  			 
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
	
	
	public static boolean persistir(String consulta) throws Exception 
	{
		boolean pude = false;
		
		 String connectionUrl = 
	           "jdbc:sqlserver://10.108.0.1:1433;" +
	           "databaseName=VsStadium;";
	        // Declare the JDBC objects.
	        try {
	        	
	            // Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			PreparedStatement pstmt = con.prepareStatement(consulta);
			pstmt.executeUpdate();
			pstmt.close();
			//coment
			pude = true;
			con.close();

		} catch (Exception e) {
			pude = false;
			e.printStackTrace();
			return pude;
		}

		return pude;
	}
	
	
	
	
	public static boolean persistirECM(String consulta) throws Exception 
	{
		boolean pude = false;
		
		 String connectionUrl = 
	           "jdbc:sqlserver://10.108.0.1:1433;" +
	           "databaseName=EcommerceMaps;";
	        // Declare the JDBC objects.
	        try {
	        	
	            // Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			PreparedStatement pstmt = con.prepareStatement(consulta);
			pstmt.executeUpdate();
			pstmt.close();
			//coment
			pude = true;
			con.close();

		} catch (Exception e) {
			pude = false;
			e.printStackTrace();
			return pude;
		}

		return pude;
	}
	
	
	
	public static boolean persistirSALESO(String consulta) throws Exception 
	{
		boolean pude = false;
		
		 String connectionUrl = 
	           "jdbc:sqlserver://10.108.0.1:1433;" +
	           "databaseName=ClarksSalesO;";
	        // Declare the JDBC objects.
	        try {
	        	
	            // Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			PreparedStatement pstmt = con.prepareStatement(consulta);
			pstmt.executeUpdate();
			pstmt.close();
			//coment
			pude = true;
			con.close();

		} catch (Exception e) {
			pude = false;
			e.printStackTrace();
			return pude;
		}

		return pude;
	}
	
	
	
	

	public static ArrayList<DataArticulo> EncuentraSincroArticulos(String consulta) 
	{
		ArrayList<DataArticulo> retorno = new ArrayList<>();
		
		// Create a variable for the connection string.
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;databaseName=STADIUM_DEV2;";
					    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
					
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
		    Statement s = con.createStatement();
		  	ResultSet rs = s.executeQuery (consulta);
		  	while (rs.next())
		  	{
		  		DataArticulo a = new DataArticulo();
		  		a.setId(rs.getString(1));
		  		a.setDescripcion(rs.getString(2));
		  		a.setBarra(rs.getString(3));
		  		retorno.add(a);
		  			
		  	}
		  	closeFullConnection(con, rs, s);
		}
	    // Handle any errors that may have occurred.
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	    }
	 
	  return retorno;
	  
	}


	public static List<DataListaTRE> darTREs(String consulta, List<Integer> documentos, int suc, List<Integer> documentosInternos) 
	{
		List<DataListaTRE> retorno = new ArrayList<>();
		Hashtable<String, DataIDIDDescripcion> articulos = new Hashtable<>();
		 String connectionUrl = 
		       "jdbc:sqlserver://10.108.0.1:1433;" +
		       "databaseName=VsStadium;";
		    // Declare the JDBC objects.
		
		int position = 0;
		for (Integer entero : documentos) 
		{
			
			    try 
			    {
			    	// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta+" "+documentos.get(position));
			  		System.out.println(consulta);
			  		while (rs.next())
			  		{
			  			String art = rs.getString(3);
			  			int numero = rs.getInt(4);
			  			
			  			numero = numero*-1;
			  			
			  			if(articulos.get(art)==null)// no est? en el hash
			  			{
			  				articulos.put(art,new DataIDIDDescripcion(numero,documentosInternos.get(position), art));
			  			}
			  			else
			  			{
			  				DataIDIDDescripcion atroden = articulos.get(art);
			  				int suma = atroden.getId()+numero;
			  				
			  				articulos.put(art,new DataIDIDDescripcion(suma,documentosInternos.get(position), art));
			  				
			  			}
			  			
			  			
			  		}
			  		closeFullConnection(con, rs, s);
			  	 }
			    
			    
			    // Handle any errors that may have occurred.
			    catch (Exception e) 
			    {
			    	e.printStackTrace();
			    }
			    position++;
		}
			    
			    /***********************************************************************
			     * BUSCO LAS TRANSFERENCIAS DE SALIDA CON DESTINO NN
			     * ****************************************************************************/
			    Enumeration e = articulos.elements();
			    while (e.hasMoreElements()) 
			    {
			    	DataIDIDDescripcion data = (DataIDIDDescripcion) e.nextElement();
			    	
			    	String cons = "SELECT  TOP ("+data.getId()+") D.IdDocumento, D.IdTransaccion, D.IdTipoDocumento, D.Fecha,D.NumeroDoc,D.IdDeposito, D.IdDepDestino, M.IdArticulo, M.Cantidad "+
								"  FROM [VsStadium].[dbo].[Documento]D , MovStock M "+
								"  WHERE M.IdDocumento = D.IdDocumento AND "+
								"  IdTipoDocumento = 'TRS' "+
								"  AND IdDepDestino = "+suc+
								"  AND M.IdArticulo = '"+data.getDescripcion()+"' "+
								"  ORDER BY Fecha DESC ";
	
			    	
			    	System.out.println(cons);
			    	
			    	
			    	 try 
					    {
					    	// Establish the connection.
					        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
					        Statement s = con.createStatement();
					  		ResultSet rs = s.executeQuery (cons);
					  		int cant = 0;
					  		String artAnt="";
					  		boolean cambio = false;
					  		while (rs.next())
					  		{
					  			int cantidadLinea = rs.getInt(9)*-1;
					  			if(!artAnt.equals(rs.getString(8)))
					  			{
					  				cant = 0;
					  				cant +=cantidadLinea;
					  			}
					  			
					  			artAnt = (rs.getString(8));
					  			
					  			if(cant<=data.getId())
					  			{
					  				DataListaTRE tre = new DataListaTRE();
						  			tre.setArticulo(rs.getString(8));
						  			tre.setCantidad(cantidadLinea);
						  			tre.setDepDestino(rs.getInt(7));
						  			tre.setDepOrigen(rs.getInt(6));
						  			tre.setIdTRE(rs.getInt(5));
						  			tre.setIdDoc(data.getIid());
						  			
						  			String fechaLarga = rs.getString(4);
						  			String mes = fechaLarga.substring(5,7);
						  			String anio = fechaLarga.substring(0, 4);
						  			String dia = fechaLarga.substring(8, 10);;
						  			
						  			String fecha = dia + "-" + mes +"-" + anio;
						  			tre.setFecha(fecha);
						  			retorno.add(tre);
						  			
					  			}
	
	
					  			
					  		}
					  		closeFullConnection(con, rs, s);
					  	 }
			    	 	
					    
					    // Handle any errors that may have occurred.
					    catch (Exception ex) 
					    {
					    	ex.printStackTrace();
					    }
			    	
			    }
			    
		
		
		return retorno;
	}
	
	
	
	public static List<DataDescDescripcion> darListaDataDesDescripcion(String consulta) 
	{
		List<DataDescDescripcion> lista = new ArrayList<>();
		String connectionUrl = 
			       "jdbc:sqlserver://10.108.0.1:1433;" +
			       "databaseName=VsStadium;";
			    // Declare the JDBC objects.
		
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
			String artAnt = "";
			String artAct = "";
			
			while (rs.next())
			{
				artAct = rs.getString(1);
				if(!artAct.equals(artAnt))// si son diferentes agrego el primero, todos los demas no me importan
				{
					DataDescDescripcion d = new DataDescDescripcion();
					//id es suma venta
					//descripcion es articulo
					//fecha es Stock
					Double venta = rs.getDouble(2);
					if(venta!=0.0)
					{
						Double stock = rs.getDouble(5);
						d.setId(String.valueOf((int)Math.floor(venta)));
						d.setDescripcion(rs.getString(1));
						d.setFecha(String.valueOf((int)Math.floor(stock)));
						
						String fecha =  "N/D";
						if(rs.getString(7)!=null)
						{
							String fechaLarga = rs.getString(7);
				  			String mes = fechaLarga.substring(5,7);
				  			String anio = fechaLarga.substring(0, 4);
				  			String dia = fechaLarga.substring(8, 10);;
				  			
				  			fecha = dia + "-" + mes +"-" + anio;
							
						}
						
						
			  			
						
						d.setFechaRec(fecha);
						String cant = rs.getString(8);
						int cantDias = 0;
						try
						{
							cantDias = Integer.parseInt(cant);
						}
						catch (Exception e)
						{
							cantDias = 0;
						}
						
						d.setCantDias(cantDias);
					
						lista.add(d);
					}
				}
				artAnt = artAct;
				
				
				
				
			}
			closeFullConnection(con, rs, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	
	public static List<DataDescDescripcion> darListaFunCumplen(String consulta) 
	{
		List<DataDescDescripcion> lista = new ArrayList<>();
		String connectionUrl = 
			       "jdbc:sqlserver://10.108.0.1:1433;" +
			       "databaseName=metiri;";
			    // Declare the JDBC objects.
		
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
			
			
			while (rs.next())
			{
				
				DataDescDescripcion d = new DataDescDescripcion();
				d.setId(rs.getString(1)+" "+rs.getString(2));
				d.setDescripcion(rs.getString(3));
				
				
				lista.add(d);
				
				
				
				
				
			}
			closeFullConnection(con, rs, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	
	public static List<DataIRotaFT> darListaFueraTemporada(String consulta) 
	{
		List<DataDescDescripcion> lista = new ArrayList<>();
		String connectionUrl = 
			       "jdbc:sqlserver://10.108.0.1:1433;" +
			       "databaseName=VsStadium;";
			    // Declare the JDBC objects.
		List<DataIRotaFT> retorno = new ArrayList<>();
		
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
	  		DataIRotaFT vivo = null;
	  		
			String colorAnt = "";
			String colorAct = "";
			boolean pri = true;
			List<DataIDDescripcion> tallesStocks = new ArrayList<>();
			int totalStock = 0;
			
			while (rs.next())
			{
				colorAct = rs.getString(4);
				if (pri)//entra solo por primera vez
				{
					//creo al vivo
					vivo = new DataIRotaFT();
					
					//Agrego un talle a la lista
					Double x = rs.getDouble(6);
					int stock = (int) Math.floor(x);
					DataIDDescripcion d = new DataIDDescripcion(stock,rs.getString(5));
					tallesStocks.add(d);
					//sumo la cantidad de stock al total
					totalStock+=rs.getInt(6);
					vivo.setArticuloFULL(rs.getString(1));
					//seteo lo que no varia en el vivo
					vivo.setArticulo(rs.getString(3));
					DataIDDescripcion color = new DataIDDescripcion(rs.getInt(4),rs.getString(7));
					vivo.setColor(color);
					vivo.setDiferenciaRecep(rs.getInt(11));
					
					//formateo la fecha de recepcion
					String fechaRecibido = rs.getString(9);
					
					String dia = fechaRecibido.substring(8, 10);
					String mes = fechaRecibido.substring(5, 7);
					String anio = fechaRecibido.substring(0, 4);
					
					String fecha = dia + "-" + mes + "-" + anio;
					
					vivo.setFechaRecepcion(fecha);
					vivo.setPrecio(rs.getDouble(10));
					vivo.setTotalVenta(rs.getInt(2));
					
					pri = false;
					
				}
				else
				{
					if(colorAct.equals(colorAnt))//no hubo cambio de color sigo agregando talles
					{
						//Agrego un talle a la lista
						Double x = rs.getDouble(6);
						int stock = (int) Math.floor(x);
						DataIDDescripcion d = new DataIDDescripcion(stock,rs.getString(5));
						tallesStocks.add(d);
						//sumo la cantidad de stock al total
						totalStock+=rs.getInt(6);
					}
					else
					{
						
						//seteo los talles a vivo
						vivo.setTallesStock(tallesStocks);
						vivo.setTotalStock(totalStock);
						//agrego vivo a la lista retorno
						retorno.add(vivo);
						
						//reseteo el stock total
						totalStock = 0;
						
						//mato a vivo
						vivo = new DataIRotaFT();
						
						//mato los talles
						tallesStocks = new ArrayList<>();
						vivo.setTotalStock(totalStock);
						//hago todo lo de pri porque es el primero de nuevo
						
						//Agrego un talle a la lista
						Double x = rs.getDouble(6);
						int stock = (int) Math.floor(x);
						DataIDDescripcion d = new DataIDDescripcion(stock,rs.getString(5));
						tallesStocks.add(d);
						//sumo la cantidad de stock al total
						totalStock+=rs.getInt(6);
						
						//seteo lo que no varia en el vivo
						vivo.setArticulo(rs.getString(3));
						DataIDDescripcion color = new DataIDDescripcion(rs.getInt(4),rs.getString(7));
						vivo.setColor(color);
						vivo.setDiferenciaRecep(rs.getInt(11));
						vivo.setArticuloFULL(rs.getString(1));
						//formateo la fecha de recepcion
						
						String fecha = null;
						try
						{
						String fechaRecibido = rs.getString(9);
						
						String dia = fechaRecibido.substring(8, 10);
						String mes = fechaRecibido.substring(5, 7);
						String anio = fechaRecibido.substring(0, 4);
						
						fecha = dia + "-" + mes + "-" + anio;
							
						}
						catch (Exception e)
						{
							fecha = "no disponible";
						}
						vivo.setFechaRecepcion(fecha);
						vivo.setPrecio(rs.getDouble(10));
						vivo.setTotalVenta(rs.getInt(2));
						
						
					}
				}
				colorAnt = colorAct;
				
				
				
			}
			closeFullConnection(con, rs, s);
			//agrego al ultimo que no se comparo con nada
			
			//seteo los talles a vivo
			vivo.setTallesStock(tallesStocks);
			vivo.setTotalStock(totalStock);
			
			//agrego vivo a la lista retorno
			retorno.add(vivo);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return retorno;
	}

	public static int darDiasDiff(String consulta) {
		
		String connectionUrl = 
			       "jdbc:sqlserver://10.108.0.1:1433;" +
			       "databaseName=VsStadium;";
			    // Declare the JDBC objects.
		int retorno = 0;
		
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
			
			while (rs.next())
			{
				retorno = rs.getInt(1);
							
			}
			closeFullConnection(con, rs, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}

		return retorno;
	}

	public static StringBuilder darListaAB(String consulta) 
	{
		StringBuilder retorno = new StringBuilder();
		String connectionUrl = 
			       "jdbc:sqlserver://10.108.0.1:1433;" +
			       "databaseName=VsStadium;";
			    // Declare the JDBC objects.
		
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	        
			ResultSet rs = s.executeQuery(consulta);
			
			int largoArticulo = 17;
			int largoBarras = 25;
			
			while (rs.next())
			{								
				String barra = rs.getString(1);
				String articulo = rs.getString(2);
				articulo = articulo.replace(" ", "");
				int diferenciaArticulo = largoArticulo - articulo.length();
				int diferenciaBarras = largoBarras - barra.length();				
				if(diferenciaBarras!=0)
				{
					for (int i = 0; i < diferenciaBarras; i++) 
					{
						barra +=" ";
					}
				}				
				if(diferenciaArticulo!=0)
				{
					for (int i = 0; i < diferenciaArticulo; i++) 
					{
						articulo +=" ";
					}
				}				
				retorno.append(barra+","+articulo);
				retorno.append("\r"+"\n");
				barra = null;
				articulo = null;				
			}
			closeFullConnection(con, rs, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
		return retorno;
	}
	public static StringBuilder darListaAD(String consulta) 
	{
		StringBuilder retorno = new StringBuilder();
		String connectionUrl = 
			       "jdbc:sqlserver://10.108.0.1:1433;" +
			       "databaseName=VsStadium;";
			    // Declare the JDBC objects.		
		try 
		{			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();	        
			ResultSet rs = s.executeQuery(consulta);			
			int largoArticulo = 17;
			int largodescripcion = 30;			
			while (rs.next())
			{				
				
				String descripcion = rs.getString(2);
				String articulo = rs.getString(1);
				
				if(descripcion.length()>30)
				{
					descripcion = descripcion.substring(0,30);
				}
					
				
				int diferenciaArticulo = largoArticulo - articulo.length();
				int diferenciaDescripcion = largodescripcion - descripcion.length();
				
				if(diferenciaDescripcion!=0)
				{
					for (int i = 0; i < diferenciaDescripcion; i++) 
					{
						descripcion +=" ";
					}
				}
				
				if(diferenciaArticulo!=0)
				{
					for (int i = 0; i < diferenciaArticulo; i++) 
					{
						articulo +=" ";
					}
				}
				

				retorno.append(articulo+","+descripcion);
				retorno.append("\r"+"\n");
				articulo=null;
				descripcion=null;
				
				
			}
			closeFullConnection(con, rs, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return retorno;
	}

	

	

	public static List<DocumentoEnvio> darEnvios(String consulta) {
		List<DocumentoEnvio> lista=new ArrayList<>();
		String connectionUrl = 
			       "jdbc:sqlserver://10.108.0.1:1433;" +
			       "databaseName=VsStadium;";
			    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			String consultaEsta = "select DocOrigen from documentos where DocOrigen = ";
			
			while (rs.next())
			{
				DocumentoEnvio v=new DocumentoEnvio();
				v.setNumeroDoc(rs.getInt(10));
				v.setCantidad(rs.getInt(2));
				DataIDDescripcion razon=new DataIDDescripcion();
				razon.setId(rs.getInt(11));
				razon.setDescripcion(rs.getString(17));
				v.setRazon(razon);
				DataIDDescripcion depositoOrigen=new DataIDDescripcion();
				depositoOrigen.setId(rs.getInt(13));
				depositoOrigen.setDescripcion(rs.getString(22));
				v.setDepositoO(depositoOrigen);
				DataIDDescripcion depositoDestino=new DataIDDescripcion();
				depositoDestino.setId(rs.getInt(7));
				depositoDestino.setDescripcion(rs.getString(21));
				v.setDepositoD(depositoDestino);
				DataIDDescripcion usuario=new DataIDDescripcion();
				usuario.setId(rs.getInt(18));
				usuario.setDescripcion(rs.getString(19));
				v.setUsuario(usuario);
				v.setArticulos(getArticulos(rs.getInt(1)));
				v.setIncluir(false);
				v.setCustom(true);
				lista.add(v);
				
			}
			closeFullConnection(con, rs, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return lista;
	}
	
	public static List<DataArticulo> getArticulos(int idDocumento){
		List<DataArticulo>lista=new ArrayList<>();
		String consulta="Select IdMovStock, IdDocumento, IdArticulo, Cantidad=1*Cantidad, " +
				"IdMoneda,Monto, MontoImp, PorcImp1, PorcImp2, IdEstado, IdLinea, Gasto " +
				"From MovStock Where IdDocumento = "+idDocumento+" Order By IdMovStock";
		
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
		"databaseName=VsStadium;";
			    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
			
			while (rs.next())
			{
				DataArticulo art=new DataArticulo();
				art.setId(rs.getString(3));
				art.setCantidad(rs.getInt(4));
				lista.add(art);
			}
			closeFullConnection(con, rs, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return lista;
		
	}

	public static List<DataDescDescripcion> darProveedores(String consulta) {
		List<DataDescDescripcion>proveedores=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://179.27.145.162:2833; databaseName=VsHushPuppies;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					 // Establish the connection.
		            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
		            
		            //
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataDescDescripcion prov=new DataDescDescripcion();
						prov.setId(rs.getString(1));
						prov.setDescripcion(rs.getString(2));
						proveedores.add(prov);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return proveedores;
	}

	public static List<ArticuloPedido> darDetalleOC(String consulta) {
		List<ArticuloPedido>detalle=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						ArticuloPedido ped=new ArticuloPedido();
						ped.setArticulo(rs.getString(1));
						ped.setCantidadPedida(rs.getInt(2));
						ped.setCantidadEntregada(rs.getInt(3));
						detalle.add(ped);
					}
					closeFullConnection(con, rs, s);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
				return detalle;	
		}

	public static List<DataTalleCantidad> darDetalleArticuloCantidad(String consulta) 
	{
		List<DataTalleCantidad>detalle=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataTalleCantidad ped=new DataTalleCantidad();
						ped.setTalle(rs.getString(1));
						ped.setCantidad(rs.getInt(2));
						ped.setMax(rs.getInt(2));
						detalle.add(ped);
					}
					closeFullConnection(con, rs, s);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
				return detalle;	
	}
	
	
	
	public static List<DataDocVisual> darListaDocsVisual(String consulta) 
	{
		List<DataDocVisual> lista = new ArrayList<>();
		
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
		"databaseName=VsStadium;";
			    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
			
	  		while (rs.next()) 
			{
	  			DataDocVisual retorno = new DataDocVisual();
	  			retorno.setIdInterno(rs.getInt(1));
	  			retorno.setIdVisual(rs.getInt(2));
	  			retorno.setEstado(rs.getString(3));
	  			DataIDDescripcion  origen =  new DataIDDescripcion(rs.getInt(7),rs.getString(8));
	  			retorno.setDepoOrigen(origen);
	  			
	  			DataIDDescripcion  destino =  new DataIDDescripcion(rs.getInt(5),rs.getString(6));
	  			retorno.setDepoDestino(destino);
	  			try
	  			{
	  				retorno.setUsuario(rs.getString(9).substring(0, 12));
	  			}
	  			catch (Exception e)
	  			{
	  				retorno.setUsuario(rs.getString(9));
	  			}
	  			
	  			
				
				lista.add(retorno);

			}
	  		closeFullConnection(con, rs, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}
	public static List<DataArtMovS> darListaArticulosDocs(String consulta) 
	{
		List<DataArtMovS> lista = null;
		
		Hashtable<String, DataArtMovS> articulosH = new Hashtable<>();
		
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
		"databaseName=VsStadium;";
			    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
			
	  		while (rs.next()) 
			{
	  			if(articulosH.get(rs.getString(3))==null)
	  			{
	  				DataArtMovS in = new DataArtMovS();
	  				in.setArticulo(rs.getString(3));
	  				in.setCantidad(rs.getInt(4));
	  				in.setIdMS(rs.getInt(1));
	  				in.setIdDoc(rs.getInt(2));
	  				List<String>barras = new ArrayList<>();
	  				barras.add(rs.getString(5));
	  				in.setBarras(barras);
	  				articulosH.put(in.getArticulo(), in);
	  				
	  			}
	  			else
	  			{
	  				DataArtMovS in = articulosH.get(rs.getString(3));
	  				in.getBarras().add(rs.getString(5));
	  				articulosH.put(in.getArticulo(), in);
	  			}
	  			
			}
	  		closeFullConnection(con, rs, s);
			
			lista = new ArrayList<>(articulosH.values());
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}
	public static long darNumeroProveedor(long idProveedor) {
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				String consulta="";
				int numeroProveedor=0;
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			        consulta="select Numero from PersonaEmpresa where IdPersonaEmpresa="+idProveedor;
			    	System.out.println(consulta);
			  		ResultSet rs = s.executeQuery (consulta);
					
					
			  		while (rs.next()) 
					{
			  			numeroProveedor=rs.getInt(1);
			  			
					}
					closeFullConnection(con, rs, s);
					

					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				return numeroProveedor;
	}
	public static int darNumeroImportacion(long idProveedor, int numeroDoc) {
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
					int numeroImp=0;
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			        String consulta="select DOC2.NumeroDoc " +
							"from Documento DOC, OrdenCompra OC,ImpOrdenCompra ioc,Importacion imp,DOCUMENTO DOC2 " +
							"where DOC.IdDocumento=OC.IdDocumento AND DOC.NumeroDoc="+numeroDoc+" AND OC.IdProveedor="+idProveedor+" and " +
							"ioc.IdDocOrdCompra=oc.IdDocumento and imp.IdDocumento=ioc.IdDocImportacion AND IMP.IdDocumento=DOC2.IdDocumento";
				
			    	System.out.println(consulta);
			  		ResultSet rs = s.executeQuery (consulta);
					
					
			  		while (rs.next()) 
					{
			  			numeroImp=rs.getInt(1);
			  			
					}
					closeFullConnection(con, rs, s);
					

					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				return numeroImp;
	}
	
	public static List<DataEtiquetaComponentes> darEtiquetasClarks(String consulta) 
	{
		List<DataEtiquetaComponentes> lista=new ArrayList<>();
		String connectionUrl = 
			       "jdbc:sqlserver://10.108.0.1:1433;" +
			       "databaseName=VsStadium;";
			    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
			String artAnt = "";
	  		DataEtiquetaComponentes etAnt = new DataEtiquetaComponentes();
	  		List <DataIDDescDescripcion> componentes = new ArrayList<>();
	  		
	  		boolean pri = true;
	  		int stockF = 0;
			while (rs.next())
			{
				stockF = rs.getInt(5);
				if(pri)
				{
					pri = false;
					etAnt.setArticulo(rs.getString(1).substring(0,9));
					etAnt.setOrigen(rs.getString(4));
					DataIDDescDescripcion dat = new DataIDDescDescripcion();
					dat.setDesc(rs.getString(2));
					dat.setDescripcion(rs.getString(3));
					etAnt.setStock(rs.getInt(5));
					componentes.add(dat);
				}
				
				else
				{
					String art = rs.getString(1);
					if(art.equals(artAnt))
					{
						DataIDDescDescripcion dat = new DataIDDescDescripcion();
						dat.setDesc(rs.getString(2));
						dat.setDescripcion(rs.getString(3));
						
						componentes.add(dat);
					}
					else
					{
						etAnt.setComponentes(componentes);
						int stock = rs.getInt(5);
						//agrego una por cada unidad en stock
						for (int i = 0; i < etAnt.getStock(); i++) 
						{
							lista.add(etAnt);
						}
						
						etAnt = new DataEtiquetaComponentes();
						componentes = new ArrayList<>();
						
						
						etAnt.setArticulo(rs.getString(1).substring(0,9));
						etAnt.setOrigen(rs.getString(4));
						DataIDDescDescripcion dat = new DataIDDescDescripcion();
						dat.setDesc(rs.getString(2));
						dat.setDescripcion(rs.getString(3));
						etAnt.setStock(rs.getInt(5));
						componentes.add(dat);
						
						
					}
				}
				artAnt = rs.getString(1);
			}
			
			etAnt.setComponentes(componentes);
			
			//agrego una por cada unidad en stock
			for (int i = 0; i < stockF; i++) 
			{
				lista.add(etAnt);
			}
			
			for (DataEtiquetaComponentes d : lista) 
			{
				String componentesS = "";
				int cantidad = 0;
				for (DataIDDescDescripcion de : d.getComponentes()) 
				{
					componentesS +=  de.getDesc() + " "+ de.getDescripcion();
					cantidad++;
				}
				if(cantidad<4)
				{
					int dif = 4-cantidad;
					
					for (int i = 0; i < dif; i++) 
					{
						DataIDDescDescripcion da = new DataIDDescDescripcion();
						da.setDesc("");
						da.setDescripcion("");
						d.getComponentes().add(da);
					}
				}
				
				System.out.println(d.getArticulo()+" "+  componentesS +" "+d.getOrigen());
			}
			closeFullConnection(con, rs, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return lista;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void closeFullConnection(Connection con,ResultSet rs,Statement s) throws SQLException{
		rs.close();
		s.close();
		con.close();
	}
	public static List<DataIDDescripcion> darListaIdDescripcion(String consulta) 
	{
		List<DataIDDescripcion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataIDDescripcion da=new DataIDDescripcion();
						da.setId(rs.getInt(1));
						da.setDescripcion(rs.getString(2));
						lista.add(da);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	
	public static List<DataIDDescripcion> darListaIdDescripcionIB(String consulta) 
	{
		List<DataIDDescripcion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataIDDescripcion da=new DataIDDescripcion();
						da.setId(rs.getInt(1));
						da.setDescripcion(rs.getString(2));
						da.setIdB(rs.getInt(3));
						lista.add(da);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	
	public static List<DataIDDescripcion> darListaIdDescripcionWEB(String consulta) 
	{
		List<DataIDDescripcion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.4:1433;" +
				"databaseName=VsStadium71;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataIDDescripcion da=new DataIDDescripcion();
						try{
							da.setId(rs.getInt(1));							
						}catch (Exception e) {
							da.setIdLong(rs.getLong(5));
						}
						da.setDescripcion(rs.getString(2));
						
						try{
							da.setIdD(rs.getDouble(3));
							da.setDescripcionB(rs.getString(4));
						}
						catch (Exception e) {
							
						}
						
						lista.add(da);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	
	public static List<DataIDDescripcion> darListaIdDescripcionSAP(String consulta) 
	{
		List<DataIDDescripcion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;" +
				"databaseName=STADIUM_DEV2;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataIDDescripcion da=new DataIDDescripcion();
						da.setId(rs.getInt(1));
						da.setDescripcion(rs.getString(2));
						lista.add(da);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	
	
	
	
	
	
	
	
	
	
	public static List<DataIDDescripcion> darListaTags (String in, Hashtable<Integer, List<List<DataIDDescripcion>>> tagsXclase, int idClase, int pos)
	{
		
		
		
		List<List<DataIDDescripcion>> tagsL = tagsXclase.get(idClase);
		List<DataIDDescripcion> tagsH = tagsL.get(pos);
		
		List<DataIDDescripcion> tags = new ArrayList<>();
		for (DataIDDescripcion t : tagsH) 
		{
			try 
			{
				tags.add(t.clone());
			} 
			catch (CloneNotSupportedException e) 
			{
				
			}
		}
		
		
		
		
		
		
		try
		{
			String [] arreglo = in.split(",");
			if(arreglo!=null)
			{
				for (DataIDDescripcion t : tags) 
				{
					for (int i = 0; i < arreglo.length; i++) 
					{
						if(t.getDescripcion().equals(arreglo[i]))
						{
							t.setIdB(1);
						}
						
					}
				}
				
			}
		}
		catch (Exception e) 
		{
			
		}
		
		
		
		return tags;
		
	}
	
	
	public static List<ArticuloAnysys> darListaArticulosTag(String consulta, Hashtable<Integer, List<List<DataIDDescripcion>>> tagsXclase) 
	{
		List<ArticuloAnysys>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;" +
				"databaseName=anysys;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						ArticuloAnysys articulo = new ArticuloAnysys();
						String t1 = rs.getString(1);
						String t2 = rs.getString(2);
						String t3 = rs.getString(3);
						String t4 = rs.getString(4);
						String t5 = rs.getString(5);
						
						int idClase = rs.getInt(10);
						List<DataIDDescripcion> t1L = darListaTags(t1,tagsXclase,idClase,0);
						List<DataIDDescripcion> t2L = darListaTags(t2,tagsXclase,idClase,1);
						List<DataIDDescripcion> t3L = darListaTags(t3,tagsXclase,idClase,2);
						List<DataIDDescripcion> t4L = darListaTags(t4,tagsXclase,idClase,3);
						List<DataIDDescripcion> t5L = darListaTags(t5,tagsXclase,idClase,4);
						
						
						
						articulo.setBase(rs.getString(9));
						articulo.setBaseColor(rs.getString(6));
						articulo.setDescripcionCorta(rs.getString(7));
						articulo.setStock(rs.getInt(8));
						articulo.setTags1(t1L);
						articulo.setTags2(t2L);
						articulo.setTags3(t3L);
						articulo.setTags4(t4L);
						articulo.setTags5(t5L);
						
						
						lista.add(articulo);
						
						
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Proveedor> darListaProveedores(String consulta) 
	{
		List<Proveedor>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						Proveedor p = new Proveedor();
						p.setId(rs.getInt(2));
						p.setNombre(rs.getString(3));
						p.setRut(rs.getString(4));
						p.setDireccion(rs.getString(5));
						p.setTelefono(rs.getString(6));
						p.setMail(rs.getString(7));
						lista.add(p);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	public static List<Articulo> darListaArticulos(String consulta) 
	{
		List<Articulo>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						Articulo a = new Articulo();
						a.setArticulo(rs.getString(1));
						a.setProveedor(rs.getInt(2));
						a.setCosto(rs.getDouble(3));
						a.setProvOrigen(rs.getInt(4));
						a.setIdMarca(rs.getInt(5));
						a.setIdSeccion(rs.getInt(6));
						a.setIdClase(rs.getInt(7));
						a.setDescripcion(rs.getString(8));
						
						lista.add(a);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	
	public static List<DataSalesO> darListaSalesO(String fechaI, String fechaF, int idTienda) 
	{
		List<DataSalesO>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				String consulta = "select  AB.NroBarra, "+ 
								"	Cantidad = case D.IdTipoDocumento when 'DCO' then  VL.Cantidad*-1 when 'DCR' then  VL.Cantidad*-1 else VL.Cantidad end, "+
								"	case D.IdTipoDocumento when 'DCO' then  VL.PrecioLista*-1 when 'DCR' then  VL.PrecioLista*-1 else VL.PrecioLista end, "+
								"	VL.Precio"+
								"	from  VentaLinea VL inner join  Documento D on D.IdDocumento = VL.IdDocumento inner join (Select IdArticulo, MAX(NroBarra)NroBarra from ArtBarra where IdArticulo like '061.%' and LEN(NroBarra)=13  group by IdArticulo) AB on AB.IdArticulo = VL.IdArticulo "+
								"	where D.IdDeposito = "+idTienda+" AND D.IdTipoDocumento in ('VCO', 'DCO', 'VCR', 'DCR') and VL.IdArticulo like '061.%' and LEN(AB.NroBarra)=13 "+
								"	AND D.Fecha between '"+fechaI+"' AND  '"+fechaF+"'";
				System.out.println(consulta);
		
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataSalesO d = new DataSalesO();
						
						d.setBarras(rs.getString(1));
						d.setCantidad(rs.getInt(2));
						d.setPrecioLista(rs.getDouble(3));
						d.setPrecioVenta(rs.getDouble(4));
						
						lista.add(d);
						
						
						
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	public static List<DataClarksParametroTienda> darListaTiendasSalesO() 
	{
		List<DataClarksParametroTienda>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=ClarksSalesO;";
					    // Declare the JDBC objects.
				String consulta = "SELECT Customer,Store,RecipentID,SenderID,IdIntStore,LastMSG,LastHDR FROM Parametros";
				
		
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataClarksParametroTienda d = new DataClarksParametroTienda();
						d.setCustomer(rs.getString(1).replaceAll(" ", ""));
						d.setStore(rs.getString(2).replaceAll(" ", ""));
						d.setRecipentID(rs.getString(3).replaceAll(" ", ""));
						d.setSenderID(rs.getString(4).replaceAll(" ", ""));
						d.setIdTienda(rs.getInt(5));
						d.setLastMSG(rs.getInt(6));
						d.setLastHDR(rs.getInt(7));
						
						
						
						lista.add(d);
						
						
						
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	public static List<DataIDDescripcion> darClientesWeb() {
		List<DataIDDescripcion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				String consulta = "Select Distinct Pe.IdPersonaEmpresa, CONCAT(Pe.Numero,Pe.NumeroDig)From GrpPerEmp Grp, PersonaEmpresa Pe Where Grp.IdPersonaEmpresa = Pe.IdPersonaEmpresa And Grp.IdGrupoTipo = 2 And Grp.IdListaEmpresa = 1 And Pe.Numero > 0 And Activo <> 0";
				
		
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataIDDescripcion d = new DataIDDescripcion(1,rs.getString(2));
						
						
						
						lista.add(d);
						
						
						
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	public static List<DataDescDescripcion> darlistaDescrpcionDescripcion(String consulta,int idEmpresa) 
	{
		List<DataDescDescripcion>lista=new ArrayList<>();
		
				
		
				try 
				{
					
					// Establish the connection.
					darConexion(idEmpresa);
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					while (rs.next())
					{
						DataDescDescripcion d = new DataDescDescripcion(rs.getString(1),rs.getString(2));
						lista.add(d);
						
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}

	
	
	public static List<ArticuloForus> darArticuloVs(String consulta, int idEmpresa) 
	{
		List<ArticuloForus>lista=new ArrayList<>();
		
				
		
				try 
				{
					
					// Establish the connection.
					darConexion(idEmpresa);
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					while (rs.next())
					{
						ArticuloForus a = new ArticuloForus(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
						lista.add(a);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}


	
	
	

	public static List<DataRowSincAnalizer> darRowsSincAnalizer(String fechaI,String fechaF) 
	{
		
		
		
		
		String consulta = "select t2.Fecha, t2.IdDeposito, t2.MARCA, sum(t2.[{Sin Definir}]) as 'Sin Definir $', sum(t2.[{Sin Definir1}]) as 'Sin Definir Un', SUM(t2.Dama) as 'Dama $', SUM(t2.Dama1) as ' Dama U', 	 SUM(t2.Hombre) as 'Hombre $', SUM(t2.Hombre1) as 'Hombre U', SUM(t2.Ni?o) as 'Ni?o $', SUM(t2.Ni?o1) as 'Ni?o U', SUM(t2.Accesorio) as 'Accesorio $',  SUM(t2.Accesorio1) as 'Accesorio U', t2.depo    from (SELECT * FROM 		( 			Select 	Doc.Fecha, DEP.Descripcion as IdDeposito, AM.Descripcion AS MARCA, ASEC.Descripcion AS SECCION, ASEC.Descripcion+'1' AS SECCION2, SUM(VL.Precio*(Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End))- AVG(Vta.TotDesc) as PLATA, 		SUM(VL.Cantidad * (Case Td.SignoDocumento When 0 Then -1 When 1 Then 1 Else 0 End) * (Case Vta.xDescuento When 0 Then 1 Else 0 End)) as Cantidad, doc.IdDeposito as depo		From 	Venta Vta INNER JOIN VentaLinea VL on vl.IdDocumento = Vta.IdDocumento 			INNER JOIN Articulo AR ON VL.IdArticulo = AR.IdArticulo 			INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca 			INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion 			INNER JOIN Documento Doc on Doc.IdDocumento = Vta.IdDocumento  			INNER JOIN TipoDocumento Td on Td.IdTipoDocumento = Doc.IdTipoDocumento 			INNER JOIN Deposito DEP ON  DEP.IdDeposito = DOC.IdDeposito 		where (Doc.IdListaEmpresa = 1) And  (Doc.IdDeposito Between  0 And 201) And (Doc.Fecha Between '"+fechaI+"' And '"+fechaF+"')  And Doc.IdTipoDocumento not in ('REC','RDC')  GROUP BY Doc.Fecha,DEP.Descripcion, AM.Descripcion, ASEC.Descripcion, doc.IdDeposito) AS TABLA 	PIVOT 	(SUM(TABLA.PLATA) FOR [SECCION] IN ([{Sin Definir}], [Dama], [Hombre], [Ni?o], [Accesorio])) AS P 	PIVOT	(SUM(Cantidad) FOR [SECCION2] IN ([{Sin Definir1}], [Dama1], [Hombre1], [Ni?o1], [Accesorio1])) AS P2 	) as t2 GROUP BY t2.Fecha, t2.IdDeposito, t2.MARCA, t2.depo 	ORDER BY t2.Fecha, t2.IdDeposito, t2.MARCA";
		
		System.out.println(consulta);
		List<DataRowSincAnalizer> lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					while (rs.next())
					{
						int totalU = 0;
						double total=0.0;
						
						DataRowSincAnalizer r = new DataRowSincAnalizer();
						r.setFecha(rs.getString(1));
						r.setDeposito(rs.getString(2));
						r.setMarca(rs.getString(3));
						
						r.setSinDefinir(rs.getDouble(4));
						r.setSinDefinirU(rs.getInt(5));
						
						r.setDama(rs.getDouble(6));
						r.setDamaU(rs.getInt(7));
						
						r.setHombre(rs.getDouble(8));
						r.setHombreU(rs.getInt(9));
						
						r.setNinio(rs.getDouble(10));
						r.setNinioU(rs.getInt(11));
						
						r.setAccesorio(rs.getDouble(12));
						r.setAccesorioU(rs.getInt(13));
						
						r.setTotal(r.getSinDefinir()+r.getDama()+r.getHombre()+r.getNinio()+r.getAccesorio());
						r.setTotalU(r.getSinDefinirU()+r.getDamaU()+r.getHombreU()+r.getNinioU()+r.getAccesorioU());
						
						r.setIdDepo(rs.getInt(14));
						
						String consultaI = "insert into SalesAnalizer (Fecha,Tienda,Marca,SinDefinir,SinDefinirU,Dama,DamaU,Hombre,HombreU,Ninio,NinioU,Accesorio,AccesorioU, Total,TotalU, IdDeposito) "+
								" values ('"+r.getFecha()+"', '"+r.getDeposito()+"','"+r.getMarca()+"', "+r.getSinDefinir()+","+r.getSinDefinirU()+","+r.getDama()+","+r.getDamaU()+","+r.getHombre()+","+r.getHombreU()+","+r.getNinio()+","+r.getNinioU()+","+r.getAccesorio()+","+r.getAccesorioU()+", "+r.getTotal()+","+r.getTotalU()+","+r.getIdDepo()+");"; 
						
						persistirSALESO(consultaI);
						
						consultaI = null;
					
						
						
						
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	
	
	public static List<RepoDimension> darListaDimReposicion(String consulta, String valor, int intentos, int idSinc, int porcentaje, int idEmpresa) 
	{
		Logica Logica = new Logica();
		List<RepoDimension>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						RepoDimension r=new RepoDimension();
						r.setIdDeposito(rs.getInt(3));
						r.setCantidadVendida(rs.getInt(2));
						lista.add(r);
					}
					if(idSinc==0)
					{
						RepoDimension r=new RepoDimension();
						r.setIdDeposito(99);
						r.setCantidadVendida(0);
						lista.add(r);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					//e.printStackTrace();
					if(intentos>3)
					{
						Logica.actualizarLogSincRepo(idSinc, "ERROR: obteniendo los datos de "+valor+" en el intento numero "+intentos+" Detalle: "+e.getMessage(), porcentaje,idEmpresa);
					}
					
				}
			
				return lista;
	}
	
	public static DataIDIDDescripcion darDID(Hashtable<String, Integer> stocksReservados,Hashtable<String, Integer> stocksEnviados, int idDeposito, int stock, String articulo)
	{
		try
		{
			if(articulo.equals("001.00604000128.0")&& idDeposito==20)
			{
				System.out.println("");
			}
			DataIDIDDescripcion d =new DataIDIDDescripcion();
			int stockRes = 0;
			int stockEnv = 0;
			if(stocksReservados!=null)
			{
				if(stocksReservados.get(articulo+"-"+idDeposito)!=null)
				{
					stockRes = stocksReservados.get(articulo+"-"+idDeposito);
				}
			}
			/**********************suma las unidades que se le enviaron***************************/
			if(stocksEnviados!=null)
			{
				if(stocksEnviados.get(articulo+"-"+idDeposito)!=null)
				{
					stockEnv = stocksEnviados.get(articulo+"-"+idDeposito);
				}
			}
			
			
			d.setId(idDeposito);
			d.setIid(stock-stockRes+stockEnv);
		
			return d;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
	
	
	public static List<DataIDIDDescripcion> darIdOrdenVenta(String pedidosIn)
	{
		List<DataIDIDDescripcion> lista = new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.4:1433;" +
				"databaseName=VsStadium71;";
		 //String connectionUrl="jdbc:sqlserver://osonecasap:1433;" +
		//		 "databaseName=VsStadium71;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery ("SELECT DISTINCT DOOV.NumeroDoc, ISNULL(CFE.NumeroDoc,0), WEOV.IdCarrito  FROM  "+
												"	OrdenVenta OV   "+
												"	INNER JOIN WebOrdenVenta WEOV ON WEOV.IdDocumento = OV.IdDocumento "+  
												"	INNER JOIN OrdenVentaLinea OVL ON OVL.IdDocumento =  OV.IdDocumento "+ 
												"	INNER JOIN Documento DOOV ON DOOV.IdDocumento = OV.IdDocumento "+  
												"	LEFT OUTER JOIN VentaAfectaOV VAFO ON VAFO.IdLineaOrdVta = OVL.IdLineaDocumento "+  
												"	LEFT OUTER JOIN VentaLinea VL ON  VL.idLineaDocumento = VAFO.IdLineaVenta "+  
												"	LEFT OUTER JOIN Venta V ON V.IdDocumento = VL.IdDocumento "+  
												"	LEFT OUTER JOIN Documento DOV ON DOV.IdDocumento = V.IdDocumento "+  
												"	LEFT OUTER JOIN VentaCFE CFE on CFE.IdDocumento = V.IdDocumento  "+
												"	WHERE WEOV.IdCarrito in ("+pedidosIn+")");
					
					
					while (rs.next())
					{
						DataIDIDDescripcion retorno = new DataIDIDDescripcion();
						retorno.setId(rs.getInt(1));//orden
						retorno.setIid(rs.getInt(2));//factura
						retorno.setDescripcion(rs.getString(3));
						lista.add(retorno);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
	
	
	
	public static Hashtable<String, StockReposicion> darListaDeposStock(String consulta, String valor, int intentos, int idSinc, int porcentaje, ArticuloReposicion articulo, Hashtable<String, Integer> stocksReservados, Hashtable<String, Integer> stocksEnviados, int idEmpresa) 
	{
		Logica Logica = new Logica();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
				Hashtable<String, StockReposicion> retorno = new Hashtable<>();
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
			  		
					String artAnt = "";
					StockReposicion srAnt = null;
					Hashtable<Integer, DataIDIDDescripcion> deposAnt = null;
					
					
					
					boolean pri = true;
					while (rs.next())
					{
						 if(pri)
						 {
							pri = false;
							 
							srAnt = new StockReposicion();
							srAnt.setArticulo(rs.getString(2));
							deposAnt = new Hashtable<>();
							 System.out.println("");
							DataIDIDDescripcion d = darDID(stocksReservados,stocksEnviados, rs.getInt(1),rs.getInt(3), rs.getString(2));
							
							d.setDescripcion(rs.getString(2));
							deposAnt.put(d.getId(),d);
							 
						 }
						 else
						 {
							 if(artAnt.equals(rs.getString(2)))
							 {
								 DataIDIDDescripcion d = darDID(stocksReservados,stocksEnviados, rs.getInt(1),rs.getInt(3), rs.getString(2));
								 
								 d.setDescripcion(rs.getString(2));
								 deposAnt.put(d.getId(),d);
							 }
							 else
							 {
								 srAnt.setDepositos(deposAnt);
								 retorno.put(srAnt.getArticulo(), srAnt);
								 
								 
								 srAnt = new StockReposicion();
								 srAnt.setArticulo(rs.getString(2));
								 deposAnt = new Hashtable<>();
									 
								 DataIDIDDescripcion d = darDID(stocksReservados,stocksEnviados, rs.getInt(1),rs.getInt(3), rs.getString(2));
									
								 d.setDescripcion(rs.getString(2));
								 deposAnt.put(d.getId(),d);
								 
								 
							 }
							 
						 }
						
						
						artAnt = rs.getString(2);
					}
					
					srAnt.setDepositos(deposAnt);
					retorno.put(srAnt.getArticulo(), srAnt);
					
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					//e.printStackTrace();
					if(intentos>3)
					{
						Logica.actualizarLogSincRepo(idSinc, "ERROR: obteniendo los datos de "+valor+" en el intento numero "+intentos+" Detalle: "+e.getMessage(), porcentaje,idEmpresa);
					}
					
				}
			
				return retorno;
	}
	
	
	
	
	public static List<ArticuloReposicion> darListaVLReposicion(String consulta, int idSinc, int porcentaje, int idEmpresa) 
	{
		Logica Logica = new Logica();
		List<ArticuloReposicion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						if(rs.getInt(6)>0)
						{
							//001.00600000122.0
							//											
							ArticuloReposicion r=new ArticuloReposicion(rs.getString(1),rs.getString(1).substring(0,13),rs.getString(1).substring(0,9),rs.getInt(2), rs.getLong(3), rs.getInt(4),rs.getInt(6),rs.getInt(7),rs.getInt(8),0, rs.getInt(9),rs.getInt(5), rs.getInt(10),rs.getInt(11),rs.getInt(12),1);
																		//String articulo, String articuloBaseColor, String articuloBase, 				int marca, 	int seccion,   int clase,   int venta,	stockCentral,stockLocal,stockBaseColor,int precio, int sucursal, int genero, int categoria
							
							
							lista.add(r);
						}
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					if(idSinc!=0)
					{
						Logica.actualizarLogSincRepo(idSinc, "ERROR: no se pudo acceder a los datos: "+e.getMessage(), porcentaje,idEmpresa);
					}
				}
			
				return lista;
	}
	
	public static List<ArticuloReposicion> darListaVLReposicion2(String consulta, int idSinc, int porcentaje, Hashtable<String, Integer> stocksEnviados, int idEmpresa) 
	{
		List<ArticuloReposicion>lista=new ArrayList<>();
		 /*String connectionUrl = 
		           "jdbc:sqlserver://179.27.145.162:2833;" +
		           "databaseName=VsHushPuppies;";
		        // Declare the JDBC objects.*/
		        try {
		        	
		            // Establish the connection.
		           /* Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");*/
					darConexion(idEmpresa);
		            
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						if(rs.getInt(6)>0)
						{
							if(rs.getString(1).length()>=13){
								//001.00600000122.0-1
								int stk = rs.getInt(8);
								int sucu = rs.getInt(5);
								if(stocksEnviados.get(rs.getString(1)+"-"+sucu)!=null)
								{
									stk +=stocksEnviados.get(rs.getString(1)+"-"+sucu);
								}
								//											
								ArticuloReposicion r=new ArticuloReposicion(rs.getString(1),rs.getString(1).substring(0,13),rs.getString(1).substring(0,9),rs.getInt(2), rs.getLong(3), rs.getInt(4),rs.getInt(6),rs.getInt(7),stk,0, rs.getInt(9),rs.getInt(5), rs.getInt(10),rs.getInt(11),rs.getInt(12),1);
																			//String articulo, String articuloBaseColor, String articuloBase, 				int marca, 	int seccion,   int clase,   int venta,	stockCentral,stockLocal,stockBaseColor,int precio, int sucursal, int genero, int categoria
								
								
								lista.add(r);
							}
							
						}
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					if(idSinc!=0)
					{
						Logica Logica = new Logica();
						Logica.actualizarLogSincRepo(idSinc, "ERROR: no se pudo acceder a los datos: "+e.getMessage(), porcentaje, idEmpresa);
					}
				}
			
				return lista;
	}
	
	public static List<DataIDDescripcion> darArticuloRepoFromDistr(String query, int idEmpresa) 
	{
		Logica Logica = new Logica();
		List<DataIDDescripcion>lista=new ArrayList<>();
		 String connectionUrl = 
		           "jdbc:sqlserver://179.27.145.162:2833;" +
		           "databaseName=VsHushPuppies;";
		        // Declare the JDBC objects.
		 		int idD=0;
		        try {
		        	
		            // Establish the connection.
		            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
		            
			        Statement s = con.createStatement();
			        ResultSet rs = null;
			       
			        rs = s.executeQuery (query);			  						
			  		
					String justificacion = "";
					int destino = 0;
					int docAnt = 0;
					String fehchaAnt = null;
					
					while (rs.next())
					{
						DataIDDescripcion ar = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
						lista.add(ar);
					}
						
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			
				return lista;
	}
	
	public static List<ArticuloReposicion> darArticuloRepoFromDistr(String consulta, int idSinc,Hashtable<String, Integer> stocksEnviados,
			int ultimaD, Hashtable<String, String> distribucionesActivas, int idEmpresa, int tipo) 
	{
		Logica Logica = new Logica();
		List<ArticuloReposicion>lista=new ArrayList<>();
		 String connectionUrl = 
		           "jdbc:sqlserver://179.27.145.162:2833;" +
		           "databaseName=VsHushPuppies;";
		        // Declare the JDBC objects.
		 		int idD=0;
		        try {
		        	
		            // Establish the connection.
		            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
		            
			        Statement s = con.createStatement();
			        ResultSet rs = null;
			       
			        rs = s.executeQuery (consulta);			  						
			  		
					String justificacion = "";
					int destino = 0;
					int docAnt = 0;
					String fehchaAnt = null;
					
					while (rs.next())
					{
						idD = rs.getInt(14);
						destino = rs.getInt(11);
						
						ArticuloReposicion a = new ArticuloReposicion(rs.getString(1), rs.getString(1).substring(0,13), rs.getString(2), rs.getInt(4), rs.getLong(5), rs.getInt(6), rs.getInt(3), 0, 0, 0, 0, destino, rs.getInt(7), rs.getInt(8), rs.getInt(9),tipo);
						//marca,		seccion,	clase,		   venta, 					 sucursal,genero,	     categoria,    temporada
						
						
						a.setDestino(destino);
						
						a.setOrigen(9000);						
						justificacion = rs.getString(10);
						a.setSolicitud(idD);
						if(rs.getInt(11)==0){
							try {
								justificacion += " - "+rs.getString(13);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
						if(distribucionesActivas.get(a.getArticulo()+"-"+a.getOrigen()+"-"+a.getDestino()+"-"+a.getSolicitud())==null){
							System.out.println("Ingresando linea");
							Logica.guardarLineaReposicion(a, justificacion, idSinc,0,idEmpresa);
						}
						else{
							System.out.println("Linea activa");
						}
						fehchaAnt = rs.getString(15);
						docAnt = idD;
					}
					
					Logica.actualizarFechaSincRepo(idSinc, fehchaAnt,idEmpresa);
					/*if(idD!=0){
						Logica.persistir("insert into reposicion_distribucion (ultimaDist) values ("+idD+")");
					}*/
					
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					/*if(idD!=0){
						Logica.persistir("insert into reposicion_distribucion (ultimaDist) values ("+idD+")");
					}*/
					if(idSinc!=0)
					{
						Logica.actualizarLogSincRepo(idSinc, "ERROR: no se pudo acceder a los datos: "+e.getMessage(), 0, idEmpresa);
					}
				}
			
				return lista;
	}
	
	
	public static Hashtable<String, List<ArticuloReposicion>> darListaBasesClearinf(String consulta) 
	{
		 Hashtable<String, List<ArticuloReposicion>>  lista=new Hashtable<String, List<ArticuloReposicion>>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
			  		Hashtable<String, Hashtable<String, List<ArticuloReposicion>> > basesColor = new Hashtable<String, Hashtable<String,List<ArticuloReposicion>>>();
			  		
					
					while (rs.next())
					{
						String base = rs.getString(1);
						String baseColor = rs.getString(2).substring(0,13);
						
						if(basesColor.get(base)==null)
						{
							
							Hashtable<String, List<ArticuloReposicion>> colors = new Hashtable<String, List<ArticuloReposicion>>();
							List<ArticuloReposicion> arts = new ArrayList<>();
							ArticuloReposicion ar=new ArticuloReposicion(rs.getString(2),rs.getString(2).substring(0,13),rs.getString(2).substring(0,9),rs.getInt(3), rs.getLong(4), rs.getInt(5),rs.getInt(7),rs.getInt(8),rs.getInt(9),0,           rs.getInt(10),rs.getInt(6), rs.getInt(11),rs.getInt(12),rs.getInt(13),0);
							arts.add(ar);
							colors.put(baseColor, arts);
							basesColor.put(base, colors);
							
						}
						else
						{
							Hashtable<String, List<ArticuloReposicion>> colors = basesColor.get(base);
							if(colors.get(baseColor)==null)
							{
								List<ArticuloReposicion> arts = new ArrayList<>();
								ArticuloReposicion ar=new ArticuloReposicion(rs.getString(2),rs.getString(2).substring(0,13),rs.getString(2).substring(0,9),rs.getInt(3), rs.getLong(4), rs.getInt(5),rs.getInt(7),rs.getInt(8),rs.getInt(9),0,           rs.getInt(10),rs.getInt(6), rs.getInt(11),rs.getInt(12),rs.getInt(13),0);
								arts.add(ar);
								colors.put(baseColor, arts);
								basesColor.put(base, colors);
								
							}
						}
					}
					closeFullConnection(con, rs, s);
					
					Set<String> keys = basesColor.keySet();
			        for(String key: keys)
			        {
			        	Hashtable<String, List<ArticuloReposicion>> in = basesColor.get(key);
			        	Set<String> keys2 = in.keySet();
			        	List<ArticuloReposicion> listaBase = new ArrayList<>();
			        	for(String key2: keys2)
				        {
			        		for (ArticuloReposicion a : in.get(key2)) 
			        		{
								listaBase.add(a);
							}
				        }
			        	
			        	lista.put(key, listaBase); 
			        }
					
					
					
					
					
				}
				catch(Exception e)
				{
					
				}
				
			
				return lista;
	}
	
	
	
	public static List<ArticuloReposicion> darListaVLReposicionMayorista(String consulta, Hashtable<String, DataDepositoSAP> depositosCliente) 
	{
		List<ArticuloReposicion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;databaseName=STADIUM_DEV2;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
				     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				    con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
							//001.00600000122.0
							//				articulo!articuloBaseColor ! articuloBase ! marca ! seccion ! clase ! venta ! stockCentral ! stockLocal ! stockBaseColor ! precio ! sucursal
						int suc = 0;
						try
						{
							suc = depositosCliente.get(rs.getString(10)).getIdEncuentra();
						}
						catch(Exception e2)
						{
							
						}
						int idPicking=0;
						try 
						{
							_EncuentraConexion econ = new _EncuentraConexion();
							Connection cone;
							cone = econ.getConnection();
							
							idPicking = econ.darIdDescripcion("select idPicking, ''  from reposicion_articulos where  `idArticulo` = '"+rs.getString(1)+"' AND `Origen`= 99 AND `Destino`= "+suc+" AND `Seccion` = "+rs.getInt(3)+"").get(0).getId();
							
						}
						catch (Exception e) 
						{
							idPicking=0;
						}
						 
						ArticuloReposicion r=new ArticuloReposicion(rs.getString(1),rs.getString(1).substring(0,13),rs.getString(1).substring(0,9),rs.getInt(2), rs.getLong(3), rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),0,           rs.getInt(9),suc,0,0,0,3);
						r.setIdpicking(idPicking);
						r.setDepoCliente(rs.getString(10));
						r.setIdLineaSAP(rs.getInt(11));
						
						lista.add(r);
						
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					//Logica.actualizarLogSincRepo(idSinc, "ERROR: no se pudo acceder a los datos: "+e.getMessage(), porcentaje);
					
					
				}
			
				return lista;
	}



	public static Hashtable<String, DataDepositoSAP> darDepositosSAP(String consulta, int idEmpresa) 
	{
		Hashtable<String, DataDepositoSAP> lista=new Hashtable<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;databaseName=STADIUM_DEV2;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
				try 
				{
					
					// Establish the connection.
				     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				    con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (consulta);
					
					
					while (rs.next())
					{
						DataDepositoSAP r = new DataDepositoSAP();
						r.setIdDepo(rs.getString(1));
						r.setNombre(rs.getString(2));
						r.setDireccion(rs.getString(3));
						r.setPadre(rs.getString(4));
						Logica Logica = new Logica();
						int id = Logica.encuentraAltaUpdateDepo(r, idEmpresa); 	
						r.setIdEncuentra(id);
						lista.put(r.getIdDepo(), r);
						
						
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					//Logica.actualizarLogSincRepo(idSinc, "ERROR: no se pudo acceder a los datos: "+e.getMessage(), porcentaje);
					
					
				}
			
				return lista;
	}
	
	
	
	



	public static List<DataArticuloConsulta> ConsultarStock(String consulta) 
	{
		List<DataArticuloConsulta>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
		try 
		{
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery (consulta);
			/*
			 * deposito
			 * 	base color
			 * 		talle stock
			 * */
			
			while (rs.next())
			{
					System.out.println(rs.getString(1));	
			}
			closeFullConnection(con, rs, s);
					
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}



	public static List<ArticuloReposicion> darArticuloRepoFromLoad(List<DataIDDescripcion> articulosCantidad, String tempTable,StringBuilder inserts, String query, int sucursal, boolean manual, int idEmpresa) 
	{
		Logica Logica = new Logica();

		List<ArticuloReposicion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
		try 
		{
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			Statement s = con.createStatement();
			
			boolean isResult = s.execute(tempTable + inserts.toString()+ query); 
			
			
			
			// Skip over update counts.  
			while (!isResult) {
			    if (s.getUpdateCount() == 0)
			    	break;
			        // End of results.
			        
			    isResult = s.getMoreResults();
			}


			// Process first result set.
			ResultSet rs = s.getResultSet();
			// Process second result set.
			rs = s.getResultSet();
			
			String justificacion = "Lineas manuales";
			int idSincro = Logica.darNextSincRepo(idEmpresa);
			Logica.actualizarSincRepo(1,idSincro,idEmpresa);
			if(!manual)
			{
				
				justificacion = "Venta WEB";
			}
				
				
			while (rs.next())
			{
				ArticuloReposicion a = new ArticuloReposicion(rs.getString(1), rs.getString(1).substring(0,13), rs.getString(2), rs.getInt(4), rs.getLong(5), rs.getInt(6), rs.getInt(3), 0, 0, 0, 0, sucursal, rs.getInt(7), rs.getInt(8), rs.getInt(9), 1);
																																//marca,		seccion,	clase,		   venta, 					 sucursal,genero,	     categoria,    temporada
				a.setDestino(sucursal);
				a.setOrigen(99);
				Logica.guardarLineaReposicion(a, justificacion, idSincro,0,idEmpresa);
				
				
			}
			closeFullConnection(con, rs, s);
					
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	
	
	public static List<DataIDIDDescripcion> darArticuloRepoOrderClosed(String tempTable,StringBuilder inserts, String query) 
	{
		

		List<DataIDIDDescripcion>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;" +
				"databaseName=STADIUM_DEV2;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
		try 
		{
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			Statement s = con.createStatement();
			System.out.println(tempTable + inserts.toString()+ query);
			boolean isResult = s.execute(tempTable + inserts.toString()+ query); 
			
			
			
			// Skip over update counts.  
			while (!isResult) 
			{
			    if (s.getUpdateCount() == 0)
			    	break;
			        // End of results.
			        
			    isResult = s.getMoreResults();
			}


			// Process first result set.
			ResultSet rs = s.getResultSet();
			// Process second result set.
			rs = s.getResultSet();
				
			while (rs.next())
			{
				DataIDIDDescripcion ar = new DataIDIDDescripcion(rs.getInt(1), rs.getInt(2), rs.getString(3));
				lista.add(ar);
				
			}
			closeFullConnection(con, rs, s);
					
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	public static List<ArticuloReposicion> darArticuloRepoOrderVM(String tempTable,StringBuilder inserts, String query, Hashtable<String, DataDepositoSAP> depositosCliente) 
	{
		

		List<ArticuloReposicion> lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;" +
				"databaseName=STADIUM_DEV2;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
		try 
		{
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			Statement s = con.createStatement();
			
			boolean isResult = s.execute(tempTable + inserts.toString()+ query); 
			System.out.println(tempTable + inserts.toString()+ query);
			
			
			// Skip over update counts.  
			while (!isResult) 
			{
			    if (s.getUpdateCount() == 0)
			    	break;
			        // End of results.
			        
			    isResult = s.getMoreResults();
			}


			// Process first result set.
			//ResultSet rs = s.getResultSet();
			// Process second result set.
			ResultSet rs = s.getResultSet();
				
			String sinMarca="";
			int marca=0;
			
			int suma=0;
			
			while (rs.next())
			{
				
				if(rs.getInt(3)==1151){
					suma++;
				}
				
				//				articulo!articuloBaseColor ! articuloBase ! marca ! seccion ! clase ! venta ! stockCentral ! stockLocal ! stockBaseColor ! precio ! sucursal
				int suc = 0;
				try
				{
					suc = depositosCliente.get(rs.getString(10)).getIdEncuentra();
				}
				catch(Exception e2)
				{
					
				}
				
				
				int idPicking=0;
				try 
				{
					_EncuentraConexion econ = new _EncuentraConexion();
					Connection cone;
					cone = econ.getConnection();
					
					idPicking = econ.darIdDescripcion("select idPicking, ''  from reposicion_articulos where  `idArticulo` = '"+rs.getString(1)+"' AND `Origen`= 99 AND `Destino`= "+suc+" AND `Seccion` = "+rs.getInt(3)+"").get(0).getId();
					
				}
				catch (Exception e) 
				{
					idPicking=0;
				}
				
				try{
					marca=rs.getInt(2);
				}
				catch (Exception e){
					marca=0;
					if (sinMarca.equals("")){
						sinMarca+= rs.getString(1);
					}
					else{
						sinMarca+=", "+rs.getString(1);						
					}
				}
				if(rs.getString(1).equals("201.B0007100040.0")){
					System.out.println("aca");
				}
				 
				ArticuloReposicion r=new ArticuloReposicion(rs.getString(1),rs.getString(1).substring(0,13),rs.getString(1).substring(0,9),marca, rs.getLong(3), rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),0,           rs.getInt(9),suc,0,0,0,3);
				r.setIdpicking(idPicking);
				r.setDepoCliente(rs.getString(10));
				r.setIdLineaSAP(rs.getInt(11));
				
				lista.add(r);
				
			}
			closeFullConnection(con, rs, s);
			if(!sinMarca.equals("")){
				String cuerpo = "Los siguientes articulos fueron ingresados sin marca: "+sinMarca;
				EnviaMail.enviarMail("dbuenahora@stadium.com.uy","Articulos sin marca" , cuerpo);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	
	
	
	
	
	public static List<LineaTomaPedido> darTomaPedidos(String consulta)
	{
		List<LineaTomaPedido>retorno=new ArrayList<>();
	
		
		// Create a variable for the connection string.
        String connectionUrl = 
           "jdbc:sqlserver://osonecasap:1433;" +
           "databaseName=Productiva_Osoneca;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","Sql0sonecaS4p");
            
            //
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 System.out.println(consulta);
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 boolean pri = true;
	  		 String artColAnt = "";
	  		 int rowspan = 0;
	  		List<LineaTomaPedidoTalle> lineas = null;
	  		LineaTomaPedido lt = null;
	  		 
	  		 while (rs.next())
	  		   {
	  			 
	  			 if(pri)
	  			 {
	  				pri = false;
	  				rowspan =0;
	  				lt = new LineaTomaPedido();
	  				lt.setColor(rs.getString(3));
	  				lt.setDescColor(rs.getString(4));
	  				lt.setDescModel(rs.getString(5));
	  				lt.setModel(rs.getString(2));
	  				lt.setGenero(rs.getString(7));
	  				
	  				
	  				lineas = new ArrayList<>();
	  				
	  				
	  				LineaTomaPedidoTalle ltt = new LineaTomaPedidoTalle();
	  				ltt.setComprado(0);
	  				ltt.setEan(rs.getString(8));
	  				ltt.setPrecioCliente(rs.getDouble(13));
	  				ltt.setPvp(rs.getDouble(12));
	  				ltt.setStock(rs.getInt(11));
	  				ltt.setTalla(rs.getString(6));
	  				ltt.setTallaMX(rs.getString(14));
	  				ltt.setTotalCompra(0.0);
	  				
	  				lineas.add(ltt);
	  				 
	  			 }
	  			 else
	  			 {
	  				 if(artColAnt.equals(rs.getString(2)+"."+rs.getString(3)))
	  				 {
	  					 //mismo modelo color
	  					LineaTomaPedidoTalle ltt = new LineaTomaPedidoTalle();
	  					ltt.setComprado(0);
		  				ltt.setEan(rs.getString(8));
		  				ltt.setPrecioCliente(rs.getDouble(13));
		  				ltt.setPvp(rs.getDouble(12));
		  				ltt.setStock(rs.getInt(11));
		  				ltt.setTalla(rs.getString(6));
		  				ltt.setTallaMX(rs.getString(14));
		  				ltt.setTotalCompra(0.0);
		  				
		  				lineas.add(ltt);
	  				 }
	  				 else
	  				 {
	  					 //cambia
	  					 lt.setTallas(lineas);
	  					 lt.setRowspan(rowspan);
	  					 retorno.add(lt);
	  					 
	  					 //uno nuevo
	  					rowspan =0;
		  				lt = new LineaTomaPedido();
		  				lt.setColor(rs.getString(3));
		  				lt.setDescColor(rs.getString(4));
		  				lt.setDescModel(rs.getString(5));
		  				lt.setModel(rs.getString(2));
		  				lt.setGenero(rs.getString(7));
		  				
		  				
		  				lineas = new ArrayList<>();
		  				
		  				
		  				LineaTomaPedidoTalle ltt = new LineaTomaPedidoTalle();
		  				ltt.setComprado(0);
		  				ltt.setEan(rs.getString(8));
		  				ltt.setPrecioCliente(rs.getDouble(13));
		  				ltt.setPvp(rs.getDouble(12));
		  				ltt.setStock(rs.getInt(11));
		  				ltt.setTalla(rs.getString(6));
		  				ltt.setTallaMX(rs.getString(14));
		  				ltt.setTotalCompra(0.0);
		  				
		  				lineas.add(ltt);
	  					 
	  					 
	  				 }
	  			 }
	  				 
	  			 rowspan++;
	  			 artColAnt = rs.getString(2)+"."+rs.getString(3);
	  		}
	  		//la ultima pasada
			lt.setTallas(lineas);
			lt.setRowspan(rowspan);
			retorno.add(lt);
	  		closeFullConnection(con, rs, s);
	  	}
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
      return retorno;
      
	}



	public static List<DataOVOsoneca> darlistaOVOSO(String q) 
	{
		List<DataOVOsoneca>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://osonecasap:1433;" +
				"databaseName=Productiva_Osoneca;";
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","Sql0sonecaS4p");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (q);
					while (rs.next())
					{
						String fechaE = rs.getString(2).substring(0,4)+rs.getString(2).substring(5,7)+rs.getString(2).substring(8,10);
						DataOVOsoneca d = new DataOVOsoneca(rs.getString(1), rs.getString(1)+"_1", fechaE, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(12), rs.getString(10),rs.getString(11), rs.getString(15),fechaE);
						lista.add(d);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}



	public static List<DataOVOsonecaLineas> darlistaOVOSOLineas(String q) 
	{
		List<DataOVOsonecaLineas>lista=new ArrayList<>();
		String connectionUrl="jdbc:sqlserver://osonecasap:1433;" +
				"databaseName=Productiva_Osoneca;";
				try 
				{
					
					// Establish the connection.
			        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			        con = DriverManager.getConnection(connectionUrl,"sa","Sql0sonecaS4p");
			        Statement s = con.createStatement();
			  		ResultSet rs = s.executeQuery (q);
					while (rs.next())
					{
						int qty = rs.getInt(6);
						String qtySTR = String.valueOf(qty)+"000";
						DataOVOsonecaLineas d = new DataOVOsonecaLineas(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),qtySTR,rs.getString(7),rs.getString(8),qtySTR,rs.getString(10));
						lista.add(d);
					}
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
				}
			
				return lista;
	}
/*
	public static WSPortalComprobanteAPDF0200Execute darParametrosCFE(String consulta) 
	{
		WSPortalComprobanteAPDF0200Execute parameters = new WSPortalComprobanteAPDF0200Execute();
		String connectionUrl="jdbc:sqlserver://10.108.0.1:1433;" +
				"databaseName=VsStadium;";
					    // Declare the JDBC objects.
				//System.out.println(consulta);
		try 
		{
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery (consulta);
			
			
			while (rs.next())
			{
				parameters.setPctehash(rs.getString(1));
				parameters.setPempruc(rs.getString(2));
				parameters.setPiddocnro(rs.getInt(4));
				parameters.setPiddocserie(rs.getString(3));
				parameters.setPiddoctipocfe((short) rs.getInt(5));
				parameters.setPtotmnttotal(rs.getDouble(6));
			}
			closeFullConnection(con, rs, s);
					
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		return parameters;
		
	}*/

	public static List<DataIDDescripcion> darListaIdDescripcionID(String string) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	public static int daridDocSAP (String consulta) 
	{
		int retorno = 0;
		String connectionUrl="jdbc:sqlserver://10.108.0.8:1433;" +
		"databaseName=STADIUM_DEV2;";
			    // Declare the JDBC objects.
		System.out.println(consulta);
		try 
		{
			
			// Establish the connection.
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	        Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
			
			
	  		while (rs.next()) 
			{
				
				
				retorno=rs.getInt(1);
				

			}
	  		closeFullConnection(con, rs, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return retorno;
		
	}
	
	public static List <DataIDDescDescripcion> darReporteEcommerceRemitos(String consulta) 
	{	
		List <DataIDDescDescripcion> retorno = new ArrayList<>();
				
		// Create a variable for the connection string.
        String connectionUrl = 
           "jdbc:sqlserver://10.108.0.1:1433;" +
           "databaseName=VsStadium;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            
            //
            	
        		
	  		 Statement s = con.createStatement();
	  		
	  		 
	  		 	  		 
	  		 ResultSet rs = s.executeQuery (consulta);
	  		 
	  		 
	  		 while (rs.next())
	  		   {   
	  			DataIDDescDescripcion d = new DataIDDescDescripcion(rs.getInt(1), rs.getString(3),rs.getString(2));
	  			d.setDescripcionII(rs.getString(4));
	  			d.setPorcentaje(rs.getInt(5));
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
	
	
	
	
	
	
	public static List<String> darArticulosAlarmasDash(String consulta) 
	{
		// Create a variable for the connection string.
        String connectionUrl = "";
        
        
       connectionUrl = "jdbc:sqlserver://10.108.0.8:1433;" +
	                "databaseName=anysys;";
        
         
        // Declare the JDBC objects.
        List<String>  retorno = new ArrayList<>();
        try 
        {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
	  		
	  		
	  		 while (rs.next())
	  		 {   
	  			retorno.add(rs.getString(1));
	  		 }
	  		
	  		closeFullConnection(con, rs, s);
      }
      catch (Exception e) 
        {
          e.printStackTrace();
      }
     
      return retorno;
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
		            
		            /*String query =	"SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; "
		            		+ " SELECT a.idarticulo,a.\"Cantidad pendiente\",origen,NumeroDoc from [VsHushPuppies].[dbo].[encuentra_distribucion] a "
		            		+ "where destino = 1200 and Comentario = CONCAT('Trans.Venta Dep. 1200 Doc. ','"
							+serie+"','-','"+vta+"') "+
		            		"UNION ALL "+
							"SELECT a.idarticulo,a.\"Cantidad pendiente\",origen,NumeroDoc from [VsHushPuppies].[dbo].[encuentra_distribucion_terminada] a "
							+ "where destino = 1200 and Comentario = CONCAT('Trans.Venta Dep. 1200 Doc. ','"
							+serie+"','-','"+vta+"') ";		  */
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
	
	public static List<Compras> darComprasWeb(String query,Hashtable<String, String> depositosPickHT,int ultimaVenta, int idEmpresa, Hashtable<String, DataIDDescripcion> destinoPedidos) 
	{
		Logica Logica = new Logica();
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
							cli = new  Cliente();	
							
							if(rs.getInt(2)==196799 ) {
								System.out.println("caso");
							}
													
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
							
							
							c.setIdCanal(canal);
							
							
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
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					
					
					if(ultimoId>ultimaVenta && ultimaVenta!=0){
						Logica.persistir("insert into ecommerce_sincro (ultimaVenta, idEmpresa) values ("+ultimoId+","+idEmpresa+")");
					}
					
					
					closeFullConnection(con, rs, s);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					
					if(ultimoId>ultimaVenta  && ultimaVenta!=0){
						Logica.persistir("insert into ecommerce_sincro (ultimaVenta,idEmpresa) values ("+ultimoId+","+idEmpresa+")");
					}
				}
			
				return pedidos;
	}	
	

public static List<DataIDDescripcion> darVtaVisual(String query) 
{
	Logica Logica = new Logica();
	List<DataIDDescripcion> distribuciones;
	
	 String connectionUrl = 
	           "jdbc:sqlserver://179.27.145.162:2833;" +
	           "databaseName=VsECommerce;";
	        // Declare the JDBC objects.
	        try {
	        	
	            // Establish the connection.
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
	            			        
		        Statement s = con.createStatement();
		        ResultSet rs = s.executeQuery (query);
		        
				StringBuilder vtas= new StringBuilder();
				int i= 0;
				while (rs.next())
				{
					if(i==0){
						vtas.append(" Comentario = CONCAT('Trans.Venta Dep. 1200 Doc. ','"+rs.getString(1)+"','-','"+rs.getString(2)+"') ");
					}else{
						vtas.append(" or Comentario = CONCAT('Trans.Venta Dep. 1200 Doc. ','"+rs.getString(1)+"','-','"+rs.getString(2)+"') ");
					}
						 
			        i++;
				}
				
				System.out.println(vtas.toString());
				String queryLineas = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT numerodoc,idarticulo,\"Cantidad pendiente\",substring(comentario,30,6) "+
				"from [VsHushPuppies].[dbo].[encuentra_distribucion] where destino = 1200 AND origen=9000 and ("+vtas.toString()+");";
				System.out.println(queryLineas);
				distribuciones = darDistribucionesVisual(queryLineas); 
				
				closeFullConnection(con, rs, s);
				return distribuciones;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return new ArrayList<>();
			}
		
			
}	


public static List<DataIDDescripcion> darDistribucionesVisual(String query) 
{
	List<DataIDDescripcion> items = new ArrayList<>(); ;
	
	 String connectionUrl = 
           "jdbc:sqlserver://179.27.145.162:2833;" +
           "databaseName=VsHushPuppies;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
           	        
	        Statement s = con.createStatement();
	        ResultSet rs = s.executeQuery (query);
			
			while (rs.next())
			{
	        	DataIDDescripcion data = new DataIDDescripcion(rs.getInt(1),rs.getString(2));
	        	data.setIdB(rs.getInt(3));
	        	data.setDescripcionB(rs.getString(4));
	        	items.add(data);
			}
			closeFullConnection(con, rs, s);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
	
		return items;
}

public static List<DataIDDescDescripcion> darMovTransitoVisual(String query) 
{
	List<DataIDDescDescripcion> items = new ArrayList<>(); ;
	
	 String connectionUrl = 
           "jdbc:sqlserver://179.27.145.162:2833;" +
           "databaseName=VsHushPuppies;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
           	        
	        Statement s = con.createStatement();
	        ResultSet rs = s.executeQuery (query);
			
			while (rs.next())
			{
				DataIDDescDescripcion data = new DataIDDescDescripcion(rs.getInt(1), "","");
	        	data.setIdII(rs.getInt(2));
	        	
	        	items.add(data);
			}
			closeFullConnection(con, rs, s);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
	
		return items;
}


public static Hashtable<String, DataIDDescripcion> darVtasSinDistribucion(String query) 
{
	Logica Logica = new Logica();
	List<DataIDDescripcion> vtasSD;
	
	 String connectionUrl = 
           "jdbc:sqlserver://179.27.145.162:2833;" +
           "databaseName=VsECommerce;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
            			        
	        Statement s = con.createStatement();
	        ResultSet rs = s.executeQuery (query);
	        		        
			Hashtable<String, DataIDDescripcion> ventas = new Hashtable<>();
			String vtas="";
			int i= 0;
			while (rs.next())
			{
				DataIDDescripcion data = new DataIDDescripcion(rs.getInt(3), rs.getString(4));
				data.setDescripcionB(rs.getString(5));
				ventas.put(rs.getString(2), data);
				
				if(i==0){
					vtas +=" Comentario = CONCAT('Trans.Venta Dep. 1200 Doc. ','"+rs.getString(1)+"','-','"+rs.getString(2)+"') ";
				}else{
					vtas += " or Comentario = CONCAT('Trans.Venta Dep. 1200 Doc. ','"+rs.getString(1)+"','-','"+rs.getString(2)+"') ";
				}
					 
		        i++;
			}
			
			String queryLineas = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT substring(comentario,30,6) from [VsHushPuppies].[dbo].[encuentra_distribucion] where destino = 1200 AND origen=9000 and ("+
			vtas+");";
			System.out.println(queryLineas);
			ventas = separarVtasSinDistribuciones(ventas,queryLineas); 
			
			closeFullConnection(con, rs, s);
			return ventas;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new Hashtable<>();
		}
			
}	

public static Hashtable<String, DataIDDescripcion> separarVtasSinDistribuciones(Hashtable<String, DataIDDescripcion> vtas,String query) 
{
	
	 String connectionUrl = 
           "jdbc:sqlserver://179.27.145.162:2833;" +
           "databaseName=VsHushPuppies;";
        // Declare the JDBC objects.
        try {
        	
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
           	        
	        Statement s = con.createStatement();
	        ResultSet rs = s.executeQuery (query);
			
			while (rs.next())
			{
				System.out.println(rs.getString(1));
	        	vtas.remove(rs.getString(1));
			}
			closeFullConnection(con, rs, s);
						
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
	
		return vtas;
}



public static List<Remito> DarRemitos(String query) 
{
	List<Remito> retorno = new ArrayList<>();
	 String connectionUrl = "jdbc:sqlserver://179.27.145.162:2833;" +
	           "databaseName=VsHushPuppies;";
	        // Declare the JDBC objects.
	        try {
	        	
	            // Establish the connection.
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
	           	        
		        Statement s = con.createStatement();
		        ResultSet rs = s.executeQuery (query);
				
		        
		        List<RemitoLinea> lineas = null;
		        Remito remitoAnt = null;
		        int idDocAnt = 0;
		        boolean pri = true;
		        Hashtable<String, String> entregas = null;
		        
				while (rs.next())
				{
					
					int idDocAct = rs.getInt(1);
					Fecha fecha = new Fecha(rs.getString(7));
					if(pri)
					{
						pri = false;
						remitoAnt = new Remito(rs.getInt(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(10), rs.getString(3), fecha.darFechaDia_Mes_Anio_Barra());
						lineas = new ArrayList<>();
						lineas.add(new RemitoLinea(rs.getString(9), rs.getInt(8)));
						entregas = new Hashtable<>();
						entregas.put(rs.getString(2),rs.getString(2));
						
						
						
					}
					else if(idDocAct==idDocAnt)
					{
						lineas.add(new RemitoLinea(rs.getString(9), rs.getInt(8)));
						entregas.put(rs.getString(2),rs.getString(2));
					}
					else
					{
						remitoAnt.setLineas(lineas);
						remitoAnt.setEntregasAfecta(new ArrayList<>(entregas.values()));
						retorno.add(remitoAnt);
						
						remitoAnt = new Remito(rs.getInt(2), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(10), rs.getString(3), fecha.darFechaDia_Mes_Anio_Barra());
						lineas = new ArrayList<>();
						lineas.add(new RemitoLinea(rs.getString(9), rs.getInt(8)));
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

private static void darConexion(int idEmpresa)
{
	PropertiesHelper pH = null;
	switch (idEmpresa) 
	{
		case 1:
			pH = new PropertiesHelper("SQLSTADIUM");
		break;
		case 2:
			pH = new PropertiesHelper("SQLFORUS");
			break;
		case 4:
			pH = new PropertiesHelper("SQLELREY");
		break;

		default:
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
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public static Hashtable<String, Integer> darStocks(List<DataIDDescripcion> lista, int origen) 
{
	
	Hashtable<String, Integer> items = new Hashtable<>();
	
	 String connectionUrl = 
	           "jdbc:sqlserver://179.27.145.162:2833;" +
	           "databaseName=VsHushPuppies;";
	        // Declare the JDBC objects.
	        try {
	        	
	            // Establish the connection.
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
	            
	            String arts = "";
	            for(DataIDDescripcion l:lista){
	            	arts += "'"+l.getDescripcion()+"',";
	            }
	            arts = arts.substring(0,arts.length()-1);
	            
	            String query =	"SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT IdArticulo,stock FROM dbo.encuentra_stock WHERE deposit="+origen+" and IdArticulo in ("+arts+");";
	            System.out.println(query);
		        Statement s = con.createStatement();
		        ResultSet rs = s.executeQuery (query);
				
				while (rs.next())
				{
		        	items.put(rs.getString(1), rs.getInt(2));				
				}
				closeFullConnection(con, rs, s);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();				
			}
		
			return items;
}

public static Hashtable<String, Integer> darStocks(String arts, int origen) 
{
	
	Hashtable<String, Integer> items = new Hashtable<>();
	
	 String connectionUrl = 
	           "jdbc:sqlserver://179.27.145.162:2833;" +
	           "databaseName=VsHushPuppies;";
	        // Declare the JDBC objects.
	        try {
	        	
	            // Establish the connection.
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
	            
	            String query =	"SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT IdArticulo,stock FROM dbo.encuentra_stock WHERE deposit="+origen+" and IdArticulo in ("+arts+");";
	            System.out.println(query);
		        Statement s = con.createStatement();
		        ResultSet rs = s.executeQuery (query);
				
				while (rs.next())
				{
		        	items.put(rs.getString(1), rs.getInt(2));				
				}
				closeFullConnection(con, rs, s);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();				
			}
		
			return items;
}


public static Hashtable<String, Integer> darStocksLike(String consulta) 
{
	
	Hashtable<String, Integer> items = new Hashtable<>();
	
	 String connectionUrl = 
	           "jdbc:sqlserver://179.27.145.162:2833;" +
	           "databaseName=VsHushPuppies;";
	        // Declare the JDBC objects.
	        try {
	        	
	            // Establish the connection.
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(connectionUrl,"ENCUENTRA",".Wm$2oo.$!");
	            	            
	            System.out.println(consulta);
		        Statement s = con.createStatement();
		        ResultSet rs = s.executeQuery (consulta);
				
				while (rs.next())
				{
		        	items.put(rs.getString(1), rs.getInt(2));				
				}
				closeFullConnection(con, rs, s);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();				
			}
		
			return items;
}

////////////////////////////////////////OPERACIONES TIENDA/////////////////////////////////////////////////////////

public static String DarConexionTienda(int idTienda, int idEmpresa) 
{
	
	// Create a variable for the connection string.
	if(idTienda==99)
	{
		return "jdbc:sqlserver://10.108.0.1:1433; databaseName=VsStadium;";
	}
    String connectionUrl = "jdbc:sqlserver://10.108.0.1:1433; databaseName=VsStadium;";
    // Declare the JDBC objects.
    try 
    {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
        Statement s = con.createStatement();
  		ResultSet rs = s.executeQuery ("SELECT ServidorSQL FROM ConexionRemota where IdTienda = "+idTienda);
  		
  		while (rs.next())
  		{   
  			return "jdbc:sqlserver://"+rs.getString(1)+":1433; databaseName=VsStadium;";
  		}
  		
  	
  		closeFullConnection(con, rs, s);
      
  }
  // Handle any errors that may have occurred.
  catch (Exception e) {
      e.printStackTrace();
  }
 
  return "";
}


public static List<DataDescDescripcion> DarArticulosMatch(String query, int idTienda, int idEmpresa) 
{
	List<DataDescDescripcion> retorno = new ArrayList<>();
	// Create a variable for the connection string.
    String connectionUrl = DarConexionTienda(idTienda,idEmpresa);
    // Declare the JDBC objects.
    try 
    {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
        Statement s = con.createStatement();
  		ResultSet rs = s.executeQuery (query);
  		
  		while (rs.next())
  		{   
  			DataDescDescripcion dato = new DataDescDescripcion(rs.getString(1), rs.getString(2));
  			
  			retorno.add(dato);
  		}
  		
  	
  		closeFullConnection(con, rs, s);
      
  }
  // Handle any errors that may have occurred.
  catch (Exception e) {
      e.printStackTrace();
  }
 
  return retorno;
}

public static List<DataIDDescripcion> darListaIdDescripcionInStore(String consulta,int idTienda, int idEmpresa) 
{
	List<DataIDDescripcion>lista=new ArrayList<>();
	String connectionUrl = DarConexionTienda(idTienda,idEmpresa);
				    // Declare the JDBC objects.
			System.out.println(consulta);
			try 
			{
				
				// Establish the connection.
		        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
		        Statement s = con.createStatement();
		  		ResultSet rs = s.executeQuery (consulta);
				
				
				while (rs.next())
				{
					DataIDDescripcion da=new DataIDDescripcion();
					da.setId(rs.getInt(1));
					da.setDescripcion(rs.getString(2));
					lista.add(da);
				}
				closeFullConnection(con, rs, s);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				
			}
		
			return lista;
}

public static Hashtable<String, Integer> darHTStockInStore(String consulta, int idTienda, int idEmpresa) 
{
	Hashtable<String, Integer>lista=new Hashtable<>();
	String connectionUrl = DarConexionTienda(idTienda,idEmpresa);
			System.out.println(consulta);
			try 
			{
				
				// Establish the connection.
		        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
		        Statement s = con.createStatement();
		  		ResultSet rs = s.executeQuery (consulta);
				
				
				while (rs.next())
				{
					//depArtTalle.put(m.getDestino()+"-"+m.getArticulo()+"-"+m.getTalle(), m.getCantidad());
					//DEPO-BASECOL-TALLE
					lista.put(rs.getInt(2)+"-"+rs.getString(1).substring(0,13)+"-"+rs.getString(1).substring(13,17), rs.getInt(3));
					
				}
				closeFullConnection(con, rs, s);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				
			}
		
			return lista;
}

public static List<RecepcionExpedicion> darArtRecepcionables(String consulta,List<RecepcionExpedicion> recepcionablesSEL)
{
	 
	 Hashtable<Integer, List<DataIDDescripcion>> documentos = new Hashtable<Integer, List<DataIDDescripcion>>();
	// Create a variable for the connection string.
    String connectionUrl ="jdbc:sqlserver://10.108.0.1:1433;databaseName=VsStadium;";
    // Declare the JDBC objects.
    try 
    {
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
        Statement s = con.createStatement();
		
		boolean isResult = s.execute(consulta); 
		
		
		
		// Skip over update counts.  
		while (!isResult) {
		    if (s.getUpdateCount() == 0)
		    	break;
		        // End of results.
		        
		    isResult = s.getMoreResults();
		}


		// Process first result set.
		ResultSet rs = s.getResultSet();
		// Process second result set.
		rs = s.getResultSet();
  		while (rs.next())
  		{  
  			DataIDDescripcion d = new DataIDDescripcion(rs.getInt(3), rs.getInt(1), rs.getString(2));
  			if(documentos.get(rs.getInt(1))==null)
  			{
  				List<DataIDDescripcion> articulos = new ArrayList<>();
  				articulos.add(d);
  				documentos.put(rs.getInt(1),articulos);
  			}
  			else
  			{
  				documentos.get(rs.getInt(1)).add(d);
  			}
  		} 
  		closeFullConnection(con, rs, s);
  		
  		
  		for (RecepcionExpedicion r : recepcionablesSEL) 
  		{
  			for (DocumentoEnvio de : r.getDocumentos()) 
  			{
  				List<DataIDDescripcion> articulos = documentos.get(de.getNumeroDoc());
  				List<RemitoLinea> lineas = new ArrayList<>();
  				
  				
  				for (DataIDDescripcion a : articulos) 
  				{
  					RemitoLinea rl = new RemitoLinea(a.getDescripcion(), a.getId());
  					lineas.add(rl);
				}
  				
  				de.setLineas(lineas);
  				
			}
  			
  			
		}
  		
  		
  		
    }
  // Handle any errors that may have occurred.
    catch (Exception e) 
    {
      e.printStackTrace();
    }
 
  return recepcionablesSEL;
  
}

///////////////////////////////////////////////////////////////

public static Hashtable<String, List<DataDescDescripcion>> DarRangosConteoStore(String query, int idTienda, int idEmpresa) 
{
	Hashtable<String, List<DataDescDescripcion>> retorno = new Hashtable<String, List<DataDescDescripcion>>();
	// Create a variable for the connection string.
    String connectionUrl = DarConexionTienda(idTienda, idEmpresa);
    // Declare the JDBC objects.
    try 
    {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
        Statement s = con.createStatement();
  		ResultSet rs = s.executeQuery (query);
  		
  		while (rs.next())
  		{   
  			DataDescDescripcion dato = new DataDescDescripcion(rs.getString(2), rs.getString(3));
  			if(retorno.get(rs.getString(1))==null)
  			{
  				List<DataDescDescripcion> datos = new ArrayList<>();
  				
  				datos.add(dato);
  				retorno.put(rs.getString(1),datos);
  			}
  			else
  			{
  				retorno.get(rs.getString(1)).add(dato);
  			}
  		}
  		
  	
  		closeFullConnection(con, rs, s);
      
  }
  // Handle any errors that may have occurred.
  catch (Exception e) {
      e.printStackTrace();
  }    
 
  return retorno;
}


	public static List<ArticuloConteo> darArticuloConteo(String query, int idTienda, int idEmpresa) 
	{
		List<ArticuloConteo> retorno = new ArrayList<>(); 
		String connectionUrl = DarConexionTienda(idTienda, idEmpresa);
	        // Declare the JDBC objects.
	        try 
	        {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
	            Statement s = con.createStatement();
		  		ResultSet rs = s.executeQuery (query);
		  		
		  		while (rs.next())
		  		{   
		  			ArticuloConteo a = new ArticuloConteo(rs.getString(2), rs.getInt(1),rs.getString(3));
		  			retorno.add(a);
		  		}
		  		
		  	
		  		closeFullConnection(con, rs, s);
	          
	      }
	      // Handle any errors that may have occurred.
	      catch (Exception e) {
	          e.printStackTrace();
	      }
	     
	      return retorno;
	}
	
	public static List<DataIDDescripcion> darStockArtTienda(String q, int idTienda, int idEmpresa) 
	{
		List<DataIDDescripcion> retorno = new ArrayList<>();
		String connectionUrl = DarConexionTienda(idTienda,idEmpresa);
        // Declare the JDBC objects.
        try 
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl,"sa","sqlstadium");
            Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (q);
	  		
	  		while (rs.next())
	  		{   
	  			retorno.add(new DataIDDescripcion(rs.getInt(1),rs.getString(2),rs.getString(3)));
	  		}
	  		
	  	
	  		closeFullConnection(con, rs, s);
          
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
      return retorno;
	}
	
	
	public static List<DataDescDescripcion> darDeposMayoristaForus(String q, int idEmpresa) 
	{
		List<DataDescDescripcion> retorno = new ArrayList<>();
		try
		{
			darConexion(idEmpresa);
            Statement s = con.createStatement();
	  		ResultSet rs = s.executeQuery (q);
	  		
	  		while (rs.next())
	  		{   
	  			retorno.add(new DataDescDescripcion(rs.getString(1),rs.getString(2)));
	  		}
	  	
	  		closeFullConnection(con, rs, s);
          
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
      return retorno;
	}
	
	public static int darPesoPedido(String consulta)
	{
		 
		// Create a variable for the connection string.
	 String connectionUrl ="jdbc:mysql://168.138.133.144:3306/encuentra_api";
	    // Declare the JDBC objects.
	    int salida = 0;
	    try 
	    {
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	        con = DriverManager.getConnection(connectionUrl+"?autoReconnect=true","root","@200SRL");
	        Statement s = con.createStatement();
			
			ResultSet rs = s.executeQuery(consulta); 
			
	  		while (rs.next())
	  		{  
	  			salida = rs.getInt(1);
	  			
	  		} 
	  		closeFullConnection(con, rs, s);
	  		
	    }
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    }
	 
	  return salida;
	  
	}

}