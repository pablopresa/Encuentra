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
					Inventario&nbsp;<i class="fa fa-angle-double-right"
						aria-hidden="true"></i>&nbsp;Ubicaciones en Cero
				</h3>
				<h6>Detalle de las ubicaciones en Cero de las Estanterias ya
					inventariadas</h6>
					
				<div class="text-right"> 
					<div class="flex-container">	
                 		<a href="<%=basePath%>v3/mantenimiento/ImprimirEt.jsp" class="btn btn-info" target="_blank"><span class="fas fa-print"></span>&nbsp;Seleccionar ojos a Imprimir</a>
                  	</div>
                </div>
			</div>
		</div>
		<!-- /. ROW  -->
		<hr />
		<div class="row">
			<div class="col-md-12">
				<c:if test="${menError!=null}">
					<h6>
						<strong>Mensaje:</strong>
					</h6>
					<div class="alert alert-info">
						<strong style="color: red"> <c:out value="${menError}"></c:out></strong>
					</div>
				</c:if>
				<!-- Advanced Tables -->
				<div class="card">
					<div class="card-header">
						<h5>Filtrar por zona</h5>
					</div>
					<div class="card-body">
						<form class="form-inline" role="form" action="<%=basePath%>/DarOjosEnCero.do">
			            	<div class="form-group mx-sm-3 mb-8">
								<label for="estanteria">Zonas:&nbsp;</label>
								<select name="zona" class="form-control">
									<option value="" selected="selected"></option>
									<c:forEach var="gr" items="${lstZonas}">
										<c:if test="${gr.id!=zonaSeleccionada}">
											<option value="${gr.id}"><c:out value="${gr.descripcion}"></c:out> </option>
										</c:if>
										<c:if test="${gr.id==zonaSeleccionada}">
											<option value="${gr.id}" selected="selected"><c:out value="${gr.descripcion}"></c:out> </option>
										</c:if>
									</c:forEach>
								</select>
							</div>
			                <button type="submit" class="btn btn-primary" value="Buscar"><i class="feather icon-search"></i>Filtrar</button>
						</form>
						<div class="card-header">
							<h5>Resultados</h5>
						</div>
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="encuentra-default">
								<thead class="thead-dark">
									<tr>
										<th>Estanterías</th>
										<th>Cod. Ubicación en Cero</th>
										<th>Cantidad</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="o" items="${listaO}">
										<tr class="gradeD">
											<td><c:out value="${o.desc}"></c:out></td>
											<td><c:out value="${o.idOjo}"></c:out></td>
											<td><c:out value="${o.cant}"></c:out></td>
										</tr>
									</c:forEach>
									
								</tbody>

							</table>

						</div>

					</div>
				</div>
				<!--End Advanced Tables -->
			</div>
		</div>
		<!-- /. ROW  -->
<!-- /. PAGE WRAPPER  -->
<jsp:include page="/v3/util/footer.jsp"></jsp:include>
