import java.util.List;

import beans.Usuario;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import main.TestUnit2;
import persistencia.MSSQL;
import persistencia._EncuentraPersistir;


public class SincPicker 
{

	public static void main(String[] args) 
	{
		TestUnit2 tu = new TestUnit2();
		Logica Logica = new Logica();
		Usuario u = Logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");
		//Usuario u = Logica.loginEncuentraSinEmpresa("$admin", "SINPASS");
		int idEmpresa = u.getIdEmpresa();
		
		
		sincDeposMayorista(idEmpresa, Logica);
		
		
		tu.SincronizarDistribuciones(idEmpresa);
		//tu.Sincronizar(true, idEmpresa, Integer.parseInt(u.getDeposito()));
		
	}
	
	
	public static void sincDeposMayorista (int idEmpresa,Logica lo)
	{
		String idEstanteriaExp = lo.darParametroEmpresa(idEmpresa, 26); 
		String idEstanteriaPick = lo.darParametroEmpresa(idEmpresa, 25);
		
		List <DataDescDescripcion> lista = MSSQL.darDeposMayoristaForus("SELECT distinct  [IdCliente] ,[cliente]   FROM [VsHushPuppies].[dbo].[encuentra_distribucion] D where D.origen = 9000 and D.destino = 0",idEmpresa);
		
		_EncuentraPersistir eper = new _EncuentraPersistir();
		
		String ojoE = "";
		String ojoP = "";
		
		for (DataDescDescripcion dc : lista) 
		{
			try 
			{
				ojoE = dc.getId()+"E";
				ojoP = dc.getId()+"P";
				
				eper.persistir("INSERT INTO ojos (idOjo,idEstanteria,IdEmpresa) VALUES ('"+ojoE+"',"+idEstanteriaExp+","+idEmpresa+")  ON DUPLICATE KEY UPDATE idEstanteria = VALUES (idEstanteria)");
				eper.persistir("INSERT INTO ojos (idOjo,idEstanteria,IdEmpresa) VALUES ('"+ojoP+"',"+idEstanteriaPick+","+idEmpresa+")  ON DUPLICATE KEY UPDATE idEstanteria = VALUES (idEstanteria)");
				eper.persistir("INSERT INTO `depositos` (`idDeposito`, `Nombre`, `Direccion`, `Tipo`, `IdDepositoSAP`, `estanteriaPicking`, `estanteriaExpedicion`,idEmpresa) \r\n" + 
						"VALUES ('"+dc.getId()+"', '"+dc.getDescripcion()+"', '"+dc.getDescripcion()+"', '100', '"+dc.getId()+"', '"+ojoP+"', '"+ojoE+"',"+idEmpresa+") "
						+ "ON DUPLICATE KEY UPDATE Nombre = VALUES (`Nombre`), estanteriaPicking = '"+ojoP+"', estanteriaExpedicion = '"+ojoE+"' ;");
				
			}
			catch (Exception e) 
			{
			
				e.printStackTrace();
			}
		}
		
		
	}
	
	

}
