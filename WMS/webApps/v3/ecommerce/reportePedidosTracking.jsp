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
                    	<h3>Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Tracking / Pedidos</h3>   
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
                             	Filtro por fechas
                            </h5> 
                        </div>
					<div class="card-body">
						<form class="form-horizontal" role="form" method="post"	action="<%=basePath%>/reportePedidosTracking.do">
							<div class="row">   
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
                             	Trackings por Pedidos
                            </h5> 
                        </div>
					<div class="card-body">
						<div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                          	<th style="text-align: center;">Pedido</th>
                                           	<th style="text-align: center;">N? Tracking </th>
											<th style="text-align: center;">Fecha</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="t" items="${pedidosT}">
											<tr class="gradeD">
												<td style="text-align: center;"><c:out value="${t.id}"></c:out></td>
												<td style="text-align: center;"><a href="https://forus.fenicio.com.uy/paquetes?sku=&sitio=&referenciaVisual=${t.id}" target="_blank">${t.descripcion}</a></td>
												<td style="text-align: center;"><c:out value="${t.descripcionB}"></c:out></td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
					</div>
				</div>

								
					
					
							
                            
                            
                        </div>
                    </div>
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
