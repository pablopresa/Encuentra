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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Estanterías</h3>   
                        <h6>Puede Modificar agregar o eliminar </h6>
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
                             <h5>Estanterías</h5>
                        </div>
                        <div class="card-body">
                             <div class="table-responsive">
							    <table class="table table-striped table-bordered table-hover" id="encuentra-default">
							        <thead class="thead-dark">
                                        <tr>
											<th class="text-center">Descripción</th>
											<th class="text-center">Depósito</th>
											<th class="text-center">Estantes</th>
											<th class="text-center">Modulos</th>
											<th class="text-center">Uso</th>
											<th class="text-center">Copiar</th>
											<th class="text-center">Editar</th>
											<th class="text-center">Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="e" items="${sectoresL}">
											<tr class="gradeD">												
												<td class="text-center"><c:out value="${e.descripcion}"></c:out></td>
												<td class="text-center"><c:out value="${e.deposito}"></c:out></td>
												<td class="text-center"><c:out value="${e.estantes}"></c:out></td>
												<td class="text-center"><c:out value="${e.modulos}"></c:out></td>
												<td class="text-center"><c:out value="${e.uso}"></c:out></td>
												<td class="text-center"><a href="<%=basePath%>darTiposSector.do?paraQuien=3&id=${e.id}"><button class="btn btn-primary"><span class="feather icon-copy"></span></button></a> </td>
												<td class="text-center"><a href="<%=basePath%>darTiposSector.do?paraQuien=4&id=${e.id}"><button class="btn btn-warning"><span class="feather icon-edit"></span></button></a> </td>
												<td class="text-center"><a href="#"
												
												onClick="if(confirm('¿Seguro que desea eliminar la estantería: ${e.descripcion}?'))
															{
																window.location='<%=basePath%>EliminarEstanteria.do?idTipo=${e.id}'
																
																
															}
															else {
																alert('Sin Cambios')
																}">
												<button class="btn btn-danger"><span class="fas fa-trash-alt"></span></button></a> </td>
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
