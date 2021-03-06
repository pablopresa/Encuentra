package integraciones.mains.garrido;

import java.util.List;

import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import integraciones.erp.sapBO.ClienteSap;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class SincroGarridoSap{
	
	private static final int GARRIDO = 10;
	
	public static void main(String[] args) {
		int idEmpresa = GARRIDO;
		
		ClienteSap  cliente = new ClienteSap(idEmpresa);
		
		LogicaAPI logica = new LogicaAPI();
		
		Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
		
		try {			
			if(args[0].equalsIgnoreCase("maestros")) {
				sincroMaestros(cliente, logica, cwa, idEmpresa);				
			}
			else if (args[0].equalsIgnoreCase("ordenes")) {
				sincroOrdenes(cliente, logica, cwa, idEmpresa);
			}		
			
		} catch (Exception e) {
			sincroMaestros(cliente, logica, cwa, idEmpresa);
			sincroOrdenes(cliente, logica, cwa, idEmpresa);
		}
	}
	
	public static List<DataIDDescripcion> obtenerDatos(String funcion, ClienteSap cliente) {
				return cliente.obtenerDatos(funcion);
	}
	
	public static List<DataDescDescripcion> obtenerBarras(ClienteSap cliente){
		return cliente.obtenerBarras();
	}
	
	public static void obtenerOrdenes(ClienteSap cliente){
		cliente.obtenerOrdenesDeVenta();
	}
	
	public static void sincroMaestros (ClienteSap  cliente, LogicaAPI logica, Call_WS_APIENCUENTRA cwa, int idEmpresa) {
			System.out.println("Yendo a buscar clases...");
			List<DataIDDescripcion> clases = obtenerDatos("clases", cliente);
			System.out.println("Insertando clases...");
			cwa.putMaestros(logica.darToken(idEmpresa), clases, "art_clase");
			
			System.out.println("Yendo a buscar categorķas...");
			List<DataIDDescripcion> categorias = obtenerDatos("categorias", cliente);
			System.out.println("Insertando categorķas...");
			cwa.putMaestros(logica.darToken(idEmpresa), categorias, "art_categoria");
			
			System.out.println("Yendo a buscar marcas...");
			List<DataIDDescripcion> marcas = obtenerDatos("marcas", cliente);
			System.out.println("Insertando marcas...");
			cwa.putMaestros(logica.darToken(idEmpresa), marcas, "art_marca");
			
			System.out.println("Yendo a buscar generos...");
			List<DataIDDescripcion> generos = obtenerDatos("generos", cliente);
			System.out.println("Insertando generos...");
			cwa.putMaestros(logica.darToken(idEmpresa), generos, "art_genero");
			
			System.out.println("Yendo a buscar unidades de medida...");
			List<DataIDDescripcion> unidades = obtenerDatos("unidad_medida", cliente);
			System.out.println("Insertando unidades de medida...");
			cwa.putMaestros(logica.darToken(idEmpresa), unidades, "art_uom");
			
			System.out.println("Yendo a buscar codigos de barra...");
			List<DataDescDescripcion> barras = obtenerBarras(cliente);
			System.out.println("Insertando codigos de barra...");
			cwa.putMaestrosBarras(logica.darToken(idEmpresa), barras, "artbarra");
			
			System.out.println("Yendo a buscar artķculos...");
			List<DTO_Articulo> articulos = cliente.obtenerArticulos();
			System.out.println("Insertando artķculos...");
			cwa.putArticulos(logica.darToken(idEmpresa), articulos);
	}
	
	public static void sincroOrdenes (ClienteSap  cliente, LogicaAPI logica, Call_WS_APIENCUENTRA cwa, int idEmpresa) {
			System.out.println("Yendo a buscar ordenes de venta...");
			obtenerOrdenes(cliente);
			System.out.println("Ordenes procesadas.");
	}
}
