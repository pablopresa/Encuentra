import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Usuario;
import helper.PropertiesHelper;
import persistencia._EncuentraConexion;

public class MigrarDepositos 
{

	private static  Connection cone = null;
	
	public static void main(String[] args) 
	{
		int idEmpresa = 1;
		try 
		{
		
			cone = getConnection();
			//String consulta = "SELECT `idDeposito`, `Nombre`, `Direccion`, `Tipo`, `Login`, `IdDepositoSAP`, `customShipping`, `encuentraShipping` FROM encuentra_v2.depositos WHERE idDeposito NOT IN (1,0,71,99) AND customshipping=1";
			//para los que faltaron
			String consulta = "SELECT `idDeposito`, `Nombre`, `Direccion`, `Tipo`, `Login`, `IdDepositoSAP`, `customShipping`, `encuentraShipping` from encuentra_v2.depositos WHERE idDeposito =911";
			
			Statement s = cone.createStatement();
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				int idDeposito = rs.getInt(1);
				String  descripcion = rs.getString(2);
				persistir(""
						+ "INSERT INTO `depositos` (`idDeposito`, `Nombre`, `Direccion`, `Tipo`, `Login`, `IdDepositoSAP`, `customShipping`, `encuentraShipping`, `IdEmpresa`) \r\n" + 
						"VALUES ("+rs.getInt(1)+",'"+rs.getString(2)+"' , '"+rs.getString(3)+"', '"+rs.getInt(4)+"', '"+rs.getString(5)+"', '"+rs.getString(6)+"', '1', '1', "+idEmpresa+");\r\n" + 
						""
						+ "");
				
				
				String insertEstanteria = ""
						+ "INSERT INTO ESTANTERIAS (descripcion,numpiso,tiposector,cantidadestantes,cantidadmodulos,color,sector,iddeposito,idempresa,iduso) \r\n" + 
						"VALUES(CONCAT ('Expedicion ', '"+descripcion+"' ),1,12,1,1,'green',0,99,"+idEmpresa+",4);"
						+ "";
				
				int idEstanteria = persistirDarUltimo(insertEstanteria, "ESTANTERIAS", "idEstanteria", idEmpresa);
				
				persistir("INSERT INTO ojos (idOjo,idEstanteria,idModulo,idEstante,idRecorrido,area,Ancho,Alto,Profindidad,IdEmpresa)\r\n" + 
						"VALUES ( CONCAT('"+idDeposito+"','E'),"+idEstanteria+",1,1,999999,0,0,0,0,"+idEmpresa+");");
				
				
				persistir("UPDATE depositos SET estanteriaExpedicion = "+idEstanteria+" WHERE idDeposito = "+idDeposito+";");
				
				
				insertEstanteria = ""
						+ "INSERT INTO ESTANTERIAS (descripcion,numpiso,tiposector,cantidadestantes,cantidadmodulos,color,sector,iddeposito,idempresa,iduso) \r\n" + 
						"VALUES(CONCAT ('Picking ', '"+descripcion+"' ),1,12,1,1,'green',0,99,"+idEmpresa+",5);"
						+ "";
				
				idEstanteria = persistirDarUltimo(insertEstanteria, "ESTANTERIAS", "idEstanteria", idEmpresa);
				
				persistir("INSERT INTO ojos (idOjo,idEstanteria,idModulo,idEstante,idRecorrido,area,Ancho,Alto,Profindidad,IdEmpresa)\r\n" + 
						"VALUES ( CONCAT('"+idDeposito+"','P'),"+idEstanteria+",1,1,999999,0,0,0,0,"+idEmpresa+");");
				
				persistir("UPDATE depositos SET estanteriaPicking = "+idEstanteria+" WHERE idDeposito = "+idDeposito+";");
				
			}
			

			
			cone = null;
			

		} catch (Exception e) 
		{

			e.printStackTrace();

		}

	}
	
	public static int persistirDarUltimo(String consulta, String tabla, String key, int idEmpresa) throws Exception 
	{
		cone = getConnection();
		
		int retorno  = 0;
		PreparedStatement pstmt = null;
		try 
		{
		    pstmt = cone.prepareStatement(consulta);
			pstmt.executeUpdate();
			retorno = UltimoId("select max("+key+") from "+tabla+ " where IdEmpresa="+idEmpresa,false);

		} catch (Exception e) 
		{
			
			e.printStackTrace();
			
			return retorno;
		}
		return retorno;
	}
	
		public static int UltimoId(String consulta, boolean desconectar) 
		{
			int ultimo =0;
		
		try 
		{
			Statement s = cone.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				ultimo = rs.getInt(1);
				
			}
			
			rs.close();
			

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return ultimo;
	}
		
	private static boolean persistir(String consulta) throws Exception 
	{
		cone = getConnection();
		
		boolean pude = false;	
		PreparedStatement pstmt = null;
		try {
			pstmt = cone.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+consulta);
			pstmt.executeUpdate();
			pude = true;
			

		} catch (Exception e )
		{
			pude = false;
			System.out.println("persistiendo: "+consulta);
			System.out.println("error");
			e.printStackTrace();
			
			return pude;
		}
		
		return pude;
	}
	
	private static  Connection connect() throws Exception,InstantiationException, IllegalAccessException 
	{
		
		Connection conn = null;
		PropertiesHelper pH=new PropertiesHelper("conexion");
		pH.loadProperties();
		String puerto = pH.getValue("puerto");
		String bd = pH.getValue("bd");
		String usuario = pH.getValue("usuario");
		String password = pH.getValue("password");
		String servidor = pH.getValue("servidor");
		String prefijoURL=pH.getValue("prefijoURL");
		String url = prefijoURL + servidor + ":" + puerto + "/" + bd;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url +"?allowMultiQueries=true&autoReconnect=true", usuario, password);

		} catch (SQLException ex) {
			throw new Exception(
					"Hubo un problema al intentar conectarse con la base de datos "
							+ ex.getMessage());
		} catch (ClassNotFoundException ex) {
			throw new Exception(
					"Hubo un problema al intentar conectarse con la base de datos2 "
							+ ex.getMessage());
		}
		return conn;
	}
	
	private static  Connection getConnection() throws Exception 
	{
		if (cone == null) {
			cone = connect();
		}
		return cone;
	}

}
