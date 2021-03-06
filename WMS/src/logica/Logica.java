package logica;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import beans.ArticuloLineaReposicion;
import beans.ArticuloMatrizHTML;
import beans.ArticuloReposicion;
import beans.BultosACrear;
import beans.ConsolidarSku;
import beans.DatosInventario;
import beans.DatosInventarioStatus;
import beans.DepositoParametros;
import beans.Fecha;
import beans.LineaOrdenAlmacen;
import beans.Menu;
import beans.MovArticulo;
import beans.MovStock;
import beans.Nota;
import beans.OjosEnCero;
import beans.OrdenAlmacen;
import beans.ProcessEcommerce;
import beans.RemitoRegistro;
import beans.ReportObject;
import beans.StockReposicion;
import beans.Tareas;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.elementoPicking;
import beans.jsonEstadoMP;
import beans.api.GrabarRecepcion;
import beans.api.articuloBarra;
import beans.encuentra.ArticuloConteo;
import beans.encuentra.ArticuloMedidas;
import beans.encuentra.ColorOC;
import beans.encuentra.ConteoTiendas;
import beans.encuentra.DataArtMovS;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataArticuloOC;
import beans.encuentra.DataDocTipoEnvio;
//import beans.encuentra.DataItemFoundAndStock;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataListaPicking;
import beans.encuentra.DataOjo;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.DataOjoArticuloCantidad;
import beans.encuentra.DataPicking;
import beans.encuentra.DataPickingS;
import beans.encuentra.DepositoAdmin;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DepositoStock;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.MovimientoAlmacen;
import beans.encuentra.Ojo;
import beans.encuentra.RecepcionExpedicion;
import beans.encuentra.Remito;
import beans.encuentra.Ruta;
import beans.encuentra.RutaDeposito;
import beans.encuentra.SKUType;
import beans.encuentra.Sector;
import beans.encuentra.TalleOC;
import beans.encuentra.Tarea;
import beans.encuentra.Tarjeta;
import beans.encuentra.TipoSector;
import beans.encuentra.Transporte;
import beans.encuentra.UsoEstanteria;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import beans.endpoints.itemsWHS;
import beans.endpoints.suborderWHS;
import beans.endpoints.updateSuborderWHS;
import beans.reportes.AjustesDiferencias;
import beans.reportes.BultosRangoFechaDestino;
import beans.reportes.ConteoOjo;
import beans.reportes.CumplimientoOrdenes;
import beans.reportes.ExpedicionMovimiento;
import beans.reportes.FrecuenciaUbicacionesProductos;
import beans.reportes.InventarioXUbicacion;
import beans.reportes.MonitorVentaM;
import beans.reportes.MonitorVentaMArticulo;
import beans.reportes.MovsXArticulo;
import beans.reportes.PedidosProcesadosXOperario;
import beans.reportes.PedidosRetrasados;
import beans.reportes.Picking;
import beans.reportes.Picking2;
import beans.reportes.ProductividadPicking;
import beans.reportes.ReporTEST;
import beans.reportes.StockEncuentraVisual;
import beans.reportes.inventDisponible;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import cliente_rest_Invoke.Call_WS_MODDO;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.ArticuloPedido;
import dataTypes.DTO_Articulo;
import dataTypes.DataArtBarraCant;
import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloCantBarra;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataBooleanDesc;
import dataTypes.DataClasificacionEntregaArti;
import dataTypes.DataDepositoSAP;
import dataTypes.DataDescDescripcion;
import dataTypes.DataDetallePedido;
import dataTypes.DataDetallePedidoPrint;
import dataTypes.DataDetalleRecepcion;
import dataTypes.DataDocVisual;
import dataTypes.DataEcommerce_LogPedido;
import dataTypes.DataEcommerce_canales_envio;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import dataTypes.DataOC;
import dataTypes.DataPedidoArticuloEcommerceVerif;
import dataTypes.DataPedidoArticuloEcommerceVerif_ar;
import dataTypes.DataPedidoPickup;
import dataTypes.DataPosiblePedido;
import dataTypes.DataRecepcion;
import dataTypes.DataReglaReposicion;
import dataTypes.PosicionesPosibles;
import dataTypes.ReposicionPicking;
import dataTypes.TareasPendientes;
import dataTypes.trackingPedido;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.Compras;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import eCommerce_jsonObjectsII.Presentaciones;
import eCommerce_jsonObjectsII.Productos;
import eCommerce_jsonObjectsII.Variantes;
import helper.PropertiesHelper;
import jsonObjects.SendMail;
import main.Distribuidor;
import main.utilidades.CallWSRFID;
import persistencia.MSSQL;
import persistencia._EncuentraConexion;
import persistencia._EncuentraPersistir;
import web.informes.ReporteTest;
import webservices.Views;


public class Logica
{
	
	private final int EMPRESA_FORUS = 2;
	private final int EMPRESA_ELREY = 4;
	private final int EMPRESA_PPGG = 5;
	private final int EMPRESA_LAISLA = 6;
	private final int EMPRESA_BAS = 8;
	private final int EMPRESA_GARRIDO = 10;
	
	private _EncuentraConexion econ;
	
	private _EncuentraPersistir eper;
	
	public Logica()
	{
		econ = new _EncuentraConexion();
		eper = new _EncuentraPersistir();
	}
	
	
	public _EncuentraConexion getEcon() {
		return econ;
	}
	public _EncuentraPersistir getEper() {
		return eper;
	}
	
	
	public void encuentraUpdateTarjetas(String idGrupo, int idEmpresa) 
	{		

		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			econ.updateTarjetas(idGrupo,idEmpresa);
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
	String darFechaActual() 
	{

		Calendar c = Calendar.getInstance();
		
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString((c.get(Calendar.MONTH)) + 1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		String minutos = Integer.toString(c.get(Calendar.MINUTE));
		System.out.println(dia + "/" + mes + "/" + annio + " A las " + hora	+ ":" + minutos);
		return dia + "/" + mes + "/" + annio + " A las " + hora + ":" + minutos;

	}

	
	public  List<DataRecepcion> getRecepciones( int idEmpresa)
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			List<DataRecepcion>recepciones=null;
			String consulta="Select * from recepcion where idEmpresa="+idEmpresa+" and cantidadSolicitada>cantidadRecibida";
			return econ.darRecepciones(consulta, true);
		} 
		catch (Exception e) 
		{
			return new ArrayList<>();
		}
		
	}
	public  List<DataDetalleRecepcion> DetalleRecepcion(int idRecepcion, int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
		cone = econ.getConnection();
		List<DataRecepcion>recepciones=null;
		String consulta="select ar.idArticulo as articulo,ar.cantidadPedida,ar.pendientesFacturar,ar.cantidadFacturada " +
				"from detallerecepcion dr,articulosarecepcionar ar WHERE dr.idEmpresa="+idEmpresa+" and dr.idEmpresa=ar.idEmpresa and dr.idRecepcion="+idRecepcion+" and dr.idDetalle=ar.idDetalle and ar.pendientesFacturar>0 order by ar.idArticulo asc";
		return econ.darDetalleRecepciones(consulta);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	

	public  Usuario loginEncuentraSinEmpresa(String nombre, String pass) {

		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "select * from usuarios where BINARY Nickname = '"
					+ nombre + "' AND BINARY `Contrase?a`= '" + pass + "' and baja=0";

			Usuario u = econ.login(consulta);
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	public  Usuario loginEncuentra(String nombre, String pass, int idEmpresa) {

		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection(); 
			String consulta = "select * from usuarios where  idEmpresa="+idEmpresa+" and  BINARY Nickname = '"
					+ nombre + "' AND BINARY `Contrase?a`= '" + pass + "' and baja=0";

			Usuario u = econ.login(consulta);
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public List<DataIDDescripcion> DarDistribucionesVisual(String fi, String ff){
		try {
			
			String query = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT docv_serie,docv_numerodoc FROM dbo.encuentra_ventaWEB v  WHERE v.doc_fecha between '"+fi+"' AND '"+ff+"';";
			return MSSQL.darVtaVisual(query);
		} catch (Exception e) {
			return new ArrayList<>();
		}
		
	}

	
	public List<DataIDDescripcion> DarVtasSinDistribucionesVisual(String fi, String ff, int idEmpresa){
		try {
			List<DataIDDescripcion> ventas;
			String query = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT docv_serie,docv_numerodoc,doc_numerodoc,doc_fecha,doc_comentario FROM dbo.encuentra_ventaWEB v "+
			" WHERE v.doc_fecha between '"+fi+"' AND '"+ff+"';";
			Hashtable<String, DataIDDescripcion> ventasHT = MSSQL.darVtasSinDistribucion(query); //TRAIGO VENTAS SIN DISTRIBUCIONES
			
			ff=ff.split(" ")[0]+" 23:59:59";
			List<DataIDDescripcion> pedidos = darListaDataIdDescripcionMYSQLConsulta("select idpedido,'' from ecommerce_pedido where idEmpresa="+idEmpresa+" stamptime between '"+fi+"' AND '"+ff+"';"); //TRAIGO VENTAS ENCUENTRA
			pedidos.remove(0);
			
			for(DataIDDescripcion d:pedidos){	//DESCARTO LAS QUE YA TENGO PARA QUE QUEDEN SOLO LAS QUE NO TIENEN DISTRIBUCIONES
				ventasHT.remove(d.getId()+"");
			}
			
			ventas = new ArrayList<>(ventasHT.values());
			
			return ventas;
		} catch (Exception e) {
			return new ArrayList<>();
		}
		
	}
	
	public  Usuario loginEncuentra_depoInvent(Usuario u, int idEmpresa) 
	{

		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
						
			cone = econ.getConnection();
			boolean hay = econ.hayReg("SELECT * FROM invent WHERE idEmpresa="+idEmpresa+" and  activo = 1 AND idDeposito = '"+u.getDeposito()+"'");
			
			u.setInventario(hay);
			
			
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}


	
	public  List<DataDescDescripcion> darrepos(int origen, int idEmpresa) 
	{
		String consulta = "";
		if(origen==0)
		{
			consulta = "select pic.id, pic.fecha, pic.unidades from picking pic, reposicion_articulos ra "
					+ "where ra.idPicking = pic.id AND ra.Estado = 2 AND pic.id!=0 "
					+ " and ra.idEmpresa=pic.idEmpresa and ra.idEmpresa="+idEmpresa+" group by pic.id";
		}
		
		try 
		{

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			return econ.darRepos(consulta);
		}
		catch (Exception e) 
		{
			return new ArrayList<>();
		} 

	}
	
	

	public  List<DataDescDescripcion> darProveedoresParaOrdenes(boolean importa)
	{
		
		String consulta="";
		if(importa)
		{
			
			consulta = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT Numero, Nombre  FROM [VsHushPuppies].[dbo].[encuentra_orden_compra] "
					+ "WHERE ESTADO !='Terminado' GROUP BY Numero, Nombre";
		}
		else
		{
			//consulta = "SELECT OC.CardCode, OC.CardName FROM OPOR  OC INNER JOIN POR1 DET ON DET.DocEntry = OC.DocEntry INNER JOIN OITM ART ON ART.itemCode = DET.itemCode WHERE OC.DocStatus ='O' and DET.Quantity - (DET.Quantity-DET.OpenQty)>0 AND ART.itmsGrpCod = 151 GROUP BY OC.CardCode, OC.CardName order by OC.CardName";
		}
		
		
	
		
		
		
		return MSSQL.darProveedores(consulta);
	}
	
	
	
	

	public  List<DataOC> darOrdenesDeCompra(DataDescDescripcion objProvedor, boolean importa,int idEmpresa, List<DataIDDescripcion> datosExcel) 
	{
	
		
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMDD");
		String finicial=sdf.format(fechaIni);
		String ffinal=sdf.format(fechaFin);*/
		 
		String consulta="";
		String qExcel = "";
		
		if(importa)
		{
			consulta = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT OC.*, A.DescCorta "
					+ "FROM [VsHushPuppies].[dbo].[encuentra_orden_compra] OC "
					+ "LEFT OUTER JOIN [VsHushPuppies].[dbo].[encuentra_articulos] A ON A.idArticulo = OC.idArticulo "
					+ "WHERE OC.ESTADO !='Terminado' AND OC.Numero = "+objProvedor.getId();
		}
		else
		{
			consulta = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT OC.*, A.DescCorta "
					+ "FROM [VsHushPuppies].[dbo].[encuentra_orden_compra] OC "
					+ "LEFT OUTER JOIN [VsHushPuppies].[dbo].[encuentra_articulos] A ON A.idArticulo = OC.idArticulo "
					+ "WHERE OC.ESTADO !='Terminado' AND OC.Numero = "+objProvedor.getId();
		}
		
		if(datosExcel!=null) {
			for(DataIDDescripcion data:datosExcel) {
				qExcel += " (oc.idarticulo ='"+data.getDescripcion().replace("\\.", "")+"' AND numerodoc = "+data.getIdB()+" AND "+data.getId()+" <= solicitada-entregada) OR ";
			}
			qExcel = qExcel.substring(0,qExcel.length()-3);
			consulta = consulta+" AND ("+qExcel+")";
		}
		
		System.out.println(consulta);
		return MSSQL.darOrdenesDeCompra(consulta,objProvedor,idEmpresa);
		 
		
	}
	
	public List<DataOC> ControlarOCExcel(List<DataOC> ordenes, List<DataIDDescripcion> datosExcel){
		
		try {
			for(DataIDDescripcion data: datosExcel) {
				for(DataOC oc:ordenes) {
					if(data.getIdB()==oc.getNumeroDocumento()) {
						for(DataArticuloOC art:oc.getArticulos()) {
							for(ColorOC col:art.getColores()) {
								for(TalleOC tal:col.getTalles()) {
									System.out.println(art.getIdArticulo()+col.getId()+tal.getTalle());
									if(data.getDescripcion().equals(art.getIdArticulo()+col.getId()+tal.getTalle())) {
										tal.setSolicitada(data.getId());
										break;
									}
								}
							}
						}
					}					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ordenes;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public  List<DataLineaRepo> encuentraDarArtRepos(int idDoc,String paraQuien, int idEmpresa, int idUsuario) 
	{

		
		List<DataLineaRepo> repos = darListaLineasReservadasRepoPick(idDoc,idEmpresa, idUsuario);
		System.out.println("");
		List<DataLineaRepo> retorno = new ArrayList<>();

		List<DataIDDescripcion> lineasParcial = encuentraLineasParcial(idDoc,idEmpresa);
		Map<String, String> lineasP = new HashMap<>();

		

		boolean esParcial = true;
		if (lineasParcial.isEmpty()) 
		{
			esParcial = false;
		}
		else 
		{
			for (DataIDDescripcion d : lineasParcial) 
			{
				lineasP.put(d.getDescripcion(), d.getDescripcion());

			}

		}

		boolean agregalo = false;

		boolean esTarea = false;

		if (paraQuien.equals("tareas")) {
			esTarea = true;
		} else {
			esTarea = false;
		}

		for (DataLineaRepo da : repos) 
		{

			if (esParcial) 
			{
				try 
				{
					String artInHT = lineasP.get(da.getIdArticulo());
					if (artInHT.equals(da.getIdArticulo()) && esTarea) 
					{
						agregalo = false;
					} 
					else if (artInHT.equals(da.getIdArticulo()) && !esTarea) 
					{
						agregalo = true;
					}

				} 
				catch (Exception e) 
				{
					if (esTarea) 
					{
						agregalo = true;
					} 
					else if (!esTarea) 
					{
						agregalo = false;
					}
				}
			}
			else
			{
				agregalo = true;
			}
			if (agregalo) 
			{
				retorno.add(da);
				
			}
			
		}

		Collections.sort(retorno);
		
		//debug
		for (DataLineaRepo da : retorno) 
		{
			System.out.println("Recorrido " + da.getRecorrido() + " Articulo: "
					+ da.getIdArticulo() + " Ubicacion " + da.getCubi()+" idPedido"+da.getPedido());
		}
		return retorno;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public  Hashtable<String, List<DataIDIDDescripcion>> encuentraReservaDaArtRepos(List<DataLineaRepo> repos,
			Hashtable<String, String> excluirO, int idEmpresa) 
	{	
		Hashtable<String, List<DataIDIDDescripcion>> retorno = new Hashtable<String, List<DataIDIDDescripcion>>();		

		int recorrido = 0;
		
		for (DataLineaRepo da : repos) 
		{
			try
			{
				List<DataIDIDDescripcion> ubicacionesArtuculo = encuentraDarSectores(da.getIdArticulo(), recorrido,excluirO,idEmpresa);
				if (!ubicacionesArtuculo.isEmpty()) 
				{
					retorno.put(da.getIdArticulo(), ubicacionesArtuculo);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			
		}

			
		return retorno;
	}
	
	
	
	
	
	
	
	
	
	private  boolean  encuentraReservarUbicacion(String articulo, String cubi, int cantidad, int idEmpresa) 
	{
		if (cantidad < 0) {
			cantidad = 0;
		}
		String insert="UPDATE ojostienenarticulos SET Reservada = Reservada+"+cantidad+" WHERE idOjo = '"+cubi+"' AND idArticulo = '"+articulo+"' AND idEmpresa ="+idEmpresa+";" ;	
		
		try 
		{
		
			eper.persistir(insert);
		
			return true;
			//eper.desconectar();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public  String  encuentraReservarUbicacionII(String articulo, String cubi, int cantidad,int idPick,int dest,Long idpedido,boolean reserva, int idE, int solicitud) 
	{
		try 
		{
			String retorno ="";
			if(reserva){
				if (cantidad < 0) {
					cantidad = 0;
				}
				retorno += "UPDATE ojostienenarticulos SET Reservada = Reservada+"+cantidad+" WHERE idOjo = '"+cubi+"' AND idArticulo = '"+articulo+"' AND idEmpresa ="+idE+"; " ;
				//eper.persistir(insert);
			}
			
			retorno+="INSERT IGNORE INTO `reposicion_articulos_ojos` (`idArticulo`, `idPicking`, `cUbicacion`, `Cantidad`, `destino`, `pedido`,idEmpresa, solicitud) "+
					"VALUES ('"+articulo+"', "+idPick+", '"+cubi+"',"+cantidad+","+dest+","+idpedido+","+idE+","+solicitud+");";
			//eper.persistir(insertII);
		
			return retorno;
			//eper.desconectar();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return "";
		}
		
	}

	public  DataIDIDDescripcion DarEstanteria(String cubi, int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

		  String consulta = "select o.idEstanteria,o.idEstante,o.idModulo,e.Sector,e.Descripcion " +
							"from ojos o "+ 
							"inner join Estanterias e on o.idEstanteria=e.idEstanteria and o.idEmpresa=e.idEmpresa "+
							"where o.idEmpresa="+idEmpresa+" and o.IdOjo ='"+cubi + "'"; 
		  
		  System.out.println(consulta);

			return econ.darEstanteEstMod(consulta);
		} catch (Exception e) {
			return null;
		}

	}

	
	private  List<DataIDIDDescripcion> encuentraDarSectores(String articulo,int recorrido,Hashtable<String, String> excluirO,
			int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "";
			String whArt = "";
			
			if(excluirO!=null){
				
				if(excluirO.get(articulo)!=null){
					whArt = " where T.`idArticulo` =  '"+articulo+"' and o.idojo not in ("+excluirO.get(articulo)+") ";
				}
				else{
					whArt = " where T.`idArticulo` =  '"+articulo+"' ";
				}
				
			}
			else{
				whArt = "	WHERE T.`idArticulo` =  '"+articulo+"' ";
			}

			if (recorrido == 1 || recorrido == 0) {
				consulta = " select O.`IdRecorrido`, O.`IdOjo`, T.Cantidad - if(ISNULL(T.Reservada),0,T.Reservada) as CANTDISP "+
							"FROM `ojos` O "+
							"INNER JOIN `ojostienenarticulos` T on O.IdOjo = T.idOjo and O.idEmpresa=T.idEmpresa "+
							"INNER JOIN estanterias E on E.idEstanteria = O.idEstanteria and T.idEmpresa=E.idEmpresa "+
							whArt+
							"  and E.idUso in (1,3)	and T.idEmpresa="+idEmpresa+
							"	ORDER BY O.IdRecorrido ";
			} else {
				consulta = "select  O.IdRecorrido , O.`IdOjo`, T.Cantidad - if(ISNULL(T.Reservada),0,T.Reservada) as CANTDISP, abs(O.`IdRecorrido` - "+recorrido+")  as distancia "+ 
							"FROM `ojos` O "+
							"INNER JOIN `ojostienenarticulos` T on O.IdOjo = T.idOjo and O.idEmpresa=T.idEmpresa "+
							"INNER JOIN estanterias E on E.idEstanteria = O.idEstanteria and T.idEmpresa=E.idEmpresa "+
							whArt+
							"	and E.idUso in (1,3) and T.idEmpresa="+idEmpresa+
							"	order by distancia";
			}

			

			
			return econ.darIdIdDescripcion(consulta);

		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	
	public  Hashtable<String,List<DataIDIDDescripcion>> encuentraDarSectoresHT(String inns, int idEmpresa, boolean elevadoristas) 
	{
		Hashtable<String,List<DataIDIDDescripcion>> retorno = new Hashtable<String, List<DataIDIDDescripcion>>();

		try {
				@SuppressWarnings("unused") Connection cone;
				cone = econ.getConnection();
				
					
					String consulta = " select O.`IdRecorrido`, O.`IdOjo`, T.Cantidad - if(ISNULL(T.Reservada),0,T.Reservada) as CANTDISP, T.idArticulo  "+
							"	FROM `ojos` O "+
							"	INNER JOIN `ojostienenarticulos` T on O.IdOjo = T.idOjo and O.idEmpresa=T.idEmpresa "+
							"	INNER JOIN estanterias E on E.idEstanteria = O.idEstanteria and T.idEmpresa=E.idEmpresa "+
							"	WHERE O.idEmpresa="+idEmpresa+" and T.idArticulo IN ("+inns+") and o.idestanteria not in "
						    + "(108,109,110,111,112,113) and E.idUso in (1,3) ";
					if (elevadoristas) {
						consulta += "and e.almacenapallet=1 ";
					}	
					else {
						consulta += "and e.almacenapallet=0 ";
					}
					consulta+= "	ORDER BY T.idArticulo, O.IdRecorrido ";

		
				List<DataIDIDDescripcion> lista =  econ.darIdIdDescripcion(consulta);
				
				
				String artAnt = "";
				boolean pri = true;
				List<DataIDIDDescripcion> listaCurrent = null;
				for (DataIDIDDescripcion a : lista) 
				{
					
					if(pri)
					{
						pri=false;
						listaCurrent = new ArrayList<>();
						listaCurrent.add(a);
						
					}
					if(artAnt.equals(a.getDescripcionB()))
					{
						listaCurrent.add(a);
					}
					else
					{
						retorno.put(artAnt,listaCurrent);
						
						listaCurrent = new ArrayList<>();
						listaCurrent.add(a);
					}
					
					artAnt = a.getDescripcionB();
					
				}
				
				retorno.put(artAnt,listaCurrent);
				
				return retorno;
				

		} catch (Exception e) {
			return new Hashtable<>();
		}
	}

	public  List<TipoSector> encuentraDarTiposSector(int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select * from `tipoestanteria` where idEmpresa="+idEmpresa;

			return econ.darTiposSector(consulta);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public  List<SKUType> encuentraDarTipoSKU(int idEmpresa) {
		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select * from `tiposku`";

			return econ.darTiposSKU(consulta);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public  List<DataArticulo> encuentraDarArticulos(String texto, int idEmpresa) {
		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "	select a.*,sku.`Descripcion` FROM `articulos` a, `tiposku` sku WHERE "
					+ "a.idEmpresa=sku.idEmpresa and a.idEmpresa="+idEmpresa+" AND "
					+ "(a.`IdArticulo` LIKE '%"
					+ texto
					+ "%'  OR a.`Descripcion` LIKE '%"
					+ texto
					+ "%')  AND a.`idTipo`=sku.`id`";

			return econ.darArticulos(consulta);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	private  List<DataIDDescripcion> encuentraLineasParcial(int idDoc, int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select * from `lineastarea` where idEmpresa="+idEmpresa+" and `IdDocumento` = "
					+ idDoc;

			return econ.darIdDescripcion(consulta);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	/**
	especifica si una ubicacion existe
	@param el id de OJO y el ID de empresa
	@return -0 si no existe-1 si es ojo -2 si es bulro
	@throws what kind of exception does this method throw
	*/
	public int encuentraExisteUbica(String cod, int idEmpresa, int idDeposito) {

		try 
		{

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT 1 'tipo' FROM ojos"+
							" JOIN estanterias  ON estanterias.idEstanteria = ojos.idEstanteria AND estanterias.idEmpresa = ojos.IdEmpresa" +
							" where ojos.idEmpresa="+idEmpresa+" and ojos.idOjo = '"+cod+"' AND estanterias.idDeposito = "+idDeposito+""+
							" UNION all"+
							" SELECT 2 'tipo' FROM articulos WHERE IdTipo=4  AND idEmpresa="+idEmpresa+" AND idArticulo = '"+cod+"';";

			
			return econ.darInt(consulta);
		} catch (Exception e) 
		{
			return 0;
		}
	}
	
	public int depositoPickUp(int idEmpresa, int idDeposito) {

		try 
		{

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT PickUp FROM depositos WHERE idEmpresa = "+idEmpresa+" AND idDeposito = "+idDeposito+";";

			
			return econ.darInt(consulta);
		} catch (Exception e) 
		{
			return 0;
		}
	}

	
	public  int encuentraAltaSector(Sector s, boolean update, int idE) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "INSERT INTO `estanterias` "
					+ "(`IdEstanteria`, `Descripcion`, `numPiso`, `TipoSector`, `CantidadEstantes`, `CantidadModulos`, `Color`, `Sector`, `idDeposito`, `idEmpresa`, `idUso`) "
					+ "VALUES ("
					+ s.getId()
					+ ",'"
					+ s.getDescripcion()
					+ "',"
					+ s.getPiso()
					+ ", "
					+ s.getTipo()
					+ ", "
					+ s.getEstantes()
					+ ", "
					+ s.getModulos()
					+ ",'"
					+ s.getColor()
					+ "',"
					+ s.getSectorGlb()
					+ ","
					+ s.getDeposito()
					+ ","
					+ s.getEmpresa()
					+ ","
					+ s.getUso()
					+ ") "
					+ " ON DUPLICATE KEY UPDATE `Descripcion` = '"
					+ s.getDescripcion()
					+ "', `numPiso` = "
					+ s.getPiso()
					+ ",  "
					+ " `TipoSector` = "
					+ s.getTipo()
					+ ", `CantidadEstantes` = "
					+ s.getEstantes()
					+ ", `CantidadModulos` = "
					+ s.getModulos()
					+ ", `Color` ='" + s.getColor() + "'"
					+ ", `Sector` =" + s.getSectorGlb()
					+ ", `idDeposito` =" + s.getDeposito()
					+ ", `idUso` =" + s.getUso()
					+ ",  idEmpresa =" + idE;


			try {
				

				eper.persistir(consulta);
			} catch (Exception e) {
				
			}
			boolean cerrarConexion = false;
			return econ.UltimoId("SELECT  idEstanteria  FROM `estanterias` where idEmpresa="+idE+" ORDER BY idestanteria DESC LIMIT 1",cerrarConexion);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public  boolean  GenerarVinculo(String codigo, String articulo, int idE) {
		String insert="insert into artbarra (idArticulo,Barra,idEmpresa) values ('"+articulo+"','"+codigo+"',"+idE+")" ;	
		
		try {
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			articuloBarra ab = new articuloBarra(articulo, codigo);
			api.grabarBarra(ab, idE);
			
			boolean pude = eper.persistir(insert);
			return pude;
			//eper.desconectar();
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		
	}
	public  int encuentraAltaTarea(Tarea t, boolean update, int idUsu, int idE,boolean prioritaria) 
	{
		int idMain = 0;
		int ultimoId = 0;
		
		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			
			boolean cerrarConexion = false;
			idMain = econ.UltimoId("SELECT MAX(`main`) FROM `tareas` where idEmpresa="+idE,cerrarConexion);
			ultimoId=eper.persistirDarUltimo("INSERT INTO `picking` (`unidades`,Filtro,idEmpresa) VALUES ("+t.getCantidadPares()+", 'REPO PICKING',"+idE+");", "picking", "id",idE);
			
			System.out.println("ultimo ID = "+idMain);
			idMain++;
			int parcial = 0;
			if (t.isParcial()) {
				parcial = 1;
			}
			
			int priori=0;
			if(prioritaria)
			{
				priori=1;
			}
			if(t.getTipo().getId() != 117)
			{
				ultimoId=t.getIdDocumento();
			}

			for (Usuario us : t.getUsuarios()) 
			{
				String consulta = "INSERT INTO `tareas` "
						+ " (`TipoTarea`, `IdResponsable`, `Porcentaje`, `Estado`, `Observaciones`, `FechaFin`, `Productividad`, `Unidades`,`IdDoc`,`IdUsuario`, `main`, `Parcial`, idEmpresa, Prioritaria, idDeposito) "
						+ " VALUES ("
						+ t.getTipo().getId()
						+ ", "
						+ t.getResponsable().getId()
						+ ","
						+ ""
						+ t.getPorcentaje()
						+ ", "
						+ t.getEstado().getId()
						+ ", '"
						+ t.getObservacion()
						+ "', '', 0, "
						+ t.getCantidadPares()
						+ ","
						+ ultimoId
						+ ","
						+ us.getNumero() + "," + idMain + "," + parcial + ","+idE+","+priori+","+t.getIdDeposito()+");";

				System.out.println(consulta);

				eper.persistir(consulta);
			}
			
			int idTar = econ.UltimoId("SELECT MAX(`IdTarea`) FROM `tareas` where idEmpresa="+idE,cerrarConexion);
			t.setId(idTar);
			encuentraAltaEventoTarea(idTar, 0, idUsu,0,idE);	// Alta de tarea evento
			
			if(t.getTipo().getId() == 117) { // Si es elevadorista
				for(ArticuloLineaReposicion ar : t.getArticulosIn()) {//inserto todas las lineas de ArticuloLineaReposicion
					String consulta = "INSERT INTO tarea_movimiento_articulos "
							+ " (idTarea, idUsuario, idArticulo, cantidad, idEmpresa) " 
							+ " VALUES("
							+ idTar + ", "
							+ 0 + ",'"
							+ ar.getIdArticulo() + "', "
							+ ar.getCantidad() + ", "
							+ idE + ") "
							+ " ON DUPLICATE KEY UPDATE cantidad=cantidad+"+ ar.getCantidad()+"  ;";
					
					eper.persistir(consulta);
				}
			}

		} catch (Exception e) {
			
		}

		return idMain;

	}

	public  boolean encuentraBajaSector(int id, int idEmpresa) 
	{
		boolean pude=false;
		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consultaII = "DELETE FROM `estanterias` WHERE idEmpresa="+idEmpresa+" AND `idEstanteria` ="
					+ id;
			String consulta = "DELETE FROM `ojos` WHERE idEmpresa="+idEmpresa+" AND  `IdEstanteria` = "
					+ id;
			String consultaI ="select 0,c.idOjo,c.cantidad from cantidadojo c "
					+ "inner join ojos o on c.idOjo=o.idOjo and c.idEmpresa=o.idEmpresa "
					+ "where o.idEmpresa="+idEmpresa+" AND o.idEstanteria= "+ id;
			
			

			try 
			{
				boolean hayCantOjos = true;
				List<DataIDDescripcion> cantidadOjos = darListaDataIdDescripcionBConsulMYSQL(consultaI);
				
				for(DataIDDescripcion co:cantidadOjos){
				
					int cant = Integer.parseInt(co.getDescripcionB());
					if (cant > 0){
						hayCantOjos = false;
					}
				}
				
				if (hayCantOjos){
				
				for(DataIDDescripcion co:cantidadOjos){
					
					String consultaI2 ="DELETE FROM cantidadojo where idEmpresa="+idEmpresa+" AND idOjo= '" +co.getDescripcion()+"'";
					
					if (hayCantOjos){
						hayCantOjos= eper.persistir(consultaI2);
					}
				}
									
				Connection cone2;
				
				
				if (hayCantOjos){
			

				pude =eper.persistir(consulta);
				
				if(pude)
				{
					pude = eper.persistir(consultaII);					
				}
				
				}
				
				}
				
			} catch (Exception e) {
				return false;
			}

			return pude;
		} catch (Exception e) {
			return pude;
		}
	}

	public  boolean encuentraAltaOjo(List<Ojo> ojos, int idE) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String queries = "";
			for (Ojo o : ojos) {
				String consulta = "INSERT INTO `ojos` (`IdOjo`, `IdEstanteria`, `idEstante`, `idModulo`, `IdRecorrido`, `Area`, `Ancho`,`Alto`,idEmpresa) "
						+ " VALUES ('"
						+ o.getIdOjo()
						+ "', "
						+ o.getIdSector()
						+ ", "
						+ o.getModulo()
						+ ", "
						+ o.getEstante()
						+ ", 999999, "
						+ o.getArea()
						+ ","
						+ o.getAncho()
						+ ","
						+ o.getAlto()
						+ ","
						+ idE + ") "+
						"ON DUPLICATE KEY UPDATE `IdEstanteria` = "
					+ o.getIdSector()
					+ ", `idEstante` = "
					+ o.getModulo()
					+ ",  "
					+ " `idModulo` = "
					+ o.getEstante()
					+ ",  `Area` = "
					+ o.getArea()
					+ ", `Ancho` =" + o.getAncho() 
					+ ", `Alto` =" + o.getAlto()
					+ ", idEmpresa =" +idE +";";

				
				
				
				// /SI EST? ALREVEZ PERO EST? BIEN AS?

				queries+=consulta;
				
			}
			
			try {
				
				System.out.println(queries);
				eper.persistir(queries);
			} catch (Exception e) {
				
			}
			return true;
		} catch (Exception e) {
			return true;
		}
	}

	public  boolean encuentraAltaUpdateArticulo(DataArticulo da, int idE) {
		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "";
			if (da.getFuncion().equals("999999"))// es alta
			{
				consulta = "INSERT INTO `articulos` (`IdArticulo`, `Descripcion`, `AnchoCaja`, `AltoCaja`,`ProfCaja`, `Stock99`,`IdTipo`,idEmpresa) "
						+ "VALUES ('"
						+ da.getId()
						+ "','"
						+ da.getDescripcion()
						+ "', "
						+ da.getAnchoCaja()
						+ ","
						+ da.getAltoCaja()
						+ ", "
						+ da.getProfCaja()
						+ ","
						+ da.getCantidad() + "," + da.getIdTypeSKU() + ","+idE+")";
			} else// es update
			{
				consulta = "INSERT INTO `articulos` (`IdArticulo`, `Descripcion`, `AnchoCaja`, `AltoCaja`,`ProfCaja`, `Stock99`,`IdTipo`,idEmpresa) "
						+ "VALUES ('"
						+ da.getId()
						+ "','"
						+ da.getDescripcion()
						+ "', "
						+ da.getAnchoCaja()
						+ ","
						+ da.getAltoCaja()
						+ ", "
						+ da.getProfCaja()
						+ ","
						+ da.getCantidad()
						+ ","
						+ da.getIdTypeSKU()
						+ ")"
						+ " ON DUPLICATE KEY UPDATE `IdArticulo` = '"
						+ da.getId()
						+ "',`Descripcion` = '"
						+ da.getDescripcion()
						+ "', `AnchoCaja` = "
						+ da.getAnchoCaja()
						+ ", `AltoCaja` = "
						+ da.getAltoCaja()
						+ ", `ProfCaja` = "
						+ da.getProfCaja()
						+ ", `Stock99` = "
						+ da.getCantidad() + ",`IdTipo`=" + da.getIdTypeSKU() + ",idEmpresa=" + idE;
			}

			try {
				
				System.out.println(consulta);
				eper.persistir(consulta);
			} catch (Exception e) {
				
			}

			return true;
		} catch (Exception e) {
			return true;
		}
	}
	
	public  boolean encuentraAltaUpdateTipoEstanteria(TipoSector ts, int idE) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "";
			if (ts.getIdTipo() == 999999)// es alta
			{
				consulta = "INSERT INTO `tipoestanteria` (`Descripcion`, `Ancho`, `Alto`, `Profundidad`,idEmpresa) "
						+ "VALUES ('"
						+ ts.getDescripcion()
						+ "',"
						+ ts.getAncho()
						+ ", "
						+ ts.getAlto()
						+ ","
						+ ts.getProfundidad() 
						+ ","
						+ idE + ");";
			} else// es update
			{
				consulta = "INSERT INTO `tipoestanteria` (`idTipo`, `Descripcion`, `Ancho`, `Alto`, `Profundidad`,idEmpresa) "
						+ " VALUES ("
						+ ts.getIdTipo()
						+ ",'"
						+ ts.getDescripcion()
						+ "',"
						+ ts.getAncho()
						+ ", "
						+ ts.getAlto()
						+ ","
						+ ts.getProfundidad()
						+ ","
						+ idE
						+ ") "
						+ " ON DUPLICATE KEY UPDATE `Descripcion` = '"
						+ ts.getDescripcion()
						+ "', `Ancho` = "
						+ ts.getAncho()
						+ ", `Alto` = "
						+  ts.getAlto()
						+ ", `Profundidad` = "
						+ ts.getProfundidad()
						+ ", idEmpresa = "
						+ idE;
			}

			try {
				
				System.out.println(consulta);
				eper.persistir(consulta);
			} catch (Exception e) {
				
			}

			return true;
		} catch (Exception e) {
			return true;
		}

	}

	public  boolean encuentraAltaUpdateTipoSKU(SKUType sku, int idE) {
		boolean ok = false;
		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "";
			if (sku.getId() == 999999)// es alta
			{
				consulta = "INSERT INTO `tiposku` (`Descripcion`,idEmpresa) "
						+ "VALUES ('" + sku.getDescripcion() + "',"+idE+")";
			} else// es update
			{
				consulta = "INSERT INTO `tiposku` (`idTipoSKU`, `Descripcion`,idEmpresa) "
						+ " VALUES ("
						+ sku.getId()
						+ ",'"
						+ sku.getDescripcion()
						+ ",'"
						+ idE
						+ "') "
						+ " ON DUPLICATE KEY UPDATE `Descripcion` = '"
						+ sku.getDescripcion() + "' idEmpresa = "+idE;
			}
			try 
			{
				
				
				ok =eper.persistir(consulta);
			} catch (Exception e) 
			{
				return false;
			}

			return ok;
		}
		catch (Exception e) 
		{
			return ok;
		}

	}

	public  boolean encuentraBajaArticulo(String id, int idEmpresa) {
		boolean retorno = false;
		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "DELETE FROM `articulos` WHERE idEmpresa="+idEmpresa+" AND IdArticulo='" + id
					+ "'";

			try {
			

				retorno = eper.persistir(consulta);
			} catch (Exception e) {
				
				return false;
			}

			return retorno;
		} catch (Exception e) {
			return false;
		}
	}

	public  boolean encuentraBajaTipoEstanteria(int id, int idEmpresa) {
		boolean retorno = false;
		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "DELETE FROM `tipoestanteria` WHERE idEmpresa="+idEmpresa+" AND `idTipo`="
					+ id;

			try {
				

				retorno = eper.persistir(consulta);
			} catch (Exception e) {
				
				return false;
			}

			return retorno;
		} catch (Exception e) {
			return false;
		}
	}
	

	public  boolean encuentraBajaTipoSKU(int id, int idEmpresa) {
		boolean retorno = false;
		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "DELETE FROM `tiposku` WHERE idEmpresa="+idEmpresa+" AND `id`=" + id;

			try {
				

				retorno = eper.persistir(consulta);
			} catch (Exception e) {
				
				return false;
			}

			return retorno;
		} catch (Exception e) {
			return false;
		}
	}

	public  List<Sector> encuentraDarSectores(int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT `idEstanteria`, `Descripcion`, `numPiso`, `TipoSector`, `CantidadEstantes`, `CantidadModulos`, `Sector` , `Color`, `idDeposito`, `idUso` "
					        + "FROM `estanterias`  where `idEstanteria` != 0 AND idEmpresa = "+idEmpresa+" and iduso in (1,2) order by idestanteria asc;";

			return econ.darSectores(consulta);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	public  List<DepositoAdmin> encuentraDarDepositosAdmin(int depoPpal, int idEmpresa) 
	{

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();


//			String consulta = "select idDeposito,Nombre,Direccion,Login,Prioridad,Pickup,PreparaPickup,PreparaDelivery,PreparaEnvioCD,IdGrupo from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPpal+" "
//					+ "UNION ALL select idDeposito,Nombre,Direccion,Login ,Prioridad,Pickup,PreparaPickup,PreparaDelivery,PreparaEnvioCD,IdGrupo from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPpal+"";
			String consulta = "select idDeposito,Nombre,Direccion,Login, d.Prioridad, d.PreparaPickup, d.PreparaDelivery, d.PreparaEnvioCD, d.IdGrupo from depositos d WHERE idEmpresa="+idEmpresa+" and idDeposito="+depoPpal+" "
				  + "UNION ALL select idDeposito,Nombre,Direccion,Login, d.Prioridad, d.PreparaPickup, d.PreparaDelivery, d.PreparaEnvioCD, d.IdGrupo from depositos d WHERE idEmpresa="+idEmpresa+" and idDeposito!="+depoPpal+"";


			return econ.darDepositos(consulta);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	public  DepositoAdmin BuscarDeposito(int idDeposito, int idEmpresa) 
	{

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select idDeposito,Nombre,Direccion,Login,ciudad,departamento,telefono,nroPuerta, local from depositos WHERE idEmpresa="+idEmpresa+" and idDeposito="+idDeposito;

			return econ.BuscarDeposito(consulta);
		} catch (Exception e) {
			return null;
		}
	}
	
	public  Hashtable<Integer, DepositoAdmin> HTDepositos(int idEmpresa) 
	{

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select idDeposito,Nombre,Direccion,Login from depositos WHERE idEmpresa="+idEmpresa;

			return econ.darDepositosHT(consulta);
		} catch (Exception e) {
			return null;
		}
	}
	
	public  List<UsoEstanteria> encuentraDarUsosEstanterias(int idEmpresa) 
	{

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT idUso, descripcion, inventariable FROM usos_estanteria where idempresa="+idEmpresa+
							" AND iduso != 0;";

			return econ.darUsos(consulta);
		} catch (Exception e) {
			return null;
		}
	}

	public  List<DataIDDescripcion> encuentraDarTiposUsuarios(int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select * from `perfiles` where idEmpresa="+idEmpresa;

			return econ.darIdDescripcion(consulta);
		} catch (Exception e) {
			return null;
		}
	}

	public  String encuentraPrimerOjo(String id, int idEmpresa) {
		String codUbi = "";
		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT `IdOjo` FROM `ojos` WHERE idEmpresa="+idEmpresa+" and `IdEstanteria` = "
					+ id + " " + " ORDER BY `idEstante`, `idModulo` LIMIT 1";
			try {

				codUbi = econ.UltimoCUbi(consulta);
			} catch (Exception e) {
				
			}

			return codUbi;
		} catch (Exception e) {
			return "";
		}
	}
	
	public List<DataIDDescripcion> darSiguientesCubis(int cantidad, int idEmpresa) 
	{
		
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			/*if(idEmpresa==4)
			{
				query="	select 0, Codigo from codigoubicacion where Orden = (	select orden from codigoubicacion CU where CU.Codigo = (	SELECT ojo FROM  (				select MAX(O.idEstanteria) eria,MAX(idModulo) modul,MAX(idEstante) esta, O.idOjo ojo from ojos O INNER JOIN estanterias E ON E.idEstanteria=O.idEstanteria WHERE E.IdEmpresa="+idEmpresa+" AND E.idUso=1			) t0 		))+1";
			}
			else
			{*/
			 String	query = "select 1,codigo from codigoubicacion c "
						+ "where c.idEmpresa="+idEmpresa+" and c.Codigo not in (select idojo from ojos where idEmpresa="+idEmpresa+") "+
						"order by substring(c.Codigo,1,1),convert(substring(c.Codigo,2,3),signed integer) limit "+cantidad;
			//}
			
			return econ.darListaDataIdDescripcion(query);		
						
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return new ArrayList<>();
			
		}
		
	}
	
	public List<DataIDDescripcion> encuentraOjos(int id, int idEmpresa) 
	{
		
		
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			String query = "select 1,idojo from ojos where idEmpresa="+idEmpresa+" and idestanteria = "+id+
					" order by substring(idojo,1,1),convert(substring(idojo,2,3),signed integer)";
			
			return econ.darListaDataIdDescripcion(query);		
						
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return new ArrayList<>();
			
		}
		
	}

	public  DataOjo encuentraCalcularOcupacion(DataOjo dojo) 
	{
		

		int cantLibre;
		int cantOcupada;
		int porcentaje;

		// variables auxiliares
		int cuantoEntraAncho;
		int cuantoEntraAlto;
		int cuantoEntra;

		int promAnchoCaja;
		int promAltoCaja;

		int suma = 0;
		int sumaAN = 0;
		int sumaAL = 0;

		for (DataArticulo ar : dojo.getArticulos()) {
			if (ar.getId().contains("057.10463")) {
				System.out.println("");
			}
			suma += ar.getCantidad();
			sumaAN += (ar.getAnchoCaja() * ar.getCantidad());
			sumaAL += (ar.getAltoCaja() * ar.getCantidad());
		}

		promAltoCaja = sumaAL / suma;
		promAnchoCaja = sumaAN / suma;

		// calculamos cuanto entra de ancho y de alto
		try {
			cuantoEntraAlto = dojo.getAltoOjo() / promAltoCaja;
			cuantoEntraAncho = dojo.getAnchoOjo() / promAnchoCaja;

			cuantoEntra = cuantoEntraAlto * cuantoEntraAncho;

			System.out.println("entran " + cuantoEntra);
			System.out.println("hay " + suma + " ocupados");

			// calculamos porcentaje ocupado
			cantLibre = cuantoEntra - suma;
			System.out.println("hay " + cantLibre + " lugares libres");

			cantOcupada = suma;

			porcentaje = (cantOcupada * 100) / cuantoEntra;

			System.out.println("hay un " + porcentaje + "% ocupado");

			dojo.setCantLibre(cantLibre);
			dojo.setCantOcupada(cantOcupada);
			dojo.setPorcentaje(porcentaje);
		} catch (Exception e) {
			dojo.setCantLibre(suma);
			dojo.setCantOcupada(suma);
			dojo.setPorcentaje(0);
		}
		return dojo;
	}

	public  boolean encuentraPuedeBajaEstanteria(String cod, int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT O.`idOjo`, O.`idArticulo`,A.Descripcion , "
					+ " O.`Cantidad`, A.AnchoCaja, A.AltoCaja, A.ProfCaja, OS.Ancho, OS.Alto, OS.Profindidad, OS.IdEstanteria, OS.idEstante, OS.idModulo "
					+ " FROM `ojostienenarticulos` O, `articulos` A, `ojos` OS "
					+ " WHERE o.idEmpresa=A.idEmpresa and A.idEmpresa=OS.idEmpresa and OS.idEmpresa="+idEmpresa
					+ " AND O.idArticulo = A.IdArticulo AND O.idOjo = OS.IdOjo AND `IdEstanteria` ="
					+ cod + ";";
			System.out.println(consulta);
			return econ.hayReg(consulta);
		} catch (Exception e) {
			return false;
		}
	}

	public  DataArticulo encuentraCodArticulo(String cod, int idEmpresa) 
	{

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select A.Id, A.Descripcion,0,0,0,0,1 from  art_descripcion A,  `artbarra` B "
					+ "where A.idEmpresa=B.idEmpresa and B.idEmpresa="+idEmpresa+" and  A.Id = B.IdArticulo AND B.Barra = '"+cod+"';";
			DataArticulo ret = econ.codArticulo(consulta);
			if(ret.getId()==null)
			{

				cone = econ.getConnection();
				consulta = "select A.idArticulo, A.Descripcion,0,0,0,0,1 from  articulos A where idEmpresa="+idEmpresa+" and A.idArticulo='"+cod+"'";
				ret = econ.codArticulo(consulta);
				
				if((ret.getId()==null || ret==null) && idEmpresa==8)//validacion para BAS
				{
					cone = econ.getConnection();
					CallWSRFID call = new CallWSRFID();
					String ean = call.darEan(cod);
					consulta = "select A.Id, A.Descripcion,0,0,0,0,1 from  art_descripcion A,  `artbarra` B "
							+ "where A.idEmpresa=B.idEmpresa and B.idEmpresa="+idEmpresa+" and  A.Id = B.IdArticulo AND B.Barra = '"+ean+"';";
					ret = econ.codArticulo(consulta);
				}
				
			}
			return ret;
			 
		} catch (Exception e) {
			return null;
		}
	}
	
	public  DataArticulo encuentraCodArticuloT(String cod, int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select A.idArticulo, A.Descripcion,0,0,0,0,1 from  articulos A,  `artbarra` B "
					+ "where A.idEmpresa=B.idEmpresa and B.idEmpresa="+idEmpresa+" and  A.idArticulo = B.IdArticulo AND B.Barra = '"+cod+"';";

			return econ.codArticuloSinBase(consulta);
		} catch (Exception e) {
			return null;
		}
	}


	
	public  List<DataPickingS> encuentraDarPickingS(String destino, int idEmpresa, int idDeposito) 
	{

		try 
		{

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "";
			if(destino.equals("diferencias"))
			{
				consulta = "select * from (select idPicking, GROUP_CONCAT(DISTINCT DEP.Nombre) DESTINOS,  SUM(RAR.Cantidad) sol, "
						+ "SUM(RAR.Picked) pick, SUM(RAR.Remitidas) remi, GROUP_CONCAT(DISTINCT DEP.idDeposito) deps, "
						+ "SUM(RAR.Verif) verif, USU.USUS from reposicion_articulos RAR, depositos DEP, "
						+ "( SELECT TA.iddoc, GROUP_CONCAT(CONCAT(' ',US.Nombre,' ', US.Apellido))  USUS,TA.idEmpresa FROM tareas TA "
						+ "INNER JOIN usuarios US ON TA.idUsuario = US.idUsuario and TA.idEmpresa=US.idEmpresa GROUP BY TA.idDoc) USU "
						+ "WHERE RAR.Origen="+idDeposito+" AND RAR.idEmpresa=DEP.idEmpresa and DEP.idEmpresa=USU.idEmpresa and RAR.idEmpresa="+idEmpresa+" and "
						+ "USU.idDoc = RAR.idPicking AND RAR.Destino = DEP.idDeposito AND  RAR.idPicking!=0 AND RAR.Estado!=4  "
						+ "AND RAR.Remitidas =0 group by RAR.idPicking) t0 where sol!=pick and pick>0 ";
			}
			else if(destino.equals("packing"))
			{
				consulta = "select * from (select idPicking, GROUP_CONCAT(DISTINCT DEP.Nombre) DESTINOS,  SUM(RAR.Cantidad) sol, "
						+ "SUM(RAR.Picked) pick, SUM(RAR.Remitidas) remi, GROUP_CONCAT(DISTINCT DEP.idDeposito) deps, "
						+ "SUM(RAR.Verif) verif, USU.USUS from reposicion_articulos RAR, depositos DEP , "
						+ "(SELECT TA.iddoc, GROUP_CONCAT(CONCAT(' ',US.Nombre,' ', US.Apellido)) USUS,TA.idEmpresa FROM tareas TA "
						+ "INNER JOIN usuarios US ON TA.idUsuario = US.idUsuario and TA.idEmpresa=US.idEmpresa GROUP BY TA.idDoc) USU "
						+ "WHERE RAR.Origen="+idDeposito+" AND RAR.idEmpresa=DEP.idEmpresa and DEP.idEmpresa=USU.idEmpresa and RAR.idEmpresa="+idEmpresa+" and "
						+ "USU.iddoc = RAR.idPicking AND  RAR.Destino = DEP.idDeposito AND  RAR.idPicking!=0 AND RAR.Estado!=4 "
						+ "group by RAR.idPicking) t0 where pick>0 and remi>0 ";
			}
			else if(destino.equals("activos"))
			{
				consulta = "SELECT *"
						+ " FROM ("
						+ "		SELECT idPicking, CONCAT(GROUP_CONCAT(DISTINCT DEP.Nombre)) DESTINOS, SUM(RAR.Cantidad) sol, SUM(RAR.Picked) pick, SUM(RAR.Remitidas) remi, GROUP_CONCAT(DISTINCT DEP.idDeposito) deps, SUM(RAR.Verif) verif, USU.USUS"
						+ "		FROM reposicion_articulos RAR, depositos DEP, "
						+ "		("
						+ "			SELECT TA.iddoc, GROUP_CONCAT(CONCAT(' ',US.Nombre,' ', US.Apellido)) USUS,TA.idEmpresa"
						+ "			FROM tareas TA"
						+ "			INNER JOIN usuarios US ON TA.idUsuario = US.idUsuario AND TA.idEmpresa=US.idEmpresa"
						+ "			GROUP BY TA.idDoc"
						+ "		 ) USU"
						+ "		WHERE RAR.Origen="+idDeposito+" AND RAR.idEmpresa=DEP.idEmpresa AND DEP.idEmpresa=USU.idEmpresa AND RAR.idEmpresa="+ idEmpresa +" AND USU.idDoc = RAR.idPicking AND RAR.Destino = DEP.idDeposito AND RAR.idPicking!=0 AND RAR.Estado NOT in (4,5,7,11) AND RAR.Remitidas < rar.cantidad"
						+ "		GROUP BY RAR.idPicking) t0";
			}
			else if (destino.equals("externos"))
			{
				consulta = "SELECT *\r\n" + 
						" FROM (\r\n" + 
						" SELECT RAR.idPicking, CONCAT(GROUP_CONCAT(DISTINCT DEP.Nombre)) DESTINOS, SUM(RAR.Cantidad) sol, SUM(RAR.Picked) pick, SUM(RAR.Remitidas) remi, GROUP_CONCAT(DISTINCT DEP.idDeposito) deps, SUM(RAR.Verif) verif, USU.USUS,ra.cant\r\n" + 
						" FROM reposicion_articulos RAR\r\n" + 
						" INNER JOIN (\r\n" + 
						" SELECT COUNT(DISTINCT destino) cant, idpicking, idempresa\r\n" + 
						" FROM reposicion_articulos\r\n" + 
						" WHERE idPicking!=0 AND Estado NOT in (4,7)\r\n" + 
						" GROUP BY idpicking) RA ON RA.idpicking = rar.idpicking AND RA.idempresa=RAR.IdEmpresa, depositos DEP, (\r\n" + 
						" SELECT TA.iddoc, GROUP_CONCAT(CONCAT(' ',US.Nombre,' ', US.Apellido)) USUS,TA.idEmpresa\r\n" + 
						" FROM tareas TA\r\n" + 
						" INNER JOIN usuarios US ON TA.idUsuario = US.idUsuario AND TA.idEmpresa=US.idEmpresa AND US.idUsuario = 0\r\n" + 
						" GROUP BY TA.idDoc) USU\r\n" + 
						" WHERE RAR.Origen="+idDeposito+" AND  RAR.idEmpresa=DEP.idEmpresa AND DEP.idEmpresa=USU.idEmpresa AND \r\n" + 
						" RAR.idEmpresa="+idEmpresa+" \r\n" + 
						" AND USU.idDoc = RAR.idPicking AND RAR.Destino = DEP.idDeposito AND RAR.idPicking!=0 AND RAR.Estado NOT in (4,7) AND RAR.Remitidas < rar.cantidad\r\n" + 
						" GROUP BY RAR.idPicking) t0";
			}
			else
			{
				/*consulta= "select * from (select idPicking, CONCAT(GROUP_CONCAT(DISTINCT DEP.Nombre)) DESTINOS, SUM(RAR.Cantidad) sol, "
						+ "SUM(RAR.Picked) pick, SUM(RAR.Remitidas) remi, GROUP_CONCAT(DISTINCT DEP.idDeposito) deps, "
						+ "SUM(RAR.Verif) verif, USU.USUS from reposicion_articulos RAR, depositos DEP , "
						+ "(SELECT TA.iddoc, GROUP_CONCAT(CONCAT(' ',US.Nombre,' ', US.Apellido))  USUS,TA.idEmpresa FROM tareas TA "
						+ "INNER JOIN usuarios US ON TA.idUsuario = US.idUsuario and TA.idEmpresa=US.idEmpresa GROUP BY TA.idDoc) USU "
						+ "WHERE RAR.idEmpresa=DEP.idEmpresa and DEP.idEmpresa=USU.idEmpresa and RAR.idEmpresa="+idEmpresa+" and "
						+ "USU.idDoc = RAR.idPicking AND RAR.Destino = DEP.idDeposito AND  RAR.idPicking!=0  AND "
						+ "RAR.Estado not in (4,7) AND RAR.Remitidas < rar.cantidad "
						+ "group by RAR.idPicking) t0 where pick>0";*/
				consulta= "select * from (select RAR.idPicking, CONCAT(GROUP_CONCAT(DISTINCT DEP.Nombre)) DESTINOS, SUM(RAR.Cantidad) sol, "
						+ "SUM(RAR.Picked) pick, SUM(RAR.Remitidas) remi, GROUP_CONCAT(DISTINCT DEP.idDeposito) deps, "
						+ "SUM(RAR.Verif) verif, USU.USUS,ra.cant from reposicion_articulos RAR "
						+ "INNER JOIN ( " + 
						"SELECT COUNT(distinct idPosSort) cant, idpicking, idempresa FROM reposicion_articulos " + 
						"WHERE idPicking!=0 AND Estado NOT in (4,7) " + 
						"GROUP BY idpicking) " + 
						"RA ON RA.idpicking = rar.idpicking AND RA.idempresa=RAR.IdEmpresa "
						+ ", depositos DEP , "
						+ "(SELECT TA.iddoc, GROUP_CONCAT(CONCAT(' ',US.Nombre,' ', US.Apellido))  USUS,TA.idEmpresa FROM tareas TA "
						+ "INNER JOIN usuarios US ON TA.idUsuario = US.idUsuario and TA.idEmpresa=US.idEmpresa GROUP BY TA.idDoc) USU "
						+ "WHERE RAR.Origen="+idDeposito+" AND RAR.idEmpresa=DEP.idEmpresa and DEP.idEmpresa=USU.idEmpresa and RAR.idEmpresa="+idEmpresa+" and "
						+ "USU.idDoc = RAR.idPicking AND RAR.Destino = DEP.idDeposito AND  RAR.idPicking!=0  AND "
						+ "RAR.Estado not in (4,7) AND RAR.Remitidas < rar.cantidad "
						+ "group by RAR.idPicking) t0 where pick>0";
			}	

			System.out.println(consulta);
			
			return econ.darPickingS(consulta);
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	public  boolean encuentraActualizaMedidaArticulo(DataArticulo d, boolean afectaBase, int idEmpresa) 
	{
		boolean retorno = false;
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "";
			if (afectaBase) 
			{
				consulta = "update articulos set `AnchoCaja` = "
						+ d.getAnchoCaja() + ", `AltoCaja` = "
						+ d.getAltoCaja() + ", `ProfCaja` = " + d.getProfCaja()
						+ " where `IdArticulo` like '" + d.getCodBase() + "%' AND idEmpresa ="+idEmpresa+";";
			} 
			else 
			{
				consulta = "update articulos set `AnchoCaja` = "
						+ d.getAnchoCaja() + ", `AltoCaja` = "
						+ d.getAltoCaja() + ", `ProfCaja` = " + d.getProfCaja()
						+ " where `IdArticulo` = '" + d.getId() + "' AND idEmpresa ="+idEmpresa+";";
			}
			try 
			{
				retorno = eper.persistir(consulta);
			} 
			catch (Exception e) 
			{
				return false;
			}
			return retorno;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
		

	
	
	public  boolean encuentraActualizaEstadoEnvio(int estado, int idEnvio, int idEmpresa) 
	{
		boolean retorno = false;
		
		String consulta = "UPDATE `envio` SET `IdEstadoEnvio`="+estado+" WHERE  `idEnvio`="+idEnvio+" AND idEmpresa ="+idEmpresa+";";
		try 
		{
			retorno = eper.persistir(consulta);
		}
		catch (Exception e) 
		{
				
				return false;
		}

		return retorno;
		
	}
	
	public  List<DataIDDescripcion> encuentraDaroperarios(int idEmpresa) 
	{
		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select `idUsuario`, concat_ws(' ', nombre, apellido) as persona, perfil  from `usuarios` "
					+ "where idEmpresa="+idEmpresa+" and baja=0 AND perfil IN (1,2,11) ";
			

			return econ.darIdDescripcion(consulta);
		} catch (Exception e) {
			return null;
		}
	}
	
	public  List<DataIDDescripcion> infoAvanceTareasOperarios(int idEmpresa) 
	{
		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT SUM(ra.Cantidad-ra.picked), CONCAT_WS('<br>', nombre, apellido) FROM tareas t\r\n" + 
					"INNER JOIN usuarios u ON t.idUsuario=u.idUsuario AND t.IdEmpresa=u.idEmpresa\r\n" + 
					"INNER JOIN reposicion_articulos ra ON ra.idPicking=t.idDoc AND t.IdEmpresa=ra.IdEmpresa\r\n" + 
					"WHERE t.IdEmpresa="+idEmpresa+" AND t.Estado !=2\r\n" + 
					"GROUP BY ra.idUsuario\r\n" + 
					"HAVING SUM(ra.Cantidad-ra.picked) >0\r\n" + 
					"ORDER BY SUM(ra.Cantidad-ra.picked) desc\r\n" + 
					"LIMIT 10";

			return econ.darIdDescripcion(consulta);
		} catch (Exception e) {
			return null;
		}
	}
	

	public  List<DataIDDescripcion> encuentraDarSuper(int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select `idUsuario`, `Nickname` from `usuarios` where idEmpresa="+idEmpresa+" and "
					+ "`Perfil` = 3 AND baja=0 ";

			return econ.darIdDescripcion(consulta);
		} catch (Exception e) {
			return null;
		}
	}

	public  List<DataIDDescripcion> encuentraDarTiposTarea(int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select `IdTipo`, `Tarea` FROM `tipostarea`  "
					+ "WHERE idEmpresa="+idEmpresa+" and idTipo NOT BETWEEN 100 AND 120";

			return econ.darIdDescripcion(consulta);
		} catch (Exception e) {
			return null;
		}
	}

	
	private  Double encuentraDistribuirCargaTarea(int idMain, int cantUsuarios, int idEmpresa, List<Usuario> usus, int idPicking) 
	{
		Double porcentaje = 100.00 / cantUsuarios;
		String qUnidades = "";
		boolean setUnidades = true;
		/*if(cantUsuarios > 1) {
			setUnidades = false;
		}*/
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			String consulta = "";
			for(Usuario u : usus){
				consulta += "UPDATE `tareas` t SET `PorcentajeAsig` = "
						+ porcentaje + ", unidades=(select sum(cantidad) from reposicion_articulos where idusuario="+u.getNumero()+" and idpicking="+idPicking+") "
						+ " WHERE `main` =" + idMain+" and idusuario="+u.getNumero()+" AND idEmpresa ="+idEmpresa+";";
			}
			
						

			try {
				eper.persistir(consulta);
			} catch (Exception e) {
				
				return 0.0;
			}

			return porcentaje;
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	public  void encuentraDistribuirCargaPicking(Tarea tarea,List<DataLineaRepo> articulosIn, int idEmpresa, int idMain) 
	{
		List<Usuario> usus = tarea.getUsuarios();
		//if(usus.size()>1)
		//{
			if(usus.size() > 1) {
				articulosIn = encuentraDarArtRepos(tarea.getIdDocumento(), "", idEmpresa, 0);
			}
			
			int corte = articulosIn.size()/usus.size();
			int corteInpar = 0;
			if(articulosIn.size() % usus.size() > 0) {
				corteInpar = 1;
			}
	
			int pasada = 0;
			int indexUsuario = 0;
			Hashtable<Integer, Integer> cantidades_usuarios = new Hashtable<>();
			for (Iterator<DataLineaRepo> iterator = articulosIn.iterator(); iterator.hasNext();) 
			{
				DataLineaRepo linea = iterator.next();
				Usuario curr = usus.get(indexUsuario);
				
				updateUsuarioLineaPicking(curr.getNumero(),linea, tarea.getIdDocumento(),idEmpresa);
				
				/*int cant = 0;
				try {
					 cant = cantidades_usuarios.get(curr.getNumero()) + linea.getSolicitada();
				} catch (Exception e) {
					cant = linea.getSolicitada();
				}
				cantidades_usuarios.put(curr.getNumero(), cant);
				*/
				iterator.remove();
			    pasada++;
			    if(pasada>= corte + corteInpar)
			    {
			    	pasada=0;
			    	indexUsuario++;
			    }
			    	
			}
			
			
			encuentraDistribuirCargaTarea(idMain, usus.size(), idEmpresa, usus, tarea.getIdDocumento());
			System.out.println("");
			
			
		}
		
	//}
	
	

	private  void updateUsuarioLineaPicking(int numero, DataLineaRepo art, int idPick, int idEmpresa) 
	{
		try 
		{
			String query = "";
			query += "UPDATE `reposicion_articulos` SET idUsuario = "+numero+" WHERE  `idPicking`="+idPick+" AND `idArticulo`='"+art.getIdArticulo()+"' AND "
					+ "`Origen`="+art.getIdDepOrigen()+" AND `Destino`="+art.getIdDepDestino()+" and seccion="+art.getPedido()+" and idSolicitudTraslado="+art.getDocumento()+
						" AND idEmpresa ="+idEmpresa+" and idusuario = 0;";
			query += "update reposicion_articulos_ojos SET idUsuario = "+numero+" WHERE  `idPicking`="+idPick+" AND `idArticulo`='"+art.getIdArticulo()+"' "+
														" AND `Destino`="+art.getIdDepDestino()+" and pedido="+art.getPedido()+" and solicitud="+art.getDocumento()+
						" AND idEmpresa ="+idEmpresa+" and idusuario = 0;";
					
			eper.persistir(query);			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			
			
		}
		
	}


	public  List<DataLineaListaTareasMob> encuentraDarTareasMob(Usuario usuario,int idEmpresa) {
		return encuentraDarTareas(usuario, idEmpresa);
	}
	
	
	
	private  List<DataLineaListaTareasMob> encuentraDarTareas(Usuario usuario,int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT DISTINCT T.main, T.IdTarea, T.TipoTarea, TI.Tarea, T.Porcentaje, T.Estado, TE.Descripcion, T.Observaciones, T.IdUsuario,T.IdDoc,T.Unidades,TI.Resolucion, ur.idusuario, concat(ur.nombre,' ',ur.apellido)\r\n" + 
					" FROM tareas T\r\n" + 
					" INNER JOIN  tipostarea TI ON TI.idTipo = T.TipoTarea AND TI.IdEmpresa = T.IdEmpresa\r\n" + 
					" INNER join  tareasestados TE ON TE.idEstado = T.Estado AND TE.IdEmpresa = T.IdEmpresa\r\n" + 
					" INNER JOIN usuarios u ON u.idUsuario = T.idUsuario AND u.idEmpresa = T.IdEmpresa\r\n" + 
					" INNER JOIN usuarios ur ON ur.idUsuario = T.idresponsable AND ur.idEmpresa = T.IdEmpresa\r\n" + 
					" WHERE T.idEmpresa=TI.idEmpresa AND TI.idEmpresa=TE.idEmpresa AND TI.idEmpresa="+idEmpresa+" \r\n" + 
					" AND T.Estado = TE.IdEstado AND T.Estado = TE.IdEstado AND T.IdUsuario = "+usuario.getNumero()+"  AND T.Estado != 2 AND T.Estado != 3 AND T.Estado != 5"+
					" UNION ALL "+
					"SELECT DISTINCT T.main, T.IdTarea, T.TipoTarea, TI.Tarea, T.Porcentaje, T.Estado, TE.Descripcion, T.Observaciones, T.IdUsuario,T.IdDoc,T.Unidades,TI.Resolucion, ur.idusuario, concat(ur.nombre,' ',ur.apellido)\r\n" + 
					" FROM tareas T\r\n" + 
					" INNER JOIN  tipostarea TI ON TI.idTipo = T.TipoTarea AND TI.IdEmpresa = T.IdEmpresa\r\n" + 
					" INNER join  tareasestados TE ON TE.idEstado = T.Estado AND TE.IdEmpresa = T.IdEmpresa\r\n" + 
					" LEFT OUTER JOIN usuarios u ON u.idDeposito = t.iddeposito AND u.idEmpresa = T.IdEmpresa\r\n" + 
					" INNER JOIN usuarios ur ON ur.idUsuario = T.idresponsable AND ur.idEmpresa = T.IdEmpresa\r\n" + 
					" WHERE  TI.idEmpresa="+idEmpresa+" \r\n" + 
					" AND T.IdUsuario = 0 AND T.Estado != 2 AND T.Estado != 3 AND T.Estado != 5 AND t.iddeposito="+usuario.getDeposito();
			

					
					
					/*
					 * CONSULTA ANTERIOR:
					 * 
					 * "SELECT  DISTINCT T.main, T.IdTarea, T.TipoTarea, TI.Tarea, T.Porcentaje, T.Estado, TE.Descripcion, "
					+ "	T.Observaciones, T.IdUsuario,T.IdDoc,T.Unidades,TI.Resolucion "
					+ "	FROM `tareas` T, `tipostarea` TI, `tareasestados`  TE "
					+ "	WHERE T.TipoTarea = TI.IdTipo "
					+ " and T.idEmpresa=TI.idEmpresa and TI.idEmpresa=TE.idEmpresa and TI.idEmpresa="+idEmpresa+" "
					+ "	AND T.Estado = TE.IdEstado "
					+ "	AND T.Estado = TE.IdEstado "
					+ "	AND T.IdUsuario = "
					+ idFunc
					+ " AND T.Estado != 2 "
					+ "	AND T.Estado != 3 " + "	AND T.Estado != 5";*/

		
			return econ.darTareasMob(consulta, usuario);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	


	
	
	

	public  DataIDDescripcion encuentraDarCantEjecutoresPosicionMio(
			int main, int idUsuario,int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select `IdUsuario`, '' from `tareas` where idEmpresa="+idEmpresa+" and `main` = "
					+ main + " order by `IdTarea`";
		
 
			List<DataIDDescripcion> di = econ
					.darIdDescripcion(consulta);

			int pos = 0;
			int cantU = di.size();
			for (DataIDDescripcion d : di) {
				pos++;
				if (d.getId() == idUsuario) {
					break;
				}

			}
			DataIDDescripcion retorno = new DataIDDescripcion(cantU,
					String.valueOf(pos));

			return retorno;

		} catch (Exception e) {
			return null;
		}
	}

	public  DataIDDescripcion encuentraDarUltimoItemLinea(int main, int idEmpresa, int idUsuario) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "select `ultimoItem` ,'' from `tareas` where idEmpresa="+idEmpresa+" and `main` = "
					+ main + " and `idUsuario`=" + idUsuario;

			List<DataIDDescripcion> di = econ
					.darIdDescripcion(consulta);

			DataIDDescripcion retorno = new DataIDDescripcion();

			for (DataIDDescripcion d : di) {

				retorno.setId(d.getId());
				break;

			}

			return retorno;

		} catch (Exception e) {
			return null;
		}
	}
	
	public String query_encuentraAltaEventoTarea(int idTarea, int estado,	int idUsuario, int idMain, int idE) 
	{
		try {
			String consulta = "INSERT INTO `tareaevento` (`idTarea`, `idEstado`, `idUsuario`,idEmpresa) VALUES ("
					+ idTarea + ", " + estado + "," + idUsuario + ","+idE+");";
			String consultaII = "";
			
			if(idMain!=0)
			{
				consultaII = "UPDATE `tareas` SET `Estado`=" + estado
						+ " WHERE `main`= " + idMain+ " AND idEmpresa ="+idE+";";
			}
			else
			{
				consultaII = "UPDATE `tareas` SET `Estado`=" + estado
						+ " WHERE `idTarea`= " + idTarea+ " AND idEmpresa ="+idE+";";
			}
			

			try {
					
					return consulta+" "+consultaII;
			} catch (Exception e) {
				
			}

			return "";
		} catch (Exception e) {
			return "";
		}
	}

	public  int encuentraAltaEventoTarea(int idTarea, int estado,	int idUsuario, int idMain, int idE) 
	{
		try {
			String consulta = "INSERT INTO `tareaevento` (`idTarea`, `idEstado`, `idUsuario`,idEmpresa) VALUES ("
					+ idTarea + ", " + estado + "," + idUsuario + ","+idE+");";
			String consultaII = "";
			
			if(idMain!=0)
			{
				consultaII = "UPDATE `tareas` SET `Estado`=" + estado
						+ " WHERE `main`= " + idMain+ " AND idEmpresa ="+idE+";";
			}
			else
			{
				consultaII = "UPDATE `tareas` SET `Estado`=" + estado
						+ " WHERE `idTarea`= " + idTarea+ " AND idEmpresa ="+idE+";";
			}
			

			try {
				
				eper.persistir(consulta);

			} catch (Exception e) {
				
			}
			try {
				

				eper.persistir(consultaII);

			} catch (Exception e) {
				
			}

			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	public  int encuentraUpdateAvanceTarea(int idTarea, int porcentaje, int ultimalinea, int idEmpresa) {
		try {
			String consulta = "UPDATE `tareas` SET `Porcentaje`=" + porcentaje
					+ ", `ultimaLinea` = " + ultimalinea + " WHERE `IdTarea`="
					+ idTarea+" AND idEmpresa ="+idEmpresa+";";

			try {
				

				eper.persistir(consulta);

			} catch (Exception e) {
				
			}

			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public  String query_encuentraUpdateAvanceTarea(int idTarea, int porcentaje, int ultimalinea, int idEmpresa) {
		try {
			String consulta = "UPDATE `tareas` SET `Porcentaje`=" + porcentaje
					+ ", `ultimaLinea` = " + ultimalinea + " WHERE `IdTarea`="
					+ idTarea+" AND idEmpresa ="+idEmpresa+";";


			try {
				

				return consulta;

			} catch (Exception e) {
				
			}

			return "";
		} catch (Exception e) {
			return "";
		}
	}


	
	public  String query_encuentraUpdateOjosTienenArt(String idOjo, String articulo, int idEmpresa) {
		try {
			String consulta = "";
			String consultaII = "";
			if (!articulo.equals("")) 
			{
				consulta = "UPDATE `ojostienenarticulos` "
						+ " SET `Cantidad`=`Cantidad`-1, `Reservada` = `Reservada`-1  " + " WHERE `idOjo`='"
						+ idOjo + "' AND `idArticulo`='" + articulo
						+ "' AND idEmpresa ="+idEmpresa+" LIMIT 1;";
			}
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos` WHERE `idOjo`='"
						+ idOjo + "' AND idEmpresa ="+idEmpresa+";";
			}
			
			consultaII = " DELETE FROM `ojostienenarticulos` WHERE `Cantidad` =0 AND idEmpresa ="+idEmpresa+";";

			try {
			

				return consulta+"  "+consultaII;

			} catch (Exception e) {
				
			}

			return "";
		} catch (Exception e) {
			return "";
		}
	}
	
	
	public  int encuentraUpdateOjosTienenArt(String idOjo, String articulo, int idEmpresa, int qty) {

		try {
			String consulta = "";
			String consultaII = "";
			if (!articulo.equals("")) 
			{
				consulta = "UPDATE `ojostienenarticulos` "
						+ " SET `Cantidad`=`Cantidad`-"+qty+", `Reservada` = `Reservada`-"+qty+"  " + " WHERE `idOjo`='"
						+ idOjo + "' AND `idArticulo`='" + articulo
						+ "' AND idEmpresa ="+idEmpresa+" LIMIT 1;";
			}
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos` WHERE `idOjo`='"
						+ idOjo + "' AND idEmpresa ="+idEmpresa+";";
			}
			
			consultaII = " DELETE FROM `ojostienenarticulos` WHERE `Cantidad` =0 AND idEmpresa ="+idEmpresa+";";

			try {
			

				eper.persistir(consulta);
				eper.persistir(consultaII);

			} catch (Exception e) {
				
			}

			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	public  List<String> query_encuentraUpdateOjosTienenArt(String idOjo, String articulo, int idEmpresa, int qty) {
		List<String> retorno = new ArrayList<>();
		try {
			String consulta = "";
			String consultaII = "";
			if (!articulo.equals("")) 
			{
				consulta = "UPDATE `ojostienenarticulos` "
						+ " SET `Cantidad`=`Cantidad`-"+qty+", `Reservada` = if (`Reservada`-"+qty+" >=0 , `Reservada`-"+qty+" , 0 ) WHERE `idOjo`='"
						+ idOjo + "' AND `idArticulo`='" + articulo
						+ "' AND idEmpresa ="+idEmpresa+" LIMIT 1; ";
			}
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos` WHERE `idOjo`='"
						+ idOjo + "' AND idEmpresa ="+idEmpresa+"; ";
			}
			
			consultaII = " DELETE FROM `ojostienenarticulos` WHERE `Cantidad` =0 AND idEmpresa ="+idEmpresa+"; ";

			try {
			

				retorno.add(consulta);
				retorno.add(consultaII);

			} catch (Exception e) {
				
			}

			return retorno;
		} catch (Exception e) {
			return new ArrayList<>();

		}
	}

	public  Integer encuentraDarUltimaLinea(int idTarea, int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT `ultimaLinea`, '' FROM `tareas` WHERE idEmpresa="+idEmpresa+" and `IdTarea` = "
					+ idTarea;

			return econ.darIdDescripcion(consulta).get(0).getId();
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public  List<DataIDDescripcion> EncuentraSortearListaDI(List<DataIDDescripcion> ordenable) 
	{
		Collections.sort(ordenable);

	
		
		Hashtable<String, DataIDDescripcion> datasHT = new Hashtable<>(); 
		

		for (DataIDDescripcion d : ordenable) 
		{
			
			if(datasHT.get(d.getDescripcion())==null)
			{
				if(d.getIdB()==-1)
				{
					d.setId(d.getId()-1);
				}
				else
				{
					d.setId(d.getId()+1);
				}
				datasHT.put(d.getDescripcion(), d);
			}
			else
			{
				DataIDDescripcion in = datasHT.get(d.getDescripcion());
				if(d.getIdB()==-1)
				{
					in.setId(in.getId()-1);
				}
				else
				{
					in.setId(in.getId()+1);
				}
				datasHT.put(d.getDescripcion(), in);
			}

			//System.out.println(d.getDescripcion());
		}

		List<DataIDDescripcion> articulosCant = new ArrayList<>(datasHT.values());
		Collections.sort(articulosCant);

		return articulosCant;

	}
	
	
	
	@SuppressWarnings("unchecked")
	public  List<DataIDDescripcion> EncuentraSortearListaDICant(List<DataIDDescripcion> ordenable) 
	{
		Collections.sort(ordenable);

		
		
		Hashtable<String, DataIDDescripcion> datasHT = new Hashtable<>(); 
		

		for (DataIDDescripcion d : ordenable) 
		{
			
			if(datasHT.get(d.getDescripcion())==null)
			{
				datasHT.put(d.getDescripcion(), d);
			}
			else
			{
				DataIDDescripcion in = datasHT.get(d.getDescripcion());
				in.setId(in.getId()+d.getId());
				
				datasHT.put(d.getDescripcion(), in);
			}
			
			
		}

		List<DataIDDescripcion> articulosCant = new ArrayList<>(datasHT.values());
		Collections.sort(articulosCant);

		return articulosCant;

	}
	
	
	public List<String> encuentraUpdateCountOjos(String idOjo, String articulo,int cantidad, int usuario, int idE) 
	{
		List<String> retorno = new ArrayList<>();
		try {
			String consulta = "";
			String consultaII = "";
			String consultaIII = "";
			
			
		
			
				
			

			if (!articulo.equals("")) 
			{

				if(cantidad==0)
				{
					consultaII = "DELETE FROM ojostienenarticulos_conteo WHERE idEmpresa="+idE+" AND idOjo = '"+idOjo+"' AND idArticulo = '"+articulo+"'";
				}
				else
				{
					consultaII = "INSERT INTO `ojostienenarticulos_conteo` (`idOjo`, `idArticulo`, `Cantidad`, `usuarioUpdate`,idEmpresa) VALUES ('"
						+ idOjo
						+ "', '"
						+ articulo
						+ "', "
						+ cantidad+","+usuario
						+ ","+idE+") ON DUPLICATE KEY UPDATE `Cantidad` = `Cantidad`+"
						+ cantidad+",`usuarioUpdate`="+usuario;
					
				
					
				}

			} 
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos_conteo` WHERE idEmpresa="+idE+" AND `idOjo`='"
						+ idOjo + "'";
			}

			try 
			{
				if(!consulta.isEmpty())
				{
					retorno.add(consulta);
				}
				if(!consultaII.isEmpty())
				{
					retorno.add(consultaII);	
				}
				
				if(!consultaII.isEmpty())
				{
					retorno.add(consultaIII);	
				}
				

			} catch (Exception e) {
				
			}

			return retorno;
		} catch (Exception e) {
			return retorno;
		}
	}
	
	public List<String> query_encuentraUpdateOjos(String idOjo, String articulo,int cantidad, boolean actualiza,int usuario, boolean inventariando, String tipo,int idE) 
	{
		List<String> retorno = new ArrayList<>();
		try {
			String consulta = "";
			String consultaII = "";
			String consultaIII = "";
			
			String consultaMovStock = "";
			String consultaMovStockII = "";
			
			if (actualiza) 
			{
				consultaMovStock = "insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,IdTipo,idEmpresa) "
						+ "select idArticulo,idOjo,'',Cantidad,"+usuario+",'UOU',idEmpresa from ojostienenarticulos "
								+ "where idOjo = '"+ idOjo + "' and idEmpresa="+idE;
				consulta = "DELETE FROM `ojostienenarticulos` WHERE idEmpresa="+idE+" AND `idOjo`='"+ idOjo + "'";
				
			}

			if (!articulo.equals("")) 
			{

				if(cantidad==0)
				{
					consultaMovStock = "insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,IdTipo,idEmpresa) "
							+ "select idArticulo,idOjo,'',Cantidad,"+usuario+",'UOU',idEmpresa from ojostienenarticulos "
									+ "where idOjo = '"+idOjo+"' and idArticulo = '"+articulo+"' and idEmpresa="+idE;
					consultaII = "DELETE FROM ojostienenarticulos WHERE idEmpresa="+idE+" AND idOjo = '"+idOjo+"' AND idArticulo = '"+articulo+"'";
				}
				else
				{
					consultaII = "INSERT INTO `ojostienenarticulos` (`idOjo`, `idArticulo`, `Cantidad`, `usuarioUpdate`,idEmpresa) VALUES ('"
						+ idOjo
						+ "', '"
						+ articulo
						+ "', "
						+ cantidad+","+usuario
						+ ","+idE+") ON DUPLICATE KEY UPDATE `Cantidad` = `Cantidad`+"
						+ cantidad+",`usuarioUpdate`="+usuario;
					
					
					
					
					consultaMovStockII = "insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,IdTipo,idEmpresa)"+
										"	values ('"+articulo+"','','"+idOjo+"',"+cantidad+","+usuario+",'"+tipo+"',"+idE+")";
					
					if(!inventariando)
					{
						consultaIII = "UPDATE `ojostienenarticulos` SET `InventUpdate`='0' WHERE  `idOjo`='"+idOjo+"' AND `idArticulo`='"+articulo+"' AND idEmpresa ="+idE+";";
					}
				}

			} 
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos` WHERE idEmpresa="+idE+" AND `idOjo`='"
						+ idOjo + "'";
			}

			try {
				
				
				if(!consultaMovStock.isEmpty())
				{
					retorno.add(consultaMovStock);
				}
				if(!consultaMovStockII.isEmpty())
				{
					retorno.add(consultaMovStockII);	
				}

				if(!consulta.isEmpty())
				{
					retorno.add(consulta);
				}
				if(!consultaII.isEmpty())
				{
					retorno.add(consultaII);	
				}
				
				if(!consultaII.isEmpty())
				{
					retorno.add(consultaIII);	
				}
				

			} catch (Exception e) {
				
			}

			return retorno;
		} catch (Exception e) {
			return retorno;
		}
	}
	public  int encuentraUpdateOjos(String idOjo, String articulo,int cantidad, boolean actualiza,int usuario, boolean inventariando, String tipo,int idE) 
	{
		try {
			String consulta = "";
			String consultaII = "";
			String consultaIII = "";
			
			String consultaMovStock = "";
			String consultaMovStockII = "";
			
			if (actualiza) 
			{
				consultaMovStock = "insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,IdTipo,idEmpresa) "
						+ "select idArticulo,idOjo,'',Cantidad,"+usuario+",'UOU',idEmpresa from ojostienenarticulos "
								+ "where idOjo = '"+ idOjo + "' and idEmpresa="+idE;
				consulta = "DELETE FROM `ojostienenarticulos` WHERE idEmpresa="+idE+" AND `idOjo`='"+ idOjo + "'";
				
			}

			if (!articulo.equals("")) 
			{

				if(cantidad==0)
				{
					consultaMovStock = "insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,IdTipo,idEmpresa) "
							+ "select idArticulo,idOjo,'',Cantidad,"+usuario+",'UOU',idEmpresa from ojostienenarticulos "
									+ "where idOjo = '"+idOjo+"' and idArticulo = '"+articulo+"' and idEmpresa="+idE;
					consultaII = "DELETE FROM ojostienenarticulos WHERE idEmpresa="+idE+" AND idOjo = '"+idOjo+"' AND idArticulo = '"+articulo+"'";
				}
				else
				{
					consultaII = "INSERT INTO `ojostienenarticulos` (`idOjo`, `idArticulo`, `Cantidad`, `usuarioUpdate`,idEmpresa) VALUES ('"
						+ idOjo
						+ "', '"
						+ articulo
						+ "', "
						+ cantidad+","+usuario
						+ ","+idE+") ON DUPLICATE KEY UPDATE `Cantidad` = `Cantidad`+"
						+ cantidad+",`usuarioUpdate`="+usuario;
					
					
					
					
					consultaMovStockII = "insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,IdTipo,idEmpresa)"+
										"	values ('"+articulo+"','','"+idOjo+"',"+cantidad+","+usuario+",'"+tipo+"',"+idE+")";
					
					if(!inventariando)
					{
						consultaIII = "UPDATE `ojostienenarticulos` SET `InventUpdate`='0' WHERE  `idOjo`='"+idOjo+"' AND `idArticulo`='"+articulo+"' AND idEmpresa ="+idE+";";
					}
				}

			} 
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos` WHERE idEmpresa="+idE+" AND `idOjo`='"
						+ idOjo + "'";
			}

			try {
				
				
				if(!consultaMovStock.isEmpty())
				{
					eper.persistir(consultaMovStock);
				}
				if(!consultaMovStockII.isEmpty())
				{
					eper.persistir(consultaMovStockII);	
				}

				if(!consulta.isEmpty())
				{
					eper.persistir(consulta);
				}
				if(!consultaII.isEmpty())
				{
					eper.persistir(consultaII);	
				}
				
				if(!consultaII.isEmpty())
				{
					eper.persistir(consultaIII);	
				}
				

			} catch (Exception e) {
				
			}

			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	
	public  String query_encuentraMoverOjos(String idOjo, String articulo,int cantidad,int usuario, int idE) {
		try {
			String consulta = "";
			String consultaII = "";
			String retorno = "";

			if (!articulo.equals("")) 
			{

				if(cantidad==0)
				{
					consultaII = "DELETE FROM ojostienenarticulos WHERE idEmpresa="+idE+" AND idOjo = '"+idOjo+"' AND idArticulo = '"+articulo+"'";
				}
				else
				{
					consultaII = "INSERT INTO `ojostienenarticulos` (`idOjo`, `idArticulo`, `Cantidad`, `usuarioUpdate`,idEmpresa) VALUES ('"
						+ idOjo
						+ "', '"
						+ articulo
						+ "', "
						+ cantidad+","+usuario
						+ ","+idE+") ON DUPLICATE KEY UPDATE `Cantidad` = `Cantidad`+"
						+ cantidad+",`usuarioUpdate`="+usuario+" ,inventUpdate=0, Actualizado=CURRENT_TIMESTAMP();";
				}

			} 
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos` WHERE idEmpresa="+idE+" AND `idOjo`='"
						+ idOjo + "'";
			}

			try {
			

				if(!consulta.isEmpty())
				{
					retorno +=consulta+" ";
				}
				if(!consultaII.isEmpty())
				{
					
					retorno +=consultaII+" ";
				}
				
				

			} catch (Exception e) {
				
			}

			return retorno;
		} catch (Exception e) {
			return "";
		}
	}
	
	public  int encuentraMoverOjos(String idOjo, String articulo,int cantidad,int usuario, int idE) {
		try {
			String consulta = "";
			String consultaII = "";

			if (!articulo.equals("")) 
			{

				if(cantidad==0)
				{
					consultaII = "DELETE FROM ojostienenarticulos WHERE idEmpresa="+idE+" AND idOjo = '"+idOjo+"' AND idArticulo = '"+articulo+"'";
				}
				else
				{
					consultaII = "INSERT INTO `ojostienenarticulos` (`idOjo`, `idArticulo`, `Cantidad`, `usuarioUpdate`,idEmpresa) VALUES ('"
						+ idOjo
						+ "', '"
						+ articulo
						+ "', "
						+ cantidad+","+usuario
						+ ","+idE+") ON DUPLICATE KEY UPDATE `Cantidad` = `Cantidad`+"
						+ cantidad+",`usuarioUpdate`="+usuario+" ,inventUpdate=0, Actualizado=CURRENT_TIMESTAMP();";
				}

			} 
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos` WHERE idEmpresa="+idE+" AND `idOjo`='"
						+ idOjo + "'";
			}

			try 
			{
			
				if(!consulta.isEmpty())
				{
					eper.persistir(consulta);
				}
				if(!consultaII.isEmpty())
				{
					eper.persistir(consultaII);	
				}
							

			}
			catch (Exception e) 
			{
				
			}

			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public  List<String> querys_encuentraMoverOjos(String idOjo, String articulo,int cantidad,int usuario, int idE) {
		List<String>  retorno = new ArrayList<>();
		try {
			String consulta = "";
			String consultaII = "";

			if (!articulo.equals("")) 
			{

				if(cantidad==0)
				{
					consultaII = "DELETE FROM ojostienenarticulos WHERE idEmpresa="+idE+" AND idOjo = '"+idOjo+"' AND idArticulo = '"+articulo+"'";
				}
				else
				{
					consultaII = "INSERT INTO `ojostienenarticulos` (`idOjo`, `idArticulo`, `Cantidad`, `usuarioUpdate`,idEmpresa) VALUES ('"
						+ idOjo
						+ "', '"
						+ articulo
						+ "', "
						+ cantidad+","+usuario
						+ ","+idE+") ON DUPLICATE KEY UPDATE `Cantidad` = `Cantidad`+"
						+ cantidad+",`usuarioUpdate`="+usuario+" ,inventUpdate=0, Actualizado=CURRENT_TIMESTAMP(); ";
				}

			} 
			else 
			{
				consulta = "DELETE FROM `ojostienenarticulos` WHERE idEmpresa="+idE+" AND `idOjo`='"+ idOjo + "'; ";
			}

			try 
			{
			
				if(!consulta.isEmpty())
				{
					retorno.add(consulta);
				}
				if(!consultaII.isEmpty())
				{
					retorno.add(consultaII);	
				}
							

			}
			catch (Exception e) 
			{
				
			}

			return retorno;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	
	
	public  int encuentraBajarOjos(String idOjo, String articulo,int cantidad, int user, String tipo, int idE) 
	{
		try {
			
			int puede = 0;
			
			/*String checkStock = "select IF((cantidad-reservada-"+cantidad+")>=0, TRUE, FALSE) puede "
					+ "FROM ojostienenarticulos "
					+ "where idArticulo = '"+articulo+"' and idOjo = '"+idOjo+"' AND idEmpresa ="+idE+";";*/
			
			String checkStock = "select IF((ota.cantidad-ota.reservada-"+cantidad+")>=0 AND a.IdTipo <> 4, TRUE, " +
									" IF((ota.cantidad-ota.reservada-"+cantidad+") >= 0 AND a.IdTipo = 4 AND (SUM(bc.cantidad)-SUM(bc.cantidadReservada)) = SUM(bc.cantidad), TRUE, " +
											" FALSE)) puede \r\n" + 
					" FROM ojostienenarticulos ota\r\n" + 
					" INNER JOIN articulos a ON a.idArticulo = ota.idArticulo AND a.IdEmpresa = ota.IdEmpresa \r\n" + 
					" LEFT OUTER JOIN bulto_contenido bc ON bc.idBulto = ota.idArticulo AND bc.IdEmpresa = ota.IdEmpresa\r\n" + 
					" where ota.idArticulo = '"+articulo+"' and ota.idOjo = '"+idOjo+"' AND ota.idEmpresa ="+idE+";";
			
			String consulta = "update ojostienenarticulos set cantidad = cantidad - "+cantidad+", actualizado = CURRENT_TIMESTAMP, usuarioUpdate ="+user+" "
					+ "where idArticulo = '"+articulo+"' and idOjo = '"+idOjo+"' AND idEmpresa ="+idE+"; "+
					"delete from ojostienenarticulos where cantidad=0 and idEmpresa ="+idE+"; ";
			
			try {
				
				String consultaMovStockII = "insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,IdTipo,idEmpresa)"+
									"	values ('"+articulo+"','"+idOjo+"','',"+cantidad+","+user+",'"+tipo+"',"+idE+")";
				
				@SuppressWarnings("unused") Connection cone;
				cone = econ.getConnection();
				
				puede = econ.darInt(checkStock);
				
				if(puede==1){
					eper.persistir(consulta);
					eper.persistir(consultaMovStockII);
				}
				
			} catch (Exception e) {

			}

			return puede;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public  List<String> encuentraBajarOjosCount(String idOjo, String articulo,int cantidad, int user, String tipo, int idE) 
	{ 
		List<String> retorno = new ArrayList<>();
		
		try 
		{
			retorno.add("update ojostienenarticulos_conteo set cantidad = cantidad - "+cantidad+", actualizado = CURRENT_TIMESTAMP, usuarioUpdate ="+user+" "
					+ "where idArticulo = '"+articulo+"' and idOjo = '"+idOjo+"' AND idEmpresa ="+idE+"; "+
					"delete from ojostienenarticulos where cantidad=0 and idEmpresa ="+idE+"; ");
			
		} 
		catch (Exception e) 
		{
			return retorno;
		}
		return retorno;
	}
	
	public  void encuentraUpdateAvanceInvent(String fecha, String deposito, int idEmpresa) 
	{
		try {
			String consulta = ""
					+ "UPDATE invent set conteo = "
					+ "("
					+ "  SELECT SUM(OT.Cantidad) "
					+ "   FROM `ojostienenarticulos` OT  "
					+ "   WHERE OT.Actualizado>= '"+fecha+"' AND idEmpresa ="+idEmpresa
					+ " ), PorcentajeOjos = "
					+ " ( "
					+ "   ("
					+ "     ("
					+ "       SELECT SUM(T0.cantidad) "
					+ "       FROM "
					+ "       ("
					+ "         SELECT 1 as cantidad "
					+ "         FROM `ojostienenarticulos` OT "
					+ "         WHERE OT.idEmpresa="+idEmpresa+" "
					+ "         AND OT.Actualizado>="
					+ "         ("
					+ "            SELECT fecha FROM invent WHERE activo = 1 AND idDeposito = '"+deposito+"' AND idEmpresa ="+idEmpresa+" "
					+ "          ) "
					+ "         GROUP BY OT.idOjo "
					+ "       ) "
					+ "       t0"
					+ "     ) "
					+ "     *100)"
					+ "     /"
					+ "    ("
					+ "       SELECT SUM(T0.cantidad) FROM "
					+ "        (SELECT 1 as cantidad FROM `ojos` OT where idEmpresa="+idEmpresa+" AND idOjo NOT LIKE ('%E') AND idOjo NOT LIKE ('%P') AND idOjo NOT LIKE ('SIN ASIGNAR') AND idOjo NOT IN('0','1') "
					+ "			GROUP BY OT.idOjo) "
					+ "    t0"
					+ "    )"
					+ "  ) WHERE idDeposito = "+ " '"+deposito+"' AND idEmpresa="+idEmpresa;
			
			try {
				

				eper.persistir(consulta);
			} catch (Exception e) {
				
			}

			
		} catch (Exception e) {
			
		}
	}
	
	public  void encuentraUpdateOjoInvent(String idOjo, int idEmpresa) 
	{
		try {
			String consulta = "UPDATE `ojostienenarticulos` SET `InventUpdate`='1' WHERE idEmpresa="+idEmpresa+" AND `idOjo`='"+idOjo+"'  ";
			
			try {
				eper.persistir(consulta);
			} catch (Exception e) {
				
			}
			
		} catch (Exception e) {
			
		}
	}
	
	public  int encuentraBajarOjosXArt(String idOjo, String articulo,int cantidad, int idEmpresa) 
	{
		try {
			String consulta = "delete from ojostienenarticulos where idEmpresa="+idEmpresa+" AND idArticulo = '"+articulo+"' and idOjo = '"+idOjo+"' and Cantidad="+cantidad;
			
			try {
				eper.persistir(consulta);
			} catch (Exception e) {
				
			}

			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public  List<Tarea> encuentraDarTareas(int idEstado, int idSub,
			int idTarea, int idEmpresa) {

		try {

			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			String consulta = "";

			if (idEstado == 99)// todos los estados
			{
				consulta = "SELECT `IdTarea`, `tipostarea`.Tarea ,`FechaInicio`,`IdResponsable`, "
						+ "	`Porcentaje`,`Estado`, `tareasestados`.Descripcion ,`Observaciones`,`FechaFin`,`Unidades`, "
						+ "	`Nota`,`IdDoc`,`tareas`.`IdUsuario`,`usuarios`.Nombre as 'nom' ,`usuarios`.Apellido as 'ape' ,`main`,`ultimaLinea`, SU.Nickname as 'super' "
						+ "	FROM `tareas`,`tipostarea` ,`tareasestados`, `usuarios`, `usuarios` SU "
						+ "	where  `tipostarea`.IdTipo = `tareas`.`TipoTarea` "
						+ " and tareas.idEmpresa=tipostarea.idEmpresa and tipostarea.idEmpresa=tareasestados.idEmpresa and "
						+ " tareasestados.idEmpresa = usuarios.idEmpresa and usuarios.idEmpresa = SU.idEmpresa and SU.idEmpresa="+idEmpresa
						+ "	and `tareasestados`.IdEstado = `tareas`.Estado "
						+ "	and `usuarios`.idUsuario = `tareas`.IdUsuario "
						+ "	and SU.idUsuario = `tareas`.`IdResponsable` ";

			} else if (idEstado == -5)// todas las sin terminar
			{

				consulta = "SELECT `IdTarea`, `tipostarea`.Tarea ,`FechaInicio`,`IdResponsable`, "
						+ "	`Porcentaje`,`Estado`, `tareasestados`.Descripcion ,`Observaciones`,`FechaFin`,`Unidades`, "
						+ "	`Nota`,`IdDoc`,`tareas`.`IdUsuario`,`usuarios`.Nombre as 'nom' ,`usuarios`.Apellido as 'ape' ,`main`,`ultimaLinea`, SU.Nickname as 'super' "
						+ "	FROM `tareas`,`tipostarea` ,`tareasestados`, `usuarios`, `usuarios` SU "
						+ "	where  `tipostarea`.IdTipo = `tareas`.`TipoTarea` "
						+ " and tareas.idEmpresa=tipostarea.idEmpresa and tipostarea.idEmpresa=tareasestados.idEmpresa and "
						+ " tareasestados.idEmpresa = usuarios.idEmpresa and usuarios.idEmpresa = SU.idEmpresa and SU.idEmpresa="+idEmpresa
						+ "	and `tareasestados`.IdEstado = `tareas`.Estado "
						+ "	and `usuarios`.idUsuario = `tareas`.IdUsuario "
						+ "	and SU.idUsuario = `tareas`.`IdResponsable` "
						+ "	and (`tareas`.Estado  = 0 OR `tareas`.Estado  = 1 OR `tareas`.Estado  = 4)";

			} else if (idEstado == 2)// tareas terminadas sin aprobar
			{
				consulta = "SELECT `IdTarea`, `tipostarea`.Tarea ,`FechaInicio`,`IdResponsable`, "
						+ "	`Porcentaje`,`Estado`, `tareasestados`.Descripcion ,`Observaciones`,`FechaFin`,`Unidades`, "
						+ "	`Nota`,`IdDoc`,`tareas`.`IdUsuario`,`usuarios`.Nombre as 'nom' ,`usuarios`.Apellido as 'ape' ,`main`,`ultimaLinea`, SU.Nickname as 'super' "
						+ "	FROM `tareas`,`tipostarea` ,`tareasestados`, `usuarios`, `usuarios` SU "
						+ "	where  `tipostarea`.IdTipo = `tareas`.`TipoTarea` "
						+ " and tareas.idEmpresa=tipostarea.idEmpresa and tipostarea.idEmpresa=tareasestados.idEmpresa and "
						+ " tareasestados.idEmpresa = usuarios.idEmpresa and usuarios.idEmpresa = SU.idEmpresa and SU.idEmpresa="+idEmpresa
						+ "	and `tareasestados`.IdEstado = `tareas`.Estado "
						+ "	and `usuarios`.idUsuario = `tareas`.IdUsuario "
						+ "	and SU.idUsuario = `tareas`.`IdResponsable` "
						+ "	and `tareas`.Estado  = 2";
			}

			return econ.darTareas(consulta);
		} catch (Exception e) {
			return null;
		}
	}

	public  List<Ojo> encuentraEtiqueta(String idOjo, int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			String consulta = "SELECT `IdOjo`, o.IdEstanteria, `idEstante` AS 'MODULO', `idModulo` AS 'ESTANTE', IF(e.idUso IN (4,5), d.alias,e.descripcion) "
					+ "FROM OJOS o,estanterias e "
					+ "LEFT OUTER JOIN depositos d ON e.idEstanteria=d.estanteriaPicking OR e.idEstanteria=d.estanteriaExpedicion "
					+ " WHERE o.idEmpresa=e.idEmpresa and e.idEmpresa="+idEmpresa+" and o.idestanteria = e.idestanteria and `IdOjo` = '"
					+ idOjo + "'";

			return econ.darOjo(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public  List<DataOjoArticulo> encuentraArticulosBulto(int idEmpresa, String idBulto, boolean stocks, int deposito) 
	{
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			String qry = "SELECT BC.idArticulo, B.descripcion, OJ.idModulo, OJ.idEstante, B.idBulto, ES.idEstanteria, BC.cantidad, OT.Actualizado, 0 AS 'alias',BC.cantidadReservada AS 'reservada', 4, '' "+
							" FROM bulto B INNER JOIN bulto_contenido BC ON B.idBulto = BC.idBulto AND B.IdEmpresa = BC.IdEmpresa "+
							" LEFT OUTER JOIN ojostienenarticulos OT ON OT.idArticulo = B.idBulto AND OT.IdEmpresa = B.IdEmpresa "+
							" LEFT OUTER JOIN ojos OJ ON OJ.idOjo = OT.idOjo AND OJ.IdEmpresa = OT.IdEmpresa "+
							" LEFT OUTER JOIN estanterias ES ON ES.idEstanteria = OJ.idEstanteria AND ES.idEmpresa = OJ.IdEmpresa "+ 
							" WHERE B.IdEmpresa = "+idEmpresa+" AND B.idBulto = '"+idBulto+"'";
			
			return econ.darArticulosOjos(qry, stocks, deposito, idEmpresa);
			
		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();
		}

	}
	
	public boolean BultoMovible(String idOjo, String articulo, String subarticulo, int cant_in_bulto,  int idEmpresa) {
		boolean movible = false;
		try {
			String consulta = "";
			if(idOjo.equals(articulo)){
				consulta = "select IF(SUM(bc.cantidad) - SUM(bc.cantidadReservada) >= "+cant_in_bulto+", TRUE, FALSE ) from bulto_contenido bc " + 
						" where bc.idArticulo = '"+subarticulo+"' and bc.idbulto='"+articulo+"' AND bc.idEmpresa = "+idEmpresa+";";
			}
			else{
				consulta = "select IF(a.idTipo != 4, TRUE, IF(SUM(bc.cantidadReservada) > 0, FALSE, TRUE)) puede \r\n" + 
						" FROM ojostienenarticulos ota\r\n" + 
						" INNER JOIN articulos a ON a.idArticulo = ota.idArticulo AND a.IdEmpresa = ota.IdEmpresa \r\n" + 
						" LEFT OUTER JOIN bulto_contenido bc ON bc.idBulto = ota.idArticulo AND bc.IdEmpresa = ota.IdEmpresa\r\n" + 
						" where ota.idArticulo = '"+articulo+"' and ota.idOjo = '"+idOjo+"' AND ota.idEmpresa = "+idEmpresa+";";
			}
			 
			
			System.out.println(consulta);
			_EncuentraPersistir ep = new _EncuentraPersistir();
			movible = ep.esArticuloOBultoMovible(consulta);		
			
			return movible;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}
	
	public boolean ArticuloEnBultoMovible(String bulto, String articulo, int idEmpresa, int cant_in_bulto) {
		boolean movible = false;
		try {
			
			String consulta = "select IF(SUM(bc.cantidad) - SUM(bc.cantidadReservada) >= "+cant_in_bulto+", TRUE, FALSE ) from bulto_contenido " + 
					" where bc.idArticulo = '"+articulo+"' and bc.idbulot='"+bulto+"' AND ota.idEmpresa = "+idEmpresa+";";
			
			System.out.println(consulta);
			_EncuentraPersistir ep = new _EncuentraPersistir();
			movible = ep.esArticuloOBultoMovible(consulta);
			
			
			return movible;

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}
	
	public  List<DataOjoArticulo> encuentraDarOjosArticulos(String idOjo,String estanteria, String categoria, String tipoUso, String articulo,	boolean stock, boolean desconsolidarBulto, int idEmpresa , int deposito, String SubCategoria) 
	{
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			
			
			String pie = " ORDER BY IdEstanteria, idModulo, idEstante, idOjo";
			String consulta = "";
			String consultaUA = "";
			String consultaEstanteria = "";
			String consultaCategoria = "";
			String consultaUsos = "";
			String consultaArticulo = "";
			String consultaArticuloB = "";
			String consultaOjo = "";

			if (!desconsolidarBulto) {
				consulta = "SELECT OT.idArticulo, OS.Descripcion, OJ.idModulo , OJ.idEstante,OT.idOjo,  OJ.IdEstanteria, OT.Cantidad, \r\n" + 
						"OT.Actualizado,if(OJ.alias='',if(OS.TipoSector=999,CONCAT(OT.Alias,' (',OT.idOjo,')'),OT.idOjo),OJ.alias), OT.Reservada, \r\n" + 
						"a.idTipo,'' bulto\r\n" + 
						"FROM `ojos` OJ \r\n" + 
						"INNER JOIN `ojostienenarticulos` OT on OJ.idOjo=OT.idOjo AND OT.IdEmpresa=OJ.IdEmpresa\r\n" + 
						"inner join articulos a on a.idarticulo=ot.idarticulo AND OT.IdEmpresa=a.IdEmpresa \r\n" +
						"INNER JOIN `estanterias` OS on OJ.idEstanteria=OS.idEstanteria AND OJ.IdEmpresa=OS.IdEmpresa \r\n" + 
						"INNER JOIN `depositos` DE on DE.idDeposito=OS.idDeposito AND DE.IdEmpresa=OS.IdEmpresa \r\n" + 
						"WHERE OT.idEmpresa="+idEmpresa+" and DE.idDeposito="+deposito+" ";
				
				consultaUA = "";
								
			}
			else // son la misma...
			{
				consulta = "SELECT OT.idArticulo, OS.Descripcion, OJ.idModulo , OJ.idEstante,OT.idOjo,  OJ.IdEstanteria, OT.Cantidad, \r\n" + 
						"OT.Actualizado,if(OS.TipoSector=999,CONCAT(OT.Alias,' (',OT.idOjo,')'),OT.idOjo), OT.Reservada, \r\n" + 
						"a.idTipo,'' bulto,a.descripcion\r\n" + 
						"FROM `ojos` OJ \r\n" + 
						"INNER JOIN `ojostienenarticulos` OT on OJ.idOjo=OT.idOjo AND OT.IdEmpresa=OJ.IdEmpresa\r\n" + 
						"inner join articulos a on a.idarticulo=ot.idarticulo AND OT.IdEmpresa=a.IdEmpresa \r\n" +
						"INNER JOIN `estanterias` OS on OJ.idEstanteria=OS.idEstanteria AND OJ.IdEmpresa=OS.IdEmpresa \r\n" + 
						"INNER JOIN `depositos` DE on DE.idDeposito=OS.idDeposito AND DE.IdEmpresa=OS.IdEmpresa \r\n" + 
						"WHERE OT.idEmpresa="+idEmpresa+" and DE.idDeposito="+deposito+" ";
				
				consultaUA = "SELECT BC.idArticulo, OS.Descripcion, OJ.idModulo , OJ.idEstante,OT.idOjo,  OJ.IdEstanteria, BC.cantidad, \r\n" + 
						"OT.Actualizado,if(OS.TipoSector=999,CONCAT(OT.Alias,' (',OT.idOjo,')'),OT.idOjo), OT.Reservada, \r\n" + 
						"a.idTipo,BU.idBulto bulto,a.descripcion\r\n" + 
						"FROM `ojos` OJ \r\n" + 
						"INNER JOIN `ojostienenarticulos` OT on OJ.idOjo=OT.idOjo AND OT.IdEmpresa=OJ.IdEmpresa\r\n" + 
						"INNER JOIN `estanterias` OS on OJ.idEstanteria=OS.idEstanteria AND OJ.IdEmpresa=OS.IdEmpresa \r\n" + 
						"INNER JOIN `depositos` DE on DE.idDeposito=OS.idDeposito AND DE.IdEmpresa=OS.IdEmpresa \r\n" + 
						"inner join bulto BU ON BU.idBulto = OT.idArticulo \r\n" + 
						"INNER JOIN bulto_contenido BC ON BC.idBulto=BU.idBulto AND BC.IdEmpresa = BU.IdEmpresa\r\n" + 
						"inner join articulos a on a.idarticulo=bc.idarticulo AND bc.IdEmpresa=a.IdEmpresa \r\n" +
						"WHERE OT.idEmpresa="+idEmpresa+" and DE.idDeposito="+deposito+" ";
			}
			
			if (idOjo != null && !idOjo.equals("")) {
				consultaOjo = " AND OT.idOjo = '"+ idOjo+ "' ";
			}
			if (estanteria == null) {

			} else if (!estanteria.equals("") && !estanteria.equals("0")) {
				consultaEstanteria = " AND OJ.IdEstanteria IN ("+estanteria+")";
			}
			
			/*
			if (categoria == null) {

			} else if (!categoria.equals("")) {
				consultaCategoria = " AND a.IdCategoria IN ("+categoria+")";
			}
			*/
			
			if(categoria != null && !categoria.equals("") && !categoria.contains("0,") && SubCategoria != null && !SubCategoria.equals("") && !categoria.contains("0,")) {
				consultaCategoria = "AND (a.IdFamilia IN ("+categoria+") AND a.IdSubfamilia IN ("+SubCategoria+"))";
			}else if(categoria != null && !categoria.equals("") && !categoria.contains("0,")) {
				consultaCategoria = " AND a.IdFamilia IN ("+categoria+")";
			}else if(SubCategoria != null && !SubCategoria.equals("") && !SubCategoria.contains("0,")) {
				consultaCategoria = " AND a.IdSubfamilia IN ("+SubCategoria+")";
			}
			
			if (tipoUso == null) {

			} else if (!tipoUso.equals("")) {
				consultaUsos = " AND OS.idUso in ("+ tipoUso+") ";
			}
			/*else{
				consultaUsos = " AND OS.idUso in (1) ";
			}*/
			
			if (articulo == null) {

			} else if (!articulo.equals("")) 
			{
				consultaArticulo = " AND OT.idArticulo LIKE '%"+ articulo+"%' ";
				consultaArticuloB= " AND MATCH (BC.idArticulo) AGAINST ('"+ articulo+"' IN BOOLEAN MODE) ";
			}
			
			String qry = "";
			if (!consultaUA.equals("")){
				System.out.println(consulta+consultaOjo+ consultaEstanteria+ consultaCategoria +consultaUsos + consultaArticulo + " UNION ALL "+consultaUA+consultaOjo+ consultaEstanteria +consultaCategoria +consultaUsos + consultaArticuloB+ pie);
				qry = consulta+consultaOjo+ consultaEstanteria + consultaCategoria +consultaUsos + consultaArticulo + " UNION ALL "+consultaUA+consultaOjo+ consultaEstanteria +consultaCategoria + consultaUsos + consultaArticuloB+ pie;
			}
			else{
				System.out.println(consulta+consultaOjo+ consultaEstanteria + consultaCategoria + consultaUsos + consultaArticulo + pie);
				qry = consulta+consultaOjo+ consultaEstanteria + consultaCategoria + consultaUsos + consultaArticulo + pie;
			}
			
			
			List<DataOjoArticulo> ojos = econ.darArticulosOjos(qry, stock, deposito, idEmpresa);			
			
			return ojos;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

	}

	/*public  List<DataOjoArticulo> encuentraDarOjosArticulosExacto(String idOjo,	String estanteria, String estante, 
			String articulo, int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			String pie = " ORDER BY OJ.IdEstanteria, OJ.idModulo, OJ.idEstante, OT.idOjo";
			String consulta = "";
			String consultaEstanteria = "";
			String consultaEstante = "";
			String consultaArticulo = "";

			if (idOjo == null || idOjo.equals("")) {
				consulta = "SELECT OT.idArticulo,'', OJ.idEstante,OJ.idModulo, OT.idOjo, OJ.IdEstanteria, OT.Cantidad, OT.Actualizado,'',0,1 "
						+ " FROM `ojos` OJ, `ojostienenarticulos` OT "
						+ " WHERE OJ.idEmpresa=OT.idEmpresa and OJ.IdOjo = OT.idOjo and OT.idEmpresa="+idEmpresa+" ";
			} else {
				consulta = "SELECT OT.idArticulo,'', OJ.idEstante,OJ.idModulo, OT.idOjo, OJ.IdEstanteria, OT.Cantidad, OT.Actualizado,'',0,1 "
						+ " FROM `ojos` OJ, `ojostienenarticulos` OT "
						+ " WHERE OJ.idEmpresa=OT.idEmpresa and OJ.IdOjo = OT.idOjo and OT.idEmpresa="+idEmpresa+" AND OT.idOjo = '"
						+ idOjo
						+ "'";
			}

			if (estanteria == null) {

			} else if (!estanteria.equals("")) {
				consultaEstanteria = " AND OJ.IdEstanteria = " + estanteria;
			}

			if (estante == null) {

			} else if (!estante.equals("")) {
				consultaEstante = " AND OJ.idEstante = " + estante;
			}
			if (articulo == null) {

			} else if (!articulo.equals("")) 
			{
				consultaArticulo = " AND OT.idArticulo = '" + articulo+"' ";
			}

			System.out.println(consulta + consultaEstanteria + consultaEstante + consultaArticulo
					+ pie);

			return econ.darArticulosOjos(consulta+ consultaEstanteria + consultaEstante + consultaArticulo + pie, false, 0);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}*/
	
	public  String query_encuentraBajaArticulosOjos(int id, String descripcion, String idOjo, int idEmpresa) 
	{

		try {

			

			String consulta = "UPDATE `ojostienenarticulos` SET `Cantidad`= `Cantidad`-"
					+ id
					+ " WHERE idEmpresa="+idEmpresa+" AND `idOjo`='"
					+ idOjo
					+ "' AND `idArticulo`='"
					+ descripcion + "';";

			String consultaII = " delete from `ojostienenarticulos` where idEmpresa="+idEmpresa+" AND `Cantidad` = 0;";
			return consulta+consultaII;

		} catch (Exception e) 
		{
			return "";
		}

	}


	

	public  void encuentraBajaArticulosOjos(int id, String descripcion, String idOjo, int idEmpresa) 
	{

		try {

			

			String consulta = "UPDATE `ojostienenarticulos` SET `Cantidad`= `Cantidad`-"
					+ id
					+ " WHERE idEmpresa="+idEmpresa+" AND `idOjo`='"
					+ idOjo
					+ "' AND `idArticulo`='"
					+ descripcion + "'";

			String consultaII = "delete from `ojostienenarticulos` where idEmpresa="+idEmpresa+" AND `Cantidad` = 0";
			eper.persistir(consulta);
			eper.persistir(consultaII);

		} catch (Exception e) {

		}

	}

	

	public  String query_encuentraUpdateAvanceLineaTarea(int idTarea, int unidad,int idmain, int idEmpresa) {
		try {
			String consulta = "";
			
			
			if(idmain==0)
			{
				consulta = "UPDATE `tareas` SET `ultimoItem`=" + unidad
						+ " WHERE idEmpresa="+idEmpresa+" AND `IdTarea`=" + idTarea 
						+ " LIMIT 1; ";
			}
			else
			{
				consulta = "UPDATE `tareas` SET `ultimoItem`=" + unidad
						+ " WHERE idEmpresa="+idEmpresa+" AND `IdTarea`=" + idTarea + " AND `main`=" + idmain
						+ " LIMIT 1; ";	
			}
			
			
			

			try {
			

				return consulta;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public  void encuentraUpdateAvanceLineaTarea(int idTarea, int unidad,int idmain, int idEmpresa) {
		try {
			String consulta = "UPDATE `tareas` SET `ultimoItem`=" + unidad
					+ " WHERE idEmpresa="+idEmpresa+" AND `IdTarea`=" + idTarea + " AND `main`=" + idmain
					+ " LIMIT 1;";

			try {
			

				eper.persistir(consulta);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String query_encuentraUpdateAvancePicking(int idPicking, String articulo, int cantidad, int idOr, int idDest, boolean verificacion,Long pedido, int doc, int idEmpresa,int depositoEcommerce, int autoVerificacion)
	{
		
		try {
			String verif = "";
			if(verificacion){
				verif += ", verif = verif+"+cantidad+", verif_at=CURRENT_TIMESTAMP() ";
			}
			String pedidoEC="";
			if(idDest==depositoEcommerce){
				pedidoEC=" and seccion="+pedido+" ";
			}
			String remit = "";
			if(autoVerificacion==1) {
				remit = ", Remitidas = Remitidas+"+cantidad+", remitida_at = CURRENT_TIMESTAMP() ";
			}
			
			String consulta = "UPDATE `reposicion_articulos` SET `Picked`=`Picked`+"+cantidad+" , picked_at=CURRENT_TIMESTAMP() "+verif+remit+" WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+idPicking+" AND `idArticulo`='"+articulo+"' AND `Origen`="+idOr+" AND `Destino`="+idDest+" and idSolicitudTraslado="+doc+ pedidoEC+"; ";

			try {
				

				return consulta;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
	
	public boolean encuentraUpdateAvancePicking(int idPicking, String articulo, int cantidad, int idOr, int idDest, boolean verificacion,Long pedido, int doc, int idEmpresa,int depositoEcommerce, int autoVerificacion)
	{
		boolean salida = false;
		try {
			String verif = "";
			if(verificacion){
				verif += ", verif = verif+"+cantidad+", verif_at=CURRENT_TIMESTAMP() ";
			}
			String pedidoEC="";
			if(idDest==depositoEcommerce){
				pedidoEC=" and seccion="+pedido+" ";
			}
			String remit = "";
			if(autoVerificacion==1) {
				remit = ", Remitidas = Remitidas+"+cantidad+", remitida_at = CURRENT_TIMESTAMP() ";
			}
			
			String consulta = "UPDATE `reposicion_articulos` SET `Picked`=`Picked`+"+cantidad+" , picked_at=CURRENT_TIMESTAMP() "+verif+remit+" WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+idPicking+" AND `idArticulo`='"+articulo+"' AND `Origen`="+idOr+" AND `Destino`="+idDest+" and idSolicitudTraslado="+doc+ pedidoEC+";";

			try {
				

				salida = eper.persistir(consulta);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;

	}

	public  List<DataIDDescripcion> darEstanterias(int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		String consulta = "SELECT `idEstanteria`, `Descripcion`  FROM `estanterias` WHERE idEmpresa="+idEmpresa+"";

		return econ.darListaDataIdDescripcion(consulta);
	}
	public  List<DataIDDescripcion> darEstanterias(int idEmpresa, int idDeposito) 
	{
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		String consulta = "SELECT `idEstanteria`, `Descripcion`  FROM `estanterias` WHERE idDeposito="+idDeposito+" AND idEmpresa="+idEmpresa+"";

		return econ.darListaDataIdDescripcion(consulta);
	}

	public  void encuentraBajaTarea(int idTarea, int idEmpresa) {
		String consulta0 = "delete from `noencontrados` where idEmpresa="+idEmpresa+" AND `idRepo` = "
				+ idTarea;
		String consulta1 = "delete from `tareaevento` where idEmpresa="+idEmpresa+" AND `idTarea` = "
				+ idTarea;
		String consulta2 = "delete from `tareas` where idEmpresa="+idEmpresa+" AND `IdTarea` = " + idTarea;

		try {
			

			eper.persistir(consulta0);
			eper.persistir(consulta1);
			eper.persistir(consulta2);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	

	public  boolean encuentraAltaUsuario(Usuario u) {
		String consulta = "INSERT INTO `usuarios` (`idUsuario`, `Contrase?a`, `Nickname`, `Nombre`, `Apellido`, `Perfil`, `idDeposito`,idEmpresa) "
				+ " VALUES ("
				+ u.getNumero()
				+ ", '"
				+ u.getPass()
				+ "', '"
				+ u.getNick()
				+ "', '"
				+ u.getNombre()
				+ "', '"
				+ u.getApellido() 
				+ "', " 
				+ u.getPerfil() 
				+ ", " 
				+ u.getDeposito()
				+ ", " 
				+ u.getIdEmpresa()
				+ ") ON DUPLICATE KEY UPDATE `Contrase?a`='"+ u.getPass()
				+ "', `Nickname`='"+ u.getNick()+ "', `Nombre`='"+ u.getNombre()+ "', `Apellido`='"+ u.getApellido()+ "', `Perfil`='"+ u.getPerfil()
				+ "', `idDeposito`="+ u.getDeposito()+ ";";
		try {
			

			eper.persistir(consulta);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	

	public  void encuentraAltaNoncontrados(int idTarea, DataLineaRepo d,
			int encontrado, int linea, int idE) {
		String consulta = "INSERT INTO `noencontrados` (`idRepo`, `articulo`, `cantidad`, `Ubicacion`, `encontrado`, `idLinea`,idEmpresa) "
				+ " VALUES ("
				+ idTarea
				+ ", '"
				+ d.getIdArticulo()
				+ "', "
				+ d.getSolicitada()
				+ ", '"
				+ d.getCubi()
				+ "',"
				+ encontrado
				+ "," + linea + ","+idE+") ON DUPLICATE KEY UPDATE `cantidad` = `cantidad`+1;";

		String consultaUp = "UPDATE `tareaevento` SET `TimeStamp` = CURRENT_TIMESTAMP() WHERE idEmpresa="+idE+" AND `idTarea` =  "
				+ idTarea + " AND `idEstado` = 2";

		try {
		
			eper.persistir(consulta);
			eper.persistir(consultaUp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public  String  query_encuentraAltaNoncontrados(int idTarea, DataLineaRepo d, int encontrado, int linea, int idE) {
		String consulta = "INSERT INTO `noencontrados` (`idRepo`, `articulo`, `cantidad`, `Ubicacion`, `encontrado`, `idLinea`,idEmpresa) "
				+ " VALUES ("
				+ idTarea
				+ ", '"
				+ d.getIdArticulo()
				+ "', "
				+ d.getSolicitada()
				+ ", '"
				+ d.getCubi()
				+ "',"
				+ encontrado
				+ "," + linea + ","+idE+") ON DUPLICATE KEY UPDATE `cantidad` = `cantidad`+1;";

		String consultaUp = "UPDATE `tareaevento` SET `TimeStamp` = CURRENT_TIMESTAMP() WHERE idEmpresa="+idE+" AND `idTarea` =  "
				+ idTarea + " AND `idEstado` = 2";

		try {
		
		
			
			return consulta+"  "+consultaUp;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	


	public  List<DataIDDescripcion> darListaDataIdDescripcion(String tabla, int idEmpresa) {

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			
			String consulta="";
			
			if(tabla.equals("seg_grupos")){
				consulta = "select * from " + tabla+" where idEmpresa="+idEmpresa+" and idGrupo not in(105,100)";	
			}
			else{				
				consulta = "select * from " + tabla+" where idEmpresa="+idEmpresa;
			}
			
			if(tabla.equals("vista_rep_art_distribucion")) {
				consulta = "select * from " + tabla+" where idEmpresa="+idEmpresa+" ORDER BY idSolicitudTraslado";
			}
			
			System.out.println(consulta);
			return econ.darListaDataIdDescripcion(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	public  List<DataIDDescripcion> darListaDataIdDescripcionMYSQLConsulta(String consulta) {

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			return econ.darListaDataIdDescripcion(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	
	public  List<DataEcommerce_canales_envio> darListaEcommerce_canales_envio(int canal, int idEmpresa) {

		@SuppressWarnings("unused") Connection cone;
		try {
			String consulta = "select tipoEnvio,codigo,courier,iddeposito,Usuario,Pass,EC.nombre,CE.idTienda, "
					+ "(SELECT if(CURTIME()>'13:00:00',DATE_ADD(CURDATE(),INTERVAL 1 DAY),CURDATE())) "
					+ " from ecommerce_canales_envio CE\r\n" + 
					"inner join ecommerce_envioml EM on EM.idEmpresa=CE.idEmpresa AND nombre=courier\r\n" + 
					"INNER JOIN ecommerce_canal_ml EC ON EC.id = CE.idCanal AND EC.IdEmpresa=CE.IdEmpresa\r\n" + 
					"where CE.idcanal="+canal+" AND CE.IdEmpresa="+idEmpresa;
			
			cone = econ.getConnection();
			return econ.darListaDataEcommerce_canales_envio(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	public  DataEcommerce_canales_envio darListaEcommerce_canales_envioPY(int canal, int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			String consulta = "select tipoEnvio,codigo,courier,0,Usuario,Pass,\"\",0, \r\n" + 
					"(SELECT if(CURTIME()>'13:00:00',DATE_ADD(CURDATE(),INTERVAL 1 DAY),CURDATE())) \r\n" + 
					"from ecommerce_canales_envio CE\r\n" + 
					"inner join ecommerce_envioml EM on EM.idEmpresa=CE.idEmpresa AND nombre=courier\r\n" + 
					"INNER JOIN ecommerce_canal_ml EC ON EC.id = CE.idCanal AND EC.IdEmpresa=CE.IdEmpresa\r\n" + 
					"where CE.idcanal="+canal+" AND CE.IdEmpresa="+idEmpresa+" AND courier='PEDIDOS_YA';";
			cone = econ.getConnection();
			return econ.darListaDataEcommerce_canales_envio(consulta).get(0);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public  DataEcommerce_canales_envio darEcommerce_canal_envio(int canal, int idEmpresa, int destino) {

		@SuppressWarnings("unused") Connection cone;
		try {
			String consulta = "select tipoEnvio,codigo,courier,iddeposito,Usuario,Pass,EC.nombre,CE.idTienda, "
					+ "(SELECT if(CURTIME()>'13:00:00',DATE_ADD(CURDATE(),INTERVAL 1 DAY),CURDATE())) "
					+ " from ecommerce_canales_envio CE\r\n" + 
					"inner join ecommerce_envioml EM on EM.idEmpresa=CE.idEmpresa AND nombre=courier\r\n" + 
					"INNER JOIN ecommerce_canal_ml EC ON EC.id = CE.idCanal AND EC.IdEmpresa=CE.IdEmpresa\r\n" + 
					"where CE.idcanal="+canal+" AND CE.IdEmpresa="+idEmpresa+" AND idDeposito="+destino+";";
			
			cone = econ.getConnection();
			return econ.darDataEcommerce_canal_envio(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	private  List<DataIDIDDescripcion> darListaDataIdIdDescripcionMYSQLConsulta(String consulta) {

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			return econ.darListaDataIdIdDescripcion(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	

	
	public  List<DataIDIDDescripcion> darListaPinckConfirmarTodo(boolean picking, int idEmpresa) 
	{
		String consulta = "";
				
		if(picking)
		{
			consulta="SELECT sum(RA.Cantidad) , RA.idPicking,CONCAT(DE.Nombre,' ' ,DE.Direccion) FROM reposicion_articulos RA  "
					+ "inner join Depositos DE on DE.idDeposito = RA.Destino and RA.idEmpresa=DE.idEmpresa "
					+ "where RA.idEmpresa="+idEmpresa+" and RA.Mayorista = 1 and RA.idPicking!=0 "
					+ "group by RA.Destino, RA.idPicking having sum(RA.Picked)=0 ";
			
		}
		else
		{
			consulta="SELECT sum(RA.Cantidad) , RA.idPicking,CONCAT(DE.Nombre,' ' ,DE.Direccion) FROM reposicion_articulos RA  "
					+ "inner join Depositos DE on DE.idDeposito = RA.Destino and RA.idEmpresa=DE.idEmpresa "
					+ "where RA.idEmpresa="+idEmpresa+" and RA.Mayorista = 1 and RA.idPicking!=0 "
					+ "group by RA.Destino, RA.idPicking having sum(RA.Picked)!=0 AND sum(RA.Verif)=0 ";
		}

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			return econ.darListaDataIdIdDescripcion(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	public  List<DataIDDescripcion> darListaDataIdDescripcionCancelaAR(String consulta) {

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			return econ.darListaDataIdDescripcionAR(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	private  List<DataIDDescripcion> darListaDataIdDescripcionConsul(String consulta, int idEmpresa) 
	{
		return MSSQL.darIDDescripcion(consulta,idEmpresa);
	}
	
	

	public  List<DataIDDescripcion> darListaDepositos(int tipo, boolean conRepo, boolean pasaraRepo, boolean agruparNombre,int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			String consulta = "";
			if (pasaraRepo)
			{
				if(agruparNombre)
				{
					consulta = "  SELECT D.idDeposito,D.Nombre FROM depositos D "
							+ "INNER JOIN reposicion_articulosvm RA ON RA.Destino = D.idDeposito and RA.idEmpresa=D.idEmpresa "
							+ "AND RA.Estado=1  "
							+ "WHERE RA.idEmpresa="+idEmpresa+" and Tipo = "+ tipo+ " "
							+ "GROUP BY D.Nombre ";
				}
				else
				{
					consulta = "  SELECT D.idDeposito,D.Nombre FROM depositos D "
							+ "INNER JOIN reposicion_articulosvm RA ON RA.Destino = D.idDeposito and RA.idEmpresa=D.idEmpresa "
							+ "AND RA.Estado=1  "
							+ "WHERE RA.idEmpresa="+idEmpresa+" and Tipo = "+ tipo+ " "
							+ "GROUP BY D.idDeposito,D.Nombre ";
				}
				
			}
			else
			{
				if(conRepo)
				{
					consulta = "  SELECT D.idDeposito,D.Nombre FROM depositos D "
							+ "INNER JOIN reposicion_articulos RA ON RA.Destino = D.idDeposito and RA.idEmpresa=D.idEmpresa "
							+ "AND RA.Estado in (1,11) "
							+ "WHERE RA.idEmpresa="+idEmpresa+" and Tipo = "+ tipo+ " "
							+ "GROUP BY D.idDeposito,D.Nombre ";
				}
				else
				{
					consulta = "SELECT D.idDeposito,D.Nombre FROM depositos D  WHERE idEmpresa="+idEmpresa+" and Tipo ="+tipo;
				}
			}

			return econ.darListaDataIdDescripcion(consulta + " ORDER BY D.idDeposito");

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	public  List<DataIDDescripcion> darIDescUsuarioPorTipo(int tipo, int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			String consulta = "select idUsuario, CONCAT(Nombre, ' ', Apellido) as NOM from usuarios "
					+ "where idEmpresa="+idEmpresa+" and Perfil = "+tipo+" and baja=0";

			return econ.darListaDataIdDescripcion(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	public  List<DataIDDescripcion> darRazonesEnvio(int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			String consulta = "select * from razonesdoc where idEmpresa="+idEmpresa;

			return econ.darListaDataIdDescripcion(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public  List<Transporte> darTransportes(boolean encomiendas, int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			
			String consulta ="";
			if(encomiendas){
				consulta = "select * from transportes where idEmpresa="+idEmpresa+" and propio=0";
			}
			else{
				consulta = "select * from transportes where idEmpresa="+idEmpresa;
			}
			
			return econ.darListaTransportes(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	public  List<DepositoEnvio> darDepositosenvio(int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			String consulta = "select * from depositos where idEmpresa="+idEmpresa+" and tipo <> 4";

			return econ.darListadepositosEnvio(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public  DataIDDescripcion darAliasDeposito(String idDep, int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			System.out.println("");
			cone = econ.getConnection();

			String consulta = "select iddeposito, if(alias='',nombre,alias ), direccion from depositos where idEmpresa="+idEmpresa+" and iddeposito = "+idDep;

			return econ.darDataIdDescripcion(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return new DataIDDescripcion(0,"");
		}
	}
	
	public  DataIDDescripcion darLocalidadDeposito(String idDep, int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			System.out.println("");
			cone = econ.getConnection();

			String consulta = "select idDeposito, ciudad, departamento from depositos where idEmpresa="+idEmpresa+" and iddeposito = "+idDep;
			return econ.darDataIdDescripcion(consulta);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return new DataIDDescripcion(0,"");
		}
	}
	
	public  DataIDDescripcion darComentarioVenta(Long idVenta, int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			System.out.println("");
			cone = econ.getConnection();

			
			String consulta = "SELECT idVenta, Comentario, agencia FROM encuentra_api.ecommerce_import_venta WHERE idVenta = "+idVenta+" AND idEmpresa = "+idEmpresa+";";
			return econ.darDataIdDescripcion(consulta);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return new DataIDDescripcion(0,"");
		}
	}
	
	public  Hashtable<Integer, DataIDDescripcion> darAliasDepositos(int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			System.out.println("");
			cone = econ.getConnection();

			String consulta = "select iddeposito, alias, direccion from depositos where idEmpresa="+idEmpresa;

			return econ.darHasDataIdDescripcion_(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return new Hashtable<>();
		}
	}
	
	public  DataIDDescripcion DescripcionUsuario(int idUsuario, int idEmpresa) 
	{

		@SuppressWarnings("unused") Connection cone;
		try {
			System.out.println("");
			cone = econ.getConnection();

			String consulta = "select idusuario, CONCAT(nombre,' ', apellido) from usuarios where idusuario="+idUsuario+" and idEmpresa = "+idEmpresa;

			return econ.darDataIdDescripcion(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return new DataIDDescripcion(0,"");
		}
	}
	
	public  List<DocumentoEnvio>  darEnvios (Ruta ruta, String fechaI, String fechaF, int idEnvio, int idEmpresa)
	{
		List<DocumentoEnvio> retorno = new ArrayList<>();
		//if(depositoObj.getDescripcion().equals("1")) // envios custom los busca en el ERP
		//{
			/*String consulta="";
			if(fechaI==null)
			{
				consulta="Select Distinct MS.IdDocumento,SUM(MS.Cantidad) AS CANTIDAD,MS.IdEstado, Doc.*,Rd.Comentario as RazonDesc,Pe.Numero as NumeroUsuario , Pe.Nombre as Usuario, MSE.Descripcion as DescEstado, " 
							+"Dep.Descripcion as DescDeposito, DD.Descripcion as DescDepDest " 
							+"FROM MovStock MS INNER JOIN Documento Doc ON MS.IdDocumento = Doc.IdDocumento "
							+"INNER JOIN MovStockEstado MSE ON MS.IdEstado = MSE.IdMovStockEstado "
							+"INNER JOIN Deposito Dep ON Doc.IdDeposito = Dep.IdDeposito "
							+"INNER JOIN Deposito DD ON Doc.IdDepDestino = DD.IdDeposito "
							+"INNER JOIN Transaccion Tr ON Tr.IdTransaccion = Doc.IdTransaccion "
							+"INNER JOIN PersonaEmpresa Pe ON Pe.IdPersonaEmpresa = Tr.IdUsuario "
							+"INNER JOIN RazonDocumento Rd ON Doc.IdRazonDocumento=Rd.IdRazonDocumento "
							+"Where ((Doc.IdDepDestino ="+depositoObj.getId()+"  And Doc.IdTipoDocumento IN ('EMR','SMR','AJE','AJS','TRS','SMP','OEM','ORM')) "
							+"Or (Doc.IdDeposito ="+depositoObj.getId()+" And Doc.IdTipoDocumento IN ('TRE','EMC'))) "
							+"And (Doc.IdTipoDocumento IN ('EMR','SMR','TRE','AJE','AJS','TRS','AJI','EMC','SMP','OEM','ORM')) "
							+"And (  MS.IdEstado = 4 )  And Doc.IdListaEmpresa = 1 " 
							+"group by dOC.SAPExportado ,MS.IdDocumento,MS.IdEstado, Doc.IdDocumento,doc.IdTipoDocumento,doc.IdTransaccion,doc.IdDeposito,doc.Comentario,doc.Fecha,doc.NumeroDoc, "
							+"doc.IdRazonDocumento,doc.Serie,doc.IdDepDestino,doc.Hora,doc.IdListaEmpresa,Rd.Comentario,Pe.Numero,Pe.Nombre, MSE.Descripcion,  "
							+"Dep.Descripcion, DD.Descripcion "
							+"Order by  Rd.Comentario, Doc.NumeroDoc";
			}
			else
			{
				consulta="Select Distinct MS.IdDocumento,SUM(MS.Cantidad) AS CANTIDAD,MS.IdEstado, Doc.*,Rd.Comentario as RazonDesc,Pe.Numero as NumeroUsuario , Pe.Nombre as Usuario, MSE.Descripcion as DescEstado, " 
						+"Dep.Descripcion as DescDeposito, DD.Descripcion as DescDepDest " 
						+"FROM MovStock MS INNER JOIN Documento Doc ON MS.IdDocumento = Doc.IdDocumento "
						+"INNER JOIN MovStockEstado MSE ON MS.IdEstado = MSE.IdMovStockEstado "
						+"INNER JOIN Deposito Dep ON Doc.IdDeposito = Dep.IdDeposito "
						+"INNER JOIN Deposito DD ON Doc.IdDepDestino = DD.IdDeposito "
						+"INNER JOIN Transaccion Tr ON Tr.IdTransaccion = Doc.IdTransaccion "
						+"INNER JOIN PersonaEmpresa Pe ON Pe.IdPersonaEmpresa = Tr.IdUsuario "
						+"INNER JOIN RazonDocumento Rd ON Doc.IdRazonDocumento=Rd.IdRazonDocumento "
						+"Where ((Doc.IdDepDestino ="+depositoObj.getId()+"  And Doc.IdTipoDocumento IN ('EMR','SMR','AJE','AJS','TRS','SMP','OEM','ORM')) "
						+"Or (Doc.IdDeposito ="+depositoObj.getId()+" And Doc.IdTipoDocumento IN ('TRE','EMC'))) "
						+"And (Doc.IdTipoDocumento IN ('EMR','SMR','TRE','AJE','AJS','TRS','AJI','EMC','SMP','OEM','ORM')) "
						+"And (  MS.IdEstado = 4 )  And Doc.IdListaEmpresa = 1 " 
						+"AND Fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' "
						+"group by dOC.SAPExportado , MS.IdDocumento,MS.IdEstado, Doc.IdDocumento,doc.IdTipoDocumento,doc.IdTransaccion,doc.IdDeposito,doc.Comentario,doc.Fecha,doc.NumeroDoc, "
						+"doc.IdRazonDocumento,doc.Serie,doc.IdDepDestino,doc.Hora,doc.IdListaEmpresa,Rd.Comentario,Pe.Numero,Pe.Nombre, MSE.Descripcion,  "
						+"Dep.Descripcion, DD.Descripcion "
						+"Order by  Rd.Comentario, Doc.NumeroDoc";
			}*/
			//retorno = MSSQL.darEnvios(consulta);
		//}
		/*if(depositoObj.getDescripcionB().equals("1")) // envios encuentra
		{*/
		
			String queryFecha = "";
			String queryDeposito = "";
			String queryJoinRutaDeposito = "";
			String queryOrderBy = "";
			
			if(ruta.getDepositos().size() > 1) {
				queryDeposito = " AND ED.idDestino IN(";
				for (RutaDeposito deposito : ruta.getDepositos()) {
					
					if(ruta.getDepositos().get(0).equals(deposito)) {
						queryDeposito += deposito.getIdDeposito();
					}else {
						queryDeposito += ","+deposito.getIdDeposito();
					}
					
				}
				queryDeposito += ")";
				
				
				queryJoinRutaDeposito = " INNER JOIN ruta_depositos RDEP ON RDEP.idDeposito = ED.idDestino AND RDEP.idEmpresa = ED.IdEmpresa";
				queryOrderBy = " ORDER BY RDEP.Orden desc";
			}else if(ruta.getDepositos().size() == 1){
				queryDeposito = " AND ED.idDestino ="+ruta.getDepositos().get(0).getIdDeposito();
			}else if(ruta.getDepositos().size() == 0){
				return retorno;
			}
			
			if(fechaI!=null)
			{
				queryFecha = "	AND ED.fecha >='"+fechaI+"' AND ED.fecha <= '"+fechaF+"' ";
			}
					
			String query = "select ED.idDocumento numeroDoc, SUM(EL.Cantidad) cantidad, ED.idRazon idRazon, ER.Descripcion DescRazon, ED.idOrigen idorigen, DOR.Nombre descOrigen,  ED.idDestino idDestino, DDE.Nombre descDestino,ED.usuario idUsuario, CONCAT (US.Nombre,' ', US.Apellido) descUsuario,ED.RemitoML, ED.RemitoWEB, ED.comentario "+
							"	from envio_encuentra_documento ED  "+
							"	inner join envio_encuentra_documento_lineas EL ON EL.idDocumento = ED.idDocumento and ED.idEmpresa=EL.idEmpresa"+
							"	inner join depositos DOR on ED.idOrigen = DOR.idDeposito and DOR.idEmpresa=ED.idEmpresa"+
							"	INNER JOIN depositos DDE on ED.idDestino = DDE.idDeposito and ED.idEmpresa=DDE.idEmpresa"
							+	queryJoinRutaDeposito+
							"	INNER JOIN usuarios  US ON US.idUsuario=ED.usuario and US.idEmpresa=ED.idEmpresa" +
							"	INNER JOIN envio_encuentra_razones ER ON ER.idRazon = ED.idRazon and ER.idEmpresa=ED.idEmpresa"+
							"	WHERE TRUE and ED.idEmpresa="+idEmpresa+" " +
							"	AND ED.idEnvio="+idEnvio+" "
							+	queryDeposito
							+	queryFecha+
							"	GROUP BY ED.idDocumento "
							+   queryOrderBy;
			
			try 
			{
				@SuppressWarnings("unused") Connection cone;
				cone = econ.getConnection();

				String consulta = query;

				List<DocumentoEnvio> enviosEncuentra = econ.darEnvios(consulta);
				for (DocumentoEnvio d : enviosEncuentra) 
				{
					retorno.add(d);
				}  

			} catch (Exception e) {

				e.printStackTrace();
				return null;
			}
						
		//}
		
		return retorno;
	}


	
	
	public  int altaEnvio(Envio envio, int idE, Usuario u) 
	{
		Call_WS_analoga ws = new Call_WS_analoga();
		StringBuilder sb = new StringBuilder();
		int idEnvio = 0;
		
		//Hashtable<Integer, StringBuilder> fenicioHT = new Hashtable<>();
		List<jsonEstadoMP> cambioEstados = new ArrayList<>();
		List<SendMail> colaMails = new ArrayList<>();
		
		
		try 
		{
			int parcial;
			if(envio.isParcial())
			{
				parcial = 2;
			}
			else
			{
				parcial = 1;
			}
			
			
			/*
			 * Envio
			 */
			String consultaE = "";
			boolean nuevoEnvio = true;
			try
			{
				idEnvio = envio.getIdEnvio();
				if(idEnvio==0)
				{
					nuevoEnvio = true;
					
				}
				else
				{
					nuevoEnvio = false;
				}
				
			}
			catch(Exception e)
			{
				
			}

			if(nuevoEnvio)
			{
				consultaE = "INSERT INTO `envio` (`IdTransporte`, `IdChofer`, `IdAcompaniante`, " +" `Sentido`, `IdEstadoEnvio`,idEmpresa)"+ 
						" VALUES ("+envio.getTransporte().getId()+", "+envio.getChofer().getId()+", "+envio.getAcompaniante().getId()+", "+envio.getSentido()+", "+parcial+","+idE+");";
	
				idEnvio = eper.persistirDarUltimo(consultaE,"envio","idEnvio",idE);
			}
			else
			{
				consultaE = "INSERT INTO `envio` (idEnvio, `IdTransporte`, `IdChofer`, `IdAcompaniante`, " +" `Sentido`, `IdEstadoEnvio`,idEmpresa)"+ 
						" VALUES ("+idEnvio+","+envio.getTransporte().getId()+", "+envio.getChofer().getId()+", "+envio.getAcompaniante().getId()+", "+envio.getSentido()+", "+parcial+","+idE+") " +
								" ON DUPLICATE KEY UPDATE "+ 
								" `IdTransporte` = "+envio.getTransporte().getId()+", "+
								" `IdChofer`=  "+envio.getChofer().getId()+",  "+
								" `IdAcompaniante`= "+envio.getAcompaniante().getId()+",  "+
								" `Sentido`=  "+envio.getSentido()+", "+
								//" `Fecha` = '2014-04-11 15:45:56', "+ 
								" `IdEstadoEnvio`="+parcial+";";
				
				eper.persistir(consultaE);
				
				sb.append("delete from documentos where idEmpresa="+idE+" AND IdEnvio = "+idEnvio+";");
				sb.append("delete from enviodepositos where idEmpresa="+idE+" AND IdEnvio = "+idEnvio+";");
				
				/*eper.persistir(consultaElimina1);
				eper.persistir(consultaElimina2);*/
				persistir(sb.toString());
				
			}
			
			boolean integracionActiva = false;
			integracionActiva = darIntegracionProductiva(4, idE);
			
			/** CONTROLAR SI 
			    ID ENVIO 
			    VIENE EN 0 **/
			if (idEnvio!=0){
			
			
			for (DepositoEnvio depo : envio.getDepositos()) 
			{
				/*
				 * Enviodepo
				*/
				String consED = " INSERT INTO `enviodepositos` (`idEnvio`, `idDeposito`, `Posicion`,idEmpresa) VALUES ("+idEnvio+", "+depo.getIdDeposito()+", "+depo.getOrdenCarga()+","+idE+");";
				persistir(consED);
				for (DataDocTipoEnvio docE : depo.getDocumentos()) 
				{
					for (DocumentoEnvio doc : docE.getDocumentos()) 
					{
						if(doc.isCustom())
						{
							/*
							 * Documentos
							*/
							String consDoc = " INSERT INTO `documentos` (`Origen`, `Destino`, `Fecha`, `Usuario`, `DocOrigen`, `CantUnidades`,`IdRazon`, `Comentario`,`IdEnvio`, `Sentido`,idEmpresa) " +
											" VALUES ("+doc.getDepositoO().getId()+", "+doc.getDepositoD().getId()+", '"+envio.getFecha()+"', "+doc.getUsuario().getId()+","+doc.getNumeroDoc()+","+doc.getCantidad()+","+doc.getRazon().getId()+",'"+doc.getComentario()+"',"+idEnvio+",  "+envio.getSentido()+","+idE+");";
							
							int iddoc = eper.persistirDarUltimo(consDoc, "documentos", "idDoc",idE);
							
							if(doc.getArticulos()!=null)
							{
								sb = new StringBuilder();
								for (DataArticulo arti : doc.getArticulos()) 
								{
									/*	
									 * Articulodoc
									 */
									sb.append("INSERT INTO `articulosdocumento` (`idArticulo`, `idDocumento`, `Cantidad`,idEmpresa) VALUES ('"+arti.getId()+"', "+iddoc+", "+arti.getCantidad()+","+idE+");");
									
									
									//updateEcommerceEstado(Integer.parseInt(arti.getId()), 4);//estado 4
									if(doc.getRazon().getId()==2)
									{
										try
										{
											
											
										}
										catch(Exception e)
										{
											
										}
										
									}
								}
								persistir(sb.toString());
							}
							else
							{
								String consArDoc="";
								if(doc.getRazon().getId()==100){
									consArDoc = "INSERT INTO `articulosdocumento` (`idArticulo`, `idDocumento`, `Cantidad`,idEmpresa) VALUES ('BULTO', "+iddoc+", "+doc.getCantidad()+","+idE+");";
								}
								else{
									consArDoc = "INSERT INTO `articulosdocumento` (`idArticulo`, `idDocumento`, `Cantidad`,idEmpresa) VALUES ('sin definir', "+iddoc+", 1,"+idE+");";
								}
								persistir(consArDoc);
							}
						}
						else
						{
							
							persistir("UPDATE `envio_encuentra_documento` SET `idEnvio`='"+idEnvio+"', comentario ='"+doc.getComentario()+"' WHERE idEmpresa="+idE+" AND `idDocumento`="+doc.getNumeroDoc()+";");
							
							
							
							
							if(doc.getArticulos()!=null && doc.getRazon().getId()==2)
							{
								for (DataArticulo arti : doc.getArticulos()) 
								{
									/*DataIDDescripcion infoArti = darDataIdDescripcion("select p.idcanalML,d.mailCliente,d.tracking,"
											+ " d.idDestino,0,p.idFenicio from ecommerce_pedido p "
											+ " inner join ecommerce_pedido_destino d on p.idpedido=d.idpedido and d.idEmpresa=p.idEmpresa"
											+ " where p.idEmpresa="+idE+" AND p.idpedido="+arti.getId());*/
									
									arti.setId(arti.getId().trim());
									updateEcommerceEstado(Long.parseLong(arti.getId()), 4,idE,u);//estado 4
									DataArticuloEcommerceVerifR r = darArticuloEcommerceReqReclasifica(new Long(arti.getId()),0,idE);
									
									//CAMBIO DE ESTADO FENICIO
									boolean canalActivo = false;
									try {
										//canalActivo = canalActivoEC(infoArti.getId(), idE);
										canalActivo = canalActivoEC(r.getCanal(), idE);
									} catch (Exception e) {}
									if(canalActivo){
										/*String jotason2="";
										if(!infoArti.getDescripcionB().equals(""))
										{
											jotason2=
													 "     { "+
													 "        \"id\":\""+infoArti.getDescripcionC()+"\", "+
													 "        \"estado\":\"despachado\", "+
													 "		  \"trackingID\":\""+infoArti.getDescripcionB()+"\" "+
													 "     }, ";
										}
										else
										{
											jotason2=
													 "     { "+
													 "        \"id\":\""+infoArti.getDescripcionC()+"\", "+
													 "        \"estado\":\"despachado\" "+
													 "     }, ";
										}
										
										
										if(fenicioHT.get(infoArti.getId())==null){
											StringBuilder sbf = new StringBuilder();
											sbf.append(jotason2);
											fenicioHT.put(infoArti.getId(), sbf);
										}
										else{
											fenicioHT.get(infoArti.getId()).append(jotason2);
										}*/
										jsonEstadoMP jsonEstado = JSONCambioEstadoMarketPlace(idE, r);
										if(!jsonEstado.getJson().equals("")) {
											cambioEstados.add(jsonEstado);
										}																		
									}
									
									//ENVIO DE MAIL DE TRACKING
									if (integracionActiva){
										String bodyName = "";
										String urlTracking = "";
										PropertiesHelper pH=new PropertiesHelper("BodyNames");
										pH.loadProperties();		  
										if(r.getCanal()!=0 && r.getCanal()!=5){
											bodyName = pH.getValue(r.getCanal()+"");
										}
										
										if(r.getIdDestino()>10000){
											pH=new PropertiesHelper("UrlTracking");
											pH.loadProperties();
											urlTracking = pH.getValue(r.getIdDestino()+"");
										}
										
										if(!bodyName.equals("") && !urlTracking.equals("")){
											String body = EnviaMail.MailHTMLOD_FORUS(bodyName, r.getTracking(), urlTracking);
											SendMail sm = new SendMail(arti.getId(),r.getMailCliente(), "Pedido en Camino", body,
													"no-responder@forusuruguay.com.uy");
											
											colaMails.add(sm);
										}
									}
									
									
									
									if(doc.getRazon().getId()==2)
									{
										try
										{
																						
										}
										catch(Exception e)
										{
											
										}										
									}
								}
							}							
						}						
					}					
				}				
			}	
					
			}
			
			
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			if (integracionActiva){
				if(colaMails.size()>0){
					//MAILSPOOLER
					
					api.PutColaEnvioMails(colaMails, idE);
				}
			}
			
			integracionActiva = darIntegracionProductiva(3, idE);
			if (integracionActiva){
				/*Enumeration<Integer> elements = fenicioHT.keys();	
				while(elements.hasMoreElements()){
					int key=elements.nextElement();
					String builder = fenicioHT.get(key).toString();
					String json = "["+builder.substring(0,builder.length()-2)+"]";
					ws.setPedidos(json,key,idE);
				}*/
				if(!cambioEstados.isEmpty())
				api.putColaCambioEstadoMarketPlace(cambioEstados,idE);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}	
		
		return idEnvio;
	}

	public  List<Envio> encuentraDarEnvios(String fechaI, String fechaF, Integer estadoEnvio, String articulo, int depo,int idEmpresa) 
	{
		String consulta = "";
		String fechaBet = "";
		String estado ="";
		String idDepo ="";
		String order = "	ORDER BY idEnvio, idDeposito ";
		
		String consl2="select E.idEnvio idEnvio, E.Sentido, E.IdChofer, UC.Nombre, UC.Apellido, E.IdAcompaniante, UA.Nombre, UA.Apellido, E.IdTransporte, T.Matricula, T.Marca, T.Descripcion, "+ 
				"E.Fecha, E.IdEstadoEnvio, EE.Descripcion, ED.idDeposito idDeposito, Dep.nombre,  ED.Posicion "+
				"FROM envio E "+
				"inner join enviodepositos ED on ED.idEnvio=E.idEnvio and E.idEmpresa=ED.idEmpresa "+
				"inner join depositos Dep on Dep.idDeposito=ED.idDeposito and Dep.idEmpresa=ED.idEmpresa "+
				"inner join envioestado EE on EE.Id = E.IdEstadoEnvio and EE.idEmpresa=E.idEmpresa "+
				"inner join usuarios UC on E.IdChofer = UC.idUsuario and E.idEmpresa=UC.idEmpresa "+
				"inner join usuarios UA on UA.idUsuario = E.IdAcompaniante and UA.idEmpresa=E.idEmpresa "+
				"inner join transportes T on T.IdTransporte=E.IdTransporte and T.idEmpresa=E.idEmpresa "+
				"inner join envio_encuentra_documento DCE on DCE.idEnvio = E.idEnvio AND "
				+ "DCE.idDestino=ED.idDeposito and DCE.idEmpresa=E.idEmpresa "+
				"inner join envio_encuentra_razones RDDO on RDDO.idRazon = DCE.idRazon and RDDO.idEmpresa=DCE.idEmpresa "+
				"where E.idEmpresa="+idEmpresa+" ";
		
		if(estadoEnvio!=null)
		{
			estado ="	AND E.IdEstadoEnvio = "+estadoEnvio;
			
		}
		if(fechaI!=null)
		{
			fechaBet = "		AND E.Fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"'";
			 
		}
		if(depo!=0)
		{
			idDepo ="		AND ED.idDeposito = "+depo;
		}
		
		String gr = " GROUP BY E.idEnvio,ED.idDeposito ";
		
		consulta+=consl2+estado+fechaBet+idDepo+gr+order;
		
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			System.out.println(consulta);
			return econ.darListaEnvios(consulta,idEmpresa);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	public  List<Envio> encuentraDarEnviosPendienteDeposito(Integer estadoEnvio, Integer estadoDocumentos, Integer idDeposito
			,int idEmpresa) 
	{
		String consulta = "";
		
		if(estadoEnvio!=null && estadoDocumentos!=null && idDeposito !=null)
		{
			consulta = " select E.idEnvio, E.Sentido, E.IdChofer, CH.Nombre, CH.Apellido, E.IdAcompaniante, AC.Nombre, AC.Apellido, E.IdTransporte, T.Matricula, T.Marca, T.Descripcion  "+ 
							"	, E.Fecha, E.IdEstadoEnvio, EE.Descripcion, EDEP.idDeposito,  EDEP.Posicion, D.DocOrigen, D.IdRazon, RD.`Desc`, D.CantUnidades, D.Origen, D.Destino, D.Usuario, D.Comentario "+  
							"	from envio E, transportes T, usuarios CH, usuarios AC, envioestado EE, documentos D, "+
							"	enviodepositos EDEP, depositos DEP,razonesdoc RD   "+
							"	where E.IdTransporte = T.IdTransporte   "+
							"	e.idEmpresa=t.idEmpresa and t.idEmpresa=ch.idEmpresa and ch.idEmpresa=ac.idEmpresa and "+
							"	ac.idEmpresa=ee.idEmpresa and ee.idEmpresa=d.idEmpresa and d.idEmpresa=edep.idEmpresa "+
							"	edep.idEmpresa=dep.idEmpresa and dep.idEmpresa=rd.idEmpresa "+
							"	AND E.IdChofer = CH.idUsuario   "+
							"	AND E.IdAcompaniante = AC.idUsuario "+  
							"	AND E.IdEstadoEnvio = EE.Id   "+
							"	AND E.idEnvio = D.IdEnvio  "+
							"	AND D.Destino = EDEP.idDeposito "+ 
							"	AND EDEP.idEnvio = E.idEnvio   "+
							"	AND EDEP.idDeposito = DEP.idDeposito "+  
							"	and RD.idRazon = D.IdRazon  " +
							"	and E.idEmpresa = "+idEmpresa+" "+
							"	AND E.IdEstadoEnvio = "+estadoEnvio+" "+
							"	AND D.Estado = "+estadoDocumentos+" "+
							"	AND D.Destino = "+idDeposito+" "+
							"	ORDER BY E.idEnvio, EDEP.idDeposito, RD.idRazon";
			System.out.println(consulta);
		}
		
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();

			
			return econ.darListaEnvios(consulta,idEmpresa);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	

	
	public  List <DataDocVisual> encuentraDarListaDocsVisual(int origen, int destino)
	{
		String consulta = "";
		
		
		if(origen==0 && destino!=0)
		{
			consulta ="Select Distinct MS.IdDocumento,Doc.NumeroDoc, MSE.Descripcion , Doc.IdTipoDocumento AS tipo, Doc.IdDeposito AS Destino ,Dep.Descripcion as DescDeposito, Doc.IdDepDestino as depOrigen,DD.Descripcion as ORIGEN , Pe.Nombre as Usuario "+
					"	FROM MovStock MS INNER JOIN Documento Doc "+
					"	ON MS.IdDocumento = Doc.IdDocumento INNER JOIN MovStockEstado MSE "+ 
					"	ON MS.IdEstado = MSE.IdMovStockEstado INNER JOIN Deposito Dep "+
					"	ON Doc.IdDeposito = Dep.IdDeposito INNER JOIN Deposito DD "+
					"	ON Doc.IdDepDestino = DD.IdDeposito INNER JOIN Transaccion Tr "+ 
					"	ON Tr.IdTransaccion = Doc.IdTransaccion INNER JOIN PersonaEmpresa Pe "+ 
					"	ON Pe.IdPersonaEmpresa = Tr.IdUsuario   "+
					"	 "+
					"	 Where ((Doc.IdDepDestino = /*deposito de destino*/"+destino+" And Doc.IdTipoDocumento IN ('EMR','SMR','AJE','AJS','TRS','SMP','OEM','ORM')) Or (Doc.IdDeposito = /*deposito de destino*/"+destino+" And Doc.IdTipoDocumento IN ('TRE','EMC')))  "+
					"	And (Doc.IdTipoDocumento IN ('EMR','SMR','TRE','AJE','AJS','TRS','AJI','EMC','SMP','OEM','ORM'))  "+
					"	/* "+
					"	a continucacion los estados "+
					"	0	Normal "+
					"	1	Pedido "+
					"	2	Reservado "+
					"	3	Remitido "+
					"	4	Pendiente "+
					"	5	Facturar "+
					"	*/ "+
					"	And (  MS.IdEstado = 4 ) "+ 
					"	 And Doc.IdListaEmpresa = 1  "+
					"	Order by  Doc.IdTipoDocumento, Doc.NumeroDoc";
		}
		else if(origen !=0 && destino!=0)
		{
			consulta ="Select Distinct MS.IdDocumento,Doc.NumeroDoc, MSE.Descripcion , Doc.IdTipoDocumento AS tipo, Doc.IdDeposito AS Destino ,Dep.Descripcion as DescDeposito, Doc.IdDepDestino as depOrigen,DD.Descripcion as ORIGEN , Pe.Nombre as Usuario "+
					"	FROM MovStock MS INNER JOIN Documento Doc "+
					"	ON MS.IdDocumento = Doc.IdDocumento INNER JOIN MovStockEstado MSE "+ 
					"	ON MS.IdEstado = MSE.IdMovStockEstado INNER JOIN Deposito Dep "+
					"	ON Doc.IdDeposito = Dep.IdDeposito INNER JOIN Deposito DD "+
					"	ON Doc.IdDepDestino = DD.IdDeposito INNER JOIN Transaccion Tr "+ 
					"	ON Tr.IdTransaccion = Doc.IdTransaccion INNER JOIN PersonaEmpresa Pe "+ 
					"	ON Pe.IdPersonaEmpresa = Tr.IdUsuario   "+
					"	 "+
					"	 Where ((Doc.IdDeposito =/*deposito de origen*/ "+origen+" And Doc.IdTipoDocumento IN ('EMR','SMR','AJE','AJS','TRS','AJI','SMP','EMC','OEM','ORM')) Or (Doc.IdDepDestino = /*deposito de origen*/"+origen+" And Doc.IdTipoDocumento IN ('TRE','EMC'))) "+ 
					"	And ((Doc.IdDepDestino = /*deposito de destino*/"+destino+" And Doc.IdTipoDocumento IN ('EMR','SMR','AJE','AJS','TRS','SMP','OEM','ORM')) Or (Doc.IdDeposito = /*deposito de destino*/"+destino+" And Doc.IdTipoDocumento IN ('TRE','EMC')))  "+
					"	And (Doc.IdTipoDocumento IN ('EMR','SMR','TRE','AJE','AJS','TRS','AJI','EMC','SMP','OEM','ORM'))  "+
					"	/* "+
					"	a continucacion los estados "+
					"	0	Normal "+
					"	1	Pedido "+
					"	2	Reservado "+
					"	3	Remitido "+
					"	4	Pendiente "+
					"	5	Facturar "+
					"	*/ "+
					"	And (  MS.IdEstado = 4 ) "+ 
					"	 And Doc.IdListaEmpresa = 1  "+
					"	Order by  Doc.IdTipoDocumento, Doc.NumeroDoc"; 

			
		}
		else if(origen!=0 && destino==0)
		{
			consulta ="Select Distinct MS.IdDocumento,Doc.NumeroDoc, MSE.Descripcion , Doc.IdTipoDocumento AS tipo, Doc.IdDeposito AS Destino ,Dep.Descripcion as DescDeposito, Doc.IdDepDestino as depOrigen,DD.Descripcion as ORIGEN , Pe.Nombre as Usuario "+
					"	FROM MovStock MS INNER JOIN Documento Doc "+
					"	ON MS.IdDocumento = Doc.IdDocumento INNER JOIN MovStockEstado MSE "+ 
					"	ON MS.IdEstado = MSE.IdMovStockEstado INNER JOIN Deposito Dep "+
					"	ON Doc.IdDeposito = Dep.IdDeposito INNER JOIN Deposito DD "+
					"	ON Doc.IdDepDestino = DD.IdDeposito INNER JOIN Transaccion Tr "+ 
					"	ON Tr.IdTransaccion = Doc.IdTransaccion INNER JOIN PersonaEmpresa Pe "+ 
					"	ON Pe.IdPersonaEmpresa = Tr.IdUsuario   "+
					"	 "+
					"	 Where ((Doc.IdDeposito =/*deposito de origen*/ "+origen+" And Doc.IdTipoDocumento IN ('EMR','SMR','AJE','AJS','TRS','AJI','SMP','EMC','OEM','ORM')) Or (Doc.IdDepDestino = /*deposito de origen*/"+origen+" And Doc.IdTipoDocumento IN ('TRE','EMC'))) "+ 
					"	And (Doc.IdTipoDocumento IN ('EMR','SMR','TRE','AJE','AJS','TRS','AJI','EMC','SMP','OEM','ORM'))  "+
					"	/* "+
					"	a continucacion los estados "+
					"	0	Normal "+
					"	1	Pedido "+
					"	2	Reservado "+
					"	3	Remitido "+
					"	4	Pendiente "+
					"	5	Facturar "+
					"	*/ "+
					"	And (  MS.IdEstado = 4 ) "+ 
					"	 And Doc.IdListaEmpresa = 1  "+
					"	Order by  Doc.IdTipoDocumento, Doc.NumeroDoc";
		}
		else if(origen==0 && destino==0)
		{
		consulta ="Select Distinct MS.IdDocumento,Doc.NumeroDoc, MSE.Descripcion , Doc.IdTipoDocumento AS tipo, Doc.IdDeposito AS Destino ,Dep.Descripcion as DescDeposito, Doc.IdDepDestino as depOrigen,DD.Descripcion as ORIGEN , Pe.Nombre as Usuario "+
						"	FROM MovStock MS INNER JOIN Documento Doc "+
						"	ON MS.IdDocumento = Doc.IdDocumento INNER JOIN MovStockEstado MSE "+ 
						"	ON MS.IdEstado = MSE.IdMovStockEstado INNER JOIN Deposito Dep "+
						"	ON Doc.IdDeposito = Dep.IdDeposito INNER JOIN Deposito DD "+
						"	ON Doc.IdDepDestino = DD.IdDeposito INNER JOIN Transaccion Tr "+ 
						"	ON Tr.IdTransaccion = Doc.IdTransaccion INNER JOIN PersonaEmpresa Pe "+ 
						"	ON Pe.IdPersonaEmpresa = Tr.IdUsuario   "+
						"	 "+
						"	 Where (Doc.IdTipoDocumento IN ('EMR','SMR','TRE','AJE','AJS','TRS','AJI','EMC','SMP','OEM','ORM'))  "+
						"	/* "+
						"	a continucacion los estados "+
						"	0	Normal "+
						"	1	Pedido "+
						"	2	Reservado "+
						"	3	Remitido "+
						"	4	Pendiente "+
						"	5	Facturar "+
						"	*/ "+
						"	And (  MS.IdEstado = 4 ) "+ 
						"	 And Doc.IdListaEmpresa = 1  "+
						"	Order by  Doc.IdTipoDocumento, Doc.NumeroDoc"; 
		}
		return MSSQL.darListaDocsVisual(consulta);
			
		
		
		
		
	}
	public  List <DataArtMovS> encuentraDarListaArticulosDoc(List<Integer> documentos)
	{
		String in = "";
		
		for (Integer i : documentos) 
		{
			in +=  i +",";
		}
		System.out.println("original " + in);
		
		in = in.substring(0, in.length()-1);
		
		System.out.println("salida " + in);
		
		String consulta = "Select IdMovStock, IdDocumento, AB.IdArticulo, Cantidad=1*Cantidad, AB.NroBarra "+
							"	From MovStock INNER JOIN ArtBarra AB ON MovStock.IdArticulo = AB.IdArticulo "+
							"	Where IdDocumento in("+in+") Order By IdMovStock";  
		
		return MSSQL.darListaArticulosDocs(consulta);
				
	}
	
	
	public  List <DataArtMovS> encuentraDarListaArticulosEntrega(List<Integer> documentos, int idEmpresa)
	{
		try
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			String in = "";
			
			for (Integer i : documentos) 
			{
				in +=  i +",";
			}
			System.out.println("original " + in);
			
			in = in.substring(0, in.length()-1);
			
			System.out.println("salida " + in);
			
			String consulta = "Select D.IdEnvio ,D.DocOrigen, AB.idArticulo, AD.Cantidad, AB.Barra "+
								"	from documentos D, articulosdocumento AD, artbarra AB "+
								"	where D.idDoc = AD.idDocumento "+
								"	and D.idEmpresa=AD.idEmpresa and AD.idEmpresa=AB.idEmpresa and AB.idEmpresa="+idEmpresa+
								"	and AD.idArticulo = AB.idArticulo " +
								"	and AD.Cantidad > AD.Confirmada"+
								"	and D.DocOrigen in ("+in+") "+
								"	order by D.IdEnvio";  
			
			return econ.darListaArticulosDocs(consulta);
		}
		catch (Exception e)
		{
			return null;
		}
		
			
		
		
		
		
	}
	
	
	

	public  void AsignarTareaARecepcion(int idMain, int id,int idEmpresa) 
	{
		String update="UPDATE RECEPCION SET TAREAMAIN="+idMain+" WHERE idEmpresa="+idEmpresa+" AND idRecepcion="+id;
		try{
			
			System.out.println(update);
			eper.persistir(update);
			boolean ok=ActualizarEstadoRecepcion(id, 2,idEmpresa);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public  List<DataArtBarraCant> darListaARecepcionar(int main, int idEmpresa) throws Exception 
	{
		@SuppressWarnings("unused")
		Connection cone = econ.getConnection();
		
		String select="select ar.idArticulo,ab.Barra,ar.cantidadPedida "+
                       "from recepcion r,detallerecepcion d, articulosarecepcionar ar "
                       + "left outer join artbarra ab  on ab.IdArticulo=ar.idArticulo and ab.idEmpresa=ar.idEmpresa"+
                       " where r.idEmpresa=d.idEmpresa and d.idEmpresa=ar.idEmpresa and r.idEmpresa="+idEmpresa+
                       " and r.idRecepcion=d.idRecepcion and d.idDetalle=ar.idDetalle and r.tareaMain="+main;
		System.out.println(select);
		return econ.darData(select);
	}
	
	
	public  void updateRecepcionMob(int idRecepcion, String inns, Hashtable<String, DataIDIDDescripcion> hashArt, int IdUsuario, int idE) throws Exception 
	{
		Hashtable<String, DataIDIDDescripcion> hash = new Hashtable<>();
		
		
		Connection con = econ.getConnection();
		List<DataIDDescripcion> artTipos = darListaDataIdDescripcionConsulMYSQL("SELECT IdTipo, idArticulo FROM articulos "
				+ "WHERE idEmpresa="+idE+" and idArticulo IN ("+inns+")");
		
		String bultosIn = "";
		for (DataIDDescripcion a : artTipos) 
		{
			if(a.getId()==4)//bulto
			{
				bultosIn+="'"+a.getDescripcion()+"',";
				/*creo un bulto min con lo que preciso para cerrarlo*/
				bulto b = new bulto();
				b.setIdBulto(a.getDescripcion());
				b.setUsuarioClose(IdUsuario);
				boolean movimiento = true;//reconozco el movimiento en el cierre del bulto
				
				Cerrar_Bulto(b, movimiento,idE);
			}
			else
			{
				hash.put(a.getDescripcion(), hashArt.get(a.getDescripcion()));
			}
			
		}
			
		if(bultosIn.length()>0)
		{
			bultosIn=bultosIn.substring(0,bultosIn.length()-1);
			String select2 = "SELECT SUM(D.cantidad),D.idArticulo FROM bulto B "
					+ "INNER JOIN bulto_contenido D ON D.idBulto = B.idBulto and B.idEmpresa=D.idEmpresa "
					+ "WHERE B.idBulto IN ("+bultosIn+") and b.idEmpresa= "+idE
							+ " GROUP BY D.idArticulo;";
			List<DataIDDescripcion> artInBultos = darListaDataIdDescripcionConsulMYSQL(select2);
			
			for (DataIDDescripcion a : artInBultos) 
			{
				if(hash.get(a.getDescripcion())!=null)
				{
					DataIDIDDescripcion d = hash.get(a.getDescripcion());
					d.setId(d.getId()+a.getId());
					
					hash.put(a.getDescripcion(), d);
				}
				else
				{
					DataIDIDDescripcion d = new DataIDIDDescripcion(a.getId(), a.getDescripcion());
					hash.put(a.getDescripcion(), d);
				}
			}
			
			con = econ.getConnection();
			
		}
			
			
		
		
		
		
		
		con = econ.getConnection();
		String select="select ar.cantidadPedida,ar.idArticulo,ar.id from recepcion r,detalleRecepcion dr, articulosarecepcionar ar "
				+ "where r.idRecepcion=dr.idRecepcion and dr.idDetalle=ar.idDetalle AND ar.cantidadPedida-ar.cantidadRecibida >0 "
				+ "and r.idEmpresa=dr.idEmpresa and dr.idEmpresa=ar.idEmpresa "
				+ "AND R.idRecepcion="+idRecepcion+" and R.idEmpresa="+idE;
		List<DataIDIDDescripcion>articulosEnRecepcion=econ.darArticulosRecepcion(select);
		for (DataIDIDDescripcion articulo : articulosEnRecepcion) 
		{
			//id-> cantidadPedida
			//Descripcion-> idArticulo
			//iId-> idDetalle
			if(hash.get(articulo.getDescripcion())!=null)
			{
				DataIDIDDescripcion elemento = hash.get(articulo.getDescripcion()); 
				if(articulo.getDescripcion().equals(elemento.getDescripcion()))
				{
					String update="update articulosarecepcionar ar set ar.cantidadContada = ar.cantidadContada+"+elemento.getId()+" where ar.idEmpresa="+idE+" AND id="+articulo.getIid();
					try 
					{
						eper.persistir(update);
						eper.persistir("INSERT INTO `barrasrecepcion` (`idBarra`, `cantidad`, `idUsuario`, `idRecepcion`,idEmpresa) "
								+ " VALUES ('"+articulo.getDescripcion()+"', "+elemento.getId()+", "+IdUsuario+", "+idRecepcion+","+idE+") " +
								" ON DUPLICATE KEY UPDATE "
								+ "cantidad = cantidad+"+elemento.getId()+", idUsuario = "+IdUsuario+";") ;
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		//String update2="update detallerecepcion DR set DR.CantidadContada=(select sum(ar.CantidadContada) as contadas from articulosarecepcionar ar where DR.idDetalle = ar.idDetalle and DR.idRecepcion = "+idRecepcion+")";
		String update2="update detallerecepcion as t1 inner join (select sum(ar.CantidadContada) as contadas, ar.idDetalle from articulosarecepcionar ar group by ar.idDetalle ) as t2 set t1.CantidadContada = t2.contadas where t1.idEmpresa="+idE+" AND t1.idDetalle = t2.idDetalle and t1.idRecepcion="+idRecepcion
		;
		try 
		{
			
			eper.persistir(update2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		String update3="update recepcion r set r.cantidadContada=(select sum(ar.CantidadContada) as contadas from articulosarecepcionar ar,detallerecepcion DR where DR.idEmpresa="+idE+" AND DR.idDetalle = ar.idDetalle and DR.idRecepcion = "+idRecepcion+") where idEmpresa="+idE+" AND idRecepcion = "+idRecepcion;
		try 
		{
		
			eper.persistir(update3);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		try 
		{
				con=econ.getConnection();
				ActualizarEstadoRecepcion(idRecepcion, 3,idE);
				
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
		}
		
	
	}
	

	
	

	public  List<DataRecepcion> darRecepcionesCulminadas(int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
		cone = econ.getConnection();
		List<DataRecepcion>recepciones=null;
		String consulta="Select * from recepcion where idEmpresa ="+idEmpresa+" and estado IN (2,3,4)";
		return econ.darRecepciones(consulta, true);
		} catch (Exception e) {
			return null;
		}
	}
	
	public  List<DataRecepcion> darRecepcionesEstados(String[]estados, int idEmpresa) 
	{
		String in = "";
		for (int i = 0; i < estados.length; i++) 
		{
			in+=estados[i]+",";
		}
		if(in.length()>0)
		{
			in = in.substring(0,in.length()-1);
		}
		
		
		
		@SuppressWarnings("unused") Connection cone;
		try {
		cone = econ.getConnection();
		List<DataRecepcion>recepciones=null;
		String consulta="Select * from recepcion where idEmpresa="+idEmpresa+" and estado in ("+in+")";
		return econ.darRecepciones(consulta, true);
		} catch (Exception e) {
			return null;
		}
	}
	
	public  List<DataRecepcion> darRecepcionessf(int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try {
		cone = econ.getConnection();
		List<DataRecepcion>recepciones=null;
		String consulta="Select R.* from recepcion R, articulosarecepcionar AR, detallerecepcion DR  "
				+ "where R.idRecepcion = DR.idRecepcion and DR.idDetalle=AR.idDetalle AND AR.cantidadRecibida-AR.cantidadFacturada>0 "
				+ "and r.idEmpresa=ar.idEmpresa and ar.idEmpresa=dr.idEmpresa and r.idEmpresa="+idEmpresa+" "
				+ "group by R.idRecepcion";
		return econ.darRecepciones(consulta,true);
		} catch (Exception e) {
			return null;
		}
	}
	
	public  List<DataRecepcion> darRecepcionID(int idRece, int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try {
		cone = econ.getConnection();
		List<DataRecepcion>recepciones=null;
		String consulta="Select * from recepcion where idEmpresa="+idEmpresa+" and idRecepcion = "+idRece;
		return econ.darRecepciones(consulta, true);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	public  List<DataRecepcion> encuentraDarRecepcionesPendientes(int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try {
		cone = econ.getConnection();
		List<DataRecepcion>recepciones=null;
		String consulta="Select R.idRecepcion,R.estado,R.fecha, R.cantidadSolicitada,R.cantidadRecibida,R.vehiculo,R.proveedor,R.tareaMain, R.idProveedor, R.tipo "
				+ "from recepcion R,auditoriarecepcion AR "
				+ "where r.idEmpresa=ar.idEmpresa and r.idEmpresa="+idEmpresa+" and R.idRecepcion = AR.idRecepcion AND R.estado= 1 "
				+ "group by R.idRecepcion";
		return econ.darRecepciones(consulta, true);
		} catch (Exception e) {
			return null;
		}
	}
	
	

	public  int encuentraAltaRecepcion(DataRecepcion recepcion,String tipo,int idE) 
	{
		/**************************************************************INSERTO RECEPCION************************************************************************/
		int idRecepcion = 0;
		
		try
		{
			String insert ="INSERT INTO `recepcion` (`estado`, `fecha`, `cantidadSolicitada`, `cantidadRecibida`, `vehiculo`, `proveedor`,`idProveedor`,`tipo`,idEmpresa) " +
					" VALUES (1, '"+recepcion.getFechaSQL()+"', "+recepcion.getCantidadEsperada()+", 0, '"+recepcion.getVehiculo()+"', '"+recepcion.getProveedor().getDescripcion()+"' , '"+recepcion.getProveedor().getId()+"','"+tipo+"',"+idE+" );";
			
			idRecepcion = eper.persistirDarUltimo(insert, "recepcion", "idRecepcion",idE);
			//persisto la auditoria
			eper.persistir("INSERT INTO `auditoriarecepcion` (`idRecepcion`, `estado`,fecha, idEmpresa) VALUES ("+idRecepcion+", 1,'"+recepcion.getFechaSQL()+"',"+idE+");");
			
			ActualizarEstadoRecepcion(idRecepcion, 1,idE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		/********************************************************************FIN ******************************************************************/
		List<String>articulosNO = new ArrayList<>();
		for (DataOC oc : recepcion.getOrdenes()) 
		{
			int idDetalle = 0;
			/*********************************************************************INSERTO UN DETALLE POR CADA DOCUMENTO*****************************************************************/
			
			String insertDet ="INSERT INTO `detallerecepcion` (`idRecepcion`, `idDocumento`, `cantidadPedida`, `cantidadRecepcionada`,IdEmpresa,`Folio`) VALUES ("+idRecepcion+", "+oc.getNumeroDocumento()+", "+oc.getUnidadesSolicitadas()+", 0,"+idE+",'"+oc.getFolio()+"');";
			try
			{
				
				idDetalle = eper.persistirDarUltimo(insertDet, "detallerecepcion","idDetalle",idE);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			/***************************************************************************************INSERTO EN ARTICULOS POR CADA ARTICULO DE CADA OC***********************************************/
			
			String insertArtAR = "INSERT INTO  `articulosarecepcionar` (`idDetalle`, `idArticulo`, `cantidadPedida`, `Color`,`idLineaOC`, etiquetar, distribuir,idEmpresa) VALUES ";
			String insertArt ="insert into art_descripcion(id,descripcion,idEmpresa) values "; 
			StringBuilder valuesAR = new StringBuilder();
			StringBuilder valuesAD = new StringBuilder();
			boolean pri = true;
			for (DataArticuloOC art : oc.getArticulos()) 
			{
				for (ColorOC col : art.getColores()) 
				{
					for (TalleOC tal : col.getTalles()) 
					{
						
						if(pri)
						{
							pri = false;
							
							valuesAR.append("("+idDetalle+", '"+art.getIdArticulo()+""+col.getId()+""+tal.getTalle()+"', "+tal.getaRecibir()+",'"+col.getDescripcion()+"',"+tal.getIdLinea()+","+col.getEtiqueta()+","+col.getDistribuir()+","+idE+")");
							valuesAD.append("('"+art.getIdArticulo()+""+col.getId()+""+tal.getTalle()+"','',"+idE+") ");
						}
						else
						{
							valuesAR.append(",("+idDetalle+", '"+art.getIdArticulo()+""+col.getId()+""+tal.getTalle()+"', "+tal.getaRecibir()+",'"+col.getDescripcion()+"',"+tal.getIdLinea()+","+col.getEtiqueta()+","+col.getDistribuir()+","+idE+")");
							valuesAD.append(",('"+art.getIdArticulo()+""+col.getId()+""+tal.getTalle()+"','',"+idE+") ");
						}
						 
							
					}
				}
			}
			
			String cola =" ON DUPLICATE KEY UPDATE descripcion = VALUES(descripcion)";
			try 
			{
				eper.persistir(insertArt+valuesAD.toString()+cola);
				eper.persistir(insertArtAR+valuesAR.toString());
			} catch (Exception e) 
			{
				
				e.printStackTrace();
				return 0;
				
			}
			
		}
		if(articulosNO.isEmpty())
		{
			return idRecepcion;
		}
		else
		{
			return 0;
		}
		
		
	}
	
	public int encuentraAltaPackingRecepcion(int idRecepcion, List<VORecepcionSinOrden> recepcion,int idE) 
	{
		String insertArtAR = "INSERT INTO  `packing_recepcion` (`idRecepcion`, `idArticulo`, `cantidad`, `cantBultos`,`Total`, idEmpresa) VALUES ";
		StringBuilder valuesAR = new StringBuilder();
		boolean pri = true;
		for (VORecepcionSinOrden art : recepcion) 
		{
			if(pri)
			{
				pri = false;
				valuesAR.append("("+idRecepcion+", '"+art.getIdArticulo()+"', "+art.getCantidadPacking()+","+art.getCantidadBultos()+","+art.getCantidadTotal()+","+idE+")");
			}
			else
			{
				valuesAR.append(",("+idRecepcion+", '"+art.getIdArticulo()+"', "+art.getCantidadPacking()+","+art.getCantidadBultos()+","+art.getCantidadTotal()+","+idE+")");
			}
		}
			
		String cola =" ON DUPLICATE KEY UPDATE Total = VALUES(Total)";
		try 
		{
			eper.persistir(insertArtAR+valuesAR.toString()+cola);
		} catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
			return 1;
	}
	
	public void crearBultosARecepcionarDesdePacking (int idRecepcion, List<VORecepcionSinOrden> listaPacking, int usuario, String depoCentral, int idEmpresa)
	{
		Utilidades util = new Utilidades();
		List<bulto> bultos = new LinkedList<bulto>(); 
		bulto b = new bulto();
		
		//Total de bultos a recepcionar
		int total = 0;
		for(VORecepcionSinOrden linea: listaPacking)
		{
			total+=linea.getCantidadBultos();
		}
		//CON LA INFO DEL PACKING VOY A CREAR LOS BULTOS
		String[] indices = util.auxIndices(total);
		int numeroIndice = 0;
		for(VORecepcionSinOrden linea: listaPacking)
		{
			for(int i=0; i<linea.getCantidadBultos(); i++)
			{
				int posicion = 0;
				int equipo = 2;
				Fecha fecha = new Fecha(0,0,0);
				fecha.now();
				String id= "R"+indices[numeroIndice]+equipo+posicion+fecha.darFechaString();
				bulto bul = new bulto(id, "Recepcion "+idRecepcion, true, 0, 0, 0, 0.0, false, "", usuario, depoCentral,idEmpresa);
				List<bultoContenido> listaContenido = new ArrayList<>();
				bultoContenido bc = new bultoContenido(linea.getIdArticulo(),linea.getCantidadPacking(), idRecepcion, usuario);
				bul.Agregar_A_Bulto_NO_persist(bc);
				listaContenido.add(bc);
				bul.setContenidoList(listaContenido);
				
				bultos.add(bul);
				System.out.println(linea);
				numeroIndice++;
			}
		}
		
		b.crearBultos(idRecepcion, bultos, idEmpresa);
	}
	
	
	public boolean darIntegracionProductiva(int idIntegracion, int idEmpresa)
	{
		return hayRegistro("select * from integraciones where idIntegracion = "+idIntegracion+" and IdEmpresa="+idEmpresa+" and Productivo = 1");
	}
	
	public  DataBooleanDesc confirmarRecepcion(int id,String serie,int remito,int parcial, int idEmpresa,List<DataIDIDDescripcion> lista, int destino, int idUsuario) 
	{
		boolean productivoVS = hayRegistro("select * from integraciones where idIntegracion = 1 and Productivo = 1 AND idEmpresa="+idEmpresa);
		
		StringBuilder queries = new StringBuilder();
		for (DataIDIDDescripcion ar : lista) 
		{
			queries.append("UPDATE articulosarecepcionar AR\r\n" + 
					"JOIN detallerecepcion DR\r\n" + 
					"ON DR.idDetalle = AR.idDetalle\r\n" + 
					"AND DR.idRecepcion = "+id+" AND DR.idDocumento = "+ar.getId()+"\r\n" +
					"SET cantidadFacturada =cantidadFacturada+"+ar.getIid()+" WHERE AR.idEmpresa ="+idEmpresa+" and idArticulo='"+ar.getDescripcion()+"'; \r\n");
		}
		
		
		boolean actualizo=false;
		boolean ok=false;
		try 
		{
			System.out.println(queries.toString());
			eper.persistir(queries.toString());
			
			//WS INTEGRADOR//
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			
			Connection conn=econ.getConnection();
			//=econ.darArticulosRecepcionados(select1);
			String select2="select idProveedor,tipo from recepcion where idEmpresa="+idEmpresa+" AND idRecepcion="+id;
			conn=econ.getConnection();
			DataIDDescripcion rec=econ.darProveedorRecepcion(select2);
			
			Hashtable<Integer, List<DataIDDescripcion>>articulosXOC = new Hashtable<Integer, List<DataIDDescripcion>>();
			
			
			
			int doc=0;
			// id = idOC
			//Iid =  cantidad
			// descripcion = articulo
			// descripcionB = folio
			for (DataIDIDDescripcion d : lista) 
			{
				if(articulosXOC.get(d.getId())==null)
				{
				
					List <DataIDDescripcion> lista2=new ArrayList<>();
					DataIDDescripcion data=new DataIDDescripcion();
					data.setId(d.getIid());
					data.setIdB(d.getId());
					data.setDescripcion(d.getDescripcion());
					data.setDescripcionB(d.getDescripcionB());
					lista2.add(data);
					
					articulosXOC.put(d.getId(),lista2);
					
				}
				else
				{
					List <DataIDDescripcion> lista2 = articulosXOC.get(d.getId());
					DataIDDescripcion data=new DataIDDescripcion();
					data.setId(d.getIid());
					data.setIdB(d.getId());
					data.setDescripcion(d.getDescripcion());
					data.setDescripcionB(d.getDescripcionB());
					lista2.add(data);
					
					articulosXOC.put(d.getId(),lista2);
				}
			}
				
				
				
			List<List<DataIDDescripcion>> listaOrdenes = new ArrayList<List<DataIDDescripcion>>(articulosXOC.values());	
				
			for (List<DataIDDescripcion> lista2 : listaOrdenes) 
			{
				int idOC = lista2.get(0).getIdB();
				String folio = lista2.get(0).getDescripcionB();
				
				System.out.println("Enviaria a recepcionar");
				System.out.println("Tipo "+"OCC"+" comentario "+folio+" idOrden "+ idOC+" proveedor "+ rec.getId()+" Factura "+ serie+" "+remito);
				System.out.println("con este detalle de articulos");
				for (DataIDDescripcion a : lista2) 
				{
						System.out.println(a.getDescripcion() +" - "+a.getId());
				}
				
				actualizo=true;
				if(productivoVS)
				{
					//actualizo = ws.GrabarRecepcion(lista2,"OCC","Folio: "+folio, idOC, rec.getId(), serie,remito,id,idEmpresa);
					GrabarRecepcion r = new GrabarRecepcion();
					r.setDetalle(lista2);
					r.setTipoAfecta("OCC");
					r.setObservacion("Folio: "+folio);
					r.setNumeroDoc(idOC);
					r.setNroProveedor(rec.getId());
					r.setSerieRemito(serie);
					r.setNroRemito(remito);
					r.setIdRecepcion(id);
					r.setIdEmpresa(idEmpresa);
					r.setDestino(destino);
					r.setIdUsuario(idUsuario);
					actualizo = api.grabarRecepcion(r, idEmpresa);
				}
				
				
			}
				
				
			
			
			
			

			
			if(!actualizo){
				String update="update articulosarecepcionar set pendientesFacturar=pendientesFacturar-cantidadfacturada,cantidadFacturada=0 where idEmpresa="+idEmpresa+" AND iddetalle in (select iddetalle  from detallerecepcion where idEmpresa="+idEmpresa+" AND idrecepcion="+id+")";
				
				eper.persistir(update);
				String consulta="select sum(cantidadPedida) -sum(cantidadRecibida) from  articulosArecepcionar where idEmpresa="+idEmpresa+" AND iddetalle in (select iddetalle from detallerecepcion where idEmpresa="+idEmpresa+" AND idrecepcion="+id+")group by iddetalle";
				Connection conn3=econ.getConnection();
				int pendientes=econ.getSuma(consulta);
				if(pendientes==0)
					ok=ActualizarEstadoRecepcion(id, 5,idEmpresa);
			}
		} catch (Exception e) 
		{
			//actualizo=new DataBooleanDesc();
			//actualizo.setBool(false);
			//actualizo.setDescricpion("Error interno");
			e.printStackTrace();
		}

		DataBooleanDesc retorno =new DataBooleanDesc();
		retorno.setBool(false);
		retorno.setDescricpion("");
		return retorno;
		
		
	}
	
	
	public  List<DataIDDescripcion> encuentraDarDepositos(int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
		cone = econ.getConnection();
		
		return econ.darListaDataIdDescripcion("select idDeposito, Nombre from depositos where idEmpresa="+idEmpresa+" AND login=1;");
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	public  List<DataIDDescripcion> encuentraDarDepositosConLogin(int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
		cone = econ.getConnection();
		
		return econ.darListaDataIdDescripcion("select idDeposito, Nombre from depositos where Login=1 and idEmpresa="+idEmpresa);
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	public  List<DataIDDescripcion> encuentraDarEquiposDeposito(String deposito, int empresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
		cone = econ.getConnection();
		List<DataRecepcion>recepciones=null;
		
		return econ.darListaDataIdDescripcion("SELECT e.idEquipo,e.descripcion FROM equipos e WHERE e.idDeposito="+deposito+" AND idEmpresa="+empresa+";");
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	public  List<DataIDDescripcion> encuentraDarEquiposTrabajo(String deposito, int empresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
		cone = econ.getConnection();
		List<DataRecepcion>recepciones=null;
		
		return econ.darListaDataIdDescripcion("SELECT e.idEquipo,e.descripcion FROM equipos_trabajo e WHERE e.idDeposito="+deposito+" AND idEmpresa="+empresa+";");
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	
	

	public  boolean ActualizarEstadoRecepcion(int idRecepcion, int idEstado, int idE) 
	{
		String update="UPDATE RECEPCION SET ESTADO="+idEstado+" WHERE idEmpresa="+idE+" AND idRecepcion="+idRecepcion;
		boolean pasa = false;
		try
		{
			
			System.out.println(update);
			pasa = eper.persistir(update);
			if(pasa)
			{
				return eper.persistir("INSERT INTO `auditoriarecepcion` (`idRecepcion`, `estado`,idEmpresa) VALUES ("+idRecepcion+", "+idEstado+","+idE+");");
			}
			else
			{
				return pasa;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return pasa;
		}

		
	}
	


	
	
	public  DataRecepcion darAgendada(String fechaSQL, int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			
			
			
			cone = econ.getConnection();
			
			String consulta = "select idRecepcion, proveedor, tipo  from  recepcion where (fecha BETWEEN '"+fechaSQL+"' AND DATE_ADD('"+fechaSQL+"', INTERVAL 1 HOUR)) AND idEmpresa="+idEmpresa;
			return econ.daragendada(consulta);
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	


	

	
	

	public  void ModificarRecepcion(int idRecepcion, List<DataIDDescDescripcion> lista, int idEmpresa) {
		System.out.println(idRecepcion);
		for(int i=0;i<lista.size();i++){
		String consulta="update articulosarecepcionar  set cantidadFacturada=cantidadFacturada+"+lista.get(i).getId()+" " +
				"where idEmpresa="+idEmpresa+" AND idArticulo='"+lista.get(i).getDescripcion()+"' and iddetalle in (select idDetalle from detallerecepcion where idEmpresa="+idEmpresa+" AND idrecepcion="+idRecepcion+")";
		try {
			System.out.println(consulta);
		
			eper.persistir(consulta);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		}
	}

	
	public  DataIDDescripcion darTipoRecepcion(int main, int idEmpresa) {
		String consulta="Select * from recepcion where idEmpresa="+idEmpresa+" AND tareaMain="+main;
		DataIDDescripcion tipo=null;
		@SuppressWarnings("unused") Connection cone;
		try {
			cone=econ.getConnection();
			tipo=econ.getTipo(consulta);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return tipo;
	}
	

	
	




public  int encuentraAltaDepoA(DepositoAdmin depoA, int idE) 
{
	
	String consulta="INSERT INTO `depositos` (`idDeposito`, `Nombre`, `Direccion`, `Login`,idEmpresa) VALUES ("+depoA.getId()+", '"+depoA.getNombre()+"', '"+depoA.getDireccion()+"', "+depoA.getLogin()+","+idE+") " +
			" ON DUPLICATE KEY UPDATE `Nombre` = VALUES(`Nombre`),`Direccion` = VALUES(`Direccion`), `Login` = VALUES(`Login`) ;";
	try 
	{
		
		return  eper.persistirDarUltimo(consulta, "depositos", "idDeposito",idE);
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return 0;
	}
	
	
}

public  boolean encuentraEliminarDepositoAdmin(int id, int idEmpresa) 
{
	String consulta="DELETE FROM `depositos` WHERE idEmpresa="+idEmpresa+" AND `idDeposito`="+id;
	boolean retorno = false;
	try 
	{
		
		retorno = eper.persistir(consulta);
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return retorno;
	}
	
	return retorno;
}





public  int darRecepcion(int idMain, int idEmpresa) {
	String select = "SELECT IDRECEPCION FROM RECEPCION WHERE idEmpresa="+idEmpresa+" AND TAREAMAIN="+idMain;
	int cant=0;
	@SuppressWarnings("unused") Connection cone;
	try {
		cone=econ.getConnection();
		cant=econ.getRecepcion(select);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	return cant;
}


public  void encuentraCantOjos(String idOjo, int cantidad, int numero, int idE) 
{
	String insert="INSERT INTO `cantidadojo` (`idOjo`, `Cantidad`, `idUsuario`,idEmpresa) VALUES ('"+idOjo+"', "+cantidad+", "+numero+","+idE+") ON DUPLICATE KEY UPDATE Cantidad = "+cantidad+", idUsuario = "+numero+";" ;	
	
	try 
	{
		
		
		eper.persistir(insert);
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}	
}

public  int encuentraDarCantidadOJO(String idOjo, int idEmpresa) 
{
	String consulta = "SELECT Cantidad, idOjo FROM cantidadojo WHERE idEmpresa="+idEmpresa+" AND idOjo = '"+idOjo+"'";
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		

		List <DataIDDescripcion> lista = econ.darIdDescripcion(consulta);
		return lista.get(0).getId();
		

	} catch (Exception e) {

		//e.printStackTrace();
		return 0;
	}
	
}
public  Hashtable<String, String> darBarrasTodas(int idEmpresa) 
{
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		
		persistir("insert ignore into artbarra (select idArticulo,idArticulo,idEmpresa from articulos where idEmpresa="+idEmpresa+" AND  IdTipo = 2)");
		String consulta = "SELECT AB.idArticulo,AB.Barra FROM artbarra AB WHERE idEmpresa="+idEmpresa+";";

		return  econ.DarBarras(consulta);
		
		

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  List<DataDescDescripcion> darBarrasInn(String inns, int idEmpresa) 
{
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		
		
		String consulta = "select idArticulo,Barra from artbarra where idArticulo in ("+inns+") and IdEmpresa ="+idEmpresa;

		return  econ.darBarrasInn(consulta);
		
		

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  Hashtable<String, String> darBarrasHT(String inns, int idEmpresa) 
{
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		
		
		String consulta = "select idArticulo,Barra from artbarra where idArticulo in ("+inns+") and IdEmpresa ="+idEmpresa;

		return  econ.darBarrasInnHT(consulta);
		
		

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  Hashtable<String, String> darBarrasHTArt(String inns, int idEmpresa) 
{
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		
		
		String consulta = "SELECT MAX(barra),idarticulo FROM artbarra WHERE barra != idarticulo AND idArticulo in ("+inns+") and IdEmpresa ="+idEmpresa +" GROUP BY idarticulo";

		return  econ.darBarrasInnHT(consulta);
		
		

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}



public  List<Menu> darMenu(List<DataIDDescripcion> grupos, boolean permisos, int idEmpresa) 
{
	try 
	{
		String ids="(";
		boolean first=true;
		for(DataIDDescripcion g:grupos){
			if (first){
				first=false;
				ids+=g.getId();
			}
			else{
				ids+=","+g.getId();
			}
		}
		ids+=")";
		
		List<Menu> menus = darMenuHijos(ids, "0",permisos,idEmpresa);
		
		for (Menu m : menus) 
		{
			List<Menu> menusHijos = darMenuHijos(ids,m.getId(),permisos,idEmpresa);
			if(menusHijos.isEmpty())
			{
				m.setTieneHijos(false);
			}
			else
			{
				m.setTieneHijos(true);
				for (Menu hi : menusHijos) 
				{
					List<Menu> menusNietos = darMenuHijos(ids,hi.getId(),permisos,idEmpresa);
					if(menusNietos.isEmpty())
					{
						hi.setTieneHijos(false);
					}
					else
					{
						hi.setTieneHijos(true);
						for (Menu ni : menusNietos) 
						{
							List<Menu> menusBisNietos = darMenuHijos(ids,ni.getId(),permisos,idEmpresa);
							if(menusBisNietos.isEmpty())
							{
								ni.setTieneHijos(false);
							}
							else
							{
								ni.setTieneHijos(true);
								
							}
							ni.setHijos(menusNietos);
						}
					}
					hi.setHijos(menusNietos);
				}
			}
			m.setHijos(menusHijos);
			
			
			
			
		}
		
		return  menus;
		
		

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}


private  List<Menu> darMenuHijos(String grupos, String padre,boolean permisos, int idEmpresa) 
{
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		
		String consulta = "";
		
		if(permisos){
			consulta = "select distinct M.idMenu, M.Titulo, S.Visible, M.Padre, M.icon, M.href, M._BLANK, M.tags from seg_menu M, seguridad S where M.idMenu = S.idmenu and S.idGrupo in "+grupos+" and Padre = '"+padre+"' AND S.IdEmpresa="+idEmpresa+" AND M.idMenu not in ('A.F.C','C.A.E','D.B','E.D') order by M.idMenu asc ";

		}
		else{
			consulta = "select distinct M.idMenu, M.Titulo, S.Visible, M.Padre, M.icon, M.href, M._BLANK, M.tags from seg_menu M, seguridad S where M.idMenu = S.idmenu and S.idGrupo in "+grupos+" and Padre = '"+padre+"' AND S.IdEmpresa="+idEmpresa+" AND S.Visible=1 order by M.idMenu asc";

		}
		
		List<Menu> menus = econ.DarMenu(consulta);
		
		return  menus;
		
		

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}


public  void encuentraActualizaFechaAgenda(int idAgen, String fechaSQL, int idE) 
{
	String update="UPDATE `recepcion` SET `fecha`='"+fechaSQL+"' WHERE idEmpresa="+idE+" AND `idRecepcion`="+idAgen+";" ;	
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		
		System.out.println(update);
		eper.persistir(update);
		eper.persistir("INSERT INTO `auditoriarecepcion` (`idRecepcion`, `estado`,fecha,idEmpresa) VALUES ("+idAgen+", "+1+",'"+fechaSQL+"',"+idE+");");
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}	
	
}

public  void encuentraEliminaAgenda(int idAgen, int idEmpresa) 
{
	String update="delete from detallerecepcion where idEmpresa="+idEmpresa+" AND idRecepcion = "+idAgen+"; ";
	String updateII="DELETE FROM auditoriarecepcion WHERE idEmpresa="+idEmpresa+" AND idRecepcion = "+idAgen+";";
	String updateIII= "delete from recepcion where idEmpresa="+idEmpresa+" AND idRecepcion = "+idAgen ;	
	
	try 
	{
		
		
		eper.persistir(update);
		eper.persistir(updateII);
		eper.persistir(updateIII);
		//eper.persistir("INSERT INTO `auditoriarecepcion` (`idRecepcion`, `estado`,fecha) VALUES ("+idAgen+", "+1+",'"+fechaSQL+"');");
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}	
	
}






public  Hashtable<String, StockReposicion> darDeposStock(String consulta ,String valor, int idSinc, int porcentaje, ArticuloReposicion articulo, Hashtable<String, Integer> stocksReservados, Hashtable<String, Integer> stocksEnviados, int idEmpersa) 
{
	int intentos = 0;
	Hashtable<String, StockReposicion> lista = MSSQL.darListaDeposStock(consulta, valor, intentos, idSinc,porcentaje, articulo, stocksReservados,stocksEnviados,idEmpersa);
	
	while(lista.isEmpty()&& intentos<4)
	{
		intentos++;
		lista = MSSQL.darListaDeposStock(consulta, valor, intentos, idSinc, porcentaje, articulo, stocksReservados,stocksEnviados,idEmpersa);
	}
	
	
	
	
	return lista;
	
}








public  Fecha darUltimaSincronizacionRepo(boolean central, int idEmpresa)
{
	List<DataIDDescripcion> lista=null;
	try {

		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		
		String consulta = "";
		if(central)
		{
			consulta = "select Id, Fecha from reposicion_sincronizacion where OK = 1 AND idEmpresa="+idEmpresa+" AND central = 1 order by  Id desc limit 1";
		}
		else
		{
			consulta = "select Id, Fecha from reposicion_sincronizacion where OK = 1 AND idEmpresa="+idEmpresa+" AND central = 0 order by Id desc limit 1";
		}

		lista = econ.darIdDescripcion(consulta);
		
		Fecha fecha = new Fecha(lista.get(0).getDescripcion());
		
		return fecha;
	} catch (Exception e) {
		return null;
	}
}


public  int actualizarSincRepo(int ok, int idSinc, int idE) 
{
	String consulta = "INSERT INTO `reposicion_sincronizacion` (OK, Id,idEmpresa) VALUES ("+ok+","+idSinc+","+idE+");";
	
	try 
	{
		
		return  eper.persistirDarUltimo(consulta, "reposicion_sincronizacion", "Id",idE);
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return 0;
	}
	
}

public  void actualizarOKSincRepo(int ok, int idSinc, int idEmpresa) 
{
	String consulta = "UPDATE `reposicion_sincronizacion` SET `OK`='"+ok+"' WHERE idEmpresa="+idEmpresa+" AND `Id`="+idSinc+";";
	
	try 
	{
		
		eper.persistir(consulta);
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
}
public  void actualizarFechaSincRepo(int idSinc, String fecha, int idEmpresa) 
{
	String consulta = "UPDATE `reposicion_sincronizacion` SET  `Fecha`='"+fecha+"' WHERE idEmpresa="+idEmpresa+" AND `Id`="+idSinc+";";
	
	try 
	{
		
		eper.persistir(consulta);
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
}
public  void actualizarLogSincRepo(int idSinc, String mensaje, int porcentaje, int idE) 
{
	String consulta = "INSERT INTO `reposicion_log` (`IdSinc`, `Evento`, `Porcentaje`,idEmpresa) VALUES ("+idSinc+", '"+mensaje+"', "+porcentaje+","+idE+");";
	
	try 
	{
		eper.persistir(consulta);
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
}
	
public  int darNextSincRepo(int idEmpresa) 
{
	int retorno = 0;
	
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		boolean cerrarConexion = false;
		retorno = econ.UltimoId("select max(Id) from reposicion_sincronizacion WHERE idEmpresa="+idEmpresa,cerrarConexion);
		retorno+=1;

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;

	
}


public  List<DataIDDescDescripcion> DarListaEventos(int idSinc, int idEmpresa) 
{
	List<DataIDDescDescripcion> retorno = null;
	String consulta ="select IdEvento,Fecha,Evento, Porcentaje from reposicion_log where idEmpresa = "+idEmpresa+" AND IdSinc = "+idSinc; 
	
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		retorno = econ.darLogSinc(consulta);
		

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;
}


public  void guardarLineaReposicion(ArticuloReposicion articulo,	String justificacion, int idSinc, int mayorista, int idE) 
{
	boolean liberaPickingMayo = false;
	Logica logica = new Logica();
	String  libPickMay= logica.darParametroEmpresa(idE, 24);
	
	if(libPickMay!=null && libPickMay.equals("1"))
	{
		liberaPickingMayo = true;
	}
	int tipo = articulo.getTipo();
	
	String consulta = "";
	if(articulo.getOrigen()!=0)
	{
		if(tipo==3&&liberaPickingMayo)
		{
			consulta="INSERT INTO `reposicion_articulosvm` (`IdSincronizacion`, `idArticulo`, `Origen`, `Destino`, `Cantidad`, `Estado`, `Justificacion`,`Marca`,`Seccion`,`Clase`, `Mayorista`,`stockCen`, `idPicking`,`idLineaSAP`,`IdGenero`,`IdCategoria`, idEmpresa, idTipo) " +
							"VALUES ("+idSinc+", '"+articulo.getArticulo()+"', "+articulo.getOrigen()+", "+articulo.getSucursal()+", "+articulo.getVenta()+", 1, '"+justificacion+"',"+articulo.getMarca()+","+articulo.getSeccion()+","+articulo.getClase()+","+1+","+articulo.getStockCentral()+","+articulo.getIdpicking()+","+articulo.getIdLineaSAP()+","+articulo.getGenero()+","+articulo.getCategoria()+","+idE+","+articulo.getTipo()+") on duplicate key update `Justificacion`= '"+justificacion+"',`Cantidad`="+articulo.getVenta()+";";
		}
		else
		{
			//agregar tipo
			switch(tipo) 
			{
				case 2:
					System.out.println("venta web");
					consulta="INSERT INTO `reposicion_articulos` (`IdSincronizacion`, `idArticulo`, `Origen`, `Destino`, `Cantidad`, "
							+ "`Estado`, `Justificacion`,`Marca`,`Seccion`,`Clase`, `Mayorista`,`IdGenero`,`IdCategoria`,"
							+ "`IdSolicitudTraslado`, idEmpresa, idTipo, prioridad) " +
								"VALUES ("+idSinc+", '"+articulo.getArticulo()+"', "+articulo.getOrigen()+", "+articulo.getSucursal()+", "
										+ ""+articulo.getVenta()+", 1, '"+justificacion+"',"+articulo.getMarca()+","+articulo.getSeccion()
										+","+articulo.getClase()+","+1+","+articulo.getGenero()+","+articulo.getCategoria()+","
										+articulo.getSolicitud()+","+idE+","+articulo.getTipo()+","+articulo.getPrioridad()
										+") on duplicate key update `Cantidad`="+articulo.getVenta();
					break;
				case 1:
					System.out.println("Picking Repo Locales");
					consulta="INSERT INTO `reposicion_articulos` (`IdSincronizacion`, `idArticulo`, `Origen`, `Destino`, `Cantidad`, "
							+ "`Estado`, `Justificacion`,`Marca`,`Seccion`,`Clase`, `Mayorista`,`IdGenero`,`IdCategoria`"
							+ ",`IdSolicitudTraslado`, idEmpresa, idTipo, prioridad) " +
								"VALUES ("+idSinc+", '"+articulo.getArticulo()+"', "+articulo.getOrigen()+", "+articulo.getSucursal()+", "
							+articulo.getVenta()+", 1, '"+justificacion+"',"+articulo.getMarca()+","+articulo.getSeccion()+","
								+articulo.getClase()+","+mayorista+","+articulo.getGenero()+","+articulo.getCategoria()+","
							+articulo.getSolicitud()+","+idE+","+articulo.getTipo()+","+articulo.getPrioridad()
							+") on duplicate key update `Cantidad`="+articulo.getVenta();
					break;
				case 3:
					System.out.println("Picking por mayor");
					consulta="INSERT INTO `reposicion_articulos` (`IdSincronizacion`, `idArticulo`, `Origen`, `Destino`, `Cantidad`, "
							+ "`Estado`, `Justificacion`,`Marca`,`Seccion`,`Clase`, `Mayorista`,`IdGenero`,`IdCategoria`"
							+ ",`IdSolicitudTraslado`, idEmpresa, idTipo, prioridad) " +
								"VALUES ("+idSinc+", '"+articulo.getArticulo()+"', "+articulo.getOrigen()+", "+articulo.getDestino()+", "
							+articulo.getVenta()+", 1, '"+justificacion+"',"+articulo.getMarca()+","+articulo.getSeccion()+","
								+articulo.getClase()+","+1+","+articulo.getGenero()+","+articulo.getCategoria()+","
							+articulo.getSolicitud()+","+idE+","+articulo.getTipo()+","+articulo.getPrioridad()
							+") on duplicate key update `Cantidad`="+articulo.getVenta();
					break;
			}			
		}
		

		try 
		{
			System.out.println("consulta picking manual "+consulta);
			eper.persistir(consulta);
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			
		}
	}
	
	
	
	
}

@SuppressWarnings("unchecked")
public  List<DataLineaRepo> darListaLineasRepoPick(int idPick, int idEmpresa) 
{
	
	String consulta = "select AR.Descripcion, RA.idArticulo, Cantidad, 0, DDe.Nombre, Destino,0,Origen,'',RA.stockCen "
			+ " FROM reposicion_articulos  RA, "
			+ " reposicion_sincronizacion RS, "
			+ " depositos DOr, "
			+ " depositos DDe, "
			+ " art_descripcion AR "
			+ " where "
			+ " RA.IdSincronizacion = RS.Id  AND RA.idEmpresa = RS.idEmpresa "
			+ " AND RA.Origen = DOr.idDeposito AND  RA.idEmpresa = DOr.idEmpresa "
			+ " AND DDe.idDeposito = RA.Destino AND  DDe.idEmpresa = RA.idEmpresa "
			+ " AND AR.Id =RA.idArticulo AND AR.idEmpresa =RA.idEmpresa "
			+ " AND RA.idEmpresa="+idEmpresa+" AND RA.Estado in (2,3) AND RA.idPicking = "+idPick;
	
	
	
	List<DataLineaRepo> repos = null;
	List<DataLineaRepo> retorno = new ArrayList<>();
	 
	
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		repos = econ.darListaPicking(consulta);
		
		
		
		int recorrido = 0;
		
		for (DataLineaRepo da : repos) 
		{
			List<DataIDIDDescripcion> ubicacionesArtuculo = encuentraDarSectores(da.getIdArticulo(), recorrido,null,idEmpresa);
			if (!ubicacionesArtuculo.isEmpty()) 
			{
			// entro si hay ubicaciones para ese articulo
				int cantidadSol = da.getSolicitada();
				int cantEnOjos = 0;
				
				for (DataIDIDDescripcion ubi : ubicacionesArtuculo) 
				{
					if(cantidadSol>0)
					{
						DataLineaRepo temp = da.clone(); // hago un linearepo temp
						int cantOjo = ubi.getIid(); // obtengo la cantidad en ese ojo
						cantEnOjos +=cantOjo; // la sumo a la cantidad que sacar?  por si en los ojos no hay suficiente para lo solicitado
						if(cantidadSol<=cantOjo)
						{
							// si entra ac? quiere decir que de ese ojo se puede sacar todo lo que le piden de ese articulo
							temp.setSolicitada(cantidadSol);
							temp.setCubi(ubi.getDescripcion());
							temp.setRecorrido(ubi.getId());
							cantidadSol = 0;
							
							DataIDIDDescripcion estEstMod = DarEstanteria(temp.getCubi(),idEmpresa);
							
							temp.setEstnteria(estEstMod.getId());
							temp.setEstnte(Integer.parseInt(estEstMod.getDescripcion()));
							temp.setModulo(estEstMod.getIid());
							retorno.add(temp);
							recorrido = ubi.getId();	
							
							
						}
						else
						{
							temp.setSolicitada(cantOjo); // le pido que saque todo lo de ese ojo
							temp.setCubi(ubi.getDescripcion());
							temp.setRecorrido(ubi.getId());
							cantidadSol = cantidadSol-cantOjo; // bajo lo que saco del total solicitado
							
							DataIDIDDescripcion estEstMod = DarEstanteria(temp.getCubi(),idEmpresa);
							
							temp.setEstnteria(estEstMod.getId());
							temp.setEstnte(Integer.parseInt(estEstMod.getDescripcion()));
							temp.setModulo(estEstMod.getIid());
							retorno.add(temp);
							recorrido = ubi.getId();	
							
							
						}
								
						
								
							
					}
				}
			} 
			else 
			{
				//no hay ubicaciones para ese articulo
				da.setCubi("SIN ASIGNAR");
				retorno.add(da);
			}
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return retorno;
	}
	Collections.sort(retorno);
	return retorno;
	
}


@SuppressWarnings("unchecked")
private  List<DataLineaRepo> darListaLineasReservadasRepoPick(int idPick, int idEmpresa, int idUsuario) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
	} 
	catch (Exception e1) 
	{
		
		e1.printStackTrace();
	}
	List<DataIDDescripcion> list= econ.darIdDescripcion("SELECT distinct destino,'' FROM reposicion_articulos WHERE mayorista=1 AND idEmpresa="+idEmpresa+" and idpicking="+idPick);
	
	String rao = "left outer JOIN reposicion_articulos_ojos RAO ON RAO.idArticulo = AR.idArticulo AND RAO.idEmpresa = AR.idEmpresa AND RAO.idPicking = RA.idPicking AND RAO.destino=RA.Destino and RA.idSolicitudTraslado=RAO.solicitud and ra.idUsuario=rao.idUsuario ";
	
	
	String justificacion=", ' ' ";
	
	// Si destino es e-commerce
	if(list.size()==1 && list.get(0).getId()==1200){
		rao+=" and RAO.pedido=RA.seccion ";
		justificacion= ", RA.Justificacion ";
	}
	
	String wHusu = "";
	String iNusu = "";
	if(idUsuario!=0){
		wHusu = " and u.idusuario = '"+idUsuario+"' ";
		iNusu = " inner join usuarios u on u.idusuario=ra.idusuario and u.idEmpresa=ra.idEmpresa ";
	}
	
	String consulta = "select ifnull(ARD.Descripcion,AR.Descripcion), RA.idArticulo AS codigoID, ifnull(rao.cantidad,ra.cantidad), 0, DDe.Nombre, RA.Destino, 0 as stk,Origen,'',\r\n" 
			+ "			 if(ISNULL(OTA.Cantidad),0, OTA.Cantidad), if(RAO.cUbicacion is null,'SIN ASIGNAR',RAO.cUbicacion), if(OJO.idEstanteria is null,0,OJO.idEstanteria), \r\n" 
			+ "			 if(OJO.idEstante is null,0,OJO.idEstante), if(OJO.idModulo is null,0,OJO.idModulo), if(OJO.idRecorrido is null,999999,OJO.idRecorrido), \r\n" 
			+ "			 if(EST.Descripcion is null,'--',EST.Descripcion),RA.seccion,RA.idSolicitudTraslado, RA.Justificacion, autoVerificacion, ifnull(RAO.idBulto,''), ifnull(bc.cant,0), ifnull(raoBulto.cant,0),ra.picked,ra.idPosSort, AR.Imagen, AR.Packing ,RA.Cantidad,AR.unidadVenta \r\n" 
			+ "			 FROM depositos DOr \r\n" 
			+ "			 INNER JOIN reposicion_articulos RA ON RA.Origen = DOr.idDeposito AND RA.idEmpresa = DOr.idEmpresa \r\n" 
			+iNusu
			+ "			 INNER JOIN depositos DDe ON DDe.idDeposito = RA.Destino AND DDe.idEmpresa = RA.idEmpresa \r\n" 
			+ "			 INNER JOIN articulos AR ON AR.idArticulo =RA.idArticulo AND AR.idEmpresa =RA.idEmpresa \r\n"  
			+ "			 left outer JOIN art_descripcion ARD ON ARD.id =AR.idArticulo AND ARD.idEmpresa =AR.idEmpresa \r\n"  
			+ rao
			+ " left outer JOIN ojos OJO ON OJO.idOjo = RAO.cUbicacion AND OJO.idEmpresa = RAO.idEmpresa "
			+ " left outer JOIN estanterias EST ON OJO.idEstanteria = EST.idEstanteria AND OJO.idEmpresa = EST.idEmpresa "
			+ " LEFT outer JOIN ojostienenarticulos OTA ON OTA.idOjo = OJO.idOjo AND OTA.idArticulo = RA.idArticulo AND OTA.idEmpresa = RA.idEmpresa "
			+ " LEFT OUTER JOIN (SELECT idbulto, SUM(cantidad) cant, idEmpresa FROM bulto_contenido GROUP BY idbulto) bc ON bc.idbulto=rao.idBulto AND RAO.idEmpresa = bc.idEmpresa "
			+ " LEFT OUTER JOIN (SELECT idbulto, SUM(cantidad) cant, idEmpresa, idpicking FROM reposicion_articulos_ojos GROUP BY idBulto,idpicking) raoBulto "
			+ " ON raoBulto.idbulto=rao.idBulto AND raoBulto.idEmpresa=rao.idEmpresa AND raoBulto.idPicking=rao.idPicking "
			+ " where  RA.idEmpresa="+idEmpresa+" AND RA.idPicking = "+idPick+ " and RA.estado!=4 "+wHusu+" \r\n"
			+ " ORDER BY RAO.idBulto";
			
	
	
	
	
	List<DataLineaRepo> retorno = null;
	 
	
	try 
	{
		
		cone = econ.getConnection();
		System.out.println(consulta);
		retorno = econ.darListaPickingReservadas(consulta);
		
		
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return retorno;
	}
	Collections.sort(retorno);
	return retorno;
	
}


public List<PosicionesPosibles> getPosicionesPosibles(String idArticulo, int ultimaPosicion, int deposito){
	List<PosicionesPosibles> resultado = new ArrayList<>();
	String consulta = "SELECT e.idEstanteria,o.idModulo,o.idEstante,o.idOjo,o.idRecorrido, e.Descripcion FROM ojostienenarticulos ota"
			+ " INNER JOIN ojos o ON ota.idOjo = o.idOjo AND o.IdEmpresa = ota.IdEmpresa"
			+ " INNER JOIN estanterias e ON o.idEstanteria = e.idEstanteria"
			+ " WHERE ota.idOjo NOT IN ('1','0') AND ota.idOjo NOT LIKE '$p' AND ota.idOjo NOT LIKE '$e' AND idArticulo = '"+idArticulo+"' AND e.idEstanteria NOT IN(2,632) AND e.idDeposito="+deposito+" AND e.almacenapallet=0"
			+ " ORDER BY ABS("+ultimaPosicion+" - o.idRecorrido)";
	
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		resultado = econ.creoPosicionesPosibles(consulta);
	} catch(Exception e) {
		e.printStackTrace();
		return resultado;
	}

	
	return resultado;
}



public ReposicionPicking creoReposicionPicking(int idTarea, int ultimaUbicacion, int idEmpresa){
	
	ReposicionPicking resultado = null;
	
	
	
	String consulta = "SELECT t2.*\r\n" + 
			"FROM \r\n" + 
			"(\r\n" + 
			"SELECT tma.idTarea,tma.idArticulo,o.idRecorrido, IFNULL(ABS("+ultimaUbicacion+" - o.idRecorrido),10000) distancia\r\n" + 
			"FROM tarea_movimiento_articulos tma\r\n" + 
			"LEFT OUTER JOIN\r\n" + 
			"	(\r\n" + 
			"		SELECT IFNULL(artBU.idArticulo,artAR.idArticulo) idBulto ,vis.idOjo, IFNULL( bc.idArticulo,  artAR.idArticulo) articulo , vis.Cantidad, vis.idRecorrido\r\n" + 
			"		FROM \r\n" + 
			"		vista_ojostienenarticulos vis \r\n" + 
			"		 LEFT OUTER JOIN articulos artBU ON artBU.idArticulo = vis.idArticulo AND vis.idempresa=artBU.IdEmpresa  AND artBU.IdTipo=4\r\n" + 
			"		 LEFT OUTER JOIN bulto bu ON bu.idBulto = artBU.idArticulo AND bu.idempresa=artBU.IdEmpresa\r\n" + 
			"		 LEFT OUTER JOIN bulto_contenido bc ON bc.idBulto = artBU.idArticulo AND bc.idempresa=artBU.IdEmpresa\r\n" + 
			"		 LEFT OUTER JOIN articulos artAR ON artAR.idArticulo = vis.idArticulo AND vis.idempresa=artAR.IdEmpresa  AND artAR.IdTipo=1\r\n" + 
			"		 WHERE vis.idEmpresa="+idEmpresa+" \r\n" + 
			"		 AND vis.almacenapallet=1\r\n" + 
			"\r\n" + 
			"	)\r\n" + 
			"	o ON o.articulo = tma.idArticulo\r\n" + 
			"WHERE tma.idTarea = "+idTarea+" AND tma.idEmpresa="+idEmpresa+" AND tma.terminado = 0\r\n" + 
			"ORDER BY distancia\r\n" + 
			"LIMIT 1\r\n" + 
			") AS t1\r\n" + 
			"INNER JOIN (\r\n" + 
			"SELECT tma.idTarea,tma.idArticulo,a.Descripcion d1,tma.cantidad c1, cantidadMovida,cantidadBajada,o.idBulto,o.cantidad,o.Descripcion,    o.idOjo,o.estante,o.modulo,o.idRecorrido, ABS("+ultimaUbicacion+" - o.idRecorrido) distancia, a.Imagen, a.packing  \r\n" + 
			"FROM tarea_movimiento_articulos tma\r\n" + 
			"INNER JOIN articulos a ON a.idArticulo = tma.idArticulo AND tma.idEmpresa = a.IdEmpresa\r\n" + 
			"LEFT OUTER JOIN\r\n" + 
			"(\r\n" + 
			"	SELECT IFNULL(artBU.idArticulo,artAR.idArticulo) idBulto ,vis.idOjo, IFNULL( bc.idArticulo,  artAR.idArticulo) articulo , \r\n" + 
			"	CASE when bu.idBulto IS NULL then vis.Cantidad	ELSE bc.cantidad END cantidad	, vis.idRecorrido, vis.Descripcion, vis.estante, vis.modulo\r\n" + 
			"	FROM \r\n" + 
			"	vista_ojostienenarticulos vis \r\n" + 
			"	 LEFT OUTER JOIN articulos artBU ON artBU.idArticulo = vis.idArticulo AND vis.idempresa=artBU.IdEmpresa  AND artBU.IdTipo=4\r\n" + 
			"	 LEFT OUTER JOIN bulto bu ON bu.idBulto = artBU.idArticulo AND bu.idempresa=artBU.IdEmpresa\r\n" + 
			"	 LEFT OUTER JOIN bulto_contenido bc ON bc.idBulto = artBU.idArticulo AND bc.idempresa=artBU.IdEmpresa\r\n" + 
			"	 LEFT OUTER JOIN articulos artAR ON artAR.idArticulo = vis.idArticulo AND vis.idempresa=artAR.IdEmpresa  AND artAR.IdTipo=1\r\n" + 
			"	 WHERE vis.idEmpresa="+idEmpresa+"\r\n" + 
			"	 AND vis.almacenapallet=1\r\n" + 
			"\r\n" + 
			") o  ON o.articulo = tma.idArticulo\r\n" + 
			"WHERE tma.idTarea = "+idTarea+" AND tma.idEmpresa="+idEmpresa+" AND tma.terminado = 0\r\n" + 
			") AS t2 ON t1.idarticulo=t2.idarticulo AND t1.idtarea=t2.idtarea\r\n" + 
			"ORDER BY t2.distancia,t2.idojo";
	
	
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		resultado = econ.creoRepPicking(consulta);
	} catch(Exception e) {
		e.printStackTrace();
		return resultado;
	}
	return resultado;
}


public int getUltimaUbicacion(int idTarea,int idEmpresa) {
	int resultado = 0;
	String consulta = "SELECT tmad.idojo,o.idRecorrido FROM tarea_movimiento_articulos_detalle tmad"
			+ "INNER JOIN ojos o on tmad.idojo=o.idOjo AND tmad.idEmpresa=o.IdEmpresa"
			+ "WHERE tmad.idempresa="+idEmpresa+" and tmad.idtarea="+idTarea
			+ "ORDER BY tmad.fecha DESC LIMIT 1";
	try 
	{
		
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		resultado = econ.ultimaUbicacion(consulta);
		

	} catch (Exception e) {
		
		e.printStackTrace();
		return resultado;
	}
	return resultado;
}




public  List<ArticuloLineaReposicion> darListaLineasRepo(String deposito,String marca, String clase, String seccion, boolean central, boolean mayorista, String genero, String categoria, String fechaI, String fechaF,String tope,String soloPickup,String prioridad, String distribucion, int idEmpresa, int idDepoOrigen, String canales,  String couriers, int facturados, String idTipoRepo, String zonas) 
{
	
	
	String depositoIN = "";
	String distribucionIN = "";
	String marcaIN="";
	String claseIN="";
	String seccionIN="";
	String categoriaIN="";
	String generoIN="";
	String origen = "";
	String mayo = "";
	String fechaIN = "";
	String ob = "";
	String limit = "";
	String spickup = "";
	String sprioridad="";
	String canal = "";
	String destinosIN = "";
	String couriersIn = "";
	String tipoRepo = " AND RA.idTipo =" + idTipoRepo; //cree el tipo, con el que recibo por parametro
	
	String lfzonas = "";
	if(zonas!=null && !zonas.equals(""))
	{
		lfzonas = " LEFT OUTER JOIN zonastienenestanterias ZO ON ZO.idEstanteria=ES.idEstanteria AND ZO.idEmpresa=ES.idEmpresa ";
	}
	
	String consulta = " select IdSincronizacion, if(ISNULL(p.STAMPTIME),RS.Fecha,P.STAMPTIME),RA.idArticulo,Origen, DOr.Nombre,Destino,DDe.Nombre, "
			+ "RA.Cantidad-RA.Picked,Justificacion , IF(RA.Mayorista=1,RA.Seccion,0)  ped,  "
			+ " if(ISNULL (CONCAT(t0.idOjo)),'Sin Asignar', CONCAT(t0.Descripcion,' M',t0.idEstante,' E',t0.idModulo,' ')) ubicacion, "
			+ "RA.idpicking,IF(ISNULL(da.descripcion),'',da.descripcion),RA.idtipo,RA.idSolicitudTraslado" +
	" FROM "
	+ " reposicion_articulos  RA "
	+ " LEFT OUTER " + 
	" JOIN  " + 
	" ( " + 
	" SELECT OTA.idArticulo, OJO.idOjo,ojo.idEstante,ojo.idModulo, ES.*,ZO.idZona from ojostienenarticulos OTA " + 
	" INNER  " + 
	" JOIN ojos OJO ON OJO.idOjo= OTA.idOjo AND OJO.idEmpresa= OTA.idEmpresa AND OTA.idOjo!='1'  " + 
	" INNER   " + 
	" JOIN estanterias ES ON ES.idEstanteria=OJO.idEstanteria AND ES.idEmpresa=OJO.idEmpresa AND ES.AlmacenaPallet=0 " + 
	" LEFT OUTER JOIN zonastienenestanterias ZO ON ZO.idEstanteria=ES.idEstanteria AND ZO.idEmpresa=ES.idEmpresa " + 
	"  " + 
	" ) t0 " + 
	"ON  t0.idArticulo = RA.idArticulo AND t0.idEmpresa = RA.idEmpresa "
	+ " LEFT OUTER JOIN ecommerce_pedido p on p.idpedido=RA.seccion AND p.idEmpresa=RA.idEmpresa "
	+ " LEFT OUTER JOIN ecommerce_pedido_factura epf on p.idpedido=epf.idpedido AND p.idEmpresa=epf.idEmpresa "
	+ " LEFT OUTER JOIN ecommerce_pedido_destino epd ON epd.idpedido=RA.seccion AND epd.idEmpresa=RA.idEmpresa "
	+ " LEFT OUTER JOIN art_descripcion da on da.id=RA.idArticulo AND da.idEmpresa=RA.idEmpresa "
	+ ", reposicion_sincronizacion RS "
	+ ", depositos DOr "
	+ ", depositos DDe "+
	" where  "
	+ "    RA.IdSincronizacion = RS.Id AND RA.idEmpresa = RS.idEmpresa "
	+ " AND RA.Origen = DOr.idDeposito AND RA.idEmpresa = DOr.idEmpresa "
	+ " AND DDe.idDeposito = RA.Destino AND DDe.idEmpresa = RA.idEmpresa" 
	+ " AND RA.idEmpresa = "+idEmpresa+" AND RA.Cantidad-RA.remitidas >0 AND RA.Estado in (1,11) ";
	
	if(!distribucion.equals(""))
	{
		distribucionIN = " AND RA.IdSolicitudTraslado in("+distribucion+") ";
	}
	
	origen = " AND Origen = "+idDepoOrigen+" ";
	
	if(!deposito.equals(""))
	{
		depositoIN = " AND RA.Destino IN ("+deposito+") ";
	}
	
	if(!marca.equals(""))
	{
		marcaIN = " AND RA.Marca in("+marca+")";
	}
	if(!clase.equals(""))
	{
		claseIN = " AND RA.Clase in ("+clase+")";
	}
	if(!seccion.equals(""))
	{
		seccionIN = " AND RA.Seccion in ("+seccion+")";
	}
	
	if(mayorista)
	{
		mayo = " AND Mayorista = 1 ";
	}
	else
	{
		mayo = " AND Mayorista = 0 ";
	}
	
	if(!categoria.equals(""))
	{
		categoriaIN = " AND RA.IdCategoria in("+categoria+") ";
	}
	if(!genero.equals(""))
	{
		generoIN = " AND RA.IdGenero in("+genero+") ";
	}
	
	
	if(soloPickup!= null && !soloPickup.equals(""))
	{
		spickup=" AND RA.Seccion IN (SELECT idPedido FROM ecommerce_pedido_destino WHERE idEmpresa="+idEmpresa+" AND idDestino < 100000) ";
	}
	
	if(prioridad!= null && !prioridad.equals(""))
	{
		sprioridad=" AND RA.prioridad = 1 ";
	}

	if(!fechaI.equals("")&&!fechaF.equals(""))
	{
		if(idTipoRepo.equals("2")){
			fechaIN = " and (p.stamptime BETWEEN '"+fechaI+"' AND '"+fechaF+"')";
		}
		else{
			fechaIN = " and (rs.fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"')";
		}
		
	}
	else
	{
		if(idTipoRepo.equals("2")){
			fechaIN = " and (p.stamptime > current_timestamp - INTERVAL '1' MONTH)";
		}
		else{
			fechaIN = " and (rs.fecha > current_timestamp - INTERVAL '1' MONTH)";
		}
	}
	
	if(couriers!=null && !couriers.equals(""))
	{
		couriersIn = " AND RA.Seccion IN (SELECT D.idPedido FROM ecommerce_pedido_destino D INNER JOIN ecommerce_pedido P ON D.idPedido = P.idPedido AND P.IdEmpresa = D.IdEmpresa WHERE D.idDestino IN ("+couriers+") AND P.EstadoEncuentra=1 AND P.IdEmpresa = "+idEmpresa+") ";
	}
	String facturaIn = "";
	if(facturados==1)
	{
		facturaIn = " AND p.idpedido=epf.idpedido ";
	}
	
	String gb = " GROUP BY IdSincronizacion, RS.Fecha ,RA.idArticulo,Origen, DOr.Nombre,Destino,DDe.Nombre, RA.Cantidad-RA.Picked,Justificacion , ped,RA.idpicking ";
	
	
	String depositoEcommerce = darParametroEmpresa(idEmpresa, 5);
	
	if(deposito.equals(depositoEcommerce))
	{	
			ob = " ORDER BY ped";	
	}
	else{
		ob = " ORDER BY Fecha";
	}
	
	if(!tope.equals("")){
		limit = " limit "+tope;
	}
	
	String justi = "";
	String pCancelados = "";
	if(!idTipoRepo.equals("2")){
		String on = darParametroEmpresa(idEmpresa, 18);
		if (!on.equals("1"))
			justi = " and RA.justificacion NOT LIKE '%no encontrado%' ";
	}
	else {
		String on = darParametroEmpresa(idEmpresa, 17);
		if (!on.equals("1")) {
			justi = " and RA.justificacion NOT LIKE '%no encontrado%' ";
		}
		pCancelados = " and p.estadoencuentra!=99 ";
	}
	
	if(canales!=null && !canales.equals("")){
		canal = " AND p.idCanalML IN ("+canales+") ";
	}
	
	String zonasIN = "";
	
	if(zonas!=null && !zonas.equals(""))
	{
		zonasIN = " AND t0.idZona IN ("+zonas+") ";
	}
		
	//agregue tipo repo a la consulta
	List<ArticuloLineaReposicion> retorno = null;
	consulta+=tipoRepo+zonasIN+depositoIN+seccionIN+claseIN+marcaIN+origen+mayo+generoIN+categoriaIN+spickup+sprioridad+fechaIN+distribucionIN+justi+canal+destinosIN+couriersIn+facturaIn+pCancelados+gb+ob+limit;
	
	System.out.println("CONSULTA ARTICULOS A REPONER: "+consulta);
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		System.out.println(consulta);
		retorno = econ.darListaArticulosRepo(consulta);
		

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;

	
}


public  List<ArticuloReposicion> darListaArticulosToDist(String artBase, int idEmpersa) 
{
	
	
	String consulta = "Select top 1 AR.IdArticulo, AR.IdMarca, AR.IdSeccion, AR.IdClase,99,1 as Cantidad, 	0 as stkcentral, 0 as stklocal, 0   as precio , AR.IdGenero, AR.idCategoria,AR.IdTemporada "+  	
						"	From "
						+ " Articulo AR "
						+ " INNER JOIN ArtMarca AM ON AR.IdMarca = AM.IdMarca AND  AR.idEmpersa = AM.idEmpersa "
						+ " INNER JOIN ArtSeccion ASEC ON ASEC.IdSeccion = AR.IdSeccion AND ASEC.idEmpersa = AR.idEmpersa "+
						"	where AR.idEmpersa="+idEmpersa+" AND AR.IdSeccion !=0 	AND  SUBSTRING(AR.IdArticulo,0,14) = '"+artBase+"' 	 "+
						"	group by AR.IdArticulo, AR.IdMarca, AR.IdSeccion, AR.IdClase, AR.IdGenero,AR.idCategoria,AR.IdTemporada ";
	
	
	System.out.println(consulta);
	int intentos = 0;
	List<ArticuloReposicion> lista = MSSQL.darListaVLReposicion(consulta, 0, 0,idEmpersa);
	
	
	while(lista.isEmpty()&& intentos<4)
	{
		intentos++;
		lista = MSSQL.darListaVLReposicion(consulta, 0, 0,idEmpersa);
	}
	
	
	
	
	return lista;

	
}


public  Hashtable<String, ArticuloMatrizHTML> darArticulosDistII(String articulo, boolean analizar, Integer depoOrigen, Distribuidor d, Hashtable<String, List<ArticuloReposicion>> basesHT,int idEmpresa)
{
	List<DataIDDescripcion> lista = null;
	if(basesHT==null)
	{
		 lista = darListaDataIdDescripcionConsul("select distinct 0, SUBSTRING(IdArticulo,0,14) from Articulo where IdArticulo  like '"+articulo+"%' and SUBSTRING(IdArticulo,10,4)!='0098'",idEmpresa);
	}
	else
	{
		lista = new ArrayList<>();
		for (ArticuloReposicion s : basesHT.get(articulo)) 
		{
			lista.add(new DataIDDescripcion(0,s.getArticuloBaseColor()));
		}
	}
	Hashtable<String, ArticuloMatrizHTML> hashMaster;
	if(basesHT!=null)
	{
		hashMaster = d.AddArtProcesador(lista, analizar, depoOrigen,basesHT.get(articulo),idEmpresa);
	}
	else
	{
		hashMaster = d.AddArtProcesador(lista, analizar, depoOrigen,null,idEmpresa);
	}
	
			
	
	return hashMaster;
}

public  Hashtable<String, Integer> DarStocksReservadosRepo(int idEmpresa) 
{
	Hashtable<String, Integer> retorno = null;
	
	String consulta = "select RA.idArticulo,RA.Origen, (RA.Cantidad) from reposicion_articulos RA where RA.Estado = 1 AND idEmpresa="+idEmpresa+" GROUP BY RA.idArticulo, RA.Origen";
	
	
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		retorno = econ.darStockArticulosRepo(consulta);
		

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;
}

public  Hashtable<String, Integer> DarStocksEnviadosRepo(int idEmpresa) 
{
	Hashtable<String, Integer> retorno = null;
	
	String consulta = "select RA.idArticulo,RA.Destino, SUM(RA.Cantidad-RA.remitidas) from reposicion_articulos RA "
			+ "where RA.Estado != 7 and RA.cantidad!=RA.remitidas AND RA.idsincronizacion > 20000 AND RA.IdEmpresa ="+idEmpresa+
			" GROUP BY RA.idArticulo, RA.Destino";	
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		retorno = econ.darStockArticulosRepo(consulta);
		

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;
}




public  void encuentraAltaCantRecep(List<DataIDDescripcion> articulosCant, int idUsuario, int idRecep, int idE) 
{
	

	try 
	{
		
		
		
		for (DataIDDescripcion d :articulosCant) 
		{
			eper.persistir("INSERT INTO `barrasrecepcion` (`idBarra`, `cantidad`, `idUsuario`, `idRecepcion`,idEmpresa) VALUES ('"+d.getDescripcion()+"', "+d.getId()+", "+idUsuario+", "+idRecep+","+idE+") " +
					" ON DUPLICATE KEY UPDATE cantidad = cantidad+"+d.getId()+", idUsuario = "+idUsuario+";") ;
		}
		
		
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
	
}



public  void encuentraAltaCantVerifII(int verif,int idPicking,String articulo,int destino, int pedido,int doc, int idEmpresa) 
{
	try 
	{
		String qu ="UPDATE `reposicion_articulos` SET `Verif`=`Verif`+"+verif+" WHERE idEmpresa="+idEmpresa+" AND idPicking = "+idPicking+" AND `idArticulo`='"+articulo+"' AND `Destino`="+destino+" and idsolicitudTraslado="+doc+" ";
		if(destino==1200){
			qu += " and seccion= "+pedido;
		}
		
		eper.persistir(qu);
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();		
	}
}



public  List<DataIDDescDescripcion> encuentraDarArticulosContadosRec(int idRece, int idEmpresa) 
{
	List<DataIDDescDescripcion> retorno = null;
	String consulta ="select AB.idArticulo AS ART, BR.idBarra AS BARRA,SUM(BR.cantidad) AS CANT,BR.idUsuario AS USU , AC.Descripcion from barrasrecepcion BR "
			+ "LEFT OUTER JOIN artbarra AB ON AB.Barra = BR.idBarra AND BR.IdEmpresa=AB.IdEmpresa "
			+ "LEFT OUTER JOIN  art_colors AC ON AC.Id =  SUBSTRING(AB.idArticulo,8,3) AND AC.IdEmpresa=BR.IdEmpresa "
			+ "WHERE BR.idEmpresa="+idEmpresa+" and BR.idRecepcion = "+idRece+"  GROUP BY BR.idBarra"; 
	
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		retorno = econ.darArtsRecep(consulta);
		

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;
}
public  List<DataIDDescDescripcion> encuentraDarArticulosnoContadosRec(int idRece, int idEmpresa) 
{
	List<DataIDDescDescripcion> retorno = null;
	String consulta ="select ARR.idArticulo AS ART, AB.Barra AS BARRA,SUM(ARR.cantidadRecibida) AS CANT,0 AS USU , AC.Descripcion from articulosarecepcionar ARR INNER JOIN detallerecepcion DR ON DR.idDetalle = ARR.idDetalle LEFT OUTER JOIN artbarra AB ON AB.idArticulo = ARR.idArticulo LEFT OUTER JOIN  art_colors AC ON AC.Id =  CAST(SUBSTRING(ARR.idArticulo,10,4) AS UNSIGNED) WHERE ARR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+idRece+"  GROUP BY AB.Barra"; 
	
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		retorno = econ.darArtsRecep(consulta);
		
		
		

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;
}

public  List<DataIDDescDescripcion> darArticulosCantRecep(int idRec, int idEmpresa) 
{

	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		String consulta = "SELECT SUM(ar.cantidadPedida),0, ar.idArticulo as articulo,0 from detallerecepcion dr,articulosarecepcionar ar WHERE dr.idRecepcion="+idRec+" and dr.idDetalle=ar.idDetalle AND dr.idEmpresa="+idEmpresa+" GROUP BY  ar.idArticulo order by ar.idArticulo asc";

		return econ.darListaDDRecep(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}
public List<DataDescDescripcion> darBarrasARecepcionar(int idEmpresa, int idRecepcion)
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		String consulta = "SELECT AR.idArticulo,  AB.Barra FROM articulosarecepcionar AR "
				+ "INNER JOIN detallerecepcion DR ON AR.idDetalle = DR.idDetalle AND DR.IdEmpresa = AR.IdEmpresa "
				+ "INNER JOIN recepcion RE ON RE.idRecepcion = DR.idRecepcion AND RE.IdEmpresa = DR.IdEmpresa "
				+ "LEFT OUTER JOIN artbarra AB ON AB.idArticulo = AR.idArticulo AND AB.IdEmpresa = AR.IdEmpresa "
				+ "WHERE AR.cantidadContada<AR.cantidadPedida "+
				" AND AB.IdEmpresa = "+idEmpresa+" AND RE.idRecepcion = "+idRecepcion+" "+
				"UNION ALL "+
				"SELECT b.idBulto, b.idBulto FROM bulto b "+
				"INNER JOIN bulto_contenido bc ON b.idBulto=bc.idBulto AND b.IdEmpresa=bc.IdEmpresa "+
				"INNER JOIN articulosarecepcionar AR ON bc.idArticulo = AR.idArticulo AND bc.IdEmpresa=AR.IdEmpresa "+
				"INNER JOIN detallerecepcion DR ON AR.idDetalle = DR.idDetalle "+
				"AND DR.IdEmpresa = AR.IdEmpresa AND DR.idRecepcion = bc.recepcion "+
				"INNER JOIN recepcion RE ON RE.idRecepcion = DR.idRecepcion "+
				"AND RE.IdEmpresa = DR.IdEmpresa " +
				"WHERE AR.cantidadContada<AR.cantidadPedida "+
				"AND RE.IdEmpresa = "+idEmpresa+" AND RE.idRecepcion = "+idRecepcion+" ";

		return econ.darListaDataDescDescripcion(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  void encuentraContarNuevamente(int recep, String barra, int idEmpresa) 
{
	
	String consulta = "";
	String consulta2 = "";
	
	if(barra.equals(""))
	{
		consulta = "UPDATE `barrasrecepcion` SET `cantidad`=0 WHERE idEmpresa="+idEmpresa+" AND `idRecepcion`="+recep+";";
		consulta2 = "UPDATE articulosarecepcionar AR JOIN (SELECT AR.idDetalle, AR.idArticulo, AR.idEmpresa  FROM detallerecepcion DR INNER JOIN articulosarecepcionar AR ON DR.idDetalle = AR.idDetalle "+ 
				"	WHERE idrecepcion = "+recep+" AND AR.IdEmpresa = "+idEmpresa+" "+
				"	AND DR.IdEmpresa=AR.IdEmpresa) T ON AR.idDetalle = T.idDetalle AND AR.idArticulo = T.idArticulo AND AR.IdEmpresa = T.idEmpresa SET cantidadContada = 0 "+
				"	WHERE AR.IdEmpresa = "+idEmpresa+"";
		
		
	}
	else
	{
		consulta = "UPDATE `barrasrecepcion` SET `cantidad`=0 WHERE idEmpresa="+idEmpresa+" AND `idBarra`='"+barra+"' AND `idRecepcion`="+recep+";";
		
		consulta2 = "UPDATE articulosarecepcionar AR JOIN (SELECT AR.idDetalle, AR.idArticulo, AR.idEmpresa  FROM detallerecepcion DR INNER JOIN articulosarecepcionar AR ON DR.idDetalle = AR.idDetalle "+ 
					"	WHERE idrecepcion = "+recep+" AND AR.IdEmpresa = "+idEmpresa+" "+
					"	AND DR.IdEmpresa=AR.IdEmpresa) T ON AR.idDetalle = T.idDetalle AND AR.idArticulo = T.idArticulo AND AR.IdEmpresa = T.idEmpresa SET cantidadContada = 0 "+
					"	WHERE AR.idArticulo = '"+barra+"' AND AR.IdEmpresa = "+idEmpresa+"";
	
	}
	
	try 
	{
		
		
		String update2="update detallerecepcion DR "+ 	
				"	 join (select sum(ar.CantidadContada) as contadas, DR.idRecepcion from articulosarecepcionar ar,  detallerecepcion DR where DR.idEmpresa=AR.idEmpresa AND  DR.idDetalle = ar.idDetalle and DR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+recep+" ) T "+
				"	 ON T.idRecepcion = DR.idRecepcion "+
				"	 SET DR.cantidadRecepcionada = T.contadas, "+
				"	 DR.CantidadContada = T.contadas "+
				"	 where DR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+recep+"";
		
		String update3="update recepcion r set r.cantidadRecibida="
				+ "			("
				+ "				select sum(ar.CantidadContada) as contadas "
				+ "				from articulosarecepcionar ar , detallerecepcion DR "
				+ "				where  DR.idEmpresa=AR.idEmpresa AND DR.idDetalle = ar.idDetalle AND DR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+recep+""
						+ ") ,"
						+ "r.cantidadContada="
				+ "			("
				+ "				select sum(ar.CantidadContada) as contadas "
				+ "				from articulosarecepcionar ar , detallerecepcion DR "
				+ "				where  DR.idEmpresa=AR.idEmpresa AND DR.idDetalle = ar.idDetalle AND DR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+recep+""
						+ ")"
						+ "WHERE r.idEmpresa="+idEmpresa+" AND r.idRecepcion = "+recep+"";
		
		eper.persistir(consulta);
		eper.persistir(consulta2);
		
		eper.persistir(update2);
		eper.persistir(update3);
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
}





public  List<DataIDDescripcion> darListaDataIdDescripcionConsulMYSQL(String query) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		List<DataIDDescripcion> retorno = econ.darListaDataIdDescripcion(query);
		
		retorno.remove(0);
		return retorno; 
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return new ArrayList<>();
	}

}

private  List<DataIDDescripcion> darListaDataIdDescripcionBConsulMYSQL(String query) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		List<DataIDDescripcion> retorno = econ.darListaDataIdDescripcionDB(query);
		
		
		return retorno; 
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return new ArrayList<>();
	}

	

	
}

public  void updateEstadoRecepFromTar(int idTar, int estado, int idE) 
{

	try 
	{
		@SuppressWarnings("unused")
		Connection con=econ.getConnection();
		String consulta="Select idRecepcion from recepcion where idEmpresa="+idE+" AND tareaMain="+idTar;
		int idRecepcion=econ.getIdRecepcion(consulta);
		boolean ok=ActualizarEstadoRecepcion(idRecepcion, estado,idE);
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
}


public  List<DataArticuloCantBarra> encuentraDarListaArticulosRecepcionados(int idRecep, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		String consulta = "select AR.idArticulo,AR.cantidadRecibida-AR.cantidadFacturada,AR.cantidadFacturada,AR.pendientesFacturar, DR.idDocumento,AR.idLineaOC,DR.Folio from articulosarecepcionar AR, detallerecepcion DR where DR.idDetalle = AR.idDetalle  AND AR.cantidadRecibida-AR.cantidadFacturada>0 AND DR.IdEmpresa="+idEmpresa+" AND DR.idRecepcion = "+idRecep;

		return econ.darListaArtRecepcionados(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}


public  List<Tarjeta> darTarjetas(String idGrupo, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		String consulta = "SELECT distinct T.idTarjeta,T.tipo,T.cantidad,T.porcentaje,T.titulo,T.icon,T.bgcolor,T.href,T.texto,T.fcolor,T.idempresa,T.decimales, T.padre "
				+ "FROM tarjetas T "
				+ "INNER JOIN tarjetas_grupos TG ON TG.idTarjeta = T.idTarjeta AND TG.IdEmpresa = T.idEmpresa "
				+ "WHERE T.IdEmpresa="+idEmpresa+" AND TG.idGrupo IN ("+idGrupo+") AND T.cantidad > 0 OR (T.Tipo=2 AND T.idEmpresa = "+idEmpresa+") order by T.tipo desc";
		return econ.darListaTarjetas(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  List<DataIDDescripcion> darTarjetaEstadisticasVenta(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		String consulta = "SELECT SUBSTRING(pe.fecha,6,10), pe.ped,ifnull(pp.proces,0) AS proc FROM (\r\n" + 
				"	SELECT SUBSTRING(stamptime,1,10) fecha,COUNT(idpedido) ped,idempresa emp\r\n" + 
				"	FROM ecommerce_pedido \r\n" + 
				"	WHERE DATEDIFF(CURRENT_TIMESTAMP(),stampTime)<=14 AND stamptime> CURRENT_TIMESTAMP() - INTERVAL '1' MONTH AND idEmpresa = "+ idEmpresa +"\r\n" + 
				"	GROUP BY SUBSTRING(stamptime,1,10),idempresa \r\n" + 
				") AS pe\r\n" + 
				"LEFT OUTER JOIN \r\n" + 
				"(SELECT SUBSTRING(t.prom,1,10) fecha,COUNT(t.ped) proces,t.emp FROM (\r\n" + 
				"		SELECT p.idPedido ped,p.stampTime fec, p.IdEmpresa emp,r.PTimeStamp pro,MAX(r.PTimeStamp) prom FROM ecommerce_pedido p\r\n" + 
				"		INNER JOIN ecommerce_pedido_articulos_req r \r\n" + 
				"		ON r.idPedido=p.idPedido AND r.IdEmpresa=p.IdEmpresa\r\n" + 
				"		WHERE p.EstadoEncuentra NOT IN (1,2,99) AND r.CantidadProcesasa=r.CantidadRequerida\r\n" + 
				"		AND DATEDIFF(CURRENT_TIMESTAMP(),r.PTimeStamp)<=14 AND r.PTimeStamp> CURRENT_TIMESTAMP() - INTERVAL '1' MONTH AND p.IdEmpresa = "+ idEmpresa +"\r\n" + 
				"		GROUP BY p.idPedido\r\n" + 
				"	) AS t	\r\n" + 
				"	GROUP BY SUBSTRING(t.prom,1,10),t.emp ORDER BY t.emp,SUBSTRING(t.prom,1,10)\r\n" + 
				") AS pp\r\n" + 
				"ON pe.fecha=pp.fecha AND pe.emp=pp.emp\r\n" + 
				"ORDER BY pe.emp,pe.fecha;";

		return econ.darTarjetaEstadisticasVenta(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  String darTotalizadores(int idEmpresa, String estados, String cantDias) 
{
	@SuppressWarnings("unused") Connection cone;
	
	HashMap<String, Integer> hashMT = new HashMap<String, Integer>();
	List<DataIDDescripcion> estadosParaMatriz = new ArrayList<>();
	
	
	DataIDDescripcion aux = new DataIDDescripcion(29,"Sin Facturar");estadosParaMatriz.add(aux);
	aux = new DataIDDescripcion(1,"Items Pedidos");estadosParaMatriz.add(aux);
	aux = new DataIDDescripcion(2,"Items Confirmados");estadosParaMatriz.add(aux);
	aux = new DataIDDescripcion(3,"Items Clasificados (Orden Liberada)");estadosParaMatriz.add(aux);
	aux = new DataIDDescripcion(25,"Items Clasificados (FALTA ETIQUETA)");estadosParaMatriz.add(aux);
	aux = new DataIDDescripcion(34,"Pedido en envio pronto para despacho");estadosParaMatriz.add(aux);
	aux = new DataIDDescripcion(4,"Pedido Despachado");estadosParaMatriz.add(aux);
	aux = new DataIDDescripcion(6,"Pedido entregado a cliente");estadosParaMatriz.add(aux);
	aux = new DataIDDescripcion(99,"Cancelados");estadosParaMatriz.add(aux);
	
	
	List<DataIDDescripcion> couriersParaMatriz = darCouriersDashboardMatriz(idEmpresa,estados,cantDias);
	
	try {
		cone = econ.getConnection();

		String consulta = " SELECT ep.EstadoEncuentra, epd.idDestino, COUNT(ep.idPedido)" + 
				" FROM ecommerce_pedido ep" + 
				" INNER JOIN ecommerce_pedido_destino epd ON epd.idPedido=ep.idPedido AND epd.IdEmpresa=ep.idEmpresa" + 
				" WHERE ep.IdEmpresa= " + idEmpresa + " AND ep.EstadoEncuentra IN (" + estados + ")" +
				" AND DATEDIFF(CURRENT_TIMESTAMP(),ep.stampTime)<=" + cantDias +
				" GROUP BY epd.idDestino, ep.EstadoEncuentra" +
				" UNION ALL" + 
				" SELECT 29, epd.idDestino, COUNT(epd.idPedido)" + 
				" FROM ecommerce_pedido ep" + 
				" INNER JOIN ecommerce_pedido_destino epd ON epd.idPedido=ep.idPedido AND epd.IdEmpresa=ep.idEmpresa" + 
				" LEFT OUTER JOIN ecommerce_pedido_factura epf ON epf.idPedido=ep.idPedido AND epf.IdEmpresa=ep.idEmpresa" + 
				" WHERE ep.IdEmpresa= "+ idEmpresa +" AND epf.idPedido IS NULL" + 
				" AND DATEDIFF(CURRENT_TIMESTAMP(),ep.stampTime)<=" + cantDias +
				" AND ep.EstadoEncuentra IN (1)" +
				" GROUP BY epd.idDestino";
		
		hashMT = econ.darTotalizadores(consulta);
		List<DataIDDescripcion> destinosIgnorar = new ArrayList<>();
		List<DataIDDescripcion> estadosIgnorar = new ArrayList<>();
		
		for (DataIDDescripcion me : estadosParaMatriz) {
			int totalXEstado = 0;
			
			for (DataIDDescripcion mc : couriersParaMatriz) {
				if(!hashMT.containsKey(me.getId()+","+mc.getId())) //Si no existe se agrega cantidad 0
					hashMT.put(me.getId()+","+mc.getId(),0);
					
				else
					totalXEstado += hashMT.get(me.getId()+","+mc.getId());				
			}
			hashMT.put(me.getId()+",-1", totalXEstado);
			if(totalXEstado == 0) {
				estadosIgnorar.add(me);
			}
		}
		
		for (DataIDDescripcion mc : couriersParaMatriz) {
			int totalXDestino = 0;
			for(DataIDDescripcion me : estadosParaMatriz) {
				totalXDestino += hashMT.get(me.getId()+","+mc.getId());				
			}
			if(totalXDestino==0) {
				destinosIgnorar.add(mc);
			}
			
		}
		
	    DataIDDescripcion data = new DataIDDescripcion(-1,"TOTAL");
	    couriersParaMatriz.add(data);
	    
	    if(idEmpresa==2)
	    	estadosParaMatriz.remove(0); // Sin factura
		
	    String tablaMatrizEstadoPedidos = " <div class=\"table-responsive\">\r\n" + 
	    		"							    <table class=\"table table-striped table-bordered table-hover\" id=\"encuentra-default-no-order\">\r\n" + 
	    		"							        <thead class=\"thead-dark\">\r\n" + 
	    		"                                        <tr>\r\n" +
	    		"											<th class=\"text-center\"> Estados </th>\r\n";
	    
	    		for (DataIDDescripcion mc : couriersParaMatriz) {
	    			boolean existe = false;
	    			for(DataIDDescripcion DI : destinosIgnorar) {
	    				if(mc.getId() == DI.getId()) {
	    					existe = true;
	    					break;
	    				}
	    			}
	    			
	    			if(!existe) {
	    				tablaMatrizEstadoPedidos+= "<th class=\"text-center\">"+ mc.getDescripcion() +"</th>\r\n";
	    			}
	    			
	    		}
	    		tablaMatrizEstadoPedidos +=
	    		"                                        </tr>\r\n" + 
	    		"                                    </thead>\r\n" + 
	    		"                                    <tbody>\r\n" +
	    		"	                                    	<tr class=\"gradeD\">	\r\n";
	    		for (DataIDDescripcion me : estadosParaMatriz) 
	    		{
	    			boolean existe = false;
	    			for(DataIDDescripcion EI : estadosIgnorar) {
	    				if(EI.getId() == me.getId()) {
	    					existe = true;
	    					break;
	    				}
	    			}
	    			
	    			if(!existe) {
	    				tablaMatrizEstadoPedidos +="														<td class=\"text-center\">"+ me.getDescripcion() +"</td>\r\n";
		    			for (DataIDDescripcion mc : couriersParaMatriz) 
		    			{
		    				Integer totalLinea = hashMT.get(me.getId()+","+mc.getId());
		    				String color="";
		    				if (totalLinea == 0) 
		    					color = " text-white";
		    				else
		    					color = " text-dark";
		    				tablaMatrizEstadoPedidos +="														<td class=\"text-center" + color + "\"> "+ totalLinea +" </td>\r\n";
		    			}
		    		
		    		tablaMatrizEstadoPedidos += "											</tr>\r\n";
	    			}
	    			
	    			
	    		}
	    		tablaMatrizEstadoPedidos +=
	    		"                                    </tbody>\r\n" + 
	    		"                                </table>";
		
		return tablaMatrizEstadoPedidos;

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  List<DataIDDescripcion> darCouriers(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	
	List<DataIDDescripcion> lista = new ArrayList<>();
	
	try {
		cone = econ.getConnection();

	   String consultaCourrriers = " SELECT ECE.idDeposito, ECE.nombre" + 
							   	" FROM ecommerce_envioml ECE" + 
							   	" INNER JOIN ecommerce_pedido_destino EPD ON ECE.idDeposito = EPD.idDestino AND ECE.IdEmpresa=EPD.IdEmpresa" + 
								" WHERE ECE.idEmpresa=" + idEmpresa + "  AND idDeposito != 0" +
						   		" GROUP BY ECE.idDeposito, ECE.nombre;";
	   
	   
	   lista = econ.darIdDescripcion(consultaCourrriers);
	   
	   /*
	   DataIDDescripcion data = new DataIDDescripcion(-1,"TOTAL");
	   lista.add(data);
	   */
		
	   return lista;

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  List<DataIDDescripcion> darCouriersDashboardMatriz(int idEmpresa, String estados, String cantDias) 
{
	@SuppressWarnings("unused") Connection cone;
	
	List<DataIDDescripcion> lista = new ArrayList<>();
	
	try {
		cone = econ.getConnection();

	   String consultaCourrriers = " SELECT ECE.idDeposito, ECE.nombre" + 
							   	" FROM ecommerce_envioml ECE" + 
							   	" INNER JOIN ecommerce_pedido_destino EPD ON ECE.idDeposito = EPD.idDestino AND ECE.IdEmpresa=EPD.IdEmpresa" + 
								" WHERE ECE.idEmpresa=" + idEmpresa + 
								" AND idDeposito IN (\r\n" + 
								"	SELECT DISTINCT(ST.destino)\r\n" + 
								"	FROM(\r\n" + 
								"	SELECT epd.idDestino destino\r\n" + 
								"	FROM ecommerce_pedido ep\r\n" + 
								"	INNER JOIN ecommerce_pedido_destino epd ON epd.idPedido=ep.idPedido AND epd.IdEmpresa=ep.idEmpresa\r\n" + 
								"	WHERE ep.IdEmpresa=" + idEmpresa +" AND ep.EstadoEncuentra IN ("+estados+") AND DATEDIFF(CURRENT_TIMESTAMP(),ep.stampTime)<=" + cantDias +"\r\n" + 
								"	GROUP BY epd.idDestino\r\n" + 
								"	UNION ALL\r\n" + 
								"	SELECT epd.idDestino destino\r\n" + 
								"	FROM ecommerce_pedido ep\r\n" + 
								"	INNER JOIN ecommerce_pedido_destino epd ON epd.idPedido=ep.idPedido AND epd.IdEmpresa=ep.idEmpresa\r\n" + 
								"	LEFT OUTER\r\n" + 
								"	JOIN ecommerce_pedido_factura epf ON epf.idPedido=ep.idPedido AND epf.IdEmpresa=ep.idEmpresa\r\n" + 
								"	WHERE ep.IdEmpresa=" + idEmpresa +" AND epf.idPedido IS NULL AND DATEDIFF(CURRENT_TIMESTAMP(),ep.stampTime)<=" + cantDias +" AND ep.EstadoEncuentra IN (1)\r\n" + 
								"	GROUP BY epd.idDestino\r\n" + 
								"	) ST\r\n" + 
								")" +
						   		" GROUP BY ECE.idDeposito, ECE.nombre;";
	   
	   
	   lista = econ.darIdDescripcion(consultaCourrriers);
	   
	   /*
	   DataIDDescripcion data = new DataIDDescripcion(-1,"TOTAL");
	   lista.add(data);
	   */
		
	   return lista;

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  List<Tarjeta> darTarjetasGuest(int idGrupo, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		String consulta = "SELECT T.* FROM tarjetas T, tarjetas_grupos TG WHERE TG.idTarjeta = T.idTarjeta AND TG.IdEmpresa="+idEmpresa+" AND TG.idGrupo ="+idGrupo+"";

		return econ.darListaTarjetas(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  Hashtable<String, DataDepositoSAP> encuentraDarDepositosSAP(int idEmpersa) 
{
	String consulta = "select DIR.Address, CONCAT(CM.CardCode,'-', CardName,ISNULL('-'+DIR.City,'')) CLiente, DIR.Street,CM.CardCode from OCRD  CM INNER JOIN CRD1 DIR ON CM.CardCode = DIR.CardCode AND DIR.AdresType = 'S' where CM.GroupCode = 118";
	return MSSQL.darDepositosSAP(consulta,idEmpersa);
}


public  int encuentraAltaUpdateDepo(DataDepositoSAP r, int idE) 
{
	try 
	{
		
		
		
		
		String direccion = "";
		try
		{
			direccion = r.getDireccion().replace("'","");
		}
		catch(Exception e)
		{
			direccion = "Sin direcci?n";
		}
		
		int retorno = eper.persistirDarId("INSERT INTO `depositos` ( `Nombre`, `Direccion`, `Tipo`, `Login`, `IdDepositoSAP`,idEmpresa) " +
												" VALUES ('"+r.getNombre().replace("'","")+"', '"+direccion+"', 100, 0, '"+r.getIdDepo().replace("'","")+"',"+idE+") on duplicate key update `Direccion` = '"+direccion+"'", r.getIdDepo().replace("'","")) ;
		
		String insertPadre = "INSERT INTO `depositospadre` (`idDeposito`, `padre`,idEmpresa) VALUES ('"+r.getIdDepo().replace("'","")+"', '"+r.getPadre().replace("'","")+"',"+idE+");"; 
		 
		
		eper.persistir(insertPadre);
		 return retorno;
		
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return 0;
		
	}
}


public  int encuentraAltaPicking(List<ArticuloLineaReposicion> articulosIn, int unidades, String filtros, int idE, boolean verifECauto, boolean paramRemision) 
{
int idPicking = 0;
int verificacion = 0;
int remision = 0;
	
	try 
	{
		if (verifECauto==true) {
			verificacion=1;
		}
		if(paramRemision) {
			remision =1;
		}
		
		idPicking = eper.persistirDarUltimo("INSERT INTO `picking` (`unidades`,Filtro,idEmpresa) VALUES ("+unidades+", '"+filtros+"',"+idE+");", "picking", "id",idE);
		
		String qry= "";
		String whEc = "";
		for (ArticuloLineaReposicion art : articulosIn) 
		{			
			whEc = "";
			if(art.isEcommerce()){
				whEc = " and seccion ="+art.getPedido();
			}			
			
			if(art.getIdPick()==0){
				qry += "UPDATE `reposicion_articulos` SET `idPicking`="+idPicking+",idPosSort = '"+art.getPosClasif()+"', autoVerificacion = "+verificacion+", Estado = 2, "
						+ "remision_bulto = "+remision+", idusuario=0 "
						+ "WHERE idEmpresa="+idE+" AND `IdSincronizacion`="+art.getSinc().getId()+" AND `idArticulo`='"+art.getIdArticulo()+"' "
						+ "AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+" and idSolicitudTraslado="+art.getSolicitud() 
						+" AND idpicking = 0 " + whEc+";";		
			}
			else{				
				qry += "INSERT INTO reposicion_articulos (IdSincronizacion,idArticulo,Origen,Destino,Cantidad,Estado,Justificacion,Mayorista,idpicking,idUsuario,IdSolicitudTraslado,IdEmpresa,remision_bulto,idTipo,seccion,idPosSort,autoVerificacion) (\r\n" + 
						" SELECT IdSincronizacion,idArticulo,Origen,Destino,cantidad-picked,2,Justificacion,Mayorista,"+idPicking+",0,IdSolicitudTraslado,IdEmpresa,"+remision+",idTipo,seccion,'"+art.getPosClasif()+"',"+verificacion+" \r\n" + 
						" FROM reposicion_articulos ra\r\n" + 
						" WHERE idEmpresa="+idE+" AND `IdSincronizacion`="+art.getSinc().getId()+" AND `idArticulo`='"+art.getIdArticulo()+"' "+
						" AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+" and idSolicitudTraslado="+art.getSolicitud() +
						" AND idpicking="+ art.getIdPick() + whEc+");";			
				
				qry += "UPDATE `reposicion_articulos` SET estado = 12 "+ 
						" WHERE idEmpresa="+idE+" AND `IdSincronizacion`="+art.getSinc().getId()+" AND `idArticulo`='"+art.getIdArticulo()+"' "+
						" AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+" and idSolicitudTraslado="+art.getSolicitud() +
						" AND idpicking="+ art.getIdPick() + whEc+";";
			}
		}	
		
		eper.persistir(qry);
		System.out.println(qry);
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();	
	}
	return idPicking;	
}


public  void encuentraUpdateEstadoArticulosPicking(List<DataLineaRepo> articulosIn, String idPick, int estado, 
		Hashtable<String, List<DataIDIDDescripcion>> reservas, int idEmpresa, boolean elevadoristas) 
{
	try 
	{		
		StringBuilder sb = new StringBuilder();
		List<DataIDDescripcion> list = null;
		 
		StringBuilder sb2 = new StringBuilder();
		Hashtable<String, List<DataIDIDDescripcion>>  ubicacionesArtuculoHT;
		
		if(reservas!= null){
			ubicacionesArtuculoHT = reservas;
			list = new ArrayList<>();
			for (DataLineaRepo art : articulosIn) 
			{
				DataIDDescripcion data = new DataIDDescripcion(art.getSolicitada(),art.getIdArticulo());
				data.setDescripcionB(art.getPedido()+"");
				data.setIdB(art.getIdDepDestino());
				list.add(data);
			}
		}else{
			for (DataLineaRepo art : articulosIn) 
			{
				try
				{
					 sb.append("UPDATE `reposicion_articulos` SET Estado = "+estado+" WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+idPick+" AND `idArticulo`='"+art.getIdArticulo()+"' "
					 		+ "AND `Origen`="+art.getIdDepOrigen()+" AND `Destino`="+art.getIdDepDestino()+" and idSolicitudTraslado="+art.getDocumento()+";");
				}
				catch(Exception e)
				{
					
				}	
				
			}
			eper.persistir(sb.toString());
			
			list = darListaDataIdDescripcionConsulMYSQL("select cantidad-picked,idarticulo,seccion,destino,idSolicitudTraslado from reposicion_articulos where idEmpresa="+idEmpresa+" AND idpicking="+idPick);
			
			StringBuilder innsSB = new StringBuilder();
			
			for (DataIDDescripcion da : list) 
			{
				innsSB.append("'"+da.getDescripcion()+"',");
			}
			
			String inns = innsSB.toString().substring(0, innsSB.toString().length()-1);
			ubicacionesArtuculoHT = encuentraDarSectoresHT(inns,idEmpresa, elevadoristas);
		}
		
		
		for (DataIDDescripcion da : list) 
		{
			try
			{
				int documento =Integer.parseInt(da.getDescripcionC());				
				List<DataIDIDDescripcion> ubicacionesArtuculo = ubicacionesArtuculoHT.get(da.getDescripcion());
				
				if (ubicacionesArtuculo!=null && !ubicacionesArtuculo.isEmpty()) 
				{
					// entro si hay ubicaciones para ese articulo
					int cantidadSol = da.getId();
					
					for (DataIDIDDescripcion ubi : ubicacionesArtuculo) 
					{
						
						if(ubi.getIid()>0)
						{							
						
							DataLineaRepo temp = new DataLineaRepo();
							//temp.setIdArticulo(da.getIdArticulo());
							temp.setIdArticulo(da.getDescripcion());
							temp.setPedido(Long.parseLong(da.getDescripcionB()));
							temp.setIdDepDestino(da.getIdB());
							temp.setDocumento(documento);
							
							try
							{
								if(cantidadSol>0)
								{
									
									//temp = da; // hago un linearepo temp
									int cantOjo = ubi.getIid(); // obtengo la cantidad en ese ojo
									
									
									if(cantidadSol<=cantOjo)
									{
										// si entra ac? quiere decir que de ese ojo se puede sacar todo lo que le piden de ese articulo
										temp.setSolicitada(cantidadSol);
										ubi.setIid(ubi.getIid()-cantidadSol);
										cantidadSol = 0;
										
										
									}
									else
									{
										temp.setSolicitada(cantOjo); // le pido que saque todo lo de ese ojo
										cantidadSol = cantidadSol-cantOjo; // bajo lo que saco del total solicitado
										ubi.setIid(0);
										
									}
										
									temp.setCubi(ubi.getDescripcion());
									temp.setRecorrido(ubi.getId());
									
									DataIDIDDescripcion estEstMod = DarEstanteria(temp.getCubi(),idEmpresa);
									System.out.println("");
									
									temp.setEstnteria(estEstMod.getId());
									temp.setEstnte(Integer.parseInt(estEstMod.getDescripcion()));
									temp.setModulo(estEstMod.getIid());
									
									/*
									 * hay que reservar la cantidad en ese ojo
									 * */
									
									//encuentraReservarUbicacion(temp.getIdArticulo(),temp.getCubi(),temp.getSolicitada());
									sb2.append(encuentraReservarUbicacionII(temp.getIdArticulo(),temp.getCubi(),temp.getSolicitada(),Integer.parseInt(idPick),temp.getIdDepDestino(),
											temp.getPedido(),true,idEmpresa,temp.getDocumento()));
									
									
																		
								}
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					if(cantidadSol>0){
						DataLineaRepo temp = new DataLineaRepo();
						//temp.setIdArticulo(da.getIdArticulo());
						temp = new DataLineaRepo();
						temp.setIdArticulo(da.getDescripcion());
						temp.setPedido(Long.parseLong(da.getDescripcionB()));
						temp.setIdDepDestino(da.getIdB());
						temp.setDocumento(documento);
						temp.setCubi("SIN ASIGNAR");
						temp.setSolicitada(cantidadSol);
						sb2.append(encuentraReservarUbicacionII(temp.getIdArticulo(),temp.getCubi(),temp.getSolicitada(),Integer.parseInt(idPick),temp.getIdDepDestino(),
								temp.getPedido(),false,idEmpresa,temp.getDocumento()));
						
					}
				} 
				else 
				{
					//no hay ubicaciones para ese articulo
					DataLineaRepo temp = new DataLineaRepo();
					temp = new DataLineaRepo();
					temp.setIdArticulo(da.getDescripcion());
					temp.setPedido(Long.parseLong(da.getDescripcionB()));
					temp.setIdDepDestino(da.getIdB());
					temp.setDocumento(documento);
					temp.setCubi("SIN ASIGNAR");
					temp.setSolicitada(da.getId());
					sb2.append(encuentraReservarUbicacionII(temp.getIdArticulo(),temp.getCubi(),temp.getSolicitada(),Integer.parseInt(idPick),temp.getIdDepDestino(),
							temp.getPedido(),false,idEmpresa,temp.getDocumento()));
					
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			
		}

		persistir(sb2.toString());
		//////////////////////////
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}

public  List<DataPicking> encuentraDarPickingWMS(int idPick, int idEmpresa) 
{

	@SuppressWarnings("unused") Connection cone;
	String consulta="SELECT RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, "+ 
			"RAR.idUsuario,USU.Nickname, RAR.Cantidad, RAR.Picked,RAR.Remitidas, RAR.IdPacking, "+
			"IF(GROUP_CONCAT(DISTINCT ABR.Barra) IS NULL,'SINCB',GROUP_CONCAT(DISTINCT ABR.Barra)) BARRAS, "+
			"RAR.Verif,idPosSort,Mayorista, RAR.Seccion as pedido ,RAR.idLineaSAP, AR.Descripcion,RAR.IdSolicitudTraslado, RAR.remision_bulto, TA.IdTarea "+
			"FROM reposicion_articulos RAR "+
			"left outer join artbarra ABR on ABR.idArticulo = RAR.idArticulo AND ABR.IdEmpresa=RAR.IdEmpresa "+
			"left outer join art_descripcion AR on AR.id=RAR.idArticulo AND AR.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN depositos DOR ON DOR.idDeposito=RAR.Origen AND DOR.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN depositos DES ON DES.idDeposito=RAR.Destino AND DES.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN usuarios USU ON USU.idUsuario=RAR.idUsuario AND USU.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN Tareas TA on TA.idDoc = RAR.idPicking "+
			"INNER JOIN empresa e ON e.idEmpresa=RAR.IdEmpresa "+
			"WHERE RAR.idPicking = "+idPick+
			" AND RAR.Remitidas < RAR.cantidad AND RAR.Estado not in (4,7) AND RAR.IdEmpresa= "+idEmpresa+
			" GROUP BY RAR.IdSincronizacion, RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario,RAR.IdSolicitudTraslado, RAR.Seccion ";
			
	try {
		cone = econ.getConnection();
		List<DataIDDescripcion> list= econ.darIdDescripcion("SELECT distinct destino,'' FROM reposicion_articulos WHERE mayorista=1 and idpicking="+idPick);	
		if(list.size()==1 && list.get(0).getId()==1200){
			consulta += ",RAR.Seccion ";
		}
		consulta += " ORDER BY RAR.picked desc";
	} catch (Exception e1) {		
		e1.printStackTrace();
	}
	
	try {
		cone = econ.getConnection();		
		//String consulta = "SELECT RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario,USU.Nickname, RAR.Cantidad, RAR.Picked,RAR.Remitidas, RAR.IdPacking, GROUP_CONCAT(DISTINCT ABR.Barra) BARRAS,RAR.Verif,idPosSort,Mayorista, RAR.Seccion as pedido ,RAR.idLineaSAP, AR.Descripcion FROM reposicion_articulos RAR, artbarra ABR, depositos DOR, depositos DES, usuarios USU, art_descripcion AR WHERE AR.id = RAR.idArticulo AND USU.idUsuario = RAR.idUsuario AND DOR.idDeposito = RAR.Origen AND DES.idDeposito = RAR.Destino AND ABR.idArticulo = RAR.idArticulo AND RAR.idPicking = "+idPick+" AND RAR.Estado!=4 GROUP BY RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario";
				
		return econ.darPicking(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  List<DataPicking> encuentraDarPicking(int idPick, int idEmpresa) 
{

	@SuppressWarnings("unused") Connection cone;
	String consulta="SELECT RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, "+ 
			"RAR.idUsuario,USU.Nickname, RAR.Cantidad, RAR.Picked,RAR.Remitidas, RAR.IdPacking, "+
			"IF(GROUP_CONCAT(DISTINCT ABR.Barra) IS NULL,'SINCB',GROUP_CONCAT(DISTINCT ABR.Barra)) BARRAS, "+
			"RAR.Verif,idPosSort,Mayorista, RAR.Seccion as pedido ,RAR.idLineaSAP, AR.Descripcion,RAR.IdSolicitudTraslado, RAR.remision_bulto "+
			"FROM reposicion_articulos RAR "+
			"left outer join artbarra ABR on ABR.idArticulo = RAR.idArticulo AND ABR.IdEmpresa=RAR.IdEmpresa "+
			"left outer join art_descripcion AR on AR.id=RAR.idArticulo AND AR.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN depositos DOR ON DOR.idDeposito=RAR.Origen AND DOR.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN depositos DES ON DES.idDeposito=RAR.Destino AND DES.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN usuarios USU ON USU.idUsuario=RAR.idUsuario AND USU.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN empresa e ON e.idEmpresa=RAR.IdEmpresa "+
			"WHERE RAR.idPicking = "+idPick+
			" AND RAR.Remitidas < RAR.cantidad AND RAR.Estado not in (4,7) AND RAR.IdEmpresa= "+idEmpresa+
			" GROUP BY RAR.IdSincronizacion, RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario,RAR.IdSolicitudTraslado ";
			
	try {
		cone = econ.getConnection();
		List<DataIDDescripcion> list= econ.darIdDescripcion("SELECT distinct destino,'' FROM reposicion_articulos WHERE mayorista=1 and idpicking="+idPick);	
		if(list.size()==1 && list.get(0).getId()==1200){
			consulta += ",RAR.Seccion ";
		}
		consulta += " ORDER BY RAR.picked desc";
	} catch (Exception e1) {		
		e1.printStackTrace();
	}
	
	try {
		cone = econ.getConnection();		
		//String consulta = "SELECT RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario,USU.Nickname, RAR.Cantidad, RAR.Picked,RAR.Remitidas, RAR.IdPacking, GROUP_CONCAT(DISTINCT ABR.Barra) BARRAS,RAR.Verif,idPosSort,Mayorista, RAR.Seccion as pedido ,RAR.idLineaSAP, AR.Descripcion FROM reposicion_articulos RAR, artbarra ABR, depositos DOR, depositos DES, usuarios USU, art_descripcion AR WHERE AR.id = RAR.idArticulo AND USU.idUsuario = RAR.idUsuario AND DOR.idDeposito = RAR.Origen AND DES.idDeposito = RAR.Destino AND ABR.idArticulo = RAR.idArticulo AND RAR.idPicking = "+idPick+" AND RAR.Estado!=4 GROUP BY RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario";
				
		return econ.darPicking(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  List<DataPicking> encuentraDarPickingCBulto(int idPick, int idEmpresa) 
{

	@SuppressWarnings("unused") Connection cone;
	String consulta="SELECT RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, "+ 
			"RAR.idUsuario,USU.Nickname, RAR.Cantidad, RAR.Picked,RAR.Remitidas, RAR.IdPacking, "+
			"IF(GROUP_CONCAT(DISTINCT ABR.Barra) IS NULL,'SINCB',GROUP_CONCAT(DISTINCT ABR.Barra)) BARRAS, "+
			"RAR.Verif,idPosSort,Mayorista, RAR.Seccion as pedido ,RAR.idLineaSAP, AR.Descripcion,RAR.IdSolicitudTraslado, RAR.remision_bulto,"
			+ "rao.idBulto AS idb, rarr.cant, rao.cantidad, "
			+ " '', "//agrego esto para que los field me queden en el mismo orden
			//" --  ifnull(ota.idOjo,''), "//comento esto en el Query y baja 3 segundos la consulta
			+ "  rarr.destinos,  RAR.Justificacion, AR.Packing, AR.Imagen "+
			"FROM reposicion_articulos RAR "+
			"LEFT OUTER "+
			"JOIN artbarra ABR ON ABR.idArticulo = RAR.idArticulo AND ABR.IdEmpresa=RAR.IdEmpresa "+
			"LEFT OUTER "+
			"JOIN articulos AR ON AR.idArticulo=RAR.idArticulo AND AR.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN depositos DOR ON DOR.idDeposito=RAR.Origen AND DOR.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN depositos DES ON DES.idDeposito=RAR.Destino AND DES.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN usuarios USU ON USU.idUsuario=RAR.idUsuario AND USU.IdEmpresa=RAR.IdEmpresa "+
			"INNER JOIN empresa e ON e.idEmpresa=RAR.IdEmpresa "+
			"INNER JOIN reposicion_articulos_ojos rao ON rao.idpicking = RAR.idpicking AND rao.idempresa = RAR.idempresa AND rao.idarticulo = RAR.idarticulo AND rao.solicitud = RAR.IdSolicitudTraslado AND rao.destino = RAR.destino AND rao.pedido = RAR.seccion "+
			"LEFT outer JOIN ( "+
			"SELECT idbulto, COUNT(idbulto) cant, idpicking,idempresa,if(idbulto='',1,COUNT( distinct destino)) destinos "+
			"FROM reposicion_articulos_ojos group by idbulto,idpicking) "+
			"rarr ON rao.idpicking = RARr.idpicking AND rao.idempresa = RARr.idempresa AND rao.idbulto = RArR.idbulto "+
			//" --  LEFT OUTER JOIN ojostienenarticulos ota ON ota.idArticulo=rao.idBulto AND ota.IdEmpresa=rao.IdEmpresa "+//comento esto en el Query y baja 3 segundos la consulta
			"WHERE RAR.idPicking = "+idPick+" AND RAR.Verif < RAR.cantidad AND RAR.Estado NOT in (4,7) AND RAR.IdEmpresa= "+idEmpresa+
			" GROUP BY RAR.IdSincronizacion, RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario,RAR.IdSolicitudTraslado, rao.idBulto ";
	try {
		cone = econ.getConnection();
		List<DataIDDescripcion> list= econ.darIdDescripcion("SELECT distinct destino,'' FROM reposicion_articulos WHERE mayorista=1 and idpicking="+idPick);	
		if(list.size()==1 && list.get(0).getId()==1200){
			consulta += ",RAR.Seccion ";
		}
		consulta += " ORDER BY RAR.picked desc";
	} catch (Exception e1) {		
		e1.printStackTrace();
	}
	
	try {
		cone = econ.getConnection();		
		//String consulta = "SELECT RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario,USU.Nickname, RAR.Cantidad, RAR.Picked,RAR.Remitidas, RAR.IdPacking, GROUP_CONCAT(DISTINCT ABR.Barra) BARRAS,RAR.Verif,idPosSort,Mayorista, RAR.Seccion as pedido ,RAR.idLineaSAP, AR.Descripcion FROM reposicion_articulos RAR, artbarra ABR, depositos DOR, depositos DES, usuarios USU, art_descripcion AR WHERE AR.id = RAR.idArticulo AND USU.idUsuario = RAR.idUsuario AND DOR.idDeposito = RAR.Origen AND DES.idDeposito = RAR.Destino AND ABR.idArticulo = RAR.idArticulo AND RAR.idPicking = "+idPick+" AND RAR.Estado!=4 GROUP BY RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario";
		System.out.println(consulta);
		return econ.darPickingCBulto(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}

public  List<DataPicking> encuentraDarClasificacion(int idTarea, int idEmpresa) 
{

	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		//String consulta = "SELECT RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario,USU.Nickname, RAR.Cantidad, RAR.Picked,RAR.Remitidas, RAR.IdPacking, GROUP_CONCAT(DISTINCT ABR.Barra) BARRAS,RAR.Verif,idPosSort,Mayorista, RAR.Seccion as pedido ,RAR.idLineaSAP, AR.Descripcion FROM reposicion_articulos RAR, artbarra ABR, depositos DOR, depositos DES, usuarios USU, art_descripcion AR WHERE AR.id = RAR.idArticulo AND USU.idUsuario = RAR.idUsuario AND DOR.idDeposito = RAR.Origen AND DES.idDeposito = RAR.Destino AND ABR.idArticulo = RAR.idArticulo AND RAR.idPicking = "+idPick+" AND RAR.Estado!=4 GROUP BY RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario";
		String consulta = 	"SELECT s.idArticulo, 0, '', 0, s.NombreDestino, s.idTarea, 0,'', "+
							"s.Cantidad, s.Cantidad,0, 0,IF(GROUP_CONCAT(DISTINCT ABR.Barra) IS NULL,'SINCB', "+
							"GROUP_CONCAT(DISTINCT ABR.Barra)) BARRAS,s.CantidadProcesada,s.PosSorter,0, 0 ,0, s.NombreDestino,0 "+ 
							"FROM  sorter s "+
							"left outer join artbarra ABR on ABR.idArticulo = s.idArticulo "+
							"left outer join art_descripcion AR on AR.id=s.idArticulo, usuarios USU "+  
							"WHERE s.Cantidad > s.CantidadProcesada and idtarea= "+idTarea+" AND s.IdEmpresa="+idEmpresa+
							" GROUP BY s.IdArticulo ";
		return econ.darPicking(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}
}


public  void encuentraVerificarPicking(List<DataIDDescripcion> articulosCant, List<DataPicking> pickings, int idEmpresa) 
{
	List<DataIDDescripcion> articulosCantResto = new ArrayList<>();
	for (DataIDDescripcion did : articulosCant)
	{
		try
		{
			DataIDDescripcion did2 = did.clone();
			articulosCantResto.add(did2);
		}
		catch (Exception e)
		{
			
		}
		
	}
	
	
	int cantTot = 1;
	while(cantTot>0)
	{
		int pos = 0;
		cantTot = 0;
		for (DataIDDescripcion art : articulosCant) 
		{
			for (DataPicking p : pickings) 
			{
				if(p.getArticulo().equals(art.getDescripcion()))
				{
					if(art.getId()>0)
					{
						p.setVerificada(p.getVerificada()+1);
						art.setId(art.getId()-1);
					}
					else
					{
						try
						{
							articulosCantResto.remove(pos);
						}
						catch (Exception e)
						{
							articulosCantResto.clear();
						}
						
						
					}
				}
			}
			pos++;
			for (DataIDDescripcion d : articulosCantResto) 
			{
				cantTot+=d.getId();
			}
		}
		
		
	}
	
	

	encuentraUpdateVerifPicking(pickings,false,idEmpresa);
	
	
	
}


public  String query_encuentraUpdateVerifPicking(DataPicking art, boolean correccion, int idEmpresa) 
{
	try 
	{
		String verif ="";
		if(!correccion){
			verif="Verif+";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE `reposicion_articulos` SET Verif = "+verif+art.getVerificada()+", verif_at=CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+art.getIdPicking()+" AND `idArticulo`='"+art.getArticulo()+"' AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+" and idSolicitudTraslado="+art.getSolicitud()+";");
		
		return sb.toString();
		
		
		
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return "";
		
	}
	
}
public  String query_encuentraUpdateVerifPicking(List<DataPicking> pick, boolean correccion, int idEmpresa) 
{
	try 
	{
		String verif ="";
		if(!correccion){
			verif="Verif+";
		}
		
		StringBuilder sb = new StringBuilder();
		for (DataPicking art : pick) 
		{
			sb.append("UPDATE `reposicion_articulos` SET Verif = "+verif+art.getVerificada()+", verif_at=CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+art.getIdPicking()+" AND `idArticulo`='"+art.getArticulo()+"' AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+" and idSolicitudTraslado="+art.getSolicitud()+";");
		}
		return sb.toString();
		
		
		
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return "";
		
	}
	
}



public  void encuentraUpdateVerifPicking(List<DataPicking> pick, boolean correccion, int idEmpresa) 
{
	try 
	{
		String verif ="";
		if(!correccion){
			verif="Verif+";
		}
		
		StringBuilder sb = new StringBuilder();
		for (DataPicking art : pick) 
		{
			sb.append("UPDATE `reposicion_articulos` SET Verif = "+verif+art.getVerificada()+", verif_at=CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+art.getIdPicking()+" AND `idArticulo`='"+art.getArticulo()+"' AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+" and idSolicitudTraslado="+art.getSolicitud()+";");
		}
		persistir(sb.toString());
		
		
		
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}


public  void encuentraDescartarLineasPicking(List<DataPicking> pick, int estado, int idPicking, int idEmpresa) 
{
	try 
	{
		
		for (DataPicking art : pick) 
		{
			eper.persistir("update reposicion_articulos set Estado = "+estado+", idPicking = "+idPicking+" where idEmpresa="+idEmpresa+" AND idArticulo = '"+art.getArticulo()+"' and idPicking = "+art.getIdPicking()+" ");
		}
		
		
		
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}

public  String query_encuentraUpdateRemirPickinEC(List<DataPicking> pick, int idEmpresa) 
{
	try 
	{	
		StringBuilder sb = new StringBuilder();
		for (DataPicking art : pick) 
		{
		
			sb.append("UPDATE `reposicion_articulos` SET Remitidas = remitidas+"+art.getRemitida()+
					", Estado = 7, remitida_at = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+
					" AND `idPicking`="+art.getIdPicking()+" AND `idArticulo`='"+art.getArticulo()+
					"' AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+
					" and seccion="+art.getIdPedido()+" and idSolicitudTraslado="+art.getSolicitud()+";");
		}
		
		return sb.toString();
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();	
		return "";
	}
	
}

public  void encuentraUpdateRemirPickinEC(List<DataPicking> pick, int idEmpresa) 
{
	try 
	{		
		for (DataPicking art : pick) 
		{
			int cantidad = art.getVerificada()-art.getRemitida();
			eper.persistir("UPDATE `reposicion_articulos` SET Remitidas = remitidas+"+cantidad+
					", Estado = 7, remitida_at = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+
					" AND `idPicking`="+art.getIdPicking()+" AND `idArticulo`='"+art.getArticulo()+
					"' AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+
					" and seccion="+art.getIdPedido()+" and idSolicitudTraslado="+art.getSolicitud()+";");
		}		
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();	
	}
	
}

public  void remitirBultoPicking(bulto b, int origen, int idEmpresa) 
{
	try 
	{		
		
		for (bultoContenido art : b.getContenidoList()) 
		{
			try {
				Connection cone = econ.getConnection();
				String querySolicitudes = "SELECT cantidad-remitidas, idsolicitudtraslado,'' FROM reposicion_articulos WHERE idEmpresa="+idEmpresa+
						" AND `idPicking`="+art.getPicking()+" AND `idArticulo`='"+art.getIdArticulo()+"'"+
						" AND `Origen`="+origen+" AND `Destino`="+b.getDestino()+
						" and cantidad-remitidas > 0";
				List<DataIDDescripcion> solicitudes = econ.darListaDataIdDescripcionDB(querySolicitudes);
				
				int pendiente = art.getCantidad();
				for(DataIDDescripcion s : solicitudes) {
					int solicitud = 0;
					try {solicitud = Integer.parseInt(s.getDescripcion());}catch (Exception e) {}
					if(solicitud!=0) {
						int cantidad = 0;						
						if(pendiente - s.getId() <= 0) {
							cantidad = pendiente;
						}
						else {
							cantidad = s.getId();
						}
						eper.persistir("UPDATE `reposicion_articulos` SET Remitidas = remitidas+"+cantidad+
								", remitida_at = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+
								" AND `idPicking`="+art.getPicking()+" AND `idArticulo`='"+art.getIdArticulo()+"'"+
								" AND `Origen`="+origen+" AND `Destino`="+b.getDestino()+
								" and idSolicitudTraslado="+solicitud+";");
					}
					pendiente = pendiente - s.getId();
					if(pendiente <= 0) {
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("error remitiendo caja");
				e.printStackTrace();
			}			
		}		
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();	
	}
	
}

public void remitirBultoMayorista(List<DataIDDescripcion> arts, int origen, int destino, int solicitud, int idEmpresa) 
{			
	try {
		Connection cone = eper.getConnection();		
		String query = "";
		for(DataIDDescripcion a: arts) {
			query += "UPDATE `reposicion_articulos` SET Remitidas = remitidas+"+a.getId()+
					", remitida_at = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+
					" AND `idArticulo`='"+a.getDescripcion()+"'"+
					" AND `Origen`="+origen+" AND `Destino`="+destino+
					" and idSolicitudTraslado="+solicitud+";";
		}
		eper.persistir(query);
		
	} catch (Exception e) {
		System.out.println("error remitiendo mayorista");
		e.printStackTrace();
	}			
		
}

public  void updateDocVisual(int oldDoc, List<String> arts, int newDoc, int idEmpresa) 
{
	try 
	{		
		StringBuilder sb = new StringBuilder();
		for (String art : arts) 
		{
			sb.append("UPDATE `reposicion_articulos` SET idSolicitudTraslado = "+newDoc+"  "
					+ "WHERE idEmpresa="+idEmpresa+" AND `idArticulo`='"+art+"' and idSolicitudTraslado="+oldDoc+";");
		}
		
		eper.persistir(sb.toString());
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();	
	}
	
}





public  void encuentraUpdateClasifPicking(List<DataPicking> pick, int idEmpresa) 
{
	try 
	{
		
		for (DataPicking art : pick) 
		{
			eper.persistir("UPDATE `reposicion_articulos` SET Verif = Verif+"+art.getVerificada()+", verif_at=CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+art.getIdPicking()+" AND `idArticulo`='"+art.getArticulo()+"' AND `Origen`="+art.getOrigen().getId()+" AND `Destino`="+art.getDestino().getId()+" and idSolicitudTraslado="+art.getSolicitud()+";");
		}
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}

public  void encuentraUpdateClasifTarea(List<DataPicking> pick, int idEmpresa) 
{
	try 
	{
		
		for (DataPicking art : pick) 
		{
			eper.persistir("UPDATE `sorter` SET CantidadProcesada = CantidadProcesada+"+art.getVerificada()+" WHERE idEmpresa="+idEmpresa+" AND `idTarea`="+art.getIdPicking()+" AND `idArticulo`='"+art.getArticulo()+"' and cantidad>cantidadProcesada;");
		}
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}


public  int encuentrDarCapacidadSorter(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		String consulta = "select Capacidad, Descripcion from clasificador WHERE idEmpresa = "+idEmpresa;

		return econ.darListaDataIdDescripcion(consulta).get(1).getId();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return 0;
	}

	
	
}


public  Hashtable<Integer, DataDescDescripcion> encuentraDarIdDepositos(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "select idDeposito, IdDepositoSAP, CONCAT(Nombre,' ', Direccion) from depositos WHERE idEmpresa = "+idEmpresa;

	return econ.encuentraDarIdDepositos(consulta);
	
}


private  Hashtable<String, DataDescDescripcion> encuentraDarHashDescDescripcion(String consulta) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	

	return econ.encuentraHashDescDescipcion(consulta);
	
}


public  Hashtable<String, DataDescDescripcion> encuentraDarMotivosMovimiento(int idEmpresa) 
{
	String consulta = "select IdMotivo,Descripcion from motivos_movimientos WHERE idEmpresa = "+idEmpresa;
	
	return  encuentraDarHashDescDescripcion(consulta);
}


public  String query_encuentraUpdateLineasPicking(int idPicking, int idEmpresa) 
{
	String retorno = "";
	try  
	{
		
		
		retorno +=" update reposicion_articulos set Estado = 11 where idEmpresa="+idEmpresa+" AND (Cantidad>Picked OR  Cantidad>Remitidas  )  AND idPicking = "+idPicking+";";
		retorno +=" update reposicion_articulos set Estado = 5 where idEmpresa="+idEmpresa+" AND (Cantidad=Picked OR  Cantidad=Remitidas  )  AND idPicking = "+idPicking+";";
		//eper.persistir("update ojostienenarticulos OT inner join reposicion_articulos_ojos RAO on OT.idOjo = RAO.cUbicacion AND  OT.idArticulo = RAO.idArticulo set  OT.Reservada =  OT.Reservada-RAO.cantidad where RAO.idEmpresa="+idEmpresa+" AND RAO.idPicking = "+idPicking+"");
		retorno +=" update ojostienenarticulos OT \r\n" + 
				"inner join reposicion_articulos_ojos RAO \r\n" + 
				"on OT.idOjo = RAO.cUbicacion AND  OT.idArticulo = RAO.idArticulo \r\n" + 
				"AND OT.IdEmpresa=RAO.IdEmpresa \r\n" + 
				"inner join reposicion_articulos R \r\n" + 
				"ON R.idPicking=RAO.idPicking AND  R.idArticulo = RAO.idArticulo \r\n" + 
				"AND RAO.destino=R.Destino AND RAO.pedido=R.Seccion AND RAO.IdEmpresa=R.IdEmpresa \r\n" + 
				"\r\n" + 
				"set  OT.Reservada =  OT.Reservada + R.picked - RAO.cantidad \r\n" + 
				"where RAO.idEmpresa="+idEmpresa+" AND RAO.idPicking = "+idPicking+" AND R.Cantidad > R.Picked; ";
		
		return retorno;
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return "";
		
	}
	
}


public  void encuentraUpdateLineasPicking(int idPicking, int idEmpresa) 
{
    try
    {


        eper.persistir("update reposicion_articulos set Estado = 11 where idEmpresa="+idEmpresa+" AND (Cantidad>Picked OR  Cantidad>Remitidas  )  AND idPicking = "+idPicking+";");
        eper.persistir("update reposicion_articulos set Estado = 5 where idEmpresa="+idEmpresa+" AND (Cantidad=Picked OR  Cantidad=Remitidas  )  AND idPicking = "+idPicking+";");
        //eper.persistir("update ojostienenarticulos OT inner join reposicion_articulos_ojos RAO on OT.idOjo = RAO.cUbicacion AND  OT.idArticulo = RAO.idArticulo set  OT.Reservada =  OT.Reservada-RAO.cantidad where RAO.idEmpresa="+idEmpresa+" AND RAO.idPicking = "+idPicking+"");
        /*eper.persistir("update ojostienenarticulos OT \r\n" + 
                "inner join reposicion_articulos_ojos RAO \r\n" + 
                "on OT.idOjo = RAO.cUbicacion AND  OT.idArticulo = RAO.idArticulo \r\n" + 
                "AND OT.IdEmpresa=RAO.IdEmpresa \r\n" + 
                "inner join reposicion_articulos R \r\n" + 
                "ON R.idPicking=RAO.idPicking AND  R.idArticulo = RAO.idArticulo \r\n" + 
                "AND RAO.destino=R.Destino AND RAO.pedido=R.Seccion AND RAO.IdEmpresa=R.IdEmpresa \r\n" + 
                "\r\n" + 
                "set  OT.Reservada =  OT.Reservada + R.picked - RAO.cantidad \r\n" + 
                "where RAO.idEmpresa="+idEmpresa+" AND RAO.idPicking = "+idPicking+" AND R.Cantidad > R.Picked and RAO.idBulto = ''; ");
        */

    } 
    catch (Exception e) 
    {

        e.printStackTrace();


    }
	
}



public  List<DataIDDescripcion> darListaDetallTarjeta(int idTarjeta) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		
		return econ.detalleTarjetas(idTarjeta);
		
	}	
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	return null;
}


public  void encuentraAltaTareaEntrega(int idMain,List<ArticuloPedido> articulos, int idE) 
{
	try 
	{
		
		for (ArticuloPedido a : articulos) 
		{
				eper.persistir("INSERT INTO `clasificacion_entregas_articulos` (`idTarea`, `idDistribucion`, `idLineaDistribucion`, `idArticulo`, `destino`, `CantidadOrden`,`origen`,`Coment`,`Razon`,idEmpresa) " +
												" VALUES ("+idMain+", "+a.getIdDist()+", "+a.getLineaOrden()+", '"+a.getArticulo()+"', '"+a.getDestino()+"', "+(a.getCantidadPedida()-a.getCantidadEntregada())+",'"+a.getOrigen()+"','"+a.getComent()+"',"+a.getRazon()+","+idE+") ON DUPLICATE KEY UPDATE `idTarea` = "+idMain+";");
	
		}
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}


public  List<DataClasificacionEntregaArti> encuentraDarLineasEntrega(int idMain, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		String consulta = "SELECT C.idTarea,C.idDistribucion,C.idLineaDistribucion,C.idArticulo,C.destino,C.CantidadOrden,C.CantidadVerificada,C.CantidadMovida ,GROUP_CONCAT(DISTINCT AB.Barra) BARRAS FROM clasificacion_entregas_articulos C, artbarra AB WHERE AB.idArticulo = C.idArticulo AND   C.idTarea = "+idMain+" AND C.CantidadOrden-C.CantidadMovida>0 AND C.idEmpresa="+idEmpresa+" GROUP BY C.idTarea,C.idDistribucion,C.idLineaDistribucion,C.idArticulo,C.destino";
		return econ.darLineasEntrega(consulta);
	}
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	return null;
}


public  List<DataReglaReposicion> DarReglasReposicion(int idEmpresa) 
{
	List<DataReglaReposicion> retorno = null;
	String consulta =" SELECT * FROM reposicion_reglas WHERE idEmpresa ="+idEmpresa; 
	
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		retorno = econ.darReglas(consulta);
		

	}
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;
}


public  List<DataIDIDDescripcion> darFiltrosReglasReposicion(int idEmpresa) 
{
	List<DataIDIDDescripcion> retorno = null;
	String consulta ="SELECT IdFiltro,Prioridad,Nombre FROM reposicion_reglas_filtro WHERE idEmpresa ="+idEmpresa; 
	
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		retorno = econ.darFiltrosReglasReposicion(consulta);
		

	}
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;
}


public  List<DataIDDescripcion> darPromediosPondRepo(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		String consulta = "select idPromedio, Factor from procesadorrepopromedios WHERE idEmpresa ="+idEmpresa; 
		List<DataIDDescripcion> lista = econ.darListaDataIdDescripcion(consulta);
		lista.remove(0);
		return lista;

	} catch (Exception e) 
	{

		e.printStackTrace();
		return null;
	}
}


public  List<DataDescDescripcion> darLogClienteWSSAP(String SSID)
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		String consulta = "select Fecha,Accion,Mensaje from clientesap_log where SessionID = '"+SSID+"' order by Fecha";

		return econ.darListaLogsClienteWSSAP(consulta);
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return null;
	}
	
}


public  List<ArticuloReposicion> darArticuloRepoFromLoad(List<DataIDDescripcion> articulosCantidad, int sucursal, boolean manual, int idEmpersa) 
{
	StringBuilder inserts = new StringBuilder();
	
	for (DataIDDescripcion ar : articulosCantidad) 
	{
		inserts.append(" INSERT INTO @artis VALUES ('"+ar.getDescripcion()+"',"+ar.getId()+"); ");
		
	}
	String tempTable = " DECLARE @artis TABLE (idarticulo char(50), cantidad int); ";
	String query = " select ART.IdArticulo,ART.Descripcion,ARTS.cantidad,ART.IdMarca,ART.IdSeccion,ART.IdClase, ART.IdGenero,ART.IdCategoria,ART.IdTemporada from Articulo ART INNER JOIN @artis ARTS ON ARTS.idarticulo = ART.IdArticulo WHERE ART.IdEmpresa="+idEmpersa+"; ";
	
	return MSSQL.darArticuloRepoFromLoad(articulosCantidad, tempTable,inserts,query,sucursal, manual,idEmpersa);
	
}

public  List<ArticuloReposicion> darArticuloRepoFromLoadForus(List<DataIDDescripcion> articulosCantidad, int sucursal, boolean manual, 
		int idE,int origen, int tipo, boolean prioridad) 
{
	@SuppressWarnings("unused") Connection cone;		
	try {
		StringBuilder inserts = new StringBuilder();
		for (DataIDDescripcion ar : articulosCantidad) 
		{
			
			Long longo =ar.getIdLong();
			if(longo==null) 
			{
				longo = 0L;
			}
			inserts.append(" INSERT INTO tartis (idArticulo,cantidad,pedido,solicitud,idEmpresa,NotaArticulo) VALUES ('"+ar.getDescripcion()+"',"+ar.getId()+","+longo+","+ar.getIdB()+","+idE+",'"+ar.getDescripcionB()+"') on duplicate key update NotaArticulo = CONCAT(NotaArticulo,' ','"+ar.getDescripcionB()+"'),cantidad = cantidad+"+ar.getId()+"; \n");
			
		}
		String tempTable = " TRUNCATE TABLE tartis; \n";
		String query = " select ARTS.IdArticulo,ifnull(ART.Descripcion,''),ARTS.cantidad,ifNull(ART.IdMarca,0),ARTS.pedido,ifnull(ART.IdClase,0), IFNULL(ART.IdGenero,0), ifnull(ART.IdCategoria,0),ifnull(ART.IdTemporada,0),ARTS.solicitud, ARTS.NotaArticulo from tartis ARTS LEFT OUTER JOIN Articulos ART ON ARTS.idarticulo = ART.IdArticulo WHERE ARTs.IdEmpresa="+idE+"; ";
		
		eper.getConnection();
		eper.persistir(tempTable+inserts.toString());
		
		cone = econ.getConnection();
		return econ.darArticuloRepoFromLoad(articulosCantidad, tempTable,inserts,query,sucursal, manual,idE, origen, tipo, prioridad);
	} catch (Exception e) {
		
		e.printStackTrace();
		return null;
	}
	
}



//darArticuloRepoOrderClosed
public  void darArticuloRepoOrderClosed(int idEmpresa) 
{
	try 
	{		
		eper.persistir("delete from  reposicion_articulos where idEmpresa="+idEmpresa+" AND estado = 1 and origen = 9000 and destino !=1200");
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
}








public  void logger(int idUsuario, int idModulo, String mensaje, int idE)
{
	String q = "INSERT INTO logger (idUsuario,idModulo,mensaje,idEmpresa) VALUES ("+idUsuario+","+idModulo+", '"+mensaje+"',"+idE+");";
	
	
	try 
	{
		
	
		eper.persistir(q);
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
}





public  void updateValueRegla(int idRegla, int valor, String field, int idEmpresa) 
{
	String insert = "UPDATE reposicion_reglas SET "+field+"="+valor+" WHERE idEmpresa="+idEmpresa+" AND idRegla="+idRegla+";";
	
	try 
	{
		
		
		eper.persistir(insert);
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
	
	
	
}


public  void updateValuelineaRepo(int idSincro,String idArticulo,int origen, int destino, int cantidad, int borrar,Long pedido,int pick, int idEmpresa) 
{
	String insert = "";
	if(borrar!=1)
	{
		insert = "UPDATE `reposicion_articulos` SET `Cantidad`="+cantidad+" WHERE idEmpresa="+idEmpresa+" AND `IdSincronizacion`="+idSincro+" AND `idArticulo`='"+idArticulo+"' AND `Origen`="+origen+" AND `Destino`="+destino+"  AND `idPicking`="+pick;
	}
	else
	{
		if(pedido>0){
			insert = "DELETE FROM `reposicion_articulos` WHERE idEmpresa="+idEmpresa+" AND `IdSincronizacion`="+idSincro+" AND `idArticulo`='"+idArticulo+"' AND `Origen`="+origen+" AND `Destino`="+destino+"  AND `idPicking`="+pick+" and seccion="+pedido+";";			
		}
		else{
			insert = "DELETE FROM `reposicion_articulos` WHERE idEmpresa="+idEmpresa+" AND `IdSincronizacion`="+idSincro+" AND `idArticulo`='"+idArticulo+"' AND `Origen`="+origen+" AND `Destino`="+destino+"  AND `idPicking`="+pick;
		}
		
	}
	
	try 
	{		
		
		eper.persistir(insert);
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
	
	
	
}





public  void updateFiltroRegla(int idRegla, int idfiltro, String valor, int idE) 
{
	String insert = "";
	
	if(valor.equals(""))
	{
		insert = "DELETE FROM reposicion_reglas_filtro_valor WHERE idEmpresa="+idE+" AND idRegla="+idRegla+" AND idFiltro="+idfiltro+";";
	}
	else
	{
		insert = "INSERT INTO reposicion_reglas_filtro_valor (idRegla,idFiltro,Valor,idEmpresa) VALUES ("+idRegla+","+idfiltro+", '"+valor+"',"+idE+") ON DUPLICATE KEY UPDATE Valor = '"+valor+"'"; 
	}
	try 
	{	
		eper.persistir(insert);
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();		
	}
}

public  void updateFiltroRegla2(int idRegla, List<DataIDDescripcion> filtros, int idE) 
{
	String delete = "DELETE FROM reposicion_reglas_filtro_valor WHERE idEmpresa="+idE+" AND idRegla="+idRegla+"; ";
	String insert = "";
	
	for(DataIDDescripcion f:filtros) {
		insert += "INSERT INTO reposicion_reglas_filtro_valor (idRegla,idFiltro,Valor,idEmpresa) VALUES "
				+ "("+idRegla+","+f.getId()+", '"+f.getDescripcion()+"',"+idE+") ON DUPLICATE KEY UPDATE Valor = '"+f.getDescripcion()+"'; "; 
	}
	try 
	{	
		eper.persistir(delete+insert);		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();		
	}
}


public  void updateDepoRegla(int idRegla, int id, String on, int idE) 
{
	String insert = "";
	
	if(on!=null && on.equals("on"))
	{
		insert = "INSERT INTO reposicion_reglas_destinos (idRegla, idDestino,idEmpresa) VALUES ("+idRegla+", "+id+","+idE+");";
	}
	else
	{
		insert = "DELETE FROM reposicion_reglas_destinos WHERE idEmpresa="+idE+" AND idRegla="+idRegla+" AND idDestino="+id+";";
	}
	try 
	{		
		eper.persistir(insert);		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();		
	}	
}


public  void updateDepoRegla2(int idRegla, List<Integer> depositos, int idE) 
{
	String delete = "DELETE FROM reposicion_reglas_destinos WHERE idEmpresa="+idE+" AND idRegla="+idRegla+"; ";
	String insert = "";
	
	for(Integer d:depositos) {
		insert += "INSERT INTO reposicion_reglas_destinos (idRegla, idDestino,idEmpresa) VALUES ("+idRegla+", "+d+","+idE+"); ";
	}
	try 
	{		
		eper.persistir(delete+insert);		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();		
	}	
}

public  void altaReglaRepo(String nombreRegla, int priodiRegla,int minCentralRegla, int maxLocalRegla, int idE) 
{
	String insert = "INSERT INTO reposicion_reglas (Prioridad, MinCentral, MaxLocal, Nombre,idEmpresa) VALUES ("+priodiRegla+", "+minCentralRegla+", "+maxLocalRegla+", '"+nombreRegla+"',"+idE+");";
	

	try 
	{

		
		eper.persistir(insert);
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
}


public  void bajaReglaRepo(int idRegla, int idEmpresa) 
{
	String insert = "delete from reposicion_reglas where idEmpresa="+idEmpresa+" AND idRegla ="+idRegla;
	
	try 
	{
		
		
		eper.persistir(insert);
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
}


public  void pasarAPickingVM(List<ArticuloLineaReposicion> articulosIn, int idE) 
{
	
	
	try 
	{
		
		
		for (ArticuloLineaReposicion a : articulosIn) 
		{
			String consultaI = "insert into reposicion_articulos (select * from reposicion_articulosvm where Seccion ="+a.getPedido()+" and idArticulo='"+a.getIdArticulo()+"' and Origen ="+a.getOrigen().getId()+" and Destino ="+a.getDestino().getId()+" and idEmpresa="+idE+")";
			String consultaII = "UPDATE `reposicion_articulosvm` SET `estado`=3, `Picked`='"+a.getToPick()+"' WHERE idEmpresa="+idE+" AND `idArticulo`='"+a.getIdArticulo()+"' AND `Origen`="+a.getOrigen().getId()+" AND `Destino`="+a.getDestino().getId()+" AND `Seccion`="+a.getPedido()+"";
			String consultaIII = "UPDATE `reposicion_articulos` SET `Cantidad`='"+a.getToPick()+"' WHERE idEmpresa="+idE+" AND `idArticulo`='"+a.getIdArticulo()+"' AND `Origen`="+a.getOrigen().getId()+" AND `Destino`="+a.getDestino().getId()+" AND `Seccion`="+a.getPedido()+"";
			
			eper.persistir(consultaI);
			eper.persistir(consultaII);
			eper.persistir(consultaIII);
			
			
		}
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
	
	
}





public  List<DataIDDescripcion> darArticulosProcessEcommerce(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
/*
	String consulta = "SELECT sum(t0.cant), t0.idArticulo from "+ 
					"	( "+
					"	select idArticulo, cantidadPedido as cant, idPedido "+ 
					"	from ecommerce_pedido_articulos AP "+
					"	where not exists (select idArticulo, CantidadRequerida-CantidadProcesasa,idPedido from ecommerce_pedido_articulos_req APR where AP.idPedido=APR.idPedido and AP.idArticulo=APR.idArticulo) ) as t0 "+
					"	group by t0.idArticulo";
					
	
	String consulta = "SELECT sum(t0.cant), t0.idArticulo, GROUP_CONCAT(t0.idPedido) from "  +
				"	(  "+
				"	select idArticulo, cantidadPedido as cant, idPedido "+ 
				"	from ecommerce_pedido_articulos AP  "+
				"	where not exists (select idPedido,idArticulo, CantidadRequerida-CantidadProcesasa,idPedido from ecommerce_pedido_articulos_req APR where AP.idPedido=APR.idPedido and AP.idArticulo=APR.idArticulo) ) as t0 "+
				"	group by t0.idArticulo" ;
	*/
	String consulta="SELECT sum(t0.cant), t0.idArticulo, GROUP_CONCAT(t0.idPedido) "
					+ "	from 	"
					+ "( "+ 
					"		select idArticulo, cantidadPedido as cant, idPedido  "+
					"		from ecommerce_pedido_articulos AP  "+
					"		where  not exists "
					+ "		("
					+ "			select idPedido,idArticulo, CantidadRequerida-CantidadProcesasa,idPedido "
					+ "			from ecommerce_pedido_articulos_req APR "
					+ "			where APR.idEmpresa="+idEmpresa+" AND AP.idPedido=APR.idPedido and AP.idArticulo=APR.idArticulo"
					+ "		) "+ 
					"		AND AP.idEmpresa="+idEmpresa+" AND idPedido not in "
					+ "		(select distinct IdPedido from ecommerce_pedidos_log where Estado=4) "+
					") as t0  "+
					"group by t0.idArticulo";
	
	try
	{
		List<DataIDDescripcion> retorno =  econ.darListaDataIdDescripcionB(consulta);
	
		try 
		{
		
			
			eper.persistir("update ecommerce_pedido_articulos set Procesado = 1 where idEmpresa="+idEmpresa+" AND Procesado = 0");
			
			
			
			//eper.desconectar();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			
		}
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}


public  List<DataIDDescripcion> darDepositosProcessEcommerce(List<DataIDDescripcion> depoNO, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta;
	if(depoNO==null)
	{
		consulta = "select Prioridad, IdAlmacenERP,Contactos from ecommerce_almacenes  where habilitado = 1 AND idEmpresa ="+idEmpresa;
	}
	else
	{
		String finSentencia="";
		for(DataIDDescripcion d:depoNO){
			finSentencia+=" and IdAlmacenERP!="+d.getId()+" ";
		}
		
		
		consulta = "select Prioridad, IdAlmacenERP,Contactos from ecommerce_almacenes where habilitado = 1  AND idEmpresa ="+idEmpresa+finSentencia;
	}

	try
	{
		//List<DataIDDescripcion> depCancelados= econ.darListaDataIdDescripcion("select deposito,0 from ecommerce_pedido_articulos_req_cancelados where idPedido");
		
		List<DataIDDescripcion> retorno =  econ.darListaDeposEcommerce(consulta);
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}





public  List<DataIDDescDescripcion> darArticulosOrderProcessEcommerce(int idEmpresa) 
{
	
	
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	//String consulta = "select cantidadPedido as cant, idPedido,idArticulo from ecommerce_pedido_articulos AP where not exists (select idArticulo, CantidadRequerida-CantidadProcesasa,idPedido from ecommerce_pedido_articulos_req APR where AP.idPedido=APR.idPedido and AP.idArticulo=APR.idArticulo)";
	String consulta =" SELECT  cantidadPedido AS cant, idPedido,idArticulo "
			+ " FROM ecommerce_pedido_articulos AP "
			+ " INNER JOIN art_descripcion AD ON AD.id = AP.idArticulo AND AD.idEmpresa = AP.idEmpresa "
			+ " WHERE AP.idEmpresa="+idEmpresa+" AND NOT EXISTS 	"
			+ " ("
			+ "   SELECT idPedido,idArticulo, CantidadRequerida-CantidadProcesasa,idPedido "
			+ "   FROM ecommerce_pedido_articulos_req APR "
			+ "   WHERE APR.idEmpresa="+idEmpresa+" AND AP.idPedido=APR.idPedido AND AP.idEmpresa=APR.idEmpresa AND AP.idArticulo=APR.idArticulo "
			+ " ) "
			+ "AND idPedido NOT IN "
			+ " ("
			+ "   SELECT DISTINCT IdPedido FROM ecommerce_pedidos_log WHERE idEmpresa="+idEmpresa+" AND Estado=4"
			+ " ) ";
	try
	{
		List<DataIDDescDescripcion> retorno =  econ.darListaDataIdDescDescripcion(consulta);
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}


public  List<DataIDDescDescripcion> darStockOrderItems(String consultaStock, Hashtable<String, Integer> stockPedidos) 
{
	List<DataIDDescDescripcion> retorno = new ArrayList<>();
	
	List<DataIDDescDescripcion> lst = MSSQL.darIDDescDescripcion(consultaStock);
	for (DataIDDescDescripcion s : lst) 
	{
		//1 id= stock 
		//2 desc = articulo 
		//3 descripcion = deposto
		
		//								articulo:deposito
		if(stockPedidos.get(s.getDesc()+":"+s.getDescripcion())!=null)
		{
			int cantPedida= stockPedidos.get(s.getDesc()+":"+s.getDescripcion());
			if(s.getId()>cantPedida)
			{
				retorno.add(new DataIDDescDescripcion(s.getId()-cantPedida, s.getDesc(), s.getDescripcion()));
			}
			
		}
		else
		{
			retorno.add(s);
		}
		
		
	}
	
	
	
	
	
	
	return retorno;
}




public void rechazarArticuloReq(String idArticulo, Long idPedido,	int iddepoCancela, String motivo, int idE, 
		boolean pickup, boolean devolucionstock) 
{
	
	String insert = "INSERT INTO `ecommerce_pedido_articulos_req_cancelados` "
			+ "(`idPedido`, `IdArticulo`, `deposito`,`Motivo`,idEmpresa) "
			+ "VALUES ('"+idPedido+"', '"+idArticulo+"', '"+iddepoCancela+"','"+motivo+"',"+idE+");";
	
	String updateLinea = "";
	updateLinea = "DELETE FROM `ecommerce_pedido_articulos_req` WHERE idEmpresa="+idE+" AND `idPedido`="+idPedido+
				" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoCancela+";";
	
	
	
	String updatePickup = "";
	String updatePedido = "";
	if(pickup) {
		updatePickup = "UPDATE `ecommerce_pedido_articulos_req` set procesarEnPickup=0, cantConfirmada=0, subestado=0, "
				+ "cantidadProcesasa=0, confirmado=0, CTimeStamp=RTimeStamp, PTimeStamp=RTimeStamp "
				+ "WHERE idEmpresa="+idE+" AND `idPedido`="+idPedido+
				" AND `Deposito`="+iddepoCancela+";";
		if(!devolucionstock) {
			updateLinea = "UPDATE `ecommerce_pedido_articulos_req` set deposito = "+
					"(SELECT valor FROM empresa_parametro WHERE idParametro = 4 AND idEmpresa ="+idE+"), " +
					"procesarEnPickup=0, cantConfirmada=0, subestado=0, " + 
					"cantidadProcesasa=0, confirmado=0, CTimeStamp=RTimeStamp, PTimeStamp=RTimeStamp "+
					" WHERE idEmpresa="+idE+" AND `idPedido`="+idPedido+
					" AND `Deposito`="+iddepoCancela+";";
			
			updatePickup = "";
		}
		
		updatePedido = "update ecommerce_pedido set shippingType = 2 where idpedido ="+idPedido;
	}
	
	@SuppressWarnings("unused") Connection cone;
	try 
	{		
		persistir(insert + updateLinea + updatePickup + updatePedido);
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();		
	}
	
	
}


public  void confirmarArticuloReq(String idArticulo, Long idPedido,	int iddepoConfirma, int idEmpresa) 
{
	
	
	
	
	String consultaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET `Confirmado`='1',  CTimeStamp = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoConfirma+";";
	
	
	
	try 
	{

		
		eper.persistir(consultaInsert);
		
		
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	// TODO Auto-generated method stub
	
}


public  List<Long> confirmarArticuloReq(int cantidad, String idArticulo,	int iddepoConfirma, int idEmpresa) 
{
	List<Long> retorno = new ArrayList<>();
	String q="select docVisual,CantidadRequerida-cantConfirmada,idPedido from ecommerce_pedido_articulos_req where idEmpresa="+idEmpresa+" AND idArticulo = '"+idArticulo+"' and Deposito = "+iddepoConfirma+" and Confirmado = 0  order by idPedido";
	
	List<DataIDDescripcion> pedidosArticulo = darListaDataIdDescripcionConsulMYSQL(q);
	
	
	
	try 
	{
	
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
	//Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
	
	List<DataIDDescripcion> list;
	
	
	int cantPendiente=cantidad;
	for (DataIDDescripcion p : pedidosArticulo) 
	{
		String consultaInsert="";
		try
		{
			if(cantPendiente>0)
			{
				int cantidadPedida = Integer.parseInt(p.getDescripcion());
				
				if(cantidadPedida==cantPendiente)
				{
					consultaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET cantConfirmada=cantConfirmada"+cantidadPedida+", `Confirmado`='1', `subestado`='2', fechaConfirmado = CURRENT_TIMESTAMP(), CTimeStamp = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+p.getIdLong()+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoConfirma+";";
					
					cantPendiente=0;
					retorno.add(p.getIdLong());
					/**************************************************/
					list = new ArrayList<>();
					list.add(new DataIDDescripcion(cantidadPedida,idArticulo));
					list.get(0).setIdB(1200);
					//ws.EntregaPrepararYTerminar(p.getId(), list,1200);
					/**************************************************/				
				}
				else if(cantidadPedida<cantPendiente)
				{
					consultaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET cantConfirmada=cantConfirmada"+cantidadPedida+", `Confirmado`='1', `subestado`='2', fechaConfirmado = CURRENT_TIMESTAMP(), CTimeStamp = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+p.getIdLong()+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoConfirma+";";
					cantPendiente-=cantidadPedida;
					retorno.add(p.getIdLong());
					/**************************************************/
					list = new ArrayList<>();
					list.add(new DataIDDescripcion(cantidadPedida,idArticulo));
					list.get(0).setIdB(1200);
					//ws.EntregaPrepararYTerminar(p.getId(), list,1200);
					/**************************************************/				
				}
				else{
					consultaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET cantConfirmada=cantConfirmada"+cantPendiente+" WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+p.getIdLong()+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoConfirma+";";
					
					retorno.add(p.getIdLong());
					/**************************************************/
					list = new ArrayList<>();
					list.add(new DataIDDescripcion(cantidadPedida,idArticulo));
					list.get(0).setIdB(1200);
					//ws.EntregaPrepararYTerminar(p.getId(), list,1200);
					/**************************************************/	
					cantPendiente=0;
				}
				
				
			}
			
			eper.persistir(consultaInsert);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	return retorno;
	
}






public int confirmarArticuloReqForus(int cantidad, String idArticulo,int iddepoConfirma,Long pedido, int idEmpresa) 
{
	
	String q="select docVisual,CantidadRequerida-cantConfirmada,idPedido from ecommerce_pedido_articulos_req where idEmpresa="+idEmpresa+" AND idArticulo = '"+idArticulo+"' and Deposito = "+iddepoConfirma+" and Confirmado = 0 and idpedido="+pedido+"  order by idPedido";
	
	List<DataIDDescripcion> pedidosArticulo = darListaDataIdDescripcionConsulMYSQL(q);
	
	int cantidadPersistida = 0;
	int cantPendiente=cantidad;
	for (DataIDDescripcion p : pedidosArticulo) 
	{
		String consultaInsert="";
		try
		{
			if(cantPendiente>0)
			{
				int cantidadPedida = Integer.parseInt(p.getDescripcion());
				
				if(cantidadPedida==cantPendiente)
				{
					consultaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET cantConfirmada=cantConfirmada+"+cantidadPedida+", `Confirmado`='1', `subestado`='2', fechaConfirmado = CURRENT_TIMESTAMP(), CTimeStamp = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+pedido+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoConfirma+";";
					
					cantPendiente=0;	
					cantidadPersistida += cantidadPedida;
				}
				else if(cantidadPedida<cantPendiente)
				{
					consultaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET cantConfirmada=cantConfirmada+"+cantidadPedida+", `Confirmado`='1', `subestado`='2', fechaConfirmado = CURRENT_TIMESTAMP(), CTimeStamp = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+pedido+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoConfirma+";";
					
					cantPendiente-=cantidadPedida;
					cantidadPersistida += cantidadPedida;
				}
				else{
					consultaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET cantConfirmada=cantConfirmada+"+cantPendiente+" WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+pedido+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+iddepoConfirma+";";
											
					cantPendiente=0;
					cantidadPersistida += cantPendiente;
				}
				
				
			}
			
			eper.persistir(consultaInsert);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	return cantidadPersistida;
	
}


public  boolean confirmarArticuloReq(Long idPedido, String consulta, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	if(consulta==null)
	{
		consulta = "select case when ISNULL(SUM(PR.CantidadRequerida)) then 0 else SUM(PR.CantidadRequerida) end confirmada,P.UnidadesTotal,P.idPedido from ecommerce_pedido_articulos_req PR inner join ecommerce_pedido P on P.idPedido=PR.idPedido where P.idEmpresa="+idEmpresa+" AND P.idPedido="+idPedido+" and Confirmado=1 and P.estadoencuentra in (1,2)   limit 1";
		return econ.confirmarEnvioArticuloReq(consulta);
	}
	

	try
	{
		return econ.confirmarArticuloReq(consulta);
		
	}
	catch (Exception e)
	{
		return false;
	}

}


public  DataArticuloEcommerceVerifR darArticuloEcommerceReq(String articulo, String pedido, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "select  R.idPedido, R.idArticulo,R.CantidadProcesasa cantP , P.descripcion, P.UnidadesTotal, P.URLetiqueta, R.Deposito, P.ML,IFNULL(IV.clicedula,''), IFNULL(IV.clitelefono,''), IFNULL(IV.mail,''),P.idCanalML, P.EstadoEncuentra, P.stampTime,P.idFenicio,IFNULL(pd.iddestino,'0'), p.ticketNumber, p.subpedido, IFNULL(pd.tracking,''), p.shippingType "+
					"	from ecommerce_pedido_articulos_req R  "
					+ "	inner join ecommerce_pedido P on P.idPedido=R.idPedido AND P.idEmpresa = R.idEmpresa"+
					"   left outer join ecommerce_import_venta IV on  IV.idVenta=P.idPedido and IV.idVenta=R.idpedido AND IV.idEmpresa = P.idEmpresa"+
					"	left outer join ecommerce_pedido_destino pd on pd.idpedido=r.idpedido and pd.idpedido=p.idpedido AND pd.idEmpresa = p.idEmpresa "+
					"   where R.idEmpresa="+idEmpresa+" AND R.CantidadRequerida>R.CantidadProcesasa  "+
					"	AND P.EstadoEncuentra in (1,2) "+
					"	and p.cancelado = 0 "+
					"	AND P.idPedido not in (select distinct IdPedido from ecommerce_estadospedido where idEmpresa="+idEmpresa+" AND idEstado >2) "+
					"	AND R.Confirmado=1   "+
					"	AND R.idArticulo='"+articulo+"' and R.idPedido="+pedido+" "+
					"	ORDER BY P.stampTime";

	try
	{
		DataArticuloEcommerceVerifR art = econ.darArtReqEcommerceVerif(consulta);
		if(art!=null)
		{
			return art;
		}
		else
		{
			 consulta = "select  R.idPedido, R.idArticulo,R.CantidadProcesasa cantP , P.descripcion, P.UnidadesTotal, P.URLetiqueta, R.Deposito, P.ML,IFNULL(IV.clicedula,''), IFNULL(IV.clitelefono,''), IFNULL(IV.mail,''),P.idCanalML, P.EstadoEncuentra, P.stampTime,P.idFenicio,IFNULL(pd.iddestino,'0'), p.ticketNumber, p.subpedido, IFNULL(pd.tracking,''), p.shippingType    "+
					"	from ecommerce_pedido_articulos_req R  inner join ecommerce_pedido P on P.idPedido=R.idPedido  AND P.idEmpresa=R.idEmpresa"+
					"   inner join ecommerce_pedido_articulos PA on R.idpedido=PA.idPedido and R.idArticulo=PA.idArticulo AND R.idEmpresa=PA.idEmpresa" +
					"   left outer join ecommerce_import_venta IV on IV.idVenta=P.idPedido and IV.idVenta=R.idpedido and IV.idVenta = PA.idPedido and IV.idEmpresa = PA.idEmpresa "+
					"	left outer join ecommerce_pedido_destino pd on pd.idpedido=r.idpedido and pd.idpedido=p.idpedido and pd.idEmpresa=p.idEmpresa "+
				    "   where R.idEmpresa="+idEmpresa+""
				    + " AND  R.CantidadRequerida>R.CantidadProcesasa  "+
					"	AND P.EstadoEncuentra in (1,2) "+
					"	and p.cancelado = 0 "+
					"	AND P.idPedido in (select distinct IdPedido from ecommerce_pedido_articulos where idEmpresa="+idEmpresa+" AND modificado =1) "+
					"	AND R.Confirmado=1   "+
					"	AND R.idArticulo='"+articulo+"' and R.idPedido="+pedido+" "+
					"	ORDER BY P.stampTime";
			
			art = econ.darArtReqEcommerceVerif(consulta);
			if(art!=null)
			{
				return art;
			}
			else
			{				
			
			consulta = "select  R.idPedido, R.idArticulo,R.CantidadProcesasa cantP , P.descripcion, P.UnidadesTotal, P.URLetiqueta, R.Deposito, P.ML, IFNULL(IV.clicedula,''), IFNULL(IV.clitelefono,''), IFNULL(IV.mail,''),P.idCanalML, P.EstadoEncuentra, P.stampTime,P.idFenicio, IFNULL(pd.iddestino,'0'), p.ticketNumber, p.subpedido, IFNULL(pd.tracking,''), p.shippingType   "+
					"	from ecommerce_pedido_articulos_req R  inner join ecommerce_pedido P on P.idPedido=R.idPedido AND P.idEmpresa=R.idEmpresa "+
					"   left outer join ecommerce_import_venta IV on IV.idVenta=P.idPedido and IV.idVenta=R.idpedido  and IV.idEmpresa=R.idEmpresa"+
					"	left outer join ecommerce_pedido_destino pd on pd.idpedido=r.idpedido and pd.idpedido=p.idpedido and pd.idEmpresa=p.idEmpresa "+
					"   where R.idEmpresa="+idEmpresa+" AND R.CantidadRequerida>R.CantidadProcesasa  "+
					"	AND P.EstadoEncuentra in (1,2) "+
					"	and p.cancelado = 0 "+
					"	AND P.idPedido not in (select distinct IdPedido from ecommerce_estadospedido where idEmpresa="+idEmpresa+" AND idEstado >2) "+
					"	AND R.Confirmado=0   "+
					"	AND R.idArticulo='"+articulo+"' and R.idPedido="+pedido+" "+
					"	ORDER BY P.stampTime";
			
			art = econ.darArtReqEcommerceVerif(consulta);
			if(art!=null)
			{	//Articulo sin confirmar
				art.setMensaje("ATENCION: encontramos este articulo, pero no tiene confirmacion por parte del deposito "+art.getIdDeposito()+" confirmelo e intente de nuevo");
				//art.setMensaje("Articulo sin confirmar");
				return art;
			}
			else
			{
				consulta="select EPA.idPedido,P.descripcion "+
							"	FROM ecommerce_pedido_articulos EPA  "+
							"	INNER JOIN ecommerce_pedido P on P.idPedido=EPA.idPedido AND P.idEmpresa=EPA.idEmpresa "+
							"	  LEFT JOIN ecommerce_pedido_articulos_req T ON T.idPedido=EPA.idPedido AND EPA.idArticulo=T.idArticulo AND EPA.idEmpresa=T.idEmpresa "+
							"	WHERE EPA.idEmpresa="+idEmpresa+" AND T.idPedido IS NULL "+
							"	AND EPA.idPedido not in (select distinct IdPedido from ecommerce_estadospedido where idEmpresa="+idEmpresa+" AND idEstado >2) "+
							"	AND EPA.idArticulo = '"+articulo+"' and T.idPedido="+pedido+" ";
							
				List<DataIDDescripcion> lista = darListaDataIdDescripcionConsulMYSQL(consulta);
				if(!lista.isEmpty())
				{
					art = new DataArticuloEcommerceVerifR(new Long(0), 0, 0, "", "", "", 0);
					String pedidosIN = "";
					for (DataIDDescripcion p : lista) 
					{
						pedidosIN+="Pedido"+p.getId()+"-"+p.getDescripcion()+" ";
					}
					art.setMensaje("ATENCION: se encontr? uno o varios pedidos con este articulo, pero al parecer este articulo no se pidio a ningun deposito "+pedidosIN);
					return art;
				}
				else
				{
					return null;
				}
				
			}
		}
		}
		
	}
	catch (Exception e)
	{
		return null;
	}
	
}


public  DataArticuloEcommerceVerifR darArticuloEcommerceReqReclasifica(Long idPedido, int estado, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "";
	if(estado==0)
	{
		consulta = "select  R.idPedido, R.idArticulo,R.CantidadProcesasa cantP , P.descripcion, P.UnidadesTotal, P.URLetiqueta, R.Deposito,P.ML, ifnull(IV.clicedula,''), ifnull(IV.clitelefono,''), ifnull(IV.mail,''),P.idCanalML, P.EstadoEncuentra, P.stampTime,P.idFenicio,IFNULL(d.iddestino,'0'), p.ticketNumber, p.subpedido, IFNULL(d.tracking,''), p.shippingType   "+  
				"	from ecommerce_pedido_articulos_req R  	"+
				"	inner join ecommerce_pedido P on P.idPedido=R.idPedido AND P.idEmpresa=R.idEmpresa "+
				"	inner join ecommerce_pedido_articulos PA on R.idpedido=PA.idPedido and R.idArticulo=PA.idArticulo AND R.idEmpresa=PA.idEmpresa "+
				"	left outer join ecommerce_import_venta IV on IV.idVenta=P.idPedido and IV.idVenta=R.idpedido and IV.idVenta = PA.idPedido AND IV.idEmpresa = PA.idEmpresa"+
				"	left outer join ecommerce_pedido_destino d on d.idpedido=r.idpedido and d.idpedido=p.idpedido AND d.idEmpresa = p.idEmpresa "+
				"	where R.idEmpresa="+idEmpresa+" AND R.idPedido="+idPedido+" limit 1";
	}
	else
	{
		consulta = "select  R.idPedido, R.idArticulo,R.CantidadProcesasa cantP , P.descripcion, P.UnidadesTotal, P.URLetiqueta, R.Deposito, P.ML,ifnull(IV.clicedula,''), ifnull(IV.clitelefono,''), ifnull(IV.mail,''),P.idCanalML, P.EstadoEncuentra, P.stampTime,P.idFenicio,IFNULL(d.iddestino,'0'), p.ticketNumber, p.subpedido, IFNULL(d.tracking,''), p.shippingType   "+  
				"	from ecommerce_pedido_articulos_req R  	"+
				"	inner join ecommerce_pedido P on P.idPedido=R.idPedido AND P.idEmpresa=R.idEmpresa"+
				"	inner join ecommerce_pedido_articulos PA on R.idpedido=PA.idPedido and R.idArticulo=PA.idArticulo and R.idEmpresa=PA.idEmpresa "+
				"	left outer join ecommerce_import_venta IV on IV.idVenta=P.idPedido and IV.idVenta=R.idpedido and IV.idVenta = PA.idPedido and IV.idEmpresa = PA.idEmpresa"+ 
				"	left outer join ecommerce_pedido_destino d on d.idpedido=r.idpedido and d.idpedido=p.idpedido AND d.idEmpresa = p.idEmpresa "+
				"	where P.idEmpresa="+idEmpresa+" AND P.EstadoEncuentra = "+estado+" AND  R.idPedido="+idPedido+" limit 1";
	}
	

	try
	{
		return econ.darArtReqEcommerceVerif(consulta);
		
	}
	catch (Exception e)
	{
		return null;
	}
}


public  List<DataIDDescripcion> darArticuloFactEcommerceReq(String idPedido, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "select 0,CONCAT(P.idPedido,' - ', P.descripcion, ' - ', F.idFactura)  from ecommerce_pedido P inner join ecommerce_pedido_factura F on F.idPedido = P.idPedido and P.idPedido ="+idPedido+" AND P.IdEmpresa ="+idEmpresa;

	try
	{
		return darListaDataIdDescripcionMYSQLConsulta(consulta);
		
	}
	catch (Exception e)
	{
		return null;
	}
}

public  List<DataIDDescripcion> darArticuloFactEcommerceReqArticulo(String idArticulo, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "select '-', Concat(ifnull (OT.idOjo, 'SIN ASIGNAR'),' - ',EP.descripcion), EP.idPedido "+ 
					"	from ecommerce_pedido EP   "+
					"	inner join ecommerce_pedido_articulos_req ER ON ER.idPedido = EP.idPedido AND ER.idEmpresa = EP.idEmpresa "+ 
					"	left JOIN ojostienenarticulos OT ON OT.idArticulo = ER.idPedido  AND  OT.idEmpresa = ER.idEmpresa "+
					"	where "+ 
					"	ER.idEmpresa="+idEmpresa+" AND ER.idArticulo = '"+idArticulo+"' "+ 
					"	AND EP.idPedido not in (select distinct IdPedido from ecommerce_pedidos_log where idEmpresa="+idEmpresa+" AND Estado IN (4,5,6,34,40,44) and `level` = 4) " +
					"	AND DATE(EP.stampTime)>'20180603' "+
					"	GROUP BY EP.idPedido";

	
	try
	{
		return darListaDataIdDescripcionMYSQLConsulta(consulta);
		
	}
	catch (Exception e)
	{
		return null;
	}
}

public  List<DataIDDescripcion> darArticuloFactEcommerceReqIdPedido(String idsPedidos, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "select '-', Concat(ifnull (OT.idOjo, 'SIN ASIGNAR'),' - ',EP.descripcion), EP.idPedido "+
			" from ecommerce_pedido EP  "+
			"	inner join ecommerce_pedido_articulos_req ER ON ER.idPedido = EP.idPedido "+ 
			"	left JOIN ojostienenarticulos OT ON OT.idArticulo = ER.idPedido  "+
			"	where "+
			"	ER.idPedido in ( "+idsPedidos+") AND EP.IdEmpresa ="+idEmpresa + 
			"	GROUP BY EP.idPedido";

	try
	{
		return darListaDataIdDescripcionMYSQLConsulta(consulta);
		
	}
	catch (Exception e)
	{
		return null;
	}
}


public  List<DataArticuloEcommerceVerifR> darArticulosEcommerceReq(Long idPedid, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "select  R.idPedido, R.idArticulo,R.CantidadProcesasa cantP , P.descripcion, P.UnidadesTotal, P.URLetiqueta, R.Deposito  from ecommerce_pedido_articulos_req R " +
			" inner join ecommerce_pedido P on P.idPedido=R.idPedido where R.CantidadRequerida!=R.CantidadProcesasa and R.idPedido = '"+idPedid+"' AND R.Confirmado=1 AND R.IdEmpresa ="+idEmpresa+" order by P.UnidadesTotal limit 1";

	try
	{
		return econ.darArtsReqEcommerceVerif(consulta);
		
	}
	catch (Exception e)
	{
		return null;
	}
}


public  boolean updateEcommerceArticuloReq(String idArticulo, Long idPedido, int idDeposito,int canal, int idEmpresa, Usuario u) 
{
	String consultaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET `CantidadProcesasa`= `CantidadProcesasa`+1 , PTimeStamp = CURRENT_TIMESTAMP()  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+" AND `idArticulo`='"+idArticulo+"' AND `Deposito`="+idDeposito+" and CantidadProcesasa<CantidadRequerida;";
	String consultaPuedeProcesar = "select "
			+ "		("
			+ "			case when ISNULL(SUM(PR.CantidadProcesasa)) then 0 else SUM(PR.CantidadProcesasa) end)+1 procesada,	P.UnidadesTotal,P.idPedido "
			+ "			from ecommerce_pedido_articulos_req PR"
			+ " 		inner join ecommerce_pedido P on P.idPedido=PR.idPedido AND P.idEmpresa=PR.idEmpresa "
									+ "where P.idEmpresa="+idEmpresa+" AND P.idPedido="+idPedido+" and Confirmado=1   limit 1";
	
	boolean puedeProcesar = false;
	
	
	

	try 
	{
	
		puedeProcesar =confirmarArticuloReq(idPedido,consultaPuedeProcesar,idEmpresa);
		
		if(puedeProcesar)
		{
			
			persistir(consultaInsert);
			
			boolean pedidoIncompleto =  confirmarArticuloReq(idPedido,consultaPuedeProcesar,idEmpresa);
			if(!pedidoIncompleto)
			{
				updateEcommerceEstado(idPedido, 3,idEmpresa, u);
				
			}
			
			
			
			
		}
		else
		{
			return false;
		}
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
	return puedeProcesar;
	

}

public  String query_updateEcommerceEstado(Long idPedido, int estado, int idEmpresa, int idUsuario,List<DataIDDescripcion> estados) 
{
	Utilidades util = new Utilidades();
	
	StringBuilder sb = new StringBuilder();
	String estadoEcommerce = util.statusMapperFenicio(estado);
	String consultaInsert = "UPDATE `ecommerce_pedido` SET `EstadoEncuentra`="+estado+", EstadoEcommerce='"+estadoEcommerce+"' "
			+ "WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	String fechaInsert ="";
	if(estado==4){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET CantidadProcesasa = CantidadRequerida, DespachoTimeStamp = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}else if(estado==6){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET RetiroTimeStamp = CURRENT_TIMESTAMP()  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}else if(estado==34){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET EnvioTimeStamp = CURRENT_TIMESTAMP()  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}
	else if(estado==5){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET TiendaTimeStamp = CURRENT_TIMESTAMP()  WHERE  idEmpresa="+idEmpresa+" AND`idPedido`="+idPedido+";";
	}

	
	
	String descEstado = "";
	for (DataIDDescripcion e : estados) 
	{
		if(e.getId()==estado)
		{
			descEstado=e.getDescripcion();
			break;
		}
	}
	
	
	try 
	{
		boolean cambioEstado= true;
		if(estado==30 || estado==33 || estado==44){
			cambioEstado = false;
		}
		
		if(cambioEstado){
		
			sb.append(consultaInsert);	
			sb.append("insert into ecommerce_estadospedido (idPedido,idEstado,idEmpresa) values ("+idPedido+","+estado+","+idEmpresa+");");
		}
		
		sb.append(query_logPedido(idPedido, idUsuario, estado, "Cambiando estado a "+descEstado,4,idEmpresa));
		
		if(!fechaInsert.equals("")){
			sb.append(fechaInsert);
		}
		
		
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	return sb.toString();
}



public  void updateEcommerceEstado(Long idPedido, int estado, int idEmpresa, Usuario u) 
{
	Utilidades util = new Utilidades();
	String estadoEcommerce = util.statusMapperFenicio(estado);
	String consultaInsert = "UPDATE `ecommerce_pedido` SET `EstadoEncuentra`="+estado+", EstadoEcommerce='"+estadoEcommerce+"' "
			+ "WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	String fechaInsert ="";
	if(estado==4){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET CantidadProcesasa = CantidadRequerida, DespachoTimeStamp = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}else if(estado==6){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET RetiroTimeStamp = CURRENT_TIMESTAMP()  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}else if(estado==34){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET EnvioTimeStamp = CURRENT_TIMESTAMP()  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}
	else if(estado==5){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET TiendaTimeStamp = CURRENT_TIMESTAMP()  WHERE  idEmpresa="+idEmpresa+" AND`idPedido`="+idPedido+";";
	}

	List<DataIDDescripcion> estados = darListaDataIdDescripcionConsulMYSQL("select id,descripcion from ecommerce_estado_encuentra where idEmpresa="+idEmpresa);
	estados.remove(0);
	
	String descEstado = "";
	for (DataIDDescripcion e : estados) 
	{
		if(e.getId()==estado)
		{
			descEstado=e.getDescripcion();
			break;
		}
	}
	
	
	try 
	{
		boolean cambioEstado= true;
		if(estado==30 || estado==33 || estado==44){
			cambioEstado = false;
		}
		
		if(cambioEstado){
		
			eper.persistir(consultaInsert);	
			eper.persistir("insert into ecommerce_estadospedido (idPedido,idEstado,idEmpresa) values ("+idPedido+","+estado+","+idEmpresa+")");
		}
		
		logPedido(idPedido, u.getNumero(), estado, "Cambiando estado a "+descEstado,4,idEmpresa);
		
		if(!fechaInsert.equals("")){
			persistir(fechaInsert);
		}
		
		boolean integracionCallbackestados = darIntegracionProductiva(6,idEmpresa);
		if(integracionCallbackestados) {
			ProcessEcommerce process = new ProcessEcommerce();
			process.callBackEstado(idEmpresa, idPedido, estado, u.getIdEquipo());
		}
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}

public  List<String> listquery_updateEcommerceEstado(Long idPedido, int estado, int idEmpresa, Usuario u, List<DataIDDescripcion> estados) 
{
	List<String> lString = new ArrayList<>();
	Utilidades util = new Utilidades();
	String estadoEcommerce = util.statusMapperFenicio(estado);
	String consultaInsert = "UPDATE `ecommerce_pedido` SET `EstadoEncuentra`="+estado+", EstadoEcommerce='"+estadoEcommerce+"' "
			+ "WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+"; ";
	
	
	String fechaInsert ="";
	if(estado==4){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET CantidadProcesasa = CantidadRequerida, DespachoTimeStamp = CURRENT_TIMESTAMP() WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+"; ";
	}else if(estado==6){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET RetiroTimeStamp = CURRENT_TIMESTAMP()  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+"; ";
	}else if(estado==34){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET EnvioTimeStamp = CURRENT_TIMESTAMP()  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+"; ";
	}
	else if(estado==5){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET TiendaTimeStamp = CURRENT_TIMESTAMP()  WHERE  idEmpresa="+idEmpresa+" AND`idPedido`="+idPedido+"; ";
	}
	
	String descEstado = "";
	for (DataIDDescripcion e : estados) 
	{
		if(e.getId()==estado)
		{
			descEstado=e.getDescripcion();
			break;
		}
	}
	
	
	try 
	{
		boolean cambioEstado= true;
		if(estado==30 || estado==33 || estado==44){
			cambioEstado = false;
		}
		
		if(cambioEstado){
		
			lString.add(consultaInsert);
			String estadoPEdido = "insert into ecommerce_estadospedido (idPedido,idEstado,idEmpresa) values ("+idPedido+","+estado+","+idEmpresa+"); " ;
			lString.add(estadoPEdido);
		}
		
		String qLog = query_logPedido(idPedido, u.getNumero(), estado, "Cambiando estado a "+descEstado,4,idEmpresa);
		lString.add(qLog);
		
		if(!fechaInsert.equals("")){
			lString.add(fechaInsert);
		}

		boolean integracionCallbackestados = darIntegracionProductiva(6,idEmpresa);
		if(integracionCallbackestados) {
			ProcessEcommerce process = new ProcessEcommerce();
			process.callBackEstado(idEmpresa, idPedido, estado, u.getIdEquipo());
		}
		
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();	
	}
	return lString;
	
}

public String tackingpedidoPeYA(String idPedido, int idEmpresa)
{
	String q = "SELECT 0,'PEDIDOS_YA' FROM ecommerce_pedido_destino ED  "
			+ "inner JOIN  ecommerce_pedido EP ON ED.idPedido = EP.idPedido AND EP.IdEmpresa = ED.IdEmpresa "
			+ "INNER JOIN ecommerce_envioml EE ON EE.idDeposito = ED.idDestino AND EE.IdEmpresa = ED.IdEmpresa  "
			+ "WHERE   EP.idEmpresa = "+idEmpresa+" AND ED.idDestino=300000 AND ED.tracking='' AND EP.idFenicio="+idPedido;
	DataIDDescripcion tra = darDataIdDescripcion(q);
	
	if(tra!=null && !tra.getDescripcion().equals(""))
	{
		return tra.getDescripcion();
	}
	return "";
}


public  void updateEcommerceEstadoFecha(Long idPedido, int estado, int idEmpresa, int idUsuario, String fecha) 
{
	
	String consultaInsert = "UPDATE `ecommerce_pedido` SET `EstadoEncuentra`="+estado+" WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	String fechaInsert ="";
	if(estado==4){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET CantidadProcesasa = CantidadRequerida, DespachoTimeStamp = '"+fecha+"' WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}else if(estado==6){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET RetiroTimeStamp = '"+fecha+"'  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}else if(estado==34){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET EnvioTimeStamp = '"+fecha+"'  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+";";
	}
	else if(estado==5){
		fechaInsert = "UPDATE `ecommerce_pedido_articulos_req` SET TiendaTimeStamp = '"+fecha+"'  WHERE  idEmpresa="+idEmpresa+" AND`idPedido`="+idPedido+";";
	}

	List<DataIDDescripcion> estados = darListaDataIdDescripcionConsulMYSQL("select id,descripcion from ecommerce_estado_encuentra where idEmpresa="+idEmpresa);
	estados.remove(0);
	
	String descEstado = "";
	for (DataIDDescripcion e : estados) 
	{
		if(e.getId()==estado)
		{
			descEstado=e.getDescripcion();
			break;
		}
	}	
	
	try 
	{
		boolean cambioEstado= true;
		if(estado==30 || estado==33 || estado==44){
			cambioEstado = false;
		}
		
		if(cambioEstado){
		
			eper.persistir(consultaInsert);	
			eper.persistir("insert into ecommerce_estadospedido (idPedido,idEstado,idEmpresa) values ("+idPedido+","+estado+","+idEmpresa+")");
		}
		
		logPedido(idPedido, idUsuario, estado, "Cambiando estado a "+descEstado,4,idEmpresa);
		
		if(!fechaInsert.equals("")){
			persistir(fechaInsert);
		}
		
		
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}


public  boolean procesarOrdenPedido(Long idPedido, int idEmpresa) 
{
	boolean retorno = false;
	if(darIntegracionProductiva(1, idEmpresa))
	{
		
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		String consulta = "select 0, V.porcDescuento,'UYU' ,V.clicedula, V.clinombre, V.clidireccion, V.cliRuc, V.clitelefono, '', V.idVenta, V.uletiqueta, V.FP, V.ML, V.Comentario, "
				+ "importePago, p.idCanalML, p.stampTime, if(isnull(fp.idFormaVisual),'',fp.idFormaVisual)  "
				+ "from ecommerce_import_venta V "
				+ "inner join ecommerce_pedido p on p.idpedido=V.idventa  AND V.idEmpresa=p.idEmpresa"
				+ "left outer join fp_visual_fenicio fp on v.fpVisual=fp.formafenicio  and pd.idEmpresa=V.idEmpresa"
				+ "where V.idEmpresa="+idEmpresa+" Sincronizada=0 and idVenta = "+idPedido;
		
		try
		{		
			retorno = econ.encuentraPedidosOrdenVenta(consulta,idEmpresa);
			System.out.println(retorno); 
			 updateEcommerceImportOrden(idPedido,idEmpresa);
			
		}
		catch (Exception e)
		{
			
		}	
	}
	else
	{
		return true;
	}
	
	
	
		
	
	return retorno;
	
}






public  boolean ReprocesarOrdenPedido(Long idPedido, int idEmpresa) 
{
	boolean retorno = false;
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "select 0, V.porcDescuento,'UYU' ,V.clicedula, V.clinombre, V.clidireccion, '', V.clitelefono, '', V.idVenta, V.uletiqueta, V.FP, V.ML, V.Comentario, importePago  from ecommerce_import_venta V where V.idEmpresa="+idEmpresa+" AND idVenta = "+idPedido;
		try
	{
		
		retorno = econ.encuentraPedidosOrdenVenta(consulta,idEmpresa);
		System.out.println(retorno); 
		boolean pudo = updateEcommerceImportOrden(idPedido, idEmpresa);
		
		
		
	}
	catch (Exception e)
	{
		
	}
	
	return retorno;
	
}
public  boolean ReReprocesarOrdenPedido(Long idPedido, int idEmpresa)//,String access_token) 
{
	boolean retorno = false;
	
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String consulta = "select 0, V.porcDescuento,'UYU' ,V.clicedula, V.clinombre, V.clidireccion, '', V.clitelefono, '', V.idVenta, V.uletiqueta, V.FP, V.ML, V.Comentario, importePago, p.idCanalML, p.stamptime  from ecommerce_import_venta V inner join ecommerce_pedido p on p.idpedido=V.idventa where V.idEmpresa="+idEmpresa+" AND idVenta = "+idPedido;
	try
	{
		
		retorno = econ.encuentraPedidosOrdenVenta(consulta,idEmpresa);
		System.out.println(retorno); 
		boolean pudo = updateEcommerceImportOrden(idPedido,idEmpresa);
		
		if(!retorno)
		{
			if(esMLPedido(idPedido,idEmpresa))
			{
				//Call_WS_meli ws = new Call_WS_meli();
				
				//ws.getPedidosMLOV(access_token,idPedido+"");
				List<DataIDDescDescripcion> canales = EcommercedarCanalesML(idEmpresa);
				
				for (DataIDDescDescripcion c : canales) 
				{

					String usr = c.getDesc();
					String secret = c.getDescripcion();
					//String access_token= ws.getToken(usr,secret);
					//ws.getPedidosMLOV(access_token,idPedido+"",c.getId(),c.getDescII());
				}
				
				retorno = procesarOrdenPedido(idPedido, idEmpresa);
				
			}
			else
			{
				Call_WS_analoga call = new Call_WS_analoga();
				DataIDDescDescripcion canal = EcommercedarCanalAnaloga(idPedido,idEmpresa);
				
				call.getPedidoOV(idPedido,canal.getId(),idEmpresa);
				
				retorno = procesarOrdenPedido(idPedido,idEmpresa);
			}
		}
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	return retorno;
	
}











private  boolean updateEcommerceImportOrden(Long idPedido, int idEmpresa) 
{
	
String consultaInsert = "UPDATE `encuentra_v2`.`ecommerce_import_venta` SET `Sincronizada`='1' WHERE idEmpresa="+idEmpresa+" AND `idVenta`="+idPedido;
	
	
	
	try 
	{
		
		
		return eper.persistir(consultaInsert);
		
		
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return false;
		
	}
	
}


public  void altaOrdenPedido(Long idPedido, int idOrden, int idFactura, String urlFact, int idE) 
{
	String consulta = "";
	
	if(idFactura!=0 && idOrden!=0)
	{
		consulta = "INSERT INTO `ecommerce_pedido_factura` (`idPedido`, `IdOrden`, `idFactura`,`urlFactura`, idEmpresa) VALUES ( '"+idPedido+"', '"+idOrden+"','"+idFactura+"','"+urlFact+"',"+idE+") on duplicate key update `IdOrden`= "+idOrden+",`idFactura`="+idFactura+" ,`urlFactura`='"+urlFact+"' ;";
	}
	else if(idFactura==0)
	{
		consulta = "INSERT INTO `ecommerce_pedido_factura` (`idPedido`, `IdOrden`,idEmpresa) VALUES ( '"+idPedido+"', '"+idOrden+"',"+idE+") on duplicate key update `IdOrden`= "+idOrden+";";
	}
	else
	{
		consulta="UPDATE `ecommerce_pedido_factura` SET `idFactura`='"+idFactura+"' ,`urlFactura`='"+urlFact+"' WHERE  idEmpresa="+idE+" AND`idPedido`="+idPedido;
	}
		
	try 
	{	
		eper.persistir(consulta);		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();		
	}	
}


public  List<DataDetallePedido> darListaDetallePedidosEcommerce(String idPedido, String estado, String formaPago, String sinFactura, String sinEt, 
		String fechaI, String fechaF, Integer idTienda, String DepCero, String art,String destino, int idEmpresa, 
		String idFenicio, String aConsolidar, String diasTarjeta, boolean detalleCompleto) 
{
	
	String qPedido = "";
	String qFenicio = "";
	String qEstado = "";
	String qFormaPago = "";
	String qFacturados = "";
	String qEtiqueta = "";
	String qFechaI = "";
	String limit = "";
	String qDepCero="";
	String qArticulo="";
	String qDestino="";
	
	if(sinEt!=null)
	{
		qEtiqueta = " and PE.URLetiqueta not like 'HTTP%' ";
	}
	
	if(diasTarjeta!=null)
	{
		switch (diasTarjeta) {
		case "0": // HOY
		case "1": // AYER
		case "2": // 2 DIAS
		case "3": // 3 DIAS
			qFechaI = " AND PE.EstadoEncuentra IN (1,2) and PE.stamptime> current_timestamp - INTERVAL '2' MONTH AND DATEDIFF(CURRENT_TIMESTAMP(),PE.stamptime)= "+diasTarjeta+" ";
			break;
		case "4": // MAS DE 3 DIAS
			qFechaI = " AND PE.EstadoEncuentra IN (1,2) and PE.stamptime> current_timestamp - INTERVAL '2' MONTH AND DATEDIFF(CURRENT_TIMESTAMP(),PE.stamptime)>3 ";
			break;

		default:
			break;
		}
	}
	else if(!fechaI.equals(""))
	{
		//qFechaI = " and (PR.RTimeStamp BETWEEN '"+fechaI+"' AND '"+fechaF+"')";
		qFechaI = " and (PE.stamptime BETWEEN '"+fechaI+"' AND '"+fechaF+"')";
	}
	else
	{
		limit=" order by PR.RTimeStamp desc limit 600 ";
	}
	
	if(sinFactura!=null)
	{
		qFacturados = " and (PF.idFactura=0 or PF.idFactura is null) ";
	}
	
	if(idPedido!=null && !idPedido.equals(""))
	{
		qPedido = " and PE.idPedido = "+idPedido+" ";
	}
	
	if(idFenicio!=null && !idFenicio.equals(""))
	{
		qFenicio = " and PE.idFenicio = '"+idFenicio+"' ";
	}
	
	if(estado!=null &&!estado.equals(""))
	{
		qEstado = " and PE.EstadoEncuentra in ("+estado+") ";
	}
	
	if(formaPago!=null &&!formaPago.equals(""))
	{
		if(formaPago.equals("MERCADO LIBRE")){
			qFormaPago = " and PE.FormaPago regexp '[0-9]$' ";
		}
		else{
			qFormaPago = " and PE.FormaPago = '"+formaPago+"' ";
		}
	}
	
	if(DepCero != null){
		qDepCero= " and PR.Deposito=0 ";
	}
	
	if(art!=null && !art.equals(""))
	{
		qArticulo = " and PR.idArticulo like '"+art+"%' ";
	}
	
	if(destino!=null && !destino.equals(""))
	{
		qDestino = " and PD.idDestino IN ("+destino+") ";
	}
	
	String innerDepo = "";
	String  qTienda="";
	if(idTienda!=null && idTienda!=0)
	{
		innerDepo = "	INNER JOIN envio_encuentra_documento_lineas ENVL ON ENVL.idArticulo = PE.idPedido INNER JOIN envio_encuentra_documento ENV ON ENV.idDocumento = ENVL.idDocumento ";
		qTienda="and ENV.idDestino = "+idTienda+" ";
	}
	
	String qry = "";
	String groupBy = "";
	
		if(aConsolidar!=null && !aConsolidar.equals("")) //muestra los pedidos por detalle cerrado y pedidos a m?s de un dep?sito
		{
		qry = "SELECT PE.idPedido, PE.UnidadesTotal, EE.descripcion,GROUP_CONCAT(' ', PR.idArticulo), COUNT(DISTINCT(PR.Deposito)), SUM(PR.CantidadRequerida), "+
				" SUM(PR.CantidadProcesasa), SUM(PR.cantConfirmada), MAX(PR.RTimeStamp), MAX(PR.CTimeStamp), MAX(PR.PTimeStamp), IFNULL(PF.IdOrden, 0) ov, IFNULL(PF.idFactura, 0) fac, PE.FormaPago, "+
				"   CONCAT(PE.descripcion,'<br/>', IFNULL(clicedula, '')), PE.URLetiqueta, IFNULL(PF.urlFactura, ''), EE.id, IFNULL(PD.idDestino, 0),ECML.nombre,ARTMOD.modificado,ARTMOD.cambio,PR.DespachoTimeStamp, "+
				"   PD.retiroCFormulario, PD.personaRetira, PE.estadoEcommerce, IFNULL(dep.Nombre, 0), " + 
				"	PE.idFenicio, IF(COUNT(DISTINCT(PR.Deposito)) > 1, 'SI', 'NO') AS ParaConsolidar,ad.descripcion " +
				",IFNULL(group_CONCAT(ulup.Nombre,' ',ulup.Apellido),'---'),IF(PR.RTimeStamp=PR.RetiroTimeStamp,'---',PR.RetiroTimeStamp), PR.docVisual,ARTMOD.NotaArticulo, if(pe.idPedido = pe.subpedido,'',pe.subpedido), PST.id, PST.descripcion,   "+
				"PE.Facturado, if(PD.idDestino=700000,CONCAT('',PD.tracking),PD.tracking), IFNULL(ec.mail,'---'), IFNULL(ec.telefono,'---'), \r\n" + 
				"IFNULL(ec.Departamento,'---'),IFNULL(ec.Ciudad,'---'), IFNULL(ec.Direccion,'---'), IFNULL(ec.cpostal,'---'), IFNULL(ec.DireccionNota,'---'),'','',if(EE.id not IN (1,2,99),max(PR.ptimestamp),'---') "+
				"	,deO.Nombre FROM ecommerce_pedido PE " +
				"   INNER JOIN ecommerce_pedido_articulos_req PR  ON PE.idPedido = PR.idPedido AND PR.idEmpresa = PE.idEmpresa "+
				"	INNER JOIN depositos deO ON deO.idDeposito=PR.Deposito AND PR.idEmpresa=deO.idEmpresa "+
				"	INNER JOIN ecommerce_estado_encuentra EE ON PE.EstadoEncuentra = EE.id AND PE.idEmpresa = EE.idEmpresa "+
				"	LEFT OUTER JOIN ecommerce_pedido_destino PD ON PE.idPedido=PD.idPedido AND PE.idEmpresa = PD.idEmpresa "+
				" 	INNER JOIN ecommerce_canal_ml ECML ON ECML.id=PE.idCanalML AND PE.idEmpresa=ECML.idEmpresa "	+
				"   INNER JOIN ecommerce_pedido_articulos ARTMOD ON PE.idPedido=ARTMOD.idPedido and PR.idArticulo=ARTMOD.idArticulo AND PE.idEmpresa= ARTMOD.idEmpresa " +
				innerDepo+
				"	LEFT OUTER JOIN ecommerce_pedido_factura PF ON PF.idPedido = PE.idPedido AND PE.idEmpresa=PF.idEmpresa "+
				"   LEFT OUTER JOIN ecommerce_import_venta IV ON IV.idventa=PE.idPedido AND PE.idEmpresa= IV.idEmpresa "+
				"   LEFT OUTER JOIN depositos dep ON dep.idDeposito=PD.idDestino AND PD.idEmpresa=dep.idEmpresa "+
				"	LEFT OUTER JOIN art_descripcion ad on ad.id=ARTMOD.idArticulo AND ad.idEmpresa=PD.idEmpresa "+
				"LEFT OUTER JOIN ecommerce_pedidos_log lup \r\n" + 
				"ON PE.idPedido=lup.idPedido AND PE.idEmpresa = lup.idEmpresa AND lup.Estado=3 AND lup.IdUsuario > 0\r\n" + 
				"AND lup.idpedido=pr.idpedido \r\n " + 
				"LEFT OUTER JOIN usuarios ulup ON ulup.idUsuario=lup.IdUsuario AND ulup.idEmpresa=lup.IdEmpresa \r\n" + 
				"LEFT OUTER JOIN ecommerce_import_clientes ec ON ec.idPedido=PE.idPedido AND ec.IdEmpresa=PE.IdEmpresa "+
				"LEFT OUTER JOIN ecommerce_shipping_type PST ON PST.id = PE.shippingType "+
				"	where PE.idEmpresa="+idEmpresa+" ";
		groupBy = " GROUP BY PE.idPedido";
		}
		else //busqueda normal
		{
			if(detalleCompleto)
			{
				qry = "SELECT PE.idPedido, PE.UnidadesTotal, EE.descripcion, PR.idArticulo, PR.Deposito, PR.CantidadRequerida, "+
				"PR.CantidadProcesasa, PR.cantConfirmada, PR.RTimeStamp, PR.CTimeStamp, PR.PTimeStamp,  IFNULL(PF.IdOrden, 0) "+
				"ov, IFNULL(PF.idFactura, 0) fac , PE.FormaPago, CONCAT(PE.descripcion,'<br/>',IFNULL(clicedula, '')), PE.URLetiqueta, "+
				"IFNULL(PF.urlFactura, ''), EE.id, IFNULL(PD.idDestino, 0),ECML.nombre,ARTMOD.modificado,ARTMOD.cambio,PR.DespachoTimeStamp, "+
				"PD.retiroCFormulario, PD.personaRetira, PE.estadoEcommerce, IFNULL(dep.Nombre, 0), PE.idFenicio,'',ad.descripcion "+
				",IFNULL(group_CONCAT(ulup.Nombre,' ',ulup.Apellido),'---'),IF(PR.RTimeStamp=PR.RetiroTimeStamp,'---',PR.RetiroTimeStamp), PR.docVisual,ARTMOD.NotaArticulo, if(pe.idPedido = pe.subpedido,'',pe.subpedido), PST.id, PST.descripcion,  "+
				"PE.Facturado, if(PD.idDestino=700000,CONCAT('',PD.tracking),PD.tracking), IFNULL(ec.mail,'---'), IFNULL(ec.telefono,'---'), \r\n" + 
				"IFNULL(ec.Departamento,'---'),IFNULL(ec.Ciudad,'---'), IFNULL(ec.Direccion,'---'), IFNULL(ec.cpostal,'---'), IFNULL(ec.DireccionNota,'---'),"
				+ " if(EE.id not IN (1,2,99),max(PR.ptimestamp),'---') "+
				"	,deO.Nombre FROM ecommerce_pedido PE "
				+ " INNER JOIN ecommerce_pedido_articulos_req PR  ON PE.idPedido = PR.idPedido AND PR.idEmpresa = PE.idEmpresa "+
				"	INNER JOIN depositos deO ON deO.idDeposito=PR.Deposito AND PR.idEmpresa=deO.idEmpresa "+
				"	INNER JOIN ecommerce_estado_encuentra EE ON PE.EstadoEncuentra = EE.id AND PE.idEmpresa = EE.idEmpresa "+
				"	LEFT OUTER JOIN ecommerce_pedido_destino PD ON PE.idPedido=PD.idPedido AND PE.idEmpresa = PD.idEmpresa "+
				" 	INNER JOIN ecommerce_canal_ml ECML ON ECML.id=PE.idCanalML AND PE.idEmpresa=ECML.idEmpresa "	+
				"   INNER JOIN ecommerce_pedido_articulos ARTMOD ON PE.idPedido=ARTMOD.idPedido and PR.idArticulo=ARTMOD.idArticulo AND PE.idEmpresa= ARTMOD.idEmpresa " +
				innerDepo+
				"	LEFT OUTER JOIN ecommerce_pedido_factura PF ON PF.idPedido = PE.idPedido AND PE.idEmpresa=PF.idEmpresa "+
				"   LEFT OUTER JOIN ecommerce_import_venta IV ON IV.idventa=PE.idPedido AND PE.idEmpresa= IV.idEmpresa "+
				"   LEFT OUTER JOIN depositos dep ON dep.idDeposito=PD.idDestino AND PD.idEmpresa=dep.idEmpresa "+
				"	LEFT OUTER JOIN art_descripcion ad on ad.id=ARTMOD.idArticulo AND ad.idEmpresa=PD.idEmpresa "+
				"LEFT OUTER JOIN ecommerce_pedidos_log lup \r\n" + 
				"ON PE.idPedido=lup.idPedido AND PE.idEmpresa = lup.idEmpresa AND lup.Estado=3 AND lup.IdUsuario > 0 \r\n" + 
				"AND lup.idpedido=pr.idpedido \r\n" + 
				"LEFT OUTER JOIN usuarios ulup ON ulup.idUsuario=lup.IdUsuario AND ulup.idEmpresa=lup.IdEmpresa \r\n" + 
				"LEFT OUTER JOIN ecommerce_import_clientes ec ON ec.idPedido=PE.idPedido AND ec.IdEmpresa=PE.IdEmpresa "+
				"LEFT OUTER JOIN ecommerce_shipping_type PST ON PST.id = PE.shippingType "+
				
				"	where PE.idEmpresa="+idEmpresa+" ";
				groupBy = "GROUP by\r\n" + 
				"deO.Nombre,  PE.idPedido, PE.UnidadesTotal, EE.descripcion ,PR.idArticulo, PR.Deposito, PR.CantidadRequerida, \r\n" + 
				"PR.CantidadProcesasa, PR.cantConfirmada, PR.RTimeStamp, PR.CTimeStamp, PR.PTimeStamp,ov, \r\n" + 
				"fac , PE.FormaPago, CONCAT(PE.descripcion,'<br/>',IFNULL(clicedula, '')), PE.URLetiqueta, \r\n" + 
				"PF.urlFactura, EE.id, PD.idDestino,ECML.nombre,ARTMOD.modificado,ARTMOD.cambio,PR.DespachoTimeStamp, \r\n" + 
				"PD.retiroCFormulario, PD.personaRetira, PE.estadoEcommerce, dep.Nombre, \r\n" + 
				"PE.idFenicio,'',ad.descripcion\r\n" + 
				",lup.IdUsuario,IF(PR.RTimeStamp=PR.RetiroTimeStamp,'---',PR.RetiroTimeStamp), PR.docVisual,ARTMOD.NotaArticulo, if(pe.idPedido = pe.subpedido,'',pe.subpedido), PST.id, PST.descripcion,  \r\n" + 
				"PE.Facturado, if(PD.idDestino=700000,CONCAT('',PD.tracking),PD.tracking), IFNULL(ec.mail,'---'), IFNULL(ec.telefono,'---'), \r\n" + 
				"IFNULL(ec.Departamento,'---'),IFNULL(ec.Ciudad,'---'), IFNULL(ec.Direccion,'---'), IFNULL(ec.cpostal,'---'), IFNULL(ec.DireccionNota,'---')  ";
		
			}
			else
			{
				qry = "SELECT PE.idPedido, PE.UnidadesTotal, EE.descripcion, PR.idArticulo, PR.Deposito, PR.CantidadRequerida, PR.CantidadProcesasa, PR.cantConfirmada, PR.RTimeStamp, PR.CTimeStamp, PR.PTimeStamp, IFNULL(PF.IdOrden, 0) ov, IFNULL(PF.idFactura, 0) fac, PE.FormaPago, CONCAT(PE.descripcion,'<br/>', IFNULL(clicedula, '')), PE.URLetiqueta, IFNULL(PF.urlFactura, ''), \r\n" + 
						"EE.id, IFNULL(PD.idDestino, 0),ECML.nombre,ARTMOD.modificado,ARTMOD.cambio,PR.DespachoTimeStamp, PD.retiroCFormulario, PD.personaRetira, PE.estadoEcommerce, IFNULL(dep.Nombre, 0), PE.idFenicio,'',ad.descripcion, "
						+ "IFNULL(GROUP_CONCAT(ulup.Nombre,' ',ulup.Apellido),'---'),IF(PR.RTimeStamp=PR.RetiroTimeStamp,'---',PR.RetiroTimeStamp), PR.docVisual,ARTMOD.NotaArticulo, if(pe.idPedido = pe.subpedido,'',pe.subpedido), PST.id, PST.descripcion,  "
						+ "PE.Facturado, if(PD.idDestino=700000,CONCAT('',PD.tracking),PD.tracking)\r\n" + 

						" ,deO.Nombre  FROM ecommerce_pedido PE\r\n" + 
						" INNER JOIN ecommerce_pedido_articulos_req PR ON PE.idPedido = PR.idPedido AND PR.idEmpresa = PE.idEmpresa\r\n "+
						" INNER JOIN depositos deO ON deO.idDeposito=PR.Deposito AND PR.idEmpresa=deO.idEmpresa "+ 
						"INNER JOIN ecommerce_estado_encuentra EE ON PE.EstadoEncuentra = EE.id AND PE.idEmpresa = EE.idEmpresa\r\n" + 
						"LEFT OUTER\r\n" + 
						"JOIN ecommerce_pedido_destino PD ON PE.idPedido=PD.idPedido AND PE.idEmpresa = PD.idEmpresa\r\n" + 
						"INNER JOIN ecommerce_canal_ml ECML ON ECML.id=PE.idCanalML AND PE.idEmpresa=ECML.idEmpresa\r\n" + 
						"INNER JOIN ecommerce_pedido_articulos ARTMOD ON PE.idPedido=ARTMOD.idPedido AND PR.idArticulo=ARTMOD.idArticulo AND PE.idEmpresa= ARTMOD.idEmpresa\r\n" + 
						innerDepo+
						"LEFT OUTER JOIN ecommerce_pedido_factura PF ON PF.idPedido = PE.idPedido AND PE.idEmpresa=PF.idEmpresa\r\n" + 
						"LEFT OUTER JOIN ecommerce_import_venta IV ON IV.idventa=PE.idPedido AND PE.idEmpresa= IV.idEmpresa\r\n" + 
						"LEFT OUTER JOIN depositos dep ON dep.idDeposito=PD.idDestino AND PD.idEmpresa=dep.idEmpresa\r\n" + 
						"LEFT OUTER JOIN art_descripcion ad ON ad.id=ARTMOD.idArticulo AND ad.idEmpresa=PD.idEmpresa\r\n" + 
						"LEFT OUTER JOIN ecommerce_pedidos_log lup ON PE.idPedido=lup.idPedido AND PE.idEmpresa = lup.idEmpresa AND lup.Estado=3 AND lup.IdUsuario > 0 AND lup.idpedido=pr.idpedido\r\n" + 
						"LEFT OUTER\r\n" + 
						"JOIN usuarios ulup ON ulup.idUsuario=lup.IdUsuario AND ulup.idEmpresa=lup.IdEmpresa\r\n" + 
						"LEFT OUTER JOIN ecommerce_shipping_type PST ON PST.id = PE.shippingType "+
						"WHERE PE.idEmpresa="+idEmpresa+" ";  
				
						groupBy = "GROUP by\r\n" +
						" deO.Nombre, PE.idPedido, PE.UnidadesTotal, EE.descripcion,PR.idArticulo, PR.Deposito, PR.CantidadRequerida, \r\n" + 
						"PR.CantidadProcesasa, PR.cantConfirmada, PR.RTimeStamp, PR.CTimeStamp, PR.PTimeStamp,ov, \r\n" + 
						"fac, PE.FormaPago, CONCAT(PE.descripcion,'<br/>', IFNULL(clicedula, '')), PE.URLetiqueta, \r\n" + 
						"PF.urlFactura, EE.id, PD.idDestino,ECML.nombre,ARTMOD.modificado,ARTMOD.cambio,PR.DespachoTimeStamp, \r\n" + 
						"PD.retiroCFormulario, PD.personaRetira, PE.estadoEcommerce, dep.Nombre, \r\n" + 
						"PE.idFenicio,'',ad.descripcion\r\n" + 
						",lup.IdUsuario,IF(PR.RTimeStamp=PR.RetiroTimeStamp,'---',PR.RetiroTimeStamp), PR.docVisual,ARTMOD.NotaArticulo, if(pe.idPedido = pe.subpedido,'',pe.subpedido), PST.id, PST.descripcion,  \r\n" + 
						"PE.Facturado, if(PD.idDestino=700000,\r\n" + 
						"CONCAT('',PD.tracking),PD.tracking)";
			}
	
		}
	
	
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		String query = qry+qPedido+qFenicio+qArticulo+qEstado+qDestino+qFacturados+qFormaPago+qEtiqueta+qFechaI+qDepCero+qTienda+groupBy+limit;
		return econ.encuentraVerPedidos(query,idEmpresa,detalleCompleto);
		
		
		
	}
	catch (Exception e)
	{
		
	}
	
	return null;
}

public  List<DataDetallePedidoPrint> darListaDetallePedidosEcommercePrint(String pedidosIn,int idEmpresa) 
{
	String qPedido = " and EP.idPedido in ("+pedidosIn+") ";
	
	
	
	String qry = "SELECT EP.idPedido, EIC.Nombre, EIC.Apellido, EIC.cedula, EIC.Direccion, EIC.DireccionNota, EIC.mail, EIC.telefono ,   \r\n" + 
			"EIV.porcDescuento, EIV.cliRuc, EIV.clicedula, EIV.FP, EIV.importePago, EIVL.idArticulo, EIVL.cantidad, EIVL.precioImp, \r\n" + 
			"EIV.Comentario, AB.Barra,   AB.Descripcion, IFNULL (EPD.CostoEnvio, 0)\r\n" + 
			"FROM   ecommerce_pedido EP  \r\n" + 
			"INNER JOIN ecommerce_pedido_articulos EPA ON EP.idPedido = EPA.idPedido  \r\n" + 
			"INNER JOIN ecommerce_import_venta EIV ON EIV.idVenta = EPA.idPedido  \r\n" + 
			"INNER JOIN ecommerce_import_ventalinea EIVL ON EIVL.idVenta = EPA.idPedido AND EIVL.idArticulo = EPA.idArticulo  \r\n" + 
			"INNER JOIN ecommerce_import_clientes EIC ON EIC.idPedido = EPA.idPedido  \r\n" + 
			"LEFT OUTER JOIN \r\n" + 
			"(	SELECT AB.idArticulo,A.Descripcion, MAX(AB.Barra) 'Barra', AB.IdEmpresa \r\n" + 
			"	FROM artbarra AB \r\n" + 
			"	INNER JOIN articulos A ON A.idArticulo = AB.idArticulo AND A.IdTipo=1\r\n" + 
			"	WHERE AB.IdEmpresa = "+idEmpresa+" GROUP BY AB.idArticulo,AB.IdEmpresa,A.Descripcion \r\n" + 
			") AB ON AB.idArticulo = EPA.idArticulo    \r\n" + 
			"LEFT OUTER JOIN ecommerce_pedido_destino EPD ON EPD.idPedido= EP.idPedido\r\n" + 
			"WHERE EP.IdEmpresa = "+idEmpresa+"  \r\n" + 
			"";
	
		
			
			
	
	
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		System.out.println(qry+qPedido);
		String query = qry+qPedido;
		return econ.encuentraVerPedidosPrint(query,idEmpresa,true);
		
		
		
	}
	catch (Exception e)
	{
		
	}
	
	return null;
}

public  List<DataDetallePedido> darListaDetallePedidosPendienteEcommerce(boolean ml, int idEmpresa) 
{
	
	
	String qry = "SELECT EP.idPedido,0,Ee.descripcion, AR.idArticulo,0,AR.CantidadRequerida,AR.CantidadProcesasa,AR.CantidadProcesasa,EP.stampTime,EP.stampTime,EP.stampTime,0,0,Ep.descripcion,'','','', EE.id "+
				"	from  ecommerce_pedido_articulos_req AR "+
				"	inner join ecommerce_pedido EP ON EP.idPedido=AR.idPedido "+
				"	inner join ecommerce_estado_encuentra EE on EE.id=EP.EstadoEncuentra "+
				"	where "+
				"	EP.idPedido not in (select distinct IdPedido from ecommerce_pedidos_log where Estado=4 and `level` = 4 or Estado=99) "+
				"	AND EP.ML=1 "+
				"	AND date(EP.stampTime)>='20180604' "+
				"	order by EP.stampTime asc";
	
	
	
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		
		return econ.encuentraVerPedidosPendientes(qry,idEmpresa);
		
		
		
	}
	catch (Exception e)
	{
		
	}
	
	return null;
}

public  void vaciarOjoEcommerce_pedido(Long idPedido, int idE) 
{	
	try 
	{		
		String query = "delete from ojostienenarticulos where idarticulo like '"+idPedido+"\\_%' and idempresa="+idE; 			
			
		eper.persistir(query);
	} 
	catch (Exception e) 
	{		
		e.printStackTrace();		
	}
	
}

public  void vaciarOjoEcommerce(Long idPedido, int idE, boolean pickup) 
{
	
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		/*cone = econ.getConnection();
		String query = "SELECT r.cantidadrequerida,r.idArticulo,o.idojo ojo from ecommerce_pedido_articulos_req r "+
				"inner join ojostienenarticulos o on r.idarticulo=o.idarticulo "+
				"WHERE r.idEmpresa="+idE+" AND r.idpedido="+idPedido+" AND r.cantidadprocesasa=1 AND o.idOjo IN "+
				"(SELECT idojo FROM ojostienenarticulos WHERE idEmpresa="+idE+" AND idarticulo="+idPedido+") "; 
		
		List<DataIDDescripcion> articulos = econ.darListaDataIdDescripcion(query);
		articulos.remove(0);
		
		String ojos = "";
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ojostienenarticulos where idEmpresa="+idE+" AND idArticulo = '"+idPedido+"';");
		for(DataIDDescripcion a :articulos){
			sb.append("update ojostienenarticulos set cantidad=cantidad-"+a.getId()+" where idEmpresa="+idE+" AND idArticulo = '"+a.getDescripcion()+"'  and idojo= '"+a.getDescripcionB()+"';");
			sb.append("insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,tipo,idEmpresa) values "+
					"('"+a.getDescripcion()+"','"+a.getDescripcionB()+"','',"+a.getId()+","+0+",'DEL',"+idE+");");
			ojos += "'"+a.getDescripcionB()+"',";
		}
		ojos = ojos.substring(0,ojos.length()-1);
		sb.append("delete from ojostienenarticulos where idEmpresa="+idE+" AND idojo in ("+ojos+") and cantidad=0;");
		
		eper.persistir(sb.toString());*/
		cone = econ.getConnection();
		List<DataIDDescripcion> articulos = new ArrayList<>();
		if(!pickup) {
			String query = "SELECT r.cantidadrequerida,r.idArticulo,o.idojo ojo from ecommerce_pedido_articulos_req r "+
					"inner join ojostienenarticulos o on r.idarticulo=o.idarticulo "+
					"WHERE r.idpedido="+idPedido+" AND r.cantidadprocesasa=1 AND o.idOjo IN "+
					"(SELECT idojo FROM ojostienenarticulos WHERE idarticulo="+idPedido+") "; 
			
			articulos = econ.darListaDataIdDescripcion(query);
			articulos.remove(0);
		}		
		
		String ojos = "";
		StringBuilder sb = new StringBuilder();
		sb.append("delete from ojostienenarticulos where idArticulo = '"+idPedido+"';");
		for(DataIDDescripcion a :articulos){
			sb.append("update ojostienenarticulos set cantidad=cantidad-"+a.getId()+" where idArticulo = '"+a.getDescripcion()+"'  and idojo= '"+a.getDescripcionB()+"';");
			ojos += "'"+a.getDescripcionB()+"',";
		}
		
		if(!ojos.equals("")) {
			ojos = ojos.substring(0,ojos.length()-1);
			sb.append("delete from ojostienenarticulos where idojo in ("+ojos+") and cantidad=0;");
		}
		
		
		eper.persistir(sb.toString());
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
}




public  boolean persistir (String query) 
{
	
	try 
	{
		
		return eper.persistir(query);		
		
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return false;
	}
	
}


//NICO
public  String Consulta (String query){
	

	String resp="";
	try 
	{
	
		resp = eper.Consulta(query);	
		return 	resp;
		
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return 	resp;
	}
	
}


public  boolean persistirLote (List<StringBuilder> querys) 
{
	
	try 
	{
	
		for (StringBuilder query : querys) 
		{
			 eper.persistir(query.toString());
		}
		
	
		return true;
		
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return false;
	}
	
}


public  void ConfirmarPickingAll(int idPicking, boolean picked, int idEmpresa) 
{
	String consulta = "";
	
	
	if(picked)
	{
		consulta="update reposicion_articulos set Picked = Cantidad where idEmpresa="+idEmpresa+" AND idPicking="+idPicking;
	}
	else
	{
		consulta="update reposicion_articulos set Verif = Picked where idEmpresa="+idEmpresa+" AND idPicking="+idPicking;
	}
	
	
	try 
	{
		
		
		eper.persistir(consulta);
		
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
	}
	
	
	
}







public  void borrarVM(int idEmpresa) 
{
	
	
	try 
	{
		
		//eper.persistir("INSERT INTO reposicion_articulos_historico SELECT * FROM reposicion_articulosvm");
		eper.persistir("Delete from reposicion_articulosvm where idEmpresa ="+idEmpresa);
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		
	}
	
}

public  String query_logPedido(Long idPedido, int idUsuario, int estado, String mensaje, int level, int idEmpresa) 
{
	
	
	try 
	{
	
		return "INSERT INTO `ecommerce_pedidos_log` (`IdPedido`,`IdUsuario`, `Estado`, `Mensaje`,`level`,`idEmpresa`) VALUES ("+idPedido+","+idUsuario+", "+estado+", '"+mensaje+"',"+level+","+idEmpresa+"); ";
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		return "";
	}
	
}

public  void logPedido(Long idPedido, int idUsuario, int estado, String mensaje, int level, int idEmpresa) 
{
	
	
	try 
	{
	
		eper.persistir("INSERT INTO `ecommerce_pedidos_log` (`IdPedido`,`IdUsuario`, `Estado`, `Mensaje`,`level`,`idEmpresa`) VALUES ("+idPedido+","+idUsuario+", "+estado+", '"+mensaje+"',"+level+","+idEmpresa+");");
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		
	}
	
}





public  List<DataEcommerce_LogPedido> darLogPedidoEcommerce(String fechaSQL, String idPedido, String level, int idEmpresa) 
{
	
	int tope = 1500;
	String queryFecha="";
	String queryPedido="";
	String queryLevel="";
	String queryTope="";
	String queryOrder = " order by fecha desc ";
	
	if(fechaSQL.equals("") && idPedido.equals("") && level.equals(""))
	{
		queryTope = " limit "+tope;
	}
	else
	{
		if(!fechaSQL.equals(""))
		{
			queryFecha = " AND fecha >= '"+fechaSQL+"' ";
		}
		if(!idPedido.equals(""))
		{
			queryPedido = "  AND IdPedido = "+idPedido+" ";
		}
		if(!level.equals(""))
		{
			queryLevel = " AND level in ("+level+")  ";
		}
	}
	
	String query = "select idPedido, mensaje, level, fecha, IF(l.IdUsuario=0, 'Indefinido',CONCAT(u.Nombre,' ',u.Apellido)) "
				+ "from ecommerce_pedidos_log l "
				+ "LEFT OUTER JOIN usuarios u ON u.idUsuario=l.IdUsuario AND u.idEmpresa=l.IdEmpresa "
				+ "where l.idEmpresa ="+idEmpresa+" AND true"+queryFecha+queryPedido+queryLevel+queryOrder+queryTope;
	
	

	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return econ.darListaLogEcommerce(query);
		
	}
	catch (Exception e)
	{
		return null;
	}
	
	
	
}


public  void guardarEnvioEcommerce(int depoEnvio,	List<DataIDDescripcion> pedidosSave, Usuario u, int idDepo, int idRazon,int idEmpresa) 
{
	// guarda el cabezal
	String queryCab = "INSERT INTO `envio_encuentra_documento` (`idDestino`, `idEnvio`, `idRazon`,`usuario`,`idOrigen`,`idEmpresa`) VALUES ("+depoEnvio+", '0',"+idRazon+", "+u.getNumero()+","+idDepo+","+idEmpresa+");";
	StringBuilder sb = null;
	

	//guarda las lineas
	try 
	{
		System.out.println("ALTA ENVIO");
		int idEnvio=eper.persistirDarUltimo(queryCab, "envio_encuentra_documento", "idDocumento",idEmpresa);
		
		for (DataIDDescripcion p : pedidosSave) 
		{
			sb = new StringBuilder();
			sb.append("INSERT INTO `envio_encuentra_documento_lineas` (`idDocumento`, `idArticulo`, `Cantidad`,`idEmpresa`) VALUES ("+idEnvio+", '"+p.getDescripcion()+"', "+p.getId()+","+idEmpresa+");");
			sb.append( "UPDATE `ecommerce_pedido_destino` SET `Cerrado`='1' WHERE  `idEmpresa`="+idEmpresa+" AND `idPedido`="+p.getDescripcion()+";");
			/*persistir(queryDet);
			persistir(queryCerrar);	*/
			
			persistir(sb.toString());
			
				String send="";
				switch (depoEnvio){
				case 900000: send="hacia MIRTRANS";
					break;
				case 800000: send="hacia DAC";
					break;
				case 700000: send="hacia UES";
					break;
				case 600000: send="hacia DISTRICAD";
					break;
				case 500000: send="hacia DHL";
					break;
				default: send="hacia Pickup "+depoEnvio;
					break;
				}
				
				logPedido(Long.parseLong(p.getDescripcion()), u.getNumero(), 40, "despachando pedido, envio asignado "+idEnvio+" "+send, 0,idEmpresa);
									
			updateEcommerceEstado(Long.parseLong(p.getDescripcion()), 34,idEmpresa, u);//despachando pedido
			
		}
	
	}
	catch (Exception e)
	{
		e.printStackTrace();
		logger(0, 0, "error grabando remitos: "+e.getStackTrace(),idEmpresa);
	}
	
}


public  DataIDDescripcion darTipoShippingDatosDeposito(int idDepo, int idEmpresa) 
{
	 DataIDDescripcion retorno = new DataIDDescripcion(idDepo,"0");
	 retorno.setDescripcionB("0");
	List<DataIDDescripcion> detallesDepo = darListaDataIdDescripcionConsulMYSQL("select customShipping, encuentraShipping from depositos where idEmpresa="+idEmpresa+" AND idDeposito = "+idDepo);
	
	for (DataIDDescripcion d : detallesDepo) 
	{
		retorno.setId(idDepo);
		retorno.setDescripcion(d.getId()+""); //custom
		retorno.setDescripcionB(d.getDescripcion());//encuentra
		
		
	}
	
	return retorno;
}


public  boolean esMLPedido(Long idPedido, int idEmpresa)
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return econ.hayReg("select 1 from ecommerce_pedido where  idEmpresa="+idEmpresa+" AND  idPedido = "+idPedido+" and ML= 1");
		
	}
	catch (Exception e)
	{
		return false;
	}
}



public  DataIDDescripcion darDataIdDescripcion(String query) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return econ.darDataIdDescripcion(query);
		
	}
	catch (Exception e)
	{
		return null;
	}
}


public  DataIDDescripcion darEnvioPedido(Long idPedido, int idEmpresa) 
{
	String q = "select EE.idDeposito, EE.nombre, d.direccion from "+
				"	ecommerce_pedido_destino EPD "+
				"	inner join ecommerce_envioml EE on EPD.idDestino = EE.idDeposito AND EE.idEmpresa=EPD.idEmpresa"+
				"	inner join depositos d on d.iddeposito=EE.idDeposito and d.idEmpresa=EE.idEmpresa"+
				"	where EPD.idEmpresa="+idEmpresa+" AND  EPD.idPedido = "+idPedido;
	
	
	List<DataIDDescripcion> lista = darListaDataIdDescripcionConsulMYSQL(q);
	
	
	try
	{
		return lista.get(0);
	}
	catch (Exception e)
	{
		
	}
	
	return null;
}

public  DataIDDescripcion darEnvioPedido(int dep, int idEmpresa) 
{
	String st = "   								   						    ";
	//url = ImprimirEtiquetasNuevas(ar,idEmp,env,"CD 9000"," Av. Italia 4346"+st+"Tel.:2613 7566 		  Montevideo-Uruguay",1);
	
	String q = "select 0, nombre, CONCAT(direccion,'"+st+"','Tel.:',telefono,' 		  Montevideo-Uruguay') from depositos "+
				"	where idEmpresa="+idEmpresa+" AND  idDeposito = "+dep;
	
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e1) {
		
		e1.printStackTrace();
	}
	List<DataIDDescripcion> retorno = econ.darListaDataIdDescripcion(q);
	
	
	try
	{
		retorno.remove(0);
		return retorno.get(0);
	}
	catch (Exception e)
	{
		DataIDDescripcion d = new DataIDDescripcion(0,"");
		d.setDescripcionB("");
		return d;
	}
	
	
}


public  boolean ecommercePedidoTieneOV(Long idPedido, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return econ.hayReg("select * from ecommerce_pedido_factura where idEmpresa="+idEmpresa+" AND IdPedido="+idPedido+" and IdOrden !=0");
		
	}
	catch (Exception e)
	{
		return false;
	}
	
}


public  boolean pedidoPasoXEstados(String idPedido, List<Integer> estados, int idEmpresa){
	boolean first = true;
	String cadena ="(";
	for (Integer e: estados){
		if(first)
		{
			first =false;
		}
		else{
			cadena+=",";
		}
		cadena += e;	
	}
	cadena+=")";
	
	String consulta="select * from ecommerce_pedidos_log where idEmpresa="+idEmpresa+" AND  IdPedido="+idPedido+" and Estado in "+cadena;
	@SuppressWarnings("unused") Connection cone;
	try{
		cone=econ.getConnection();
		return econ.hayReg(consulta);
	}
	catch(Exception e){
		return false;
	}
}

public  boolean hayRegistro(String consulta){
	@SuppressWarnings("unused") Connection cone;
	try{
		cone=econ.getConnection();
		return econ.hayReg(consulta);
	}
	catch(Exception e){
		return false;
	}
}

public  DataIDDescripcion BuscarAlmacenEcommerce(int dep, int idEmpresa){
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	String 	consulta = "select IdAlmacenERP,Contactos from ecommerce_almacenes  where idEmpresa="+idEmpresa+" AND  habilitado = 1 and IdAlmacenERP="+dep;
	
	
	try
	{
		DataIDDescripcion retorno =  econ.darDataIdDescripcion(consulta);
		return retorno;
	}
	catch (Exception e)
	{
		return new DataIDDescripcion();
	}
}

public  boolean AltaNota(Nota n,int idEmpresa){
	
	@SuppressWarnings("unused") Connection cone;
	try {
		

	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	try
	{
		boolean retorno =eper.persistir("insert into ecommerce_Nota (idPedido,idUsuario,txtNota,idEmpresa) values("+n.getIdPedido()+", "+n.getIdUsuario()+", '"+n.getTxtNota()+"',"+idEmpresa+");");
		return retorno;
	}
	catch (Exception e)
	{
		return false;
	}
}


public  List<Nota> ListaNota(Long idPedido, int idEmpresa){
	
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	List<Nota> retorno = new ArrayList<>();
	
	try
	{
		
		 retorno = econ.PersistirNotas(idPedido, idEmpresa);
		return retorno;
	}
	catch (Exception e)
	{
		return retorno;
	}
}


public  List<DataArticuloEcommercePedido> ArticulosPedidosEcommercePendientes(int idDeposito,int subE, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	try
	{
		String consulta="";
		
	if (subE==2){
		consulta = "select idPedido,idArticulo,Deposito,CantidadRequerida,RTimeStamp,subestado,CTimeStamp from ecommerce_pedido_articulos_req where idEmpresa="+idEmpresa+" AND  Deposito = "+idDeposito+" and Confirmado = 1 and subestado="+subE+" order by CTimeStamp desc limit 100";
	}
	else{
		
		consulta = "select r.idPedido,r.idArticulo,r.Deposito,r.CantidadRequerida,r.RTimeStamp,r.subestado,CTimeStamp from ecommerce_pedido_articulos_req r inner join ecommerce_pedido p on p.idPedido=r.idPedido AND p.idEmpresa=r.idEmpresa  where r.idEmpresa="+idEmpresa+" AND  r.Deposito = "+idDeposito+" and r.Confirmado = 0 and r.subestado="+subE+" and p.EstadoEncuentra!=99";
	}
		
		return econ.darArtReqEcommerce(consulta);
		
	}
	catch (Exception e)
	{
		return null;
	}

}


public  boolean CambioSubestadoEcommercePedido(Long pedido, String art, int subE,int dep, int idEmpresa){
	
	
	
	
	
	try
	{

		List<DataIDDescripcion> subEstados = darListaDataIdDescripcionConsulMYSQL("select sub,nombre from ecommerce_subestado where idEmpresa="+idEmpresa+" ");
		
		String estado = Consulta("select EE.id from ecommerce_estado_encuentra EE inner join ecommerce_pedido EP on EE.id=EP.EstadoEncuentra AND EE.idEmpresa=EP.idEmpresa  where EP.idEmpresa="+idEmpresa+" AND  EP.idPedido="+pedido);	
		
		String descSEstado = "";
		for (DataIDDescripcion se : subEstados) 
		{
			if(se.getId()==subE)
			{
				descSEstado=se.getDescripcion();
			}
		}	
		
					
			
		String query="";
		if(subE==1){
			 query ="update ecommerce_pedido_articulos_req set subestado="+subE+", fechaImpreso=CURRENT_TIMESTAMP where  idEmpresa="+idEmpresa+" AND idPedido="+pedido+" and idArticulo='"+art+"' and Deposito="+dep;
		}
		else if(subE==2){
			 query ="update ecommerce_pedido_articulos_req set subestado="+subE+", fechaConfirmado=CURRENT_TIMESTAMP where  idEmpresa="+idEmpresa+" AND idPedido="+pedido+" and idArticulo='"+art+"' and Deposito="+dep;
		}
		else{
			query ="update ecommerce_pedido_articulos_req set subestado="+subE+ "where idEmpresa="+idEmpresa+" AND  idPedido="+pedido+" and idArticulo='"+art+"' and Deposito="+dep;
		}
		
		boolean retorno =eper.persistir(query);
		if (retorno){
				logPedido(pedido, 0, Integer.parseInt(estado), descSEstado+" en local",4,idEmpresa);			
		}
		
		return retorno;
	}
	catch (Exception e)
	{
		return false;
	}	
}


public  List<DataIDDescDescripcion> EcommercedarCanalesML(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}

	
	String consulta ="select ml.id, ml.idUsr, ml.secret,ml.seller from ecommerce_canal_ml ml where idEmpresa="+idEmpresa+" AND  ml.id > 0";
	
	try
	{
		List<DataIDDescDescripcion> retorno =  econ.darListaDataIdDescDescripcionCanalesML(consulta);
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}


public  int darDestinoEcommercePedido(Long idPedido, int idEmpresa) 
{
	try
	{
		List<DataIDDescripcion> lista = darListaDataIdDescripcionConsulMYSQL("select idDestino,'' from ecommerce_pedido_destino where idEmpresa="+idEmpresa+" AND  idPedido = "+idPedido);
		return lista.get(0).getId();
	}
	catch (Exception e) 
	{
		return 0;
	}
	
	
	
}


public  Hashtable<Long, DataIDDescripcion> darDestinosEcommercePedidos(String ins, int idEmpresa) 
{
	Hashtable<Long, DataIDDescripcion> destinos = new Hashtable<>();
	try
	{
		List<DataIDDescripcion> lista = darListaDataIdDescripcionConsulMYSQL("SELECT D.idDeposito, EP.idPedido, D.Nombre FROM \r\n" + 
				"ecommerce_pedido_destino EP\r\n" + 
				"INNER JOIN depositos D ON EP.idDestino = D.idDeposito AND D.IdEmpresa = EP.IdEmpresa\r\n" + 
				"WHERE EP.IdEmpresa = "+idEmpresa+" AND EP.idPedido IN ("+ins+")");
		
		
		//"idDeposito"	"idPedido"	"Nombre"
		//ID			Descripcion		DescripcionB
		
		
		for (DataIDDescripcion des : lista) 
		{
			destinos.put(Long.parseLong(des.getDescripcion()), des);
		}
		
		return destinos;
	}
	catch (Exception e) 
	{
		return destinos;
	}
	
}


public  boolean IngresarFactura(String fact, Long pedido, int idEmpresa){
	
	boolean retorno=false;
	
	try
	{
		boolean existe =hayRegistro("select idPedido from ecommerce_pedido_factura where idEmpresa="+idEmpresa+"  AND  idPedido= "+pedido);
		
		if (existe){
			retorno =eper.persistir("update ecommerce_pedido_factura set idFactura ="+fact+" where idEmpresa="+idEmpresa+"  AND  idPedido='"+pedido+"'");
			
		}
		else{
			retorno =eper.persistir("insert into ecommerce_pedido_factura (idFactura,idPedido,idOrden,urlFactura,Cominicado,idEmpresa) values ("+fact+","+pedido+","+fact+","+"'',"+0+","+idEmpresa+")");
			
		}
	return retorno;	
	}
	catch (Exception e)
	{
		return false;
	}
}



public  List<DataIDDescripcion> DarArticulosXEstanteria(String estanteria, int idEmpresa) 
{
	String estIn="";
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	
	try
	{
		if(!estanteria.equals(""))
		{
			estIn=" where OJ.idEmpresa="+idEmpresa+" AND OJ.IdEstanteria='"+estanteria+"'";
		}
		else
		{
			estIn=" where OJ.idEmpresa="+idEmpresa;
		}
		
		String consulta="SELECT sum(OT.Cantidad),OJ.IdEstanteria "+
						"FROM ojos OJ " +
						"inner join ojostienenarticulos OT on OJ.idEmpresa=OT.idEmpresa AND OJ.idOjo=OT.idOjo "+estIn+ 
					 	" group by(OJ.idEstanteria) order by OJ.idEstanteria asc ";
		
		List<DataIDDescripcion> retorno = econ.darIdDescripcion(consulta);  
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}

public  DataIDDescripcion darCustomShipp(Long idPedido, int idEmpresa) 
{
	String query = "select d.customShipping,'' from depositos d "+
				   "inner join ecommerce_pedido_destino p on p.idEmpresa=d.idEmpresa AND p.idDestino=d.idDeposito "+
				   "where p.idEmpresa="+idEmpresa+" AND p.idPedido="+idPedido;
	
	try
	{
		@SuppressWarnings("unused") Connection cone;		
		cone = econ.getConnection();
	
		List<DataIDDescripcion> lista = darListaDataIdDescripcionConsulMYSQL(query);
		
		return lista.get(0);
	}
	catch (Exception e)
	{
		
	}
	
	return null;
}




public  List<DataOjoArticulo> DarArtsSinMedidas(int idEmpresa, int deposito) 
{
	String query = "select SUBSTRING(a.idArticulo,1,10),o.idModulo,o.idEstante,ota.idOjo,o.idEstanteria,'','','' from ojos o "+
				   "inner join ojostienenarticulos ota on ota.idOjo=o.idOjo ota.idEmpresa=o.idEmpresa "+
				   "inner join Articulos a on a.idArticulo=ota.idArticulo a.idEmpresa=ota.idEmpresa"+
			       "where a.idEmpresa="+idEmpresa+" AND (a.AnchoCaja*a.AltoCaja*a.ProfCaja=0) and "+
				   "a.idArticulo like '%.%'";
	
	
	try
	{
		@SuppressWarnings("unused") Connection cone;		
		cone = econ.getConnection();
		
		return econ.darArticulosOjos(query, false, deposito, idEmpresa);	

	}
	catch (Exception e)
	{
		e.printStackTrace();
		return new ArrayList<>();
	}
	
}

public  boolean ingresarMedidasArticulo(List<ArticuloMedidas> arts, int idEmpresa) 
{
	boolean b=false;
	
	try
	{		
		int cont=0;
		StringBuilder query = new StringBuilder();
		StringBuilder queryFinal = new StringBuilder();
		List<StringBuilder> listaQuerys= new ArrayList<>(); 
		query.append("update Articulos set ");
		boolean pri=true;
		
		for(ArticuloMedidas a:arts){
			
			if(listaQuerys.size()==50){
				for (StringBuilder q:listaQuerys){
					queryFinal.append(q);
					queryFinal.append(";");
				}
				b = eper.persistir(queryFinal.toString());
				queryFinal = new StringBuilder();
				listaQuerys= new ArrayList<>(); 
				
			}
			
			if (cont==1000){
				//AnysisPersistir(query.toString());
				listaQuerys.add(query);
				
			    query= new StringBuilder();
				query.append("update Articulos set ");
				pri=true;
				cont=0;
			}			
			
				if(pri){
					query.append("AnchoCaja="+a.getAncho()+",AltoCaja="+a.getAlto()+",ProfCaja="+a.getProf()+" where idEmpresa="+idEmpresa+"  AND idArticulo='"+a.getArticulo()+"'");
					pri=false;
				}
				else{
					query.append("; update Articulos set ");
					query.append("AnchoCaja="+a.getAncho()+",AltoCaja="+a.getAlto()+",ProfCaja="+a.getProf()+" where idEmpresa="+idEmpresa+"  AND  idArticulo='"+a.getArticulo()+"'");
				}
				cont++;			
		
		}
		if(!query.toString().equals("")){
			//AnysisPersistir(query.toString());
			listaQuerys.add(query);
			
		}
		
		query = new StringBuilder();
		for (StringBuilder q:listaQuerys){
			query.append(q);
			query.append(";");
		}
		b = eper.persistir(query.toString());
		
		//b = eper.persistir(query);
		return b;
	}
	catch(Exception e)
	{
		return b;
	}
}

public  boolean ecommerceRetiroFormulario(Long idPedido, int idEmpresa) 
{
	boolean b=false;
	
	try
	{		
		
		
		String query = "update ecommerce_pedido_destino set retiroCFormulario=1 where idEmpresa="+idEmpresa+" AND idpedido="+idPedido;
		
		b = eper.persistir(query);
		return b;
	}
	catch(Exception e)
	{
		return b;
	}
	
	
}

public  List<DataIDDescripcion> ListaUsuarios(int idGrupo, int idEmpresa){
	
	String query="";
	
	if(idGrupo==0){
		 query = "select idUsuario, concat_ws(' ', nombre, apellido) as persona  from usuarios where idEmpresa="+idEmpresa+" AND idUsuario > 0 and baja=0 ORDER BY apellido, nombre";
	}
	else{
		 query = "select u.idUsuario, concat_ws(' ', u.nombre, u.apellido) as persona \r\n" + 
		 		"from usuarios u \r\n" + 
		 		"inner join seguridad_usuarios su on u.idUsuario=su.idUsuario AND u.idEmpresa=su.idEmpresa \r\n" + 
		 		"where su.idEmpresa="+idEmpresa+" and su.idGrupo= "+idGrupo+" AND u.idUsuario > 0 and baja=0 \r\n" + 
		 		"ORDER BY apellido, nombre;";
	}


	try
	{

		@SuppressWarnings("unused") Connection cone;		
		cone = econ.getConnection();
		
		List<DataIDDescripcion> lista = econ.darListaDataIdDescripcion(query);	

		lista.remove(0);
		return lista;
	}
	catch (Exception e)
	{
	
	}

	return null;
}

public  boolean AsignarQuitarUsuarioAGrupo(List<Integer> lista, int grupo ,String opcion,int idEmpresa)
{
	boolean b=false;
	
	try
	{	
		
		
		String query="";
		
		if(opcion.equals("add")){
			for(Integer l:lista){
				 //query = query+" update usuarios set idGrupo="+grupo+" where idUsuario="+l+";";
				query = query+" insert into seguridad_usuarios (idGrupo,idUsuario,idEmpresa) values ("+grupo+","+l+","+idEmpresa+");";
			}
		}
		else{
			for(Integer l:lista){
				 //query = query+" update usuarios set idGrupo="+grupo+" where idUsuario="+l+";";
				query = query+" delete from seguridad_usuarios where idUsuario="+l+" and idGrupo="+grupo+" and idEmpresa="+idEmpresa+";";
			}
		}
		
		b = eper.persistir(query);
		return b;
	}
	catch(Exception e)
	{
		return b;
	}
	
	
}

public  boolean AsignarPermisosAGrupo(List<String> lista, int grupo, int idEmpresa)
{
	boolean b=false;
	
	try
	{		
			
			String query="update seguridad set Visible=0 where idEmpresa="+idEmpresa+" AND idGrupo="+grupo+";";
			for(String l:lista){
				query = query+" update seguridad set Visible=1 where idEmpresa="+idEmpresa+" AND idmenu='"+l+"' and idGrupo="+grupo+";";
			}
			
			b = eper.persistir(query);
		
		
		return b;
	}
	catch(Exception e)
	{
		return b;
	}
	
	
}


public  List<DataIDDescDescripcion> DarMatrizDepositosUnidades(String fi, String ff, String razones, int idEmpresa){
	
	String query="";
	String finQuery="";
	
	if(!razones.equals("")){
		finQuery=" and RDD.IdRazon in ("+razones+") ";
	}
	
	
		 /*query = "select Destino,sum(CantUnidades),Substring(Fecha,1,10) from documentos "+ 
				 "where Origen=99 and Fecha between '"+fi+" 00:00:00' and '"+ff+" 23:59:59' "+finQuery+" "+ 
				 "group by Destino,Substring(Fecha,1,10) order by (Destino) asc";*/
	
		  /* query = "select t.* from (select Destino,sum(CantUnidades) suma,Substring(Fecha,1,10) fecha from documentos "+ 
				   "where Fecha between '"+fi+" 00:00:00' and '"+ff+" 23:59:59' "+finQuery+" "+
				   "group by Destino,Substring(Fecha,1,10) "+
	"union all "+
	"select ed.idDestino Destino,sum(Cantidad) suma,Substring(Fecha,1,10) fecha from envio_encuentra_documento ed "+
	"inner join envio_encuentra_documento_lineas edl on edl.idDocumento=ed.idDocumento "+
					 "where ed.Fecha between '"+fi+" 00:00:00' and '"+ff+" 23:59:59' "+				 
					 "group by Destino,Substring(Fecha,1,10)) t order by Destino,Substring(Fecha,1,10) asc; ";
	*/
	String order = "	ORDER BY dest,fec asc";
	
	String cons1 = "select DC.Destino dest, sum(DC.CantUnidades) cant, Substring(E.Fecha,1,10) fec  "+
					"	FROM envio E "+
					"	inner join enviodepositos ED on ED.idEnvio=E.idEnvio AND ED.idEmpresa=E.idEmpresa"+
					"	inner join envioestado EE on EE.Id = E.IdEstadoEnvio AND EE.idEmpresa = E.idEmpresa"+
					"	inner join usuarios UC on E.IdChofer = UC.idUsuario AND E.idEmpresa = UC.idEmpresa "+
					"	inner join usuarios UA on UA.idUsuario = E.IdAcompaniante AND UA.idEmpresa = E.idEmpresa "+
					"	inner join transportes T on T.IdTransporte=E.IdTransporte AND T.idEmpresa=E.idEmpresa "+
					"	inner join documentos DC on DC.IdEnvio = E.idEnvio  AND DC.Destino=ED.idDeposito AND DC.idEmpresa = E.idEmpresa"+
					"	inner join razonesdoc RDD on RDD.idRazon = DC.IdRazon AND  RDD.idEmpresa = DC.idEmpresa "+
					"	where E.idEmpresa = "+idEmpresa+" AND true";
	
	String consl2="select DCE.idDestino dest, sum(DCEL.cantidad) cant, Substring(E.Fecha,1,10) fec  "+ 	
				"	FROM envio E "+
				"	inner join enviodepositos ED on ED.idEnvio=E.idEnvio AND ED.idEmpresa=E.idEmpresa"+
				"	inner join envioestado EE on EE.Id = E.IdEstadoEnvio AND EE.idEmpresa = E.idEmpresa"+
				"	inner join usuarios UC on E.IdChofer = UC.idUsuario AND E.idEmpresa = UC.idEmpresa "+
				"	inner join usuarios UA on UA.idUsuario = E.IdAcompaniante AND UA.idEmpresa = E.idEmpresa "+
				"	inner join transportes T on T.IdTransporte=E.IdTransporte AND T.idEmpresa=E.idEmpresa "+
				"	inner join envio_encuentra_documento DCE on DCE.idEnvio = E.idEnvio AND DCE.idDestino=ED.idDeposito AND DCE.idEmpresa=ED.idEmpresa "+
				"	inner join envio_encuentra_documento_lineas DCEL on DCEL.idDocumento = DCE.idDocumento AND DCEL.idEmpresa = DCE.idEmpresa "+
				"	inner join envio_encuentra_razones RDDO on RDDO.idRazon = DCE.idRazon AND RDDO.idEmpresa = DCE.idEmpresa "+
				"	where E.idEmpresa = "+idEmpresa+" AND true";
	
	
	String union = " union all ";	
	String fechaBet = "	AND E.Fecha BETWEEN '"+fi+" 00:00:00' AND '"+ff+" 23:59:59' ";
	String group = " group by dest, fec ";
	
	query="select t.* from ("+cons1+fechaBet+finQuery+group+union+consl2+fechaBet+group+") t "+order;
	
	try
	{

		@SuppressWarnings("unused") Connection cone;		
		cone = econ.getConnection();
		
		List<DataIDDescDescripcion> listadoUnidades = econ.darListaDataIdDescDescripcion(query);	

		
		List<DataIDDescDescripcion> retorno = new ArrayList<>();
		int ultimoDest=0;
		int ultimaCant=0;
		int suma=0;
		String ultimaFecha =""; 
		
		for(DataIDDescDescripcion lu:listadoUnidades){
			if(lu.getId() == ultimoDest && lu.getDescripcion().equals(ultimaFecha)){
				ultimaCant = Integer.parseInt(retorno.get(retorno.size()-1).getDesc());
				suma = ultimaCant+Integer.parseInt(lu.getDesc());
				retorno.get(retorno.size()-1).setDesc(suma+"");
			}
			else{
				retorno.add(lu);
			}
			ultimaCant=0;
			suma=0;
			ultimaFecha=lu.getDescripcion();
			ultimoDest=lu.getId();
		}
		
		return retorno;
	}
	catch (Exception e)
	{
	
	}

	return null;
}

public  boolean AltaGrupoSeguridad(String nombre, int IdEmpresa)
{
	boolean b=false;
	
	try
	{	
		
		
		int idGrupo = eper.persistirDarUltimo("insert into seg_grupos(Nombre, idEmpresa) values ('"+nombre+"', "+IdEmpresa+")", "seg_grupos", "IdGrupo",IdEmpresa);
		String query="insert into seguridad (idmenu,idGrupo,Visible,idEmpresa) select idmenu,"+idGrupo+",0,"+IdEmpresa+" from seg_menu;";
		
		b = eper.persistir(query);
		return b;
	}
	catch(Exception e)
	{
		return b;
	}
	
	
}

public  List<DataIDDescripcion> permisosDeGrupo(int idU, int idEmpresa)
{
	
	try
	{	
		List<DataIDDescripcion> lista = darListaDataIdDescripcionMYSQLConsulta("select idGrupo,'' from seguridad_usuarios where idEmpresa="+idEmpresa+" AND idUsuario="+idU);
		
		return lista;
	}
	catch(Exception e)
	{
		return null;
	}
	
	
}



public  List<DataIDDescripcion> darPaquetes(Long pedido, int idEmpresa)
{
	
	try
	{	
		@SuppressWarnings("unused") Connection cone;		
		cone = econ.getConnection();
	
		String query="select a.cantidadPedido,a.idArticulo "
				+ " from ecommerce_pedido p "
				+ " inner join ecommerce_pedido_articulos a on a.idPedido=p.idPedido AND a.idEmpresa=p.idEmpresa"
				+ " where p.idEmpresa="+idEmpresa+" AND p.idPedido= "+pedido+" and p.EstadoEncuentra in (3,25)";
				
				
		List<DataIDDescripcion> lista = econ.darListaDataIdDescripcion(query);
		List<DataIDDescripcion> retorno = new ArrayList<>();
		
		lista.remove(0);
		for(DataIDDescripcion l:lista){
			for(int i=1;i<=l.getId();i++){
				retorno.add(new DataIDDescripcion(1,l.getDescripcion()));
			}
		}
		
		
		return retorno;
	}
	catch(Exception e)
	{
		return null;
	}
	
	
}

public  DataIDDescDescripcion EcommercedarCanalesAnaloga(int canal, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}

	
	String consulta ="select c.id, c.idUsr, c.secret,c.seller from ecommerce_canal_ml c where idEmpresa="+idEmpresa+" AND c.id ="+canal;
	
	try
	{
		List<DataIDDescDescripcion> retorno =  econ.darListaDataIdDescDescripcionCanalesML(consulta);
		return retorno.get(0);
	}
	catch (Exception e)
	{
		return null;
	}
}

public  List<DataIDDescDescripcion> EcommercedarCanalesAnaloga(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}

	
	String consulta ="select c.id, c.idUsr, c.secret,c.seller,c.nombre from ecommerce_canal_ml c where idEmpresa="+idEmpresa+" ";
	
	try
	{
		List<DataIDDescDescripcion> retorno =  econ.darListaDataIdDescDescripcionCanalesML(consulta);
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}

public  DataIDDescDescripcion EcommercedarCanalAnaloga(Long pedido, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}

	
	String consulta ="select c.id, c.idUsr, c.secret,c.seller from ecommerce_canal_ml c inner join ecommerce_pedido p "
			+ "on c.id=p.idCanalML AND c.idEmpresa=p.idEmpresa where p.idEmpresa="+idEmpresa+" AND p.idPedido = "+ pedido;
	
	try
	{
		List<DataIDDescDescripcion> retorno =  econ.darListaDataIdDescDescripcionCanalesML(consulta);
		return retorno.get(0);
	}
	catch (Exception e)
	{
		return null;
	}
}





public  List<DataIDDescripcion> darRecepcionesABorrar(String id, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		List<DataIDDescripcion> retorno = econ.darListaDataIdDescripcion("select iddocumento,'' from detallerecepcion where idEmpresa="+idEmpresa+" AND idrecepcion="+id);
		
		retorno.remove(0);
		return retorno; 
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return new ArrayList<>();
	}

}

public Hashtable<Integer,Usuario> darUsuarios(int idEmpresa){
	
	@SuppressWarnings("unused") Connection cone;
	Hashtable<Integer,Usuario> retorno = new Hashtable<>();
	try 
	{
		cone = econ.getConnection();
		String query = "select u.idUsuario, u.`Contrase?a`,u.Nickname,u.Nombre,u.Apellido,u.perfil,u.mail,u.idGrupo,p.descripcion,u.idDeposito,u.idEmpresa from"
				+ " usuarios u "
				+ " inner join perfiles p on u.perfil=p.id AND u.idEmpresa=p.idEmpresa "
				+ " where idusuario >0 and idusuario!=6666 and baja=0 AND p.idEmpresa="+idEmpresa;
		retorno = econ.DarUsuarios(query);
		
		return retorno; 
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return retorno;
	}
}

public List<MovArticulo> MovimientosArticulo(String arti,String ubi, Hashtable<String, String> grafosExistentes, int idEmpresa){
	
	@SuppressWarnings("unused") Connection cone;
	List<MovArticulo> movimientos = new ArrayList<>();
	try{
		econ.getConnection();
		if(ubi==null||ubi.equals(""))
			ubi = "%";
		String query = "SELECT mv.idMovimiento,mv.idArticulo,mv.codOrigen,mv.codDestino,mv.cantidad, mv.usuario, CONCAT(u.Nombre, ' ', u.Apellido),mv.`update`,mv.IdTipo "
				 	 + "FROM movsarticulos mv " 
				     + "INNER JOIN usuarios u ON u.idUsuario=mv.usuario AND mv.IdEmpresa=u.idEmpresa "
					 + "where mv.idEmpresa="+idEmpresa+" AND mv.idarticulo LIKE '"+arti+"' and mv.codDestino LIKE '"+ubi+"'";
		movimientos = econ.darMovimientoArticulos(query);
		
		for(MovArticulo ma:movimientos){
			if(grafosExistentes.get(ma.getCodOrigen()+"-"+ma.getCodDestino()+"-"+ma.getUpdate())==null){
				grafosExistentes.put(ma.getCodOrigen()+"-"+ma.getCodDestino()+"-"+ma.getUpdate(),"");
				
				if(!ma.getCodOrigen().equals("0")&&!ma.getCodOrigen().equals("")){
					List<MovArticulo>aux = MovimientosArticulo(ma.getIdArticulo(), ma.getCodOrigen(),grafosExistentes, idEmpresa);
					ma.setOrigenes(aux);					
					
					System.out.println("--");
				}
			}
			else{
				return null;
			}
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	return movimientos;

}

public void GuardarOrdenAlmacen (List <LineaOrdenAlmacen> list, int total, int IdEmpresa){

	try {
		eper.getConnection();
		String query1, query2="";
		int id=0;

		query1 = "INSERT INTO orden_almacen (total,IdEmpresa ) values ("+total+","+IdEmpresa+")";
		id=eper.persistirDarUltimo(query1, "orden_almacen", "idorden",IdEmpresa);
		for (LineaOrdenAlmacen li:list){
			query2 += "INSERT INTO orden_almacen_lineas (idOrden,ubicacion,SKU,cantidad,idEmpresa) values ("+id+",'"+li.getUbicacion()+"','"+li.getSku()
					+"',"+li.getCantidad()+","+IdEmpresa+"); ";
		}
		eper.persistir(query2);
		


	} catch (Exception e) {
		
	}	
	
	
}

public String query_IngresarMovimientoArticulo(String origen, String destino, String art, int cantidad, int usuario, int idEmpresa){
	
	try
	{					
		String query="insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario, idEmpresa) values "+
					"('"+art+"','"+origen+"','"+destino+"',"+cantidad+","+usuario+","+idEmpresa+"); ";		
		
		 return query;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return "";
	}
	
}

public void IngresarMovimientoArticulo(String origen, String destino, String art, int cantidad, int usuario, int idEmpresa){
	
	try
	{					
		String query="insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario, idEmpresa) values "+
					"('"+art+"','"+origen+"','"+destino+"',"+cantidad+","+usuario+","+idEmpresa+")";		
		
		 eper.persistir(query);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}


public String query_IngresarMovimientoArticuloTipo(String origen, String destino, String art, int cantidad, int usuario, String tipo, int idEmpresa){
	
	try
	{					
		String query="insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,idTipo, IdEmpresa) values "+
					"('"+art+"','"+origen+"','"+destino+"',"+cantidad+","+usuario+",'"+tipo+"',"+idEmpresa+")";		
		
		return query;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return "";
	}
	
}

public void IngresarMovimientoArticuloTipo(String origen, String destino, String art, int cantidad, int usuario, String tipo, int idEmpresa){
	
	try
	{					
		String query="insert into movsarticulos (idArticulo,codOrigen,codDestino,cantidad,usuario,idTipo, IdEmpresa) values "+
					"('"+art+"','"+origen+"','"+destino+"',"+cantidad+","+usuario+",'"+tipo+"',"+idEmpresa+")";		
		
		 eper.persistir(query);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}

public List<ConsolidarSku> ReporteConsolidacion(String articulo, String estanteria, String marca, String genero, String categoria, String clases, int idEmpresa){

	/*
	String query="SELECT idarticulo,COUNT(idojo),GROUP_CONCAT(idojo),SUM(cantidad) FROM ojostienenarticulos "+
				"WHERE idEmpresa="+idEmpresa+" AND TRUE GROUP BY (idarticulo) "+
				"HAVING COUNT(idojo)>4;";
	*/
	
	Connection cone;
	try {
		cone = econ.getConnection();
		
		String qArt = "";
		String qEst = "";
		String qMar = "";
		String qGen = "";
		String qCat = "";
		String qCla = "";
		
		if(!articulo.equals(""))
		{
			qArt = " AND OT.idArticulo like '"+articulo+"%' ";
		}
		
		if(!estanteria.equals(""))
		{
			qEst= " AND ES.idEstanteria IN ("+estanteria+") ";
		}
		if(!marca.equals(""))
		{
			qMar = " AND AR.IdMarca IN ("+marca+") ";
		}
		if(!genero.equals(""))
		{
			qGen = " AND AR.IdGenero IN ("+genero+") ";
		}
		if(!categoria.equals(""))
		{
			qCat = " AND AR.IdCategoria IN ("+categoria+") ";
		}
		if(!clases.equals(""))
		{
			qCla = " AND AR.IdClase IN ("+clases+") ";
		}
		
		String consulta = "SELECT AM.Descripcion marca,AG.Descripcion genero,CAT.Descripcion categoria,AC.Descripcion clase,ot.idarticulo articulo,COUNT(ot.idojo) AS CantOjos,GROUP_CONCAT(ot.idojo),SUM(ot.cantidad) \r\n" + 
				"FROM ojostienenarticulos ot \r\n" + 
				"INNER JOIN ojos o ON o.idOjo=ot.idOjo AND o.IdEmpresa=ot.IdEmpresa \r\n" + 
				"INNER JOIN estanterias ES ON ES.idEstanteria=o.idEstanteria AND ES.idEmpresa=o.IdEmpresa \r\n" + 
				"INNER JOIN articulos AR ON AR.idArticulo = ot.idArticulo AND ot.IdEmpresa = AR.IdEmpresa \r\n" + 
				"LEFT OUTER JOIN art_marca AM ON AM.id = AR.IdMarca AND AM.IdEmpresa = AR.IdEmpresa \r\n" + 
				"LEFT OUTER JOIN art_genero AG ON AG.id = AR.IdGenero AND AG.IdEmpresa = AR.IdEmpresa \r\n" + 
				"LEFT OUTER JOIN art_categoria CAT ON CAT.id = AR.IdCategoria AND CAT.IdEmpresa = AR.IdEmpresa \r\n" + 
				"LEFT OUTER JOIN art_clase AC ON AC.id = AR.IdClase AND AC.IdEmpresa = AR.IdEmpresa \r\n" + 
				" WHERE ot.IdEmpresa = "+ idEmpresa + " AND ES.idUso IN (1) "; 
		
		String groupBy = " GROUP BY (ot.idarticulo) ";
		String havingYOrder = " HAVING CantOjos>1\r\n" + 
							  " ORDER BY CantOjos desc;";
		
		return econ.darReporteConsolidacion(consulta+qArt+qEst+qMar+qGen+qCat+qCla+groupBy+havingYOrder, idEmpresa);
	
	}
	catch (Exception e) 
	{
		
		return new ArrayList<>();
	}
}

public List<DataIDDescripcion> ArticulosAAlmacenar(int idEmpresa){
	List<DataIDDescripcion> lista = new ArrayList<>();
	try {
		
		econ.getConnection();
		String query="SELECT cantidad,idarticulo FROM ojostienenarticulos "+
					"WHERE idEmpresa="+idEmpresa+" AND idojo='0' ";
		lista = econ.darIdDescripcion(query);
		
	} catch (Exception e) {
		
	}
	return lista;
}

public  List<OrdenAlmacen> getOrdenesAlmacen(int idEmpresa)
{
	List<OrdenAlmacen> ordenes = new ArrayList<>();
	@SuppressWarnings("unused") Connection cone;
	try {
	cone = econ.getConnection();
	
	String consulta="Select * from orden_almacen where idEmpresa="+idEmpresa+" AND estado = -1";
	ordenes = econ.darOrdenesAlmacen(consulta);
	
		} catch (Exception e) {
		e.printStackTrace();
	}
	return ordenes;
}

public  void CambioEstadoOrdenAlmacen(int estado, int id, int idEmpresa) 
{
	String update="UPDATE orden_almacen SET estado="+estado+" WHERE idEmpresa="+idEmpresa+" AND idOrden="+id;
	try{
		
		System.out.println(update);
		eper.persistir(update);
	}catch(Exception e){
		e.printStackTrace();
	}
	
}

public  void encuentraCambiarContrasena(Usuario u, int idEmpresa) {
	String consulta = "UPDATE usuarios  "
			+ "SET Contrase?a='"+ u.getPass() +"' "
			+ "WHERE idEmpresa="+idEmpresa+" AND idUsuario="+ u.getNumero() +";";
	try {

		eper.persistir(consulta);

	} catch (Exception e) {
		e.printStackTrace();
	}

}

public  List<DatosInventario> DatosInventario(String idDeposito, int idEmpresa)
{
	List<DatosInventario> datos = new ArrayList<>();
	@SuppressWarnings("unused") Connection cone;
	try {
	cone = econ.getConnection();
	
	String consulta="SELECT e.idEstanteria,e.Descripcion,o.idOjo,o.idModulo,o.idEstante,SUM(ota.Cantidad),ota.Actualizado FROM ojostienenarticulos ota "+
					"INNER JOIN ojos o ON o.idOjo=ota.idOjo AND o.idEmpresa=ota.idEmpresa "+
					"INNER JOIN estanterias e ON e.idEstanteria=o.idEstanteria AND e.idEmpresa=o.idEmpresa "+
					"WHERE o.idEmpresa="+idEmpresa+" AND ota.Actualizado>=(SELECT fecha FROM invent WHERE idEmpresa="+idEmpresa+" AND idDeposito = '"+idDeposito+"')  AND ota.InventUpdate=1 "+
					"GROUP BY OTA.IDOJO;";
	datos = econ.getDatosInventario(consulta);
	
		} catch (Exception e) {
		e.printStackTrace();
	}
	return datos;
}


public  DatosInventarioStatus DatosInventarioStatus(String idDeposito,int idEmpresa )
{
	
	@SuppressWarnings("unused") Connection cone;
	try {
	cone = econ.getConnection();
	
	String consulta="SELECT DATE_FORMAT(T0.Desde, '%d/%m %H:%i') desde, DATE_FORMAT(T0.ultimaAct, '%d/%m %H:%i') hasta,T0.cantidad, "+
						"	ROUND(((60.00*T0.cantidad)/(TIMESTAMPDIFF(MINUTE,T0.desde,T0.ultimaAct))),2) UnidadesHora, T0.porcentaje "
						+ "FROM "
						+ "	("
						+ "		SELECT "
						+ "			("
						+ "				SELECT fecha FROM invent "+ 
						"				WHERE idEmpresa="+idEmpresa+" AND idDeposito = '"+idDeposito+"' "+
						"			) desde,"
						+ "			("
						+ "				SELECT MAX(OT.Actualizado) FROM ojostienenarticulos OT "
						+ "				WHERE OT.idEmpresa="+idEmpresa+" AND ot.InventUpdate=1 "
						+ "				AND OT.Actualizado>="
						+ "					("
						+ "						SELECT fecha FROM invent "+ 
						"						WHERE idEmpresa="+idEmpresa+" AND idDeposito = '"+idDeposito+"' "+
						"					)"
						+ "			) ultimaAct,"
						+ "			("
						+ "				SELECT SUM(ota.Cantidad) "
						+ "				FROM ojostienenarticulos ota "
						+ "				WHERE ota.idEmpresa="+idEmpresa+" AND ota.InventUpdate=1 "
						+ "				AND ota.Actualizado>= "
						+ "				("
						+ "					SELECT fecha FROM invent "+ 
						"					WHERE idEmpresa="+idEmpresa+" AND idDeposito = '"+idDeposito+"' "+
						"				)"
						+ "			) cantidad,"
						+ "			("
						+ "				SELECT PorcentajeOjos "
						+ "				FROM invent "+ 
						"				WHERE idEmpresa="+idEmpresa+" AND idDeposito = '"+idDeposito+"' "+
						"			) porcentaje"
						+ ") T0";
	
	return econ.getDatosInventarioStatus(consulta);
	
	
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
	return null;
}

public List<OjosEnCero> OjosEnCero(String zonas, int idEmpresa){

	@SuppressWarnings("unused") Connection cone;
	List<OjosEnCero> lista = new ArrayList<>();
	try {
		econ.getConnection();
		
		String zonasQry = "";
		if (zonas!=null && !zonas.equals(""))
			zonasQry ="AND zte.idZona IN ("+zonas+") \r\n";
		
		String query= "SELECT est.descr, GROUP_CONCAT(o.idOjo),est.canti\r\n" + 
				"FROM 	(\r\n" + 
				"SELECT e.idestanteria eest,e.descripcion descr, SUM(ota.Cantidad) canti, e.idEmpresa emp, ue.idUso uso\r\n" + 
				"FROM estanterias e\r\n" + 
				"INNER JOIN zonastienenestanterias zte ON zte.idEstanteria= e.idEstanteria AND zte.idEmpresa = e.idEmpresa\r\n" + 
				"INNER JOIN usos_estanteria ue on e.iduso=ue.iduso and e.idEmpresa=ue.idEmpresa \r\n" + 
				"INNER JOIN ojos o ON o.idEstanteria=e.idEstanteria AND o.idEmpresa=e.idEmpresa\r\n" + 
				"INNER JOIN ojostienenarticulos ota ON ota.idOjo=o.idOjo AND ota.idEmpresa=o.idEmpresa\r\n" + 
				"WHERE e.idEmpresa="+idEmpresa+" "+ zonasQry +
				"GROUP BY e.idestanteria 	) est\r\n" + 
				"INNER JOIN ojos o ON o.idEstanteria = est.eest AND o.idEmpresa = est.emp\r\n" + 
				"LEFT OUTER\r\n" + 
				"JOIN ojostienenarticulos ota ON o.idOjo=ota.idOjo AND o.idEmpresa=ota.idEmpresa \r\n" + 
				"WHERE est.emp="+idEmpresa+"\r\n" + 
				"AND ota.idojo IS NULL AND o.idEstanteria AND est.uso=1 \r\n" + 
				"GROUP BY est.eest;";


		lista = econ.getOjosEnCero(query);
		
	} catch (Exception e) {
		
	}
	return lista;
}


public List<DataIDDescripcion> ArtRecepSinMov(String idOjo, int idEmpresa) {
	
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
		
		String query= "SELECT CONCAT (idArticulo,' - ', AD.Descripcion)Articulo, Cantidad, DATEDIFF(CURRENT_DATE(),OT.Actualizado) dias "
				+ "FROM ojostienenarticulos OT "
				+ "INNER JOIN art_descripcion AD ON OT.idArticulo = AD.id AND OT.idEmpresa = AD.idEmpresa "
				+ "WHERE idEmpresa="+idEmpresa+" AND idOjo = '"+idOjo+"' "
				+ "ORDER BY DATEDIFF(CURRENT_DATE(),OT.Actualizado)  desc;";
		
	return econ.ArtRecepSinMov(query);
		
	} catch (Exception e) {
		//return
	}
	return null;
}



public  List<Ojo> darOjosEstanteria(int cantidad, int idEstanteria, int idEmpresa) {
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();

		String consulta = "SELECT * FROM ojos WHERE idEmpresa="+idEmpresa+" AND idEstanteria ="+idEstanteria+" AND idOjo NOT IN (SELECT PosSorter FROM sorter WHERE idEmpresa="+idEmpresa+") "
				+ "ORDER BY idRecorrido "
				+ "LIMIT "+cantidad+";";

		return econ.darOjo(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return null;
	}

}

public  void LimpiarSorter(int idMain, int idEmpresa) {
	String consulta = "insert into sorter_historico (select * from sorter where idTarea="+idMain+" AND idEmpresa="+idEmpresa+") ";
	String consulta2 = "delete from sorter where idTarea="+idMain+" AND idEmpresa="+idEmpresa;
			
	try {

		eper.persistir(consulta);
		eper.persistir(consulta2);

	} catch (Exception e) {
		e.printStackTrace();
	}

}

public  int darNextSincWEB(int idEmpresa) 
{
	int retorno = 0;
	
	try {
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		boolean cerrarConexion = false;
		retorno = econ.UltimoId("select max(ultimaVenta) from ecommerce_sincro WHERE idEmpresa="+idEmpresa+"",cerrarConexion);
		 

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;

	
}

public  List<Compras> sincroPedidosWeb(int idEmpresa, Hashtable<String, DataIDDescripcion> destinoPedidos) 
{
	List<Compras> compras = new ArrayList<>();
	try {
		List<DataIDDescripcion>depositosPick = darListaDataIdDescripcionMYSQLConsulta("select idDeposito,nombre from ecommerce_envioml WHERE idEmpresa="+idEmpresa+" AND idDeposito !=0");
		depositosPick.remove(0);
		Hashtable<String, String> depositosPickHT = new Hashtable<>();
		for (DataIDDescripcion d : depositosPick) 
		{
			depositosPickHT.put(d.getDescripcion(), String.valueOf(d.getId()));
		}
		
			//QUERY
		String queryPedidos = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select Vta_Total, DocV_NumeroDoc, 'estado' estado, Doc_Fecha, '$' moneda, Doc_IdDepDestino, "+
				"'0.0' as montoenvio, 'MetodoPago' metodoPago,DocV_Serie,Doc_Comentario, Cli_Apellido, Cli_Nombre, VEnt_Ciudad as 'localidad', '' mail, "+ 
				"VEnt_Departamento, 0 latitud, VEnt_CliTelefono, 0 longitud,VEnt_Direccion,VEnt_Ciudad,VEnt_CodigoPostal,VEnt_Comentario, 'CI' 'docTipo', CONCAT(Cli_Numero,Cli_NumeroDig),doc_Fecha "+
				"  from dbo.encuentra_ventaWEB WHERE "
				/*****************************************/
				//+ " Doc_Comentario like '%SUPERMALL%'  and "
				/*****************************************/
				+ " DocV_NumeroDoc ";
		
			//VENTAS QUE NO TENIAMOS DISTRIBUCIONES
		
		String ventasAtrasadas="";
		List<DataIDDescripcion> idVentasAtrasadas = new ArrayList<>();
		try {
			idVentasAtrasadas = darListaDataIdDescripcionMYSQLConsulta("select id,'' from aaatemporal");
			idVentasAtrasadas.remove(0);
		} catch (Exception e) {
			
		}
			
		if(idVentasAtrasadas.size()>0){
			for(DataIDDescripcion id:idVentasAtrasadas){
				ventasAtrasadas+=id.getId()+",";
			}
			ventasAtrasadas = ventasAtrasadas.substring(0,ventasAtrasadas.length()-1);
			String queryAtrasados = queryPedidos + " in ("+ventasAtrasadas+")"; 
			compras = MSSQL.darComprasWeb(queryAtrasados, depositosPickHT,0,idEmpresa,destinoPedidos);
			persistir("delete from aaatemporal where idEmpresa="+idEmpresa);
		}			
		
			//VENTAS NUEVAS
		int ultimaVenta = darNextSincWEB(idEmpresa);		
		List<Compras> compras2 =MSSQL.darComprasWeb(queryPedidos, depositosPickHT,ultimaVenta,idEmpresa,destinoPedidos);
		
		
		compras.addAll(compras2);

		return compras;

	} catch (Exception e) {

		e.printStackTrace();
		return compras;
	}

}

public void borrarConteoOjo(String idArticulo, String idOjo, int idEmpresa) {

	String query = ""
			+ " delete from ojostienenarticulos_conteo "
			+ " where idArticulo = '%s' "
			+ " and idOjo = '%s' "
			+ " and idEmpresa = %s ";

	String consulta = String.format(query, idArticulo, idOjo, idEmpresa);

	try {
		eper.persistir(consulta);
	} catch (Exception e) {
		e.printStackTrace();
	}
}


public void encuentraUpdateAlias(String idOjo, String articulo, String dest, int idEmpresa) {

	try {
		String consulta = "";
		consulta = "UPDATE `ojostienenarticulos` SET `Alias`='"+dest+"' WHERE idEmpresa="+idEmpresa+" AND `idOjo`='"+idOjo+"' AND `idArticulo`='"+articulo+"';";

		eper.persistir(consulta);

	}
	catch (Exception e) {
		e.printStackTrace();
	}

}


public void resetReservasPicking(List<DataLineaRepo> articulos, int pick, int idEmpresa){
	try {
		StringBuilder sb = new StringBuilder();
		
	for(DataLineaRepo a:articulos){ 
		sb.append("update reposicion_articulos_ojos set cantidad =cantidad-"+a.getSolicitada()+" where idEmpresa="+idEmpresa+" AND idArticulo ='"+a.getIdArticulo()+"' and idPicking="+pick+" and cUbicacion='"+a.getCubi()+"' and pedido="+a.getPedido()+" and destino="+a.getIdDepDestino()+" ; ");
		sb.append("UPDATE `ojostienenarticulos` SET `Reservada`= reservada-"+a.getSolicitada()+" WHERE idEmpresa="+idEmpresa+" AND `idOjo`='"+a.getCubi()+"' AND `idArticulo`='"+a.getIdArticulo()+"';");
	}
	sb.append("delete from reposicion_articulos_ojos where idEmpresa="+idEmpresa+" AND idPicking="+pick+" and cantidad=0 ; ");
		eper.persistir(sb.toString()); 
System.out.println("hola");
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}

public List<DepositoParametros> parametrosDeposito(String deposito, int idEmpresa)
{
	@SuppressWarnings("unused") Connection cone;
	try {
		
		cone = econ.getConnection();
		
		String consulta = "SELECT dp.idParametro, dp.idDeposito, dp.habilitado, dp.idEmpresa, p.descripcion FROM depositos_parametros dp "
						 +"INNER JOIN parametros p ON p.idParametro=dp.idParametro AND p.idEmpresa=dp.idEmpresa "
						 +"WHERE idDeposito=" + Integer.parseInt(deposito) + " AND p.idEmpresa=" + idEmpresa + ";";

		return econ.darParametrosDepo(consulta);

	} 
	catch (Exception e) 
	{

		e.printStackTrace();
		return new ArrayList<>();
	}

}


public  String query_encuentraLogEvento(int hilo, int usuario, int unidades,int tipo, int sentido, boolean interno, String sesion, String comentario, int idDeposito,int idEmpresa) 
{
	try 
	{
		String consulta = "INSERT INTO `tarea_registro` (`idUsuario`, `TipoTarea`, `Comentario`, `interno`, `Sesion`, `Hilo`, `Unidades`, `sentido`, `idDeposito`, `idEmpresa`)"
				+ " VALUES ('"+usuario+"', '"+tipo+"', '"+comentario+"', "+interno+", '"+sesion+"', '"+hilo+"', '"+unidades+"', '"+sentido+"', '"+idDeposito+"',"+idEmpresa+");";
		
		return consulta;
			
	} 
	catch (Exception e) 
	{
		return "";
	}

}


public  void encuentraLogEvento(int hilo, int usuario, int unidades,int tipo, int sentido, boolean interno, String sesion, String comentario, int idDeposito,int idEmpresa) 
{
	try 
	{
		String consulta = "INSERT INTO `tarea_registro` (`idUsuario`, `TipoTarea`, `Comentario`, `interno`, `Sesion`, `Hilo`, `Unidades`, `sentido`, `idDeposito`, `idEmpresa`)"
				+ " VALUES ('"+usuario+"', '"+tipo+"', '"+comentario+"', "+interno+", '"+sesion+"', '"+hilo+"', '"+unidades+"', '"+sentido+"', '"+idDeposito+"',"+idEmpresa+");";
		
		eper.persistir(consulta);
			
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}

}

public  void encuentraRegistrarRemitos(RemitoRegistro rem) 
{
	try 
	{
		String consulta = "INSERT INTO `remitos_registro` (`Hilo`, `Usuario`, `idEquipo`, `Remito`, `Etiqueta`, `idDeposito`, `idEmpresa`)"
				+ " VALUES ("+rem.getHilo()+", "+rem.getUsuario()+", "+rem.getIdEquipo()+", '"+rem.getRemito()+"', '"+rem.getEtiqueta()+"', "+rem.getIdDeposito()+", "+rem.getIdEmpresa()+");";
		
		eper.persistir(consulta);
			
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}

}

public void encuentraCambiarDeposito(String deposito, int numero, int empresa) 
{
	try 
	{
		String consulta = "UPDATE usuarios "
				+ "SET idDeposito="+ deposito +" "
				+ "WHERE idUsuario="+ numero +" AND idEmpresa="+ empresa +";";

		eper.persistir(consulta);

	} catch (Exception e) {
		e.printStackTrace();
	}

}

public void encuentraCambiarEquipo(int equipo, int numero, int empresa) 
{
	try 
	{
		String consulta = "UPDATE usuarios "
				+ "SET idEquipo="+ equipo +" "
				+ "WHERE idUsuario="+ numero +" AND idEmpresa="+ empresa +";";

		eper.persistir(consulta);

	} catch (Exception e) {
		e.printStackTrace();
	}

}

public void encuentraCambiarEquipoTrabajo(int equipo, int numero, int empresa) 
{
	try 
	{
		String consulta = "UPDATE usuarios "
				+ "SET equipo_trabajo="+ equipo +" "
				+ "WHERE idUsuario="+ numero +" AND idEmpresa="+ empresa +";";

		eper.persistir(consulta);

	} catch (Exception e) {
		e.printStackTrace();
	}

}

public  List<ArticuloLineaReposicion> darArtsLineasRepoEC(StringBuilder sb, int idEmpresa) 
{
	String pedidos = sb.toString();
	pedidos = pedidos.substring(0,pedidos.length()-1);
	String consulta = " select IdSincronizacion, RS.Fecha ,RA.idArticulo,Origen, DOr.Nombre,Destino,DDe.Nombre, RA.Cantidad-RA.Picked,Justificacion , IF(RA.Mayorista=1,RA.Seccion,0)  ped"+
	",  if(ISNULL(GROUP_CONCAT(OTA.idOjo)),'Sin Asignar', GROUP_CONCAT(ES.Descripcion,' M',OJO.idModulo,' E',OJO.idEstante,' ')) ubicacion, RA.idpicking,'',RA.idTipo, RA.idSolicitudTraslado " +
	" FROM reposicion_articulos  RA "
	+ " LEFT OUTER JOIN ojostienenarticulos OTA ON OTA.idArticulo = RA.idArticulo AND OTA.idEmpresa = RA.idEmpresa "
	+ " LEFT OUTER JOIN ojos OJO ON OJO.idOjo= OTA.idOjo AND OJO.idEmpresa= OTA.idEmpresa "
	+ " LEFT OUTER JOIN estanterias ES ON ES.idEstanteria=OJO.idEstanteria AND ES.idEmpresa=OJO.idEmpresa ,"
	+ " reposicion_sincronizacion RS, "
	+ " depositos DOr, "
	+ " depositos DDe "+
	" where "
	+ " RA.IdSincronizacion = RS.Id  AND RA.idEmpresa = RS.idEmpresa "
	+ " AND RA.Origen = DOr.idDeposito AND RA.idEmpresa = DOr.idEmpresa "
	+ " AND DDe.idDeposito = RA.Destino AND DDe.idEmpresa = RA.idEmpresa "+ 
	"   AND RA.idEmpresa="+idEmpresa+" AND RA.Cantidad-RA.remitidas >0 AND RA.Estado in (1,11) AND RA.Seccion in ("+pedidos+") "+
	" GROUP BY IdSincronizacion, RS.Fecha ,RA.idArticulo,Origen, DOr.Nombre,Destino,DDe.Nombre, RA.Cantidad-RA.Picked,Justificacion , ped";
	
	List<ArticuloLineaReposicion> retorno = null;	
	
	try 
	{
		@SuppressWarnings("unused") Connection cone;
		cone = econ.getConnection();
		System.out.println(consulta);
		retorno = econ.darListaArticulosRepo(consulta);
		

	} catch (Exception e) {
		
		e.printStackTrace();
		return retorno;
	}
	return retorno;

	
}

public List<TareasPendientes> DarTareasPendientes(int idEmpresa) {
	
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
		
		String query= "SELECT fechainicio,iddoc,idtarea,unidades,te.descripcion,CONCAT(u.nombre,' ',u.apellido), t.porcentaje from tareas t "
				+"inner join tareasestados te on te.idestado=t.estado and t.idempresa=te.idempresa "
				+"inner join usuarios u on u.idUsuario=t.idUsuario and t.idEmpresa=u.idEmpresa "
				+ "where t.idEmpresa="+idEmpresa+" and t.estado!=2 and idtarea>3184 order by fechainicio";
		
		
		return econ.TareasPendientes(query,idEmpresa);
		
	} catch (Exception e) {
		//return
	}
	return new ArrayList<>();
}

public  void deshacerTarea(int idPick, int idEmpresa) 
{
	try 
	{
		String delete = "delete from tareas where idEmpresa="+idEmpresa+" AND iddoc="+idPick;		
		String update = "update reposicion_articulos set idpicking=0, estado=1, idPosSort=0 where idEmpresa="+idEmpresa+" AND idpicking="+idPick;
		String reservasArticulos = "update ojostienenarticulos OT \r\n" + 
				"inner join reposicion_articulos_ojos RAO \r\n" + 
				"on OT.idOjo = RAO.cUbicacion AND  OT.idArticulo = RAO.idArticulo and RAO.idBulto='' \r\n" + 
				"AND OT.IdEmpresa=RAO.IdEmpresa \r\n" + 
				"inner join reposicion_articulos R \r\n" + 
				"ON R.idPicking=RAO.idPicking AND  R.idArticulo = RAO.idArticulo \r\n" + 
				"AND RAO.destino=R.Destino AND RAO.pedido=R.Seccion AND RAO.IdEmpresa=R.IdEmpresa \r\n" + 
				"\r\n" + 
				"set  OT.Reservada =  OT.Reservada + R.picked - RAO.cantidad \r\n" + 
				"where RAO.idEmpresa="+idEmpresa+" AND RAO.idPicking = "+idPick+" AND R.Cantidad > R.Picked; ";
		String reservasBultos = "update ojostienenarticulos OT \r\n" + 
				"inner join reposicion_articulos_ojos RAO \r\n" + 
				"on OT.idOjo = RAO.cUbicacion AND  OT.idArticulo = RAO.idBulto \r\n" + 
				"AND OT.IdEmpresa=RAO.IdEmpresa \r\n" + 
				"inner join reposicion_articulos R \r\n" + 
				"ON R.idPicking=RAO.idPicking AND  R.idArticulo = RAO.idArticulo \r\n" + 
				"AND RAO.destino=R.Destino AND RAO.pedido=R.Seccion AND RAO.IdEmpresa=R.IdEmpresa \r\n" + 
				"\r\n" + 
				"set  OT.Reservada =  OT.Reservada + R.picked - RAO.cantidad \r\n" + 
				"where RAO.idEmpresa="+idEmpresa+" AND RAO.idPicking = "+idPick+" AND R.Cantidad > R.Picked; ";
		String reservasBultoContenido = "UPDATE bulto_contenido bc \r\n" + 
				" INNER JOIN reposicion_articulos_ojos rao ON rao.idBulto = bc.idBulto AND rao.idArticulo = bc.idArticulo AND rao.IdEmpresa = bc.IdEmpresa\r\n" + 
				" SET bc.cantidadReservada = if(bc.cantidadReservada - rao.Cantidad > 0, bc.cantidadReservada - rao.Cantidad, 0)\r\n" + 
				" WHERE rao.idPicking = "+idPick+" AND rao.IdEmpresa = "+idEmpresa+" AND bc.IdEmpresa = "+idEmpresa+";";
	
		eper.persistir(reservasArticulos);
		eper.persistir(reservasBultos);
		eper.persistir(reservasBultoContenido);
		eper.persistir(delete);
		eper.persistir(update);
			
	} 
	catch (Exception e) 
	{
		
	}

}

public  String query_justificarNoEncontrado(int idPick,String articulo,int idOr,int idDest,Long pedido, int idEmpresa) 
{
	try 
	{
		String pedidoEC="";
		String justificacion = " if(locate('NO ENCONTRADO',Justificacion)=0,CONCAT(justificacion,' (NO ENCONTRADO)'),justificacion) ";
		if(idDest==1200){
			pedidoEC=" and seccion="+pedido+" ";
		}
		
		String update = "update reposicion_articulos set justificacion = "+justificacion+" where idEmpresa="+idEmpresa+" AND idpicking="+idPick+
				" and `idArticulo`='"+articulo+"' AND `Origen`="+idOr+" AND `Destino`="+idDest+ pedidoEC+";";;
	
		return update;
			
	} 
	catch (Exception e) 
	{
		return "";
	}

}

public  void justificarNoEncontrado(int idPick,String articulo,int idOr,int idDest,Long pedido, int idEmpresa) 
{
	try 
	{
		String pedidoEC="";
		String justificacion = " if(locate('NO ENCONTRADO',Justificacion)=0,CONCAT(justificacion,' (NO ENCONTRADO)'),justificacion) ";
		Utilidades util = new Utilidades();
		int depoEcommerce = util.darParametroEmpresaINT(idEmpresa, 5);
		if(idDest==depoEcommerce){
			pedidoEC=" and seccion="+pedido+" ";
		}
		
		String update = "update reposicion_articulos set justificacion = "+justificacion+" where idEmpresa="+idEmpresa+" AND idpicking="+idPick+
				" and `idArticulo`='"+articulo+"' AND `Origen`="+idOr+" AND `Destino`="+idDest+ pedidoEC+";";;
	
		eper.persistir(update);
			
	} 
	catch (Exception e) 
	{
		
	}

}

public  List<DataIDDescripcion> darRecorrido(boolean con, int idEmpresa) {
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	String distinto = "";
	String order="";
	if (con)
		{
		distinto="!";
		order=" ORDER BY DescES";
		}
	else
		order=" ORDER BY OJO.idRecorrido";
	
	String consulta = "SELECT CONCAT(ES.Descripcion,' M',OJO.idModulo,' E',OJO.idEstante,' ') AS DescEs, OJO.idOjo, OJO.idRecorrido "
			+ " FROM ojos OJO "
			+ " LEFT OUTER JOIN estanterias ES ON ES.idEstanteria=OJO.idEstanteria AND ES.idEmpresa=OJO.idEmpresa "
			+ " WHERE OJO.idEmpresa="+idEmpresa+" AND ES.idEstanteria != 107 AND ES.idEstanteria != 0 AND OJO.idRecorrido"+distinto+"=0";

	return econ.DarRecorrido(consulta+order);
}

public List<Tareas> darTareas(String filtro, String fechaI, String fechaF, int idEmpresa) {
	
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	String filtroIN = "";
	String fechaIN = "";

	if(filtro.equals("1"))
	{
		filtroIN = " AND T.Estado != 2";
	}
	else
		filtroIN = " AND T.Estado = 2";
	
	
	if(!fechaI.equals("")&&!fechaF.equals(""))
	{
		fechaIN = " AND (TA.FechaHora BETWEEN '"+fechaI+"' AND '"+fechaF+"')";
	}
	
	
	String consulta = "SELECT TA.Hilo, T.Observaciones, CONCAT(U.Nombre,' ',U.Apellido) AS Usuario, MIN(TA.FechaHora) AS Primer_Picking, MAX(TAF.FechaHora) AS Ultimo_Picking, T.ultimaLinea AS Linea_Actual , T.Unidades AS TOTAL_Lineas, TIMESTAMPDIFF(MINUTE, MIN(TA.FechaHora),MAX(TAF.FechaHora)) AS Tiempo "
			+ " FROM tarea_registro  TA "
			+ "INNER JOIN tarea_registro TAF ON TA.Hilo = TAF.Hilo AND TA.idRegistro = TAF.idRegistro AND TA.idEmpresa = TAF.idEmpresa "
			+ "INNER JOIN usuarios U ON TA.idUsuario = U.idUsuario AND TA.idEmpresa = U.idEmpresa "
			+ "INNER JOIN tareas T ON TA.Hilo = T.idDoc AND TA.idEmpresa = T.idEmpresa  "
			+ "WHERE TA.idEmpresa="+idEmpresa+"  AND TA.TipoTarea = 107 AND TAF.TipoTarea = 107 AND TA.idUsuario=U.idUsuario AND T.idUsuario=TA.idUsuario AND TA.Comentario NOT LIKE 'Asignando picking%' AND TA.Comentario NOT LIKE ' iniciando tarea%' ";

	String order = " GROUP BY TA.Hilo, TAF.Hilo ORDER BY TA.Hilo;";
			
	return econ.DarTareas(consulta+fechaIN+filtroIN+order);
}



public  List<DataIDDescripcion> darUbicacionPedidoEnOjos(Long idpedido, int idEmpresa) {

	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
		/*String consulta = "SELECT t.cant ,t.idarticulo,t.ojo from "+
				"( "+
					"	SELECT r.cantidadprocesasa cant,r.idArticulo,o.idojo ojo from ecommerce_pedido_articulos_req r "+
					"	inner join ojostienenarticulos o on r.idarticulo=o.idarticulo AND r.idEmpresa=o.idEmpresa "+
					"	WHERE r.idEmpresa="+idEmpresa+" AND r.idpedido="+idpedido+" AND r.cantidadprocesasa=1 AND o.idOjo IN "+
					"	(SELECT idojo FROM ojostienenarticulos WHERE idEmpresa="+idEmpresa+" AND idarticulo="+idpedido+") "+
					"	UNION ALL "+
					"	SELECT r.cantidadprocesasa cant,r.idArticulo,'-' ojo FROM ecommerce_pedido_articulos_req r WHERE idEmpresa="+idEmpresa+" AND idpedido="+idpedido+" "+
					"	) AS t "+
					"	GROUP BY idarticulo ";*/
		/*String consulta = "SELECT t.cant ,t.idarticulo,t.ojo from "+
				"( "+
				"	SELECT o.Cantidad cant,r.idArticulo,o.idojo ojo from ecommerce_pedido_articulos_req r "+
				"	inner join ojostienenarticulos o on r.idarticulo=o.idarticulo "+
				"	WHERE r.idpedido="+idpedido+" AND r.cantidadprocesasa>0 AND o.idOjo IN "+
				"	(SELECT idojo FROM ojostienenarticulos WHERE idarticulo="+idpedido+") "+
				"	UNION ALL "+
				"	SELECT r.cantidadrequerida-r.cantidadprocesasa cant,r.idArticulo,'-' ojo FROM ecommerce_pedido_articulos_req r WHERE idpedido="+idpedido+" "+
				"	) AS t "+
				"	GROUP BY idarticulo,ojo "+
				" 	HAVING cant>0 ";*/

		String consulta = "select cantidad, idarticulo, idOjo " + 
						  "from ojostienenarticulos where idarticulo like '"+idpedido+"\\_%'";

		List<DataIDDescripcion> lista = econ.darListaDataIdDescripcion(consulta);
		for(DataIDDescripcion l: lista) {
			try {
				if (l.getDescripcion().contains("_")) {
					l.setDescripcion(l.getDescripcion().split("_")[1]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return lista;

	} catch (Exception e) {

		e.printStackTrace();
		return new ArrayList<>();
	}
}

public  Hashtable<String, DataIDDescripcion> darPedidosPorCanalFenicio(int canal, int idEmpresa) {

	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
		String consulta = "";
		if(canal==0) {
			consulta = "SELECT idcanalml,idpedido,idFenicio from ecommerce_pedido where idEmpresa="+idEmpresa+" AND stamptime >'2020-09-01'"
					+ " and estadoencuentra in (1,2,25) and idcanalml="+canal;
		}
		else {
			consulta = "SELECT idcanalml,p.idpedido,idFenicio from ecommerce_pedido p "
					+ "LEFT OUTER JOIN ecommerce_pedido_destino d ON d.idPedido=p.idPedido AND p.IdEmpresa=d.IdEmpresa "
					+ "where p.idEmpresa="+idEmpresa+" AND URLetiqueta='' AND stamptime >'2021-04-25' "
					+ " and estadoencuentra in (1,2,25) and idcanalml="+canal+" AND d.idDestino IS null ";
		}
		
		return econ.darHASDataIdDescripcion(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return new Hashtable<>();
	}
}

public  List<DataIDDescripcion> pedidosSinDespachar(int idEmpresa) {

	@SuppressWarnings("unused") Connection cone;
	List<DataIDDescripcion> lista = new ArrayList<>();
	try {
		cone = econ.getConnection();
		String consulta = "SELECT idcanalml,idpedido,idFenicio from ecommerce_pedido where idEmpresa="+idEmpresa+" AND stamptime >'2020-09-01'"
					+ " and estadoencuentra in (1,2,25,3,34)";
		
		lista = econ.darListaDataIdDescripcion(consulta);
		lista.remove(0);
		return lista;

	} catch (Exception e) {

		e.printStackTrace();
		return new ArrayList<>();
	}
}


public List<DataPedidoArticuloEcommerceVerif> darListaPedidosVerificadosArt(String articulo, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		String consulta = "select DATE_FORMAT(EP.stampTime,'%d/%m/%Y') FECHA , Procesando.*, IFNULL((GROUP_CONCAT(OT.idOjo)),'SIN ASIGNAR') OJO, EF.estadoExtFenicio "
				+ "	from ecommerce_pedido_articulos_req ER "
				+ " inner join ecommerce_pedido EP on EP.idPedido = ER.idPedido AND EP.idEmpresa = ER.idEmpresa"
				+ " inner join "
				+ "	("
				+ "		select P.idPedido,P.UnidadesTotal, SUM(R.CantidadProcesasa) totalProcesado, P.idEmpresa "
				+ "		from ecommerce_pedido P "
				+ "		inner join ecommerce_pedido_articulos_req R on P.idPedido = R.idPedido AND P.idEmpresa = R.idEmpresa "
				+ "		where "
				+ "		P.idEmpresa="+idEmpresa+" AND P.EstadoEncuentra <3 "
				+ "		group by P.idPedido,idEmpresa having SUM(R.CantidadProcesasa)>0"
				+ " ) procesando "+
				"	on procesando.idPedido = EP.idPedido AND procesando.idEmpresa = EP.idEmpresa "
				+ " INNER JOIN estadosfenicio EF ON EF.estadoIntFenicio = EP.EstadoEcommerce AND EF.idEmpresa = EP.idEmpresa "
				+ " left outer join ojostienenarticulos OT on OT.idArticulo = CONCAT(EP.idPedido,'') AND OT.idEmpresa = EP.idEmpresa "
				+ " where ER.idEmpresa="+idEmpresa+" and EP.EstadoEncuentra <3 "
				+ " and ER.CantidadProcesasa>0 "+
				"	and ER.idArticulo = '"+articulo+"' group by Procesando.idPedido order by EP.stampTime";
		
		return econ.darListaPedidosVerificadosArt(consulta);

	} 
	catch (Exception e) 
	{

		e.printStackTrace();
		return new ArrayList<>();
	}
}


public List<DataPedidoArticuloEcommerceVerif_ar> darListaPedidosVerificadosArt_ar(String idPedido, int idEmpresa) {
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		String consulta = "select AR.idArticulo, Deposito, CantidadRequerida, CantidadProcesasa, OTT.idOjo "
				+ "from ecommerce_pedido_articulos_req AR "
				+ " left outer join "
				+ "	("
				+ "		select idArticulo, idOjo,idEmpresa "
				+ "		from ojostienenarticulos "
				+ "		where idEmpresa="+idEmpresa+" AND idOjo in "
				+ "			("
				+ "				select idOjo from ojostienenarticulos where idEmpresa="+idEmpresa+" AND idArticulo = '"+idPedido+"' "
				+ "			)"
				+ "	) "
				+ "	OTT on OTT.idArticulo = AR.idArticulo AND OTT.idEmpresa = AR.idEmpresa"
				+ " where AR.idEmpresa="+idEmpresa+" AND idPedido = "+idPedido;
		
		return econ.darListaPedidosVerificadosArt_ar(consulta);

	} 
	catch (Exception e) 
	{

		e.printStackTrace();
		return new ArrayList<DataPedidoArticuloEcommerceVerif_ar>();
	}
}


public List<DataPosiblePedido> ClasificacionDarPedidos(String idarticulo,int pick, int idEmpresa, int depositoCentral){
	@SuppressWarnings("unused") Connection cone;
	try {
		String innerPick="";
		String whPick="";
		if(pick>0){
			innerPick = " inner join reposicion_articulos ra on ra.idEmpresa=r.idEmpresa AND ra.idarticulo=r.idarticulo and ra.seccion=r.idpedido and ra.idSolicitudTraslado"
					+ "=r.docVisual ";
			whPick = " and ra.idPicking="+pick+" AND ra.idEmpresa="+idEmpresa+" and ra.origen="+depositoCentral+" ";
		}
		cone = econ.getConnection();
		String consulta = "select * from ( "+
				"select  R.idPedido, P.stampTime fecha, R.Confirmado "+
				"from ecommerce_pedido_articulos_req R "
				+ " inner join ecommerce_pedido P on P.idEmpresa=R.idEmpresa AND P.idPedido=R.idPedido  "+innerPick+
				"left outer join ecommerce_import_venta IV on IV.idEmpresa=P.idEmpresa AND IV.idVenta=P.idPedido and IV.idVenta=R.idpedido "+
				"where R.idEmpresa="+idEmpresa+" AND R.CantidadRequerida>R.CantidadProcesasa   "+
				"AND P.EstadoEncuentra in (1,2)  "+
				"and p.cancelado = 0  "+
				"AND P.idPedido not in (select distinct IdPedido from ecommerce_estadospedido where idEmpresa="+idEmpresa+" AND idEstado >2)  "+
				"AND R.Confirmado=1    "+
				"AND R.idArticulo='"+idarticulo+"'  "+whPick+
				" "+
				"union "+
				" "+
				"select  R.idPedido, P.stampTime fecha, R.Confirmado "+
				"from ecommerce_pedido_articulos_req R  inner join ecommerce_pedido P on P.idEmpresa=R.idEmpresa AND P.idPedido=R.idPedido  "+
				"inner join ecommerce_pedido_articulos PA on R.idEmpresa=PA.idEmpresa AND R.idpedido=PA.idPedido and R.idArticulo=PA.idArticulo  "+innerPick+
				"left outer join ecommerce_import_venta IV on IV.idEmpresa=P.idEmpresa AND IV.idVenta=P.idPedido and IV.idVenta=R.idpedido and IV.idVenta = PA.idPedido  "+
				"where R.idEmpresa="+idEmpresa+" AND R.CantidadRequerida>R.CantidadProcesasa   "+
				"AND P.EstadoEncuentra in (1,2)  "+
				"and p.cancelado = 0  "+
				"AND P.idPedido in (select distinct IdPedido from ecommerce_pedido_articulos where  idEmpresa="+idEmpresa+" AND  modificado =1)  "+
				"AND R.Confirmado=1    "+
				"AND R.idArticulo='"+idarticulo+"'  "+whPick+
				" "+
				"UNION "+
				" "+
				"select  R.idPedido, P.stampTime fecha, R.Confirmado "+
				"from ecommerce_pedido_articulos_req R  inner join ecommerce_pedido P on P.idEmpresa=R.idEmpresa AND P.idPedido=R.idPedido  "+innerPick+
				"left outer join ecommerce_import_venta IV on IV.idEmpresa=P.idEmpresa AND IV.idVenta=P.idPedido and IV.idVenta=R.idpedido "+
				"where R.idEmpresa="+idEmpresa+" AND R.CantidadRequerida>R.CantidadProcesasa   "+
				"AND P.EstadoEncuentra in (1,2)  "+
				"and p.cancelado = 0  "+
				"AND P.idPedido not in (select distinct IdPedido from ecommerce_estadospedido where idEmpresa="+idEmpresa+" AND idEstado >2)  "+
				"AND R.Confirmado=0    "+
				"AND R.idArticulo='"+idarticulo+"' "+whPick+
				" "+
				"UNION "+
				" "+
				"select EPA.idPedido,P.stampTime fecha, 0 "+
				"FROM ecommerce_pedido_articulos EPA   "+
				"INNER JOIN ecommerce_pedido P on P.idEmpresa=EPA.idEmpresa AND P.idPedido=EPA.idPedido  "+
				"LEFT JOIN ecommerce_pedido_articulos_req T ON T.idEmpresa=EPA.idEmpresa AND T.idPedido=EPA.idPedido AND EPA.idArticulo=T.idArticulo  "+
				"WHERE EPA.idEmpresa="+idEmpresa+" AND T.idPedido IS NULL  "+
				"AND EPA.idPedido not in (select distinct IdPedido from ecommerce_estadospedido where idEmpresa="+idEmpresa+" AND idEstado >2)  "+
				"AND EPA.idArticulo = '"+idarticulo+"' "+
				") as T order BY T.idPedido";
		
		
		
		return econ.ClasificacionDarPedidos(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return new ArrayList<>();
	}
}

public List<DataIDDescripcion> PedidosDarTracking(String fechaI, String fechaF,String destino,boolean procesado, int idEmpresa){
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
		String qDestino="";
		String process = "";
		if(!destino.equals("")){
			qDestino = " and EPD.idDestino="+destino+" ";
		}
		if(procesado){
			process = " and EP.estadoencuentra=3 and EPD.cerrado=0 ";
		}
		String consulta = "SELECT EPD.idPedido, EPD.tracking, EPAR.PTimeStamp AS Fecha_Procesado "
						+ "	FROM  ecommerce_pedido_destino EPD "
						+ "INNER JOIN ecommerce_pedido_articulos_req EPAR ON EPD.idPedido=EPAR.idPedido AND EPD.idEmpresa=EPAR.idEmpresa "
						+ "INNER JOIN ecommerce_pedido EP ON EP.idPedido=EPD.idPedido AND EP.idEmpresa=EPD.idEmpresa "
						+ "WHERE EPD.idEmpresa="+idEmpresa+" AND PTimeStamp BETWEEN '"+fechaI+"' AND '"+fechaF+"' "+qDestino+process+" GROUP BY ep.idpedido " ;

		return econ.darListaDataIdDescripcionDB(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return new ArrayList<>();
	}
}

public List<DataIDDescripcion> PedidosPendientesExpedicion(String fechaI, String fechaF,String destinos, int idEmpresa){
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
		String qDestino="";
		if(destinos!=null && !destinos.equals("")){
			qDestino = " AND idDeposito IN ("+destinos+") ";
		}

		String consulta = 	"select distinct EPD.idPedido, p.stampTime , DE.Nombre, DE.idDeposito from ecommerce_pedido_destino EPD " + 
							"inner join depositos DE ON DE.idDeposito = EPD.idDestino AND DE.IdEmpresa=EPD.IdEmpresa " + 
							"inner join ecommerce_pedido p on p.idPedido=EPD.idPedido AND p.IdEmpresa=EPD.IdEmpresa " + 
							"WHERE EPD.Cerrado=0 and p.EstadoEncuentra in (3) AND EPD.IdEmpresa="+idEmpresa+" AND p.stampTime BETWEEN '"+fechaI+"' AND '"+fechaF+"' " ;

		System.out.println(consulta+qDestino);
		return econ.darListaDataIdDescripcionDB(consulta+qDestino);

	} catch (Exception e) {

		e.printStackTrace();
		return new ArrayList<>();
	}
}

public  List<DataIDDescripcion> ArticulosECSinPedir(int idEmpresa){
	
	String query="select * from "+
				"( "+
				"SELECT E.CantidadRequerida,E.idArticulo, E.idPedido,E.docVisual,R.idArticulo articuloP, E.RTimeStamp from "+
				"ecommerce_pedido_articulos_req E "+
				"left outer join reposicion_articulos R on R.Seccion = E.idPedido and R.idArticulo = E.idArticulo and R.idEmpresa = E.idEmpresa "+
				"where E.idEmpresa="+idEmpresa+" AND E.Deposito = 9000 and E.confirmado=0 AND E.RTimeStamp>'2020-05-25' "+
				") t0 "+
				"where t0.articuloP is NULL ";
	try
	{

		@SuppressWarnings("unused") Connection cone;		
		cone = econ.getConnection();
		
		List<DataIDDescripcion> lista = econ.darListaECSinPedir(query);	
		return lista;
	}
	catch (Exception e)
	{
	
	}

	return new ArrayList<>();
}


public  void UpdatePickingStatus(int estado, int idPick, int idEmpresa) 
{
	String update="UPDATE reposicion_articulos SET estado="+estado+" WHERE idEmpresa="+idEmpresa+" AND idPicking="+idPick;
	try{
		
		System.out.println(update);
		eper.persistir(update);
	}catch(Exception e){
		e.printStackTrace();
	}
	
}

public  List<DataPicking> encuentraDarNoEncontrados(int idPick, int idEmpresa) 
{

	@SuppressWarnings("unused") Connection cone;
	String consulta="SELECT RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario,USU.Nickname, RAR.Cantidad, RAR.Picked,RAR.Remitidas, RAR.IdPacking,IF(GROUP_CONCAT(DISTINCT ABR.Barra) IS NULL,'SINCB',GROUP_CONCAT(DISTINCT ABR.Barra)) BARRAS,RAR.Verif,idPosSort,Mayorista, RAR.Seccion as pedido ,RAR.idLineaSAP, AR.Descripcion,RAR.IdSolicitudTraslado,0 "
			+ " FROM  reposicion_articulos RAR "
			+ " left outer join artbarra ABR on ABR.idArticulo = RAR.idArticulo AND ABR.idEmpresa = RAR.idEmpresa "
			+ " left outer join art_descripcion AR on AR.id=RAR.idArticulo AND AR.idEmpresa=RAR.idEmpresa , "
			+ "  depositos DOR, "
			+ " depositos DES, "
			+ " usuarios USU "
			+ " WHERE "
			+ " USU.idUsuario = RAR.idUsuario AND USU.idEmpresa = RAR.idEmpresa "
			+ " AND DOR.idDeposito = RAR.Origen AND DOR.idEmpresa = RAR.idEmpresa "
			+ " AND DES.idDeposito = RAR.Destino AND DES.idEmpresa = RAR.idEmpresa "
			+ " AND RAR.idEmpresa="+idEmpresa+" AND RAR.idPicking = "+idPick+" "
			+ " AND RAR.verif < RAR.cantidad AND RAR.Estado not in (4) GROUP BY RAR.IdSincronizacion, RAR.idArticulo, RAR.Origen, DOR.Nombre, RAR.Destino, DES.Nombre, RAR.idPicking, RAR.idUsuario";
	try {
		cone = econ.getConnection();
		List<DataIDDescripcion> list= econ.darIdDescripcion("SELECT distinct destino,'' FROM reposicion_articulos WHERE idEmpresa="+idEmpresa+" AND mayorista=1 and idpicking="+idPick);	
		if(list.size()==1 && list.get(0).getId()==1200){
			consulta += ",RAR.Seccion";
		}
	} catch (Exception e1) {
		
		e1.printStackTrace();
	}
	
	try {
		cone = econ.getConnection();	
		System.out.println(consulta);
		return econ.darPicking(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return new ArrayList<>();
	}
}




public String darArticuloAlEscanear (String barra, Hashtable<String, String> artBarra, int idEmpresa){
	
	String articulo = "";	
	
	try
	{
		articulo = artBarra.get(barra.toUpperCase());
	}
	catch(Exception e)
	{	
	}
	if(articulo==null || articulo.equals(""))
	{
		
		if(idEmpresa==8)
		{
			try
			{
				@SuppressWarnings("unused") Connection cone;
				cone = econ.getConnection();
				CallWSRFID call = new CallWSRFID();
				String ean = call.darEan(barra);
				String consulta = "select A.Id, A.Descripcion,0,0,0,0,1 from  art_descripcion A,  `artbarra` B "
						+ "where A.idEmpresa=B.idEmpresa and B.idEmpresa="+idEmpresa+" and  A.Id = B.IdArticulo AND B.Barra = '"+ean+"';";
				DataArticulo ret = econ.codArticulo(consulta);
				if(ret!=null)
				{
					return ret.getId();
				}
			}
			catch (Exception e) 
			{
				
			}
			
		}
		try
		{
			List<String> articulos = new ArrayList<>(artBarra.values());
			for (String a : articulos) 
			{
				if(barra.equals(a))
				{
					//puso el articulo y no el codebar
					articulo=barra;
					break;
				}
			}
		}
		catch(Exception e){
			System.out.println("Esta buscando bulto");
			articulo=barra;
		}
	}
	return articulo;
	}

public  Hashtable<String, String> NoRemplazarDistribuciones(int idEmpresa){
	
	String query="SELECT idarticulo,origen,destino,idsolicitudtraslado "
			+ " from  reposicion_articulos "+ 
				 "WHERE idEmpresa="+idEmpresa+" AND idpicking !=0 AND idsolicitudtraslado IN (	"+
					"SELECT idsolicitudtraslado FROM reposicion_articulos "+
					"where  idEmpresa="+idEmpresa+" AND estado = 1 and origen = 9000 and destino !=1200)";
	try
	{

		@SuppressWarnings("unused") Connection cone;		
		cone = econ.getConnection();
		
		Hashtable<String, String> lista = econ.DarNoRemplazarDistribuciones(query);	
		return lista;
	}
	catch (Exception e)
	{
	
	}

	return null;
}

public List<ReportObject> DistribucionesPorPicking(String fechaI, String fechaF, int idEmpresa){
	@SuppressWarnings("unused") Connection cone;
	try {
		cone = econ.getConnection();
		
		String consulta = "SELECT ra.idPicking,SUM(ra.Cantidad),SUM(ra.Verif),0 "+
						",GROUP_CONCAT(distinct ra.IdSolicitudTraslado),CONCAT(u.Nombre,' ',u.Apellido),'','' FROM reposicion_articulos ra "+
						"INNER JOIN picking p ON p.id=ra.idPicking AND  p.idEmpresa=ra.idEmpresa "+
						"INNER JOIN tareas t ON t.idDoc=p.id AND t.idDoc=ra.idPicking AND t.idEmpresa=ra.idEmpresa "+
						"INNER JOIN usuarios u ON u.idUsuario=t.idUsuario AND u.idEmpresa=t.idEmpresa "+
						"WHERE p.idEmpresa="+idEmpresa+" AND idpicking!=0 AND ra.IdSolicitudTraslado!=0 AND destino!=1200 and p.fecha between '"+fechaI+"' and '"+fechaF+"' "+
						"GROUP BY ra.idPicking; "; 

		return econ.DarDistribucionesPorPicking(consulta);

	} catch (Exception e) {

		e.printStackTrace();
		return new ArrayList<>();
	}
}

	/****************************************BULTOS*************************************/
	public void CrearBulto(bulto b, int idEmpresa) 
	{
		
		int remision = 0;
		if(b.isRemision_al_cerrar()) {
			remision = 1;
		}
		String insert = "";
		String numero = "0";
		if(b.getPedido()!=0){
			numero = "select ifnull(max(numerador)+1,1) from bulto where idempresa="+idEmpresa+" and pedido="+b.getPedido();
			try {
				econ.getConnection();
				b.setNumerador(econ.UltimoId(numero, true));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		insert="insert into bulto (idBulto,descripcion,usuario,ancho,alto,prof,peso,fragil,destino,equipo, idEmpresa,remision_al_cerrar,posSort,pedido,numerador) values "
				+ "('"+b.getIdBulto()+"','"+b.getDescripcion()+"',"+b.getUsuario()+","+b.getAncho()+","+b.getAlto()+","+
				b.getProf()+","+b.getPeso()+","+b.isFragil()+",'"+b.getDestino()+"',"+b.getEquipo_trabajo()+","+idEmpresa+","+remision+",'"+b.getPosSort()+"',"+b.getPedido()+","+b.getNumerador()+"); "+
				"insert into articulos (idarticulo,descripcion,idTipo,idEmpresa) values ('"+b.getIdBulto()+"','"+b.getDescripcion()+"',4,"+idEmpresa+");";
		
		try{			
			System.out.println(insert);
			eper.persistir(insert);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public int crearBultosMasivo(int idRecepcion, List<bulto> bultos,int idEmpresa) 
	{
		String insertBultos = "insert into bulto (idBulto,cerrado,descripcion,usuario,ancho,alto,prof,peso,fragil,destino,equipo, idEmpresa,remision_al_cerrar) VALUES ";
		String insertContenido="insert into bulto_contenido (idBulto,idArticulo,cantidad,picking,recepcion,usuario, idEmpresa) values ";
		String insertArt =	"insert into articulos (idarticulo,descripcion,idTipo, idEmpresa) values ";
		
		StringBuilder valuesBUL = new StringBuilder();
		StringBuilder valuesCONT = new StringBuilder();
		StringBuilder  valuesArtBultos = new StringBuilder();
		boolean pri = true;
		for (bulto b : bultos) 
		{
			int remision = 0;
			if(b.isRemision_al_cerrar()) {
				remision = 1;
			}
			
			int cerrado = 0;
			if(b.getCerrado())
				cerrado = 1;
			
			bultoContenido bc = new bultoContenido();
			bc = b.getContenidoList().get(0);
			
			if(pri)
			{
				pri = false;
				valuesBUL.append("('"+b.getIdBulto()+"',"+cerrado+",'"+b.getDescripcion()+"',"+b.getUsuario()+","+b.getAncho()+","+b.getAlto()+","+
						b.getProf()+","+b.getPeso()+","+b.isFragil()+",'"+b.getDestino()+"',"+b.getEquipo_trabajo()+","+idEmpresa+","+remision+")");
				valuesCONT.append("('"+b.getIdBulto()+"','"+bc.getIdArticulo()+"',"+bc.getCantidad()+","+bc.getPicking()+","+bc.getRecepcion()+","+
						bc.getUsuario()+","+idEmpresa+")");
				valuesArtBultos.append("('"+b.getIdBulto()+"','"+b.getDescripcion()+"',4,"+idEmpresa+")");
			}
			else
			{
				valuesBUL.append(", ('"+b.getIdBulto()+"',"+cerrado+",'"+b.getDescripcion()+"',"+b.getUsuario()+","+b.getAncho()+","+b.getAlto()+","+
						b.getProf()+","+b.getPeso()+","+b.isFragil()+",'"+b.getDestino()+"',"+b.getEquipo_trabajo()+","+idEmpresa+","+remision+")");
				valuesCONT.append(", ('"+b.getIdBulto()+"','"+bc.getIdArticulo()+"',"+bc.getCantidad()+","+bc.getPicking()+","+bc.getRecepcion()+","+
						bc.getUsuario()+","+idEmpresa+")");
				valuesArtBultos.append(", ('"+b.getIdBulto()+"','"+b.getDescripcion()+"',4,"+idEmpresa+")");
			}
		}
			
		try 
		{
			eper.persistir(insertBultos+valuesBUL.toString());
			eper.persistir(insertContenido+valuesCONT.toString());
			eper.persistir(insertArt+valuesArtBultos.toString());
		} catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
			return 1;
	}
	
	/****************************************BULTOS*************************************/
	public void EliminarBulto(bulto b, int idEmpresa) 
	{
		String delete="DELETE FROM `bulto` WHERE  `idBulto`='"+b.getIdBulto()+"' and `idEmpresa`="+idEmpresa+";";
		try{			
			
			eper.persistir(delete);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public void CrearBultos(List<bulto> bultos, int idEmpresa) 
	{
		String insert="insert into bulto (idBulto,descripcion,usuario,ancho,alto,prof,peso,fragil,destino,equipo,idEmpresa) values ";
		String insertArt =	"insert into articulos (idarticulo,descripcion,idTipo, idEmpresa) values ";
		
		String valuesBultos = "";
		String valuesArtBultos = "";
		for (bulto b : bultos) 
		{
			valuesBultos+="('"+b.getIdBulto()+"','"+b.getDescripcion()+"',"+b.getUsuario()+","+b.getAncho()+","+b.getAlto()+","+b.getProf()+","+b.getPeso()+","+b.isFragil()+",'"+b.getDestino()+"',"+b.getEquipo_trabajo()+","+idEmpresa+"),";
			valuesArtBultos+="('"+b.getIdBulto()+"','"+b.getDescripcion()+"',4,"+idEmpresa+"),";
		}
		
		valuesArtBultos=valuesArtBultos.substring(0,valuesArtBultos.length()-1);
		valuesBultos=valuesBultos.substring(0,valuesBultos.length()-1);
		
		
		 
		try{			
			System.out.println(insert);
			eper.persistir(insert+valuesBultos+";"+insertArt+valuesArtBultos);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public void AgregarABulto(bultoContenido bc, String idBulto, int idEmpresa) 
	{
		String insert="insert into bulto_contenido (idBulto,idArticulo,cantidad,picking,recepcion,usuario, idEmpresa) values "
				+ "('"+idBulto+"','"+bc.getIdArticulo()+"',"+bc.getCantidad()+","+bc.getPicking()+","+bc.getRecepcion()+","+
				bc.getUsuario()+","+idEmpresa+") on duplicate key update cantidad="+bc.getCantidad();
		try{			
			System.out.println(insert);
			eper.persistir(insert);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public bulto AgregarABultoReposicion(bultoContenido bc, bulto b, DataPicking art, int idEmpresa) 
	{
		boolean salida = false;
		Hashtable<String,bultoContenido> bAux = new Hashtable<>();
		try{
			bAux = b.clonarContenido();
			if(b.getContenido().get(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion())==null){
				b.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(),bc);
			}
			else{
				int cant = b.getContenido().get(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion()).getCantidad();
				System.out.println(cant);
				int this_cant = bc.getCantidad();
				bc.setCantidad(cant + this_cant);
				b.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(),bc);
			}
			
			salida = eper.AgregarABulto_AfectarRepo(bc, b.getIdBulto(), art, idEmpresa);
			
			if (salida) {
				b.setPersistencia(salida);
				return b;
			}
			else {
				b.setPersistencia(salida);
				b.setContenido(bAux);
				return b;
			}
		}catch(Exception e){
			e.printStackTrace();
			b.setPersistencia(salida);
			b.setContenido(bAux);
			return b;
		}
	
	}
	
	public void GuardarDetalleBulto(List<bultoContenido> detBulto, String idBulto, int idEmpresa) 
	{
		String insert="";
		String insertMovStock="";
		for (bultoContenido detalle : detBulto) 
		{
			insert+="insert into bulto_contenido (idBulto,idArticulo,cantidad,picking,recepcion,usuario, idEmpresa) values "
					+ "('"+idBulto+"','"+detalle.getIdArticulo()+"',"+detalle.getCantidad()+","+detalle.getPicking()+","+detalle.getRecepcion()+","+
					detalle.getUsuario()+","+idEmpresa+") on duplicate key update cantidad="+detalle.getCantidad()+"; ";
			
			
		}
		
		
		try{			
			System.out.println(insert);
			eper.persistir(insert);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	public void CargarRemito(String idBulto, String remito, int tipo, int unidades, int idEmpresa) 
	{
		String insert="insert into bulto_remito (idBulto,tipo,doc,unidades,idEmpresa) values "
				+ "('"+idBulto+"',"+tipo+",'"+remito+"',"+unidades+","+idEmpresa+")";
		try{			
			System.out.println(insert);
			eper.persistir(insert);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public void Cargar_Caracteristica(String idBulto,String caracteristica, String valor, int idEmpresa)
	{
		String insert="insert into bulto_caracteristica (idBulto,caracteristica,valor, idEmpresa) values "
				+ "('"+idBulto+"','"+caracteristica+"','"+valor+"', "+idEmpresa+")";
		try{			
			System.out.println(insert);
			eper.persistir(insert);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public boolean Cerrar_Bulto(bulto b,boolean movimiento, int idEmpresa)
	{
		String existe = "select 1,idbulto from bulto where idbulto='"+b.getIdBulto()+"' and cerrado=0";
		
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		DataIDDescripcion bultoabierto = econ.darDataIdDescripcion(existe);
		
		if(bultoabierto!=null) {
			String insert="update bulto set cerrado=1, fechaClose=CURRENT_TIMESTAMP(), usuarioClose="+b.getUsuarioClose()+" where IdEmpresa="+idEmpresa+" AND idbulto='"+b.getIdBulto()+"'";
			String movS = "";
			if(movimiento)
			{
				movS = "insert into bulto_contenido_movimiento (idBulto,idArticulo,cantidad,picking,recepcion,usuario,IdEmpresa) "
						+ " select idBulto,idArticulo,cantidad,picking,recepcion,"+b.getUsuarioClose()+","+idEmpresa+" from bulto_contenido where IdEmpresa="+idEmpresa+" AND idBulto = '"+b.getIdBulto()+"'";
			}
			
			try{			
				System.out.println(insert);
				eper.persistir(insert);
				if(movimiento)
				{
					eper.persistir(movS);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}	
	}
	
	
	
	
	public void eliminarArticulosBultos(String cadenaBultosEliminar, int idEmpresa) {
		try
		{						
			String delete1 = "DELETE FROM articulos a WHERE a.idArticulo IN ("+cadenaBultosEliminar+") AND a.idEmpresa= "+idEmpresa+"";
			String delete2 = "DELETE FROM bulto b WHERE b.idBulto IN ("+cadenaBultosEliminar+") AND b.idEmpresa = "+idEmpresa+"";
			
			
			eper.persistir(delete1);
			eper.persistir(delete2);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public void bajarSubirDeBulto(String idBulto, List<DataIDDescripcion> articulos, int idEmpresa, int idUsuario, int multiplo, boolean movs)
	{
		String insert="insert into bulto_contenido_movimiento (idBulto,idArticulo,cantidad,picking,recepcion,usuario,IdEmpresa) values ";
		String updates= "";
		
		for (DataIDDescripcion a : articulos) 
		{
			int cantidad = a.getId()*multiplo;
			insert += "('"+idBulto+"','"+a.getDescripcion()+"',"+cantidad+",0,0,"+idUsuario+","+idEmpresa+"),";
			if(movs) {
				updates += "UPDATE bulto_contenido SET cantidad = cantidad+"+a.getId()*multiplo+" WHERE idArticulo = '"+a.getDescripcion()+"' AND idBulto = '"+idBulto+"' AND idEmpresa = "+idEmpresa+"; ";
			}
			
		}
		
		insert = insert.substring(0,insert.length()-1)+";";
		
		
		
		try
		{			
			System.out.println(insert);
			
			String delete="DELETE FROM ojostienenarticulos \r\n" + 
					"WHERE idArticulo IN ( \r\n" + 
					"SELECT idBulto from bulto \r\n" + 
					"WHERE idBulto IN \r\n" + 
					"(	\r\n" + 
					"SELECT idBulto \r\n" + 
					"FROM bulto_contenido bc \r\n" + 
					"WHERE  idEmpresa="+idEmpresa+" group by idbulto having sum( bc.Cantidad)=0  \r\n" + 
					") \r\n" + 
					"AND idEmpresa="+idEmpresa+"  AND idBulto='"+idBulto+"'     \r\n" + 
					"); ";
			delete += "DELETE FROM bulto_contenido WHERE idBulto = '"+idBulto+"' AND idEmpresa = "+idEmpresa+" AND cantidad = 0; ";
			
			
			/*
			String delete = "DELETE FROM bulto_contenido WHERE idBulto = '"+idBulto+"' AND idEmpresa = "+idEmpresa+"; ";
			String insertUp = "INSERT INTO bulto_contenido (idBulto,idArticulo,cantidad,picking,recepcion,fecha,usuario,IdEmpresa,cantidadReservada) "
							+ " SELECT idBulto,idArticulo,SUM(cantidad) 'cantidad', 0,0, CURRENT_TIMESTAMP(), "+idUsuario+", "+idEmpresa+"  "
							+ " FROM bulto_contenido_movimiento WHERE idBulto ='"+idBulto+"' AND idEmpresa = "+idEmpresa+"  GROUP BY idBulto,idArticulo,IdEmpresa HAVING SUM(cantidad)>0 ";
			*/
			
			eper.persistir(insert+updates+delete);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public String query_bajarSubirDeBulto(String idBulto, List<DataIDDescripcion> articulos, int idEmpresa, int idUsuario, int multiplo, boolean movs)
	{
		String insert="insert into bulto_contenido_movimiento (idBulto,idArticulo,cantidad,picking,recepcion,usuario,IdEmpresa) values ";
		String updates= "";
		
		for (DataIDDescripcion a : articulos) 
		{
			int cantidad = a.getId()*multiplo;
			insert += "('"+idBulto+"','"+a.getDescripcion()+"',"+cantidad+",0,0,"+idUsuario+","+idEmpresa+"),";
			if(movs) {
				updates += "UPDATE bulto_contenido SET cantidad = cantidad+"+a.getId()*multiplo+" WHERE idArticulo = '"+a.getDescripcion()+"' AND idBulto = '"+idBulto+"' AND idEmpresa = "+idEmpresa+"; ";
			}
			
		}
		
		insert = insert.substring(0,insert.length()-1)+";";
		
		
		
		try
		{			
			System.out.println(insert);
			
			String delete="DELETE FROM ojostienenarticulos \r\n" + 
					"WHERE idArticulo IN ( \r\n" + 
					"SELECT idBulto from bulto \r\n" + 
					"WHERE idBulto IN \r\n" + 
					"(	\r\n" + 
					"SELECT idBulto \r\n" + 
					"FROM bulto_contenido bc \r\n" + 
					"WHERE  idEmpresa="+idEmpresa+" group by idbulto having sum( bc.Cantidad)=0  \r\n" + 
					") \r\n" + 
					"AND idEmpresa="+idEmpresa+"  AND idBulto='"+idBulto+"'     \r\n" + 
					"); ";
			delete += "DELETE FROM bulto_contenido WHERE idBulto = '"+idBulto+"' AND idEmpresa = "+idEmpresa+" AND cantidad = 0; ";
			
			
			/*
			String delete = "DELETE FROM bulto_contenido WHERE idBulto = '"+idBulto+"' AND idEmpresa = "+idEmpresa+"; ";
			String insertUp = "INSERT INTO bulto_contenido (idBulto,idArticulo,cantidad,picking,recepcion,fecha,usuario,IdEmpresa,cantidadReservada) "
							+ " SELECT idBulto,idArticulo,SUM(cantidad) 'cantidad', 0,0, CURRENT_TIMESTAMP(), "+idUsuario+", "+idEmpresa+"  "
							+ " FROM bulto_contenido_movimiento WHERE idBulto ='"+idBulto+"' AND idEmpresa = "+idEmpresa+"  GROUP BY idBulto,idArticulo,IdEmpresa HAVING SUM(cantidad)>0 ";
			*/
			
			return insert+updates+delete;
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	
	}
	
	public Hashtable<String, bulto> BultosAbiertos(int idEmpresa){
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			
			String consulta = "SELECT B.idBulto,B.descripcion,B.cerrado,B.fecha,B.usuario,B.ancho,B.alto,B.prof,B.peso,B.fragil,B.destino,B.fechaClose,B.usuarioClose,B.tipoMercaderia,B.equipo,B.IdEmpresa,B.remision_al_cerrar,B.posSort,B.pedido,B.numerador,D.alias from bulto B INNER JOIN depositos D ON B.destino = D.idDeposito AND B.IdEmpresa = D.IdEmpresa where cerrado=0 AND B.IdEmpresa="+idEmpresa+""; 
			String consulta2 = "SELECT CONCAT(B.equipo,'-' ,B.posSort) ,BC.* from bulto_contenido BC INNER JOIN bulto B ON BC.idBulto = B.idBulto AND BC.IdEmpresa = B.IdEmpresa where BC.idBulto IN (@@) AND  BC.IdEmpresa="+idEmpresa+"";
			String consulta3 = "select CONCAT(B.equipo,'-' ,B.posSort) ,BC.* from bulto_caracteristica BC INNER JOIN bulto B ON BC.idBulto = B.idBulto AND BC.IdEmpresa = B.IdEmpresa where BC.idBulto IN (@@) AND  BC.IdEmpresa="+idEmpresa+"";
			String consulta4 = "select CONCAT(B.equipo,'-' ,B.posSort) ,BC.* from bulto_remito BC INNER JOIN bulto B ON BC.idBulto = B.idBulto AND BC.IdEmpresa = B.IdEmpresa where BC.idBulto IN (@@) AND  BC.IdEmpresa="+idEmpresa+"";

			return econ.DarBultosAbiertos(consulta, consulta2,consulta3,consulta4,idEmpresa);

		} catch (Exception e) {

			e.printStackTrace();
			return new Hashtable<>();
		}
	}
	
	public Hashtable<Long, Integer> Numerador_bultos(int idEmpresa){
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			
			String consulta = "SELECT b.pedido,COUNT(b.idbulto) FROM bulto b "+
								"WHERE pedido IN ( "+
									"SELECT DISTINCT seccion FROM reposicion_articulos WHERE estado!=7 AND idEmpresa=5 "+
								") and pedido!=0 and b.IdEmpresa=5 "+
								"GROUP BY b.pedido;"; 

			return econ.Numerador_bultos(consulta,idEmpresa);

		} catch (Exception e) {

			e.printStackTrace();
			return new Hashtable<>();
		}
	}


	public List<Integer> darSeries(String queryInsert) 
	{
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			int idOrigen = econ.UltimoId("select max(idSerie) from utilseries",false);
			idOrigen++;
			eper.persistir(queryInsert);
			
			int idfin = econ.UltimoId("select max(idSerie) from utilseries",false);
			List<Integer> retorno = new ArrayList<>();
			for (int i = idOrigen; i <= idfin; i++) 
			{
				retorno.add(i);
			}
			
			return retorno;

		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	/****************************************BULTOS*************************************/
	
	
	public  void updateRecepcionConfirmarCantidades(int idRecepcion, int IdUsuario, int idEmpresa) throws Exception 
	{
		
		
		
		Connection con = econ.getConnection();
		con = econ.getConnection();
		
		String select="select ar.cantidadContada-ar.cantidadRecibida,ar.idArticulo,ar.id "
					+ " from recepcion r,"
					+ " detalleRecepcion dr, "
					+ " articulosarecepcionar ar "
					+ " where r.idEmpresa=dr.idEmpresa AND r.idRecepcion=dr.idRecepcion AND r.idEmpresa=dr.idEmpresa "
					+ " and dr.idEmpresa=ar.idEmpresa AND  dr.idDetalle=ar.idDetalle AND dr.idEmpresa=ar.idEmpresa "
					+ " AND AR.idEmpresa="+idEmpresa+" AND ar.cantidadContada >0 and ar.cantidadRecibida<ar.cantidadContada  AND R.idRecepcion="+idRecepcion;
		List<DataIDIDDescripcion>articulosEnRecepcion=econ.darArticulosRecepcion(select);
		for (DataIDIDDescripcion articulo : articulosEnRecepcion) 
		{
			//id-> cantidadContada
			//Descripcion-> idArticulo
			//iId-> idDetalle
			String update="update articulosarecepcionar ar set ar.cantidadRecibida = ar.cantidadRecibida+"+articulo.getId()+" where idEmpresa="+idEmpresa+" AND  id="+articulo.getIid();
			try 
			{
				eper.persistir(update);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		
		String update2="update detallerecepcion DR "
				+ "	set DR.cantidadRecepcionada="
				+ "	("
				+ "		select sum(ar.CantidadContada) as contadas "
				+ "		from articulosarecepcionar ar "
				+ "		where  DR.idEmpresa="+idEmpresa+" AND DR.idEmpresa=AR.idEmpresa AND DR.idDetalle = ar.idDetalle and DR.idRecepcion = "+idRecepcion+")";
		try 
		{
			
			eper.persistir(update2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		String update3="update recepcion r "
				+ "		set r.cantidadRecibida="
				+ "		("
				+ "			select sum(ar.CantidadContada) as contadas "
				+ "			from articulosarecepcionar ar , detallerecepcion DR "
				+ "			where DR.idDetalle = ar.idDetalle AND DR.idEmpresa =AR.idEmpresa AND DR.idEmpresa="+idEmpresa+" and DR.idRecepcion = "+idRecepcion+")"
						+ " where r.idEmpresa="+idEmpresa+" and r.idRecepcion = "+idRecepcion+"";
		try 
		{
		
			eper.persistir(update3);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		try 
		{
				con=econ.getConnection();
				ActualizarEstadoRecepcion(idRecepcion, 3,idEmpresa);
				
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
		}
		
	
	}
	
	
	public  void updateRecepcionConfirmarCantidadesAll(int idRecepcion, int IdUsuario, int idEmpresa) throws Exception 
	{
		
		
		
		@SuppressWarnings("unused")
		Connection con = econ.getConnection();
		con = econ.getConnection();
		
		String select="select ar.cantidadPedida,ar.idArticulo,ar.id "
					+ " from recepcion r,detalleRecepcion dr, articulosarecepcionar ar "
					+ " where "
					+ "	r.idRecepcion=dr.idRecepcion AND dr.idEmpresa=r.idEmpresa "
					+ "and dr.idDetalle=ar.idDetalle  AND dr.idEmpresa = ar.idEmpresa	"
					+ " and ar.cantidadRecibida=0 AND ar.cantidadContada=0 AND ar.idEmpresa="+idEmpresa+" AND R.idRecepcion="+idRecepcion;
		List<DataIDIDDescripcion>articulosEnRecepcion=econ.darArticulosRecepcion(select);
		for (DataIDIDDescripcion articulo : articulosEnRecepcion) 
		{
			//id-> cantidadPedida
			//Descripcion-> idArticulo
			//iId-> idDetalle
			String update="update articulosarecepcionar ar set ar.cantidadRecibida = ar.cantidadRecibida+"+articulo.getId()+",ar.cantidadContada = ar.cantidadContada+"+articulo.getId()+" where ar.idEmpresa="+idEmpresa+" AND id="+articulo.getIid();
			try 
			{
				eper.persistir(update);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			
			encuentraUpdateOjos("0", articulo.getDescripcion(),articulo.getId(), false, IdUsuario,false,"RCM",idEmpresa);
		
			
			
			
		}
		
		String update2="update detallerecepcion DR "+ 	
						"	 join (select sum(ar.CantidadContada) as contadas, DR.idRecepcion from articulosarecepcionar ar,  detallerecepcion DR where DR.idEmpresa=AR.idEmpresa AND  DR.idDetalle = ar.idDetalle and DR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+idRecepcion+" ) T "+
						"	 ON T.idRecepcion = DR.idRecepcion "+
						"	 SET DR.cantidadRecepcionada = T.contadas, "+
						"	 DR.CantidadContada = T.contadas "+
						"	 where DR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+idRecepcion+"";
		try 
		{
			
			eper.persistir(update2);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		String update3="update recepcion r set r.cantidadRecibida="
				+ "			("
				+ "				select sum(ar.CantidadContada) as contadas "
				+ "				from articulosarecepcionar ar , detallerecepcion DR "
				+ "				where  DR.idEmpresa=AR.idEmpresa AND DR.idDetalle = ar.idDetalle AND DR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+idRecepcion+""
						+ ") ,"
						+ "r.cantidadContada="
				+ "			("
				+ "				select sum(ar.CantidadContada) as contadas "
				+ "				from articulosarecepcionar ar , detallerecepcion DR "
				+ "				where  DR.idEmpresa=AR.idEmpresa AND DR.idDetalle = ar.idDetalle AND DR.idEmpresa="+idEmpresa+" AND DR.idRecepcion = "+idRecepcion+""
						+ ")"
						+ "WHERE r.idEmpresa="+idEmpresa+" AND r.idRecepcion = "+idRecepcion+"";
		try 
		{
		
			eper.persistir(update3);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		try 
		{
				con=econ.getConnection();
				ActualizarEstadoRecepcion(idRecepcion, 3,idEmpresa);
				
		} 
		catch (Exception e) 
		{
				e.printStackTrace();
		}
		
	
	}
	
	public bulto BuscarBulto(String id, int idEmpresa){
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			
			String consulta = "select * from bulto where idEmpresa="+idEmpresa+" AND idBulto ='"+id+"' "; 
			String consulta2 = "select * from bulto_contenido where  idEmpresa="+idEmpresa+" AND  idBulto='@@'";
			String consulta3 = "select * from bulto_caracteristica where idEmpresa="+idEmpresa+" AND  idBulto='@@'";
			String consulta4 = "select * from bulto_remito where  idEmpresa="+idEmpresa+" AND idBulto='@@'";

			return econ.DarBulto(consulta, consulta2,consulta3,consulta4,idEmpresa);

		} catch (Exception e) {

			e.printStackTrace();
			return new bulto();
		}
	}
	
	
	/****************************************EXPEDICION*************************************/
	public  List<DataIDDescripcion> ArticulosEnEstanteriaClasificacion(String dep, int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
		cone = econ.getConnection();
		
		String query = "";
		if(dep.equals("")){
			query = "SELECT SUM(ota.Cantidad),if(d.iddeposito>9000,d.nombre,CONCAT(d.idDeposito,'-',d.alias)),d.idDeposito FROM ojostienenarticulos ota "+
					"INNER JOIN ojos o on o.idOjo=ota.idOjo  AND o.idEmpresa=ota.idEmpresa "+
					"INNER JOIN depositos d ON d.idEmpresa=o.idEmpresa AND d.estanteriaPicking=o.idOjo AND d.estanteriaPicking!=0 "+
					"WHERE o.idEmpresa="+idEmpresa+" AND o.idOjo!='1200P' "+
					"GROUP BY ota.idojo order by d.idDeposito;";
		}
		else {
			String qDep = "";			
			
			if(!dep.equals("sort")) {
				qDep = " AND d.iddeposito = "+dep;
			}
			query = "SELECT 3,ota.idArticulo, if(d.iddeposito>9000,d.nombre,CONCAT(d.idDeposito,'-',d.alias)), d.idDeposito,ota.idArticulo FROM ojostienenarticulos ota "+
					"INNER JOIN ojos o on o.idOjo=ota.idOjo AND o.idEmpresa=ota.idEmpresa "+
					"INNER JOIN depositos d ON d.idEmpresa=o.idEmpresa AND d.estanteriaPicking=o.idOjo AND d.estanteriaPicking!='0' "+
					"INNER JOIN bulto b on b.idBulto=ota.idArticulo AND b.idEmpresa=ota.idEmpresa "+
					"LEFT OUTER JOIN bulto_remito br on b.idBulto=br.idBulto AND b.idEmpresa=br.idEmpresa "+
					"where o.idEmpresa="+idEmpresa+" AND b.cerrado=1 "+qDep+ "and br.idBulto != br.doc "+
					" UNION ALL "+
					"SELECT 6,ota.idArticulo, if(d.iddeposito>9000,d.nombre,CONCAT(d.idDeposito,'-',d.alias)), d.idDeposito,ota.idArticulo FROM ojostienenarticulos ota "+
					"INNER JOIN ojos o on o.idOjo=ota.idOjo AND o.idEmpresa=ota.idEmpresa "+
					"INNER JOIN depositos d ON d.idEmpresa=o.idEmpresa AND d.estanteriaPicking=o.idOjo AND d.estanteriaPicking!='0' "+
					"INNER JOIN bulto b on b.idBulto=ota.idArticulo AND b.idEmpresa=ota.idEmpresa "+
					"LEFT OUTER JOIN bulto_remito br on b.idBulto=br.idBulto AND b.idEmpresa=br.idEmpresa "+
					"where o.idEmpresa="+idEmpresa+" AND b.cerrado=1 "+qDep+ "and br.idBulto = br.doc "+
					" UNION ALL "+
					"SELECT 2,ota.idArticulo, if(d.iddeposito>9000,d.nombre,CONCAT(d.idDeposito,'-',d.alias)), d.idDeposito, "+
					"CASE "
					+ "WHEN d.iddeposito=50000 THEN substring(pd.tracking,1,17) "
					+ "WHEN d.iddeposito=60000 THEN substring(pd.tracking,1,17) "
					+ "WHEN d.iddeposito=800000 THEN substring(pd.tracking,1,17) "
					+ "ELSE pd.tracking "
					+"END"
					+ " FROM ojostienenarticulos ota "+
					"INNER JOIN ojos o on o.idOjo=ota.idOjo AND o.idEmpresa=ota.idEmpresa "+
					"INNER JOIN depositos d ON d.idEmpresa=o.idEmpresa AND d.estanteriaPicking=o.idOjo AND d.estanteriaPicking!=0 "+
					"INNER JOIN ecommerce_pedido p on p.idPedido=ota.idArticulo AND  p.idEmpresa=ota.idEmpresa "+
					"INNER JOIN ecommerce_pedido_destino pd on p.idPedido=pd.idpedido AND pd.iddestino=d.iddeposito AND  pd.idEmpresa=p.idEmpresa "+
					"where o.idEmpresa="+idEmpresa+" AND pd.Cerrado = 0 AND p.estadoEncuentra=3 "+qDep;
				/*query = "SELECT 3,ota.idArticulo FROM ojostienenarticulos ota "+
						"INNER JOIN ojos o on o.idOjo=ota.idOjo AND o.idEmpresa=ota.idEmpresa "+
						"INNER JOIN depositos d ON d.idEmpresa=o.idEmpresa AND d.estanteriaPicking=o.idEstanteria AND d.estanteriaPicking!=0 "+
						"INNER JOIN bulto b on b.idBulto=ota.idArticulo AND b.idEmpresa=ota.idEmpresa "+
						"where o.idEmpresa="+idEmpresa+" AND b.cerrado=1 and d.iddeposito = "+dep+
						" UNION ALL "+
						"SELECT 2,ota.idArticulo FROM ojostienenarticulos ota "+
						"INNER JOIN ojos o on o.idOjo=ota.idOjo AND o.idEmpresa=ota.idEmpresa "+
						"INNER JOIN depositos d ON d.idEmpresa=o.idEmpresa AND d.estanteriaPicking=o.idEstanteria AND d.estanteriaPicking!=0 "+
						"INNER JOIN ecommerce_pedido p on p.idPedido=ota.idArticulo AND  p.idEmpresa=ota.idEmpresa "+
						"where o.idEmpresa="+idEmpresa+" AND d.iddeposito = "+dep;*/	
			
		}
		
		return econ.darListaDataIdDescripcion(query);
		} 
		catch (Exception e) 
		{
			return new ArrayList<>();
		}
	}
	
	public List<Integer> GenerarDocumnetoExpedicion(int depoEnvio,	Hashtable<Integer, List<DataIDDescripcion>> paquetes, Usuario u, int idDepo, int idEmpresa) 
	{
		List<List<DataIDDescripcion>> paq = new ArrayList<List<DataIDDescripcion>>(paquetes.values());
		List<Integer> salida = new ArrayList<>();
		// guarda el cabezal
		try 
		{
			for(List<DataIDDescripcion> pedidosSave: paq){
				int idRazon = pedidosSave.get(0).getId();
				String queryCab = "INSERT INTO `envio_encuentra_documento` (`idDestino`, `idEnvio`, `idRazon`,`usuario`,`idOrigen`,`idEmpresa`) VALUES ("+depoEnvio+", '0',"+idRazon+", "+u.getNumero()+","+idDepo+","+idEmpresa+");";
				StringBuilder sb = null;
				
				int idEnvio = 0;
				//guarda las lineas
				
				idEnvio = eper.persistirDarUltimo(queryCab, "envio_encuentra_documento", "idDocumento", idEmpresa);
				
				for (DataIDDescripcion p : pedidosSave) 
				{
					sb = new StringBuilder();
					sb.append("INSERT INTO `envio_encuentra_documento_lineas` (`idDocumento`, `idArticulo`, `Cantidad`,`idEmpresa`) VALUES ("+idEnvio+", '"+p.getDescripcion()+"', 1,"+idEmpresa+");");
					
					switch (idRazon) {
					case 2:	//ECOMMERCE
						//for(DataIDDescripcion pe : pedidosSave){
							sb.append("INSERT INTO `envio_encuentra_documento_remitos` (`idDocumento`, `URLRemito`, `Cantidad`,`idEmpresa`) VALUES ("+idEnvio+", '"+p.getDescripcion()+"', 1,"+idEmpresa+");");
							sb.append("UPDATE `ecommerce_pedido_destino` SET `Cerrado`='1' WHERE  `idEmpresa`="+idEmpresa+" AND `idPedido`="+p.getDescripcion()+";");
							updateEcommerceEstado(Long.parseLong(p.getDescripcion()), 34,idEmpresa,u);//moviendo pedido a expedicion
						//}
						
						break;
					case 6:
					case 3:	//BULTOS
						bulto b = BuscarBulto(p.getDescripcion(),idEmpresa);
						for(DataIDDescripcion r : b.getRemitos()){
							sb.append("INSERT INTO `envio_encuentra_documento_remitos` (`idDocumento`, `URLRemito`, `Cantidad`,`idEmpresa`) VALUES ("+idEnvio+", '"+r.getDescripcion()+"', "+r.getId()+","+idEmpresa+");");
						}
						
						break;
					case 4:	//TRANSITO
						//BULTOS
						bulto bt = BuscarBulto(p.getDescripcion(),idEmpresa);
						for(DataIDDescripcion r : bt.getRemitos()){
							sb.append("INSERT INTO `envio_encuentra_documento_remitos` (`idDocumento`, `URLRemito`, `Cantidad`,`idEmpresa`) VALUES ("+idEnvio+", '"+r.getDescripcion()+"', "+r.getId()+","+idEmpresa+");");
						}
						break;
					case 5:	//CROSS DOCKING
						
						break;
					default:
						break;
					}
					
					persistir(sb.toString());
				}
				salida.add(idEnvio);
			}
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return salida;
		
	}
	
	
	
	
	public List<DataIDDescripcion> encuentraOjosExpedicion(String dep, String ojo, int idEmpresa) 
	{
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			String qDep = "";
			String qOjo = "";
			if(!dep.equals("")&&!dep.equals("sort")){
				qDep = "and d.idDeposito = "+dep;	
			}
			if(!ojo.equals("")){
				qOjo = "and o.idOjo = "+ojo;	
			}
			
			String query = "select d.iddeposito,o.idojo from ojos o "+
						   "inner join depositos d on o.idOjo = d.estanteriaExpedicion AND o.idEmpresa = d.idEmpresa "+
						   "where d.idEmpresa="+idEmpresa+" AND true "+qDep+qOjo;					
			
			return econ.darListaDataIdDescripcion(query);							
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return new ArrayList<>();			
		}		
	}
	/****************************************EXPEDICION*************************************/


	public Hashtable<String, Integer> darCantidadRecibida(String ordenesIn, int idEmpresa) 
	{
		Hashtable<String, Integer> retorno = new Hashtable<>();
		@SuppressWarnings("unused") Connection cone;
		try 
		{
		cone = econ.getConnection();
		
		String query = "Select DR.idDocumento, AR.idArticulo, SUM(AR.cantidadPedida) "
				+ " from articulosarecepcionar AR "
				+ " inner join detallerecepcion DR on AR.idDetalle = DR.idDetalle AND AR.idEmpresa = DR.idEmpresa "
				+ " INNER JOIN recepcion R ON R.idRecepcion = DR.idRecepcion AND R.IdEmpresa = DR.IdEmpresa"
				+ " WHERE DR.idEmpresa="+idEmpresa+" AND R.estado!=5   AND DR.idDocumento in ("+ordenesIn+") group by DR.idDocumento, AR.idArticulo";
		
		List<DataIDDescripcion> lista = econ.darListaDataIdDescripcion(query);
		lista.remove(0);
		for (DataIDDescripcion a : lista) 
		{
			//id=idDocumento
			//descripcion=idArticulo
			//descripcionB=cantidadPedida
			
			retorno.put(a.getId()+"-"+a.getDescripcion(), Integer.parseInt(a.getDescripcionB()));
		}
		
		return retorno;
		
		} 
		catch (Exception e) 
		{
			return null;
		}
		
	}
	
	
	public List<DataIDDescripcion> DescripcionArticulos(List<DataIDDescripcion> lista, int idEmpresa) 
	{
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			String arts = "";
			Hashtable<String, DataIDDescripcion> hash = new Hashtable<>();
			for(DataIDDescripcion d:lista){
				arts += "'"+d.getDescripcion()+"',";
				hash.put(d.getDescripcion(), d);
			}
			if(arts.length()>0){
				arts = arts.substring(0,arts.length()-1);
			}
			
			String query = "select * from art_descripcion where idEmpresa="+idEmpresa+" AND id in ("+arts+") order by id ";					
			
			return econ.DarDescripcionArticulos(hash, query);							
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return new ArrayList<>();			
		}		
	}


	public List<Remito> darRemitosPendientesDepo(String depo, String depoO, boolean TR) 
	{
		String qDepoOrigen = "";
		String qTR = "";
		if(depoO!=null && depoO.equals(""))
		{
			qDepoOrigen = " and Origen = "+depoO+" ";
		}
		if(TR)
		{
			qTR = " AND  Origen not in (9000, 1200) ";
		}
		/*
		String q1 = " SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select T0.*, T2.* from (SELECT IdDocumento, EntregaAfecta, Comentario, NumeroDoc, Origen, Destino, Fecha, SUM(cantidad) cantidad FROM dbo.encuentra_transferencias_pendientes "+  
					" where Destino = "+depo+" ";
		String q2 =	" group by  EntregaAfecta, NumeroDoc,Comentario, Origen, Destino, Fecha,IdDocumento "+ 
					"	)  T0 inner join (	select IdDocumento, IdArticulo, Cantidad   from dbo.encuentra_transferencias_pendientes ) T2 on t2.IdDocumento = t0.IdDocumento order by t0.IdDocumento ";
		*/
		
		String q1 = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT t0.IdDocumento, t0.EntregaAfecta, t0.Comentario, t0.NumeroDoc, t0.origen, t0.Destino, t0.Fecha, t0.cantidad, t0.IdArticulo, t1.cantidadTotal\r\n" + 
				"FROM dbo.encuentra_transferencias_pendientes t0\r\n" + 
				"INNER JOIN \r\n" + 
				"(\r\n" + 
				"SELECT NumeroDoc, SUM(cantidad) cantidadTotal\r\n" + 
				"FROM dbo.encuentra_transferencias_pendientes\r\n" + 
				"WHERE Destino = "+depo+"\r\n" + 
				"GROUP BY NumeroDoc\r\n" + 
				") t1 ON t1.NumeroDoc = t0.NumeroDoc\r\n" + 
				"WHERE  t0.Destino = "+depo;
		String q2 = "ORDER BY t0.IdDocumento;";
		
		try 
		{
			
			System.out.println(q1+qDepoOrigen+qTR+q2);	
			return MSSQL.DarRemitos(q1+qDepoOrigen+qTR+q2);							
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return new ArrayList<>();			
		}		
	}

	public Long darIdPedidoXDistri(int idEntrega, int idEmpresa) 
	{
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			
			
			String query = "SELECT idPedido FROM ecommerce_pedido_articulos_req WHERE docVisual = "+idEntrega+" AND IdEmpresa = "+idEmpresa;					
			
			return econ.DarIdPedidoXDistr( query);							
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return (long) 0;			
		}		
		
	}
	
	
	
	public Long darIdPedidoIdFenicio(String idFenicio, int idEmpresa) 
	{
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			
			
			String query = "SELECT idPedido FROM ecommerce_pedido WHERE idFenicio ="+idFenicio+" AND IdEmpresa = "+idEmpresa;					
			
			return econ.DarIdPedidoXDistr( query);							
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return (long) 0;			
		}		
		
	}
	
	public DataIDDescripcion darIdPedidoEstado(String idFenicio, int idEmpresa) 
	{
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();			
			
			String query = "SELECT 0,estadoencuentra,'',0,idPedido FROM ecommerce_pedido WHERE idFenicio ="+idFenicio+" AND IdEmpresa = "+idEmpresa;					
			
			return econ.darDataIdDescripcion(query);								
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return new DataIDDescripcion(0,"0");			
		}		
		
	}
	
	public Hashtable<Integer, DataIDDescripcion> darHasIdPedidoEstado(String idFenicio, int idEmpresa) 
	{
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();		
			
			String query = "SELECT idPedido,estadoencuentra,idFenicio FROM ecommerce_pedido WHERE idFenicio in ("+idFenicio+") AND IdEmpresa = "+idEmpresa;					
			
			return econ.darHasDataIdDescripcion(query);								
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return new Hashtable<>();			
		}		
		
	}
	
	public DataIDDescripcion darIdPedidoDocumento(int documento, int idEmpresa) 
	{
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();			
			
			String query = "SELECT idPedido,docvisual FROM ecommerce_pedido_articulos_Req WHERE docvisual ="+documento+" AND IdEmpresa = "+idEmpresa;					
			
			return econ.darDataIdDescripcion(query);								
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return new DataIDDescripcion(0,"0");			
		}		
		
	}
	
	public Hashtable<Integer, List<DataDescDescripcion>> darIdPedidosXDistri(String inns, int idEmpresa) 
	{
		
		try 
		{
			
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			
			
			String query = "SELECT idPedido,docVisual,idArticulo FROM ecommerce_pedido_articulos_req WHERE docVisual in ("+inns+") and docVisual!=0 AND IdEmpresa = "+idEmpresa;					
			
			return econ.DarIdPedidosXDistr( query);							
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return new Hashtable<Integer, List<DataDescDescripcion>>() ;			
		}		
		
	}


	public List<DataIDDescDescripcion> darDeposMovPend(int idEmpresa) 
	{
		List<DataIDDescripcion> depos = darListaDataIdDescripcionMYSQLConsulta("SELECT DE.IdDepositoSAP, DE.Nombre FROM depositos DE WHERE DE.idDeposito<100000 AND DE.IdEmpresa="+idEmpresa);
		depos.remove(0);
		
		List <DataIDDescDescripcion> depositosConTRS= MSSQL.darMovTransitoVisual("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select distinct TR.Destino, CAST (SUM(1) as int) as suma from dbo.encuentra_transferencias_pendientes TR where TR.Origen!=9000 and TR.Origen!=1200 AND TR.Destino!=9000 and TR.Destino!=1200 group by TR.Destino order by tr.Destino");
		
		for (DataIDDescDescripcion dtr : depositosConTRS) 
		{
			for (DataIDDescripcion d : depos) 
			{
				if(d.getId()==dtr.getId())
				{
					dtr.setDescripcion(d.getDescripcion());
				}
			}
		}
		
		
		return depositosConTRS;
	}


	public String darTrackingPedido(Long idPedido,int idEmpresa) 
	{
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			
			
			String query = "select tracking from ecommerce_pedido_destino where idPedido = "+idPedido+" AND IdEmpresa = "+idEmpresa;					
			
			return econ.DarTrackingXpedido(query);							
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return "";			
		}		
		
	}


	public List<DataIDDescripcion> darDataIdDescripcionAnySys(String q) 
	{
		
		return darListaDataIdDescripcionMYSQLConsulta(q);
	}
	
	 
	public int DeleteReception(int idR, int idEmpresa)
	{
		String delete="delete from recepcion where IdEmpresa="+idEmpresa+" AND idRecepcion="+idR+" and cantidadContada=0";
		int afectados = 0;		
		try{			
			System.out.println(delete);
			afectados = eper.persistirUpt(delete);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return afectados;
	
	}
	
	public DataIDDescripcion dataRecepcion(int idrecepcion) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			return econ.darDataIdDescripcion("Select 0,proveedor,vehiculo,0,0,fecha from recepcion where idrecepcion="+idrecepcion);
			
		}
		catch (Exception e)
		{
			return null;
		}
	}


	public Usuario loginEncuentraAPI2(String token) 
	{

		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			Usuario u = new Usuario();
			String consulta = "select U.* from usuarios U inner join apitoken T on T.idUsuario=U.idUsuario where Token = '"+token+"'";
			u = econ.login(consulta);
			
			return u;
		}
		catch (Exception e)
		{
			return null;
		}	
	}
	
	public DataIDDescripcion getDate() 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			return econ.darDataIdDescripcion("SELECT 0,CURRENT_TIMESTAMP()");			
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public List<DataIDDescripcion> canalesActivosEC(int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			return econ.darListaDataIdDescripcion("SELECT id,nombre FROM ecommerce_canal_ml WHERE activo=1 and id NOT IN (0,8,9,10,11,12) AND idempresa="+idEmpresa);			
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
	}
	
	public List<DataIDDescripcion> canalesActivosModdo(int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			return econ.darListaDataIdDescripcion("SELECT id,nombre,seller FROM ecommerce_canal_ml WHERE activo=1 and id IN (8,9,10,11,12) AND idempresa="+idEmpresa);			
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
	}
	
	public boolean canalActivoEC(int canal, int idEmpresa)
	{
		return hayRegistro("SELECT id,nombre FROM ecommerce_canal_ml WHERE activo=1 AND id="+canal+" AND idempresa="+idEmpresa);
	}
	
	public int RegistrarMovimientoStock(int origen, int destino, int usu, List<DataIDDescripcion> arts, int idEmpresa,int picking) {
		Connection cone;
		int idRegistro = 0;
		try {
			cone = eper.getConnection();
			
			
			String registro = "insert into movStock_cabezal(usuario,origen,destino,idEmpresa,picking) "
					+ "values("+usu+","+origen+","+destino+","+idEmpresa+","+picking+")";
			
			idRegistro = eper.persistirDarUltimo(registro, "movStock_cabezal", "id",idEmpresa);
			
			StringBuilder sb = new StringBuilder();
			for(DataIDDescripcion a:arts){
				sb.append("insert into movstock_detalle values ("+idRegistro+",'"+a.getDescripcion()+"',"+a.getId()+","+idEmpresa+");");
			}
			
			System.out.println(sb.toString());
			eper.persistir(sb.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return idRegistro;
	}
	
	public void RegistrarDocMovimientoStock(int ok, int idRegistro, int doc, String obs, int idEmpresa, int usu, int intentos) {
		Connection cone;
		try {
			cone = eper.getConnection();
			String registro = "update movStock_cabezal set estado = "+ok+", doc = "+doc+", observacion='"+obs+"'," 
					+ " fecha = CURRENT_TIMESTAMP(), usuario = "+usu+", intentos = intentos+"+intentos
					+ " where id = "+idRegistro+" and idEmpresa="+idEmpresa;
			
			eper.persistir(registro);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MovStock> queueMovsStock(int idEmpresa){
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			String query = "SELECT c.id,c.estado,c.destino,c.doc,sum(d.cantidad),c.fecha,c.observacion,CONCAT(u.nombre,' ',u.apellido), "
					+"c.origen,c.picking,c.intentos,dep.nombre FROM movStock_cabezal c "
					+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"INNER JOIN usuarios u on u.idusuario=c.usuario and u.idempresa=c.idempresa "
					+"INNER JOIN depositos dep on dep.idDeposito=c.destino and dep.idEmpresa=c.idEmpresa "
					+"WHERE c.idempresa="+idEmpresa+" GROUP BY c.id ORDER BY c.fecha desc limit 1000";
			cone = econ.getConnection();
			return econ.darQueueMovsStock(query);		
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
	}
	
	public MovStock MovStock(int id, int idEmpresa){
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			String query = "SELECT c.id,c.estado,c.destino,c.doc,sum(d.cantidad),c.fecha,c.observacion,CONCAT(u.nombre,' ',u.apellido), "
					+"origen,c.picking,c.intentos FROM movStock_cabezal c "
					+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"INNER JOIN usuarios u on u.idusuario=c.usuario and u.idempresa=c.idempresa "
					+"WHERE c.idempresa="+idEmpresa+" and c.id ="+id;
			
			String subQuery = "SELECT d.cantidad,d.idarticulo FROM movStock_cabezal c "
					+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"WHERE c.idempresa="+idEmpresa+" and c.id ="+id;
			
			cone = econ.getConnection();
			return econ.darMovsStock(query,subQuery);		
		}
		catch (Exception e)
		{
			return null;
		}
	}
	public  List<DataListaPicking> encuentraDarListaPicking(int idPicking, int idEmpresa) 
	{
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();

			String consulta = "SELECT RAO.pedido , RAO.idArticulo, RAO.Cantidad, AB.Barra, CONCAT(E.Descripcion, ' / Mod: ', O.idEstante, ' / Est: ',O.idModulo) AS Ubicacion, RAO.cUbicacion 'Ojo', RA.Justificacion, AI.url "
					+ "FROM reposicion_articulos_ojos RAO "
					+ "INNER JOIN reposicion_articulos RA ON RA.idPicking = RAO.idPicking AND RA.idArticulo=RAO.idArticulo AND RA.IdEmpresa=RAO.IdEmpresa "
					+ "INNER JOIN ( "
					+ "				SELECT * "
					+ "				FROM artbarra "
					+ "				WHERE idEmpresa= "+ idEmpresa	
					+ "				GROUP BY idArticulo "
					+ "			  ) AB "
					+ "				ON AB.idArticulo = RAO.idArticulo AND AB.IdEmpresa=RAO.IdEmpresa "
					+ "INNER JOIN ojos O ON O.idOjo = RAO.cUbicacion AND O.IdEmpresa=RAO.IdEmpresa "
					+ "INNER JOIN estanterias E ON E.idEstanteria = O.idEstanteria AND E.idEmpresa=O.IdEmpresa "
					+ "LEFT OUTER JOIN art_imagen AI ON AI.idArticulo=RAO.idArticulo AND AI.idEmpresa=RAO.IdEmpresa "
					+ "WHERE RAO.IdEmpresa="+ idEmpresa +" AND RAO.idpicking ="+ idPicking +" "
					+ "GROUP BY RAO.idArticulo, RAO.cUbicacion, RAO.pedido, RAO.Cantidad "
					+ "ORDER BY O.idRecorrido";
			return econ.darListaPickingExport(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	public List<ArticuloReposicion> darVentaLineasReposicion (String fecha, int idSinc, int porcentaje, boolean central, 
			Hashtable<String, Integer> stocksEnviados, String excluirDeps, int depC, int idEmpresa){
	
		String qExclusion = "";
		if(excluirDeps!=null && !excluirDeps.equals("")) {
			qExclusion = " AND t.depo NOT IN ("+excluirDeps+") ";
		}
		
		String query = "Select t.IdArticulo, t.IdMarca, t.IdSeccion, t.IdClase,t.depo, "+
				"t.Cantidad, stkC.stock as stkcentral, ISNULL(stkL.stock+stkL.stockPendiente,0) as stklocal, "+ 
				"t.precio,t.IdGenero, t.IdCategoria, t.IdTemporada "+ 
				"FROM dbo.encuentra_transacciones t "+
				"INNER JOIN dbo.encuentra_stock stkC "+
				"ON t.idarticulo=stkC.idarticulo AND stkC.deposit="+depC+" and stkC.stock>0 "+ 
				"LEFT OUTER JOIN dbo.encuentra_stock stkL "+
				"ON t.idarticulo=stkL.idarticulo AND stkL.deposit=t.depo "+
				"WHERE t.fechaSql >= '"+fecha+"' "+qExclusion+" "+
				"group by t.IdArticulo, t.depo,t.cantidad,t.IdMarca, t.IdSeccion, t.IdClase,t.IdGenero, "+
				"t.IdCategoria, stkC.stock,stkL.stock,stkL.stockPendiente, t.IdTemporada,t.precio";
				
		System.out.println(query);
		
		int intentos = 0;
		List<ArticuloReposicion> lista = MSSQL.darListaVLReposicion2(query, idSinc, porcentaje, stocksEnviados,idEmpresa);
		
		
		while(lista.isEmpty()&& intentos<4)
		{
			intentos++;
			lista = MSSQL.darListaVLReposicion2(query, idSinc, porcentaje, stocksEnviados,idEmpresa);
		}
		
		return lista;
	}//si dale
	
	


	


	

	public  List<DataArtPedidoPickup> darArticulosPedidoEcommercePickup(Long idPedido, boolean pick, int idEmpresa) 
	{
		Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		String qPick = " EP.EstadoEncuentra=2 ";
		if(pick) {
			qPick = " ER.procesarEnPickup = 1 AND EP.EstadoEncuentra in (1,2) ";
		}

		String consulta ="select ER.idPedido, ER.idArticulo, ER.CantidadRequerida, ER.CantidadRequerida-ER.CantidadProcesasa 'pendiente', "
				+ "EP.idCanalML, ER.deposito from "
				+ "ecommerce_pedido  EP inner join ecommerce_pedido_articulos_req ER "
				+ "on EP.idPedido = ER.idPedido and ER.idEmpresa=EP.idEmpresa "
				+ "where "+qPick
				+ "AND ER.idPedido ="+ idPedido + " and ER.idEmpresa="+idEmpresa;
		try
		{
			List<DataArtPedidoPickup> retorno =  econ.darArticulosPedidoEcommercePickup(consulta);
			return retorno;
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
	}
	
	public  void updatePickTiendas(Long idPedido, String idArticulo, int idEmpresa, boolean pickup, int idDep, int cantidad) 
	{
		try 
		{
			String consulta = "UPDATE `ecommerce_pedido_articulos_req` SET `CantidadProcesasa`= `CantidadProcesasa`+"+cantidad+" , "
					+ "PTimeStamp = CURRENT_TIMESTAMP()  WHERE idEmpresa="+idEmpresa+" AND `idPedido`="+idPedido+" AND "
							+ "`idArticulo`='"+idArticulo+"' AND `Deposito`="+idDep+" and CantidadProcesasa<CantidadRequerida;";
			if(pickup) {
				consulta += "UPDATE ecommerce_pedido_articulos_req SET CantidadPickeada=CantidadPickeada+1 "
						+ "WHERE idpedido="+idPedido+" AND idArticulo='"+idArticulo+"'  and idEmpresa="+idEmpresa+";";
			}
			
			
			eper.persistir(consulta);
				
		} 
		catch (Exception e) 
		{
			
		}

	}


	public Double darCostoEnvioPedido(Long idPedido, int idEmpresa) 
	{
		String q = "SELECT PD.CostoEnvio from ecommerce_pedido_destino PD WHERE PD.idPedido = "+idPedido+" AND PD.IdEmpresa = "+idEmpresa+"";
		
		
		Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try
		{
			
			return  econ.darCostoEnvioPedido(q);
		}
		catch (Exception e)
		{
			return 0.0;
		}
		
	}
	public boolean darFreeshippingEnvioPedido(Long idPedido, int idEmpresa) 
	{
		String q = "SELECT PD.isFreeShipping from ecommerce_pedido_destino PD WHERE PD.idPedido = "+idPedido+" AND PD.IdEmpresa = "+idEmpresa+"";
		
		
		Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try
		{
			
			return  econ.darBoolean(q);
		}
		catch (Exception e)
		{
			return false;
		}
		
	}



	public Cliente darClienteShippingEcommerce(Long idPedido, int idEmp) 
	{
		String q = "SELECT Nombre,Apellido,Direccion,Ciudad,Departamento,cpostal,telefono,mail,cedula,digito,DireccionNota, nroPuerta, apto FROM ecommerce_import_clientes WHERE idPedido = "+idPedido+" AND IdEmpresa="+idEmp+"";
		
		
		Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try
		{
			
			return  econ.darClienteShipping(q);
		}
		catch (Exception e)
		{

		}
		return null;
	}
	
	public Hashtable<Long, Cliente> darClientesShippingEcommerce(String idPedidos, int idEmp) 
	{
		String q = "SELECT Nombre,Apellido,Direccion,Ciudad,Departamento,cpostal,telefono,mail,cedula,digito,DireccionNota, nroPuerta, apto, idPedido FROM ecommerce_import_clientes WHERE idPedido in ( "+idPedidos+" ) AND IdEmpresa="+idEmp+"";
		
		
		Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try
		{
			
			return  econ.darClienteSshipping(q);
		}
		catch (Exception e)
		{

		}
		return null;
	}
	
	
	
	//////////////////////////////////////////////OPERACIONES TIENDA/////////////////////////////////////////////////////////
	
	public List<DataDescDescripcion> matchArticulo(String base, int idTienda, int idEmpresa) 
	{
			String q = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; select substring(A.idArticulo,1,13),CONCAT(A.DescCorta,' ',AP.Descripcion) dsc from Articulo A inner join ArtParte AP on AP.IdParte = 1 and AP.IdArtParte = substring(A.idArticulo,10,4)  inner join ArtBarra AB ON AB.IdArticulo = A.IdArticulo inner join MovStockTotal MS on A.IdArticulo = MS.IdArticulo where MS.stock>0  " + 
					" AND ((A.IdArticulo like '%"+base+"%') " + 
					" OR (A.DescCorta like '%"+base+"%') " + 
					" OR (AB.NroBarra = '"+base+"')) " + 
					" group by substring(A.idArticulo,1,13), A.DescCorta,AP.Descripcion";
			
			return MSSQL.DarArticulosMatch(q, idTienda, idEmpresa);
	}

	public  List<DataIDDescripcion> darDeposStockInStore(String base, int depo, int idTienda, int idEmpresa) 
	{
		String consulta = "";
		if(depo!=0)
		{
			consulta = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; select idDeposito,idDeposito  as dd  from MovStockTotal where SUBSTRING(IdArticulo,1,13) = '"+base+"' AND Stock>0 and iddeposito="+depo
					+ " group by IdDeposito order by IdDeposito";
		}
		else{
			consulta = "select idDeposito,idDeposito  as dd  from MovStockTotal where SUBSTRING(IdArticulo,1,13) = '"+base+"' AND Stock>0 group by IdDeposito order by IdDeposito";
		}
		
		return MSSQL.darListaIdDescripcionInStore(consulta, idTienda, idEmpresa);
	}

	public  Hashtable<String, Integer> ConsultarStockBaseColorInStore(String basesIn, String depo, int idTienda, int idEmpresa) 
	{
		String consulta = "";
		if(!depo.equals("0")){
			consulta = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; select IdArticulo, idDeposito, Stock from MovStockTotal where SUBSTRING(IdArticulo,1,13) in ("+basesIn+") "
					+ "AND Stock>0 and idDeposito="+depo;
		}
		else{
			consulta = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED; select IdArticulo, idDeposito, Stock from MovStockTotal where SUBSTRING(IdArticulo,1,13) in ("+basesIn+")  AND Stock>0 ";
		}
		
		
		return MSSQL.darHTStockInStore(consulta,idTienda,idEmpresa);
	}
	
	public  List<String> darTallesBase(String base) 
	{
		String consulta = "select 0,SUBSTRING(idArticulo,14,4) from Articulo where SUBSTRING(idArticulo,0,14)='"+base+"'";
		List<DataIDDescripcion> lista =MSSQL.darListaIdDescripcion(consulta);
		List<String> retorno = new ArrayList<>();
		for (DataIDDescripcion t : lista) 
		{
			retorno.add(t.getDescripcion());
		}
		
		return retorno;
	}
	
	public List<RecepcionExpedicion> darListaEnviosRec(int idDepo, int idEmpresa) 
	{
		List<RecepcionExpedicion> retorno = null;
		String q = "SELECT E.idEnvio, E.Fecha,CONCAT(CH.Nombre,' ',ch.Apellido, '/',AC.Nombre, ' ',AC.Apellido) AS chofac, D.Origen,D.Destino, D.DocOrigen,D.Comentario,D.IdRazon, D.CantUnidades, DOR.Nombre, DDE.Nombre,IFNULL (MM.Descripcion, 'Sin Definir')   FROM "+
				"	envio E INNER JOIN documentos D ON E.idEnvio = D.IdEnvio AND E.idEmpresa=D.idEmpresa "+
				"	INNER JOIN enviodepositos ED ON E.idEnvio = ED.idEnvio AND ED.idDeposito = D.Destino AND ED.idEmpresa=D.idEmpresa "+
				"	INNER JOIN usuarios ch on ch.idUsuario = E.IdChofer AND CH.idEmpresa=E.idEmpresa "+
				"	INNER JOIN usuarios ac ON ac.idUsuario = E.IdAcompaniante AND AC.idEmpresa=CH.idEmpresa "+
				"   INNER JOIN depositos DOR ON DOR.idDeposito = D.Origen AND DOR.idEmpresa=D.idEmpresa "+
				"	INNER JOIN depositos DDE ON DDE.idDeposito = D.Destino AND DDE.idEmpresa=D.idEmpresa "+
				"	LEFT OUTER JOIN motivos_movimientos MM ON D.IdRazon = MM.IdMotivo AND MM.idEmpresa=D.idEmpresa "+
				"	WHERE ED.idDeposito = "+idDepo+" AND E.idEmpresa="+idEmpresa+" AND D.Recibido=0 ORDER BY E.idEnvio;";
				 
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			retorno = econ.darListaEnviosRec(q);
			

		} catch (Exception e) {
			
			e.printStackTrace();
			return retorno;
		}
		return retorno;
	}
	
	public List<RecepcionExpedicion> darArtRecepcionables(List<RecepcionExpedicion> recepcionablesSEL, String tempInsert, int idEmpresa) 
	{
		
		String q = "DECLARE @temporal table (idDepositoO int, numeroDocumento int); "
				+ "insert into @temporal values "
					+tempInsert+ "; "+
				    " select D.NumeroDoc, MS.IdArticulo, MS.Cantidad*-1 from Documento D inner join MovStock MS on D.IdDocumento = MS.IdDocumento "
				    + "inner join @temporal temp on temp.idDepositoO = D.IdDeposito and D.NumeroDoc = temp.numeroDocumento where D.IdTipoDocumento='TRS'";
		return MSSQL.darArtRecepcionables(q,recepcionablesSEL);
	}
	
	public void actualizarDocRecepcionado(int idEnvio, int numeroDoc, int idEmpresa) 
	{
		Connection cone;
		try 
		{
			cone = eper.getConnection();
			String insert = "UPDATE documentos SET Recibido = 1 WHERE DocOrigen ="+numeroDoc+" AND IdEnvio="+idEnvio+" AND idEmpresa="+idEmpresa+";";
			
			eper.persistir(insert);			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	public void registrarRecepcionDoc(int idUsuario, int numeroDoc, String firma, int idEmpresa) 
	{
		Connection cone;
		try 
		{
			cone = eper.getConnection();
			String insert = "INSERT INTO documento_recibido (numeroDoc,usuarioRecibe,Firma,idEmpresa) VALUES ("+numeroDoc+","+idUsuario+",'"+firma+"',"+idEmpresa+")";
			
			eper.persistir(insert);			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	public void confirmarRecepcionExpedicion(int idEnvio, int idDeposito, int idEmpresa) 
	{
		Connection cone;
		try 
		{
			cone = eper.getConnection();
			String insert = "UPDATE enviodepositos SET Recibido = 1 WHERE idEnvio = "+idEnvio+" AND idDeposito = "+idDeposito+" AND idEmpresa="+idEmpresa+";";
			
			eper.persistir(insert);		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	////////////////////////////////////////////////////
	
	public Hashtable<String, List<DataDescDescripcion>> DarRangosConteoStore(int idDepo,int idEmpresa) 
	{
		String query = "select DISTINCT 'marca' as 'tipo', cast (M.idMarca as nvarchar)'id', M.Descripcion from ArtMarca M "+
						"	inner join Articulo AR ON AR.IdMarca = M.IdMarca and AR.IdArticulo in  "+
						"	(select distinct idArticulo from MovStockTotal MS where MS.iddeposito = "+idDepo+" and Stock>0) "+
						"	union all "+
						"	select  DISTINCT 'seccion' as 'tipo', cast (S.IdSeccion as nvarchar)'id', S.Descripcion from ArtSeccion S "+
						"	inner join Articulo AR ON S.IdSeccion = AR.IdSeccion and AR.IdArticulo in (select distinct idArticulo from MovStockTotal MS where MS.iddeposito = "+idDepo+" and Stock>0) "+
						"	union all "+
						"	select  DISTINCT 'categoria' as 'tipo', cast(C.IdCategoria as nvarchar)'id',C.Descripcion from ArtCategoria C "+
						"	inner join Articulo AR ON AR.IdCategoria = C.IdCategoria and AR.IdArticulo in "+ 
						"	(select distinct idArticulo from MovStockTotal MS where MS.iddeposito = "+idDepo+" and Stock>0) "+
						"	union all "+
						"	select  DISTINCT 'clase' as 'tipo', cast(C.IdClase as nvarchar)'id', C.Descripcion  from ArtClase C "+
						"	inner join Articulo AR ON AR.IdClase = C.IdClase and AR.IdArticulo in "+ 
						"	(select distinct idArticulo from MovStockTotal MS where MS.iddeposito = "+idDepo+" and Stock>0) "+
						"	union all "+
						"	select DISTINCT  'genero' as 'tipo', cast(G.IdGenero as nvarchar)'id', G.Descripcion from ArtGenero G "+
						"	inner join Articulo AR ON AR.IdGenero = G.IdGenero and AR.IdArticulo in "+ 
						"	(select distinct idArticulo from MovStockTotal MS where MS.iddeposito = "+idDepo+" and Stock>0) ";
						/*
						"	union all "+
						"	select DISTINCT  'articulos' as 'tipo', idArticulo 'id', Descripcion from articulo "+
						"	where IdArticulo in (select distinct idArticulo from MovStockTotal MS where MS.iddeposito = "+idDepo+" and Stock>0) ";*/
		return MSSQL.DarRangosConteoStore(query,idDepo,idEmpresa);
	}
	
	public List<ArticuloConteo> darListaArticulosConteo(String marca, String clase, String seccion, String genero,String categoria, String articulo, int idDepo, int idEmpresa) 
	{		
		String marcaIN = "";
		String claseIN = "";
		String seccionIN = "";
		String categoriaIN = "";
		String generoIN = "";
		String artIN = "";
		
		
		if(!marca.equals(""))
		{
			 marcaIN = " and AR.IdMarca in ("+marca+") ";
		}
		
		if(!clase.equals(""))
		{
			claseIN = "  AND AR.IdClase in ("+clase+") ";
		}
		if(!seccion.equals(""))
		{
			seccionIN = "  AND AR.IdSeccion in ("+seccion+") ";
		}
		if(!categoria.equals(""))
		{
			categoriaIN = " and AR.IdCategoria in ("+categoria+") ";
		}
		if(!genero.equals(""))
		{
			generoIN = " AND AR.IdGenero in ("+genero+") ";
		}
		if(articulo!=null && !articulo.equals(""))
		{
			artIN = " AND AR.IdArticulo LIKE '"+articulo+"%' ";
		}
		
		String query = " select stock, AR.idArticulo , AR.DescCorta "+
						" from MovStockTotal MS "+
						" inner join Articulo AR on MS.IdArticulo = AR.IdArticulo "+
						" where MS.iddeposito = "+idDepo+" and Stock>0 "+
						marcaIN+
						claseIN+
						seccionIN+
						generoIN+
						categoriaIN+
						artIN+
						" order by IdArticulo";
		
		
		return MSSQL.darArticuloConteo(query, idDepo, idEmpresa);
	}
	
	public int altaConteo(ConteoTiendas conteo, int idEmpresa) 
	{
		int id = 0;
		Connection cone;
		try 
		{
			cone = eper.getConnection();
			String insert = "INSERT INTO `conteoinventario` (`Descripcion`, `UsuarioPide`, `Deposito`, `skus`, `unidades`, `idEmpresa`) VALUES "
			+ "('"+conteo.getDescripcion()+"', '"+conteo.getUsuarioPide().getId()+"', '"+conteo.getDeposito()+"',"+conteo.getTotalArticulos()+","+conteo.getTotalUnidades()+","+idEmpresa+");";
			
			id = eper.persistirDarUltimo(insert, "conteoinventario", "idConteo",idEmpresa);
			String ins = "INSERT INTO `conteoinventarioarticulos` (`idConteo`, `idArticulo`, `idEmpresa`) VALUES ";
			String t=" ON DUPLICATE KEY UPDATE `idArticulo` = VALUES(`idArticulo`);";
			String er = "";
			boolean pri = true;
			
			if(id!=0)
			{
				
				for (ArticuloConteo ac : conteo.getArticulos()) 
				{
					if(pri)
					{
						pri=false;
						er+=" ("+id+", '"+ac.getArticulo()+"',"+idEmpresa+") ";
					}
					else
					{
						er+=", ("+id+", '"+ac.getArticulo()+"',"+idEmpresa+") ";
					}
				}
				eper.persistir(ins+er+t);
				
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return id;
	}
	
	public List<ConteoTiendas> darConteoTiendas(int idDeposito, String fechaI, String fechaF, boolean terminados, int idConteo, int idEmpresa) 
	{

		List<ConteoTiendas> retorno = new ArrayList<>();
		String fechaIIN = "";
		String fechaFIN = "";
		String depositoIn = "";
		String openIN = "";
		String idConteoIN = "";
		
		
		if(!fechaI.equals(""))
		{
			 fechaIIN = "	AND CI.FechaPedido>='"+fechaI+"' ";
		}
		
		if(!fechaF.equals(""))
		{
			fechaFIN = "	AND CI.FechaPedido<='"+fechaF+"' ";
		}
		if(idDeposito!=0)
		{
			depositoIn = "	AND CI.Deposito = "+idDeposito+" ";
		}
		if(terminados)
		{
			openIN = "	AND CI.Abierto = 0 ";
		}
		else
		{
			openIN = "	AND CI.Abierto = 1 ";
		}
		
		if(idConteo!=0)
		{
			idConteoIN = "	AND CI.IdConteo = 2 ";
		}
		
		String qu = "SELECT CI.IdConteo,Descripcion,UsuarioPide, CONCAT(UC.Nombre,' ',UC.Apellido), CI.Deposito, DE.Nombre, CI.FechaPedido, CI.Abierto, CI.skus, CI.unidades, "+
					"	CIA.idArticulo, CIA.contadas, IFNULL(CIA.fecha, CI.FechaPedido), CIA.usuario,CIA.Nombreusuario, CIA.idOjo, CIA.inn  "+ 
					"	FROM conteoinventario CI "+ 
					"	INNER JOIN usuarios UC ON UC.idUsuario = CI.UsuarioPide AND UC.idEmpresa=CI.idEmpresa "+
					"	INNER JOIN depositos DE ON DE.idDeposito = CI.Deposito AND DE.idEmpresa=CI.idEmpresa "+
					"	INNER JOIN "+ 
					"	( "+
					"		SELECT CA.idConteo, CA.idArticulo, IFNULL(SUM(CL.CantidadContada),0) 'contadas',  MAX(CL.estampa) 'fecha', IFNULL(CL.usuarioCuenta,0) usuario , CONCAT(IFNULL(UCA.Nombre,'SIN') ,' ' ,IFNULL(UCA.Apellido,'DEFINIR')) Nombreusuario,IFNULL(CL.idOjo,0) idOjo, 1 AS inn, CA.idEmpresa "+
					"		FROM conteoinventarioarticulos CA "+
					"		LEFT outer JOIN conteoinventariolineas CL ON CL.idConteo = CA.idConteo AND CL.idArticulo = CA.idArticulo AND CA.idEmpresa=CL.idEmpresa "+
					"		left outer JOIN usuarios UCA ON UCA.idUsuario = CL.usuarioCuenta AND UCA.idEmpresa=CL.idEmpresa "+ 
					"		GROUP BY CA.idConteo, CA.idArticulo,CL.usuarioCuenta, CL.idOjo,UCA.Nombre, UCA.Apellido "+
					"		UNION ALL "+
					"		SELECT CL.idConteo, CL.idArticulo, SUM(CL.CantidadContada), MAX(CL.estampa), CL.usuarioCuenta, CONCAT(UCA.Nombre,' ' ,UCA.Apellido)  Nombreusuario,CL.idOjo, 0 AS inn, CL.idEmpresa "+
					"		 "+	
					"		FROM conteoinventariolineas CL INNER JOIN usuarios UCA ON UCA.idUsuario = CL.usuarioCuenta AND UCA.idEmpresa=CL.idEmpresa "+ 
					"		WHERE CL.enLista=0  AND CL.idEmpresa="+idEmpresa+" "+
					"		GROUP BY CL.idConteo, CL.idArticulo, CL.usuarioCuenta,CL.idOjo,UCA.Nombre, UCA.Apellido "+
					"	) CIA ON CIA.idConteo = CI.IdConteo AND CIA.idEmpresa=CI.idEmpresa "+
					"	WHERE CI.idEmpresa="+idEmpresa+" "+
						idConteoIN+ 
						fechaIIN+
						fechaFIN+
						openIN+
						depositoIn+
					"	ORDER BY CI.fechaPedido, CIA.fecha" ;
		
		
		try 
		{
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			
			retorno = econ.darConteoTienda(qu,idEmpresa);
			

		} catch (Exception e) {
			
			e.printStackTrace();
			return retorno;
		}
		
		return retorno;
	}
	
	public void AltaLineaConteo(int idConteo, String articulo, int cantidad, int idUsuario, int enLista, String idOjo, int idEmpresa) 
	{
		Connection cone;
		try 
		{
			cone = eper.getConnection();
			String insert = "INSERT INTO `conteoinventariolineas` (`idConteo`, `idArticulo`, `CantidadContada`, `usuarioCuenta`, `enLista`, `idOjo`, `idEmpresa`) "+ 
							" VALUES ('"+idConteo+"', '"+articulo+"', '"+cantidad+"', '"+idUsuario+"', '"+enLista+"', '"+idOjo+"',"+idEmpresa+");";
			
			eper.persistir(insert);			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void closeConteo(int idConteo, int idEmpresa) 
	{
		Connection cone;
		try 
		{
			cone = eper.getConnection();
			String insert = "update conteoinventario set abierto = 0 where idConteo ="+idConteo + " and idEmpresa ="+idEmpresa;
			
			eper.persistir(insert);			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}


public boolean VerifCantidadesPicking(List<bultoContenido> articulos, String destino, int idEmpresa) {
	boolean retorno = true;
	try
	{
		String artis = "";
		String picks = "";
		for(bultoContenido bc:articulos){
			artis += "'"+bc.getIdArticulo()+"',";
			picks += bc.getPicking()+",";
		}
		artis = artis.substring(0,artis.length()-1);
		picks = picks.substring(0,picks.length()-1);
		String query = "SELECT sum(ra.cantidad), ra.idarticulo,'', ra.idpicking FROM bulto b "+
				"INNER JOIN bulto_contenido bc ON b.idbulto=bc.idbulto and b.idempresa=bc.idempresa "+
				"INNER JOIN reposicion_articulos ra ON bc.picking=ra.idPicking and bc.idarticulo=ra.idarticulo and b.destino=ra.destino and bc.idempresa=ra.idempresa "+
				"WHERE bc.picking IN ("+picks+") AND bc.idarticulo IN ("+artis+") and ra.destino ="+destino+" and ra.idempresa="+idEmpresa+
				" GROUP BY idarticulo,picking";
		econ.getConnection();
		List<DataIDDescripcion> ordenPick = econ.darListaDataIdDescripcion(query);
		ordenPick.remove(0);
		
		for(DataIDDescripcion p: ordenPick) {
			for(bultoContenido bc: articulos) {
				if(p.getDescripcion().equals(bc.getIdArticulo()) && p.getIdB()==bc.getPicking()) {
					if (p.getId()<bc.getCantidad()) {
						retorno = false;
					}
					break;
				}
			}
			if(!retorno) {
				break;
			}
		}
			
			
	}catch (Exception e) {
		return false;
	}
	return retorno;
}

public String darParametroDeposito(int idEmpresa, int idDeposito, int idParametro) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return econ.darParametroEmpresa("SELECT Valor FROM depositos_parametros WHERE idParametro = "+idParametro+" AND idEmpresa="+idEmpresa+" AND idDeposito ="+idDeposito);			
	}
	catch (Exception e)
	{
		return null;
	}
}
public String darParametroEmpresa(int idEmpresa, int idParametro) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return econ.darParametroEmpresa("SELECT valor FROM empresa_parametro WHERE idParametro = "+idParametro+" AND idEmpresa = "+idEmpresa+"");			
	}
	catch (Exception e)
	{
		return null;
	}
}

public int darDepositoEcommerceCanal(int idEmpresa, int idCanal) 
{
	if(idCanal==0)
	{
		return Integer.parseInt(darParametroEmpresa(idEmpresa, 5));
	}
	
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return Integer.parseInt(econ.darParametroEmpresa("SELECT idDepositoEcommerce from ecommerce_canal_ml   WHERE idEmpresa="+idEmpresa+" AND id="+idCanal+""));			
	}
	catch (Exception e)
	{
		return 0;
	}
}
public int darDepositoCentralCanal(int idEmpresa, int idCanal) 
{
	if(idCanal==0)
	{
		return Integer.parseInt(darParametroEmpresa(idEmpresa, 4));
	}
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return Integer.parseInt(econ.darParametroEmpresa("SELECT idDepositoCentral from ecommerce_canal_ml   WHERE idEmpresa="+idEmpresa+" AND id="+idCanal+""));			
	}
	catch (Exception e)
	{
		return 0;
	}
}

public List<DataIDDescripcion> darParametrosEmpresa(int idEmpresa, String ids) 
{
	@SuppressWarnings("unused") Connection cone;
	String query = "SELECT idParametro,valor FROM empresa_parametro WHERE idEmpresa = "+idEmpresa;
	if (!ids.equalsIgnoreCase("")) {
		query += " and idParametro in ("+ids+")";
	}
	try 
	{
		cone = econ.getConnection();
		return econ.darParametrosEmpresa(query);			
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}

public List<DataIDDescripcion> darIntegracionesEmpresa(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = econ.getConnection();
		return econ.darParametrosEmpresa("SELECT idIntegracion,productivo FROM integraciones WHERE idEmpresa = "+idEmpresa+"");			
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}

public void updateParametroEmpresa(int idEmpresa, int idParametro, boolean value)
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = eper.getConnection();
		int valor = 0;
		if(value) {
			valor = 1;
		}
		String procesados = "update empresa_parametro set valor='"+valor+"' where idParametro="+idParametro+" and idEmpresa="+idEmpresa;
		eper.persistir(procesados);
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}

public void updateParametroEmpresa(int idEmpresa, int idParametro, int value)
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = eper.getConnection();
		String procesados = "update empresa_parametro set valor="+value+" where idParametro="+idParametro+" and idEmpresa="+idEmpresa;
		eper.persistir(procesados);
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
}

	///////////////////////////////////////////OPERACIONES PICKUP

	public List<DataPedidoPickup> darPedidosProcXID(int idDeposito, int idEmpresa) 
	{
		Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		// TODO generar la lista por ID de deposito
		String consulta ="select distinct EP.idPedido, DATE_FORMAT(EP.stampTime,'%d/%m/%Y') AS Fecha, EP.descripcion, ARE.req, ARE.pick from  \r\n" + 
				"ecommerce_pedido  EP \r\n" + 
				"inner join ecommerce_pedido_articulos_req ER  \r\n" + 
				"on EP.idPedido = ER.idPedido  AND EP.IdEmpresa = ER.IdEmpresa\r\n" + 
				"inner join ecommerce_pedido_destino ED on ED.idPedido = EP.idPedido  AND ED.IdEmpresa = EP.IdEmpresa\r\n" + 
				"INNER JOIN (SELECT RA.Seccion idPedido, SUM(RA.Cantidad) req, SUM(RA.Picked) pick, RA.IdEmpresa FROM reposicion_articulos RA WHERE RA.Seccion>100 GROUP BY RA.Seccion,  RA.IdEmpresa) ARE  \r\n" + 
				"ON ARE.idPedido = EP.idPedido  AND ARE.IdEmpresa = EP.IdEmpresa\r\n" + 
				"where \r\n" + 
				"ER.IdEmpresa = "+idEmpresa+"\r\n" + 
				"AND EP.EstadoEncuentra in (2)  \r\n" + 
				"group by  EP.idPedido, EP.stampTime, EP.descripcion \r\n" + 
				"ORDER BY Fecha;";
		try
		{
			List<DataPedidoPickup> retorno =  econ.darDatosPedidosPickupDetalle(consulta);
			return retorno;
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
		
		
	}
	
	
	
	public  List<DataPedidoPickup> darPedidosPickup(int idDeposito, int idEmpresa) 
	{
		Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		String consulta ="select distinct EP.idPedido, DATE_FORMAT(EP.stampTime,'%d/%m/%Y') AS Fecha, EP.descripcion, ARE.req, ARE.pick from "+ 
						"ecommerce_pedido  EP inner join ecommerce_pedido_articulos_req ER "+ 
						"on EP.idPedido = ER.idPedido "+ 
						"inner join ecommerce_pedido_destino ED on ED.idPedido = EP.idPedido "+ 
						"INNER JOIN (SELECT ER.idPedido, SUM(ER.CantidadRequerida) req, SUM(ER.CantidadPickeada) pick  FROM ecommerce_pedido_articulos_req ER GROUP BY ER.idPedido) ARE "+ 
						"ON ARE.idPedido = EP.idPedido "+ 
						"where ED.idDestino = "+idDeposito + 
						" AND ER.procesarEnPickup = 1 "+ 
						"AND EP.EstadoEncuentra in (1,2) "+ 
						"group by  EP.idPedido, EP.stampTime, EP.descripcion "+
						"ORDER BY Fecha;";
		try
		{
			List<DataPedidoPickup> retorno =  econ.darDatosPedidosPickupDetalle(consulta);
			return retorno;
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
	}
	
	
	
	
	
	public  List<DataIDDescripcion> darStocksPedidosProcessEcommerce(String deposINN, int idEmpresa) 
	{
	
	
	String consulta = "";
	Connection cone;
	try 
	{
		cone = econ.getConnection();
		if(deposINN!=null)
		{
			consulta="select SUM(CantidadRequerida),idArticulo,Deposito from ecommerce_pedido_articulos_req where idEmpresa="+idEmpresa+" AND Confirmado = 0 and Deposito in ("+deposINN+") group by idArticulo,Deposito order by Deposito";
		}
		else
		{
			consulta="select SUM(CantidadRequerida),idArticulo,Deposito from ecommerce_pedido_articulos_req where idEmpresa="+idEmpresa+" AND Confirmado = 0 group by idArticulo,Deposito order by Deposito";
		}
		List<DataIDDescripcion> retorno =  econ.darListaDeposEcommerce(consulta);
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
	}
	
	
	public  List<DataIDDescripcion> darDepositosProcessEcommercePickup(int depo, int idEmpresa) 
	{
	Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	String consulta = "select Prioridad, IdAlmacenERP,Contactos "
			+ " FROM ecommerce_almacenes  EA "
			+ " INNER JOIN ecommerce_envioml EE ON EA.idEmpresa=EE.idEmpresa AND "
			+ " EA.IdAlmacenERP = EE.idDeposito where EA.idEmpresa="+idEmpresa+" AND EE.PreparaPedidoPickup = 1 and IdAlmacenERP="+depo+" ";
		
	try
	{
		//List<DataIDDescripcion> depCancelados= econ.darListaDataIdDescripcion("select deposito,0 from ecommerce_pedido_articulos_req_cancelados where idPedido");
		
		List<DataIDDescripcion> retorno =  econ.darListaDeposEcommerce(consulta);
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
	}
	
	
	public  List<DataArtPedidoPickup> darArticulosOrderProcessEcommercePickup(int idDepo, int idEmpresa) 
	{
	
	
	Connection cone;
	try {
		cone = econ.getConnection();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	//
	String consulta ="SELECT * from "
			+ " ( "
			+ " SELECT  cantidadPedido AS cant, AP.idPedido,idArticulo  "
			+ " FROM ecommerce_pedido_articulos AP  "
			+ " INNER JOIN art_descripcion AD ON AP.idEmpresa=AD.idEmpresa AND AD.id = AP.idArticulo  "
			+ " WHERE AP.idEmpresa="+idEmpresa+" AND "
			+ " NOT EXISTS 	 "
			+ " ("
			+ "		SELECT idPedido,idArticulo, CantidadRequerida-CantidadProcesasa,idPedido  "
			+ "	   FROM ecommerce_pedido_articulos_req APR  "
			+ "    WHERE AP.idEmpresa=APR.idEmpresa AND AP.idPedido=APR.idPedido AND AP.idArticulo=APR.idArticulo "
			+ ")  AND idPedido NOT IN  "
			+ " ("
			+ "		SELECT DISTINCT IdPedido FROM ecommerce_pedidos_log WHERE idEmpresa="+idEmpresa+" AND  Estado=4)   "
			+ ") t0  "
			+ "	INNER JOIN   "
			+ "	( 	"
			+ "	SELECT idPedido, idDestino "
			+ "	from ecommerce_pedido_destino "
			+ "	WHERE idempresa="+idEmpresa+" and idDestino IN "
			+ "	("
			+ "		SELECT DISTINCT idDeposito FROM ecommerce_envioml WHERE idEmpresa="+idEmpresa+" AND PreparaPedidoPickup = 1"
			+ "	)  "
			+ ")  t1  ON t0.idPedido = t1.idPedido  WHERE T1.idDestino="+idDepo;
	try
	{
		List<DataArtPedidoPickup> retorno =  econ.darArticulosOrderProcessEcommercePickup(consulta);
		return retorno;
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
	}
	
	
	public List<DataPedidoPickup> darDatosPedidosPickup(List<String> pedidosCompletos,int idEmpresa) 
	{
		String in = "";
		
		for (String p : pedidosCompletos) 
		{
			in +=p+",";
		}
		
		in = in.substring(0,in.length()-1);
		
		
		String q = "SELECT idPedido, descripcion,  DATE_FORMAT(EP.stampTime,'%d/%m/%Y') FROM ecommerce_pedido EP WHERE idEmpresa="+idEmpresa+" AND EP.idPedido IN ("+in+")";
		
		
		
		Connection cone;
		try {
			
			cone = econ.getConnection();
			
			
			return econ.darDatosPedidosPickup(q);
		
		} 
		catch (Exception e) 
		{
		
			e.printStackTrace();
			return new ArrayList<>();
		}
	
	}
	
	public void ProcesarPickup(Long idPedido, int canal, int idEmpresa, Usuario u) {
		Connection cone;
		try {
			cone = eper.getConnection();

			String procesados = "update ecommerce_pedido_articulos_req set cantidadprocesasa=cantidadrequerida,confirmado=1,cTimeStamp=CURRENT_TIMESTAMP(), "
					+ "pTimeStamp=CURRENT_TIMESTAMP() where idpedido="+idPedido+" and idEmpresa="+idEmpresa;
			eper.persistir(procesados);
			
			updateEcommerceEstado(idPedido, 3, idEmpresa, u);
			
			

		} catch (Exception e) {

			e.printStackTrace();
			
		}

	}


	public  String darNombreDepoPick(int idTienda, int idEmpresa)
	{
		DataIDDescripcion d = darDataIdDescripcion("select idDeposito,nombre from ecommerce_envioml where idDeposito = "+idTienda+" and idEmpresa="+idEmpresa);
		if(d!=null)
		{
			return d.getDescripcion();
		}
		else
		{
			return "";
		}
		
	}
	
	public  boolean encuentraExisteUbicaBool(String cod, int idEmpresa) {

		try {

			Connection cone;
			cone = econ.getConnection();

			String consulta = "SELECT `Orden`, `Codigo` FROM `codigoubicacion` where `Codigo` = '"
					+ cod + "' and idEmpresa="+idEmpresa;

			return econ.hayReg(consulta);
		} catch (Exception e) {
			return false;
		}
	}
	
	public  List<DataPedidoPickup> darPedidosPickupEntregar(int idDeposito, String busqueda, int idEmpresa) 
	{
		Connection cone;
		try {
			cone = econ.getConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		String consulta ="select distinct EP.idPedido, DATE_FORMAT(EP.stampTime,'%d/%m/%Y') , EP.descripcion, ARE.req, ARE.pick, if(isnull(CONCAT ('M. ',O.idEstante,' E.', O.idModulo,' - ',O.idOjo)),'SIN ASIGNAR',CONCAT ('M. ',O.idEstante,' E.', O.idModulo,' - ',O.idOjo)) from "+ 
					"	ecommerce_pedido  EP inner join ecommerce_pedido_articulos_req ER "+
					"	on EP.idPedido = ER.idPedido and ep.idempresa=er.idempresa "+ 
					"	inner join ecommerce_pedido_destino ED on ED.idPedido = EP.idPedido and ep.idempresa=ed.idempresa"+ 
					"	INNER JOIN (SELECT ER.idPedido, SUM(ER.CantidadRequerida) req, SUM(ER.CantidadPickeada) pick, er.idEmpresa empr  FROM ecommerce_pedido_articulos_req ER where er.idempresa="+idEmpresa+" GROUP BY ER.idPedido) ARE "+ 
					"	ON ARE.idPedido = EP.idPedido and ep.idempresa=are.empr"+ 
					"	LEFT OUTER JOIN ojostienenarticulos OT ON OT.idArticulo = EP.idPedido+'' and ep.idempresa=ot.idempresa "+
					"	LEFT OUTER JOIN ojos O ON O.idOjo = OT.idOjo and o.idempresa=ot.idempresa "+
					"	LEFT OUTER JOIN estanterias E ON E.idEstanteria = O.idEstanteria AND E.idDeposito=ED.idDestino and e.idempresa=o.idempresa "+
					"	where ED.idDestino = "+idDeposito+" and Ep.idEmpresa="+idEmpresa +
					"	AND EP.EstadoEncuentra = 5 "+
					"	AND (EP.idPedido = '"+busqueda+"' OR EP.descripcion like '%"+busqueda+"%') "+
					"	group by  EP.idPedido, EP.stampTime, EP.descripcion;";
		try
		{
			List<DataPedidoPickup> retorno =  econ.darDatosPedidosPickupDetalle(consulta);
			return retorno;
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
	}
	
	public void RegistrarEntrega(Long idPedido, String doc, String firma, int idEmpresa, String vendedor) {
		Connection cone;
		try {
			cone = eper.getConnection();

			String registro = "insert into ecommerce_registro_entrega values("+idPedido+",'"+doc+"','"+firma+","+vendedor+"',"+idEmpresa+")";
			eper.persistir(registro);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////////////////////////// FIN OPERACIONES PICKUP
	
	public List<DataIDDescripcion> DarArtsDescCorta (List<bultoContenido> lista, int idEmpresa){
		Connection cone;
		List<DataIDDescripcion> retorno = new ArrayList<>();
		try 
		{
			String arts = "";
			String caseW = "";
			
			for(bultoContenido bc :lista) {
				arts += "'"+bc.getIdArticulo()+"',";
				caseW += " WHEN idarticulo = '"+bc.getIdArticulo()+"' THEN "+bc.getCantidad()+" ";
			}
			arts = arts.substring(0,arts.length()-1);
			
			cone = econ.getConnection();
			String query = "SELECT SUM(t.cantis), t.descr from "+
						"(SELECT idarticulo,descripcion descr, "+
						"	CASE "+
								caseW+
						"	    ELSE 0 "+
						"	END cantis "+
						"	FROM articulos "+
						"	WHERE idarticulo IN ("+arts+") AND idempresa="+idEmpresa+") "+
						"	AS t "+
						"	GROUP BY (t.descr);";
			
			retorno = econ.darListaDataIdDescripcion(query);	
			retorno.remove(0);
			
			return retorno;		
		}
		catch (Exception e)
		{
			return retorno;
		}
	}
	
	public List<trackingPedido> PedidosATrackear(int idEmpresa){
		
		Connection cone;
		try {
			cone = econ.getConnection();
			String query = "select distinct p.idpedido,p.idfenicio,p.idcanalml,ce.courier,d.tracking,ce.usuario,ce.pass, d.destinofinal from ecommerce_pedido p "+
							"inner join ecommerce_pedido_destino d on p.idpedido=d.idpedido and p.idempresa= d.idempresa "+
							"inner join ecommerce_envioml eml on eml.iddeposito=d.iddestino and eml.idempresa= d.idempresa "+
							"inner join ecommerce_canales_envio CE on eml.nombre=ce.courier and eml.idempresa= ce.idempresa and ce.idcanal=p.idcanalml "+
							"where p.estadoencuentra in (3,4) and p.stamptime > '2021-07-15' AND p.idempresa="+idEmpresa;
			
			return econ.darPedidosATrackear(query);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public boolean updateArtEcommerce(Long idPedido, String newArt, String oldArt, int dep, int idEmpresa, int idUsuario, int depCentral, int depWeb, int solicitud) {
		boolean retorno = true;
		Connection cone;
		try {
			cone = eper.getConnection();
			eper.updateEcommerceArticulo(idPedido, newArt, oldArt, dep, idEmpresa, depCentral, depWeb, solicitud);
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}
	
	public boolean updateClienteEcommerce(Long idPedido, int idEmpresa, String nombre, String apellido, String direccion, String nroPuerta, String apto, String ciudad, String departamento, String cpostal, String telefono, String cedula, String direccionnota) {
		boolean retorno = true;
		Connection cone;
		try {
			cone = eper.getConnection();

			String saveCambios = "INSERT INTO ecommerce_import_clientes (idPedido,idEmpresa,nombre,apellido,direccion,ciudad,departamento,cpostal,telefono,cedula,direccionnota,nroPuerta,apto) VALUES "+
					" ("+idPedido+", "+idEmpresa+", '"+nombre+"', '"+apellido+"', '"+direccion+"'" + 
					" , '"+ciudad+"', '"+departamento+"', '"+cpostal+"', '"+telefono+"', '"+cedula+"'" + 
					" , '"+direccionnota+"', '"+nroPuerta+"', '"+apto+"') \r\n" + 
					" ON DUPLICATE KEY UPDATE " +
					" nombre='"+nombre+"', apellido='"+apellido+"', direccion='"+direccion+"'" + 
					" , ciudad ='"+ciudad+"', departamento ='"+departamento+"', cpostal='"+cpostal+"', telefono ='"+telefono+"', cedula ='"+cedula+"'" + 
					" , direccionnota ='"+direccionnota+"', nroPuerta ='"+nroPuerta+"', apto ='"+apto+"'";
			eper.persistir(saveCambios);
			
		} catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		}
		return retorno;
	}


	public List<MovsXArticulo> darListaMovsXArticulo(String articulo, String fechaI, String fechaF, int idEmpresa) {
		Connection cone;
		try {
			cone = econ.getConnection();
			String query = "SELECT MS.`update`, CONCAT(US.nombre,' ',US.Apellido),\r\n" + 
					"CONCAT\r\n" + 
					"(\r\n" + 
					"CASE \r\n" + 
					"when ISNULL(ESOR.Descripcion) AND MS.codOrigen !=''\r\n" + 
					"then \r\n" + 
					"BUOR.descripcion\r\n" + 
					"when ISNULL(ESOR.Descripcion) AND MS.codOrigen =''\r\n" + 
					"then \r\n" + 
					"'Alta en Ojo [Entrada]'\r\n" + 
					"else\r\n" + 
					"ESOR.Descripcion\r\n" + 
					"END\r\n" + 
					",\r\n" + 
					"' (',\r\n" + 
					"MS.codOrigen,\r\n" + 
					"')'\r\n" + 
					")\r\n" + 
					"'Origen'\r\n" + 
					",\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"CONCAT(\r\n" + 
					"CASE \r\n" + 
					"when ISNULL(ESDE.Descripcion) AND MS.codDestino !=''\r\n" + 
					"then \r\n" + 
					"BUDE.descripcion\r\n" + 
					"when ISNULL(ESDE.Descripcion) AND MS.codDestino =''\r\n" + 
					"then \r\n" + 
					"'Baja de Ojo [Salida]'\r\n" + 
					"else\r\n" + 
					"ESDE.Descripcion\r\n" + 
					"END\r\n" + 
					",\r\n" + 
					"' (',\r\n" + 
					"MS.codDestino,\r\n" + 
					"')'\r\n" + 
					")\r\n" + 
					"'Destino'\r\n" + 
					"\r\n" + 
					",MS.cantidad \r\n" + 
					"from movsarticulos MS\r\n" + 
					"INNER JOIN usuarios US ON US.idUsuario = MS.usuario AND US.idEmpresa = MS.IdEmpresa\r\n" + 
					"LEFT OUTER JOIN ojos OJOR ON OJOR.idOjo = MS.codOrigen AND OJOR.IdEmpresa =MS.IdEmpresa\r\n" + 
					"LEFT OUTER JOIN estanterias ESOR ON ESOR.idEstanteria = OJOR.idEstanteria AND OJOR.IdEmpresa =ESOR.IdEmpresa\r\n" + 
					"LEFT OUTER JOIN ojos OJDE ON OJDE.idOjo = MS.codDestino AND OJDE.IdEmpresa =MS.IdEmpresa\r\n" + 
					"LEFT OUTER JOIN estanterias ESDE ON ESDE.idEstanteria = OJDE.idEstanteria AND OJDE.IdEmpresa =ESDE.IdEmpresa\r\n" + 
					"\r\n" + 
					"LEFT OUTER JOIN bulto BUOR ON BUOR.idBulto = MS.codOrigen AND BUOR.IdEmpresa = MS.IdEmpresa \r\n" + 
					"LEFT OUTER JOIN bulto BUDE ON BUDE.idBulto = MS.codDestino AND BUDE.IdEmpresa = MS.IdEmpresa\r\n" + 
					"\r\n" + 
					"WHERE MS.idEmpresa = "+idEmpresa+" AND `update` BETWEEN '"+fechaI+"' AND '"+fechaF+"'\r\n" + 
					"AND MS.idArticulo = '"+articulo+"'\r\n" + 
					"ORDER BY MS.`update`";
			return econ.darListaMovsXArticulo(query);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	public List<InventarioXUbicacion> darListaInventXUbi(String articulo, String estanteria, String marca,	String genero, String categoria, 
			String clases, String agruparArt, int idEmpresa, String idDeposito) 
	{
		
		Connection cone;
		try {
			cone = econ.getConnection();
			
			String qArt = "";
			String qEst = "";
			String qMar = "";
			String qGen = "";
			String qCat = "";
			String qCla = "";
			String qAgr = "";
			
			
			String qFieldOjo = "";
			String qFieldCant = "";
			String qFieldEstanteria = "";
			
			if(!articulo.equals(""))
			{
				qArt = " AND OT.idArticulo like '"+articulo+"%' ";
			}
			
			if(!estanteria.equals(""))
			{
				qEst= " AND ES.idEstanteria IN ("+estanteria+") ";
			}
			if(!marca.equals(""))
			{
				qMar = " AND AR.IdMarca IN ("+marca+") ";
			}
			if(!genero.equals(""))
			{
				qGen = " AND AR.IdGenero IN ("+genero+") ";
			}
			if(!categoria.equals(""))
			{
				qCat = " AND AR.IdCategoria IN ("+categoria+") ";
			}
			if(!clases.equals(""))
			{
				qCla = " AND AR.IdClase IN ("+clases+") ";
				
			}
			if(agruparArt!=null && agruparArt.equals("on"))
			{
				qAgr = " GROUP BY DE.Nombre, AM.Descripcion,AG.Descripcion,AC.Descripcion, AR.idArticulo ";
				qFieldOjo = " GROUP_CONCAT(OT.idOjo) ubicaciones ";
				qFieldCant = " SUM(OT.Cantidad) ";
				qFieldEstanteria = " GROUP_CONCAT(ES.Descripcion) ";
				
			}
			else
			{
				qFieldOjo = " OT.idOjo ubicaciones ";
				qFieldCant = " OT.Cantidad ";
				qFieldEstanteria = " ES.Descripcion ";
			}
			
			String consulta = "SELECT DE.Nombre, AM.Descripcion marca,AG.Descripcion genero,AT.Descripcion categoria,AC.Descripcion clase, AR.idArticulo articulo, "+qFieldCant+" cantidad, "+qFieldOjo+", "+qFieldEstanteria+" FROM \r\n" + 
					" ojostienenarticulos OT\r\n" + 
					" INNER JOIN articulos AR ON AR.idArticulo = OT.idArticulo AND OT.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN ojos OJ ON OJ.idOjo = OT.idOjo AND OJ.IdEmpresa = OT.IdEmpresa\r\n" + 
					" INNER JOIN estanterias ES ON ES.idEstanteria = OJ.idEstanteria AND ES.idEmpresa = OJ.IdEmpresa\r\n" + 
					" INNER JOIN depositos DE ON DE.idDeposito = ES.idDeposito AND DE.IdEmpresa = ES.idEmpresa\r\n" + 
					" INNER JOIN art_marca AM ON AM.id = AR.IdMarca AND AM.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_genero AG ON AG.id = AR.IdGenero AND AG.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_categoria AT ON AT.id = AR.IdCategoria AND AT.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_clase AC ON AC.id = AR.IdClase AND AC.IdEmpresa = AR.IdEmpresa\r\n" + 
					" WHERE OT.IdEmpresa = "+idEmpresa+" AND ES.idDeposito = "+idDeposito+" ";
			
			return econ.darListaInventXUbi(consulta+qEst+qArt+qMar+qGen+qCat+qCla+qAgr);
		
		}
		catch (Exception e) 
		{
			
			return new ArrayList<>();
		}
	}	
	
	
	public List<inventDisponible> darListainventDisponible(String articulo, String idDeposito, String estanteria, String marca, String genero, String categoria, 
			String clases, int idEmpresa) 
	{
		
		Connection cone;
		try {
			cone = econ.getConnection();
			
			String qArt = "";
			String qDep = "";
			String qEst = "";
			String qMar = "";
			String qGen = "";
			String qCat = "";
			String qCla = "";
			
			if(!articulo.equals(""))
			{
				qArt = " AND OT.idArticulo like '"+articulo+"%' ";
			}
			if(!idDeposito.equals(""))
			{
				qDep= " AND ES.idDeposito IN ("+idDeposito+") ";
			}
			
			if(!estanteria.equals(""))
			{
				qEst= " AND ES.idEstanteria IN ("+estanteria+") ";
			}
			if(!marca.equals(""))
			{
				qMar = " AND AR.IdMarca IN ("+marca+") ";
			}
			if(!genero.equals(""))
			{
				qGen = " AND AR.IdGenero IN ("+genero+") ";
			}
			if(!categoria.equals(""))
			{
				qCat = " AND AR.IdCategoria IN ("+categoria+") ";
			}
			if(!clases.equals(""))
			{
				qCla = " AND AR.IdClase IN ("+clases+") ";
			}
			
			String consulta = "SELECT DE.Nombre, AM.Descripcion marca,AG.Descripcion genero,AT.Descripcion categoria,AC.Descripcion clase, AR.idArticulo articulo, SUM(OT.Cantidad) cantidad\r\n" + 
					" FROM \r\n" + 
					" ojostienenarticulos OT\r\n" + 
					" INNER JOIN articulos AR ON AR.idArticulo = OT.idArticulo AND OT.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN ojos OJ ON OJ.idOjo = OT.idOjo AND OJ.IdEmpresa = OT.IdEmpresa\r\n" + 
					" INNER JOIN estanterias ES ON ES.idEstanteria = OJ.idEstanteria AND ES.idEmpresa = OJ.IdEmpresa\r\n" + 
					" INNER JOIN depositos DE ON DE.idDeposito = ES.idDeposito AND DE.IdEmpresa = ES.idEmpresa\r\n" + 
					" INNER JOIN art_marca AM ON AM.id = AR.IdMarca AND AM.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_genero AG ON AG.id = AR.IdGenero AND AG.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_categoria AT ON AT.id = AR.IdCategoria AND AT.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_clase AC ON AC.id = AR.IdClase AND AC.IdEmpresa = AR.IdEmpresa\r\n" + 
					" WHERE OT.IdEmpresa = "+ idEmpresa; 
			
			String groupBy = " GROUP BY DE.Nombre, AM.Descripcion,AG.Descripcion,AC.Descripcion, AR.idArticulo;";
			
			return econ.darListainventDisponible(consulta+qEst+qDep+qArt+qMar+qGen+qCat+qCla+groupBy);
		
		}
		catch (Exception e) 
		{
			
			return new ArrayList<>();
		}
	}	
	
	public List<DataLineaRepo> darArticulosQueNoEstanEnReposicionArt(int idEmpresa){
		
		Connection cone;
		try {
			cone = econ.getConnection();
			/* No estan en Reposicion_articulos? */
			String consulta = "SELECT req.idPedido, req.idArticulo, req.Deposito, req.CantidadRequerida FROM ecommerce_pedido ep " + 
					"INNER JOIN ecommerce_pedido_articulos_req req ON ep.idPedido=req.idPedido AND ep.IdEmpresa=req.IdEmpresa " + 
					"WHERE ep.idEmpresa =" +idEmpresa+ " AND ep.estadoencuentra = 1 AND ep.stampTime > '2020-12-02 17:18:17' " + 
					"having CONCAT(req.idPedido,'_',req.idArticulo) NOT IN (SELECT CONCAT(seccion,'_',idArticulo) FROM reposicion_articulos WHERE idEmpresa =" +idEmpresa+");";
			return econ.darArticulosQueNoEstanEnReposicionArt(consulta);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<DataIDDescripcion> darArticulosQueNoEstanEnEcPedArt(int idEmpresa){
		
		Connection cone;
		try {
			cone = econ.getConnection();
			/* No estan en Reposicion_articulos? */
			String consulta = "SELECT epr.idPedido, epr.idArticulo, epr.CantidadRequerida\r\n" + 
					"FROM ecommerce_pedido_articulos_req epr \r\n" + 
					"LEFT OUTER JOIN ecommerce_pedido_articulos epa \r\n" + 
					"ON epr.idPedido=epa.idPedido AND epr.idArticulo=epa.idArticulo\r\n" + 
					"WHERE epr.IdEmpresa="+idEmpresa+" AND epa.idArticulo IS NULL;";
			return econ.darListaDataIdDescripcionLong(consulta);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public  void insertarEnReposArt(List<DataLineaRepo> lista, int idE) 
	{
		String consulta = "";
		
		for(DataLineaRepo dl: lista)
		{
			boolean error = false;
			consulta = "INSERT INTO reposicion_articulos (IdSincronizacion, idArticulo, Origen, Destino, Cantidad, Estado, Justificacion, Seccion, IdEmpresa, Mayorista, idtipo) "
					+ "VALUES ((SELECT MAX(id) FROM reposicion_sincronizacion WHERE idEmpresa = "+idE+"),'"+dl.getIdArticulo() +"', "+dl.getIdDepOrigen()+",1200,"+dl.getSolicitada()+",1,'Venta WEB "+dl.getPedido()+"[]',"+dl.getPedido()+","+idE+",1,2);";
			
			try 
			{
				error = eper.persistir(consulta);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			if(!error)
				System.out.println(dl.getPedido()+" Fallo");
		}
		
	}
	
	public  void insertarEnEcPedArt(List<DataIDDescripcion> lista, int idE) 
	{
		String consulta = "";
		
		for(DataIDDescripcion dl: lista)
		{
			boolean error = false;
			consulta = "INSERT INTO `ecommerce_pedido_articulos`"
					+ " (`idPedido`, `idArticulo`, `cantidadPedido`, `IdEmpresa`) VALUES ('"+dl.getIdLong()+"', '"+dl.getDescripcion()+"', '"+dl.getDescripcionB()+"', '"+idE+"');";
			
			try 
			{
				error = eper.persistir(consulta);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			if(!error)
				System.out.println(dl.getId()+" Fallo");
		}
		
	}
	
	/**/
	
	public List<ProductividadPicking> darListaReporteProductividadPicking(String idDeposito, String idUsuario, 
			String fechaPI, String fechaPF, String categoria, String clase, int idEmpresa){
		List<ProductividadPicking> result = new ArrayList<>();


		Connection cone;
		try {
			cone = econ.getConnection();

			String qDep = "";
			String qUsu = "";
			String qCat = "";
			String qCla = "";
			String qFechPick = "";
			String qFechVerif = "";

			if(!idDeposito.equals(""))
			{
				qDep= " AND ra.Destino IN ("+idDeposito+") ";
			}

			if(!idUsuario.equals(""))
			{
				qUsu= " AND ra.idUsuario IN ("+idUsuario+") ";
			}
			if(!categoria.equals(""))
			{
				qCat = " AND a.IdCategoria IN ("+categoria+") ";
			}
			if(!clase.equals(""))
			{
				qCla = " AND a.IdClase IN ("+clase+") ";
			}
			if(!fechaPI.equals("") && !fechaPF.equals(""))
			{
				qFechPick = " AND ra.picked_at BETWEEN '"+fechaPI+"' AND '"+fechaPF+"'";
				qFechVerif = " AND tr.FechaHora BETWEEN '"+fechaPI+"' AND '"+fechaPF+"'";
			}

			String consulta = 
					" SELECT p.id,DATE(p.fecha) AS Fecha, IFNULL(cla.Descripcion,'sin definir') AS Clase, IFNULL(cat.Descripcion, 'sin definir') AS Categoria,CONCAT(usu.Nombre, ' ',usu.Apellido) AS NombreCompleto, \r\n" + 
					" p.unidades AS Solicitadas, (SELECT SUM(Verif) FROM reposicion_articulos ra WHERE ra.idPicking = p.id) AS Verificadas,\r\n" + 
					" (select SUM(Remitidas) FROM reposicion_articulos ra WHERE ra.idPicking = p.id) AS Remitidas,\r\n" + 
					" IF(\r\n" + 
					" \r\n" + 
					" (TIMESTAMPDIFF(MINUTE,(select MIN(ra.picked_at) FROM reposicion_articulos ra WHERE ra.idPicking = p.id AND ra.Picked > 0),  \r\n" + 
					" (select MAX(ra.picked_at) FROM reposicion_articulos ra WHERE ra.idPicking = p.id AND ra.Picked > 0)  \r\n" + 
					" )  \r\n" + 
					" ) IS NULL, 'Sin Comenzar', TIMESTAMPDIFF(MINUTE,(select MIN(ra.picked_at) FROM reposicion_articulos ra WHERE ra.idPicking = p.id AND ra.Picked > 0),  \r\n" + 
					" (select MAX(ra.picked_at) FROM reposicion_articulos ra WHERE ra.idPicking = p.id AND ra.Picked > 0)  \r\n" + 
					" )  \r\n" + 
					" ) AS Tiempo_Picking_Minutos  \r\n" + 
					" ,COUNT(Distinct(ra.idArticulo)) AS Cantidad_Lineas,\r\n" + 
					" COUNT(DISTINCT(cUbicacion)) AS Cantidad_Posiciones\r\n" + 
					" FROM picking p \r\n" + 
					" INNER JOIN reposicion_articulos ra ON ra.idPicking = p.id AND ra.IdEmpresa = p.IdEmpresa\r\n" + 
					" INNER JOIN reposicion_articulos_ojos rao ON rao.idPicking = ra.idPicking AND ra.IdEmpresa = rao.IdEmpresa \r\n" + 
					"	AND ra.idArticulo = rao.idArticulo\r\n" + 
					" INNER JOIN articulos a ON a.idArticulo = ra.idArticulo AND a.IdEmpresa = ra.IdEmpresa\r\n" + 
					" LEFT OUTER JOIN art_clase cla ON cla.id = a.IdClase AND cla.IdEmpresa = a.IdEmpresa\r\n" + 
					" LEFT OUTER JOIN art_categoria cat ON cat.id = a.IdCategoria AND cat.IdEmpresa = a.IdEmpresa\r\n" + 
					" INNER JOIN usuarios usu ON usu.idUsuario = ra.idUsuario AND usu.idEmpresa = ra.IdEmpresa"
							+ " WHERE ra.idEmpresa = "+ idEmpresa + " AND ra.idPicking <> 0"; 

			String groupByP = " GROUP BY p.id ";
			
			String consulta2 = "SELECT tr.Hilo, CONCAT(u.Nombre,' ',u.Apellido) AS usuarioVerif, TIMESTAMPDIFF(MINUTE, MIN(tr.FechaHora),MAX(tr.FechaHora)) AS TiempoVerif \r\n" + 
					" FROM tarea_registro tr\r\n" + 
					" INNER JOIN usuarios u ON tr.idUsuario = u.idUsuario AND u.idEmpresa = tr.IdEmpresa\r\n" + 
					" WHERE tr.idEmpresa = "+ idEmpresa +" AND tr.tipoTarea = 112 " + 
					" AND tr.Comentario LIKE 'Verificando articulo%'\r\n";
			String groupByV = " GROUP BY tr.Hilo;";
			return econ.darListaReporteProductividadPicking(consulta+qDep+qUsu+qCat+qCla+qFechPick+groupByP, consulta2+qFechVerif+groupByV);

		}
		catch (Exception e) 
		{

			return new ArrayList<>();
		}
	}

	/*pedidosProcesadosXOperario*/
	public List<PedidosProcesadosXOperario> darListaPedidosProcesadosXOperario(String idUsuario, 
			String fechaPI, String fechaPF, int idEmpresa){
		List<PedidosProcesadosXOperario> result = new ArrayList<>();


		Connection cone;
		try {
			cone = econ.getConnection();

			String qUsu = "";
			String qFechPick = "";

			if(!idUsuario.equals(""))
			{
				qUsu= " AND ra.idUsuario IN ("+idUsuario+") ";
			}
			if(!fechaPI.equals("") && !fechaPF.equals(""))
			{
				qFechPick = " AND ra.picked_at BETWEEN '"+fechaPI+"' AND '"+fechaPF+"'";
			}

			String consulta = 
					" SELECT COUNT(DISTINCT(ep.idPedido)) AS Cantidad_Procesados, SUM(ra.Picked), CONCAT(us.Nombre, ' ', us.Apellido)\r\n" + 
					" FROM ecommerce_pedido ep\r\n" + 
					" INNER JOIN reposicion_articulos ra ON ra.Seccion = ep.idPedido AND ra.IdEmpresa = ep.IdEmpresa\r\n" + 
					" INNER JOIN usuarios us ON us.idUsuario = ra.idUsuario AND us.idEmpresa = ra.IdEmpresa\r\n" + 
					" WHERE (ep.EstadoEncuentra > 3 AND ep.EstadoEncuentra <> 99)\r\n" + 
					" AND ra.idUsuario <> 0\r\n" + 
					" AND ep.IdEmpresa = " + idEmpresa; 

			String groupByP = " GROUP BY ra.idUsuario";
			
			return econ.darListaPedidosProcesadosXOperario(consulta+qUsu+qFechPick+groupByP);

		}
		catch (Exception e) 
		{

			return new ArrayList<>();
		}
	}
	
	//PedidosRetrasados
	public List<PedidosRetrasados> darListaPedidosRetrasados(String fechaPI, String fechaPF, int idEmpresa){
		List<PedidosRetrasados> result = new ArrayList<>();


		Connection cone;
		try {
			cone = econ.getConnection();

			String qFechPick = "";

			if(!fechaPI.equals("") && !fechaPF.equals(""))
			{
				qFechPick = " AND ep.stampTime BETWEEN '"+fechaPI+"' AND '"+fechaPF+"'";
			}

			String consulta = 
					" SELECT ep.idPedido, if(TIMESTAMPDIFF(HOUR, ep.stampTime, NOW()) > 12 AND TIMESTAMPDIFF(HOUR, ep.stampTime, NOW()) < 24, 'Pr?ximo a atrasarse', 'Atrasado') 'Desc',\r\n" + 
					"	 CONCAT(TIMESTAMPDIFF(HOUR, ep.stampTime, NOW())) AS Sincronizado_Hace_Horas, eee.Descripcion 'Estado encuentra', \r\n" +
					" d.Nombre"+
					" FROM ecommerce_pedido ep \r\n" + 
					" INNER JOIN ecommerce_estado_encuentra eee ON eee.id = ep.EstadoEncuentra AND eee.IdEmpresa = ep.IdEmpresa \r\n"+ 
					" INNER JOIN ecommerce_pedido_destino epd ON epd.idPedido = ep.idPedido AND epd.IdEmpresa = ep.IdEmpresa \r\n" + 
					" INNER JOIN depositos d ON d.idDeposito = epd.idDestino AND d.IdEmpresa = epd.IdEmpresa \r\n" +
					" WHERE TIMESTAMPDIFF(HOUR, ep.stampTime, NOW()) > 12" + 
					" AND eee.id IN (1,2,3)" + 
					" AND ep.IdEmpresa =" + idEmpresa; 

			String orderBy = " ORDER BY CAST(Sincronizado_Hace_Horas AS SIGNED INTEGER) DESC;";
			
			return econ.darListaPedidosRetrasados(consulta+qFechPick+orderBy);

		}
		catch (Exception e) 
		{

			return new ArrayList<>();
		}
	}
	
	public List<Picking> darListaReportePicking(String pickingNumber, String idDeposito, String idArticulo, 
			String fechaI, String fechaF, String marca, String genero, 
			String categoria, String clase, int idEmpresa){

		List<Picking> result = new ArrayList<>();

		Connection cone;
		try {
			cone = econ.getConnection();

			String qDep = "";
			String qArt = "";
			String qMar = "";
			String qGen = "";
			String qCat = "";
			String qCla = "";
			String qFec = "";

			if(!idDeposito.equals(""))
			{
				qDep= " AND ra.Destino IN ("+idDeposito+") ";
			}
			if(!idArticulo.equals(""))
			{
				qArt= " AND ra.idArticulo IN ('"+idArticulo+"') ";
			}
			if(!marca.equals(""))
			{
				qMar = " AND a.IdMarca IN ("+marca+") ";
			}
			if(!genero.equals(""))
			{
				qGen = " AND a.IdGenero IN ("+genero+") ";
			}
			if(!categoria.equals(""))
			{
				qCat = " AND a.IdCategoria IN ("+categoria+") ";
			}
			if(!clase.equals(""))
			{
				qCla = " AND a.IdClase IN ("+clase+") ";
			}
			if(!fechaI.equals("") && !fechaF.equals(""))
			{
				qFec = " AND rs.Fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' "
						+ " AND ra.remitida_at BETWEEN '"+fechaI+"' AND '"+fechaF+"' ";
			}

			String consulta = 
							"SELECT ra.IdSolicitudTraslado, ra.idArticulo, ra.Seccion, cla.Descripcion AS Clase, mar.Descripcion AS Marca, " + 
							" gen.Descripcion AS Genero, cat.Descripcion AS Categoria, origen.idOjo AS Ojo, estOrigen.Descripcion AS Estanteria, " + 
							" estOrigen.numPiso AS Piso, origen.idModulo AS Modulo, origen.idEstante AS Estante, destino.idDeposito AS Deposito, " + 
							" pic.Estado,rs.Fecha AS Fecha_Solicitud, ra.remitida_at AS Fecha_Documento," + 
							" us.Nickname AS Usuario," + 
							" IF(destino.idDeposito = 1200, br.doc, mc.doc) AS Documento, " + 
							" IF(destino.idDeposito = 1200, br.idBulto, '') AS NroCaja" + 
							" FROM reposicion_articulos ra" + 
							" INNER JOIN articulos a ON a.idArticulo = ra.idArticulo AND a.IdEmpresa = ra.IdEmpresa" + 
							" INNER JOIN art_marca mar ON mar.Id = a.IdMarca AND mar.IdEmpresa = a.IdEmpresa" + 
							" INNER JOIN art_clase cla ON cla.id = a.IdClase AND cla.IdEmpresa = a.IdEmpresa" + 
							" INNER JOIN art_genero gen ON gen.id = a.IdGenero AND gen.IdEmpresa = a.IdEmpresa" + 
							" INNER JOIN art_categoria cat ON cat.id = a.IdCategoria AND cat.IdEmpresa = a.IdEmpresa" + 
							" INNER JOIN ojos origen ON origen.idOjo = ra.Origen AND origen.IdEmpresa = ra.IdEmpresa" + 
							" INNER JOIN estanterias estOrigen ON estOrigen.idEstanteria = origen.idEstanteria AND estOrigen.idEmpresa = origen.IdEmpresa" + 
							" INNER JOIN depositos destino ON destino.idDeposito = ra.Destino AND destino.IdEmpresa = ra.IdEmpresa" + 
							" INNER JOIN picking pic ON pic.id = ra.idPicking AND pic.IdEmpresa = ra.IdEmpresa" + 
							" INNER JOIN reposicion_sincronizacion rs ON rs.Id = ra.IdSincronizacion AND rs.IdEmpresa = ra.IdEmpresa" + 
							" INNER JOIN tareas t ON t.idDoc = pic.id AND t.IdEmpresa = pic.IdEmpresa" + 
							" INNER JOIN usuarios us ON us.idUsuario = t.idUsuario AND us.idEmpresa = t.IdEmpresa" + 
							" LEFT OUTER JOIN bulto_contenido_movimiento bcm ON bcm.picking = pic.id AND bcm.IdEmpresa = pic.IdEmpresa" + 
							" AND bcm.idArticulo = a.idArticulo AND bcm.IdEmpresa = a.IdEmpresa" + 
							" LEFT OUTER JOIN bulto_remito br ON br.idBulto = bcm.idBulto AND br.IdEmpresa = bcm.IdEmpresa" + 
							" LEFT OUTER JOIN movstock_cabezal mc ON mc.picking = pic.id AND pic.IdEmpresa = mc.idEmpresa" + 
							" WHERE ra.idEmpresa = "+ idEmpresa; 

			String groupBy = "";

			return econ.darListaReportePicking(consulta+qDep+qArt+qMar+qGen+qCat+qCla+qFec+groupBy);

		}
		catch (Exception e) 
		{

			return new ArrayList<>();
		}
	}
	
	public List<Picking2> darListaReportePicking2(String pickingNumber, String idDeposito, String idArticulo, 
			String fechaI, String fechaF, String marca, String genero, 
			String categoria, String clase, int idEmpresa, int depositoFrom){

		Connection cone;
		try {
			cone = econ.getConnection();

			String qNumber = "";
			String qDep = "";
			String qArt = "";
			String qMar = "";
			String qGen = "";
			String qCat = "";
			String qCla = "";
			String qFec = "";
			
			if(!pickingNumber.equals(""))
			{
				qNumber= " AND ra.idPicking IN ("+pickingNumber+") ";
			}

			if(!idDeposito.equals(""))
			{
				qDep= " AND ra.Destino IN ("+idDeposito+") ";
			}
			if(!idArticulo.equals(""))
			{
				qArt= " AND ra.idArticulo IN ('"+idArticulo+"') ";
			}
			if(!marca.equals(""))
			{
				qMar = " AND a.IdMarca IN ("+marca+") ";
			}
			if(!genero.equals(""))
			{
				qGen = " AND a.IdGenero IN ("+genero+") ";
			}
			if(!categoria.equals(""))
			{
				qCat = " AND a.IdCategoria IN ("+categoria+") ";
			}
			if(!clase.equals(""))
			{
				qCla = " AND a.IdClase IN ("+clase+") ";
			}
			if(!fechaI.equals("") && !fechaF.equals("") && pickingNumber.equals(""))
			{
				qFec = " AND (ra.remitida_at BETWEEN '"+fechaI+"' AND '"+fechaF+"') ";
			}
			
			String consulta = 
							"SELECT ra.idPicking, ra.IdSolicitudTraslado, rs.Fecha AS Fecha_Solicitud, ra.remitida_at AS Fecha_Documento, GROUP_CONCAT(distinct rao.cUbicacion,'(',rao.Cantidad,')') Origen, ra.Destino, ra.Justificacion, ra.Seccion,\r\n" + 
							"ra.idArticulo, IFNULL(mar.Descripcion,'0') AS Marca, IFNULL(cla.Descripcion,'0') AS Clase, IFNULL(gen.Descripcion,'') AS Genero, IFNULL(cat.Descripcion,'0') AS Categoria,\r\n" + 
							"ra.Cantidad, ra.Picked, ra.Verif, ra.Remitidas, CONCAT(us.nombre,' ',us.apellido) AS UsuarioPicking, IFNULL(br.doc,'') Remito\r\n"+
							"from reposicion_articulos ra\r\n" + 
							"JOIN reposicion_articulos_ojos rao ON rao.idArticulo = ra.idArticulo AND rao.idPicking = ra.idPicking AND rao.destino = ra.Destino AND rao.pedido = ra.Seccion AND rao.idEmpresa = ra.IdEmpresa\r\n" + 
							"JOIN reposicion_sincronizacion rs ON rs.Id = ra.IdSincronizacion AND rs.IdEmpresa = ra.IdEmpresa\r\n" + 
							"JOIN articulos a ON a.idArticulo = ra.idArticulo AND a.IdEmpresa = ra.IdEmpresa\r\n" + 
							"left JOIN art_marca mar ON mar.Id = a.IdMarca AND mar.IdEmpresa = a.IdEmpresa\r\n" + 
							"left JOIN art_clase cla ON cla.id = a.IdClase AND cla.IdEmpresa = a.IdEmpresa\r\n" + 
							"left JOIN art_genero gen ON gen.id = a.IdGenero AND gen.IdEmpresa = a.IdEmpresa\r\n" + 
							"left JOIN art_categoria cat ON cat.id = a.IdCategoria AND cat.IdEmpresa = a.IdEmpresa\r\n" + 
							"JOIN usuarios us ON us.idUsuario = ra.idUsuario AND us.idEmpresa = ra.IdEmpresa\r\n" + 
							" left join bulto_contenido bc on bc.idArticulo = a.idArticulo \r\n"+
							" left join bulto_remito br on br.idBulto = bc.idBulto " + 
							" WHERE ra.idEmpresa = "+ idEmpresa +
							" AND ra.Origen = "+depositoFrom+" AND ra.idPicking != 0";

			String groupBy = " GROUP BY ra.idArticulo, ra.idPicking, ra.Cantidad, ra.destino, ra.seccion, ra.IdSolicitudTraslado "
							+ " ORDER BY idPicking";

			return econ.darListaReportePicking2(consulta+qNumber+qDep+qArt+qMar+qGen+qCat+qCla+qFec+groupBy);

		}
		catch (Exception e) 
		{

			return new ArrayList<>();
		}
	}

	public List<MonitorVentaM> darmonitorVtaMayorista( String depositoD, String fechaI, String fechaF,  String pedido, int idEmpresa){
	
	Connection cone;
	try {
		cone = econ.getConnection();

		String Qpedidos = "";
		String qDepD = "";
		String qFec = "";
		

		if(!depositoD.equals(""))
		{
			qDepD= " AND RO.Destino IN ("+depositoD+") ";
		}
		if(!pedido.equals(""))
		{
			Qpedidos= " AND RO.Seccion IN ("+pedido+") ";
		}
		else if(!fechaI.equals("") && !fechaF.equals(""))
		{
			qFec = " AND RO.picked_at BETWEEN '"+fechaI+"' AND '"+fechaF+"' ";
		}
		
		String groupBy = " GROUP BY RO.Seccion, DE.Nombre ";
		
		String consulta = "SELECT MIN(RO.picked_at), DE.Nombre,RO.Seccion, SUM(RO.Cantidad) 'Cantidad', SUM(picked), SUM(RO.Verif), SUM(remitidas), ifnull(bu.qty,0) ,\r\n" + 
				"GROUP_CONCAT(DISTINCT RE.Descripcion ORDER BY RE.Paso ASC SEPARATOR ' | ')\r\n" + 
				"FROM reposicion_articulos RO\r\n" + 
				"INNER JOIN reposicion_estado RE ON RO.IdEmpresa = RE.IdEmpresa AND RO.Estado = RE.IdEstado\r\n" + 
				"INNER JOIN depositos DE ON DE.idDeposito = RO.Destino AND DE.IdEmpresa = RO.IdEmpresa\r\n" + 
				"LEFT OUTER JOIN \r\n" + 
				"(\r\n" + 
				"SELECT COUNT(idBulto) qty, idPicking, idEmpresa FROM ( SELECT idBulto, B.idEmpresa,B.picking 'idPicking' FROM bulto_contenido B WHERE B.picking!=0 GROUP BY idBulto,B.picking ) T  GROUP BY idPicking\r\n" + 
				") bu\r\n" + 
				"ON bu.idPicking = RO.idPicking AND bu.idEmpresa=RO.IdEmpresa\r\n" + 
				"\r\n" + 
				"WHERE RO.idEmpresa="+idEmpresa+" ";
		
		return econ.darListaMonitorVentaM(consulta+Qpedidos+qDepD+qFec+groupBy);

	}
	catch (Exception e) 
	{

		return new ArrayList<>();
	}

}

	public List<BultosRangoFechaDestino> darListaBultosRangoDestino(String depositoO, String depositoD, String fechaI,
			String fechaF, String agruparFech, String agruparDest, int idEmpresa){
	List<BultosRangoFechaDestino> bultosRangoDestino = new ArrayList<>();
	Connection cone;
	try {
		cone = econ.getConnection();

		String qDepO = "";
		String qDepD = "";
		String qFec = "";
		String groupBy = "";

		/*if(!depositoO.equals(""))
		{
			qDepO= " AND ra.Destino IN ("+depositoO+") ";
		}*/
		if(!depositoD.equals(""))
		{
			qDepD= " AND eed.idDestino IN ("+depositoD+") ";
		}
		if(!fechaI.equals("") && !fechaF.equals(""))
		{
			qFec = " AND eed.fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' ";
		}
		if(agruparFech!=null && agruparFech.equals("on") && agruparDest!=null && agruparDest.equals("on")) {
			groupBy = " GROUP BY CAST(eed.fecha AS DATE), b.destino";
		} else if (agruparFech==null && agruparDest!=null && agruparDest.equals("on")) {
			groupBy = " GROUP BY b.destino";
		} else if (agruparFech!=null && agruparFech.equals("on") && agruparDest==null) {
			groupBy = " GROUP BY CAST(eed.fecha AS DATE)";
		}
		
		String consulta = "";
		if(agruparFech==null) {
			consulta = " SELECT CONCAT(MIN(DISTINCT(CAST(eed.fecha AS DATE))), ' - ', MAX(DISTINCT(CAST(eed.fecha AS DATE)))) AS Fecha, '9000' AS Origen,"
				+ " GROUP_CONCAT(DISTINCT(b.Destino)) Destinos, COUNT(DISTINCT(b.idBulto)) AS Cantidad_Bultos";
		} else {
			consulta = "SELECT CAST(eed.fecha AS DATE) AS Fecha, '9000' AS Origen, " + 
					" GROUP_CONCAT(DISTINCT(b.Destino)) Destinos, COUNT(DISTINCT(b.idBulto)) AS Cantidad_Bultos";
		}
		
		consulta += /*EL DEPOSITO ORIGEN PUEDE VARIAR*/ 
				" FROM envio_encuentra_documento eed\r\n" + 
				" INNER JOIN envio_encuentra_documento_lineas eedl ON eed.idDocumento = eedl.idDocumento AND eed.IdEmpresa = eedl.IdEmpresa\r\n" + 
				" INNER JOIN bulto b ON eedl.idArticulo = b.idBulto AND eedl.IdEmpresa = b.IdEmpresa AND b.destino=eed.idDestino\r\n" + 
				" WHERE b.IdEmpresa = "+idEmpresa+" AND b.destino <> '9000'\r\n";
				//" GROUP BY CAST(eed.fecha AS DATE), eed.idDestino;

		return econ.darListaBultosRangoDestino(consulta+qDepO+qDepD+qFec+groupBy);

	}
	catch (Exception e) 
	{

		return new ArrayList<>();
	}

}
	
	public List<StockEncuentraVisual> darListaStockEncuentraVisual(String deposito, String articulos, int idEmpresa){
	List<StockEncuentraVisual> stocks = new ArrayList<>();
	Connection cone;
	try {
		
		String qDep = "";
		String qArt = "";
		String qArtV = "";
		String consulta = "";
		String consulta2 = "";
		
		String[] idsArticulos = articulos.split(",");
		cone = econ.getConnection();

		if(!articulos.equalsIgnoreCase("")) {

			qDep = "";
			qArt = "AND (";
			qArtV = "AND (";

			
			
			
			for (String str : idsArticulos) {
				qArt += " ota.idArticulo LIKE '%"+str+"%' ";
				qArtV += " idArticulo LIKE '%"+str+"%' ";
				if(idsArticulos.length > 1) {
					qArt += "OR";
					qArtV += "OR";
				}
			}
			if(idsArticulos.length > 1) {
				qArt = qArt.substring(0, qArt.length()-3);
				qArtV = qArtV.substring(0, qArtV.length()-3);
			}
			qArt += ") ";
			qArtV += ") ";
		}
		if(!deposito.equalsIgnoreCase(""))
		{
			qDep= " AND e.idDeposito = '"+deposito+"' ";
		}
		
			consulta+="select q1.idArticulo articulo, ifnull(suma1, 0)+ifnull(suma2, 0) \"stock encuentra\", CONCAT(ifnull(concat1, ''), ifnull(CONCAT(',', concat2), '')) AS arrayOjosCant from \r\n"
					+ "(\r\n"
					+ "select ota.idArticulo, SUM(ota.cantidad) suma1, o.idOjo, ota.Cantidad, GROUP_CONCAT('{\"descripcion\": ', '\"', ota.idOjo, '\"', ', \"id\": ', ota.Cantidad, '}') concat1 \r\n"
					+ "FROM ojostienenarticulos ota\r\n"
					+ " INNER JOIN ojos o ON o.idOjo = ota.idOjo AND o.IdEmpresa = ota.IdEmpresa\r\n"
					+ " INNER JOIN estanterias e ON e.idEstanteria = o.idEstanteria \r\n"
					+ " AND o.IdEmpresa = e.idEmpresa  " + qDep
					+ " WHERE ota.IdEmpresa = "+idEmpresa+" "+qArt+" \r\n"
					+ " AND o.idOjo not in('0','1') \r\n"
					+ " AND o.idOjo \r\n"
					+ " NOT LIKE '%E' AND o.idOjo NOT LIKE '%P'\r\n"
					+ " GROUP BY ota.idArticulo\r\n"
					+ ") as q1\r\n"
					+ "left join (\r\n"
					+ "select a.idArticulo articulo, sum(bc.cantidad) suma2, GROUP_CONCAT('{\"descripcion\": ', '\"', o.idOjo, '\"', ', \"id\": ', bc.Cantidad, '}') concat2 from bulto b \r\n"
					+ "join bulto_contenido bc on b.idBulto = bc.idBulto and b.IdEmpresa = bc.idEmpresa \r\n"
					+ "join articulos a on a.idArticulo =bc.idArticulo and a.IdEmpresa = bc.idEmpresa\r\n"
					+ "join ojostienenarticulos o on b.idBulto=o.idArticulo and a.IdEmpresa = o.idEmpresa \r\n"
					+ "where a.idEmpresa="+idEmpresa+" and (bc.picking=0 or (b.cerrado=0 and bc.picking<>0)) \r\n"
					+ "group by a.idArticulo\r\n"
					+ ") as q2\r\n"
					+ "on q1.idArticulo = q2.articulo";
			
			String consultaVisual = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED SELECT IdArticulo,stock FROM dbo.encuentra_stock WHERE deposit="+deposito+" "+qArtV;
			List<StockEncuentraVisual> stocksEncuentra = econ.darListaStockEncuentraVisual(consulta);
					
			List<DataIDDescripcion> artsVisual = new ArrayList<>();
			
			for (String idStr : idsArticulos) {
				DataIDDescripcion art = new DataIDDescripcion();
				art.setDescripcion(idStr);
				artsVisual.add(art);
			}
			
			MSSQL ms = new MSSQL();
			if(artsVisual.size() > 0) {
				Hashtable<String, Integer> stocksVisual = ms.darStocksLike(consultaVisual);
				
					for (StockEncuentraVisual sev : stocksEncuentra) {
						if(stocksVisual.get(sev.getIdArticulo()) != null)
							sev.setStockVisual(stocksVisual.get(sev.getIdArticulo()).toString());
					}
				return stocksEncuentra;
			}
		
		return new ArrayList<>();
	}
	catch (Exception e) 
	{

		return new ArrayList<>();
	}

}

	public List<ExpedicionMovimiento> darListaExpedicionMovimiento(String depositoO, String depositoD, String fechaI, 
			String fechaF, int idEmpresa){
		List<ExpedicionMovimiento> expedicionMovs = new ArrayList<>();
		Connection cone;
		try {
			cone = econ.getConnection();

			String qDepO = "";
			String qDepD = "";
			String qFec = "";

			if(!depositoO.equals(""))
			{
				qDepO= " AND envED.idOrigen IN ("+depositoO+") ";
			}
			if(!depositoD.equals(""))
			{
				qDepD= " AND envED.idDestino IN ("+depositoD+") ";
			}
			if(!fechaI.equals("") && !fechaF.equals(""))
			{
				qFec = " AND envED.fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' ";
			}
			

			String consulta = 
					" SELECT t1.Solicitud, t1.Origen, t1.Destino, \r\n" + 
					" t1.Seccion, t1.Articulo, AM.Descripcion, AC.Descripcion, t1.nroBulto, \r\n" + 
					" t1.Documento, t1.Fecha\r\n" + 
					" from\r\n" + 
					" (SELECT repA.IdSolicitudTraslado AS Solicitud, deO.Nombre AS Origen, \r\n" + 
					" dD.Nombre AS Destino, repA.Seccion AS Seccion,\r\n" + 
					" art.idArticulo AS Articulo,\r\n" + 
					" bulC.idBulto AS nroBulto,\r\n" + 
					"  envED.IdEmpresa AS 'EMPRESA',\r\n" + 
					" envED.idDocumento AS Documento, envED.fecha AS Fecha, art.IdMarca, art.IdClase\r\n" + 
					" FROM envio_encuentra_documento envED\r\n" + 
					" INNER JOIN envio env ON env.idEnvio = envED.idEnvio AND env.IdEmpresa = envED.IdEmpresa\r\n" + 
					" INNER JOIN envio_encuentra_documento_lineas envEDL ON envEDL.idDocumento = envED.idDocumento AND envEDL.IdEmpresa = envED.IdEmpresa\r\n" + 
					" INNER JOIN envio_encuentra_razones envER ON envER.idRazon = envED.idRazon AND envER.IdEmpresa = envED.IdEmpresa\r\n" + 
					" INNER JOIN bulto_contenido bulC ON bulC.idBulto = envEDL.idArticulo AND bulC.IdEmpresa = envEDL.IdEmpresa\r\n" + 
					" INNER JOIN picking pic ON pic.id = bulC.picking AND pic.IdEmpresa = bulC.IdEmpresa\r\n" + 
					" INNER JOIN depositos deO ON deO.idDeposito = envED.idOrigen AND deO.IdEmpresa = envED.IdEmpresa  \r\n" + 
					" INNER JOIN depositos dD ON dD.idDeposito = envED.idDestino AND envED.IdEmpresa = dD.IdEmpresa " +
					" INNER JOIN reposicion_articulos repA ON repA.idPicking = pic.id AND repA.IdEmpresa = pic.IdEmpresa\r\n" + 
					" INNER JOIN articulos art ON art.idArticulo = repA.idArticulo AND art.IdEmpresa = repA.IdEmpresa\r\n" + 
					" WHERE envER.idRazon = 3\r\n" + 
					 qFec + qDepO + qDepD +")\r\n" + 
					" t1\r\n" + 
					" INNER JOIN art_marca AM ON AM.id = t1.IdMarca AND AM.IdEmpresa = t1.EMPRESA\r\n" + 
					" INNER JOIN art_clase AC ON AC.id = t1.IdClase AND AC.IdEmpresa = t1.EMPRESA\r\n" + 
					" WHERE t1.EMPRESA ="+idEmpresa; 
			
			
			
			consulta += 
					" UNION ALL \r\n" + 
					" SELECT repA.IdSolicitudTraslado AS Solicitud, \r\n" + 
					"	deO.Nombre AS Origen, \r\n" + 
					"    dD.Nombre AS Destino, \r\n" + 
					"	repA.Seccion AS Seccion,\r\n" + 
					"	art.idArticulo AS Articulo, artM.Descripcion AS Marca, \r\n" + 
					"	artC.Descripcion AS Clase, 'Ecommerce' AS nroBulto, \r\n" + 
					"	envED.idDocumento AS Documento, envED.fecha AS Fecha\r\n" + 
					" FROM envio_encuentra_documento envED\r\n" + 
					" INNER JOIN envio env ON env.idEnvio = envED.idEnvio AND env.IdEmpresa = envED.IdEmpresa\r\n" + 
					" INNER JOIN envio_encuentra_documento_lineas envEDL ON envEDL.idDocumento = envED.idDocumento AND envEDL.IdEmpresa = envED.IdEmpresa\r\n" + 
					" INNER JOIN envio_encuentra_razones envER ON envER.idRazon = envED.idRazon AND envER.IdEmpresa = envED.IdEmpresa\r\n" + 
					" INNER JOIN ecommerce_pedido ecoP ON ecoP.idPedido = envEDL.idArticulo AND ecoP.IdEmpresa = envEDL.IdEmpresa\r\n" + 
					" INNER JOIN ecommerce_pedido_articulos ecoPA ON ecoPA.idPedido = ecoP.idPedido AND ecoPA.IdEmpresa = ecoP.IdEmpresa\r\n" + 
					" INNER JOIN reposicion_articulos repA ON repA.idArticulo = ecoPA.idArticulo AND repA.IdEmpresa = ecoPA.IdEmpresa AND repA.Seccion = ecoPA.idPedido\r\n" + 
					" INNER JOIN depositos deO ON deO.idDeposito = envED.idOrigen AND deO.IdEmpresa = envED.IdEmpresa  \r\n" + 
					" INNER JOIN depositos dD ON dD.idDeposito = envED.idDestino AND envED.IdEmpresa = dD.IdEmpresa " +
					" INNER JOIN articulos art ON art.idArticulo = repA.idArticulo AND art.IdEmpresa = repA.IdEmpresa\r\n" + 
					" INNER JOIN art_marca artM ON artM.id = art.IdMarca AND artM.IdEmpresa = art.IdEmpresa\r\n" + 
					" INNER JOIN art_clase artC ON artC.id = art.idClase AND artC.IdEmpresa = art.IdEmpresa\r\n" + 
					" WHERE envER.idRazon = 2" +
					" AND envED.IdEmpresa = " + idEmpresa; 
			
			consulta += qDepO + qDepD + qFec;
			
			return econ.darListaExpedicionMovimiento(consulta);
		}
		catch (Exception e) 
		{

			return new ArrayList<>();
		}

	}
	
	
	public List<AjustesDiferencias> darListaAjustesDiferencias(String deposito, String fechaI, String fechaF, 
			String articulo, String marca, String genero, String categoria, String clase, int idEmpresa){
		List<AjustesDiferencias> listaAjustesDiferencias = new ArrayList<>();
		Connection cone;
		try {
			cone = econ.getConnection();
			String qDep = "";
			String qFec = "";
			String qArt = "";
			String qMar = "";
			String qGen = "";
			String qCat = "";
			String qCla = "";
			
			if(!deposito.equals(""))
			{
				qDep= " AND (ESOR.idDeposito = "+deposito+" OR ESDE.idDeposito = "+deposito+") ";
			}
			if(!fechaI.equals("") && !fechaF.equals(""))
			{
				qFec= " AND DATE(MA.`update`)>= '"+fechaI+"' AND DATE(MA.`update`)<= '"+fechaF+"' ";
			}
			if(!articulo.equals(""))
			{
				qArt= " AND MA.idArticulo LIKE '"+articulo+"' ";
			}
			if(!marca.equals(""))
			{
				qMar = " AND AM.id IN ("+marca+") ";
			}
			if(!genero.equals(""))
			{
				qGen = "  AND AG.id IN ("+genero+") ";
			}
			if(!categoria.equals(""))
			{
				qCat = " AND AT.id IN ("+categoria+") ";
			}
			if(!clase.equals(""))
			{
				qCla = " AND AC.id IN ("+clase+") ";
			}
			

			String consulta = 
					" SELECT IFNULL(ESOR.idDeposito,ESDE.idDeposito) Deposito,MA.`update` 'Fecha', AM.Descripcion 'Marca',\r\n" + 
					" AG.Descripcion 'Genero',AT.Descripcion 'Categoria',AC.Descripcion 'Clase', CONCAT(US.Nombre , ' ',US.Apellido) 'Usuario',\r\n" + 
					" AR.idArticulo, AR.Descripcion, MA.cantidad * (CASE when  MA.IdTipo = 'ADD' then 1 ELSE -1 END) 'Cantidad',\r\n" + 
					"  CONCAT(IFNULL(ESOR.Descripcion,ESDE.Descripcion), ' (',  IFNULL(OJOR.idOjo,OJDE.idOjo), ')' )'observaciones'\r\n" + 
					" FROM movsarticulos MA \r\n" + 
					" INNER JOIN articulos AR ON AR.idArticulo = MA.idArticulo AND MA.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_marca AM ON AM.id = AR.IdMarca AND AM.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_genero AG ON AG.id = AR.IdGenero AND AG.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_categoria AT ON AT.id = AR.IdCategoria AND AT.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN art_clase AC ON AC.id = AR.IdClase AND AC.IdEmpresa = AR.IdEmpresa\r\n" + 
					" INNER JOIN usuarios US ON US.idUsuario = MA.usuario AND US.idEmpresa = MA.IdEmpresa\r\n" + 
					" LEFT OUTER JOIN ojos OJOR ON OJOR.idOjo = MA.codOrigen AND OJOR.IdEmpresa = MA.IdEmpresa\r\n" + 
					" LEFT OUTER JOIN ojos OJDE ON OJDE.idOjo = MA.codDestino AND OJDE.IdEmpresa = MA.IdEmpresa\r\n" + 
					" LEFT OUTER JOIN estanterias ESOR ON ESOR.idEstanteria = OJOR.idEstanteria AND ESOR.idEmpresa = OJOR.IdEmpresa\r\n" + 
					" LEFT OUTER JOIN estanterias ESDE ON ESDE.idEstanteria = OJDE.idEstanteria AND ESDE.idEmpresa = OJDE.IdEmpresa\r\n" + 
					" WHERE MA.idEmpresa = " + idEmpresa; 
					
			
			consulta = consulta+qDep+qFec+qArt+qMar+qGen+qCat+qCla;
			
			return econ.darListaAjustesDiferencias(consulta);

		}
		catch (Exception e) 
		{
			return new ArrayList<>();
		}

	}
	
	public List<FrecuenciaUbicacionesProductos> darListaFrecuenciasUbicacionesArticulos(String articulo, String fechaI, String fechaF, 
			String agrupar, String marca, String genero, String categoria, String clase, int idEmpresa){
		List<FrecuenciaUbicacionesProductos> listaAjustesDiferencias = new ArrayList<>();
		Connection cone;
		try {
			cone = econ.getConnection();
			String qArt = "";
			String qFec = "";
			String qMar = "";
			String qGen = "";
			String qCat = "";
			String qCla = "";
			String groupBy = " GROUP BY rao.cUbicacion";

			if(!articulo.equals(""))
			{
				qArt= " AND ra.idArticulo = '"+articulo+"' ";
			}
			if(!fechaI.equals("") && !fechaF.equals(""))
			{
				qFec= " AND p.fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' ";
			}
			if(!marca.equals(""))
			{
				qMar = " AND ra.Marca IN ("+marca+") ";
			}
			if(!genero.equals(""))
			{
				qGen = "  AND ra.idGenero IN ("+genero+") ";
			}
			if(!categoria.equals(""))
			{
				qCat = " AND ra.idCategoria IN ("+categoria+") ";
			}
			if(!clase.equals(""))
			{
				qCla = " AND ra.Clase IN ("+clase+") ";
			}
			if(agrupar!=null && agrupar.equals("on")) {
				groupBy = " GROUP BY rao.idArticulo";
			}
			

			String consulta = 
					" SELECT ra.idArticulo AS Articulo, GROUP_CONCAT(DISTINCT(rao.cUbicacion)) AS Ubicacion, COUNT(ra.idArticulo) AS Hits, CONCAT(ROUND\r\n" + 
					" ((COUNT(ra.idArticulo)*100)/(\r\n" + 
					" SELECT COUNT(ra.idArticulo) from reposicion_articulos ra \r\n" + 
					" INNER JOIN reposicion_articulos_ojos rao ON rao.idArticulo = ra.idArticulo\r\n" + 
					" AND ra.idPicking = rao.idPicking AND ra.IdEmpresa = rao.IdEmpresa\r\n " + 
					" INNER JOIN picking p ON p.id = ra.idPicking AND p.IdEmpresa = ra.IdEmpresa" + 
					" WHERE ra.idEmpresa = "+idEmpresa+qArt+qFec+qMar+qGen+qCat+qCla+"), 0\r\n" + 
					" ), ' %') AS Porcentaje \r\n" + 
					" FROM reposicion_articulos ra \r\n" + 
					" INNER JOIN reposicion_articulos_ojos rao ON rao.idArticulo = ra.idArticulo\r\n" + 
					" AND ra.idPicking = rao.idPicking AND ra.IdEmpresa = rao.IdEmpresa\r\n " + 
					" INNER JOIN picking p ON p.id = ra.idPicking AND p.IdEmpresa = ra.IdEmpresa" + 
					" WHERE ra.idEmpresa = "+idEmpresa+qArt+qFec+qMar+qGen+qCat+qCla+groupBy;
					
			
			return econ.darListaFrecuenciasUbicacionesArticulos(consulta);

		}
		catch (Exception e) 
		{

			return new ArrayList<>();
		}

	}
	
	//
	public List<CumplimientoOrdenes> darListaCumplimientoOrdenes(String deposito, String fechaI, String fechaF, 
			String marca, String genero, String categoria, String clase, int idEmpresa){
		List<CumplimientoOrdenes> listaAjustesDiferencias = new ArrayList<>();
		Connection cone;
		try {
			cone = econ.getConnection();
			String qDep = "";
			String qFec = "";
			String qMar = "";
			String qGen = "";
			String qCat = "";
			String qCla = "";
			
			if(!deposito.equals(""))
			{
				qDep= " AND ra.Destino IN ("+deposito+") ";
			}
			if(!fechaI.equals("") && !fechaF.equals(""))
			{
				qFec= " AND p.fecha BETWEEN '"+fechaI+"' AND '"+fechaF+"' ";
			}
			if(!marca.equals(""))
			{
				qMar = " AND a.IdMarca IN ("+marca+") ";
			}
			if(!genero.equals(""))
			{
				qGen = " AND a.IdGenero IN ("+genero+") ";
			}
			if(!categoria.equals(""))
			{
				qCat = " AND a.IdCategoria IN ("+categoria+") ";
			}
			if(!clase.equals(""))
			{
				qCla = " AND a.IdClase IN ("+clase+") ";
			}
			

			String consulta = 
					" SELECT p.id, p.fecha, GROUP_CONCAT(DISTINCT(CONCAT(usu.Nombre, ' ', usu.Apellido))) 'Usuarios', \r\n" + 
					" SUM(ra.Cantidad) 'Solicitadas', SUM(ra.Remitidas) 'Remitidas'\r\n" + 
					" FROM picking p \r\n" + 
					" INNER JOIN reposicion_articulos ra ON ra.idPicking = p.id AND ra.IdEmpresa = p.IdEmpresa\r\n" + 
					" INNER JOIN articulos a ON a.idArticulo = ra.idArticulo AND a.IdEmpresa = ra.IdEmpresa\r\n" + 
					" INNER JOIN usuarios usu ON usu.idUsuario = ra.idUsuario AND usu.idEmpresa = ra.IdEmpresa\r\n" + 
					" WHERE ra.idEmpresa = " + idEmpresa +qDep+qFec+qMar+qGen+qCat+qCla+
					" GROUP BY p.id\r\n" + 
					" ORDER BY p.fecha DESC";
			
			return econ.darListaCumplimientoOrdenes(consulta);

		}
		catch (Exception e) 
		{

			return new ArrayList<>();
		}

	}
	


public List<VORecepcionSinOrden> darPackingDeRecepcion(int idRecepcion, int idEmpresa){
	
	Connection cone;
	try {
		cone = econ.getConnection();
		String consulta = "SELECT pr.idArticulo, pr.cantidad, pr.cantBultos, pr.Total \r\n" + 
				"FROM packing_recepcion pr WHERE pr.idRecepcion="+idRecepcion+" AND pr.idEmpresa=" +idEmpresa;
		return econ.darPackingRecepcion(consulta);
	} catch (Exception e) {
		e.printStackTrace();
		return new LinkedList<>();
	}
}

public List<DataIDDescripcion> darArtBarrasRecepcionToPrint(int idRecepcion, int idEmpresa){
	
	Connection cone;
	try {
		cone = econ.getConnection();
		String consulta = "SELECT pr.idArticulo, artis.Descripcion, ab.Barra, pr.Total "+
				"FROM packing_recepcion pr "+
				"INNER JOIN articulos artis ON artis.idArticulo=pr.idArticulo AND artis.idEmpresa=pr.IdEmpresa "+
				"INNER JOIN (SELECT idarticulo, MAX(barra) 'barra',idEmpresa FROM artbarra WHERE barra != idarticulo GROUP BY idarticulo) ab ON pr.idArticulo=ab.idArticulo AND pr.idEmpresa=ab.IdEmpresa "+
				"WHERE pr.idRecepcion="+idRecepcion+" AND pr.idEmpresa=" +idEmpresa;
		return econ.darArtBarrasRecepcionToPrint(consulta);
	} catch (Exception e) {
		e.printStackTrace();
		return new LinkedList<>();
	}
}

public List<bulto> darBultosToPrintRecepcion(int idRecepcion, StringBuilder sb, int idEmpresa)
{
	Connection cone;
	try {
		String marks = sb.toString();
		marks = marks.substring(0,marks.length()-1);
		cone = econ.getConnection();
		String consulta = "SELECT * FROM bulto_contenido"
				+ " WHERE recepcion = "+idRecepcion+" AND idempresa = "+idEmpresa+" AND idArticulo IN ("+marks+")"
				+ " ORDER BY idArticulo;";
		return econ.darBultosToPrintRecepcion(consulta,idRecepcion,idEmpresa);
	} catch (Exception e) {
		e.printStackTrace();
		return new LinkedList<>();
	}
}

public List<bulto> darBultosToPrintPicking(int idPicking, int idEmpresa)
{
	Connection cone;
	try {
		
		cone = econ.getConnection();
		String consulta = "SELECT B.idBulto,B.descripcion, B.cerrado,B.fecha, B.usuario, B.destino,B.fechaClose, B.pedido, B.numerador, BC.idArticulo, BC.cantidad, BC.picking, BC.recepcion, BC.fecha, BC.usuario \r\n" + 
				"FROM bulto B \r\n" + 
				"INNER JOIN bulto_Contenido BC ON B.idBulto = BC.idBulto AND BC.IdEmpresa = B.IdEmpresa\r\n" + 
				"WHERE  B.idEmpresa="+idEmpresa+" \r\n" + 
				"AND B.idBulto in\r\n" + 
				"(SELECT idBulto FROM bulto_contenido BC WHERE BC.Picking = "+idPicking+")";
		
		return econ.darBultosPicking(consulta,idEmpresa);
	} catch (Exception e) {
		e.printStackTrace();
		return new LinkedList<>();
	}
}

public List<PedidosAtr> darListaPedidoATR(String query) {
	_EncuentraConexion ec = new _EncuentraConexion();
	List<PedidosAtr> pedidosATR = new ArrayList<>();
	try {
		pedidosATR = ec.darPedidosAtrasados(query);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return pedidosATR;
}


public boolean persistirArticulos(List<DTO_Articulo> datos, int idEmpresa) {
	_EncuentraPersistir ep = new _EncuentraPersistir();
	boolean retorno = false;
	StringBuilder consulta = new StringBuilder();
	int contador = 1;
	Utilidades u = new Utilidades();
	Map<String, Object> generos = new HashMap<>();
	//generos = ec.obtenerDatosBD("art_genero", idEmpresa);
	Map<String, Object> colores = new HashMap<>();
	// colores = ec.obtenerDatosBD("art_colors", idEmpresa);
	for (DTO_Articulo art : datos) 
	{
		
		int idGenero = u.tryParse(art.getIdGenero());
		String idColor = "";
		try {
			if(idGenero == 0) {
				idGenero = generos.containsKey(art.getIdGenero()) ? (int) generos.get(art.getIdGenero()) : 0;
			}
			
			
			idColor = colores.containsKey(art.getColor()) ? (String) colores.get(art.getColor()) : "0";
		} catch (Exception e) {
		}

		String insImg = "";

		String valImg = "";
		String ondupImg = "";

		if (art.getImagen() != null) {
			insImg = ",Imagen ";
			valImg = ", '" + art.getImagen() + "'";
			ondupImg = " , Imagen = VALUES(Imagen)";

		}

		// cambiar para que siempre ponga referencia interna y guardar ean13 en barras y
		// art_descripcion
		consulta.append(
				"INSERT INTO articulos (idarticulo, descripcion, idcategoria, idmarca, idgenero, talle, color, idtemporada, idproveedor, idempresa"
						+ insImg + ",Packing, unidadVenta, IdFamilia, IdSubfamilia)" + " VALUES ('" + art.getIdArticulo() + "','" + art.getDescripcion()

						+ "'," + art.getIdCategoria() + "," + art.getIdMarca() + "," + idGenero + ",'" + art.getTalle()
						+ "'," + " '" + idColor + "'," + art.getIdTemporada() + "," + art.getIdProveedor() + ","
						+ idEmpresa + "" + valImg + "," + art.getEmpaque() + "," + art.getCantMinDeVenta() +","+art.getIdFamilia()+","+art.getIdSubfamilia()+") "
						+ " ON DUPLICATE KEY UPDATE idArticulo = VALUES(idArticulo), Descripcion = VALUES(Descripcion), Talle = VALUES(Talle),Color  = VALUES(Color),IdTemporada  = "
						+ " VALUES(IdTemporada),Coleccion  = VALUES(Coleccion),IdProveedor = VALUES(IdProveedor),IdMarca = VALUES(IdMarca),IdClase = VALUES(IdClase),IdCategoria = "
						+ " VALUES(IdCategoria), Packing = VALUES(Packing), unidadVenta = VALUES(unidadVenta) " + ondupImg + ", IdFamilia = VALUES(IdFamilia), IdSubfamilia = VALUES(IdSubfamilia); ");

		List<String> arts = art.getCodigoBarras();
		if (!arts.isEmpty()) {
			for (String a : arts) {
				consulta.append("INSERT INTO artbarra (idarticulo, barra, idEmpresa) VALUES ('" + art.getIdArticulo()
						+ "', '" + a + "', " + idEmpresa + ")"
						+ " ON DUPLICATE KEY UPDATE idArticulo = VALUES(idArticulo), barra = VALUES(barra); ");
			}
		}

		consulta.append("INSERT INTO artbarra (idarticulo, barra, idEmpresa) VALUES ('" + art.getIdArticulo() + "', '"
				+ art.getIdArticulo() + "', " + idEmpresa + ")"
				+ " ON DUPLICATE KEY UPDATE idArticulo = VALUES(idArticulo), barra = VALUES(barra); ");

		consulta.append("INSERT INTO art_descripcion (id, descripcion, idEmpresa) VALUES ('" + art.getIdArticulo()
				+ "', '" + art.getDescripcion() + "', " + idEmpresa + ")"
				+ " ON DUPLICATE KEY UPDATE id = VALUES(id), descripcion = VALUES(descripcion);  ");
	}
	System.out.println(consulta);
	try {
		retorno = ep.persistirDatosMaestros(consulta.toString());
	} catch (Exception e) {

		e.printStackTrace();
	}

	return retorno;
}

public List<DataArticuloEcommerceVerifR> SubPedidos(String idEcommerce, String subpedido, int estado, int idEmpresa) {
	try {
		return econ.darSubPedidos(idEcommerce, subpedido, estado, idEmpresa);
	} catch (Exception e) {
		System.out.println(e);
		return new ArrayList<>();
	}
}
	
		
public boolean persistirGenericos(List<DataIDDescripcion> datos, String nombreTabla, int idEmpresa) {
	_EncuentraPersistir ep = new _EncuentraPersistir();
	boolean retorno = false;
	StringBuilder consulta = new StringBuilder();
	for (DataIDDescripcion did : datos) {
		consulta.append("INSERT INTO "+nombreTabla+" (id, descripcion, idempresa) VALUES ("+did.getId()+",'"+did.getDescripcion()+"') "
						+ " ON DUPLICATE KEY UPDATE id = VALUES(id), Descripcion = VALUES(Descripcion); ");

	}
	System.out.println(consulta.toString());
	try {
		retorno = ep.persistirDatosMaestros(consulta.toString());
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return retorno;
}
	
	public EncuentraPedido EncuentraPedido (String subpedido, int idEmpresa) {
		try {			
			Connection cone = econ.getConnection();
			return econ.darEncuentraPedido(subpedido, idEmpresa);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public boolean updateSuborderWHSModdo (updateSuborderWHS order, EncuentraPedido p, int idEmpresa, Usuario u) {
		boolean salida = false;
		try {
			if(order.getNewSuborders().size() == 1) {		//si tengo una sola suborden va a pertenecer a la misma que ya tengo registrada
				String update = "update ecommerce_pedido_articulos_req set Deposito="+order.getNewSuborders().get(0).getWhouse()+", subestado=0, CantidadProcesasa=0, Confirmado=0,"
						+ "fechaImpreso= CURRENT_TIMESTAMP(), fechaConfirmado=CURRENT_TIMESTAMP(), RTimeStamp=CURRENT_TIMESTAMP(), "
						+ "CTimeStamp=CURRENT_TIMESTAMP(), PTimeStamp=CURRENT_TIMESTAMP(), EnvioTimeStamp=CURRENT_TIMESTAMP(), "
						+ "DespachoTimeStamp=CURRENT_TIMESTAMP(), RetiroTimeStamp=CURRENT_TIMESTAMP() "
						+ "where idEmpresa="+idEmpresa+" AND idPedido="+p.getIdPedido()+" ";
								
				salida = persistir(update);
				logPedido(p.getIdPedido(), u.getNumero(), 1, 
						"CAMBIO DEPOSITO, pidiendo articulos a deposito "+order.getNewSuborders().get(0).getWhouse(), 0,idEmpresa);
				updateEcommerceEstado(p.getIdPedido(),1,idEmpresa,u);
				
			}
			else {											//tengo mas subordenes y tengo que crear pedidos = subordenes
															//actualizo la suborden que ya existia
															//y creo las demas
					boolean clickCollect = p.getShippingType().getId() == 3;
					List<DataIDDescripcion> pedidoCentral = new ArrayList<>();
					int depoCentral = Integer.parseInt(darParametroEmpresa(idEmpresa, 4));//Id de Deposito Principal
					int depoWeb = Integer.parseInt(darParametroEmpresa(idEmpresa, 5));//Id de Deposito Ecommerce
					
					for(suborderWHS sub:order.getNewSuborders()){
						
						p.setSubpedido(sub.getSuborder());
						p.setArticulosPedido(new ArrayList<>());
						p.setCantidad(0);
						p.setPrecio(0.0);
						Map<String, EncuentraPedidoArticulo> itemsWHS = new HashMap<>();
						int unidades = 0;
						for(itemsWHS it: sub.getItems()) {
							EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
							unidades += it.getQty();
							
							art.setArticulo(it.getItem());
							art.setCantidad(it.getQty());
							art.setProcesada(0);
							art.setOrigen(sub.getWhouse());
							art.setDistribucionAfecta(sub.getDistributionId());
							art.setClickCollect(clickCollect);				
							art.setSubTracking(sub.getSubTracking());
							
							if(itemsWHS.get(art.getArticulo()) == null) {
								itemsWHS.put(art.getArticulo(), art);
							}
							else {
								EncuentraPedidoArticulo artE = itemsWHS.get(art.getArticulo());
								artE.setCantidad(art.getCantidad()+artE.getCantidad());
								itemsWHS.put(art.getArticulo(), artE);
							}							
						}
						
						p.setCantidad(unidades);
						p.setArticulosPedido(new ArrayList<>(itemsWHS.values()));
						
						if(order.getOriginalSubOrder().equals(sub.getSuborder())) {	//actualizo la suborden que ya existia 
							logPedido(p.getIdPedido(), u.getNumero(), 1, 
									"MODIFICACION A RAIZ DE CAMBIO DE SUB ORDEN", 0,idEmpresa);
							UpdateEncuentraPedidoUnidades(p, idEmpresa);
							UpdateArticuloReq(p,idEmpresa);
							
						}
						else {	//ingreso ordenes nuevas
							int idPedido = darSeries("insert into utilseries (IdTipo,IdEmpresa) values ('MODDO',"+idEmpresa+")").get(0);	//consigo nuevo idpedido 
							p.setIdPedido(new Long(idPedido+""));
							logPedido(p.getIdPedido(), u.getNumero(), 1, 
									"GENERACION DE PEDIDO A RAIZ DE CAMBIO DE SUB ORDEN (SUB ORDEN ORIGINAL "+order.getOriginalSubOrder()+")", 0,idEmpresa);
							p.save(idEmpresa);	//guardo pedido y articulos
							//p.updateDestino(Integer.parseInt(p.getSucursalPick()),sub.getSubTracking(), idEmpresa,0.0,false);	//guardo destino
							try {
								Call_WS_MODDO moddo = new Call_WS_MODDO();
								boolean preparaCentral = false;
								try {
									preparaCentral = p.getArticulosPedido().get(0).getOrigen() == depoCentral;
								} catch (Exception e) {}
								moddo.GuardarDestino(p, preparaCentral, idEmpresa,p.getCanalAnaloga(), "");
							} catch (Exception e) {
								System.out.println("no se pudo guardar destino WHS Moddo");
							}
							
							pedidoCentral.addAll(AltaArticuloReq(p, idEmpresa, depoCentral, depoWeb));	//guardo articulos req
						}
						
					}
					
					if(!pedidoCentral.isEmpty())														//guardo reposiciones
					{
						boolean manual = false;
						darArticuloRepoFromLoadForus(pedidoCentral,depoWeb,manual,idEmpresa,depoCentral,2,false);
					}
				
			}
		} catch (Exception e) {
			salida = false;
			e.printStackTrace();
		}
		return salida;
	}
	
	public void CambioEstadoMarketPlace(int idEmpresa, DataArticuloEcommerceVerifR articuloR) {
			
		try 
		{
			if(!hayRegistro("SELECT idPedido FROM ecommerce_pedido WHERE shippingType = 3 AND idPedido = "+articuloR.getIdPedido()+" AND idEmpresa="+idEmpresa +" and idcanalml <= 7"))
			{
				List<jsonEstadoMP> lista = new ArrayList<>();
				jsonEstadoMP jsonEstado = JSONCambioEstadoMarketPlace(idEmpresa, articuloR);
				if(jsonEstado.getJson().equals("")) {
					logPedido(articuloR.getIdPedido(), 0, 0, "No se pudo poner en cola el cambio de estado del MarketPlace", 0, idEmpresa);
				}
				else {				
					lista.add(jsonEstado);
					
					Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
					api.putColaCambioEstadoMarketPlace(lista,idEmpresa);
				}
			}
				
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public jsonEstadoMP JSONCambioEstadoMarketPlace(int idEmpresa, DataArticuloEcommerceVerifR articuloR) {
		String json = "";
		jsonEstadoMP jsonEstado = new jsonEstadoMP();
		try {			
			jsonEstado.setIdPedido(articuloR.getIdPedido());
			jsonEstado.setJson(json);
			jsonEstado.setCanal(articuloR.getCanal());
			jsonEstado.setIdEmpresa(idEmpresa);
			jsonEstado.setIdMarketPlace(articuloR.getIdEcommerce());
			
			//String track = darTrackingPedido(articuloR.getIdPedido(),idEmpresa);
			switch (idEmpresa) {
			case 2:
				if(articuloR.getCanal() <= 7 ) {
					Call_WS_analoga c = new Call_WS_analoga();
					json = c.JSONUpdateState(articuloR.getIdPedido(), articuloR.getIdEcommerce(), articuloR.getTracking(), articuloR.getEstadoEncuentra());
				}
				else {
					Utilidades util = new Utilidades();
					String pdfInBase64 = "";
					if(!articuloR.getUrlEtiqueta().equalsIgnoreCase("")) {
						try {
							byte[] encoded = util.encodeFile(articuloR.getUrlEtiqueta());
							pdfInBase64 = new String(encoded);
						} catch (Exception e) {}
					}				
					
					Call_WS_MODDO m = new Call_WS_MODDO();
					json = m.JSONUpdateState(articuloR.getSubpedido(), articuloR.getTracking(), articuloR.getEstadoEncuentra(), articuloR.getTicketNumber(), 
							articuloR.getIdDestino(), articuloR.getFechaEntrega(), pdfInBase64);
					/*if(CambioEstadoModdo(articuloR.getIdEcommerce(), articuloR.getEstadoEncuentra())) {
						json+="; "+m.JSONUpdateState(articuloR.getIdEcommerce(), articuloR.getTracking(), articuloR.getEstadoEncuentra(), articuloR.getTicketNumber(), 
								articuloR.getIdDestino(), articuloR.getFechaEntrega(), pdfInBase64);
					}*/
				}
				break;
			default:
				break;
			}

			jsonEstado.setJson(json);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonEstado;
	}
	
	public void putColaEstadoMarketPlace (List<jsonEstadoMP> lista) {
		String insert = "";
		try {
			for(jsonEstadoMP e:lista) {
				insert += "insert ignore into ecommerce_cola_estados (idPedido, json, canal, idEmpresa) values "
						+ "("+e.getIdPedido()+",'"+e.getJson()+"',"+e.getCanal()+","+e.getIdEmpresa()+");";
			}
			persistir(insert);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<jsonEstadoMP> PendienteColaEstadoMarketPlace(int canal, int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			String query = "select id, json from ecommerce_cola_estados where ok = 0 AND canal="+canal+" and idEmpresa="+idEmpresa;
			return econ.DarPendienteColaEstadoMarketPlace(query);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public void updateColaEstadoMarketPlace(int id) {
		try {
			String update = "update ecommerce_cola_estados set ok=1 where id = "+id;
			
			persistir(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean CambioEstadoModdo(String idEcommerce, int estado) {
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			return econ.IsCambioEstadoModdo(idEcommerce,estado);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int tipoDestino (int destino, int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			return econ.darDataIdDescripcion("select tipo, '' from depositos where idDeposito="+destino+" and idEmpresa="+idEmpresa).getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public Hashtable<Integer, List<DataIDDescripcion>> SolicitudesMayoristas(String idbulto, int destino, 
			Hashtable<String, DataIDDescripcion> articulosHT, int idEmpresa, boolean bultoCerrado) {
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();			
			return econ.darSolicitudesMayoristas(idbulto, destino, articulosHT, idEmpresa, bultoCerrado);
		} catch (Exception e) {
			e.printStackTrace();
			return new Hashtable<>();
		}
	}

	
	public List<DataIDDescripcion> AltaArticuloReq (EncuentraPedido p, int idEmpresa, int depoCentral, int depoWeb) {
		
		List<DataIDDescripcion> pedidoCentral = new ArrayList<>();
		try
		{
			int clickCollect = 0;
			String SclickCollect = "";
			
			
			DataIDDescripcion did;
			for (EncuentraPedidoArticulo it : p.getArticulosPedido())
			{
				if(it.getVariacion()==null)
				{
					it.setVariacion("");
				}
			
				did = new DataIDDescripcion(it.getCantidad(),it.getArticulo());
				did.setIdLong(p.getIdPedido());
				did.setIdB(it.getDistribucionAfecta());
				did.setDescripcionB(it.getVariacion());
				if(it.getVariacion()==null) {
					did.setDescripcionB("");
				}
				if(it.getOrigen()==depoCentral)
				{
					pedidoCentral.add(did);
				}
				else if(it.isClickCollect()){
						clickCollect = 1;
						SclickCollect = "Click & Collect";
				}
				
				
				boolean req = true;
				req = persistir("INSERT INTO `ecommerce_pedido_articulos_req` (`idPedido`, `idArticulo`, `Deposito`, `CantidadRequerida`,"+
						" `CantidadProcesasa`, `Confirmado`,cantConfirmada,docVisual, idEmpresa, procesarEnPickup) " +
						" VALUES ('"+p.getIdPedido()+"', '"+it.getArticulo()+"', '"+it.getOrigen()+"', '"+it.getCantidad()+"', '0', '0',0,"+it.getDistribucionAfecta()+","+idEmpresa+","+clickCollect+") "
								+ "ON duplicate key update CantidadRequerida = CantidadRequerida + "+it.getCantidad()+";");
				if (req) 
				{
					logPedido(p.getIdPedido(), 0, 0, " Guardando pedido "+SclickCollect+" de articulo "+it.getArticulo()+
							" a deposito "+it.getOrigen(),0,idEmpresa);			
				}
				else 
				{
					logPedido(p.getIdPedido(), 0, 0, " Fallo queriendo pedir el articulo "+it.getArticulo()+
							" a deposito "+it.getOrigen(),0,idEmpresa);	
				}
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return pedidoCentral;
	}
	
	public void UpdateArticuloReq(EncuentraPedido p, int idEmpresa){
		try {
			String updateArt ="";
			String updateArtReq ="";
			String delete = "";
			String artsDelete = "";
			int depoDelete = 0;
			for (EncuentraPedidoArticulo a : p.getArticulosPedido()) {
				updateArtReq += "update ecommerce_pedido_articulos_req set cantidadRequerida ="+a.getCantidad()+", docVisual="+a.getDistribucionAfecta()+" where idpedido ="+p.getIdPedido()+
						" and idArticulo='"+a.getArticulo()+"' and deposito="+a.getOrigen()+" and idEmpresa ="+idEmpresa+";";
				updateArt += "update ecommerce_pedido_articulos set cantidadpedido ="+a.getCantidad()+" where idpedido ="+p.getIdPedido()+
						" and idArticulo='"+a.getArticulo()+"' and idEmpresa ="+idEmpresa+";";
				depoDelete = a.getOrigen();
				artsDelete+= "'"+a.getArticulo()+"',";
			}
			
			if(!artsDelete.equals("")) {
				artsDelete = artsDelete.substring(0,artsDelete.length()-1);
				delete = "delete from ecommerce_pedido_articulos_req where idpedido ="+p.getIdPedido()+" "+ 
						" and idArticulo not in ("+artsDelete+") and deposito="+depoDelete+" and idEmpresa ="+idEmpresa+"; "+
						"delete from ecommerce_pedido_articulos where idpedido ="+p.getIdPedido()+" "+ 
						" and idArticulo not in ("+artsDelete+") and idEmpresa ="+idEmpresa+"; ";
			}
			
			persistir(updateArt+updateArtReq+delete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateEncuentraPedidoUnidades(EncuentraPedido p, int idEmpresa){
		try {
			String update = "update ecommerce_pedido set UnidadesTotal ="+p.getCantidad()+" where idpedido ="+p.getIdPedido()+" and idEmpresa ="+idEmpresa;			
			persistir(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public eCommerce_jsonObjectsII.EncuentraPedido darPedidoTracking(String tracking, int idEmpresa) 
	{
		String q = "SELECT P.idPedido, P.idFenicio FROM ecommerce_pedido_destino ED INNER JOIN ecommerce_pedido P ON P.idPedido = ED.idPedido AND ED.IdEmpresa = P.IdEmpresa WHERE tracking = '"+tracking+"' AND ED.IdEmpresa = "+idEmpresa;
		
		
		
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			return econ.DarPedidoTracking(q);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		
	}
	
	public  Hashtable<String, DataIDDescripcion> PedidosPorCanal(int idEmpresa, int canal, int dias){
		
		String query="SELECT subpedido,estadoencuentra,idpedido from  ecommerce_pedido WHERE idEmpresa="+idEmpresa+" AND idcanalml = "+canal+ " and "+
					"CONVERT(SUBSTRING_INDEX(datediff(CURRENT_TIMESTAMP(),stampTime),':',1), UNSIGNED INTEGER) = "+dias; 
					 
		try
		{
			@SuppressWarnings("unused") Connection cone;		
			cone = econ.getConnection();
			//econ.PedidosPorCanal(query);	
			return econ.encuentraHashIDDescipcion(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	public int darCantidadAConfirmar (Long idPedido, int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			String query = "SELECT SUM(epar.CantidadRequerida)-SUM(epar.cantConfirmada), '' "+
					" FROM ecommerce_pedido_articulos_req epar "+
					" WHERE idempresa = "+idEmpresa+" AND idpedido = "+idPedido+
					" GROUP BY epar.idPedido; ";
			return econ.darDataIdDescripcion(query).getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public  void updateParametrosMatrizDerivacion(List<DepositoAdmin> deposParam, int idEmpresa) 
	{
		try 
		{
			StringBuilder sb = new StringBuilder();
			for (DepositoAdmin dep : deposParam) 
			{
				int pickup = Utilidades.parseBooleanToInt(dep.getParametros().isPreparaPickup());
				int delivery = Utilidades.parseBooleanToInt(dep.getParametros().isPreparaDelivery());
				int cd = Utilidades.parseBooleanToInt(dep.getParametros().isPreparaEnvioCD());
				
				sb.append("UPDATE `depositos` SET Prioridad = "+dep.getParametros().getPrioridad()+", PreparaPickup="+pickup+", PreparaDelivery="+delivery+", PreparaEnvioCD="+cd+", IdGrupo ="+dep.getParametros().getIdGrupo()+" WHERE idEmpresa="+idEmpresa+" AND `idDeposito`="+dep.getId()+";");
			}
			persistir(sb.toString());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}


	public boolean MovimientoDeArticulos(MovimientoAlmacen movimientos, int idEmpresa) {
		boolean result = false;
		try {
			result = eper.MovimientoDeArticulos(movimientos, idEmpresa);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isBultoConVariasReservas(String idBulto, int idEmpresa, int idPicking) {
		boolean result = false;
		try {
			result = econ.isBultoConVariasReservas(idBulto, idEmpresa, idPicking);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	public Hashtable<String, String> buscarPedido(int dias, int idEmpresa, String estados) 
	{

		
		@SuppressWarnings("unused") Connection cone;
		try 
		{
			cone = econ.getConnection();
			Hashtable<String, String> retorno = new Hashtable<>();
			String q = "SELECT P.idPedido FROM ecommerce_pedido P "
					+ "WHERE stampTime>= DATE_ADD(DATE(CURRENT_TIMESTAMP()) ,INTERVAL -"+dias+" DAY)   AND P.IdEmpresa="+idEmpresa;
			if(!estados.equals("")) {
				q += " and P.estadoEncuentra in ("+estados+")";
			}
			List<String> matches = econ.BuscarArticulo(q);
			
			for (String m : matches) 
			{
				retorno.put(m, m);
			}
		
			
			return retorno;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	
	public  boolean AltaZona(String nombre, int idEmpresa)
	{
		boolean b=false;
		
		try
		{	
			String query="INSERT INTO zonas (idZona, Descripcion, idEmpresa) \r\n" + 
					"SELECT IF(MAX(idZona) IS NULL, 1, MAX(idZona)+1), '"+nombre+"', "+idEmpresa+"  FROM zonas WHERE idEmpresa="+idEmpresa;
			
			b = eper.persistir(query);
			return b;
		}
		catch(Exception e)
		{
			return b;
		}
		
		
	}
	
	public  List<DataIDDescripcion> ListaZonas(int idZona, int idEmpresa)
	{
		
		String query="";

		
;


        if(idZona==0)
        {
             query = " SELECT idEstanteria, Descripcion FROM estanterias WHERE idempresa = "+idEmpresa+" AND idUso = 1 "+
                     " ORDER BY Descripcion, idEstanteria;";
        }
        else{
             query = " SELECT e.idEstanteria, e.Descripcion " +
                     " FROM estanterias e " +
                     " INNER JOIN zonastienenestanterias zte ON e.idEstanteria=zte.idEstanteria AND e.idEmpresa=zte.idEmpresa " +
                     " WHERE e.idempresa = "+ idEmpresa +" AND zte.idZona = "+ idZona +
                     " ORDER BY Descripcion, idEstanteria;";

        }
		
		try
		{

			@SuppressWarnings("unused") Connection cone;		
			cone = econ.getConnection();
			
			List<DataIDDescripcion> lista = econ.darListaDataIdDescripcion(query);	

			lista.remove(0);
			return lista;
		}
		catch (Exception e)
		{
		
		}

		return new ArrayList<>();
	}
	
	public  boolean AsignarQuitarEstanteriasAZona(List<Integer> lista, int zona ,String opcion,int idEmpresa)
	{
		boolean b=false;
		
		try
		{	
			String query="";
			
			if(opcion.equals("add")){
				for(Integer l:lista){
					query = query+" insert into zonastienenestanterias (idZona,idEstanteria,idEmpresa) values ("+zona+","+l+","+idEmpresa+");";
				}
			}
			else{
				for(Integer l:lista){
					query = query+" delete from zonastienenestanterias where idEstanteria="+l+" and idZona="+zona+" and idEmpresa="+idEmpresa+";";
				}
			}
			b = eper.persistir(query);
			return b;
		}
		catch(Exception e)
		{
			return b;
		}
	}


	
	public String query_MovimientosAlmacen(DataLineaRepo voy, String articulo, int idEmpresa, Usuario uLog) 
	{
		String r = "";
		
		r+= query_encuentraUpdateOjosTienenArt(voy.getCubi(),articulo,idEmpresa);	//BAJO CANTIDAD FISICA DEL OJO Y LAS RESERVADAS;
		r+= query_encuentraMoverOjos("1",articulo,1,uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A ZONA DE PICKING
		r+= query_IngresarMovimientoArticulo(voy.getCubi(), "1", articulo, 1,  uLog.getNumero(),idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA A ZONA DE PICKING
		
		return r;
		
	}
	
	public List<DataIDDescripcion> Empresas(){
		
		List<DataIDDescripcion> empresas = null;
		String query = "select idempresa, descripcion from empresa";
		@SuppressWarnings("unused") Connection cone;	
		
		try {
			cone = econ.getConnection();
			empresas =  econ.darListaDataIdDescripcion(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresas;
	}


	public Cliente darSenderPeYa(int idEmpresa, String pedido) 
	{
		String q = "SELECT D.Nombre_Encargado, D.Apellido_Encargado,D.Localidad,D.email, D.departamento,D.ciudad,D.telefono,\r\n" + 
				"D.nroPuerta,D.Observaciones,D.Latitud, D.Longitud,D.Calle FROM depositos D WHERE idEmpresa="+idEmpresa+" \r\n" + 
				"AND idDeposito = (\r\n" + 
				"SELECT R.Deposito FROM ecommerce_pedido_articulos_req  R  inner JOIN ecommerce_pedido P ON P.idPedido = R.idPedido AND P.IdEmpresa = R.IdEmpresa \r\n" + 
				"WHERE  P.idFenicio = "+pedido+" LIMIT 1)";
		System.out.println(q);
		
		try
		{

			@SuppressWarnings("unused") Connection cone;		
			cone = econ.getConnection();
			
			return econ.darSenderPeYa(q);	

			
		}
		catch (Exception e)
		{
		
		}

		
		
		return null;
	}
	
	
	public List<BultosACrear> bultosAIngresar(){
		Connection cone;
		try {
			cone = econ.getConnection();
			String consulta = "SELECT ubicacion, idArticulo, Cantidad, Estiba " + 
					"FROM aux3 order by Estiba";
			return econ.darBultosACrear(consulta);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	public List<DepositoStock> darDepositStockOrder(String inn, int idEmpresa,String idCanal) 
	{
		String q = "";
		if(idCanal==null || idCanal.equals(""))
		{
			q = "SELECT DE.idDeposito, DE.Prioridad,DE.PreparaPickup,DE.PreparaDelivery,DE.PreparaEnvioCD, ST.idArticulo, ST.Stock FROM art_stock ST \r\n" + 
					"INNER JOIN depositos DE ON ST.idDeposito = DE.idDeposito AND ST.idEmpresa=DE.IdEmpresa\r\n" + 
					"WHERE ST.idEmpresa="+idEmpresa+" AND ST.idArticulo IN ("+inn+") and ST.Stock > 0 " + 
					"ORDER BY DE.Prioridad";
		}
		else
		{
			q = "SELECT DE.idDeposito, DE.Prioridad,DE.PreparaPickup,DE.PreparaDelivery,DE.PreparaEnvioCD, ST.idArticulo, ST.Stock \r\n"
					+ "FROM art_stock ST \r\n"
					+ "INNER JOIN depositos DE ON ST.idDeposito = DE.idDeposito AND ST.idEmpresa=DE.IdEmpresa\r\n"
					+ "INNER JOIN ecommerce_canal_depositos_donan ECD ON ECD.idEmpresa = DE.IdEmpresa AND ECD.idDeposito = DE.idDeposito\r\n"
					+ "WHERE ST.idEmpresa="+idEmpresa+" AND ST.idArticulo IN ("+inn+") and ST.Stock > 0  AND ECD.IdCanal="+idCanal+"\r\n"
					+ "ORDER BY DE.Prioridad";
		}
		
		
		try
		{

			@SuppressWarnings("unused") Connection cone;		
			cone = econ.getConnection();
			
			return econ.darDepositStockOrder(q);	

			
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
	}


	public List<DataIDDescripcion> darListaPickingsSincronizados(List<Integer> ordersIn,int idEmpresa) 
	{
		String inns = "";
		boolean pri = true;
		
		
		for (Integer i : ordersIn) 
		{
			if(pri)
			{
				pri=false;
				inns +=i;
			}
			else
			{
				inns +=","+i;
			}
			
			
			
		}
		

		String q = "SELECT Seccion, idArticulo FROM reposicion_articulos RA WHERE idEmpresa="+idEmpresa+" and  RA.IdSolicitudTraslado in ("+inns+")";
		
		return darListaDataIdDescripcionConsulMYSQL(q);
		
		
		
	}
	
	public List<ReporteTest> reporteTests(int desde , int hasta){
		Connection cone;
		try {
			cone = econ.getConnection();
			String consulta = "SELECT r.id , r.DescripcionB, r.Valor, r.Descripcion FROM reporte r WHERE r.Valor >="+desde+" AND r.valor < "+hasta+";";
			return econ.reportesTest(consulta);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<ReporTEST> darReporteTEST(int desde, int hasta) 
	{
		
		String q = "SELECT R.id,R.DescripcionB,R.Valor,R.Descripcion FROM reporte R WHERE R.Valor >="+desde+" AND R.Valor<="+hasta+" ;";
		try
		{
			@SuppressWarnings("unused") Connection cone;		
			cone = econ.getConnection();
			
			return econ.darReporteTEST(q);	

			
		}
		catch (Exception e)
		{
			return new ArrayList<>();
		}
		
	}


	public List<MonitorVentaMArticulo> darmonitorVtaMayoristaArticulos(String pedido, int idEmpresa) 
	{
		Connection cone;
		try {
			cone = econ.getConnection();
			String consulta = "SELECT RO.picked_at, RO.idArticulo,RO.Justificacion,RO.Seccion, RO.Cantidad 'Cantidad', picked, RO.Verif, remitidas, \r\n" + 
					"ifnull(GROUP_CONCAT(DISTINCT BU.numerador ORDER BY BU.numerador ASC SEPARATOR ' '),0) nroCaja,\r\n" + 
					"RE.Descripcion, RO.idPicking \r\n" + 
					"FROM reposicion_articulos RO\r\n" + 
					"INNER JOIN reposicion_estado RE ON RO.IdEmpresa = RE.IdEmpresa AND RO.Estado = RE.IdEstado\r\n" + 
					"INNER JOIN depositos DE ON DE.idDeposito = RO.Destino AND DE.IdEmpresa = RO.IdEmpresa\r\n" + 
					"LEFT OUTER JOIN \r\n" + 
					"(\r\n" + 
					"	SELECT B.idEmpresa,  B.picking 'idPicking', B.idArticulo, B.cantidad, BU.numerador\r\n" + 
					"FROM bulto_contenido B \r\n" + 
					"INNER JOIN bulto BU ON B.idBulto= BU.idBulto AND BU.IdEmpresa = B.IdEmpresa\r\n" + 
					"WHERE B.picking!=0\r\n" + 
					") bu\r\n" + 
					"ON bu.idPicking = RO.idPicking AND bu.idEmpresa=RO.IdEmpresa\r\n" + 
					"AND bu.idArticulo = RO.idArticulo\r\n" + 
					"WHERE RO.idEmpresa="+idEmpresa+" \r\n" + 
					"AND RO.seccion ="+pedido+"\r\n" + 
					"GROUP BY RO.picked_at, RO.idArticulo, RO.estado ,RO.Justificacion,RO.Seccion";
			
			
			
			
			return econ.darmonitorVtaMayoristaArticulos(consulta);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	public List<DataIDDescripcion> darArticuculosPallets(List<ArticuloLineaReposicion> articulosIn, int idEmpresa) 
	{
		String inns = "";
		List<DataIDDescripcion> retorno = new ArrayList<>();
		boolean pri = true;
		Hashtable<String, DataIDDescripcion> artCant = new Hashtable<>();
		
		for (ArticuloLineaReposicion a : articulosIn) 
		{
			try
			{
				DataIDDescripcion in =null;
				
				if(artCant.containsKey(a.getIdArticulo()))
				{
					in = artCant.get(a.getIdArticulo());
					in.setId(in.getId()+a.getCantidad());
					in.setDescripcionB(in.getDescripcionB()+" "+a.getPedido());
				}
				else
				{
					in = new DataIDDescripcion(a.getCantidad(), a.getIdArticulo());
					in.setDescripcionB("Pedidos "+a.getPedido()+" ");
					
				}
				artCant.put(a.getIdArticulo(), in);
				
				if(pri)
				{
					pri=false;
					inns+="'"+a.getIdArticulo()+"'";
				}
				else
				{
					inns+=",'"+a.getIdArticulo()+"'";
				}
			}
			catch (Exception e) 
			{
				
			}
		}
		
		String qArtsNoPallet = "SELECT SUM(OT.Cantidad-OT.reservada),OT.idArticulo \r\n" + 
				"from ojostienenarticulos OT \r\n" + 
				"INNER JOIN ojos O ON O.idOjo = OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n" + 
				"INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa \r\n" + 
				"WHERE ES.AlmacenaPallet=0 AND O.idOjo!='1' AND ES.iduso in (1,3) AND OT.IdEmpresa="+idEmpresa+" AND OT.idArticulo IN ("+inns+")\r\n" + 
				"GROUP BY OT.idArticulo;" ;
			
		System.out.println(qArtsNoPallet);
				
		List <DataIDDescripcion> artsEnOjos = darListaDataIdDescripcionBConsulMYSQL(qArtsNoPallet);
		
		Hashtable<String, DataIDDescripcion> artsEnOjosHT = new Hashtable<>();
		if(artsEnOjos!=null)
		{
			for (DataIDDescripcion aoj : artsEnOjos) 
			{
				artsEnOjosHT.put(aoj.getDescripcion(), aoj);
			}
		}
		
		pri = true;
		inns = "";
		
		
		for (ArticuloLineaReposicion a : articulosIn) 
		{
			if(artsEnOjosHT.get(a.getIdArticulo())!=null)
			{
				DataIDDescripcion aoj = artsEnOjosHT.get(a.getIdArticulo()); 
				try
				{
					DataIDDescripcion in = artCant.get(aoj.getDescripcion());
					if(in.getId()>aoj.getId())
					{
						//si lo que necesito es mayor a lo que hay
						if(pri)
						{
							pri=false;
							inns+="'"+aoj.getDescripcion()+"'";
						}
						else
						{
							inns+=",'"+aoj.getDescripcion()+"'";
						}
					}
					else
					{
						artCant.remove(aoj.getDescripcion());
					}
				}
				catch (Exception e) 
				{
					
				}
			}
			else //el artriculo no existe en picking 
			{
				//si lo que necesito es mayor a lo que hay
				if(pri)
				{
					pri=false;
					inns+="'"+a.getIdArticulo()+"'";
				}
				else
				{
					inns+=",'"+a.getIdArticulo()+"'";
				}
			}
			  
			
			
		}
		
		
		
		
		
		
		/*
		String qArtsEnPallet = "SELECT BC.cantidad,BC.idArticulo, BC.idBulto, O.idRecorrido FROM bulto_contenido BC \r\n" + 
				"INNER JOIN bulto BU ON BU.idBulto = BC.idBulto AND BC.IdEmpresa = BU.IdEmpresa\r\n" + 
				"INNER JOIN ojostienenarticulos OT ON OT.idArticulo = BU.idBulto AND OT.IdEmpresa = BU.IdEmpresa\r\n" + 
				"INNER JOIN ojos O ON O.idOjo = OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n" + 
				"INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa \r\n" + 
				"WHERE  ES.AlmacenaPallet=1 AND OT.IdEmpresa=5 AND BC.idArticulo IN ("+inns+") ORDER BY BC.idArticulo, BC.cantidad,O.idRecorrido;";
		*/
		
		String qArtsEnPallet = "SELECT BC.cantidad,BC.idArticulo, BC.idBulto, O.idRecorrido FROM bulto_contenido BC \r\n" + 
				"INNER JOIN bulto BU ON BU.idBulto = BC.idBulto AND BC.IdEmpresa = BU.IdEmpresa\r\n" + 
				"INNER JOIN ojostienenarticulos OT ON OT.idArticulo = BU.idBulto AND OT.IdEmpresa = BU.IdEmpresa\r\n" + 
				"INNER JOIN ojos O ON O.idOjo = OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n" + 
				"INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa \r\n" + 
				"WHERE  ES.AlmacenaPallet=1 AND OT.IdEmpresa="+idEmpresa+" AND o.idOjo NOT IN ('1') AND BC.idArticulo IN ("+inns+") "+
				" UNION ALL "+
				"SELECT OT.cantidad,OT.idArticulo, OT.idArticulo 'idBulto', O.idRecorrido\r\n" + 
				"from ojostienenarticulos OT \r\n" + 
				"INNER JOIN ojos O ON O.idOjo = OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n" + 
				"INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa \r\n" + 
				"WHERE ES.AlmacenaPallet=1 AND OT.IdEmpresa="+idEmpresa+" AND o.idOjo NOT IN ('1') AND OT.idArticulo IN ("+inns+") \r\n" + 
				"ORDER BY idArticulo, cantidad DESC;";
		
		
		System.out.println(qArtsEnPallet);
		
		List <DataIDDescripcion> artsEnPallet = darListaDataIdDescripcionBConsulMYSQL(qArtsEnPallet);
		List <List <DataIDDescripcion> > listaArts = new ArrayList<>();
		List <DataIDDescripcion> current = null;
		String currentArt = "";
		pri = true;
		
		
	//	if(artsEnPallet != null) {  //////////
		for (DataIDDescripcion aep : artsEnPallet) 
		{
			try
			{
				if(pri)
				{
					pri = false;
					current = new ArrayList<>();
					current.add(aep);
				}
				else if (aep.getDescripcion().equals(currentArt))
				{
					current.add(aep);
				}
				else
				{
					listaArts.add(current);
					current = new ArrayList<>();
					current.add(aep);
				}
				currentArt = aep.getDescripcion();
			}
			catch (Exception e) 
			{
				
			}
			
		}
	//	}
		
		listaArts.add(current);
		for (List <DataIDDescripcion> lis : listaArts) 
		{
			try
			{
				int remanente = 0;
				pri=true;
				DataIDDescripcion in = null;
				for (DataIDDescripcion d : lis) 
				{
					try
					{
						 
						if(pri)
						{
							in = artCant.get(d.getDescripcion());
							pri = false;
							remanente = in.getId();
						}
						
						if(in.getId()<=d.getId())
						{
							//messi el bulto
							d.setIdB(in.getId());//id es la cantidad del bulto y IDb es la cantidad que preciso
							d.setDescripcionC(in.getDescripcionB());
							retorno.add(d);
							remanente = remanente-d.getId();
						}
						if(remanente<=0)
						{
							break;
						}
						
			
								
					}
					catch (Exception e) 
					{
						
					}
					
				}
				if(remanente>0)//si hay remanentes voy a agregar los bultos de a uno
				{
					pri = true;
					for (DataIDDescripcion d : lis) 
					{
						try
						{
							 
							if(pri)
							{
								in = artCant.get(d.getDescripcion());
								pri = false;
								remanente = in.getId();
							}
							
							d.setIdB(in.getId());//id es la cantidad del bulto y IDb es la cantidad que preciso
							d.setDescripcionC(in.getDescripcionB());
							retorno.add(d);
							remanente = remanente-d.getId();
							
							if(remanente<=0)
							{
								break;
							}
							
				
									
						}
						catch (Exception e) 
						{
							
						}
						
					}
					
				}
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		
		
		
		
		// TODO Auto-generated method stub
		return retorno;
	}


	public Hashtable<String, DataIDDescripcion> darOjosArti(String inns,int idEmpresa, int pallet) 
	{
		
		String q = "SELECT  OT.idArticulo, ES.Descripcion, O.idEstante 'modulo', O.idModulo 'estante', O.idOjo, O.idRecorrido \r\n" + 
				"FROM \r\n" + 
				"ojostienenarticulos OT\r\n" + 
				"INNER JOIN ojos O ON O.idOjo = OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n" + 
				"INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa \r\n" + 
				"WHERE  ES.AlmacenaPallet="+pallet+" AND OT.IdEmpresa="+idEmpresa+" AND OT.idArticulo IN ("+inns+") ";
		
		
		Connection cone;
		try {
			cone = econ.getConnection();
			
			return econ.darOjosArti(q);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}


	public Hashtable<String, String> darDescripcionArticulos(String inns, int idEmpresa) 
	{

		String q = "SELECT a.idArticulo, a.Descripcion FROM articulos a WHERE a.idArticulo IN ("+inns+") AND a.IdEmpresa="+idEmpresa;
		
		
		Connection cone;
		try {
			cone = econ.getConnection();
			
			return econ.darDescripcionArticulos(q);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void BajarReposicionPicking(String idArticulo,int cantidad, int idTarea) {
		String q = "UPDATE tarea_movimiento_articulos SET cantidadBajada = cantidadBajada + "+cantidad+""
				+ " WHERE idtarea = "+idTarea+" and idarticulo='"+idArticulo+"';";
		
		
		Connection cone;
		try {
			cone = eper.getConnection();
			
			eper.persistir(q);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void MoverReposicionPicking(String idArticulo,int cantidad, int idTarea) {
		
		String q = "UPDATE tarea_movimiento_articulos SET cantidadMovida = cantidadMovida + "+cantidad+" , terminado = if(cantidadbajada=cantidadMovida,1,0)"
				+ " WHERE idtarea = "+idTarea+" and idarticulo='"+idArticulo+"';";
		
		
		Connection cone;
		try {
			cone = eper.getConnection();
			
			eper.persistir(q);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MovimientoAlmacen> darMovimientosRepo(List<DataIDDescripcion> articulosMover, int idEmpresa, int idUsuario) 
	{
		List<MovimientoAlmacen>  movimientos = new ArrayList<>();
		String artsIn = "";
		boolean pri = true;
		for (DataIDDescripcion a : articulosMover) 
		{
			if(pri)
			{
				artsIn+="'"+a.getDescripcion()+"'";
				pri=false;
			}
			else
			{
				artsIn+=",'"+a.getDescripcion()+"'";
			}
		}
		String q = "SELECT BC.cantidad,BC.idArticulo, BC.idBulto, O.idOjo \r\n" + 
				" FROM bulto_contenido BC \r\n" + 
				" INNER JOIN bulto BU ON BU.idBulto = BC.idBulto AND BC.IdEmpresa = BU.IdEmpresa\r\n" + 
				" INNER JOIN ojostienenarticulos OT ON OT.idArticulo = BU.idBulto AND OT.IdEmpresa = BU.IdEmpresa\r\n" + 
				" INNER JOIN ojos O ON O.idOjo = OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n" + 
				" INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa \r\n" + 
				" WHERE  ES.AlmacenaPallet=1 AND OT.IdEmpresa="+idEmpresa+" AND BC.idArticulo IN ("+artsIn+") ORDER BY BC.idArticulo, BC.cantidad,O.idRecorrido;\r\n" + 
				"";
		Connection cone;
		try 
		{
			cone = econ.getConnection();
			List<DataIDDescripcion> bultosPosibles =econ.darBultosPosibleRepo(q);
			
			
			for (DataIDDescripcion a : articulosMover) 
			{
				
				int cantidadAmover = a.getId();
				int cantidadPendiente=cantidadAmover;
				String ojoOrigen = a.getDescripcionB();
				String ojoDestino = a.getDescripcionC();
				String articulo = a.getDescripcion();
				
				
				
				
				for (DataIDDescripcion b : bultosPosibles) 
				{
					int cantidadBulto = b.getId();
					String articuloInBulto = b.getDescripcion();
					String idBulto = b.getDescripcionB();
					String ojoInBulto = b.getDescripcionC();
					
					if(articuloInBulto.equals(articulo) && ojoOrigen.equals(ojoInBulto))//mismo SKU mismo Ojo
					{
						/**************cantidad exacta del bulto**************/
						double resto =   cantidadPendiente % cantidadBulto;
						
						
						if(cantidadPendiente==cantidadBulto || resto==0.0)
						{
							
							List<elementoPicking> aMover = new ArrayList<>();
							
							elementoPicking ep = new elementoPicking(cantidadBulto, articulo, 0);
							ep.setIdBulto(idBulto);
							aMover.add(ep);
							MovimientoAlmacen mov = new MovimientoAlmacen(idBulto, ojoDestino, idUsuario, aMover);
							movimientos.add(mov);
							
							
							cantidadPendiente -= cantidadBulto;
							
						}
						
					}
				
					if(cantidadPendiente<=0)
					{
						break;
					}
				
				}
				if(cantidadPendiente>0)
				{

					for (DataIDDescripcion b : bultosPosibles) 
					{
						int cantidadBulto = b.getId();
						String articuloInBulto = b.getDescripcion();
						String idBulto = b.getDescripcionB();
						String ojoInBulto = b.getDescripcionC();
						
						if(articuloInBulto.equals(articulo) && ojoOrigen.equals(ojoInBulto))//mismo SKU mismo Ojo
						{
							/**************cantidad exacta del bulto**************/
							if(cantidadPendiente>=cantidadBulto)
							{
								List<elementoPicking> aMover = new ArrayList<>();
								
								elementoPicking ep = new elementoPicking(cantidadBulto, articulo, 0);
								ep.setIdBulto(idBulto);
								aMover.add(ep);
								MovimientoAlmacen mov = new MovimientoAlmacen(idBulto, ojoDestino, idUsuario, aMover);
								movimientos.add(mov);
								
								
								cantidadPendiente -= cantidadBulto;
								
							}
							
						}
					
						if(cantidadPendiente<=0)
						{
							break;
						}
					
					}
				}
				
				if(cantidadPendiente>0) //con lo que se intenta mover del ojo que vino en el XLS no alcanza o lo esta sacando de otro ojo
				{
					for (DataIDDescripcion b : bultosPosibles) 
					{
						int cantidadBulto = b.getId();
						String articuloInBulto = b.getDescripcion();
						String idBulto = b.getDescripcionB();
						
						
						if(articuloInBulto.equals(articulo) && cantidadPendiente>=cantidadBulto)//SOLO MISMO OJO
						{
								List<elementoPicking> aMover = new ArrayList<>();
								
								elementoPicking ep = new elementoPicking(cantidadBulto, articulo, 0);
								ep.setIdBulto(idBulto);
								aMover.add(ep);
								MovimientoAlmacen mov = new MovimientoAlmacen(idBulto, ojoDestino, idUsuario, aMover);
								movimientos.add(mov);
								
								cantidadPendiente = cantidadAmover-cantidadBulto;
							
						}
					
						if(cantidadPendiente<=0)
						{
							break;
						}
					
					}
				}
				
			}
				
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return movimientos;
		}
		return movimientos;
		
		
		
	}


	public List<ConteoOjo> darListaConteoOjo(String fechaI, String fechaF, int idEmpresa, String estanteriasIn, String ojosIn) 
	{
		
		String qEstanteria = "";

		if(!estanteriasIn.equals(""))
		{
			qEstanteria= " AND ES.idEstanteria IN ("+estanteriasIn+") ";
		}
		String inn = "";
		
		String ojosInq = "";
		
		if(!ojosIn.equals(""))
		{
			ojosInq = " AND OJ.idOjo in ("+ojosIn+") ";
		}
		
		String filtros = ""
				+ "SELECT DISTINCT oc.idOjo, oc.idEmpresa \r\n" + 
				" FROM ojostienenarticulos_conteo oc \r\n" + 
				" INNER JOIN ojos OJ ON OJ.idOjo = oc.idOjo AND oc.idEmpresa = OJ.IdEmpresa\r\n" + 
				" INNER JOIN estanterias ES ON ES.idEstanteria = OJ.idEstanteria AND ES.idEmpresa = OJ.IdEmpresa\r\n" + 
				" WHERE actualizado \r\n"+
				" BETWEEN '"+fechaI+"' AND '"+fechaF+"'\r\n" +
				ojosInq+
				qEstanteria;
		
		
		String q = ""
				+ "SELECT ES.Descripcion, O.idEstante 'modulo', O.idModulo 'estante' , O.idOjo, OT.idArticulo,OT.Cantidad 'cantidad_ojo',ifnull(OTC.Cantidad,0) 'Cantidad_contada', ifnull(OTC.Actualizado,'') 'Fecha conteo', OT.Actualizado 'fecha Act ojo',IFNULL (CONCAT(US.Nombre,' ',US.Apellido),'') 'usuario', AR.descripcion \r\n" + 
				"\r\n" + 
				" from ojos O INNER JOIN \r\n" + 
				" (\r\n" + 
				filtros+
				" ) \r\n" + 
				" OC\r\n" + 
				" ON OC.idOjo = O.idOjo AND OC.idEmpresa = O.idempresa \r\n" + 
				" INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa\r\n " + 
				" inner JOIN ojostienenarticulos OT ON OT.idOjo = O.idOjo AND OT.IdEmpresa = O.IdEmpresa\r\n " +
				" inner JOIN Articulos AR ON AR.idArticulo = OT.idArticulo AND AR.IdEmpresa = OT.IdEmpresa\r\n " +
				" LEFT OUTER JOIN  ojostienenarticulos_conteo OTC ON OTC.idOjo = O.idOjo AND OTC.idArticulo = OT.idArticulo \r\n " + 
				" LEFT OUTER JOIN usuarios US ON US.idUsuario = OTC.usuarioUpdate AND OTC.IdEmpresa = US.idEmpresa\r\n " + 
				" WHERE O.IdEmpresa = "+idEmpresa+" "+
				" UNION ALL\r\n " + 
				" \r\n" + 
				" SELECT ES.Descripcion, O.idEstante 'modulo', O.idModulo 'estante' , OTC.idOjo, OTC.idArticulo,IFNULL(OT.Cantidad,0) 'cantidad_ojo',OTC.Cantidad 'Cantidad_contada', OTC.Actualizado  'Fecha conteo', ifnull(OT.Actualizado,'') 'fecha Act ojo',  IFNULL (CONCAT(US.Nombre,' ',US.Apellido),'') 'usuario', AR.descripcion   \r\n" + 
				" from ojos O INNER JOIN \r\n" + 
				" (\r\n" + 
				filtros+ 
				" ) \r\n" + 
				" OC\r\n" + 
				" ON OC.idOjo = O.idOjo AND OC.idEmpresa = O.idempresa \r\n" + 
				" INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa\r\n" + 
				" JOIN  ojostienenarticulos_conteo OTC ON OTC.idOjo = O.idOjo AND O.IdEmpresa = OTC.IdEmpresa\r\n" +
				" inner JOIN Articulos AR ON AR.idArticulo = OTC.idArticulo AND AR.IdEmpresa = OTC.IdEmpresa\r\n " +
				" LEFT OUTER JOIN usuarios US ON US.idUsuario = OTC.usuarioUpdate AND OTC.IdEmpresa = US.idEmpresa\r\n" + 
				" LEFT outer JOIN ojostienenarticulos OT ON OT.idOjo = O.idOjo AND OT.IdEmpresa = O.IdEmpresa AND OTC.idArticulo = OT.idArticulo \r\n" + 
				" WHERE OT.idOjo IS NULL AND O.IdEmpresa = "+idEmpresa+"\r\n" + 
				"";
		
		
		

		try {
			System.out.println(q);
			return econ.darListaConteoOjo(q);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	public List<DataIDDescripcion> darOjosConteados(int idEmpresa) 
	{
		String q = "SELECT  CONCAT(OJ.idEstanteria,OJ.idEstante,OJ.idModulo) id, oc.idOjo\r\n" + 
				" FROM ojostienenarticulos_conteo oc \r\n" + 
				" INNER JOIN ojos OJ ON OJ.idOjo = oc.idOjo AND oc.idEmpresa = OJ.IdEmpresa\r\n" + 
				" INNER JOIN estanterias ES ON ES.idEstanteria = OJ.idEstanteria AND ES.idEmpresa = OJ.IdEmpresa"+
				" WHERE oc.idEmpresa="+idEmpresa;
		
		
		return darListaDataIdDescripcionMYSQLConsulta(q);
	}


	public List<DataIDDescripcion> darEmpaqueFoto(String inns, int idEmpresa) 
	{
		String q = "SELECT packing,imagen,Descripcion  FROM articulos WHERE idArticulo IN ("+inns+") AND idempresa="+idEmpresa+";";
		List<DataIDDescripcion> retorno = darListaDataIdDescripcionMYSQLConsulta(q); 
		retorno.remove(0);
		return retorno;
	}


	public void liberarReserva(DataLineaRepo dl, String nuevoOjo, int idEmpresa) 
	{
		String qUp = "UPDATE ojostienenarticulos SET Reservada = if(Reservada-"+(dl.getSolicitada()+dl.getPicked()) +" >=0 ,Reservada-"+(dl.getSolicitada()+dl.getPicked()) +", 0) WHERE idOjo = '"+dl.getCubi()+"'";
		String qUp2 = "UPDATE ojostienenarticulos SET Reservada = Reservada+"+(dl.getSolicitada()+dl.getPicked())+" WHERE idOjo = '"+nuevoOjo+"'";
		String qUp3 = "update reposicion_articulos_ojos set cUbicacion='"+nuevoOjo+"' WHERE idPicking = "+dl.getDocumento()+" AND cUbicacion='"+dl.getCubi()+"' AND idArticulo='"+dl.getIdArticulo()+"' and destino = "+dl.getIdDepDestino()+" AND pedido = "+dl.getPedido()+" AND idEmpresa="+idEmpresa+" ";
		
		
		try 
		{
			getEper().persistir(qUp);
			getEper().persistir(qUp2);
			getEper().persistir(qUp3);
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		
	}
	
	public int getPesoPedido(Long idPedido) {
		
		
		String q = "SELECT SUM(SKU_weight) FROM producteca_ecom_det_ped WHERE OPERATIONID = "+idPedido+";";
		
		
		Connection cone;
		int salida = 0;
		try {
			salida = MSSQL.darPesoPedido(q);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}


	public void noEncontradoRepo(ReposicionPicking r, int idEmpresa) 
	{
		String q = "UPDATE `tarea_movimiento_articulos` SET `terminado`='1' WHERE  `idTarea`="+r.getIdTarea()+" AND `idArticulo`='"+r.getIdArticulo()+"' AND idEmpresa="+idEmpresa+";";
		try 
		{
			getEper().persistir(q);
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		
		
	}
	
	public Hashtable<String, bulto> BultosAbiertos2(int idEmpresa){
		@SuppressWarnings("unused") Connection cone;
		try {
			cone = econ.getConnection();
			
			String consulta = "SELECT B.idBulto,B.descripcion,B.cerrado,B.fecha,B.usuario,B.ancho,B.alto,B.prof,B.peso,B.fragil,"
					+ "B.destino,B.fechaClose,B.usuarioClose,B.tipoMercaderia,B.equipo,B.IdEmpresa,B.remision_al_cerrar,B.posSort,B.pedido,"
					+ "B.numerador,D.alias from bulto B "
					+ "INNER JOIN depositos D ON B.destino = D.idDeposito AND B.IdEmpresa = D.IdEmpresa "
					+ "where B.IdEmpresa="+idEmpresa+" and fechaclose between '2021-07-02 09:09:29' AND '2021-07-05 13:01:51' and cerrado=1"; 
			String consulta2 = "SELECT b.idbulto ,BC.* from bulto_contenido BC INNER JOIN bulto B ON BC.idBulto = B.idBulto AND BC.IdEmpresa = B.IdEmpresa where BC.idBulto IN (@@) AND  BC.IdEmpresa="+idEmpresa+"";
			String consulta3 = "select b.idbulto ,BC.* from bulto_caracteristica BC INNER JOIN bulto B ON BC.idBulto = B.idBulto AND BC.IdEmpresa = B.IdEmpresa where BC.idBulto IN (@@) AND  BC.IdEmpresa="+idEmpresa+"";
			String consulta4 = "select b.idbulto ,BC.* from bulto_remito BC INNER JOIN bulto B ON BC.idBulto = B.idBulto AND BC.IdEmpresa = B.IdEmpresa where BC.idBulto IN (@@) AND  BC.IdEmpresa="+idEmpresa+"";

			return econ.DarBultosAbiertos2(consulta, consulta2,consulta3,consulta4,idEmpresa);


		} catch (Exception e) {
			e.printStackTrace();
			return new Hashtable<>();
		}
	}

	public List<DataOjoArticuloCantidad> encuentraCantidadReservada(String idOjo, int idEmpresa) {
		List<DataOjoArticuloCantidad> resultado = new ArrayList<>();
		String consulta = "SELECT ota.idOjo,ota.idArticulo,ota.Cantidad,ota.Reservada,a.descripcion FROM ojostienenarticulos ota \r\n"
				+ "INNER JOIN articulos a ON a.idArticulo = ota.idArticulo AND a.IdEmpresa = ota.IdEmpresa\r\n"
				+ "WHERE ota.idempresa="+idEmpresa+" AND ota.idojo='"+idOjo+"' AND ota.Reservada > 0 ;";
		try {
			@SuppressWarnings("unused") Connection cone;
			cone = econ.getConnection();
			resultado = econ.darReservas(consulta);
		} catch(Exception e) {
			e.printStackTrace();
			return resultado;
		}
		return resultado;

			
	}


	public void quitarArticuloReservadoOjo(String ojoReservado, String articuloReservado, int idEmpresa) {
		String consulta = "UPDATE ojostienenarticulos SET Reservada=0 WHERE idempresa="+idEmpresa+" AND idojo='"+ojoReservado+"' AND idarticulo='"+articuloReservado+"'; ";
		try 
		{
			getEper().persistir(consulta);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<Integer> darPromedioTiempoEsperaDeposito(Integer idEmpresa, Integer tipo) {
		
		List<Integer> ret = new ArrayList<>();
		
		String resultHistorico;
		String resultUltimoMes;
		String resultUltimaSemana;
		
		String bdd = "select truncate(avg(-timestampdiff(hour, e.Fecha, rs.Fecha)), 0) \r\n"
		+ "from reposicion_articulos ra \r\n"
		+ "join reposicion_sincronizacion rs on ra.IdSincronizacion =rs.Id and ra.IdEmpresa =rs.IdEmpresa \r\n"
		+ "join envio_encuentra_documento_lineas eedl on eedl.idArticulo = ra.seccion and eedl.IdEmpresa = ra.idEmpresa\r\n"
		+ "join envio_encuentra_documento d on eedl.idDocumento =d.idDocumento and eedl.idEmpresa = d.IdEmpresa \r\n"
		+ "join envio e on e.idEnvio = d.IdEnvio and d.IdEmpresa =e.IdEmpresa \r\n"
		+ "where ra.idEmpresa=%s and ra.idTipo = %s %s";
		
		String filtroMes=" and e.Fecha> DATE_SUB(sysdate(), INTERVAL 30 DAY) ";
		String filtroSemana=" and e.Fecha> DATE_SUB(sysdate(), INTERVAL 7 DAY) ";

		String queryHistorico = String.format(bdd, idEmpresa, tipo, "");
		String queryUltimoMes = String.format(bdd, idEmpresa, tipo, filtroMes);
		String queryUltimaSemana = String.format(bdd, idEmpresa, tipo, filtroSemana);
				
		try 
		{
			resultHistorico = getEper().Consulta(queryHistorico);
			resultUltimoMes = getEper().Consulta(queryUltimoMes);
			resultUltimaSemana = getEper().Consulta(queryUltimaSemana);
			
			ret.add(Integer.parseInt(resultHistorico));
			ret.add(Integer.parseInt(resultUltimoMes));
			ret.add(Integer.parseInt(resultUltimaSemana));
			
			return ret;
		} 
		catch (Exception e) 
		{
			return new ArrayList<>();
		}
	}


	public List<DataIDDescripcion> remitoEc(int idPicking, int idEmpresa) {
			List<DataIDDescripcion> resultado = new ArrayList<>();
			String consulta = "SELECT picked,idarticulo,seccion FROM reposicion_articulos WHERE idempresa="+idEmpresa+" AND idpicking="+idPicking+"";
			try {
				@SuppressWarnings("unused") Connection cone;
				cone = econ.getConnection();
				resultado = econ.darRemitoEc(consulta);
			} catch(Exception e) {
				e.printStackTrace();
				return resultado;
			}
			return resultado;
	}
	
	public List<Presentaciones> darListaStockPrecio(String articulo, int idEmpresa){
		
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		
		Views views = new Views();
		
//		if(desde==null || desde.isEmpty()) 
//			desde="0";
//		
//		if(total==null || total.isEmpty()) 
//			total="100";
		
		String query = queryInformeStockPrecio(idEmpresa, articulo);
		
		Map<String, DataIDDescripcion> stocks = views.darStock(query);
		
		if(stocks==null || stocks.isEmpty())
			return new ArrayList<>();
		
		StringBuilder idsArticulo = new StringBuilder();
		boolean primero = true;
		for(String key : stocks.keySet())
		 {
			 if(primero)
			 {
				 primero = false;
				 idsArticulo.append("'"+key+"'");
			 }
			 else
				 idsArticulo.append(",'"+key+"'");
		}
		
		Map<String, Double> precios = api.getPrecios(idsArticulo.toString(), idEmpresa); 
		
		if(precios==null || precios.isEmpty())
			return new ArrayList<>();
		
		for(Map.Entry<String, Double> entry : precios.entrySet()) {
			try
			{
				DataIDDescripcion artStk = stocks.get(entry.getKey());
				artStk.setIdD(entry.getValue());
				stocks.put(entry.getKey(), artStk);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
		}
		
		List<DataIDDescripcion> lista = new ArrayList<>(stocks.values());
		
		Productos[] productos = api.getProductos(lista, idEmpresa);
		
		List<Presentaciones> presentaciones = new ArrayList<>();

		if(productos.length==0) {
			return presentaciones;
		}
		
		for(Productos producto : productos)
			for(Variantes variante : producto.getVariantes())
				for(Presentaciones presentacion : variante.getPresentaciones())
					presentaciones.add(presentacion);
		return presentaciones;
	}
	
	private String queryInformeStockPrecio(int idEmpresa, String articulo) 
	{
		
		
		String qArticulo = (articulo==null || articulo.isEmpty())? "" : " where AD.id like '"+articulo+"%' ";
		String q = "";
		switch (idEmpresa) 
		{
		case EMPRESA_BAS:
			
			q="SELECT IFNULL(if(t00.stk<0,0,t00.stk),0), id, descripcion \r\n"
					+ " FROM \r\n"
					+ " (\r\n"
					+ " SELECT AD.id,AD.descripcion\r\n"
					+ " FROM art_descripcion AD\r\n"
					+ " INNER JOIN \r\n"
					+ " (\r\n"
					+ " SELECT SUBSTR(id,1,CHARACTER_LENGTH(a.id)-3) subid \r\n"
					+ " from art_descripcion a WHERE idEmpresa = "+idEmpresa+" GROUP BY SUBSTR(id,1,CHARACTER_LENGTH(a.id)-3) \r\n"
					+ " ORDER BY id \r\n"
//					+ " LIMIT "+desde+","+total+"\r\n"
					+ " )\r\n"
					+ " TS\r\n"
					+ " ON TS.subid=SUBSTR(AD.id,1,CHARACTER_LENGTH(AD.id)-3) \r\n"
					+ " ) AD\r\n"
					+ " LEFT OUTER JOIN\r\n"
					+ " (\r\n"
					+ " SELECT SUM(t0.cant) stk,t0.idArticulo\r\n"
					+ " FROM (\r\n"
					+ " SELECT idArticulo, SUM(cantidad)*-1 cant\r\n"
					+ " FROM reposicion_articulos ra\r\n"
					+ " WHERE ra.estado in (1,11,12) AND ra.idEmpresa= "+idEmpresa+"\r\n"
					+ " GROUP BY ra.idArticulo UNION ALL\r\n"
					+ " SELECT BC.idArticulo, SUM(BC.cantidad) cant\r\n"
					+ " FROM bulto B\r\n"
					+ " INNER JOIN bulto_contenido BC ON BC.idBulto = B.idBulto AND BC.IdEmpresa = B.idEmpresa\r\n"
					+ " INNER JOIN ojostienenarticulos OT ON B.idBulto=OT.idArticulo AND B.idEmpresa=OT.IdEmpresa\r\n"
					+ " INNER JOIN ojos O ON O.idOjo=OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n"
					+ " INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa\r\n"
					+ " WHERE ES.idUso = 1 AND ES.idEmpresa= "+idEmpresa+"\r\n"
					+ " GROUP BY BC.idArticulo UNION ALL\r\n"
					+ " SELECT OT.idArticulo, SUM(OT.cantidad) cant\r\n"
					+ " FROM ojostienenarticulos OT\r\n"
					+ " INNER JOIN ojos O ON O.idOjo=OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n"
					+ " INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa\r\n"
					+ " WHERE ES.idUso = 1 AND ES.idEmpresa= "+idEmpresa+"\r\n"
					+ " GROUP BY OT.idArticulo UNION ALL\r\n"
					+ " SELECT OT.idArticulo, SUM(OT.cantidad) cant\r\n"
					+ " FROM ojostienenarticulos OT\r\n"
					+ " INNER JOIN ojos O ON O.idOjo=OT.idOjo AND O.IdEmpresa = OT.IdEmpresa\r\n"
					+ " INNER JOIN estanterias ES ON ES.idEstanteria = O.idEstanteria AND ES.idEmpresa = O.IdEmpresa\r\n"
					+ " WHERE ES.idEstanteria IN (0,2) AND ES.idEmpresa= "+idEmpresa+"\r\n"
					+ " GROUP BY OT.idArticulo) t0\r\n"
					+ " GROUP BY t0.idArticulo\r\n"
					+ " ) t00\r\n"
					+ " ON t00.idArticulo = AD.id\r\n"
					+ qArticulo
					+ " ORDER BY AD.id";
		 	System.out.println(q);
			break;

		default:
			break;
		}
		return q;
	}
	
	public List<String> darUbicacionesArticulo(String idArticulo, int idEmpresa) {
		String query = String.format("select o.idOjo from ojostienenarticulos o where o.idArticulo = '%s' and o.idEmpresa=%s and o.idOjo not in ('0', '1');", idArticulo, idEmpresa);
		return getEcon().BuscarArticulo(query);
	}
	
	public List<DataIDDescripcion> darReporteRecepcionCantidad(int idEmpresa, String idArticulo, String fechaDesde, String fechaHasta){
		String query = 
				" select m.idArticulo, a.Descripcion, sum(m.cantidad) cantidad "
				+ " from movsarticulos m "
				+ " join articulos a "
				+ " on m.idArticulo = a.idArticulo "
				+ " where m.IdTipo = 'REC' "
				+ " and m.IdEmpresa = %s"
				+ " and m.idArticulo = '%s' "
				+ " and m.update between '%s' and '%s' "
				+ " group by m.idArticulo "
				+ " order by cantidad desc;";
		
		String consulta = String.format(query, idEmpresa, idArticulo, fechaDesde, fechaHasta);
		
		try {
			return econ.darReporteRecepcionCantidad(consulta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}

