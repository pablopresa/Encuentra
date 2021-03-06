<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		
		  <link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
        <!-- /. NAV SIDE  -->
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Ecommerce&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Log de eventos de pedidos</h3>   
                        <h6>Puede filtrar y seleccionar un pedido en particular </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
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
                        	<form class="form-horizontal" role="form"  action="<%=basePath%>/EcommerceLogPedido.do">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-12 control-label" for="level" class="col-md-4 control-label">Nivel de error:&nbsp;</label>
								          <div class="col-md-8">
								          	<select class="select2-multi col-sm-12" multiple="multiple" placeholder="Puede Seleccionar Varios" name="level">
										        <option value=""></option>
										    	<option value="1">Advertencia</option>
										    	<option value="0">Informaci?n</option>
										    	<option value="-1">Error</option>
										    	<option value="4">Cambios de estado</option>
										  	</select>
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-12 control-label" for="art" class="col-md-4 control-label">IdPedido:&nbsp;</label>
								          <div class="col-md-8">
								          <div class="input-group input-group-sm">
									            <input type="text" class="form-control bg-white" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="idPedido"/>
										  </div>
										  </div>
								        </div>
								    </div>
									<div class="col-sm-12 col-lg-12">
										<div class="container">
									        <div class="row">
	  											<div class="col align-self-end">
									            	<input type="submit" class="btn btn-info" value="Ver Logs"/>
									        	</div>
									    	</div>
								    	</div>
								    </div>
							</div>
							</form>
						</div>
                    <div class="card-header">
                             Listado de eventos de pedidos, si selecciona uno en particular, muestra todos los registros del pedido (sin tomar en cuenta los demas filtros) 
                        </div>
                        <div class="card-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                           <th class="text-center">Fecha</th>
	                                           <th class="text-center">Id Pedido</th>
	                                           <th class="text-center">Usuario</th>
											   <th class="text-center">Mensaje</th>
											   <th class="text-center">Nivel</th>
										    </tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="log" items="${listaEcommerceLogs}">
												<tr class="gradeD">
													<td class="text-center">${log.fecha}</td>
													<td class="text-center"><a href="<%=basePath%>EcommerceLogPedido.do?idPedido=${log.idpedido}"><button class="btn btn-success">${log.idpedido}</button></a></td>
													<td class="text-center">${log.usuario}</td>
													<td style="width: 70%; vertical-align: middle;">${log.mensaje}</td>
													<td class="text-center">
														<c:if test="${log.level==0}"><i class="fa fa-check" style="font-size:40px;color:green"></i></c:if>
														<c:if test="${log.level==-1}"><i class="fa fa-warning" style="font-size:40px;color:red"></i></c:if>
														<c:if test="${log.level==1}"><i class="fa fa-info" style="font-size:40px;color:#dfc12a"></i></c:if>
														<c:if test="${log.level==1}"><i class="fa fa-exchange" style="font-size:40px;color:#1E90FF"></i></c:if>
													</td>
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
		
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
