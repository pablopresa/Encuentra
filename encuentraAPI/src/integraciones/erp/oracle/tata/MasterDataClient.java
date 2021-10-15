package integraciones.erp.oracle.tata;

import com.google.gson.Gson;

import beans.api.OkHttpResponseHandler;
import integraciones.erp.oracle.tata.bean.MasterDataQuery;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MasterDataClient {

	
	public MasterDataClient() {
		
	}
	
	public String transform(String exp) {
		return exp.replace("class:", "clazz:");
	}
	
	public MasterDataClient execute(MasterDataQuery query) {
		
		Gson gsonn=new Gson();
		String jsonn = gsonn.toJson(query);
		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, jsonn);
				Request request = new Request.Builder()
				  .url("{{baseUrl}}/stocks")
				  .method("POST", body)
				  .addHeader("Authorization", "Bearer <token>")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					OkHttpResponseHandler responseHandler = new OkHttpResponseHandler() ;
					jsonn = transform(responseHandler.responseHandler(response));
					return gsonn.fromJson(jsonn, MasterDataClient.class);
				}
				catch(Exception ex) {
					return null;
				}
	}
	
	
}
