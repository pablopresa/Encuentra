package forus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.Compras;
import logica.Logica;
import persistencia.MSSQL;



public class grabarClienteForus {


	public static void main(String[] args) {
		Logica logica = new Logica();
		List<Integer> nroPedido = Arrays.asList(172900);
		List<DataIDDescripcion> canalesFenicioALL = logica.darListaDataIdDescripcionMYSQLConsulta("select id,nombre from ecommerce_canal_ml where id NOT IN (5,0)  AND idEmpresa="+2);
		canalesFenicioALL.remove(0);
		Call_WS_analoga call = new Call_WS_analoga();
		Hashtable<String, DataIDDescripcion> destinoPedidos = new Hashtable<>();

		for (DataIDDescripcion c:canalesFenicioALL)
		{
			destinoPedidos = call.DestinoPedidos(c.getId(), 10, 2,destinoPedidos);
		}
		grabarClienteForus gcf = new grabarClienteForus();

		List<Compras> compras = gcf.reSincPedidos(2, destinoPedidos, nroPedido, logica);

		for (Compras compra : compras) {
			System.out.println(compra.getCompra().getId());
			compra.getCliente().setIdPedido(compra.getCompra().getId());
			compra.getCliente().save(2);
		}
	}


	public List<Compras> reSincPedidos(int idEmpresa, Hashtable<String, DataIDDescripcion> destinoPedidos, List<Integer> nroPedido, Logica logica){
		List<Compras> compras = new ArrayList<>();
		List<DataIDDescripcion>depositosPick = logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito,nombre from ecommerce_envioml WHERE idEmpresa="+idEmpresa+" AND idDeposito !=0");
		depositosPick.remove(0);
		Hashtable<String, String> depositosPickHT = new Hashtable<>();
		for (DataIDDescripcion d : depositosPick) 
		{
			depositosPickHT.put(d.getDescripcion(), String.valueOf(d.getId()));
		}
		String nrosPedido = nroPedido.toString();
		nrosPedido = nrosPedido.replace("[", "");
		nrosPedido = nrosPedido.replace("]", "");

		//QUERY
		String queryPedidos = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select Vta_Total, DocV_NumeroDoc, 'estado' estado, Doc_Fecha, '$' moneda, Doc_IdDepDestino, "+
				"'0.0' as montoenvio, 'MetodoPago' metodoPago,DocV_Serie,Doc_Comentario, Cli_Apellido, Cli_Nombre, VEnt_Ciudad as 'localidad', '' mail, "+ 
				"VEnt_Departamento, 0 latitud, VEnt_CliTelefono, 0 longitud,VEnt_Direccion,VEnt_Ciudad,VEnt_CodigoPostal,VEnt_Comentario, 'CI' 'docTipo', CONCAT(Cli_Numero,Cli_NumeroDig),doc_Fecha "+
				"  from dbo.encuentra_ventaWEB WHERE DocV_NumeroDoc IN ("+nrosPedido+")";

		compras =MSSQL.darComprasWeb(queryPedidos, depositosPickHT,0,idEmpresa,destinoPedidos);


		return compras;
	}

}
