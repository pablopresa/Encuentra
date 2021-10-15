package web.util;

import dataTypes.DataDocVisual;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Logica;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class _EncuentraTraeDocsVerif
  extends Action
{
  @Override
public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    HttpSession session = request.getSession();
 Logica Logica = new Logica();
    int idOrigen = Integer.parseInt(request.getParameter("Dori"));
    int idDestino = Integer.parseInt(request.getParameter("Ddes"));
    
    request.setAttribute("origen", Integer.valueOf(idOrigen));
    request.setAttribute("destino", Integer.valueOf(idDestino));
    
    List<DataDocVisual> documentos = Logica.encuentraDarListaDocsVisual(idOrigen, idDestino);
    
    session.setAttribute("documentos", documentos);
    
    String jq = (String)session.getAttribute("jquery");
    if (jq != null) {
      return mapping.findForward("okJQM");
    }
    return mapping.findForward("ok");
  }
}
