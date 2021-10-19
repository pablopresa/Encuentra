<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

                                    
<c:set var="linea" scope="page" value="0"></c:set>
<fmt:parseNumber var="i" type="number" value="${linea}"/>
<c:forEach var="p" items="${pickings}">
	<c:if test="${p.sol!=p.pick}">
			<c:set var="coloPI" scope="page" value="#ffcccc"></c:set>
		</c:if>
		<c:if test="${p.sol==p.pick}">
			<c:set var="coloPI" scope="page" value="#adebad"></c:set>
		</c:if>
		<c:if test="${p.verificada!=p.pick}">
			<c:set var="coloVE" scope="page" value="#ffcccc"></c:set>
		</c:if>
		<c:if test="${p.verificada==p.pick}">
			<c:set var="coloVE" scope="page" value="#adebad"></c:set>
		</c:if>
		
		<fmt:parseNumber var="i" type="number" value="${i+1}"/>
		
		<tr>
			<td style="vertical-align: middle;"><div align="center"><c:out value="${p.usuario.descripcion}"></c:out></div></td>
			<td style="vertical-align: middle;"><div align="center"><c:out value="${p.idPedido}"></c:out></div></td>
			<td style="vertical-align: middle;"><div align="center"><c:out value="${p.articulo}"></c:out></div></td>
			<td style="vertical-align: middle;"><div align="center"><c:out value="${p.descripcion}"></c:out></div></td>
			<!--<td style="vertical-align: middle;"><div align="center"><c:out value="${p.stockOrigen}"></c:out></div></td>
			<c:if test="${p.stockOSAP>=p.verificada}">
				<td style="vertical-align: middle;"><div align="center"><c:out value="${p.stockOSAP}"></c:out></div></td>
			</c:if>
			<c:if test="${p.stockOSAP<p.verificada}">
				<td style="vertical-align: middle; background-color:ffcccc"><div align="center"><c:out value="${p.stockOSAP}"></c:out></div></td>
			</c:if>-->
			<td style="vertical-align: middle;"><div align="center"><c:out value="${p.destino.descripcion}"></c:out></div></td>
			<td style="vertical-align: middle;"><div align="center" id="sol-${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}"><c:out value="${p.sol}"></c:out></div></td>
			<td style="background-color: ${coloPI}; text-align:center; vertical-align: middle;" id="pick-${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}"><c:out value="${p.pick}"></c:out></td>
			<td style="text-align:center; vertical-align: middle; background-color: ${coloVE};"><input class="form-control" style="width: 80px;" type="number" 
			value="${p.verificada}" onchange="ChangeQuantity('${p.articulo}','${p.origen.id}','${p.destino.id}','${p.idPedido}','${p.solicitud}','${p.idPicking}')" 
			name="${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}" id="${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}"/></td>
			
	</c:forEach>
									
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
              