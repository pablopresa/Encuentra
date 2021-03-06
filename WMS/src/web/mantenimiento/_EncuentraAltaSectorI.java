package web.mantenimiento;

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


import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.Ojo;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;


public class _EncuentraAltaSectorI extends Action 
{
	List<Ojo> ojosLineal = new ArrayList<>();
	int area;
	boolean update = false;
	TipoSector tipoSector = new TipoSector();

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		ojosLineal = new ArrayList<>();
		area=0;
		update = false;
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
 		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}

		String [] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J","K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z" };
		Sector sector = new Sector();
		String descripcion = "";
		String modulos = "";
		String idEstanteriaStr = "";
		String estantes = "";
		String pisoStr = "";
		String tipoStr = "";
		String sentido = "";
		String color = "";
		String codUbi = "";
		String deposito = "";
		String usoStr="";
		int cantModulos = 0;
		int idEstanteria = 0;
		int cantEstantes = 0;
		int piso=0;
		int tipo=0;
		int idSector = 0;
		int uso=1;
		
		List <List <Ojo>> estanteriaALTA = new ArrayList<List<Ojo>>();
		List<DataIDDescripcion> listaOjos = new ArrayList<>();
		
		descripcion = request.getParameter("descripcion");
		
		modulos = request.getParameter("modulos");
		String para = request.getParameter("para");
		
		if(para!=null && para.equals("4"))
		{
			idSector = Integer.parseInt(request.getParameter("id"));
			update = true;
			
		}
		idEstanteriaStr = request.getParameter("id");
		estantes = request.getParameter("estantes");
		pisoStr = request.getParameter("piso");
		tipoStr = request.getParameter("tipo");
		sentido = request.getParameter("senti");
		color = request.getParameter("color");
		codUbi = request.getParameter("cubi");
		deposito = request.getParameter("depo");
		usoStr = request.getParameter("uso");
		
		boolean estanteriaSinOjos = false;
		
		if(update)
		{
			//codUbi = Logica.encuentraPrimerOjo(Integer.toString(idSector));
			try{
				listaOjos = Logica.encuentraOjos(idSector, idEmpresa);
				listaOjos.remove(0);
				codUbi = listaOjos.get(0).getDescripcion();
			}
			catch (Exception e) {
				estanteriaSinOjos = true;
				e.printStackTrace();
			}
			
		}
		boolean esDosLetras = false;
		codUbi = codUbi.toUpperCase();
		
		List<TipoSector> tipos = (List<TipoSector>) session.getAttribute("tiposS");
		
		
		for (TipoSector t : tipos) 
		{
			if(t.getIdTipo()==tipo)
			{
				tipoSector = t;
				break;
			}
		}
		
		
		area = tipoSector.getAlto() * tipoSector.getAncho();
		
		request.setAttribute("descripcion", descripcion);
		request.setAttribute("id", idEstanteriaStr);
		request.setAttribute("modulos", modulos);
		request.setAttribute("estantes", estantes);
		request.setAttribute("piso", pisoStr );
		request.setAttribute("tipo", tipoStr );
		request.setAttribute("senti", sentido);
		request.setAttribute("color", color);
		request.setAttribute("cubi", codUbi );
		request.setAttribute("dep", deposito );
		request.setAttribute("uso", usoStr );
		
		String mensaje = "";
		
		/*
		 * VALIDACIONES DE LETRAS
		 
		 */
		
		boolean ok = false;
		
		if(descripcion.isEmpty())
		{
			mensaje = "La descripci?n no puede ser vacia";
			request.setAttribute("menError", mensaje);
			return mapping.findForward("no");
		}
		//El idEstanteria
		try 
		{
			idEstanteria = Integer.parseInt(idEstanteriaStr);
		} 
		catch (Exception e) 
		{
			idEstanteria = 0;
		}
		
		//El Piso
		int depo = 0;
		try
		{
			depo = Integer.parseInt(deposito);
		}
		catch(Exception e)
		{
			mensaje = "Exprese correctamente el n? de deposito";
			request.setAttribute("menError", mensaje);
			return mapping.findForward("no");
		}
			
		try 
		{
			piso = Integer.parseInt(pisoStr);
		} 
		catch (Exception e) 
		{
			mensaje = "Exprese correctamente el n? de piso";
			request.setAttribute("menError", mensaje);
			e.printStackTrace();
			return mapping.findForward("no");
		}
		
		//Estantes
		try 
		{
			cantEstantes = Integer.parseInt(estantes);
		} 
		catch (Exception e) 
		{
			mensaje = "Exprese correctamente el n? estantes";
			request.setAttribute("menError", mensaje);
			e.printStackTrace();
			return mapping.findForward("no");
		}
		
		//los modulos
		try 
		{
			cantModulos = Integer.parseInt(modulos);
		} 
		catch (Exception e) 
		{
			mensaje = "Exprese correctamente la cantidad de modulos";
			request.setAttribute("menError", mensaje);
			e.printStackTrace();
			return mapping.findForward("no");
		}
		
		//tipo
		try 
		{
			tipo = Integer.parseInt(tipoStr);
		} 
		catch (Exception e) 
		{
			mensaje = "Exprese correctamente el tipo";
			request.setAttribute("menError", mensaje);
			e.printStackTrace();
			return mapping.findForward("no");
		}
		
		//uso
		try 
		{
			uso = Integer.parseInt(usoStr);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			mensaje = "Seleccione correctamente el uso";
			request.setAttribute("menError", mensaje);
			return mapping.findForward("no");
		}

		
		
		if(cantEstantes <= 0 || cantModulos <=0)
		{
			mensaje = "los valores no pueden ser 0";
			request.setAttribute("menError", mensaje);
			return mapping.findForward("no");
		}
		
		//evitamos desbpodes
		if(descripcion.length()> 49)
		{
			descripcion = descripcion.substring(0,48);
		}
		
		
		// instanciamos una estanteria
		
		
		///VALIDAMOS COD UBICACION
		
		if(!update || estanteriaSinOjos)
		{

			listaOjos = Logica.darSiguientesCubis(cantEstantes*cantModulos, idEmpresa);
			listaOjos.remove(0);
			if(listaOjos.size()==0){
				mensaje = "No se encuentran codigos de ubicacion disponibles en el sistema";
				request.setAttribute("menError", mensaje);
				return mapping.findForward("no");
			}
			else{
				codUbi=listaOjos.get(0).getDescripcion();
			}
			
		}
	
		if(listaOjos.size()!=cantModulos*cantEstantes)
		{
			//hay que ver si se pueden eliminar los ojos;
			
			
			boolean pri = true;
			for (DataIDDescripcion oj : listaOjos) 
			{
				List<DataOjoArticulo> ojosArticulos = Logica.encuentraDarOjosArticulos(oj.getDescripcion(),"" ,"", "", "",false,false, idEmpresa,depo,null);
				if(!ojosArticulos.isEmpty())
				{
					mensaje = "No se puede editar una estanteria (en tama?o) porque los ojos tienen articulos";
					request.setAttribute("menError", mensaje);
					return mapping.findForward("no");
				}
				
				
				
			}
			//si llega hasta aca es porque no hay nada
			Logica.persistir("delete FROM ojos WHERE idEstanteria = "+idSector+" AND idEmpresa = "+idEmpresa);
			
			listaOjos = Logica.darSiguientesCubis(cantEstantes*cantModulos, idEmpresa);
			listaOjos.remove(0);
			if(listaOjos.size()==0){
				mensaje = "No se encuentran codigos de ubicacion disponibles en el sistema";
				request.setAttribute("menError", mensaje);
				return mapping.findForward("no");
			}
			else{
				codUbi=listaOjos.get(0).getDescripcion();
			}
			
				
			
			
		}
		
		
		sector.setDescripcion(descripcion);
		sector.setColor(color);
		sector.setEstantes(cantEstantes);
		sector.setModulos(cantModulos);
		sector.setTipo(tipo);
		sector.setPiso(piso);
		sector.setDeposito(depo);
		sector.setSentido(sentido);
		sector.setUso(uso);
		sector.setEmpresa(uLog.getIdEmpresa());
		
		if(update)
		{
			sector.setId(idSector);
		}
		else
		{
			sector.setId(idEstanteria);
			idSector = idEstanteria;
		}
		
		int idsectorUpdate = idSector;
		
		
		idSector= Logica.encuentraAltaSector(sector, update, idEmpresa);
		
		if(update){
			idSector =  idsectorUpdate;
		}
		
		System.out.println(idSector);
		
		
		estanteriaALTA = dameEstanteriaAuto(720000, idSector, cantModulos, cantEstantes, sentido, listaOjos);
		 
	/*****************************************************TERMINAMOS Y DEVOLVEMOS*****************************************************************************************************/
			
		session.setAttribute("sector",sector );
		
		List<List<String>> trs = new ArrayList<List<String>>();
		int numModAux;
		boolean sube = false;
		numModAux = cantEstantes;
		List<String> StrEstantes = new ArrayList<>();
		
		
		
		if(sentido.equals("ID"))
		{
			sube = true;
			numModAux = 1;
		}
		
		StrEstantes.add("");
		if(sube)
		{
			for (int i = 1; i <= cantModulos; i++) 
			{
				StrEstantes.add("Modulo "+i);
				
			}
		}
		else
		{
			for (int i = cantModulos; i >= 1; i--) 
			{
				StrEstantes.add("Modulo "+i);
				
			}
			
		}
		
		trs.add(StrEstantes);
		
		
		
		for (int i = 0; i < cantEstantes; i++) 
		{
			List <String> tds = new ArrayList<>();
			
			tds.add("Estante "+ numModAux);
			if(sube)
			{
				numModAux++;
			}
			else
			{
				numModAux--;
			}
			
			for (List<Ojo> l : estanteriaALTA) 
			{
				tds.add(l.get(i).getIdOjo());
			}
			trs.add(tds);
		}
		
		
		//dibujando
		
		for (List<String> list : trs) 
		{
			String linea = "";
			for (String string : list) 
			{
				linea += " "+string;
			}
			System.out.println(linea);
		}
		
			request.setAttribute("trs", trs);
			session.setAttribute("ojosLista", ojosLineal);
			
			session.setAttribute("sector", sector);
			session.setAttribute("secInt", idSector);
			System.out.println(idSector);
			
			
		return mapping.findForward("ok");
	
	}
	
	
	public List <List<Ojo>> dameEstanteriaAuto( int area, int idSector, int modulos, int estantes, String senti,List<DataIDDescripcion> codigosUbi)
	{
		List <List<Ojo>>  estantesRetorno = new ArrayList<List<Ojo>>(); 
		
		
		if(senti.equals("ID"))
		{
			int cont=0;
			for (int i = 1; i <= modulos; i++) 
			{
				List<Ojo> ojos = new ArrayList<>();
				for (int j = 0; j < estantes; j++) 
				{
										
					Ojo o = new Ojo();
					o.setEstante(j+1);
					o.setIdSector(idSector);
					o.setModulo(i);
					o.setIdOjo(codigosUbi.get(cont).getDescripcion());
					o.setArea(area);
					o.setAlto(tipoSector.getAlto());
					o.setAncho(tipoSector.getAncho());
										
					o.setRecorrido(0);
					
					ojos.add(o);
					ojosLineal.add(o);
					cont++;
										
				}
				
				estantesRetorno.add(ojos);
				
			}
		
			
				//estantesL.add(modulosL);
			
		}
		else
			{
				
				Hashtable<Integer, Ojo> ojitos = new Hashtable<>();
				int idCubis=estantes*modulos;
				int cont =codigosUbi.size()-1;
				
				for (int i = 1; i <= modulos; i++) 
				{
					for (int j = 0; j < estantes; j++) 
					{
						
						Ojo o = new Ojo();
						o.setEstante(j+1);
						o.setIdSector(idSector);
						o.setModulo(i);
						o.setIdOjo(codigosUbi.get(cont).getDescripcion());
						o.setArea(area);
						o.setRecorrido(0);
						o.setArea(area);
						o.setAlto(tipoSector.getAlto());
						o.setAncho(tipoSector.getAncho());
						ojitos.put(idCubis, o);
						idCubis--;
						ojosLineal.add(o);
						cont--;
						
					}
						
					
				}
				
				List<Ojo> ojos = new ArrayList<>();
				int vuelta = 0;
				for (int j = 1; j <= (estantes*modulos); j++) 
				{
					vuelta++;
					Ojo o = ojitos.get(j);
					ojos.add(o);
					System.out.println(o.getIdOjo());
					
					if(vuelta==estantes)
					{
						estantesRetorno.add(ojos);
						ojos = new ArrayList<>();
						vuelta = 0;
					}
					
				}
				
					//estantesL.add(modulosL);
				
			}
			
		
		return estantesRetorno;
		
	}
	

	
	public List <List<Ojo>> dameEstanteria ( int area, int idSector, int modulos, int estantes, String senti, String letraI, String letraII, int numI, int nunII, boolean esDosLetras)
	{
		List <List<Ojo>>  estantesRetorno = new ArrayList<List<Ojo>>(); 
		
		
		if(senti.equals("ID"))
		{
			String letraAct = letraI;
			
			
			int numActual = numI;
			
			for (int i = 1; i <= modulos; i++) 
			{
				List<Ojo> ojos = new ArrayList<>();
				for (int j = 0; j < estantes; j++) 
				{
					
					System.out.println(letraAct+numActual);
					
					Ojo o = new Ojo();
					o.setEstante(j+1);
					o.setIdSector(idSector);
					o.setModulo(i);
					o.setIdOjo(letraAct+numActual);
					o.setArea(area);
					o.setAlto(tipoSector.getAlto());
					o.setAncho(tipoSector.getAncho());
					
					o.setRecorrido(0);
					
					ojos.add(o);
					ojosLineal.add(o);
					numActual = numActual + 1;
					
					if(numActual==1000)
					{
						numActual = 1;
						letraAct = letraII;
					}
					
				}
				
				estantesRetorno.add(ojos);
				
			}
		
			
				//estantesL.add(modulosL);
			
		}
		else
			{
				String letraAct = letraI;
				Hashtable<Integer, Ojo> ojitos = new Hashtable<>();
				int idCubis=estantes*modulos;
				int numActual = numI;
				
				for (int i = 1; i <= modulos; i++) 
				{
					for (int j = 0; j < estantes; j++) 
					{
						
						System.out.println(letraAct+numActual);
						
						Ojo o = new Ojo();
						o.setEstante(j+1);
						o.setIdSector(idSector);
						o.setModulo(i);
						o.setIdOjo(letraAct+numActual);
						o.setArea(area);
						o.setRecorrido(0);
						o.setArea(area);
						o.setAlto(tipoSector.getAlto());
						o.setAncho(tipoSector.getAncho());
						ojitos.put(idCubis, o);
						idCubis--;
						ojosLineal.add(o);
						numActual = numActual + 1;
						
						if(numActual==1000)
						{
							numActual = 1;
							letraAct = letraII;
						}
						
					}
						
					
				}
				
				List<Ojo> ojos = new ArrayList<>();
				int vuelta = 0;
				for (int j = 1; j <= (estantes*modulos); j++) 
				{
					vuelta++;
					Ojo o = ojitos.get(j);
					ojos.add(o);
					System.out.println(o.getIdOjo());
					
					if(vuelta==estantes)
					{
						estantesRetorno.add(ojos);
						ojos = new ArrayList<>();
						vuelta = 0;
					}
					
					
					
					
				}
				
					//estantesL.add(modulosL);
				
			}
		
		return estantesRetorno;
		
	}
	
	
	
	
	public Integer dameElNumero (char [] carasteres)
	{
		String numero ="";
		
		for (char c : carasteres) 
		{
			try
			{
				int num = Integer.parseInt(String.valueOf(c));
				numero = numero + num;
				
			}
			catch (Exception e) 
			{
				System.out.println("no es num el " + c);
			}
		}
		
		return Integer.parseInt(numero);
	}

}
