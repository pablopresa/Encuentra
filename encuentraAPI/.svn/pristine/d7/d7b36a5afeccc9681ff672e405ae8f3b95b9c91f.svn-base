package integraciones.erp.sapBO.stadium;

import integraciones.erp.sapBO.ClienteSap;

public class SincroStadiumSap{
	
	private static final int STADIUM = 1;
	
	public static void main(String[] args) {
		try {

			int idEmpresa = STADIUM;
			
			ClienteSap  cliente = new ClienteSap(idEmpresa);
			
			System.out.println("Yendo a buscar ordenes de venta...");
			obtenerOrdenesDeVenta(cliente);
			System.out.println("Ordenes de venta procesadas.");
			
			System.out.println("Yendo a buscar ordenes de compra...");
			obtenerOrdenesDeCompra(cliente);
			System.out.println("Ordenes de venta procesadas.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void obtenerOrdenesDeVenta(ClienteSap cliente){
		cliente.obtenerOrdenesDeVenta();
	}	
	
	private static void obtenerOrdenesDeCompra(ClienteSap cliente){
		cliente.obtenerOrdenesDeCompra();
	}
}
