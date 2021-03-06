
 
import helper.PropertiesHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
 
public class HttpClientFormAuthentication {
    public static void main(String[] agrs) {
        
        DefaultHttpClient client = new DefaultHttpClient();
 
        try {
        
            HttpGet securedResource = new HttpGet("https://www.stadium.com.uy/files.php/dac/68866/870-172943.pdf");            
            HttpResponse httpResponse = client.execute(securedResource);
            HttpEntity responseEntity = httpResponse.getEntity();
            String strResponse = EntityUtils.toString(responseEntity);
            
            String [] arreglo = strResponse.split("\r\n");
            
            String token = "";
            for (String s : arreglo) 
            {
            	//System.out.println(s);
            	if(s.contains("  var STOKEN = '"))
            	{
            		token = s.replace("var STOKEN","");
            		token = token.replace("=","");
            		token = token.replace("\t","");
            		token = token.replace(" ","");
            		token = token.replace(";","");
            		token = token.replace("'","");
            		System.out.println(token);
            		break;
            		
            	}
			}
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            //EntityUtils.consume(responseEntity);
 
            System.out.println("Http status code for Unauthenticated Request: " + statusCode);// Statue code should be 200
           // System.out.println("Response for Unauthenticated Request: n" + strResponse); // Should be login page
            //System.out.println("================================================================n");
 
            HttpPost authpost = new HttpPost("https://www.stadium.com.uy/admin.php/ingresar");
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("txtUsuario", "gmonzon@stadium.com.uy"));
            nameValuePairs.add(new BasicNameValuePair("txtPassword", "Nokia-1100"));
            nameValuePairs.add(new BasicNameValuePair("token", token));
            nameValuePairs.add(new BasicNameValuePair("_frm", "frmLogin"));
            
            authpost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            authpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
 
            httpResponse = client.execute(authpost);
            responseEntity = httpResponse.getEntity();
            strResponse = EntityUtils.toString(responseEntity);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            //EntityUtils.consume(responseEntity);
 
            System.out.println("Http status code for Authenticattion Request: " + statusCode);// Status code should be 302
            System.out.println("Response for Authenticattion Request: n" + strResponse); // Should be blank string
            System.out.println("================================================================n");
 
            httpResponse = client.execute(securedResource);
            responseEntity = httpResponse.getEntity();
            
            
            /***********************************descargo el PDF******************************************/
            PropertiesHelper pH=new PropertiesHelper("paths");
			pH.loadProperties();
			String path = pH.getValue("pdf");
			String filePath = path+"/"+10000000+".pdf";
			InputStream is = responseEntity.getContent();
			File file = new File(filePath);
            file.delete();
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            int inByte;
            while ((inByte = is.read()) != -1) 
            {
                fos.write(inByte);
            }
            is.close();
            fos.close();
            
           
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}