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
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Expedición&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Lista de Envíos Parciales</h3>   
                        <h6>Puede Retomar, Finalizar o eliminar </h6>
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
                        	<h5>
                        		Envios Parciales
                        	</h5>
                             
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th style="text-align: center;">Envio</th>
											<th style="text-align: center;">Fecha</th>
											<th style="text-align: center;">Transporte</th>
											<th style="text-align: center;">Chofer</th>
											<th style="text-align: center;">Acompañante</th>
											<th style="text-align: center;">Depositos</th>
											<th style="text-align: center;">Ver documentos</th>
											<th style="text-align: center;">Accion</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="en" items="${envios}">
											<tr class="gradeD">
												<td style="text-align: center;" class="center"><c:out value="${en.idEnvio}"></c:out></td>
												<td style="text-align: center;" class="center"><c:out value="${en.fechaVis}"></c:out></td>
												<td style="text-align: center;" class="center"><c:out value="${en.transporte.descripcion}"></c:out></td>
												<td style="text-align: center;" class="center"><c:out value="${en.chofer.descripcion}"></c:out></td>
												<td style="text-align: center;" class="center"><c:out value="${en.acompaniante.descripcion}"></c:out></td>
												<td style="text-align: center;" class="center">
													<c:forEach var="de" items="${en.depositos}">
														&nbsp;-&nbsp;<c:out value="${de.idDeposito}"></c:out>
													</c:forEach>
												</td>
												<td style="text-align: center;" class="center"><a href="<%=basePath%>/AccionEnvio.do?idEnvio=${en.idEnvio}&accion=1">Documentos</a></td>
												<c:if test="${en.estado.id==2}">
													<td  style="text-align: center;" class="center">
														<a href="<%=basePath%>/AccionEnvio.do?idEnvio=${en.idEnvio}&accion=4" class="btn btn-danger">Eliminar</a>
														&nbsp;<a href="<%=basePath%>/AccionEnvio.do?idEnvio=${en.idEnvio}&accion=2" class="btn btn-info">Retomar</a>
														&nbsp;<a href="<%=basePath%>/AccionEnvio.do?idEnvio=${en.idEnvio}&accion=3" class="btn btn-success">Pasar a envío</a> 
														
													</td>
												</c:if>
												
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
