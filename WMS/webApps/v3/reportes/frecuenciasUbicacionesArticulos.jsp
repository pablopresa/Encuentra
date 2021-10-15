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
             	<th class="text-center">Ojo</th>
             	<th class="text-center">Articulo</th>
              	<th class="text-center">Hits</th>
               	<th class="text-center">Porcentaje</th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
				<td class="text-center">${l.ojo}</td>
				<td class="text-center">${l.articulo}</td>
				<td class="text-center">${l.hits}</td>
				<td class="text-center">${l.porcentaje}</td>
	  		</tr>
	  		</c:forEach>
   		</tbody>
	</table>
</div>