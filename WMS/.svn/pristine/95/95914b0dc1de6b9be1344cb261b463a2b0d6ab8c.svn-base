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
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Reporte Recorrido&nbsp;</h3>   
                        
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                    	
                    	<div class="card-header">
                             Filtre por con o sin recorrido
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/reporteRecorridoOjos.do">
                            	<div class="row">

									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="estanteria"
												class="col-md-4 control-label">Seleccionar Filtro:&nbsp</label>
											<div class="col-md-8">
												<select name="frecorrido" class="form-control">
													<option value="sin">Ubicaciones sin Recorrido</option>
													<option value="con">Ubicaciones con Recorrido</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
										  	</div>											
										</div>
									</div>
								</div>
							</form>
                            
                        </div>
                    	
                        <div class="card-header">
                             Ubicaciones y su recorrido
                        </div>
                        <div class="card-body">
                        	
							<hr>
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                           <th>Ubicación</th>
											<th>Ojo</th>
											<th>Recorrido</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="u" items="${ubicaciones}">
											<tr class="gradeD">											
												<td><c:out value="${u.descripcion}"></c:out></td>
												<td><c:out value="${u.descripcionB}"></c:out></td>
												<td><c:out value="${u.id}"></c:out></td>
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
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
