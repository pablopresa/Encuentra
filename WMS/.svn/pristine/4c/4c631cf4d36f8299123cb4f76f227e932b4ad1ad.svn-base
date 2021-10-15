<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

	<c:set var = "colapsar" scope = "request" value = "yes"/>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        <!-- /. NAV SIDE  -->
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Confirmar/verificar Diferencias</h3>   
                        <h6>Seleccione lo que desea controlar</h6>
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
                             Pickings con diferencias
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                           <th>Picking</th>
											<th>Destino/s</th>
											<th>Solicitada</th>
											<th>Encontrada</th>
											<th>Seleccionar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="p" items="${pickingsS}">
                                    		<c:if test="${p.solicitada!=p.encontrada}">
												<c:set var="coloPI" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${p.solicitada==p.encontrada}">
												<c:set var="coloPI" scope="page" value="#adebad"></c:set>
											</c:if>
											
											
											<tr>
												<td style="vertical-align: middle;"><div align="center"><c:out value="${p.id}"></c:out></div></td>
												<td style="vertical-align: middle;">
													<div align="center">
														<c:forEach var="d" items="${p.destinos}">
															<c:out value="${d.descripcion}"></c:out><br/>
														</c:forEach>
													</div>
												</td>
												<td style="vertical-align: middle;"><div align="center"><c:out value="${p.solicitada}"></c:out></div></td>
												<td style="vertical-align: middle; background-color: ${coloPI}"><div align="center"><c:out value="${p.encontrada}"></c:out></div></td>
												<td style="vertical-align: middle;">
													<a href="<%=basePath%>/DarPickingVC.do?idPick=${p.id}&accion=Diferencia">Seleccionar</a>
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
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
