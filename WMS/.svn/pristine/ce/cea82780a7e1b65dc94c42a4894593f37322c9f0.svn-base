package beans.encuentra;

import java.util.List;

import dataTypes.DataIDDescripcion;

public class DepositoEnvio  implements Comparable
{
	private int idDeposito, totalU, ordenCarga,totalBultos;
	private String direccion, descripcion;
	private List<DataDocTipoEnvio> documentos;
	private List<DataDocTipoEnvio> documentosExtra;
	private int tipo;
	private List<DataIDDescripcion> cantidadesXTipoDocs;
	
	
	
	
	public int getTotalBultos() {
		return totalBultos;
	}

	public void setTotalBultos(int totalBultos) {
		this.totalBultos = totalBultos;
	}

	public List<DataDocTipoEnvio> getDocumentosExtra() 
	{
		return documentosExtra;
	}
	
	public void setDocumentosExtra(List<DataDocTipoEnvio> documentosExtra) 
	{
		this.documentosExtra = documentosExtra;
	}
	public int getIdDeposito() 
	{
		return idDeposito;
	}
	public void setIdDeposito(int idDeposito) 
	{
		this.idDeposito = idDeposito;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public List<DataDocTipoEnvio> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DataDocTipoEnvio> documentos) {
		this.documentos = documentos;
	}
	
	
	
	public int getOrdenCarga() {
		return ordenCarga;
	}
	public void setOrdenCarga(int ordenCarga) {
		this.ordenCarga = ordenCarga;
	}
	public int getTotalU() {
		return totalU;
	}
	public void setTotalU(int totalU) {
		this.totalU = totalU;
	}
	public DepositoEnvio() 
	{
	
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	@Override
	public int compareTo(Object o) 
	{
		  DepositoEnvio d = (DepositoEnvio)o;        

		  if(this.ordenCarga==d.getOrdenCarga()) 
          { 
              return 0;
          } 
          else if(this.ordenCarga>d.getOrdenCarga())
          { 
          	
              return -1; 
          }
          else
          {
          	return 1;
          }
	}
	@Override
	public boolean equals(Object a) {
		boolean respuesta=false;
		DepositoEnvio u = (DepositoEnvio)a;
		if(u.idDeposito==this.idDeposito){
		respuesta=true;
		};
		return respuesta;
}

	public List<DataIDDescripcion> getCantidadesXTipoDocs() {
		return cantidadesXTipoDocs;
	}

	public void setCantidadesXTipoDocs(List<DataIDDescripcion> cantidadesXTipoDocs) {
		this.cantidadesXTipoDocs = cantidadesXTipoDocs;
	}

}
