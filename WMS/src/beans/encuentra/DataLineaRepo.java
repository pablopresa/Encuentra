package beans.encuentra;

import java.util.List;

public class DataLineaRepo implements Comparable, Cloneable 
{
	private String idArticulo;
	private int recorrido;
	private String cubi;
	private int estnteria;
	private int estnte;
	private int modulo;
	private String descripcion;
	private int solicitada;
	private int entregada;
	private int remitida;
	private String descDeposito;
	private int idDepDestino;
	private int preparada;
	private int idDepOrigen;
	private int sotck;
	private int areaArt;
	private int sectorEstanteria;
	private String descEstanteria;
	private Long pedido;
	private String pedidoSTR;
	private int documento;
	private String justificacion;
	private int autoVerificacion;
	private boolean isBulto;
	private List<DataLineaRepo> contenido;
	private boolean isBultoCerrado;
	private int qtyBulto;
	private int qtyPickBulto;
	private int caja;
	private boolean consolidaPedidos;
	private int picked;
	private String posClasif;
	private String imagen;
	private int packing;
	private int qPedido;
	private int cantDeVenta;
	
	
	
	
	public int getCantDeVenta() {
		return cantDeVenta;
	}
	public void setCantDeVenta(int cantDeVenta) {
		this.cantDeVenta = cantDeVenta;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getPacking() {
		return packing;
	}
	public void setPacking(int packing) {
		this.packing = packing;
	}
	public int getqPedido() {
		return qPedido;
	}
	public void setqPedido(int qPedido) {
		this.qPedido = qPedido;
	}
	public int getCaja() {
		return caja;
	}
	public void setCaja(int caja) {
		this.caja = caja;
	}
	public boolean isConsolidaPedidos() {
		return consolidaPedidos;
	}
	public void setConsolidaPedidos(boolean consolidaPedidos) {
		this.consolidaPedidos = consolidaPedidos;
	}
	public int getQtyBulto() {
		return qtyBulto;
	}
	public void setQtyBulto(int qtyBulto) {
		this.qtyBulto = qtyBulto;
	}
	public int getQtyPickBulto() {
		return qtyPickBulto;
	}
	public void setQtyPickBulto(int qtyPickBulto) {
		this.qtyPickBulto = qtyPickBulto;
	}
	public int getAreaArt() {
		return areaArt;
	}
	public void setAreaArt(int areaArt) {
		this.areaArt = areaArt;
	}
	public int getSectorEstanteria() {
		return sectorEstanteria;
	}
	public void setSectorEstanteria(int sectorEstanteria) {
		this.sectorEstanteria = sectorEstanteria;
	}
	public int getEstnteria() {
		return estnteria;
	}
	public void setEstnteria(int estnteria) {
		this.estnteria = estnteria;
	}
	public int getEstnte() {
		return estnte;
	}
	public void setEstnte(int estnte) {
		this.estnte = estnte;
	}
	public int getModulo() {
		return modulo;
	}
	public void setModulo(int modulo) {
		this.modulo = modulo;
	}
	public int getRecorrido() {
		return recorrido;
	}
	public void setRecorrido(int recorrido) {
		this.recorrido = recorrido;
	}
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public String getCubi() {
		return cubi;
	}
	public void setCubi(String cubi) {
		this.cubi = cubi;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getSolicitada() {
		return solicitada;
	}
	public void setSolicitada(int solicitada) {
		this.solicitada = solicitada;
	}
	public int getEntregada() {
		return entregada;
	}
	public void setEntregada(int entregada) {
		this.entregada = entregada;
	}
	public String getDescDeposito() {
		return descDeposito;
	}
	public void setDescDeposito(String descDeposito) {
		this.descDeposito = descDeposito;
	}
	public int getIdDepDestino() {
		return idDepDestino;
	}
	public void setIdDepDestino(int idDepDestino) {
		this.idDepDestino = idDepDestino;
	}
	public int getPreparada() {
		return preparada;
	}
	public void setPreparada(int preparada) {
		this.preparada = preparada;
	}
	public int getIdDepOrigen() {
		return idDepOrigen;
	}
	public void setIdDepOrigen(int idDepOrigen) {
		this.idDepOrigen = idDepOrigen;
	}
	public int getSotck() {
		return sotck;
	}
	public void setSotck(int sotck) {
		this.sotck = sotck;
	}
	public boolean isBulto() {
		return isBulto;
	}
	public List<DataLineaRepo> getContenido() {
		return contenido;
	}
	public void setBulto(boolean isBulto) {
		this.isBulto = isBulto;
	}
	public void setContenido(List<DataLineaRepo> contenido) {
		this.contenido = contenido;
	}
	public DataLineaRepo() 
	{
	}
	
	
	public String getPedidoSTR() {
		return pedidoSTR;
	}
	public void setPedidoSTR(String pedidoSTR) {
		this.pedidoSTR = pedidoSTR;
	}
	@Override
	public int compareTo(Object o) 
	{
		  DataLineaRepo dl = (DataLineaRepo)o;        

	        if(this.recorrido==dl.getRecorrido()) 
	        {
	        	if(this.getCubi()==dl.getCubi())
	        	{
	        		if(this.idArticulo.compareToIgnoreCase(dl.getIdArticulo()) == 0) 
		            { 
		                return 0;
		            } 
		            else if(this.idArticulo.compareToIgnoreCase(dl.getIdArticulo()) > 0)
		            { 
		            	
		                return 1; 
		            }
		            else
		            {
		            	return -1;
		            }

	        	}
	        	else if(this.cubi.compareToIgnoreCase(dl.getCubi()) == 0)
	        	{
	        		return 0;
	        	}
	        	else if(this.cubi.compareToIgnoreCase(dl.getCubi()) > 0)
	        	{
	        		return 1;
	        	}
	        	else
	        	{
	        		return -1;
	        	}
	        } 
	        else if (this.recorrido<dl.getRecorrido())
	        { 
	            return -1; 
	        }
	        else
	        {
	        	return 1;
	        }

		
	}
	
	
	
	@Override
	public DataLineaRepo clone() {
		DataLineaRepo clone = null;
        try
        {
            clone = (DataLineaRepo) super.clone();
                        
            
           
        }
        catch(CloneNotSupportedException e)
        {
            throw new RuntimeException(e); // won't happen
        }
       
        return clone;
       
    }
	public String getDescEstanteria() {
		return descEstanteria;
	}
	public void setDescEstanteria(String descEstanteria) {
		this.descEstanteria = descEstanteria;
	}
	public Long getPedido() {
		return pedido;
	}
	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public int getAutoVerificacion() {
		return autoVerificacion;
	}
	public void setAutoVerificacion(int autoVerificacion) {
		this.autoVerificacion = autoVerificacion;
	}
	public boolean isBultoCerrado() {
		return isBultoCerrado;
	}
	public void setBultoCerrado(boolean isBultoCerrado) {
		this.isBultoCerrado = isBultoCerrado;
	}

	public int getRemitida() {
		return remitida;
	}
	public void setRemitida(int remitida) {
		this.remitida = remitida;
	}
	public int getPicked() {
		return picked;
	}
	public void setPicked(int picked) {
		this.picked = picked;
	}
	public DataLineaRepo Clonar() {
		DataLineaRepo clon = new DataLineaRepo();
		clon.setIdArticulo(this.idArticulo);
		clon.setRecorrido(this.recorrido);
		clon.setCubi(this.cubi);
		clon.setEstnteria(this.estnteria);
		clon.setEstnte(this.estnte);
		clon.setModulo(this.modulo);
		clon.setDescripcion(this.descripcion);
		clon.setSolicitada(this.solicitada);
		clon.setEntregada(this.entregada);
		clon.setRemitida(this.remitida);
		clon.setDescDeposito(this.descDeposito);
		clon.setIdDepDestino(this.idDepDestino);
		clon.setPreparada(this.preparada);
		clon.setIdDepOrigen(this.idDepOrigen);
		clon.setSotck(this.sotck);
		clon.setAreaArt(this.areaArt);
		clon.setSectorEstanteria(this.sectorEstanteria);
		clon.setDescEstanteria(this.descEstanteria);
		clon.setPedido(this.pedido);
		clon.setDocumento(this.documento);
		clon.setJustificacion(this.justificacion);
		clon.setAutoVerificacion(this.autoVerificacion);
		clon.setBulto(this.isBulto);
		clon.setContenido(this.contenido);
		clon.setBultoCerrado(this.isBultoCerrado);
		clon.setQtyBulto(this.qtyBulto);
		clon.setQtyPickBulto(this.qtyPickBulto);
		clon.setConsolidaPedidos(this.consolidaPedidos);
		clon.setPicked(this.picked);
		
		return clon;
	}
	public String getPosClasif() {
		return posClasif;
	}
	public void setPosClasif(String posClasif) {
		this.posClasif = posClasif;
	}
	
	
	
	
	
	
}

