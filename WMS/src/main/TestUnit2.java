package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import dataTypes.DataDepositoSAP;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import dataTypes.DataReglaReposicion;
import helper.PropertiesHelper;
import logica.Logica;
import persistencia.MSSQL;
import beans.ArticuloReposicion;
import beans.ArticuloReposicionMaster;
import beans.Fecha;
import beans.encuentra.DepositoAdmin;

public class TestUnit2 
{
	public TestUnit2(){}
	
	public void SincronizarVentasM(int idEmpresa)
	{
		Logica Logica = new Logica();
		Logica.darArticuloRepoOrderClosed(idEmpresa);
		int idSinc = Logica.darNextSincRepo(idEmpresa);
		Hashtable<String, DataDepositoSAP>  depositosCliente = Logica.encuentraDarDepositosSAP(idEmpresa);
		//List<ArticuloReposicion> listaArticulosaClientesMayoristas = Logica.darVentaLineasMayorista(depositosCliente);
		List<ArticuloReposicion> listaArticulosaClientesMayoristas = null;//Logica.darArticuloRepoVM(depositosCliente,idEmpresa);
		Logica.borrarVM(idEmpresa);
		if(listaArticulosaClientesMayoristas.isEmpty())
		{
			Logica.actualizarSincRepo(0,idSinc,idEmpresa);
		}
		else
		{
			Logica.actualizarSincRepo(0,idSinc,idEmpresa);
		}
		if(!listaArticulosaClientesMayoristas.isEmpty())
		{
			for (ArticuloReposicion articulo : listaArticulosaClientesMayoristas) 
			{				
				
				String justificacion = "Solicitud de pedido por Venta por mayor";
				articulo.setOrigen(99);
				Logica.guardarLineaReposicion(articulo, justificacion, idSinc,1,idEmpresa);
			}
		}
	}
	
	
	
