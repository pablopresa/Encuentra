package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import beans.bultoContenido;
import beans.elementoPicking;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataPicking;
import beans.encuentra.MovimientoAlmacen;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;

public class _EncuentraPersistir {

	private Connection connection = null;
	public _EncuentraPersistir(){};

	private Connection connect() throws Exception,
			InstantiationException, IllegalAccessException {

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
			conn = DriverManager.getConnection(url+"?allowMultiQueries=true&autoReconnect=true", usuario, password);

		} catch (Exception e) {
			System.out.println("Error al abrir la conexión");
		}

		return conn;
	}

	public Connection getConnection() throws Exception {
		if (connection == null || connection.isClosed()) {
			connection = connect();
		}
		return connection;
	}
	


	private void desconectar(ResultSet rs,PreparedStatement ps,Statement st,Connection con) throws Exception 
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
	    if (con != null) {
	        try {
	        	con.close();
	        	con = null;
	        	
	        } catch (SQLException e) { e.printStackTrace();/* ignored */}
	    }
		
		
	}
	private void desconectar(ResultSet rs,Statement ps,Statement st,Connection con) throws Exception 
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
	    if (con != null) {
	        try {
	        	con.close();
	        	con = null;
	        	
	        } catch (SQLException e) { e.printStackTrace();/* ignored */}
	    }
		
		
	}

	public boolean persistir(String consulta) throws Exception 
	{
		connection = getConnection();
		
		boolean pude = false;	
		PreparedStatement pstmt = null;
		try {
 			pstmt = connection.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+consulta);
			pstmt.executeUpdate();
			//pstmt.close();
			//coment
			pude = true;
			desconectar(null,pstmt,null, connection);

		} catch (Exception e )
		{
			pude = false;
			System.out.println("persistiendo: "+consulta);
			System.out.println("error");
			e.printStackTrace();
			desconectar(null,pstmt,null, connection);
			return pude;
		}
		
		return pude;
	}
	
	
	public boolean persistirL(List<String> queries) throws Exception 
	{
		connection = getConnection();
		
		boolean pude = false;	
		PreparedStatement pstmt = null;
		String consulta ="";
		try 
		{
			for (String query : queries) 
			{
				consulta = query;
				System.out.println(query);
	 			pstmt = connection.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+consulta);
				pstmt.executeUpdate();
			}
			
			pude = true;
			desconectar(null,pstmt,null, connection);

		} catch (Exception e )
		{
			pude = false;
			System.out.println("persistiendo: "+consulta);
			System.out.println("error");
			e.printStackTrace();
			desconectar(null,pstmt,null, connection);
			return pude;
		}
		
		return pude;
	}
	
	public int persistirUpt(String consulta) throws Exception 
	{
		connection = getConnection();
		
		int salida = 0;	
		Statement st = null;
		try {
			st = connection.createStatement();
			salida = st.executeUpdate(consulta);
			//pstmt.close();
			//coment
			desconectar(null,null,st, connection);

		} catch (Exception e )
		{
			System.out.println("persistiendo: "+consulta);
			desconectar(null,null,st, connection);
			return 0;
		}
		
		return salida;
	}
	
	
	
	
	
	
	
	public boolean persistirBatch(String consulta) throws Exception 
	{
		connection = getConnection();
		boolean pude = false;
		System.out.println(consulta);
		Statement s = null;
		try 
		{
			int counter = 0;
			s = connection.createStatement();
			boolean on = false;
			s.addBatch(consulta);
			s.executeBatch();
			s.close();
			pude = true;
			desconectar(null,null,s,connection);
		} 
		catch (Exception e) 
		{
			if(e.getMessage().contains("empty"))
			{
				pude = true;
			}
			else
			{
				pude = false;
			}
			e.printStackTrace();
			desconectar(null,null,s,connection);
			return pude;
		}
		
		return pude;
	}
	
	
	public boolean persistirIdDesc(List<DataIDDescripcion> lista, String tabla, boolean borrar, int idEmpresa) throws Exception 
	{
		
		System.out.println("Persistiendo "+ tabla);
		connection = getConnection();
		PreparedStatement pstmt = null;
		
		StringBuilder values = new StringBuilder();
		String insert = "INSERT ignore INTO "+tabla+" (`Id`, `Descripcion`,idEmpresa) VALUES ";
		List<String> inserts = new ArrayList<>();
		int contador = 0;
		boolean pri = true;
		for (DataIDDescripcion d : lista) 
		{
			if(pri)
			{
				pri = false;
				values.append (" ("+d.getId()+", '"+d.getDescripcion()+"',"+idEmpresa+")");
			}
			else
			{
				values.append (",("+d.getId()+", '"+d.getDescripcion()+"',"+idEmpresa+")");
			}
			
			contador++;
			
			if(contador == 2000)
			{
				inserts.add(insert+values.toString());
				pri=true;
				values = new StringBuilder();
				contador=0;
			}
		}
		
		inserts.add(insert+values.toString());

		if(borrar)
		{
			try {
				PreparedStatement pstmt0 = connection.prepareStatement("delete from "+tabla+" where idEmpresa="+idEmpresa );
				pstmt0.executeUpdate();
				pstmt0.close();
			} catch (Exception e) {
				System.out.println("****************problema de foreign**************");
			}
			
		}
		boolean pude = false;
		
		try 
		{
			for (String i : inserts) 
			{
				if(tabla.equals("art_genero"))
				{
					System.out.println(i);
				}
				pstmt = connection.prepareStatement(i);
				pstmt.executeUpdate();
			}
			
			//pstmt.close();
			//coment
			pude = true;

		} 
		catch (Exception e)
		{
			pude = false;
		}
		
		desconectar(null,pstmt,null, connection);
		return true;
	}
	
	public boolean persistirDescDesc(List<DataDescDescripcion> lista, String tabla, boolean borrar, int idEmpresa) throws Exception 
	{
		return persistirDescDesc(lista, tabla, borrar, idEmpresa, "Id", "Descripcion");
	}
	
	public boolean persistirDescDesc(List<DataDescDescripcion> lista, String tabla, boolean borrar, int idEmpresa, String id, String descripcion) throws Exception 
	{
		connection = getConnection();
		System.out.println("Persistiendo "+ tabla);
		PreparedStatement pstmt = null;
		StringBuilder values = new StringBuilder();
		String insert = "INSERT INTO "+ tabla +" (`"+id+"`, `"+descripcion+"`, idEmpresa) VALUES ";
		List<String> inserts = new ArrayList<>();
		int contador = 0;
		boolean pri = true;
		for (DataDescDescripcion d : lista) 
		{
			if(pri)
			{
				pri = false;
				values.append (" ('"+d.getId()+"', '"+d.getDescripcion()+"',"+idEmpresa+")");
			}
			else
			{
				values.append (",('"+d.getId()+"', '"+d.getDescripcion()+"',"+idEmpresa+")");
			}
			contador++;
			
			if(contador==1000)
			{
				String cola = " ON DUPLICATE KEY UPDATE "+descripcion+"= VALUES("+descripcion+") ";
				inserts.add(insert+values.toString() +cola);
				pri=true;
				values = new StringBuilder();
				contador=0;
			}
		}
		
		String cola = " ON DUPLICATE KEY UPDATE "+descripcion+"= VALUES("+descripcion+") ";
		inserts.add(insert+values.toString() +cola);

		boolean pude;
		try 
		{
			for (String i : inserts) 
			{
				System.out.println(i);
				pstmt = connection.prepareStatement(i);
				pstmt.executeUpdate();
			}
			
			//pstmt.close();
			//coment
			pude = true;

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			pude = false;
		}
		

		
		desconectar(null,pstmt,null, connection);
		return true;
	}
	
	public boolean persistirIdIdDescripcion(List<DataIDDescripcion> lista, String tabla, String columna1, String columna2, String columna3, int idEmpresa) throws Exception 
	{
		
		System.out.println("Persistiendo "+ tabla);
		connection = getConnection();
		PreparedStatement pstmt = null;
		
		String onDup ="";
		StringBuilder values = new StringBuilder();
		String insert = "INSERT ignore INTO "+tabla+" (`" + columna1 + "`, `" + columna2 + "`,`"+columna3+"`, idEmpresa) VALUES ";
		List<String> inserts = new ArrayList<>();
		int contador = 0;
		boolean pri = true;
		for (DataIDDescripcion d : lista) 
		{
			onDup= " on duplicate key update Descripcion = "+d.getDescripcion()+" ";
			if(pri) {
				pri = false;
				values.append (" ("+d.getId()+", "+d.getIdB()+", '"+d.getDescripcion()+"', "+idEmpresa+")");
			}
			else {
				values.append (",("+d.getId()+", "+d.getIdB()+", '"+d.getDescripcion()+"', "+idEmpresa+")");
			}
			
			contador++;
			
			if(contador == 2000)
			{
				inserts.add(insert+values.toString());
				pri=true;
				values = new StringBuilder();
				contador=0;
			}
		}
		
		inserts.add(insert + values.toString() + onDup);
		
		boolean pude = false;
		
		try 
		{
			for (String i : inserts) 
			{
				if(tabla.equals("art_genero")) {
					System.out.println(i);
				}
				pstmt = connection.prepareStatement(i);
				pstmt.executeUpdate();
			}
			
			//pstmt.close();
			//coment
			pude = true;

		} 
		catch (Exception e)
		{
			pude = false;
		}
		
		desconectar(null,pstmt,null, connection);
		return true;
	}
	
	
	
	public int persistirDarUltimo(String consulta, String tabla, String key, int idEmpresa) throws Exception 
	{
		connection = getConnection();
		_EncuentraConexion econ;
		int retorno  = 0;
		PreparedStatement pstmt = null;
		try {
		    pstmt = connection.prepareStatement(consulta);
			pstmt.executeUpdate();
			//pstmt.close();
			//coment
			econ = new _EncuentraConexion();
			Connection cone;
			cone = econ.getConnection();
			boolean cerrarConexion = false;
			retorno = econ.UltimoId("select max("+key+") from "+tabla+ " where IdEmpresa="+idEmpresa,cerrarConexion);

		} catch (Exception e) {
			
			e.printStackTrace();
			desconectar(null,pstmt,null, connection);
			return retorno;
		}

		
		return retorno;
	}

	
	public int persistirDarId(String consulta, String idDepoSap) throws Exception 
	{
		connection = getConnection();
		_EncuentraConexion econ = new _EncuentraConexion();
		int retorno  = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(consulta);
			pstmt.executeUpdate();
			//pstmt.close();
			//coment
			Connection cone;
			cone = econ.getConnection();
			retorno = econ.DarIdDeposito("select idDeposito from depositos where IdDepositoSAP = '"+idDepoSap+"'");

		} catch (Exception e) {
			desconectar(null,pstmt,null, connection);
			e.printStackTrace();
			return retorno;
		}
		
		desconectar(null,pstmt,null, connection);
		return retorno;
	}

	
	//NICO
	public String Consulta(String consulta) throws Exception {
		connection = getConnection();
		String resp="";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery(consulta);
			while (rs.next()) 
			{
				resp = rs.getString(1);
			}
			rs.close();
			
			//pstmt.close();
			//coment
			

		} catch (Exception e)
		{
			desconectar(null,pstmt,null, connection);
			//System.out.println(consulta);
			e.printStackTrace();
			return resp;
		}
		desconectar(null,pstmt,null, connection);
		return resp;
	}
		
	/////UPDATE ARTICULOS ECOMMERCE////
	public void updateEcommerceArticuloReq(Long idPedido, String newArt, String oldArt, int dep, int idEmpresa, Connection trans, int depCentral, int depWeb, int solicitud) 
	{
		try {
			String query = "";
			String queryW = "where idEmpresa="+idEmpresa+" AND idPedido="+idPedido+" and idArticulo='"+oldArt+"' "
					+ "and Deposito="+dep;
			if(idEmpresa == 1) {
				query = "delete from ecommerce_pedido_articulos_req "+queryW;
			}
			else {
				query = "update ecommerce_pedido_articulos_req set idarticulo='"+newArt+"',"
						+ " subestado=0, CantidadProcesasa=0, Confirmado=0, cantConfirmada=0, fechaImpreso= CURRENT_TIMESTAMP(), "
						+ "fechaConfirmado=CURRENT_TIMESTAMP(), RTimeStamp=CURRENT_TIMESTAMP(), CTimeStamp=CURRENT_TIMESTAMP(), "
						+ "PTimeStamp=CURRENT_TIMESTAMP(), EnvioTimeStamp=CURRENT_TIMESTAMP(), DespachoTimeStamp=CURRENT_TIMESTAMP(), "
						+ "RetiroTimeStamp=CURRENT_TIMESTAMP() "
						+ queryW;	
			}
					
				
			PreparedStatement pstmt = null;
			
			pstmt = trans.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+query);
			pstmt.executeUpdate();
					
			switch (idEmpresa) {
			case 1:
				//PIDO LOS PARES A DEPOSITO
				break;
			case 2:
				
				break;
			case 4:
				if(dep == depCentral) {
					updateStatusReposicionArticulo(idPedido,oldArt,dep,depWeb,solicitud,idEmpresa,4,trans);
				}
				
				break;
				
			default:
				break;
			}
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
			if(trans!=null){                    
                try {
					trans.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
	}
	
	public void updateStatusReposicionArticulo(Long idPedido, String art, int dep, int destino, int solicitud, int idEmpresa, int status, Connection trans) 
	{
		try {
			
			String query = "update reposicion_articulos set estado="+status+" where idEmpresa="+idEmpresa+" AND seccion="+idPedido+" and idArticulo='"+art+"' "
					+ "and origen="+dep+" and destino="+destino+" and idSolicitudTraslado="+solicitud;							
				
			PreparedStatement pstmt = null;
			
			pstmt = trans.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+query);
			pstmt.executeUpdate();					
			
		} catch (Exception e) {
			e.printStackTrace();
			if(trans!=null){                    
                try {
					trans.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
	}
	
	public void updateEcommerceArticulo(Long idPedido, String newArt, String oldArt, int dep, int idEmpresa, int depCentral, int depWeb, int solicitud) 
	{
		PreparedStatement pstmt = null;
		try {
			String query = "update ecommerce_pedido_articulos set idArticulo='"+newArt+"', modificado=1 " + 
					"where idEmpresa="+idEmpresa+" AND idPedido="+idPedido+" and idArticulo='"+oldArt+"'";			
			
			connection = getConnection();
			connection.setAutoCommit(false); 
			
			
			pstmt = connection.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+query);
			pstmt.executeUpdate();
			
			updateEcommerceArticuloReq(idPedido, newArt, oldArt, dep, idEmpresa, connection, depCentral, depWeb, solicitud);
			
			/*if(idEmpresa==1) {
				updateEcommerceVentaLinea();
			}*/
			
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(connection!=null){                    
                try {
                	connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
		finally {
			try {
				desconectar(null,pstmt,null, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateEcommerceVentaLinea() 
	{
		
	}
	
	/////FIN UPDATE ARTICULOS ECOMMERCE////
	
	public boolean AgregarABulto_AfectarRepo(bultoContenido bc, String idBulto,DataPicking art, int idEmpresa) 
	{
		boolean salida = false;
		Statement st = null;
		String insert="insert into bulto_contenido (idBulto,idArticulo,cantidad,picking,recepcion,usuario, idEmpresa) values "
				+ "('"+idBulto+"','"+bc.getIdArticulo()+"',"+bc.getCantidad()+","+bc.getPicking()+","+bc.getRecepcion()+","+
				bc.getUsuario()+","+idEmpresa+") on duplicate key update cantidad="+bc.getCantidad();		
		
		String updateRepo = "UPDATE `reposicion_articulos` SET Verif = Verif+"+art.getVerificada()+", verif_at=CURRENT_TIMESTAMP() "
				+ "WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+art.getIdPicking()+" AND `idArticulo`='"+art.getArticulo()+"' AND "
						+ "`Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+" "
								+ "and idSolicitudTraslado="+art.getSolicitud()+";";
		try {			
			connection = getConnection();
			connection.setAutoCommit(false); 
			
			int affected = 0;	
			
			
			st = connection.createStatement();
			affected = st.executeUpdate(insert);			
			
			if(affected < 1) {
				System.out.println("rollback bulto");
				System.out.println(insert);
				System.out.println(updateRepo);
				salida = false;
				connection.rollback();
			}
			else {
				if(art != null) {
					Statement st2 = connection.createStatement();
					affected = st2.executeUpdate(updateRepo);		
					
					if(affected < 1) {
						System.out.println("rollback repo");
						System.out.println(insert);
						System.out.println(updateRepo);
						salida = false;
						connection.rollback();
					}
					
				}
			}					
						
			connection.commit();
			salida = true;
			
			
		} catch (Exception e) {
			System.out.println("catch");
			System.out.println(insert);
			System.out.println(updateRepo);
			salida = false;
			e.printStackTrace();
			if(connection!=null){                    
                try {
                	connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
		finally {
			try {
				desconectar(null,null,st, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return salida;
	}
	
	public boolean AgregarABulto_AfectarRepo_BultoCerrado(List<bultoContenido> bc, String idBulto,DataPicking art, int idEmpresa) 
	{
		boolean salida = false;
		Statement st = null;
		String updateRepo = "";
		for (DataPicking articulo : art.getContenido()) {
			updateRepo += "UPDATE `reposicion_articulos` SET Verif = Verif+"+articulo.getContenidoQty()+", verif_at=CURRENT_TIMESTAMP() "
					+ "WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+articulo.getIdPicking()+" AND `idArticulo`='"+articulo.getArticulo()+"' AND "
							+ "`Origen`="+articulo.getOrigen().getId()+" AND `Destino`="+articulo.getDestino().getId()+" "
									+ "and idSolicitudTraslado="+articulo.getSolicitud()+"; ";
		}
		try {			
			connection = getConnection();
			connection.setAutoCommit(false); 
			
			int affected = 0;	
			
			
			if(art != null) {
			st = connection.createStatement();
				affected = st.executeUpdate(updateRepo);		
				
				if(affected < 1) {
					System.out.println("rollback repo");
					System.out.println(updateRepo);
					salida = false;
					connection.rollback();
				}
				
			}
								
					
			connection.commit();
			salida = true;
			
			
		} catch (Exception e) {
			System.out.println("catch");
			System.out.println(updateRepo);
			salida = false;
			e.printStackTrace();
			if(connection!=null){                    
                try {
                	connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
		finally {
			try {
				desconectar(null,null,st, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return salida;
	}
	
	
	
	
	
	
	
	public List<String> buscarContenidoBultos(String bp_reservado, String codigoOjo, int idEmpresa, List<DataLineaRepo> contenido) throws Exception 
	{
		connection = getConnection();
		List<String> bps = new ArrayList<>();
		List<String> bultos = new ArrayList<>();
		PreparedStatement pstmt = null;

		
		String query = "SELECT claves.idBulto\r\n" + 
				"from\r\n" + 
				"(\r\n" + 
				"    SELECT idBulto, idEmpresa, GROUP_CONCAT(clave ORDER BY clave) claveBulto FROM\r\n" + 
				"    (SELECT BC.idBulto, BC.idEmpresa, CONCAT (BC.idArticulo,'-',cantidad) AS clave from bulto_contenido BC ORDER BY BC.idArticulo) t0 group by idBulto \r\n" + 
				")\r\n" + 
				"AS claves\r\n" + 
				"INNER JOIN \r\n" + 
				"(\r\n" + 
				"    SELECT idBulto, idEmpresa, GROUP_CONCAT(clave ORDER BY clave) claveBulto FROM\r\n" + 
				"    (\r\n" + 
				"        SELECT BC.idBulto, BC.idEmpresa, CONCAT (BC.idArticulo,'-',cantidad) AS clave from bulto_contenido BC WHERE BC.idBulto =  '"+bp_reservado+"' ORDER BY BC.idArticulo\r\n" + 
				"    ) t0 group by idBulto \r\n" + 
				") clave\r\n" + 
				"ON clave.claveBulto = claves.claveBulto AND claves.idEmpresa = clave.idEmpresa\r\n" + 
				"INNER JOIN ojostienenarticulos ota ON ota.idArticulo = claves.idBulto AND ota.IdEmpresa = claves.idEmpresa AND ota.idOjo = '"+codigoOjo+"'\r\n" + 
				"WHERE claves.idEmpresa = "+idEmpresa+" AND claves.idBulto!='"+bp_reservado+"' limit 5";
		System.out.println(query);
		try {
			pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			String ids = "(";
			while(rs.next()) {
				String idBulto = rs.getString(1);
				bps.add(idBulto);
				ids += "'" + idBulto + "',";
			}
			if(bps.size() > 0) {
				ids = StringUtils.removeEnd(ids, ",");
				String query2 = "SELECT idbulto, idarticulo, cantidad, cantidadreservada FROM bulto_contenido WHERE idbulto IN "+ids+");";
				pstmt = connection.prepareStatement(query2);
				ResultSet rs2 = pstmt.executeQuery();
				String bultoActual = "";
				Hashtable<String, Hashtable<String, int[]>> bultosDevueltos = new Hashtable<String, Hashtable<String, int[]>>();
				Hashtable<String, int[]> contenidoBultoDevuelto = new Hashtable<String, int[]>();
				while(rs2.next()) {
					if(rs2.getString(1).equalsIgnoreCase(bultoActual)) {
						int[] datos = new int[2];
						datos[0] = rs2.getInt(3);
						datos[1] = rs2.getInt(4);
						contenidoBultoDevuelto.put(rs2.getString(2), datos);
					} else {
						if(contenidoBultoDevuelto.size() > 0) {
							bultosDevueltos.put(bultoActual, contenidoBultoDevuelto);
						}
						bultoActual = rs2.getString(1);
						contenidoBultoDevuelto = new Hashtable<String, int[]>();
						int[] datos = new int[2];
						datos[0] = rs2.getInt(3);
						datos[1] = rs2.getInt(4);
						contenidoBultoDevuelto.put(rs2.getString(2), datos);
					}
				}
				if(contenidoBultoDevuelto.size() > 0) {
					bultosDevueltos.put(bultoActual, contenidoBultoDevuelto);
				}
				
				
				
				List<String> claves = Collections.list(bultosDevueltos.keys());
				
				for (String bulto : claves) {
					int contador = 0;
					for (DataLineaRepo articuloPadre : contenido) {
						Hashtable<String, int[]> contenidoB = bultosDevueltos.get(bulto);
						int[] cantidades = contenidoB.get(articuloPadre.getIdArticulo()); // me trae en null
						System.out.println(articuloPadre.getIdArticulo());
						System.out.println(cantidades[0] + " - " + cantidades[1]);
						System.out.println(articuloPadre.getSolicitada());
						if(cantidades[0]-cantidades[1] >= articuloPadre.getSolicitada()) {
							contador++;
						}
					}
					if(contenido.size() == contador) {
						bultos.add(bulto);
					}
				}
				
			}
			
			desconectar(null,pstmt,null, connection);

		} catch (Exception e )
		{
			System.out.println("persistiendo: "+query);
			System.out.println("error");
			e.printStackTrace();
			desconectar(null,pstmt,null, connection);
			return bultos;
		}
		
		return bultos;
	}
	
	public boolean esArticuloOBultoMovible(String consulta) {
		boolean movible = false;
		try 
		{
			connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(consulta);;
			ResultSet rs = pstmt.executeQuery(consulta);
			while (rs.next()) 
			{
				movible = rs.getBoolean(1);
			}

			desconectar(null,pstmt,null, connection);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return movible;
	}
	
	public boolean intercambiarBultosPicking(int idPicking, String idBultoReservado, String idBultoPickeado, int idEmpresa) throws Exception {
		boolean modificado = false;
		connection = getConnection();
		PreparedStatement pstmt = null;
		
		String query = "UPDATE reposicion_articulos_ojos SET idBulto = '"+idBultoPickeado+"' WHERE IdEmpresa = "+idEmpresa+" "
				+ " AND idPicking = "+idPicking+" AND idBulto = '"+idBultoReservado+"';";
		String query2 = "UPDATE ojostienenarticulos SET";
		try {
			pstmt = connection.prepareStatement(query);
			int rs = pstmt.executeUpdate();
			if(rs > 0) {
				modificado = true;
			}
			desconectar(null,pstmt,null, connection);

		} catch (Exception e ) {
			e.printStackTrace();
			return modificado;
		}
		return modificado;
	}
	

	
	public boolean persistirDatosMaestros(String query) throws Exception {
		boolean retorno = false;

		connection = getConnection();
		PreparedStatement pstmt = null;
		
		
		try {
			pstmt = connection.prepareStatement(query);
			int rs = pstmt.executeUpdate();
			if(rs > 0) {
				retorno = true;
			}
			desconectar(null,pstmt,null, connection);

		} catch (Exception e ) {
			e.printStackTrace();
			return retorno;
		}
		
		return retorno;
	}
	
	public boolean MovimientoDeArticulos(MovimientoAlmacen movimientos, int idEmpresa) {
		PreparedStatement pstmt = null;			
		boolean result = false;
		try {			
			connection = getConnection();
			connection.setAutoCommit(false); 
			
			//pstmt = connection.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+query);
			//pstmt.executeUpdate();	
			BajaMovimientoArticulos(movimientos, idEmpresa, connection);
			AltaMovimientoArticulos(movimientos, idEmpresa, connection);
			RegistroMovimientoArticulos(movimientos,idEmpresa, connection);
			connection.commit();
			result = true;
		} catch (Exception e) {
			System.out.println("error persistiendo Movimientos de Almacen");
			e.printStackTrace();
			if(connection!=null){                    
                try {
                	connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
		finally {
			try {
				desconectar(null,pstmt,null, connection);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void AltaMovimientoArticulos(MovimientoAlmacen movimientos, int idEmpresa, Connection trans) 
	{
		try {
			String query = "";		
			
			for(elementoPicking m: movimientos.getaMover()) {
				query += "INSERT INTO `ojostienenarticulos` (`idOjo`, `idArticulo`, `Cantidad`, `usuarioUpdate`,idEmpresa) VALUES ('"
						+ movimientos.getUbicacionDestino()
						+ "', '"
						+ m.getIdArticulo()
						+ "', "
						+ m.getCantidad()+","+movimientos.getIdUsuario()
						+ ","+idEmpresa+") ON DUPLICATE KEY UPDATE `Cantidad` = `Cantidad`+"
						+ m.getCantidad()+",`usuarioUpdate`="+movimientos.getIdUsuario()+" ,inventUpdate=0, Actualizado=CURRENT_TIMESTAMP();";
			}
			
				
			PreparedStatement pstmt = null;
			
			pstmt = trans.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+query);
			pstmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
			if(trans!=null){                    
                try {
					trans.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
	}
	
	public void BajaMovimientoArticulos(MovimientoAlmacen movimientos, int idEmpresa, Connection trans) 
	{
		try {
			String query = "";
			
			for(elementoPicking m: movimientos.getaMover()) {
				if(m.getIdBulto()!= null) {						
					//si es un bulto saco del contenido del bulto
					query += "UPDATE `bulto_contenido` "
							+ " SET `Cantidad`=`Cantidad`-"+m.getCantidad()+ ", cantidadReservada=cantidadReservada-"+ m.getReserva() +" WHERE `idBulto`='"
							+ m.getIdBulto() + "' AND `idArticulo`='" + m.getIdArticulo()+"' AND picking= "+m.getPicking()+" and recepcion="+m.getRecepcion()
									+ " AND idEmpresa ="+idEmpresa+" LIMIT 1;";	
				}
				else {				
					//si es un articulo unitario saco de la ubicacion
					query += "UPDATE `ojostienenarticulos` "
							+ " SET `Cantidad`=`Cantidad`-"+m.getCantidad()+", `Reservada` = `Reservada`-"+m.getCantidad()+" WHERE `idOjo`='"
							+ movimientos.getUbicacionOrigen() + "' AND `idArticulo`='" + m.getIdArticulo()+"' "
									+ " AND idEmpresa ="+idEmpresa+" LIMIT 1;";	
				}
				
			}
			
			query +=" delete FROM ojostienenarticulos WHERE idEmpresa=5 and idArticulo IN (SELECT AC.idArticulo FROM bulto_contenido BC INNER JOIN articulos AC ON AC.idArticulo = BC.idBulto AND AC.IdTipo = 4 GROUP BY AC.idArticulo HAVING  SUM(`Cantidad`) <= 0); ";
			query += " DELETE FROM `bulto_contenido` WHERE `Cantidad` = 0 AND idEmpresa ="+idEmpresa+";";
			query += " delete FROM bulto WHERE idBulto NOT IN (SELECT distinct idBulto FROM  bulto_contenido) AND idEmpresa = "+idEmpresa+"; ";
			query += " DELETE FROM `ojostienenarticulos` WHERE `Cantidad` = 0 AND idEmpresa ="+idEmpresa+";";
			
				
			PreparedStatement pstmt = null;
			
			pstmt = trans.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+query);
			pstmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
			if(trans!=null){                    
                try {
					trans.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
	}
	
	public void RegistroMovimientoArticulos(MovimientoAlmacen movimientos, int idEmpresa, Connection trans) 
	{
		try {
			String query = "";					
			String origen = "";
			String tipo = "MOV";
			
			for(elementoPicking m: movimientos.getaMover()) {
				if(m.getIdBulto()!= null) {	
					origen = m.getIdBulto();
				}
				else {
					origen = movimientos.getUbicacionOrigen();
				}
				query += "insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,idTipo, IdEmpresa) values "+
						"('"+m.getIdArticulo()+"','"+origen+"','"+movimientos.getUbicacionDestino()+"',"+m.getCantidad()+","
								+ ""+movimientos.getIdUsuario()+",'"+tipo+"',"+idEmpresa+");";	
			}
			
			
			PreparedStatement pstmt = null;
			
			pstmt = trans.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+query);
			pstmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
			if(trans!=null){                    
                try {
					trans.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
	}
	
	public void persistirTransacciones(List<String> queries) 
	{
		Statement pstmt = null;
		try 
		{
			connection = getConnection();
			connection.setAutoCommit(false); 
			
			boolean corte= false;
			for (String query : queries) 
			{
				System.out.println(query);
				 //pstmt = connection.prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+query);
				pstmt = connection.createStatement ();
				pstmt.execute("SET GLOBAL max_allowed_packet=1073741824; "+query);
				
				 
				 
		
				 /*
				 if(rowAffected==0)
				 {
					corte = true;
					connection.rollback();
					break;
				 }
				 */
			}
			if(!corte)
			{
				connection.commit();
			}
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			if(connection!=null){                    
                try {
                	connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
             }
		}
		finally 
		{
			try 
			{
				desconectar(null,pstmt,null, connection);
			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
