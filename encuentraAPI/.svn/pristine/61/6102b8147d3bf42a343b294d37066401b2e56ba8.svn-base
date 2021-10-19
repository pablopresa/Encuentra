package integraciones.mains.elRey;

import java.util.ArrayList;
import java.util.List;

import beans.api.json.SendMail;
import beans.api.json.SendMailSpooler;
import beans.datatypes.DataIDDescripcion;
import beans.helper.PropertiesHelper;
import integraciones.erp.odoo.laIsla.ClienteOdoo_LaIsla;
import logica.LogicaAPI;

public class MailShopify {

	public MailShopify() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<DataIDDescripcion> lista = LogicaAPI.darEtiquetasClientesShopify();
		ArrayList<SendMail> mails = new ArrayList<>();
		String cuerpo = "<p>Estimado cliente: <br>\r\n" + 
				"Enviamos en adjunto la factura de su compra <strong>WEBXXXX</strong>.</p>\r\n" + 
				"<p>En caso de tener alguna consulta o necesitar factura con RUT, agradecemos que nos contactes a <a href=\"mailto:contacto@elreydelentretenimiento.com\">contacto@elreydelentretenimiento.com</a>.<br>\r\n" + 
				"Brindando los siguientes datos: Razón Social, RUT, Dirección, Localidad o Departamento.</p>\r\n" + 
				"<p>Este correo incluye un documento en formato PDF, por lo para poder abrir el mismo correctamente debe tener un programa instalado que lea este tipo de archivos.</p>\r\n" + 
				"<p>Gracias por comprar en <strong>EL REY DEL ENTRETENIMIENTO</strong>.</p>";

		for(DataIDDescripcion DID: lista) {
			String cuerpoPedido = cuerpo.replace("WEBXXXX", String.valueOf(DID.getId()));
			String adjunto = "";
			try {
				PropertiesHelper ph = new PropertiesHelper("paths");
				ph.loadProperties();
				adjunto = DID.getDescripcionB().replace(ph.getValue("HTTP_pdf"),ph.getValue("pdf"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			SendMail mail = new SendMail(String.valueOf(DID.getId()),DID.getDescripcion(),"Factura de pedido WEB"+DID.getId()+"",cuerpoPedido,"comunicaciones@elrey.uy", adjunto);
			mails.add(mail);
		}
		
		try {
			LogicaAPI.PutMailSpooler(mails.toArray(new SendMail[mails.size()]), 4);
			for(SendMail SM: mails) {
				LogicaAPI.RegistrarEnvioMailShopify(Integer.parseInt(SM.getId()));
			}
			
		}catch(Exception e)		{
			
		}		
		
		
	}

}
