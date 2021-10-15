package dataTypes;

import java.util.List;





public class DataMovimientoStockLocales 
{
	private int origen,destino,razon;
	private List<DataIDDescripcion> lista;
	
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public List<DataIDDescripcion> getLista() {
		return lista;
	}
	public void setLista(List<DataIDDescripcion> lista) {
		this.lista = lista;
	}
	public DataMovimientoStockLocales(int origen, int destino, List<DataIDDescripcion> lista, int razon) 
	{
		this.origen = origen;
		this.destino = destino;
		this.lista = lista;
		this.razon = razon;
	}
	
	public boolean Grabar(String comentario)
	{
		boolean msg = false;
		
		switch (this.origen) 
		{
			/*
			case 1:
			{
				WS01 ws = new WS01();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 2:
			{
				WS02 ws = new WS02();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 3:
			{
				WS03 ws = new WS03();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 4:
			{
				WS04 ws = new WS04();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 5:
			{
				WS05 ws = new WS05();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 6:
			{
				WS06 ws = new WS06();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 7:
			{
				WS07 ws = new WS07();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 8:
			{
				WS08 ws = new WS08();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 9:
			{
				WS09 ws = new WS09();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 10:
			{
				WS10 ws = new WS10();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
				
			}
			case 11:
			{
				WS11 ws = new WS11();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 12:
			{
				WS12 ws = new WS12();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 14:
			{
				WS14 ws = new WS14();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 15:
			{
				WS15 ws = new WS15();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 16:
			{
				WS16 ws = new WS16();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 17:
			{
				WS17 ws = new WS17();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 18:
			{
				WS18 ws = new WS18();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 19:
			{
				WS19 ws = new WS19();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 20:
			{
				WS20 ws = new WS20();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 21:
			{
				WS21 ws = new WS21();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			case 22:
			{
				WS22 ws = new WS22();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
				
			}
			case 23:
			{
				WS23 ws = new WS23();
				msg = ws.movimientoDeStock(this.origen, this.destino, this.razon, this.lista, comentario);
				break;
			}
			
			*/
		}
				
				
		return msg;		
	}
	
	

}
