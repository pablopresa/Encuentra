import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import beans.bulto;
import beans.encuentra.IPrint;

public class MainGenerarEtiqueta {

	public MainGenerarEtiqueta() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bulto b = new bulto("1219728072021085204","Caja distribucion lc.1380237416", true, 0, 0, 0, 0.0, true, "2021-07-28 08:51:15", 107, "1380237416", 5);
		b.setUsuarioClose(107);
		b.setPedido(Long.valueOf(219730));
		
		try {
			IPrint.ImprimirEtiquetaBulto_Pedido(b, 3, 5, 2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
