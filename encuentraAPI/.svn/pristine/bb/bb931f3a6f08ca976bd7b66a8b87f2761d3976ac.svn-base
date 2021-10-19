package integraciones.lAaS;

import java.util.Hashtable;
import java.util.List;

import beans.datatypes.DataIDDescripcion;
import integraciones.marketplaces.fenicio.apiComercios.PrecioLista;
import integraciones.marketplaces.fenicio.apiComercios.PrecioVenta;
import integraciones.marketplaces.fenicio.apiComercios.Presentaciones;
import integraciones.marketplaces.fenicio.apiComercios.Productos;
import integraciones.marketplaces.fenicio.apiComercios.Variantes;

public class LogicaProdFenicio 
{
  
	
	public LogicaProdFenicio() {}
	
	public Productos [] darProductos (List<DataIDDescripcion> lista)
	{
		Hashtable<String, Productos> prodsHt = new Hashtable<>();
		Productos [] arregloProductos = null;
		
		for (DataIDDescripcion ar : lista) 
		{
			String madre = ar.getDescripcion().substring(0, ar.getDescripcion().length()-3);
			String variante = ar.getDescripcion().substring(0, ar.getDescripcion().length()-3);
			String talle = ar.getDescripcion().substring(ar.getDescripcion().length()-3);
			Productos pr = null;
			if(prodsHt.containsKey(madre))
			{
				pr =  prodsHt.get(madre);
				Presentaciones pre = darPresentacion (madre,variante,talle,ar);
				
				boolean tieneVa = false;
				int posVa=0;
				for (int i = 0; i <  pr.getVariantes().length; i++) 
				{
					if(pr.getVariantes()[i].getCodigo().equals(variante))
					{
						tieneVa = true;
						posVa = i;
						break;
					}
					
				}
				if(tieneVa)
				{
					
					Variantes [] vars = pr.getVariantes();
					Variantes var = vars[posVa];
					var.setPresentaciones(addPresentaciones(var.getPresentaciones(), pre));
					vars[posVa]=var;
					
					pr.setVariantes(vars);
					
				}
				else
				{
					Presentaciones[] arregloPR = new Presentaciones []{pre};
					
					Variantes var = new Variantes();
					var.setCodigo(variante);
					var.setPresentaciones(arregloPR);
					
					Variantes[] arreglosVA = new Variantes[] {var};
					
					pr.setVariantes(arreglosVA);
				}
				
			}
			else
			{
				pr = new Productos();
				pr.setCodigo(madre);
				
				Presentaciones pre =darPresentacion(madre, variante, talle, ar);
				
				Presentaciones[] arregloPR = new Presentaciones []{pre};
				
				Variantes var = new Variantes();
				var.setCodigo(variante);
				var.setPresentaciones(arregloPR);
				
				Variantes[] arreglosVA = new Variantes[] {var};
				
				pr.setVariantes(arreglosVA);
				
				
				
			}
			
			arregloProductos =  addProductos(arregloProductos, pr);
			prodsHt.put(madre, pr);
		}
		return arregloProductos;
	}
	
	public  Presentaciones[] addPresentaciones(Presentaciones arr[], Presentaciones n)
    {
	 	System.out.println("");
        int leng = arr.length;
  
        // create a new array of size n+1
        
        Presentaciones newarr[] = new Presentaciones[leng + 1];
        if(leng>0)
        {
	        int i = 0;
	        for (i = 0; i < leng; i++)
	        {
	        	newarr[i] = arr[i];
	        }
	        newarr[i] = n;
        }
        else
        {
        	newarr[0] = n;
        }
  
        
  
        return newarr;
    }
	
	public  Variantes[] addVariantes(Variantes arr[], Variantes n)
    {
	 	System.out.println("");
        int leng = arr.length;
  
        // create a new array of size n+1
        
        Variantes newarr[] = new Variantes[leng + 1];
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
	
	public  Productos[] addProductos(Productos arr[], Productos n)
    {
	 	try
	 	{
	 		System.out.println("");
		 	int leng =0;
		 	if(arr!=null)
		 	{
		 		leng = arr.length;
		 	}
		 	
	  
	        // create a new array of size n+1
	        
	        Productos newarr[] = new Productos[leng+1];
	        if(leng>0)
	        {
		        int i = 0;
		        for (i = 0; i < leng; i++)
		        {
		        	newarr[i] = arr[i];
		        }
		        newarr[i] = n;
	        }
	        else
	        {
	        	newarr[0] = n;
	        }
	  
	        
	  
	        return newarr;
	 		
	 	}
	 	catch (Exception e) 
	 	{
	 		e.printStackTrace();
			return null;
		}
		
    }

	private Presentaciones darPresentacion(String madre, String variante, String talle, DataIDDescripcion ar) 
	{
		Presentaciones pre = new Presentaciones();
		pre.setCodigo(talle);
		
		PrecioLista pl = new PrecioLista();
		pl.setUYU(ar.getIdD()+"");
		pre.setPrecioLista(pl);
		pre.setStock(ar.getId());
		pre.setNombre(talle);
		pre.setSku(madre+"-"+variante+"-"+talle);
		PrecioVenta pv = new PrecioVenta();
		pv.setUYU(ar.getIdD()+"");
		pre.setPrecioVenta(pv);
		pre.setIdentificador(null);
		
		return pre;
	}
}
