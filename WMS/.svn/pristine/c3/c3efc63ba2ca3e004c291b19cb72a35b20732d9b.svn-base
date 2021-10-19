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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Tipos de SKU</h3>   
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
                             Tipos de SKU
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th style="visibility: hidden; width:1px; "></th>
											<th>Descripción</th>
											<th>Editar</th>
											<th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="t" items="${types}">
										<c:if test="${t.id!=idEdita}">										
											<tr class="gradeD">
												<td style="visibility: hidden;"><div align="center" style="width: 1px;"><c:out value="${t.id}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.descripcion}"></c:out></div></td>
												<td><div align="center"><a href="<%=basePath%>editaTipoSKU.do?idTipo=${t.id}"><img alt="Editar" src="<%=basePath%>/imagenes/icons/package_go.png"></a> </div></td>
												<td><div align="center"><a href="#"
												
												onClick="if(confirm('Seguro que desea eliminar este tipo de SKU?'))
															{
																window.location='<%=basePath%>EliminarTipoSKU.do?idTipo=${t.id}&esSKU=1'
															}
															else {
																alert('Sin Cambios')
																}">
												<img alt="Eliminar" src="<%=basePath%>/imagenes/icons/package_delete.png"></a> </div></td>
											</tr>
										</c:if>
										<c:if test="${t.id==idEdita}">
											<tr class="gradeD">
												<form action="<%=basePath%>altaTipoSKU.do" method="post">	
													<td style="visibility: hidden;"><div align="center" style="width: 1px;"><input type="text" name="idTipo" style="width: 95%" value="${t.id}"/><c:out value="${t.id}"></c:out></div></td>
													<td><input type="text" name="descripcion" style="width: 95%" value="${t.descripcion}"/>
													<input type="text" name="esSKU" style="width: 1px; visibility: hidden;" value="1"/></td>
													<td><input type="submit" value="GUARDAR" style="width: 95%" > </td>
													<td><div align="center">-----</div></td>
												</form>	
											</tr>
										</c:if>			
									</c:forEach>
										<tr class="gradeD">
											<form action="<%=basePath%>altaTipoSKU.do" method="post">	
												<td style="visibility: hidden;"><div align="center" style="width: 1px;">999999<input type="text" name="idTipo" style="width: 95%" value="999999"/></div></td>
												<td><input type="text" name="descripcion" style="width: 95%" value="${descripcion}"/>
												<input type="text" name="esSKU" style="width: 1px; visibility: hidden;" value="1"/>
												</td>
												<td><input type="submit" value="NUEVA" style="width: 95%"/></td>
												<td><div align="center">-----</div></td>
												
											</form>	
										</tr>
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
