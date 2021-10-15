package integraciones.erp.visualStore.stadium.tienda.clienteWSVS;


import persistencia._EncuentraConexionAPI;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.jws.WebParam;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import javax.xml.ws.Response;

import beans.datatypes.DataIDDescripcion;



public class WSCommunicate {
	
	
	
	public boolean ConfirmarTransferenciaTienda(int origen, int destino, int numeroDoc,List<DataIDDescripcion>lista, String coments,short idTienda, short equipo, short tienda,Long usuario)
	{
		SWStadium service = new SWStadium();
		Long empresa = (long) 50000;
		SWStadiumSoap port = service.getSWStadiumSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Boolean> tr = new Holder<Boolean>();
		Holder<String> msj = new Holder<String>();
		try 
	    {
			port.confirmarTransferenciaTienda(msj, usuario, equipo, idTienda, empresa, (short)origen, (short)destino, numeroDoc, xmlDetalle, tr);
			//port.confirmarTransferencia(msj, usuario, equipo, idTienda, empresa, (short)origen, (short)destino, numeroDoc, xmlDetalle, tr);
			
			System.out.println(msj.value);
			return true;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public DataIDDescripcion GrabarTransferenciaTienda(int origen, int destino,List<DataIDDescripcion>lista, String coments,short idTienda, short equipo, short tienda,Long usuario,boolean confirmada, String coment, int razon)
	{
		SWStadium service = new SWStadium();
		Long empresa = (long) 50000;
		SWStadiumSoap port = service.getSWStadiumSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Integer> tr = new Holder<Integer>();
		Holder<String> msj = new Holder<String>();
		DataIDDescripcion retorno = new DataIDDescripcion();
		try 
	    {
			//Holder<String> mensaje,
			//long idUsuario,
			//short idEquipo,
			//short idTienda,
			//long nroEmpresa,
			//int idDeposito,
			// XMLGregorianCalendar fecha,
			//String hora,
			//String comentario,
			//int idDepDestino,
			//int idRazonDocumento,
			//String xmlDetalle,
			//boolean confirmada,
			//Holder<Integer> grabarTransferenciaTiendaResult)
			port.grabarTransferenciaTienda(msj, usuario, equipo, idTienda, empresa, origen, getXMLGregorianCalendarNow().toString(), "", coment, destino, razon, xmlDetalle, confirmada, tr);
			
			
			System.out.println(msj.value);
			try {
				retorno.setId(tr.value);
				retorno.setDescripcion(msj.value);

				if(retorno.getDescripcion()==null) {
					retorno.setDescripcion("");
				}
				if(tr.value==null) {
					retorno.setId(0);
				}
			} catch (Exception e) {
				retorno.setId(0);
				retorno.setDescripcion("");
			}
			return retorno;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			retorno.setId(0);
			retorno.setDescripcion("error");
			return retorno;
		}
	}
	
	public int encolarTransferenciaTienda(int origen, int destino,List<DataIDDescripcion>lista, String coments,short idTienda, short equipo, 
			short tienda,Long usuario,boolean confirmada, String coment, int razon, String idPedido, int doc, String descripcion, int idEmpresa)
	{		
		try 
	    {			
			 _EncuentraConexionAPI eper = new _EncuentraConexionAPI();
			
			 int conf = 0;
			 int exitoso = 0;
			 if(confirmada) {
				 conf = 1;
			 }
			 if (doc!=0) {
				 exitoso = 1;
			 }
			
			String q1 = "INSERT INTO `cola_mov_stock` "
					+ " (`idPedidoWEB`, `intentos`, `exitoso`, `origen`, `destino`, `coments`, `idTienda`, `equipo`, `tienda`, `usuario`, `confirmada`, `coment`, `razon`,`docVisual`,`descVisual`) "
					+ " VALUES ("+idPedido+", '1', "+exitoso+", '"+origen+"', '"+destino+"', '"+coments+"', '"+idTienda+"', '"+equipo+"', '"+tienda+"', '"+usuario+"', "+conf+", '"+coment+"', '"+razon+"',"+doc+",'"+descripcion+"');";
			int id=eper.persistirDarUltimo(q1,"cola_mov_stock","id",idEmpresa);
			
			String q2 = "INSERT INTO `cola_mov_stock_lineas` (`id`, `idArticulo`, `cantidad`) VALUES ";
			boolean pri = true;
			for (DataIDDescripcion a : lista) 
			{
				if(pri)
				{
					q2+=" ("+id+", '"+a.getDescripcion()+"', '"+a.getId()+"')";
					pri = false;
				}
				else
				{
					q2+=" ,("+id+", '"+a.getDescripcion()+"', '"+a.getId()+"')";
				}
			}
			
			eper.persistir(q2);
			
			return id;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateColaTransferenciaTienda(int id, int numeroDoc, String descVisual)
	{
		
		try 
	    {
			int ok = 0;
			if(numeroDoc!=0) {
				ok = 1;
			}
			_EncuentraConexionAPI eper = new _EncuentraConexionAPI();
			
			String q1 = "update `cola_mov_stock` set ultimo_intento=CURRENT_TIMESTAMP(),intentos = intentos+1, exitoso="+ok+",docVisual="+numeroDoc+", descVisual='"+descVisual+"'"+
					" where id="+id;
					
			
			eper.persistir(q1);			
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
		}
		return id;
	}
	
	public int GrabarTransferencia(int origen, int destino,List<DataIDDescripcion>lista, String coments,short idTienda, short equipo, short tienda,Long usuario,boolean confirmada, String coment, int razon)
	{
		SWStadium service = new SWStadium();
		Long empresa = (long) 50000;
		SWStadiumSoap port = service.getSWStadiumSoap();
		String xmlDetalle=this.ArmarXMLDetalle(lista);
		Holder<Integer> tr = new Holder<Integer>();
		Holder<String> msj = new Holder<String>();
		try 
	    {
			//Holder<String> mensaje,
			//long idUsuario,
			//short idEquipo,
			//short idTienda,
			//long nroEmpresa,
			//int idDeposito,
			// XMLGregorianCalendar fecha,
			//String hora,
			//String comentario,
			//int idDepDestino,
			//int idRazonDocumento,
			//String xmlDetalle,
			//boolean confirmada,
			//Holder<Integer> grabarTransferenciaTiendaResult)
			port.grabarTransferencia(msj, usuario, equipo, idTienda, empresa, origen, getXMLGregorianCalendarNow().toString(), "", coment, destino, razon, xmlDetalle, confirmada, tr);;
			
			
			System.out.println(msj.value);
			return tr.value;
		} 
	    catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
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
	
	
	
	
	
	public String ArmarXML(List<DataIDDescripcion>lista){
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
    public XMLGregorianCalendar getXMLGregorianCalendarNow() 
            throws DatatypeConfigurationException
    {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now = 
            datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }
	
	
	
}
