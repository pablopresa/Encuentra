package aTest;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import beans.BultosACrear;
import beans.bulto;
import beans.bultoContenido;
import logica.Logica;

public class mainPPGbultos {

	public mainPPGbultos() {
	}

	public static void main(String[] args) {
		Logica logica = new Logica();
		
		List<BultosACrear> bultosBD = logica.bultosAIngresar();
		
		
		List<bulto> bultosList = new LinkedList<bulto>(); 
		
		int indice = 1;
		String bultoAux = bultosBD.get(0).getIdBulto();
		//Creo bultos
		for(BultosACrear b: bultosBD)
		{
			if(!bultoAux.equals(b.getIdBulto()))
			{
				indice=1;
				bultoAux=b.getIdBulto();
			}
			
			String idBulto = b.getIdBulto()+"_"+indice;
			bulto bAux = new bulto(idBulto, "bulto inventario_1",true,0,0,0,0.0,false,"",1,"",5);
			bAux.Crear_Bulto(5);
			List<bultoContenido> contenidoList = new ArrayList<>();
			bultoContenido bContent = new bultoContenido(b.getIdArticulo(), b.getCantidad(), -200, 1) ;
			bAux.Agregar_A_Bulto(bContent);
			contenidoList.add(bContent);
			bultosList.add(bAux);
			//logica.persistir("insert into aux4 values ('"+idBulto+"','"+b.getUbicacion()+"')");
			indice++;
		}
		
		logica.persistir("UPDATE bulto SET cerrado = 1 WHERE descripcion = 'bulto inventario_1' AND idEmpresa = 5 AND fecha > '2021-04-22' AND cerrado = 0;");
		
		//bulto.crearBultos(-100, bultosList, 5);
		
		
		
			
		
	}

}
