package aTest;

import java.util.ArrayList;
import java.util.List;

import beans.Usuario;
import beans.encuentra.IPrint;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataIDDescripcion;

public class PruebaLabelPicking {

	public PruebaLabelPicking() {
	}

	public static void main(String[] args) {
		String etiq = "";
		int idPicking = 12345;
		int idEmpresa = 5;
		
		List<DataIDDescripcion> listaDepos = new ArrayList<>();
		listaDepos.add(new DataIDDescripcion(1095498722,"SOC.FOMENTO RURAL COL.SUIZA-CR"));
		listaDepos.add(new DataIDDescripcion(171720810,"LUCIA AUDIFFRED - PPGG"));
		listaDepos.add(new DataIDDescripcion(293380502,"ANA MARIA ZUVELA"));
		listaDepos.add(new DataIDDescripcion(206613002,"M. ALEJANDRA COCINO SPADIER"));
		
		Usuario uLog = new Usuario();
		uLog.setNick("Pepito");
		uLog.setDeposito("5");
		uLog.setIdEquipo(99);
		uLog.setNumero(1);
		uLog.setIdEmpresa(idEmpresa);
		
		try {
			etiq = "";//IPrint.etiquetas_verificacion_destinos(idPicking, listaDepos, uLog);	
			for(DataIDDescripcion ls :listaDepos)
			{
				etiq = IPrint.etiquetas_verificacion_destinos(ls, uLog);	
			}
			
			System.out.println(etiq);
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			//api.PutColaImpresion("labels"+idPicking+"_"+idEmpresa, etiq, 0, 1,uLog.getIdEquipo(),idEmpresa);
		}
		catch (Exception e) {
			System.out.println("error");
		}
		
	}

}
