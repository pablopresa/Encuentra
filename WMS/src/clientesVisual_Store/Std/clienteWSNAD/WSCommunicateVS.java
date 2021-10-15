package clientesVisual_Store.Std.clienteWSNAD;

import helper.PropertiesHelper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import javax.xml.ws.Response;

import logica.EnviaMail;
import logica.ImpresionesPDF;
import logica.Logica;
import beans.Fecha;

import persistencia.MSSQL;

import dataTypes.DataBooleanDesc;
import dataTypes.DataIDDescripcion;

public class WSCommunicateVS {
	
	Logica Logica = new Logica();
	
	public DataIDDescripcion grabarmovStockTienda(int origen, int destino, int IdUsuario, int tienda, String comentario, List<DataIDDescripcion> detalle, int idEmpresa, int idEquipoPrint, String nombreUsuario)
	{
		SWNadWeb service = new SWNadWeb();
		SWNadWebSoap port = service.getSWNadWebSoap();
		int idDoc = 0;
		
				
		int idEquipo = 2000+origen;
	    
		String x = "<NewDataSet> "+
				   " <TransfCab> "+
				   "     <IdEquipo>"+idEquipo+"</IdEquipo> "+
				   "     <IdTienda>"+tienda+"</IdTienda> "+
				   "     <IdUsuario>"+IdUsuario+"</IdUsuario> "+
				   "     <IdEmpresa>1</IdEmpresa> "+
				   "     <IdDepOrigen>"+origen+"</IdDepOrigen> "+
				   "	<IdDepDestino>"+destino+"</IdDepDestino> "+
				   "		<IdMotivo>42</IdMotivo> "+
					"	<Comentario>"+comentario+"</Comentario> "+
				"		<Confirmada>True</Confirmada> "+
				"    </TransfCab> ";
			for (DataIDDescripcion a : detalle) 
			{
				x+= "    <TransfDet> "+
					"       <IdArticulo>"+a.getDescripcion()+"</IdArticulo> "+
					"       <Cantidad>"+a.getId()+"</Cantidad> "+
					"   </TransfDet> ";
			}
		
			x+=	"</NewDataSet>";
	    
	   ImpresionesPDF print = new ImpresionesPDF();
	    
	   Holder<String> msj = new Holder<String>();
	   Holder<Integer> tr = new Holder<Integer>();
	   
	    port.grabarTransferenciaTiendaXml(msj,x, tr);  
	    
	    
	    System.out.println(x);
	    try 
	    {
	    	DataIDDescripcion salida = new DataIDDescripcion();
	    	
	    	try {
				if(tr.value!=null){
					System.out.println(tr.value);
					int nvoDoc = tr.value;
					salida.setId(nvoDoc);
				}
				if(msj.value!=null){
					System.out.println(msj.value);
					String menError = msj.value;
					menError = menError.substring(1,menError.length());
					salida.setDescripcion(menError);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			
			idDoc = salida.getId();
			
			print.imprimirTicketMovStock(origen, destino, nombreUsuario, comentario, detalle,idDoc+"",2,idEquipoPrint,idEmpresa, 1);
			return salida;
		} 
	    catch (Exception e) 
	    {
			e.printStackTrace();
			DataIDDescripcion salida = new DataIDDescripcion(idDoc,"Sucedio un error grabando en Visual"); 
			return salida;	
		}
	    
	}
	
	public WSCommunicateVS(){}
	
	
	
	
}
