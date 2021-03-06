import java.util.List;

import beans.Usuario;
import logica.Logica;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;
import dataTypes.trackingPedido;


public class GetTrackingsEC 
{
	public static void main(String[] args) 
	{	
		Logica logica = new Logica();
		Usuario u = logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");		
		int idEmpresa = u.getIdEmpresa();
		
		Call_WS_analoga call = new Call_WS_analoga(); 		
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		
		List<trackingPedido> trackings = logica.PedidosATrackear(idEmpresa);
		for(trackingPedido t:trackings) {
			DataIDDescripcion track = api.getTracking(t, idEmpresa);
			System.out.println("**********"+t.getCourier()+"**********");
			System.out.println("**********"+track.getDescripcion()+"**********");
			if(track != null && track.getDescripcion() != null) {
				if(track.getDescripcion().toUpperCase().equals("ENTREGADO")) {
					
					DataArticuloEcommerceVerifR r = logica.darArticuloEcommerceReqReclasifica(t.getIdPedido(),0,idEmpresa);
					if(t.getDestinoFinal() == 0) {
						r.setFechaEntrega(track.getDescripcionB());
						r.setEstadoEncuentra(6);
						logica.updateEcommerceEstadoFecha(t.getIdPedido(), 6, idEmpresa, u.getNumero(),track.getDescripcionB());
					}
					else {	//envios de una tienda a otra desde modo. Si el courier dice que entrego el paquete en realidad significa que entrego en la tienda
						r.setEstadoEncuentra(5);
						logica.updateEcommerceEstadoFecha(t.getIdPedido(), 5, idEmpresa, u.getNumero(),track.getDescripcionB());
					}					
					
					logica.CambioEstadoMarketPlace(idEmpresa, r);
					
				}
				/*
				else if(track.getDescripcion().toUpperCase().equals("RETIRADO") && t.getCanal() > 7) {
					DataArticuloEcommerceVerifR r = logica.darArticuloEcommerceReqReclasifica(t.getIdPedido(),0,idEmpresa);
					r.setEstadoEncuentra(4);
					logica.CambioEstadoMarketPlace(idEmpresa, r);
					logica.updateEcommerceEstadoFecha(t.getIdPedido(), 4, idEmpresa, u.getNumero(),track.getDescripcionB());
				}
				*/
			}
			
		}
		
		
		TestEstadoPedidos estadosFenicio = new TestEstadoPedidos();
		estadosFenicio.main(null);
	
	}	

	public GetTrackingsEC() {
	}
	
	
	
	
	
	
	

}
