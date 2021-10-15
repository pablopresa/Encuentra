package web.dashboards;

import java.text.DecimalFormat;
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

import dataTypes.DataIDDescripcion;
import dataTypes.DataIndicador;
import dataTypes.DataIndicadorPicking;
import beans.Fecha;
import beans.Usuario;

public class DetalleDash extends Action {

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		List<DataIDDescripcion> usuarios= (List<DataIDDescripcion>) session.getAttribute("usuarios");
		List<DataIDDescripcion> marcas= (List<DataIDDescripcion>) session.getAttribute("marcas");
		List<DataIDDescripcion> clases = (List<DataIDDescripcion>) session.getAttribute("clases");
		List<DataIDDescripcion> depositosD = (List<DataIDDescripcion>) session.getAttribute("depositosD");
		List<DataIDDescripcion> modulos = (List<DataIDDescripcion>) session.getAttribute("modulos");
		
	
		
		List <Integer> usuariosIn = new ArrayList<>();
		List <Integer> marcasIn = new ArrayList<>();
		List <Integer> clasesIn = new ArrayList<>();
		List <Integer> depositosIn = new ArrayList<>();
		List <Integer> modulosIn = new ArrayList<>();
		
		DecimalFormat d1 = new DecimalFormat("##.##");
		Hashtable<String, String> tablasActivas = (Hashtable<String, String>) session.getAttribute("tablasAct");
		if(tablasActivas==null)
		{
			tablasActivas = new Hashtable<>();
		}
		
		
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		String tablaDatos = "";
		String tablaDatosII = "";	
		String tablaDatosIII = "";
		if(idEmpresa == 2)
		{
			tablaDatos = "z_forus";
			tablaDatosII = "z_forus_picking_cab";
			tablaDatosIII = "z_forus_picking_det";
		}
		
		
		
		
		
		String talbaSel = request.getParameter("tabla");
		
		
		if(depositosD!=null)
		{
			for (DataIDDescripcion t : marcas) 
			{
				
				if(t.getIdB()==1)
				{
					
					marcasIn.add(t.getId());
				}
				
				
			}
			
			
			for (DataIDDescripcion t : clases) 
			{
				if(t.getIdB()==1)
				{
					clasesIn.add(t.getId());
				}
			}
			
			
			
			for (DataIDDescripcion t : usuarios) 
			{
				if(t.getIdB()==1)
				{
					usuariosIn.add(t.getId());
				}
			}
			
			
			for (DataIDDescripcion t : depositosD) 
			{
				if(t.getIdB()==1)
				{
					depositosIn.add(t.getId());
				}
			}
			
			for (DataIDDescripcion t : modulos) 
			{
				if(t.getIdB()==1)
				{
					modulosIn.add(t.getId());
				}
			}
		}
		
		
		
		
		
		/***********************************************************************************************/
		
	
		
		String fechas = request.getParameter("fechas");
		
		Fecha fechaF=null;
		Fecha fechaI=null;
		Fecha fechaFAP = null;
		Fecha fechaIAP = null;
		
		if(fechas==null)
		{
			fechaF = new Fecha();
			fechaI = new Fecha();
		}
		else
		{
			try
			{
				String [] rango = fechas.split("-");
				
				String i = rango[0];
				String f = rango[1];
				
				String[] ddmmaaaaI = i.split("/");
				String[] ddmmaaaaF = f.split("/");
				
				
				fechaI = new Fecha(Integer.parseInt(ddmmaaaaI[0]),Integer.parseInt(ddmmaaaaI[1]),Integer.parseInt(ddmmaaaaI[2]),0,0);
				fechaF = new Fecha(Integer.parseInt(ddmmaaaaF[0]),Integer.parseInt(ddmmaaaaF[1]),Integer.parseInt(ddmmaaaaF[2]),0,0);
			}
			catch (Exception e) 
			{
				fechaF = new Fecha();
				fechaI = new Fecha(-29, 0, 0);
			}
			
			
		}
		
		
		
		
		
		
		request.setAttribute("fini", fechaI.darFechaDia_Mes_Anio_Barra());
		request.setAttribute("ffin", fechaF.darFechaDia_Mes_Anio_Barra());
		
