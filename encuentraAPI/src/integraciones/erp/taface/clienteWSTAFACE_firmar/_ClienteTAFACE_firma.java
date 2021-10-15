package integraciones.erp.taface.clienteWSTAFACE_firmar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


import beans.Fecha;
import beans.datatypes.DataIDDescripcion;
import integraciones.erp.taface.clienteWSTAFACE_Confirmar.WSIntegracionConfirmarFirmaComprobante0205;
import integraciones.erp.taface.clienteWSTAFACE_Confirmar.WSIntegracionConfirmarFirmaComprobante0205Execute;
import integraciones.erp.taface.clienteWSTAFACE_Confirmar.WSIntegracionConfirmarFirmaComprobante0205ExecuteResponse;
import integraciones.erp.taface.clienteWSTAFACE_Confirmar.WSIntegracionConfirmarFirmaComprobante0205SoapPort;
import integraciones.erp.taface.clienteWSTAFACE_firmar.SDTCFEEntrada205DGI.Detalles;
import integraciones.erp.taface.clienteWSTAFACE_firmar.SDTCFEEntrada205DGIDetalle.DETCodItems;



public class _ClienteTAFACE_firma 
{

	public _ClienteTAFACE_firma() {
	}
	public static void main(String[] args) 
	{
		
		
		
		//System.out.println(rsp.getPnombrepdf());
		
		
		List<DataIDDescripcion> lista = new ArrayList<>();
		
		lista.add(new DataIDDescripcion(1, "184.574NU290409.0", "Classic Life Style - Ref.WL574ENUY Piedra-Beige 09"));
		
		
		firmar(1,76, 39, "STADIUM Central", 12, lista, "Shop. Tres Cruces - Local 24", "Montevideo", "15885454", "AA");

		
		
	}
	
	
	
