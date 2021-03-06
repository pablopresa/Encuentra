package main.utilidades;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import beans.DepositoParametros;
import beans.Menu;
import beans.Usuario;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.Tarjeta;

public class CallWSRFID 
{

	public CallWSRFID() {}

	
	public String darEan(String cod)
	{
		
		try 
		{
		cod = cod.trim();
		PropertiesHelper ph = new PropertiesHelper("BAS");
		ph.loadProperties();
		String host = ph.getValue("ktrack");
		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\r\n    \"ItemId\": \""+cod+"\"\r\n}");
				Request requestOK = new Request.Builder()
				  .url(host)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
				Response responseOK = client.newCall(requestOK).execute();
				
				
				int bytesRead = 0;
		        BufferedInputStream bis = new BufferedInputStream(responseOK.body().byteStream());
		        byte[] contents = new byte[1024];
		        String strFileContents=""; 
		        while((bytesRead = bis.read(contents)) != -1) 
		        { 
		            strFileContents += new String(contents, 0, bytesRead);              
		        }
		        System.out.println(strFileContents);
		        responseOK.close();
		        
		        System.out.println(strFileContents);
		       
		        Gson gs = new Gson();
		        Respuesta r = gs.fromJson(strFileContents, Respuesta.class);
		        
		        return r.getEAN();
		
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return "";
	}
	//
}

class Respuesta
{
    private String EAN;

    public String getEAN ()
    {
        return EAN;
    }

    public void setEAN (String EAN)
    {
        this.EAN = EAN;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [EAN = "+EAN+"]";
    }
}
