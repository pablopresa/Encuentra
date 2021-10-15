package web.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import beans.DepositoParametros;
import beans.Menu;
import beans.Usuario;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.Tarjeta;

public class CallWSRFID extends Action {

	

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		try 
		{
		String cod = request.getParameter("ItemId");
		
		 main.utilidades.CallWSRFID call = new  main.utilidades.CallWSRFID();
		
		request.setAttribute("EAN", call.darEan(cod));
		
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
	}
	//
}

class Respuesta
{
    private String EAN;

    public String getEAN ()
    {
        return EAN;
    }

    public void setEAN (String EAN)
    {
        this.EAN = EAN;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [EAN = "+EAN+"]";
    }
}
