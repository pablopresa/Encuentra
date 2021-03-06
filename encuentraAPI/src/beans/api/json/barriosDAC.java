package beans.api.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class barriosDAC
{
    
	 private int K_Pais;
	 private String D_Pais;
	 private int K_Estado;
	 private String D_Estado;
	 private int K_Ciudad;
	 private String D_Ciudad;
	 private int K_Barrio;
	 private String D_Barrio;
	 private String Codigo_Postal;


	 // Getter Methods 

	 public float getK_Pais() {
	  return K_Pais;
	 }

	 public String getD_Pais() {
	  return D_Pais;
	 }

	 public float getK_Estado() {
	  return K_Estado;
	 }

	 public String getD_Estado() {
	  return D_Estado;
	 }

	 public float getK_Ciudad() {
	  return K_Ciudad;
	 }

	 public String getD_Ciudad() {
	  return D_Ciudad;
	 }

	 public float getK_Barrio() {
	  return K_Barrio;
	 }

	 public String getD_Barrio() {
	  return D_Barrio;
	 }

	 public String getCodigo_Postal() {
	  return Codigo_Postal;
	 }

	 // Setter Methods 

	 
	 
	 public void setD_Barrio(String D_Barrio) {
	  this.D_Barrio = D_Barrio;
	 }

	 public void setK_Pais(int k_Pais) {
		K_Pais = k_Pais;
	}

	public void setD_Pais(String d_Pais) {
		D_Pais = d_Pais;
	}

	public void setK_Estado(int k_Estado) {
		K_Estado = k_Estado;
	}

	public void setD_Estado(String d_Estado) {
		D_Estado = d_Estado;
	}

	public void setK_Ciudad(int k_Ciudad) {
		K_Ciudad = k_Ciudad;
	}

	public void setD_Ciudad(String d_Ciudad) {
		D_Ciudad = d_Ciudad;
	}

	public void setK_Barrio(int k_Barrio) {
		K_Barrio = k_Barrio;
	}

	public void setCodigo_Postal(String Codigo_Postal) {
	  this.Codigo_Postal = Codigo_Postal;
	 }
    
    public barriosDAC(){
    	
    }
    
}