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
				<th class="text-center">Id Articulo</th>
				<th class="text-center">Stock en Encuentra</th>
				<th class="text-center">Stock en Visual</th>
				<th class="text-center"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="l" items="${lista}">
				<tr>
					<td class="text-center">${l.idArticulo}</td>
					<td class="text-center">${l.sotckEncuentra}</td>
					<td class="text-center">${l.stockVisual}</td>
					<!-- <td class="text-center"><button class="btn btn-primary"
							onclick='showModal("${l.ojosCantidad}")'>Ver detalles</button></td> -->
					<td class="text-center"><a style="color: white" class="btn btn-primary md-trigger" data-modal="modal-${l.idArticulo}">Ver detalles</a> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<c:forEach var="l" items="${lista}">
	<div class="md-modal md-effect-1" id="modal-${l.idArticulo}">
        <div class="md-content">
            <h3 class="theme-bg2">Articulo: ${l.idArticulo}</h3>
            <div>
                <table class="table table-striped table-bordered table-hover">
					<tr>
						<th style="text-align: center;">Ojo</th>
						<th style="text-align: center;">Cantidad</th>
					</tr>
				<c:forEach var='oc' items='${l.ojosCantidad}'>
					<tr>
						<td style="text-align: center;">${oc.descripcion}</td>
						<td style="text-align: center;">${oc.id}</td>
					</tr>
				</c:forEach>
				</table>
				<button class="btn btn-primary md-close" style="display: none">Cerrar</button>
                <!--<a class="btn btn-primary md-close" onclick="closeModal('modal-${d.numeroDoc}')" href="#">Cerrar</a>  -->
                
            </div>
        </div>
    </div>
</c:forEach>           
<div class="md-overlay"></div>  