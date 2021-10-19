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
             	<th class="text-center">Picking</th>
             	<th class="text-center">Fecha</th>
              	<th class="text-center">Usuario</th>
               	<th class="text-center">Solicitadas</th>
              	<th class="text-center">Remitidas</th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
				<td class="text-center">${l.idPicking}</td>
				<td class="text-center">${l.fecha}</td>
				<td class="text-center">${l.usuario}</td>
				<td class="text-center">${l.solicitadas}</td>
				<td class="text-center">${l.remitidas}</td>
	  		</c:forEach>
   		</tbody>
	</table>
</div>

