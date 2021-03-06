package persistencia;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import beans.CallBackPedido;
import beans.Fecha;
import beans.Usuario;
import beans.jsonEstadoMP;
import beans.api.DataPrintable;
import beans.api.EquipoPrintSpooler;
import beans.api.EquiposPrintSpool;
import beans.api.GrabarRecepcion;
import beans.api.MovStock;
import beans.api.pedidoFactura;
import beans.api.json.RetornoPedido;
import beans.api.json.SendMail;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.MetodoPago;
import beans.datatypes.Remito;
import beans.datatypes.RemitoLinea;
import beans.encuentra.Cliente;
import beans.encuentra.Compra;
import beans.encuentra.Compras;
import beans.encuentra.Items;
import beans.helper.PropertiesHelperAPI;
import integraciones.erp.billerTata.TicketCambio;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.erp.visualStore.objetos.ParametrosVisual;
import integraciones.marketplaces.fenicio.Descuento;
import integraciones.marketplaces.fenicio.Lineas;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import logica.Util;


public class _EncuentraConexionAPI2
{

	private static Connection connection = null;

	private static Connection connect() throws Exception,InstantiationException, IllegalAccessException 
	{
		
		Connection conn = null;
		PropertiesHelperAPI pH=new PropertiesHelperAPI("conexion");
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
			conn = DriverManager.getConnection(url+"?allowMultiQueries=true&autoReconnect=true", usuario, password);

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

	public static Connection getConnection() throws Exception {
		if (connection == null) {
			connection = connect();
		}
		return connection;
	}

	public static void desconectar() throws Exception {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new Exception("Error al cerrar la Base de Datos");
		}
	}
	

