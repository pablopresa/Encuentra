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
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Nueva Orden de Almacen</h3>   
                        <h6>Estos son todos los articulos que estan en la zona de recepcion</h6>
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
                        	<h5>
                        		Listado de Articulos
                        	</h5>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="<%=basePath%>/ordenAlmacen.do">
                            	<input type="text" style="visibility: hidden;" name = "save" value ="true">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th style="text-align: center; width: auto;">Articulo</th>
											<th style="text-align: center; width: auto;">Cantidad</th>
											<th style="text-align: center; width: auto;"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="a" items="${articulos}">
											<tr class="gradeD">
												
												<td style="text-align: center; width: auto;">${a.descripcion}</td>
												<td style="text-align: center; width: auto;">${a.id}</td>
												<td style="text-align: center; width: auto;">
												<div class="checkbox checkbox-fill d-inline">
													<input type="checkbox" name="${a.descripcion}" id="checkbox-fill-${a.descripcion}" value="true">
													<label for="checkbox-fill-${a.descripcion}" class="cr"></label>
												</div>												
												</td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                                
                                <div class="col-sm-6 col-lg-4">
										<div class="form-group">											
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Ingresar Orden" />
										  	</div>											
										</div>
									</div>
                                </form>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
