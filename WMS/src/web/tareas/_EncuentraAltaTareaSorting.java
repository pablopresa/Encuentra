package web.tareas;

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

import beans.Usuario;
import beans.encuentra.Ojo;
import beans.encuentra.Tarea;
import dataTypes.DataIDDescripcion;


public class _EncuentraAltaTareaSorting extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession(true);
		Logica Logica = new Logica();
		
		Tarea tarea = (Tarea)session.getAttribute("tarea");
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		tarea.setIdDocumento(0);
		tarea.setIdDeposito(Integer.parseInt(uLog.getDeposito()));
		int idTarea = Logica.encuentraAltaTarea(tarea, false, uLog.getNumero(),idEmpresa,false);
		
		List<DataIDDescripcion> listaSort = (List<DataIDDescripcion>) session.getAttribute("lectura");
		Hashtable<String, String> destinos = new Hashtable<>();
        
		
		for (DataIDDescripcion l : listaSort) 
        {
        	destinos.put(l.getDescripcionB(),l.getDescripcionB());
        }
         
        List<String> destinosL = new ArrayList<>(destinos.values());
		
	    Hashtable<String, String> ojosDeDestino = new Hashtable<>();
	    
	    List<Ojo> ojosSorter = Logica.darOjosEstanteria(destinosL.size(), 107,idEmpresa) ;//new ArrayList<>();

	    
	    int pos = 0;
	    for (String d : destinosL) // la lista de destinos tiene el mismo largo que la de ojossorter
	    {
			ojosDeDestino.put(d,ojosSorter.get(pos).getIdOjo());
	    	pos++;
		}
		
	    //List<String> persistires = new ArrayList<>();
	    List<StringBuilder> persistires = new ArrayList<>();
	    StringBuilder sb = new StringBuilder();
	    
	    
	    for (DataIDDescripcion linea : listaSort) 
	    {
			sb.append
	    	("INSERT INTO `sorter` (`IdTarea`, `IdArticulo`, `Cantidad`, `CantidadProcesada`, `PosSorter`, `NombreDestino`, `idEmpresa`) VALUES "
	    			+ "('"+idTarea+"', "
	    			+ " '"+linea.getDescripcion()+"',  "
	    			+ "'"+linea.getId()+"',  "
	    			+ "'0', "
	    			+ " '"+ojosDeDestino.get(linea.getDescripcionB())+"', " //saco del hasht el id de Ojo
	    			+ " '"+linea.getDescripcionB()+"' "
	    			+ ","+idEmpresa+") on duplicate key update Cantidad=Cantidad+"+linea.getId()+";");
	    	
		}
	    
	    persistires.add(sb);
	    Logica.persistirLote(persistires);
	    /*for (String string : persistires) 
	    {
			
			Logica.persistir(string);
		}*/
		
	    uLog.registrarEventoHilo(session.getId(), "Asignando picking  "+ idTarea, idTarea,101);
	    
	    
		return mapping.findForward("ok");
	}
		
}
