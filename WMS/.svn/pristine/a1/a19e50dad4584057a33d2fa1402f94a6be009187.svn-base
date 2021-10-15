package cliente_rest_Invoke;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {

    public static void main(String args[]) throws IOException {
        
        String stringUrl = "curl -u ab3616a9bf32f218cad693da699ee2db352b5d1a: \\ https://api.printnode.com/whoami";
        URL url = new URL(stringUrl);
        URLConnection uc = url.openConnection();

        uc.setRequestProperty("X-Requested-With", "Curl");

 
        InputStream inputStream =  uc.getInputStream();
        
        try 
        {
          int bytesRead = 0;
          BufferedInputStream bis = new BufferedInputStream(inputStream);
          byte[] contents = new byte[1024];
          String strFileContents=""; 
          while((bytesRead = bis.read(contents)) != -1) 
          { 
              strFileContents += new String(contents, 0, bytesRead);              
          }

          
          
          System.out.println(strFileContents);
          
          
          
        }
        catch (RuntimeException runtimeException) 
        {
          // In case of an unexpected exception you may want to abort
          // the HTTP request in order to shut down the underlying
          // connection immediately.
          
          runtimeException.printStackTrace();
        }
        // read this input

    }
}