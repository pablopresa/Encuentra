package web.tareas;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.TipoSector;
import dataTypes.DataArtBarraCant;
import dataTypes.DataClasificacionEntregaArti;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.LogicaBulto;
import logica.Utilidades;
import persistencia._EncuentraPersistir;

@SuppressWarnings("unused")
public class _EncuentraEventoTarea extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		LogicaBulto logicaB = new LogicaBulto();
	
	
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
 
		List<TipoSector> tipos = Logica.encuentraDarTiposSector(idEmpresa);
		//estado=1&
		//main=1&
		//tarea=5&
		//idRepo=6937819
		
		String esta = request.getParameter("estado");
		String mai = request.getParameter("main");
		String tar = request.getParameter("tarea");
		String idR = request.getParameter("idRepo");
		
		try
		{
			int idD = Integer.parseInt(request.getParameter("idDoc"));
			validarUsuarioTarea(Integer.parseInt(tar), idD, uLog.getNumero(), idEmpresa);
			uLog.registrarEventoHilo(session.getId(), " iniciando tarea picking "+ idD, idD ,101);
			session.setAttribute("idPicking", idD);
		}
		catch(Exception e)
		{
			
		}
		String nombreTarea= request.getParameter("nombreTarea");
		
		session.setAttribute("nombreTarea", nombreTarea);
				
		int ultimaLinea = Logica.encuentraDarUltimaLinea(Integer.parseInt(tar),idEmpresa);
		int ultimoIndex =0;
		ultimoIndex= ultimaLinea;
		/*if(ultimaLinea==0)
		{
			ultimoIndex= ultimaLinea;
		}
		else
		{
			ultimoIndex= ultimaLinea+1;
		}*/
	
		
		//pregunto si es una repo
		int intR=Integer.parseInt(idR);
		if(intR==-100)
		{
			// es una clasificacion de entrega
			List<DataClasificacionEntregaArti> artis = Logica.encuentraDarLineasEntrega(Integer.parseInt(mai),idEmpresa);
			session.setAttribute("artisEntrega", artis);
			session.setAttribute("idTarea", Integer.parseInt(tar));
			session.setAttribute("idMain", Integer.parseInt(mai));
			return mapping.findForward("entrga");
		}
		
		
		
		
		DataIDDescripcion cantUPos = Logica.encuentraDarCantEjecutoresPosicionMio(Integer.parseInt(mai), uLog.getNumero(),idEmpresa);
		DataIDDescripcion ultimoItem = Logica.encuentraDarUltimoItemLinea(Integer.parseInt(mai),idEmpresa, uLog.getNumero());
		//id es la can usuarios 
		int cantEjecutores = cantUPos.getId();
		int posicion = Integer.parseInt(cantUPos.getDescripcion());
		//descripcion es la posicion en string
		
		
		
		if(intR>1)
		{
			//es repo
			//la traigo
			List <DataLineaRepo> repos = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				repos = Logica.encuentraDarArtRepos(Integer.parseInt(idR), "",idEmpresa, uLog.getNumero());
				if(repos.size()!=0) {
					break;
				}
			}
			
			
			if(repos.size()==0)
			{
				request.setAttribute("menError","No se pudo acceder a la tarea, intente nuevamente");
				//Logica.encuentraUpdateAvanceTarea(Integer.parseInt(tar),100, -100,idEmpresa);
				//Logica.encuentraAltaEventoTarea(Integer.parseInt(tar), 2, uLog.getNumero(),0,idEmpresa);
				uLog.registrarEventoHilo(session.getId(),"Se intento ingresar a la tarea y no se pudo", Integer.parseInt(idR), 101);
				//int idDeposito = Integer.parseInt(uLog.getDeposito());
				List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
				session.setAttribute("tarMob", tarMob);
				return mapping.findForward("vacio");
			}
			
			session.setAttribute("ListaRepoOriginal", repos);
			
			//pregunto cuanto son
			/*if(cantEjecutores>1)
			{
				int inicioMio;
				
				int cantidadMia = (repos.size()-1)/cantEjecutores;
				inicioMio = ((cantidadMia*posicion)-cantidadMia)+1;
				//por si no da exacto
				int multip = cantidadMia*cantEjecutores;
				
				if(multip!= repos.size() && cantEjecutores==posicion)
				{
					cantidadMia = cantidadMia +(repos.size()-(cantidadMia*cantEjecutores));
				}
				List <DataLineaRepo> retorno = new ArrayList<>();
				
				
				int voyX=0;
				int cantIn=0;
				boolean pone = false;
				for (DataLineaRepo da : repos) 
				{
					voyX ++;
					if(voyX==inicioMio)
					{
						pone = true;
					}
					else if(cantIn==cantidadMia)
					{
						pone = false;
						break;
					}
					
					
					if(pone)
					{
						retorno.add(da);
						cantIn++;
					}
					
				}
				
				repos = retorno;
			}
			*/
	
			DataLineaRepo voy = repos.get(ultimoIndex); 
		/*	int x=0;
			for (int i = ultimoIndex; i < repos.size(); i++) // Recorre todas las lineas faltantes
			{
				voy = repos.get(i); // a todos los repos le ponen lo mismos pickeados
				if(voy.getSolicitada()>voy.getPicked()) // si falta pickear de la linea    1>3
				{
					x=i;
					break;
					
				}
				else{// si no falta pickear de la linea, termino la linea
					ultimoItem.setId(0);
				}
			}
			
			ultimoIndex=x; */
			session.setAttribute("repoArt", repos);
			session.setAttribute("idTarea", Integer.parseInt(tar));
			session.setAttribute("idMain", Integer.parseInt(mai));
			session.setAttribute("voy", voy);
			
			
			List<String> codigos = logicaB.bultosValidosEnOjo(voy.getIdArticulo(), voy.getCubi(), idEmpresa, voy.getContenido()); // DEMORA
			session.setAttribute("codigosRecomendados", codigos);
			
			session.setAttribute("cuantasVoy", ultimoIndex);
			session.setAttribute("unidad", Integer.valueOf(ultimoItem.getId()));
			session.setAttribute("total", (repos.size()));
		}
		else if(intR==0)
		{
			List<DataArtBarraCant>recepciones=Logica.darListaARecepcionar(Integer.parseInt(mai),idEmpresa);
			DataIDDescripcion tipo=Logica.darTipoRecepcion(Integer.parseInt(mai),idEmpresa);
			
			session.setAttribute("idMain", mai);
			session.setAttribute("idTarea", Integer.parseInt(tar));
			session.setAttribute("artBarCantTorecep", recepciones);
			session.setAttribute("tipoTar", tipo);
			Logica.encuentraAltaEventoTarea(Integer.parseInt(tar), Integer.parseInt(esta), uLog.getNumero(),0,idEmpresa);
			return mapping.findForward("rec");
			
		}
		
		
		
		Logica.encuentraAltaEventoTarea(Integer.parseInt(tar), Integer.parseInt(esta), uLog.getNumero(),0,idEmpresa);
		
		
		//guardo el evento sobre la tarea
		
		
		return mapping.findForward("repo");
			
	}
	
	public void validarUsuarioTarea(int idTarea, int idPick, int idUsuarioLogueado, int idEmpresa) {
		_EncuentraPersistir ePer = new _EncuentraPersistir();
		try {
			ePer.persistir("UPDATE tareas " + 
					" SET idUsuario = (if(idUsuario > 0, idUsuario, "+idUsuarioLogueado+"))\r\n" + 
					" WHERE idtarea = "+idTarea+" AND IdEmpresa = "+idEmpresa+";");
			ePer.persistir("UPDATE `reposicion_articulos` SET idUsuario = (if(idUsuario > 0, idUsuario, "+idUsuarioLogueado+")) WHERE `idPicking`="+idPick+" AND idEmpresa ="+idEmpresa+";");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
