package beans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import logica.Logica;
import persistencia._EncuentraConexion;

public class Usuario {

	private String nick;
	private String pass;
	private Sucursal suc;
	private int numero,grupo;
	private int perfil;
	private String perfilDesc;
	private String nombre;
	private String apellido;
	private String mail;
	private boolean inventario;
	private String deposito;
	private int idEmpresa;
	private int idEquipo;
	private int equipo_trabajo;
	private List<String> seguridadUI;

	
	public String getPerfilDesc() {
		return perfilDesc;
	}

	public void setPerfilDesc(String perfilDesc) {
		this.perfilDesc = perfilDesc;
	}

	public int getNumero() {
		return numero;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Sucursal getSuc() {
		return suc;
	}

	public void setSuc(Sucursal suc) {
		this.suc = suc;
	}

	public Usuario(String nick, String pass, Sucursal suc, int numero,
			int perfil) {
		this.nick = nick;
		this.pass = pass;
		this.suc = suc;
		this.numero = numero;
		this.perfil = perfil;
	}

	public Usuario() {
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isInventario() {
		return inventario;
	}

	public void setInventario(boolean inventario) {
		this.inventario = inventario;
	}

	public String getDeposito() {
		return deposito;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	
	public void registrarEvento(int hilo, int unidades,int tipo, int sentido, boolean interno, String sesion, String comentario)
	{
		Logica lo = new Logica();
		
		
		lo.encuentraLogEvento(hilo,this.getNumero(),unidades, tipo,sentido,interno,sesion,comentario,Integer.parseInt(this.getDeposito()),this.getIdEmpresa());
		
	}
	
	public String query_registrarEvento(int hilo, int unidades,int tipo, int sentido, boolean interno, String sesion, String comentario)
	{
		Logica lo = new Logica();
		
		
		return lo.query_encuentraLogEvento(hilo,this.getNumero(),unidades, tipo,sentido,interno,sesion,comentario,Integer.parseInt(this.getDeposito()),this.getIdEmpresa());
		
	}
	
	
	public String query_registrarEventoMin(String sesion, String comentario)
	{
		Logica lo = new Logica();
		
		
		return lo.query_encuentraLogEvento(0,this.getNumero(),0, 101,0,true,sesion,comentario,Integer.parseInt(this.getDeposito()),this.getIdEmpresa());
		
	}
	
	public void registrarEventoMin(String sesion, String comentario)
	{
		Logica lo = new Logica();
		
		
		lo.encuentraLogEvento(0,this.getNumero(),0, 101,0,true,sesion,comentario,Integer.parseInt(this.getDeposito()),this.getIdEmpresa());
		
	}
	
	public String query_registrarEventoHilo(String sesion, String comentario, int hilo, int tipoTarea)
	{
		Logica lo = new Logica();
		
		
		return lo.query_encuentraLogEvento(hilo,this.getNumero(),0, tipoTarea,0,true,sesion,comentario,Integer.parseInt(this.getDeposito()),this.getIdEmpresa());
		
	}
	
	public void registrarEventoHilo(String sesion, String comentario, int hilo, int tipoTarea)
	{
		Logica lo = new Logica();
		
		
		lo.encuentraLogEvento(hilo,this.getNumero(),0, tipoTarea,0,true,sesion,comentario,Integer.parseInt(this.getDeposito()),this.getIdEmpresa());
		
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getEquipo_trabajo() {
		return equipo_trabajo;
	}

	public void setEquipo_trabajo(int equipo_trabajo) {
		this.equipo_trabajo = equipo_trabajo;
	}
	
	
	
	public List<String> getSeguridadUI() 
	{
		return seguridadUI;
	}

	

	public void setSeguridadUI(String grupos) 
	{
		try
		{
			_EncuentraConexion econ = new _EncuentraConexion();
			Connection cone;
			cone = econ.getConnection();
			String q = "SELECT idUI FROM seguridad_ui SU WHERE idGrupo IN ("+grupos+") AND Visible = 1 AND idEmpresa = "+this.idEmpresa;
			this.seguridadUI = econ.darSeguridadUI(q);
			
		}
		catch (Exception e) 
		{
			this.seguridadUI = new ArrayList<>();
			System.out.println("no se pudo asignar seguridad");
		}
	
	}
}
