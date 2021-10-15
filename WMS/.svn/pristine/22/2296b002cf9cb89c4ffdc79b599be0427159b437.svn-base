package web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class _EncuentraValidarSession extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{	
		
		try{
			HttpSession session = request.getSession();			
			
			session.setAttribute("html",session);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
			
	}
	

}
