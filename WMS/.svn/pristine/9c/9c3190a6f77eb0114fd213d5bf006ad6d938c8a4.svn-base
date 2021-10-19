package main;

import java.util.List;

import dataTypes.DataIDDescripcion;
import logica.Logica;
import persistencia._EncuentraPersistir;

public class BajarReservas {

	public static void main(String[] args) {
		_EncuentraPersistir ec = new _EncuentraPersistir();
		//
		Logica logica = new Logica();
		List<DataIDDescripcion> empresas = logica.Empresas();
		empresas.remove(0);
		for(DataIDDescripcion e: empresas){
			int idEmpresa = e.getId();
			String consultaSetearTodoCero = "update ojostienenarticulos ota SET reservada = 0\r\n" + 
					" WHERE ota.IdEmpresa="+idEmpresa+" AND ota.reservada > 0 \r\n" + 
					" AND CONCAT(ota.idArticulo, '-', ota.idOjo) NOT IN (\r\n" + 
					"        SELECT CONCAT(rao.idArticulo, '-', rao.cUbicacion) clave\r\n" + 
					"        from reposicion_articulos_ojos rao \r\n" + 
					"        WHERE rao.idBulto = '' AND rao.idPicking IN (\r\n" + 
					"            SELECT DISTINCT(T.idDoc) FROM tareas t \r\n" + 
					"            INNER JOIN reposicion_articulos_ojos rao ON rao.idPicking = t.idDoc AND rao.IdEmpresa = t.IdEmpresa\r\n" + 
					"            INNER JOIN tareasestados te ON te.idEstado = t.Estado AND te.IdEmpresa = t.IdEmpresa\r\n" + 
					"            WHERE te.idEstado <> 2 AND t.IdEmpresa = "+idEmpresa+"\r\n" + 
					"        )\r\n" + 
					"        GROUP BY rao.idArticulo, rao.cUbicacion\r\n" + 
					"    );";
			

			String consultaSetearReservadas = "update ojostienenarticulos ota \r\n" + 
					" INNER JOIN (SELECT rao.idArticulo, rao.cUbicacion, SUM(rao.Cantidad) AS cantidad FROM ojostienenarticulos ota\r\n" + 
					"				INNER JOIN reposicion_articulos_ojos rao ON rao.cUbicacion = ota.idOjo AND rao.idArticulo = ota.idArticulo AND rao.IdEmpresa = ota.IdEmpresa\r\n" + 
					"				WHERE rao.idBulto = '' AND rao.idPicking IN (\r\n" + 
					"    				SELECT DISTINCT(T.idDoc) FROM tareas t \r\n" + 
					"    				INNER JOIN reposicion_articulos_ojos rao ON rao.idPicking = t.idDoc AND rao.IdEmpresa = t.IdEmpresa\r\n" + 
					"    				INNER JOIN tareasestados te ON te.idEstado = t.Estado AND te.IdEmpresa = t.IdEmpresa\r\n" + 
					"    				WHERE te.idEstado <> 2 AND ota.IdEmpresa = "+idEmpresa+"\r\n" + 
					"				)\r\n" + 
					"				GROUP BY rao.idArticulo, rao.cUbicacion) tabla ON ota.idArticulo = tabla.idarticulo AND ota.idOjo = tabla.cubicacion\r\n" + 
					" SET reservada = tabla.cantidad \r\n" + 
					" WHERE ota.idArticulo = tabla.idarticulo AND ota.idOjo = tabla.cubicacion AND ota.IdEmpresa = "+idEmpresa+";";
			
			String consultaSetearTodoCeroBultos = "UPDATE bulto_contenido bc INNER JOIN (SELECT bc.idBulto, bc.idarticulo, bc.IdEmpresa from bulto_contenido bc \r\n" + 
					" INNER JOIN ojostienenarticulos ota ON ota.idArticulo = bc.idBulto AND ota.IdEmpresa = bc.IdEmpresa\r\n" + 
					"	WHERE bc.IdEmpresa="+idEmpresa+" AND bc.cantidadReservada > 0 \r\n" + 
					"	AND CONCAT(ota.idArticulo, '-', ota.idOjo) NOT IN (\r\n" + 
					"		SELECT CONCAT(rao.idArticulo, '-', rao.cUbicacion) clave\r\n" + 
					"			from reposicion_articulos_ojos rao \r\n" + 
					"			WHERE rao.idBulto = '' AND rao.idPicking IN (\r\n" + 
					"				SELECT DISTINCT(T.idDoc) FROM tareas t \r\n" + 
					"				INNER JOIN reposicion_articulos_ojos rao ON rao.idPicking = t.idDoc AND rao.IdEmpresa = t.IdEmpresa\r\n" + 
					"				INNER JOIN tareasestados te ON te.idEstado = t.Estado AND te.IdEmpresa = t.IdEmpresa\r\n" + 
					"				WHERE te.idEstado <> 2 AND t.IdEmpresa = "+idEmpresa+"\r\n" + 
					"			)\r\n" + 
					"		GROUP BY rao.idArticulo, rao.cUbicacion\r\n" + 
					"	)\r\n" + 
					" GROUP BY bc.idbulto, bc.idarticulo) as tabla1\r\n" + 
					" SET cantidadReservada = 0 WHERE bc.idBulto = tabla1.idBulto AND bc.idArticulo = tabla1.idarticulo AND bc.IdEmpresa = tabla1.idempresa;";
			
			String consultaSetearReservadasBultos = " UPDATE bulto_contenido bc INNER JOIN (SELECT tabla.* from bulto_contenido bc \r\n" + 
					" INNER JOIN ojostienenarticulos ota ON ota.idArticulo = bc.idBulto AND ota.IdEmpresa = bc.IdEmpresa\r\n" + 
					" INNER JOIN (SELECT rao.idArticulo, rao.cUbicacion, SUM(rao.Cantidad) AS cantidad, rao.idBulto, rao.idPicking FROM reposicion_articulos_ojos rao\r\n" + 
					"	WHERE rao.idBulto <> '' AND rao.idPicking IN (\r\n" + 
					"		SELECT DISTINCT(T.idDoc) FROM tareas t\r\n" + 
					"		INNER JOIN reposicion_articulos_ojos rao ON rao.idPicking = t.idDoc AND rao.IdEmpresa = t.IdEmpresa\r\n" + 
					"		INNER JOIN tareasestados te ON te.idEstado = t.Estado AND te.IdEmpresa = t.IdEmpresa\r\n" + 
					"			WHERE te.idEstado <> 2 AND rao.IdEmpresa = "+idEmpresa+"\r\n" + 
					"	)\r\n" + 
					"	GROUP BY rao.idArticulo, rao.cUbicacion) tabla ON ota.idOjo = tabla.cubicacion AND bc.idBulto = tabla.idBulto AND ota.idArticulo = tabla.idBulto\r\n" + 
					" WHERE ota.idArticulo = tabla.idBulto AND ota.idOjo = tabla.cubicacion AND ota.IdEmpresa = "+idEmpresa+"\r\n" + 
					" GROUP BY tabla.idArticulo, tabla.cUbicacion, tabla.idbulto, tabla.idpicking) as tabla1\r\n" + 
					" SET bc.cantidadReservada = tabla1.cantidad WHERE bc.idBulto = tabla1.idbulto AND bc.idArticulo = tabla1.idArticulo;";
			try {
				ec.persistir(consultaSetearTodoCero);
				ec.persistir(consultaSetearReservadas);
				ec.persistir(consultaSetearTodoCeroBultos);
				ec.persistir(consultaSetearReservadasBultos);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}

}
