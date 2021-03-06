package integraciones.erp.odoo.laIsla;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.StockDeposito;
import beans.encuentra.DepositoMayorista;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;
import persistencia._EncuentraConexionAPI2;

public class SincroLaIsla {

	public static void main(String[] args) {
		//String[] nombresMetodos = {"almacenes", "categorias","marcas","talles","colores","temporadas","generos","productos","obtenerStock"};
		String[] nombresMetodos = {"almacenes", "categorias","marcas","talles","colores","temporadas","generos","productos"};
		//String[] nombresMetodos = {"obtenerStock"};
		
		Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
		LogicaAPI logica = new LogicaAPI();
		List<DataIDDescripcion> datos = null;
		List<DTO_Articulo> datosArt = null;
//		SincroLaIsla s = new SincroLaIsla();
		String token = logica.darToken(6);
		for (String tabla : nombresMetodos) {
			switch (tabla) {
				case "almacenes":
					System.out.println("BUSCANDO ALMACENES...");
					datos = obtenerDatosGenericos(tabla);
					List<DepositoMayorista> depos = new ArrayList<>();
					for (DataIDDescripcion de : datos) 
					{
						DepositoMayorista  dps = new DepositoMayorista(de.getId()+"",de.getDescripcion() ,de.getDescripcion(), "LOCAL" );
						depos.add(dps);
					}
					
					System.out.println("INSERTANDO ALMACENES...");
					cwa.putDeposM(token, depos);
					break;
			
				case "categorias":
					System.out.println("BUSCANDO CATEGORIAS...");
					datos = obtenerDatosGenericos(tabla);
					System.out.println("INSERTANDO CATEGORIAS...");
					cwa.putMaestros(token, datos, "art_categoria");
					break;

				case "marcas":
					System.out.println("BUSCANDO MARCAS...");
					datos = obtenerDatosGenericos(tabla);
					System.out.println("INSERTANDO MARCAS...");
					cwa.putMaestros(token, datos, "art_marca");
					break;

				case "talles": //No existe
					//datos = s.obtenerDatosGenericos(tabla);
					//cwa.putMaestros(token, datos, "art_cagoria");
					break;

				case "colores":
					System.out.println("BUSCANDO COLORES...");
					datos = obtenerDatosGenericos(tabla);
					System.out.println("INSERTANDO COLORES...");
					cwa.putMaestros(token, datos, "art_colors");
					break;

				case "temporadas":
					System.out.println("BUSCANDO TEMPORADAS...");
					datos = obtenerDatosGenericos(tabla);
					System.out.println("INSERTANDO TEMPORADAS...");
					cwa.putMaestros(token, datos, "art_temporadas");
					break;

				case "generos":
					System.out.println("BUSCANDO GENEROS...");
					datos = obtenerDatosGenericos(tabla);
					System.out.println("INSERTANDO GENEROS...");
					cwa.putMaestros(token, datos, "art_genero");
					break;
			
				case "productos":
					try {
						System.out.println("BUSCANDO PRODUCTOS...");
						datosArt = obtenerDatosArticulos();
						System.out.println("INSERTANDO PRODUCTOS...");
						cwa.putArticulos(token, datosArt);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
					default:
						break;
					
			/*	case "obtenerStock":
					try {
						System.out.println("BUSCANDO STOCKS...");
						List<StockDeposito> arts = s.obtenerStockArticulos();
						System.out.println("INSERTANDO STOCKS...");
						cwa.putStk(token, arts);
					} catch (Exception e) {}
					break;*/
			}
		}
	}
				
	
	
	public static List<DataIDDescripcion> obtenerDatosGenericos(String tabla) {
		List<DataIDDescripcion> datos = new ArrayList<>(); 
		try {
			
			LaIsla li = new LaIsla();
			String json = li.obtenerDatos(tabla);
			System.out.println(json);
			Gson gson = new Gson();
			DatosGenericos[] lecturas = gson.fromJson(json, DatosGenericos[].class);
			for (DatosGenericos dg : lecturas) 
			{
				DataIDDescripcion did = new DataIDDescripcion(dg.getId(), validarDescripcion(dg.getName()));
				datos.add(did);
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return datos;
		}
		return datos;
	}
	
	
	public static List<DTO_Articulo> obtenerDatosArticulos() {
		//List<DTO_Articulo> datos = new ArrayList<DTO_Articulo>(); 
		List<DTO_Articulo> datos = new ArrayList<>();
		try {
			
			LaIsla li = new LaIsla();
			String json = li.obtenerDatos("productos");
			//System.out.println(json);
			Gson gson = new Gson();
			Articulo_Encuentra[] lecturas = gson.fromJson(json, Articulo_Encuentra[].class);
			for (Articulo_Encuentra ae : lecturas) {
				
				List<String> codigos = new ArrayList<>();
				String ean = ae.getEan13();
				System.out.println("ENTRA EN LECTURAS CON EAN "+ean);
				if(ean!=null && !ean.equals("")) {					
					codigos.add(ean);
					if(ean.charAt(0)=='0') {
						codigos.add(ean.substring(1));
					}				
				}
				DTO_Articulo art = new DTO_Articulo(ae.getDefault_code(), validarDescripcion(ae.getName()), ae.getTalle_proveedor(),
							ae.getColor_proveedor(), ae.getTemporada()+"", "0", ae.getSeller_ids()+"", ae.getMarca()+"", "0", ae.getCategoria()+"", ae.getSexo()+"", codigos);
				datos.add(art);
			}	
			//System.out.println(gson.toJson(datos));
		} catch (Exception e) 
		{
			Gson gson = new Gson();
			System.out.println(gson.toJson(datos));
			e.printStackTrace();
			return datos;
		}
		return datos;
	}
	
	public List<StockDeposito> obtenerStockArticulos() {
		//List<DTO_Articulo> datos = new ArrayList<DTO_Articulo>(); 
		List<StockDeposito> artsStock = new ArrayList<>();
		try {
			
			LaIsla li = new LaIsla();
			artsStock = li.obtenerStockArticulos();

		} catch (Exception e) 
		{
			e.printStackTrace();
			return artsStock;
		}
		return artsStock;
	}
	
	public static String validarDescripcion(String descripcion){
			if(descripcion.contains("'")) {
				descripcion = descripcion.replace('\'', ' ');
			}
			if(descripcion.contains("\"")) {
				descripcion = descripcion.replace('"', ' ');
			}
			if(descripcion.contains("`")) {
				descripcion = descripcion.replace('`', ' ');
			}
		return descripcion;
	}
	
	

	
	
	
}
