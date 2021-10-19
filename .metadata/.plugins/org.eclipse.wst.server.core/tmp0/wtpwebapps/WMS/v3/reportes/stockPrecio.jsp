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
				Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Stock - Precio
			</h3>
			<!-- 				<h6>Puede buscar dentro del listado y decidir cuántos artículos se muestran. (Ah, re que no)</h6> -->
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
						<div class="col-sm-6 col-lg-4">
							<div class="form-group">
								<label class="col-md-12 control-label" for="art">Articulo:&nbsp;</label>
								<div class="col-md-10">
									<div class="input-group input-group-sm">
										<input type="text" class="form-control bg-white"
											aria-label="Small" aria-describedby="inputGroup-sizing-sm"
											name="articulo" required="" placeholder="Campo obligatorio">
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
								<th class="text-center">SKU</th>
								<th class="text-center">Stock</th>
								<th class="text-center">Precio lista</th>
								<th class="text-center">Precio venta</th>
							</tr>
						</thead>
						<tbody id="bFenicio">
							<c:forEach var="l" items="${lista}">
								<tr>
									<td class="text-center">${l.codigo}</td>
									<td class="text-center">${l.sku}</td>
									<td class="text-center">${l.stock}</td>
									<td class="text-center"><c:if
											test="${l.precioLista.UYU != null}">${l.precioLista}</c:if></td>
									<td class="text-center"><c:if
											test="${l.precioVenta.UYU != null}">${l.precioVenta}</c:if></td>
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
	
	
	
	
