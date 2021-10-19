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
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Cargar desde Archivo</h3>   
                        <h6>Cargue el archivo y despues guarde los datos</h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                             Actualizar/Agregar a ojo
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" enctype="MULTIPART/FORM-DATA" method="post" action="<%=basePath%>/cargarFileRepo.do">
                            	<div class="row">
                            			<div class="col-sm-6 col-lg-4">
	                            	<label class="col-md-4 control-label" class="col-md-4 control-label"></label>
								         <div class="input-group mb-3">
								         	  <input type="file" name="file">
								         	  
								        </div>
								    </div>
									<div class="col-sm-6 col-lg-4">									
										<div class="form-group">
											<label class="col-md-4 control-label" for="archi" class="col-md-4 control-label"></label>
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Vista previa" />
										  	</div>											
										</div>
									</div>
								</div>
							</form>
							<hr>
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" >
                                    <thead>
                                        <tr>
                                           <th>Bulto</th>
                                           <th>Articulo</th>
										   <th>Cantidad</th>
										   <th>Origen</th>
										   <th>Destino</th>
										 </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="m" items="${resultMov}">
                                    		<c:forEach var="l" items="${m.aMover}">
											<tr class="gradeD">
												<td> <c:out value="${l.idBulto}"></c:out> </td>
												<td> <c:out value="${l.idArticulo}"></c:out> </td>
												<td> <c:out value="${l.cantidad}"></c:out> </td>
												<td> <c:out value="${m.ubicacionOrigen}"></c:out> </td>
												<td> <c:out value="${m.ubicacionDestino}"></c:out> </td>
											</tr>	
											</c:forEach>
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
