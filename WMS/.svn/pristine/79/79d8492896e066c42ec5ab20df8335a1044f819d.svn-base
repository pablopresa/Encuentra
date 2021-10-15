package main;

import java.util.ArrayList;
import java.util.List;

import logica.Logica;

import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;

import persistencia.MSSQL;

import persistencia._EncuentraPersistir;
import beans.ArticuloForus;
import beans.Fecha;
import beans.Usuario;
public class SincronizaEncuentraElRey 
{
	
	
	public static void main(String[] args) 
	{
		
		sincro();
	}
		
	public static void sincro() 
	{	
		
		Logica Logica = new Logica();
		Usuario u = Logica.loginEncuentraSinEmpresa("ElREY", "SINPASS");
		
		int idEmpresa = u.getIdEmpresa();
		Fecha fec = null;
		try 
		{
			String fechaMYSQL = Logica.darListaDataIdDescripcionMYSQLConsulta("select 0, fechahora from fechasincronizacionart where idEmpresa="+idEmpresa).get(1).getDescripcion();
			fec = new Fecha(fechaMYSQL);
		}
		catch (Exception e) 
		{
			fec = new Fecha();
		}
		
		String fecha = fec.darFechaToSQLSinMinutos();
		try 
		{
			
			Logica.persistir("update fechasincronizacionart set fechahora = CURRENT_TIMESTAMP() where idEmpresa="+idEmpresa);
			//Logica.persistir("insert into reposicion_articulos_historico (select RA.* from reposicion_articulos RA inner join picking P  ON P.id=RA.idPicking  where DATE(P.fecha)!=CURRENT_DATE()  and idPicking !=0)");
			//Logica.persistir("delete RA.* from reposicion_articulos RA inner join picking P  ON P.id=RA.idPicking  where DATE(P.fecha)!=CURRENT_DATE()  and idPicking !=0");
			
					
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		
		
		List<String> consultasIdDesc = new ArrayList<>();
		
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'seccion';");
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'categoria';");
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'marca';");
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'clase';");
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'genero';");
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'color';");
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'artdescripcion';");
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'barra' union all select distinct IdArticulo id, IdArticulo Descripcion from encuentra_articulos;");
		consultasIdDesc.add("select id, Descripcion from encuentra_maestros where tipo = 'temporada';");
		
		
		
		List <DataIDDescripcion> dataArToSort = new ArrayList<>();
		
		try
		{
			_EncuentraPersistir eper = new _EncuentraPersistir();
			
			List<DataIDDescripcion> secciones = MSSQL.darIDDescripcion(consultasIdDesc.get(0),idEmpresa);
			eper.persistirIdDesc(secciones, "art_seccion",true, idEmpresa);
			
			List<DataIDDescripcion> categorias = MSSQL.darIDDescripcion(consultasIdDesc.get(1),idEmpresa);
			eper.persistirIdDesc(categorias, "art_categoria",true, idEmpresa);
			
			List<DataIDDescripcion> marcas = MSSQL.darIDDescripcion(consultasIdDesc.get(2),idEmpresa);
			eper.persistirIdDesc(marcas, "art_marca",true, idEmpresa);
			
			List<DataIDDescripcion> clases = MSSQL.darIDDescripcion(consultasIdDesc.get(3),idEmpresa);
			eper.persistirIdDesc(clases, "art_clase",true, idEmpresa);
			
			List<DataIDDescripcion> generos = MSSQL.darIDDescripcion(consultasIdDesc.get(4),idEmpresa);
			eper.persistirIdDesc(generos, "art_genero",true, idEmpresa);
			
			List<DataDescDescripcion> colores = MSSQL.darlistaDescrpcionDescripcion(consultasIdDesc.get(5),idEmpresa);
			eper.persistirDescDesc(colores, "art_colors",true, idEmpresa);
		
			
			try
			{
				List<ArticuloForus> articulosForus = MSSQL.darArticuloVs("SELECT [IdArticulo],[Descripcion],[talle],[color],[IdTemporada],[Coleccion],[IdProveedor],[IdMarca],[IdClase],[IdCategoria] FROM encuentra_articulos",idEmpresa);
				
				List<String> insertsB = new ArrayList<>();
				int counter = 0;
				
				StringBuilder cons = new StringBuilder();
				cons.append("INSERT INTO articulos (idArticulo,Descripcion,Talle,Color,IdTemporada,Coleccion,IdProveedor,IdMarca,IdClase,IdCategoria,idEmpresa) VALUES ");
				
				for (ArticuloForus d : articulosForus) 
				{
					cons.append("('"+d.getIdArticulo()+"', '"+d.getDescripcion().replace("'", "")+"', '"+d.getTalle()+"', '"+d.getColor()+"', "+d.getIdTemporada()+", "+d.getColeccion()+","+d.getIdProveedor()+","+d.getIdMarca()+","+d.getIdClase()+", "+d.getIdCategoria()+","+idEmpresa+"), ");
					if(counter==30000)
					{
						String consulta = cons.toString().substring(0, cons.length()-2)+" ON DUPLICATE KEY UPDATE idArticulo = VALUES(idArticulo), Descripcion = VALUES(Descripcion), Talle = VALUES(Talle),Color  = VALUES(Color),IdTemporada  = VALUES(IdTemporada),Coleccion  = VALUES(Coleccion),IdProveedor = VALUES(IdProveedor),IdMarca = VALUES(IdMarca),IdClase = VALUES(IdClase),IdCategoria = VALUES(IdCategoria);";
						insertsB.add(consulta);
						cons = new StringBuilder();
						cons.append("INSERT INTO articulos (idArticulo,Descripcion,Talle,Color,IdTemporada,Coleccion,IdProveedor,IdMarca,IdClase,IdCategoria,idEmpresa) VALUES ");
						counter=0;
					}
					counter++;
					
				}
				
				String consulta = cons.toString().substring(0, cons.length()-2)+" ON DUPLICATE KEY UPDATE idArticulo = VALUES(idArticulo), Descripcion = VALUES(Descripcion), Talle = VALUES(Talle),Color  = VALUES(Color),IdTemporada  = VALUES(IdTemporada),Coleccion  = VALUES(Coleccion),IdProveedor = VALUES(IdProveedor),IdMarca = VALUES(IdMarca),IdClase = VALUES(IdClase),IdCategoria = VALUES(IdCategoria);";
				insertsB.add(consulta);
				
				boolean todoOK=true;
				for (String insert : insertsB) 
				{
					boolean pudo =eper.persistirBatch(insert);
					if(!pudo)
					{
						todoOK=false;
					}
				}
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			List<DataDescDescripcion> descripciones = MSSQL.darlistaDescrpcionDescripcion(consultasIdDesc.get(6),idEmpresa);
			if(!descripciones.isEmpty())
			{
				StringBuilder con = new StringBuilder();
				con.append("INSERT INTO art_Descripcion (`Id`, `Descripcion`,idEmpresa) VALUES ");
				for (DataDescDescripcion ad : descripciones) 
				{
					
					con.append("('"+ad.getId()+"', '"+ad.getDescripcion().replace("'", "")+"',"+idEmpresa+"), ");
				}
				
				String consulta0 = con.toString().substring(0, con.length()-2)+" ON DUPLICATE KEY UPDATE `Descripcion` = VALUES(`Descripcion`)";
				
				eper.persistirBatch(consulta0);
			
			}
			List<DataDescDescripcion> barrasVisual = MSSQL.darlistaDescrpcionDescripcion(consultasIdDesc.get(7),idEmpresa);
			
			List<String> insertsB = new ArrayList<>();
			int counter = 0;
			
			StringBuilder cons = new StringBuilder();
			cons.append("INSERT INTO `artbarra_old` (`IdArticulo`, `Barra`,idEmpresa) VALUES ");
			
			for (DataDescDescripcion d : barrasVisual) 
			{
				cons.append("('"+d.getId()+"', '"+d.getDescripcion().replace(" ", "")+"',"+idEmpresa+"), ");
				
				
				
				if(counter==30000)
				{
					String consulta = cons.toString().substring(0, cons.length()-2)+" ON DUPLICATE KEY UPDATE `Barra` = VALUES(`Barra`)";
					insertsB.add(consulta);
					
					cons = new StringBuilder();
					cons.append("INSERT INTO `artbarra_old` (`IdArticulo`, `Barra`,idEmpresa) VALUES ");
					counter=0;
				}
				counter++;
				
			}
			
			String consulta = cons.toString().substring(0, cons.length()-2)+" ON DUPLICATE KEY UPDATE `Barra` = VALUES(`Barra`)";
			insertsB.add(consulta);
			
			eper.persistirBatch("DELETE FROM `artbarra_old`");
			boolean todoOK=true;
			for (String insert : insertsB) 
			{
				boolean pudo =eper.persistirBatch(insert);
				if(!pudo)
				{
					todoOK=false;
				}
			}
			if(todoOK)
			{
				eper.persistirBatch("DELETE FROM `artbarra` where idEmpresa="+idEmpresa);
				eper.persistirBatch("insert into artbarra(select * from artbarra_old where idEmpresa="+idEmpresa+" )");
				
			}
			
			
			
			
			List<DataIDDescripcion> temporadas = MSSQL.darIDDescripcion(consultasIdDesc.get(8),idEmpresa);
			eper.persistirIdDesc(temporadas, "art_temporadas",true,idEmpresa);
			System.gc();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		List <DataIDDescripcion> dataArSorted = Logica.EncuentraSortearListaDI(dataArToSort);
		System.out.println(dataArToSort.size());
		System.out.println(dataArSorted.size());
		
		/*
		try
		{
			Connection cone2;
			cone2 = _EncuentraPersistir.getConnection();
			
			for (DataIDDescripcion d : dataArSorted) 
			{
				String consultaMSSQLArtStock = "select SUM(Stock), '' from MovStockTotal " +
						" where IdArticulo = '"+d.getDescripcion()+"' AND IdDeposito = 99";
				
				Double miStock = Logica.encuentraDarMYSQLStock(d.getDescripcion());
				
			
				String consultaMYSQLStock = "";
				String stockS = null;
				try
				{
					
					
					
					
					
					Double stock = null;
					try 
					{
						stockS = MSSQL.darIdDescripcion(consultaMSSQLArtStock).get(0).getId();
						stock = Double.parseDouble(stockS);
						
					} catch (Exception e) 
					{
						
						System.out.println( "no hay stock en 99 para el articulo "+d.getDescripcion());
						
						stock = 0.0;
					}
					
					if(stock==0.0)
					{
						_EncuentraPersistir.persistir("delete from ojostienenarticulos where idArticulo = '"+d.getDescripcion()+"'");
						consultaMYSQLStock = "UPDATE `articulos` SET `Stock99`="+stock+" WHERE `IdArticulo`='"+d.getDescripcion()+"' LIMIT 1;";
						_EncuentraPersistir.persistir(consultaMYSQLStock);
					}
					else
					{
						List <DataIDDescripcion> misOjos = Logica.encuentraDarMYSQLStockUbi(d.getDescripcion());
						int StockEnojos = 0;
						for (DataIDDescripcion o : misOjos) 
						{
							StockEnojos +=o.getId(); 
						}

						if(!Double.valueOf(StockEnojos).equals(stock))
						{
							consultaMYSQLStock = "UPDATE `articulos` SET `Stock99`="+stock+" WHERE `IdArticulo`='"+d.getDescripcion()+"' LIMIT 1;";
							_EncuentraPersistir.persistir(consultaMYSQLStock);
							System.out.println( "Actualizando stock y ubicaciones para: "+d.getDescripcion());
														
							if (StockEnojos > stock)
							{
								
								//sacar de la primer ubicacion
								Double diferencia = StockEnojos - stock;
								
								for (DataIDDescripcion da : misOjos) 
								{
									Double cantPri = Double.valueOf(da.getId());// cantidad en el ojo
									
									if(cantPri< diferencia)
									{
										Logica.encuentraUpdateOjos(da.getDescripcion(), d.getDescripcion(), 0, false);
										diferencia = diferencia - cantPri;
									}
									else
									{
										Double diff = cantPri-diferencia;
										int difer = diff.intValue();
										
										Logica.encuentraUpdateOjos(da.getDescripcion(), d.getDescripcion(), difer,false);
										break;
									}
									
								}
								//si con la primera no empato sacar de la siguiente y así hasta terminar.
								
							}
							
						}
						
					}
				}
				catch (Exception e) 
				{
					System.out.println(stockS);
					System.out.println(consultaMYSQLStock);
				
					e.printStackTrace();
				}
				
			}
			
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		*/
		
		
		
		/*
		 * actualizar los codigos de barras
		 */
	
		
		/*
		 * acomodar las ubicaciones acorde al stock
		 */

		
		
		
		
	}
		
}
