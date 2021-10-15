<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<input type="hidden" value="<%=basePath%>" id="basePath">
<div class="table-responsive">
	<table class="display table nowrap table-striped table-hover dataTable"
		id="encuentra-default">
		<thead class="thead-dark">
			<tr>
				<th><a style="color:white;" href="javascript:seleccionarTodos()">Todos</a><br/><a style="color:white;" href="javascript:deseleccionarTodos()">Ninguno</a> </th>
				<th class="text-center">Estanteria</th>
				<th class="text-center">Modulo</th>
				<th class="text-center">Estante</th>
				<th class="text-center">Ojo</th>
				<th class="text-center">Articulo</th>
				<th class="text-center">Cantidad</th>
				<th class="text-center">Cantidad Conteo</th>
				<th class="text-center">Diferencia</th>
				<th class="text-center">Ult Act.</th>
				<th class="text-center">Fecha Conteo</th>
				<th class="text-center">Usuario</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="l" items="${lista}">
				<tr id="tr-${l.idOjo}-${l.idArticulo}">
					<td style="text-align: center;">
						<div class="checkbox checkbox-fill d-inline">
							<input type="checkbox" class="check" name="${l.idOjo}-${l.idArticulo}" id="${l.idOjo}-${l.idArticulo}">
							<label for="${l.idOjo}-${l.idArticulo}" class="cr"></label>
						</div>
					</td>
					<td class="text-center">${l.estanteria}</td>
					<td class="text-center">${l.modulo}</td>
					<td class="text-center">${l.estante}</td>
					<td class="text-center">${l.idOjo}</td>
					<td class="text-center">${l.idArticulo} <br/>${l.descripcionArticulo} </td>
					<td class="text-center">${l.cantidad_ojo}</td>
					<td class="text-center">${l.cantidad_contada}</td>
					<c:if test="${l.diferencia>0}">
						<td class="text-center" style="background-color: #ffb3b3">${l.diferencia}</td>
					</c:if>
					<c:if test="${l.diferencia==0}">
						<td class="text-center" style="background-color: #f2ffe6">${l.diferencia}</td>
					</c:if>
					<td class="text-center">${l.fecha_Act}</td>
					<td class="text-center">${l.fecha_conteo}</td>
					<td class="text-center">${l.usuario}</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
			<div class="container" style="margin-left:70%;margin-top:1%;">
				<a style="color: white" href="javascript:eliminar()" class="btn btn-primary md-trigger">Eliminar seleccionados</a>
				<a style="color: white" href="<%=basePath%>updateOjosCount.do" class="btn btn-primary md-trigger">Corregir con conteo</a>
			</div>
</div>

<script type="text/javascript">

	function eliminar() {

		var checkboxes = document.getElementsByClassName('check');
		var ret = '';
		var primero = true;
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].checked) {
				if(!primero){
					ret+= ',';
				}
				ret += checkboxes[i].getAttribute('name');
				primero = false;
			}
		}
		if (ret != '') {
			var path = $("#basePath").val() + "deleteSelectedCount.do?articulos=" + ret;
			console.log(path);	
			$.ajax({
				url: path,
				success: 
					function(){
						var arts = ret.split(',');
						for(var i=0; i<arts.length; i++){
							$('#tr-'+arts[i]).remove();
						}
						alert("Los conteos se han eliminado con éxito.");
				},
				error: 
					function(error){
						console.log(error);
					}
			});
		} 
		else {
			alert("Debe seleccionar conteos para eliminarlos.");
		}
	}
	
	function seleccionarTodos(){
		var checkboxes = document.getElementsByClassName('check');
		for (var i = 0; i < checkboxes.length; i++) {
			if (!checkboxes[i].checked) {
				checkboxes[i].click();
			}
		}
	}
	
	function deseleccionarTodos(){
		var checkboxes = document.getElementsByClassName('check');
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].checked) {
				checkboxes[i].click();
			}
		}
	}
	
</script>

