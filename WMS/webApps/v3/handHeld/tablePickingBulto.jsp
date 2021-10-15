<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

						
{"table":"<tr><td>Articulo</td><td>Cantidad</td><td>Cont</td><td>OK</td></tr><c:forEach var="c" items="${modeloPickingBulto.listaElementos}"><tr><td>${c.idArticulo}</td><td>${c.cantidad}</td><td>${c.pick}</td><c:if test="${c.cantidad==c.pick}"><td><img alt='ok' src='<%=basePath%>imagenes/icons/accept.png' border='0' style='width: 20px;'></td></c:if><c:if test="${c.cantidad!=c.pick}"><td>&nbsp;</td></c:if><tr></c:forEach>","menError": "${menError}","elementos_json": ${modeloPickingBulto.elementos_json},"count": ${modeloPickingBulto.count}}
<c:remove var="menError" scope="session" /> 