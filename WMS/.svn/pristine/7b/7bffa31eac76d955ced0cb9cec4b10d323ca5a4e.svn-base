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
                    	<h3>Tareas&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Tareas y productividad</h3>   
                        <h6>Puede buscar dentro del listado tambien </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                        	<h5>
                             	Filtros
                            </h5>
                        </div>
					<div class="card-body">
						<form class="form-horizontal" role="form" method="post"	action="<%=basePath%>/reporteTareas.do">
							<div class="row">
							<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="filtro" class="col-md-4 control-label">Filtro:&nbsp</label>
								          	<div class="col-md-8">
								            	<select name="filtro" class="form-control">
													<option value="1" selected="selected">Sin Terminar</option>
													<option value="2">Terminadas</option>
												</select>
								          	</div>
								        </div>
								    </div>	
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											    <label class="col-md-4 control-label" for="fini">Fecha:&nbsp;</label>
												<div class="col-md-8">
										           <input class="form-control rango" type="text" name="fini" id="fini">
										        </div>										        
										    </div>
									</div>	    								   
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="fini">&nbsp;</label>
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
										  	</div>											
										</div>
									</div>
								</div>	
						</form>				
					</div>
					</div>
					
					<div class="card">
                        <div class="card-header">
                        	<h5>
                             	Productividad
                            </h5>
                        </div>
					<div class="card-body">
						<div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                          	<th class="text-center">Tarea</th>
                                           	<th class="text-center">Observaciones</th>
											<th class="text-center">Usuario</th>
											<th class="text-center">Primer<br> Picking</th>
											<th class="text-center">Ultimo<br> Picking</th>
											<th class="text-center">Linea<br> Actual</th>
											<th class="text-center">Total<br> Lineas</th>
											<th class="text-center">Tiempo<br>(minutos)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="t" items="${tareas}">
											<tr class="gradeD">
												<td class="text-center">${t.tarea}</td>
												<td class="text-center">${t.observaciones}</td>
												<td class="text-center">${t.usuario}</td>
												<td class="text-center">${t.primerPicking}</td>												
												<td class="text-center">${t.ultimoPicking}</td>
												<td class="text-center">${t.ultimaLinea}</td>
												<td class="text-center">${t.totalLineas}</td>
												<td class="text-center">${t.tiempo}</td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
					</div>
                            
                            
                        </div>
                    
		 <script type="text/javascript">
        function deleteLinea(ojo,art,cant)
			{
				 if(confirm("Seguro desea dar de baja esa linea?"))
				 {
					 var req = "<%=basePath%>/EcommerceBajarLineaUbicaciones.do?idArt="+art+"&idOjo="+ojo+"&cantidad="+cant;
					 window.location.assign(req);
				 }
				
				
				
			}
        </script>
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
