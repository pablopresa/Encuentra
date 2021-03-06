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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Alta Usuarios</h3>   
                        <h6>Complete todos los campos </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${mensajeE!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong> <c:out value="${mensajeE}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                             <h5>Formulario de alta de usuarios</h5>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" role="form" method="post"  action="<%=basePath%>/altaU.do">
                            	<div class="row">
																	
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="numero">Codigo:</label>
											<div class="col-md-10">
												<c:if test="${upd}">
													<c:set var="disabled" value="readonly=readonly"></c:set> 
												</c:if>												 
										  		<input required class="form-control" name="numero" id="numero" value="${numero}" <c:out value="${disabled}"></c:out> />
										  	</div>											
										</div>
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="cubi">Nombre:</label>
											<div class="col-md-10">
												<input required class="form-control" name="nombre" id="nombre" value="${nombre}"/>
											</div>
										 </div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="apellido">Apellido:</label>
											<div class="col-md-10">
										  		<input required class="form-control" name="apellido" id="apellido" value="${apellido}"/>
											</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="perfil">Dep?sito:</label>
											<div class="col-md-10">
										  		<select class="form-control" name="deposito" id="deposito" >
											  		<c:forEach var="d" items="${depositoU}">
											  			<c:if test="${d.id==deposito}">
											  				<option value="${d.id}" selected="selected">${d.descripcion}</option>
											  			</c:if>
											  			<c:if test="${d.id!=deposito}">
											  				<option value="${d.id}">${d.descripcion}</option>
											  			</c:if>
											  		</c:forEach>
											  	</select>
										  	</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="perfil">Perfil:</label>
											<div class="col-md-10">
										  		<select class="form-control" name="tipo" id="tipo" >
											  		<c:forEach var="t" items="${tiposU}">
											  			<c:if test="${t.id==tipo}">
											  				<option value="${t.id}" selected="selected">${t.descripcion}</option>
											  			</c:if>
											  			<c:if test="${t.id!=tipo}">
											  				<option value="${t.id}">${t.descripcion}</option>
											  			</c:if>
											  		</c:forEach>
											  	</select>
										  	</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="perfil">Permisos:</label>
											<div class="col-md-10">
										  		<select class="form-control" name="grPermiso" id="grPermiso" >
											  		<c:forEach var="d" items="${lstGruposAsociarPermisos}">
											  			<c:if test="${d.id==permiso}">
											  				<option value="${d.id}" selected="selected">${d.descripcion}</option>
											  			</c:if>
											  			<c:if test="${d.id!=permiso}">
											  				<option value="${d.id}">${d.descripcion}</option>
											  			</c:if>
											  		</c:forEach>
											  	</select>
										  	</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="login">Login:</label>
											<div class="col-md-10">
										  		<input required class="form-control" name="login" id="login" value="${login}"/>
											</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="p1">Password:</label>
											<div class="col-md-10">
										  		<input required class="form-control" name="p1" id="p1" value="${p1}" type="password"/>
											</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-12 control-label" for="p2">Repetir Password:</label>
											<div class="col-md-10">
										  		<input required class="form-control" name="p2" id="p2" value="${p2}" type="password"/>
											</div>
										</div>
									</div>
									<div class="col text-center">
										<button type="submit" class="btn btn-primary"><i class="feather icon-user-plus"></i>Alta Usuario</button>
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
