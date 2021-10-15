package jsonObjects;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONWSARGNS 
{
	public static String parseador (JSONObject jsonObject, String tag)
	{
		JSONParser parser = new JSONParser();
		String retorno =  "";
		try {

			
			retorno = (String) jsonObject.get(tag);
			

			
			
			/*			// loop array
			JSONArray tags = (JSONArray) jsonObject.get("Tags");
			Iterator<String> iterator = tags.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			*/

		
		} catch (Exception e) 
		{
			//manejo de error
		}  
		
		return retorno;
		
	}
	
}


