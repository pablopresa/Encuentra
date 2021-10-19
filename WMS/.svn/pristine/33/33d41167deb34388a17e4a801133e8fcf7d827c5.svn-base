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
              	<th class="text-center">Usuario</th>
               	<th class="text-center">Origen</th>
               	<th class="text-center">Destino</th>
               	<th class="text-center">Cantidad</th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
            
				<td class="text-center">${l.update}</td>
				<td class="text-center">${l.usuario}</td>
				<td class="text-center">${l.origen}</td>
				<td class="text-center">${l.destino}</td>
				<td class="text-center">${l.cantidad}</td>
			
	  		</tr>
	  		</c:forEach>
   		</tbody>
	</table>
</div>

		