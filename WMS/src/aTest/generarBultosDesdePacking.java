package aTest;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.itextpdf.text.DocumentException;

import beans.Fecha;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DepositoAdmin;
import beans.encuentra.IPrint;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import helper.PropertiesHelper;
import logica.Logica;
import logica.Utilidades;
import persistencia._EncuentraPersistir;
import web.expedicion._EncuentraAccionEnvio;

public class generarBultosDesdePacking 
{
	
	
	public static void main(String[] args) throws Exception 
	{	
		Connection conn = null;
		PropertiesHelper pH=new PropertiesHelper("conexion");
		pH.loadProperties();
		String puerto = pH.getValue("puerto");
		String bd = "encuentra";
		String usuario = pH.getValue("usuario");
		String password = pH.getValue("password");
		String servidor = pH.getValue("servidor");
		String prefijoURL=pH.getValue("prefijoURL");
		String url = prefijoURL + servidor + ":" + puerto + "/" + bd;
		
		
		Logica logica = new Logica();
		Utilidades util = new Utilidades();
		int idRecepcion = -300;
		int idEmpresa = 5;
		
		List<bulto> bultos = new LinkedList<bulto>(); 
		

		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url +"?autoReconnect=true", usuario, password);

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
		
		
		List<DataLineaBulto> lista = new ArrayList<>();
		String consulta = "select * from aux_ojos____";
		
		Hashtable<String, Integer> contadorArticulos = new Hashtable<>();
		
		try 
		{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataLineaBulto d = new DataLineaBulto(rs.getString(1),rs.getString(2),rs.getString(5),rs.getInt(3),rs.getInt(4));
				lista.add(d);
				
			}

			desconectar(rs,null, s,conn);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		Hashtable<String, List<String>> bultosEnOjos = new Hashtable<String, List<String>>();
		Hashtable<String, List<String>> bultosEnOjosBajar = new Hashtable<String, List<String>>();
		
		
		for (DataLineaBulto b : lista)//recorro lista de empaques por ojo 
		{
			int numerador = 1;
			for (int i = 0; i < b.getCantidad_de_bultos(); i++)//por cada bulto creo uno nuevo 
			{
				
				if(contadorArticulos.containsKey(b.getIdArticulo())) 
				{
					numerador = contadorArticulos.get(b.getIdArticulo());
					numerador++;
				}
				
				String codigo = b.getIdBulto()+"_"+b.getIdArticulo()+"_"+numerador;
				bulto bul = new bulto(codigo, "Estiba de articulo "+b.getIdArticulo(), true, 0, 0, 0, 0.0, false, "", 1119,"1",idEmpresa);
				
				bultoContenido bc = new bultoContenido(b.getIdArticulo(), b.getEmpaque());
				bul.Agregar_A_Bulto_NO_persist(bc); 
				List<bultoContenido> listaContenido = new ArrayList<>();
				listaContenido.add(bc);
				bul.setContenidoList(listaContenido);
				
				bultos.add(bul);
				contadorArticulos.put(b.getIdArticulo(),numerador);
				
				if(bultosEnOjos.containsKey(b.getIdOjo()))
				{
					bultosEnOjos.get(b.getIdOjo()).add(bul.getIdBulto());
					bultosEnOjosBajar.get(b.getIdOjo()).add(b.getIdBulto());
				}
				else
				{
					List<String> bultosbajar = new ArrayList<>();
					List<String> bultosSubir = new ArrayList<>();
					
					bultosbajar.add(b.getIdBulto());
					bultosSubir.add(bul.getIdBulto());
					
					bultosEnOjos.put(b.getIdOjo(), bultosSubir);
					bultosEnOjosBajar.put(b.getIdOjo(), bultosbajar);
					
					
				}
				
				
				
				
				
			}
		}
		
		
		bulto bu = new bulto();
		bu.crearBultos(idRecepcion, bultos, idEmpresa);
		
	    Enumeration<String> enumeration = bultosEnOjosBajar.keys();
	    