		if(fechaI.darFechaAnio_Mes_Dia().equals(fechaF.darFechaAnio_Mes_Dia()))
		{
			fechaFAP = new Fecha(fechaF.getDia(),fechaF.getMes(),fechaF.getAnio(),0,0,-52);
			fechaIAP = new Fecha(fechaI.getDia(),fechaI.getMes(),fechaI.getAnio(),0,0,-52);
		}
		else
		{
			fechaFAP = new Fecha(fechaF.getDia(),fechaF.getMes(),fechaF.getAnio(),0,0,0,-1);
			fechaIAP = new Fecha(fechaI.getDia(),fechaI.getMes(),fechaI.getAnio(),0,0,0,-1);
		}
		
		
		
		DecimalFormat f = new DecimalFormat("##.00");
		
		Hashtable<String, String> fechasAP = new Hashtable<>();
		fechasAP.put("fechaIAP",fechaIAP.darFechaAnioMesDia());
		fechasAP.put("fechaFAP",fechaFAP.darFechaAnioMesDia());
		Procesador p = new Procesador();
		
		if(talbaSel.equals("UxTienda"))
		{
			List <DataIndicador> tablaDepos = p.darIndicador(fechaI.darFechaAnioMesDia(),fechaF.darFechaAnioMesDia(),11,usuariosIn,marcasIn,clasesIn,depositosIn,modulosIn);
			
			DataIndicador totalDepo = darTotales(tablaDepos, false, false, false, false, false);
			
			Double precision = ((totalDepo.getIdInt2()*100.00)/totalDepo.getIdInt3());
			
			Double precisionF = Double.parseDouble(d1.format(precision).replace(",", "."));  
			
			totalDepo.setValorDouble1(precisionF);
			session.setAttribute("ttlTblDepo", totalDepo);
			
			tablasActivas.put("tablaDepos", "tablaDepos");
			
			session.setAttribute("tablaDepos",tablaDepos);
		}
		else if (talbaSel.equals("UxMarca"))
		{
			List <DataIndicador> tablaMarcas = p.darIndicador(fechaI.darFechaAnioMesDia(),fechaF.darFechaAnioMesDia(),13,usuariosIn,marcasIn,clasesIn,depositosIn,modulosIn);
			
			DataIndicador totalMarcas = darTotales(tablaMarcas, false, false, false, false, false);
			
			Double precision = ((totalMarcas.getIdInt2()*100.00)/totalMarcas.getIdInt3());
			
			Double precisionF = Double.parseDouble(d1.format(precision).replace(",", "."));  
			
			totalMarcas.setValorDouble1(precisionF);
			session.setAttribute("ttlTblMarca", totalMarcas);
			
			session.setAttribute("tablaMarcas",tablaMarcas);
			tablasActivas.put("tablaMarcas", "tablaMarcas");
			
		}
		else if (talbaSel.equals("UxClase"))
		{
			List <DataIndicador> tablaClases = p.darIndicador(fechaI.darFechaAnioMesDia(),fechaF.darFechaAnioMesDia(),14,usuariosIn,marcasIn,clasesIn,depositosIn,modulosIn);
			
			DataIndicador totalClases = darTotales(tablaClases, false, false, false, false, false);
			
			Double precision = ((totalClases.getIdInt2()*100.00)/totalClases.getIdInt3());
			
			Double precisionF = Double.parseDouble(d1.format(precision).replace(",", "."));  
			
			totalClases.setValorDouble1(precisionF);
			session.setAttribute("ttlTblClase", totalClases);
			
			session.setAttribute("tablaClases",tablaClases);
			tablasActivas.put("tablaClases", "tablaClases");
			
		}
		if(talbaSel.equals("UxUsuarios"))
		{
			List <DataIndicador> tablaUsuarios = p.darIndicador(fechaI.darFechaAnioMesDia(),fechaF.darFechaAnioMesDia(),12,usuariosIn,marcasIn,clasesIn,depositosIn,modulosIn);
			
			DataIndicador totalUsuario = darTotales(tablaUsuarios, false, false, false, false, false);
			
			Double precision = ((totalUsuario.getIdInt2()*100.00)/totalUsuario.getIdInt3());
			
			Double precisionF = Double.parseDouble(d1.format(precision).replace(",", "."));  
			
			totalUsuario.setValorDouble1(precisionF);
			session.setAttribute("ttlTblUsuarios", totalUsuario);
			
			tablasActivas.put("tablaUsuarios", "tablaUsuarios");
			
			session.setAttribute("tablaUsuarios",tablaUsuarios);
		}
		
