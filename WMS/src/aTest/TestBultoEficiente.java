package aTest;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.encuentra.DataLineaRepo;
import logica.LogicaBultoGeneral;
import persistencia._EncuentraConexion;

public class TestBultoEficiente {

	public static void main(String[] args) {
	/*
		String idBultoS = "ZZZ-ZZZ-01";
		String Ojo = "A171";		
		bulto b = new bulto(idBultoS, "bultoPrueba", false, 2, 2, 2, 2.0, true, "2020-12-05", 0, Ojo, 2);

		b.Crear_Bulto(b.getIdEmpresa());
		
		


		bultoContenido bc1 = new bultoContenido("0000510VE0085", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc1);
		bultoContenido bc2 = new bultoContenido("0000624MA0105", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc2);
		bultoContenido bc3 = new bultoContenido("0000H12170120", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc3);
		bultoContenido bc4 = new bultoContenido("0001004033080", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc4);
		bultoContenido bc5 = new bultoContenido("0001100990100", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc5);
		bultoContenido bc6 = new bultoContenido("0001144WP1280", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc6);
		bultoContenido bc7 = new bultoContenido("0001152DK1280", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc7);
		bultoContenido bc8 = new bultoContenido("0001153260270", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc8);
		bultoContenido bc9 = new bultoContenido("0001176SGL090", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc9);
		bultoContenido bc10 = new bultoContenido("8011186645410", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc10);
		bultoContenido bc11 = new bultoContenido("8011153268410", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc11);
		bultoContenido bc12 = new bultoContenido("0011359LB7380", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc12);
		bultoContenido bc13 = new bultoContenido("0011370645380", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc13);
		bultoContenido bc14 = new bultoContenido("006078704M110", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc14);
		bultoContenido bc15 = new bultoContenido("0111250DK1370", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc15);
		bultoContenido bc16 = new bultoContenido("0AM4084P05340", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc16);
		bultoContenido bc17 = new bultoContenido("0J84934CSK070", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc17);
		bultoContenido bc18 = new bultoContenido("0J95316111070", 1, 0, 0);
		b.Agregar_A_Bulto_NO_persist(bc18);


		b.guardarDetalleBulto();
		b.Cerrar_Bulto(0, true);
		
		Logica l = new Logica();
		l.encuentraUpdateOjos(Ojo, idBultoS, 1, false, 0, false, "ADD", 2);
		
		
		*/
		
		
		// TODO Auto-generated method stub
		List<DataLineaRepo> listaPicking = new ArrayList<>();
		
		//SELECT * FROM reposicion_articulos ra WHERE ra.idPicking = 24 
		_EncuentraConexion eCon = new _EncuentraConexion();
		LogicaBultoGeneral lg = new LogicaBultoGeneral();
		String query = "SELECT * FROM reposicion_articulos ra WHERE ra.idPicking = 0 AND ra.IdSincronizacion = 57910;";
		try {
			java.sql.Connection con = eCon.getConnection();
			java.sql.Statement consulta = con.createStatement();
			System.out.println(query);
			ResultSet rs = consulta.executeQuery(query);
			while(rs.next()) {
				DataLineaRepo alr = new DataLineaRepo();
				alr.setPedido(rs.getLong(9)); //ecomm 1
				alr.setIdArticulo(rs.getString(2));
				alr.setSolicitada(rs.getInt(5)); // ecomm 3
				alr.setIdDepOrigen(3); //ecomm 1
				alr.setIdDepDestino(rs.getInt(4));
				listaPicking.add(alr);
			}
			//lg.seleccionModoPicking(0,false, 4, listaPicking);
			//lg.pickingEficiente_DivisionBusquedas(0, listaPicking, 0, 1000351, 2, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
