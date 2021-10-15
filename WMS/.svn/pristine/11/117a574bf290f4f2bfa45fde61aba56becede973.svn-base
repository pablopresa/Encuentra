package web.picking;

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
import beans.encuentra.DataLineaRepo;
import dataTypes.DataIDIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class _EncuentraReOrdenarRecorrido extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		try {
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}			
			
			int idTarea = (Integer) session.getAttribute("idTarea");
			int idMain = (Integer) session.getAttribute("idMain");
			String nombreTarea = (String) session.getAttribute("nombreTarea");
			List <DataLineaRepo> repos = (List<DataLineaRepo>) session.getAttribute("repoArt");
			
			DataLineaRepo voy = (DataLineaRepo) session.getAttribute("voy");
			int cauntasVan = (Integer) session.getAttribute("cuantasVoy");
			int unidad = (Integer) session.getAttribute("unidad");
			int idPicking = (Integer) session.getAttribute("idPicking");
			Hashtable<String, String> excluirOjos =  (Hashtable<String, String>) session.getAttribute("excluirOjos");
			
			List<DataLineaRepo> reOrdenar = new ArrayList<>();
			List<DataLineaRepo> ArtAReservar;
			
			for(int i=cauntasVan; i<repos.size(); i++){
				if(repos.get(i).getIdArticulo().equals(voy.getIdArticulo()) && 
						repos.get(i).getCubi().equals(voy.getCubi()) &&  
						repos.get(i).getPedido() == voy.getPedido() &&  
						repos.get(i).getIdDepDestino() == voy.getIdDepDestino() ){
					
					int cantidadLinea = repos.get(i).getSolicitada()-unidad;
					repos.get(i).setSolicitada(cantidadLinea);
				}
				reOrdenar.add(repos.get(i));
			}
			
			Logica.resetReservasPicking(reOrdenar, idPicking,idEmpresa);
			
			//REAGRUPO TODOS LOS ARTICULOS
			System.out.println("Preparo para armar nuevo recorrido");
			int cantidad = 0; 
			Hashtable<String, DataLineaRepo> articulos = new Hashtable<>();
			for(DataLineaRepo ro:reOrdenar){
				if(ro.getIdArticulo().equals(voy.getIdArticulo())){
					int cantidadLinea = ro.getSolicitada();//-unidad;
					ro.setSolicitada(cantidadLinea);
				}
				if(articulos.get(ro.getIdArticulo()+"-"+ro.getPedido())==null){
					articulos.put(ro.getIdArticulo()+"-"+ro.getPedido(), ro);
				}
				else{
					cantidad = articulos.get(ro.getIdArticulo()+"-"+ro.getPedido()).getSolicitada() + ro.getSolicitada();
					articulos.get(ro.getIdArticulo()+"-"+ro.getPedido()).setSolicitada(cantidad);
				}
			}
			ArtAReservar = new ArrayList<>(articulos.values());
			
			System.out.println("");
			String excluirArt = voy.getIdArticulo();
			String excluirOjo = voy.getCubi();
			if(excluirOjos==null){
				excluirOjos = new Hashtable<>();
			}
						
			if(excluirOjos.get(excluirArt)==null){
				excluirOjos.put(excluirArt,"'"+excluirOjo+"'");
			}
			else{
				String oj =excluirOjos.get(excluirArt)+",'"+excluirOjo+"'";
				excluirOjos.put(excluirArt,oj);
			}
			
			Hashtable<String, List<DataIDIDDescripcion>> reservas = Logica.encuentraReservaDaArtRepos(ArtAReservar,excluirOjos,idEmpresa);
			Logica.encuentraUpdateEstadoArticulosPicking(reOrdenar, idPicking+"", 3,reservas,idEmpresa,false);
			
			session.setAttribute("excluirOjos", excluirOjos);
			repos = Logica.encuentraDarArtRepos(idPicking, "",idEmpresa, uLog.getNumero());
			session.setAttribute("repoArt", repos);			
			session.setAttribute("total", (repos.size()));
			session.setAttribute("unidad", 0);
			
			if(repos.get(cauntasVan).getIdArticulo().equals(voy.getIdArticulo()) && 
					repos.get(cauntasVan).getCubi().equals(voy.getCubi()) &&  
					repos.get(cauntasVan).getPedido() == voy.getPedido() &&  
					repos.get(cauntasVan).getIdDepDestino() == voy.getIdDepDestino() && repos.get(cauntasVan).getSolicitada()==unidad){
				
				cauntasVan++;
				session.setAttribute("voy", repos.get(cauntasVan));				
				session.setAttribute("cuantasVoy", cauntasVan);		
			}
			else{
				session.setAttribute("voy", repos.get(cauntasVan));				
				session.setAttribute("cuantasVoy", cauntasVan);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return mapping.findForward("ok");
	}
	
}
