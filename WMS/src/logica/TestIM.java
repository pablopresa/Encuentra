//Import packagesppp
package logica;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TestIM{
	//Static swing components
	static JFrame frmMain;
	static Container pane;
	static JButton btnPrint;
	private static BufferedImage bi;
	
	public static void main (String[] args)
	{
		imprimir_EtiArticulo ia = new imprimir_EtiArticulo();
		try {
			ia.imprimirEtiquetas("$1000.00", "Articuuuuuu", "escala uno", "escala dos", "descrip", "maecaaaa","$23232");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
