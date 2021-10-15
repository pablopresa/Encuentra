<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                           <th>Fecha</th>
	                                           <th>Id Pedido</th>
												<th>Mensaje</th>
												<th>Nivel</th>
										    </tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="log" items="${listaEcommerceLogs}">
												<tr class="gradeD">
													<td>${log.fecha}</td>
													<td><a href="<%=basePath%>EcommerceLogPedido.do?idPedido=${log.idpedido}"><button class="btn btn-success">${log.idpedido}</button></a></td>
													<td style="width: 70%">${log.mensaje}</td>
													<td>
														<c:if test="${log.level==0}"><i class="fa fa-check" style="font-size:40px;color:green"></i></c:if>
														<c:if test="${log.level==-1}"><i class="fa fa-warning" style="font-size:40px;color:red"></i></c:if>
														<c:if test="${log.level==1}"><i class="fa fa-info" style="font-size:40px;color:#dfc12a"></i></c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
	                            </div>