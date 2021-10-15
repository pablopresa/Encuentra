package beans.api.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class JSONdataDAC
{
    
	private String RUT,Nombre,Codigo,Pegote;

    private String K_Usuario;
    private String K_Guia,K_Oficina_Destino,Codigo_Rastreo;
    private String ID_Session;

    private String K_Cliente;

    private String B_Contrato;

    private String D_Cliente;
    
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

	 public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getPegote() {
		return Pegote;
	}

	public void setPegote(String pegote) {
		Pegote = pegote;
	}

	public int getK_Pais() {
	  return K_Pais;
	 }

	 public String getD_Pais() {
	  return D_Pais;
	 }

	 public int getK_Estado() {
	  return K_Estado;
	 }

	 public String getD_Estado() {
	  return D_Estado;
	 }

	 public int getK_Ciudad() {
	  return K_Ciudad;
	 }

	 public String getD_Ciudad() {
	  return D_Ciudad;
	 }

	 public int getK_Barrio() {
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
    
    
    
    

    public String getRUT ()
    {
        return RUT;
    }

    public void setRUT (String RUT)
    {
        this.RUT = RUT;
    }

    public String getK_Usuario ()
    {
        return K_Usuario;
    }

    public void setK_Usuario (String K_Usuario)
    {
        this.K_Usuario = K_Usuario;
    }

    public String getID_Session ()
    {
        return ID_Session;
    }

    public void setID_Session (String ID_Session)
    {
        this.ID_Session = ID_Session;
    }

    public String getK_Cliente ()
    {
        return K_Cliente;
    }

    public void setK_Cliente (String K_Cliente)
    {
        this.K_Cliente = K_Cliente;
    }

    public String getB_Contrato ()
    {
        return B_Contrato;
    }

    public void setB_Contrato (String B_Contrato)
    {
        this.B_Contrato = B_Contrato;
    }

    public String getD_Cliente ()
    {
        return D_Cliente;
    }

    public void setD_Cliente (String D_Cliente)
    {
        this.D_Cliente = D_Cliente;
    }
    
    public JSONdataDAC(){
    	
    }

	public String getK_Guia() {
		return K_Guia;
	}

	public void setK_Guia(String k_Guia) {
		K_Guia = k_Guia;
	}

	public String getK_Oficina_Destino() {
		return K_Oficina_Destino;
	}

	public void setK_Oficina_Destino(String k_Oficina_Destino) {
		K_Oficina_Destino = k_Oficina_Destino;
	}

	public String getCodigo_Rastreo() {
		return Codigo_Rastreo;
	}

	public void setCodigo_Rastreo(String codigo_Rastreo) {
		Codigo_Rastreo = codigo_Rastreo;
	}
    
}