<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<input type="text" value="<%=basePath%>" id="basePath"
	style="display: none">
<div class="table-responsive">
	<table class="display table nowrap table-striped table-hover dataTable"
		id="encuentra-default">
		<thead class="thead-dark">
			<tr>
				<th class="text-center">fecha</th>
				<th class="text-center">cliente</th>
				<th class="text-center">pedido</th>
				<th class="text-center">C. Ped</th>
				<th class="text-center">Pick</th>
				<th class="text-center">Veri</th>
				<th class="text-center">Remi</th>
				<th class="text-center">Bultos</th>
				<th class="text-center">Estado</th>
				<th class="text-center"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="l" items="${lista}">
				<tr>
					<td class="text-center">${l.fecha}</td>
					<td class="text-center">${l.cliente}</td>
					<td class="text-center">${l.pedido}</td>
					<td class="text-center">${l.cantidad}</td>
					<td class="text-center">${l.picked}</td>
					<td class="text-center">${l.verificadas}</td>
					<td class="text-center">${l.remitidas}</td>
					<td class="text-center">${l.cantBultos}</td>
					<td class="text-center">${l.estados}</td>
					
					
					<td class="text-center"><a style="color: white" href="<%=basePath%>monitorVentaM.do?idPedidoVM=${l.pedido}" class="btn btn-primary md-trigger">Ver detalles</a> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="md-overlay"></div>  