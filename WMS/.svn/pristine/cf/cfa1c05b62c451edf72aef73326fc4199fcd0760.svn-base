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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Articulos</h3>   
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
                             Articulos
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="<%=basePath%>DarArticulos.do" method="post">
									<table class="table table-striped table-bordered table-hover" >
										<tr>
										<td><input type="text" name="texto" style="width: 95%" value="${texto}" /></td>
										<td><input type="submit" value="Buscar" style="width: 95%" /></td>
										</tr>
									</table>
								</form>
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th>Identificador</th>
											<th>Descripción</th>
											<th>Ancho</th>
											<th>Alto</th>
											<th>Profundidad</th>
											<th>Stock en depósito</th>
											<th>Tipo de SKU</th>
											<th>Editar</th>
											<th>Eliminar</th>
											<th style="visibility: hidden; width:1px; "></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="t" items="${articulos}">
										<c:if test="${t.id!=idEdita}">											
											<tr class="gradeD">
												<td><div align="center"><c:out value="${t.id}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.descripcion}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.anchoCaja}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.altoCaja}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.profCaja}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.cantidad}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.typeSKU}"></c:out></div></td>
												<td><div align="center"><a href="<%=basePath%>editarArticulo.do?idArt=${t.id}"><img alt="Editar" src="<%=basePath%>/imagenes/icons/package_go.png"></a> </div></td>
												<td><div align="center"><a href="#"
												onClick="if(confirm('Seguro que desea eliminar este Articulo?'))
												{
												window.location='<%=basePath%>EliminarArticulo.do?id=${t.id}'
												}
												else {
													alert('Sin Cambios')
												}">
												<img alt="Eliminar" src="<%=basePath%>/imagenes/icons/package_delete.png"></a> </div></td>
												<td style="visibility: hidden;"><div align="center" style="width: 1px;"><c:out value="${t.idTypeSKU}"></c:out></div></td>
											</tr>
										</c:if>
										<c:if test="${t.id==idEdita}">
											<tr class="gradeD">
												<form action="<%=basePath%>altaArticulo.do" method="post">	
													<td><input type="text" name="Identificador" style="width: 95%" value="${t.id}" /></td>
													<td><input type="text" name="Descripcion" style="width: 95%" value="${t.descripcion}" /></td>
													<td><input type="text" name="Ancho" style="width: 95%" value="${t.anchoCaja}" /></td>
													<td><input type="text" name="Alto" style="width: 95%" value="${t.altoCaja}" /></td>
													<td><input type="text" name="Profundidad" style="width: 95%" value="${t.profCaja}" /></td>
													<td><input type="text" name="Stock" style="width: 95%" value="${t.cantidad}" /></td>
													<td><select name="skuType"  style="width: 95%" >
															<c:forEach var="ty" items="${types}">
																<option value="${ty.id}"><c:out value="${ty.descripcion}"></c:out> </option>
															</c:forEach>
													</select></td>
													<td><input type="submit" value="GUARDAR" style="width: 95%" > </td>
													<td><div align="center">-----</div></td>
												    <td style="visibility: hidden;"><div align="center" style="width: 1px;"><input type="text" name="function" style="width: 95%" value="100000"/></div></td>
												</form>	
											</tr>
										</c:if>			
									</c:forEach>
											 <tr class="gradeD">
												<form action="<%=basePath%>altaArticulo.do" method="post">
													<td><input type="text" name="Identificador" style="width: 95%" value="${id}" /></td>
													<td><input type="text" name="Descripcion" style="width: 95%" value="${descripcion}" /></td>
													<td><input type="text" name="Ancho" style="width: 95%" value="${anchoCaja}" /></td>
													<td><input type="text" name="Alto" style="width: 95%" value="${altoCaja}" /></td>
													<td><input type="text" name="Profundidad" style="width: 95%" value="${profCaja}" /></td>
														<td><input type="text" name="Stock" style="width: 95%" value="${cantidad}" /></td>
													<td><select name="skuType"  style="width: 95%" >
															<c:forEach var="ty" items="${types}">
																<option value="${ty.id}"><c:out value="${ty.descripcion}"></c:out> </option>
															</c:forEach>
													</select></td>
													<td><input type="submit" value="NUEVA" style="width: 95%" /></td>
												    <td><div align="center">-----</div></td>
												    <td style="visibility: hidden;"><div align="center" style="width: 1px;"><input type="text" name="function" style="width: 95%" value="999999"/></div></td>
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
