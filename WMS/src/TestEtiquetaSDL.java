import java.io.FileNotFoundException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataEcommerce_canales_envio;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.EncuentraPedido;
import jsonObjects.Credenciales;
import jsonObjects.Shipping;
import logica.Logica;


public class TestEtiquetaSDL 
{
	public static void main(String[] args) throws FileNotFoundException, DocumentException 
	{	
		DataArticuloEcommerceVerifR ar = new DataArticuloEcommerceVerifR();
		ar.setIdPedido(new Long("167819"));
		ar.setIdDestino(400000);
		ar.setCanal(1);
		String url = "";
		String track = "";
		boolean hayEtiqueta = false;
		int idEmp = 2;
		Logica Logica = new Logica();
		
		List<DataEcommerce_canales_envio> envios = Logica.darListaEcommerce_canales_envio(ar.getCanal(),idEmp);
		for(DataEcommerce_canales_envio env:envios)
		{
			if((""+ar.getIdDestino()).equals(env.getIddeposito()))
			{	
				Cliente clienteSh = Logica.darClienteShippingEcommerce(ar.getIdPedido(),idEmp);
				Credenciales credenciales = new Credenciales();
				
				credenciales.setUser(env.getUsuario());
				credenciales.setPass(env.getPass());
				credenciales.setPedido(ar.getIdPedido()+"");										
				
				Shipping shipp = new Shipping();
				
				shipp.setCredenciales(credenciales);
				shipp.setCliente(clienteSh);
														
				Call_WS_APIENCUENTRA enc = new Call_WS_APIENCUENTRA();
				DataDescDescripcion etiqueta = enc.setEnvio(shipp, env, ar.getIdPedido()+"", env.getFechaEntrega(), "", idEmp);
				
				if(etiqueta.getDescripcion().contains("http://"))
				{
					ar.setUrlEtiqueta(etiqueta.getDescripcion());	
					url = etiqueta.getDescripcion();
					track = etiqueta.getId();
					hayEtiqueta = true;
				}
																		
				break;
			}
		}
		
		if(hayEtiqueta) {
			hayEtiqueta = true;	
			
			EncuentraPedido p = new EncuentraPedido();
			p.setIdPedido(ar.getIdPedido());
			p.setUrlEtiqueta(url);
			p.updateEtiqueta(0,idEmp);
			
			if(ar.getIdDestino()<9000) {
				p.updateShipping(ar.getIdDestino(), p.getIdPedido()+"","",idEmp);
			}
			else {
				p.updateShipping(ar.getIdDestino(), track,"",idEmp);
			}
			
			ar.setUrlEtiqueta(url);		
			
		}
		
	
	}
	

	public TestEtiquetaSDL() {
	}
	
	
	
	
	
	
	

}
