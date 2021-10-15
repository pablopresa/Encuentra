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
             	<th class="text-center">Id. Picking</th>
             	<th class="text-center">Fecha</th>
              	<th class="text-center">Clase</th>
               	<th class="text-center">Categoria</th>
               	<th class="text-center">Usuario Pickea</th>
               	<th class="text-center">Cant. Solicitadas</th>
               	<th class="text-center">Cant. Verificadas</th>
               	<th class="text-center">Cant. Remitidas</th>
               	<th class="text-center">Tiempo Pickeo(Minutos)</th>
               	<th class="text-center">Cantidad Líneas</th>
               	<th class="text-center">Cantidad Posiciones</th>
               	<th class="text-center">Usuario Verifica</th>
               	<th class="text-center">Tiempo Verifica(Minutos)</th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
				<td class="text-center">${l.idPicking}</td>
				<td class="text-center">${l.fecha}</td>
				<td class="text-center">${l.clase}</td>
				<td class="text-center">${l.categoria}</td>
				<td class="text-center">${l.usuarioPicking}</td>
				<td class="text-center">${l.solicitadas}</td>
				<td class="text-center">${l.verificadas}</td>
				<td class="text-center">${l.remitidas}</td>
				<td class="text-center">${l.tiempoPicking}</td>
				<td class="text-center">${l.cantidadLineas}</td>
				<td class="text-center">${l.cantidadPosiciones}</td>
				<td class="text-center">${l.usuarioVerif}</td>
				<td class="text-center">${l.tiempoVerificacion}</td>
	  		</tr>
	  		</c:forEach>
   		</tbody>
	</table>
</div>

