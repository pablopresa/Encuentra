package web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class _EncuentraMenuMob extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String seccion = request.getParameter("sec");
		
		if(seccion!=null)
			request.setAttribute("sec", seccion);
		else
			seccion = "M";
			request.setAttribute("sec", seccion);
		
		return mapping.findForward("ok");

	}

}
