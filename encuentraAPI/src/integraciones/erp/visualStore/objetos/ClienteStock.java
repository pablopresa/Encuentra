package integraciones.erp.visualStore.objetos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import integraciones.erp.odoo.laIsla.ArticuloCantidadEncuentra;
import integraciones.erp.odoo.laIsla.StockArticulos;
import persistencia.MSSQL_API;

public class ClienteStock 
{
	private int idEmpresa;

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public ClienteStock(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
	public List<StockArticulos> darStockArts(List<ArticuloCantidadEncuentra> articulos,	Map<Integer, Integer> excluir_deps)
	{
		if (articulos.isEmpty())
		{
			return new ArrayList<StockArticulos>();
		}
		List<StockArticulos>  retorno = null;
		String artsIn = "";
		String depositosIn = "";
		String depositosEx = "";
		
		
		boolean pri = true;
		
		for (ArticuloCantidadEncuentra ar  : articulos) 
		{
			if(pri)
			{
				artsIn+= "'"+ar.getIdArticulo()+"'";
				pri=false;
			}
			else
			{
				artsIn+=",'"+ar.getIdArticulo()+"'";
			}
		}
		
		if(excluir_deps!=null)
		{
			List<Integer> deposEx = new ArrayList<Integer>(excluir_deps.values());
			pri = true;
			for (Integer i : deposEx) 
			{
				if(pri)
				{
					depositosIn+= i;
					pri=false;
				}
				else
				{
					artsIn+=","+i;
				}
			}
			
			if(!depositosIn.equals(""))
			{
				depositosEx = " and IdDeposito not in ("+depositosIn+")";
			}
		}
		
		
		
		
		
		String q = "select IdDeposito,IdArticulo,Pedido,stock-Pedido 'disp' from MovStockTotal ms where ms.IdArticulo in ("+artsIn+") "+depositosEx;
		
		
		MSSQL_API sql = new MSSQL_API();
		
		retorno = sql.darStockVS(q,idEmpresa);
		
		
		
		
		
		
		return retorno;
	}

}
