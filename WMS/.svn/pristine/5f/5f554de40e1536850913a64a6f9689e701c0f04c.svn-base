package web.dashboards;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;

import beans.Usuario;
import dataTypes.DataIndicador;
import dataTypes.DataIndicadorPicking;
import persistencia.MSSQL;
import persistencia._EncuentraConexion;

public class Procesador 
{
	private _EncuentraConexion econ;
	public Procesador()
	{
		econ = new _EncuentraConexion();
	}
	
	public _EncuentraConexion getEcon() {
		return econ;
	}
	
	public  List<DataIndicador> darIndicador(String fechaI, String fechaF, int idIndicador, List<Integer> usuarios, List<Integer> marcas, List<Integer> clases, List<Integer> depositos, List<Integer> modulos) 
	{
		
		
		String deposIn = "";
		String marcasIn = "";
		String clasesIn = "";
		String usuariosIn = "";
		String modulosIn = "";
		
		fechaF = fechaF+" 23:59:59";

		
		if(modulos!=null)
		{
			for (int i : modulos) 
			{
				modulosIn+=i+",";
			}
			
			if(modulosIn.length()>0)
			{
				modulosIn = modulosIn.substring(0, modulosIn.length()-1);
			}
		}
		
		
		if(usuarios!=null)
		{
			for (int i : usuarios) 
			{
				usuariosIn+=i+",";
			}
			
			if(usuariosIn.length()>0)
			{
				usuariosIn = usuariosIn.substring(0, usuariosIn.length()-1);
			}
		}
		
		
		if(marcas!=null)
		{
			for (int i : marcas) 
			{
				marcasIn+=i+",";
			}
			
			if(marcasIn.length()>0)
			{
				marcasIn = marcasIn.substring(0, marcasIn.length()-1);
			}
		}
		
		if(clases!=null)
		{
			for (int i : clases) 
			{
				clasesIn+=i+",";
			}
			
			if(clasesIn.length()>0)
			{
				clasesIn = clasesIn.substring(0, clasesIn.length()-1);
			}
		}
		
		
		
		for (int i : depositos) 
		{
			deposIn+=i+",";
		}
		
		if(deposIn.length()>0)
		{
			deposIn = deposIn.substring(0, deposIn.length()-1);
		}
		
		String consulta = "";
		switch (idIndicador) 
		{
			case 1: // pickings
			{
				consulta = " SELECT Z.WHS_TO, SUM(Z.movidas), Z.WHS_TO_NAME,'',0.0,0.0,SUM(Z.intencion) FROM z_forus Z WHERE WHS_TO IN ("+deposIn+") AND ID_Operador IN ("+usuariosIn+") AND idMarca IN ("+marcasIn+") AND idClase IN ("+clasesIn+") AND IdtipoDato IN ("+modulosIn+") AND Z.fecha>='"+fechaI+"' AND Z.fecha<='"+fechaF+"'  GROUP BY Z.WHS_TO, Z.WHS_TO_NAME ORDER BY Z.WHS_TO ";
				 break;
			}
			case 11: // pickings por tienda
			{
				consulta = " SELECT Z.WHS_TO, SUM(Z.movidas), Z.WHS_TO_NAME,'',(SUM(Z.movidas)*100.00)/SUM(Z.intencion),0.0,SUM(Z.intencion) FROM z_forus Z WHERE WHS_TO IN ("+deposIn+") AND ID_Operador IN ("+usuariosIn+") AND idMarca IN ("+marcasIn+") AND idClase IN ("+clasesIn+") AND IdtipoDato IN ("+modulosIn+") AND Z.fecha>='"+fechaI+"' AND Z.fecha<='"+fechaF+"'  GROUP BY Z.WHS_TO, Z.WHS_TO_NAME ORDER BY Z.WHS_TO ";
				break;
				 
			}
			
			case 12: // pickings por Usuario
			{
				consulta = "SELECT Z.ID_Operador, SUM(Z.movidas), Z.Operario,'',(SUM(Z.movidas)*100.00)/SUM(Z.intencion),0.0,SUM(Z.intencion) FROM z_forus Z WHERE WHS_TO IN ("+deposIn+") AND ID_Operador IN ("+usuariosIn+") AND idMarca IN ("+marcasIn+") AND idClase IN ("+clasesIn+") AND IdtipoDato IN ("+modulosIn+") AND Z.fecha>='"+fechaI+"' AND Z.fecha<='"+fechaF+"'  GROUP BY Z.ID_Operador, Z.Operario ORDER BY Z.Operario ";
				break;
				 
			}
			case 13: // pickings por marca
			{
				consulta = "SELECT Z.idMarca, SUM(Z.movidas), Z.marca,'',(SUM(Z.movidas)*100.00)/SUM(Z.intencion),0.0,SUM(Z.intencion) FROM z_forus Z WHERE WHS_TO IN ("+deposIn+") AND ID_Operador IN ("+usuariosIn+") AND idMarca IN ("+marcasIn+") AND idClase IN ("+clasesIn+") AND IdtipoDato IN ("+modulosIn+") AND Z.fecha>='"+fechaI+"' AND Z.fecha<='"+fechaF+"'  GROUP BY Z.idMarca, Z.marca ORDER BY Z.marca ";
				break;
				 
			}
			case 14: // pickings por clase
			{
				consulta = "SELECT Z.idClase, SUM(Z.movidas), Z.Clase,'',(SUM(Z.movidas)*100.00)/SUM(Z.intencion),0.0,SUM(Z.intencion) FROM z_forus Z WHERE WHS_TO IN ("+deposIn+") AND ID_Operador IN ("+usuariosIn+") AND idMarca IN ("+marcasIn+") AND idClase IN ("+clasesIn+") AND IdtipoDato IN ("+modulosIn+") AND Z.fecha>='"+fechaI+"' AND Z.fecha<='"+fechaF+"'  GROUP BY Z.idClase, Z.Clase ORDER BY Z.Clase ";
				break;
				 
			}
		
		
		default:
			break;
		}
		
		 System.out.println(consulta);

			try 
			{
				@SuppressWarnings("unused") Connection cone;
				cone = econ.getConnection();
				return econ.darListaIndicadoresEncuentra(consulta);

			} catch (Exception e) 
			{

				e.printStackTrace();
				return null;
			}
		
	}

