package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import beans.Usuario;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import logica.EnviaMail;
import logica.Logica;
import logica.Utilidades;
import persistencia._EncuentraConexion;

public class EcommerceProcessOrders {

	private String ok="";
	
	
	
	public EcommerceProcessOrders() 
	{
	}

	/***********************NEGAR PAR 
	 * @throws Exception *****************************/
	public void negarSKU(String idArticulo, Long idPedido, int iddepoCancela, String motivo, int cantidad, int idEmpresa, 
			boolean pickup, boolean devuelveStock, Usuario uLog) throws Exception
	{
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Logica.rechazarArticuloReq(idArticulo,idPedido,iddepoCancela,motivo,idEmpresa, pickup, devuelveStock);
		
		Logica.logPedido(idPedido, uLog.getNumero(), 1, "Rechazando pedido de articulo "+idArticulo+" por parte del deposito "+iddepoCancela,0,idEmpresa); 
		
		
	}
	 
	/***********************Confirmar par *****************************/
	public void confirmarSKU(String idArticulo, int iddepoConfirma, int cantidad, Long idPedido, int idEmpresa, Usuario u)
	{
		Logica Logica = new Logica();
		Logica.CambioSubestadoEcommercePedido(idPedido, idArticulo, 2,iddepoConfirma,idEmpresa);
		Logica.confirmarArticuloReq(idArticulo,idPedido,iddepoConfirma,idEmpresa);
		
		Logica.logPedido(idPedido, 0, 2, "Confirmando envio de articulo "+idArticulo+" por parte del deposito "+iddepoConfirma,0,idEmpresa);
		if(todosConfirmados(idPedido,idEmpresa))
		{
			Logica.updateEcommerceEstado(idPedido, 2,idEmpresa,u);
			
		}
		
		
	}
	@SuppressWarnings("unlikely-arg-type")
	public void confirmarSKUxDistrib(String idArticulo, int iddepoConfirma, int cantidad, int idEntrega, int idEmpresa, Usuario u, Long idVenta)
	{
		Logica Logica = new Logica();
		Long idPedido = new Long("0");
		if(idEmpresa == 2) {
			idPedido = Logica.darIdPedidoXDistri(idEntrega, idEmpresa);			
		}
		if(idPedido.equals(new Long("0"))) {
			idPedido = idVenta;
		}
		
		Logica.CambioSubestadoEcommercePedido(idPedido, idArticulo, 2,iddepoConfirma,idEmpresa);
		int confirmados = 0;
		confirmados = Logica.confirmarArticuloReqForus(cantidad, idArticulo, iddepoConfirma, idPedido,idEmpresa);
		
		if(confirmados > 0) {
			Logica.logPedido(idPedido, 0, 2, "Confirmando envio de articulo "+idArticulo+" por parte del deposito "+iddepoConfirma,0,idEmpresa);
			if(todosConfirmados(idPedido,idEmpresa))
			{
				Logica.updateEcommerceEstado(idPedido, 2,idEmpresa,u);			
			}
		}
		else {
			Logica.logPedido(idPedido, 0, 2, "Se intento confirmar el envio de "+cantidad+" unidad/es del articulo "+idArticulo+" por parte del deposito "+iddepoConfirma+", pero el mismo ya estaba confirmado",0,idEmpresa);
		}
		
	}
	/***********************Confirmar par sin idPedido *****************************/
	public void confirmarSKU(String idArticulo, int iddepoConfirma, int cantidad,int idEmpresa, Usuario u)
	{
		Logica Logica = new Logica();
		List<Long> pedidosConfirmados =  Logica.confirmarArticuloReq(cantidad,idArticulo,iddepoConfirma,idEmpresa);
		
		
		for (Long idPedido : pedidosConfirmados) 
		{
			Logica.logPedido(idPedido, 0, 2, "Confirmando envio de articulo "+idArticulo+" por parte del deposito "+iddepoConfirma,0,idEmpresa);
			if(todosConfirmados(idPedido,idEmpresa))
			{
				Logica.updateEcommerceEstado(idPedido, 2,idEmpresa,u);
				
			}
		}		
	}
	
	
	public String query_confirmarSKUForus(String idArticulo, int iddepoConfirma, int cantidad, Long pedido, int idEmpresa, int idUsuario,List<DataIDDescripcion> estados)
	{
		Logica Logica = new Logica();
		Long idPedido= new Long(pedido);
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE `ecommerce_pedido_articulos_req` SET  CTimeStamp=CURRENT_TIMESTAMP(), cantConfirmada=cantConfirmada+"+cantidad+" WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+pedido+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoConfirma+";");
		
