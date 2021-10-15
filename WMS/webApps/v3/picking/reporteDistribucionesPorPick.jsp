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
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Reporte Distribuciones</h3>   
                        <h6>Distribuciones asociadas a pickings </h6>
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
						<form class="form-horizontal" role="form" method="post"	action="<%=basePath%>/DistribucionesPicking.do">
							<div class="row">
								<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												    <label class="col-md-4 control-label" for="fini">Fecha:&nbsp;</label>
													<div class="col-md-8">
											           <input class="form-control form-control-sm bg-white rango" type="text" name="fini" id="fini">
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
                        		 Pickings
                        	</h5>
                            
                        </div>
						<div class="card-body">
							<div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                          	<th>Picking</th>
                                           	<th>Distribuciones</th>
											<th>Solicitadas</th>
											<th>Verificadas</th>
											<th>Usuario</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="p" items="${picks_distribuciones}">
											<tr class="gradeD">
												<td>${p.int1}</td>
												<td>${p.str1}</td>
												<td>${p.int2}</td>
												<td>${p.int3}</td>
												<td>${p.str2}</td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
						</div>	
					</div>	                         
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
            
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
