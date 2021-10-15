package beans.api;

import java.io.BufferedInputStream;

import okhttp3.Response;

public class OkHttpResponseHandler {

	public OkHttpResponseHandler() {
		
	}
	
	public String responseHandler(Response response) {
		try {
			int bytesRead = 0;
			BufferedInputStream bis = new BufferedInputStream(response.body().byteStream());
			byte[] contents = new byte[1024];
			String strFileContents=""; 
			while((bytesRead = bis.read(contents)) != -1) 
			{ 
				strFileContents += new String(contents, 0, bytesRead);              
			}
			System.out.println(strFileContents);
			response.close();
			
			System.out.println(strFileContents);
			return strFileContents;
		}
		catch(Exception ex) {
			return ex.getMessage();
		}
	}
}
