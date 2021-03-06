package beans;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import beans.encuentra.DataPicking;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.LogicaBulto;

public class bulto implements Cloneable  
{
	private String idBulto;
	private String descripcion;
	private boolean cerrado;
	private int ancho;
	private int alto;
	private int prof;
	private Double peso;
	private boolean fragil;
	private String fecha;
	private int usuario;
	private String destino;
	private int usuarioClose;
	private String fechaClose;
	private int equipo_trabajo;
	private List<String> caracteristicas;
	private List<DataIDDescripcion> remitos;
	private Hashtable<String,bultoContenido> contenido;
	private List<bultoContenido> contenidoList;
	private int idEmpresa;
	private Logica logica;
	private boolean persistencia;
	private boolean remision_al_cerrar;
	private String posSort;
	private Long pedido;
	private int numerador;
	private int unidades;
	private String descDestino;
	
	
	public Double getPeso() {
		return peso;
	}


	
	
	
	
	
	
	
	
	
	

	public String getDescDestino() {
		return descDestino;
	}













	public void setDescDestino(String descDestino) {
		this.descDestino = descDestino;
	}













	public int getUnidades() 
	{
		int retorno = 0;
		
		try
		{
			for (bultoContenido  b : this.contenidoList) 
			{
				retorno +=b.getCantidad();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		
		
		return retorno;
	}















	public void setPeso(Double peso) {
		this.peso = peso;
	}



	public boolean isFragil() {
		return fragil;
	}



	public void setFragil(boolean fragil) {
		this.fragil = fragil;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public int getUsuario() {
		return usuario;
	}



	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}



	public List<String> getCaracteristicas() {
		return caracteristicas;
	}



	public void setCaracteristicas(List<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}



	public List<DataIDDescripcion> getRemitos() {
		return remitos;
	}



	public void setRemitos(List<DataIDDescripcion> remitos) {
		this.remitos = remitos;
	}



	public String getIdBulto() {
		return idBulto;
	}



	public void setIdBulto(String idBulto) {
		this.idBulto = idBulto;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public boolean getCerrado() {
		return cerrado;
	}



	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}



	public int getAncho() {
		return ancho;
	}



	public void setAncho(int ancho) {
		this.ancho = ancho;
	}



	public int getAlto() {
		return alto;
	}



	public void setAlto(int alto) {
		this.alto = alto;
	}



	public int getProf() {
		return prof;
	}



	public void setProf(int prof) {
		this.prof = prof;
	}

	public Hashtable<String,bultoContenido> getContenido() {
		return contenido;
	}



	public void setContenido(Hashtable<String,bultoContenido> contenido) {
		this.contenido = contenido;
	}
	
	
	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public int getUsuarioClose() {
		return usuarioClose;
	}



	public void setUsuarioClose(int usuarioClose) {
		this.usuarioClose = usuarioClose;
	}



	public String getFechaClose() {
		return fechaClose;
	}



	public void setFechaClose(String fechaClose) {
		this.fechaClose = fechaClose;
	}

	public void setContenidoList(List<bultoContenido> contenidoList) {
		this.contenidoList = contenidoList;
	}













	public bulto(){
		this.logica = new Logica();
	}
	
	public bulto(String id, String desc, boolean estado, int anch, int alt, int pr, Double pe,
				boolean frag, String fech, int usu, String dest, int idEmpresa){
		this.idBulto = id;
		this.descripcion = desc;
		this.cerrado = estado;
		this.ancho = anch;
		this.alto = alt;
		this.prof = pr;
		this.peso = pe;
		this.fragil = frag;
		this.fecha = fech;
		this.usuario = usu;
		this.destino = dest;
		this.contenido = new Hashtable<>();
		this.caracteristicas = new ArrayList<>();
		this.remitos = new ArrayList<>();
		this.logica = new Logica();
		this.idEmpresa = idEmpresa;
		this.posSort = "";
		this.pedido = new Long("0");
	}

	public void Crear_Bulto(int idEmpresa)
	{
		this.logica.CrearBulto(this,idEmpresa);
	}
	
	public void crearBultos(int idRecepcion, List<bulto> bultos,int idEmpresa)
	{
		this.logica.crearBultosMasivo(idRecepcion, bultos, idEmpresa);
	}
	
	public void Eliminar_Bulto(int idEmpresa)
	{
		this.logica.EliminarBulto(this,idEmpresa);
	}
	
	public void Agregar_A_Bulto(bultoContenido bc){
		if(this.getContenido().get(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion())==null){
			this.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(),bc);
		}
		else{
			int cant = this.getContenido().get(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion()).getCantidad();
			System.out.println(cant);
			bc.setCantidad(cant+1);
			this.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(),bc);
		}
		
		this.logica.AgregarABulto(bc, this.getIdBulto(),this.getIdEmpresa());
	}
	
	public bulto Agregar_A_Bulto_Reposicion(bultoContenido bc, DataPicking art){
		
		return this.logica.AgregarABultoReposicion(bc, this, art,this.getIdEmpresa());		
		
	}
	

