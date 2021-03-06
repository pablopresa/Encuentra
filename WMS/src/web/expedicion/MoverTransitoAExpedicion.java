package web.expedicion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.EnviaMail;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;




import beans.Articulo;


import beans.Estado;
import beans.OjosEnCero;
import beans.Sucursal;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataIDCollection;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Remito;
import beans.encuentra.RemitoLinea;
import beans.encuentra.Transporte;

public class MoverTransitoAExpedicion extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
		
				HttpSession session = request.getSession();
				Logica l = new Logica();
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");
				Utilidades util = new Utilidades();
				int idEmpresa = util.darEmpresa(uLog);
				
				String ojoRecep = "0";
				
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				List<Remito> remitosIn =  (List<Remito>) session.getAttribute("remitosTRIn");
				List<bulto> bultosRemitos = new ArrayList<>();
				
				DataIDDescDescripcion depoTarget = (DataIDDescDescripcion) session.getAttribute("depoTR");
				List<DataIDDescripcion> remitosCantidad = new ArrayList<>();
				
				for (Remito r : remitosIn) 
				{
					int cantRem = 0;
					bulto bul = new bulto(r.getIdDestino()+"_"+r.getNumeroDoc(), r.getIdDestino()+"_"+r.getNumeroDoc(), false, 0, 0, 0, 0.0, false, "", uLog.getNumero(), ""+r.getIdDestino(), idEmpresa);
					
					for (RemitoLinea rl : r.getLineas()) 
					{
						cantRem += rl.getCantidad();
						bultoContenido bc = new bultoContenido(rl.getIdArticulo(), rl.getCantidad(), 0, uLog.getNumero());
						bul.Agregar_A_Bulto_NO_persist(bc);
					}
					
					bultosRemitos.add(bul);
					
					remitosCantidad.add(new DataIDDescripcion(cantRem,r.getNumeroDoc()+""));					
				}
				
				l.CrearBultos(bultosRemitos, idEmpresa);
				
				for(bulto b:bultosRemitos){
					for(DataIDDescripcion d:remitosCantidad){
						if(b.getIdBulto().contains(d.getDescripcion())){
							b.Cargar_Remito(d.getDescripcion(), 1, d.getId());
							break;
						}
					}
				}
				
				
				List<DataIDDescripcion> ojosExpedicion = l.encuentraOjosExpedicion(depoTarget.getId()+"","", idEmpresa);
				
				ojosExpedicion.remove(0);
				
				DataIDDescripcion  ojoExp = ojosExpedicion.get(0); 
				
				Hashtable<Integer, List<DataIDDescripcion>> docsRazones = new Hashtable<Integer, List<DataIDDescripcion>>();
				List<DataIDDescripcion> dataDocumento= new ArrayList<>();
				
				//4 = trasito
				int transito = 4;
				
				for (bulto bu : bultosRemitos) 
				{
					l.encuentraUpdateOjos(ojoRecep, bu.getIdBulto(),1, false, uLog.getNumero(),false,"RRT",idEmpresa);
					
					//descripcion va idBulto
					//id= razon
					DataIDDescripcion b = new DataIDDescripcion(transito,bu.getIdBulto());
					
					dataDocumento.add(b);
					
					
					l.encuentraBajaArticulosOjos(1,bu.getIdBulto(), ojoRecep, idEmpresa);	//MOVIMIENTO DE MERCADERIA
					l.encuentraMoverOjos(ojoExp.getDescripcion(),b.getDescripcion(),1,uLog.getNumero(), idEmpresa);		
					l.IngresarMovimientoArticulo(ojoRecep, ojoExp.getDescripcion(), b.getDescripcion(), 1,  uLog.getNumero(), idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA
					
				}
				
				docsRazones.put(transito, dataDocumento);
				
				List<Integer> docs;
				docs = l.GenerarDocumnetoExpedicion(depoTarget.getId(), docsRazones, uLog, Integer.parseInt(uLog.getDeposito()),idEmpresa);	//GENERO DOC PARA EXPEDICION
				
				
				if (docs.size()>0){
					String docs_ = "";
					for(Integer d :docs){
						docs_ += d+",";
					}
					docs_ = docs_.substring(0,docs_.length()-1);
					uLog.registrarEventoMin(session.getId(), "Se generaron los siguientes documentos ("+docs_+") para el destino "+depoTarget.getId());
					
					request.setAttribute("menError", "Se generaron los siguientes documentos ("+docs_+") para el destino "+depoTarget.getId());
					return mapping.findForward("ok");
				}
				else{
					request.setAttribute("menError", "No se pudieron grabar documentos, cierre sesion e intente nuevamente");
					return mapping.findForward("dep");
				}
				
				
								
							
				
					
			}
	
		}


