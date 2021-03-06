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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Depósitos</h3>   
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
                             <h5>Depositos</h5>
                        </div>
                        <div class="card-block">
                            <div class="table-responsive">
                            <form action="<%=basePath%>AltaDeposito.do">
                                <table class="display table nowrap table-striped table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
											<th>Descripción</th>
											<th>Direccion</th>
											<th>Disp. Login</th>
											<th>Imprimir</th>
											<th>Copiar</th>
											<th>Editar</th>
											<th>Eliminar</th>
                                        </tr>
									</thead>
                                    <tbody>
										<tr>
											<td>
												<c:choose>
												    <c:when test="${para==4}">
												       <input type="number" name="id" value="${depoSel.id}" readonly="readonly" style="width: 100%" required/>
												    </c:when>
												    <c:when test="${para==3}">
												       <input type="number" name="id" style="width: 100%" required/>
												    </c:when>
												    <c:otherwise>
												        <input type="number" name="id" style="width: 100%" required/>
												    </c:otherwise>
												</c:choose>
												  
											</td>
											<td><input type="text" name="descripcion" value="${depoSel.nombre}" style="width: 100%" required/> </td>
											<td><input type="text" name="direccion"  value="${depoSel.direccion}" style="width: 100%" required/> </td>
											<td>
											<c:if test="${depoSel==null}">
												<select name="login" style="width: 100%">
													<option value="0">NO</option>
													<option value="1">SI</option>
												</select>
											</c:if>
											<c:if test="${depoSel!=null}">
												<select name="login" style="width: 100%">
													<c:if test="${depoSel.login==1}">
													<option value="0">NO</option>
													<option value="1" selected="selected">SI</option>
													</c:if>
													<c:if test="${depoSel.login!=1}">
													<option value="0" selected="selected">NO</option>
													<option value="1" >SI</option>
													</c:if>
												</select>
											</c:if> 
											</td>
											<c:choose>
												    <c:when test="${para==4}">
												      <td><input type="submit" value="Guardar Cambios" style="width: 100%; font-weight: bolder;"></td>
												    </c:when>
												    <c:when test="${para==3}">
												       <td><input type="submit" value="Guardar" style="width: 100%; font-weight: bolder;"></td>
												    </c:when>
												    <c:otherwise>
												        <td><input type="submit" value="Guardar" style="width: 100%; font-weight: bolder;"></td>
												    </c:otherwise>
											</c:choose>
											
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										
										<c:forEach var="d" items="${depositosA}">
											<tr class="gradeD">
												
												<td><c:out value="${d.id}"></c:out></td>
												<td><div align="center"><c:out value="${d.nombre}"></c:out></div></td>
												<td><div align="center"><c:out value="${d.direccion}"></c:out></div></td>
												<td><div align="center"><c:if test="${d.login==1}">SI</c:if> <c:if test="${d.login!=1}">NO</c:if></div></td>
												<td><div align="center"><a href="<%=basePath%>PrintLabelDestinos.do?idDestiny=${d.id}&descDestiny=${d.nombre}" type="button" class="btn btn-icon btn-rounded btn-secondary"><i class="feather icon-printer"></i></a></div></td>
												<td><div align="center"><a href="<%=basePath%>EditaCopiaDeposito.do?paraQuien=3&id=${d.id}" type="button" class="btn btn-icon btn-rounded btn-primary"><i class="feather icon-copy"> </i></a> </div></td>
												<td><div align="center"><a href="<%=basePath%>EditaCopiaDeposito.do?paraQuien=4&id=${d.id}" type="button" class="btn btn-icon btn-rounded btn-warning"><i class="feather icon-edit"> </i></a> </div></td>
												<td><div align="center"><a href="#"
												
												onClick="if(confirm('¿Seguro que desea eliminar el depósito: ${d.nombre}?'))
															{
																window.location='<%=basePath%>EliminaDeposito.do?id=${d.id}'
															
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
