package integraciones.erp.visualStore.forus.central;


import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import beans.datatypes.DataBooleanDesc;
import beans.datatypes.DataIDDescripcion;

public class ClienteWSVisualForus 
{

	private short equipo = 1250;
	private Long empresa = (long) 1;
	private short tienda = 1;
	private Long usuario =(long) 1250;
	
	
	public DataIDDescripcion GrabarTransferencia(int origen, int destino, int razon,List<DataIDDescripcion>lista, String coments,
			Map<String, Integer> stocks){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Integer> tr = new Holder<>();
		Holder<String> msj = new Holder<>();
		
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		int nvoDoc = 0;
		try 
	    {
			DataBooleanDesc comprobacion = ComprobacionStock(lista,stocks);
			if(comprobacion.isBool()){
				Integer tiend = origen;
				  short tienda_s = tiend.shortValue();
				  System.out.println(tienda_s);
				  int intentos = 0;
				  boolean ok = false;
					  while (intentos < 3 && !ok) 
					  {
						  
						  String fecha = getXMLGregorianCalendarNow().toString();
					  intentos++;
					  port.grabarTransferencia(msj, usuario, equipo, tienda, empresa,	(short)origen, fecha, "", coments, (short)destino, razon, xmlDetalle, tr);
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
		
	
	public boolean ConfirmarTransferencia(int origen, int destino, int numeroDoc,List<DataIDDescripcion>lista, String coments,short idTienda){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Boolean> tr = new Holder<>();
		Holder<String> msj = new Holder<>();
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
		Holder<Integer> tr = new Holder<>();
		Holder<String> msj = new Holder<>();
		try 
	    {
			String fecha = getXMLGregorianCalendarNow().toString();
			port.grabarAjusteStock(msj, usuario, equipo, tienda, empresa, 9000, fecha, "", coments, tipoAjuste, razon,xmlDetalle, tr);
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
		Holder<Integer> tr = new Holder<>();
		Holder<String> msj = new Holder<>();
		try 
	    {
			port.grabarRecepcion(msj, usuario, equipo, tienda, empresa, tipoAfecta, (short)9000, numeroDoc, (short)9000, getXMLGregorianCalendarNow().toString(), 	"",coments, nroProveedor, serieRemito, nroRemito, xmlDetalle, tr);
			
			if(msj.toString().equals("") || msj.toString().equals("null"))
			{
				return true;
			}
			else
			{
				return false;
			}
			/*
			if(msj.toString().equals(""))
			{
				return true;
			}
			else
			{
				
				//_EncuentraPersistir eper = new _EncuentraPersistir();
				//String q = "UPDATE `detallerecepcion` SET `RecepcionERP`='"+msj+"' WHERE idDocumento="+numeroDoc+" AND idRecepcion = "+idEmpresa+" AND IdEmpresa = "+idEmpresa+"";
				//eper.persistir(q);
				return false;
			}
			*/
			
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
		Holder<Boolean> tr = new Holder<>();
		Holder<String> msj = new Holder<>();
		try 
	    {
			port.grabarArtBarra(msj, tienda, empresa, art,barra, false, tr);
						
			return tr.value;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public DataIDDescripcion EntregaPrepararYTerminar(int numeroDoc,List<DataIDDescripcion>lista, int depositoCreacion, Map<String, Integer> stocks){
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		data.setDescripcionB("");
		int nvoDoc = 0;
		try 
	    {
			SWWms service = new SWWms();
			SWWmsSoap port = service.getSWWmsSoap();
			String xmlDetalle=this.ArmarXMLDetalleEntrega(lista);
			Holder<Boolean> tr = new Holder<>();
			System.out.println(xmlDetalle);
			Holder<String> msj = new Holder<>();
			Holder<Integer> difDoc = new Holder<>();
			difDoc.value=0;
			
			DataBooleanDesc comprobacion = ComprobacionStock(lista,stocks);
			if(comprobacion.isBool()){
				int intentos = 0;
				boolean ok = false;
				  while (intentos < 3 && !ok) {
					  intentos++;
					  port.entregaPrepararyTerminar(msj, usuario, equipo, tienda, empresa, (short)depositoCreacion, numeroDoc, false, xmlDetalle,difDoc, tr);
					  if(msj.value==null || msj.value.equals("")){
						  ok = true;
					  }
				  }				
				
				try {
					if(difDoc.value!=null){
						System.out.println(difDoc.value);
						nvoDoc = difDoc.value;
						if(nvoDoc==0) {
							data.setId(1);
						}
						else {
							data.setId(0);
							data.setDescripcionB(nvoDoc+"");
						}
						
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
			data.setDescripcion("Sucedio un error mientras se grababa el doc "+numeroDoc);
			return data;
		}
	}
	
	public DataIDDescripcion EntregaPreparar(int numeroDoc,List<DataIDDescripcion>lista, int depositoCreacion, Map<String, Integer> stocks){
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		int nvoDoc = 0;
		try 
	    {
			SWWms service = new SWWms();
			SWWmsSoap port = service.getSWWmsSoap();
			String xmlDetalle=this.ArmarXMLDetalleEntrega(lista);
			Holder<Boolean> tr = new Holder<>();
			System.out.println(xmlDetalle);
			Holder<String> msj = new Holder<>();
			Holder<Integer> difDoc = new Holder<>();
			difDoc.value=0;
						  
		  DataBooleanDesc comprobacion = ComprobacionStock(lista,stocks);
			if(comprobacion.isBool()){
				int intentos = 0;
				boolean ok = false;
				  while (intentos < 3 && !ok) {
					  intentos++;
					  port.entregaPreparar(msj, usuario, equipo, tienda, empresa, (short)depositoCreacion, numeroDoc, false, xmlDetalle, difDoc, tr);
					  if(msj.value==null || msj.value.equals("")){
						  ok = true;
					  }
				  }				
				
				try {
					if(difDoc.value!=null){
						System.out.println(difDoc.value);
						nvoDoc = difDoc.value;
						if(nvoDoc==0) {
							data.setId(1);
						}
						else {
							data.setId(0);
							data.setDescripcionB(nvoDoc+"");
						}						
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
			data.setDescripcion("Sucedio un error mientras se grababa el doc "+numeroDoc);
			return data;
		}
	}
	
	public boolean EntregaTerminar(int numeroDoc,List<DataIDDescripcion>lista, int depositoCreacion){
		SWWms service = new SWWms();
		SWWmsSoap port = service.getSWWmsSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Boolean> tr = new Holder<>();
		Holder<String> msj = new Holder<>();
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

	public DataBooleanDesc ComprobacionStock(List<DataIDDescripcion>lista, Map<String, Integer> stocks){
		DataBooleanDesc data = new DataBooleanDesc();
		boolean comprobacion = true;
		String msj = "";
		try {
			//Hashtable<String, Integer> stocks = MSSQL.darStocks(lista, origen);
			
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
