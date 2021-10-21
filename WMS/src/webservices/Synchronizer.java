package webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Usuario;
import beans.api.DepositoMayorista;
import beans.api.StockDeposito;
import beans.endpoints.wsResponse;
import dataTypes.DTO_Articulo;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import persistencia._EncuentraPersistir;




@Path("/Synchronizer")
public class Synchronizer 
{
	@POST
	@Path("/putMasters")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String putMasters(String data,@QueryParam ("token") String a, @QueryParam ("tabla") String tabla)
	{
		Logica l = new Logica();
		_EncuentraPersistir eper = new _EncuentraPersistir();
		Gson gson = new Gson();
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		String json = "";		
		System.out.println(tabla);
		try {
			Usuario u = l.loginEncuentraAPI2(a);
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				System.out.println(data);
				List<DataIDDescripcion> datos = new ArrayList<>();
				try {
					datos = gson.fromJson(data, new TypeToken<List<DataIDDescripcion>>(){}.getType());
					eper.persistirIdDesc(datos, tabla, true, idEmpresa);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
	@POST
	@Path("/putMastersBarras")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String putMastersBarras(String data,@QueryParam ("token") String a, @QueryParam ("tabla") String tabla)
	{
		Logica l = new Logica();
		_EncuentraPersistir eper = new _EncuentraPersistir();
		Gson gson = new Gson();
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		String json = "";		
		System.out.println(tabla);
		try {
			Usuario u = l.loginEncuentraAPI2(a);
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				System.out.println(data);
				List<DataDescDescripcion> datos = new ArrayList<>();
				try {
					datos = gson.fromJson(data, new TypeToken<List<DataDescDescripcion>>(){}.getType());
					eper.persistirDescDesc(datos, tabla, true, idEmpresa, "idArticulo", "barra");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
	@POST
	@Path("/putWhs")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String putWhs(String data,@QueryParam ("token") String a)
	{
		Logica l = new Logica();
		_EncuentraPersistir eper = new _EncuentraPersistir();
		Gson gson = new Gson();
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		String json = "";		
		
		try {
			Usuario u = l.loginEncuentraAPI2(a);
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				Logica logica = new Logica();
				System.out.println(data);
				List<DepositoMayorista> datos = new ArrayList<>();
				try {
					datos = gson.fromJson(data, new TypeToken<List<DepositoMayorista>>(){}.getType());
					
					String estanteriaE =  logica.darParametroEmpresa(u.getIdEmpresa(), 25);
					String estanteriaP =  logica.darParametroEmpresa(u.getIdEmpresa(), 26);
					
					String ojoE = "";
					String ojoP = "";
										
					for (DepositoMayorista dc : datos) 
					{
						String nombreQuery = dc.getNombre().replace("'", "");
						
						if(dc.getTipo()!=null && dc.getTipo().equals("LOCAL"))
						{

							eper.persistir("INSERT INTO depositos (idDeposito,IdDepositoSAP, Nombre,IdEmpresa,Tipo) VALUES ("+dc.getIdDeposito()+",'"+dc.getIdDeposito()+"','"+nombreQuery+"',"+idEmpresa+",0) ON DUPLICATE KEY UPDATE Nombre = VALUES(Nombre)");
							ojoE = dc.getIdDeposito()+"E";
							ojoP = dc.getIdDeposito()+"P";
							eper.persistir("INSERT INTO ojos (idOjo,idEstanteria,IdEmpresa) VALUES ('"+ojoE+"',"+estanteriaE+","+idEmpresa+")  ON DUPLICATE KEY UPDATE idEstanteria = VALUES (idEstanteria)");
							eper.persistir("INSERT INTO ojos (idOjo,idEstanteria,IdEmpresa) VALUES ('"+ojoP+"',"+estanteriaP+","+idEmpresa+")  ON DUPLICATE KEY UPDATE idEstanteria = VALUES (idEstanteria)");
						}
						else
						{
							try 
							{
								ojoE = dc.getIdDeposito()+"E";
								ojoP = dc.getIdDeposito()+"P";
								eper.persistir("INSERT INTO ojos (idOjo,idEstanteria,IdEmpresa) VALUES ('"+ojoE+"',"+estanteriaE+","+idEmpresa+")  ON DUPLICATE KEY UPDATE idEstanteria = VALUES (idEstanteria)");
								eper.persistir("INSERT INTO ojos (idOjo,idEstanteria,IdEmpresa) VALUES ('"+ojoP+"',"+estanteriaP+","+idEmpresa+")  ON DUPLICATE KEY UPDATE idEstanteria = VALUES (idEstanteria)");
								eper.persistir("INSERT INTO `depositos` (`idDeposito`, `Nombre`, `Direccion`, `Tipo`, `IdDepositoSAP`, `estanteriaPicking`, `estanteriaExpedicion`,idEmpresa, departamento, ciudad) \r\n" + 
										"VALUES ('"+dc.getIdDeposito()+"', '"+nombreQuery+"', '"+dc.getDireccion()+"', '100', '"+dc.getIdDeposito()+"', '"+ojoP+"', '"+ojoE+"',"+idEmpresa+",'"+dc.getDepartamento()+"', '"+dc.getCiudad()+"') "
												+ "ON DUPLICATE KEY UPDATE Nombre = VALUES (`Nombre`), estanteriaPicking = '"+ojoP+"', estanteriaExpedicion = '"+ojoE+"', departamento = '"+dc.getDepartamento()+"', ciudad = '"+dc.getCiudad()+"' ;");
							}
							catch (Exception e) 
							{
							ojoE = dc.getIdDeposito()+"E";
							ojoP = dc.getIdDeposito()+"P";
							eper.persistir("INSERT INTO ojos (idOjo,idEstanteria,IdEmpresa,alias) VALUES ('"+ojoE+"',"+estanteriaE+","+idEmpresa+",'"+nombreQuery+"')  ON DUPLICATE KEY UPDATE idEstanteria = VALUES (idEstanteria)");
							eper.persistir("INSERT INTO ojos (idOjo,idEstanteria,IdEmpresa,alias) VALUES ('"+ojoP+"',"+estanteriaP+","+idEmpresa+",'"+nombreQuery+"')  ON DUPLICATE KEY UPDATE idEstanteria = VALUES (idEstanteria)");
							eper.persistir("INSERT INTO `depositos` (`idDeposito`, `Nombre`, `Direccion`, `Tipo`, `IdDepositoSAP`, `estanteriaPicking`, `estanteriaExpedicion`,idEmpresa, alias) \r\n" + 
									"VALUES ('"+dc.getIdDeposito()+"', '"+nombreQuery+"', '"+dc.getDireccion()+"', '100', '"+dc.getIdDeposito()+"', '"+ojoP+"', '"+ojoE+"',"+idEmpresa+
									",'"+nombreQuery+"') "
											+ "ON DUPLICATE KEY UPDATE Nombre = VALUES (`Nombre`), estanteriaPicking = '"+ojoP+"', estanteriaExpedicion = '"+ojoE+"' ;");
							
								e.printStackTrace();
							}
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
		
		json = gson.toJson(response);
		return json;
	}	
	
	
	@POST
	@Path("/putItems")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String putItems(String data,@QueryParam ("token") String a)
	{
		
		//ingresar articulos
		// art_descripcion
		//barras
		Logica l = new Logica();	
		Gson gson = new Gson();
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		String json = "";		
		System.out.println("/putItems");
		try {
			Usuario u = l.loginEncuentraAPI2(a);
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				Logica logica = new Logica();
				
				List<DTO_Articulo> datos = new ArrayList<>();
				try {
					datos = gson.fromJson(data, new TypeToken<List<DTO_Articulo>>(){}.getType());
					int indiceI = 0;
					int indiceF = 500;
					int tamanoDatos = datos.size();
					if(tamanoDatos>500)
					{
						try
						{
							boolean bandera = true;
							while(bandera) 
							{
								List<DTO_Articulo> subListaDatos = datos.subList(indiceI, indiceF);
								logica.persistirArticulos(subListaDatos, idEmpresa);
								indiceI = indiceF;
								indiceF = indiceF+500;
								if(indiceF > tamanoDatos) {
									indiceF = tamanoDatos;
								}
								if(indiceI == indiceF) {
									bandera = false;
								}
							}
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						logica.persistirArticulos(datos, idEmpresa);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				response.setCode("404");
				response.setDescription("Usuario incorrecto");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
	
	@POST
	@Path("/putStk")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String putStk(String data,@QueryParam ("token") String a)
	{
		Logica l = new Logica();
		_EncuentraPersistir eper = new _EncuentraPersistir();
		Gson gson = new Gson();
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		String json = "";		
		
		try {
			Usuario u = l.loginEncuentraAPI2(a);
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				System.out.println(data);
				List<StockDeposito> datos = new ArrayList<>();
				try {
					datos = gson.fromJson(data, new TypeToken<List<StockDeposito>>(){}.getType());
					
					
					StringBuilder artsDelete = new StringBuilder();
					boolean pri = true;
					String cabezal = "INSERT IGNORE INTO art_stock (idArticulo,idDeposito,Stock,idEmpresa) VALUES ";
					StringBuilder valores = new StringBuilder();
					String sValores = "";
					for (StockDeposito dc : datos) 
					{
						try 
						{
							if(pri)
							{
								pri = false;
								artsDelete.append("'"+dc.getIdArticulo()+"'");
							}
							else
							{
								artsDelete.append(",'"+dc.getIdArticulo()+"'");
							}
							
							
							valores.append("('"+dc.getIdArticulo()+"',"+dc.getIdDeposito()+", "+dc.getStock()+", "+idEmpresa+"),");
							
						}
						catch (Exception e) 
						{
						
							e.printStackTrace();
						}
					}
					
					sValores = valores.substring(0, valores.length()-1);
					String clean = " delete from art_stock where idArticulo in ("+artsDelete+") and idEmpresa ="+idEmpresa+"; ";
					eper.persistir(clean + cabezal + sValores+" ON DUPLICATE KEY UPDATE  Stock = VALUES(Stock)");
					


				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
	@POST
	@Path("/putConexionSubfamilias")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String putConexionSubfamilias(String data, @QueryParam ("token") String a, @QueryParam ("tabla") String tabla, @QueryParam ("columna1") String columna1, @QueryParam ("columna2") String columna2, @QueryParam ("columna3") String columna3)
	{
		Logica l = new Logica();
		_EncuentraPersistir eper = new _EncuentraPersistir();
		Gson gson = new Gson();
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		String json = "";		
		System.out.println(tabla);
		try {
			Usuario u = l.loginEncuentraAPI2(a);
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				System.out.println(data);
				List<DataIDDescripcion> datos = new ArrayList<>();
				try {
					datos = gson.fromJson(data, new TypeToken<List<DataIDDescripcion>>(){}.getType());
					eper.persistirIdIdDescripcion(datos, tabla, columna1, columna2, columna3, idEmpresa);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
}