	public List<DataIndicadorPicking> darIndicadorPicking(String fechaI, String fechaF,  List<Integer> usuarios, List<Integer> marcas, List<Integer> clases, List<Integer> depositos,
			 String tablaCabezal, String tablaDetalles) 
	{
		String deposIn = "";
		String marcasIn = "";
		String clasesIn = "";
		String usuariosIn = "";
		
		
		fechaF = fechaF+" 23:59:59";

		
		
		
		
		if(usuarios!=null)
		{
			for (int i : usuarios) 
			{
				usuariosIn+=i+",";
			}
			
			if(usuariosIn.length()>0)
			{
				usuariosIn = usuariosIn.substring(0, usuariosIn.length()-1);
			}
		}
		
		
		if(marcas!=null)
		{
			for (int i : marcas) 
			{
				marcasIn+=i+",";
			}
			
			if(marcasIn.length()>0)
			{
				marcasIn = marcasIn.substring(0, marcasIn.length()-1);
			}
		}
		
		if(clases!=null)
		{
			for (int i : clases) 
			{
				clasesIn+=i+",";
			}
			
			if(clasesIn.length()>0)
			{
				clasesIn = clasesIn.substring(0, clasesIn.length()-1);
			}
		}
		
		
		
		for (int i : depositos) 
		{
			deposIn+=i+",";
		}
		
		if(deposIn.length()>0)
		{
			deposIn = deposIn.substring(0, deposIn.length()-1);
		}
		
		String consulta = "SELECT D.marca,  D.seccion,  D.clase,  D.categoria,  D.nickname,D.idArticulo, D.oJOs,  D.idSincronizacion,  D.idMarca,  D.idSeccion,  D.idClase,\r\n" + 
				"			 D.idCategoria,  D.idPicking,  D.idUsuario,  D.cantidad,  D.destino,D.Picked,D.picked_at,D.Verif,D.verif_at,D.idPedido , "
				+ " C.fecha, C.destinos, C.inicioPicking, C.finPicking, C.nickname, C.inicioVerificacion, C.finVerificacion, C.id, C.solicitada, C.pickeadas, C.verificadas, C.tiempoPicking, C.idUsuario, C.tiempoVerificacion, C.unidadesXHora, C.unidadesXHora_verif, C.TiempoPausa \r\n" + 
				"\r\n" + 
				"FROM z_forus_picking_cab C INNER JOIN \r\n" + 
				"z_forus_picking_det D ON C.id = D.idPicking\r\n" + 
				"WHERE C.fecha>='"+fechaI+"'\r\n" + 
				"AND C.fecha<='"+fechaF+"'\r\n" +
				"AND D.idMarca IN ("+marcasIn+")\r\n" + 
				"AND D.idClase IN ("+clasesIn+")\r\n" + 
				"AND D.idUsuario IN ("+usuariosIn+")\r\n" + 
				"AND D.Destino IN ("+deposIn+")";
		
				
		
		 System.out.println(consulta);

			try 
			{
				@SuppressWarnings("unused") Connection cone;
				cone = econ.getConnection();
				return econ.darListaIndicadoresPicking(consulta);

			} catch (Exception e) 
			{

				e.printStackTrace();
				return null;
			}
	}

}
