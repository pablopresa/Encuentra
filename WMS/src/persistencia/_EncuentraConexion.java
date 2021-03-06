
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import beans.ReportObject;
import beans.TalleMatrizRepo;
import beans.Tareas;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.jsonEstadoMP;
import beans.encuentra.ArticuloConteo;
import beans.encuentra.ArticuloDistribucion;
import beans.encuentra.ConteoTiendas;
import beans.encuentra.DataArtMovS;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataDetallePacking;
import beans.encuentra.DataDocTipoEnvio;
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
import beans.encuentra.Distribucion;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.MovimientoMatrizDis;
import beans.encuentra.Ojo;
import beans.encuentra.ParametrosPreparacion;
import beans.encuentra.RecepcionExpedicion;
import beans.encuentra.Ruta;
import beans.encuentra.RutaDeposito;
import beans.encuentra.SKUType;
import beans.encuentra.Sector;
import beans.encuentra.Tarea;
import beans.encuentra.Tarjeta;
import beans.encuentra.TaskType;
import beans.encuentra.TipoSector;
import beans.encuentra.Transporte;
import beans.encuentra.UsoEstanteria;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
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
import clientesVisual_Store.Std.clienteWSVS_new.OrdenVenta;
import clientesVisual_Store.Std.clienteWSVS_new.OrdenVentaLinea;
import clientesVisual_Store.Std.clienteWSVS_new.WSCommunicate;
import dataTypes.ArticuloPedido;
import dataTypes.Bulto_ReposicionPicking;
import dataTypes.Cantidades;
import dataTypes.ColorDetalleRecepcion;
import dataTypes.DTO_Articulo;
import dataTypes.DataArtBarraCant;
import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloCantBarra;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataClasificacionEntregaArti;
import dataTypes.DataDescDescripcion;
import dataTypes.DataDetallePedido;
import dataTypes.DataDetallePedidoPrint;
import dataTypes.DataDetallePedidoPrintArt;
import dataTypes.DataDetalleRecepcion;
import dataTypes.DataDetalleTarea;
import dataTypes.DataDocVisual;
import dataTypes.DataEcommerceReporte;
import dataTypes.DataEcommerceReporteLista;
import dataTypes.DataEcommerce_LogPedido;
import dataTypes.DataEcommerce_canales_envio;
import dataTypes.DataEntregaVerificada;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import dataTypes.DataIDIDID;
import dataTypes.DataIndicador;
import dataTypes.DataIndicadorPicking;
import dataTypes.DataIndicadorPickingLinea;
import dataTypes.DataPedidoArticuloEcommerceVerif;
import dataTypes.DataPedidoArticuloEcommerceVerif_ar;
import dataTypes.DataPedidoPickup;
import dataTypes.DataPosiblePedido;
import dataTypes.DataRecepcion;
import dataTypes.DataReglaReposicion;
import dataTypes.LeadTimes;
import dataTypes.PosicionesPosibles;
import dataTypes.ReposicionPicking;
import dataTypes.TareasPendientes;
import dataTypes.Ubicacion_ReposicionPicking;
import dataTypes.trackingPedido;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import helper.PropertiesHelper;
import logica.DTO_ArticuloCantidad;
import logica.Logica;
import logica.PedidosAtr;
import logica.Utilidad;
import main.Distribuidor;
import web.informes.ReporteTest;



public class _EncuentraConexion 
{

	private  Connection connection = null;
	public  _EncuentraConexion(){
		try {
			this.connection = getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private  Connection connect() throws Exception,InstantiationException, IllegalAccessException 
	{

		Connection conn = null;
		PropertiesHelper pH=new PropertiesHelper("conexion");
		pH.loadProperties();
		String puerto = pH.getValue("puerto");
		String bd = pH.getValue("bd");
		String usuario = pH.getValue("usuario");
		String password = pH.getValue("password");
		String servidor = pH.getValue("servidor");
		String prefijoURL=pH.getValue("prefijoURL");
		String url = prefijoURL + servidor + ":" + puerto + "/" + bd;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url +"?autoReconnect=true", usuario, password);

		} catch (SQLException ex) 
		{
			ex.printStackTrace();
			throw new Exception(
					"Hubo un problema al intentar conectarse con la base de datos "
							+ ex.getMessage());
		} catch (ClassNotFoundException ex) {
			throw new Exception(
					"Hubo un problema al intentar conectarse con la base de datos2 "
							+ ex.getMessage());
		}
		return conn;
	}

	public  Connection getConnection() throws Exception {
		if (connection == null) {
			connection = connect();
		}
		return connection;
	}

