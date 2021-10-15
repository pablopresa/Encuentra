package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import helper.PropertiesHelper;

public class Conexion 
{
	
	private  Connection connection = null;
	public  Conexion()
	{
		try {
			this.connection = getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private  Connection connect() throws Exception,InstantiationException, IllegalAccessException 
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

		} catch (SQLException ex) 
		{
			ex.printStackTrace();
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

	public  Connection getConnection() throws Exception {
		if (connection == null) {
			connection = connect();
		}
		return connection;
	}

	public  void desconectar(ResultSet rs,PreparedStatement ps,Statement st) throws Exception 
	{
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {  e.printStackTrace(); /* ignored */}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) { e.printStackTrace();/* ignored */}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {  e.printStackTrace(); /* ignored */}
		}
		if (this.connection != null) {
			try {
				this.connection.close();
				this.connection = null;
			} catch (SQLException e) { e.printStackTrace();/* ignored */}
		}


	}
	
	
	

}
