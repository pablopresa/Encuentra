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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Usuarios</h3>   
                        <h6>Puede modificar agregar o eliminar </h6>
                    </div>
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
                        	<h5><a href="<%=basePath%>darTiposU.do" class="btn btn-info"><i class="feather icon-user-plus"></i>Nuevo Usuario</a></h5>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="text-center">Codigo</th>
											<th class="text-center">Nombre</th>
											<th class="text-center">Nick</th>
											<th class="text-center">Perfil</th>
											<th class="text-center">Editar</th>
											<th class="text-center">Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="u" items="${users}">
											<tr class="gradeD">
												
												<td class="text-center">${u.numero}</td>
												<td class="text-center">${u.nombre} ${u.apellido}</td>
												<td class="text-center">${u.nick}</td>
												<td class="text-center">${u.perfilDesc}</td>
												<td class="text-center"><a href="<%=basePath%>indexUsuarios.do?action=update&idUser=${u.numero}"><button class="btn btn-warning"><span class="feather icon-edit"></span></button></a></td>
											<td class="text-center"><a href="#"
												onClick="if(confirm('Seguro que desea eliminar al usuario ${u.nombre} ${u.apellido}?'))
															{
																window.location='<%=basePath%>indexUsuarios.do?action=delete&idUser=${u.numero}'
															}">
													<button class="btn btn-danger"><span class="fas fa-trash-alt"></span></button></a>
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
