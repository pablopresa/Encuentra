package integraciones.erp.odoo.laIsla;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import persistencia._EncuentraConexionAPI2;

public class IDSession_LaIsla_AB {
	static LaIsla li = new LaIsla();

	public static void main(String[] args) {
		int idSession = -1;
		String fecha = "";
		try {
			fecha = _EncuentraConexionAPI2.darFechatokenApi("SELECT obtenido from apitoken_cliente where idCliente = 99 and idEmpresa = 6;");
			
			if(fecha != null) {
				String[] fechaHora = fecha.split(" ");
				String[] fechas = fechaHora[0].split("-");
				LocalDate fechaCreado = LocalDate.of(Integer.parseInt(fechas[0]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[2]));
				String[] horas = fechaHora[1].split(":");
				LocalTime horaCreado = LocalTime.of(Integer.parseInt(horas[0]), Integer.parseInt(horas[1]));
				LocalDateTime seCreoToken = LocalDateTime.of(fechaCreado, horaCreado);
				
				int diaDespues = Integer.parseInt(fechas[2]) + 1;
				LocalDate diaVencimiento = LocalDate.of(Integer.parseInt(fechas[0]), Integer.parseInt(fechas[1]), diaDespues);
				LocalTime horaVencimiento = LocalTime.of(1, 30);
				LocalDateTime vencimiento = LocalDateTime.of(diaVencimiento, horaVencimiento);
				
				if(ChronoUnit.MINUTES.between(vencimiento, LocalDateTime.now()) > 0) {
					System.out.println("Cerre session");
					idSession = Integer.parseInt(_EncuentraConexionAPI2.dartokenApi("SELECT token from apitoken_cliente where idCliente = 99 and idEmpresa = 6;"));
					li.cerrarSession(idSession);
					_EncuentraConexionAPI2.persistir("DELETE FROM apitoken_cliente WHERE idCliente = 99 and idEmpresa = 6;");
				} else {
					System.out.println("Session valida");
				}
			} else {
				System.out.println("Abri session");
				idSession = li.abrirSessionVentas();
				_EncuentraConexionAPI2.persistir("INSERT INTO apitoken_cliente (idCliente, token, idEmpresa) VALUES (99, "+idSession+", 6);");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