	public static RetornoPedido darArticulosOjos(String consulta,String articulo) 
	{
		int largo = 0;
		try 
		{
			RetornoPedido rp = new RetornoPedido();
			rp.setPedido(articulo);
			boolean ready = false;
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			List<String> ubis = new ArrayList<>();
			String cliente = "";
		
			
			while (rs.next()) 
			{
				largo++;
				ubis.add(rs.getString(3));
				cliente = rs.getString(1);
				ready=true;
			}
			
			if(ready)
			{
				rp.setDisponible_Retiro("true");
			}
			else
			{
				rp.setDisponible_Retiro("false");
			}
			
			String[] arreglo = new String[largo];
			for (int i = 0; i < largo; i++) 
			{
				arreglo[i]=ubis.get(i);
			}
			rp.setCliente(cliente);
			rp.setUbicaciones(arreglo);
			
		
			rs.close();
			return rp;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;

	
	}
	
	public static int persistir(String consulta) throws Exception {
		
		int retorno=0;		
		try {			
			PreparedStatement pstmt = connection.prepareStatement(consulta);
			
			//pstmt.close();
			//coment
			retorno = pstmt.executeUpdate();

		} catch (Exception e)
		{
			retorno=-2;
			//System.out.println(consulta);
			e.printStackTrace();
			if(e.getMessage().contains("Duplicate entry"))
			{
				return 0;
			}
			return retorno;
		}

		return retorno;
	}
	
	public static Usuario login(String consulta) 
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
			//u.setIdGrupo(rs.getInt(8));
			u.setDeposito(rs.getString(9));
			u.setIdEmpresa(rs.getInt(10));
			

		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return u;
}

	
	public static String barrio(String consulta) 
	{
	
	try 
	{
		Statement s = connection.createStatement();
		System.out.println(consulta);
		ResultSet rs = s.executeQuery(consulta);
		
		while (rs.next()) 
		{
			
			return rs.getString(2);

			

		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return "";
}
	
	
	
	
	public static List<DataPrintable> darColaImpresion(String consulta) 
	{
	
		List<DataPrintable> lista = new ArrayList<>();
	try 
	{
		Statement s = connection.createStatement();
		System.out.println(consulta);
		ResultSet rs = s.executeQuery(consulta);
		
		while (rs.next()) 
		{
			
			lista.add(new DataPrintable(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			
			

		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return lista;
}
	
	public static List<SendMail> darColaEnvioMails(String consulta) 
	{
	
		List<SendMail> lista = new ArrayList<>();
	try 
	{
		Statement s = connection.createStatement();
		System.out.println(consulta);
		ResultSet rs = s.executeQuery(consulta);
		
		while (rs.next()) 
		{			
			SendMail sm = new SendMail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7));
			sm.setIdEmpresa(rs.getInt(6));
			lista.add(sm);
		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return lista;
}

	public static String dartokenApi(String consulta) 
	{
		
		try 
		{
			Statement s = connection.createStatement();
			
			ResultSet rs = s.executeQuery(consulta);
			
			while (rs.next()) 
			{			
				return rs.getString(1);
			}
			rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static String darFechatokenApi(String consulta) 
	{
		
		try 
		{
			if(connection == null) {
				connection = getConnection();
			}
			Statement s = connection.createStatement();
			
			ResultSet rs = s.executeQuery(consulta);
			
			while (rs.next()) 
			{			
				return rs.getString(1);
			}
			rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public static List<DataIDDescripcion> darlistaDataIdDesc(String consulta) 
	{
		
		List<DataIDDescripcion> lista = new ArrayList<>();
	try 
	{
		getConnection();
		Statement s = connection.createStatement();
		System.out.println(consulta);
		ResultSet rs = s.executeQuery(consulta);
		
		while (rs.next()) 
		{
			
			lista.add(new DataIDDescripcion(rs.getInt(1),rs.getString(2)));
			
			

		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return lista;
}
	
	public static List<DataIDDescripcion> darlistaLongDesc(String consulta) 
	{
		
		List<DataIDDescripcion> lista = new ArrayList<>();
	try 
	{
		getConnection();
		Statement s = connection.createStatement();
		System.out.println(consulta);
		ResultSet rs = s.executeQuery(consulta);
		
		while (rs.next()) 
		{
			
			lista.add(new DataIDDescripcion(rs.getLong(1),rs.getString(2)));

		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return lista;
	}
	
	public static List<DataIDDescripcion> darlistaEtiquetasShopifyMail(String consulta) 
	{
		
		List<DataIDDescripcion> lista = new ArrayList<>();
	try 
	{
		getConnection();
		Statement s = connection.createStatement();
		System.out.println(consulta);
		ResultSet rs = s.executeQuery(consulta);
		
		while (rs.next()) 
		{
			
			lista.add(new DataIDDescripcion(rs.getInt(1),rs.getString(2), rs.getString(3)));

		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return lista;
	}
	
	public static List<CallBackPedido> darCallBackPedidoEncuentra(String consulta) 
	{
		
		List<CallBackPedido> lista = new ArrayList<>();
	try 
	{
		getConnection();
		Statement s = connection.createStatement();
		System.out.println(consulta);
		ResultSet rs = s.executeQuery(consulta);
		
		while (rs.next()) 
		{
			CallBackPedido callBack = new CallBackPedido(rs.getInt(1),rs.getInt(3), rs.getInt(8), rs.getLong(2), rs.getString(4), rs.getString(6), rs.getString(7), rs.getBoolean(5));
			callBack.setPrinter(rs.getInt(9));
			
			lista.add(callBack);

		}
		rs.close();
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	return lista;
	}
	
	public static List<Compras> darComprasWeb(String query, int idEmpresa) 
	{
		Util u = new Util();
		List<Compras> pedidos = new ArrayList<>();
		Compras compra;
		Compra c;
		Cliente cli;
		List<Items> items;
		
		
		
		try 
		{
			Statement s = connection.createStatement();
			System.out.println(query);
			ResultSet rs = s.executeQuery(query);
			
			while (rs.next()) 
			{			
				try {
					compra = new Compras();
					c = new Compra();
					cli = new  Cliente();
					
					
					c.setImporte(rs.getString(1));
					c.setId(rs.getString(2));
					
					c.setEstado("preparando");
					c.setFecha(rs.getString(4));
					c.setMoneda(rs.getString(5));
					c.setOrigen("0");
					c.setMontoEnvio(rs.getString(7));
					c.setMetodoPago(rs.getString(8));
					c.setIdVenta(rs.getString(9));
					String fecha =rs.getString(24).substring(0,rs.getString(24).length()-2); 
					c.setFecha(fecha);
					
					boolean freshipping = false;
					
					try
					{
						if(rs.getInt(30)==1)
						{
							freshipping = true;
						}
					}
					catch (Exception e) {}
					
					c.setFreeshipping(freshipping);
					
					c.setSucursal(rs.getString(6));
					
					c.setIdCanal(rs.getString(23));
					c.setZpl(rs.getString(25));
					
					try {
						if(c.getSucursal().equals("70000")) {
							String split = c.getZpl().split("\\{")[1];
							split = split.split("\\}")[0];
							if(split!=null) {
								c.setTrackingNumber("{"+split+"}");
							}
							else {
								c.setTrackingNumber(c.getId());
							}							
						}
						else {
							if(rs.getString(26).equals("")) {
								c.setTrackingNumber(c.getId());
							}
							else {
								c.setTrackingNumber(rs.getString(26));
							}							
						}	
					} catch (Exception e) {
						c.setTrackingNumber(c.getId());
					}
						
					c.setArtEnvio(rs.getString(27));
					
					
					
			        items = darComprasWeb_items(c.getId(), u);  			        
			        
			        c.setItems(items);
			        cli.setNombre(u.validarString(rs.getString(10)));
					cli.setApellido(u.validarString(rs.getString(11)));
					cli.setLocalidad(u.validarString(rs.getString(14)));
					cli.setDepartamento(u.validarString(rs.getString(13)));
					cli.setEmail("");
					
					cli.setLatitud("0");
					cli.setLongitud("0");
					
					cli.setTelefono(u.validarString(rs.getString(15)));
					cli.setCalle(u.validarString(rs.getString(17)));
					cli.setCiudad(u.validarString(rs.getString(18)));
					cli.setCp(u.validarString(rs.getString(19)));
					cli.setObs(u.validarString(rs.getString(20)));						
					cli.setDocumentoTipo(u.validarString(rs.getString(21)));
					cli.setDocumentoNro(u.validarString(rs.getString(22)));
					cli.setNroPuerta(u.validarString(rs.getString(28)));
					cli.setNroApto(u.validarString(rs.getString(29)));
					
					compra.setCompra(c);
					compra.setCliente(cli);
					
					pedidos.add(compra);
										
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
			rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		 
		
		return pedidos;
	}
	
	public static List<Items> darComprasWeb_items(String vta, Util u) 
	{
		Items i;
		List<Items> items = new ArrayList<>(); ;
		String query =	"SELECT L.SKU idarticulo,L.QUANTITY 'Cantidad pendiente', L.PRICE,1,L.IDD,"
				+ "REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(L.variation,'\\\"key\\\":',''),'\\\"value\\\":',''),'[{\\\"',''),'\\\"}]',''),'\\\"','') "
				+ "FROM producteca_ecom_det_ped L WHERE OPERATIONID = '"+vta+"'";
		 
		List<Compras> pedidos = new ArrayList<>();
		Compras compra;
		Compra c;
		Cliente cli;
				
			
		String consulta = query;
		try 
		{
			Statement s = connection.createStatement();
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);
			
			while (rs.next()) 
			{	
				i = new Items();
		        i.setSku(u.validarString(rs.getString(1)));
			    i.setCod(u.validarString(rs.getString(1)));
			    i.setCant(String.valueOf(rs.getInt(2)));
			    i.setImporte(rs.getDouble(3)+"");
			    i.setOrigen(u.validarString(rs.getString(4)));
			    i.setDocVisual(u.validarString(rs.getString(5)));
			    i.setVariacion(u.validarString(rs.getString(6)));
			    items.add(i);				
			}
			rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return items;
	}

	public static List<Cliente> darClientesProducteca(String q) 
	{
		Util u = new Util();
		List<Cliente>  retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			
			ResultSet rs = s.executeQuery(q);
			
			while (rs.next()) 
			{	
				
				
				
				Cliente c = new Cliente();
				c.setNombre(u.validarString(rs.getString(1)));
				c.setApellido(u.validarString(rs.getString(2)));
				c.setCalle(u.validarString(rs.getString(3)));
				c.setNroPuerta(u.validarString(rs.getString(4)));
				c.setCiudad(u.validarString(rs.getString(6)));
				c.setDepartamento(u.validarString(rs.getString(5)));
				c.setCp(u.validarString(rs.getString(7)));
				c.setTelefono(u.validarString(rs.getString(8)));
				c.setEmail(u.validarString(rs.getString(9)));
				c.setDocumentoNro(u.validarString(rs.getString(10)));
				c.setObs(u.validarString(rs.getString(11)));
				c.setIdPedido(u.validarString(rs.getString(12)));
				
				retorno.add(c);
				
			}
			rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static Cliente darClienteProducteca(String q) 
	{
		Util u = new Util();
		Cliente c = null;
		try 
		{
			Statement s = connection.createStatement();
			
			ResultSet rs = s.executeQuery(q);
			
			while (rs.next()) 
			{	
				
				
				
				c = new Cliente();
				c.setNombre(u.validarString(rs.getString(1)));
				c.setApellido(u.validarString(rs.getString(2)));
				c.setCalle(u.validarString(rs.getString(3)));
				c.setNroPuerta(u.validarString(rs.getString(4)));
				c.setCiudad(u.validarString(rs.getString(6)));
				c.setDepartamento(u.validarString(rs.getString(5)));
				c.setCp(u.validarString(rs.getString(7)));
				c.setTelefono(u.validarString(rs.getString(8)));
				c.setEmail(u.validarString(rs.getString(9)));
				c.setDocumentoNro(u.validarString(rs.getString(10)));
				c.setObs(u.validarString(rs.getString(11)));
				c.setIdPedido(u.validarString(rs.getString(12)));
				
				
			}
			rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return c;
	}

	public static List<DataIDDescripcion> darListaDataIdDescripcionConsulMYSQL(String q) 
	{
		List<DataIDDescripcion>  retorno = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			
			ResultSet rs = s.executeQuery(q);
			
			while (rs.next()) 
			{	
				DataIDDescripcion d = new DataIDDescripcion(rs.getInt(1),rs.getString(2));
								
				try
				{
					d.setId(rs.getInt(3));
				}
				catch (Exception e) 
				{
					
				}
				try
				{
					d.setDescripcionB(rs.getString(4));
				}
				catch (Exception e) 
				{
					
				}
				
				
				retorno.add(d);
				
			}
			rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return retorno;
	}

	public static EquiposPrintSpool darEquiposPrintSpool(String q) 
	{		
		EquiposPrintSpool retorno = new EquiposPrintSpool();
		List<EquipoPrintSpooler> equipos = new ArrayList<>();
		try 
		{
			Statement s = connection.createStatement();
			
			ResultSet rs = s.executeQuery(q);
			
			while (rs.next()) 
			{	
				EquipoPrintSpooler equipo = new EquipoPrintSpooler(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
								
				equipos.add(equipo);
				
			}
			rs.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		retorno.setEquipos(equipos);
		return retorno;
	}
	
	public static int persistirDarUltimo(String consulta, String tabla, String key, int idEmpresa) throws Exception 
	{
		int retorno  = 0;
		PreparedStatement pstmt = null;
		try {
		    pstmt = connection.prepareStatement(consulta);
			pstmt.executeUpdate();
			
			retorno = UltimoId("select max("+key+") from "+tabla+ " where IdEmpresa="+idEmpresa);

		} catch (Exception e) {
			
			e.printStackTrace();
			return retorno;
		}
		
		return retorno;
	}
	
	public static int UltimoId(String consulta) 
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
	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	return ultimo;
}
	
	public static OrdenVenta encuentraPedidosOrdenVenta(String consulta) 
	{
		OrdenVenta orden = new OrdenVenta();
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
				int cantidad=0;
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery("select precioImp,cantidad,Replace (TRIM(idArticulo),'?','') from ecommerce_import_ventalinea V "
						+ "LEFT OUTER JOIN ecommerce_cupones c on c.descripcion=v.idarticulo "
						+ "where idVenta ="+rs.getLong(10)+"  order by precioImp desc");
				
				while (rs2.next())
				{
					cantidad+=rs2.getInt(2);
					
					OrdenVentaLinea ovl = new OrdenVentaLinea(rs2.getDouble(1), rs2.getString(2), rs2.getString(3).replace(" ", ""));
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
				
				orden = new OrdenVenta(0, porcDescuento, "$", rs.getString(4).replace("", ""), rs.getString(5), rs.getString(6), "", rs.getString(8), ventaLineas, rs.getLong(10),vendedor, rs.getString(12),0, rs.getDouble(15));
				orden.setComentario(rs.getString(14));
				String f= rs.getString(17);
				Fecha fecha= new Fecha(f);
				orden.setFecha(fecha.darFechaAnio_Mes_Dia());
				System.out.println("\n \n "+orden.getCliNombre());
				for (OrdenVentaLinea ovl : ventaLineas) 
				{
					System.out.println(ovl.getIdArticulo()+"  "+ovl.getPrecioImp());
				}
					
				
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return orden;
		

	}
	
	public static List<OrdenVenta> ListarOrdenesSF(String consulta) 
	{
		List<OrdenVenta> lista = new ArrayList<>();
		
		try (Statement s = connection.createStatement();Statement s2 = connection.createStatement();)
		{
			
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
				int cantidad=0;
				
				ResultSet rs2 = s2.executeQuery("select precioImp,cantidad,Replace (TRIM(idArticulo),'?',''), descripcion,Descuento from ecommerce_import_ventalinea V "
						+ "where idVenta ="+rs.getLong(10)+"  order by precioImp desc");
				
				while (rs2.next())
				{
					cantidad+=rs2.getInt(2);
					
					OrdenVentaLinea ovl = new OrdenVentaLinea(rs2.getDouble(1), rs2.getString(2), rs2.getString(3).replace(" ", ""));
					ovl.setDescripcion(rs2.getString(4));
					ovl.setDescuento(rs2.getDouble(5));
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
				OrdenVenta orden = new OrdenVenta(0, porcDescuento, "$", rs.getString(4).replace("", ""), rs.getString(5), rs.getString(6), rs.getString(19), 
						rs.getString(8), ventaLineas, rs.getLong(10),vendedor, rs.getString(12),0, rs.getDouble(15));
				orden.setComentario(rs.getString(14));
				String f= rs.getString(17);
				Fecha fecha= new Fecha(f);
				orden.setFecha(fecha.darFechaAnio_Mes_Dia());
				orden.setPrinter(rs.getInt(20));
				
				System.out.println("\n \n "+orden.getCliNombre());
				for (OrdenVentaLinea ovl : ventaLineas) 
				{
					System.out.println(ovl.getIdArticulo()+"  "+ovl.getPrecioImp());
				}
				lista.add(orden);
				
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return lista;
		

	}
	
	public static OrdenVenta darOrden(String consulta) 
	{
		OrdenVenta orden = null;
		try (Statement s = connection.createStatement();)
		{
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next())
			{
				List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
				int cantidad=0;
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery("select precioImp,cantidad,Replace (TRIM(idArticulo),'?',''), descripcion,Descuento,cantregalo,idlinea from ecommerce_import_ventalinea V "
						+ "where idVenta ="+rs.getLong(10)+"  order by precioImp desc");
				
				while (rs2.next())
				{
					cantidad+=rs2.getInt(2);
					
					OrdenVentaLinea ovl = new OrdenVentaLinea(rs2.getDouble(1), rs2.getString(2), rs2.getString(3).replace(" ", ""));
					ovl.setDescripcion(rs2.getString(4));
					ovl.setDescuento(rs2.getDouble(5));
					ovl.setCantidadRegalo(rs2.getInt(6));
					ovl.setLinea(rs2.getInt(7));
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
				orden = new OrdenVenta();
				orden = new OrdenVenta(0, porcDescuento, "$", rs.getString(4).replace("", ""), rs.getString(5), rs.getString(6), rs.getString(19), 
						rs.getString(8), ventaLineas, rs.getLong(10),vendedor, rs.getString(12),0, rs.getDouble(15));
				orden.setComentario(rs.getString(14));
				String f= rs.getString(17);
				Fecha fecha= new Fecha(f);
				orden.setFecha(fecha.darFechaAnio_Mes_Dia());
				
				System.out.println("\n \n "+orden.getCliNombre());
				for (OrdenVentaLinea ovl : ventaLineas) 
				{
					System.out.println(ovl.getIdArticulo()+"  "+ovl.getPrecioImp());
				}
				
				
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return orden;
		

	}
	
	public static List<Cliente> darClientes(String q) 
	{
		List <Cliente> clientes = new ArrayList<>();
		try (Statement s = connection.createStatement();)
		{			
			
			ResultSet rs = s.executeQuery(q);
						
			while (rs.next())
			{
				try {
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
					clientes.add(c);
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return clientes;
		}
		
		return clientes;
	}
	
	public static Cliente darCliente(String q) 
	{
		Cliente c = new Cliente();
		try 
		{			
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(q);
						
			while (rs.next())
			{
				try {
					
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
					c.setDocumentoTipo("CI");
					
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return c;
		}
		
		return c;
	}
	
	public static List<MovStock> darQueueMovsStock(String consulta, int idEmpresa) 
	{
		List<MovStock> lista = new ArrayList<>();
		
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			
			String subQuery = "";
			
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
				m.setUsuario(rs.getString(8));
				m.setOrigen(rs.getInt(9));
				m.setOrigenDoc(rs.getLong(10));
				m.setIntentos(rs.getInt(11));
				
				m.setDocSolicitud(rs.getInt(12));
				m.setRazon(rs.getInt(13));
				m.setIdUsuario(rs.getInt(14));
				
				String msjErp = Util.saltoDeLinea(rs.getString(15), 10);				
				m.setMsjErp(msjErp);
				m.setEntrega(rs.getInt(16) == 1);
				
				subQuery = "SELECT d.cantidad,d.idarticulo FROM movStock_cabezal c "
						+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
						+"WHERE c.idempresa="+idEmpresa+" and c.id ="+m.getId();
				List<DataIDDescripcion> detalle = new ArrayList<>();
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery(subQuery);
				while (rs2.next()) {
					try {
						DataIDDescripcion data = new DataIDDescripcion(rs2.getInt(1),rs2.getString(2));
						detalle.add(data);
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				
				m.setDetalle(detalle);
				rs2.close();
				s2.close();
				
				lista.add(m);				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}
	
	public static MovStock darMovsStock(String consulta, int idEmpresa) 
	{
		MovStock m = new MovStock();
		
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			
			String subQuery = "";
			
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
				m.setUsuario(rs.getString(8));
				m.setOrigen(rs.getInt(9));
				m.setOrigenDoc(rs.getLong(10));
				m.setIntentos(rs.getInt(11));
				
				m.setDocSolicitud(rs.getInt(12));
				m.setRazon(rs.getInt(13));
				m.setIdUsuario(rs.getInt(14));
				
				String msjErp = Util.saltoDeLinea(rs.getString(15), 10);				
				m.setMsjErp(msjErp);
				m.setEntrega(rs.getInt(16) == 1);
				
				subQuery = "SELECT d.cantidad,d.idarticulo FROM movStock_cabezal c "
						+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
						+"WHERE c.idempresa="+idEmpresa+" and c.id ="+m.getId();
				List<DataIDDescripcion> detalle = new ArrayList<>();
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery(subQuery);
				while (rs2.next()) {
					try {
						DataIDDescripcion data = new DataIDDescripcion(rs2.getInt(1),rs2.getString(2));
						detalle.add(data);
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				
				m.setDetalle(detalle);
				rs2.close();
				s2.close();
								
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return m;
	}
	
	public static List<MovStock> darQueueConfirmacionTransferencia(String consulta, int idEmpresa) 
	{
		List<MovStock> lista = new ArrayList<>();
		
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			
			String subQuery = "";
			
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
				m.setUsuario(rs.getString(8));
				m.setOrigen(rs.getInt(9));
				m.setOrigenDoc(rs.getLong(10));
				m.setIntentos(rs.getInt(11));
				m.setNombreDestino(rs.getString(12));
				m.setDocSolicitud(rs.getInt(13));
				m.setRazon(rs.getInt(14));
				m.setIdUsuario(rs.getInt(15));

				String msjErp = Util.saltoDeLinea(rs.getString(16), 10);				
				m.setMsjErp(msjErp);
				
				subQuery = "SELECT d.cantidad,d.idarticulo FROM mov_confirm_transf_cabezal c "
						+"INNER JOIN mov_confirm_transf_detalle d on c.id=d.id and d.idempresa=c.idempresa "
						+"WHERE c.idempresa="+idEmpresa+" and c.id ="+m.getId();
				List<DataIDDescripcion> detalle = new ArrayList<>();
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery(subQuery);
				while (rs2.next()) {
					try {
						DataIDDescripcion data = new DataIDDescripcion(rs2.getInt(1),rs2.getString(2));
						detalle.add(data);
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				
				m.setDetalle(detalle);
				rs2.close();
				s2.close();
				
				lista.add(m);				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}
	
	public static MovStock darConfirmTransf(String consulta, int idEmpresa) 
	{
		MovStock m = new MovStock();
		
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			
			String subQuery = "";
			
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
				m.setUsuario(rs.getString(8));
				m.setOrigen(rs.getInt(9));
				m.setOrigenDoc(rs.getLong(10));
				m.setIntentos(rs.getInt(11));
				m.setNombreDestino(rs.getString(12));
				m.setDocSolicitud(rs.getInt(13));
				m.setRazon(rs.getInt(14));
				m.setIdUsuario(rs.getInt(15));

				String msjErp = Util.saltoDeLinea(rs.getString(16), 10);				
				m.setMsjErp(msjErp);
				
				subQuery = "SELECT d.cantidad,d.idarticulo FROM mov_confirm_transf_cabezal c "
						+"INNER JOIN mov_confirm_transf_detalle d on c.id=d.id and d.idempresa=c.idempresa "
						+"WHERE c.idempresa="+idEmpresa+" and c.id ="+m.getId();
				List<DataIDDescripcion> detalle = new ArrayList<>();
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery(subQuery);
				while (rs2.next()) {
					try {
						DataIDDescripcion data = new DataIDDescripcion(rs2.getInt(1),rs2.getString(2));
						detalle.add(data);
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				
				m.setDetalle(detalle);
				rs2.close();
				s2.close();				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return m;
	}
	
	public static List<GrabarRecepcion> darQueueRecepciones(String consulta, int idEmpresa) 
	{
		List<GrabarRecepcion> lista = new ArrayList<>();
		
		try 
		{
			//connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			
			String subQuery = "";
			
			while (rs.next())
			{
				System.out.println(rs.getString(1));
				GrabarRecepcion r = new GrabarRecepcion();
				r.setId(rs.getInt(1));
				r.setFecha(rs.getString(2));
				r.setEstado(rs.getInt(3));
				r.setDestino(rs.getInt(4));
				r.setIdUsuario(rs.getInt(5));
				r.setObservacion(rs.getString(6));
				r.setNumeroDoc(rs.getInt(7));
				r.setNroProveedor(rs.getInt(8));
				r.setSerieRemito(rs.getString(9));
				r.setNroRemito(rs.getInt(10));
				r.setIdRecepcion(rs.getInt(11));
				r.setTipoAfecta(rs.getString(12));
				r.setIdEmpresa(rs.getInt(13));
				r.setIntentos(rs.getInt(14));
				
				String msjErp = Util.saltoDeLinea(rs.getString(15), 10);				
				r.setMsjErp(msjErp);
				
				subQuery = "SELECT d.cantidad,d.idarticulo FROM mov_recepcion_cabezal c "
						+"INNER JOIN mov_recepcion_detalle d on c.id=d.id and d.idempresa=c.idempresa "
						+"WHERE c.idempresa="+idEmpresa+" and c.id ="+r.getId();
				List<DataIDDescripcion> detalle = new ArrayList<>();
				Statement s2 = connection.createStatement();
				ResultSet rs2 = s2.executeQuery(subQuery);
				int cantidad = 0;
				while (rs2.next()) {
					try {
						DataIDDescripcion data = new DataIDDescripcion(rs2.getInt(1),rs2.getString(2));
						detalle.add(data);
						cantidad += rs2.getInt(1);
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
				
				r.setCantidad(cantidad);
				r.setDetalle(detalle);
				rs2.close();
				s2.close();
				
				lista.add(r);				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}
	
	public static Map<Integer,pedidoFactura> darPedidosSinFactura(String consulta, Map<Integer,pedidoFactura> pedidos) 
	{	
		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) 
			{
				try {
					pedidoFactura p= new pedidoFactura(rs.getLong(1), rs.getInt(2));
					pedidos.put(p.getNroFactura(), p);
				} catch (Exception e) {e.printStackTrace();}
				
			}
			
			rs.close();	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		return pedidos;
	}
	
	public static List<jsonEstadoMP> DarPendienteColaEstadoMarketPlace(String consulta) 
	{
		List<jsonEstadoMP> lista = new ArrayList<>();

		try 
		{
			getConnection();
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				jsonEstadoMP l = new jsonEstadoMP();
				l.setId(rs.getInt(1));
				l.setIdPedido(rs.getLong(2));
				l.setJson(rs.getString(3));
				l.setCanal(rs.getInt(4));
				l.setIdEmpresa(rs.getInt(5));
				l.setIdMarketPlace(rs.getString(6));
				
				lista.add(l);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}
	
	public static Hashtable<Integer, CanalMarketPlace> DarCanalesMarketPlace(int idEmpresa, int idMarketPlace) 
	{
		Hashtable<Integer, CanalMarketPlace> lista = new Hashtable<>();
		String query = "select id, idUsr, secret, nombre, host, seller from ecommerce_marketplaces_canales where activo = 1 and idEmpresa="+idEmpresa+
				" and idMarketPlace="+idMarketPlace;
		try 
		{
			connection = getConnection(); 
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			while (rs.next())
			{
				CanalMarketPlace c = new CanalMarketPlace();
				c.setId(rs.getInt(1));
				c.setUser(rs.getString(2));
				c.setPass(rs.getString(3));
				c.setNombre(rs.getString(4));;
				c.setHost(rs.getString(5));
				c.setSeller(rs.getString(6));
				
				lista.put(c.getId(),c);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return lista;
	}

	
	public static void darMapeoFormaPago(List<MetodoPago> pagos, int idEmpresa) {
		try {
			if(connection == null) {
				connection = getConnection();
			}
			String str_pagos = "";
			for(MetodoPago mp: pagos) {
				str_pagos += "'"+mp.getDescripcion()+"',";
			}
			if(!str_pagos.equals("")) {
				str_pagos = str_pagos.substring(0,str_pagos.length()-1);
			}
	        Statement s = connection.createStatement();
	        String query ="SELECT mm.id, mm.comentario, mm.comentario_fenicio from mapeo_metodopagos mm "
	        		+ "where mm.comentario_fenicio in ( "+str_pagos+" ) and idEmpresa="+idEmpresa;
	  		ResultSet rs = s.executeQuery (query);
	  		
	  		while (rs.next())
	  		{   
	  			String desc = rs.getString(3);
	  			int id = rs.getInt(1);
	  			pagos.stream()
				.filter((x) -> x.getDescripcion().equals(desc))
				.forEach((n) -> n.setIdMetodoPago(id));
	  		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void darMapeoDescuento(List<Descuento> descuentos) {
		try {
			if(connection == null) {
				connection = getConnection();
			}
			String str_descuentos = "";
			for(Descuento d: descuentos) {
				str_descuentos += "'"+d.getCodigo()+"',";
			}
			if(!str_descuentos.equals("")) {
				str_descuentos = str_descuentos.substring(0,str_descuentos.length()-1);
			}
	        Statement s = connection.createStatement();
	        String query ="SELECT iddescuento, codigo from mapeo_descuento_laisla "
	        		+ "where codigo in ( "+str_descuentos+" ) ";
	  		ResultSet rs = s.executeQuery (query);
	  		
	  		while (rs.next())
	  		{   
	  			String desc = rs.getString(2);
	  			String id = rs.getString(1);
	  			descuentos.stream()
				.filter((x) -> x.getCodigo().equals(desc))
				.forEach((n) -> n.setCodigo(id));
	  		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String obtenerCIPedido(String consulta, int idEmpresa) {
		String ci = "";
		try {
			if(connection == null) {
				connection = getConnection();
			}
	  		Statement s = connection.createStatement();
	  		ResultSet rs = s.executeQuery (consulta);
	  		if (rs.next())
	  			{   
	  				ci = rs.getString(1);		
	  			}	  	
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return ci;
	}
	
	public static List<Remito> darRemitos(String albaranesBuscados, int idEmpresa){
		List<Remito> remitos = new ArrayList<>();
		try {
			if(connection == null) {
				connection = getConnection();
			}
			
			if(!albaranesBuscados.equalsIgnoreCase("")) {
							
				
				
		  		Statement s = connection.createStatement();
		  		String query = "SELECT msc.origenDoc, msc.doc, msc.origen, msc.destino, 0, '', msc.ultimoUpdate, msd.*,msc.docSolicitud FROM movStock_detalle msd"
		  				+ " INNER JOIN movStock_cabezal msc ON msc.id = msd.id AND msc.idEmpresa = msd.idEmpresa "
		  				+ " WHERE msc.doc IN ("+albaranesBuscados+") and estado=1 AND msd.idEmpresa = 6";
		  		
		  		
		  		System.out.println(query);
		  		ResultSet rs = s.executeQuery(query);
		  		int ultimoRemito = 0;
		  		List<RemitoLinea> lineas = new ArrayList<>();
		  		Remito remito = null;
		  		int qty = 0;
		  		while (rs.next())
		  			{   		  				
		  				if(ultimoRemito == 0 || ultimoRemito != rs.getInt(8)) {
		  					ultimoRemito = rs.getInt(8);
		  					if(remito != null) {
		  						remito.setLineas(lineas);
		  						remito.setUnidades(qty);
		  						qty = 0;
			  					lineas = new ArrayList<>();
			  					remitos.add(remito);
		  					}
		  					qty += rs.getInt(10);
		  					int idDestino = rs.getInt(4);
		  					if(idEmpresa == 6) {
		  						idDestino = 145;
		  					}
		  					String fecha = rs.getString(7);
		  					try {
								fecha = rs.getString(7).split(" ")[0]+ "<br>" +rs.getString(7).split(" ")[1];
							} catch (Exception e) {}
		  					remito = new Remito(0, rs.getInt(2), rs.getInt(3), idDestino, rs.getInt(5), rs.getString(6), fecha);
		  					remito.setIdPedidoWEB(rs.getInt(1));
		  					
		  				}
		  				RemitoLinea linea = new RemitoLinea(rs.getString(9), rs.getInt(10));
		  				linea.setEntrega(rs.getString(1));
		  				lineas.add(linea);
		  			}	
				if(lineas.size() > 0) {
					remito.setLineas(lineas);
					remito.setUnidades(qty);
					qty = 0;
					remitos.add(remito);
				}  	
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		return remitos;
	}
	
	public static void getMapeoArticulosfenicio(Lineas[] lineas, int idEmpresa) {
		try
		{
			String in = "";
			for(Lineas l:lineas) {
				if(l.getSku().equals("") || l.getSku().equals("N/D")) {
					l.setSku(l.getNombre());
				}
				in += "'"+l.getSku()+"',";
			}
			try {
				in = in.substring(0,in.length()-1);
			} catch (Exception e) {in="";}
			
			if(connection == null) {
				connection = getConnection();
			}
            Statement s = connection.createStatement();
	  		ResultSet rs = s.executeQuery ("SELECT skuFenicio, idarticulo from mapeo_articulos_fenicio where skuFenicio in ("+in+") and idempresa="+idEmpresa);
	  		List<Lineas> lista = Arrays.asList(lineas);
	  		
	  		while (rs.next())
	  		{   
	  			String sku = rs.getString(1);
	  			String art = rs.getString(2);
	  			try {
	  				lista.stream().filter((x) -> x.getSku().equals(sku)).forEach((n) -> n.setSku(art));
				} catch (Exception e) {
					e.printStackTrace();
				}	  			
	  		}
	  		lineas = (Lineas[]) lista.toArray();	  		
      }
      // Handle any errors that may have occurred.
      catch (Exception e) {
          e.printStackTrace();
      }
     
	}

	public static Hashtable<String, Double> darArtPrecio(String query) 
	{
		Hashtable<String, Double> retorno =  new Hashtable<>();
		try(Statement s = connection.createStatement();)
		{
			connection = getConnection();
			System.out.println(query);
	  		ResultSet rs = s.executeQuery (query);
	  		
	  		
	  		while (rs.next())
	  		{   
	  			String sku = rs.getString(1);
	  			Double precio = rs.getDouble(2);
	  			try 
	  			{
	  				retorno.put(sku, precio);
				} 
	  			catch (Exception e) 
	  			{
					e.printStackTrace();
				}	  			
	  		}
	  			  		
      }
      // Handle any errors that may have occurred.
      catch (Exception e) 
		{
          e.printStackTrace();
		}
		return retorno;
	}

	public static List<TicketCambio> darTicketCambioBAS(String query) 
	{
		List<TicketCambio> retorno = new ArrayList<>();
		
		try(Statement s = connection.createStatement();)
		{
			if(connection == null) 
			{
				connection = getConnection();
			}
            
	  		ResultSet rs = s.executeQuery (query);
	  		
	  		while (rs.next())
	  		{   
	  			TicketCambio tk = new TicketCambio(rs.getString(2), rs.getString(1),rs.getInt(3));
	  			retorno.add(tk);
	  		}
	  			  		
      }
      // Handle any errors that may have occurred.
      catch (Exception e) 
		{
          e.printStackTrace();
		}
		return retorno;
		
	}
	
	public static Hashtable<Integer, Integer> solicitud_a_origenes(String consulta, int idEmpresa) 
	{
		Hashtable<Integer, Integer> origenes = new Hashtable<>();
		
		try(Statement s = connection.createStatement();)
		{
			//connection = getConnection(); 
			ResultSet rs = s.executeQuery(consulta);
			
			String subQuery = "";
			
			while (rs.next())
			{				
				origenes.put(rs.getInt(1), rs.getInt(1));								
			}
			rs.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return origenes;
	}
	
	public static DataIDDescripcion darDataIdDescripcion(String consulta) 
	{
		DataIDDescripcion data = new DataIDDescripcion();

		try (Statement s = connection.createStatement();ResultSet rs = s.executeQuery(consulta);)
		{
			while (rs.next())
			{
				data = new DataIDDescripcion(rs.getInt(1), rs.getString(2));
				try {
					data.setDescripcionB(rs.getString(3));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return data;
	}

	public static ParametrosVisual darParametrosVS(String consulta) 
	{
		

		try 
		{
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(consulta);

			while (rs.next())
			{
				return new ParametrosVisual(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getString(4));
			}
			rs.close();
			s.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}

		return null;
	}
}
