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
             	<th class="text-center">Deposito</th>
              	<th class="text-center">Marca</th>
               	<th class="text-center">Genero</th>
               	<th class="text-center">Categoria</th>
               	<th class="text-center">Clase</th>
               	<th class="text-center">Articulo</th>
               	<th class="text-center">Cantidad</th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
            
				<td class="text-center">${l.nombre}</td>
				<td class="text-center">${l.marca}</td>
				<td class="text-center">${l.genero}</td>
				<td class="text-center">${l.categoria}</td>
				<td class="text-center">${l.clase}</td>
				<td class="text-center">${l.articulo}</td>
				<td class="text-center">${l.cantidad}</td>
	  		</tr>
	  		</c:forEach>
   		</tbody>
	</table>
</div>

		