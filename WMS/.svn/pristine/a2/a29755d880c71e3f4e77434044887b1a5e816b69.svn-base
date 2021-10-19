package aTest;
import java.io.FileNotFoundException;
import com.itextpdf.text.DocumentException;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataPicking;

import dataTypes.DataIDDescripcion;


public class TestAgregarABultoRepo 
{
	public static void main(String[] args) throws FileNotFoundException, DocumentException 
	{	
		
		try {
			DataPicking data = new DataPicking();
			data.setArticulo("5245838OB2350");
			data.setVerificada(1);
			data.setIdPicking(10568);
			data.setOrigen(new DataIDDescripcion(9000,""));
			data.setDestino(new DataIDDescripcion(403,""));
			data.setSolicitud(124227);
			
			bulto b = new bulto("prueba_nico","Caja distribucion prueba",false,0,0,0,0.0,false,"",
					1119,"403",2);
			b.setEquipo_trabajo(1);
			
			bultoContenido bc = new bultoContenido();
			bc.setIdArticulo("2011188BEU350");
			bc.setCantidad(1);
			bc.setPicking(10568);
			bc.setUsuario(1119);
			b.Crear_Bulto(2);
			b.Agregar_A_Bulto_Reposicion(bc, data);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

	public TestAgregarABultoRepo() {
	}
	
	
	
	
	
	
	

}
