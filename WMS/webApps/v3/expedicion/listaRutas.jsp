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
                    	<h3>Expedicion&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Rutas</h3>   
                        <h6>Puede Modificar agregar o eliminar </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                  
                  
                  
                  
                  <div class="col-sm-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    
                    
                    
                    <div class="card">
                        <div class="card-header">
                             <h5>Rutas definidas</h5>
                        </div>
                        <div class="card-block">
                            <div class="table-responsive">
                            <form action="<%=basePath%>AltaRuta.do">
                                <table class="display table nowrap table-striped table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
											<th>Descripción</th>
											<th>Ver Depositos</th>
											<th>Copiar</th>
											<th>Editar</th>
											<th>Eliminar</th>
                                        </tr>
									</thead>
                                    <tbody>
										<tr>
											<td>
												<c:choose>
												    <c:when test="${paraR==4}">
												       <input type="number" name="id" value="${rutaSel.idRuta}" readonly="readonly" style="width: 100%" required/>
												    </c:when>
												    <c:when test="${paraR==3}">
												       <input type="number" name="id" style="width: 100%" readonly="readonly" value="0" required/>
												    </c:when>
												    <c:otherwise>
												        <input type="number" name="id" style="width: 100%" readonly="readonly" value="0" required/>
												    </c:otherwise>
												</c:choose>
												  
											</td>
											<td><input type="text" name="descripcion" value="${rutaSel.descripcion}" style="width: 100%" required/> </td>
											
											<c:choose>
												    <c:when test="${paraR==4}">
												      <td><input type="submit" value="Guardar Cambios" style="width: 100%; font-weight: bolder;"></td>
												    </c:when>
												    <c:when test="${paraR==3}">
												       <td><input type="submit" value="Guardar" style="width: 100%; font-weight: bolder;"></td>
												    </c:when>
												    <c:otherwise>
												        <td><input type="submit" value="Guardar" style="width: 100%; font-weight: bolder;"></td>
												    </c:otherwise>
											</c:choose>
											
											<td><input style="visibility: hidden;" type="text" name="paraR" value="${paraR}" style="width: 100%"/></td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										
										<c:forEach var="r" items="${rutas}">
											<tr class="gradeD">
												
												<td><c:out value="${r.idRuta}"></c:out></td>
												<td><div align="center"><c:out value="${r.descripcion}"></c:out></div></td>
												<td>
													<div align="center">
														<a href="<%=basePath%>darRutasDepos.do?id=${r.idRuta}&paraQuien=5" type="button" class="btn btn-icon btn-rounded btn-secondary"><i class="feather icon-eye"></i></a>
														<a href="<%=basePath%>editarRutasDepos.do?id=${r.idRuta}&paraQuien=6" type="button" class="btn btn-icon btn-rounded btn-secondary"><i class="feather icon-sliders"></i></a>
													</div>
												</td>
												<td><div align="center"><a href="<%=basePath%>darRutas.do?paraQuien=3&id=${r.idRuta}" type="button" class="btn btn-icon btn-rounded btn-primary"><i class="feather icon-copy"> </i></a> </div></td>
												<td><div align="center"><a href="<%=basePath%>darRutas.do?paraQuien=4&id=${r.idRuta}" type="button" class="btn btn-icon btn-rounded btn-warning"><i class="feather icon-edit"> </i></a> </div></td>
												<td><div align="center"><a href="#"
												
												onClick="if(confirm('¿Seguro que desea eliminar la ruta: ${r.descripcion}?'))
															{
																window.location='<%=basePath%>AltaRuta.do?paraR=-1&id=${r.idRuta}&descripcion=${r.descripcion}'
															
															}
															else {
																alert('Sin Cambios')
																}"
												type="button" class="btn btn-icon btn-rounded btn-danger"><i class="feather icon-trash-2"> </i></a> </div></td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                                </form>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
          
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
