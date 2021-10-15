<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

        <jsp:include page="/v3/util/menu.jsp"></jsp:include>
		  
        <!-- /. NAV SIDE  -->
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Conteos&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Lista de Conteos</h3>   
                        <h6>Puede filtrar y seleccionar los que quiera </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> ${menError}"</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                    <div class="card-header">
                    		<h5>Filtros para aplicar en la lista</h5>                             
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form"  action="<%=basePath%>/DarConteoStore.do">
                            	<div class="row">
                            		<script type="text/javascript" src="<%=basePath%>v2/assets/Picker/assets/bootstrap.js"></script>
									<script src="<%=basePath%>v2/assets/Picker/assets/moment-with-locales.js"></script>
									<script src="<%=basePath%>v2/assets/Picker/assets/daterangepicker.js"></script>
									<!-- fin scripts y css de picker -->
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="fini">Fecha:&nbsp;</label>
											<div class="col-md-8">
												 <input class="form-control rango bg-white" type="text" name="fini" id="fini"/>
											</div>
										</div>
									</div>
	                            		<div class="col-sm-6 col-lg-4">
									        <div class="form-group">
									          <label class="col-md-4 control-label" for="deposito">Deposito:&nbsp;</label>
									          <div class="col-md-8">
									            <input type="text" class="form-control" name="deposito" value="${uLogeado.deposito}" readonly="readonly">
											  </div>
									        </div>
									    </div>
										<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="p-2" for="estanteria">Mostrar Terminados:&nbsp;</label>
											<div class="switch d-inline m-r-10">
												<input type="checkbox" id="switch-1" name="agrpTienda" value="1"
													checked> <label for="switch-1" class="cr"></label>
											</div>										
										</div>
									</div>
	                            				   
	                            		<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<div style="width: 1px; height: 1px; visibility: hidden;"><input type="text" name="central" value="${central}"> </div>
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
	                             Lista de articulos a controlar, Marque los que desea
	                             <br/>
	                             Son :${totalSKU} articulos
	                             <br/>
	                             Son :${total} unidades
	                            </h5> 
	                        </div>
                        <div class="card-body">
							<div class="table-responsive">								
	                            	<table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                         	<th class="text-center">Fecha</th>
												<th class="text-center">Descripcion</th>
												<th class="text-center">Articulos</th>
												<th class="text-center">Unidades</th>
												<th class="text-center">Ver Detalle</th>
												<th class="text-center">Cerrar Conteo</th>
											</tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="c" items="${conteosTienda}">
												<tr class="gradeD">
													<td class="text-center">${c.fecha}</td>
													<td class="text-center">${c.descripcion}</td>
													<td class="text-center">${c.totalArticulos}</td>
													<td class="text-center">${c.totalUnidades}</td>
													<td class="text-center"><a href="<%=basePath%>verDetalleConteo.do?idConteo=${c.idConteo}" class="btn btn-success"><span class="fas fa-eye"></span></a></td>
													<td class="text-center"><a href="<%=basePath%>CloseConteoStore.do?idConteo=${c.idConteo}" class="btn btn-danger"><span class="fas fa-archive"></span></a></td>
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
        
		 <c:set var="switching" value="yes" scope="request"/>
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
