package web.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import beans.Fecha;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;

public class CopiarBulto extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception 
	{

		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		
		
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}

		if(uLog!=null)
		{
			bulto bul = (bulto) session.getAttribute("currentBulto");
			Hashtable<String, DataOjoArticulo> articulosOJO = (Hashtable<String, DataOjoArticulo>) session.getAttribute("articulosOJO");
			List<DataIDDescripcion> articulosBulto = (List<DataIDDescripcion>) session.getAttribute("articulosBulto");
			String ojo = (String) session.getAttribute("ojoOrigen");
			int accion = Integer.parseInt(request.getParameter("accion"));
			Random r = new Random();
			int ran =r.nextInt((999 - 1) + 1) + 1;
			
			switch (accion) 
			{
				case 0://copiar
					
					
					
					
					String dummyLabel = request.getParameter("DummyLabel");
					if(dummyLabel.equals(""))
					{
						
						
						int cantCopias = Integer.parseInt(request.getParameter("clonarBultoQty"));
						
						for (DataIDDescripcion ab : articulosBulto) 
						{
							ab.setId(ab.getId()*(cantCopias+1));
						}
						
						
						/*verifico si en el origen hay merca suficiente para la cantidad de copias*/
						String articulosNo = "";
						
						for (DataIDDescripcion ar : articulosBulto) 
						{
							
							if(articulosOJO.get(ar.getDescripcion())!=null)//pregunto si el escaneado est? en el ojo de origen
							{
								DataOjoArticulo ojArt =articulosOJO.get(ar.getDescripcion()); 
								int cantEnOjo= ojArt.getCantidad();
								
								if(cantEnOjo<ar.getId())
								{
									articulosNo +=ar.getDescripcion()+" hay "+cantEnOjo+" en ojo = "+ojo+" y quiere usar "+ar.getId()+"\\n";
								}
								
								
								
							}
							else//no estaba en origen
							{
								
								articulosNo +=ar.getDescripcion()+" hay 0 en ojo "+ojo+" \\n";
								
							}
							
							
							
						}
						
						for (DataIDDescripcion ab : articulosBulto) 
						{
							ab.setId(ab.getId()/(cantCopias+1));
						}
						
						if(articulosNo.equals(""))
						{
							bul.Crear_Bulto(idEmpresa);
							bul.guardarDetalleBulto();
							boolean moverStock = false;
							bul.Cerrar_Bulto(uLog.getNumero(),moverStock);
							
							List<bulto> bultos = bul.copiar(bul, cantCopias);
							
							for (bulto bulto : bultos) 
							{
								
								for (DataIDDescripcion b : articulosBulto) 
								{
									Logica.encuentraBajaArticulosOjos(b.getId(),b.getDescripcion(), ojo, idEmpresa);
									//muevo los articulos del origen al bulto
									Logica.IngresarMovimientoArticuloTipo(ojo, bulto.getIdBulto(), b.getDescripcion(), b.getId(), uLog.getNumero(),"BUA", idEmpresa);
									
									
								}
								Logica.bajarSubirDeBulto(bulto.getIdBulto(), articulosBulto, idEmpresa, uLog.getNumero(),1, false);
								Logica.encuentraUpdateOjos(ojo, bulto.getIdBulto(),1, false,uLog.getNumero(),uLog.isInventario(),"ADD",idEmpresa);
								
								
								
							}
							
							
							IPrint ip = new IPrint();
							String path = ip.ImprimirEtiquetasBultosR(bultos,"Bultos_STANDALONE"+ran, uLog);
							Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
				            
							
							 
							
							
							api.PutColaImpresion(ran+"Bultos_STANDALONE"+ran, path, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
						}
						else
						{
							request.setAttribute("menError", "no se puede crear bultos porque \\n"+articulosNo);
							return mapping.findForward("no");
						}
						
						
						
						
						
						
						
						
						
						
						
			            
					}
					else
					{
						bul.setIdBulto(dummyLabel);
						bul.guardarDetalleBulto();
						boolean moverStock = false;
						bul.Cerrar_Bulto(uLog.getNumero(),moverStock);
						
						
						
						
					}
					
					
					
					
					
					
					
					
		            
					
					
				break;
				case 1://Contar de nuevo
					//bul.destruir();
				break;
				
				case 2://Contar de nuevo
					//bul.destruir();
				break;

			default:
				break;
			}
			
		}
		
		
		
		
		
		
		
		//return mapping.findForward("bul");
		
		
		return mapping.findForward("ok");
	}
}



