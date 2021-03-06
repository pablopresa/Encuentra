package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Usuario;
import beans.api.json.RetornoPedido;
import beans.helper.PropertiesHelper;

public class _EncuentraConexionAPI
{

	private static Connection connection = null;

	private static Connection connect(String bd) throws Exception,InstantiationException, IllegalAccessException 
	{
		
		Connection conn = null;
		PropertiesHelper pH=new PropertiesHelper("conexion");
		pH.loadProperties();
		String puerto = pH.getValue("puerto");
		//String bd = pH.getValue("bd");
		String usuario = pH.getValue("usuario");
		String password = pH.getValue("password");
		String servidor = pH.getValue("servidor");
		String prefijoURL=pH.getValue("prefijoURL");
		String url = prefijoURL + servidor + ":" + puerto + "/" + bd;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, usuario, password);

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

	public static Connection getConnection(String bd) throws Exception {
		if (connection == null) {
			connection = connect(bd);
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
	

	public static RetornoPedido darArticulosOjos(String consulta,String articulo) 
	{
		int largo = 0;
		try 
		{
			RetornoPedido rp = new RetornoPedido();
			rp.setPedido(articulo);
			boolean ready = false;
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			List<String> ubis = new ArrayList<>();
			String cliente = "";
		
			
			while (rs.next()) 
			{
				largo++;
				ubis.add(rs.getString(3));
				cliente = rs.getString(1);
				ready=true;
			}
			
			if(ready)
			{
				rp.setDisponible_Retiro("true");
			}
			else
			{
				rp.setDisponible_Retiro("false");
			}
			
			String[] arreglo = new String[largo];
			for (int i = 0; i < largo; i++) 
			{
				arreglo[i]=ubis.get(i);
			}
			rp.setCliente(cliente);
			rp.setUbicaciones(arreglo);
			
		
			rs.close();
			return rp;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;

	
	}
	
	public static int persistir(String consulta) throws Exception {
		
		int retorno=0;		
		try {			
			PreparedStatement pstmt = connection.prepareStatement(consulta);
			
			//pstmt.close();
			//coment
			retorno = pstmt.executeUpdate();

		} catch (Exception e)
		{
			retorno=-2;
			//System.out.println(consulta);
			e.printStackTrace();
			if(e.getMessage().contains("Duplicate entry"))
			{
				return 0;
			}
			return retorno;
		}

		return retorno;
	}
	
	public static Usuario login(String consulta) 
	{
	Usuario u = new Usuario();
	try 
	{
		Statement s = connection.createStatement();
		System.out.println(consulta);
		ResultSet rs = s.executeQuery(consulta);
		
		while (rs.next()) 
		{

			int numero = rs.getInt(1);
			String nombre = rs.getString(4) +" "+rs.getString(5);
			int perfil = rs.getInt(6);
			String nombreU = rs.getString(3);

			u.setNumero(numero);
			u.setNick(nombre);
			u.setPerfil(perfil);
			u.setNombre(nombreU);
			u.setMail(rs.getString(7));
			//u.setIdGrupo(rs.getInt(8));

		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return u;
	}
	
	
	public  int UltimoId(String consulta) 
	{
		int ultimo =0;

		try 
		{
			Statement s = connection.createStatement();
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

	
	public int persistirDarUltimo(String consulta, String tabla, String key, int idEmpresa) throws Exception 
	{
		
		
		int retorno=0;		
		try {			
			PreparedStatement pstmt = connection.prepareStatement(consulta);
			
			//pstmt.close();
			//coment
			pstmt.executeUpdate();

		} catch (Exception e)
		{
			return 0;
			
		}

		retorno = UltimoId("select max("+key+") from "+tabla+ " where IdEmpresa="+idEmpresa);
		
		return retorno;
	}

	
}