		sb.append(Logica.query_logPedido(idPedido, 0, 2, "Confirmando envio de "+cantidad+" unidad/es del articulo "+idArticulo+" por parte del deposito "+iddepoConfirma,0,idEmpresa));
		
		
		
		return sb.toString();
		
		
	}
	
	
	public void confirmarSKUForus(String idArticulo, int iddepoConfirma, int cantidad, Long pedido, int idEmpresa, Usuario u)
	{
		Logica Logica = new Logica();
		Long idPedido= new Long(pedido);
		int confirmados = 0;
		confirmados = Logica.confirmarArticuloReqForus(cantidad,idArticulo,iddepoConfirma,pedido,idEmpresa);
		
		if(confirmados>0) {
			Logica.logPedido(idPedido, 0, 2, "Confirmando envio de "+cantidad+" unidad/es del articulo "+idArticulo+" por parte del deposito "+iddepoConfirma,0,idEmpresa);
			if(todosConfirmados(idPedido,idEmpresa))
			{
				Logica.updateEcommerceEstado(idPedido, 2,idEmpresa,u);
				
			}
		}
		else {
			Logica.logPedido(idPedido, 0, 2, "Se intento confirmar el envio de "+cantidad+" unidad/es del articulo "+idArticulo+" por parte del deposito "+iddepoConfirma+", pero el mismo ya estaba confirmado",0,idEmpresa);
		}
		
		
	}
	
	public boolean todosConfirmados(Long idPedido,int idEmpresa)
	{
		Logica Logica = new Logica();
		return Logica.confirmarArticuloReq(idPedido,null,idEmpresa);
		
		
	}
	
	public static void main(String[] args) 
	{
		/*
		List <DataIDDescripcion> lista = new ArrayList<>();
		//caracter especial
		//?
		Logica.ecommerceSincFacturas();
		int[]pedidosINRep = {1576897467,1572937466,1577156782,1573769354,1577420733,1576099282,1577267677,1577236111};
		for (int i : pedidosINRep) 
		{
			Logica.procesarOrdenPedido(i);
		}
		Logica.procesarOrdenesML();
		process(null,0,0,0);
		Call_WS_analoga ws = new Call_WS_analoga();
		
		ws.getPedidosAllDebug();
		
		//process(null,0,0,0);
		/*
		String[] articulosNO = {"001.33922010633.0","001.50060011025.0","004.23703661131.0","009.05738882221.0","009.05738882226.0","009.74509610710.0","041.16739029301.0","045.10112100007.5","047.1J493146040.0","047.1J495299439.0","047.1J495618539.0","047.J472X000232.0","047.U264X000139.0","049.08145230006.0","051.73499011201.0","051.73589001101.0","053.00114102238.0","053.01115006433.0","058.NEREA000441.0","069.10001553109.0","069.11016145604.0","069.12837679019.0","070.01851006425.0","070.08122848024.0","070.10915200322.0","070.11719800032.0","071.03153000438.0","095.31112020640.0","131.00100061030.0","132.25927530817.0","132.81709700539.0","139.80348010523.0","139.81552411620.0","139.81693000820.0","146.02201100038.0","146.06102100036.0","146.07685015737.0","146.07685800040.0","146.12000100036.0","146.14309499937.0","146.14309499938.0","146.16024100036.0","146.26011072537.0","146.27198045438.0","146.30926100037.0","146.30926200037.0","146.50043513037.0","146.52720100036.0","146.80070380141.0","146.BESSY100041.0","184.30RM2000107.5","184.COAC3687106.0"};
		int [] cantidades = {1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		int idDepoNO = 99;
		
		
		for (int i = 0; i < articulosNO.length; i++) 
		{
			String desc = articulosNO[i];
			int cantidad = cantidades[i];
			DataIDDescripcion d = new DataIDDescripcion(cantidad,desc);
			d.setDescripcionB(idDepoNO+"");
			lista.add(d);
			
		}
		//List <DataIDDescripcion> lista = Logica.darListaDataIdDescripcionCancelaAR("select * from ecommerce_pedido_articulos_req where  idPedido in (45267,45244,43132,1536193703,45464,42862,45245,43670,45418,45088,1534163423,44848,1531919308,1535864785,44995,45229,45543,44971,44844,45406,43742,44240,45494,1535070106,1535409621,45005,1533874787,45327,44025,44628,1534886498,44965,1535319294,43524,43794,45570,1535799112,1535109780,45419,45451,45362,45513,44496,1531793604,45404,45407,1533495138,1533866444,1533926089,45414,44665,45335,1535562651,1535913412,45356,1533236377,45224,43688,45394,45488,43438,45238,43565,44353,45273,45382,1535726760,43499,1535038806,45297,45219,1535677178,43690,45441,44124,1536051177,1536060765,1536063375,45333,1533528290,1535080404,1533740494,1536267475,43013,1535878037,45478,45264,44638,45210,1534085492,1533892089,45179,1534448805,45420,1536198047,45377,1534165687,44915,45306,1533596453,45457,1533885679,1533887295,45415,43965,45131,1532857069,44026,1536261103,1535743339,45055,45508,45524,45400,1533809115,45507,45370,1533916781,45480,1533055329,45525,45128,44448,43122,45503,1535660369,45285,45399,45539,1533899035,45226,1534990206,45425,1533720705,1535602592,45258,1533379374,1533382518,1533748567,1533780432,1533917054,1534248830,1534835532,1534869488,1534925460,1534926074,1534929562,1534969281,1535325071,1535341510,44557,45461,45518,45531,1534153379,45160) and CantidadProcesasa =0");
		
		
		
		for (DataIDDescripcion a : lista) 
		{
			Logica.rechazarArticuloReqSP(a.getDescripcion(), idDepoNO, "");
		}
		EcommerceProcessOrders.process(null,0,idDepoNO,0);
		
		/*
		Call_WS_analoga c = new Call_WS_analoga();
		String URLbase = "https://www.stadium.com.uy/tracking/";
		// 
		
		 int[] etique = {42814,42866,42937,42966,43057,43096,43101,43118,43132,43216,43225,43255,43305,43327,43336,43365,43373,43386,43403,43450,43459,43469,43470,43471,43472,43473,43475,43476,43479,43481,43487,43492,43494,43495,43496,43503,43504,43505,43506,43507,43509,43513,43515,43523,43526,43529,43531,43533,43537,43542,43543,43545,43547,43548,43553,43555,43560,43562,43563,43565,43566,43570,43573,43578,43579,43582,43584,43587,43598,43600,43607,43609,43610,43612,43617,43618,43622,43624,43626,43628,43629,43630,43639,43640,43641,43642,43647,43649,43650,43657,43660,43663,43667,43669,43670,43677,43682,43684,43690,43700,43706,43711,43716,43718,43719,43723,43724,43726,43727,43729,43732,43734,43736,43739,43742,43743,43746,43749,43750,43754,43755,43758,43760,43761,43766,43774,43776,43781,43783,43789,43791,43792,43794,43796,43797,43799,43800,43801,43804,43808,43812,43814,43822,43830,43836,43839,43853,43858,43863,43871,43876,43878,43881,43883,43885,43890,43891,43896,43897,43900,43902,43907,43908,43912,43916,43919,43930,43933,43943,43947,43954,43956,43959,43961,43965,43966,43978,43982,43983,43985,43986,43988,43989,43990,43992,43997,44003,44006,44008,44010,44011,44015,44017,44036,44038,44042,44048,44058,44065,44069,44072,44074,44077,44085,44087,44088,44089,44090,44097,44102,44105,44108,44110,44117,44118,44120,44123,44125,44126,44129,44132,44135,44137,44138,44141,44147,44148,44150,44151,44152,44153,44154,44155,44156,44157,44158,44160,44162,44164,44168,44171,44173,44174,44175,44183,44184,44188,44190,44191,44194,44196,44201,44203,44207,44210,44212,44214,44216,44218,44222,44226,44228,44231,44235,44241,44242,44244,44249,44252,44263,44266,44271,44281,44283,44286,44287,44289,44295,44309,44314,44325,44327,44337,44340,44345,44349,44353,44356,44359,44360,44362,44365,44367,44370,44371,44374,44381,44383,44389,44391,44395,44403,44405,44407,44408,44415,44416,44420,44421,44424,44426,44431,44439,44444,44451,44456,44460,44464,44470,44476,44483,44486,44487,44489,44490,44493,44494,44497,44500,44504,44508,44510,44512,44514,44516,44520,44521,44525,44531,44540,44544,44546,44548,44550,44551,44556,44560,44561,44562,44563,44576,44581,44584,44590,44595,44596,44606,44607,44611,44613,44618,44622,44623,44624,44628,44637,44638,44644,44646,44647,44648,44653,44654,44656,44657,44658,44663,44664,44665,44667,44670,44674,44677,44686,44691,44697,44709,44715,44718,44721,44742,44744,44766,44768,44771,44774,44776,44777,44779,44781,44784,44796,44798,44802,44804,44806,44811,44817,44822,44824,44825,44829,44835,44839,44840,44845,44846,44855,44866,44870,44871,44872,44873,44883,44886,44889,44892,44895,44904,44909,44911,44912,44914,44922,44924,44927,44928,44929,44931,44932,44937,44946,44947,44948,44950,44962,44963,44966,44967,44969,44972,44974,44978,44990,44991,44993,45000,45003,45008,45012,45018,45022,45028,45030,45032,45034,45040,45043,45044,45045,45052,45055,45059,45060,45061,45064,45068,45071,45076,45078,45084,45087,45088,45089,45092,45096,45098,45099,45102,45103,45107,45108,45110,45117,45119,45124,45132,45137,45138,45143,45145,45147,45148,45151,45157,45166,45173,45174,45180,45182,45184,45187,45194,45198,45200,45203,45209,45211,45216,45219,45226,45229,45231,45235,45239,45244,45245,45249,45253,45258,45259,45263,45264,45267,45278,45283,45285,45290,45300,45301,45311,45316,45323,45325,45328,45332,45333,45335,45344,45346,45347,45350,45366,45370,45372,45382,45388,45391,45395,45396,45397,45399,45400,45401,45404,45406,45407,45412,45414,45415,45418,45419,45420,45421,45422,45425,45449,45451,45452,45454,45458,45460,45463,45464,45465,45467,45469,45471,45472,45475,45476,45480,45494,45500,45506,45507,45521,45525,45526};
		 for (int i = 0; i < etique.length; i++) 
		 {
			int idPedido = etique[i];
			
			String funcion = "/get/etiqueta?idCompra="+idPedido;
			String retorno = c.callWSGET(URLbase, funcion);
			
			RspEtiqueta et =JSONReader.readJsonPedidoEti(retorno); 
			
			EncuentraPedido ep = new EncuentraPedido();
			ep.setIdPedido(idPedido);
			ep.setUrlEtiqueta(et.getEtiqueta());
			
			ep.updateEtiqueta();
			
			 
			 
		 }
		 
		/*
		
		
		System.out.println(et.getEtiqueta());
		
		
		
		int printerID = 309829;
		
		//String jotason = "{ \"printerId\":218257, \"title\": \"Prueba_encuentra\", \"contentType\": \"pdf_uri\", \"content\": \"http:\\/\\/encuentra.200.com.uy\\/supernow\\/pdf\\/"+documento+".pdf\", \"source\": \"encuentra\" }";
		String jotason = "{ \"printerId\":"+printerID+", \"title\": \"Prueba_encuentra\", \"contentType\": \"pdf_uri\", \"content\": \""+et.getEtiqueta()+"\", \"source\": \"encuentra\" }";
		retorno = c.callWSPOSTPrint(jotason);
		
		System.out.println(retorno);
		
		*/
		
		/*
		WSCommunicate sw = new WSCommunicate();	
		sw.testGrabarCliente();
		//sw.testGrabarOV();
		
		List<DataIDDescripcion>lista = new ArrayList<>();
		lista.add(new DataIDDescripcion(1,"051.62405060243.0"));
		 */
		
		/*
		sw.movimientoDeStock(3, 71, 1, lista, "prueba");
		//TRAER LOS ALMACENES, SIN LOS DEPOSITO_NO
		*/
		
		//PEDIR LOS PARES DE NUEVO
		
		
		//int [] depositos = {1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,21,22,23,25,26,27,28,31,41};
		/*
		int [] depositos = {25};
		List<DataIDDescripcion> movimientoStock = new ArrayList<>();
		movimientoStock.add(new DataIDDescripcion(1, "260.00001000101.0"));
		int razon = 30;
		int destino = 99;
		
		for (int i = 0; i < depositos.length; i++) 
		{
			int idDep = depositos[i];
			DataMovimientoStockLocales dm = new DataMovimientoStockLocales(idDep, destino, movimientoStock,razon); 
			dm.Grabar("Par pedido venta web, Movimiento generado por encuentra");
			
		}
		*/
		
		
		
		

	}
	
	 
	
	
	
	
	
	
	
	
	public static List<DataIDDescDescripcion> process(String idArticulo,Long idPedidoNO,List<DataIDDescripcion> iddepoCancela, int cantidad, int idEmpresa) 
	{
		Logica Logica = new Logica();
		
		/*****************************************html de mails a locales***********************************************/
		String bodyM_1= ""+
		"<p>&nbsp;</p> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='padding: 10px 0 30px 0;'> "+
			"	<table style='border: 1px solid #cccccc; border-collapse: collapse;' border='0' width='600' cellspacing='0' cellpadding='0' align='center'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;' align='center' bgcolor='#70bbd9'> "+
			"	<p>Pedido de articulos facturados por la web</p> "+
			"	</td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td style='padding: 40px 30px 40px 30px;' bgcolor='#ffffff'> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'><strong>Hola @mail@, estamos necesitando ciertos articulos que hemos vendido y se encuentran en su tienda.</strong></td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td style='padding: 20px 0px 30px; color: #153643; font-family: Arial,sans-serif; font-size: 16px; line-height: 20px; text-align: center;'> "+
			"	<p>A continuaci&oacute;n encontrar&aacute; una lista con los items que se vendieron.<br /> <span style='color: #ff0000;'><strong>El cliente ya los pag&oacute;</strong></span>, solamente necesitamos que nos confirmen que los los envian.</p> "+
			"	<a href='@URL_Confirm@' style='text-decoration: none; color: black'> "+
			"	<div style='padding:9px;background-color: #ee4c50;width: 50%; height: 60px; margin: auto;'> "+
			"	<strong>Confirmar Envio de Articulos</strong> "+
			"	</div> "+
			"	</a> "+
			"	</td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td valign='top' width='260'> "+
			"	<table style='text-align: center; border-collapse: collapse;' border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='border: 1px solid silver;'>Imagen del articulo</td> "+
			"	<td style='border: 1px solid silver;'>Nombre del articulo</td> "+
			"	<td style='border: 1px solid silver;'>Cantidad Solicitada</td> "+
			"	<td style='border: 1px solid silver;'>Pedido</td> "+
			"	</tr>";
		
		
		String bodyM_b = "</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"<tr> "+
					"<td style='padding: 30px 30px 30px 30px;' bgcolor='#ee4c50'> "+
					"<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
					"<tbody> "+
					"<tr> "+
					"<td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>Notificaci&oacute;n generada automaticamente por encuentra<br />No es necesario que responda a esta direcci&oacute;n de correo.</td> "+
					"<td align='right' width='25%'> "+
					"<table border='0' cellspacing='0' cellpadding='0'> "+
					"<tbody> "+
					"<tr> "+
					"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
					"<td style='font-size: 0; line-height: 0;' width='20'>&nbsp;</td> "+
					"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"</td> "+
					"</tr> "+
					"</tbody> "+
					"</table> "+
					"<!--analytics--> "+
					"<p>&nbsp;</p>";
		/*******************************************fin html*********************************************/
		
		
		/*son los mail de cada local a los que se le envia las notificaciones*/
		Hashtable<Integer, String> contactosLocales = new Hashtable<>();
		/*son los articulos que se vendieron y no se pidieron*/
		List<DataIDDescripcion> articulos = new ArrayList<>();
		/*********
CAMpos del obj:Id		descripcion			DescripcionB
		  	CANTIDAD	idArticulo			PEDIDOS(separados por coma)
			1			009.04307662435.0	59386
		 */
		
		/****************son los depositos a los cuales pedir mercaderias**********************/
		List <DataIDDescripcion> prioridades = new ArrayList<>();
		
		List<DataIDDescDescripcion> articulosPedido = new ArrayList<>();
		if(idArticulo!=null)
		{
			articulos.add(new DataIDDescripcion(cantidad,idArticulo));
			articulos.get(0).setDescripcionB(idPedidoNO+"");
			articulosPedido.add(new DataIDDescDescripcion(cantidad, idPedidoNO+"", idArticulo));
			prioridades = Logica.darDepositosProcessEcommerce(iddepoCancela,idEmpresa);
		}
		else
		{
			articulos = Logica.darArticulosProcessEcommerce(idEmpresa);//articulos sin pedir agrupados en articulos
			articulos.remove(0);
			prioridades = Logica.darDepositosProcessEcommerce(iddepoCancela,idEmpresa); //locales donde pedir
			articulosPedido=Logica.darArticulosOrderProcessEcommerce(idEmpresa); //pedidos sin pedir sin agrupar
		}
		
		
		/*************YA no se usa mas la lineas de articulos que pedimos y no se confirmaron.******************/
		//List <DataIDDescripcion> ArtsPedidosNoConfirmados = Logica.darStocksPedidosProcessEcommerce(null);
		/*
		 * for (DataIDDescripcion s : ArtsPedidosNoConfirmados) 
			{
										   //articulo:deposito
				stockPedidos.put(s.getDescripcion()+":"+s.getDescripcionB(), s.getId());
			}
		 */
		//id = cantidad Pendiente
		//descB = pedido
		//descripcion=articulo
		
		
		Hashtable<String, Integer> stockPedidos = new Hashtable<>();
		/*********se usa para la consulta de stock contra Visual Store******/
		String articulosIn = "";
		
		List<DataIDDescDescripcion> articulosPedidoWHS = new ArrayList<>();
		
		for (DataIDDescripcion a : articulos) 
		{
			articulosIn+="'"+a.getDescripcion()+"',";
		}
		
		
		
		if(articulosIn.equals(""))
		{
			
		}
		else
		{
			
			articulosIn=articulosIn.substring(0,articulosIn.length()-1);
			
			
			/*********se usa para la consulta de stock contra Visual Store******/
			String depositosIn = "";
			
			for (DataIDDescripcion d : prioridades) 
			{
				depositosIn+=""+d.getDescripcion()+",";
				contactosLocales.put(Integer.parseInt(d.getDescripcion()), d.getDescripcionB());
			}
			
			depositosIn=depositosIn.substring(0,depositosIn.length()-1);
			
			
			String priodidadesIn = " case deposit ";
			
			for (DataIDDescripcion d : prioridades) 
			{
				priodidadesIn+=" when "+d.getDescripcion() +" then "+d.getId() ;
			}
			priodidadesIn+=" end ";
			
			 
			String consultaStock = " SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select * from "+  
									" (SELECT stock-stockPedido+stockPendiente Stock,deposit,idArticulo, "+
										priodidadesIn+" prioridad from dbo.encuentra_stock ms where ms.Stock>0 AND deposit in ("+depositosIn+") and IdArticulo in ("+articulosIn+")) t0 "+
									" order by t0.prioridad ";
					
			
			System.out.println(consultaStock);
			
			List<DataIDDescDescripcion> stocks = Logica.darStockOrderItems(consultaStock, stockPedidos);
			//id = stock
			//descripcion = deposito
			//desc=articulo
			
			
			
			/*******por cada articulo, te hace una lista de dataIdDesc que representa los depositos y cantidades a los que pidio*/
			Hashtable<String, List<DataIDDescripcion>> articulosPedidos = new Hashtable<String, List<DataIDDescripcion>>();
			
			
			/**********estoy recorriendo los articulos de los pedidos*********/
			for (DataIDDescripcion art : articulos) 
			{
				/**************son los id de pedido de ese articulo, porque el mismo se pudo haber vendido en 2 pedidos, por eso pregunta si contiene coma***********************************/
				List<String> pedidosArt = new ArrayList<>();
				
				if(art.getDescripcionB()!=null && art.getDescripcionB().contains(","))
				{
					String [] arreglo = art.getDescripcionB().split(",");
					for (int i = 0; i < arreglo.length; i++) 
					{
						pedidosArt.add(arreglo[i]);
						
					}
				}
				else
				{
					pedidosArt.add(art.getDescripcionB());
				}
				
				for (String p : pedidosArt) 
				{
					try
					{
						Logica.logPedido(Long.parseLong(p), 0, 0, " Pidiendo articulo "+art.getDescripcion(),0,idEmpresa);
					}
					catch(Exception e){}
				}
				
				int cantPedida=art.getId();
				String arti = art.getDescripcion();
				
				/***
				 * lista de depositos a los que se le debe pedir ese articulo
				 */
				List<DataIDDescripcion> depositosPedir = new ArrayList<>();
				
				int cantidadPendiente=cantPedida;
				
				
				for (DataIDDescDescripcion s : stocks) 
				{
					if(cantidadPendiente==0)
					{
						break;
					}
					else if(s.getDesc().equals(arti))//si son el mismo articulo
					{
						if(s.getId()>=cantidadPendiente)//preginto si tiene mas stock del que le pido
						{													//cant pedida,	id de tienda			
							DataIDDescripcion depoPido = new DataIDDescripcion(cantidadPendiente,s.getDescripcion());
							depositosPedir.add(depoPido);
							
							for (String p : pedidosArt) 
							{
								try
								{
									Logica.logPedido(Long.parseLong(p), 0, 0, " Pidiendo articulo "+art.getDescripcion()+" a deposito "+depoPido.getDescripcion(),0,idEmpresa);
								}
								catch(Exception e){}
							}
							
							
							cantidadPendiente=0;
							break;
						}
						else if(s.getId()>0)//no alcanza para todo lo que pidio pero hay mas de 0
						{
							DataIDDescripcion depoPido = new DataIDDescripcion(s.getId(),s.getDescripcion());//le saco todo lo que tiene
							depositosPedir.add(depoPido);
							cantidadPendiente-=s.getId();
							
							for (String p : pedidosArt) 
							{
								try
								{
									Logica.logPedido(Long.parseLong(p), 0, 0, " SUPUESTAMENTE - Pidiendo articulo "+art.getDescripcion()+" a deposito "+depoPido.getDescripcion()+ " porque el deposito no tiene stock suficiente",0,idEmpresa);
								}
								catch(Exception e){}
							}
						}
					}
				}
				
				
				
					articulosPedidos.put(arti, depositosPedir);
				
				
				
				
			}//cierro el for de articulos en pedidos
			
			
			/************recorro los pedidos uno por uno sin agrupar por articulo******************/
			for (DataIDDescDescripcion ap : articulosPedido) 
			{
				
				try
				{
					
					//id = cantidad Pendiente
					//desc = pedido
					//descripcion=articulo
					//descII= depositoPedido
					
					String arti=ap.getDescripcion();
					int cantR = ap.getId();
					String idPedido = ap.getDesc();
					
					/****************articulos a los que le debo pedir******************/
					List<DataIDDescripcion> artsWhs = articulosPedidos.get(arti);
					
					
					/****************EN CASO QUE NO HAYA STOCK EN NINGUN LADO******************/
					if(artsWhs==null){
						DataIDDescDescripcion pedir = new DataIDDescDescripcion();
						pedir.setId(cantR);
						pedir.setDesc(idPedido);
						pedir.setDescII("0");//depopsito al cual lo pide cuando no hay stock
						pedir.setDescripcion(arti);
						
						articulosPedidoWHS.add(pedir);
					}
					/*******************************************************************/
					
					
					List<DataIDDescripcion> artsWhsSobran = new ArrayList<>();
					int cantPend=cantR;
					
					for (DataIDDescripcion ad : artsWhs) 
					{
						System.out.println();
						if(cantPend>0)
						{
							if(ad.getId()==cantPend)
							{
								DataIDDescDescripcion pedir = new DataIDDescDescripcion();
								pedir.setId(cantPend);
								pedir.setDesc(idPedido);
								pedir.setDescII(ad.getDescripcion());//depopsito al cual lo pide
								pedir.setDescripcion(arti);
								
								articulosPedidoWHS.add(pedir);
								cantPend=0;
							}
							else if(ad.getId()>cantPend)
							{
								DataIDDescDescripcion pedir = new DataIDDescDescripcion();
								pedir.setId(cantPend);
								pedir.setDesc(idPedido);
								pedir.setDescII(ad.getDescripcion());
								pedir.setDescripcion(arti);
								
								articulosPedidoWHS.add(pedir);
								
								artsWhsSobran.add(new DataIDDescripcion(ad.getId()-cantPend,ad.getDescripcion()));
								cantPend=0;
							}
							else if(ad.getId()<cantPend)
							{
								DataIDDescDescripcion pedir = new DataIDDescDescripcion();
								pedir.setId(ad.getId());//cantidad
								pedir.setDesc(idPedido);//pedido
								pedir.setDescII(ad.getDescripcion());//depPedido
								pedir.setDescripcion(arti);//articulo
								
								articulosPedidoWHS.add(pedir);
								
								cantPend-=ad.getId();
							}
						}
						else
						{
							artsWhsSobran.add(ad);
						}
						
						
					}
					
					if(cantPend > 0)
					{
						/**************si cantidad pendiente es mas que 0, es que no hay donde pedir ese articulo, lo guardamos como pedido al deposito 0***********************/
						DataIDDescDescripcion pedir = new DataIDDescDescripcion();
						pedir.setId(cantPend);
						pedir.setDesc(idPedido);
						pedir.setDescII("0");//depopsito al cual lo pide cuando no hay stock
						pedir.setDescripcion(arti);
						
						articulosPedidoWHS.add(pedir);
					}
					
					articulosPedidos.put(arti, artsWhsSobran);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				
				
			}/*********fin de for sobre articulos del pedido***/
			
			
			
			try 
			{
				
				Hashtable<Integer, Integer> htDepositosPedidos = new Hashtable<>();
				
				List<DataIDDescripcion> pedido9000 = new ArrayList<>();
									
				for (DataIDDescDescripcion pe : articulosPedidoWHS) 
				{
					/**if(pe.getDescripcion().equals("001.06003000240.0"))
					{
						System.out.println("debagueame");
					}**/
					
					try
					{
						htDepositosPedidos.put(Integer.parseInt(pe.getDescII()), Integer.parseInt(pe.getDescII()));
						//id = cantidad Pendiente
						//desc = pedido
						//descripcion=articulo
						//descII= depositoPedido
						
						if(pe.getDescII().equals("9000"))
						{
							pedido9000.add(new DataIDDescripcion(pe.getId(), pe.getDescripcion()));
						}
						
						System.out.println(pe.getDesc() +"\t"+ pe.getDescripcion() +"\t"+ pe.getId() +"\t"+ pe.getDescII());
						
						Logica.logPedido(Long.parseLong(pe.getDesc()), 0, 0, " Guardando pedido de articulo "+pe.getDescripcion()+" a deposito "+pe.getDescII(),0,idEmpresa);
						
						
						
						Logica.persistir("INSERT INTO `ecommerce_pedido_articulos_req` (`idPedido`, `idArticulo`, `Deposito`, `CantidadRequerida`, `CantidadProcesasa`, `Confirmado`,`idEmpresa`) " +
								" VALUES ('"+pe.getDesc()+"', '"+pe.getDescripcion()+"', '"+pe.getDescII()+"', '"+pe.getId()+"', '0', '0',"+idEmpresa+");");
					}
					catch(Exception e)
					{
						
					}
					
					
				}
				
				
				
				
				/***************************************agrego los del 9000 al encuentra***********************************/
				if(!pedido9000.isEmpty())
				{
					boolean manual = false;
					Logica.darArticuloRepoFromLoadForus(pedido9000,1200,manual,idEmpresa,9000,2,false);
					int last = Logica.darNextSincRepo(idEmpresa)-1;
					Logica.actualizarOKSincRepo(0,last,idEmpresa);//la dejo en OK 0 para que no interfiera con el picking
				}
				
				
			/**************************************NOTIFICACIONES****************************************/
				//voy a buscar los ariculos que no me confirmaron aun
				
				String deposINN = "";
				for (Integer idDepo : new ArrayList<>(htDepositosPedidos.values())) 
				{
					
					List<String> mailsDestino = new ArrayList<>();
						
					
					String []contactos = contactosLocales.get(idDepo).split(",");
					for (int i = 0; i < contactos.length; i++) 
					{
						mailsDestino.add(contactos[i]);
					}
						
					String primerDestino = mailsDestino.get(0).split("@")[0];
					bodyM_1 = bodyM_1.replace("@mail@", primerDestino);
						
					PropertiesHelper pH=new PropertiesHelper("urlConfirmEcommerce");
					pH.loadProperties();
					String url_confirm = pH.getValue("url");
						
						
					bodyM_1 = bodyM_1.replace("@URL_Confirm@", url_confirm+idDepo);
						
					
					String htmlTablasMedio="";
					
					
					htmlTablasMedio+="<!-- ac? iban los Articulos--> "+
							"	<tr> "+
							"		<td  colspan='4' style='border: 1px solid silver;'>" +
							"  			 ************YA NO SE MUESTRA MAS EL DETALLE************* <br/> POR FAVOR SIGA EL VINCULO PARA VER LOS ARTICULOS" +
							"		</td> "+
							"	</tr> "+
							"	<!--Fin Articulos-->";
						
						EnviaMail.enviarMailHTMLOD("NotificacionesEncuentra@stadium.local", mailsDestino, "Venta Web Solicita articulos a deposito "+idDepo, bodyM_1+htmlTablasMedio+bodyM_b,idEmpresa);
						
						bodyM_1 = bodyM_1.replace(primerDestino, "@mail@");
						bodyM_1 = bodyM_1.replace(url_confirm+idDepo, "@URL_Confirm@");
						
					}
					
				
				
				
				
				
			
			
			}
			catch (Exception e)
			{
				e.printStackTrace();
				
				
			}	
		}
		
		
		
		return articulosPedidoWHS;

	}

	public Hashtable<Long, Long> darPedidosSinglDepo(int idDepo, int idEmpresa, boolean sinClasificar) 
	{
		Hashtable<Long, Long> retorno = new Hashtable<>();
		Logica lo = new Logica();
		String qClasif = sinClasificar ? " and p.estadoEncuentra in (1,2)" : " and p.estadoEncuentra in (3)";
		String q = "SELECT 0, t0.idPedido FROM(\r\n" + 
				"SELECT ER.idPedido, SUM(ER.CantidadRequerida) qreq, idEmpresa FROM ecommerce_pedido_articulos_req  ER WHERE IdEmpresa = "+idEmpresa+" AND Deposito = "+idDepo+" GROUP BY ER.idPedido) t0\r\n" + 
				"INNER JOIN \r\n" + 
				"(\r\n" + 
				"SELECT EA.idPedido, SUM(EA.cantidadPedido) qpe, idEmpresa FROM ecommerce_pedido_articulos EA WHERE IdEmpresa = "+idEmpresa+"  GROUP BY EA.idPedido)\r\n" + 
				"t1 ON t0.idPedido = t1.idPedido "+
				"INNER JOIN ecommerce_pedido p on p.idpedido=t1.idpedido and p.idempresa=t1.idEmpresa "+
				"WHERE t0.qreq=t1.qpe "+qClasif;
		
		List<DataIDDescripcion> pedidos = lo.darListaDataIdDescripcionMYSQLConsulta(q);
		pedidos.remove(0);
		
		for (DataIDDescripcion p : pedidos) 
		{
			Long idPed = Long.parseLong(p.getDescripcion());
			
			retorno.put(idPed,idPed);
		}
		
		
		return retorno;
	}
	
	
	
	
	
	
	

}
