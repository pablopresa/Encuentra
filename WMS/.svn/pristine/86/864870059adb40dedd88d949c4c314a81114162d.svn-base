package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class _EncuentraPersistirHardcoded {

	private static Connection connection = null;

	private static Connection connect() throws Exception,
			InstantiationException, IllegalAccessException {

		Connection conn = null;
		//PropertiesHelper pH=new PropertiesHelper("conexion");
		//pH.loadProperties();
		String puerto = "3306";
		String bd = "encuentra";
		String usuario = "root";
		String password = "Std";
		String servidor = "10.108.0.3";
		String prefijoURL="jdbc:mysql://";
		String url = prefijoURL + servidor + ":" + puerto + "/" + bd;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, usuario, password);

		} catch (Exception e) {
			System.out.println("Error al abrir la conexión");
		}

		return conn;
	}

	public static Connection getConnection() throws Exception {
		if (connection == null) {
			connection = connect();
		}
		return connection;
	}

	public static void desconectar() throws Exception {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new Exception("Error al cerrar la Base de Datos");
		}
	}

	public static boolean persistir(String consulta) throws Exception {
		boolean pude = false;
		try {
			PreparedStatement pstmt = connection.prepareStatement(consulta);
			pstmt.executeUpdate();
			//pstmt.close();
			//coment
			pude = true;

		} catch (Exception e)
		{
			pude = false;
			System.out.println(consulta);
			e.printStackTrace();
			return pude;
		}

		return pude;
	}
	public static int persistirDarUltimo(String consulta, String tabla, String key) throws Exception 
	{
		_EncuentraConexion econ = new _EncuentraConexion();
		int retorno  = 0;
		try {
			PreparedStatement pstmt = connection.prepareStatement(consulta);
			pstmt.executeUpdate();
			//pstmt.close();
			//coment
			Connection cone;
			cone = econ.getConnection();
			boolean cerrarConexion = false;
			retorno = econ.UltimoId("select max("+key+") from "+tabla,cerrarConexion);

		} catch (Exception e) {
			
			e.printStackTrace();
			return retorno;
		}

		return retorno;
	}

}
