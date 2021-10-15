package web.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import persistencia.MSSQL;

import dataTypes.DataIDDescripcion;

public class _EncuentraMonitor extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		
		
		String consulta = " select t1.suma+t2.suma cant,'Mov. Pendientes VSS SAP' indicador from (select sum(1) suma from VsStadium.dbo.documento where SAPExportado = 0 and IdListaEmpresa = 1) t1, (select sum(1) suma from VsStadium.dbo.DocEstado DE inner join VsStadium.dbo.Documento  DO on DO.IdDocumento = DE.IdDocumento where DE.SAPExportado = 0  and DO.IdListaEmpresa = 1) t2 "+
						"	union all  "+
						"	select count(*) cant,'ERRORES Pendientes VSS SAP' indicador from documento where sapExportado = 3 and idListaEmpresa =1 "+
						"	union all  "+
						"	select sum(idListaEMpresa) cant,CONCAT('Mov. Pendientes tienda ', IdTienda) indicador from Comunicacion where idListaEmpresa =1 group by IdTienda "+ 
						"	union all  "+
						"	select count(*) cant,'Pendientes Generados HOY' indicador from documento where sapExportado = 0 and idListaEmpresa =1 and CONVERT (varchar(10), Fecha,103)= CONVERT (varchar(10), GETDATE(),103) "+
						"	union all "+
						"	select difMov*6 as cant, 'Mov VSS SAP /hora' indicador from EcommerceMaps.dbo.vistaAvanceSincro "+
						"	union all "+
						"	select diferro*6 as cant, 'Errores VSS SAP /hora' indicador from EcommerceMaps.dbo.vistaAvanceSincro "+
						"	union all "+
						"	SELECT ((desde.Movimientos - hasta.Movimientos)+hasta.MovimientosPeriodo)*6 as cant, 'Mov /hora Real' indicador FROM EcommerceMaps.dbo.AvanceSincro AS hasta CROSS JOIN EcommerceMaps.dbo.AvanceSincro AS desde WHERE (hasta.Clave =(SELECT MAX(Clave) AS Expr1 FROM EcommerceMaps.dbo.AvanceSincro)) AND (desde.Clave =(SELECT MAX(Clave) - 1 AS Expr1 FROM EcommerceMaps.dbo.AvanceSincro)) "+
						"	union all "+
						"	SELECT MovimientosPeriodo*6 as cant,'Mov. Generados HOY /hora' indicador from EcommerceMaps.dbo.AvanceSincro where Clave = (select max(clave) from EcommerceMaps.dbo.AvanceSincro) "+
						"	order by cant desc ";
		List<DataIDDescripcion> indicadores = MSSQL.darListaIdDescripcion(consulta);
		request.setAttribute("indicadores", indicadores);
		
		return mapping.findForward("ok");
		
	
	
	}

}
