package integraciones.erp.visualStore.objetos;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ClienteVS {
	private boolean recibirNoticiasWeb,recibirOfertasWeb,controlarMail; 
	private String fechaNacimiento; 
	private int idCliente,sexo,numero,numeroDig,idPais,codigoPostal,idEquipo,idTienda,idUsuario,idEmpresa,idDeposito;
	private String nombre,apellido,empresa,direccion,ciudad,departamento,telefono,mail,claveWeb,cIOrigen, calle, numeroPuerta,nroapto;
	
	
	@Override
	public String toString() {
		return "Clientes [numero=" + numero + ", numeroDig=" + numeroDig
				+ ", nombre=" + nombre + ", apellido=" + apellido
				+ ", direccion=" + direccion + ", ciudad=" + ciudad
				+ ", departamento=" + departamento + ", telefono=" + telefono
				+ ", mail=" + mail + "]";
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeroPuerta() {
		return numeroPuerta;
	}

	public void setNumeroPuerta(String numeroPuerta) {
		this.numeroPuerta = numeroPuerta;
	}

	public String getNroapto() {
		return nroapto;
	}

	public void setNroapto(String nroapto) {
		this.nroapto = nroapto;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public boolean isRecibirNoticiasWeb() {
		return recibirNoticiasWeb;
	}
	public void setRecibirNoticiasWeb(boolean recibirNoticiasWeb) {
		this.recibirNoticiasWeb = recibirNoticiasWeb;
	}
	public boolean isRecibirOfertasWeb() {
		return recibirOfertasWeb;
	}
	public void setRecibirOfertasWeb(boolean recibirOfertasWeb) {
		this.recibirOfertasWeb = recibirOfertasWeb;
	}
	public boolean isControlarMail() {
		return controlarMail;
	}
	public void setControlarMail(boolean controlarMail) {
		this.controlarMail = controlarMail;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNumeroDig() {
		return numeroDig;
	}
	public void setNumeroDig(int numeroDig) {
		this.numeroDig = numeroDig;
	}
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	public int getIdTienda() {
		return idTienda;
	}
	public void setIdTienda(int idTienda) {
		this.idTienda = idTienda;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getIdDeposito() {
		return idDeposito;
	}
	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getClaveWeb() {
		return claveWeb;
	}
	public void setClaveWeb(String claveWeb) {
		this.claveWeb = claveWeb;
	}
	public String getcIOrigen() {
		return cIOrigen;
	}
	public void setcIOrigen(String cIOrigen) {
		this.cIOrigen = cIOrigen;
	} 	
	
	public ClienteVS() {
	}
	
	public ClienteVS (String apellido,String nombre,String localidad,String email,String departamento,String telefono,
			String nroPuerta,String nroApto,String documentoTipo,String calle,String documentoNro, int idEmpresa)
	{
		
		this.recibirNoticiasWeb=true;
		this.recibirOfertasWeb=true;
		this.controlarMail=false;
		this.idCliente=0;
		this.fechaNacimiento="";
		this.sexo=0;
		this.idEquipo=1;
		this.idUsuario=0;
		this.idEmpresa=50000;
		this.empresa="";
		this.idTienda=99;
		this.idDeposito=71;
		this.idPais=1;
		this.codigoPostal=0;
		this.claveWeb="";
		this.cIOrigen="";
		
		this.nombre=nombre;
		this.apellido=apellido;
		this.direccion = calle+" "+nroPuerta+" "+nroApto;
		this.ciudad=localidad;
		this.departamento=departamento;
		this.telefono=telefono;
		this.mail=email;
		this.numeroPuerta = nroPuerta;
		this.calle = calle;
		this.nroapto = nroApto;
		
		int numCed;
		int digitoCed;
		String ced = "";
		String dig = "";
		
		/*if(!documentoTipo.equals("CI") || !documentoTipo.equals("ci"))
		{
			//cedula extrangera
			try
			{
				this.numero=Integer.parseInt(documentoNro);
			}
			catch (Exception e)
			{
				this.numero=0;
			}
			
			this.numeroDig=0;
			
			this.cIOrigen=documentoNro;
		}
		else*/ if(documentoNro.length()==8 && (documentoTipo.equals("CI") || documentoTipo.equals("ci")))
		{
			ced = documentoNro.substring(0,7);
			dig = documentoNro.substring(7,8);
				
		}
		else if(documentoNro.length()==7 && (documentoTipo.equals("CI") || documentoTipo.equals("ci")))
		{
			ced = documentoNro.substring(0,6);
			dig = documentoNro.substring(6,7);
		}
		else
		{
			//cedula extrangera
			try
			{
				this.numero=Integer.parseInt(documentoNro);
			}
			catch (Exception e)
			{
				this.numero=0;
			}
			
			this.numeroDig=0;
			
			this.cIOrigen=documentoNro;
		}
		
		try
		{
			numCed = Integer.parseInt(ced);
			digitoCed = Integer.parseInt(dig);
			if(numCed!=0)
			{
				this.numero=numCed;
				this.numeroDig=digitoCed;

			}
		}
		catch(Exception e)
		{
			
		}
				
	}
	
	public static String darXML(ClienteVS customer) 
	{
	    String xmlString = "";
	    try 
	    {
	        JAXBContext context = JAXBContext.newInstance(ClienteVS.class);
	        Marshaller m = context.createMarshaller();

	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

	        StringWriter sw = new StringWriter();
	        m.marshal(customer, sw);
	        xmlString = sw.toString();

	    } 
	    catch (JAXBException e) 
	    {
	        e.printStackTrace();
	    }

	    return xmlString;
	}
	
}
