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
             	<th class="text-center">Nro. Solicitud</th>
             	<th class="text-center">Origen</th>
               	<th class="text-center">Destino</th>
             	<th class="text-center">Seccion</th>
             	<th class="text-center">SKU</th>
              	<th class="text-center">Marca</th>
              	<th class="text-center">Clase</th>
              	<th class="text-center">Nro. Bulto</th>
               	<th class="text-center">Nro. Documento</th>
              	<th class="text-center">Fecha Carga</th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
				<td class="text-center">${l.nroSolicitud}</td>
				<td class="text-center">${l.origen}</td>
				<td class="text-center">${l.destino}</td>
				<td class="text-center">${l.seccion}</td>
				<td class="text-center">${l.sku}</td>
				<td class="text-center">${l.marca}</td>
				<td class="text-center">${l.clase}</td>
				<td class="text-center">${l.nroBulto}</td>
				<td class="text-center">${l.nroDocumento}</td>
				<td class="text-center">${l.fechaCarga}</td>
	  		</tr>
	  		</c:forEach>
   		</tbody>
	</table>
</div>

