
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:include page="/v3/util/menu.jsp"></jsp:include>
<div class="row">
	<div class="col-md-12">
		<h3>
			Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Cantidad
			Recepción
		</h3>
	</div>
</div>
<hr />
<div class="row">
	<div class="col-md-12">
		<c:if test="${menError!=null}">
			<h5>
				<strong>Mensaje:</strong>
			</h5>
			<div class="alert alert-info">
				<strong style="color: red"> <c:out value="${menError}"></c:out></strong>
			</div>
		</c:if>
		<div class="card card-default">

			<div class="card-header">
				<h5>Filtros</h5>
			</div>
			<div class="card-body">
				<form method="get">
					<div class="form-group">
						<div class="col-md-10">
							<div class="input-group">
								<div class="col-sm-6 col-lg-4">
									<div class="form-group">
										<label class="col-md-12 control-label" for="fechas">Fecha:&nbsp;</label>
										<div class="col-md-10">
											<input class="form-control form-control-sm bg-white rango"
												type="text" name="fechas" id="fechas">
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-lg-4">
									<label class="col-md-4 control-label" for="art">Articulo:&nbsp;</label>
									<input type="text" class="form-control bg-white"
										aria-label="Small" aria-describedby="inputGroup-sizing-sm"
										name="idArticulo" required>
								</div>

							</div>
						</div>
					</div>
					<input type="submit" class="btn btn-primary ml-4" value="Buscar" />
				</form>
			</div>

			<div class="card-header">
				<h5>Artículos</h5>
			</div>
			<div class="card-body">
				<table
					class="display table nowrap table-striped table-hover dataTable"
					id="encuentra-default">
					<thead class="thead-dark">
						<tr>
							<th class="text-center">Codigo</th>
							<th class="text-center">Descripcion</th>
							<th class="text-center">Cantidad</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="l" items="${lista}">
							<tr>
								<td class="text-center">${l.descripcion}</td>
								<td class="text-center">${l.descripcionB}</td>
								<td class="text-center">${l.id}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div id="mymodal" class="modal fade" role="dialog"></div>
<script type="text/javascript">
				</script>

<jsp:include page="/v3/util/footer.jsp"></jsp:include>




