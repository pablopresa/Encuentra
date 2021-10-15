package web.picking;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Escribir;
import logica.LeerFicheroTexto;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataLineaRepo;

public class _EncuentraPasaNContradoTarea extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
	
		String art = request.getParameter("art");
		int cauntasVan = (Integer) session.getAttribute("cuantasVoy");
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		//art=146.30273007036.0&cantidad=1
		int idTarea = (Integer) session.getAttribute("idTarea");
		int idMain = (Integer) session.getAttribute("idMain");
		String nombreTarea = (String) session.getAttribute("nombreTarea");
		
		String cant = request.getParameter("cantidad");
		
		List <DataLineaRepo> noNcontrados = (List<DataLineaRepo>) session.getAttribute("noNcontrados");
		
		
		//noEncontrado
		//cuantasVoy
		//unidad
		
		int porcentaje = 0;
			
		try//escribimos el fichero de la repo
		{
			String path = LeerFicheroTexto.LeerProperties("PathRepos.txt").get(0);
						
			Formatter fmt = new Formatter();
						
						
						
			String texto = "00000,"+art+","+fmt.format("%06d",Integer.parseInt(cant));
						
			Escribir.escribir(path, uLog.getNumero()+"-"+nombreTarea, texto);
						
						
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
			
		List <DataLineaRepo> noNcontradosR = new ArrayList<>();
			
			for (DataLineaRepo d : noNcontrados) 
			{
				if(!d.getIdArticulo().equals(art))
				{
					noNcontradosR.add(d);
				}
				else
				{
					Logica.encuentraAltaNoncontrados(idTarea, d, 1,cauntasVan+1,idEmpresa);
					session.setAttribute("cuantasVoy", cauntasVan+1);
				}
			}
			
			
			Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(),0, idEmpresa);
			//int idDeposito = Integer.parseInt(uLog.getDeposito());
			List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
			session.setAttribute("tarMob", tarMob);
			
			
			if(noNcontradosR.isEmpty())
			{
				return mapping.findForward("fin");
			}
			else
			{
				session.setAttribute("noNcontrados", noNcontradosR);
				return mapping.findForward("nofin");
			}
			
			
		
			
	}

}
