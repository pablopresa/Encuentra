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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Cambiar contraseña</h3>   
                        <h6>Complete todos los campos </h6>
                    </div>
                </div>
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                             <h5>Formulario de cambio de contraseña</h5>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" role="form" method="post"  action="<%=basePath%>cambioContrasena.do?mob=0">
                            	<div class="row">
																	
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="p1">Password:</label>
											<div class="col-md-8">
										  		<input required class="form-control" name="p1" id="p1" value="${p1}" type="password"/>
											</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="control-label" for="p2">Repetir Password:</label>
											<div class="col-md-8">
										  		<input required class="form-control" name="p2" id="p2" value="${p2}" type="password"/>
											</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="p2">&nbsp;</label>
											<div class="col-md-8">
										  		<button type="submit" class="btn btn-info">Confirmar Cambio</button>
											</div>
										</div>
									</div>
									
	                                
	                        	</div>
	                        </form>
                        </div>
                        
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
