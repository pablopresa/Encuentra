package integraciones.mains.stadium;

import java.util.Hashtable;
import java.util.List;

import beans.api.MovStock;
import beans.api.pedidoFactura;
import beans.datatypes.DataIDDescripcion;

import integraciones.erp.visualStore.stadium.tienda.clienteWSVS.WSCommunicate;
import logica.LogicaAPI;

public class TransaccionesSTD 
{
	public static void main(String[] args) 
	{	
		LogicaAPI lo = new LogicaAPI();
		int idEmpresa = 1;
		
		while (true) 
		{
			
			String msj = "";
			String idMail = "";
			
			
			List<MovStock> movs = lo.queuePendingMovsStock(idEmpresa,0);
			
			WSCommunicate cl = new WSCommunicate();
			for (MovStock m : movs) 
			{
				boolean confirmada = false;
				DataIDDescripcion sVisual;
				int numeroDoc = 0;
				String descVisual = "";
				String error = "";
				int unidades = 0;
				LogicaAPI Logica= new LogicaAPI();
				boolean sinstock = false;
				
				sVisual= cl.GrabarTransferenciaTienda(m.getOrigen(), m.getDestino(), m.getDetalle(), m.getObservacion(), (short)m.getOrigen(), (short)(m.getOrigen()+2000), 
						(short)m.getOrigen(), 11111119L, confirmada,  m.getObservacion(), m.getRazon());
				numeroDoc = sVisual.getId();
				descVisual = sVisual.getDescripcion();
				
			}
			
			
					
					/*
					
					if(devolucion) 
					{
						comentario = "*Automatico* devolucion por venta "+idPedido;
						aux = origen;
						origen = destino;
						destino = aux;			
						destinoImpresion = destino;
						
						sVisual= cl.GrabarTransferenciaTienda(origen, destino, lista, comentario, (short)origen, (short)rs.getInt(6), 
								(short)origen, rs.getLong(8), confirmada,  comentario, rs.getInt(11));
						numeroDoc = sVisual.getId();
						descVisual = sVisual.getDescripcion();
						
						idCola = cl.encolarTransferenciaTienda(origen, destino, lista, comentario, (short)origen, (short)rs.getInt(6), 
								(short)origen, rs.getLong(8), true, comentario, rs.getInt(11), idPedido+"",numeroDoc,descVisual);
					}
					else 
					{
						sVisual= cl.GrabarTransferenciaTienda(origen, destino, lista, comentario, (short)rs.getInt(5), (short)rs.getInt(6), 
								(short)rs.getInt(7), rs.getLong(8), confirmada,  rs.getString(10), rs.getInt(11));
						numeroDoc = sVisual.getId();
						descVisual = sVisual.getDescripcion();
						
						System.out.println(origen);					
						if(descVisual!= null && descVisual.contains("sin Stock") && origen!=71) {
						  sinstock = true;
						  int banderaLog = 0;
						  
						  for(DataIDDescripcion art: lista) {
							  idPedido = rs.getString(12);
							  int estadoConfirmacion = movimientoConfirmado(new Long(idPedido+""), art.getDescripcion(), origen);
							  if(estadoConfirmacion == 0) {
								  banderaLog++;
								  if(banderaLog==1) {
									  idCola = cl.updateColaTransferenciaTienda(rs.getInt(1),-1,descVisual+". Se pedira a otro deposito");
								  }
								  EcommerceProcessOrders pro = new EcommerceProcessOrders();
								  pro.negarSKU(art.getDescripcion(), Long.parseLong(idPedido), origen, "Sin stock automatico", art.getId(),false);
							  }					  
						  }								  
						}
						else {
							idCola = cl.updateColaTransferenciaTienda(rs.getInt(1),numeroDoc,descVisual);
						}
					}
					
					
					if(numeroDoc!=0)
					{					
						  unidades = 0;
						  for(DataIDDescripcion d: lista) {
							  unidades += d.getId(); 
						  }
						  ImpresionesPDF ip = new ImpresionesPDF();
						  lista = Logica.DescripcionArticulos(lista);
						  ip.imprimirTicketMovStock(origen, destino, rs.getInt(8), comentario, lista, numeroDoc, destinoImpresion);
						  error += ". Movimiento numero:"+numeroDoc+" al deposito: "+destino+" por "+unidades+" unidades";
						  
					}
					else if(!sinstock)
						{
							error += ". Movimiento no generado, Se guarda en cola de pendientes con ID "+idCola;
						}
					  
						if(!error.equals("")) {
							Logica.logPedido(rs.getLong(12), 0, 0,error ,0);
						}
					  	
				} catch (Exception e) {
					e.printStackTrace();
				}
			*/
		}
	}

	
	
}
