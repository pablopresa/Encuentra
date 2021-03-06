package integraciones.erp.visualStore.elrey.ecommerce;


import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import beans.datatypes.DataBooleanDesc;
import beans.datatypes.DataIDDescripcion;

public class ClienteWS
{

	private short equipo = 50;
	private Long empresa = (long) 1;
	private short tienda = 1;
	private Long usuario =(long) 200;
	SWWms service = new SWWms();
	SWWmsSoap port = service.getSWWmsSoap();
	
	public DataIDDescripcion GrabarTransferencia(int origen, int destino, int razon,List<DataIDDescripcion>lista, String coments){
		
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Integer> tr = new Holder<Integer>();
		Holder<String> msj = new Holder<String>();
		
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		int nvoDoc = 0;
		try 
	    {
			DataBooleanDesc comprobacion = new DataBooleanDesc();comprobacion.setBool(true);//ComprobacionStock(lista,origen);
			if(comprobacion.isBool()){
				Integer tiend = origen;
				  short tienda_s = tiend.shortValue();
				  System.out.println(tienda_s);
				  int intentos = 0;
				  boolean ok = false;
					  while (intentos < 3 && !ok) 
					  {
						  
						  XMLGregorianCalendar fecha = getXMLGregorianCalendarNow();
					  intentos++;
					  port.grabarTransferencia(msj, usuario, equipo, tienda, empresa, 
								(short)origen, fecha, "", coments, (short)destino, razon, xmlDetalle, tr);
					  if(tr.value!=null && tr.value!=0) {
						  ok = true;
					  }
				  }
				
							
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
				data.setIdB(intentos);
			}
			else{
				data.setDescripcion(comprobacion.getDescricpion());
				data.setId(0);
				data.setIdB(1);
			}
			
			return data;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return data;
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

	public DataBooleanDesc ComprobacionStock(List<DataIDDescripcion>lista, int origen){
		DataBooleanDesc data = new DataBooleanDesc();
		boolean comprobacion = true;
		String msj = "";
		try {
			Hashtable<String, Integer> stocks = new Hashtable<>();//MSSQL.darStocks(lista, origen);
			
			for(DataIDDescripcion l:lista){
				if(stocks.get(l.getDescripcion())!=null){
					if(stocks.get(l.getDescripcion())<l.getId()){
						msj += l.getDescripcion()+",";
						comprobacion = false;						
					}
				}
				else{
					msj += l.getDescripcion()+",";
					comprobacion = false;					
				}
			}
			
			if(!msj.equals("")){
				msj = msj.substring(0,msj.length()-1);
				msj = "No hay stock suficiente de los siguientes articulos: "+msj;
			}
			data.setBool(comprobacion);
			data.setDescricpion(msj);
			
		} catch (Exception e) {
			data.setBool(false);
			data.setDescricpion("No se pudo comprobar el stock");
		}
		
		return data;
		
	}
}
