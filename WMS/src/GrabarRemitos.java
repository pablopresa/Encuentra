import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import dataTypes.DataIDDescripcion;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;

public class GrabarRemitos {
	public static void main(String[] args) {
		Logica l = new Logica();
		Utilidades util = new Utilidades();
		int idEmpresa = 2;
		Usuario uLog = l.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");
		int equipo = uLog.getEquipo_trabajo();
		int idDepoCentral = util.darParametroEmpresaINT(idEmpresa,4);
		
		Hashtable<String, bulto> lista = l.BultosAbiertos2(idEmpresa);
		
		Enumeration<String> elementos = lista.keys();
		while (elementos.hasMoreElements()) {
			String idbulto=elementos.nextElement();
			try {				
			
				bulto b = null;
				
				b = lista.get(idbulto);
	
				boolean controlCantidades = l.VerifCantidadesPicking(new ArrayList<>(b.getContenido().values()), b.getDestino(), idEmpresa);
				
				if(!controlCantidades) {
					//REMUEVO LA CAJA DE LA COLECCION
					System.out.println("no pude grabar remito por diferencia de cantidades");
				}
				
				Hashtable<String, DataIDDescripcion> articulosHT = new Hashtable<>();
				List<DataIDDescripcion> articulos = new ArrayList<>();
				
				Enumeration<String> elements = b.getContenido().keys();
				
				bultoContenido bc = null;
				String key = "";
				int unidades = 0;
				while(elements.hasMoreElements()){
					key=elements.nextElement();
					bc = b.getContenido().get(key);
					unidades += bc.getCantidad();
					
					if(articulosHT.get(bc.getIdArticulo())==null){
						articulosHT.put(bc.getIdArticulo(), new DataIDDescripcion(bc.getCantidad(),bc.getIdArticulo()));
					}
					else{
						int cant = articulosHT.get(bc.getIdArticulo()).getId() + bc.getCantidad();
						articulosHT.get(bc.getIdArticulo()).setId(cant);
					}
				}
				
				int destino = 0;
				try {
					 destino = Integer.parseInt(b.getDestino());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//MOVIMIENTO EN VISUAL
				List<DataIDDescripcion> listaRemitos = new ArrayList<>();
				DataIDDescripcion remito = new DataIDDescripcion();
				int tipoComanda = 2;
				String obsTicket = "Movimiento hacia tienda "+destino;
				if(l.tipoDestino(destino, idEmpresa) == 100) {	//VENTA MAYORISTA
					/*tipoComanda = 3;
					obsTicket = "Movimiento hacia cliente "+idDestino;
					Hashtable<Integer, List<DataIDDescripcion>> solicitudesMayoristas = Logica.SolicitudesMayoristas(b.getIdBulto(),destino,
							articulosHT,idEmpresa, false);
					Enumeration<Integer> elementsSolicitudes = solicitudesMayoristas.keys();
					while (elementsSolicitudes.hasMoreElements()) {
						List<DataIDDescripcion> artsSolicitud = new ArrayList<>();
						int solicitud = elementsSolicitudes.nextElement();
						
						//articulos = new ArrayList<>(articulosHT.values());
						artsSolicitud = solicitudesMayoristas.get(solicitud);
						Collections.sort(artsSolicitud);
						artsSolicitud = Logica.DescripcionArticulos(artsSolicitud,idEmpresa);
						
						remito = util.remitir(idDepoCentral, destino, uLog.getNumero(), artsSolicitud, new Long("0"), solicitud, 0, 0, 
								uLog.getNombre()+" "+uLog.getApellido(), "Transferencia realizada por "+uLog.getNumero()+" desde encuentra", 
								idEmpresa, b, true);
						listaRemitos.add(remito);
						articulos.addAll(artsSolicitud);
					}*/
				}
				else {
					articulos = new ArrayList<>(articulosHT.values());
					Collections.sort(articulos);
					articulos = l.DescripcionArticulos(articulos,idEmpresa);
					
					remito = util.remitir(idDepoCentral, destino, uLog.getNumero(), articulos, new Long("0"), 0, 0, 0, 
							uLog.getNombre()+" "+uLog.getApellido(), "Transferencia realizada por "+uLog.getNumero()+" desde encuentra", 
							idEmpresa, b, false);
					listaRemitos.add(remito);
				}
				
				//SETEO NUMERO DE REMITO
				String msjRemito = "";
				String idRemitos = "";
				for(DataIDDescripcion r: listaRemitos) {
					b.Cargar_Remito(r.getId()+"", 1, unidades);
					idRemitos += r.getId()+",";
					if(r.getId()==0) {
						msjRemito += r.getDescripcion()+" <br/>";
					}
				}
				
				try {
					idRemitos = idRemitos.substring(0,idRemitos.length()-1);
				} catch (Exception e) {idRemitos = "";}
				//REMITO DE ENCUENTRA
				ImpresionesPDF.imprimirTicketMovStock(idDepoCentral, destino, uLog.getNick(), obsTicket, 
						articulos, idRemitos, tipoComanda, uLog.getIdEquipo(),idEmpresa, 1);
				
				System.out.println("REMITO " + idRemitos + " - DESTINO "+destino);
			} catch (Exception e) {
				System.out.println("FALLO REMITIENDO BULTO" + idbulto);
				e.printStackTrace();
			}
		}
			
	}
}
