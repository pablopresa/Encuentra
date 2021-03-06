import java.util.ArrayList;
import java.util.List;

import beans.Fecha;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import jsonObjects.SendMail;
import logica.Logica;
import logica.PedidosAtr;
import logica.Utilidades;

public class NotificaPedidosAtrasados {

	public static void main(String[] args) {
		NotificaPedidosAtrasados npa = new NotificaPedidosAtrasados();
		npa.envioMails(4, "2021-06-01");
	}
	
	public void envioMails(int idEmpresa, String fecha) {
		Logica logica = new Logica();
		List<PedidosAtr> pedidosAtrasados = new ArrayList<>();
		int horas = 0;
		int dias = 0;
		try {
			horas = logica.darDataIdDescripcion("select valor,'' from empresa_parametro where idParametro=32 and idEmpresa="+idEmpresa).getId();
			dias = horas / 24;
		} catch (Exception e) {
			System.out.println("Error trayendo margen de horas de pedidos atrasados");
		}
		
		try{
			String query = "SELECT DISTINCT p.idPedido,p.stampTime,if(p.ML=0,p.descripcion, "+
					"SUBSTRING(p.descripcion,7)),r.idArticulo,r.Deposito, r.CantidadRequerida, "+
					"if(r.CTimeStamp!=r.RTimeStamp AND confirmado=1,r.CTimeStamp,if(ra.Justificacion LIKE '%no encontrado%','NO ENCONTRADO','--')) confirmacion, "+
					"IFNULL(if(c.telefono LIKE '+598%',REPLACE(c.telefono,'+598','0'),c.telefono),''),ifnull(c.mail,'')  "+
					"from ecommerce_pedido p   "+
					"inner join ecommerce_pedido_articulos_req r on p.idpedido=r.idpedido AND p.idEmpresa=r.idEmpresa "+
					"LEFT OUTER JOIN ecommerce_import_clientes c ON c.idPedido=p.idPedido AND c.idEmpresa=r.idEmpresa   "+
					"LEFT OUTER JOIN reposicion_articulos ra ON ra.Seccion = r.idPedido AND ra.idArticulo=r.idArticulo AND ra.Origen=r.Deposito AND ra.IdEmpresa=r.IdEmpresa "+
					"WHERE p.EstadoEncuentra = 1   "+
					"AND p.cancelado=0 AND p.stampTime > '"+fecha+"' AND   "+
					"r.CantidadProcesasa!=r.CantidadRequerida AND   "+
					"CONVERT(SUBSTRING_INDEX(timediff(CURRENT_TIMESTAMP(),p.stampTime),':',1),  "+
					"UNSIGNED INTEGER) > CONVERT((select valor from empresa_parametro where idParametro=32 and idEmpresa="+idEmpresa+"), UNSIGNED INTEGER) "+
					" AND p.idEmpresa = " + idEmpresa +
					" ORDER BY p.stampTime";
			
			pedidosAtrasados = logica.darListaPedidoATR(query);
			
			String total = "SELECT count(p.idPedido),'' from ecommerce_pedido p "+
                    "WHERE "+
                    "p.cancelado=0 AND "+
                    "CONVERT(SUBSTRING_INDEX(timediff(CURRENT_TIMESTAMP(),p.stampTime),':',1),  "+
                    "UNSIGNED INTEGER) <= CONVERT((select valor from empresa_parametro where idParametro=32 and idEmpresa="+idEmpresa+"), UNSIGNED INTEGER) "+
                    "AND p.IdEmpresa = "+idEmpresa;
			int cantidadPedidos = 0;
			try {
				cantidadPedidos = logica.darListaDataIdDescripcionConsulMYSQL(total).get(0).getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String body1 = 
					"	<table style=\"height: 36px; width: 100%; border-collapse: collapse; margin-left: auto; margin-right: auto;\" border=\"1\"> "+
					"	  <tbody> "+
					"	    <tr style=\"height: 28px; background-color: #ff6600;\"> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Pedido</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Fecha</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Cliente</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Contacto</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Mail</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Articulo</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Deposito <br> Pedido</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Unidades</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 11%; text-align: center; height: 28px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Confirmacion</span></strong></h3> "+
					"	      </td> "+
					"	    </tr> ";
					
		String body2 = "";
		
		
		
		/*for (PedidosAtr p : pedidosAtrasados) 
		{
			body2+=
		
					"	    <tr style=\"height: 28px; border-color: #000000; text-align: center;\"> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getIdPedido()+"</td> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getFecha()+"</td> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getCliente().toUpperCase()+"</td> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getTel()+"</td> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getMail()+"</td> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getArticulo()+"</td> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getDeposito()+"</td> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getCantidad()+"</td> "+
					"	      <td style=\"width: 11%; height: 28px;\">"+p.getFechaConfirmacion()+"</td> "+
					"	      </td> "+
					"	    </tr> ";
		}	*/
		String ultimoIdPedido = "";
		String ultimoCliente = "";
		String ultimoMail = "";
		String ultimoTelefono = "";
		String ultimaFecha = "";
		String primerArticulo = "";
		String lineaPrimerArticulo = "";
		String clavePrimerArticulo = "";
		
		if(pedidosAtrasados.size() > 0) {
			ultimoIdPedido = pedidosAtrasados.get(0).getIdPedido();
			ultimoCliente = pedidosAtrasados.get(0).getCliente().toUpperCase();
			ultimoMail = pedidosAtrasados.get(0).getMail();
			ultimoTelefono = pedidosAtrasados.get(0).getTel();
			ultimaFecha = pedidosAtrasados.get(0).getFecha();
			primerArticulo = pedidosAtrasados.get(0).getArticulo();
			clavePrimerArticulo = primerArticulo+ultimoIdPedido+ultimoCliente;
			lineaPrimerArticulo = ""
					+ "  <td style=\"width: 11%; height: 28px;\">"+pedidosAtrasados.get(0).getArticulo()+"</td>" 
					+ "  <td style=\"width: 11%; height: 28px;\">"+pedidosAtrasados.get(0).getDeposito()+"</td> " 
					+ "  <td style=\"width: 11%; height: 28px;\">"+pedidosAtrasados.get(0).getCantidad()+"</td> " 
					+ "  <td style=\"width: 11%; height: 28px;\">"+pedidosAtrasados.get(0).getFechaConfirmacion()+"</td> "
					+ ""; 
		}
		int contadorSpan = 1;
		String lineas = "";
		int countPedidos = 0;
		
		for (PedidosAtr p : pedidosAtrasados) 
		{
			if(p.getIdPedido().equals("4470098854")) {
				System.out.println("");
			}
			if(!(p.getArticulo()+p.getIdPedido()+p.getCliente()).equalsIgnoreCase(clavePrimerArticulo)) { //para evitar que agregue nuevamente el primer articulo (lineaPrimerArticulo)
				if(ultimoIdPedido.equalsIgnoreCase(p.getIdPedido()) && ultimoCliente.equalsIgnoreCase(p.getCliente().toUpperCase())) {
					contadorSpan++;
					lineas += " <tr style=\"height: 28px; border-color: #000000; text-align: center;\"> "
								+ "  <td style=\"width: 11%; height: 28px;\">"+p.getArticulo()+"</td>" 
								+ "  <td style=\"width: 11%; height: 28px;\">"+p.getDeposito()+"</td> " 
								+ "  <td style=\"width: 11%; height: 28px;\">"+p.getCantidad()+"</td> " 
								+ "  <td style=\"width: 11%; height: 28px;\">"+p.getFechaConfirmacion()+"</td> "
								+ "</tr>";
				} else {
					countPedidos++;
					//if(contadorSpan > 1) { contadorSpan++; }
					String cabezal = "<tr style=\"height: 28px; border-color: #000000; text-align: center;\"> "
									+ "<td rowspan="+contadorSpan+" style=\"width: 11%; height: 28px;\">"+ultimoIdPedido+"</td> "
									+ "<td rowspan="+contadorSpan+" style=\"width: 15%; height: 28px;\">"+ultimaFecha+"</td> "
									+ "<td rowspan="+contadorSpan+" style=\"width: 11%; height: 28px;\">"+ultimoCliente+"</td> "
									+ "<td rowspan="+contadorSpan+" style=\"width: 11%; height: 28px;\">"+ultimoTelefono+"</td> "
									+ "<td rowspan="+contadorSpan+" style=\"width: 11%; height: 28px;\">"+ultimoMail+"</td>"
									+ lineaPrimerArticulo
									+ "</tr>";
					lineas = cabezal + lineas;
					body2+= lineas;
					contadorSpan = 1;
					ultimoIdPedido = p.getIdPedido();
					ultimoCliente = p.getCliente().toUpperCase();
					ultimoMail = p.getMail();
					ultimoTelefono = p.getTel();
					ultimaFecha = p.getFecha();
					primerArticulo = p.getArticulo();
					lineas = "";
					lineaPrimerArticulo = ""
							+ "  <td style=\"width: 11%; height: 28px;\">"+p.getArticulo()+"</td>" 
							+ "  <td style=\"width: 11%; height: 28px;\">"+p.getDeposito()+"</td> " 
							+ "  <td style=\"width: 11%; height: 28px;\">"+p.getCantidad()+"</td> " 
							+ "  <td style=\"width: 11%; height: 28px;\">"+p.getFechaConfirmacion()+"</td> "
							+ ""; 
				}
			}
		}	
	
		String body3 = "	  </tbody> "+
					"	</table> "+
					"	<p>&nbsp;</p> "+
					"	<p>&nbsp;</p> "+
					"	<p>&nbsp;</p> "+
					"	<p>&nbsp;</p> "+
					"	<p style=\"text-align: center;\">Notificacion generada automaticamente por Encuentra</p>";
		 
		String body0 = "<h2 style=\"text-align: center;\"><strong>Los siguientes pedidos llevan mas de "+dias+" d?a en estado de ?tems pedidos.</strong></h2> "+
				"<h3 style=\"text-align: center;\">"+countPedidos+" pedidos atrasados de un total de "+cantidadPedidos+".</h3> ";
		 	
			Utilidades util = new Utilidades();
			String mailsD = util.darParametroEmpresaSTR(idEmpresa, 6);	 
			//String mailsD = "onviera@200.com.uy"; 
			List<SendMail> mails = new ArrayList<>();			
			Fecha f = new Fecha(0,0,0);
			SendMail mail = new SendMail("ATRREY"+f.darFechaString(), mailsD, "PEDIDOS ATRASADOS!!!", body0+body1+body2+body3, "encuentra@200.com.uy");
			mails.add(mail);
			
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();			
			api.PutColaEnvioMails(mails, idEmpresa);
		 
		}
		catch(Exception e){
			 
			System.out.println("Error");
		}

	}

}
