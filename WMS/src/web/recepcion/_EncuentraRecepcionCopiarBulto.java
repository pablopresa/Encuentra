package web.recepcion;

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
import beans.encuentra.IPrint;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;

public class _EncuentraRecepcionCopiarBulto extends Action 
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
			
			int accion = Integer.parseInt(request.getParameter("accion"));
			String idRecepcion = (String) session.getAttribute("recepcionID");
			switch (accion) 
			{
				case 0://copiar
					
					
					
					
					String dummyLabel = request.getParameter("DummyLabel");
					if(dummyLabel.equals(""))
					{
						bul.Crear_Bulto(idEmpresa);
						bul.guardarDetalleBulto();
						boolean moverStock = false;
						bul.Cerrar_Bulto(uLog.getNumero(),moverStock);
						
						
						int cantCopias = Integer.parseInt(request.getParameter("clonarBultoQty"));
						List<bulto> bultos = bul.copiar(bul, cantCopias);
						
						IPrint ip = new IPrint();
						
						
						String path = ip.ImprimirEtiquetasBultosR(bultos,"Recepcion_"+idRecepcion, uLog);
						Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			            
						Random r = new Random();
						int ran =r.nextInt((999 - 1) + 1) + 1;
						 
						
						
						api.PutColaImpresion(ran+"Recepcion_"+idRecepcion, path, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
			            
					}
					else
					{
						bul.setIdBulto(dummyLabel);
						bul.guardarDetalleBulto();
						boolean moverStock = false;
						bul.Cerrar_Bulto(uLog.getNumero(),moverStock);
												
						//ya hago la recepcion de ese bulto
						String inns = "";
						Hashtable<String, DataIDIDDescripcion> hashArt = new Hashtable<>();
						String idOjo = (String) session.getAttribute("ojoRecepcion");
						List<DataIDDescripcion> articulosReales = new ArrayList<>();
						DataIDDescripcion da = new DataIDDescripcion(0,dummyLabel);
						articulosReales.add(da);
						
						
						List<DataIDDescripcion> articulosCantReales = Logica.EncuentraSortearListaDI(articulosReales);
						for (DataIDDescripcion d : articulosCantReales)
						{
							System.out.println(d.getId()+" "+d.getDescripcion());
							Logica.encuentraUpdateOjos(idOjo, d.getDescripcion(),d.getId(), false, uLog.getNumero(),uLog.isInventario(),"REC",idEmpresa);
							inns+="'"+d.getDescripcion()+"',";
							hashArt.put(d.getDescripcion(), new DataIDIDDescripcion(d.getId(), d.getDescripcion()));
						}
						
						inns = inns.substring(0,inns.length()-1);
						Logica.updateRecepcionMob(Integer.parseInt(idRecepcion),inns,hashArt, uLog.getNumero(),idEmpresa);
						
						
						
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



