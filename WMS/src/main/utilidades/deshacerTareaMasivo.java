package main.utilidades;

import logica.Logica;

public class deshacerTareaMasivo {

	public static void main(String[] args) {
		Logica logica = new Logica();
		
		int [] pickings = {24163, 24559, 24560, 24562, 24563, 24564, 24567, 24570, 24572, 24573, 24574, 24576};
		
		for(int p : pickings)
			logica.deshacerTarea(p, 4);
		
		System.out.println("tareas eliminadas!");
	}

}
