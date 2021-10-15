package aTest;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.DocumentException;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;



public class AfectarOrdenesElRey 
{
	public static void main(String[] args) throws FileNotFoundException, DocumentException 
	{	
		
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		List<Long> pedidos = new ArrayList<>();
		
		pedidos.add(new Long("4267759145"));
		pedidos.add(new Long("4269258007"));
		pedidos.add(new Long("4270047542"));
		pedidos.add(new Long("4269373593"));
		pedidos.add(new Long("4269768681"));
		pedidos.add(new Long("4270120296"));
		pedidos.add(new Long("4270393468"));
		pedidos.add(new Long("4270745278"));
		pedidos.add(new Long("4270739116"));
		pedidos.add(new Long("4270877838"));
		pedidos.add(new Long("4270916168"));


		
		for(Long p: pedidos) {
			api.CheckInOrder(p, 0, 4);
		}		
	
	}
	
	public static void testearApostrofes(String texto) {
		
	}
	

	public AfectarOrdenesElRey() {
	}
	
	
	
	
	
	
	

}
