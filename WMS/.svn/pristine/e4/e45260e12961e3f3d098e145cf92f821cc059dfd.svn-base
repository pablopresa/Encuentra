package persistencia.expedicion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;



import beans.encuentra.RutaDeposito;
import beans.encuentra.Ruta;

import persistencia.Conexion;

public class PersistenciaRutas 
{
	private Conexion con;
	public PersistenciaRutas()
	{
		 this.con = new Conexion();
	}
	
	
	public  List <Ruta> darRutas (int idEmpresa, int idRuta) 
	{
		
		String qidruta="";
		if(idRuta!=0)
		{
			qidruta= " AND R.idRuta="+idRuta;
		}
		String consulta = "SELECT R.idRuta,R.Descripcion, IFNULL(RD.idDeposito,0),RD.Orden,D.Nombre \r\n"
				+ "FROM rutas R LEFT OUTER JOIN ruta_depositos RD ON RD.idRuta = R.idRuta AND RD.idEmpresa = R.idEmpresa \r\n"
				+ "LEFT OUTER JOIN depositos D ON D.idDeposito = RD.idDeposito AND D.IdEmpresa = RD.idEmpresa \r\n"
				+ "WHERE R.idEmpresa="+idEmpresa+" \r\n"
				+ qidruta
				+ "ORDER BY R.idRuta, RD.idDeposito";


		try (Statement s = con.getConnection().createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);
			Hashtable<Integer, Ruta> rutasHT = new Hashtable<Integer, Ruta>();
			while (rs.next()) 
			{
				int idRutaR = rs.getInt(1);
				
				if(!rutasHT.containsKey(idRutaR))
				{
					rutasHT.put(idRutaR, new Ruta(idRutaR,rs.getString(2)));
				}
					
				
				if(rs.getInt(3)!=0)
				{
					rutasHT.get(idRutaR).getDepositos().add(new RutaDeposito(rs.getInt(3), rs.getInt(4), rs.getString(5)));
				}
				
				
				
				
			}
			List <Ruta> lista = new ArrayList<>(rutasHT.values());
			con.desconectar(rs,null, s);
			return lista;
		}
		catch (Exception e) 
		{		
			e.printStackTrace();
			return new ArrayList<Ruta>();
		}

		
	}
	
	public  Hashtable<Integer, Ruta> darRutasHT (int idEmpresa, int idRuta) 
	{
		
		String qidruta="";
		if(idRuta!=0)
		{
			qidruta= " AND R.idRuta="+idRuta;
		}
		String consulta = "SELECT R.idRuta,R.Descripcion, IFNULL(RD.idDeposito,0),RD.Orden,D.Nombre \r\n"
				+ "FROM rutas R LEFT OUTER JOIN ruta_depositos RD ON RD.idRuta = R.idRuta AND RD.idEmpresa = R.idEmpresa \r\n"
				+ "LEFT OUTER JOIN depositos D ON D.idDeposito = RD.idDeposito AND D.IdEmpresa = RD.idEmpresa \r\n"
				+ "WHERE R.idEmpresa="+idEmpresa+" \r\n"
				+ qidruta
				+ "ORDER BY R.idRuta, RD.idDeposito";


		try (Statement s = con.getConnection().createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);
			Hashtable<Integer, Ruta> rutasHT = new Hashtable<Integer, Ruta>();
			while (rs.next()) 
			{
				int idRutaR = rs.getInt(1);
				
				if(!rutasHT.containsKey(idRutaR))
				{
					rutasHT.put(idRutaR, new Ruta(idRutaR,rs.getString(2)));
				}
					
				
				if(rs.getInt(3)!=0)
				{
					rutasHT.get(idRutaR).getDepositos().add(new RutaDeposito(rs.getInt(3), rs.getInt(4), rs.getString(5)));
				}
				
				
				
				
			}
			
			con.desconectar(rs,null, s);
			return rutasHT;
		}
		catch (Exception e) 
		{		
			e.printStackTrace();
			return new Hashtable<Integer, Ruta>();
		}

		
	}


	public void ABM(int idRuta, String descripcion, int idEmpresa,boolean borrar) 
	{
		
		String consulta = "";
		
		if(borrar)
		{
			consulta = "DELETE FROM `rutas` WHERE  `idRuta`="+idRuta+" AND `idEmpresa`="+idEmpresa+";";
		}
		else if(idRuta==0)
		{
			consulta = "INSERT INTO rutas (Descripcion,idEmpresa) VALUES ('"+descripcion+"',"+idEmpresa+");";
		}
		else
		{
			consulta = "UPDATE rutas SET Descripcion='"+descripcion+"' WHERE idRuta = "+idRuta+" AND idEmpresa="+idEmpresa+";";
		}
		
		
		
		boolean pude = false;	
		PreparedStatement pstmt = null;
		try {
 			pstmt = con.getConnection().prepareStatement("SET GLOBAL max_allowed_packet=1073741824; "+consulta);
			pstmt.executeUpdate();
			//pstmt.close();
			//coment
			pude = true;
			con.desconectar(null,pstmt,null);

		} catch (Exception e )
		{
			pude = false;
			System.out.println("persistiendo: "+consulta);
			System.out.println("error");
			e.printStackTrace();
			try {
				con.desconectar(null,pstmt,null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
	}
	

}
