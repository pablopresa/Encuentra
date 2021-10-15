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
                    	<h3>Nueva Tarea&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Ordenes de Almacen pendientes</h3>   
                        <h6>Puede asignar la que necesite</h6>
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
                             Lista de Ordenes
                        </div>
                        <div class="card-body">
                        	<div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                <thead style="border: 1px solid; vertical-align: middle;">
									<tr class="gradeU odd">
										<th style="display: none; width:1px; "></th>
										<th style="display: none; width:1px; "></th>
										<th>Id interno</th>
										<th>Fecha (AAAA-MM-DD)</th>
										<th>Cantidad de unidadas</th>
										<th></th>
									</tr>
								</thead>
                                    <tbody>
                                    	<form action="<%=basePath%>AltaTareaIII.do?type=R" id="f1"	name="f1">
											<c:forEach var="a" items="${almacen}">
												<tr class="gradeD">
													<td style="display: none;"><div align="center"	style="width: 1px;"><input type="text" name="idRecepcion" style="width: 95%" value="${a.id}" />	</div></td>
													<td style="display: none;"><div align="center"	style="width: 1px;"><input type="text" name="type" style="width: 95%" value="A" /></div></td>
													<td><div align="center">
															<c:out value="${a.id}"></c:out>
														</div></td>
													<td><div align="center">
															<c:out value="${a.fecha}"></c:out>
														</div></td>
													<td><div align="center">
															<c:out value="${a.cantidad}"></c:out>
														</div></td>												
													<td><div align="center"><a href="<%=basePath%>AltaTareaIII.do?type=A&idAlmacen=${d.id}"><input type="button"  class="button" value="Asignar"/></a></div></td>
		
												</tr>
												
											</c:forEach>
									</form>
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
