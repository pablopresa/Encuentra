<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:include page="/v3/util/menu.jsp"></jsp:include>
<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script> -->
<!-- /. NAV SIDE  -->


	<c:if test="${uLogeado!=null}">

		<c:forEach var="d" items="${uLogeado.seguridadUI}">
			<c:if test="${d=='filtro_categorias'}">
				<c:set var="puede" scope="request" value="1" />
			</c:if>
		</c:forEach>




		<div class="row">
			<div class="col-md-12">
				<h3>
					Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Articulos
					en ubicaciones
				</h3>
				<h6>Puede buscar dentro del listado tambien</h6>
			</div>
		</div>
		<!-- /. ROW  -->
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
			<!-- Advanced Tables -->
			<div class="card card-default">
				<div class="card-header">
					<h5> Filtros </h5>
				</div>
				<div class="card-body">
					<div>
					<form class="form-horizontal" id="buscarArtis" role="form" method="get"	action="<%=basePath%>/DarOjosArti.do">
						<div class="row">
							<div class="col-sm-6 col-lg-3">
								<div class="input-group input-group-sm mb-3">
									<input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Art�culo" name="art" id="art">
								</div>
							</div>
							<div class="col-sm-6 col-lg-2">
								<div class="input-group input-group-sm mb-3">
									<input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Ojo" name="idOjo" id="idOjo">
								</div>
							</div>
							<label>Estanter�as:</label>
							<div class="col-sm-6 col-lg-5">
								<div class="col-sm-12">
									<select class="select2-multi" multiple="multiple" name="estanteria">
										<c:forEach var="e" items="${estanterias}">
											<option value="${e.id}">${e.descripcion}</option>
										</c:forEach>
									</select> 
								</div>
							</div>
							</div>	
													
							
						 
							<c:if test="${puede==1}">
							
								<div class="row">
								
									<label>Familias:</label>
									<div class="col-sm-6 col-lg-5">
										<div class="col-sm-12">
											<select class="select2-multi" multiple="multiple" name="categoria">
												<c:forEach var="e" items="${categorias}">
													<option value="${e.id}">${e.descripcion}</option>
												</c:forEach>
											</select> 
										</div>
									</div>
									
									<label>Subfamilias:</label>
									<div class="col-sm-6 col-lg-5">
										<div class="col-sm-12">
											<select class="select2-multi" multiple="multiple" name="subcategory">
												<c:forEach var="sc" items="${subCategorias}">
													<option value="${sc.id}">${sc.descripcion}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									
								</div>
								
							</c:if>
							
							 <!-- 
							
								<div class="row categoria">
									<label>Familias:</label>
									
									<label>Subfamilias:</label>

									<select class="select2-multi" multiple="multiple" name="categoria">
										<c:forEach var="e" items="${categorias}">
											<option value="${e.id}">${e.descripcion}</option>
										</c:forEach>
									</select> 
										
									

									<select class="select2-multi" multiple="multiple" name="subcategory">
										<c:forEach var="sc" items="${subCategorias}">
											<option value="${sc.id}">${sc.descripcion}</option>
										</c:forEach>
									</select>
								</div>
							
							
							<style>
								.categoria label{
									width: 50%;
								}
								.categoria span{
									width: 49% !important;
								    float: left;
								    margin-right: 1%;
								}
								.categoria span span, 
								.categoria span span span {
									width: 100% !important;
								}
							</style>
							-->	
					    
							
							<div class="text-left col-sm-12 col-lg-12"> 
								<div class="flex-container">
									<label class="p-2" for="estanteria">Detalle Bulto:&nbsp;</label>
									<div class="switch switch-warning d-inline m-r-10">
										<input type="checkbox" id="switch-0" name="desconsolidar"
											checked> <label for="switch-0" class="cr"></label>
									</div>									
									
									<c:set var = "stkErp" scope = "page" value = ""/>
									<c:if test="${stockERP}">
										<c:set var = "stkErp" scope = "page" value = "checked"/>
									</c:if>
									<label class="p-2" for="estanteria">Stock ERP:&nbsp;</label>										
									<div class="switch switch-warning d-inline m-r-10">
									<input type="checkbox" id="switch-6" name="uStockERP"
										<c:out value = "${stkErp}"/>> <label for="switch-6" class="cr"></label>
									</div>
									
																	
									<label class="p-2" for="estanteria">Almacen:&nbsp;</label>
									<div class="switch d-inline m-r-10">
										<input type="checkbox" id="switch-1" name="uAlmacen" value="1"
											checked> <label for="switch-1" class="cr"></label>
									</div>
	
									<label class="p-2" for="estanteria">Ecommerce:&nbsp;</label>
									<div class="switch d-inline m-r-10">
										<input type="checkbox" id="switch-2" name="uEcommerce"
											value="2" unchecked> <label for="switch-2" class="cr"></label>
									</div>
	
									<label class="p-2" for="estanteria">Recepci�n:&nbsp;</label>
									<div class="switch d-inline m-r-10">
										<input type="checkbox" id="switch-3" name="uRecepcion"
											value="3" unchecked> <label for="switch-3" class="cr"></label>
									</div>
	
									<label class="p-2" for="estanteria">Expedici�n:&nbsp;</label>
									<div class="switch d-inline m-r-10">
										<input type="checkbox" id="switch-4" name="uExpedicion"
											value="4" unchecked> <label for="switch-4" class="cr"></label>
									</div>
	
									<label class="p-2" for="estanteria">Clasificaci�n:&nbsp;</label>
									<div class="switch d-inline m-r-10">
										<input type="checkbox" id="switch-5" name="uClasificacion"
											value="5" unchecked> <label for="switch-5" class="cr"></label>
									</div>
								</div>
							</div>
							<div class="text-center col-sm-12 col-lg-12 mt-5"> 
								<div class="flex-container">
									<input type="text" style="visibility: hidden;" name="mob" id="mob" value="0">
									<button type="submit" name="Submit" class="btn btn-info" id="btnFiltro">Buscar en ubicaciones</button>
									<!-- <a href="<%=basePath%>/reporteArtEnUbicaciones.do"><button class="btn btn-success">Exportar a Excel</button></a>  -->
								</div>
							</div>
						</div>
					</form>
				</div>
				<hr>
                <div class="card-header">
                 	<h5> Articulos en ubicaciones </h5>
                </div>
                <div class="card-body">
					<table class="display table nowrap table-striped table-hover dataTable responsive" id="encuentra-default">
					<thead class="thead-dark">
						<tr>
							<th class="text-center">Descripci�n</th>
							<th class="text-center">Estante</th>
							<th class="text-center">Modulo</th>
							<th class="text-center">Cod. Ubicaci�n</th>
							<th class="text-center">Articulo</th>
							<th class="text-center">Cantidad</th>
							<th class="text-center">Reservada</th>
							<c:if test="${stockERP}">
							<th class="text-center">Stock ERP</th>
							</c:if>							
							<th class="text-center">Bulto</th>
							<th class="text-center">Actualizado</th>
							<th class="text-center"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ot" items="${ojosTienen}">
						<tr class="gradeD">
								<td class="text-center"><c:out value="${ot.descripcion}"></c:out></td>
								<td class="text-center"><c:out value="${ot.modulo}"></c:out></td>
								<td class="text-center"><c:out value="${ot.estante}"></c:out></td>
								<td class="text-center"><a
									href="<%=basePath%>DarOjosArti.do?mob=0&idOjo=${ot.idOjo}"> <c:out
											value="${ot.alias}"></c:out></a></td>
								<td class="text-center"><a
									href="<%=basePath%>DarOjosArti.do?mob=0&art=${ot.articulo}"><c:out
											value="${ot.articulo}"></c:out></a></td>
								<td class="text-center"><c:out value="${ot.cantidad}"></c:out></td>
								<td class="text-center"><c:out
										value="${ot.cantidadReservada}"></c:out></td>
								<c:if test="${stockERP}">
									<td class="text-center">${ot.stock}</td>
								</c:if>		
								<td class="text-center"><c:if test="${ot.tipoSku==4}">
										<a class="btn btn-info"
											href="javascript:detalleBulto('${ot.articulo}');">Detalle</a>
									</c:if>
									<c:if test="${ot.tipoSku==1 && ot.idBulto!=''}">
										${ot.idBulto}
									</c:if>
								</td>
								<td class="text-center"><c:out value="${ot.fechaupdated}"></c:out></td>
								<td class="text-center">
								<a href="javascript:deleteLinea('${ot.idOjo}','${ot.articulo}',${ot.cantidad});">
										<button class="btn btn-icon btn-outline-danger">
											<span class="feather icon-slash"></span>
										</button>
								</a></td>
							</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr class="gradeD">
											<td colspan="7">&nbsp;</td>
											<td><a
												href="<%=basePath%>/DarOjosArtiInvent.do?vista=show"><button
												class="btn btn-success">Ver Inventario Gral.</button> </a></td>
												<td colspan="2"><a
													href="<%=basePath%>/DarOjosArtiInvent.do?vista=invent"><button
													class="btn btn-success">Ver ultimo conteo</button> </a></td>
												</tr>
									</tfoot>
						</table>
					  </div>
				<!--End Advanced Tables -->
				</div>
			</div>
			</div><!-- ROW  --> 


			<!-- /. PAGE INNER  -->

			<div id="mymodal" class="modal fade" role="dialog"></div>
			
			</c:if>
			
			
			<script type="text/javascript">
				function deleteLinea(ojo, art, cant) {
					if (confirm("Seguro desea dar de baja esa linea?")) {
						var req = "<%=basePath%>/EcommerceBajarLineaUbicaciones.do?idArt=" + art + "&idOjo=" + ojo + "&cantidad=" + cant;
						window.location.assign(req);
					}

				}
				function detalleBulto(art) {
					$.ajax({
						url : "<%=basePath%>/DetalleBulto.do",
						type : "post",
						dataType : "html",
						data : {
							bulto : art 

						}
					}).then(function(data) {
						console.log("success");

						var firstDivContent = document.getElementById('mymodal');				    
						firstDivContent.innerHTML = data;				
						$("#mymodal").modal();
					});

				}
			</script>
			
		<script type="text/javascript">
		    $(document).ready(function() {
		        // [ Multi Select ] start
				$(".select2-multi").select2({
					placeholder: "Puede seleccionar Estanter�as"
				});
			});
		</script>
			
		<script type="text/javascript">
			$(document).ready(function() {
			    $("#btnFiltro").click(function() {
			      // disable button
			      $(this).prop("disabled", true);
			      // add spinner to button
			      $(this).html(
			        `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Filtrando...`
			      );
			      document.getElementById("buscarArtis").submit();
			    });
			 });  		      
		</script>
			
			<!-- /. PAGE WRAPPER  -->
			<jsp:include page="/v3/util/footer.jsp"></jsp:include>
