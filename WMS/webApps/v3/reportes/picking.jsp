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
             	<th class="text-center">Nro. Picking</th>
             	<th class="text-center">Nro. Solicitud</th>
              	<th class="text-center">Fecha Solicitud</th>
               	<th class="text-center">Fecha Documento</th>
               	<th class="text-center">Origen</th>
               	<th class="text-center">Destino</th>
               	<th class="text-center">Justificacion</th>
               	<th class="text-center">Pedido</th>
               	<th class="text-center">Articulo</th>
               	<th class="text-center">Marca</th>
               	<th class="text-center">Clase</th>
               	<th class="text-center">Genero</th>
               	<th class="text-center">Categoria</th>
               	<th class="text-center">Cant. Sol.</th>
               	<th class="text-center">Cant. Pick.</th>
               	<th class="text-center">Cant. Verif.</th>
               	<th class="text-center">Cant. Remit</th>
               	<th class="text-center">Usuario Picking</th>
               	<th class="text-center">Remito</th>
			</tr>
		</thead>
        <tbody>
        	<c:forEach var="l" items="${lista}">
            <tr>
				<td class="text-center">${l.idPicking}</td>
				<td class="text-center">${l.idSolicitud}</td>
				<td class="text-center">${l.fechaSolicitud}</td>
				<td class="text-center">${l.fechaDocumento}</td>
				<td class="text-center">${l.origen}</td>
				<td class="text-center">${l.destino}</td>
				<td class="text-center">${l.justificacion}</td>
				<td class="text-center">${l.seccion}</td>
				<td class="text-center">${l.idArticulo}</td>
				<td class="text-center">${l.marca}</td>
				<td class="text-center">${l.clase}</td>
				<td class="text-center">${l.genero}</td>
				<td class="text-center">${l.categoria}</td>
				<td class="text-center">${l.cantidad}</td>
				<td class="text-center">${l.picked}</td>
				<td class="text-center">${l.verif}</td>
				<td class="text-center">${l.remit}</td>
				<td class="text-center">${l.usuarioPicking}</td>
				<td class="text-center">${l.remito}</td>
				
	  		</tr>
	  		</c:forEach>
   		</tbody>
	</table>
</div>

