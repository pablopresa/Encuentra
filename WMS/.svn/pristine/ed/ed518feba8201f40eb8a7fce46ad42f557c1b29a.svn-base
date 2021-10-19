package logica;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Escritor {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		
		/*
		 * {
			  "cols": [
			          {"id":"","label":"Casa","pattern":"","type":"number"},
				  {"id":"","label":"Unidades","pattern":"","type":"number"},
				  {"id":"","label":"Venta","pattern":"","type":"number"},
				  {"id":"","label":"Variacion","pattern":"","type":"number"},
			      ],
			  "rows": [
			        {"c":[{"v":"1","f":null},{"v":100,"f":null},{"v":100.000,"f":null},{"v":10.000,"f":null}]},
				{"c":[{"v":"2","f":null},{"v":100,"f":null},{"v":100.000,"f":null},{"v":10.000,"f":null}]}
			        
			      ]
			}
		 */
	
		 JSONObject obj = new JSONObject();
		 
		
		 
		 
	       
	 
	        
	       
	        
	       
	        
	        
	        
	        JSONArray cabezal = new JSONArray();
	       
	        
	        
	        JSONObject objinHCasa = new JSONObject();
	        objinHCasa.put("id", "");
	        objinHCasa.put("label", "Casa");
	        objinHCasa.put("pattern", "");
	        objinHCasa.put("type", "number");
	        cabezal.add(objinHCasa);
	        
	        JSONObject objinHUnidades = new JSONObject();
	        objinHUnidades.put("id", "");
	        objinHUnidades.put("label", "Unidades");
	        objinHUnidades.put("pattern", "");
	        objinHUnidades.put("type", "number");
	        cabezal.add(objinHUnidades);
	        
	        JSONObject objinHVenta = new JSONObject();
	        objinHVenta.put("id", "");
	        objinHVenta.put("label", "Venta");
	        objinHVenta.put("pattern", "");
	        objinHVenta.put("type", "number");
	        cabezal.add(objinHVenta);
	        
	        JSONObject objinHVaria = new JSONObject();
	        objinHVaria.put("id", "");
	        objinHVaria.put("label", "Variacion");
	        objinHVaria.put("pattern", "");
	        objinHVaria.put("type", "number");
	        cabezal.add(objinHVaria);
	        
	        
	        
	        JSONObject celdas = new JSONObject();
	        JSONArray celda = new JSONArray();
	        
	        JSONObject objinCas = new JSONObject();
	        objinCas.put("v", 1);
	        celda.add(objinCas);
	        
	        JSONObject objinUni = new JSONObject();
	        objinUni.put("v", 125);
	        celda.add(objinUni);
	        
	        JSONObject objinVen = new JSONObject();
	        objinVen.put("v", 100.000);
	        celda.add(objinVen);
	        
	        JSONObject objinVar = new JSONObject();
	        objinVar.put("v", 10.000);
	        celda.add(objinVar);
	        
	        
	        celdas.put("c",celda);
	        
	        JSONArray celdasTodas = new JSONArray();
	        celdasTodas.add(celdas);
	        
	        
	        
	        obj.put("cols", cabezal);
	        obj.put("rows", celdasTodas);
	 
	        try {
	 
	               FileWriter file = new FileWriter("c:\\test.json");
	               file.write(obj.toJSONString());
	               file.flush();
	               file.close();
	 
	        } catch (IOException e) {
	               e.printStackTrace();
	        }
	 
	        System.out.print(obj);

	}

}
