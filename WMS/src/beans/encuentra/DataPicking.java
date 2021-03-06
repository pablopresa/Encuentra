package beans.encuentra;

import java.util.List;

import dataTypes.DataIDDescripcion;

public class DataPicking  implements Comparable
{
	private String articulo, descripcion,posSort, ojoActual,justificacion, direccion,localidad,departamento,obs, imagen;
	private DataIDDescripcion origen, destino, usuario;
	private int idPicking,sol,pick,remitida, idPacking, verificada, idPosLineaSAP, stockOrigen, stockOSAP,solicitud, remision_bulto, contenidoQty ,destinosQty,idTarea,empaque;
	private Long idPedido;
	private List<String> barras;
	private List<DataPicking> contenido;
	private boolean mayorista,consolidaPedidos;
	private boolean esBulto, estaCerrado; // Agrego dos banderas, una para saber si es bulto o art, otra para saber si esta cerrado o lo modificaron en el picking. .AG.
	
	
	
	
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getEmpaque() {
		return empaque;
	}
	public void setEmpaque(int empaque) {
		this.empaque = empaque;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public DataIDDescripcion getOrigen() {
		return origen;
	}
	public void setOrigen(DataIDDescripcion origen) {
		this.origen = origen;
	}
	public DataIDDescripcion getDestino() {
		return destino;
	}
	public void setDestino(DataIDDescripcion destino) {
		this.destino = destino;
	}
	public DataIDDescripcion getUsuario() {
		return usuario;
	}
	public void setUsuario(DataIDDescripcion usuario) {
		this.usuario = usuario;
	}
	public int getIdPicking() {
		return idPicking;
	}
	public void setIdPicking(int idPicking) {
		this.idPicking = idPicking;
	}
	public int getSol() {
		return sol;
	}
	public void setSol(int sol) {
		this.sol = sol;
	}
	public int getPick() {
		return pick;
	}
	public void setPick(int pick) {
		this.pick = pick;
	}
	public int getRemitida() {
		return remitida;
	}
	public void setRemitida(int remitida) {
		this.remitida = remitida;
	}
	
	
	
	
	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public int getStockOrigen() 
	{
		return this.stockOrigen;
		
	}
	public void setStockOrigen(int stockOrigen) {
		this.stockOrigen = stockOrigen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getVerificada() {
		return verificada;
	}
	public void setVerificada(int verificada) {
		this.verificada = verificada;
	}
	public List<String> getBarras() {
		return barras;
	}
	public void setBarras(List<String> barras) {
		this.barras = barras;
	}
	public int getIdPacking() {
		return idPacking;
	}
	public void setIdPacking(int idPacking) {
		this.idPacking = idPacking;
	}
	
	
	
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public boolean isConsolidaPedidos() {
		return consolidaPedidos;
	}
	public void setConsolidaPedidos(boolean consolidaPedidos) {
		this.consolidaPedidos = consolidaPedidos;
	}
	public boolean isEsBulto() {
		return esBulto;
	}
	public boolean isEstaCerrado() {
		return estaCerrado;
	}
	public void setEsBulto(boolean esBulto) {
		this.esBulto = esBulto;
	}
	public void setEstaCerrado(boolean estaCerrado) {
		this.estaCerrado = estaCerrado;
	}
	
	
	public DataPicking(String articulo, DataIDDescripcion origen,
			DataIDDescripcion destino, DataIDDescripcion usuario,
			int idPicking, int sol, int pick, int remitida) {
		this.articulo = articulo;
		this.origen = origen;
		this.destino = destino;
		this.usuario = usuario;
		this.idPicking = idPicking;
		this.sol = sol;
		this.pick = pick;
		this.remitida = remitida;
		this.verificada = 0;
		this.idPedido = 0L;
		this.idPosLineaSAP =0;
	}
	public DataPicking() 
	{
		this.verificada = 0;
	}
	public String getPosSort() {
		return posSort;
	}
	public void setPosSort(String posSort) {
		this.posSort = posSort;
	}
	public boolean isMayorista() {
		return mayorista;
	}
	public void setMayorista(boolean mayorista) {
		this.mayorista = mayorista;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdPosLineaSAP() {
		return idPosLineaSAP;
	}
	public void setIdPosLineaSAP(int idPosLineaSAP) {
		this.idPosLineaSAP = idPosLineaSAP;
	}
	public int getStockOSAP() {
		return stockOSAP;
	}
	public void setStockOSAP(int stockOSAP) {
		this.stockOSAP = stockOSAP;
	}
	public int getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(int solicitud) {
		this.solicitud = solicitud;
	}
	public int getRemision_bulto() {
		return remision_bulto;
	}
	public void setRemision_bulto(int remision_bulto) {
		this.remision_bulto = remision_bulto;
	}
	public List<DataPicking> getContenido() {
		return contenido;
	}
	public void setContenido(List<DataPicking> contenido) {
		this.contenido = contenido;
	}
	
	public DataPicking Clonar() {
		DataPicking d = new DataPicking();
		try {
			d.setArticulo(this.articulo);
			d.setBarras(this.barras);
			//d.setContenido(this.contenido);
			d.setDescripcion(this.descripcion);
			d.setDestino(this.destino);
			d.setEsBulto(this.esBulto);
			d.setEstaCerrado(this.estaCerrado);
			d.setIdPacking(this.idPacking);
			d.setIdPedido(this.idPedido);
			d.setIdPicking(this.idPicking);
			d.setIdPosLineaSAP(this.idPosLineaSAP);
			d.setMayorista(this.mayorista);
			d.setOrigen(this.origen);
			d.setPick(this.pick);
			d.setPosSort(this.posSort);
			d.setRemision_bulto(this.remision_bulto);
			d.setRemitida(this.remitida);
			d.setSol(this.sol);
			d.setSolicitud(this.solicitud);
			d.setStockOrigen(this.stockOrigen);
			d.setStockOSAP(this.stockOSAP);
			d.setUsuario(this.usuario);
			d.setVerificada(this.verificada);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	public int getContenidoQty() {
		return contenidoQty;
	}
	public void setContenidoQty(int contenidoQty) {
		this.contenidoQty = contenidoQty;
	}
	public String getOjoActual() {
		return ojoActual;
	}
	public void setOjoActual(String ojoActual) {
		this.ojoActual = ojoActual;
	}
	public int getDestinosQty() {
		return destinosQty;
	}
	public void setDestinosQty(int destinosQty) {
		this.destinosQty = destinosQty;
	}
	
	@Override
	public int compareTo(Object o) 
	{
		DataPicking dl = (DataPicking)o;        

	        if(this.idPedido==dl.getIdPedido()) 
	        {
	        	return 0;
	        	
	        } 
	        else if (this.idPedido<dl.getIdPedido())
	        { 
	            return -1; 
	        }
	        else
	        {
	        	return 1;
	        }

		
	}
	
	
	 public  DataPicking[] addX(DataPicking arr[], DataPicking n)
	    {
		 	System.out.println("");
	        int leng = arr.length;
	  
	        // create a new array of size n+1
	        
	        DataPicking newarr[] = new DataPicking[leng + 1];
	        if(leng>0)
	        {
		        int i = 0;
		        for (i = 0; i < leng; i++)
		        {
		        	newarr[i] = arr[i];
		        }
		        newarr[i+1] = n;
	        }
	        else
	        {
	        	newarr[0] = n;
	        }
	  
	        
	  
	        return newarr;
	    }
	

}