	public void Sincronizar(boolean central, int idEmpresa, int depCentral) 
	{
		Logica Logica = new Logica();
		
		Hashtable<String, Integer> stocksEnviados= Logica.DarStocksEnviadosRepo(idEmpresa);//traigo pendientes
		
		Hashtable<String, Integer> stocksCentral = new Hashtable<>();
		int horas=0;
		String excluirDeps="";
		
		try{
			PropertiesHelper pH=new PropertiesHelper("periodoRepo");
			pH.loadProperties();
			horas = Integer.parseInt(pH.getValue("horas"));
			pH=new PropertiesHelper("ExcluirDepsRepo");
			pH.loadProperties();
			excluirDeps = pH.getValue(idEmpresa+"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Fecha fecha = Logica.darUltimaSincronizacionRepo(central);
		Fecha fecha = new Fecha(0,0,0,-horas);
		System.out.println(fecha.darFechaAnio_Mes_Dia());
		String fechaCons = fecha.darFechaToMSSQL();
		
		List<DataIDIDDescripcion> filtrosReglas = Logica.darFiltrosReglasReposicion(idEmpresa);
		List<DataReglaReposicion> reglas = Logica.DarReglasReposicion(idEmpresa);
		
		
		int idSinc = Logica.darNextSincRepo(idEmpresa);
		int porcentaje = 0;
		
		List<ArticuloReposicion> listaArticulosVendidos = 
				Logica.darVentaLineasReposicion(fechaCons, idSinc,porcentaje, central,stocksEnviados,excluirDeps,depCentral,idEmpresa);
		
		if(listaArticulosVendidos.isEmpty())
		{
			Logica.actualizarSincRepo(0,idSinc,idEmpresa);
		}
		else
		{
			Logica.actualizarSincRepo(1,idSinc,idEmpresa);
		}
		
		
		if(listaArticulosVendidos.isEmpty())
		{
			Logica.actualizarLogSincRepo(idSinc, "INFO: No se han obtenido ventas.",porcentaje,idEmpresa);
			Logica.actualizarLogSincRepo(idSinc, "INFO: El proceso Finalizara...",porcentaje,idEmpresa);
			
		}
		else
		{
			
			Logica.actualizarLogSincRepo(idSinc, "INFO: Se han obtenido "+listaArticulosVendidos.size()+" Lineas de ventas.", porcentaje,idEmpresa);
			int pasada =0;
			for (ArticuloReposicion articulo : listaArticulosVendidos) 
			{
				System.out.println(articulo.getArticulo()+" "+articulo.getSucursal());
				DataReglaReposicion regla = null;
				try
				{
					Hashtable<Integer,DataReglaReposicion> reglasArticulo = new Hashtable<>();
					Hashtable<Integer, String> valoresParaFiltro = new Hashtable<>();
					valoresParaFiltro.put(0, articulo.getArticulo());
					valoresParaFiltro.put(1, articulo.getArticuloBaseColor());
					valoresParaFiltro.put(2, articulo.getArticuloBase());
					valoresParaFiltro.put(3, articulo.getTemporada()+"");//temporada
					valoresParaFiltro.put(4, articulo.getClase()+"");
					valoresParaFiltro.put(5, articulo.getMarca()+"");
					valoresParaFiltro.put(6, articulo.getCategoria()+""); 
					valoresParaFiltro.put(7, articulo.getGenero()+"");
					valoresParaFiltro.put(8, articulo.getSeccion()+"");
					valoresParaFiltro.put(10, "---");
					/*verifico que filtros tiene*/
					for (DataReglaReposicion re : reglas) 
					{
						Hashtable<Integer, DataIDDescripcion> filtros = re.getFiltros();
						boolean agregala = false;
						boolean todasIn = true;
						for (int i = 0; i < 11; i++)// porque tengo 10 filtros 
						{
							if(filtros.get(i)!=null)
							{
								
								if(filtros.get(i).getDescripcion().equals(valoresParaFiltro.get(i)))
								{
									if(re.getDestinos().get(articulo.getSucursal())!=null && re.getMinCentral()<=articulo.getStockCentral())
									{
										/*si la regla tiene a ese local*/
										//reglasArticulo.put(regla.getIdRegla(), regla);
										agregala = true;
									}
								}
								else
								{
									todasIn = false;
								}
							}
						}
						if(todasIn && agregala)
						{
							/*si la regla tiene a ese local*/
							reglasArticulo.put(re.getIdRegla(), re);
						}
					}
					List<DataReglaReposicion> reglasArticuloList = new ArrayList<>(reglasArticulo.values());
					Collections.sort(reglasArticuloList);
					try
					{
						regla = reglasArticuloList.get(0);
						System.out.println(articulo.getArticulo() +" Regla primera: "+ regla.getIdRegla());
						articulo.setMaxLocal(regla.getMaxLocal());
						articulo.setMinCentral(regla.getMinCentral());
					}
					catch (Exception e)
					{
						articulo.setMaxLocal(0);
						articulo.setMinCentral(0);
						System.out.println("no hay reglas para el articulo:"+articulo.getArticulo());
					}
					
					pasada++;
					porcentaje = (pasada * 100)/listaArticulosVendidos.size();
					//total------100
					//pasada----x
					int cantidadCentral = 0;
					if(stocksCentral.get(articulo.getArticulo())==null)
					{
						cantidadCentral = articulo.getStockCentral();
						stocksCentral.put(articulo.getArticulo(), cantidadCentral-(articulo.getMaxLocal()-articulo.getStockLocal()));
						
					}
					else
					{
						cantidadCentral = stocksCentral.get(articulo.getArticulo());
						stocksCentral.put(articulo.getArticulo(), cantidadCentral-(articulo.getMaxLocal()-articulo.getStockLocal()));
					}
					
					
					int cantidadEnviar = articulo.getMaxLocal()-articulo.getStockLocal();
					
					if(cantidadEnviar>0 && cantidadEnviar<=cantidadCentral && cantidadCentral>=articulo.getMinCentral())
					{
						
						String justificacion = "Stock en Central:" +articulo.getStockCentral()+"<br/> Stock en el Local "+articulo.getStockLocal()+"<br/> Regla: "+regla.getNombre()+"<br/>Min en Centr?l: "+articulo.getMinCentral()+" <br/> Max en el local "+articulo.getMaxLocal()+" <br/> diferencia "+cantidadEnviar;
						articulo.setVenta(cantidadEnviar);
						cantidadCentral+=cantidadCentral-articulo.getVenta();
						articulo.setOrigen(9000);
						Logica.guardarLineaReposicion(articulo, justificacion, idSinc,0,idEmpresa);
						
					}
					else
					{
						Logica.actualizarLogSincRepo(idSinc, articulo.getArticulo()+" "+articulo.getSucursal() +" no se envia porque cantidadCentral="+cantidadCentral+" stock en local"+articulo.getStockLocal() + " max local "+ articulo.getMaxLocal(),0,idEmpresa);
					}
					
					
					
				}//try
				catch(Exception e)
				{
					e.printStackTrace();
				}//catch
			}//for
		}//else
	}//void

	public void SincronizarDistribuciones(int idEmpresa){
		try {
			Logica Logica = new Logica();
			System.out.println("");
			int idSincro = Logica.darNextSincRepo(idEmpresa);
			Logica.actualizarSincRepo(1,idSincro,idEmpresa);
			//int ultimaDistr = Logica.darNextSincDistr();
			Hashtable<String, Integer> stocksEnviados= Logica.DarStocksEnviadosRepo(idEmpresa);//traigo pendientes
			Hashtable<String, String> distribucionesActivas = Logica.NoRemplazarDistribuciones(idEmpresa);	//distr parciales
			Logica.darArticuloRepoOrderClosed(idEmpresa);
			/*
			String query = " SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT DISTINCT(idCliente), cliente FROM dbo.encuentra_distribucion ARTS\r\n" + 
					"where ARTS.destino =0 and origen = 9000 order by ARTS.idCliente asc";
			
			List <DataIDDescripcion> lista = MSSQL.darArticuloRepoFromDistr(query, idEmpresa);
			
			for(DataIDDescripcion depos : lista)
			{
				DepositoAdmin depoA = new DepositoAdmin();
				depoA.setId(depos.getId());
				depoA.setNombre(depos.getDescripcion());
				depoA.setDireccion("");
				depoA.setLogin(0);
				
				int idDepo = Logica.encuentraAltaDepoA(depoA,idEmpresa);
				// despu?s hacer que si existe no intente los inserts 
				depoA.setEstanteriaPickingYExpedicion(idEmpresa);
			}
			*/
			
			String query = " SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select ART.IdArticulo,ART.Descripcion,ARTS.\"Cantidad pendiente\",ART.IdMarca,0,ART.IdClase, ART.IdGenero,ART.IdCategoria, "+
			"ART.IdTemporada,ARTS.comentario,ARTS.destino,ARTS.idcliente,ARTS.cliente,ARTS.NumeroDoc, ARTS.Fecha  from encuentra_Articulos ART INNER JOIN encuentra_distribucion ARTS ON ARTS.idarticulo = ART.IdArticulo "+
					"where ARTS.destino not in (1200,1400,0) and origen = 9000 order by ARTS.NumeroDoc asc";
			
			int tipo = 1;
			MSSQL.darArticuloRepoFromDistr(query, idSincro,stocksEnviados,0,distribucionesActivas,idEmpresa,tipo);
			
			//poner cliente como destino
			query = " SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select ART.IdArticulo,ART.Descripcion,ARTS.\"Cantidad pendiente\",ART.IdMarca,0,ART.IdClase, ART.IdGenero,ART.IdCategoria, "+
					"ART.IdTemporada,ARTS.comentario,ARTS.idCliente,ARTS.idcliente,ARTS.cliente,ARTS.NumeroDoc, ARTS.Fecha  from encuentra_Articulos ART INNER JOIN encuentra_distribucion ARTS ON ARTS.idarticulo = ART.IdArticulo "+
							"where ARTS.destino =0 and origen = 9000 order by ARTS.NumeroDoc asc";
					
			tipo = 3;
			MSSQL.darArticuloRepoFromDistr(query, idSincro,stocksEnviados,0,distribucionesActivas,idEmpresa,tipo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}//clase






















