package beans.api.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Cliente
{
	 private String apellido;

	    private String nombre;

	    private String localidad;

	    private String email;

	    private String departamento;
	    
	    private String ciudad;
	    
	    private String cp;

	    private String latitud;

	    private String telefono;

	    private String longitud;

	    private String nroPuerta;

	    private String nroApto;

	    private String documentoTipo;

	    private String calle;

	    private String documentoNro;
	    
	    private String rut;
	    
	    private String obs;
	    
	    

	    public Cliente(beans.encuentra.Cliente c) 
	    {
			this.nombre = c.getNombre();
			this.apellido = c.getApellido();
		    this.localidad = c.getLocalidad();
		    this.email = c.getEmail();
		    this.departamento = c.getDepartamento();
		    this.ciudad = c.getCiudad();
		    this.cp = c.getCp();
		    this.latitud = c.getLatitud();
		    this.telefono = c.getTelefono();
		    this.longitud = c.getLongitud();
		    this.nroPuerta = c.getNroPuerta();
		    this.nroApto = c.getNroApto();
		    this.documentoTipo = c.getDocumentoTipo();
		    this.calle = c.getCalle();
		    this.documentoNro = c.getDocumentoNro();
		    this.rut = c.getRut();
		    this.obs = c.getObs();
			
		}

		public String getRut() {
			return rut;
		}

		public void setRut(String rut) {
			this.rut = rut;
		}

		public String getApellido ()
	    {
	        return apellido;
	    }

	    public void setApellido (String apellido)
	    {
	        this.apellido = apellido;
	    }

	    public String getNombre ()
	    {
	        return nombre;
	    }

	    public void setNombre (String nombre)
	    {
	        this.nombre = nombre;
	    }

	    public String getLocalidad ()
	    {
	        return localidad;
	    }

	    public void setLocalidad (String localidad)
	    {
	        this.localidad = localidad;
	    }

	    public String getEmail ()
	    {
	        return email;
	    }

	    public void setEmail (String email)
	    {
	        this.email = email;
	    }

	    public String getDepartamento ()
	    {
	        return departamento;
	    }

	    public void setDepartamento (String departamento)
	    {
	        this.departamento = departamento;
	    }

	    public String getLatitud ()
	    {
	        return latitud;
	    }

	    public void setLatitud (String latitud)
	    {
	        this.latitud = latitud;
	    }

	    public String getTelefono ()
	    {
	        return telefono;
	    }

	    public void setTelefono (String telefono)
	    {
	        this.telefono = telefono;
	    }

	    public String getLongitud ()
	    {
	        return longitud;
	    }

	    public void setLongitud (String longitud)
	    {
	        this.longitud = longitud;
	    }

	    public String getNroPuerta ()
	    {
	        return nroPuerta;
	    }

	    public void setNroPuerta (String nroPuerta)
	    {
	        this.nroPuerta = nroPuerta;
	    }

	    public String getNroApto ()
	    {
	        return nroApto;
	    }

	    public void setNroApto (String nroApto)
	    {
	        this.nroApto = nroApto;
	    }

	    public String getDocumentoTipo ()
	    {
	        return documentoTipo;
	    }

	    public void setDocumentoTipo (String documentoTipo)
	    {
	        this.documentoTipo = documentoTipo;
	    }

	    public String getCalle ()
	    {
	        return calle;
	    }

	    public void setCalle (String calle)
	    {
	        this.calle = calle;
	    }

	    public String getDocumentoNro ()
	    {
	        return documentoNro;
	    }

	    public void setDocumentoNro (String documentoNro)
	    {
	        this.documentoNro = documentoNro;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [apellido = "+apellido+", nombre = "+nombre+", localidad = "+localidad+", email = "+email+", departamento = "+departamento+", latitud = "+latitud+", telefono = "+telefono+", longitud = "+longitud+", nroPuerta = "+nroPuerta+", nroApto = "+nroApto+", documentoTipo = "+documentoTipo+", calle = "+calle+", documentoNro = "+documentoNro+"]";
	    }
	    
	    

		public String getObs() {
			return obs;
		}

		public void setObs(String obs) {
			this.obs = obs;
		}

		public String getCiudad() {
			return ciudad;
		}

		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}

		public String getCp() {
			return cp;
		}

		public void setCp(String cp) {
			this.cp = cp;
		}
	    
	    
}
