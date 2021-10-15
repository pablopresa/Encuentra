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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Tipos de Estanteria</h3>   
                        <h6>Puede Modificar,agregar o eliminar </h6>
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
                             <h5>Estanterias</h5>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th style="visibility: hidden; width:1px; "></th>
											<th>Descripción</th>
											<th>Ancho (Ojo)</th>
											<th>Alto (Ojo)</th>
											<th>Profundidad</th>
											<th>Copiar</th>
											<th>Editar</th>
											<th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<tr class="odd gradeX">
                                    		<form action="<%=basePath%>altaTipoEstanteria.do" method="post">	
												<td style="visibility: hidden;"><div align="center" style="width: 1px;">999999<input type="text" name="idTipo" style="width: 95%" value="999999"/></div></td>
												<td><input type="text" name="descripcion" style="width: 95%" value="${descripcion}"/></td>
												<td><input type="text" name="ancho" style="width: 95%" value="${ancho}"/></td>
												<td><input type="text" name="alto" style="width: 95%" value="${alto}"/></td>
												<td><input type="text" name="profundidad" style="width: 95%" value="${profundidad}"/></td>
												<td><input type="submit" value="NUEVA" style="width: 95%"/> </td>
												<td><div align="center">-----</div></td>
												<td><div align="center">-----</div></td>
											</form>	
                                    	</tr>
									
										<c:forEach var="t" items="${tiposS}">
											
											<c:if test="${t.idTipo!=idEdita}">										
												<tr class="gradeD">
													<td style="visibility: hidden;"><div align="center" style="width: 1px;"><c:out value="${t.idTipo}"></c:out></div></td>
													<td><div align="center"><c:out value="${t.descripcion}"></c:out></div></td>
													<td><div align="center"><c:out value="${t.ancho}"></c:out>&nbsp;mm</div></td>
													<td><div align="center"><c:out value="${t.alto}"></c:out>&nbsp;mm</div></td>
													<td><div align="center"><c:out value="${t.profundidad}"></c:out></div></td>
													<td><div align="center"><a href="<%=basePath%>copiarTipoEstanteria.do?idTipo=${t.idTipo}"><img alt="Copiar" src="<%=basePath%>/imagenes/icons/package.png">   </a> </div></td>
													<td><div align="center"><a href="<%=basePath%>editaTipoEstanteria.do?idTipo=${t.idTipo}"><img alt="Editar" src="<%=basePath%>/imagenes/icons/package_go.png"></a> </div></td>
													<td><div align="center"><a href="#"
													
													onClick="if(confirm('¿Seguro que desea eliminar el tipo de estantería: ${t.descripcion}?'))
																{
																	window.location='<%=basePath%>EliminarTipoEstanteria.do?idTipo=${t.idTipo}'
																}
																else {
																	alert('Sin Cambios')
																	}">
													<img alt="Eliminar" src="<%=basePath%>/imagenes/icons/package_delete.png"></a> </div></td>
											
													
												</tr>
											</c:if>
											<c:if test="${t.idTipo==idEdita}">
												<tr class="gradeD">
													<form action="<%=basePath%>altaTipoEstanteria.do" method="post">	
														<td style="visibility: hidden;"><div align="center" style="width: 1px;"><input type="text" name="idTipo" style="width: 95%" value="${t.idTipo}"/><c:out value="${t.idTipo}"></c:out></div></td>
														<td><input type="text" name="descripcion" style="width: 95%" value="${t.descripcion}"/></td>
														<td><input type="text" name="ancho" style="width: 95%" value="${t.ancho}"/></td>
														<td><input type="text" name="alto" style="width: 95%" value="${t.alto}"/></td>
														<td><input type="text" name="profundidad" style="width: 95%" value="${t.profundidad}"/></td>
														<td><input type="submit" value="GUARDAR" style="width: 95%" > </td>
														<td><div align="center">-----</div></td>
														<td><div align="center">-----</div></td>
														
														
													</form>	
												</tr>
											</c:if>
														
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
