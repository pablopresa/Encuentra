package integraciones.marketplaces.fenicio;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comprador
{
    private String codigo;

    private String apellido;

    private String genero;

    private Documento documento;

    private String extras;

    private String id;

    private String telefono;

    private String nombre;

    private String email;

    public String getCodigo ()
    {
        return codigo;
    }

    public void setCodigo (String codigo)
    {
        this.codigo = codigo;
    }

    public String getApellido ()
    {
        return apellido;
    }

    public void setApellido (String apellido)
    {
        this.apellido = apellido;
    }

    public String getGenero ()
    {
        return genero;
    }

    public void setGenero (String genero)
    {
        this.genero = genero;
    }

    public Documento getDocumento ()
    {
        return documento;
    }

    public void setDocumento (Documento documento)
    {
        this.documento = documento;
    }

    public String getExtras ()
    {
        return extras;
    }

    public void setExtras (String extras)
    {
        this.extras = extras;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTelefono ()
    {
        return telefono;
    }

    public void setTelefono (String telefono)
    {
        this.telefono = telefono;
    }

    public String getNombre ()
    {
        return nombre;
    }

    public void setNombre (String nombre)
    {
        this.nombre = nombre;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [codigo = "+codigo+", apellido = "+apellido+", genero = "+genero+", documento = "+documento+", extras = "+extras+", id = "+id+", telefono = "+telefono+", nombre = "+nombre+", email = "+email+"]";
    }
}