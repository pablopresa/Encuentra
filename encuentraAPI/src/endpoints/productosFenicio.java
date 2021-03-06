package endpoints;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Usuario;
import beans.datatypes.DataIDDescripcion;
import integraciones.lAaS.LogicaProdFenicio;
import integraciones.marketplaces.fenicio.apiComercios.ConsultaFenicio;
import integraciones.marketplaces.fenicio.apiComercios.Data;
import integraciones.marketplaces.fenicio.apiComercios.Productos;
import integraciones.marketplaces.fenicio.apiComercios.RespuestaFenicio;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

@Path("/Fenicio")
public class productosFenicio {
	@GET
	@Path("/productos")
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public String createUser(@FormParam("name") String name) throws IOException
	public String put(String data, @QueryParam("token") String a) {
		System.out.println(data);
		String json = "";
		try {
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);

			if (u.getNick() == null) {
				System.out.println("access_token invalido");
				json = "access_token invalido";
			} else {
				Gson gson = new Gson();
				ConsultaFenicio con = gson.fromJson(data, ConsultaFenicio.class);

				int desde = 0;
				int total = 0;
				String solicitud = "";
				try {
					solicitud = con.get_idSolicitud();
					desde = con.getDesde();
					total = con.getTotal();
					if (desde == 0) {
						desde = 1;
					}
				} catch (Exception e) {
					desde = 1;
				}

				String q = "";
				switch (u.getIdEmpresa()) 
				{
				case 8:
					
					q="SELECT IFNULL(if(t00.stk<0,0,t00.stk),0), id, descripcion \r\n"
							+ " FROM \r\n"
							+ " (\r\n"
							+ " 	SELECT AD.id,AD.descripcion\r\n"
							+ " FROM art_descripcion AD\r\n"
							+ " INNER JOIN \r\n"
							+ " (\r\n"
							+ " 	 SELECT SUBSTR(id,1,CHARACTER_LENGTH(a.id)-3) subid \r\n"
							+ " 	 from art_descripcion a WHERE idEmpresa = "+u.getIdEmpresa()+" GROUP BY SUBSTR(id,1,CHARACTER_LENGTH(a.id)-3) \r\n"
							+ " 	 ORDER BY id \r\n"
							+ " 	 LIMIT "+desde+","+total+"\r\n"
							+ " )\r\n"
							+ " TS\r\n"
							+ " ON TS.subid=SUBSTR(AD.id,1,CHARACTER_LENGTH(AD.id)-3) \r\n"
							+ " ) AD\r\n"
							+ " LEFT OUTER JOIN\r\n"
							+ " (\r\n"
							+ " SELECT SUM(t0.cant) stk,t0.idArticulo\r\n"
							+ " FROM (\r\n"
							+ " SELECT idArticulo, SUM(cantidad-picked)*-1 cant\r\n"
							+ " FROM reposicion_articulos ra\r\n"
							+ " WHERE ra.estado in (1,2,3,11,12) AND ra.idEmpresa= "+u.getIdEmpresa()+"\r\n"
							+ " GROUP BY ra.idArticulo UNION ALL\r\n"
							+ " SELECT BC.idArticulo, SUM(BC.cantidad) cant\r\n"
							+ " FROM bulto B\r\n"
							+ " INNER JOIN bulto_contenido BC ON BC.idBulto = B.idBulto AND BC.IdEmpresa = B.idEmpresa\r\n"
							+ " INNER JOIN ojostienenarticulos OT ON B.idBulto=OT.idArticulo AND B.idEmpresa=OT.IdEmpresa\r\n"
							+ " INNER JOIN ojos O ON O.idOjo=OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n"
							+ " INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa\r\n"
							+ " WHERE ES.idUso = 1 AND ES.idEmpresa= " + u.getIdEmpresa() + "\r\n"
							+ " GROUP BY BC.idArticulo UNION ALL\r\n"
							+ " SELECT OT.idArticulo, SUM(OT.cantidad) cant\r\n" + " FROM ojostienenarticulos OT\r\n"
							+ " INNER JOIN ojos O ON O.idOjo=OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n"
							+ " INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa\r\n"
							+ " WHERE ES.idUso = 1 AND ES.idEmpresa= " + u.getIdEmpresa() + "\r\n"
							+ " GROUP BY OT.idArticulo UNION ALL\r\n"
							+ " SELECT OT.idArticulo, SUM(OT.cantidad) cant\r\n" + " FROM ojostienenarticulos OT\r\n"
							+ " INNER JOIN ojos O ON O.idOjo=OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n"
							+ " INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa\r\n"
							+ " WHERE ES.idEstanteria IN (0,2) AND ES.idEmpresa= " + u.getIdEmpresa() + "\r\n"
							+ " GROUP BY OT.idArticulo) t0\r\n" + " GROUP BY t0.idArticulo\r\n" + " ) t00\r\n"
							+ " ON t00.idArticulo = AD.id\r\n"
							// + " WHERE t00.stk > 0"
							+ " ORDER BY AD.id";
					System.out.println(q);

				}

				Call_WS_APIENCUENTRA call = new Call_WS_APIENCUENTRA();
				DataIDDescripcion consulta = new DataIDDescripcion(0, q);
				Map<String, DataIDDescripcion> stock = call.vistaStocksQ(consulta, a);

				StringBuilder idsArticulo = new StringBuilder();
				boolean pri = true;
				for (String key : stock.keySet()) {
					if (pri) {
						pri = false;
						idsArticulo.append("'" + key + "'");
					} else
						idsArticulo.append(",'" + key + "'");

				}

				Map<String, Double> artPrecios = LogicaAPI.darArtPrecio(idsArticulo.toString(), u.getIdEmpresa());

				for (Map.Entry<String, Double> entry : artPrecios.entrySet()) {
					try {
						DataIDDescripcion artStk = stock.get(entry.getKey());
						artStk.setIdD(entry.getValue());
						stock.put(entry.getKey(), artStk);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				List<DataIDDescripcion> lista = new ArrayList<>(stock.values());

				Map<String, Productos> prodsHt = new Hashtable<>();
				LogicaProdFenicio pl = new LogicaProdFenicio();
				Productos[] arregloProductos = pl.darProductos(lista);

				RespuestaFenicio rsp = new RespuestaFenicio();
				rsp.setStatus("OK");
				rsp.setMensaje("");
				rsp.set_idSolicitud(solicitud);

				Data dat = new Data();
				dat.setDesde(desde);
				dat.setTotal(total);

				arregloProductos = arregloProductos == null ? new Productos[0] : arregloProductos;
				dat.setProductos(arregloProductos);
				rsp.setData(dat);

				json = gson.toJson(rsp);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	@POST
	@Path("/preciosProductos")
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public String createUser(@FormParam("name") String name) throws IOException
	public String preciosProductos(String data, @QueryParam("token") String a) {

		Usuario u = LogicaAPI.loginEncuentraAPI2(a);
		String jsonn = "";
		Gson gsonn = new Gson();
		if (u.getNick() == null) {
			System.out.println("access_token invalido");
			jsonn = "access_token invalido";
		} else {
			Map<String, Double> artPrecios = LogicaAPI.darArtPrecio(data, u.getIdEmpresa());
			jsonn = gsonn.toJson(artPrecios);
		}
		return jsonn;
	}

	@POST
	@Path("/darProductos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String darProductos(String input) {
		Gson gson = new Gson();
		List<DataIDDescripcion> lista = gson.fromJson(input, new TypeToken<List<DataIDDescripcion>>() {
		}.getType());
		LogicaProdFenicio logica = new LogicaProdFenicio();
		Productos[] productos = logica.darProductos(lista);
		return gson.toJson(productos);
	}

}
