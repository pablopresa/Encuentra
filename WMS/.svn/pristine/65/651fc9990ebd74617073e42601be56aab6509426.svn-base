package web.recepcion;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArticuloCantBarra;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataRecepcion;

import beans.Usuario;


public class _EncuentraDarArtBarrasRecep extends Action 
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
		
		String prov = request.getParameter("proveedorSel");
		session.setAttribute("proveedorOC", prov);
		
		List<DataRecepcion>recepciones=(List<DataRecepcion>) session.getAttribute("recepciones");
		int idRece = 0;
		try
		{
			idRece = Integer.parseInt(request.getParameter("idRecepcion"));
		}
		catch (Exception e)
		{
			idRece = (Integer) session.getAttribute("idRecepcion");
		}
		
		session.setAttribute("idRecepcion", idRece);
		
		String sf = (String) session.getAttribute("sf");
		

		DataRecepcion recSel = null;
		
		for (DataRecepcion r : recepciones) 
		{
			if(r.getId()==idRece)
			{
				recSel = r; 
			}
		}
		session.setAttribute("recSel", recSel);
		
		if(sf!=null && sf.equals("1"))
		{
			List<DataArticuloCantBarra> artFacturables = Logica.encuentraDarListaArticulosRecepcionados(idRece,idEmpresa);
			session.setAttribute("artFacturables", artFacturables);
			return mapping.findForward("doc");
		}
		else
		{
		
			List<DataIDDescDescripcion> articulosContados = Logica.encuentraDarArticulosContadosRec(idRece,idEmpresa);
			
			List<DataIDDescDescripcion> articulosRecep = Logica.darArticulosCantRecep(idRece,idEmpresa);
			
			if(articulosContados.isEmpty())
			{
				articulosContados = Logica.encuentraDarArticulosnoContadosRec(idRece,idEmpresa);
				
			}

			
			List<DataIDDescDescripcion> articulosRecepNoBarra = new ArrayList<>();
			List<DataIDDescDescripcion> articulosBarra = new ArrayList<>();
			
			
			for (DataIDDescDescripcion ar : articulosRecep) 
			{
				
				
				for (DataIDDescDescripcion a : articulosContados) 
				{
					
					
					
					if(a.getDesc()!=null)
					{
						System.out.println("R:"+ar.getDescripcion()+"\t C:"+a.getDesc()+"\t"+a.getId());
						if(ar.getDescripcion().equals(a.getDesc()))
						{
							articulosBarra.add(ar);
						}
					}
				}
				
			}
			
			for (DataIDDescDescripcion ar : articulosRecep) 
			{
				boolean existe = false;
				for (DataIDDescDescripcion ab : articulosBarra) 
				{
					if(ab.getDescripcion().equals(ar.getDescripcion()))
					{
						existe = true;
						break;
					}
				}
				if(!existe)
				{
					articulosRecepNoBarra.add(ar);
				}
			}
			
			
			
			
			
			Hashtable<String, DataArticuloCantBarra> articulosHT = new Hashtable<>();
			
			
			
			for (DataIDDescDescripcion a : articulosContados) 
			{
				DataArticuloCantBarra art = new DataArticuloCantBarra(a.getDescripcion(), a.getId());
				if(a.getDesc()!=null)
				{
					art.setArticulo(a.getDesc());
					
					
					for (DataIDDescDescripcion ar : articulosBarra) 
					{
						if(ar.getDescripcion().equals(a.getDesc()))
						{
							art.setCantidadOrden(ar.getId());
							art.setIdOC(ar.getDesc());
							art.setIdLineaOC(ar.getPorcentaje());
							art.setColor(a.getDescII());
							
							break;
						}
						
						
					}
					if(art.getCantidadOrden()>0)
					{
						articulosHT.put(art.getArticulo(), art);
					}
				}
				else
				{
					List<DataDescDescripcion> artSeleccionable = new ArrayList<>();
					//<option value="${d.id}"><strong> ${d.descripcion}</strong></option>
					for (DataIDDescDescripcion ar : articulosRecepNoBarra) 
					{
						DataDescDescripcion option = new DataDescDescripcion();
						option.setId(ar.getDescripcion());
						if(ar.getId()==art.getCantidadContada())
						{
							option.setDescripcion("<option value="+ar.getDescripcion()+"><strong>"+ar.getDescripcion()+"-"+ar.getDescII()+ " ("+ar.getId()+") unidades </strong></option>");
						}
						else
						{
							option.setDescripcion("<option value="+ar.getDescripcion()+">"+ar.getDescripcion()+"-"+ar.getDescII()+ " ("+ar.getId()+") unidades</option>");
						}
						artSeleccionable.add(option);
					}
					
					art.setPosiblesArt(artSeleccionable);
					
					articulosHT.put(art.getBarra(), art);
					
				}
			}
			
			
			session.setAttribute("articuloContados", new ArrayList<>(articulosHT.values()));
			
			
			return mapping.findForward("ok");
		}
		
	}

}