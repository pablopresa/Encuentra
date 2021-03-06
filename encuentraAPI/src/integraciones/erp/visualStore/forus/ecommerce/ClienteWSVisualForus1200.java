package integraciones.erp.visualStore.forus.ecommerce;


import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import javax.xml.ws.Response;

import beans.datatypes.DataIDDescripcion;

public class ClienteWSVisualForus1200 
{

	private short equipo = 1260;
	private Long empresa = (long) 1;
	private short tienda = 1200;
	private Long usuario =(long) 1250;
	
	
	public DataIDDescripcion GrabarTransferencia(int origen, int destino, int razon,List<DataIDDescripcion>lista, String coments){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Integer> tr = new Holder<Integer>();
		Holder<String> msj = new Holder<String>();
		
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		int nvoDoc = 0;
		try 
	    {
			Integer tiend = origen;
			  short tienda_s = tiend.shortValue();
			  System.out.println(tienda_s);
			port.grabarTransferencia(msj, usuario, equipo, tienda, empresa, 
					(short)origen, getXMLGregorianCalendarNow().toString(), "", coments, (short)destino, razon, xmlDetalle, tr);
						
			try {
				if(tr.value!=null){
					System.out.println(tr.value);
					nvoDoc = tr.value;
					data.setId(nvoDoc);
				}
				if(msj.value!=null){
					System.out.println(msj.value);
					String menError = msj.value;
					menError = menError.substring(1,menError.length());
					data.setDescripcion(menError);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return data;
		}
	}
		
	
	public boolean ConfirmarTransferencia(int origen, int destino, int numeroDoc,List<DataIDDescripcion>lista, String coments,short idTienda){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Boolean> tr = new Holder<Boolean>();
		Holder<String> msj = new Holder<String>();
		try 
	    {
			port.confirmarTransferencia(msj, usuario, equipo, idTienda, empresa, (short)origen, (short)destino, numeroDoc, xmlDetalle, tr);
			return tr.value;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean AjusteDeStock(String tipoAjuste, int razon,List<DataIDDescripcion>lista, String coments){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Integer> tr = new Holder<Integer>();
		Holder<String> msj = new Holder<String>();
		try 
	    {
			port.grabarAjusteStock(msj, usuario, equipo, tienda, empresa, 9000, getXMLGregorianCalendarNow().toString(), "", coments, tipoAjuste, razon, 
					xmlDetalle, tr);
			return true;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean GrabarRecepcion(List<DataIDDescripcion>lista,String tipoAfecta, String coments, int numeroDoc, int nroProveedor,
			String serieRemito, int nroRemito){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		//this.ArmarXMLTipoAfecta(imps);
		Holder<Integer> tr = new Holder<Integer>();
		Holder<String> msj = new Holder<String>();
		try 
	    {
			port.grabarRecepcion(msj, usuario, equipo, tienda, empresa, tipoAfecta, (short)9000, numeroDoc, (short)9000, getXMLGregorianCalendarNow().toString(), "",coments, nroProveedor, serieRemito, nroRemito, xmlDetalle, tr);
			
			if(msj.toString().equals(""))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean GrabarArtBarra(String art, String barra){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		Holder<Boolean> tr = new Holder<Boolean>();
		Holder<String> msj = new Holder<String>();
		try 
	    {
			port.grabarArtBarra(msj, tienda, empresa, art,barra, false, tr);
						
			return true;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public DataIDDescripcion EntregaPrepararYTerminar(int numeroDoc,List<DataIDDescripcion>lista, int depositoCreacion){
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		int nvoDoc = 0;
		try 
	    {
			SWWms service = new SWWms();
			SWWmsSoap port = service.getSWWmsSoap();
			String xmlDetalle=this.ArmarXMLDetalleEntrega(lista);
			Holder<Boolean> tr = new Holder<Boolean>();
			System.out.println(xmlDetalle);
			Holder<String> msj = new Holder<String>();
			Holder<Integer> difDoc = new Holder<Integer>();
			difDoc.value=0;
			
			port.entregaPrepararyTerminar(msj, usuario, equipo, tienda, empresa, (short)depositoCreacion, numeroDoc, false, xmlDetalle,difDoc, tr);
			
			try {
				if(tr.value!=null && tr.value){
					System.out.println(difDoc.value);
					nvoDoc = difDoc.value;
					data.setId(1);
				}
				if(msj.value!=null){
					System.out.println(msj.value);
					String menError = msj.value;
					menError = menError.substring(1,menError.length());
					data.setDescripcion(menError);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
				
			return data;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			data.setDescripcion("Sucedio un error mientras se grababa el doc "+numeroDoc);
			return data;
		}
	}
	
	public DataIDDescripcion EntregaPreparar(int numeroDoc,List<DataIDDescripcion>lista, int depositoCreacion){
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		int nvoDoc = 0;
		try 
	    {
			SWWms service = new SWWms();
			SWWmsSoap port = service.getSWWmsSoap();
			String xmlDetalle=this.ArmarXMLDetalleEntrega(lista);
			Holder<Boolean> tr = new Holder<Boolean>();
			System.out.println(xmlDetalle);
			Holder<String> msj = new Holder<String>();
			Holder<Integer> difDoc = new Holder<Integer>();
			difDoc.value=0;
				
		  port.entregaPreparar(msj, usuario, equipo, tienda, empresa, (short)depositoCreacion, numeroDoc, false, xmlDetalle, difDoc, tr);
		  
		  try {
				if(difDoc.value!=null){
					System.out.println(difDoc.value);
					nvoDoc = difDoc.value;
					data.setId(nvoDoc);
				}
				if(msj.value!=null){
					System.out.println(msj.value);
					String menError = msj.value;
					menError = menError.substring(1,menError.length());
					data.setDescripcion(menError);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
				
			return data;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			data.setDescripcion("Sucedio un error mientras se grababa el doc "+numeroDoc);
			return data;
		}
	}
	
	public boolean EntregaTerminar(int numeroDoc,List<DataIDDescripcion>lista, int depositoCreacion){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Boolean> tr = new Holder<Boolean>();
		Holder<String> msj = new Holder<String>();
		try 
	    {
			  port.entregaTerminar(msj, usuario, equipo, tienda, empresa, (short)depositoCreacion, numeroDoc, tr);
			 				
			return true;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public XMLGregorianCalendar getXMLGregorianCalendarNow() 
            throws DatatypeConfigurationException
    {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now = 
            datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }
	
	public String ArmarXMLTipoAfecta(List<DataIDDescripcion>lista){
		String xml="<NewDataSet>";
		for(int i=0;i<lista.size();i++)
		{
			  xml=xml+"<Detalle>";
			  xml=xml+"<OCC ? Orden de Compra>"+lista.get(i).getDescripcion()+"</OCC ? Orden de Compra>";
			  xml=xml+"<IMP - Importaci?n>"+lista.get(i).getDescripcionB()+"</IMP - Importaci?n>";
			  xml=xml+"</Detalle>";
		}
		xml=xml+"</NewDataSet>";
		return xml;
	}
	
	public String ArmarXMLDetalle(List<DataIDDescripcion>lista){
		String xml="<NewDataSet>";
		for(int i=0;i<lista.size();i++){
			  xml=xml+"<Detalle>";
			  xml=xml+"<IdArticulo>"+lista.get(i).getDescripcion()+"</IdArticulo>";
			  xml=xml+"<Cantidad>"+lista.get(i).getId()+"</Cantidad>";
			  xml=xml+"</Detalle>";
		}
		xml=xml+"</NewDataSet>";
		return xml;
	}
	
	public String ArmarXMLDetalleEntrega(List<DataIDDescripcion>lista){
		String xml="<NewDataSet>";
		for(int i=0;i<lista.size();i++){
			  xml=xml+"<Detalle>";
			  xml=xml+"<IdArticulo>"+lista.get(i).getDescripcion()+"</IdArticulo>";
			  xml=xml+"<IdDepEntrega>"+lista.get(i).getIdB()+"</IdDepEntrega>";
			  xml=xml+"<Cantidad>"+lista.get(i).getId()+"</Cantidad>";
			  xml=xml+"</Detalle>";
		}
		xml=xml+"</NewDataSet>";
		return xml;
	}

}
