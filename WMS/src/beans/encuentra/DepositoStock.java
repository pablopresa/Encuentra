package beans.encuentra;

import java.util.ArrayList;
import java.util.List;

import dataTypes.DataIDDescripcion;

public class DepositoStock implements Comparable
{
	private int idDeposito;
	private int prioridad;
	private boolean preparaPickup,preparaDelivery,preparaEnvioCD;
	
	/*
	 	idArticulo (descripcion)
		Stock (id)
	 */
	private List<DataIDDescripcion> articulos;

	public int getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public boolean isPreparaPickup() {
		return preparaPickup;
	}

	public void setPreparaPickup(boolean preparaPickup) {
		this.preparaPickup = preparaPickup;
	}

	public boolean isPreparaDelivery() {
		return preparaDelivery;
	}

	public void setPreparaDelivery(boolean preparaDelivery) {
		this.preparaDelivery = preparaDelivery;
	}

	public boolean isPreparaEnvioCD() {
		return preparaEnvioCD;
	}

	public void setPreparaEnvioCD(boolean preparaEnvioCD) {
		this.preparaEnvioCD = preparaEnvioCD;
	}

	public List<DataIDDescripcion> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<DataIDDescripcion> articulos) {
		this.articulos = articulos;
	}

	public DepositoStock(int idDeposito, int prioridad, boolean preparaPickup, boolean preparaDelivery,
			boolean preparaEnvioCD) {
		this.idDeposito = idDeposito;
		this.prioridad = prioridad;
		this.preparaPickup = preparaPickup;
		this.preparaDelivery = preparaDelivery;
		this.preparaEnvioCD = preparaEnvioCD;
		this.articulos = new ArrayList<>();
	}
	
	@Override
	public int compareTo(Object o) 
	{
		DepositoStock dl = (DepositoStock)o;        

		  if(this.getPrioridad() == dl.getPrioridad()) 
          { 
              return 0;
          } 
          else if(this.getPrioridad()>dl.getPrioridad())
	      {
	      		return 1;
	      }
	      else
	      {
	      		return -1;
	      }
	      	}
	
	
	

}