	public bulto Agregar_A_Bulto_Reposicion_BultoCerrado(List<bultoContenido> contenido, DataPicking art){
		LogicaBulto logicaB = new LogicaBulto();
		return logicaB.AgregarABultoReposicion_BultoCerrado(contenido, this, art,this.getIdEmpresa());		
		
	}
	
	
	public void Agregar_A_Bulto_NO_persist(bultoContenido bc)
	{
		if(this.getContenido().get(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion())==null)
		{
			this.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(),bc);
		}
		else
		{
			int cant = this.getContenido().get(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion()).getCantidad();
			System.out.println(cant);
			bc.setCantidad(cant+1);
			this.getContenido().put(bc.getIdArticulo()+"-"+bc.getPicking()+"-"+bc.getRecepcion(),bc);
		}
		
		
	}
	
	
	public void guardarDetalleBulto()
	{
		List<bultoContenido> detalle = new ArrayList<>(this.getContenido().values());
		this.logica.GuardarDetalleBulto(detalle, this.getIdBulto(),this.getIdEmpresa());	
		
	}
	
	
	
	
	public void Cargar_Remito(String remito, int tipo, int unidades){
		this.getRemitos().add(new DataIDDescripcion(unidades,remito));
		this.logica.CargarRemito(this.getIdBulto(), remito, tipo, unidades,this.getIdEmpresa());
	}

	public void Cargar_Caracteristica(String caracteristica, String valor){
		this.getCaracteristicas().add(caracteristica+" "+valor);
		this.logica.Cargar_Caracteristica(this.getIdBulto(), caracteristica, valor,this.getIdEmpresa());
	}
	
	public boolean Cerrar_Bulto(int usu, boolean mover){
		boolean cerrado=false;
		try {
			this.setCerrado(true);
			this.setUsuarioClose(usu);
			cerrado = this.logica.Cerrar_Bulto(this, mover,this.getIdEmpresa());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cerrado;
	}



	public int getIdEmpresa() {
		return idEmpresa;
	}













	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}













	public int getEquipo_trabajo() {
		return equipo_trabajo;
	}



	public void setEquipo_trabajo(int equipo_trabajo) {
		this.equipo_trabajo = equipo_trabajo;
	}


	 @Override
	public bulto clone() throws CloneNotSupportedException
	 { 
		return (bulto) super.clone(); 
	 }
	 
	 public Hashtable<String,bultoContenido> clonarContenido() {
		 Hashtable<String,bultoContenido> nuevoContenido = new Hashtable<>();
		 
		 Enumeration<String> elements = this.contenido.keys();
		 
		 while(elements.hasMoreElements()) {
			 String key=elements.nextElement();
			 try {
				nuevoContenido.put(key, this.contenido.get(key).clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 return nuevoContenido;
	 }



	public List<bultoContenido> getContenidoList() 
	{
		
		if(this.contenidoList==null)
		{
		
			if(this.contenido!=null)
			{
				try
				{
					List<bultoContenido> retorno = new ArrayList<>(this.contenido.values());
					return retorno;
				}
				catch (Exception e) 
				{
					
				}
				
				return null;
			}
			else
			{
				return new ArrayList<>();
			}
		}
		else
		{
			return this.contenidoList;
		}
		
	}


	public List<bulto> copiar(bulto origen, int cantidadCopias)
	{
	
		List<bulto> retorno = new ArrayList<>();
		
		for (int i = 1; i <= cantidadCopias; i++) 
		{
			try
			{
				bulto copiado = origen.clone();
				copiado.setIdBulto(origen.getIdBulto()+""+i);
				
				copiado.Crear_Bulto(origen.getIdEmpresa());
				copiado.guardarDetalleBulto();
				copiado.Cerrar_Bulto(origen.getUsuario(),false);
				retorno.add(copiado);
			}
			catch (Exception e) 
			{
				
			}
			
		}
		
		
		
		retorno.add(origen);
		return retorno;
		
	}
	
	public boolean destruir()
	{
		return this.logica.persistir("delete from bulto where idEmpresa="+this.getIdEmpresa()+"  AND idBulto = '"+this.getIdBulto()+"'");
	}













	public boolean isPersistencia() {
		return persistencia;
	}













	public void setPersistencia(boolean persistencia) {
		this.persistencia = persistencia;
	}













	public boolean isRemision_al_cerrar() {
		return remision_al_cerrar;
	}













	public void setRemision_al_cerrar(boolean remision_al_cerrar) {
		this.remision_al_cerrar = remision_al_cerrar;
	}
	
	public int unidadesEnBulto() {
		int unidades = 0;
		
		if (this.getContenidoList()!=null) {
			for(bultoContenido bc : this.getContenidoList()) {
				unidades += bc.getCantidad();
			}
		}
		
		return unidades;
	}













	public String getPosSort() {
		return posSort;
	}













	public void setPosSort(String posSort) {
		this.posSort = posSort;
	}













	public Long getPedido() {
		return pedido;
	}













	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}













	public int getNumerador() {
		return numerador;
	}













	public void setNumerador(int numerador) {
		this.numerador = numerador;
	}
	
}
