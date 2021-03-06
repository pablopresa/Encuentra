package integraciones.mains.tata;

import java.util.List;

import beans.api.json.SendMail;
import beans.api.json.SendMailSpooler;
import beans.encuentra.Fecha;
import integraciones.erp.ws_Tata.Articulo;
import integraciones.erp.ws_Tata.ws_tata;
import logica.LogicaAPI;

public class SincroPrecios {
	public static void main(String[] args) {
		try {
			ws_tata tata = new ws_tata();
			
			System.out.println("Yendo a buscar articulos...");
			List<Articulo> articulos = tata.precioArticulos();
			
			System.out.println("Persistiendo precios...");
			LogicaAPI.altaArtPrecio(articulos, 8);
			int contador = articulos.size();
			
			System.out.println("Enviando notificacion...");
			String bodyName = "Se sincronizo el precio de "+contador+" items.";
			SendMail mail = new SendMail("PreciosBAS"+new Fecha().darFechaAnio_Mes_Dia(), 
					"onviera@200.com.uy,gmonzon@200.com.uy,florencia.gonzalez@tata.com.uy", 
					"Sincro de precios BAS-Encuentra", bodyName, "encuentra@200.com.uy", null);
			SendMailSpooler mails = new SendMailSpooler();
			mails.setMails(new SendMail[]{mail});
			
			LogicaAPI.PutMailSpooler(mails.getMails(), 8);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