        // iterate using enumeration object
	    List<String> consultas = new ArrayList<>(); 
        while(enumeration.hasMoreElements()) 
        {
        	
        	String qOT = " delete FROM ojostienenarticulos WHERE idArticulo IN (";
        	String qAR = " DELETE FROM articulos WHERE idArticulo IN(";
        	String bMO = " DELETE FROM bulto_contenido_movimiento WHERE idBulto IN(";
        	String bCO = " DELETE FROM bulto_contenido WHERE idBulto IN(";
        	String bUL = " DELETE FROM bulto WHERE idBulto IN (";
            String ojo = enumeration.nextElement();
            List<String> bultosbajar = bultosEnOjosBajar.get(ojo);
            String inns = "";
            boolean pri=true;
            for (String ar : bultosbajar) 
            {
				if(pri)
				{
					pri=false;
					inns+="'"+ar+"'";
				}
				else
				{
					inns+=",'"+ar+"'";
				}
			}
            
            String qOT2 = ") AND idOJo='"+ojo+"' AND idEmpresa=5; ";
            String qAR2 = ")  AND idEmpresa=5; ";
            String bCO2 = ") AND idEmpresa=5; ";
            String bMO2 = ") AND idEmpresa=5; ";
            String bUL2 = ")  AND idEmpresa=5; ";
            
            
            consultas.add(qOT+inns+qOT2);
            consultas.add(qAR+inns+qAR2);
            consultas.add(bMO+inns+bMO2);
            consultas.add(bCO+inns+bCO2);
            consultas.add(bUL+inns+bUL2);
            
            
            List<String> bultosSubir = bultosEnOjos.get(ojo);
            for (String b : bultosSubir) 
            {
            	String aADD = " INSERT INTO ojostienenarticulos  (idOjo, idArticulo, Cantidad, usuarioUpdate,IdEmpresa) VALUES ('"+ojo+"','"+b+"',1,1119,"+idEmpresa+"); ";
            	String qMov = "insert INTO movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,IdTipo,IdEmpresa) VALUES ('"+b+"','0','"+ojo+"',1,1119,'ADD',"+idEmpresa+");";
            	
            	consultas.add(aADD);
            	consultas.add(qMov);
			}
          
        }
        
        
        for (String string : consultas) 
        {
			System.out.println(string);
		}
        _EncuentraPersistir ep = new _EncuentraPersistir();
        ep.persistirTransacciones(consultas);
		
		
		/*
		 * 
		 * 
		 * 
		 * //CON LA INFO DEL PACKING VOY A CREAR LOS BULTOS String[] indices =
		 * util.auxIndices(cantidadBultosRecepcion(listaPacking)); int numeroIndice = 0;
		 * for(VORecepcionSinOrden linea: listaPacking) { for(int i=0;
		 * i<linea.getCantidadBultos(); i++) { int posicion = 0; int equipo = 2; Fecha
		 * fecha = new Fecha(0,0,0); fecha.now(); String id=
		 * "R"+indices[numeroIndice]+equipo+posicion+fecha.darFechaString(); bulto bul =
		 * new bulto(id, "Recepcion "+idRecepcion, true, 0, 0, 0, 0.0, false, "",
		 * 1119,"1",idEmpresa); List<bultoContenido> listaContenido = new
		 * ArrayList<bultoContenido>(); bultoContenido bc = new
		 * bultoContenido(linea.getIdArticulo(),linea.getCantidadPacking(), idRecepcion,
		 * 1119); bul.Agregar_A_Bulto_NO_persist(bc); listaContenido.add(bc);
		 * bul.setContenidoList(listaContenido);
		 * 
		 * bultos.add(bul); System.out.println(linea); numeroIndice++; } }
		 * 
		 * //prueba crear bulto bulto bul = new bulto(); bul.crearBultos(idRecepcion,
		 * bultos, idEmpresa);
		 * 
		 * 
		 * //PRUEBA IMPRIMIR ETIQUETAS IPrint ip = new IPrint(); Hashtable<String,
		 * Integer> totalesPorMark = new Hashtable<>();
		 * 
		 * //obtengo los totales por mark for(VORecepcionSinOrden linea: listaPacking) {
		 * totalesPorMark.put(linea.getIdArticulo(), linea.getCantidadBultos()); }
		 * 
		 * ip.ImprimirEtiquetasBultosRSinOC(bultos,
		 * "Recepcion_"+idRecepcion+"_"+idEmpresa, totalesPorMark);
		 */
	
	
	}
	
	public static int cantidadBultosRecepcion(List<VORecepcionSinOrden> listaPacking)
	{
		int total = 0;
		for(VORecepcionSinOrden linea: listaPacking)
		{
			total+=linea.getCantidadBultos();
		}
		return total;
	}
	
	
	private static void desconectar(ResultSet rs,PreparedStatement ps,Statement st, Connection connection)  
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
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) { e.printStackTrace();/* ignored */}
		}


	}
}
class DataLineaBulto
{
	private String idOjo, idArticulo,idBulto;
	private int empaque, cantidad_de_bultos;
	public String getIdOjo() {
		return idOjo;
	}
	public void setIdOjo(String idOjo) {
		this.idOjo = idOjo;
	}
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public String getIdBulto() {
		return idBulto;
	}
	public void setIdBulto(String idBulto) {
		this.idBulto = idBulto;
	}
	public int getEmpaque() {
		return empaque;
	}
	public void setEmpaque(int empaque) {
		this.empaque = empaque;
	}
	public int getCantidad_de_bultos() {
		return cantidad_de_bultos;
	}
	public void setCantidad_de_bultos(int cantidad_de_bultos) {
		this.cantidad_de_bultos = cantidad_de_bultos;
	}
	public DataLineaBulto(String idOjo, String idArticulo, String idBulto, int empaque, int cantidad_de_bultos) {
		this.idOjo = idOjo;
		this.idArticulo = idArticulo;
		this.idBulto = idBulto;
		this.empaque = empaque;
		this.cantidad_de_bultos = cantidad_de_bultos;
	}
	
	
	
	
}
