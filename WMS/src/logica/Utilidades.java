package logica;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.cert.CertificateException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.sun.xml.wss.saml.internal.saml11.jaxb10.X509DataType.X509Certificate;

import beans.ArticuloLineaReposicion;
import beans.Fecha;
import beans.MovStock;
import beans.Usuario;
import beans.bulto;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataPicking;

import beans.encuentra.Tarea;

import beans.encuentra.IPrint;

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import helper.PropertiesHelper;
import jsonObjects.SendMail;
import main.EcommerceProcessOrders;
import persistencia.MSSQL;
import persistencia._EncuentraConexion;

public class Utilidades 
{
	public Utilidades(){}

	public List<Integer> darSeries(int cuantos, String uso)
	{
		
		String insert = "insert into utilseries (IdTipo) values ";
		String values = "";
		for (int i = 0; i < cuantos; i++) 
		{
			values+="('"+uso+"'),";
		}
		
		values = values.substring(0, values.length()-1);
		
		Logica l = new Logica();
		return l.darSeries(insert+values);
		
		
	}
	
	
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	
	public HttpSession darDummySesion(String id)
	{
		HttpSession retorno = new HttpSession() {
			
			@Override
			public void setMaxInactiveInterval(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeValue(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeAttribute(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putValue(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isNew() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void invalidate() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String[] getValueNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getValue(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public HttpSessionContext getSessionContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServletContext getServletContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getMaxInactiveInterval() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getLastAccessedTime() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return id;
			}
			
			@Override
			public long getCreationTime() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Enumeration getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getAttribute(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		return retorno;
		
		
	}
	

	
	public int guardarTarea(Tarea tarea, Usuario uLog, int idEmpresa, HttpSession session, Logica log, int idPicking, boolean repoDesdeBultos, int cantidadMinima,
			boolean bultosParciales, boolean tomaDesdeAlmacen, boolean prioridadReservaBultos, boolean elevadoristas) 
	{

		LogicaBulto logicaB = new LogicaBulto();
		LogicaBultoGeneral logicaBG = new LogicaBultoGeneral();
		if(tarea.getIdDeposito() < 2){
			tarea.setIdDeposito(Integer.parseInt(uLog.getDeposito()));
		}
		
		int idMain = log.encuentraAltaTarea(tarea, false, uLog.getNumero(),idEmpresa,false );
		
		
		List<DataLineaRepo> articulosInDLR = new ArrayList<>();
		for (ArticuloLineaReposicion art : tarea.getArticulosIn()) 
		{
			DataLineaRepo li = new DataLineaRepo();
			li.setIdArticulo(art.getIdArticulo());
			li.setIdDepDestino(art.getDestino().getId());
			li.setIdDepOrigen(art.getOrigen().getId());
			li.setSolicitada(art.getCantidad());
			li.setPedido(art.getPedido());
			li.setDocumento(art.getSolicitud());
			articulosInDLR.add(li);
		}
		
		//articulosInDLR = Logica.encuentraReservaDaArtRepos(articulosInDLR,null);
		if(repoDesdeBultos && !bultosParciales) {
			int destino = tarea.getArticulosIn().get(0).getDestino().getId();
			logicaB.pickingEficiente_DivisionBusquedas(cantidadMinima, articulosInDLR, idPicking, destino, idEmpresa, tomaDesdeAlmacen, 
					uLog, session, prioridadReservaBultos, elevadoristas);
			//nico
		} else if (repoDesdeBultos && bultosParciales) {
			int destino = tarea.getArticulosIn().get(0).getDestino().getId();
			logicaBG.pickingEficiente_DivisionBusquedas(cantidadMinima, articulosInDLR, idPicking, destino, idEmpresa, tomaDesdeAlmacen, 
					uLog, session, prioridadReservaBultos, elevadoristas);
		} else {
			log.encuentraUpdateEstadoArticulosPicking(articulosInDLR, tarea.getIdDocumento()+"", 3,null,idEmpresa, elevadoristas);
		}
		
		log.encuentraDistribuirCargaPicking(tarea,articulosInDLR,idEmpresa, idMain);
		
		System.out.println("");

		
		
		for (Usuario ut :tarea.getUsuarios()) 
		{
			
			uLog.registrarEventoHilo(session.getId(), "Asignando picking a "+ut.getNumero(), tarea.getIdDocumento(), 101);
		}
		
		return idMain;
		
	}
	
	public Tarea CompleteTask(Tarea tarea, int idPicking, String obs, List<ArticuloLineaReposicion> articulosIn, int cantidades) {
		tarea.setIdDocumento(idPicking);
		tarea.setObservacion(obs);
		tarea.setArticulosIn(articulosIn);	
		tarea.setCantidadPares(cantidades);
		
		return tarea;
	}
		
	
	public Tarea TaskInit(Usuario uLog, List<Usuario> operariosSelect) {
		Tarea tarea = new Tarea();			
		
		DataIDDescripcion estado = new DataIDDescripcion();
		estado.setId(0);
		
		tarea.setEstado(estado);		
		tarea.setPorcentaje(0);
		tarea.setResponsable(new DataIDDescripcion(uLog.getNumero(),uLog.getNick()));
		tarea.setTipo(new DataIDDescripcion(1,"Picking"));
		tarea.setUsuarios(operariosSelect);
		if(tarea.getUsuarios().size()>1)
		{	
			tarea.setParcial(false);
		}
		else
		{
			tarea.setParcial(true);
		}
		
		return tarea;
	}
	
	
	
	public void ConfirmoMovimientoEcommerce(DataIDDescripcion d, int idDepoCentral, int idDepoWEB, int idEmpresa, Usuario uLog , Logica Logica) {
		System.out.println("VERIFICANDO PEDIDOS WEB");
		EcommerceProcessOrders pro = new EcommerceProcessOrders();
		pro.confirmarSKUForus(d.getDescripcion(), idDepoCentral, d.getId(),new Long(d.getDescripcionB()),idEmpresa,uLog);		
		
		Logica.encuentraBajaArticulosOjos(d.getId(),d.getDescripcion(), "1",idEmpresa);									//BAJAR DE LA ZONA DE PICKING
		Logica.encuentraMoverOjos(idDepoWEB+"P",d.getDescripcion(),d.getId(),uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A CLASIFICADOR ECOMMERCE
		Logica.IngresarMovimientoArticulo("1",idDepoWEB+"P", d.getDescripcion(), d.getId(), uLog.getNumero(),idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA 
	}
	
	
	public void EnviarMailNoEncontrados(Utilidades util, List<DataPicking> noEncontrados, int idEmpresa, Call_WS_APIENCUENTRA api) {
		String mailsD = util.darParametroEmpresaSTR(idEmpresa, 6);
		
		String body1 = "<h2 style=\"text-align: center;\"><strong>Articulos <span style=\"color: #ff6600;\">no encontrados</span> en el picking</strong></h2> "+
					"	<table style=\"height: 36px; width: 100%; border-collapse: collapse; margin-left: auto; margin-right: auto;\" border=\"1\"> "+
					"	  <tbody> "+
					"	    <tr style=\"height: 18px; background-color: #a81865;\"> "+
					"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Picking</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Usuario</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Articulo</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Cantidad</span></strong></h3> "+
					"	      </td> "+
					"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
					"	        <h3><strong><span style=\"color: #ffffff;\">Pedido</span></strong></h3> "+
					"	      </td> "+
					"	    </tr> ";
					
		String body2 = "";
		
		
		
		for (DataPicking n : noEncontrados) 
		{
			body2+=
		
					"	    <tr style=\"height: 18px; border-color: #000000; text-align: center;\"> "+
					"	      <td style=\"width: 20%; height: 18px;\">"+n.getIdPicking()+"</td> "+
					"	      <td style=\"width: 20%; height: 18px;\">"+n.getUsuario().getDescripcion()+"</td> "+
					"	      <td style=\"width: 20%; height: 18px;\">"+n.getArticulo()+"</td> "+
					"	      <td style=\"width: 20%; height: 18px;\">"+n.getSol()+"</td> "+
					"	      <td style=\"width: 20%; height: 18px;\">"+n.getIdPedido()+"</td> "+
					"	      </td> "+
					"	    </tr> ";
		}			
	
		String body3 = "	  </tbody> "+
					"	</table> "+
					"	<p>&nbsp;</p> "+
					"	<p>&nbsp;</p> "+
					"	<p>&nbsp;</p> "+
					"	<p>&nbsp;</p> "+
					"	<p style=\"text-align: center;\">Notificacion generada automaticamente por Encuentra</p>";
		
		List<String> mailDestinos = new ArrayList<>();
		String[] arregloMails = mailsD.split(",");
		for (int i = 0; i < arregloMails.length; i++) 
		{
			mailDestinos.add(arregloMails[i]);
		}
		
		SendMail sm = new SendMail("P"+noEncontrados.get(0).getIdPicking(),mailsD,"Articulos no encontrados", body1+body2+body3,
				"encuentra@200.com.uy");
		List<SendMail> mails = new ArrayList<>();
		mails.add(sm);
		api.PutColaEnvioMails(mails, idEmpresa);
		//em.enviarMailHTMLOD("encuentra@200.com.uy", mailDestinos, "Articulos no encontrados", body1+body2+body3);
	}
	
	
	public Hashtable<Integer, List<DataPicking>> clasificarOrdenes(DataPicking p, Hashtable<Integer, List<DataPicking>> hash){
		
		try {
			p.getDestino().setDescripcionB("");
			if(hash.get(p.getSolicitud())==null)
			{
				List<DataPicking> picks = new ArrayList<>();
				picks.add(p);
				hash.put(p.getSolicitud(), picks);
			}
			else
			{
				List<DataPicking> picks = hash.get(p.getSolicitud());
				picks.add(p);
				hash.put(p.getSolicitud(), picks);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hash;
	}
	
	
	
	public String query_ConfirmoMovimientoEcommerce(DataIDDescripcion d, int idDepoCentral, int idDepoWEB, int idEmpresa, Usuario uLog , Logica Logica, List<DataIDDescripcion> estados) {
		System.out.println("VERIFICANDO PEDIDOS WEB");
		EcommerceProcessOrders pro = new EcommerceProcessOrders();
		
		StringBuilder queries = new StringBuilder();
		
		
		
		queries.append(pro.query_confirmarSKUForus(d.getDescripcion(), idDepoCentral, d.getId(),new Long(d.getDescripcionB()),idEmpresa,uLog.getNumero(),estados));		
		queries.append(Logica.query_encuentraBajaArticulosOjos(d.getId(),d.getDescripcion(), "1",idEmpresa));									//BAJAR DE LA ZONA DE PICKING
		queries.append(Logica.query_encuentraMoverOjos(idDepoWEB+"P",d.getDescripcion(),d.getId(),uLog.getNumero(),idEmpresa));					//AGREGO ARTICULO A CLASIFICADOR ECOMMERCE
		queries.append(Logica.query_IngresarMovimientoArticulo("1",idDepoWEB+"P", d.getDescripcion(), d.getId(), uLog.getNumero(),idEmpresa));	//REGISTRO EL MOVIMIENTO DE MERCADERIA
		
		
		return queries.toString();
	}
	
	
	
	public  double redondea(double numero, int decimales) 
	{ 
	  double resultado;
	  BigDecimal res;

	  res = new BigDecimal(numero).setScale(decimales, BigDecimal.ROUND_UP);
	  resultado = res.doubleValue();
	  return resultado; 
	}  
	
	
	public String buscarFiltro (String buscado, String[]values)
	{
		 String retorno= "";
		 System.out.println(values);
		 for (int i = 0; i < values.length; i++) 
		 {
			 try
			 {
				 if(values[i].contains(buscado))
				 {
					 try
					 {
						 String valor = values[i].split("=")[1];
						 retorno+=valor+",";
					 }
					 catch (Exception e)
					 {
						 
					 }
					 
				 }
			 }
			 catch (Exception e)
			 {
				 
			 }
				 
		 }
		
		 if(!retorno.equals(""))
		 {
			 return retorno.substring(0, retorno.length()-1);
		 }
		 return retorno;
	}
	
	public  String darNombreMes(int idMes)
	{
		switch (idMes) 
		{
			case 1:
				return "ene";
			case 2:
				return "feb";
			case 3:
				return "mar";
			case 4:
				return "abr";
			case 5:
				return "may";
			case 6:
				return "jun";
			case 7:
				return "jul";
			case 8:
				return "ago";
			case 9:
				return "sep";
			case 10:
				return "oct";
			case 11:
				return "nov";
			case 12:
				return "dic";
			

		default:
			return "N/D";
		}
	}
	
	
	public  int darDiasDiff(String fecha1, String fecha2) {
		String consulta = "SELECT DATEDIFF(day, '" + fecha1 + "', '" + fecha2
				+ "');";

		return MSSQL.darDiasDiff(consulta);

	}
	
	

	public  String clearComillas(String input, boolean invertir)
	{
		String ouput="";
		String repleacement = "#?@";
		if(invertir)
		{
			/*busca las comillas y las pasa a #?@*/
			ouput = input.replace("'", repleacement);
		}
		else
		{
			ouput = input.replace(repleacement, "'");
		}
		
		return ouput;
	}
	
	
	public int tryParse (String in)
	{
		int retorno = 0;
		try
		{
			retorno = Integer.parseInt(in);
		}
		catch (Exception e) 
		{
			
		}
		
		return retorno;
	}
	
	
	public int darEmpresa(Usuario u)
	{
		int idEmpresa = 0;
		try
		{
				idEmpresa = u.getIdEmpresa();
		}
		catch (Exception e) 
		{
			
		}
		
		return idEmpresa;
	}

	public Long tryParseL(String in) 
	{
		Long retorno = 0L;
		try
		{
			retorno = Long.parseLong(in);
		}
		catch (Exception e) 
		{
			
		}
		
		return retorno;
	}

	public String darParametroEmpresaSTR(int idEmpresa, int idParametro) 
	{
		Logica l = new Logica();
		return l.darParametroEmpresa(idEmpresa,idParametro);
	}
	
	public int darParametroEmpresaINT(int idEmpresa, int idParametro) 
	{
		Logica l = new Logica();
		try
		{
			return Integer.parseInt(l.darParametroEmpresa(idEmpresa,idParametro));
		}
		catch (Exception e) 
		{
			return -1;
		}
	}

	public boolean darParametroEmpresaBool(int idEmpresa, int idParametro) 
	{
		Logica l = new Logica();
		try {
			int bol = Integer.parseInt(l.darParametroEmpresa(idEmpresa,idParametro));
			
			if(bol==1)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
		
	}
	
	public boolean darParametroDepositoBool(int idEmpresa,int idDeposito, int idParametro) 
	{
		Logica l = new Logica();
		try {
			int bol = Integer.parseInt(l.darParametroDeposito(idEmpresa,idDeposito,idParametro));
			
			if(bol==1)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
		
	}

	
	public void facturacionAutomatica(Long idpedido, int printer, int idEmpresa, Logica Logica) {
		try {			
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			
			String facturacion = Logica.darParametroEmpresa(idEmpresa, 23); 
			if(facturacion!= null && facturacion.equals("1")) {
				if(!Logica.darParametroEmpresa(idEmpresa, 57).equals("1")) {
					printer = 0;
				}
				api.CheckInOrder(idpedido, printer, idEmpresa);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	
	public String validarComillas(String texto) {
		if(texto.contains("\'")) {
			texto = texto.replace('\'',' ');
		}
		if(texto.contains("\"")) {
			texto = texto.replace('\"',' ');
		}
		return texto;
	}
	
	public String statusMapperFenicio(int status) {
		String estado = "";
		try {
			switch (status) {
			case 1:
				estado = "Preparando";
				break;
			case 2:
				estado = "Preparando";
				break;
			case 25:
				estado = "Listo para enviar";
				break;
			case 3:
				estado = "Listo para enviar";
				break;
			case 34:
				estado = "Listo para enviar";
				break;	
			case 4:
				estado = "En camino";
				break;
			case 5:
				estado = "Listo para retirar";
				break;			
			case 6:
				estado = "Pedido entregado";
				break;
			case 99:
				estado = "Cancelado";
				break;	

			default:
				estado = "";
				break;
			}
		} catch (Exception e) {
			return "";
		}
		return estado;
	}
	
	public int darUltimoIdAutomatico(int idParametro, int idEmpresa) {
        _EncuentraConexion ec = new _EncuentraConexion();
        int utilizarId = 0;
        try {
            utilizarId = ec.devolverIDDisponible(idParametro, idEmpresa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilizarId;
    }
	
	//Devuelve un arreglo de strings para prefijos 
	public String[] auxIndices(int largo) {   
	    String[] resultado = new String[largo];
	    String colName = "";
	    for(int i = 0; i < largo; i++) {
	        char c = (char)('A' + (i % 26));
	        colName = c + "";
	        if(i > 25){
	            colName =  resultado[(i / 26) - 1] + "" + c;
	        }
	        resultado[i] = colName;
	    }
	    return resultado;
	}
	
	public DataIDDescripcion remitir(int origen, int destino, int idUsuario, List<DataIDDescripcion> articulos, Long origenDoc, 
			int docSolicitud, int doc, int razon, String usuario, String obs, int idEmpresa, bulto b, boolean entregaSolicitud){
		DataIDDescripcion remito = null;
		try {
			Logica Logica = new Logica();
			boolean integracionActiva = Logica.darIntegracionProductiva(2, idEmpresa);
			
			MovStock m = new MovStock();			
			m.setOrigen(origen);
			m.setDestino(destino);
			m.setIdUsuario(idUsuario);
			m.setDetalle(articulos);
			m.setOrigenDoc(origenDoc);
			m.setDocSolicitud(docSolicitud);
			m.setDoc(doc);
			m.setRazon(razon);
			m.setUsuario(usuario);
			m.setObservacion(obs);	
			m.setEntrega(entregaSolicitud);
			
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			if(integracionActiva) {
				remito = api.movStock(m, false, idEmpresa);
			}
			else {
				remito = new DataIDDescripcion((int)((Math.random() * 9000000)+1000000),"");
			}
			if(remito.getId()>0) {
				
				if(!entregaSolicitud) {
					Logica.remitirBultoPicking(b, origen, idEmpresa);
				}
				else {
					//Logica.remitirBultoMayorista(articulos, origen, destino, docSolicitud, idEmpresa);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remito;
	}
	
	public int parseStringInt(String a) {
		int salida = 0;
		try {
			salida = Integer.parseInt(a);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return salida;
	}

	
	public String soloNumeros(String cadena) {
		String salida = "";
		try {
			salida = cadena.replaceAll("[A-Za-z]", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	
	public static int parseStringIntStatic(String a) {
		int salida = 0;
		try {
			salida = Integer.parseInt(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public static boolean parseIntToBoolean(int a) {
		boolean salida = false;
		try {
			if(a==1)
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public static int parseBooleanToInt(Boolean a) {
		int salida = 0;
		try {
			if(a)
				salida = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public static boolean parseStringToBoolean(String a) {
		boolean salida = false;
		try {
			if(a.equals("true"))
				salida = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public String limpiarS (String in)
	{
		String cadenaNormalize = Normalizer.normalize(in, Normalizer.Form.NFD);   
		String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
		
		return cadenaSinAcentos;
	}
	

	public Fecha darHoraHabilDeposito(int idDeposito,int idEmpresa, Fecha fecha) 
	{
		Logica lo= new Logica();
		if(lo.hayRegistro("SELECT * FROM deposito_horario DH WHERE idDeposito = "+idDeposito+" AND IdEmpresa ="+idEmpresa+" AND idDia = "+fecha.getDiaSemana()+" and horaDesde<="+fecha.getHora()+" AND "+fecha.getHora()+"<horaHasta"))
		{
			return fecha;
		}
		else
		{
			DataIDDescripcion fechaPosible = lo.darDataIdDescripcion("SELECT idDia,horaDesde FROM deposito_horario DH WHERE idDeposito = "+idDeposito+" AND IdEmpresa ="+idEmpresa+" AND idDia > "+fecha.getDiaSemana()+" AND (horaHasta-horaDesde)>0 LIMIT 1");
			
			int diaPosible = fechaPosible.getId();//esto es el dia de la semana;
			int horaPosible = Integer.parseInt(fechaPosible.getDescripcion());
			
			int [] diasSemana = {1,2,3,4,5,6,7};
			
			for (int i : diasSemana) 
			{
				Fecha temp = new Fecha(i, 0, 0);
				if(temp.getDiaSemana()==diaPosible)
				{
					temp.setHora(horaPosible);
					return temp;
				}
			}
			
			
			
			
			
			
		}
		
		return null;
	}
	
	public byte[] encodeFile(String IN_FILE) {
		byte[] encoded = null;
		try {
			byte[] inFileBytes = Files.readAllBytes(Paths.get(IN_FILE));			
			 encoded = java.util.Base64.getEncoder().encode(inFileBytes);			
		} catch (Exception e) {			
			try {
				PropertiesHelper pH=new PropertiesHelper("paths");
				pH.loadProperties();
				String path = pH.getValue("pdf")+"/modo.pdf";
				URL url = new URL(IN_FILE);	
				saveFile(url, path);
				
				byte[] inFileBytes = Files.readAllBytes(Paths.get(path));			
				encoded = java.util.Base64.getEncoder().encode(inFileBytes);	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		return encoded;
    }
	
	public byte[] decodeFile(String IN_FILE) {
		byte[] decoded = null;
		try {
	        decoded = java.util.Base64.getDecoder().decode(IN_FILE);	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decoded;
    }
	
	public byte[] decodeFile(byte[] inFileBytes) {
		byte[] decoded = null;
		try {
	        decoded = java.util.Base64.getDecoder().decode(inFileBytes);	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decoded;
    }
	
	public void writeToFile(String fileName, byte[] bytes) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
	        fos.write(bytes);
	        fos.flush();
	        fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
	public PropertiesHelper initPropertiesHelper(String path) {
		PropertiesHelper pH=new PropertiesHelper(path);	    	
    	try
    	{
    		pH.loadProperties();
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return pH;
	}
	
	public void etiquetas_verificacion_destinos (List<DataLineaRepo> lista, Usuario uLog, int idPicking, int idEmpresa, String comentarioTarea) {
		try {
			Utilidades util = new Utilidades();
			Boolean necesitaEtiquetas = util.darParametroEmpresaBool(idEmpresa, 43);
			if(necesitaEtiquetas) {
				Hashtable<String, DataIDDescripcion> destinos = new Hashtable<>();
				String pedido = "";
				for(DataLineaRepo l: lista) {
					if(l.isBulto()) {
						for(DataLineaRepo c:l.getContenido()) {
							darSorterPosition(destinos, c);
						}
					}else {
						darSorterPosition(destinos, l);
					}				
				}
				Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
				String etiq = "";
				//GENERAR ETIQUETAS
				
				List<DataIDDescripcion> destinosList = new ArrayList<>(destinos.values());
				
				//resumen picking
				etiq = IPrint.etiquetas_verificacion_destinos(idPicking, comentarioTarea,uLog);	
				api.PutColaImpresion("labels"+idPicking+"_"+idEmpresa, etiq, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
				
				//etiquetas destinos pallets
				for(DataIDDescripcion ls : destinosList)
				{
					etiq = IPrint.etiquetas_verificacion_destinos(ls, uLog);
					api.PutColaImpresion("destiny"+ls.getDescripcion()+"_"+idEmpresa, etiq, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
				}
				
	            
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Hashtable<String, DataIDDescripcion> darSorterPosition(Hashtable<String, DataIDDescripcion> destinos, DataLineaRepo l) {
		try {
			if(destinos.get(l.getPosClasif()) == null) {
				DataIDDescripcion d= new DataIDDescripcion(l.getPosClasif(), l.getDescDeposito());
				if(!l.getPosClasif().equals(l.getIdDepDestino()+"")) {
					d.setDescripcionC("Pedido: "+l.getDocumento());	
				}	
				destinos.put(l.getPosClasif(), d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destinos;
	}
	
	public String imprimirEtiqueta(bulto b, int unidades,int idEmpresa){
		String etiqueta = "";
		try {
			if(b.getPedido()!=0){
				etiqueta = IPrint.ImprimirEtiquetaBulto_Pedido(b,unidades,idEmpresa, b.getNumerador());
			}
			else{
				etiqueta = IPrint.ImprimirEtiquetaBultos(b,unidades,idEmpresa);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return etiqueta;
	}
	
	public void saveFile(URL url, String file) throws IOException {		    
			try
			{
				try (InputStream in = url.openStream()) {
				   Files.copy(in, Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					try
		            {
		            // Create a trust manager that does not validate certificate chains
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
						SSLContext sc = SSLContext.getInstance("SSL");
						sc.init(null, trustAllCerts, new java.security.SecureRandom());
						HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

						// 	Create all-trusting host name verifier
						HostnameVerifier allHostsValid = new HostnameVerifier() {
							public boolean verify(String hostname, SSLSession session) {
								return true;
							}
						};

						// Install the all-trusting host verifier
						HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);    
					    
					    InputStream in = url.openStream();
					    FileOutputStream fos = new FileOutputStream(new File(file));
					    int length = -1;
					    byte[] buffer = new byte[1024];

					    while ((length = in.read(buffer)) > -1) {
					        fos.write(buffer, 0, length);
					    }
					    fos.close();
					    in.close();       
		            }
		            catch (Exception ex)
		            {
		                  ex.printStackTrace();
		            }
				}
					
								
			}
			catch (Exception e)
			{
				e.printStackTrace();	        	
			}
			
		  }
	
	public void guardarLineasReposicion_tareas(int deposito, List<DataIDDescripcion> articulos, Usuario u, int idEmpresa){
		Logica l = new Logica();
		try {
			int id_de_Deposito_Ecommerce = darParametroEmpresaINT(idEmpresa, 5);
			int id_de_Deposito_Principal = darParametroEmpresaINT(idEmpresa, 4);
			
			 l.darArticuloRepoFromLoadForus(articulos,id_de_Deposito_Ecommerce,false,idEmpresa,deposito,2,false);
	            int idSinc = l.darNextSincRepo(idEmpresa)-1;
	            
	            
	            if(deposito!=id_de_Deposito_Principal)
	            {
	            	List<ArticuloLineaReposicion> articulosIn = new ArrayList<>();
		            
		            for (DataIDDescripcion d : articulos) 
		            {
		            	ArticuloLineaReposicion dl = new ArticuloLineaReposicion(d.getId(), d.getDescripcion(), "Venta WEB: "+d.getIdLong(), new DataIDDescripcion(idSinc, ""), new DataIDDescripcion(deposito,""), new DataIDDescripcion(id_de_Deposito_Ecommerce, ""));
		            	dl.setEcommerce(true);
		            	dl.setPedido(d.getIdLong());
		            	dl.setPosClasif("0");
		            	dl.setSolicitud(d.getIdB());
		            	articulosIn.add(dl);
					}
		            		            
		            int cantidades=0;
		            for(ArticuloLineaReposicion ae:articulosIn)
		            {				
		            		cantidades+= ae.getCantidad();
		            }

		            List<Usuario> operariosSelect = new ArrayList<>();
		            Usuario us = new Usuario();
		            us.setNumero(0);
		            us.setNick("Integraciones");
		            operariosSelect.add(us);
		            				
		            Tarea tarea = TaskInit(u, operariosSelect);	
		            					
		            String destinos = deposito+"";
		            String observacion = destinos+" "+u.getNick();
		            						
		            int idPicking = l.encuentraAltaPicking(articulosIn, cantidades, "Sin filtros aplicados",idEmpresa,true,false);						
		            tarea = CompleteTask(tarea, idPicking, observacion, articulosIn, cantidades);						
		            
		            HttpSession session = darDummySesion("SINCRO");
		            
		            guardarTarea(tarea, u, idEmpresa,session, l, idPicking,false, 10000,false, false, false, false); //Cambiar booleano 29/1
	            }
		} catch (Exception e) {
			System.out.println("Error generando tareas ");
		}
	}

	public Hashtable<Integer,DataIDDescripcion> darDeposWEB(int idEmpresa) 
	{
		Logica l = new Logica();
		try {
			
			Hashtable<Integer,DataIDDescripcion> retorno = new Hashtable<Integer, DataIDDescripcion>();
			List<DataIDDescripcion> lista = l.darListaDataIdDescripcionMYSQLConsulta("SELECT DISTINCT C.IdDepositoEcommerce, D.Nombre FROM ecommerce_canal_ml C JOIN depositos D ON D.idDeposito = C.IdDepositoEcommerce AND D.IdEmpresa = C.IdEmpresa WHERE C.idEmpresa="+idEmpresa+"");
			retorno.remove(0);
			for (DataIDDescripcion d : lista) 
			{
				retorno.put(d.getId(), d);
			}
			return retorno;
			
		} catch (Exception e) 
		{
			return  new Hashtable<Integer, DataIDDescripcion>();
		}
	}

}
