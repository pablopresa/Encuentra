<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<input type="text" value= "<%=basePath%>" id ="basePath" style="display: none">   							
  <div class="table-responsive">
     <table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
		<thead class="thead-dark">
            <tr>
             	<th class="text-center">Fecha</th>
             	<th class="text-center">Marca</th>
              	<th class="text-center">Genero</th>
               	<th class="text-center">Categoria</th>
              	<th class="text-center">Clase</th>
               	<th class="text-center">Usuario</th>
               	<th class="text-center">Articulo</th>
               	<th class="text-center">Desc. Articulo</th>
              	<th class="text-center">Cantidad</th>
               	<th class="text-center">Observaciones</th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
				<td class="text-center">${l.fecha}</td>
				<td class="text-center">${l.marca}</td>
				<td class="text-center">${l.genero}</td>
				<td class="text-center">${l.categoria}</td>
				<td class="text-center">${l.clase}</td>
				<td class="text-center">${l.usuario}</td>
				<td class="text-center">${l.articulo}</td>
				<td class="text-center">${l.descripcionArticulo}</td>
				<td class="text-center">${l.cantidad}</td>
				<td class="text-center">${l.observaciones}</td>
	  		</tr>
	  		</c:forEach>
   		</tbody>
	</table>
</div>

