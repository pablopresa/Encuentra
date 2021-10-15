package main.utilidades;

import java.util.List;

import beans.encuentra.DepositoAdmin;
import logica.Logica;

public class CrearDepositosStadium {

	public static void main(String[] args) {
		
		
		Logica logica = new Logica();
		
		int idEmpresa = 1;
		
		List<DepositoAdmin> depositos = logica.encuentraDarDepositosAdmin(0, idEmpresa);
		
		/*
		int idDepo = 123;
		DepositoAdmin depo = new DepositoAdmin();
		depo.setId(idDepo);
		depositos.add(depo);
		*/
		
		
		for(DepositoAdmin i: depositos)
		{
			i.setEstanteriaPickingYExpedicion(idEmpresa);
		}

	}

}