	public  void desconectar(ResultSet rs,PreparedStatement ps,Statement st) throws Exception 
	{
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {  e.printStackTrace(); /* ignored */}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) { e.printStackTrace();/* ignored */}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {  e.printStackTrace(); /* ignored */}
		}
		if (this.connection != null) {
			try {
				this.connection.close();
				this.connection = null;
			} catch (SQLException e) { e.printStackTrace();/* ignored */}
		}


	}

	public  Usuario login(String consulta) 
	{
		Usuario u = new Usuario();
		try 
		{
			Statement s = connection.createStatement();
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next()) 
			{

				int numero = rs.getInt(1);
				String nombre = rs.getString(4) +" "+rs.getString(5);
				int perfil = rs.getInt(6);
				String nombreU = rs.getString(3);

				u.setNumero(numero);
				u.setNick(nombre);
				u.setPerfil(perfil);
				u.setNombre(nombreU);
				u.setMail(rs.getString(7));
				u.setGrupo(rs.getInt(8));
				u.setDeposito(rs.getString(10));
				u.setIdEmpresa(rs.getInt(11));
				u.setIdEquipo(rs.getInt(12));
				u.setEquipo_trabajo(rs.getInt(13));

			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return u;
	}

	public  List<TipoSector> darTiposSector(String consulta) 
	{
		List<TipoSector> sectores = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				TipoSector t = new TipoSector();
				t.setIdTipo(rs.getInt(1));
				t.setDescripcion(rs.getString(2));
				t.setAncho(rs.getInt(3));
				t.setAlto(rs.getInt(4));
				t.setProfundidad(rs.getInt(5));

				sectores.add(t);

			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return sectores;
	}
	public  List<DataArticulo> darArticulos(String consulta)
	{
		List<DataArticulo> articulos = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataArticulo t = new DataArticulo();
				t.setId(rs.getString(1));
				t.setDescripcion(rs.getString(2));
				t.setAnchoCaja(rs.getInt(3));
				t.setAltoCaja(rs.getInt(4));
				t.setProfCaja(rs.getInt(5));
				t.setCantidad(rs.getInt(6));
				t.setIdTypeSKU(rs.getInt(7));
				t.setTypeSKU(rs.getString(8));
				articulos.add(t);

			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return articulos;
	}
	public  List<SKUType> darTiposSKU(String consulta){
		List<SKUType> skutypes = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				SKUType type = new SKUType();
				type.setId(rs.getInt(1));
				type.setDescripcion(rs.getString(2));

				skutypes.add(type);

			}		
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return skutypes;
	}
	public  List<DataRecepcion> darRecepciones(String consulta, boolean ok){
		List<DataRecepcion> recepciones = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataRecepcion rec = new DataRecepcion();
				rec.setId(rs.getInt(1));
				rec.setAgenda(rs.getString(3));
				rec.setCantidadEsperada(rs.getInt(4));
				rec.setCantidadRecepcionada(rs.getInt(5));
				DataDescDescripcion proveedor=new DataDescDescripcion();
				proveedor.setDescripcion(rs.getString(7));
				proveedor.setId(rs.getString(8));
				rec.setProveedor(proveedor);
				rec.setVehiculo(rs.getString(6));
				rec.setIdEstado(rs.getInt(2));
				try
				{
					rec.setCantidadContada(rs.getInt(11));


				}
				catch (Exception e) 
				{
					rec.setCantidadContada(0);
				}

				String tipo = rs.getString(10);
				boolean importa = false;
				if(tipo.equals("18"))
				{
					importa = true;
				}

				rec.setImporta(importa);
				recepciones.add(rec);

			}		
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return recepciones;
	}


	public  List<Sector> darSectores(String consulta) 
	{
		List<Sector> sectores = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				Sector r = new Sector();

				r.setId(rs.getInt(1));
				r.setDescripcion(rs.getString(2));
				r.setPiso(rs.getInt(3));
				r.setTipo(rs.getInt(4));
				r.setEstantes(rs.getInt(5));
				r.setModulos(rs.getInt(6));
				r.setSectorGlb(rs.getInt(7));
				r.setColor(rs.getString(8));
				r.setDeposito(rs.getInt(9));
				r.setUso(rs.getInt(10));

				sectores.add(r);

			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return sectores;
	}

	public  List<DepositoAdmin> darDepositos(String consulta) 
	{
		List<DepositoAdmin> depositos = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DepositoAdmin d = new DepositoAdmin();

				d.setId(rs.getInt(1));
				d.setNombre(rs.getString(2));
				d.setDireccion(rs.getString(3));
				d.setLogin(rs.getInt(4));

				
				try
				{
					ParametrosPreparacion dp = new ParametrosPreparacion();
					dp.setPrioridad(rs.getInt(5));
					dp.setPickup(darBoolfromInt(rs.getInt(6)));
					dp.setPreparaPickup(darBoolfromInt(rs.getInt(7)));
					dp.setPreparaDelivery(darBoolfromInt(rs.getInt(8)));
					dp.setPreparaEnvioCD(darBoolfromInt(rs.getInt(9)));
					dp.setIdGrupo(10);
					d.setParametros(dp);
					
					/*
					//cargo parametros del depo
					ParametrosPreparacion pp = new ParametrosPreparacion();
						pp.setPrioridad(rs.getInt(5));
						pp.setPreparaPickup(Utilidades.parseIntToBoolean(rs.getInt(6)));
						pp.setPreparaDelivery(Utilidades.parseIntToBoolean(rs.getInt(7)));
						pp.setPreparaEnvioCD(Utilidades.parseIntToBoolean(rs.getInt(8)));
						pp.setIdGrupo(rs.getInt(9));
						
					d.setParametros(pp);
					*/
					
				}
				catch (Exception e) 
				{
					
				}
				
				
				
				
				

	
			
				

				depositos.add(d);

			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return depositos;
	}
	private boolean darBoolfromInt(int in)
	{
		boolean retorno = false;
		
		if(in==1)
		{
			retorno = true;
		}
		
		return retorno;
	}
	
	public  DepositoAdmin BuscarDeposito(String consulta) 
	{
		DepositoAdmin d = new DepositoAdmin();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				d.setId(rs.getInt(1));
				d.setNombre(rs.getString(2));
				d.setDireccion(rs.getString(3));
				d.setLogin(rs.getInt(4));
				d.setCiudad(rs.getString(5));
				d.setDepartamento(rs.getString(6));
				d.setTelefono(rs.getString(7));
				d.setNroPuerta(rs.getInt(8));
				d.setLocal(rs.getInt(9));

			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return d;
	}
	
	public  Hashtable<Integer, DepositoAdmin> darDepositosHT(String consulta) 
	{
		Hashtable<Integer, DepositoAdmin> depositos = new Hashtable<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DepositoAdmin d = new DepositoAdmin();

				d.setId(rs.getInt(1));
				d.setNombre(rs.getString(2));
				d.setDireccion(rs.getString(3));
				d.setLogin(rs.getInt(4));


				depositos.put(d.getId(),d);

			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return depositos;
	}

	public  List<UsoEstanteria> darUsos(String consulta) 
	{
		List<UsoEstanteria> usos = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				UsoEstanteria u = new UsoEstanteria(rs.getInt(1), rs.getString(2), rs.getInt(3));	
				usos.add(u);
			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return usos;
	}

	public  boolean hayReg(String consulta) 
	{
		boolean hay = false;

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				hay = true;
				break;

			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			hay = false;
			e.printStackTrace();
		}

		return hay;
	}

	public  int darInt(String consulta) 
	{
		int retorno = 0;

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				retorno=rs.getInt(1);
				System.out.println(consulta);

			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
		}

		return retorno;
	}

	public  DataArticulo codArticulo (String consulta) 
	{
		DataArticulo a = new DataArticulo();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{

				a.setAltoCaja(rs.getInt(4));
				a.setAnchoCaja(rs.getInt(3));
				a.setDescripcion(rs.getString(2));
				a.setId(rs.getString(1));
				a.setProfCaja(rs.getInt(5));

				try
				{
					a.setCodBase(a.getId().substring(0, 9));
				}
				catch (Exception e) 
				{
					a.setCodBase(a.getId());
				}


			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return a;
	}

	public  DataArticulo codArticuloSinBase (String consulta) 
	{
		DataArticulo a = new DataArticulo();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{

				a.setAltoCaja(rs.getInt(4));
				a.setAnchoCaja(rs.getInt(3));
				a.setDescripcion(rs.getString(2));
				a.setId(rs.getString(1));
				a.setProfCaja(rs.getInt(5));

				a.setCodBase(a.getId());

			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return a;
	}


	public  List<DataPickingS> darPickingS (String consulta) 
	{
		List<DataPickingS> retorno = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				List<DataIDDescripcion>depositos = new ArrayList<>();
				String nombresDepo = rs.getString(2);
				String idsDepo = rs.getString(6);
				boolean clasificar;
				if(nombresDepo.contains(","))
				{
					// si una columna tiene comas asumo que la ultima (la de los ID) tambien
					String[]depos = nombresDepo.split(",");
					String[] idDepos = idsDepo.split(",");
					//clasificar = true;
					for (int i = 0; i < depos.length; i++) 
					{
						try
						{
							int idDepo = Integer.parseInt(idDepos[i]);
							String nom = depos[i];
							DataIDDescripcion dep = new DataIDDescripcion(idDepo, nom);
							depositos.add(dep);

						}
						catch (Exception e)
						{

						}
					}



				}
				else
				{
					//clasificar = false;
					try
					{
						int idDepo = Integer.parseInt(idsDepo);
						String nom = nombresDepo;
						DataIDDescripcion dep = new DataIDDescripcion(idDepo, nom);
						depositos.add(dep);
					}
					catch (Exception e)
					{

					}
				}

				DataPickingS a = new DataPickingS();
				a.setClasificar(rs.getInt(9)>1);
				a.setId(rs.getInt(1));
				a.setDestinos(depositos);
				a.setSolicitada(rs.getInt(3));
				a.setEncontrada(rs.getInt(4));
				a.setVerificada(rs.getInt(7));
				a.setRemitida(rs.getInt(5));

				try
				{
					a.setUsuarios(rs.getString(8));
				}
				catch (Exception e) {}

				retorno.add(a);

			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return retorno;
	}



	public  List<DataLineaListaTareasMob> darTareasMob (String consulta, Usuario usuario) 
	{
		List<DataLineaListaTareasMob> tareas = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataLineaListaTareasMob d = new DataLineaListaTareasMob();

				d.setMain(rs.getInt(1));
				d.setTarea(rs.getInt(2));
				int idTipo = rs.getInt(3);
				String descTipo = rs.getString(4);
				DataIDDescripcion tipo = new DataIDDescripcion(idTipo, descTipo);
				d.setTipo(tipo);
				d.setPorcentaje(rs.getInt(5));
				int idEstado = rs.getInt(6);
				String descEstado = rs.getString(7);
				DataIDDescripcion estado = new DataIDDescripcion(idEstado, descEstado);
				d.setDescripcion(rs.getString(8));

				d.setEstado(estado);
				d.setIdRepo(rs.getInt(10));
				d.setCantidad(rs.getInt(11));
				int minutosTranscurridos=darhoraTarea(rs.getInt(2), null);
				d.setTiempoTranscurrido(String.valueOf(minutosTranscurridos));
				d.setResolucion(rs.getInt(12));
				d.setResponsable(new DataIDDescripcion(rs.getInt(13),rs.getString(14)));
				if (tipo.getId() == 117 ){
					if (usuario.getPerfil() == 11 ) {
						tareas.add(d);
					}
				} else {
					tareas.add(d);
				}
			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return tareas;
	}	


	public  List<Tarea> darTareas (String consulta) 
	{
		List<Tarea> tareas = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				Tarea t = new Tarea();

				List<Usuario> usuarios = new ArrayList<>();
				Usuario us = new Usuario();
				t.setId(rs.getInt(1));
				DataIDDescripcion tipo = new DataIDDescripcion(0,rs.getString(2));
				t.setTipo(tipo);
				t.setFechaInicio(rs.getString(3));
				t.setResponsable(new DataIDDescripcion(rs.getInt(4),rs.getString(18)));
				t.setPorcentaje(rs.getInt(5));
				t.setEstado(new DataIDDescripcion(rs.getInt(6),rs.getString(7)));
				t.setObservacion(rs.getString(8));
				t.setFechaFin(rs.getString(9));
				t.setCantidadPares(rs.getInt(10));
				t.setIdDocumento(rs.getInt(12));

				us.setNombre(rs.getString(14)+" "+rs.getString(15));
				us.setNumero(rs.getInt(13));
				t.setUsuario(us);
				usuarios.add(us);
				t.setUsuarios(usuarios);
				t.setMain(rs.getInt(16));

				tareas.add(t);

			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return tareas;
	}
	
	

	public  int UltimoId(String consulta, boolean desconectar) 
	{
		int ultimo =0;

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				ultimo = rs.getInt(1);

			}

			rs.close();
			if(desconectar)
			{
				desconectar(rs,null, s);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return ultimo;
	}


	public  String UltimoCUbi(String consulta) 
	{
		String ultimo ="";

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				ultimo = rs.getString(1);

			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return ultimo;
	}

	public  Hashtable<String, DataOjo> darOjosOcupados(String consulta) 
	{
		Logica logica = new Logica();

		Hashtable<String, DataOjo> ojos = new Hashtable<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			String idOjoAnt = "";
			List <DataArticulo> articulos = new ArrayList<>();
			DataOjo d = new DataOjo();
			boolean pri = true;
			boolean hayRegistros = false;
			while (rs.next()) 
			{
				if(pri)
				{
					hayRegistros = true;
					pri = false;
					idOjoAnt = rs.getString(1);





					d.setAltoOjo(rs.getInt(15));
					d.setAnchoOjo(rs.getInt(14));
					d.setIdOjo(rs.getString(1));

					DataArticulo da = new DataArticulo();
					da.setAltoCaja(rs.getInt(6));
					da.setAnchoCaja(rs.getInt(5));
					da.setCantidad(rs.getInt(4));
					da.setDescripcion(rs.getString(3));
					da.setId(rs.getString(2));
					da.setProfCaja(rs.getInt(7));
					articulos.add(da);
				}
				else if(idOjoAnt.equals(rs.getString(1)))
				{
					idOjoAnt = rs.getString(1);
					DataArticulo da = new DataArticulo();
					da.setAltoCaja(rs.getInt(6));
					da.setAnchoCaja(rs.getInt(5));
					da.setCantidad(rs.getInt(4));
					da.setDescripcion(rs.getString(3));
					da.setId(rs.getString(2));
					da.setProfCaja(rs.getInt(7));
					articulos.add(da);
				}
				else // hay cambio de ojos
				{
					System.out.println(idOjoAnt);
					d.setArticulos(articulos);
					/*
					 * hay que validarlo en logica
					 * 
					 * 
					 */

					d = logica.encuentraCalcularOcupacion(d);
					ojos.put(d.getIdOjo(), d);

					//re instanciamos los objetos
					d = new DataOjo();
					articulos = new ArrayList<>();

					idOjoAnt = rs.getString(1);


					d.setAltoOjo(rs.getInt(15));
					d.setAnchoOjo(rs.getInt(14));
					d.setIdOjo(rs.getString(1));

					DataArticulo da = new DataArticulo();
					da.setAltoCaja(rs.getInt(6));
					da.setAnchoCaja(rs.getInt(5));
					da.setCantidad(rs.getInt(4));
					da.setDescripcion(rs.getString(3));
					da.setId(rs.getString(2));
					da.setProfCaja(rs.getInt(7));
					articulos.add(da);


				}

			}

			// cargamos el de la ultima vuelta porque no tuvo con que compararse
			if(hayRegistros)
			{

				d.setArticulos(articulos);
				/*
				 * hay que validarlo en logica
				 */

				// por eso
				if(d.getIdOjo().equals("D372"))
				{
					System.out.println("  ");
				}
				d = logica.encuentraCalcularOcupacion(d);
				ojos.put(d.getIdOjo(), d);
			}




			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return ojos;
	}
	public  List <DataIDDescripcion> darIdDescripcion (String consulta) 
	{
		List <DataIDDescripcion> lista = new ArrayList<>();


		try (Statement s = connection.createStatement();)
		{
			Connection cone;		
			cone = getConnection();

			
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataIDDescripcion a = new DataIDDescripcion();
				a.setId(rs.getInt(1));
				a.setDescripcion(rs.getString(2));
				try {
				a.setIdB(rs.getInt(3));
				} catch (Exception e) {
					
				}
				lista.add(a);
			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{		
			e.printStackTrace();
		}

		return lista;
	}

	public  List<DataLineaRepo> darArtBuscadoUbucacion(String consulta) 
	{
		List<DataLineaRepo> datas = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{
			
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataLineaRepo da = new DataLineaRepo();
				da.setCubi(rs.getString(1));
				da.setIdArticulo(rs.getString(2));
				da.setDescripcion(rs.getString(3));
				da.setSolicitada(rs.getInt(4));
				da.setEstnte(rs.getInt(7));
				da.setModulo(rs.getInt(6));
				da.setEstnteria(rs.getInt(8));

				datas.add(da);

			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return datas;
	}

	public  List<Ojo> darOjo(String consulta) 
	{
		List<Ojo> datas = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{
			
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				Ojo o = new Ojo();

				o.setIdOjo(rs.getString(1));
				o.setIdSector(rs.getInt(2));
				o.setEstante(rs.getInt(4));
				o.setModulo(rs.getInt(3));
				o.setDescripcionEstanteria(rs.getString(5));

				datas.add(o);

			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return datas;
	}
	
	public  List<DataOjoArticulo> darArticulosOjos(String consulta, boolean stockERP, int origen, int idEmpresa) 
	{
		List<DataOjoArticulo> datas = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);
			String arts = "";
			while (rs.next()) 
			{
				DataOjoArticulo da = new DataOjoArticulo();

				da.setArticulo(rs.getString(1));
				da.setDescripcion(rs.getString(2));
				//da.setModulo(rs.getInt(4));
				//da.setEstante(rs.getInt(3));
				da.setModulo(rs.getInt(3));
				da.setEstante(rs.getInt(4));
				da.setIdOjo(rs.getString(5));
				da.setIdSector(rs.getInt(6));
				da.setCantidad(rs.getInt(7));

				if(!rs.getString(8).equals("")){
					Fecha fecha = new Fecha(rs.getString(8));
					da.setFechaupdated(fecha.darFechaDia_Mes_Anio_HoraBarra());
				}
				da.setAlias(rs.getString(9));
				da.setCantidadReservada(rs.getInt(10));
				da.setTipoSku(rs.getInt(11));
				da.setIdBulto(rs.getString(12));
				try {
				da.setDescArticulo(rs.getString(13));

				} catch (Exception e) {
					
				}
				datas.add(da);
				
				arts += "'"+da.getArticulo()+"',";
			}
			
			if(idEmpresa == 2 && arts.length()>0 && stockERP) 
			{
					arts = arts.substring(0,arts.length()-1);
					Map<String, Integer> stocks = MSSQL.darStocks(arts, origen);	//FORUS
					
					if (!stocks.isEmpty()) {
						for(DataOjoArticulo d: datas) {
							try {d.setStock(stocks.get(d.getArticulo()));}catch (Exception e) {	e.printStackTrace();}
						}
					}				
			}

			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return datas;
	}

	public  DataIDIDDescripcion darEstanteEstMod(String consulta) 
	{
		DataIDIDDescripcion retorno = new DataIDIDDescripcion();

		try (Statement s = connection.createStatement();)
		{
			
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{

				retorno .setId(rs.getInt(1));//idEstanteria
				retorno.setIid(rs.getInt(2));//idEstante
				retorno.setDescripcion(rs.getString(3));//modulo
				retorno.setIdB(rs.getInt(4));
				try
				{
					retorno.setDescripcionB(rs.getString(5));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}

	public  List<DataIDDescripcion> darListaDataIdDescripcion(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{
			//connection = getConnection(); 
			
			ResultSet rs = s.executeQuery(consulta);
			lista.add(new DataIDDescripcion(0,"Todos"));

			while (rs.next())
			{
				//System.out.println(rs.getString(1));
				DataIDDescripcion d;
				try{
					d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				}
				catch(Exception e){
					e.printStackTrace();
					d = new DataIDDescripcion(rs.getLong(3), rs.getString(2));
				}
				try
				{
					d.setDescripcionB(rs.getString(3));
					d.setIdB(rs.getInt(4));
					d.setDescripcionC(rs.getString(5));
				}
				catch (Exception e) 
				{
					//e.printStackTrace();
				}
				
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}
	
	public  List<DataIDDescripcion> darListaDataIdDescripcionLong(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				System.out.println(rs.getString(1));
				DataIDDescripcion d = null;
				try
				{
					d = new DataIDDescripcion(rs.getLong(1), rs.getString(2));
					try
					{
						d.setDescripcionB(rs.getString(3));
					}
					catch (Exception e) 
					{

					}
				}
				catch(Exception e)
				{
					
				}
	
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}

	public  List<DataEcommerce_canales_envio> darListaDataEcommerce_canales_envio(String consulta) 
	{
		List<DataEcommerce_canales_envio> lista = new ArrayList<DataEcommerce_canales_envio>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataEcommerce_canales_envio d = new DataEcommerce_canales_envio(rs.getString(1),rs.getString(2) , rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6));
				try
				{
					d.setNombreRemite(rs.getString(7));
					d.setIdTienda(rs.getString(8));
					d.setFechaEntrega(rs.getString(9));
				}
				catch (Exception e) {

				}


				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	public DataEcommerce_canales_envio darDataEcommerce_canal_envio(String consulta) 
	{
		DataEcommerce_canales_envio d = null;
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				d = new DataEcommerce_canales_envio(rs.getString(1),rs.getString(2) , rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6));
				try
				{
					d.setNombreRemite(rs.getString(7));
					d.setIdTienda(rs.getString(8));
					d.setFechaEntrega(rs.getString(9));
				}
				catch (Exception e) {

				}

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return d;
		}

		return d;
	}

	public  Hashtable<String, DataIDDescripcion> darHASDataIdDescripcion(String consulta) 
	{
		Hashtable<String, DataIDDescripcion> lista = new Hashtable<>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataIDDescripcion d;
				try{
					d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				}
				catch(Exception e){
					d = new DataIDDescripcion(rs.getLong(3), rs.getString(2));
				}
				try
				{
					d.setDescripcionB(rs.getString(3));
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				try {
					d.setIdB(rs.getInt(4));
				} catch (Exception e) {
					e.printStackTrace();
				}
				lista.put(d.getDescripcionB(), d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}



	public List<DataPedidoArticuloEcommerceVerif>  darListaPedidosVerificadosArt(String consulta) 
	{
		List<DataPedidoArticuloEcommerceVerif>  lista = new ArrayList<>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataPedidoArticuloEcommerceVerif d = new DataPedidoArticuloEcommerceVerif(rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), rs.getInt(3), rs.getInt(4));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


	public  List<DataIDDescripcion> darListaDataIdDescripcionDB(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataIDDescripcion d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				try
				{
					d.setDescripcionB(rs.getString(3));
				}
				catch (Exception e) 
				{
					
				}
				
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}
	
	public  DataIDDescripcion darDataIdDescripcion(String consulta) 
	{
		DataIDDescripcion retorno = new DataIDDescripcion();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDDescripcion data = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				try 
				{
					data.setDescripcionB(rs.getString(3));
				}
				catch (Exception e){}
				try
				{
					data.setIdB(rs.getInt(4));
				}
				catch (Exception e){}
				try
				{

					data.setIdLong(rs.getLong(5));
				}
				catch (Exception e){}
				try
				{
					data.setDescripcionC(rs.getString(6));
				} 
				catch (Exception e){}

				return data;


			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return null;
	}

	
	public  Hashtable<Integer, DataIDDescripcion> darHasDataIdDescripcion(String consulta) 
	{
		Hashtable<Integer, DataIDDescripcion> retorno = new Hashtable<>();
	
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				try 
				{
					DataIDDescripcion data = new DataIDDescripcion(rs.getLong(1), rs.getString(2));				
					data.setDescripcionB(rs.getString(3));
					retorno.put(rs.getInt(3), data);
				}
				catch (Exception e){
					
				}
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}
	
	public  Hashtable<Integer, DataIDDescripcion> darHasDataIdDescripcion_(String consulta) 
	{
		Hashtable<Integer, DataIDDescripcion> retorno = new Hashtable<>();
	
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				try 
				{
					DataIDDescripcion data = new DataIDDescripcion(rs.getInt(1), rs.getString(2));				
					data.setDescripcionB(rs.getString(3));
					retorno.put(rs.getInt(1), data);
				}
				catch (Exception e){
					
				}
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}


	public  List<DataIDDescripcion> darListaDataIdDescripcionB(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			lista.add(new DataIDDescripcion(0,"Todos"));

			while (rs.next())
			{
				DataIDDescripcion d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				d.setDescripcionB(rs.getString(3));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	public  List<DataIDDescripcion> darArtBarrasRecepcionToPrint(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataIDDescripcion d = new DataIDDescripcion(rs.getInt(4), rs.getString(1)); //Cantidad // Articulo	
				d.setDescripcionB(rs.getString(2)); //Desc
				d.setDescripcionC(rs.getString(3)); //Barra
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DataIDIDDescripcion> darListaDataIdIdDescripcion(String consulta) 
	{
		List<DataIDIDDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDIDDescripcion d = new DataIDIDDescripcion(rs.getInt(1),rs.getInt(2), rs.getString(3));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


	public  List<DataIDDescripcion> darListaDataIdDescripcionAR(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDDescripcion d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				d.setDescripcionB(rs.getString(3));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}




	public  List<DataIDDescripcion> darListaDeposEcommerce(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDDescripcion d = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				d.setDescripcionB(rs.getString(3));

				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  Fecha darFechaReqEcommerce(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			Fecha f = null;
			while (rs.next())
			{
				f = new Fecha(rs.getString(1));

			}
			desconectar(rs,null, s);
			return f;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}


	}




	public  Hashtable<Integer, DataDescDescripcion> encuentraDarIdDepositos(String consulta) 
	{
		Hashtable<Integer, DataDescDescripcion> lista = new Hashtable<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataDescDescripcion dep = new  DataDescDescripcion(rs.getString(2),rs.getString(3));
				dep.setCantDias(rs.getInt(1));
				try
				{

				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}



				lista.put(rs.getInt(1), dep);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


	public  Hashtable<String, DataDescDescripcion> encuentraHashDescDescipcion(String consulta) 
	{
		Hashtable<String, DataDescDescripcion> lista = new Hashtable<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				lista.put(rs.getString(1),  new  DataDescDescripcion(rs.getString(1),rs.getString(2)));

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	public  Hashtable<String, DataIDDescripcion> encuentraHashIDDescipcion(String consulta) 
	{
		Hashtable<String, DataIDDescripcion> lista = new Hashtable<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataIDDescripcion data = new  DataIDDescripcion(rs.getInt(2),rs.getString(1));
				data.setIdLong(rs.getLong(3));
				lista.put(rs.getString(1),  data);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DataIDDescDescripcion> darListaDataIdDescDescripcion(String consulta) 
	{
		List<DataIDDescDescripcion> lista = new ArrayList<>();

		try 
		{
			getConnection();
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			DataIDDescDescripcion d;

			while (rs.next())
			{
				try{
					d = new DataIDDescDescripcion(rs.getInt(1), rs.getString(2), rs.getString(3));
				}
				catch (Exception e){
					d = new DataIDDescDescripcion(rs.getLong(5), rs.getString(2), rs.getString(3));
				}
				try{
					d.setTotal(Math.floor(100*rs.getDouble(4))/100);
				}
				catch (Exception e) {
					e.printStackTrace();
				}

				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DataIDDescDescripcion> darListaDataIdDescDescripcionCanalesML(String consulta) 
	{
		List<DataIDDescDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDDescDescripcion d = new DataIDDescDescripcion(rs.getInt(1), rs.getString(2), rs.getString(3));
				d.setDescII(rs.getString(4));

				try
				{
					d.setDescripcionII(rs.getString(5));
				}
				catch (Exception e) {
				}

				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DataIDDescDescripcion> darListaDDRecep(String consulta) 
	{
		List<DataIDDescDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDDescDescripcion d = new DataIDDescDescripcion(rs.getInt(1), rs.getString(2), rs.getString(3));
				d.setPorcentaje(rs.getInt(4));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


	public  List<DataIDDescDescripcion> darLogSinc(String consulta) 
	{
		List<DataIDDescDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDDescDescripcion d = new DataIDDescDescripcion(rs.getInt(1), rs.getString(2), rs.getString(3));
				d.setPorcentaje(rs.getInt(4));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DataDescDescripcion> darBarrasInn(String consulta) 
	{
		List<DataDescDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataDescDescripcion d = new DataDescDescripcion(rs.getString(1), rs.getString(2));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	public  Hashtable<String, String> darBarrasInnHT(String consulta) 
	{
		Hashtable<String, String> lista = new Hashtable<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				lista.put(rs.getString(2), rs.getString(1));
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


	public  List<DataIDDescDescripcion> darArtsRecep(String consulta) 
	{
		List<DataIDDescDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDDescDescripcion d = new DataIDDescDescripcion(rs.getInt(3), rs.getString(1), rs.getString(2));
				d.setPorcentaje(rs.getInt(4));
				d.setDescII(rs.getString(5));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


	public  List<Transporte> darListaTransportes(String consulta) 
	{
		List<Transporte> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				Transporte t = new Transporte();
				t.setId(rs.getInt(1));
				t.setDescripcion(rs.getString(3)+" "+rs.getString(4));
				t.setMarca(rs.getString(4));
				t.setMatricula(rs.getString(2));
				if(rs.getInt(5)==1)
				{
					t.setPropio(true);
				}
				else
				{
					t.setPropio(false);
				}

				lista.add(t);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DepositoEnvio> darListadepositosEnvio(String consulta) 
	{
		List<DepositoEnvio> lista = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DepositoEnvio t = new DepositoEnvio();
				t.setIdDeposito(rs.getInt(1));
				t.setDescripcion(rs.getString(2));
				t.setDireccion(rs.getString(3));
				t.setTipo(rs.getInt(4));
				lista.add(t);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


	public  DataIDIDDescripcion darVelocidad(int tipoTarea) 
	{
		String consultaPPM = "select `cantidadPares`, `CantidadMinutos`, '' from `velocidadpromedio` where IdTypeTask = "+tipoTarea+"  order by `FechaVigencia` desc limit 1";
		DataIDIDDescripcion retorno = new DataIDIDDescripcion();
		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consultaPPM);


			while (rs.next())
			{

				retorno.setId(rs.getInt(1));
				retorno.setIid(rs.getInt(2));

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return retorno;
		}
		return retorno;
	}


	public  int darhoraTarea(int tarea, List<DataIDDescripcion> events) 
	{
		int retorno = 0;


		boolean haypausa = false;
		Double diferencia = null;
		String fechaI = "";
		String fechaF = "";

		try 
		{
			List<DataIDDescripcion> eventos = null;
			if(events==null)
			{
				String consulta = "SELECT * FROM `tareaevento` WHERE `idTarea` = "+tarea+" and `idEstado` != 5 and `idEstado` != 0";
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery(consulta);


				eventos = new ArrayList<>();
				boolean pri = true;

				while (rs.next())
				{

					if(pri)
					{
						fechaI = rs.getString(5);
						pri = false;
					}
					else
					{
						fechaF = rs.getString(5);
					}

					DataIDDescripcion d = new DataIDDescripcion();
					d.setId(rs.getInt(3));
					d.setDescripcion(rs.getString(5));
					eventos.add(d);
					if(d.getId()==4)//hubo pausa
					{
						haypausa=true;
					}
				}
				rs.close();
				//desconectar(rs,null, s);
			}
			else
			{

				eventos = new ArrayList<>();
				boolean pri = true;

				for (DataIDDescripcion da : events) 
				{

					if(pri)
					{
						fechaI = da.getDescripcion();
						pri = false;
					}
					else
					{
						fechaF = da.getDescripcion();
					}


					eventos.add(da);
					if(da.getId()==4)//hubo pausa
					{
						haypausa=true;
					}
				}

			}


			/************************Comparo las fechas de inicio y fin*****************************/

			if(fechaI.substring(0, 8).equals(fechaF.substring(0,8)))//mismo dia
			{
				if(!haypausa)
				{
					String consultaIntervalo ="SELECT TIME_TO_SEC(TIMEDIFF( '"+fechaF+"' , '"+fechaI+"'))/60"; 

					Statement s2 = connection.createStatement();
					ResultSet rs2 = s2.executeQuery(consultaIntervalo);
					while (rs2.next())
					{
						diferencia = rs2.getDouble(1);
					}

					rs2.close();


				}
				else
				{
					Double tiempoActivo = 0.0;
					boolean primeraPausa = true;
					boolean mateInicio = true;
					for (DataIDDescripcion d : eventos) 
					{
						if(primeraPausa)//estoy buscando la primera pausa
						{	
							if(d.getId()==4)//encontr? la pausa
							{
								String consultaIntervalo ="SELECT TIME_TO_SEC(TIMEDIFF( '"+d.getDescripcion()+"' , '"+fechaI+"'))/60";

								Statement s2 = connection.createStatement();
								ResultSet rs2 = s2.executeQuery(consultaIntervalo);
								while (rs2.next())
								{
									tiempoActivo += rs2.getDouble(1);
								}

								rs2.close();
								primeraPausa = false;
							}
						}
						else//ya encontr? la pausa
						{
							if(d.getId()==1 && mateInicio)//encront? el nuevo inicio
							{
								fechaI = d.getDescripcion();
								mateInicio=false;

							}
							else if(d.getId()==4)//hay pausa
							{
								mateInicio=true;
								String consultaIntervalo ="SELECT TIME_TO_SEC(TIMEDIFF( '"+d.getDescripcion()+"' , '"+fechaI+"'))/60";

								Statement s2 = connection.createStatement();
								ResultSet rs2 = s2.executeQuery(consultaIntervalo);
								while (rs2.next())
								{
									tiempoActivo += rs2.getDouble(1);
								}

								rs2.close();
							}
							else if(d.getId()==2)
							{
								String consultaIntervalo ="SELECT TIME_TO_SEC(TIMEDIFF( '"+fechaF+"' , '"+fechaI+"'))/60";

								Statement s2 = connection.createStatement();
								ResultSet rs2 = s2.executeQuery(consultaIntervalo);
								while (rs2.next())
								{
									tiempoActivo += rs2.getDouble(1);
								}

								rs2.close();
								break;
							}

						}
					}
					DataIDDescripcion eventoFinal=eventos.get(eventos.size()-1);
					if(eventoFinal.getId()==1){
						String consultaIntervalo ="SELECT TIME_TO_SEC(TIMEDIFF(CURRENT_TIMESTAMP , '"+eventoFinal.getDescripcion()+"'))/60";
						Statement s2 = connection.createStatement();
						ResultSet rs2 = s2.executeQuery(consultaIntervalo);
						while (rs2.next())
						{
							tiempoActivo += rs2.getDouble(1);
						}
					}
					diferencia = tiempoActivo;				
				}
			}
			else if(!haypausa)
			{
				//algo anduvo mal
				diferencia = 86400.00;
			}
			retorno = (int) Math.round((diferencia));
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();

			return retorno;
		}
		return retorno;
	}

	public  DataDescDescripcion darFechasTarea(String consultafechas) 
	{
		DataDescDescripcion retorno = new DataDescDescripcion();
		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consultafechas);
			String fechaProp = "Sin Definir";
			String fechaS = "Sin Definir";
			while (rs.next())
			{

				String fechaRecibido = rs.getString(1);

				if(fechaRecibido.equals(""))
				{
					fechaProp = "Sin Definir";
				}
				else
				{
					String dia = fechaRecibido.substring(8, 10);
					String mes = fechaRecibido.substring(5, 7);
					String anio = fechaRecibido.substring(0, 4);
					String hora = fechaRecibido.substring(11, 16);
					fechaProp = dia + "-" + mes + "-" + anio + " " + hora;
				}

				String fechaInicio = rs.getString(2);

				if(fechaInicio.equals(""))
				{
					fechaS = "Sin Definir";
				}
				else
				{
					String dia = fechaInicio.substring(8, 10);
					String mes = fechaInicio.substring(5, 7);
					String anio = fechaInicio.substring(0, 4);
					String hora = fechaInicio.substring(11, 16);
					fechaS = dia + "-" + mes + "-" + anio + " " + hora;
				}





			}
			desconectar(rs,null, s);
			retorno.setDescripcion(fechaProp);
			retorno.setFecha(fechaS);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return retorno;
		}
		return retorno;
	}

	public  List<DataDetalleTarea> darTareasDetalle(String consulta) 
	{
		List <DataDetalleTarea> retornable = new ArrayList<>();
		String fechaAnt = "";
		boolean pri;
		int tareaAnt = -1;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				DataDetalleTarea d = new DataDetalleTarea();

				if(tareaAnt!=rs.getInt(3))//cambio de tarea?
				{
					pri = true;
				}
				else
				{
					pri = false;
				}


				if(pri)//entra solo si es la primera o hay cambio de tarea
				{	
					pri = false;
					d.setIdUsuario(rs.getInt(1));
					d.setNombreU(rs.getString(2));
					d.setIdtarea(rs.getInt(3));
					d.setNombreT(rs.getString(4));
					d.setEstado(rs.getString(5));
					d.setArticulo(rs.getString(6));
					d.setCantidad(rs.getInt(7));
					d.setUbicacion(rs.getString(8));
					int enco = rs.getInt(9);
					boolean encontrado;
					if(enco==0)
					{
						encontrado=false;
					}
					else
					{
						encontrado=true;
					}
					d.setEncontrado(encontrado);

					String fechaPi = rs.getString(10);
					String fechaS;
					String horaS;

					if(fechaPi.equals(""))
					{
						fechaS = "Sin Definir";
						horaS = "00:00:00";
					}
					else
					{
						String dia = fechaPi.substring(8, 10);
						String mes = fechaPi.substring(5, 7);
						String anio = fechaPi.substring(0, 4);
						String hora = fechaPi.substring(11);
						fechaS = dia + "-" + mes + "-" + anio + " " + hora;
						horaS = hora;
					}

					d.setHorsPiS(horaS);
					d.setHoraPi(fechaS);
					d.setVeloz(0.00);

					fechaAnt = fechaPi;

					retornable.add(d);
					tareaAnt = rs.getInt(3);

				}
				else//no es el primero ni de la lista ni de las tareas, hay que agregarlo y setearle la velocidad
				{

					d.setIdUsuario(rs.getInt(1));
					d.setNombreU(rs.getString(2));
					d.setIdtarea(rs.getInt(3));
					d.setNombreT(rs.getString(4));
					d.setEstado(rs.getString(5));
					d.setArticulo(rs.getString(6));
					d.setCantidad(rs.getInt(7));
					d.setUbicacion(rs.getString(8));
					int enco = rs.getInt(9);
					boolean encontrado;
					if(enco==0)
					{
						encontrado=false;
					}
					else
					{
						encontrado=true;
					}
					d.setEncontrado(encontrado);

					String fechaPi = rs.getString(10);
					String fechaS;
					String horaS;

					if(fechaPi.equals(""))
					{
						fechaS = "Sin Definir";
						horaS = "00:00:00";
					}
					else
					{
						String dia = fechaPi.substring(8, 10);
						String mes = fechaPi.substring(5, 7);
						String anio = fechaPi.substring(0, 4);
						String hora = fechaPi.substring(11);
						fechaS = dia + "-" + mes + "-" + anio + " " + hora;
						horaS = hora;
					}

					d.setHorsPiS(horaS);
					d.setHoraPi(fechaS);


					double veloz = darVelocidad(fechaAnt, fechaPi, d.getCantidad());

					d.setVeloz(veloz);


					fechaAnt = fechaPi;
					tareaAnt = rs.getInt(3);
					retornable.add(d);
				}





			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return retornable;
		}
		return retornable;

	}
	public  double darVelocidad(String fechaI, String fechaF, int cantidad)
	{
		double diferencia = 0;
		double tiempoEj = 0.0;

		String consultaIntervalo ="SELECT TIME_TO_SEC(TIMEDIFF( '"+fechaF+"' , '"+fechaI+"'))/60";
		Statement s2;
		try 
		{
			s2 = connection.createStatement();
			ResultSet rs2 = s2.executeQuery(consultaIntervalo);

			while (rs2.next())
			{
				diferencia = rs2.getDouble(1);
			}
			rs2.close();

		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block

			e1.printStackTrace();
		}


		double minutos = diferencia;


		tiempoEj = minutos;




		double veloz = 0.00;
		try
		{
			veloz = Double.parseDouble(String.valueOf(cantidad))/Double.parseDouble(String.valueOf(tiempoEj));

			DecimalFormat twoDForm = new DecimalFormat("#.##");
			String alfa = twoDForm.format(veloz);

			alfa = alfa.replaceAll(",", ".");
			System.out.println(twoDForm.format(veloz));
			System.out.println(alfa);
			veloz = Double.parseDouble(alfa);

		}
		catch (Exception e) 
		{
			veloz = 0.0;
			System.out.println("Entr? al catch");
			e.printStackTrace();
		}



		return veloz;

	}
	public  int darCantidadEnco(String consulta) 
	{
		int retorno = 0;

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{

				retorno = rs.getInt(1);


			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return retorno;
		}
		return retorno;
	}

	public  StringBuilder darListaAB(String consulta) 
	{
		StringBuilder retorno = new StringBuilder();
		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			int largoArticulo = 17;
			int largoBarras = 25;

			while (rs.next())
			{

				retorno.append("\r"+"\n");
				String barra = rs.getString(2);
				String articulo = rs.getString(1);

				int diferenciaArticulo = largoArticulo - articulo.length();
				int diferenciaBarras = largoBarras - barra.length();

				if(diferenciaBarras!=0)
				{
					for (int i = 0; i < diferenciaBarras; i++) 
					{
						barra +=" ";
					}
				}

				if(diferenciaArticulo!=0)
				{
					for (int i = 0; i < diferenciaArticulo; i++) 
					{
						articulo +=" ";
					}
				}


				retorno.append(barra+","+articulo);
				barra = null;
				articulo = null;



			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;
	}


	public  StringBuilder darListaAD(String consulta) 
	{
		StringBuilder retorno = new StringBuilder();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			int largoArticulo = 17;
			int largodescripcion = 30;

			while (rs.next())
			{
				retorno.append("\r"+"\n");

				String descripcion = rs.getString(2);
				String articulo = rs.getString(1);

				if(descripcion.length()>30)
				{
					descripcion = descripcion.substring(0,30);
				}


				int diferenciaArticulo = largoArticulo - articulo.length();
				int diferenciaDescripcion = largodescripcion - descripcion.length();

				if(diferenciaDescripcion!=0)
				{
					for (int i = 0; i < diferenciaDescripcion; i++) 
					{
						descripcion +=" ";
					}
				}

				if(diferenciaArticulo!=0)
				{
					for (int i = 0; i < diferenciaArticulo; i++) 
					{
						articulo +=" ";
					}
				}


				retorno.append(articulo+","+descripcion);
				articulo=null;
				descripcion=null;


			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;
	}

	public  List<TaskType> darTipoTareas(String consulta) {
		List<TaskType> tipos = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				TaskType t = new TaskType();
				t.setId(rs.getInt(1));
				t.setDescripcion(rs.getString(2));
				t.setUnidades(rs.getInt(3));
				t.setMinutos(rs.getInt(4));
				DataIDDescripcion area=new DataIDDescripcion(rs.getInt(5), rs.getString(6));
				t.setArea(area);
				DataIDDescripcion dispositivo=new DataIDDescripcion(rs.getInt(7),rs.getString(8));
				t.setResolucion(dispositivo);
				tipos.add(t);

			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return tipos;
	}


	public  List<Envio> darListaEnvios(String consulta,int idEmpresa) 
	{
		List<Envio> envios = new ArrayList<>();
		Logica Logica = new Logica();
		try 
		{
			int idEnvioAnt=0;
			int idEnvioAct =0;
			int idDepositoAnt =0;
			int idDepositoAct=0;
			int idRazonAnt = 0;
			int idRazonAct = 0;

			boolean pri = true;
			Envio envio = null;
			List<DepositoEnvio> depositosEnvio = new ArrayList<>();
			List <DataDocTipoEnvio> documentosTipo = new ArrayList<>();
			List<DocumentoEnvio> documentos = new ArrayList<>();

			int unidadesDocs=0;
			int unidadesDepo=0;
			DataDocTipoEnvio tipoEnvio = null;
			DepositoEnvio depositoEnvio = null;


			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			idEnvioAnt = 0;
			idDepositoAnt = 0;

			while (rs.next()) 
			{				
				idEnvioAct = rs.getInt(1);
				idDepositoAct = rs.getInt(16);

				if(idEnvioAnt!=idEnvioAct)
				{
					//instancio un envio
					envio = new Envio();
					envio.setIdEnvio(rs.getInt(1));
					envio.setSentido(rs.getInt(2));
					envio.setChofer(new DataIDDescripcion(rs.getInt(3), rs.getString(4)+" "+rs.getString(5)));
					envio.setAcompaniante(new DataIDDescripcion(rs.getInt(6), rs.getString(7)+" "+rs.getString(8)));

					Transporte trans = new Transporte();
					trans.setId(rs.getInt(9));
					trans.setDescripcion(rs.getString(12));
					trans.setMarca(rs.getString(11));
					trans.setMatricula(rs.getString(10));
					envio.setTransporte(trans);
					String fechaRecibido = rs.getString(13);

					String dia = fechaRecibido.substring(8, 10);
					String mes = fechaRecibido.substring(5, 7);

					String anio = fechaRecibido.substring(0, 4);
					envio.setFechaVis(dia+"/"+mes+"/"+anio);
					envio.setFecha(anio+"-"+mes+"-"+dia + " 00:00:00");
					envio.setEstado(new DataIDDescripcion(rs.getInt(14), rs.getString(15)));	

					envio.setDepositos(new ArrayList<>());
				}

				//instancio un nuevo deposito
				unidadesDepo =0;
				documentosTipo = new ArrayList<>();
				depositoEnvio = new DepositoEnvio();
				depositoEnvio.setIdDeposito(rs.getInt(16));
				depositoEnvio.setDescripcion(rs.getString(17));
				depositoEnvio.setOrdenCarga(rs.getInt(18));

				DataIDDescripcion dep = new DataIDDescripcion(rs.getInt(16),"0");
				dep.setDescripcionB("1");
				
				Ruta ruta = new Ruta(0,"");
				List<RutaDeposito> depositos = new ArrayList<RutaDeposito>();
				RutaDeposito unDepo = new RutaDeposito(dep.getId(),0,"");
				depositos.add(unDepo);
				ruta.setDepositos(depositos);
				
				List <DocumentoEnvio> documentosSel = Logica.darEnvios(ruta,null,null,rs.getInt(1),idEmpresa);

				Hashtable<Integer, DataDocTipoEnvio> htDocs = new Hashtable<>();

				for (DocumentoEnvio d : documentosSel) 
				{
					if(htDocs.get(d.getRazon().getId())==null)
					{
						DataDocTipoEnvio dt = new DataDocTipoEnvio();
						dt.setIdTipo(d.getRazon().getId());
						dt.setDecTipo(d.getRazon().getDescripcion());
						dt.setCantidad(d.getCantidad());
						List<DocumentoEnvio> docs = new ArrayList<>();
						docs.add(d);
						dt.setDocumentos(docs);
						htDocs.put(d.getRazon().getId(), dt);
					}
					else//sumo a los totales porque ya esta dentro de la lista
					{
						DataDocTipoEnvio dt = htDocs.get(d.getRazon().getId());
						dt.setCantidad(dt.getCantidad()+d.getCantidad());
						dt.getDocumentos().add(d);
						htDocs.put(d.getRazon().getId(), dt);
					}
				}

				ArrayList<DataDocTipoEnvio> docsPorTipo = new ArrayList<>(htDocs.values());

				int totalU = 0;
				int totalB = 0;

				int tipoD = 0;
				String desctipoD = "";
				Hashtable<Integer, DataIDDescripcion> cantTipos = new Hashtable<>();
				DataIDDescripcion data = new DataIDDescripcion(2,"Ecommerce");
				data.setIdB(0);
				cantTipos.put(2, data);
				data = new DataIDDescripcion(3,"Reposicion");
				data.setIdB(0);
				cantTipos.put(3, data);

				for (DataDocTipoEnvio da : docsPorTipo) 
				{
					for(DocumentoEnvio daa: da.getDocumentos()){
						totalB+= daa.getCantidad();

						tipoD = daa.getRazon().getId();
						desctipoD = daa.getRazon().getDescripcion();
						for(DataIDDescripcion it:daa.getListaDocs()){
							totalU+= it.getId();
							if(cantTipos.get(tipoD)==null) {
								data = new DataIDDescripcion(tipoD,desctipoD);
								data.setIdB(1);
								cantTipos.put(tipoD, data);
							}else {
								int cant = cantTipos.get(tipoD).getIdB()+1;
								cantTipos.get(tipoD).setIdB(cant);
							}
						}
					}						
				}

				depositoEnvio.setCantidadesXTipoDocs(new ArrayList<>(cantTipos.values()));
				Collections.sort(depositoEnvio.getCantidadesXTipoDocs());

				depositoEnvio.setTotalBultos(totalB);
				depositoEnvio.setTotalU(totalU);
				depositoEnvio.setDocumentos(docsPorTipo);

				envio.getDepositos().add(depositoEnvio);

				if(idEnvioAnt!=idEnvioAct){
					envios.add(envio);
				}

				idEnvioAnt = rs.getInt(1);
				idDepositoAnt = rs.getInt(16);

			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return envios;
	}


	public  List<Integer> darPrioridad(String consulta) {
		List<Integer> prioridades = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				prioridades.add(rs.getInt(2));

			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return prioridades;
	}

	public  List<DataArtBarraCant> darData(String select) {
		List<DataArtBarraCant> ariculos = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(select);
			while (rs.next()) 
			{
				DataArtBarraCant obj=new DataArtBarraCant();
				obj.setIdArticulo(rs.getString(1));
				obj.setCodBarra(rs.getString(2));
				obj.setCantidad(rs.getInt(3));
				ariculos.add(obj);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return ariculos;
	}

	public  boolean existeCodigo(String select) {
		int cont=0;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(select);

			while (rs.next()) 
			{
				cont++;
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return cont>0;
	}



	public  List<DataIDIDDescripcion> darArticulosRecepcion(String select) {
		List<DataIDIDDescripcion> ariculos = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(select);
			while (rs.next()) 
			{
				DataIDIDDescripcion obj=new DataIDIDDescripcion();
				obj.setId(rs.getInt(1));
				obj.setDescripcion(rs.getString(2));
				obj.setIid(rs.getInt(3));
				ariculos.add(obj);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return ariculos;
	}

	public  List<Tarea> darTareasFromMain(String select) {
		List<Tarea>tareas =new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(select);
			while (rs.next()) 
			{
				Tarea t=new Tarea();
				t.setId(rs.getInt(1));
				DataIDDescripcion estado=new DataIDDescripcion();
				estado.setId(rs.getInt(6));
				t.setEstado(estado);
				Usuario u=new Usuario();
				u.setNumero(rs.getInt(12));
				t.setUsuario(u);

				tareas.add(t);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return tareas;

	}


	public  Hashtable<Integer, DataDocVisual> darHTdocumentosEnvio(String consulta) 
	{
		Hashtable<Integer, DataDocVisual> documentos = new Hashtable<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataDocVisual data = new DataDocVisual();
				data.setIdVisual(rs.getInt(1));
				data.setIdInterno(0);
				data.setDepoOrigen(new DataIDDescripcion(rs.getInt(2),""));
				data.setDepoDestino(new DataIDDescripcion(rs.getInt(3),""));

				documentos.put(rs.getInt(1), data);


			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return documentos;
	}


	public  List<DataIDIDDescripcion> darArticulosRecepcionados(String select) {
		List<DataIDIDDescripcion>lista =new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(select);
			while (rs.next()) 
			{
				DataIDIDDescripcion data=new DataIDIDDescripcion();
				data.setId(rs.getInt(1));
				data.setIid(rs.getInt(3));
				data.setDescripcion(rs.getString(2));
				data.setDescripcionB(rs.getString(4));

				lista.add(data);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}

	public  DataIDDescripcion darProveedorRecepcion(String select) {
		DataIDDescripcion data=new DataIDDescripcion();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(select);
			while (rs.next()) 
			{
				data.setId(rs.getInt(1));
				data.setDescripcion(rs.getString(2));
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return data;
	}

	public List<DataDetalleRecepcion> darDetalleRecepciones(String consulta) {
		List<DataDetalleRecepcion>recepciones=new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			String artbase="";
			String color="";
			String talle="";
			DataDetalleRecepcion detalle=null;
			List<ColorDetalleRecepcion>colores=null;
			ColorDetalleRecepcion colordet=null;
			while (rs.next()) 
			{
				if(!rs.getString(1).substring(0, 9).equals(artbase)){
					artbase=rs.getString(1).substring(0, 9);
					if(detalle!=null)
						recepciones.add(detalle);
					detalle=new DataDetalleRecepcion();
					detalle.setArticulo(artbase);
					colores=new ArrayList<>();
					color="";
				}
				if(!rs.getString(1).substring(9,13).equals(color)){
					color=rs.getString(1).substring(9,13);
					if(colordet!=null)
						colores.add(colordet);
					colordet=new ColorDetalleRecepcion();
					colordet.setCod(color);
					colordet.setTalles(new ArrayList<>());
					colordet.setCantidades(new ArrayList<>());
				}
				colordet.getTalles().add(rs.getString(1).substring(13,17));
				colordet.getCantidades().add(new Cantidades(rs.getInt(2),rs.getInt(3),rs.getInt(4)));
				//colordet.getCantidadRecibida().add(rs.getInt(3));
				//colordet.getCantidadSolicitada().add(rs.getInt(2));
			}
			if(colores!=null){
				colores.add(colordet);
				detalle.setColores(colores);
				recepciones.add(detalle);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}


		return recepciones;
	}

	public  int getIdRecepcion(String consulta) {
		int idRecepcion=0;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				idRecepcion=rs.getInt(1);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return idRecepcion;
	}


	public  DataRecepcion daragendada(String consulta) 
	{

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataRecepcion dr = new DataRecepcion();
				dr.setId(rs.getInt(1));
				dr.setProveedor(new DataDescDescripcion("",rs.getString(2)));
				if(rs.getString(3).equals("OCC"))
				{
					dr.setImporta(false);
				}
				else
				{
					dr.setImporta(true);
				}
				return dr;
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public  Hashtable<String, String> DarBarras(String consulta) 
	{
		Hashtable<String, String> barraArt = new Hashtable<>();
		StringBuilder sb = new StringBuilder();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				barraArt.put(rs.getString(2).replace(" ", ""), rs.getString(1).replace(" ", ""));
				sb.append(rs.getString(2));
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//Escribir.escribir("c:/", "barrasII", sb.toString());
		return barraArt;
	}




	public  List<Integer> getDetalles(String consulta) {
		List<Integer> detalles = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				detalles.add(rs.getInt(1));
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return detalles;
	}

	public  int getSuma(String consulta) {
		int suma=0;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				suma=rs.getInt(1);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return suma;
	}

	public  List<DataIDIDID> darLineaDetalleRecepcion(String select2) {
		// TODO Auto-generated method stub
		return null;
	}

	public  DataIDDescripcion getTipo(String consulta) {
		DataIDDescripcion tipo=null;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				tipo= new DataIDDescripcion(rs.getInt(1), rs.getString(10));
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return tipo;
	}



	public  int getRecibidos(String select) {
		int cant=0;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(select);
			while (rs.next()) 
			{
				cant=rs.getInt(1);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return cant;
	}

	public  int getRecepcion(String select) {
		int idRecepcion=0;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(select);
			while (rs.next()) 
			{
				idRecepcion=rs.getInt(1);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return idRecepcion;
	}


	public  List<DataArtMovS> darListaArticulosDocs(String consulta) 
	{
		List<DataArtMovS> lista = null;

		Hashtable<String, DataArtMovS> articulosH = new Hashtable<>();

		System.out.println(consulta);
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next()) 
			{
				if(articulosH.get(rs.getString(3))==null)
				{
					DataArtMovS in = new DataArtMovS();
					in.setArticulo(rs.getString(3));
					in.setCantidad(rs.getInt(4));
					in.setIdMS(rs.getInt(1));
					in.setIdDoc(rs.getInt(2));
					List<String>barras = new ArrayList<>();
					barras.add(rs.getString(5));
					in.setBarras(barras);
					articulosH.put(in.getArticulo(), in);

				}
				else
				{
					DataArtMovS in = articulosH.get(rs.getString(3));
					in.getBarras().add(rs.getString(5));
					articulosH.put(in.getArticulo(), in);
				}

			}
			desconectar(rs,null, s);

			lista = new ArrayList<>(articulosH.values());

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}

	public  List<DataIDIDDescripcion> darIdIdDescripcion(String consulta) 
	{
		List<DataIDIDDescripcion> lista = new ArrayList<>();


		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataIDIDDescripcion a = new DataIDIDDescripcion();
				a.setId(rs.getInt(1));
				a.setDescripcion(rs.getString(2));
				a.setIid(rs.getInt(3));
				try
				{ 
					a.setDescripcionB(rs.getString(4));
				}
				catch (Exception e) {}

				lista.add(a);
			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<Menu> DarMenu(String consulta) 
	{
		List<Menu> lista = new ArrayList<>();


		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);




			while (rs.next()) 
			{

				Menu menuAnt = new Menu();
				menuAnt.setId(rs.getString(1));
				menuAnt.setDescripcion(rs.getString(2));
				menuAnt.setVisible(rs.getBoolean(3));
				menuAnt.setPadre(rs.getString(4));
				menuAnt.setIcon(rs.getString(5));
				menuAnt.setHref(rs.getString(6));
				menuAnt.set_blank(rs.getBoolean(7));
				menuAnt.setTags(rs.getString(8));

				lista.add(menuAnt);

			}


			rs.close();
			s.close();
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<ArticuloLineaReposicion> darListaArticulosRepo(String consulta) 
	{
		List<ArticuloLineaReposicion> lista = new ArrayList<>();


		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			System.out.println("");
			while (rs.next()) 
			{
				ArticuloLineaReposicion art = new ArticuloLineaReposicion(rs.getInt(8), rs.getString(3), rs.getString(9), 
						new DataIDDescripcion(rs.getInt(1), rs.getString(2)), new DataIDDescripcion(rs.getInt(4), rs.getString(5)), 
						new DataIDDescripcion(rs.getInt(6), rs.getString(7)));
				art.setPedido(rs.getLong(10));
				art.setPosClasif(rs.getString(6));
				try {
					if(rs.getString(11).equals("Sin Asignar")){
						art.setUbicaciones(rs.getString(11));
					}
					else{
						String[] ubis =rs.getString(11).split(",");
						int i = 0;
						String ubicaciones = "";
						for(String u:ubis){
							i++;
							if(i>2){
								break;
							}
							ubicaciones += u+", ";
						}
						ubicaciones = ubicaciones.substring(0,ubicaciones.length()-2);
						art.setUbicaciones(ubicaciones);
					}					

				} catch (Exception e) {
					art.setUbicaciones("");
				}

				art.setIdPick(rs.getInt(12));
				try
				{
					art.setArtDesc(rs.getString(13));
				}
				catch(Exception e)
				{

				}
				art.setEcommerce((rs.getInt(14) == 2));
				art.setSolicitud(rs.getInt(15));

				lista.add(art);

			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
		}

		return lista;
	}

	/*
	public  List<DataDistribucion> darListaDistribucionesArticulos(String consulta) 
	{
		List<DataDistribucion> lista = new ArrayList<>();


		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			DataDistribucion old = null;
			List<ArticuloPedido> articulosOld = null;
			boolean pri = true;
			int idSolAnt = 0;

			while (rs.next()) 
			{
				int idSol = rs.getInt(1);
				if(pri)
				{
					pri = false;
					old = new DataDistribucion();
					old.setId(idSol);
					Fecha fecha = new Fecha(rs.getString(2));

					old.setFecha(fecha.FechaMostrableSM());
					old.setMotivo(new DataIDDescripcion(rs.getInt(3), rs.getString(4)));
					old.setIdAlmacenOrigen(rs.getString(5));
					old.setComentario(rs.getString(6));
					old.setPrioridad(new DataIDDescripcion(rs.getInt(7),rs.getString(8)));
					old.setIdAlmacenTransito(rs.getString(9));

					articulosOld = new ArrayList<>();
					ArticuloPedido a = new ArticuloPedido();
					a.setArticulo(rs.getString(10));
					a.setLineaOrden(rs.getInt(11));
					a.setCantidadPedida(rs.getInt(12));
					a.setCantidadEntregada(a.getCantidadPedida()-rs.getInt(rs.getInt(13)));
					a.setDestino(rs.getString(14));

					articulosOld.add(a);

				}
				else
				{
					if(idSol==idSolAnt)
					{
						ArticuloPedido a = new ArticuloPedido();
						a.setArticulo(rs.getString(10));
						a.setLineaOrden(rs.getInt(11));
						a.setCantidadPedida(rs.getInt(12));
						a.setCantidadEntregada(a.getCantidadPedida()-rs.getInt(rs.getInt(13)));
						a.setDestino(rs.getString(14));

						articulosOld.add(a);
					}
					else
					{
						//corte
						old.setArticulos(articulosOld);
						lista.add(old);

						//empiezo de nuevo
						old = new DataDistribucion();
						old.setId(idSol);
						Fecha fecha = new Fecha(rs.getString(2));

						old.setFecha(fecha.FechaMostrableSM());
						old.setMotivo(new DataIDDescripcion(rs.getInt(3), rs.getString(4)));
						old.setIdAlmacenOrigen(rs.getString(5));
						old.setComentario(rs.getString(6));
						old.setPrioridad(new DataIDDescripcion(rs.getInt(7),rs.getString(8)));
						old.setIdAlmacenTransito(rs.getString(9));

						articulosOld = new ArrayList<>();
						ArticuloPedido a = new ArticuloPedido();
						a.setArticulo(rs.getString(10));
						a.setLineaOrden(rs.getInt(11));
						a.setCantidadPedida(rs.getInt(12));
						a.setCantidadEntregada(a.getCantidadPedida()-rs.getInt(rs.getInt(13)));
						a.setDestino(rs.getString(14));

						articulosOld.add(a);


					}
				}

				idSolAnt = idSol;

			}


			old.setArticulos(articulosOld);
			lista.add(old);
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return lista;
	}
	 */
	public  Hashtable<String, Integer> darStockArticulosRepo(String consulta) 
	{
		Hashtable<String, Integer> lista = new Hashtable<>();


		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				lista.put(rs.getString(1)+"-"+rs.getInt(2), rs.getInt(3));
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DataArticuloCantBarra> darListaArtRecepcionados(String consulta) 
	{
		List<DataArticuloCantBarra> lista = new ArrayList<>();
		Hashtable<String, DataIDDescripcion> basesCant = new Hashtable<>();
		Hashtable<String, DataIDDescripcion> basesColCant = new Hashtable<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				String base = "";
				String baseCol = "";
				//001.00600000222.0
				
				try {
					base = rs.getString(1).substring(0,7);
				} catch (Exception e) {
					base = rs.getString(1);
				}
				
				try {
					baseCol = rs.getString(1).substring(0,10);
				} catch (Exception e) {
					
				}
				
				int cantCont = rs.getInt(2);
				
				if(basesCant.get(base)==null)
				{
					basesCant.put(base, new DataIDDescripcion(cantCont,base));
				}
				else
				{
					DataIDDescripcion in = basesCant.get(base);
					in.setId(in.getId()+cantCont);
					basesCant.put(base, in);
				}

				if(basesColCant.get(baseCol)==null)
				{
					basesColCant.put(baseCol, new DataIDDescripcion(cantCont,baseCol));
				}
				else
				{
					DataIDDescripcion in = basesColCant.get(baseCol);
					in.setId(in.getId()+cantCont);
					basesColCant.put(baseCol, in);
				}


				DataArticuloCantBarra d = new DataArticuloCantBarra();
				d.setArticulo(rs.getString(1));
				d.setBarra("");
				d.setCantidadContada(cantCont);
				d.setCantFacturada(rs.getInt(3));
				d.setCantAFacturar(rs.getInt(4));
				d.setIdOC(rs.getString(5));
				d.setIdLineaOC(rs.getInt(6));
				d.setFolio(rs.getString(7));



				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}


		for (DataArticuloCantBarra d : lista) 
		{
			String base = "";
			String baseCol = "";
			
			try {
				base = d.getArticulo().substring(0,7);
			} catch (Exception e) {
				base = d.getArticulo();
			}
			
			try {
				baseCol = d.getArticulo().substring(0,10);
			} catch (Exception e) {
				
			}
			
			int cantBase = basesCant.get(base).getId();
			int cantBaseCol = basesColCant.get(baseCol).getId();

			d.setCantBase(cantBase);
			d.setCantBaseCol(cantBaseCol);


		}



		return lista;
	}

	public  List<Tarjeta> darListaTarjetas(String consulta) 
	{
		List<Tarjeta> lista = new ArrayList<>();


		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{

				Tarjeta t = new Tarjeta();
				t.setIdTarjeta(rs.getInt(1));
				t.setTipo(rs.getInt(2));
				t.setCantidad(rs.getDouble(3));
				t.setPorcentaje(rs.getInt(4));
				t.setTitulo(rs.getString(5));
				t.setIcon(rs.getString(6));
				t.setBgcolor(rs.getString(7));
				t.setHref(rs.getString(8));
				t.setTexto(rs.getString(9));
				t.setFootcolor(rs.getString(10));
				t.setDecimales(rs.getBoolean(12));
				t.setPadre(rs.getInt(13));

				lista.add(t);

			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  int DarIdDeposito(String consulta) 
	{
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				return rs.getInt(1);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}

		return 0;
	}

	public  List<DataDescDescripcion> darRepos(String consulta) 
	{
		List<DataDescDescripcion> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				DataDescDescripcion d = new DataDescDescripcion();
				d.setId(rs.getString(1));
				Fecha fecha = new Fecha(rs.getString(2));
				d.setFecha(fecha.darFechaAnioMesDia());
				d.setDescripcion(rs.getString(3));
				retorno.add(d);

			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;
	}

	public  List<DataLineaRepo> darListaPicking(String consulta) 
	{
		List<DataLineaRepo> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{

				DataLineaRepo d = new DataLineaRepo();


				d.setDescripcion(rs.getString(1));
				d.setDescDeposito(rs.getString(5));
				d.setEntregada(rs.getInt(4));
				d.setIdArticulo(rs.getString(2));
				d.setIdDepDestino(rs.getInt(6));
				d.setIdDepOrigen(rs.getInt(8));
				d.setPreparada(rs.getInt(7));
				d.setSolicitada(rs.getInt(3));
				d.setSotck(rs.getInt(10));


				retorno.add(d);



			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;
	}
	
	
	public ReposicionPicking creoRepPicking (String consulta) {
		ReposicionPicking resultado = new ReposicionPicking();
		List<Ubicacion_ReposicionPicking> coleccion_ubicacion =new ArrayList<Ubicacion_ReposicionPicking>();
		List<Bulto_ReposicionPicking> coleccion_bulto = new ArrayList<Bulto_ReposicionPicking>();
		try {
			Statement s;
			ResultSet rs = null;
			try {
				s = connection.createStatement();
				rs = s.executeQuery(consulta);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			String ultimo_ojo = "";
			boolean isFirst = true;
			Ubicacion_ReposicionPicking ubicacion = null;
			//String idArticulo = "";
			boolean esUltimo = false;
			while (rs.next())
			{
				
				if(isFirst) 
				{
					resultado = new ReposicionPicking(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(6), null , esUltimo);
					resultado.setImagen(rs.getString(15));
					resultado.setPacking(rs.getInt(16));
					
				} 
					try {
					if(rs.getString(10) != null) {
						Bulto_ReposicionPicking bulto = new Bulto_ReposicionPicking(rs.getString(7),rs.getInt(8));
						if(ultimo_ojo.equals(rs.getString(10))) { // Mientras sea igual lo voy cargando
							coleccion_bulto.add(bulto);
						
						}
						else {
							if(!isFirst) { // cuando cambia de ojo , agrego lo que tenia
								ubicacion.setListaBultos(coleccion_bulto);  // al objeto le pongo todos los bultos
								coleccion_ubicacion.add(ubicacion); // pongo el objeto ojo a la lista de ojos
								
							}					
							// creo ojo
							ubicacion = 
									new Ubicacion_ReposicionPicking(rs.getString(9),rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), null);
							coleccion_bulto = new ArrayList<Bulto_ReposicionPicking>(); //creo proximo bulto
							coleccion_bulto.add(bulto);
							isFirst = false;
							ultimo_ojo = rs.getString(10);
						}
					} else {
						isFirst = false;
					}
					} catch(Exception e) {
						System.out.println("No hay bultos");
					}
					
		//		} else {
			//		esUltimo=true;
	//				break;
			//	}
				
				
				//idArticulo = rs.getString(2);
			}
			
			
			
			if(coleccion_bulto.size() > 0) { // En el ultimo caso, si tiene algo lo agrego
				ubicacion.setListaBultos(coleccion_bulto);
				coleccion_ubicacion.add(ubicacion);
					
			}
			
			resultado.setListaUbicaciones(coleccion_ubicacion);
			resultado.setUltimo(esUltimo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	

	
	public int ultimaUbicacion(String consulta) {
		int resultado = 0;
		
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			resultado = Integer.parseInt(rs.getString(1));
		} catch (Exception e) {
			
		}

		return resultado;
		
	}
	

	public  List<DataLineaRepo> darListaPickingReservadas(String consulta) 
	{
		List<DataLineaRepo> retorno = new ArrayList<>();
		try 
		{
			//String idUltimoBulto = "";
			Hashtable<String, DataLineaRepo> listaBA = new Hashtable<>();
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			DataLineaRepo dlr = null;
			while (rs.next())
			{
				if(!rs.getString(21).equalsIgnoreCase("")) 
				{
					if(listaBA.get(rs.getString(21)) == null) {
						dlr = new DataLineaRepo();

						dlr.setBulto(true);
						dlr.setDescripcion("Bulto");
						dlr.setDescDeposito(rs.getString(5));
						dlr.setEntregada(rs.getInt(4));
						dlr.setIdArticulo(rs.getString(21));
						dlr.setIdDepDestino(rs.getInt(6));
						dlr.setIdDepOrigen(rs.getInt(8));
						dlr.setPreparada(rs.getInt(7));
						dlr.setSolicitada(1);
						dlr.setSotck(1);
						dlr.setCubi(rs.getString(11));
						dlr.setEstnteria(rs.getInt(12));
						//d.setEstnte(rs.getInt(13));
						//d.setModulo(rs.getInt(14));
						dlr.setEstnte(rs.getInt(14));
						dlr.setModulo(rs.getInt(13));
						dlr.setRecorrido(rs.getInt(15));
						dlr.setDescEstanteria(rs.getString(16));
						dlr.setPedido(0L);
						dlr.setDocumento(0); 
						dlr.setJustificacion("");
						dlr.setAutoVerificacion(rs.getInt(20));
						dlr.setContenido(new ArrayList<>());
						dlr.setBultoCerrado(rs.getInt(22) == rs.getInt(23));
						dlr.setQtyBulto(rs.getInt(22));
						dlr.setQtyPickBulto(rs.getInt(23));
						
						try
						{
							dlr.setImagen(rs.getString(26));
							dlr.setPacking(rs.getInt(27));
							dlr.setqPedido(rs.getInt(28));
							dlr.setCantDeVenta(rs.getInt(29));
						}
						catch (Exception e) 
						{
							dlr.setImagen("");
							dlr.setPacking(1);
							dlr.setqPedido(0);
							dlr.setCantDeVenta(0);
						}
						
						
						
						DataLineaRepo d = new DataLineaRepo();

						d.setBulto(false);
						d.setDescripcion(rs.getString(1));
						d.setDescDeposito(rs.getString(5));
						d.setEntregada(rs.getInt(4));
						d.setIdArticulo(rs.getString(2));
						d.setIdDepDestino(rs.getInt(6));
						d.setIdDepOrigen(rs.getInt(8));
						d.setPreparada(rs.getInt(7));
						d.setSolicitada(rs.getInt(3));
						d.setSotck(rs.getInt(10));
						d.setCubi(rs.getString(11));
						d.setEstnteria(rs.getInt(12));
						//d.setEstnte(rs.getInt(13));
						//d.setModulo(rs.getInt(14));
						d.setEstnte(rs.getInt(14));
						d.setModulo(rs.getInt(13));
						d.setRecorrido(rs.getInt(15));
						d.setDescEstanteria(rs.getString(16));
						d.setPedido(rs.getLong(17));
						d.setDocumento(rs.getInt(18)); 
						d.setJustificacion(rs.getString(19));
						d.setAutoVerificacion(rs.getInt(20));
						d.setPicked(rs.getInt(24));
						d.setPosClasif(rs.getString(25));
						
						
						try
						{
							d.setImagen(rs.getString(26));
							d.setPacking(rs.getInt(27));
							d.setqPedido(rs.getInt(28));
							d.setCantDeVenta(rs.getInt(29));
						}
						catch (Exception e) 
						{
							d.setImagen("");
							d.setPacking(1);
							d.setqPedido(0);
							d.setCantDeVenta(0);
						}
						
												
						listaBA.put(dlr.getIdArticulo(), dlr);
						listaBA.get(rs.getString(21)).getContenido().add(d);
						
					} else {
						DataLineaRepo d = new DataLineaRepo();


						d.setBulto(false);
						d.setDescripcion(rs.getString(1));
						d.setDescDeposito(rs.getString(5));
						d.setEntregada(rs.getInt(4));
						d.setIdArticulo(rs.getString(2));
						d.setIdDepDestino(rs.getInt(6));
						d.setIdDepOrigen(rs.getInt(8));
						d.setPreparada(rs.getInt(7));
						d.setSolicitada(rs.getInt(3));
						d.setSotck(rs.getInt(10));
						d.setCubi(rs.getString(11));
						d.setEstnteria(rs.getInt(12));
						//d.setEstnte(rs.getInt(13));
						//d.setModulo(rs.getInt(14));
						d.setEstnte(rs.getInt(14));
						d.setModulo(rs.getInt(13));
						d.setRecorrido(rs.getInt(15));
						d.setDescEstanteria(rs.getString(16));
						d.setPedido(rs.getLong(17));
						d.setDocumento(rs.getInt(18)); 
						d.setJustificacion(rs.getString(19));
						d.setAutoVerificacion(rs.getInt(20));
						d.setPicked(rs.getInt(24));
						d.setPosClasif(rs.getString(25));
						
						try
						{
							d.setImagen(rs.getString(26));
							d.setPacking(rs.getInt(27));
							d.setqPedido(rs.getInt(28));
							d.setCantDeVenta(rs.getInt(29));
						}
						catch (Exception e) 
						{
							d.setImagen("");
							d.setPacking(1);
							d.setqPedido(0);
							d.setCantDeVenta(0);
						}
						
						listaBA.get(rs.getString(21)).getContenido().add(d);
					}
				} else { 
					DataLineaRepo d = new DataLineaRepo();


					d.setBulto(false);
					d.setDescripcion(rs.getString(1));
					d.setDescDeposito(rs.getString(5));
					d.setEntregada(rs.getInt(4));
					d.setIdArticulo(rs.getString(2));
					d.setIdDepDestino(rs.getInt(6));
					d.setIdDepOrigen(rs.getInt(8));
					d.setPreparada(rs.getInt(7));
					d.setSolicitada(rs.getInt(3));
					d.setSotck(rs.getInt(10));
					d.setCubi(rs.getString(11));
					d.setEstnteria(rs.getInt(12));
					//d.setEstnte(rs.getInt(13));
					//d.setModulo(rs.getInt(14));
					d.setEstnte(rs.getInt(14));
					d.setModulo(rs.getInt(13));
					d.setRecorrido(rs.getInt(15));
					d.setDescEstanteria(rs.getString(16));
					d.setPedido(rs.getLong(17));
					d.setDocumento(rs.getInt(18)); 
					d.setJustificacion(rs.getString(19));
					d.setAutoVerificacion(rs.getInt(20));
					d.setPicked(rs.getInt(24));
					d.setPosClasif(rs.getString(25));
					
					try
					{
						d.setImagen(rs.getString(26));
						d.setPacking(rs.getInt(27));
						d.setqPedido(rs.getInt(28));
						d.setCantDeVenta(rs.getInt(29));
					}
					catch (Exception e) 
					{
						d.setImagen("");
						d.setPacking(1);
						d.setqPedido(0);
						dlr.setCantDeVenta(0);
					}
					
					retorno.add(d);
				}
			}
			List<DataLineaRepo> dts = new ArrayList<>(listaBA.values());
			retorno.addAll(dts);
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		System.out.println(retorno.size());
		return retorno;
	}


	public  List<DataPicking> darPicking(String consulta) 
	{
		List<DataPicking> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{

				DataPicking d = new DataPicking();
				d.setArticulo(rs.getString(1));
				d.setOrigen(new DataIDDescripcion(rs.getInt(2), rs.getString(3)));
				d.setDestino(new DataIDDescripcion(rs.getInt(4),rs.getString(5)));
				d.setIdPicking(rs.getInt(6));
				d.setUsuario(new DataIDDescripcion(rs.getInt(7),rs.getString(8)));
				d.setSol(rs.getInt(9));
				d.setPick(rs.getInt(10));
				d.setRemitida(rs.getInt(11));
				d.setIdPacking(rs.getInt(12));
				d.setVerificada(rs.getInt(14));
				d.setPosSort(rs.getString(15));
				d.setMayorista(rs.getBoolean(16));
				d.setIdPedido(rs.getLong(17));
				d.setIdPosLineaSAP(rs.getInt(18));
				d.setDescripcion(rs.getString(19));
				d.setSolicitud(rs.getInt(20));
				d.setRemision_bulto(rs.getInt(21));

				try
				{
					d.setIdTarea(rs.getInt(22));
				}
				catch (Exception e) 
				{
					d.setIdTarea(0);
				}
				
				List<String> barrasList = new ArrayList<>();
				String barras = rs.getString(13);

				if(barras.contains(","))
				{
					// si una columna tiene comas asumo que la ultima (la de los ID) tambien
					String[]barrasAr = barras.split(",");
					for (int i = 0; i < barrasAr.length; i++) 
					{
						try
						{
							String bar = barrasAr[i];
							barrasList.add(bar);
						}
						catch (Exception e)
						{

						}
					}
				}
				else
				{
					barrasList.add(barras);
				}

				d.setBarras(barrasList);
				retorno.add(d);



			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;
	}
	
	public  List<DataPicking> darPickingCBulto(String consulta) 
	{
		List<DataPicking> retorno = new ArrayList<>();
		try 
		{
			Hashtable<String, DataPicking>	bultos = new Hashtable<>();
			
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				DataPicking d = new DataPicking();
				List<String> barrasList = new ArrayList<>();
				d.setOrigen(new DataIDDescripcion(rs.getInt(2), rs.getString(3)));
				d.setDestino(new DataIDDescripcion(rs.getInt(4),rs.getString(5)));
				d.setIdPicking(rs.getInt(6));
				d.setUsuario(new DataIDDescripcion(rs.getInt(7),rs.getString(8)));
				d.setPick(rs.getInt(10));
				d.setRemitida(rs.getInt(11));
				d.setIdPacking(rs.getInt(12));
				d.setVerificada(rs.getInt(14));
				d.setPosSort(rs.getString(15));
				d.setMayorista(rs.getBoolean(16));
				d.setIdPedido(rs.getLong(17));
				d.setIdPosLineaSAP(rs.getInt(18));
				d.setDescripcion(rs.getString(19));
				d.setSolicitud(rs.getInt(20));
				d.setRemision_bulto(rs.getInt(21));
				d.setSol(rs.getInt(9));
				d.setJustificacion(rs.getString(27));
				d.setEmpaque(rs.getInt(28));
				d.setImagen(rs.getString(29));
				d.setArticulo(rs.getString(1));
				String barras = rs.getString(13);
				if(barras.contains(","))
				{
					// si una columna tiene comas asumo que la ultima (la de los ID) tambien
					String[]barrasAr = barras.split(",");
					for (int i = 0; i < barrasAr.length; i++) 
					{
						try
						{
							String bar = barrasAr[i];
							barrasList.add(bar);
						}
						catch (Exception e)
						{

						}
					}
				}
				else
				{						
					barrasList.add(barras);
				}
				d.setBarras(barrasList);
				d.setContenidoQty(rs.getInt(24));
				d.setDestinosQty(1);
				retorno.add(d);
				
				if(!rs.getString(22).equals("")) { //SI ES BULTO
					if(d.getContenidoQty()+d.getVerificada() <= d.getSol()) { //SI LA CANTIDAD DEL BULTO + LO QUE YA TENGO PICKEADO NO SE EXEDE DE LA CANTIDAD SOLICITADA LO INCLUYO
						if(rs.getString(25).equals("1")) {	//ESTA EN EL OJO 1, PICKEADO PERO SIN VERIFICAR
							DataPicking bulto = new DataPicking();
							bulto = d.Clonar();
							bulto.setArticulo(rs.getString(22));					
							bulto.setEstaCerrado(true);
							bulto.setEsBulto(true);
							
							bulto.setBarras(new ArrayList<>());
							bulto.getBarras().add(rs.getString(22));
							bulto.setContenidoQty(rs.getInt(23));
							bulto.setDestinosQty(rs.getInt(26));
							
							if(bultos.get(bulto.getArticulo())==null) {
								bulto.setContenido(new ArrayList<>());
								bulto.getContenido().add(d);
								bultos.put(bulto.getArticulo(), bulto);
							}
							else {
								bultos.get(bulto.getArticulo()).getContenido().add(d);
							}
						}						
					}					
				} 							
				
				
			}
			desconectar(rs,null, s);
			
			//me fijo si la cantidad original del bulto coincide con la lista de articulos pendientes que contiene
			//si no coinciden no muestro el bulto
			if(bultos.size() > 0) {
				Enumeration<String> elements = bultos.keys();
				while (elements.hasMoreElements()) {
					String idBulto = elements.nextElement();
					if(bultos.get(idBulto).getContenido().size() != bultos.get(idBulto).getContenidoQty()) {
						bultos.remove(idBulto);
					}
				}
				List<DataPicking> listaBultos = new ArrayList<>(bultos.values());
				if(listaBultos.size() > 0) {
					retorno.addAll(listaBultos);
				}
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;
	}


	public  List<Distribucion> encuentraDarDisBack(String consulta, int idEmpresa) 
	{
		Logica logica = new Logica();

		List<Distribucion> retorno = new ArrayList<>();

		Hashtable<Integer, DataDescDescripcion> depositos = logica.encuentraDarIdDepositos(idEmpresa);
		Hashtable<String, DataDescDescripcion> motivos = logica.encuentraDarMotivosMovimiento(idEmpresa);
		Hashtable<String, DataDescDescripcion> prioridades = new Hashtable<>();
		prioridades.put("1", new DataDescDescripcion("1", "Alta"));
		prioridades.put("2", new DataDescDescripcion("2", "Media"));
		prioridades.put("3", new DataDescDescripcion("3", "Baja"));

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				Distribucion d = new Distribucion();

				String motivo = rs.getString(3);
				String comentario = rs.getString(4);
				String depoOrigen = rs.getString(5);
				String prioridad = rs.getString(6);
				Fecha fecha = new Fecha(rs.getString(2));
				int idBackup = rs.getInt(1);


				d.setMotivo(motivos.get(motivo));
				d.setComentario(comentario);
				d.setPrioridad(prioridades.get(prioridad));
				d.setFecha(fecha);
				d.setIdBack(idBackup);
				d.setClearing(rs.getBoolean(7));

				String consultaII = "select idArticulo from distribucion_backup_articulos where idBackup = "+idBackup;

				if(d.isClearing())
				{
					d.setArticulos(articulosRSdis(consultaII,idBackup, null,idEmpresa));
					d.setDepositoOrigen(null);
				}
				else
				{
					d.setDepositoOrigen(depositos.get(Integer.parseInt(depoOrigen)));
					d.setArticulos(articulosRSdis(consultaII,idBackup, depositos.get(Integer.parseInt(depoOrigen)).getCantDias(),idEmpresa));
				}


				retorno.add(d);	
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;
	}

	public  List<ArticuloDistribucion> articulosRSdis (String consulta, int idBack,Integer idOrigen, int idEmpresa )
	{
		Logica logica = new Logica();

		List<ArticuloDistribucion> retorno = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs_II = s.executeQuery(consulta);

			int posW = 0;
			Distribuidor distb = new Distribuidor(true);
			List<String> bases = new ArrayList<>();
			Hashtable<String, ArticuloMatrizHTML> articulosHTML = new Hashtable<>();
			while (rs_II.next())
			{
				String str = rs_II.getString(1);
				articulosHTML = logica.darArticulosDistII(str, true, idOrigen, distb,null,idEmpresa);
				bases.add(rs_II.getString(1));
			}
			rs_II.close();




			for (String base : bases) 
			{
				ArticuloDistribucion articuloD = new ArticuloDistribucion();
				articuloD.setArticuloBase(base);

				Statement sI = connection.createStatement();
				ResultSet rs_III = sI.executeQuery("select IdArticuloColor from distribucion_backup_articulo_color where idBackup = "+idBack+" AND idArticulo = '"+base+"'");
				List <ArticuloMatrizHTML> artis = new ArrayList<>();
				while (rs_III.next())
				{
					artis.add(articulosHTML.get(rs_III.getString(1)));
				}
				rs_III.close();


				if(artis==null || artis.isEmpty())
				{
					System.out.println("Atencion.");
				}
				else
				{
					try
					{
						Collections.sort(artis);

						Collection<ArticuloMatrizHTML> org = new HashSet<ArticuloMatrizHTML>();
						int pos = 0;
						for (ArticuloMatrizHTML a : artis) 
						{
							a.setPos(pos);
							org.add(a);
							pos++;
						}

						Collection<ArticuloMatrizHTML> artisII = new HashSet<ArticuloMatrizHTML>(org.size());
						Iterator<ArticuloMatrizHTML> iterator = org.iterator();
						while(iterator.hasNext())
						{
							ArticuloMatrizHTML temp = iterator.next(); 
							artisII.add(temp.clone());

						}


						articuloD.setArtisOriginales(artisII);

						int posicion = 0;
						int tope = artis.size();
						for (int i = 0; i < tope; i++) 
						{
							String consultaII = "select request from distribucion_backup_articulos_movimientos where idBackup = "+idBack+" and idArticuloColor = '"+artis.get(i).getArticulo()+"'  AND  request!='null'";


							ArticuloMatrizHTML articulo = darMovimientosArtDis(consultaII,artis.get(i),idOrigen);

							artis.remove(i);
							artis.add(i,articulo);

						}
						articuloD.setArtis(artis);



						int cantidadMovida = 0;
						for (ArticuloMatrizHTML a : artis) 
						{
							List<MovimientoMatrizDis> movs = a.getMovimientos();
							for (MovimientoMatrizDis m : movs) 
							{
								cantidadMovida += m.getCantidad();
							}
						}

						articuloD.setCantidad(cantidadMovida);

						retorno.add(articuloD);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;

	}

	public  ArticuloMatrizHTML darMovimientosArtDis(String consulta, ArticuloMatrizHTML articulo, Integer idOrigen)
	{

		List<MovimientoMatrizDis> movimientos = new ArrayList<>();
		try
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			String depoOr ="";
			while (rs.next())
			{

				String re = rs.getString(1);
				int origenx;
				int origeny;
				int destinox;
				int destinoy;

				String[] values = re.split(","); 

				origenx = Integer.parseInt(values[5].split(":")[1]);
				origeny = Integer.parseInt(values[6].split(":")[1]);
				destinox = Integer.parseInt(values[2].split(":")[1]);
				destinoy = Integer.parseInt(values[3].split(":")[1]);

				String cantidadOriginal = values[1].split(":")[1];
				String cantidad = values[0].split(":")[1];



				MovimientoMatrizDis movimiento = new MovimientoMatrizDis();
				movimiento.setRequest(re);




				int cantOriginal = 0;
				int cant =0;

				try
				{
					cant = Integer.parseInt(cantidad);
					cantOriginal = Integer.parseInt(cantidadOriginal);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				cant = cant-cantOriginal;







				List<String> linea0 =  articulo.getLineas().get(0);

				List<String> lineaOrigen =  articulo.getLineas().get(origenx+1);



				depoOr = lineaOrigen.get(0);
				String talleOr = linea0.get(origeny+1);

				List<String> lineaDestino =  articulo.getLineas().get(destinox+1);

				String depoDe = lineaDestino.get(0);
				//String talleDe = linea0.get(destinoy);


				TalleMatrizRepo origen = articulo.getTallesM().get((origenx)+","+(origeny));

				try
				{
					origen.setPedido(origen.getPedido()+cant);
					origen.setOrigen(Integer.parseInt(depoOr));
					origen.setDestino(Integer.parseInt(depoDe));
					if(origen.getSugerido()>0)
					{
						origen.setSugerido(origen.getSugerido()-cant);
					}
					articulo.getTallesM().put((origenx)+","+(origeny), origen);

				}
				catch(Exception e)
				{
					e.printStackTrace();
				}


				//System.out.println((destinox)+","+(destinoy));
				TalleMatrizRepo destino = articulo.getTallesM().get((destinox)+","+(destinoy));

				destino.setOrigen(Integer.parseInt(depoOr));
				destino.setDestino(Integer.parseInt(depoDe));
				destino.setSugerido(destino.getSugerido()+cant);
				articulo.getTallesM().put((destinox)+","+(destinoy), destino);




				System.out.println("Mover "+cant+" unidad del talle "+talleOr+" desde"+depoOr+" hasta "+depoDe);
				movimiento.setCantidad(cant);
				movimiento.setTalle(talleOr);
				movimiento.setDestino(Integer.parseInt(depoDe));
				movimiento.setOrigen(Integer.parseInt(depoOr));

				articulo.getMovimientos().add(movimiento);


			}

			desconectar(rs,null, s);
			//despues de agregar todos los movimientos vamos a reconstrur la matriz
			Utilidad util = new Utilidad();
			String or;
			if (idOrigen==null)
			{
				or =null;
			}
			else
			{
				or = depoOr;
			}
			Hashtable<String, ArticuloMatrizHTML> arts = util.darMatrizFromX(articulo,or);
			articulo = arts.get(articulo.getArticulo());
			return articulo;

		}
		catch(Exception e)
		{
			return null;
		}




	}



	public  void updateTarjetas(String idGrupo, int idEmpresa) 
	{

		try 
		{
			_EncuentraPersistir eper = new _EncuentraPersistir();

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select distinct TQ.idTarjeta, Query, TQ.indice from tarjeta_query TQ inner join tarjetas_grupos TG on TG.idTarjeta=TQ.idTarjeta AND TG.IdEmpresa=TQ.IdEmpresa where TG.idEmpresa="+idEmpresa+" AND TG.idGrupo in ("+idGrupo+")");
			StringBuilder qPersistir = new StringBuilder();
			while (rs.next())
			{
				String query =  rs.getString(2);
				int idTarjeta = rs.getInt(1);
				int indice = rs.getInt(3);

				Double value = 0.0;
				int porcentaje = 0;

				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery(query);
				while (rs2.next()) 
				{
					value = rs2.getDouble(1);						
				}
				rs2.close();									

				try {
					porcentaje = Math.round(value.intValue()*100/indice);
				} catch (Exception e) {
					porcentaje = 0;
				}


				//_EncuentraPersistir.persistir("UPDATE `tarjetas` SET `Cantidad`="+value+" WHERE  `idTarjeta`="+idTarjeta+";");
				qPersistir.append("UPDATE `tarjetas` SET `Cantidad`="+value+", porcentaje="+porcentaje+" WHERE idEmpresa="+idEmpresa+" AND `idTarjeta`="+idTarjeta+";");

			}
			desconectar(rs,null, s);
			eper.persistir(qPersistir.toString());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}


	}

	public  List<DataIDDescripcion> detalleTarjetas(int idTarjeta) 
	{
		List<DataIDDescripcion> values = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select idTarjeta, Query from tarjeta_query2 where idTarjeta = "+idTarjeta);

			while (rs.next())
			{
				String query =  rs.getString(2);


				if(true)
				{
					Statement s2 = connection.createStatement();
					ResultSet rs2 = s2.executeQuery(query);

					while (rs2.next()) 
					{
						DataIDDescripcion value = new DataIDDescripcion(rs2.getInt(1), rs2.getString(2));
						int suc=0;
						try{
							suc=rs2.getInt(3);
						}
						catch(Exception e){
						}

						value.setIdB(suc);

						values.add(value);

					}
					rs2.close();
				}

			}
			desconectar(rs,null, s);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return values;


	}



	//List<DataClasificacionEntregaArti>



	public  List<DataClasificacionEntregaArti> darLineasEntrega(String consulta) 
	{
		List<DataClasificacionEntregaArti> values = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{

			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataClasificacionEntregaArti a = new DataClasificacionEntregaArti();
				a.setIdMain(rs.getInt(1));
				a.setIdDistribucion(rs.getInt(2));
				a.setIdLinea(rs.getInt(3));
				a.setArticulo(rs.getString(4));
				a.setDestino(rs.getString(5));
				a.setCantOrden(rs.getInt(6));
				a.setCantVerificada(rs.getInt(7));
				a.setCantMovida(rs.getInt(8));


				List<String> barrasList = new ArrayList<>();
				String barras = rs.getString(9);
				if(barras.contains(","))
				{
					// si una columna tiene comas asumo que la ultima (la de los ID) tambien
					String[]barrasAr = barras.split(",");
					for (int i = 0; i < barrasAr.length; i++) 
					{
						try
						{
							String bar = barrasAr[i];
							barrasList.add(bar);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				else
				{
					barrasList.add(barras);

				}

				a.setBarrasList(barrasList);

				values.add(a);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return values;


	}
	public  List<DataEntregaVerificada> DarEntregasVerif(String consulta) 
	{
		List<DataEntregaVerificada> values = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{

				DataEntregaVerificada d = new DataEntregaVerificada(rs.getInt(1), rs.getInt(4), rs.getInt(5), rs.getString(2), rs.getString(3));
				values.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return values;


	}
	public  List<ArticuloPedido> DarArticulosEntregasVerif(String consulta) 
	{
		List<ArticuloPedido> values = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				ArticuloPedido a = new ArticuloPedido();
				a.setIdDist(rs.getInt(2));
				a.setLineaOrden(rs.getInt(3));
				a.setArticulo(rs.getString(4));
				a.setDestino(rs.getString(5));
				a.setCantidadPedida(rs.getInt(6));
				a.setCantidadEntregada(rs.getInt(7));
				a.setOrigen(rs.getString(8));
				a.setComent(rs.getString(9));
				a.setRazon(rs.getInt(10));

				values.add(a);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return values;


	}


	public  List<DataReglaReposicion> darReglas(String consulta) 
	{
		List<DataReglaReposicion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataReglaReposicion d = new DataReglaReposicion();

				d.setIdRegla(rs.getInt(1));
				d.setPrioridad(rs.getInt(2));
				d.setMinCentral(rs.getInt(3));
				d.setMaxLocal(rs.getInt(4));
				d.setDestinos(darDestinosRegla(rs.getInt(1)));
				d.setFiltros(darFiltrosRegla(rs.getInt(1)));
				d.setNombre(rs.getString(5));




				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  Hashtable<Integer, Integer> darDestinosRegla(int idRegla) 
	{
		Hashtable<Integer, Integer> destinos = new Hashtable<>();
		String consulta = "select * from reposicion_reglas_destinos where idRegla = "+idRegla;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				destinos.put(rs.getInt(2), rs.getInt(2));	
			}
			rs.close();
			//desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return destinos;
	}

	public  Hashtable<Integer, DataIDDescripcion> darFiltrosRegla(int idRegla) 
	{
		Hashtable<Integer, DataIDDescripcion> filtros = new Hashtable<>();
		String consulta = "select * from reposicion_reglas_filtro_valor where idRegla = "+idRegla;
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				filtros.put(rs.getInt(2), new DataIDDescripcion(rs.getInt(2),rs.getString(3)));

			}
			rs.close();
			//desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return filtros;
	}

	public  List<DataIDIDDescripcion> darFiltrosReglasReposicion(String consulta) 
	{
		List<DataIDIDDescripcion> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				DataIDIDDescripcion d = new DataIDIDDescripcion(rs.getInt(1), rs.getInt(2), rs.getString(3));
				retorno.add(d);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return retorno;
	}

	public  List<DataDescDescripcion> darListaLogsClienteWSSAP(String consulta) 
	{
		List<DataDescDescripcion> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				DataDescDescripcion log = new DataDescDescripcion(rs.getString(2), rs.getString(3));
				log.setFecha(rs.getString(1));
				retorno.add(log);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return retorno;

	}

	public  List<DataArticuloEcommercePedido> darArtReqEcommerce(String consulta) 
	{
		List<DataArticuloEcommercePedido> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				Fecha fecha = new Fecha(rs.getString(5));
				DataArticuloEcommercePedido toadd = new DataArticuloEcommercePedido(rs.getLong(1), rs.getInt(3), rs.getInt(4), rs.getString(2), fecha.darFechaDia_Mes_Anio_HoraBarra(),rs.getInt(6));
				Fecha fechaC = new Fecha(rs.getString(7));
				toadd.setFechaConfirmado(fechaC.darFechaDia_Mes_Anio_HoraBarra());
				retorno.add(toadd);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return retorno;

	}


	public  boolean confirmarArticuloReq(String consulta) 
	{

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				System.out.println(rs.getInt(1));
				System.out.println(rs.getInt(2));
				return rs.getInt(1)<=rs.getInt(2);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return false;



	}


	public  boolean confirmarEnvioArticuloReq(String consulta) 
	{

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				System.out.println(rs.getInt(1));
				System.out.println(rs.getInt(2));
				return rs.getInt(1)==rs.getInt(2) && rs.getInt(1)!=0;

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		return false;



	}

	public  DataArticuloEcommerceVerifR darArtReqEcommerceVerif(String consulta) 
	{
		DataArticuloEcommerceVerifR retorno = null;
		try 
		{
			if (connection == null || connection.isClosed()) {
				connection = connect();
			}

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{											//idPedido,cantidadPendiente,totalPedido,  idArticulo, descripcion, String urlEtiqueta, int idDeposito
				retorno = new DataArticuloEcommerceVerifR(rs.getLong(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getInt(7));
				retorno.setMl(rs.getInt(8));
				retorno.setCiCliente(rs.getString(9));
				retorno.setTelCliente(rs.getString(10));
				retorno.setMailCliente(rs.getString(11));
				retorno.setCanal(rs.getInt(12));
				try {
					retorno.setEstadoEncuentra(rs.getInt(13));
					retorno.setFecha(rs.getString(14).split(" ")[0]);
					retorno.setIdEcommerce(rs.getString(15));
					retorno.setIdDestino(rs.getInt(16));
					retorno.setTicketNumber(rs.getString(17));
					retorno.setSubpedido(rs.getString(18));
					retorno.setTracking(rs.getString(19));
					retorno.setPickup(rs.getInt(20)==2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Statement s2 = connection.createStatement();
				Long id = rs.getLong(1);
				ResultSet rs2 = s2.executeQuery("select idPedido, sum(CantidadProcesasa) totP from ecommerce_pedido_articulos_req where idPedido = "+id+" group by idPedido ");
				while (rs2.next())
				{
					retorno.setTotalProcesado(rs2.getInt(2));
				}

				break;// asi me aseguro que solo agarre el primero
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return retorno;

	}

	public  List<DataArticuloEcommerceVerifR> darArtsReqEcommerceVerif(String consulta) 
	{
		List<DataArticuloEcommerceVerifR> lista = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{											//idPedido,cantidadPendiente,totalPedido,  idArticulo, descripcion, String urlEtiqueta, int idDeposito
				DataArticuloEcommerceVerifR toad = new DataArticuloEcommerceVerifR(rs.getLong(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getInt(7));

				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery("select idPedido, sum(CantidadProcesasa) totP from ecommerce_pedido_articulos_req where idPedido = "+toad.getIdPedido()+" group by idPedido ");
				while (rs2.next())
				{
					toad.setTotalProcesado(rs.getInt(1));
				}

				lista.add(toad);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;

	}

	public  boolean encuentraPedidosOrdenVenta(String consulta, int idEmpresa) 
	{
		boolean retorno = false;

		Logica logica = new Logica();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				retorno = true;
				List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
				int cantidad=0;
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery("select precioImp,cantidad,Replace (TRIM(idArticulo),'?',''),if(isnull(c.id),0,1) from ecommerce_import_ventalinea V "
						+ "LEFT OUTER JOIN ecommerce_cupones c on c.descripcion=v.idarticulo "
						+ "where idVenta ="+rs.getLong(10)+"  order by precioImp desc");

				while (rs2.next())
				{
					cantidad+=rs2.getInt(2);
					EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
					art.setArticulo(rs2.getString(3).replace(" ", ""));
					art.setCantidad(rs2.getInt(2));
					art.setProcesada(0);
					art.setEsCupon(rs2.getInt(4));

					OrdenVentaLinea ovl = new OrdenVentaLinea(rs2.getDouble(1), rs2.getString(2), rs2.getString(3).replace(" ", ""));
					ovl.setEsCupon(rs2.getInt(4));
					ventaLineas.add(ovl);
				}


				Double porcDescuento = rs.getDouble(2);

				int vendedor = 2000;

				if(rs.getInt(13)==1 || rs.getInt(13)==-2 || rs.getInt(13)==2)
				{
					vendedor=2003;
				}
				else{
					if(rs.getInt(16)==-1){
						vendedor=2051;
					}
					else{
						vendedor = 2000;
					}
				}

				OrdenVenta orden = new OrdenVenta(0, porcDescuento, "$", rs.getString(4).replace("", ""), rs.getString(5), rs.getString(6), "", rs.getString(8), ventaLineas, rs.getLong(10),vendedor, rs.getString(12),0, rs.getDouble(15));
				orden.setComentario(rs.getString(14));
				String f= rs.getString(17);
				Fecha fecha= new Fecha(f);
				orden.setFecha(fecha.darFechaAnio_Mes_Dia());
				System.out.println("\n \n "+orden.getCliNombre());
				orden.setFormaPagoVisual(rs.getString(18));
				for (OrdenVentaLinea ovl : ventaLineas) 
				{
					System.out.println(ovl.getIdArticulo()+"  "+ovl.getPrecioImp());
				}

				boolean existeOV=false;
				existeOV= logica.hayRegistro("select idOrden from ecommerce_pedido_factura where idPedido= "+orden.getIdCarrito());


				if(!existeOV){
					WSCommunicate ws = new WSCommunicate();
					ws.grabarOV(orden,idEmpresa);
				}

			}

			desconectar(rs,null, s);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		return retorno;


	}


	public  List<DataDetallePedido> encuentraVerPedidosPendientes(String query, int idEmpresa) 
	{
		Logica logica = new Logica();
		List<DataDetallePedido> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next())
			{	



				String fechaC = "---";
				String fechaP = "---";

				String fechaR_cruda = rs.getString(9);
				String fechaC_cruda = rs.getString(10);
				String fechaP_cruda = rs.getString(11);

				if(!fechaR_cruda.equals(fechaC_cruda))
				{
					Fecha fecha = new Fecha(fechaC_cruda);
					fechaC = fecha.darFechaDia_Mes_Anio_HoraBarra();
				}
				if(!fechaR_cruda.equals(fechaP_cruda))
				{
					Fecha fecha = new Fecha(fechaP_cruda);
					fechaP = fecha.darFechaDia_Mes_Anio_HoraBarra();
				}

				Fecha fecha = new Fecha(fechaR_cruda);
				String fechaR = fecha.darFechaDia_Mes_Anio_HoraBarra();

				boolean etiqueta = false;
				String etiquetaS = rs.getString(16);
				if(etiquetaS.contains("http"))
				{
					etiqueta=true;
				}


				int idfactura = rs.getInt(13);
				String urlFactura = rs.getString(17);
				int idEstado = rs.getInt(18);
				if(idfactura!=0 && urlFactura.equals(""))
				{
					//ClienteWS cws = new ClienteWS();
					//urlFactura = cws.darPDFFactura(idfactura);

					//logica.altaOrdenPedido(rs.getLong(1), rs.getInt(12), idfactura, urlFactura,idEmpresa);
				}


				DataDetallePedido p = new DataDetallePedido(rs.getString(3), rs.getString(4), fechaR, fechaC, fechaP, rs.getLong(1), rs.getInt(2), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(12), rs.getInt(13), "", rs.getString(14), rs.getString(15),etiqueta,0,"");
				p.setUrlEriqueta(etiquetaS);
				p.setUrlFactura(urlFactura);
				p.setIdEstado(idEstado);
				retorno.add(p);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}

	public  List<DataDetallePedidoPrint> encuentraVerPedidosPrint(String query,int idEmpresa, boolean detalleCompleto) 
	{
		
		List<DataDetallePedidoPrint> retorno = null;
		
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			int ii = 0;
			Hashtable<String, DataDetallePedidoPrint> pedidos = new Hashtable<>();
			while (rs.next())
			{	
				
				if(pedidos.get(rs.getString(1))!=null)
				{
					DataDetallePedidoPrint dp =pedidos.get(rs.getString(1));
					DataDetallePedidoPrintArt ar = new DataDetallePedidoPrintArt(rs.getString(14),rs.getString(18), rs.getInt(15), rs.getDouble(16),rs.getString(19));
					dp.getArticulos().add(ar);
					pedidos.put(rs.getString(1),dp);
				}
				else
				{
					DataDetallePedidoPrint dp = new DataDetallePedidoPrint(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),rs.getString(13), rs.getString(17),rs.getString(20));
					DataDetallePedidoPrintArt ar = new DataDetallePedidoPrintArt(rs.getString(14),rs.getString(18), rs.getInt(15), rs.getDouble(16),rs.getString(19));
					List<DataDetallePedidoPrintArt> arts = new ArrayList<>();
					arts.add(ar);
					
					dp.setArticulos(arts);
					pedidos.put(rs.getString(1),dp);
				}
				
			
			}
			desconectar(rs,null, s);
			retorno = new ArrayList<>(pedidos.values());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return retorno;
	}
			
	
	//////////////

	public  List<DataDetallePedido> encuentraVerPedidos(String query,int idEmpresa, boolean detalleCompleto) 
	{
		Logica logica = new Logica();
		List<DataDetallePedido> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			int ii = 0;
			while (rs.next())
			{					

				String fechaC = "---";
				String fechaP = "---";

				String fechaR_cruda = rs.getString(9);
				String fechaC_cruda = rs.getString(10);
				String fechaP_cruda = rs.getString(11);

				String fechaD_cruda =rs.getString(23);
				String fechaD="---";

				if(!fechaR_cruda.equals(fechaC_cruda))
				{
					Fecha fecha = new Fecha(fechaC_cruda);
					fechaC = fecha.darFechaDia_Mes_Anio_HoraBarra();
					if(fechaC.contains(" ")){
						fechaC=fechaC.split(" ")[0]+"<br> "+fechaC.split(" ")[1];
					}
				}
				if(!fechaR_cruda.equals(fechaP_cruda))
				{
					Fecha fecha = new Fecha(fechaP_cruda);
					fechaP = fecha.darFechaDia_Mes_Anio_HoraBarra();
					if(fechaP.contains(" ")){
						fechaP=fechaP.split(" ")[0]+"<br> "+fechaP.split(" ")[1];
					}
				}
				if(!fechaR_cruda.equals(fechaD_cruda))
				{
					Fecha fecha = new Fecha(fechaD_cruda);
					fechaD = fecha.darFechaDia_Mes_Anio_HoraBarra();
					if(fechaD.contains(" ")){
						fechaD=fechaD.split(" ")[0]+"<br> "+fechaD.split(" ")[1];
					}
				}

				Fecha fecha = new Fecha(fechaR_cruda);
				String fechaR = fecha.darFechaDia_Mes_Anio_HoraBarra();
				if(fechaR.contains(" ")){
					fechaR=fechaR.split(" ")[0]+"<br> "+fechaR.split(" ")[1];
				}

				boolean etiqueta = false;
				String etiquetaS = rs.getString(16);
				if(etiquetaS.contains("http"))
				{
					etiqueta=true;
				}


				int idfactura = rs.getInt(13);
				String urlFactura = rs.getString(17);
				int idEstado = rs.getInt(18);
				if(idfactura!=0 && urlFactura.equals(""))
				{
					//ClienteWS cws = new ClienteWS();
					//urlFactura = cws.darPDFFactura(idfactura);

					//logica.altaOrdenPedido(rs.getLong(1), rs.getInt(12), idfactura, urlFactura,idEmpresa);
				}

				String destino = rs.getString(27);
				try {
					String[] coleccion = rs.getString(27).split(" ");
					if(coleccion.length>3){						
						destino = coleccion[0]+" "+coleccion[1]+" "+coleccion[2]+"<br> ";
						for(int i = 3; i<coleccion.length;i++){
							destino += coleccion[i];
						}
					}
				} catch (Exception e) {
					destino = rs.getString(27);
				}		

				String descripcion = rs.getString(15);
				try {
					String[] coleccion = rs.getString(15).split(" ");
					if(coleccion.length>2){						
						descripcion = coleccion[0]+" "+coleccion[1]+"<br> ";
						for(int i = 2; i<coleccion.length;i++){
							descripcion += coleccion[i];
						}
					}
				} catch (Exception e) {
					descripcion = rs.getString(15);
				}

				String estado = rs.getString(3);
				try {
					String[] coleccion = rs.getString(3).split(" ");
					if(coleccion.length>2){						
						estado = coleccion[0]+" "+coleccion[1]+"<br> ";
						for(int i = 2; i<coleccion.length;i++){
							estado += coleccion[i]+" ";
						}
					}
				} catch (Exception e) {
					estado = rs.getString(3);
				}


				DataDetallePedido p = new DataDetallePedido(estado, rs.getString(4), fechaR, fechaC, fechaP, rs.getLong(1), rs.getInt(2), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(12), rs.getInt(13), "", rs.getString(14), descripcion,etiqueta,rs.getInt(19),rs.getString(20));
				p.setUrlEriqueta(etiquetaS);
				p.setUrlFactura(urlFactura);
				p.setIdEstado(idEstado);
				p.setArtMod(rs.getInt(21));
				p.setChange(rs.getInt(22));
				p.setFechaDespacho(fechaD);
				p.setRetiraFormulario(rs.getInt(24));
				p.setPersonaRetira(rs.getString(25));
				p.setEstadoEcommerce(rs.getString(26));
				p.setDestinoNombre(destino);
				p.setIdMS(rs.getString(28)); //idFenicio
				p.setCc(rs.getInt(8));
				try {
					p.setDepositoNombre(rs.getString(40));
				} catch (Exception e) {
				}
				
				
				try {
					p.setaConsolidar(rs.getString(29));
				} catch (Exception e) {
				}
				try {
					p.setDescArt(rs.getString(30));
				} catch (Exception e) {
					p.setDescArt("");
				}
				try {
					String usuC = "";
					usuC = rs.getString(31);					
					if(usuC.contains(",")){
						usuC=usuC.split(",")[0]+"<br> "+usuC.split(",")[1];
					}
					p.setUsuClasifica(usuC);

					String fechaE = "";
					fechaE = rs.getString(32);	
					if(!fechaE.equals("---")) {
						Fecha fechaEE = new Fecha(fechaE);
						fechaE = fechaEE.darFechaDia_Mes_Anio_HoraBarra();
						if(fechaE.contains(" ")){
							fechaE=fechaE.split(" ")[0]+"<br> "+fechaE.split(" ")[1];

						}
					}					
					p.setFechaEntrega(fechaE);
				} catch (Exception e) {
					p.setUsuClasifica("---");
					p.setFechaEntrega("---");
				}
				
				p.setDocMovimiento(rs.getInt(33));
				p.setNotaArt(rs.getString(34));
				
				p.setSubpedido(rs.getString(35));
				DataIDDescripcion shippingType;
				try {
					shippingType = new DataIDDescripcion(rs.getInt(36),rs.getString(37));
				} catch (Exception e) {
					shippingType = new DataIDDescripcion(0,"Sin definir");
				}
				
				p.setShippingType(shippingType);
				p.setImporte(rs.getDouble(38));
				p.setTracking(rs.getString(39));

				if(detalleCompleto)
				{
					
					p.setMail(rs.getString(40));
					p.setTel(rs.getString(41));
					p.setDpto(rs.getString(42));
					p.setLocalidad(rs.getString(43));
					p.setCalle(rs.getString(44).replace("null", ""));
					p.setCodPostal(rs.getString(45));
					p.setObs(rs.getString(46));

					p.setFormaEntrega("");
					if(p.getIdDestino()!=0) {
						if(p.getIdDestino()>9000) {
							p.setFormaEntrega("domicilio");
						}
						else {
							p.setFormaEntrega("sucursal");
						}					
					}

					try {
						String fechaCierre = "";
						fechaCierre = rs.getString(47);	
						if(!fechaCierre.equals("---")) {
							Fecha fechaForm = new Fecha(fechaCierre);
							fechaCierre = fechaForm.darFechaDia_Mes_Anio_HoraBarra();
							if(fechaCierre.contains(" ")){
								fechaCierre=fechaCierre.split(" ")[0]+"<br> "+fechaCierre.split(" ")[1];

							}
						}
						p.setFechaCierre(fechaCierre);
					} catch (Exception e) {
						p.setFechaCierre("");
					}
					
				}

				retorno.add(p);
				System.out.println(ii++);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}

	public  List<DataDetallePacking> darDetallePacking(String query, String idPacking) 
	{
		List<DataDetallePacking> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next())
			{	
				Fecha fecha = new Fecha(rs.getString(4));
				String fechaR = fecha.darFechaDia_Mes_Anio_HoraBarra();

				DataDetallePacking toadd = new DataDetallePacking(idPacking, rs.getString(1), rs.getString(2), fechaR, rs.getString(5), rs.getString(6),rs.getInt(3));
				retorno.add(toadd);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}

	public  List<DataEcommerce_LogPedido> darListaLogEcommerce(String query) 
	{
		List<DataEcommerce_LogPedido> retorno = new ArrayList<DataEcommerce_LogPedido>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next())
			{	try
			{
				/******************************************idPedido, mensaje, level, fecha, nombreUsuario**/
				retorno.add(new DataEcommerce_LogPedido(rs.getLong(1), rs.getInt(3), rs.getString(4),rs.getString(2),rs.getString(5)));
			}
			catch (Exception a)
			{
				a.printStackTrace();
			}


			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}

	public  List<DocumentoEnvio> darEnvios(String consulta) 
	{
		List<DocumentoEnvio> lista=new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{	
				if(rs.getInt(1)==969){
					System.out.println("remito");
				}


				DocumentoEnvio v=new DocumentoEnvio();
				v.setNumeroDoc(rs.getInt(1));
				v.setCantidad(rs.getInt(2));
				DataIDDescripcion razon=new DataIDDescripcion();
				razon.setId(rs.getInt(3));
				razon.setDescripcion(rs.getString(4));
				v.setRazon(razon);
				DataIDDescripcion depositoOrigen=new DataIDDescripcion();
				depositoOrigen.setId(rs.getInt(5));
				depositoOrigen.setDescripcion(rs.getString(6));
				v.setDepositoO(depositoOrigen);
				DataIDDescripcion depositoDestino=new DataIDDescripcion();
				depositoDestino.setId(rs.getInt(7));
				depositoDestino.setDescripcion(rs.getString(8));
				v.setDepositoD(depositoDestino);
				DataIDDescripcion usuario=new DataIDDescripcion();
				usuario.setId(rs.getInt(9));
				usuario.setDescripcion(rs.getString(10));
				v.setUsuario(usuario);

				v.setComentario(rs.getString(13));




				Statement s3 = connection.createStatement();
				ResultSet rs3 = s3.executeQuery("select URLRemito,cantidad from envio_encuentra_documento_remitos where idDocumento = "+v.getNumeroDoc());
				List<DataIDDescripcion> urls = new ArrayList<>();
				while (rs3.next())
				{
					urls.add(new DataIDDescripcion(rs3.getInt(2),rs3.getString(1)));
				}

				v.setListaDocs(urls);
				v.setCustom(false);

				List<DataArticulo>lista2=new ArrayList<>();
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery("select idArticulo,Cantidad from envio_encuentra_documento_lineas where idDocumento = "+rs.getInt(1));
				while (rs2.next())
				{
					DataArticulo art=new DataArticulo();
					art.setId(rs2.getString(1));
					art.setCantidad(rs2.getInt(2));
					lista2.add(art);
				}
				rs2.close();
				v.setArticulos(lista2);
				v.setIncluir(false);
				lista.add(v);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}



		return lista;
	}

	public  List<Nota> PersistirNotas(Long idPedido, int idEmpresa)
	{

		List<Nota> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select n.idNota,n.idPedido,n.idUsuario,n.txtNota,n.fecha,u.Nombre,u.Apellido "
					+ 					" from ecommerce_Nota n "
					+ "					inner join ecommerce_pedido e on n.idPedido=e.idPedido AND n.idEmpresa=e.idEmpresa "
					+ "					 inner join usuarios u on n.idUsuario=u.idUsuario AND n.idEmpresa=u.idEmpresa "
					+ " where n.idEmpresa="+idEmpresa+" AND n.idPedido="+idPedido);
			while (rs.next())
			{	try
			{
				Fecha f = new Fecha(rs.getString(5));
				String nombre = rs.getString(6)+" "+rs.getString(7);
				Nota n = new Nota(idPedido,rs.getInt(1),rs.getInt(3),nombre,rs.getString(4),f);
				retorno.add(n);

			}
			catch (Exception a)
			{
				a.printStackTrace();
			}


			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}


	public  List<DataEcommerceReporte> darListaDataIdDescDescripcionReporteEcommerce(String consulta) 
	{
		List<DataEcommerceReporte> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataEcommerceReporte d = new DataEcommerceReporte(rs.getString(1), rs.getString(2), rs.getInt(3));
				d.setFecha(rs.getString(4));
				d.setFechaR(rs.getString(5));
				if(rs.getString(5).equals(rs.getString(6))){
					d.setFechaC("---");
				}
				else{
					d.setFechaC(rs.getString(6));
				}

				d.setCantidad(rs.getInt(7));
				d.setOrden(rs.getInt(8));
				d.setFactura(rs.getInt(9));
				d.setDocEnvio(rs.getInt(10));

				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}



	public  List<DataEcommerceReporteLista> darListaDataIdDescDescripcionReporteEcommerceLista(String consulta) 
	{
		List<DataEcommerceReporteLista> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataEcommerceReporteLista d = new DataEcommerceReporteLista(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getInt(4));


				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<LeadTimes> darListaLeadTimesEcommerce(String consulta) 
	{
		List<LeadTimes> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				String [] promedioConfirmado = rs.getString(6).split(":");
				String pc=promedioConfirmado[0]+":"+promedioConfirmado[1];

				String [] promedioProcesado = rs.getString(7).split(":");
				String pp=promedioProcesado[0]+":"+promedioProcesado[1];

				String pd="";
				String pe="";
				try{
					String [] promedioEmpaquetado = rs.getString(8).split(":");
					pe=promedioEmpaquetado[0]+":"+promedioEmpaquetado[1];

					String [] promedioDespachado = rs.getString(9).split(":");
					pd=promedioDespachado[0]+":"+promedioDespachado[1];
				}
				catch (Exception e) {
					e.printStackTrace();
				}

				String [] promedioLd = rs.getString(10).split(":");
				String plt=promedioLd[0]+":"+promedioLd[1];


				LeadTimes lt = new LeadTimes(rs.getString(1), rs.getInt(4),pc,pp,pe,pd,plt);

				lt.setIdPedido(rs.getString(2));
				lt.setIdArt(rs.getString(3));
				lt.setDep(rs.getInt(5));

				lt.setIndice(Integer.parseInt(promedioLd[0]));

				lista.add(lt);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


	public  List<DataLineaRepo> darLineasRepoPendientes (String query){
		List<DataLineaRepo> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);


			while (rs.next())
			{
				DataLineaRepo r = new DataLineaRepo();
				r.setIdArticulo(rs.getString(1));
				r.setSolicitada(rs.getInt(2));
				r.setAreaArt(rs.getInt(3));

				lista.add(r);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DataIDDescDescripcion> darPendientesPickUp(String consulta) 
	{
		List<DataIDDescDescripcion> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				DataIDDescDescripcion d = new DataIDDescDescripcion(0, rs.getString(2), rs.getString(3));
				d.setPorcentaje(rs.getInt(4));
				d.setDescripcionII(rs.getString(5));
				d.setIdLong(rs.getLong(1));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}





	public Hashtable<Integer,Usuario> DarUsuarios(String consulta) 
	{
		Hashtable<Integer,Usuario> usuarios = new Hashtable<>();
		try 
		{
			Statement s = connection.createStatement();
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next()) 
			{
				Usuario u = new Usuario();

				u.setNumero(rs.getInt(1));//idUsuario
				u.setPass(rs.getString(2));//Contrase?a
				u.setNick(rs.getString(3));//Nickname
				u.setNombre(rs.getString(4));//Nombre
				u.setApellido(rs.getString(5));//Apellido
				u.setPerfil(rs.getInt(6));//perfil
				u.setMail(rs.getString(7));//mail
				u.setGrupo(rs.getInt(8));//idGrupo
				u.setPerfilDesc(rs.getString(9));//descripcion
				u.setDeposito(rs.getString(10));//idDeposito
				u.setIdEmpresa(rs.getInt(11));//idEmpresa

				usuarios.put(u.getNumero(), u);
			}
			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return usuarios;
	}

	public  List<TareasPendientes> TareasPendientes(String consulta, int idEmpresa) 
	{
		List<TareasPendientes> tareas = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				TareasPendientes t = new TareasPendientes();
				t.setIdPicking(rs.getInt(2));                         
				try {
					t.setFecha(rs.getString(1).split(" ")[0]+"<br>"+rs.getString(1).split(" ")[1]); 
				} catch (Exception e) {
					t.setFecha(rs.getString(1)); 
				}

				t.setIdTarea(rs.getInt(3));      
				t.setUnidades(rs.getInt(4));
				t.setDescripcionEstado(rs.getString(5));
				t.setUsuario(rs.getString(6));
				t.setAvance(rs.getInt(7));
				t.setLista(DocsInTarea(t.getIdPicking(),idEmpresa,connection));

				tareas.add(t);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return tareas;
	}

	public  List<DataIDDescripcion> DocsInTarea(int idPicking, int idEmpresa, Connection trans) 
	{
		List<DataIDDescripcion> docs = new ArrayList<>();

		try 
		{
			String query = "select seccion,idSolicitudTraslado from reposicion_articulos where idpicking ="+idPicking+" and idEmpresa="+idEmpresa+
					" group by seccion,idSolicitudTraslado";
			Statement s = trans.createStatement();
			ResultSet rs = s.executeQuery(query);


			while (rs.next())
			{            	   
				if(rs.getLong(1)!=0 || rs.getInt(2)!=0) {
					DataIDDescripcion data = new DataIDDescripcion(rs.getLong(1),rs.getString(2));
					docs.add(data);
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			if(trans!=null){                    
				try {
					trans.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}                    
			}               
		}

		return docs;
	}


	public List<MovArticulo> darMovimientoArticulos(String q){
		List<MovArticulo> origenes = new ArrayList<>();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(q);

			while (rs.next()){
				MovArticulo mov = new MovArticulo();
				mov.setIdMov(rs.getInt(1));
				mov.setIdArticulo(rs.getString(2));
				mov.setCodOrigen(rs.getString(3));
				mov.setCodDestino(rs.getString(4));
				mov.setCantidad(rs.getInt(5));
				mov.setUsuario(rs.getInt(6));
				mov.setNombreUsuario(rs.getString(7));
				mov.setUpdate(rs.getString(8));
				mov.setTipo(rs.getString(9));

				origenes.add(mov);
			}

			desconectar(rs, null, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origenes;
	}

	public List<ConsolidarSku> darReporteConsolidacion(String query, int idEmpresa){
		List<ConsolidarSku> lista = new ArrayList<>();
		List<LineaOrdenAlmacen> lineas = new ArrayList<>();
		LineaOrdenAlmacen l;
		try {

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			while(rs.next()){
				ConsolidarSku sku = new ConsolidarSku();
				sku.setMarca(rs.getString(1));
				sku.setGenero(rs.getString(2));
				sku.setCategoria(rs.getString(3));
				sku.setClase(rs.getString(4));
				sku.setArticulo(rs.getString(5));
				sku.setOjosCantidad(rs.getInt(6));
				sku.setOjos(rs.getString(7));
				sku.setTotal(rs.getInt(8));
				lineas = new ArrayList<>();

				Statement s2 = connection.createStatement();
				String queryAux = "select ot.idarticulo,ot.cantidad,ot.idojo \r\n" + 
						"FROM ojostienenarticulos ot\r\n" + 
						"INNER JOIN ojos o ON o.idOjo=ot.idOjo AND o.IdEmpresa=ot.IdEmpresa\r\n" + 
						"INNER JOIN estanterias ES ON ES.idEstanteria=o.idEstanteria AND ES.idEmpresa=o.IdEmpresa \r\n" + 
						"where ot.idEmpresa ="+idEmpresa+" AND ES.idUso IN (1) AND ot.idarticulo='"+sku.getArticulo()+"'";
				
				ResultSet rs2 = s2.executeQuery(queryAux);
				while(rs2.next()){
					l = new LineaOrdenAlmacen();
					l.setSku(rs2.getString(1));
					l.setCantidad(rs2.getInt(2));
					l.setUbicacion(rs2.getString(3));
					lineas.add(l);
				}
				sku.setListaArticulos(lineas);
				lista.add(sku);
			}
			desconectar(rs, null, s);
		} catch (Exception e) {
			// handle exception
		}
		return lista;
	}

	public List<OrdenAlmacen> darOrdenesAlmacen(String q){
		List<OrdenAlmacen> ordenes = new ArrayList<>();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(q);

			while (rs.next()){
				OrdenAlmacen ord = new OrdenAlmacen();
				ord.setId(rs.getInt(1));
				ord.setFecha(rs.getString(2));
				ord.setCantidad(rs.getInt(3));

				ordenes.add(ord);
			}

			desconectar(rs, null, s);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return ordenes;
	}

	public List<DatosInventario> getDatosInventario(String q){
		List<DatosInventario> datos = new ArrayList<>();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(q);

			while (rs.next()){
				DatosInventario d = new DatosInventario();
				d.setEstanteria(rs.getInt(1));
				d.setDescEstanteria(rs.getString(2));
				d.setIdOjo(rs.getString(3));
				d.setModulo(rs.getInt(5));
				d.setEstante(rs.getInt(4));
				d.setCantidad(rs.getInt(6));
				d.setFecha(rs.getString(7));

				datos.add(d);
			}

			desconectar(rs, null, s);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}

		return datos;
	}

	public DatosInventarioStatus getDatosInventarioStatus(String q)
	{

		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(q);
			DatosInventarioStatus d= null;
			while (rs.next()){
				d = new DatosInventarioStatus(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5));

			}



			desconectar(rs, null, s);
			return d;

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}

		return null;

	}

	public List<OjosEnCero> getOjosEnCero(String query){
		List<OjosEnCero> lista = new ArrayList<>();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()){
				OjosEnCero o = new OjosEnCero();
				o.setDesc(rs.getString(1));
				o.setIdOjo(rs.getString(2));
				o.setCant(rs.getInt(3));

				lista.add(o);
			}

			desconectar(rs, null, s);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}

		return lista;
	}

	public List<DataIDDescripcion> ArtRecepSinMov(String query) {

		List<DataIDDescripcion> lista = new ArrayList<>();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next()){
				DataIDDescripcion d = new DataIDDescripcion();
				d.setDescripcion(rs.getString(1)); // Descripcion articulo
				d.setId(rs.getInt(2)); // Cantidad
				d.setIdB(rs.getInt(3)); // dias

				lista.add(d);
			}

			desconectar(rs, null, s);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}

		return lista;
	}

	public List<ArticuloReposicion> darArticuloRepoFromLoad(List<DataIDDescripcion> articulosCantidad, String tempTable,
			StringBuilder inserts, String query, int sucursal, boolean manual, int idEmpresa, int origen, int tipo, boolean prioridad) 
	{
		Logica Logica = new Logica();

		List<ArticuloReposicion>lista=new ArrayList<>();
		int pioridadAlta = 0;
		try 
		{


			Statement s = connection.createStatement();


			ResultSet rs = s.executeQuery(query);


			String justificacion = "Lineas manuales";
			String justificacionWeb = "";
			int idSincro = Logica.darNextSincRepo(idEmpresa);
			Logica.actualizarSincRepo(1,idSincro,idEmpresa);

			if(!manual)
			{

				justificacion = "Venta WEB";
			}


			while (rs.next())
			{
				System.out.println(rs.getString(1));
				if(rs.getString(2).equals(""))
				{
					//el arrti no esta en la DB lo doy de alta
					List<DTO_Articulo> subListaDatos = new ArrayList<>();
					DTO_Articulo art = new DTO_Articulo(rs.getString(1), rs.getString(1), "", "", "0", "0", "0", "0", "0", "0");
					art.getCodigoBarras().add(rs.getString(1));
					art.setIdGenero("0");
					subListaDatos.add(art);
					Logica logica = new Logica();
					logica.persistirArticulos(subListaDatos, idEmpresa);
				}
				
				
				ArticuloReposicion a = null;
				if(idEmpresa==2)
				{
					a = new ArticuloReposicion(rs.getString(1), rs.getString(1).substring(0,13), rs.getString(2), rs.getInt(4), rs.getLong(5), rs.getInt(6), rs.getInt(3), 0, 0, 0, 0, sucursal, rs.getInt(7), rs.getInt(8), rs.getInt(9),tipo);
				}
				else
				{
					a = new ArticuloReposicion(rs.getString(1), rs.getString(1), rs.getString(2), rs.getInt(4), rs.getLong(5), rs.getInt(6), rs.getInt(3), 0, 0, 0, 0, sucursal, rs.getInt(7), rs.getInt(8), rs.getInt(9),tipo);
				}
				//marca,		seccion,	clase,		   venta, 					 sucursal,genero,	     categoria,    temporada
				a.setSolicitud(rs.getInt(10));
				a.setDescripcionLinea(rs.getString(11));
				a.setDestino(sucursal);
				a.setOrigen(origen);
				pioridadAlta = (prioridad)?1:0;				
				a.setPrioridad(pioridadAlta);
				String vari = "";
				try
				{
					vari = rs.getNString(11);
				}
				catch (Exception e) 
				{

				}
				if(justificacion.equals("Venta WEB"))
				{

					justificacionWeb = justificacion+" "+rs.getLong(5)+" "+vari;

					Logica.guardarLineaReposicion(a, justificacionWeb, idSincro,0,idEmpresa);
				}
				else if(rs.getLong(5) != 0) {
					justificacion = "Pedido "+rs.getLong(5)+" "+vari;;
					Logica.guardarLineaReposicion(a, justificacion, idSincro,0,idEmpresa);
				}
				else
				{
					Logica.guardarLineaReposicion(a, justificacion, idSincro,0,idEmpresa);	
				}

			}
			desconectar(rs, null, s) ;

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return lista;
	}

	public  List<DataIDDescripcion> DarRecorrido(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataIDDescripcion d = new DataIDDescripcion();

				d.setDescripcion(rs.getString(1));
				d.setDescripcionB(rs.getString(2));
				d.setId(rs.getInt(3));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public List<DepositoParametros> darParametrosDepo (String consulta){

		List<DepositoParametros> darParametros = new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next()){
				DepositoParametros data = new DepositoParametros();
				data.setIdParametro(rs.getInt(1)); //idParametro
				data.setIdDeposito(rs.getInt(2)); //idDeposito
				Integer hab = rs.getInt(3);
				if(hab.equals(0))
					data.setHabilitado(false); //No habilitado
				else
					data.setHabilitado(true); //Habilitado

				data.setDescripcion(rs.getString(5)); //DescripcionParametro
				darParametros.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return darParametros;
	}

	public  List<Tareas> DarTareas(String consulta) 
	{
		List<Tareas> lista = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				Tareas t = new Tareas();

				t.setTarea(rs.getInt(1));
				t.setObservaciones(rs.getString(2));
				t.setUsuario(rs.getString(3));
				try {
					t.setPrimerPicking(rs.getString(4).split(" ")[0]+"<br> "+rs.getString(4).split(" ")[1]);
				} catch (Exception e) {
					t.setPrimerPicking(rs.getString(4));
				}
				try {
					t.setUltimoPicking(rs.getString(5).split(" ")[0]+"<br> "+rs.getString(5).split(" ")[1]);
				} catch (Exception e) {
					t.setUltimoPicking(rs.getString(5));
				}

				t.setUltimaLinea(rs.getInt(6));
				t.setTotalLineas(rs.getInt(7));
				t.setTiempo(rs.getInt(8));
				lista.add(t);

			}

			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public List<DataPedidoArticuloEcommerceVerif_ar> darListaPedidosVerificadosArt_ar(String consulta) 
	{
		List<DataPedidoArticuloEcommerceVerif_ar>  lista = new ArrayList<DataPedidoArticuloEcommerceVerif_ar>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataPedidoArticuloEcommerceVerif_ar d = new DataPedidoArticuloEcommerceVerif_ar(rs.getString(1),rs.getString(2),rs.getString(5),rs.getInt(3),rs.getInt(4));
				if(d.getIdOjo()==null)
				{

					Statement s2 = connection.createStatement();
					ResultSet rs2 = s2.executeQuery("select GROUP_CONCAT(T.idOjo) from ojostienenarticulos T inner join ojos O on O.idOjo = T.idOjo where idArticulo = '"+d.getIdArticulo()+"' and O.idEstanteria in (108,109,110,111,112,113,114) group by T.idArticulo");

					while (rs2.next())
					{
						d.setIdOjo(rs2.getString(1));

					}

				}

				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<DataIDDescripcion> darListaECSinPedir(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataIDDescripcion d;
				try{
					d = new DataIDDescripcion(rs.getInt(1), rs.getString(2),new Long(rs.getInt(3)),rs.getInt(4));
					lista.add(d);
				}
				catch(Exception e){

				}				
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  Hashtable<String, String> DarNoRemplazarDistribuciones(String consulta) 
	{
		Hashtable<String, String> lista = new Hashtable<>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				try{
					lista.put(rs.getString(1)+"-"+rs.getInt(2)+"-"+rs.getInt(3)+"-"+rs.getInt(4), 
							rs.getString(1)+"-"+rs.getInt(2)+"-"+rs.getInt(3)+"-"+rs.getInt(4));
				}
				catch(Exception e){

				}				
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public  List<ReportObject> DarDistribucionesPorPicking(String consulta) 
	{
		List<ReportObject> lista = new ArrayList<>();

		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				ReportObject o = new ReportObject();
				o.setInt1(rs.getInt(1));
				o.setInt2(rs.getInt(2));
				o.setInt3(rs.getInt(3));
				o.setInt4(rs.getInt(4));
				o.setStr1(rs.getString(5));
				o.setStr2(rs.getString(6));
				o.setStr3(rs.getString(7));
				o.setStr4(rs.getString(8));

				lista.add(o);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public Hashtable<String, bulto> DarBultosAbiertos(String consulta, String consulta2, String consulta3, String consulta4, int idEmpersa) 
	{
		Hashtable<String, bulto> lista = new Hashtable<>();
		bulto b = null;
		Usuario u = null;
		bultoContenido bc = null;
		
		String idsBultos = "";
		
		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			boolean pri = true;
			Statement subs;
			ResultSet subrs;
			while (rs.next())
			{

				
				if(pri) 
				{
					pri=false;
					idsBultos +="'"+rs.getString(1)+"'";
					
				}
				else
				{
					idsBultos +=",'"+rs.getString(1)+"'";
				}
				
				b = new bulto(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getInt(6), rs.getInt(7), rs.getInt(8),
						rs.getDouble(9), rs.getBoolean(10), rs.getString(4), rs.getInt(5),rs.getString(11),idEmpersa);
				b.setEquipo_trabajo(rs.getInt(15));
				b.setRemision_al_cerrar(rs.getInt(17) == 1);
				b.setPosSort(rs.getString(18));
				b.setPedido(rs.getLong(19));
				b.setNumerador(rs.getInt(20));
				b.setDescDestino(rs.getString(21));

				

				lista.put(b.getEquipo_trabajo()+"-"+b.getPosSort(), b);
			}
			
			if(!idsBultos.equals("")) {
				subs = connection.createStatement();
				subrs = subs.executeQuery(consulta2.replace("@@", idsBultos));
				while (subrs.next())
				{
					if(subrs.getString(2).equalsIgnoreCase("1220202082021073308")) {
						System.out.println("caso");
					}
					bc = new bultoContenido();
					bc.setIdArticulo(subrs.getString(3));
					bc.setCantidad(subrs.getInt(4));
					bc.setPicking(subrs.getInt(5));
					bc.setRecepcion(subrs.getInt(6));
					bc.setFecha(subrs.getString(7));
					bc.setUsuario(subrs.getInt(8));
					
					bulto bu =lista.get(subrs.getString(1)); 
					
					bu.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(), bc);
				}
				//desconectar(subrs,null, subs);

				subs = connection.createStatement();
				subrs = subs.executeQuery(consulta3.replace("@@", idsBultos));
				while (subrs.next())
				{
					
					bulto bu =lista.get(subrs.getString(1));
					bu.getCaracteristicas().add(subrs.getString(3).toUpperCase()+" "+subrs.getString(4).toUpperCase());
				}
				//desconectar(subrs,null, subs);

				subs = connection.createStatement();
				subrs = subs.executeQuery(consulta4.replace("@@", idsBultos));
				while (subrs.next())
				{
					bulto bu =lista.get(subrs.getString(1));
					bu.getRemitos().add(new DataIDDescripcion(subrs.getInt(3),subrs.getString(4)));
				}
			}
			
			
			//desconectar(subrs,null, subs);
			
			
			
			
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	public Hashtable<Long, Integer> Numerador_bultos(String consulta, int idEmpersa) 
	{
		 Hashtable<Long, Integer> lista = new  Hashtable<Long, Integer>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				lista.put(rs.getLong(1), rs.getInt(2));
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public bulto DarBulto(String consulta, String consulta2, String consulta3, String consulta4, int idEmpersa) 
	{
		bulto b = null;
		Usuario u = null;
		bultoContenido bc = null;
		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			Statement subs;
			ResultSet subrs;
			while (rs.next())
			{

				b = new bulto(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getInt(6), rs.getInt(7), rs.getInt(8),
						rs.getDouble(9), rs.getBoolean(10), rs.getString(4), rs.getInt(5),rs.getString(11),idEmpersa);
				b.setEquipo_trabajo(rs.getInt(15));
				b.setRemision_al_cerrar(rs.getInt(17)==1);
				b.setPosSort(rs.getString(18));
				b.setPedido(rs.getLong(19));
				b.setNumerador(rs.getInt(20));

				subs = connection.createStatement();
				subrs = subs.executeQuery(consulta2.replace("@@", b.getIdBulto()));
				while (subrs.next())
				{
					bc = new bultoContenido();
					bc.setIdArticulo(subrs.getString(2));
					bc.setCantidad(subrs.getInt(3));
					bc.setPicking(subrs.getInt(4));
					bc.setRecepcion(subrs.getInt(5));
					bc.setFecha(subrs.getString(6));
					bc.setUsuario(subrs.getInt(7));
					bc.setCantidadReservada(subrs.getInt(9));
					b.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(), bc);
				}
				//desconectar(subrs,null, subs);

				subs = connection.createStatement();
				subrs = subs.executeQuery(consulta3.replace("@@", b.getIdBulto()));
				while (subrs.next())
				{
					b.getCaracteristicas().add(subrs.getString(2).toUpperCase()+" "+subrs.getString(3).toUpperCase());
				}
				//desconectar(subrs,null, subs);

				subs = connection.createStatement();
				subrs = subs.executeQuery(consulta4.replace("@@", b.getIdBulto()));
				while (subrs.next())
				{
					b.getRemitos().add(new DataIDDescripcion(subrs.getInt(4),subrs.getString(3)));
				}
				//desconectar(subrs,null, subs);


			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return b;
	}

	public  List<DataIDDescripcion> DarDescripcionArticulos(Hashtable<String, DataIDDescripcion> hash, String consulta) 
	{
		List<DataIDDescripcion> lista;

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				if(hash.get(rs.getString(1))!=null){
					hash.get(rs.getString(1)).setDescripcionC(rs.getString(2));
				}
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>(hash.values()) ;
		}

		lista = new ArrayList<>(hash.values());
		return lista;
	}

	public Long DarIdPedidoXDistr(String query) 
	{
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			Long retorno = (long) 0;
			while (rs.next())
			{
				retorno= rs.getLong(1);
			}
			desconectar(rs,null, s);
			return retorno;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return (long) 0 ;
		}


	}



	public String DarTrackingXpedido(String query) 
	{
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);
			String retorno = "";
			while (rs.next())
			{
				retorno= rs.getString(1);
			}
			desconectar(rs,null, s);
			return retorno;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "" ;
		}


	}

	public Hashtable<Integer, List<DataDescDescripcion>> DarIdPedidosXDistr(String query) 
	{
		Hashtable<Integer, List<DataDescDescripcion>> retorno  = new Hashtable<Integer, List<DataDescDescripcion>>();
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next())
			{
				if(retorno.get(rs.getInt(2))!=null)
				{
					retorno.get(rs.getInt(2)).add(new DataDescDescripcion(rs.getString(1), rs.getString(3),rs.getString(2)));
					System.out.print(rs.getString(1)+"-");
					System.out.print(rs.getString(2)+"-");
					System.out.println(rs.getString(3));
				}
				else
				{
					List<DataDescDescripcion> toadd = new ArrayList<>();
					System.out.print(rs.getString(1)+"-");
					System.out.print(rs.getString(2)+"-");
					System.out.println(rs.getString(3));
					toadd.add(new DataDescDescripcion(rs.getString(1), rs.getString(3),rs.getString(2)));
					retorno.put(rs.getInt(2), toadd);
				}

			}
			desconectar(rs,null, s);
			return retorno;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return retorno;
		}


	}

	public List<DataDescDescripcion> darListaDataDescDescripcion(String query) 
	{
		List<DataDescDescripcion> retorno = new ArrayList<>();
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next())
			{
				retorno.add(new DataDescDescripcion(rs.getString(1), rs.getString(2)));
			}
			desconectar(rs,null, s);
			return retorno;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return retorno;
		}

	}


	public List<DataIndicador> darListaIndicadoresEncuentra(String consulta) 
	{
		List<DataIndicador> retorno = new ArrayList<>();
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			DecimalFormat d1 = new DecimalFormat("##.#");
			while (rs.next())
			{   

				Double var = rs.getDouble(5);
				String str = d1.format(var).replace(",", ".");
				Double formateado = Double.parseDouble(str);
				DataIndicador d = new DataIndicador(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), formateado, rs.getDouble(6));
				try
				{
					int idInt3=rs.getInt(7);
					d.setIdInt3(idInt3);
				}
				catch (Exception e) 
				{
					d.setIdInt3(0);
				}


				retorno.add(d);
			}
			desconectar(rs,null, s);
			return retorno;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return retorno;
		}
	}

	public  List<MovStock> darQueueMovsStock(String consulta) 
	{
		List<MovStock> lista = new ArrayList<>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				System.out.println(rs.getString(1));
				MovStock m = new MovStock();
				m.setId(rs.getInt(1));
				m.setEstado(rs.getInt(2));
				m.setDestino(rs.getInt(3));
				m.setDoc(rs.getInt(4));
				m.setCantidad(rs.getInt(5));
				m.setFecha(rs.getString(6).split(" ")[0]+"<br> "+rs.getString(6).split(" ")[1]);
				m.setObservacion(rs.getString(7));
				m.setUsuario(rs.getNString(8));
				m.setOrigen(rs.getInt(9));
				m.setOrigenDoc(rs.getLong(10));
				m.setIntentos(rs.getInt(11));
				m.setNombreDestino(rs.getString(12));

				lista.add(m);				
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}

	public  MovStock darMovsStock(String consulta, String subConsulta) 
	{
		MovStock m = new MovStock();

		try 
		{	
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				System.out.println(rs.getString(1));

				m.setId(rs.getInt(1));
				m.setEstado(rs.getInt(2));
				m.setDestino(rs.getInt(3));
				m.setDoc(rs.getInt(4));
				m.setCantidad(rs.getInt(5));
				m.setFecha(rs.getString(6).split(" ")[0]+"<br> "+rs.getString(6).split(" ")[1]);
				m.setObservacion(rs.getString(7));
				m.setUsuario(rs.getNString(8));
				m.setOrigen(rs.getInt(9));
				m.setOrigenDoc(rs.getLong(10));
				m.setIntentos(rs.getInt(11));

				List<DataIDDescripcion> lista = new ArrayList<>();
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery(subConsulta);
				while (rs2.next()) {
					DataIDDescripcion data = new DataIDDescripcion(rs2.getInt(1),rs2.getString(2));
					if(m.getDestino()==1200) {
						data.setIdB(m.getDestino());
					}
					lista.add(data);
				}

				m.setDetalle(lista);
				rs2.close();
				s2.close();
			}
			desconectar(rs,null, s);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return m;
	}

	public  List<DataListaPicking> darListaPickingExport(String consulta) 
	{
		List<DataListaPicking> lista = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{
			//connection = getConnection(); 
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				System.out.println(rs.getString(1));
				try {
					DataListaPicking dl = new DataListaPicking(rs.getLong(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
					lista.add(dl);	
				} catch (Exception e) {
					//Si se cae trayendo el pedido lo pongo en 0
					DataListaPicking dl = new DataListaPicking(0L,rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
					lista.add(dl);	
				}
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}

	public String darParametroEmpresa(String consulta) 
	{
		try {
			Statement s = connection.createStatement();
			connection=connect();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				return rs.getString(1);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return null;
	}

	
	public List<DataIDDescripcion> darParametrosEmpresa(String consulta) 
	{
		List<DataIDDescripcion> retorno = new ArrayList<>();
		try {
			Statement s = connection.createStatement();
			connection=connect();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				retorno.add(new DataIDDescripcion(rs.getInt(1), rs.getString(2)));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}
	
	public List<DataPosiblePedido> ClasificacionDarPedidos(String consulta) 
	{
		List<DataPosiblePedido> retorno = new ArrayList<>();
		try {
			Statement s = connection.createStatement();
			connection=connect();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				DataPosiblePedido dp = new DataPosiblePedido(rs.getLong(1), rs.getString(2), rs.getInt(3));
				retorno.add(dp);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}

	public List<String> darSeguridadUI(String consulta) 
	{

		List<String> retorno = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				retorno.add(rs.getString(1));

			}



			desconectar(rs,null, s);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return retorno;
	}

	public List<DataPedidoPickup> darDatosPedidosPickupDetalle(String consulta) 
	{
		List<DataPedidoPickup> retorno = new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			connection=connect();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next()){
				DataPedidoPickup data = new DataPedidoPickup(rs.getString(1),rs.getString(2),rs.getString(3),null,rs.getInt(4),rs.getInt(5));
				try
				{
					data.setUbicacion(rs.getString(6));
				}
				catch (Exception e) 
				{
					e.printStackTrace();

				}
				retorno.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public  List<DataArtPedidoPickup> darArticulosPedidoEcommercePickup(String consulta) 
	{
		List<DataArtPedidoPickup> lista = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{
			getConnection();
			ResultSet rs = s.executeQuery(consulta);


			while (rs.next())
			{
				//idArticulo  //Descripcion  //Cantidad() //Cantidad Pendiente// canal
				DataArtPedidoPickup d = new DataArtPedidoPickup(rs.getString(1),rs.getString(2),0, rs.getInt(4),rs.getInt(3),rs.getInt(5));
				d.setDeposito(rs.getInt(6));

				lista.add(d);
				System.out.println("");
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public Double darCostoEnvioPedido(String q) 
	{
		try (Statement s = connection.createStatement();)
		{
			getConnection();
			ResultSet rs = s.executeQuery(q);


			while (rs.next())
			{

				return rs.getDouble(1);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0.0;
		}
		return 0.0;




	}

	public Cliente darClienteShipping(String q) 
	{
		try (Statement s = connection.createStatement();)
		{
			getConnection();
			ResultSet rs = s.executeQuery(q);


			while (rs.next())
			{

				Cliente c = new Cliente();
				c.setNombre(rs.getString(1));
				c.setApellido(rs.getString(2));
				c.setCalle(rs.getString(3).replace("\"", ""));
				c.setNroPuerta("");
				c.setCiudad(rs.getString(4));
				c.setDepartamento(rs.getString(5));
				c.setCp(rs.getString(6));
				c.setTelefono(rs.getString(7));
				c.setEmail(rs.getString(8));
				c.setDocumentoNro(rs.getString(9));
				c.setObs(rs.getString(11));
				c.setNroPuerta(rs.getString(12));
				c.setNroApto(rs.getString(13));
				return c;

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return null;
	}
	public Hashtable<Long, Cliente> darClienteSshipping(String q) 
	{
		Hashtable<Long, Cliente> retorno = new Hashtable<>();
		try (Statement s = connection.createStatement();)
		{
			 
			getConnection();
			ResultSet rs = s.executeQuery(q);

			while (rs.next())
			{

				Cliente c = new Cliente();
				c.setNombre(rs.getString(1));
				c.setApellido(rs.getString(2));
				c.setCalle(rs.getString(3).replace("\"", ""));
				c.setNroPuerta("");
				c.setCiudad(rs.getString(4));
				c.setDepartamento(rs.getString(5));
				c.setCp(rs.getString(6));
				c.setTelefono(rs.getString(7));
				c.setEmail(rs.getString(8));
				c.setDocumentoNro(rs.getString(9));
				c.setObs(rs.getString(11));
				c.setNroPuerta(rs.getString(12));
				c.setNroApto(rs.getString(13));
				retorno.put(rs.getLong(14), c);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return retorno;
	}

	////////////////////////////////////////////////////////////OPERACIONES TIENDA////////////////////////////////////////////////////////////////

	public List<RecepcionExpedicion> darListaEnviosRec(String q) 
	{
		List<RecepcionExpedicion> lista = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{
			//connection = getConnection(); 
			
			ResultSet rs = s.executeQuery(q);
			boolean pri = true;

			int idEnvioAnt = 0;
			int idEnvio=0;
			int sumaUnidades = 0;
			List <DocumentoEnvio> documentos = null;
			RecepcionExpedicion d = null;

			while (rs.next())
			{
				idEnvio = rs.getInt(1);

				if(pri)
				{
					Fecha fecha = new Fecha(rs.getString(2));
					d = new RecepcionExpedicion();

					d.setIdEnvio(idEnvio);
					d.setFecha(fecha.darFechadia_mes_Anio_hhmmBarra_SM());
					d.setChoferAc(rs.getString(3));

					documentos = new ArrayList<>();
					DocumentoEnvio de = new DocumentoEnvio();
					de.setCantidad(rs.getInt(9));
					de.setNumeroDoc(rs.getInt(6));
					de.setDepositoD(new DataIDDescripcion(rs.getInt(5),rs.getString(11)));
					de.setDepositoO(new DataIDDescripcion(rs.getInt(4), rs.getString(10)));
					de.setRazon(new DataIDDescripcion(rs.getInt(8), rs.getString(12)));

					documentos.add(de);

					sumaUnidades+=rs.getInt(9);


					pri=false;
				}
				else
				{
					if(idEnvio==idEnvioAnt)
					{
						DocumentoEnvio de = new DocumentoEnvio();
						de.setCantidad(rs.getInt(9));
						de.setNumeroDoc(rs.getInt(6));
						de.setDepositoD(new DataIDDescripcion(rs.getInt(5),rs.getString(11)));
						de.setDepositoO(new DataIDDescripcion(rs.getInt(4), rs.getString(10)));
						de.setRazon(new DataIDDescripcion(rs.getInt(8), rs.getString(12)));
						documentos.add(de);
						sumaUnidades+=rs.getInt(9);


					}
					else
					{
						d.setUnidades(sumaUnidades);
						d.setDocumentos(documentos);

						lista.add(d);
						sumaUnidades = 0;


						Fecha fecha = new Fecha(rs.getString(2));
						d = new RecepcionExpedicion();

						d.setIdEnvio(idEnvio);
						d.setFecha(fecha.darFechadia_mes_Anio_hhmmBarra_SM());
						d.setChoferAc(rs.getString(3));

						documentos = new ArrayList<>();
						DocumentoEnvio de = new DocumentoEnvio();
						de.setCantidad(rs.getInt(9));
						de.setNumeroDoc(rs.getInt(6));
						de.setDepositoD(new DataIDDescripcion(rs.getInt(5),rs.getString(11)));
						de.setDepositoO(new DataIDDescripcion(rs.getInt(4), rs.getString(10)));
						de.setRazon(new DataIDDescripcion(rs.getInt(8), rs.getString(12)));
						documentos.add(de);

						sumaUnidades+=rs.getInt(9);



					}

				}

				idEnvioAnt=idEnvio;
			}//fin del while

			if(d!=null)
			{
				d.setUnidades(sumaUnidades);
				d.setDocumentos(documentos);

				lista.add(d);
			}


			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public List<ConteoTiendas> darConteoTienda(String qu, int idEmpresa) 
	{
		List<ConteoTiendas> lista = null;

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(qu);
			Hashtable<Integer, ConteoTiendas> conteos = new Hashtable<>();
			Hashtable<Integer, String> articulosXdepo = new Hashtable<>();

			while (rs.next())
			{
				DataIDDescripcion uc = new DataIDDescripcion(rs.getInt(14), rs.getString(15));
				boolean lineaIn = false;
				if(rs.getInt(17)==1)
				{
					lineaIn = true;
				}
				String fechaContada = "";

				if(!rs.getString(13).equals(rs.getString(7)))
				{
					Fecha fc = new Fecha(rs.getString(13));
					fechaContada = fc.darFecha_mes_dia_Anio_hhmmBarra_SM();
				}

				ArticuloConteo ac = new ArticuloConteo(rs.getString(11),fechaContada,rs.getString(16),rs.getInt(12),uc,lineaIn);

				if(articulosXdepo.get(rs.getInt(5))==null)
				{

					articulosXdepo.put(rs.getInt(5),"'"+rs.getString(11)+"'");
				}
				else
				{
					String arts = articulosXdepo.get(rs.getInt(5));
					arts = arts+",'"+rs.getString(11)+"'";
					articulosXdepo.put(rs.getInt(5),arts);
				}



				if(conteos.get(rs.getInt(1))==null)
				{
					ConteoTiendas c = new ConteoTiendas();
					c.setIdConteo(rs.getInt(1));
					c.setDescripcion(rs.getString(2));
					c.setUsuarioPide(new DataIDDescripcion(rs.getInt(3),rs.getString(4)));
					c.setDeposito(rs.getInt(5));
					c.setNombreDepo(rs.getString(6));
					Fecha f = new Fecha(rs.getString(7));
					c.setFecha(f.darFechaDia_Mes_Anio_Barra());
					int open = rs.getInt(8);
					if(open==1)
					{
						c.setAbierto(true);
					}
					else
					{
						c.setAbierto(false);
					}
					c.setTotalArticulos(rs.getInt(9));
					c.setTotalUnidades(rs.getInt(10));

					List<ArticuloConteo> listaAs = new ArrayList<>();
					listaAs.add(ac);
					c.setArticulos(listaAs);

					conteos.put(c.getIdConteo(),c);

				}
				else
				{
					conteos.get(rs.getInt(1)).getArticulos().add(ac);

				}

			}//fin del while
			desconectar(rs,null, s);

			lista = new ArrayList<>(conteos.values());


			Hashtable<Integer, Hashtable<String, DataIDDescripcion>> stockArticulosXdepo = new Hashtable<>();
			Set<Integer> keys = articulosXdepo.keySet();
			Iterator<Integer> itr = keys.iterator();
			while (itr.hasNext()) 
			{ 
				int idDepo = itr.next();
				String q = "select stock, MS.idArticulo , AR.DescCorta from MovStockTotal MS inner join Articulo AR on MS.IdArticulo = AR.IdArticulo where IdDeposito =14 and MS.IdArticulo in ("+articulosXdepo.get(idDepo)+")";
				List<DataIDDescripcion> stoks = MSSQL.darStockArtTienda(q,idDepo,idEmpresa);
				Hashtable<String, DataIDDescripcion> stocksHT = new Hashtable<>();
				for (DataIDDescripcion a : stoks) 
				{
					stocksHT.put(a.getDescripcion(), new DataIDDescripcion(a.getId(),a.getDescripcionB()));

				}

				stockArticulosXdepo.put(idDepo,stocksHT);
			} 


			for (ConteoTiendas c : lista) 
			{
				for (ArticuloConteo ac : c.getArticulos()) 
				{
					DataIDDescripcion stockDesc = stockArticulosXdepo.get(c.getDeposito()).get(ac.getArticulo());
					if(stockDesc!=null)
					{
						ac.setStock(stockDesc.getId());
						ac.setDescripcion(stockDesc.getDescripcion());
					}

				}
			}


		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}


	public  List<DataArtPedidoPickup> darArticulosOrderProcessEcommercePickup(String consulta) 
	{
		List<DataArtPedidoPickup> lista = new ArrayList<>();

		try (Statement s = connection.createStatement(); ResultSet rs = s.executeQuery(consulta);)
		{
			getConnection();

			while (rs.next())
			{
				DataArtPedidoPickup d = new DataArtPedidoPickup(rs.getString(2),rs.getString(3),rs.getInt(5), rs.getInt(1),0,0);

				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}

	public List<DataPedidoPickup> darDatosPedidosPickup (String consulta){

		List<DataPedidoPickup> retorno = new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			connection=connect();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next()){
				DataPedidoPickup data = new DataPedidoPickup(rs.getString(1),rs.getString(2),rs.getString(3),null,0,0);
				retorno.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public List<DataIndicadorPicking> darListaIndicadoresPicking(String consulta) 
	{

		Hashtable<Integer, DataIndicadorPicking> indicadores = new Hashtable<>();


		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				DataIndicadorPickingLinea linea = new DataIndicadorPickingLinea
						(
								rs.getString(1),
								rs.getString(2), 
								rs.getString(3),
								rs.getString(4), 
								rs.getString(5), 
								rs.getString(6), 
								rs.getString(7), 
								rs.getInt(8),
								rs.getInt(9), 
								rs.getInt(10), 
								rs.getInt(11),
								rs.getInt(12), 
								rs.getInt(13), 
								rs.getInt(14),
								rs.getInt(15), 
								rs.getInt(16),
								rs.getInt(17),
								rs.getString(18),
								rs.getInt(19),
								rs.getString(20),
								rs.getLong(21));


				if(indicadores.get(rs.getInt(13))==null)
				{
					DataIndicadorPicking indicador = new DataIndicadorPicking(
							rs.getString(22).replace(" ", "&nbsp;<br/>"),
							rs.getString(23).replace(",", "&nbsp;<br/>"), 
							rs.getString(24).replace(" ", "&nbsp;<br/>"),
							rs.getString(25).replace(" ", "&nbsp;<br/>"), 
							rs.getString(26),
							rs.getString(27).replace(" ", "&nbsp;<br/>"), 
							rs.getString(28).replace(" ", "&nbsp;<br/>"), 
							rs.getInt(29), 
							rs.getInt(30), 
							rs.getInt(31), 
							rs.getInt(32), 
							rs.getInt(33), 
							rs.getInt(34),
							rs.getInt(35), 
							rs.getDouble(36), 
							rs.getDouble(37),
							rs.getDouble(38));

					List<DataIndicadorPickingLinea> lineas = new ArrayList<>();
					lineas.add(linea);
					indicador.setLineas(lineas);
					indicadores.put(rs.getInt(13), indicador);
				}
				else
				{
					indicadores.get(rs.getInt(13)).getLineas().add(linea);
				}

			}

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		System.out.println(indicadores.size());
		return new ArrayList<>(indicadores.values());
	}

	public  List<trackingPedido> darPedidosATrackear(String consulta) 
	{
		List<trackingPedido> lista = new ArrayList<>();

		try (Statement s = connection.createStatement();)
		{
			//connection = getConnection(); 
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				trackingPedido t;
				try{
					t = new trackingPedido();
					t.setIdPedido(rs.getLong(1));
					t.setIdEcommerce(rs.getInt(2));
					t.setCanal(rs.getInt(3));
					t.setCourier(rs.getString(4));
					t.setTracking(rs.getString(5));
					t.setUser(rs.getString(6));
					t.setPass(rs.getString(7));
					t.setDestinoFinal(rs.getInt(8));

					lista.add(t);
				}
				catch(Exception e){

				}				
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}

	public  List<DataIDDescripcion> darTarjetaEstadisticasVenta(String consulta) 
	{
		List<DataIDDescripcion> lista = new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			connection=connect();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				DataIDDescripcion d = new DataIDDescripcion(rs.getInt(2), rs.getInt(3),rs.getString(1));
				lista.add(d);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}

	public  HashMap<String, Integer> darTotalizadores(String consulta) 
	{
		HashMap<String, Integer> hashMT = new HashMap<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				hashMT.put(rs.getInt(1)+","+rs.getInt(2), rs.getInt(3));
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new HashMap<>();
		}

		return hashMT;
	}

	public  Hashtable<Integer, String> darIdDescripcionHash(String consulta) 
	{
		Hashtable<Integer, String> hashT = new Hashtable<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				hashT.put(rs.getInt(1), rs.getString(2));
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new Hashtable<>();
		}

		return hashT;
	}

	public List<MovsXArticulo> darListaMovsXArticulo(String consulta) 
	{
		List<MovsXArticulo> lista = new ArrayList<>();

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				MovsXArticulo mva = new MovsXArticulo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				lista.add(mva);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}

	public List<InventarioXUbicacion> darListaInventXUbi(String consulta) 
	{
		System.out.println(consulta);
		List<InventarioXUbicacion> lista = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				String estanterias = rs.getString(9);

				if(estanterias.contains(","))
				{
					Hashtable<String, String> hashEst = new Hashtable<>();
					String[]arrEst = estanterias.split(",");
					for (int i = 0; i < arrEst.length; i++) 
					{
						hashEst.put(arrEst[i],arrEst[i]);
					}
					List<String>listEst = new ArrayList<>(hashEst.values());
					estanterias = "";
					for (String e : listEst) 
					{
						estanterias+=e+" ";
					}

				}


				InventarioXUbicacion mva = new InventarioXUbicacion(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8), estanterias);
				lista.add(mva);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}

	public List<inventDisponible> darListainventDisponible(String consulta) 
	{
		System.out.println(consulta);
		List<inventDisponible> lista = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				inventDisponible mva = new inventDisponible(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
				lista.add(mva);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}

	public List<ProductividadPicking> darListaReporteProductividadPicking(String consulta1, String consulta2) 
	{
		System.out.println(consulta1);
		System.out.println(consulta2);
		List<ProductividadPicking> lista = new ArrayList<>();
		
		Hashtable<Integer, ProductividadPicking> pickings = new Hashtable<>();
		
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta1);			
			while (rs.next())
			{
				ProductividadPicking pp = new ProductividadPicking(rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
						rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
				pickings.put(rs.getInt(1), pp);
			}
			rs = s.executeQuery(consulta2);
			while(rs.next()) {
				if(pickings.containsKey(rs.getInt(1))) {
					ProductividadPicking picking = pickings.get(rs.getInt(1));
					ProductividadPicking pp = new ProductividadPicking(rs.getString(1), picking.getFecha(), picking.getClase(),
							picking.getCategoria(), picking.getUsuarioPicking(), picking.getSolicitadas(),
							picking.getVerificadas(), picking.getRemitidas(), picking.getTiempoPicking(),
							picking.getCantidadLineas(), picking.getCantidadPosiciones(), rs.getString(2), rs.getString(3));
					lista.add(pp);
				}				
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}
	
	//PedidosProcesadosXOperario
	public List<PedidosProcesadosXOperario> darListaPedidosProcesadosXOperario(String consulta1) 
	{
		System.out.println(consulta1);
		List<PedidosProcesadosXOperario> lista = new ArrayList<>();
		
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta1);			
			while (rs.next())
			{
				
				PedidosProcesadosXOperario ppxo = new PedidosProcesadosXOperario(rs.getString(1),rs.getString(2),rs.getString(3));
				lista.add(ppxo);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}
	
	//PedidosRetrasados
	public List<PedidosRetrasados> darListaPedidosRetrasados(String consulta1) 
	{
		System.out.println(consulta1);
		List<PedidosRetrasados> lista = new ArrayList<>();
		
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta1);			
			while (rs.next())
			{
				
				PedidosRetrasados pr = new PedidosRetrasados(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				lista.add(pr);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}
	
	public List<Picking> darListaReportePicking(String consulta) 
	{
		System.out.println(consulta);
		List<Picking> lista = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				Picking p = new Picking(rs.getString(1),rs.getString(2), //nroSol, art     **  1 2
						rs.getString(4),rs.getString(3),rs.getString(5),//clase, seccion, marca   ***  4 3 5
						rs.getString(8),rs.getString(13), //ubicacion destino    8  13
						rs.getString(14),rs.getString(18),rs.getString(19), //estado nroDoc nroCaja  14  18  19
						rs.getString(15),rs.getString(16), //fechaSol fechaDoc  15  16
						rs.getString(17)); //usu  17
				lista.add(p);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}
	
	public List<Picking2> darListaReportePicking2(String consulta) 
	{
		System.out.println(consulta);
		List<Picking2> lista = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				Picking2 p = new Picking2(rs.getString(1),rs.getString(2),rs.getString(3), 
						rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
						rs.getString(12),rs.getString(13),rs.getString(14), 
						rs.getString(15),rs.getString(16), 
						rs.getString(17),rs.getNString(18), rs.getString(19)); 
				lista.add(p);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}
	
	public List<BultosRangoFechaDestino> darListaBultosRangoDestino(String consulta) 
	{
		System.out.println(consulta);
		List<BultosRangoFechaDestino> lista = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				BultosRangoFechaDestino b = new BultosRangoFechaDestino(rs.getString(1),rs.getString(2),
						rs.getString(3),rs.getString(4));
				lista.add(b);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}
	
	

	
	
	public List<StockEncuentraVisual> darListaStockEncuentraVisual(String consulta) 
	{
		System.out.println(consulta);
		List<StockEncuentraVisual> lista = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				String strOjosCant = "[";
				strOjosCant += rs.getString(3);
				strOjosCant += "]";
				
				Gson gson = new Gson();
				List<DataIDDescripcion> listaOjosCantidad = gson.fromJson(strOjosCant, new TypeToken<List<DataIDDescripcion>>(){}.getType());				
				StockEncuentraVisual sev = new StockEncuentraVisual(rs.getString(1),rs.getString(2), "--",listaOjosCantidad);
				lista.add(sev);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	
	public List<ExpedicionMovimiento> darListaExpedicionMovimiento(String consulta) 
	{
		System.out.println(consulta);
		List<ExpedicionMovimiento> lista = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				ExpedicionMovimiento em = new ExpedicionMovimiento(rs.getString(1),rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6), 
						rs.getString(7),rs.getString(8),rs.getString(9), rs.getString(10));
				lista.add(em);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}
	
	

	public List<DataLineaRepo> darArticulosQueNoEstanEnReposicionArt(String consulta) 
	{
		System.out.println(consulta);
		List<DataLineaRepo> lista = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				DataLineaRepo mva = new DataLineaRepo();
				mva.setPedido(rs.getLong(1));
				mva.setIdArticulo(rs.getString(2));
				mva.setIdDepOrigen(rs.getInt(3));
				mva.setSolicitada(rs.getInt(4));
				lista.add(mva);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	public List<AjustesDiferencias> darListaAjustesDiferencias(String consulta) 
	{
		System.out.println(consulta);
		List<AjustesDiferencias> lista = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				AjustesDiferencias ad = new AjustesDiferencias(rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11));
				lista.add(ad);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	
	public List<FrecuenciaUbicacionesProductos> darListaFrecuenciasUbicacionesArticulos(String consulta) 
	{
		System.out.println(consulta);
		List<FrecuenciaUbicacionesProductos> lista = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				FrecuenciaUbicacionesProductos fup = new FrecuenciaUbicacionesProductos(rs.getString(2),rs.getString(1),
						rs.getString(3),rs.getString(4));
				lista.add(fup);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	//
	public List<CumplimientoOrdenes> darListaCumplimientoOrdenes(String consulta) 
	{
		System.out.println(consulta);
		List<CumplimientoOrdenes> lista = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				CumplimientoOrdenes co = new CumplimientoOrdenes(rs.getString(1),rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5));
				lista.add(co);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public List<DTO_ArticuloCantidad> buscarContenidoBulto(String idBulto) throws Exception{
		List<DTO_ArticuloCantidad> contenido = new ArrayList<DTO_ArticuloCantidad>();
		String query = "Select idArticulo, cantidad from bulto_contenido bc where bc.idBulto = '"+idBulto+"'";
		System.out.println(query);
		Connection con = this.connect();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(query);
		while(rs.next()) {
			DTO_ArticuloCantidad articulo = new DTO_ArticuloCantidad(rs.getString(1), rs.getInt(2));
			contenido.add(articulo);
		}
		desconectar(rs,null, s);
		return contenido;
	}
	

	public boolean cambiarReserva(String bultoViejo, String bultoNuevo, String ojo, int idPicking, int idEmpresa, List<DataLineaRepo> contenido) throws InstantiationException, IllegalAccessException, Exception {
		boolean resultado = false;
		boolean fallo = false;
		String query = "UPDATE ojostienenarticulos SET reservada = 0 where idArticulo = '"+bultoViejo+"' AND idOjo = '"+ojo+"' AND idEmpresa = "+idEmpresa+";";
		System.out.println(query);
		Connection con = this.connect();
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		int rs = st.executeUpdate(query);
		if(rs > 0) {
			String consultaCantidades = "SELECT SUM(bc.cantidad)-SUM(bc.cantidadReservada) FROM bulto_contenido bc WHERE bc.idBulto = '"+bultoNuevo+"' AND idEmpresa = "+idEmpresa+";";
			System.out.println(consultaCantidades);
			Statement st2 = con.createStatement();
			ResultSet rs2 = st2.executeQuery(consultaCantidades);
			if(rs2.next() && rs2.getInt(1) <= 0) {
				String query2 = "UPDATE ojostienenarticulos SET reservada = 1 where idArticulo = '"+bultoNuevo+"' AND idOjo = '"+ojo+"' AND idEmpresa = "+idEmpresa+"";
				System.out.println(query2);
				Statement st8 = con.createStatement();
				int rs8 = st8.executeUpdate(query2);
				if(!(rs8 > 0)) {
					fallo = true;
				}
			}
			String query3 = "UPDATE reposicion_articulos_ojos SET idBulto = '"+bultoNuevo+"' where idBulto = '"+bultoViejo+"' AND idPicking = "+idPicking+" AND idEmpresa = "+idEmpresa+"";
			System.out.println(query3);
			Statement st3 = con.createStatement();
			int rs3 = st3.executeUpdate(query3);
			if(rs3 > 0) {
				
				for(DataLineaRepo articulo : contenido) {
					String query4 = "UPDATE bulto_contenido SET cantidadReservada = cantidadReservada + "+articulo.getSolicitada()+" WHERE idBulto = '"+bultoNuevo+"' AND idArticulo = '"+articulo.getIdArticulo()+"' AND idempresa = "+idEmpresa+"; ";
					System.out.println(query4);
					Statement st4 = con.createStatement();
					int rs4 = st4.executeUpdate(query4);
					if(!(rs4 > 0)) {
						fallo = true;
					}
					query4 = "UPDATE bulto_contenido SET cantidadReservada = cantidadReservada - "+articulo.getSolicitada()+" WHERE idBulto = '"+bultoViejo+"' AND idArticulo = '"+articulo.getIdArticulo()+"' AND idempresa = "+idEmpresa+"; ";
					System.out.println(query4);
					st4 = con.createStatement();
					rs4 = st4.executeUpdate(query4);
					if(!(rs4 > 0)) {
						fallo = true;
					}
				}
				
				if(!fallo) {
					resultado = true;
					con.commit();
				}
			}
		}
		if(resultado == false) { con.rollback(); }
		desconectar(null,null, st);
		return resultado;
	}
	
	public boolean TransferirReserva(String bultoViejo, String bultoNuevo, String ojo, int idPicking, int idEmpresa, List<DataLineaRepo> contenido) throws InstantiationException, IllegalAccessException, Exception {
		boolean resultado = false;
		boolean fallo = false;
		String query = "UPDATE reposicion_articulos_ojos SET idBulto = '"+bultoNuevo+"' where idBulto = '"+bultoViejo+"' AND idPicking = "+idPicking+" AND idEmpresa = "+idEmpresa+"";
		System.out.println(query);
		Connection con = this.connect();
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		int rs = st.executeUpdate(query);
		if(rs > 0) {
			String query2 = "UPDATE reposicion_articulos_ojos SET idBulto = '"+bultoViejo+"' where idBulto = '"+bultoNuevo+"' AND idPicking = "+idPicking+" AND idEmpresa = "+idEmpresa+"";			
			Statement st2 = con.createStatement();
			int rs2 = st2.executeUpdate(query);
			if(!(rs2 > 0)) {
				fallo = true;
			}
								
			if(!fallo) {
				resultado = true;
				con.commit();
			}
		}
	
		if(resultado == false) { con.rollback(); }
		desconectar(null,null, st);
		return resultado;
	}

	public int devolverIDDisponible(int idParametro, int idEmpresa) throws InstantiationException, IllegalAccessException, Exception {
        int utilizarId = 0;
        String query = "INSERT into ultimosids (idParametro, ultimoIdAsignado, idEmpresa) VALUES ("+idParametro+",1,"+idEmpresa+")\r\n" + 
        		"ON DUPLICATE KEY UPDATE ultimoIdAsignado = ultimoIdAsignado + 1;";
        System.out.println(query);
        Connection con = this.connect();
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        int rs = st.executeUpdate(query);
        String query2 = "SELECT ultimoIdAsignado FROM ultimosids WHERE idEmpresa = "+idEmpresa+" AND idParametro = "+ idParametro;
        System.out.println(query2);
        Statement st2 = con.createStatement();
        ResultSet rs2 = st2.executeQuery(query2);
        if(rs2.next()) {
            utilizarId = rs2.getInt(1);
            con.commit();
        } else {
            con.rollback();
        }
        
        return utilizarId;
    }
	
	public List<DataArticuloEcommerceVerifR> darSubPedidos(String idEcommerce, String subpedido, int estado, int idEmpresa){
		List<DataArticuloEcommerceVerifR> lista = new ArrayList<>();
		try {
			String query = "select idpedido,idFenicio,subpedido from ecommerce_pedido where idEmpresa="+idEmpresa;
			if(idEcommerce!= null && !idEcommerce.equals("")) {
				query += " and idfenicio = '"+idEcommerce+"' ";
			}
			if(subpedido!= null && !subpedido.equals("")) {
				query += " and subpedido = '"+subpedido+"' ";
			}
			
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);			
			while (rs.next())
			{
				DataArticuloEcommerceVerifR data = new DataArticuloEcommerceVerifR();
				data.setIdPedido(rs.getLong(1));
				data.setIdEcommerce(rs.getString(2));
				data.setSubpedido(rs.getString(3));
				data.setEstadoEncuentra(estado);
				lista.add(data);
			}
			desconectar(rs,null, s);
		} catch (Exception e) {
			return lista;
		}
		return lista;
	}

	public List<VORecepcionSinOrden> darPackingRecepcion(String consulta) 
	{
		System.out.println(consulta);
		List<VORecepcionSinOrden> lista = new LinkedList<VORecepcionSinOrden>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{

				VORecepcionSinOrden lineas = new VORecepcionSinOrden(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				lista.add(lineas);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}
	
	public List<bulto> darBultosToPrintRecepcion(String consulta, int idRecepcion, int idEmpresa) 
	{
		System.out.println(consulta);
		List<bulto> bultos = new LinkedList<bulto>();
		
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
										//idBulto      //
				bulto bul = new bulto(rs.getString(1), "Recepcion "+idRecepcion, true, 0, 0, 0, 0.0, false, "", 1119,"1",idEmpresa);
				List<bultoContenido> listaContenido = new ArrayList<>();
				bultoContenido bc = new bultoContenido(rs.getString(2),rs.getInt(3), idRecepcion, 1119);
				bul.Agregar_A_Bulto_NO_persist(bc);
				listaContenido.add(bc);
				bul.setContenidoList(listaContenido);
				bultos.add(bul);
			}
			desconectar(rs,null, s);
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return bultos;
	}
	

	public List<bulto> darBultosPicking(String consulta, int idEmpresa) 
	{
		System.out.println(consulta);
		Hashtable<String, bulto> bultos = new Hashtable<>();
		
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
			
				bultoContenido bc = new bultoContenido(rs.getString(10), rs.getInt(11), 0, rs.getInt(15));
				bc.setPicking(rs.getInt(12));
				bulto bul = null;
				if(bultos.containsKey(rs.getString(1)))
				{
					bul = bultos.get(rs.getString(1));
					
					
				}
				else
				{
					bul = new bulto(rs.getString(1), rs.getString(2), rs.getBoolean(3), 0, 0, 0, 0.0, false, rs.getString(4), rs.getInt(5),rs.getString(6) ,idEmpresa);
					bul.setPedido(rs.getLong(8));
					List<bultoContenido> lista = new ArrayList<>();
					bul.setContenidoList(lista);
					
				}
				
				
				bul.getContenidoList().add(bc);
				bultos.put(rs.getString(1),bul);
			}
			desconectar(rs,null, s);
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}

		return new ArrayList<>(bultos.values());
	}
	
	
	public EncuentraPedido darEncuentraPedido(String subpedido, int idEmpresa){
		EncuentraPedido p = null;
		try {
			String query = "SELECT p.idPedido, `descripcion`, `UnidadesTotal`, `EstadoEcommerce`, `URLetiqueta`, `FormaPago`, "
					+ "idCanalML,idFenicio,stamptime,ticketNumber,subpedido, shippingType, d.idDestino, d.destinoFinal " + 
					" FROM ecommerce_pedido p "+
					" LEFT OUTER JOIN ecommerce_pedido_destino d on p.idPedido=d.idPedido and p.idEmpresa=d.idEmpresa "+
					" WHERE p.idempresa="+idEmpresa+" and subpedido = '"+subpedido+"';";
			String query2 = "select a.idarticulo, a.cantidadPedido FROM ecommerce_pedido p "
					+ "inner join ecommerce_pedido_articulos a on a.idpedido=p.idpedido and a.idempresa=p.idempresa "
					+ "WHERE p.idEmpresa="+idEmpresa+" and subpedido = '"+subpedido+"'";
			
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);			
			while (rs.next())
			{
				DataIDDescripcion shippingType = new DataIDDescripcion(0,"");
				
				p = new EncuentraPedido();
				p.setIdPedido(rs.getLong(1));
				p.setDescripcion(rs.getString(2));
				p.setCantidad(rs.getInt(3));
				p.setEstado(rs.getString(4));
				p.setUrlEtiqueta(rs.getString(5));
				p.setFormaPago(rs.getString(6));
				p.setCanalAnaloga(rs.getInt(7));
				p.setIdFenicio(rs.getString(8));
				p.setFecha(rs.getString(9));
				p.setTicketNumber(rs.getString(10));	
				p.setSubpedido(rs.getString(11));				
				
				p.setArticulosPedido(new ArrayList<>());
				
				
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery(query2);		
				while (rs2.next()) {
					EncuentraPedidoArticulo a = new EncuentraPedidoArticulo();
					a.setArticulo(rs2.getString(1));
					a.setCantidad(rs2.getInt(2));
					p.getArticulosPedido().add(a);
				}
				
				shippingType.setId(rs.getInt(12));
				p.setShippingType(shippingType);
				
				try {
					p.setSucursalPick(rs.getString(13));
					p.setIdDepositoEnvio(rs.getInt(14));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				rs2.close();
				s2.close();
			}
			desconectar(rs,null, s);
		} catch (Exception e) {
			return p;
		}
		return p;
	}
	
	public  List<jsonEstadoMP> DarPendienteColaEstadoMarketPlace(String consulta) 
	{
		List<jsonEstadoMP> lista = new ArrayList<>();

		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				jsonEstadoMP l = new jsonEstadoMP();
				l.setIdPedido(rs.getLong(1));
				l.setJson(rs.getString(2));
				l.setCanal(rs.getInt(3));
				l.setIdEmpresa(rs.getInt(4));
				
				lista.add(l);

			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}
	
	public  boolean IsCambioEstadoModdo(String idEcommerce, int estado) 
	{
		boolean salida = false;

		try 
		{
			//connection = getConnection(); 
			String query = 
				"SELECT COUNT(idfenicio), "+
				"(SELECT COUNT(idFenicio) FROM ecommerce_pedido WHERE idfenicio='"+idEcommerce+"' AND estadoencuentra="+estado+" "+
				"GROUP BY idfenicio) "+
				"FROM ecommerce_pedido WHERE idfenicio='"+idEcommerce+"' "+
				" GROUP BY idfenicio";
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next())
			{
				try {
					if(rs.getInt(1)==rs.getInt(2)) {
						salida = true;
					}
				} catch (Exception e) {
					salida = false;
				}
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			salida = false;
		}

		return salida;
	}
	

	public List<PedidosAtr> darPedidosAtrasados(String query) throws InstantiationException, IllegalAccessException, Exception{
		List<PedidosAtr> pedidosATR = new ArrayList<>();
		System.out.println(query);
		Connection con = this.connect();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			boolean contieneGuion = rs.getString(3).contains("-");
			String[] clienteTel = new String[3];
			if(contieneGuion) {
				clienteTel = rs.getString(3).split("-");
			} else {
				System.out.println(rs.getNString(3));
				clienteTel[0] = rs.getString(3);
				clienteTel[1] = "";
			}
			if(!clienteTel[1].equals("")) {
				clienteTel[1] = clienteTel[1].trim();
			}
			
			if(clienteTel[1].equals("null") || clienteTel[1] == null) {
				clienteTel[1] = "--";
			}
			if(clienteTel.length == 3 && clienteTel[2] != null) {
				clienteTel[1] += clienteTel[2];
			}
			String mail = "";
			if(!rs.getString(9).contains("@mail.mercadolibre")) {
				mail = rs.getString(9);
			}
			PedidosAtr pa = new PedidosAtr(rs.getString(1), rs.getString(2), clienteTel[0],rs.getString(8), mail, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			pedidosATR.add(pa);
		}
		return pedidosATR;
	}
	

	public bulto obtenerBultoCerrado(String idBulto, int idEmpresa, int idPicking) {
		bulto b = null;
		String query1 = "SELECT * FROM bulto b WHERE b.idBulto = '"+idBulto+"' AND b.idEmpresa = "+idEmpresa+" AND b.cerrado = 1;";
		String query2 = "SELECT * FROM bulto_contenido bc WHERE bc.idBulto = '"+idBulto+"' AND bc.idEmpresa = "+idEmpresa;
		Statement s;
		try {
			Connection con = this.connect();
			s = con.createStatement();
			ResultSet rs = s.executeQuery(query1);
			if(rs.next()) {
				b = new bulto(idBulto,"Caja distribucion lc."+rs.getString(11),false,0,0,0,0.0,rs.getBoolean(10),rs.getString(12),rs.getInt(13), rs.getString(11),idEmpresa);
				b.setRemision_al_cerrar(rs.getInt(17)==1);
				b.setPosSort(rs.getString(18));
				b.setPedido(rs.getLong(19));
				b.setNumerador(rs.getInt(20));
				Statement s2 = con.createStatement();
				ResultSet rs2 = s2.executeQuery(query2);
				Hashtable<String,bultoContenido> contenidoBulto = new Hashtable<>();
				while(rs2.next()) {
					bultoContenido bc = new bultoContenido(rs2.getString(2), rs2.getInt(3));
					bc.setPicking(idPicking);
					contenidoBulto.put(bc.getIdArticulo(), bc);
				}
				b.setContenido(contenidoBulto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean esBultoCerrado(String idBulto, int idEmpresa) {
		boolean estaCerrado = false;
		String query1 = "SELECT * FROM bulto b WHERE b.idBulto = '"+idBulto+"' AND b.idEmpresa = "+idEmpresa;
		Statement s;
		try {
			Connection con = this.connect();
			s = con.createStatement();
			ResultSet rs = s.executeQuery(query1);
			if(rs.next()) {
				estaCerrado = rs.getBoolean(3);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estaCerrado;
	}
	

	public Hashtable<String, Object> obtenerDatosBD(String nombreTabla, int idEmpresa) {
		Hashtable<String, Object> valores = new Hashtable<>();
		String query = "SELECT * FROM "+nombreTabla+" WHERE idEmpresa =" + idEmpresa;
		System.out.println(query);
		try {
			Connection con = this.connect();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			while(rs.next()) {
				if(!valores.containsKey(rs.getObject(1))) {
					valores.put(rs.getString(2), rs.getObject(1));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return valores;
	}
	
	public Hashtable<Integer, List<DataIDDescripcion>> darSolicitudesMayoristas(String idBulto, int destino, 
		Hashtable<String, DataIDDescripcion> articulosHT, int idEmpresa, boolean bultoCerrado) {
		
		String query = "";
		if(bultoCerrado) {
			query = "select distinct ra.idarticulo,ra.idSolicitudTraslado,ra.cantidad-ra.remitidas from bulto b "+
					"inner join bulto_contenido bc on bc.idBulto=b.idBulto and bc.idEmpresa=b.idEmpresa "+
					"INNER JOIN reposicion_articulos_ojos rao ON rao.idArticulo = bc.idArticulo AND rao.idBulto = bc.idBulto AND bc.idEmpresa=rao.idEmpresa "+
					"inner join reposicion_articulos ra on ra.idArticulo = rao.idArticulo and rao.idEmpresa=ra.idEmpresa "+						
					"where ra.cantidad-ra.remitidas > 0 and b.idBulto = '"+idBulto+"' and ra.destino = "+destino+" and  ra.idEmpresa = "+idEmpresa+
					" and ra.verif>0 and ra.remitidas<ra.verif and ra.estado not in (4,7) order by ra.verif desc";
		}
		else {
			query = "select distinct ra.idarticulo,ra.idSolicitudTraslado,ra.cantidad-ra.remitidas from bulto b "+
					"inner join bulto_contenido bc on bc.idBulto=b.idBulto and bc.idEmpresa=b.idEmpresa "+
					"inner join reposicion_articulos ra on ra.idArticulo = bc.idArticulo AND bc.idEmpresa=ra.idEmpresa and bc.picking=ra.idPicking "+						
					"where ra.cantidad-ra.remitidas > 0 and b.idBulto = '"+idBulto+"' and ra.destino = "+destino+" and  ra.idEmpresa = "+idEmpresa+
					" and ra.verif>0 and ra.remitidas<ra.verif and ra.estado not in (4,7) order by ra.verif desc";
		}
		
		
		Hashtable<Integer, List<DataIDDescripcion>> solicitudes = new Hashtable<Integer, List<DataIDDescripcion>>();
		List<DataIDDescripcion> solicitudArticulos = new ArrayList<>();
		try {
			Connection con = this.connect();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			//BUSCO LOS ARTICULOS CON SUS SOLICITUDES
			while(rs.next()) {
				DataIDDescripcion data = new DataIDDescripcion(rs.getInt(3),rs.getString(1));
				data.setIdB(rs.getInt(2));
				solicitudArticulos.add(data);
			}
			desconectar(rs,null, s);
			
			for(DataIDDescripcion soli : solicitudArticulos) {	
				int cantidadSolicitud = soli.getId();
				int solicitud = soli.getIdB();
				String articulo = soli.getDescripcion();
				
				if(articulosHT.get(articulo)!= null) {
					int cantidadArticulo = articulosHT.get(articulo).getId();
					String articuloArticulo = articulosHT.get(articulo).getDescripcion();
					DataIDDescripcion dataSolicitud = null;
					
					if (cantidadArticulo <= cantidadSolicitud) {										
						dataSolicitud = new DataIDDescripcion(cantidadArticulo,articuloArticulo);
						dataSolicitud.setIdB(destino);
						dataSolicitud.setDescripcionB(solicitud+"");
						articulosHT.remove(articulo);
					} else if (cantidadArticulo > cantidadSolicitud) {
						dataSolicitud = new DataIDDescripcion(cantidadArticulo,articuloArticulo);
						dataSolicitud.setIdB(destino);
						dataSolicitud.setDescripcionB(solicitud+"");
						articulosHT.get(articulo).setId(cantidadArticulo - cantidadSolicitud);
					}
					
					if(dataSolicitud!=null) {
						if(solicitudes.get(solicitud)!=null) {
							solicitudes.get(solicitud).add(dataSolicitud);
						}
						else {
							solicitudes.put(solicitud, new ArrayList<>());
							solicitudes.get(solicitud).add(dataSolicitud);
						}
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return solicitudes;
	}

	public boolean darBoolean(String q) 
	{
		boolean retorno = false;
		try 
		{
			Connection con = this.connect();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(q);
			while(rs.next()) 
			{
				int val = rs.getInt(1);
				if(val==1)
				{
					retorno = true;
				}
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	public EncuentraPedido DarPedidoTracking(String q) 
	{
		EncuentraPedido retorno = new EncuentraPedido();
		try 
		{
			Connection con = this.connect();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(q);
			while(rs.next()) 
			{
				Long idPedido =  rs.getLong(1);
				String idFenicio = rs.getString(2);
				retorno.setIdPedido(idPedido);
				retorno.setIdFenicio(idFenicio);
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	public  Hashtable<String, String> PedidosPorCanal(String consulta) 
	{
		Hashtable<String, String> lista = new Hashtable<>();
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				try{
					lista.put(rs.getString(1),rs.getString(1));
				}
				catch(Exception e){

				}				
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}
	
	public boolean isBultoConVariasReservas(String idBulto, int idEmpresa, int idPicking) {
		boolean result = false;
		try 
		{
			connection = getConnection(); 
			Statement s = connection.createStatement();
			String query="SELECT rao.idBulto, SUM(rao.cantidad), bcr.res FROM reposicion_articulos_ojos rao\r\n" + 
					"INNER JOIN bulto_contenido bc ON bc.idBulto=rao.idBulto AND bc.idArticulo=rao.idArticulo AND bc.IdEmpresa=rao.IdEmpresa\r\n" + 
					"INNER JOIN (SELECT idbulto,idempresa, SUM(cantidadreservada) res FROM bulto_contenido GROUP BY idbulto) bcr ON bcr.idBulto=bc.idBulto \r\n" + 
					"	AND bc.IdEmpresa=bcr.IdEmpresa\r\n" + 
					"WHERE rao.idBulto='"+idBulto+"' AND rao.idpicking="+idPicking+" and rao.idEmpresa= "+idEmpresa+"\r\n" + 
					"HAVING SUM(rao.cantidad) = bcr.res;";
			System.out.println(query);
			ResultSet rs = s.executeQuery(query);
			int count = 0;
			while (rs.next())
			{
				result = true;
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	

public List<String> BuscarArticulo(String q) 
	
	{
		List<String> retorno = new ArrayList<>();
		try 
		{
			Connection con = this.connect();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(q);
			while(rs.next()) 
			{
				retorno.add(rs.getString(1));
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

public Cliente darSenderPeYa(String q) 
{
	Cliente retorno = new Cliente();
	try 
	{
		Connection con = this.connect();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(q);
		while(rs.next()) 
		{
			retorno.setNombre(rs.getString(1));
			retorno.setApellido(rs.getString(2));
			retorno.setLocalidad(rs.getString(3));
			retorno.setEmail(rs.getString(4));
			retorno.setDepartamento(rs.getString(5));
			retorno.setCiudad(rs.getString(6));
			retorno.setTelefono(rs.getString(7));
			retorno.setNroPuerta(rs.getString(8));
			retorno.setObs(rs.getString(9));
			String lat = rs.getString(10);
			String lon = rs.getString(11);
			retorno.setCalle(rs.getString(12));
			if(lat.equals(""))
			{
				retorno.setLatitud("0");
			}
			else
			{
				retorno.setLatitud(lat);
			}
			if(lon.equals(""))
			{
				retorno.setLongitud("0");
			}
			else
			{
				retorno.setLongitud(lon);
			}
			
			retorno.setNroApto("");
			retorno.setDocumentoTipo("CI");
			retorno.setDocumentoNro("0");
			retorno.setRut("");
			
			
		}
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
	
	return retorno;
	
}

public List<DepositoStock> darDepositStockOrder(String q) 
{
	System.out.println("consulto el stock asi");
	System.out.println(q);
	Map<Integer, DepositoStock> deposHT = new Hashtable<>();
	try 
	{
		Connection con = this.connect();
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(q);
		while(rs.next()) 
		{
			DepositoStock ds = null;
			DataIDDescripcion art = new DataIDDescripcion(rs.getInt(7),rs.getString(6));
			if(deposHT.get(rs.getInt(1))==null) 
			{
				ds = new DepositoStock(rs.getInt(1), rs.getInt(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5));
				
				
				
			}
			else
			{
				ds = deposHT.get(rs.getInt(1));
				
			}
			
			ds.getArticulos().add(art);
			deposHT.put(rs.getInt(1),ds);
			
			
		}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
	List<DepositoStock>  retorno = new ArrayList<>(deposHT.values());
	Collections.sort(retorno);
	
	
	return retorno;
}
	public List<BultosACrear> darBultosACrear(String consulta) 
	{
		System.out.println(consulta);
		List<BultosACrear> lista = new ArrayList<>();
		try (Statement s = connection.createStatement(); ResultSet rs = s.executeQuery(consulta);)
		{
			while (rs.next())
			{
				BultosACrear lineas = new BultosACrear(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				lista.add(lineas);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	
		return new ArrayList<>();
	}
	public List<ReporTEST> darReporteTEST(String consulta) 
	{
		List<ReporTEST> retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				ReporTEST in = new ReporTEST(rs.getString(2),rs.getString(4),rs.getInt(1),rs.getInt(3));
				retorno.add(in);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	
		return retorno;
	}
	
	public List<ReporteTest> reportesTest(String consulta) 
	{
		System.out.println(consulta);
		List<ReporteTest> lista = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				ReporteTest lineas = new ReporteTest(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getString(4));
				lista.add(lineas);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	
		return lista;
	}

	public List<MonitorVentaM> darListaMonitorVentaM(String consulta) 
	{
		System.out.println(consulta);
		List<MonitorVentaM> lista = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				MonitorVentaM linea = new MonitorVentaM(rs.getString(1),rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7),rs.getInt(8), rs.getString(9)) ;
				lista.add(linea);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	
		return lista;
		
		
	}

	public List<MonitorVentaMArticulo> darmonitorVtaMayoristaArticulos(String consulta) 
	{
		
		System.out.println(consulta);
		List<MonitorVentaMArticulo> lista = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				MonitorVentaMArticulo linea = new MonitorVentaMArticulo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getInt(11)) ;
				lista.add(linea);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	
		return lista;
	}

	public Hashtable<String, DataIDDescripcion> darOjosArti(String consulta) 
	{
		 Hashtable<String, DataIDDescripcion> lista = new Hashtable<>();
		System.out.println(consulta);
		
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				DataIDDescripcion d = new DataIDDescripcion();
				d.setId(rs.getInt(6));//recorrido
				d.setIdB(rs.getInt(4));//estante
				d.setDescripcion(rs.getString(2));//estanteria
				d.setDescripcionB(rs.getString(5));//idOjo
				d.setIdLong(Long.parseLong(rs.getString(3)));//modulo
				
				
				lista.put(rs.getString(1), d);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	
		return lista;
	}

	public Hashtable<String, String> darDescripcionArticulos(String consulta) 
	{
		 Hashtable<String, String> lista = new Hashtable<>();
			
			
			try 
			{
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery(consulta);			
				while (rs.next())
				{
					
					lista.put(rs.getString(1), rs.getString(2));
				}
				desconectar(rs,null, s);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return null;
			}
		
			return lista;
	}

	public List<PosicionesPosibles> creoPosicionesPosibles(String consulta) {
		List<PosicionesPosibles> resultado = new ArrayList<>();

		try {
			Statement s;
			ResultSet rs = null;
			try {
				s = connection.createStatement();
				rs = s.executeQuery(consulta);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			

			while (rs.next())
			{
				PosicionesPosibles pp = new PosicionesPosibles(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6));
				resultado.add(pp);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}


	public List<DataIDDescripcion> darBultosPosibleRepo(String consulta) 
	{
		 List<DataIDDescripcion> lista = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);			
			while (rs.next())
			{
				
				DataIDDescripcion in = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				in.setDescripcionB(rs.getString(3));
				in.setDescripcionC(rs.getString(4));
				lista.add(in);
			}
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	
		return lista;
	}

	public List<ConteoOjo> darListaConteoOjo(String consulta) 
	{
		 List<ConteoOjo> lista = new ArrayList<>();
			try 
			{
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery(consulta);			
				while (rs.next())
				{
					
					ConteoOjo in = new ConteoOjo(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
					lista.add(in);
				}
				desconectar(rs,null, s);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return null;
			}
		
			return lista;
	}
	
	public Hashtable<String, bulto> DarBultosAbiertos2(String consulta, String consulta2, String consulta3, String consulta4, int idEmpersa) 
	{
		Hashtable<String, bulto> lista = new Hashtable<>();
		bulto b = null;
		Usuario u = null;
		bultoContenido bc = null;
		
		String idsBultos = "";
		
		try 
		{

			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			boolean pri = true;
			Statement subs;
			ResultSet subrs;
			while (rs.next())
			{

				
				if(pri) 
				{
					pri=false;
					idsBultos +="'"+rs.getString(1)+"'";
					
				}
				else
				{
					idsBultos +=",'"+rs.getString(1)+"'";
				}
				
				b = new bulto(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getInt(6), rs.getInt(7), rs.getInt(8),
						rs.getDouble(9), rs.getBoolean(10), rs.getString(4), rs.getInt(5),rs.getString(11),idEmpersa);
				b.setEquipo_trabajo(rs.getInt(15));
				b.setRemision_al_cerrar(rs.getInt(17) == 1);
				b.setPosSort(rs.getString(18));
				b.setPedido(rs.getLong(19));
				b.setNumerador(rs.getInt(20));
				b.setDescDestino(rs.getString(21));

				

				lista.put(b.getIdBulto(), b);
			}
			
			
			
			subs = connection.createStatement();
			subrs = subs.executeQuery(consulta2.replace("@@", idsBultos));
			while (subrs.next())
			{
				bc = new bultoContenido();
				bc.setIdArticulo(subrs.getString(3));
				bc.setCantidad(subrs.getInt(4));
				bc.setPicking(subrs.getInt(5));
				bc.setRecepcion(subrs.getInt(6));
				bc.setFecha(subrs.getString(7));
				bc.setUsuario(subrs.getInt(8));
				
				bulto bu =lista.get(subrs.getString(1)); 
				
				bu.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(), bc);
			}
			//desconectar(subrs,null, subs);

			subs = connection.createStatement();
			subrs = subs.executeQuery(consulta3.replace("@@", idsBultos));
			while (subrs.next())
			{
				
				bulto bu =lista.get(subrs.getString(1));
				bu.getCaracteristicas().add(subrs.getString(3).toUpperCase()+" "+subrs.getString(4).toUpperCase());
			}
			//desconectar(subrs,null, subs);

			subs = connection.createStatement();
			subrs = subs.executeQuery(consulta4.replace("@@", idsBultos));
			while (subrs.next())
			{
				bulto bu =lista.get(subrs.getString(1));
				bu.getRemitos().add(new DataIDDescripcion(subrs.getInt(3),subrs.getString(4)));
			}
			//desconectar(subrs,null, subs);
			
			
			
			
			desconectar(rs,null, s);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return lista;
	}


public List<DataOjoArticuloCantidad> darReservas(String consulta) {
	List<DataOjoArticuloCantidad> resultado = new ArrayList<>();
	try 
	{
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(consulta);			
		while (rs.next())
		{
			
			DataOjoArticuloCantidad in = new DataOjoArticuloCantidad(rs.getString(1),rs.getInt(4), rs.getString(2),rs.getString(5));
			resultado.add(in);
		}
		desconectar(rs,null, s);
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		return null;
	}

	return resultado;
}

public  HashMap<String, Integer> darParStringInteger(String consulta) 
{
	HashMap<String, Integer> hashMT = new HashMap<String, Integer>();

	try 
	{
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(consulta);			
		while (rs.next())
		{
			hashMT.put(rs.getString(1), rs.getInt(2));
		}
		desconectar(rs,null, s);
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		return null;
	}

	return hashMT;
}

public List<DataIDDescripcion> darRemitoEc(String consulta) {
	List<DataIDDescripcion> resultado = new ArrayList<>();
	try 
	{
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(consulta);			
		while (rs.next())
		{
			DataIDDescripcion remito = new DataIDDescripcion();
			remito.setId(rs.getInt(1));
			remito.setDescripcion(rs.getString(2));
			remito.setDescripcionB(rs.getString(3));
			resultado.add(remito);
		}
		desconectar(rs,null, s);
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		return null;
	}

	return resultado;
}

public List<DataIDDescripcion> darReporteRecepcionCantidad(String query) {
	
	System.out.println(query);
	
	List<DataIDDescripcion> lista = new ArrayList<>();
	try {
		connection=connect();
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery(query);
		while (rs.next()) {
			DataIDDescripcion d = new DataIDDescripcion();
			d.setDescripcion(rs.getString(1)); // Id Art?culo
			d.setDescripcionB(rs.getString(2)); // Descripcion Art?culo
			d.setId(rs.getInt(3)); // Cantidad
			lista.add(d);
		}
		desconectar(rs, null, s);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return lista;
}




/*public  List<DataItemFoundAndStock> darStocksLike(String consulta) 
{
	
	List<DataItemFoundAndStock> ret = new ArrayList<>();
	
	        try {
	        	
	            // Establish the connection.
	            Connection con = getConnection();         
	            System.out.println(consulta);
		        Statement s = con.createStatement();
		        ResultSet rs = s.executeQuery (consulta);
				
				while (rs.next())
				{
		        	ret.add(new DataItemFoundAndStock(rs.getString(1), rs.getInt(2)));				
				}
				desconectar(rs, null, s);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();				
			}
		
			return ret;
}*/

}

