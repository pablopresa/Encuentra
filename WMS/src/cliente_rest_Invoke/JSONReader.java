package cliente_rest_Invoke;

import java.util.ArrayList;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;

import eCommerce_jsonObjectsII.Compras;
import eCommerce_jsonObjectsII.Pedido;
import eCommerce_jsonObjectsII.RspEtiqueta;


import jsonObjects.*;
import main.process_ecommerce.mercadoLibreObjects.MLPedidos;
import main.process_ecommerce.mercadoLibreObjects.MLUitem;
import  main.process_ecommerce.mercadoLibreObjects.Pickup;
import main.process_ecommerce.mercadoLibreObjects.Shipping;
import main.process_ecommerce.mercadoLibreObjects.TokenData;
import main.process_ecommerce.mercadoLibreObjects.MLPagos;

public class JSONReader {

	
	public static TokenData readJsonMeliToken(String retorno) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			
			TokenData obj = mapper.readValue(retorno, TokenData.class);
			return obj;
			
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			System.out.println(retorno);
			return null;
		}
	}
	
	public static List<JSONProveedor> readJsonProveedor(String proveedor) 
	{
		List <JSONProveedor> proveedores = new ArrayList<>();
		try 
		{
			JSONParser parser = new JSONParser();
			JSONArray array=(JSONArray)parser.parse(proveedor);
			for(int i=0;i<array.size();i++)
			{
				JSONObject jo = (JSONObject) array.get(i);
				String joStr = jo.toString();
				ObjectMapper mapper = new ObjectMapper();
				JSONProveedor obj = mapper.readValue(joStr, JSONProveedor.class);
				proveedores.add(obj);
			}
		} 
		catch (Exception e) 
		{
			/***********si entra ac? es porque no era una colleccion lo parseo del string derecho******************/
			try
			{
				System.out.println("*******************************entre en el catch***********************************");
				ObjectMapper mapper = new ObjectMapper();
				JSONProveedor obj = mapper.readValue(proveedor, JSONProveedor.class);
				proveedores.add(obj);
			}
			catch (Exception e2)
			{
				//e2.printStackTrace();
				System.out.println(proveedor);
			}
		}
		return proveedores;

	}

	public static List<JSONCabezalOC> readJsonCabezalOC(String retorno) 
	{
		List <JSONCabezalOC> cabezales = new ArrayList<>();
		try 
		{
			JSONParser parser = new JSONParser();
			JSONArray array=(JSONArray)parser.parse(retorno);
			for(int i=0;i<array.size();i++)
			{
				JSONObject jo = (JSONObject) array.get(i);
				String joStr = jo.toString();
				ObjectMapper mapper = new ObjectMapper();
				JSONCabezalOC obj = mapper.readValue(joStr, JSONCabezalOC.class);
				cabezales.add(obj);
			}
		} 
		catch (Exception e) 
		{
			/***********si entra ac? es porque no era una colleccion lo parseo del string derecho******************/
			try
			{
				System.out.println("*******************************entre en el catch***********************************");
				ObjectMapper mapper = new ObjectMapper();
				JSONCabezalOC obj = mapper.readValue(retorno, JSONCabezalOC.class);
				cabezales.add(obj);
			}
			catch (Exception e2)
			{
				//e2.printStackTrace();
				System.out.println(retorno);
			}
		}
		return cabezales;
	}
	
	
	
	public static List<JSONDetalleOC> readJsonDetalleOC(String retorno) 
	{
		List <JSONDetalleOC> detalles = new ArrayList<>();
		try 
		{
			JSONParser parser = new JSONParser();
			JSONArray array=(JSONArray)parser.parse(retorno);
			for(int i=0;i<array.size();i++)
			{
				JSONObject jo = (JSONObject) array.get(i);
				String joStr = jo.toString();
				ObjectMapper mapper = new ObjectMapper();
				JSONDetalleOC obj = mapper.readValue(joStr, JSONDetalleOC.class);
				detalles.add(obj);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return detalles;
	}

	public static List<JSONArticulo> readJsonArticulo(String retorno) 
	{
		List <JSONArticulo> articulos = new ArrayList<>();
		try 
		{
			JSONParser parser = new JSONParser();
			JSONArray array=(JSONArray)parser.parse(retorno);
			for(int i=0;i<array.size();i++)
			{
				JSONObject jo = (JSONObject) array.get(i);
				String joStr = jo.toString();
				ObjectMapper mapper = new ObjectMapper();
				JSONArticulo obj = mapper.readValue(joStr, JSONArticulo.class);
				articulos.add(obj);
			}
		} 
		catch (Exception e) 
		{
			/***********si entra ac? es porque no era una colleccion lo parseo del string derecho******************/
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				JSONArticulo obj = mapper.readValue(retorno, JSONArticulo.class);
				articulos.add(obj);
			}
			catch (Exception e2)
			{
				//System.out.println(retorno);
				//e2.printStackTrace();
			}
		}
		return articulos;
	}

	public static Pedido readJsonPedidos(String retorno) 
	{
		Pedido pedidos = new Pedido();
		/***********si entra ac? es porque no era una colleccion lo parseo del string derecho******************/
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				pedidos = mapper.readValue(retorno, Pedido.class);
				
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
				pedidos.setCompras(new Compras[1]);
				pedidos.setMsg("");
				pedidos.setStatus("");
				pedidos.setTotal("");
			}
		
		return pedidos;
	}
	
	
	public static RspEtiqueta readJsonPedidoEti(String retorno) 
	{
		RspEtiqueta pedidos = new RspEtiqueta();
		/***********si entra ac? es porque no era una colleccion lo parseo del string derecho******************/
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				pedidos = mapper.readValue(retorno, RspEtiqueta.class);
				
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		
		return pedidos;
	}
	
	
	public static String generateJSONFactura (JSONFacturaRecepcion factura)
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(factura);
			System.out.println(jsonInString);
			return jsonInString;
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			return e2.getMessage();
		}
	}
	
	
	
	public static String generateJSONRececpcion (JSONRecepcionOC recepcion)
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(recepcion);
			System.out.println(jsonInString);
			return jsonInString;
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			return e2.getMessage();
		}
	}

	public static int getStock(String retorno) {
		int stock=0;
		try 
		{
			JSONParser parser = new JSONParser();
			JSONObject jo=(JSONObject)parser.parse(retorno);
			String joStr = jo.toString();
			ObjectMapper mapper = new ObjectMapper();
				JSONStock obj = mapper.readValue(joStr, JSONStock.class);
				stock=Integer.parseInt(obj.getCantidad());
			
		} 
		catch (Exception e) 
		{
			
				
				e.printStackTrace();
			
		}
		return stock;
		
	}
	

	
	
		
	
	public static Shipping readJsonShippingMeli(String retorno) 
	{
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			
			Shipping obj = mapper.readValue(retorno, Shipping.class);
			return obj;
			
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			System.out.println(retorno);
			return null;
		}
	
		

	}

		
	public static List<RespWSSAP> readJsonRespWSSAP(String retorno) 
	{
		
		List <RespWSSAP> resps = new ArrayList<>();
		try 
		{
			JSONParser parser = new JSONParser();
			JSONArray array=(JSONArray)parser.parse(retorno);
			for(int i=0;i<array.size();i++)
			{
				JSONObject jo = (JSONObject) array.get(i);
				
				Long code = (Long) jo.get("Code");
				JSONArray desc=(JSONArray) jo.get("Description");
				
				
				
				RespWSSAP obj = new RespWSSAP();
				obj.setCode(code+"");
				obj.setDescription(desc.get(0).toString());
				
				
				resps.add(obj);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return resps;
		
	}
	
	public static MLPedidos readJsonMeli(String retorno) 
	{
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			
			MLPedidos obj = mapper.readValue(retorno, MLPedidos.class);
			return obj;
			
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			System.out.println(retorno);
			return null;
		}
	
		

	}
	
	public static MLPagos readJsonPagoMeli(String retorno) 
	{
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			
			MLPagos obj = mapper.readValue(retorno, MLPagos.class);
			return obj;
			
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			System.out.println(retorno);
			return null;
		}
	
		

	}
	
	public static MLUitem readJsonMLUitem(String retorno) 
	{
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			
			MLUitem obj = mapper.readValue(retorno, MLUitem.class);
			return obj;
			
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			System.out.println(retorno);
			return null;
		}
	
		

	}
	
	public static Pickup readJsonPickup(String retorno) 
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			
			Pickup obj = mapper.readValue(retorno, Pickup.class);
			return obj;
			
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
			System.out.println(retorno);
			return null;
		}
	}
	

	
	
}
