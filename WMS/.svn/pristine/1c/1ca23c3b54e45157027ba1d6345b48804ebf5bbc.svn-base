package web.picking;

import java.util.ArrayList;
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
import beans.encuentra.DataPicking;

public class _EncuentraDescartarLineasPicking extends Action{

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
		
		
		
		List<DataPicking> articulosIn = new ArrayList<>();
		List<DataPicking> pickings = (List<DataPicking>) session.getAttribute("pickings");
		
		int idpicking = 0;
		
		for (DataPicking p : pickings) 
		{
			if(p.getSol()!=p.getPick())
			{
				String param = request.getParameter("CHK"+p.getArticulo()+"-"+p.getDestino().getId());
				if(param!=null && param.equals("on"))
				{
					
					articulosIn.add(p);
					idpicking = p.getIdPicking();
				}
			}
		}
		
		
		
		
		int descarta = Integer.parseInt(request.getParameter("desca"));
		if(descarta!=0)
		{
			
			/*son para descartar*/
			Logica.encuentraDescartarLineasPicking(articulosIn, 4, idpicking,idEmpresa);
		}
		else
		{
			/*son para reprocesar*/
			Logica.encuentraDescartarLineasPicking(articulosIn, 1, 0,idEmpresa);
		}
		
		
		String destino = "verifica";
		int idPick = idpicking;
		pickings = Logica.encuentraDarPicking(idPick,idEmpresa);
		int totalSol = 0;
		int totalEnc = 0;
		int totalVer = 0;
		for (DataPicking p : pickings) 
		{
		
			totalSol+=p.getSol();
			totalEnc+=p.getPick();
			totalVer+=p.getVerificada();
			
		}
	
		request.setAttribute("totSol",totalSol);
		request.setAttribute("totEnc",totalEnc);
		request.setAttribute("totVer",totalVer);
		
		session.setAttribute("pickings", pickings);
		return mapping.findForward("ok");
		
	
	}
	
	
	
	
	
}








































