package beans;

import java.util.ArrayList;
import java.util.List;

import beans.datatypes.DataIDDescripcion;
import beans.encuentra.Cliente;
import integraciones.erp.visualStore.elrey.central.ClienteWSELREY;
import integraciones.erp.visualStore.elrey.ecommerce.ClienteWS;

public class TestVsELREY {
	public static void main(String[] args) {
		Cliente c = new Cliente();
		
		c.setDocumentoNro("45628287");
		c.setDocumentoTipo("CI");
		c.setNombre("Mathias");
		c.setApellido("Paz");
		c.setCalle("18 de julio");
		c.setNroPuerta("2018");
		c.setCiudad("Montevideo");
		c.setDepartamento("Montevideo");
		c.setCp("0");
		c.setTelefono("27110241");
		c.setEmail("");
		
		ClienteWSELREY er = new ClienteWSELREY();
		//er.AltaCliente(c);
		List<DataIDDescripcion> lista = new ArrayList<>();
		lista.add(new DataIDDescripcion(1,"INX28212"));
		//er.GrabarTransferencia(1, 1200, 42, lista, "***PRUEBA*** devolver a deposito 1");
		
		ClienteWS wsEC = new ClienteWS();
		wsEC.GrabarTransferencia(1, 1200, 0, lista, "***PRUEBA*** devolver a deposito 1");
	}
}
