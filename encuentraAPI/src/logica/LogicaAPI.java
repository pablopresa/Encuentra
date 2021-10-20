package logica;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import beans.CallBackPedido;
import beans.Usuario;
import beans.jsonEstadoMP;
import beans.api.EquiposPrintSpool;
import beans.api.GrabarRecepcion;
import beans.api.MovStock;
import beans.api.pedidoFactura;
import beans.api.json.PrintObject;
import beans.api.json.RetornoPedido;
import beans.api.json.SendMail;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.Remito;
import beans.datatypes.RemitoLinea;
import beans.encuentra.Articulo;
import beans.encuentra.Compras;
import beans.encuentra.EncuentraPedidoArticulo;
import beans.encuentra.Familias;
import beans.encuentra.Maestros;
import beans.encuentra.Ordenes;
import beans.encuentra.OrdenesLineas;
import integraciones.erp.billerTata.TicketCambio;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.ParametrosVisual;
import integraciones.marketplaces.fenicio.Lineas;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import persistencia.MSSQL_API;
import persistencia._EncuentraConexionAPI;
import persistencia._EncuentraConexionAPI2;




public class LogicaAPI 
{

	
	public boolean darIntegracionProductiva(int idIntegracion, int idEmpresa)
	{
		//return hayRegistro("select * from integraciones where idIntegracion = "+idIntegracion+" and IdEmpresa="+idEmpresa+" and Productivo = 1");
		return false;
	}
	
