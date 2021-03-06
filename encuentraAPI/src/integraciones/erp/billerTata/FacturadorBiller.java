package integraciones.erp.billerTata;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.axis.providers.java.MsgProvider;

import com.sun.xml.wss.saml.internal.saml11.jaxb10.X509DataType.X509Certificate;

import beans.Usuario;
import beans.api.MovStock;
import beans.api.pedidoFactura;
import beans.api.json.SendMail;
import beans.api.json.SendMailSpooler;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.MetodoPago;
import logica.LogicaAPI;
import persistencia._EncuentraConexionAPI2;
import beans.encuentra.Cliente;
import integraciones.erp.visualStore.elrey.central.ClienteWSELREY;
import integraciones.erp.visualStore.elrey.comercioElectronico.ClienteWSfacturacion;
import integraciones.erp.visualStore.elrey.ecommerce.ClienteWS;
import integraciones.erp.visualStore.forus.central.OrdenVentaLinea;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.wms.Call_WS_APIENCUENTRA;



public class FacturadorBiller 
{
	static LogicaAPI lo = new LogicaAPI();
	static String msj, idMail;
	
	
	
	
	public static void main(String[] args) 
	{	
		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(8,8000);
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		int idEmpresa = u.getIdEmpresa();
		FacturadorBiller fb = new FacturadorBiller();
		trustAllCerts();
				
		while (true) {
			msj = "";
			idMail = "";
			try 
			{
				fb.putFacturacion(idEmpresa, 0L);
				
			} catch (Exception e) {System.out.println("Error grabando facturas");}
			
			
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				 
	}
	

	
	private  Hashtable<Integer,pedidoFactura> putFacturacion(int idEmpresa, Long idPedido) 
	{
		Hashtable<Integer,pedidoFactura> pedidos = new Hashtable<>();
		List<OrdenVenta> ordenes = lo.darOrdensSinFacturar(idEmpresa,idPedido);
		
		
		ClienteBiller cliB = new ClienteBiller();
		for(OrdenVenta o:ordenes) 
		{
			try {
				Cliente c= lo.darCliente(o.getIdCarrito(), idEmpresa);

				Client cli = new Client("", c.getNombre(),c.getApellido(), c.getCalle()+" "+c.getNroPuerta(), "", c.getCiudad(), "3", c.getTelefono(), "UY", c.getDocumentoNro(), c.getEmail(), c.getDepartamento());
				
				//MAPEO METODO DE PAGO
				List<MetodoPago> pagos = new ArrayList<>();
				MetodoPago m = new MetodoPago(1, o.getFormaPago().split(" - ")[0].toUpperCase(), "", 0.0);
				pagos.add(m);
				_EncuentraConexionAPI2.darMapeoFormaPago(pagos, idEmpresa);
				m = pagos.get(0);
				//
				
				Payments pay = new Payments(""+o.getImportePago(), m.getIdMetodoPago()+"", o.getFormaPago(), "UYU", "1");
				Payments [] pays = new Payments[] {pay};
				Items[] itms = new Items [o.getOrdenVentaLineas().size()];
				int i = 0;
				for (integraciones.erp.visualStore.objetos.OrdenVentaLinea l : o.getOrdenVentaLineas()) 
				{
					//me falta la descripcion
					
					Double totalAmount = (l.getPrecioImp()-l.getDescuento())*l.getCantidad();
					double sellingPrice = l.getPrecioImp()-l.getDescuento();
					
					Totals tot = new Totals(totalAmount+"",sellingPrice+"");
					Items itm = new Items(l.getIdArticulo(), l.getCantidad()+"", l.getDescripcion(),tot, "EA");
					
					itms[i]=itm;
					i++;
				}
				
				
				VentaBiller vta = new VentaBiller(o.getIdCarrito()+"", pays, cli, "384", "UYU", itms);
				
				
				
				String resp = cliB.grabarFactura(vta);
				
				int fact = 0;
				String msj = "";
				System.out.println("Salida de factura: "+resp);
				if(resp.equals(""))
				{
					fact = 100;
					msj="Esperando";
				}
				else
				{
					msj=resp;
				}
				
				lo.RegistrarFactura(o.getIdCarrito(), fact, idEmpresa,msj);
				
				
				
			} 
			catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		return pedidos;
	}



	public void liberarFactutra(Long idPedido) 
	{
		
		String update = "update ecommerce_import_venta SET sincronizada=0 where idEmpresa=8 AND idVenta="+idPedido+" AND sincronizada=-1";
		lo.persistir(update);
	}
	
	private static void trustAllCerts() {
		TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
			}
			};

			// Install the all-trusting trust manager
			SSLContext sc;
			try {
				sc = SSLContext.getInstance("SSL");
			
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			// 	Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);  
		 
	}
	
	
}