		else if (talbaSel.equals("UxAll"))
		{
			List <DataIndicadorPicking> tablaDatosPicking = p.darIndicadorPicking(fechaI.darFechaAnioMesDia()+"",fechaF.darFechaAnioMesDia(),usuariosIn,marcasIn,clasesIn,depositosIn, tablaDatosII, tablaDatosIII);
			
			//DataIndicador totalDepo = darTotales(tablaDepos, false, false, false, false, false);
			
			//Double precision = ((totalDepo.getIdInt2()*100.00)/totalDepo.getIdInt3());
			
			//Double precisionF = Double.parseDouble(d1.format(precision).replace(",", "."));  
			
			//totalDepo.setValorDouble1(precisionF);
			//session.setAttribute("ttlTblDepo", totalDepo);
			
			tablasActivas.put("tablaDatosPicking", "tablaDatosPicking");
			
			session.setAttribute("tablaDatosPicking",tablaDatosPicking);
			
			
			
		}
		
		
		
		
	
		
			
		
		session.setAttribute("usuarios",usuarios);
		session.setAttribute("marcas", marcas);
		session.setAttribute("clases", clases);
		session.setAttribute("depositosD", depositosD);
		session.setAttribute("modulos", modulos);
		
		
		session.setAttribute("tablasAct",tablasActivas);
		
		
		return mapping.findForward("ok");
	}
		
	public static String withSuffix(Double count) 
	{
	    if (count < 1000) return "" + count;
	    int exp = (int) (Math.log(count) / Math.log(1000));
	    return String.format("%.1f %c",count / Math.pow(1000, exp),"kMGTPE".charAt(exp-1));
	}
	
	public static DataIndicador darTotales(List<DataIndicador> lista, boolean promI1,boolean promI2,boolean promI3,boolean promD1,boolean promD2)
	{
		DataIndicador retorno = new DataIndicador();
		
		int total=0;
		
		
		int sumI1=0,sumI2=0,sumI3=0;
		Double sumD1=0.0,sumD2=0.0;
		
		
		for (DataIndicador d : lista) 
		{
			sumI1+=d.getIdInt1();
			sumI2+=d.getIdInt2();
			sumI3+=d.getIdInt3();
			sumD1+=d.getValorDouble1();
			sumD2+=d.getValorDouble2();
			
			total++;
		}
		
		if(promI1)
		{
			retorno.setIdInt1(sumI1/total);
		}
		else
		{
			retorno.setIdInt1(sumI1);
		}
		if(promI2)
		{
			retorno.setIdInt2(sumI2/total);
		}
		else
		{
			retorno.setIdInt2(sumI2);
		}
		if(promI3)
		{
			retorno.setIdInt3(sumI3/total);
		}
		else
		{
			retorno.setIdInt3(sumI3);
		}
		
		
		
		if(promD1)
		{
			retorno.setValorDouble1(sumD1/total);
		}
		else
		{
			retorno.setValorDouble1(sumD1);
		}
		if(promD2)
		{
			retorno.setValorDouble2(sumD2/total);
		}
		else
		{
			retorno.setValorDouble2(sumD2);
		}
		
		retorno.setIdString1("TOTAL");
		retorno.setIdString2("TOTAL");
		return retorno;
		
	}
	
	
}
