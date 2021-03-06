package web.tareas;

import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.Tarea;
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

public class _EncuentraAltaTareaIII
  extends Action
{
  @Override
public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    HttpSession session = request.getSession();
    Logica Logica = new Logica();
    try
    {
      String type = request.getParameter("type");
      Tarea tarea = (Tarea)session.getAttribute("tarea");
      Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
      if ((type != null) && (type.equals("R")))
      {
        String id = request.getParameter("idRecepcion");
        tarea.setIdDeposito(Integer.parseInt(uLog.getDeposito()));
        int idMain = Logica.encuentraAltaTarea(tarea, false, uLog.getNumero(),idEmpresa,false);
        Logica.AsignarTareaARecepcion(idMain, Integer.parseInt(id),idEmpresa);
      }
      else if((type != null) && (type.equals("A"))){
    	  String id = request.getParameter("idAlmacen");
    	  tarea.setIdDocumento(Integer.parseInt(id));
          int idMain = Logica.encuentraAltaTarea(tarea, false, uLog.getNumero(),idEmpresa,false);
          Logica.CambioEstadoOrdenAlmacen(0, Integer.parseInt(id),idEmpresa);
      }
      else
      {
        String numVis = (String)session.getAttribute("numVS");
        String idDoc = (String)session.getAttribute("idDoc");
        String ovs = numVis + " " + tarea.getObservacion();
        
        tarea.setObservacion(ovs);
        boolean esParcial = false;
        
        List<DataLineaRepo> repos = (List)session.getAttribute("repoArt");
        
        List<DataLineaRepo> articulosIn = new ArrayList<>();
        int cantidad = 0;
        for (DataLineaRepo d : repos)
        {
          String on = request.getParameter(d.getIdArticulo());
          if ((on != null) && (on.equals("on")))
          {
            cantidad += d.getSolicitada();
            articulosIn.add(d);
          }
          else
          {
            esParcial = true;
          }
        }
        Logica.encuentraUpdateEstadoArticulosPicking(articulosIn, idDoc, 3,null,idEmpresa,false);
        tarea.setCantidadPares(cantidad);
        tarea.setParcial(esParcial);
        int id = Integer.parseInt(idDoc);
        
        tarea.setIdDocumento(id);
        int idMain = Logica.encuentraAltaTarea(tarea, false, uLog.getNumero(),idEmpresa,false);
        Logica.encuentraDistribuirCargaPicking(tarea, articulosIn,idEmpresa,idMain);
      }
    }
    catch (Exception e)
    {
      System.out.println("llegamos al catch");
      e.printStackTrace();
      session.setAttribute("mensaje", e.getMessage());
      return mapping.findForward("ok");
    }
    return mapping.findForward("ok");
  }
}