	public static RetornoPedido encuentraDarOjosPedido(String articulo) {
		Connection cone;
		try {
			cone = _EncuentraConexionAPI.getConnection("encuentra_unilam");

			String consulta = "SELECT CONCAT(DE.idDeposito,' - ',DE.Nombre,' ',DE.Nombre2) AS CTE, true as DSP , CONCAT(EST.Descripcion, ' ', OJO.idOjo ) AS UBI "+
							"	FROM pedido PE  "+
							"	INNER JOIN depositos DE ON PE.IdCliente = DE.idDeposito "+ 
							"	INNER JOIN  reposicion_articulos RAO ON DE.idDeposito = RAO.Destino "+
							"	INNER JOIN ojostienenarticulos OTA "+ 
							"	INNER JOIN ojos OJO ON OJO.idOjo = OTA.idOjo "+
							"	INNER JOIN estanterias EST ON EST.idEstanteria = OJO.idEstanteria AND EST.TipoSector IN (11,12,13) "+
							"	WHERE PE.idPedido = "+articulo+" "+
							"	GROUP BY OTA.idOjo";
			
			return _EncuentraConexionAPI.darArticulosOjos(consulta, articulo);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public  Usuario loginEncuentraSinEmpresa(String nombre, String pass) {
		Connection cone;
		try {
			cone = _EncuentraConexionAPI.getConnection("encuentra_unilam");
			String consulta = "select * from usuarios where BINARY Nickname = '"
					+ nombre + "' AND BINARY `Contraseña`= '" + pass + "' and baja=0";

			Usuario u = new Usuario();
			u = _EncuentraConexionAPI.login(consulta);
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public static Usuario loginEncuentraBantey(String token) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI.getConnection("encuentra_bantey");
			String consulta = "select U.* from usuarios U inner join apitoken T on T.idUsuario=U.idUsuario where Token = '"+token+"'";

			Usuario u = new Usuario();

			u = _EncuentraConexionAPI.login(consulta);
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Usuario loginEncuentraAPI(String token) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI.getConnection("encuentra_bantey");
			String consulta = "select U.* from usuarios U inner join apitoken T on T.idUsuario=U.idUsuario where Token = '"+token+"'";

			Usuario u = new Usuario();

			u = _EncuentraConexionAPI.login(consulta);
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public static Usuario loginEncuentraAPI2(String token) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			String consulta = "select U.* from usuarios U inner join apitoken T on T.idUsuario=U.idUsuario where Token = '"+token+"'";

			Usuario u = new Usuario();

			u = _EncuentraConexionAPI2.login(consulta);
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public static String darTokenVivoCliente(int idEmpresa, String uso,int tiempo) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			String consulta = "SELECT token FROM temp_tokens WHERE Uso = '"+uso+"' AND idEmpresa = "+idEmpresa+" AND TIMESTAMPDIFF(MINUTE,Leased,CURRENT_TIMESTAMP())<"+tiempo+"";

			 return _EncuentraConexionAPI2.dartokenApi(consulta);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}


	
	
	public static String darBarrio(String cpostal) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			String consulta = "SELECT * FROM barrios_localidades WHERE Cpostal = '"+cpostal+"' LIMIT 1";

			

			String u = _EncuentraConexionAPI2.barrio(consulta);
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	
	public static int limpiarOrden(String idOrdenERP)
	{
		int retorno = 0;
		try
		{
			idOrdenERP = idOrdenERP.replaceAll("[^\\d.]", "");
			
			retorno = Integer.parseInt((Integer.parseInt(idOrdenERP)+"").substring(0,6));
			
			
		}
		catch (Exception e) 
		{

		}
		
		
		
		return retorno;
	}
	
	public String darHostFenicioAPI (int idEmpresa, String idCanal)
	{
		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			String consulta = "SELECT 0, hostAPI FROM ecommerce_marketplaces_canales WHERE IdEmpresa = "+idEmpresa+" AND id = "+idCanal;

			

			String u = _EncuentraConexionAPI2.barrio(consulta);
			cone = null;
			return u;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public static String ingresarOrdenBantey(Ordenes[] ords) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI.getConnection("encuentra_bantey");
			String insert = "";
			String msj="";
			int retorno=0;
			
			
			for(Ordenes o:ords)
			{
				String venta = o.getIdVenta();
				
				if(o.getIdOrdenVentaERP()!=null)
				{
					int idOrden = limpiarOrden(o.getIdOrdenVentaERP());
					o.setIdOrdenVentaERP(idOrden+"");
				}
				if(!o.getIdOrdenVentaERP().equals("0"))
				{
					String cliente = "";
					String clienteVal = "";
					
					String guia = "";
					String guiaVal = "";
					
					String etiqueta = "";
					String etiquetaVal = "";
					
					String fecha = "";
					String fechaVal = "";
					
					String hora = "";
					String horaVal = "";
					
					String mail = "";
					String mailVal = "";
					
					String celular = "";
					String celularVal = "";
					
					String departamento = "";
					String departamentoVal = "";
					
					String localidad = "";
					String localidadVal = "";
					
					String direccion = "";
					String direccionVal = "";
					
					String observacion = "";
					String observacionVal = "";
					
					
					int envioMont = 0;
					int susp = 0;
					
					if(o.getDatosCliente()!=null && !o.getDatosCliente().equals(""))
					{
						cliente = ",cliente";
						clienteVal = ",'"+o.getDatosCliente()+"'";
					}
					else if(o.getDescripcion()!=null && !o.getDescripcion().equals(""))
					{
						cliente = ",cliente";
						clienteVal = ",'"+o.getDescripcion()+"'";
					}
					
								
					if(o.getGuia() !=null && o.getGuia().length>0)
					{
						guia = ",guia";
						String guias = "";
						if(o.getGuia().length>1)
						{
							for (int i = 0; i < o.getGuia().length; i++) 
							{
								guias+=o.getGuia()[i]+",";
							}
						}
						else
						{
							guias = o.getGuia()[0];
						}
						
						guiaVal = ",'"+guias+"'";
					}
				
					if(o.getUrlEtiqueta() !=null && !o.getUrlEtiqueta().equals(""))
					{
						etiqueta = ",URLEtiqueta";
						etiquetaVal = ",'"+o.getUrlEtiqueta()+"'";
					}
					
					if(o.getFecha() !=null && !o.getFecha().equals(""))
					{
						fecha = ",fechaEntrega";
						fechaVal = ",'"+o.getFecha()+"'";
					}
					
					if(o.getHora() !=null && !o.getHora().equals(""))
					{
						hora = ",hora";
						horaVal = ",'"+o.getHora()+"'";
					}
					
					if(o.getMail() !=null && !o.getMail().equals(""))
					{
						mail = ",mail";
						mailVal = ",'"+o.getMail()+"'";
					}
					
					if(o.getCelular() !=null && !o.getCelular().equals(""))
					{
						celular = ",celular";
						celularVal = ",'"+o.getCelular()+"'";
					}
					
					if(o.getDepartamento() !=null && !o.getDepartamento().equals(""))
					{
						departamento = ",departamento";
						departamentoVal = ",'"+o.getDepartamento()+"'";
					}
					
					if(o.getLocalidad() !=null && !o.getLocalidad().equals(""))
					{
						localidad = ",localidad";
						localidadVal = ",'"+o.getLocalidad()+"'";
					}
					
					if(o.getDireccion() !=null && !o.getDireccion().equals(""))
					{
						direccion = ",direccion";
						direccionVal = ",'"+o.getDireccion()+"'";
					}
					
					if(o.getObservacion() !=null && !o.getObservacion().equals(""))
					{
						observacion = ",observacion";
						observacionVal = ",'"+o.getObservacion()+"'";
					}
					
					try {
						if(o.isEnvio_montevideo()){
							envioMont = 1;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						if(o.isSuspendida()){
							susp = 1;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					

					insert="insert into ecommerce_venta(idVenta,canalVenta,codCanalSalida"+cliente+guia+etiqueta+fecha+",idVentaCanal,envioMontevideo,suspendida"+hora+mail+celular+departamento+localidad+direccion+observacion+") values("+o.getIdOrdenVentaERP()+",'"+o.getCodCanal()+"','"+o.getCodCanalSalida()+"'"+clienteVal+guiaVal+etiquetaVal+fechaVal+",'"+o.getIdVenta()+"',"+envioMont+","+susp+horaVal+mailVal+celularVal+departamentoVal+localidadVal+direccionVal+observacionVal+")";

					retorno=_EncuentraConexionAPI.persistir(insert);
					if(retorno > 0)
					{
						msj+="{\"idVenta\": \""+o.getIdOrdenVentaERP()+"\",\"codRespuesta\": \"1\" },";
					}
					else if(retorno == 0)
					{
						msj+="{\"idVenta\": \""+o.getIdOrdenVentaERP()+"\",\"codRespuesta\": \"0\" },";
					}
					else if(retorno == -2)
					{
						msj+="{\"idVenta\": \""+o.getIdOrdenVentaERP()+"\",\"codRespuesta\": \"-1\" },";
					}
					retorno = 0;
				}
				else
				{
					msj+="{\"idVenta\": \""+o.getIdOrdenVentaERP()+"\",\"codRespuesta\": \"-1\" },";
				}
				
				
			}	
			
			if(msj.length()>0)
			{
				msj=msj.substring(0,msj.length()-1);
				
			}
						 
			msj = "{\"ordenes\": ["+msj+"]}";
			cone = null;
			return msj;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public static String modificarOrdenBantey(Ordenes[] ords) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI.getConnection("encuentra_bantey");
			String insert = "";
			String msj="";
			int retorno=0;
			
			
			for(Ordenes o:ords)
			{
				String venta = o.getIdVenta();
				
				if(o.getIdOrdenVentaERP()!=null)
				{
					int idOrden = limpiarOrden(o.getIdOrdenVentaERP());
					o.setIdOrdenVentaERP(idOrden+"");
				}
				if(!o.getIdOrdenVentaERP().equals("0"))
				{
					String salidaVal = "";
					
					String clienteVal = "";
					
					String guiaVal = "";
					
					String etiquetaVal = "";
					
					String fechaVal = "";
					
					String suspVal = "";
					
					if(o.getCodCanalSalida()!=null && !o.getCodCanalSalida().equals("")){
						salidaVal = "codCanalSalida = '"+o.getCodCanalSalida()+"', ";
					}
					
					if(o.getDatosCliente()!=null && !o.getDatosCliente().equals(""))
					{
						
						clienteVal = " cliente ='"+o.getDatosCliente()+"', ";
					}
					else if(o.getDescripcion()!=null && !o.getDescripcion().equals(""))
					{
						clienteVal = " cliente = '"+o.getDescripcion()+"', ";
					}
					
								
					if(o.getGuia() !=null && o.getGuia().length>0)
					{
						
						String guias = "";
						if(o.getGuia().length>1)
						{
							for (int i = 0; i < o.getGuia().length; i++) 
							{
								guias+=o.getGuia()[i]+",";
							}
						}
						else
						{
							guias = o.getGuia()[0];
						}
						
						guiaVal = " guia='"+guias+"', ";
					}
				
					if(o.getUrlEtiqueta() !=null && !o.getUrlEtiqueta().equals(""))
					{					
						etiquetaVal = " URLEtiqueta='"+o.getUrlEtiqueta()+"', ";
					}
					
					if(o.getFecha() !=null && !o.getFecha().equals(""))
					{
						fechaVal = " fechaEntrega='"+o.getFecha()+"', ";
					}
					
					try {
						if(o.isSuspendida()==false){
							suspVal = " suspendida = 0, ";
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					insert = "update ecommerce_venta set "+salidaVal+clienteVal+guiaVal+etiquetaVal+fechaVal+suspVal;
					insert = insert.substring(0,insert.length()-2);
					insert+=" where idventa = "+o.getIdOrdenVentaERP();
					retorno=_EncuentraConexionAPI.persistir(insert);
					if(retorno > 0)
					{
						msj+="{\"idVenta\": \""+o.getIdOrdenVentaERP()+"\",\"codRespuesta\": \"1\" },";
					}
					else if(retorno == 0)
					{
						msj+="{\"idVenta\": \""+o.getIdOrdenVentaERP()+"\",\"codRespuesta\": \"0\" },";
					}
					else if(retorno == -2)
					{
						msj+="{\"idVenta\": \""+o.getIdOrdenVentaERP()+"\",\"codRespuesta\": \"-1\" },";
					}
					retorno = 0;
				}
				else
				{
					msj+="{\"idVenta\": \""+o.getIdOrdenVentaERP()+"\",\"codRespuesta\": \"-1\" },";
				}
				
				
			}	
			
			if(msj.length()>0)
			{
				msj=msj.substring(0,msj.length()-1);
				
			}
						 
			msj = "{\"ordenes\": ["+msj+"]}";
			cone = null;
			return msj;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public static String ingresarArticuloBantey(Articulo[] art) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI.getConnection("encuentra_bantey");
			String insert = "";
			String msj="";
			int retorno=0;
			
			for(Articulo a:art){
				
				insert="insert into articulos(idArticulo,Descripcion,AnchoCaja,AltoCaja,ProfCaja,especificaciones,peso,seriado) "+ 
				"values('"+a.getIdArticulo()+"','"+a.getDescripcion()+"',"+a.getAncho()+","+a.getAlto()+","+a.getProf()+",'"+a.getEspecificaciones()+
				"',"+a.getPeso()+","+a.getSeriado()+");";
				retorno=_EncuentraConexionAPI.persistir(insert);
				
				if(retorno==0){
					insert="update articulos set ";
					if(!a.getDescripcion().equals("")){
						insert+="Descripcion='"+a.getDescripcion()+"',";
					}
					if(!a.getAncho().equals("")){
						insert+="AnchoCaja="+a.getAncho()+",";
					}
					if(!a.getAlto().equals("")){
						insert+="AltoCaja="+a.getAlto()+",";
					}
					if(!a.getProf().equals("")){
						insert+="ProfCaja="+a.getProf()+",";
					}
					if(!a.getEspecificaciones().equals("")){
						insert+="especificaciones='"+a.getEspecificaciones()+"',";
					}
					if(!a.getPeso().equals("")){
						insert+="peso="+a.getPeso()+",";
					}
					if(!a.getSeriado().equals("")){
						insert+="seriado="+a.getSeriado()+",";
					}
					
					insert=insert.substring(0,insert.length()-1);
					insert+=" where idArticulo='"+a.getIdArticulo()+"';";
					retorno=_EncuentraConexionAPI.persistir(insert);
				}
				
				if(retorno>=0){
					for(Familias f:a.getFamilias()){
						insert="insert into artcategoriaart values('"+a.getIdArticulo()+"','"+f.getIdFamilia()+"')";
						retorno=_EncuentraConexionAPI.persistir(insert);
						
						if(retorno>=0){
							insert="insert into artseccionart values('"+a.getIdArticulo()+"','"+f.getIdSubFamilia()+"')";
							retorno=_EncuentraConexionAPI.persistir(insert);
							
							if(retorno<0){
								msj+="{\"idArticulo\": \""+a.getIdArticulo()+"\",\"Respuesta\": \"Error al agregar la sub familia "+f.getIdSubFamilia()+"\" },";
								break;
							}
						}
						else{
							msj+="{\"idArticulo\": \""+a.getIdArticulo()+"\",\"Respuesta\": \"Error al agregar la familia "+f.getIdFamilia()+"\" },";
							break;
						}
						
					}
					
					if(retorno>=0){
						for(String barra:a.getBarrasL()){
							insert="insert into artbarra values('"+a.getIdArticulo()+"','"+barra+"')";
							retorno=_EncuentraConexionAPI.persistir(insert);
							
							if(retorno<0){
								msj+="{\"idArticulo\": \""+a.getIdArticulo()+"\",\"Respuesta\": \"Error al agregar el codigo de barra "+barra+"\" },";
								break;
							}
						}
					}
					
					if(retorno>=0){
						if(!a.getBarras().equals("")){	
							insert="insert into artbarra values('"+a.getIdArticulo()+"','"+a.getBarras()+"')";
							retorno=_EncuentraConexionAPI.persistir(insert);
							
							if(retorno<0){
								msj+="{\"idArticulo\": \""+a.getIdArticulo()+"\",\"Respuesta\": \"Error al agregar el codigo de barra "+a.getBarras()+"\" },";
								break;
							}
							else{
								msj+="{\"idArticulo\": \""+a.getIdArticulo()+"\",\"Respuesta\": \"OK\" },";
							}
						}
						else{
							msj+="{\"idArticulo\": \""+a.getIdArticulo()+"\",\"Respuesta\": \"OK\" },";
						}
					}
					
				}
				else{
					msj+="{\"idArticulo\": \""+a.getIdArticulo()+"\",\"Respuesta\": \"Error al agregar datos del articulo\" },";
				}
				
				retorno = 0;
				insert="";
			}		
			
			if(msj.length()>0)
			{
				msj=msj.substring(0,msj.length()-1);
				
			}
						 
			msj = "{\"Articulos\": ["+msj+"]}";
			cone = null;
			return msj;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public static String ingresarMaestrosBantey(Maestros[] msts) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI.getConnection("encuentra_bantey");
			String insert = "";
			String msj="";
			int retorno=0;
			
			for(Maestros m:msts){
				
				if(m.getIdMaestro().equals("Depositos")){
					insert="insert into depositos (IdDepositoSAP,Nombre) values("+m.getId()+",'"+m.getDescripcion()+"') ON DUPLICATE KEY UPDATE Nombre='"+m.getDescripcion()+"';";
				}
				if(m.getIdMaestro().equals("Canales_Venta")){
					insert="insert into ecommerce_canal_ml (id,nombre) values("+m.getId()+",'"+m.getDescripcion()+"') ON DUPLICATE KEY UPDATE nombre='"+m.getDescripcion()+"';";
				}
				if(m.getIdMaestro().equals("Canales_de_Salida")){
					insert="insert into ecommerce_envioml (nombre,idDeposito,pickup) values('"+m.getDescripcion()+"',"+m.getId()+",0) ON DUPLICATE KEY UPDATE nombre='"+m.getDescripcion()+"';";				
				}
				if(m.getIdMaestro().equals("Marcas")){
					insert="insert into art_marca (id,descripcion) values("+m.getId()+",'"+m.getDescripcion()+"') ON DUPLICATE KEY UPDATE descripcion='"+m.getDescripcion()+"';";
				}
				if(m.getIdMaestro().equals("Familias")){
					insert="insert into art_categoria (id,descripcion) values("+m.getId()+",'"+m.getDescripcion()+"') ON DUPLICATE KEY UPDATE descripcion='"+m.getDescripcion()+"';";	
				}
				if(m.getIdMaestro().equals("Sub_Familias")){
					insert="insert into art_seccion (id,descripcion) values("+m.getId()+",'"+m.getDescripcion()+"') ON DUPLICATE KEY UPDATE descripcion='"+m.getDescripcion()+"';";			
				}
				if(m.getIdMaestro().equals("Tipo_ documento")){
					insert="insert into tiposdocumento (id,descripcion) values("+m.getId()+",'"+m.getDescripcion()+"') ON DUPLICATE KEY UPDATE descripcion='"+m.getDescripcion()+"';";
				}
				
				retorno=_EncuentraConexionAPI.persistir(insert);
				
				if(retorno > 0)
				{
					msj+="{\"idMaestro\": \""+m.getIdMaestro()+"\",\"descripcion\": \""+m.getDescripcion()+"\",\"codRespuesta\": \"1\" },";
				}
				else if(retorno == 0)
				{
					msj+="{\"idMaestro\": \""+m.getIdMaestro()+"\",\"descripcion\": \""+m.getDescripcion()+"\",\"codRespuesta\": \"0\" },";
				}
				else if(retorno == -2)
				{
					msj+="{\"idMaestro\": \""+m.getIdMaestro()+"\",\"descripcion\": \""+m.getDescripcion()+"\",\"codRespuesta\": \"-1\" },";
				}
				retorno = 0;
				insert = "";
			}		
			
			if(msj.length()>0)
			{
				msj=msj.substring(0,msj.length()-1);				
			}
						 
			msj = "{\"Maestros\": ["+msj+"]}";
			cone = null;
			return msj;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public static String ingresarEcommercePedido(Ordenes[] ords) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI.getConnection("encuentra_bantey");
			String insert = "";
			String msj="";
			int retorno=0;
			
			for(Ordenes o:ords)
			{				
				insert="insert into ecommerce__pedido (`idPedido`, `descripcion`, `UnidadesTotal`, `EstadoEcommerce`, `cerrado`, "+
				"`URLetiqueta`, `FormaPago`,idCanalML) VALUES ("+
				o.getIdVenta()+", '"+o.getDescripcion().replace("'", "")+"', "+o.getUnidadesTotal()+", 'procesando', '0', '"+
				o.getUrlEtiqueta()+"', '"+o.getFormaPago()+"',"+o.getCodCanal()+");";
						
				retorno=_EncuentraConexionAPI.persistir(insert);
				
				if(retorno >= 0)
				{
					insert="insert into articulo (`idArticulo`, `Descripcion`, `IdTipo`) values ('"+o.getIdVenta()+"', '"+o.getDescripcion().replace("'", "")+
							"',"+2+");";
					
					retorno=_EncuentraConexionAPI.persistir(insert);
					
					if(retorno>=0){
						
						for(OrdenesLineas ol:o.getLineas()){
							
							insert="insert into ecommerce_pedido_articulos (`idPedido`, `idArticulo`, `cantidadPedido`) VALUES ("+
									o.getIdVenta()+",'"+ol.getIdArticulo()+"',"+ol.getCantidad()+");";
							
							retorno=_EncuentraConexionAPI.persistir(insert);
							
							if(retorno<0){
								msj+="{\"idVenta\": \""+o.getIdVenta()+"\",\"Respuesta\": \"Error al ingresar articulo "+ol.getIdArticulo()+"\" },";
								break;
							}
							
						}
						
						if(retorno>=0){
							  msj+="{\"idVenta\": \""+o.getIdVenta()+"\",\"Respuesta\": \"OK\" },";
						}
					}
					else{
						msj+="{\"idVenta\": \""+o.getIdVenta()+"\",\"Respuesta\": \"Error al ingresar la venta como articulo\" },";
					}
				}
				else 
				{
					msj+="{\"idVenta\": \""+o.getIdVenta()+"\",\"Respuesta\": \"Error al ingresar la venta\" },";
				}
				retorno = 0;
				insert = "";
			}		
			
			if(msj.length()>0)
			{
				msj=msj.substring(0,msj.length()-1);
				
			}
						 
			msj = "{\"Ventas\": ["+msj+"]}";
			cone = null;
			return msj;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}


	public static List<beans.api.DataPrintable> darListaToPrint(String consulta) 
	{
		Connection cone;
		try 
		{
			
			cone = _EncuentraConexionAPI2.getConnection();
			

			

			List<beans.api.DataPrintable> u = _EncuentraConexionAPI2.darColaImpresion(consulta);
			cone = null;
			return u;

		} 
		catch (Exception e) 
		{

			try 
			{
				cone = null;
				cone = _EncuentraConexionAPI2.getConnection();
		
				List<beans.api.DataPrintable> u = _EncuentraConexionAPI2.darColaImpresion(consulta);
				cone = null;
				return u;

			} catch (Exception e2) {

				e.printStackTrace();
				return null;
			}
		}
	}
	
	public static List<SendMail> darListaToSend() 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			//String consulta = "select id,destino,asunto,body,origen from mail_spooler where send = 0 and idEmpresa = "+empresa+"";
			String consulta = "select id,destino,asunto,body,origen,idEmpresa,adjunto from mail_spooler where send = 2 ";
			
			List<SendMail> mails = _EncuentraConexionAPI2.darColaEnvioMails(consulta);
			cone = null;
			return mails;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}


	public static void persistir(String consulta) 
	{
		try 
		{
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			
			_EncuentraConexionAPI2.persistir(consulta);
			cone = null;
			

		} 
		catch (Exception e) 
		{

			e.printStackTrace();
			
		}
		
	}
	
	public static void PutPrintSpooler(PrintObject p, int empresa){
		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			if(p.getIdEquipo()==null || p.getIdEquipo().equals("")){
				p.setIdEquipo("1");
			}
			String consulta = "";
			String id = "";
			for(int i=1; i <= p.getVias(); i++) {
				id = i > 1 ? p.getId()+"_"+i : p.getId();
				consulta += "insert into print_spooler (id,idEmpresa,urlArchivo,porait,printerID,idEquipo) values ('"+id+"',"+empresa+",'"
						+p.getUrl()+"',"+p.getPorait()+","+p.getPrinterId()+","+p.getIdEquipo()+") "
						+ "on duplicate key update urlArchivo= '"+p.getUrl()+"', printed = 0, porait = "+p.getPorait()+", printerID = "+p.getPrinterId()+", idEquipo = "+p.getIdEquipo()+";";
			}
			 

			_EncuentraConexionAPI2.persistir(consulta);
			cone = null;

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	
	public static void RePrintSpooler(PrintObject p, int empresa){
		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			if(p.getIdEquipo()==null || p.getIdEquipo().equals("")){
				p.setIdEquipo("1");
			}
			String consulta = "update print_spooler set urlArchivo = '"+p.getUrl()+"', IdEquipo = "+p.getIdEquipo()+", printed = 0, porait = "+p.getPorait()+", "+
			"printerID = "+p.getPrinterId()+" where id='"+p.getId()+"' and idEmpresa="+empresa;

			_EncuentraConexionAPI2.persistir(consulta);
			cone = null;

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public static void PutMailSpooler(SendMail[] mails, int empresa){
		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			StringBuilder sb = new StringBuilder();
			int count=0;
			for(SendMail m :mails){
				count++;
				String all_body = "";
				for(String b: m.getBody()){
					all_body += b;
				}
				all_body = all_body.replace("'", "\\'");
				
				
				String consulta = "insert into mail_spooler (id,idEmpresa,destino,asunto,body,origen,adjunto) values "+
				"('"+m.getId()+"',"+empresa+",'"+m.getDestino()+"','"+m.getAsunto()+"','"+all_body+"','"+m.getOrigen()+"','"+m.getAdjunto()+"');";
				System.out.println(consulta);
				_EncuentraConexionAPI2.persistir(consulta);
			}
			
			cone = null;

		} catch (Exception e) {

			e.printStackTrace();
		}
	}


	public String darToken(int idEmpresa, int idUsuario) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			String consulta = "SELECT T.Token FROM apitoken T INNER JOIN usuarios U ON U.idUsuario = T.idUsuario AND U.idUsuario = "+idUsuario+" AND U.idEmpresa= "+idEmpresa+"";
			
			String r =  _EncuentraConexionAPI2.dartokenApi(consulta);
			cone = null;
			return r;
			

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public String darToken(int idEmpresa) 
	{

		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			String consulta = "SELECT T.Token FROM apitoken T INNER JOIN usuarios U ON U.idUsuario = T.idUsuario where U.idEmpresa= "+idEmpresa+"";
			
			String r =  _EncuentraConexionAPI2.dartokenApi(consulta);
			cone = null;
			return r;
			

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	List<DataIDDescripcion> darListaDataIdDescripcionMYSQLConsulta(String q) 
	{

		try {
			Connection cone = _EncuentraConexionAPI2.getConnection();
			
			List<DataIDDescripcion>  r =  _EncuentraConexionAPI2.darlistaDataIdDesc(q);
			cone = null;
			return r;
			

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public  List<Compras> sincroPedidosWebForusUY(int idEmpresa, Map<String, DataIDDescripcion> destinoPedidos, List<DataIDDescripcion> depositosPick, int idCanal) 
	{
		List<Compras> compras = new ArrayList<>();
		try {
			
			depositosPick.remove(0);
			Map<String, String> depositosPickHT = new HashMap<>();
			for (DataIDDescripcion d : depositosPick) 
			{
				depositosPickHT.put(d.getDescripcion(), String.valueOf(d.getId()));
			}
			
				//QUERY
			String queryPedidos = "SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED select Vta_Total, DocV_NumeroDoc, 'estado' estado, Doc_Fecha, '$' moneda, Doc_IdDepDestino, "+
					"'0.0' as montoenvio, 'MetodoPago' metodoPago,DocV_Serie,Doc_Comentario, Cli_Apellido, Cli_Nombre, VEnt_Ciudad as 'localidad', '' mail, "+ 
					"VEnt_Departamento, 0 latitud, VEnt_CliTelefono, 0 longitud,VEnt_Direccion,VEnt_Ciudad,VEnt_CodigoPostal,VEnt_Comentario, 'CI' 'docTipo', CONCAT(Cli_Numero,Cli_NumeroDig),doc_Fecha "+
					"  from dbo.TESTencuentra_VENTAWEB WHERE "
					/*****************************************/
					//+ " Doc_Comentario like '%SUPERMALL%'  and "
					/*****************************************/
					+ " DocV_NumeroDoc ";
			
				//VENTAS QUE NO TENIAMOS DISTRIBUCIONES
			
			String ventasAtrasadas="";
			List<DataIDDescripcion> idVentasAtrasadas = new ArrayList<>();
			try {
				idVentasAtrasadas = darListaDataIdDescripcionMYSQLConsulta("select id,'' from aaatemporal");
				if(!idVentasAtrasadas.isEmpty())
					idVentasAtrasadas.remove(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			if(!idVentasAtrasadas.isEmpty()){
				for(DataIDDescripcion id:idVentasAtrasadas){
					ventasAtrasadas+=id.getId()+",";
				}
				ventasAtrasadas = ventasAtrasadas.substring(0,ventasAtrasadas.length()-1);
				String queryAtrasados = queryPedidos + " in ("+ventasAtrasadas+")"; 
				compras = MSSQL_API.darComprasWeb(queryAtrasados, depositosPickHT,0,idEmpresa,destinoPedidos,idCanal);
				persistir("delete from aaatemporal where idEmpresa="+idEmpresa);
			}			
			
//			VENTAS NUEVAS
			int ultimaVenta = darNextSincWEB(idEmpresa,idCanal);		
			List<Compras> compras2 = MSSQL_API.darComprasWeb(queryPedidos, depositosPickHT,ultimaVenta,idEmpresa,destinoPedidos,idCanal);
			
			
			compras.addAll(compras2);

			return compras;

		} catch (Exception e) {

			e.printStackTrace();
			return compras;
		}

	}
	
	public  int darNextSincWEB(int idEmpresa, int idCanal) 
	{
		int retorno = 0;
		
		try 
		{
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			retorno = _EncuentraConexionAPI2.UltimoId("select max(ultimaVenta) from ecommerce_sincro WHERE idEmpresa="+idEmpresa+" and idCanal="+idCanal);
			 

		} catch (Exception e) {
			
			e.printStackTrace();
			return retorno;
		}
		return retorno;

		
	}
	
	public  List<Compras> sincroPedidosWeb(int idEmpresa, String token) 
	{
		List<Compras> compras = new ArrayList<>();
		try 
		{
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			
			Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
			List<DataIDDescripcion>depositosPick = cen.DarDatosPutOrders(token,2);
			
			Hashtable<String, String> depositosPickHT = new Hashtable<>();
			for (DataIDDescripcion d : depositosPick) 
			{
				depositosPickHT.put(d.getDescripcion(), String.valueOf(d.getId()));
			}
			
				//QUERY
			String queryPedidos = "SELECT  V.AMOUNT,V.OPERATIONID, 'estado' estado, V.FEC_ULT_ACT,'$' moneda,IFNULL(V.CARRIER,0),"
								+ "IFNULL(V.SHIPPINGCOST,'0'), 'MetodoPago' metodoPago, V.IDVENTAPRODUCTECA, "+
								"	IFNULL(V.CONTACTPERSON,''), IFNULL(V.NAME,''),IFNULL(V.NAME,''), V.STATE,IFNULL(V.CITY,''),IFNULL(V.PHONENUMBER,''), "
								+ "0 longitud, IFNULL(V.STREETNAME,''), V.STATE, IFNULL(V.ZIPCODE,''), IFNULL(V.ADRESSNOTES,''), 'CI' , "
								+ "IFNULL(V.TAXID,'0'),V.MARKETPLACE, V.FEC_ULT_ACT,V.PDF, IFNULL(v.trackingnumber,''), "
								+ "IFNULL(fe.articulo,IF(V.SHIPPINGCOST IS NOT NULL,fesd.articulo,'0')), "
								+ "IFNULL(V.STREETNUMBER,''), IFNULL(V.STREETNUMBER_BI,''), V.IsFreeShipping "+
								"	FROM producteca_ecom_pedidos V "+
								" LEFT OUTER JOIN ecommerce_articulo_forma_envio fe on V.CARRIER = fe.idCourier and fe.idEmpresa="+idEmpresa
								+" LEFT OUTER JOIN ecommerce_articulo_forma_envio fesd ON -1 = fesd.idCourier and fesd.idEmpresa="+idEmpresa
								+ " WHERE V.Sincronizada=0";
		
			
			//VENTAS NUEVAS
			int ultimaVenta = cen.DarDatosPutOrders(token,3).get(0).getId();		
			List<Compras> compras2 =_EncuentraConexionAPI2.darComprasWeb(queryPedidos,idEmpresa);
			
			
			compras.addAll(compras2);

			return compras;

		} catch (Exception e) {

			e.printStackTrace();
			return compras;
		}

	}


	public void updatePedidoSinc(String inns, int estado) 
	{
		try {
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			
			String consulta = "UPDATE producteca_ecom_pedidos SET Sincronizada = "+estado+" WHERE OPERATIONID IN ("+inns+")";
			
				System.out.println(consulta);
				_EncuentraConexionAPI2.persistir(consulta);
			
			cone = null;

		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}


	public List<beans.encuentra.Cliente> darClientesProducteca() 
	{

		try 
		{
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			
			String q = "SELECT substring_index(substring_index(P.CONTACTPERSON, ' ', 1), ' ', -1) AS `nombre`, \r\n" + 
					"				     substring_index(substring_index(P.CONTACTPERSON, ' ', 2), ' ', -1) AS `apellido`,\r\n" + 
					"					      IFNULL(IFNULL(P.STREETNAME,P.STREETNAME_BI),'') calle, \r\n" + 
					"							IFNULL(IFNULL(P.STREETNUMBER, P.STREETNUMBER_BI),'') nroPuerta, \r\n" + 
					"							IFNULL(ifnull(P.STATE, P.CITY),'') Departamento, \r\n" + 
					"							IFNULL(P.CITY,'') 'ciudad', \r\n" + 
					"							IFNULL(P.ZIPCODE,'') CP, \r\n" + 
					"							ifnull(P.PHONENUMBER,''), \r\n" + 
					"							P.MAIL, \r\n" + 
					"							ifnull(P.TAXID,P.IDVENTAPRODUCTECA) cedula, \r\n" + 
					"							ifnull(P.ADRESSNOTES,''),\r\n"+
					"							OPERATIONID" + 
					"						 FROM producteca_ecom_pedidos P \r\n" + 
					"					WHERE P.Sincronizada = 0 ;";
			return _EncuentraConexionAPI2.darClientesProducteca(q);
		}
		catch (Exception e) 
		{
			return new ArrayList<beans.encuentra.Cliente>();
		} 
		 
	}
	public List<beans.encuentra.Cliente> reDarClientesProducteca() 
	{

		try 
		{
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			
			String q = "SELECT substring_index(substring_index(P.CONTACTPERSON, ' ', 1), ' ', -1) AS `nombre`, \r\n" + 
					"				     substring_index(substring_index(P.CONTACTPERSON, ' ', 2), ' ', -1) AS `apellido`,\r\n" + 
					"					      IFNULL(IFNULL(P.STREETNAME,P.STREETNAME_BI),'') calle, \r\n" + 
					"							IFNULL(IFNULL(P.STREETNUMBER, P.STREETNUMBER_BI),'') nroPuerta, \r\n" + 
					"							IFNULL(ifnull(P.STATE, P.CITY),'') Departamento, \r\n" + 
					"							IFNULL(P.CITY,'') 'ciudad', \r\n" + 
					"							IFNULL(P.ZIPCODE,'') CP, \r\n" + 
					"							ifnull(P.PHONENUMBER,''), \r\n" + 
					"							P.MAIL, \r\n" + 
					"							ifnull(P.TAXID,P.IDVENTAPRODUCTECA) cedula, \r\n" + 
					"							ifnull(P.ADRESSNOTES,''),\r\n"+
					"							OPERATIONID" + 
					"						 FROM producteca_ecom_pedidos P \r\n" + 
					"					WHERE P.OPERATIONID in (4441213717,4445407637,4445412226,4445413993,4445421782);";
			return _EncuentraConexionAPI2.darClientesProducteca(q);
		}
		catch (Exception e) 
		{
			return new ArrayList<>();
		} 
		 
	}
	
	public beans.encuentra.Cliente darClienteProducteca(Long idVenta) 
	{

		try 
		{
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			
			String q = "SELECT substring_index(substring_index(P.CONTACTPERSON, ' ', 1), ' ', -1) AS `nombre`, \r\n" + 
					"				     substring_index(substring_index(P.CONTACTPERSON, ' ', 2), ' ', -1) AS `apellido`,\r\n" + 
					"					      IFNULL(IFNULL(P.STREETNAME,P.STREETNAME_BI),'') calle, \r\n" + 
					"							IFNULL(IFNULL(P.STREETNUMBER, P.STREETNUMBER_BI),'') nroPuerta, \r\n" + 
					"							IFNULL(ifnull(P.STATE, P.CITY),'') Departamento, \r\n" + 
					"							IFNULL(P.CITY,'') 'ciudad', \r\n" + 
					"							IFNULL(P.ZIPCODE,'') CP, \r\n" + 
					"							ifnull(P.PHONENUMBER,''), \r\n" + 
					"							P.MAIL, \r\n" + 
					"							ifnull(P.TAXID,P.IDVENTAPRODUCTECA) cedula, \r\n" + 
					"							ifnull(P.ADRESSNOTES,''),\r\n"+
					"							OPERATIONID" + 
					"						 FROM producteca_ecom_pedidos P \r\n" + 
					"					WHERE P.operationid = "+idVenta;
			return _EncuentraConexionAPI2.darClienteProducteca(q);
		}
		catch (Exception e) 
		{
			return null;
		} 
		 
	}


	public static List<DataIDDescripcion> darListaDataIdDescripcionConsulMYSQL(String q) 
	{
		try 
		{
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			
			return _EncuentraConexionAPI2.darListaDataIdDescripcionConsulMYSQL(q);
		}
		catch (Exception e) 
		{
			return new ArrayList<>();
		} 
	}

	public List<DataIDDescripcion> darArticulosCombo(String idCombo, int idEmpresa) 
	{
		String q = "select ac.Cantidad, ac.IdArticulo,0, ac.Precio, a.Descripcion from ArtCombo ac join Articulo a on a.IdArticulo = ac.IdArticulo where IdCombo = '"+idCombo+"'";
		return MSSQL_API.darIDDescripcion(q, idEmpresa);
	}
	
	public static List<DataIDDescripcion> ecommerceDocumentosEntregas(Long numeroPedido, int idEmpresa) {
		String q = "SELECT NumeroDoc, IdArticulo, \"Cantidad pendiente\" \r\n" + 
				"FROM encuentra_distribucion \r\n" + 
				"WHERE comentario LIKE 'Trans.Venta Dep. 1200 Doc. A-"+numeroPedido+"%'";
		return MSSQL_API.darEcommerceDocumentosEntregas(q, idEmpresa);
	}
	
	public static String UpdateEcommerceDocumentosEntregas(int procesado, int idEmpresa, int idLlamada, String mensaje) {
		
		try {
			
			
			String q = "UPDATE callbackpedidoencuentra \r\n" + 
				"SET procesado = "+procesado+", mensaje = '"+mensaje+"' \r\n" +
				"WHERE idLlamada = "+idLlamada+" \r\n" + 
				"AND idEmpresa = "+idEmpresa+";";
			
			
				System.out.println(q);
				
			return q;
			

		} catch (Exception e) {

			e.printStackTrace();
			return "";
		}
	}


	public static EquiposPrintSpool darListaEquiposPrintSpool(Integer idEquipo, int idEmpresa) 
	{
		String qEquipo = "";
		if(idEquipo!=-1)
		{
			qEquipo = "AND idEquipo ="+idEquipo+" ";
		}
		
		String q = "SELECT idEquipo,ip_nombre,estado,fechaUpdate,Impresora1,Impresora2,Impresora3,Impresora4 FROM impresoras_estado WHERE idEmpresa = "+idEmpresa+" "+qEquipo;
		
		return _EncuentraConexionAPI2.darEquiposPrintSpool(q);
	}
	
	public List<DataIDDescripcion> pedidosCanceladosAPI(String pedidos, int idEmpresa){
		try {
			String query = "select 0,operationid,0,'' from producteca_ecom_pedidos where operationid in ("+pedidos+") and iscanceled=1";
			
			return _EncuentraConexionAPI2.darListaDataIdDescripcionConsulMYSQL(query);
			
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	
	public void saveImportCustomer(List<beans.encuentra.Cliente> clientes,int idEmpresa) 
	{
		try {
			StringBuilder sb = new StringBuilder();
			
			for (beans.encuentra.Cliente c: clientes) {
				String nroAP="";
				if(c.getNroApto()!=null)
				{
					nroAP = c.getNroApto();
				}
				sb.append("INSERT IGNORE INTO ecommerce_import_clientes (Nombre,Apellido,Direccion,Ciudad,Departamento,cpostal,telefono,mail,cedula,"
						+ "digito,idPedido,DireccionNota,IdEmpresa,nroPuerta,apto,rut,razonSocial) " + 
						"VALUES ('"+c.getNombre()+"','"+c.getApellido()+"','"+c.getCalle()+"', "
								+ "'"+c.getCiudad()+"','"+c.getDepartamento()+"','"+c.getCp()+"','"+c.getTelefono()+"','"+c.getEmail()+"', "
										+ "'"+c.getDocumentoNro()+"',"
										+ "'',"+c.getIdPedido()+",'"+c.getObs()+"',"+idEmpresa+",'"+c.getNroPuerta()+"','"+nroAP+"','"+
										c.getRut()+"','"+c.getRazonSocial()+"'); ");
			}
			
			persistir(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void saveImport1Customer(beans.encuentra.Cliente c,int idEmpresa) 
	{
		try {
			Util u = new Util();
			StringBuilder sb = new StringBuilder();
			
			String nroAP="";
			if(c.getNroApto()!=null)
			{
				nroAP = c.getNroApto();
			}
			c.setApellido(c.getApellido());
			
			
			sb.append("INSERT INTO ecommerce_import_clientes (Nombre,Apellido,Direccion,Ciudad,Departamento,cpostal,telefono,mail,cedula,"
					+ "digito,idPedido,DireccionNota,IdEmpresa,nroPuerta,apto,rut,razonSocial) " + 
					"VALUES ('"+c.getNombre()+"','"+c.getApellido()+"','"+c.getCalle()+"', "
							+ "'"+c.getCiudad()+"','"+c.getDepartamento()+"','"+c.getCp()+"','"+c.getTelefono()+"','"+c.getEmail()+"', "
									+ "'"+c.getDocumentoNro()+"',"
									+ "'',"+c.getIdPedido()+",'"+c.getObs()+"',"+idEmpresa+",'"+c.getNroPuerta()+"','"+nroAP+"','"+
									c.getRut()+"','"+c.getRazonSocial()+"'); ");
		
		
			persistir(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//MOVIMIENTOS DE STOCK
	public int RegistrarMovimientoStock(int origen, int destino, int usu, List<DataIDDescripcion> arts, int idEmpresa,Long OrigenDoc, int docSolicitud, 
			int razon, boolean entrega) 
	{		
		int idRegistro = 0;
		int intEntrega = 0;
		if(entrega) {
			intEntrega = 1;
		}
		try {			
			String registro = "insert into movStock_cabezal(usuario,origen,destino,idEmpresa,origenDoc,docSolicitud,razon,entrega) "
					+ "values("+usu+","+origen+","+destino+","+idEmpresa+","+OrigenDoc+","+docSolicitud+","+razon+","+intEntrega+")";
			
			
			Connection cone;
			cone = _EncuentraConexionAPI2.getConnection();
			System.out.println(registro);
			idRegistro = _EncuentraConexionAPI2.persistirDarUltimo(registro, "movStock_cabezal", "id",idEmpresa);
			cone =null;
			StringBuilder sb = new StringBuilder();
			for(DataIDDescripcion a:arts){
				sb.append("insert into movstock_detalle values ("+idRegistro+",'"+a.getDescripcion()+"',"+a.getId()+","+idEmpresa+");");
			}
			
			System.out.println(sb.toString());
			persistir(sb.toString());
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return idRegistro;
	}
	
	public List<MovStock> queueMovsStock(int idEmpresa, int id){
		@SuppressWarnings("unused") Connection cone;
		
		List<MovStock> movs = null;
		try 
		{
			cone = _EncuentraConexionAPI2.getConnection();
			String idQuery = "";
			if(id!=0) {
				idQuery = " and c.id ="+id;
			}
			String query = "SELECT c.id,c.estado,c.destino,c.doc,sum(d.cantidad),c.fecha,c.observacion,'', "
					+"origen,c.origenDoc,c.intentos,DocSolicitud,razon,c.usuario,c.msjErp,c.entrega FROM movStock_cabezal c "
					+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"WHERE c.idempresa="+idEmpresa+idQuery +" GROUP by c.id,c.estado,c.destino,c.doc,c.fecha,c.observacion,'', " + 
							"origen,c.origenDoc,c.intentos,DocSolicitud,razon order by c.fecha desc limit 400";
			
			movs = _EncuentraConexionAPI2.darQueueMovsStock(query,idEmpresa);		
			cone=null;
			return movs;
		}
		catch (Exception e)
		{
			return movs;
		}
	}
	
	public MovStock MovStock_articulo(int idEmpresa, Long origendoc, int origen, List<DataIDDescripcion> articulos){
		@SuppressWarnings("unused") Connection cone;
		
		MovStock mov = null;
		String q_string = "";
		for(DataIDDescripcion d: articulos) {
			q_string += "'"+d.getDescripcion()+"',";
		}
		try {
			q_string = q_string.substring(0,q_string.length()-1);
			q_string = " and d.idarticulo in ("+q_string+") ";
		} catch (Exception e) {
			return mov;
		}
		try 
		{
			cone = _EncuentraConexionAPI2.getConnection();
			String query = "SELECT c.id,c.estado,c.destino,c.doc,sum(d.cantidad),c.fecha,c.observacion,'', "
					+"origen,c.origenDoc,c.intentos,DocSolicitud,razon,c.usuario,c.msjErp,c.entrega FROM movStock_cabezal c "
					+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"WHERE origenDoc = "+origendoc+" and origen ="+origen + q_string+" and c.idempresa="+idEmpresa+
					" GROUP by c.id,c.estado,c.destino,c.doc,c.fecha,c.observacion,'', " + 
							"origen,c.origenDoc,c.intentos,DocSolicitud,razon order by c.fecha desc limit 1";
			
			mov = _EncuentraConexionAPI2.darMovsStock(query,idEmpresa);		
			cone=null;
			return mov;
		}
		catch (Exception e)
		{
			return mov;
		}
	}
	
	public MovStock confirmacionTransferencia_articulo(int idEmpresa, Long origendoc, int origen, List<DataIDDescripcion> articulos){
		@SuppressWarnings("unused") Connection cone;
		
		MovStock mov = null;
		String q_string = "";
		for(DataIDDescripcion d: articulos) {
			q_string += "'"+d.getDescripcion()+"',";
		}
		try {
			q_string = q_string.substring(0,q_string.length()-1);
			q_string = " and d.idarticulo in ("+q_string+") ";
		} catch (Exception e) {
			return mov;
		}
		try 
		{
			cone = _EncuentraConexionAPI2.getConnection();
			String query = "SELECT c.id,c.estado,c.destino,c.doc,sum(d.cantidad),c.fecha,c.observacion,'', "
					+"origen,c.origenDoc,c.intentos,DocSolicitud,razon,c.usuario,c.msjErp,c.entrega FROM mov_confirm_transf_cabezal c "
					+"INNER JOIN mov_confirm_transf_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"WHERE origenDoc = "+origendoc+" and origen ="+origen + q_string+" and c.idempresa="+idEmpresa+
					" GROUP by c.id,c.estado,c.destino,c.doc,c.fecha,c.observacion,'', " + 
							"origen,c.origenDoc,c.intentos,DocSolicitud,razon order by c.fecha desc limit 1";
			
			mov = _EncuentraConexionAPI2.darConfirmTransf(query,idEmpresa);		
			cone=null;
			return mov;
		}
		catch (Exception e)
		{
			return mov;
		}
	}
	
	public List<MovStock> queuePendingMovsStock(int idEmpresa, int id){
		@SuppressWarnings("unused") Connection cone;
		
		List<MovStock> movs = null;
		try 
		{
			cone = _EncuentraConexionAPI2.getConnection();
			String idQuery = "";
			if(id!=0) {
				idQuery = " and c.id ="+id;
			}
			String query = "SELECT c.id,c.estado,c.destino,c.doc,sum(d.cantidad),c.fecha,c.observacion,'', "
					+"origen,c.origenDoc,c.intentos,DocSolicitud,razon,c.usuario,c.msjErp,c.entrega FROM movStock_cabezal c "
					+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"WHERE estado = 0 and origen != 0 and c.idempresa="+idEmpresa+idQuery +" GROUP by c.id,c.estado,c.destino,c.doc,c.fecha,c.observacion,'', " + 
							"origen,c.origenDoc,c.intentos,DocSolicitud,razon";
			
			movs = _EncuentraConexionAPI2.darQueueMovsStock(query,idEmpresa);		
			cone=null;
			return movs;
		}
		catch (Exception e)
		{
			return movs;
		}
	}
	
	public List<MovStock> obtenerMovsStock(int idEmpresa, Long origeneDoc){
		@SuppressWarnings("unused") Connection cone;
		
		List<MovStock> movs = null;
		try 
		{
			cone = _EncuentraConexionAPI2.getConnection();
			String idQuery = "";
			String query = "SELECT c.id,c.estado,c.destino,c.doc,sum(d.cantidad),c.fecha,c.observacion,'', "
					+"origen,c.origenDoc,c.intentos,DocSolicitud,razon,c.usuario,c.msjErp,c.entrega FROM movStock_cabezal c "
					+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"WHERE estado = 1 and c.idempresa="+idEmpresa+idQuery +" and c.origenDoc = "+origeneDoc+" " + 
							"GROUP by c.id,c.estado,c.destino,c.doc,c.fecha,c.observacion,'', " + 
							"origen,c.origenDoc,c.intentos,DocSolicitud,razon";
			
			movs = _EncuentraConexionAPI2.darQueueMovsStock(query,idEmpresa);		
			cone=null;
			return movs;
		}
		catch (Exception e)
		{
			return movs;
		}
	}
	
	public void RegistrarDocMovimientoStock(int ok, int idRegistro, int doc, String obs, int idEmpresa, int usu, int intentos) {
		Connection cone;
		try {
			String registro = "update movStock_cabezal set estado = "+ok+", doc = "+doc+", msjERP='"+obs+"',"
					+ " usuario = "+usu+", intentos = intentos+"+intentos
					+ " where id = "+idRegistro+" and idEmpresa="+idEmpresa;
			
			persistir(registro);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void RegistrarDocMovimientoStock(int ok, int idRegistro, int doc, String obs, int idEmpresa, int usu, int intentos, int docSolicitud) {
		Connection cone;
		try {
			String registro = "update movStock_cabezal set estado = "+ok+", doc = "+doc+", msjERP='"+obs+"',"
					+ " usuario = "+usu+", intentos = intentos+"+intentos+", docSolicitud = "+docSolicitud
					+ " where id = "+idRegistro+" and idEmpresa="+idEmpresa;
			
			persistir(registro);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//CONFIMRACION DE MOVIMIENTOS
	public int RegistrarConfirmacionTransferencia(int origen, int destino, int usu, List<DataIDDescripcion> arts, int idEmpresa,Long OrigenDoc, 
			int docSolicitud, int razon, int doc, boolean a_confirmar) 
	{
		Connection cone;
		int idRegistro = 0;
		int estado = a_confirmar?-1:0;
		try 
		{
			cone = _EncuentraConexionAPI2.getConnection();
			String registro = "insert into mov_confirm_trans"
					+ "f_cabezal(usuario,origen,destino,idEmpresa,origenDoc,docSolicitud,razon,doc,estado) "
					+ "values("+usu+","+origen+","+destino+","+idEmpresa+","+OrigenDoc+","+docSolicitud+","+razon+","+doc+","+estado+")";
			
			idRegistro = _EncuentraConexionAPI2.persistirDarUltimo(registro, "mov_confirm_transf_cabezal", "id",idEmpresa);
			cone = null;
			StringBuilder sb = new StringBuilder();
			for(DataIDDescripcion a:arts){
				sb.append("insert into mov_confirm_transf_detalle values ("+idRegistro+",'"+a.getDescripcion()+"',"+a.getId()+","+idEmpresa+");");
			}
			
			System.out.println(sb.toString());
			persistir(sb.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return idRegistro;
	}
	
	public List<MovStock> queueConfirmacionTransferencia(int idEmpresa){
		@SuppressWarnings("unused") Connection cone;
		List<MovStock> movs = null;
		try 
		{
			cone = _EncuentraConexionAPI2.getConnection();
			
			String query = "SELECT c.id,c.estado,c.destino,c.doc,sum(d.cantidad),c.fecha,c.observacion,'Encuentra', "
					+"c.origen,c.origenDoc,c.intentos,'Ecommerce',docSolicitud,razon,usuario,msjerp FROM mov_confirm_transf_cabezal c "
					+"INNER JOIN mov_confirm_transf_detalle d on c.id=d.id and d.idempresa=c.idempresa "
					+"WHERE c.idempresa="+idEmpresa+" and estado=0 GROUP BY c.id ORDER BY c.fecha desc";
			System.out.println(query);
			movs = _EncuentraConexionAPI2.darQueueConfirmacionTransferencia(query,idEmpresa);
			cone = null;
			return movs;
		}
		catch (Exception e)
		{
			return movs;
		}
	}
	
	public void RegistrarEstadoConfirmacionTransferencia(boolean grabo, int idRegistro, String obs, int idEmpresa, int usu, int intentos) {
		
		int ok = 0;
		try {
			
			if(grabo) {
				ok = 1;
			}
			String registro = "update mov_confirm_transf_cabezal set estado = "+ok+", msjERP='"+obs+"',"
					+ " usuario = "+usu+", intentos = intentos+"+intentos
					+ " where id = "+idRegistro+" and idEmpresa="+idEmpresa;
			
			persistir(registro);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//RECEPCIONES
		public int RegistrarRecepcion(GrabarRecepcion r) 
		{		
			@SuppressWarnings("unused") Connection cone;
			int idRegistro = 0;
			try 
			{			
				cone = _EncuentraConexionAPI2.getConnection();
				String registro = "insert into mov_recepcion_cabezal(destino,usuario,observacion,numeroDoc,nroProveedor,"
						+ "serieRemito,nroRemito,idRecepcion,tipoAfecta,idEmpresa) "
						+ "values("+r.getDestino()+","+r.getIdUsuario()+",'"+r.getObservacion()+"',"+r.getNumeroDoc()+","+r.getNroProveedor()+",'"+
						r.getSerieRemito()+"',"+r.getNroRemito()+","+r.getIdRecepcion()+",'"+r.getTipoAfecta()+"',"+r.getIdEmpresa()+")";
				
				idRegistro = _EncuentraConexionAPI2.persistirDarUltimo(registro, "mov_recepcion_cabezal", "id",r.getIdEmpresa());
				cone = null;
				StringBuilder sb = new StringBuilder();
				for(DataIDDescripcion a: r.getDetalle()){
					sb.append("insert into mov_recepcion_detalle values ("+idRegistro+",'"+a.getDescripcion()+"',"+a.getId()+","+r.getIdEmpresa()+");");
				}
				
				System.out.println(sb.toString());
				persistir(sb.toString());
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return idRegistro;
		}
		
		public List<GrabarRecepcion> queueRecepciones(int idEmpresa){
			@SuppressWarnings("unused") Connection cone;
			List<GrabarRecepcion> recepciones = null;
			try 
			{
				cone = _EncuentraConexionAPI2.getConnection();
				String query = "SELECT c.id,c.fecha,c.estado,c.destino, usuario, observacion, numeroDoc,"
						+ "nroProveedor, serieRemito, nroRemito, idRecepcion, tipoAfecta, idEmpresa, intentos, ultimoUpdate, msjERP"
						+" FROM mov_confirm_transf_cabezal c "
						+"INNER JOIN mov_confirm_transf_detalle d on c.id=d.id and d.idempresa=c.idempresa "
						+"WHERE c.idempresa="+idEmpresa+" and estado=0 GROUP BY c.id ORDER BY c.fecha desc";
				
				recepciones = _EncuentraConexionAPI2.darQueueRecepciones(query,idEmpresa);
				cone = null;
				return recepciones;
			}
			catch (Exception e)
			{
				return recepciones;
			}
		}
		
		public void RegistrarEstadoRecepcion(boolean grabo, int idRegistro, String obs, int idEmpresa, int usu, int intentos) {
			Connection cone;
			int ok = 0;
			try {
				if(grabo) {
					ok = 1;
				}
				String registro = "update mov_recepcion_cabezal set estado = "+ok+", msjERP='"+obs+"',"
						+ " usuario = "+usu+", intentos = intentos+"+intentos
						+ " where id = "+idRegistro+" and idEmpresa="+idEmpresa;
				
				persistir(registro);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	//ORDENES
	
	public OrdenVenta darOrdenVenta(Long idPedido) 
	{

		String consulta = "select 0, V.porcDescuento,'UYU' ,V.clicedula, V.clinombre, V.clidireccion, V.cliRuc, V.clitelefono, '', V.idVenta, V.uletiqueta, V.FP, V.ML, V.Comentario, "
				+ "importePago, p.idCanalML, p.stampTime, if(isnull(fp.idFormaVisual),'',fp.idFormaVisual)  from ecommerce_import_venta V "
				+ "where  idVenta = "+idPedido;
		OrdenVenta orden = null;
		try
		{		
			orden = _EncuentraConexionAPI2.encuentraPedidosOrdenVenta(consulta);			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return orden;
		
	}
	
	public OrdenVenta darOrdenVenta_(int idEmpresa, Long idPedido) 
	{
		
		String consulta = "";
		consulta = "select 0, V.porcDescuento,'UYU' ,V.clicedula, V.clinombre, V.clidireccion, V.cliRuc, V.clitelefono, '', V.idVenta, V.uletiqueta, V.FP, V.ML, V.Comentario, "
				+ "importePago, 0, CURRENT_TIMESTAMP(), '', V.cliRuc  from ecommerce_import_venta V "
				+ "where @qpedidos@ and idEmpresa="+idEmpresa;
		
		String qPedidos="";
		
		if(!idPedido.equals(0L))
		{
			qPedidos= " V.idVenta="+idPedido+" ";
		}
		
		
		consulta = consulta.replace("@qpedidos@", qPedidos);
		OrdenVenta ordenes = null;
		try
		{		
			ordenes = _EncuentraConexionAPI2.darOrden(consulta);			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return ordenes;
		
	}
	
	public List<OrdenVenta> darOrdensSinFacturar(int idEmpresa, Long idPedido) 
	{
		
		String consulta = "";
		consulta = "select 0, V.porcDescuento,'UYU' ,V.clicedula, V.clinombre, V.clidireccion, V.cliRuc, V.clitelefono, '', V.idVenta, V.uletiqueta, V.FP, V.ML, V.Comentario, "
				+ "importePago, 0, CURRENT_TIMESTAMP(), '', V.cliRuc, printer  from ecommerce_import_venta V "
				+ "where Sincronizada=0 @qpedidos@ and idEmpresa="+idEmpresa;
		
		String qPedidos="";
		
		if(!idPedido.equals(0L))
		{
			qPedidos= " AND V.idVenta="+idPedido+" ";
		}
		
		
		consulta = consulta.replace("@qpedidos@", qPedidos);
		List<OrdenVenta> ordenes = null;
		try
		{		
			ordenes = _EncuentraConexionAPI2.ListarOrdenesSF(consulta);			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return ordenes;
		
	}
	
	public void CheckInOrder(Long idOrden, int printer, int idEmpresa) {
		try {
			String checkIn = "update ecommerce_import_venta set sincronizada = 0, printer = "+printer+
					" where sincronizada = -1 and idVenta = "+idOrden+" and idEmpresa="+idEmpresa+";";
			
			if(idEmpresa == 6) {
				checkIn += "update mov_confirm_transf_cabezal set estado = 0 "+
						" where estado = -1 and origenDoc = "+idOrden+" and idEmpresa="+idEmpresa;
			}
			
			persistir(checkIn);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void RegistrarFactura(Long idOrden, int factura, int idEmpresa, String comentario) {
		try {
			int ok = 0;
			if(factura!=0) {
				ok = 1;
			}
			String registro = "update ecommerce_import_venta set sincronizada = "+ok+", nroFactura = "+factura+ ", msjERP='"+comentario+"'"+
					" where idVenta = "+idOrden+" and idEmpresa="+idEmpresa;
			
			persistir(registro);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<beans.encuentra.Cliente> darClientesSinGrabar(int idEmpresa) 
	{
		String q = "SELECT Nombre,Apellido,Direccion,Ciudad,Departamento,cpostal,telefono,mail,cedula,digito,DireccionNota, nroPuerta, apto "
				+ "FROM ecommerce_import_clientes WHERE sincronizado=0 AND IdEmpresa="+idEmpresa+"";
				
		List<beans.encuentra.Cliente> clientes = null;
		try
		{			
			clientes =  _EncuentraConexionAPI2.darClientes(q);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return clientes;
	}
	
	public beans.encuentra.Cliente darCliente(Long idpedido, int idEmpresa) 
	{
		String q = "SELECT Nombre,Apellido,Direccion,Ciudad,Departamento,cpostal,telefono,mail,cedula,digito,DireccionNota, nroPuerta, apto "
				+ "FROM ecommerce_import_clientes WHERE idpedido="+idpedido+" AND IdEmpresa="+idEmpresa+"";
				
		beans.encuentra.Cliente cliente = null;
		try
		{			
			cliente =  _EncuentraConexionAPI2.darCliente(q);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return cliente;
	}
	
	public void SincronizarCliente(String documento,  int idEmpresa) {
		try {
			String registro = "update ecommerce_import_clientes set sincronizado = 1 "+
					" where cedula = '"+documento+"' and idEmpresa="+idEmpresa;
			
			persistir(registro);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<Integer,pedidoFactura> pedidosSinFactura(Map<Integer,pedidoFactura> pedidos) 
	{
		String q = "SELECT idpedido,nroFactura FROM producteca_ecom_facturas WHERE pdf='' ";
				
		try
		{			
			pedidos =  _EncuentraConexionAPI2.darPedidosSinFactura(q, pedidos);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return pedidos;
	}
	
	public Map<Integer,pedidoFactura> facturasVisual(Map<Integer,pedidoFactura> pedidos) 
	{
		StringBuilder docs = new StringBuilder();
		String sDocs="";
		
		for(Integer key : pedidos.keySet()) {
			docs.append(key+",");			
		}
		
		sDocs = docs.substring(0,docs.length()-1);
		
		String q = "SELECT NumeroDoc,Archivo FROM DocArchivo WHERE iddeposito=1200 and idListaEmpresa=1 and idTipoDocumento='VCO' and NumeroDoc in ("+sDocs+")";
				
		try
		{			
			pedidos =  MSSQL_API.darFacturasVisual(q, pedidos);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return pedidos;
	}
	
	
	
	public void guardarFacturas(List<pedidoFactura> pedidos) 
	{
		
		StringBuilder sb = new StringBuilder();
		for(pedidoFactura p : pedidos) {
			sb.append("insert into producteca_ecom_facturas (idPedido,nroFactura,pdf) values "
					+ "("+p.getIdPedido()+","+p.getNroFactura()+",'"+p.getPdf()+"') on duplicate key update nroFactura = "+p.getNroFactura()+", pdf = '"+p.getPdf()+"';");
		}
				
		try
		{			
			persistir(sb.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean putColaEstadoMarketPlace (List<jsonEstadoMP> lista) {
		String insert = "";
		try {
			for(jsonEstadoMP e:lista) {
				insert += "insert ignore into ecommerce_cola_estados (idPedido, idMarketPlace, json, canal, idEmpresa) values "
						+ "("+e.getIdPedido()+", '"+e.getIdMarketPlace()+"', '"+e.getJson()+"',"+e.getCanal()+","+e.getIdEmpresa()+");";
			}
			persistir(insert);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static List<jsonEstadoMP> pendienteColaEstadoMarketPlace(int canal, int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
			String query = "select id, idPedido, json, canal, idEmpresa, idMarketPlace from ecommerce_cola_estados where ok = 0 AND canal="+canal+" and idEmpresa="+idEmpresa;
			return _EncuentraConexionAPI2.DarPendienteColaEstadoMarketPlace(query);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public static List<jsonEstadoMP> pendienteColaEstadoMarketPlace(int idEmpresa) {
		@SuppressWarnings("unused") Connection cone;
		try {
			String query = "select id, idPedido, json, canal, idEmpresa, idMarketPlace from ecommerce_cola_estados where ok = 0 and idEmpresa="+idEmpresa;
			return _EncuentraConexionAPI2.DarPendienteColaEstadoMarketPlace(query);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public static void updateColaEstadoMarketPlace(String id) {
		try {
			String update = "update ecommerce_cola_estados set ok=1 where idMarketPlace = '"+id+"' and ok=0";			
			persistir(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Hashtable<Integer, CanalMarketPlace> canalesMarketPlace(int idEmpresa, int idMarketPlace){
		
		Hashtable<Integer, CanalMarketPlace> canales = null;
		try {
			canales =  _EncuentraConexionAPI2.DarCanalesMarketPlace(idEmpresa, idMarketPlace);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return canales;
	}
	
public static List<DataIDDescripcion> Empresas(){
		
		List<DataIDDescripcion> empresas = null;
		String query = "select idempresa, descripcion from empresa";
		try {
			empresas =  _EncuentraConexionAPI2.darlistaDataIdDesc(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empresas;
	}

public List<DataIDDescripcion> darEtiquetasZPL(String fechaDesde){
	
	List<DataIDDescripcion> etiquetas = null;
	String query = 	"SELECT operationid, pdf " +
				"FROM producteca_ecom_pedidos " +
				"WHERE fec_ult_act > '"+fechaDesde+"' AND pdf != '' ";
	try {
		etiquetas =  _EncuentraConexionAPI2.darlistaLongDesc(query);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return etiquetas;
}


public void actualizarokenVivoCliente(int idEmpresa, String uso, String access_token) 
{
	persistir("INSERT INTO temp_tokens (idEmpresa,Uso,token) VALUES ("+idEmpresa+",'"+uso+"','"+access_token+"') ON DUPLICATE KEY UPDATE token = VALUES (token)");
	
}


public static List<DataIDDescripcion> darEtiquetasClientesShopify(){
	List<DataIDDescripcion> etiquetas = null;
	String query = "SELECT pep.OPERATIONID, pep.MAIL, pef.pdf \r\n" + 
			"FROM producteca_ecom_pedidos pep \r\n" + 
			"JOIN producteca_ecom_facturas pef ON pef.idPedido = pep.OPERATIONID\r\n" + 
			"WHERE pep.MARKETPLACE = 60 \r\n" + 
			"AND pef.comunicada = 0;";
	try {
		etiquetas =  _EncuentraConexionAPI2.darlistaEtiquetasShopifyMail(query);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return etiquetas;
}

public void mapeoArticulosfenicio(List<EncuentraPedidoArticulo> articulos, int idEmpresa) 
{
	String query = "";
	for(EncuentraPedidoArticulo a: articulos)
	{
		query += "insert into mapeo_articulos_fenicio (skuFenicio,idArticulo,idEmpresa) "
				+ "values ('"+a.getSKUFenicio()+"','"+a.getArticulo()+"',"+idEmpresa+") on duplicate key update idArticulo = idArticulo;";
	}
	persistir(query);
}

public static int RegistrarEnvioMailShopify(int idPedido) {
	String query = "UPDATE producteca_ecom_facturas \r\n" + 
			"SET comunicada=1 \r\n" + 
			"WHERE idPedido="+idPedido+";";
	int persistir = 0;
	try {
		persistir = _EncuentraConexionAPI2.persistir(query);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return persistir;
}


public void getMapeoArticulosfenicio(Lineas[] lineas, int idEmpresa){
	
	try {
		_EncuentraConexionAPI2.getMapeoArticulosfenicio(lineas, idEmpresa);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

public static List<CallBackPedido> getCallBackPedidoEncuentra(){
	List<CallBackPedido> callBacks = null;
	String query = "SELECT idLLamada, idPedido, idEstado, estampa, procesado, fechaProcesado, mensaje, idEmpresa, printer FROM callbackpedidoencuentra WHERE procesado = 0;";
	try {
		callBacks = _EncuentraConexionAPI2.darCallBackPedidoEncuentra(query);
	} catch(Exception e){
		e.printStackTrace();
	}
	return callBacks;
	
}

public static List<CallBackPedido> getCallBackPedidoEncuentra(int idEmpresa){
	List<CallBackPedido> callBacks = null;
	String query = "SELECT idLLamada, idPedido, idEstado, estampa, procesado, fechaProcesado, mensaje, idEmpresa, printer FROM callbackpedidoencuentra "
			+ "WHERE idEmpresa="+idEmpresa+" and procesado = 0 and estampa >= '20211001';";
	try {
		callBacks = _EncuentraConexionAPI2.darCallBackPedidoEncuentra(query);
	} catch(Exception e){
		e.printStackTrace();
	}
	return callBacks;
	
}

public static Hashtable<String, Double> darArtPrecio(String idsArticulo, int idEmpresa) 
{
	Hashtable<String, Double> retorno = null;
	String query = "SELECT idArticulo, ifnull(Precio,0.0) FROM art_precio WHERE idArticulo IN ("+idsArticulo+") AND idEmpresa="+idEmpresa+";";
	try 
	{
		retorno = _EncuentraConexionAPI2.darArtPrecio(query);
	} 
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return retorno;
}

public static Map<String, Double> darArtPrecio_visual(String inns, int idEmpresa) 
{
	Map<String, Double> retorno = null;
	try 
	{
		retorno = MSSQL_API.darArtPrecioVisual(inns, idEmpresa);
	} 
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return retorno;
}

public static double darArtPrecio_(String art, int idEmpresa) 
{
	double precio = 0.0;
	try 
	{
		if(idEmpresa == 4) {
			precio = darArtPrecio_visual(art,idEmpresa).get(art) != null ? darArtPrecio_visual(art,idEmpresa).get(art) : 0.0;
		}
		else {
			 precio = darArtPrecio(art,idEmpresa).get(art) != null ? darArtPrecio(art,idEmpresa).get(art) : 0.0; 
		}
	} 
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return precio;
}

public static void altaArtPrecio(List<integraciones.erp.ws_Tata.Articulo> articulos, int idEmpresa) 
{
	int contador = 0;
	try 
	{
		String query = "";
		for(integraciones.erp.ws_Tata.Articulo a : articulos) {
			String idArticulo = a.getEstadistico();
			/*if(a.getEstadistico().startsWith("0")) {
				idArticulo = idArticulo.substring(1,idArticulo.length());
			}*/
			
			query += "insert into art_precio (idarticulo, precio, idEmpresa) values ('"+idArticulo+"',"+a.getPrecioVenta()+","+idEmpresa+") on duplicate key update precio="+a.getPrecioVenta();
			contador++;
			if(contador % 1000 == 0) {
				persistir(query);
				query = "";
				contador = 0;
			}
			else {
				query += ";";
			}
		}
		
		if(!query.equals("")) {
			persistir(query.substring(0,query.length()-1));
		}
	} 
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

public static List<TicketCambio> darTicketCambioBAS(Long idPedido, int idEmpresa) 
{
	List<TicketCambio> retorno = new ArrayList<>();
	String query = "SELECT V.idVenta, CONCAT(V.idArticulo, ' ', V.Descripcion), V.CantRegalo FROM ecommerce_import_ventalinea V WHERE V.IdEmpresa="+idEmpresa+" AND V.CantRegalo>0 AND V.idVenta = "+idPedido+"";
	try 
	{
		retorno = _EncuentraConexionAPI2.darTicketCambioBAS(query);
	} 
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return retorno;
}

public Hashtable<Integer, Integer> solicitud_a_origenes(int idEmpresa, Long origendoc, List<DataIDDescripcion> articulos){
	@SuppressWarnings("unused") Connection cone;
	
	Hashtable<Integer, Integer> origenes = null;
	String q_string = "";
	for(DataIDDescripcion d: articulos) {
		q_string += "'"+d.getDescripcion()+"',";
	}
	try {
		q_string = q_string.substring(0,q_string.length()-1);
		q_string = " and d.idarticulo in ("+q_string+") ";
	} catch (Exception e) {
		return origenes;
	}
	try 
	{
		cone = _EncuentraConexionAPI2.getConnection();
		String query = "SELECT distinct origen FROM movStock_cabezal c "
				+"INNER JOIN movstock_detalle d on c.id=d.id and d.idempresa=c.idempresa "
				+"WHERE origenDoc = "+origendoc + q_string+" and c.idempresa="+idEmpresa;
		
		origenes = _EncuentraConexionAPI2.solicitud_a_origenes(query,idEmpresa);		
		cone=null;
		return origenes;
	}
	catch (Exception e)
	{
		return origenes;
	}
}

public static DataIDDescripcion getDate() 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = _EncuentraConexionAPI2.getConnection();
		return _EncuentraConexionAPI2.darDataIdDescripcion("SELECT 0,CURRENT_TIMESTAMP()");			
	}
	catch (Exception e)
	{
		return null;
	}
}

public static ParametrosVisual darParametrosVS(int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		cone = _EncuentraConexionAPI2.getConnection();
		return _EncuentraConexionAPI2.darParametrosVS("SELECT LargoCBase,LargoColor,LargoTalle,URLImagen FROM parametrosvisual WHERE idEmpresa = "+idEmpresa);			
	}
	catch (Exception e)
	{
		return null;
	}
}

public static List<Remito> DarRemitosForus(String depo, String depoO, boolean TR, int idEmpresa) 
{
	@SuppressWarnings("unused") Connection cone;
	try 
	{
		return MSSQL_API.DarRemitosForus(depo, depoO, TR, idEmpresa);		
	}
	catch (Exception e)
	{
		return new ArrayList<>();
	}
}

public static void RemitosForus(List<Remito> remitos, String idDeposito, int idEmpresa) {
	try {
		Call_WS_APIENCUENTRA wms = new Call_WS_APIENCUENTRA();
		LogicaAPI l = new LogicaAPI();
		String token = l.darToken(idEmpresa);
		Map<Integer, String> parametros = wms.darParametros(token, "5");
		boolean esEcommerce = false;
		try {
			esEcommerce = idDeposito.equals(parametros.get(5));
		} catch (Exception e) {}
		String distrIn = "";
		if(esEcommerce)
		{
			for (Remito r : remitos) 
			{
				for (String ea : r.getEntregasAfecta()) 
				{
					distrIn+=ea+ ",";
				}
				
			}
			
			try 
			{
				distrIn = distrIn.substring(0,distrIn.length()-1);
			}
			catch (Exception e) 
			{
				distrIn = distrIn.substring(0,distrIn.length());
			}
			
			Hashtable<Integer, List<DataDescDescripcion>> pedidosDist = wms.distribucion_forus_pedidos(distrIn, token);
			
			for (Remito r : remitos) 
			{
				if(r.getNumeroDoc()==14989) {
					System.out.println("caso");
				}
				Long idPedido = (long)0;
				try
				{
					List<RemitoLinea> lineasN = new ArrayList<>();
					for (String ea : r.getEntregasAfecta()) 
					{
						int eaP = Integer.parseInt(ea);
						List <DataDescDescripcion> artsPedido = pedidosDist.get(eaP);
						idPedido = Long.parseLong(artsPedido.get(0).getId());
						
						for (DataDescDescripcion a : artsPedido) 
						{
							for (RemitoLinea rl : r.getLineas()) 
							{
								if(rl.getIdArticulo().equals(a.getDescripcion()) && a.getFecha().equals(ea))
								{
									rl.setVenta(a.getId());
									rl.setEntrega(ea);
									System.out.println(rl.getIdArticulo());
									
									System.out.println(a.getFecha());
									System.out.println(a.getId());
									boolean agregar = true;
									for (RemitoLinea rn : lineasN) 
									{
										if(rn.getIdArticulo().equals(rl.getIdArticulo()) && rn.getVenta().equals(rl.getVenta()) && rn.getEntrega().equals(rl.getEntrega()))
										{
											agregar = false;
										}
												
									}
									if(agregar)
									{
										lineasN.add(rl);
									}
								}
							}
						}
					}
					
					if(lineasN.isEmpty()){
						idPedido = (long)0;
					}
					else{
						r.setLineas(lineasN);
					}
					
					
				}
				catch (Exception e) 
				{
					idPedido = (long)0;
				}
				r.setComentario(r.getComentario()+" WEB: "+idPedido);
				r.setIdPedidoWEB(idPedido.intValue());
				
				
			}
			
		}
		else //recibe en otro deposito
		{				
			for (Remito r : remitos) 
			{					
				try
				{
					List<RemitoLinea> lineasN = new ArrayList<>();
					
					for (RemitoLinea rl : r.getLineas()) 
					{
						
							boolean agregar = true;
							for (RemitoLinea rn : lineasN) 
							{
								if(rn.getIdArticulo().equals(rl.getIdArticulo()))
								{
									agregar = false;
								}
							}
							if(agregar)
							{
								lineasN.add(rl);
							}
						
					}
					
					r.setLineas(lineasN);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}

public int darUltimoIdOrdenVenta(int idEmpresa) {
	String query = "select max(idVenta) from ecommerce_import_venta eiv where eiv.IdEmpresa = "+idEmpresa;
	try {
		return _EncuentraConexionAPI2.UltimoId(query);
	}
	catch(Exception e) {
		return -1;
	}
}

public int darUltimoIdOrdenCompra(int idEmpresa) {
	String query = "select max(idVenta) from ecommerce_import_venta eiv where eiv.IdEmpresa = "+idEmpresa;
	try {
		return _EncuentraConexionAPI2.UltimoId(query);
	}
	catch(Exception e) {
		return -1;
	}
}

}