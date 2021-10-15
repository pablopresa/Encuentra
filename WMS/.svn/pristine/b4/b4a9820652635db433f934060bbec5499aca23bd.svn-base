package web.mantenimiento;

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

import beans.Usuario;
import beans.encuentra.DepositoAdmin;
import beans.encuentra.SKUType;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;
import beans.encuentra.UsoEstanteria;

public class _EncuentraCargarTipos extends Action 
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
		String para = request.getParameter("paraQuien");
		
		if(para.equals("5")){
			List<SKUType> types=Logica.encuentraDarTipoSKU(idEmpresa);
			session.setAttribute("types", types);
		}else
		{
			int depoPrincipal = 9000;
			List<TipoSector> tipos = Logica.encuentraDarTiposSector(idEmpresa);
			List<DepositoAdmin> depositos = Logica.encuentraDarDepositosAdmin(depoPrincipal, idEmpresa);
			List<UsoEstanteria> usos = Logica.encuentraDarUsosEstanterias(idEmpresa);
			
			session.setAttribute("tiposS", tipos);
			session.setAttribute("depositosAS", depositos);
			session.setAttribute("usos", usos);
		}
				
		if(para.equals("2"))
		{
			return mapping.findForward("ok");
		}
		else if(para.equals("3") || para.equals("4"))
		{
			String id = request.getParameter("id");
			List <Sector> estanterias = (List<Sector>) session.getAttribute("sectoresL");
			
			Sector sec = new Sector();
			
			for (Sector sector : estanterias) 
			{
				if (sector.getId()== Integer.parseInt(id))
				{
					sec = sector;
					break;
				}
			}
			
			request.setAttribute("descripcion", sec.getDescripcion());
			request.setAttribute("modulos", sec.getModulos());
			request.setAttribute("estantes", sec.getEstantes());
			request.setAttribute("piso", sec.getPiso());
			request.setAttribute("tipo", sec.getTipo());
			request.setAttribute("sec", sec.getSectorGlb());
			request.setAttribute("dep", sec.getDeposito());
			request.setAttribute("dep", sec.getDeposito());
			request.setAttribute("uso", sec.getUso());
			request.setAttribute("para", para);
			
			if(para.equals("3"))//copiar
			{
				
			}
			else if (para.equals("4"))//Editar
			{
				String cubi = Logica.encuentraPrimerOjo(id, idEmpresa);
				request.setAttribute("cubi", cubi);
				request.setAttribute("id", sec.getId());
			}
		
			return mapping.findForward("ok");
		}else if(para.equals("5"))
		{
			return mapping.findForward("okLlistadoSKU");
		}
		else
		{
			return mapping.findForward("okLlistado");
		}
	
	
	}

}
