<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
				
<c:forEach var="m" items="${MovStock}">															
	<tr>													
		<td class="text-center">${m.fecha}</td>
		<td class="text-center">${m.usuario}</td>
		<td class="text-center">${m.destino} - ${m.nombreDestino}</td>	
		<td class="text-center">${m.cantidad}</td>	
		<td class="text-center">${m.observacion}</td>	
		<td class="text-center">${m.intentos}</td>	
		<c:if test="${m.estado==1}">
			<c:if test="${m.destino!=1200}">
				<td class="text-center">
					<a target="_BLANK" class="btn btn-success" href="<%=basePath%>/pdf/r${m.doc}.pdf">${m.doc}</a>
				</td>
			</c:if>
			<c:if test="${m.destino==1200}">
				<td class="text-center">
					<a target="_BLANK" class="btn btn-success" href="<%=basePath%>/pdf/rp${m.origenDoc}.pdf">${m.doc}</a>
				</td>
			</c:if>
		</c:if>
		<c:if test="${m.estado!=1}">
			<td class="text-center"><button class="btn btn-danger" onclick="re_send(${m.id})" id="re_send${m.id}">Re-Enviar</button></td>
		</c:if>								
	</tr>
</c:forEach>				


