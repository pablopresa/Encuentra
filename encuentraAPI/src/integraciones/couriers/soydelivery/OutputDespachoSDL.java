package integraciones.couriers.soydelivery;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputDespachoSDL {
	    
	    public int Error_code;
	   
	    public String Error_desc;
	   
	    public int Pedido_id;
	    
	    public String Pedido_external_id_out;

		
	    
	    public int getError_code() {
			return Error_code;
		}



		public void setError_code(int error_code) {
			Error_code = error_code;
		}



		public String getError_desc() {
			return Error_desc;
		}



		public void setError_desc(String error_desc) {
			Error_desc = error_desc;
		}



		public int getPedido_id() {
			return Pedido_id;
		}



		public void setPedido_id(int pedido_id) {
			Pedido_id = pedido_id;
		}



		public String getPedido_external_id_out() {
			return Pedido_external_id_out;
		}



		public void setPedido_external_id_out(String pedido_external_id_out) {
			Pedido_external_id_out = pedido_external_id_out;
		}



		public OutputDespachoSDL() {}
}