	public static String firmar(int origen, int caja, int origenDGI,String origenNombre, int destino, List<DataIDDescripcion> lista, String direccionDestino,String ciudadDestino, String documentoNRO, String documentoSRE)
	{
		try
		{
			WSIntegracionFirmarComprobante0205 servicio = new WSIntegracionFirmarComprobante0205();
			
			WSIntegracionFirmarComprobante0205SoapPort cliente = servicio.getWSIntegracionFirmarComprobante0205SoapPort();
			
			WSIntegracionFirmarComprobante0205Execute parameters = new WSIntegracionFirmarComprobante0205Execute();
			
			
			
			Long cero = new Long("0");
			
			parameters.setPcaenroautorizacion(cero);
			parameters.setPcaenroreservado(0);
			parameters.setPcaeserie("");
			parameters.setPcajaid((short) caja);
			parameters.setPempruc("210703920014");
			parameters.setPsucid((short) origen);
			
			XMLENTRADA entrada = new XMLENTRADA();
			
			
			/*
		   
			<EMPRESARUC>210703920014</EMPRESARUC>
	        <SUCURSALNRO>99</SUCURSALNRO>
	        <CAJANRO>76</CAJANRO>
	        <SOFTWAREFACTURADOR>Visual Store</SOFTWAREFACTURADOR>
	        <VERSIONDESOFTWAREFACTURADOR>080478</VERSIONDESOFTWAREFACTURADOR>
	        <TRANSACCIONNRO>76262</TRANSACCIONNRO>
	        <DOCUMENTOORIGINALTIPO>TRS</DOCUMENTOORIGINALTIPO>
	        <DOCUMENTOORIGINALSERIE>-</DOCUMENTOORIGINALSERIE>
	        <DOCUMENTOORIGINALNRO>76262</DOCUMENTOORIGINALNRO>
	        <CAJERONRO>2804</CAJERONRO>
	        <CAJERONOMBRE>Florencia González </CAJERONOMBRE>
	        <CLIENTENRO>0</CLIENTENRO>
	        <CLIENTENOMBRE>Stadium</CLIENTENOMBRE>
	        <CLIENTERAZONSOCIAL>Stadium</CLIENTERAZONSOCIAL>
	        <CLIENTEDOCUMENTO>210703920014</CLIENTEDOCUMENTO>
	        <CLIENTEDIRECCION>Shop. Tres Cruces - Local 24</CLIENTEDIRECCION>
	        <CLIENTEPAISNOM>UY</CLIENTEPAISNOM>
	        <VENDEDORNRO>0</VENDEDORNRO>
	        <TIPODENEGOCIO>2</TIPODENEGOCIO>
	        <COTIZACIONUI>4.027</COTIZACIONUI>
	        
	        */
			
			Double cotizacionUY =4.027; 
			
			SDTCFEEntrada205DATOSADICIONALES da = new SDTCFEEntrada205DATOSADICIONALES();
			da.setCAJANRO(caja);//int
			da.setCAJERONOMBRE("WMS ENCUENTRA");//string
			da.setCAJERONRO("999999");//string
			da.setCLIENTEDIRECCION(direccionDestino);//string direcccion del depo de destino
			da.setCLIENTEDOCUMENTO("210703920014");//el rut de CYBE
			da.setCLIENTEEMAIL("");
			da.setCLIENTENOMBRE("Stadium");
			da.setCLIENTENRO("0");
			da.setCLIENTEPAISNOM("UY");
			da.setCLIENTERAZONSOCIAL("Stadium");
			da.setCOTIZACIONUI(cotizacionUY);
			da.setDOCUMENTOORIGINALNRO(documentoNRO);
			da.setDOCUMENTOORIGINALSERIE(documentoSRE);
			da.setDOCUMENTOORIGINALTIPO("TRS");
			da.setEMPRESARUC("210703920014");
			da.setSOFTWAREFACTURADOR("WMS ENCUENTRA");
			da.setSUCURSALNRO(origen);
			da.setTIPODENEGOCIO((short)2);
			da.setTRANSACCIONNRO(documentoNRO);
			da.setVENDEDORNRO("0");
			da.setVERSIONDESOFTWAREFACTURADOR("2.0");
			
			
			entrada.setDATOSADICIONALES(da);
			
			SDTCFEEntrada205DGI dgi = new SDTCFEEntrada205DGI();
			/*
			 * IDDocTipoCFE>181</IDDocTipoCFE>
        <IDDocFchEmis>2019-01-07</IDDocFchEmis>
        <IDDocTipoTraslado>2</IDDocTipoTraslado>
        <EMIRUCEmisor>210703920014</EMIRUCEmisor>
        <EMIRznSoc>Cybe S.A.</EMIRznSoc>
        <EMINomComercial>STADIUM</EMINomComercial>
        <EMICorreoEmisor>compras@stadium.com.uy</EMICorreoEmisor>
        <EMIEmiSucursal>STADIUM Central</EMIEmiSucursal>
        <EMICdgDGISucur>39</EMICdgDGISucur>
        <EMIDomFiscal>Republica Francesa 900</EMIDomFiscal>
        <EMICiudad>Montevideo</EMICiudad>
        <EMIDepartamento>Montevideo</EMIDepartamento>
        <RECTipoDocRecep>2</RECTipoDocRecep>
        <RECCodPaisRecep>UY</RECCodPaisRecep>
        <RECDocRecep>210703920014</RECDocRecep>
        <RECRznSocRecep>Stadium</RECRznSocRecep>
        <RECDirRecep>Shop. Tres Cruces - Local 24</RECDirRecep>
        <RECCiudadRecep>Montevideo</RECCiudadRecep>
        <RECPaisRecep>URUGUAY</RECPaisRecep>
        <TOTCantLinDet>97</TOTCantLinDet>*/
			Fecha fecha = new Fecha();
			
			
			
			Calendar createDate = Calendar.getInstance();
			Date cDate = createDate.getTime();
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(cDate);
			XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			
			
			dgi.setIDDocTipoCFE((short)181);
			dgi.setIDDocFchEmis(date2);
			dgi.setIDDocTipoTraslado((byte)2);
			dgi.setEMIRUCEmisor("210703920014");
			dgi.setEMIRznSoc("Cybe S.A.");
			dgi.setEMINomComercial("STADIUM");
			dgi.setEMICorreoEmisor("compras@stadium.com.uy");
			dgi.setEMIEmiSucursal(origenNombre);
			dgi.setEMICdgDGISucur((short)origenDGI);
			dgi.setEMIDomFiscal("Republica Francesa 900");
			dgi.setEMICiudad("Montevideo");
			dgi.setEMIDepartamento("Montevideo");
			dgi.setRECTipoDocRecep((byte)2);
			dgi.setRECCodPaisRecep("UY");
			dgi.setRECDocRecep("210703920014");
			dgi.setRECRznSocRecep("Stadium");
			dgi.setRECDirRecep(direccionDestino);
			dgi.setRECCiudadRecep(ciudadDestino);
			dgi.setRECPaisRecep("URUGUAY");
			
			
			
			
			Detalles detalles = new Detalles();
			
			List<SDTCFEEntrada205DGIDetalle> detalle = new ArrayList<>();
			
			
			int lineas = 1;
			int total = 0;
			/*********************************/
			//descripcion = idarticulo
			//descripcionB = nombreArticulo
			/*********************************/
			for (DataIDDescripcion di : lista) 
			{
				SDTCFEEntrada205DGIDetalle det = new SDTCFEEntrada205DGIDetalle();
				
				det.setDETNroLinDet((short)lineas);
				List<SDTCFEEntrada205DGIDetalleDETCodItem> cod = new ArrayList<>();
				SDTCFEEntrada205DGIDetalleDETCodItem item = new SDTCFEEntrada205DGIDetalleDETCodItem();
				item.setDETCod(di.getDescripcion());
				item.setDETTpoCod("INT1");
				
				cod.add(item);
				
				DETCodItems dci = new DETCodItems();
				dci.detCodItem = cod;
				
				det.setDETCodItems(dci);
				det.setDETIndFact((byte)0);
				det.setDETNomItem(di.getDescripcionB());
				det.setDETCantidad((Double.parseDouble(di.getId()+"")));
				det.setDETUniMed("N/A");
				lineas++;
				
				total++;
				detalle.add(det);
			}
			
			
			
			
			detalles.detalle = detalle;
			dgi.setTOTCantLinDet((short)total);
			dgi.setDetalles(detalles);
			
			entrada.setDGI(dgi);
			
			parameters.setPxmlentrada(entrada);
			
			
			WSIntegracionFirmarComprobante0205ExecuteResponse rsp = cliente.execute(parameters);
			
			
			System.out.println("Respuesta ws: "+rsp.getPerrormessage());
			
			System.out.println("cae num auth: "+rsp.getPxmlrespuesta().getCAENA());
			System.out.println("cae serie: "+rsp.getPxmlrespuesta().getCAESERIE());
			System.out.println("cae Num: "+rsp.getPxmlrespuesta().getCAENRO());
			
			
			if(rsp.getPxmlrespuesta().getCAENRO()==0)
			{
				return rsp.getPerrormessage();
			}
			
			WSIntegracionConfirmarFirmaComprobante0205 servicio2 = new WSIntegracionConfirmarFirmaComprobante0205();
			
			WSIntegracionConfirmarFirmaComprobante0205SoapPort cliente2 = servicio2.getWSIntegracionConfirmarFirmaComprobante0205SoapPort();
			
			WSIntegracionConfirmarFirmaComprobante0205Execute parameters2 = new WSIntegracionConfirmarFirmaComprobante0205Execute();
			
			
			
			
			
			parameters2.setPempruc("210703920014");
			parameters2.setPsucid(origen);
			parameters2.setPcajaid((short)caja);
			parameters2.setPcaenro(rsp.getPxmlrespuesta().getCAENRO());
			parameters2.setPcaeserie(rsp.getPxmlrespuesta().getCAESERIE());
			parameters2.setPcaenroautorizacion(rsp.getPxmlrespuesta().getCAENA());

			
			WSIntegracionConfirmarFirmaComprobante0205ExecuteResponse rsp2 = cliente2.execute(parameters2);
			
			System.out.println("ERROR EN CONFIRMA: " +rsp2.getPerrormessage());
			
			
			return rsp2.getPerrormessage();
		}
		catch (Exception e)
		{
			return "ERROR";
		}
		
	}

}
