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
		<div id="page-wrapper">
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Clasificacion <i class="fa fa-angle-double-right" aria-hidden="true"></i> Detalle a clasificar</h3>   
                        <h6>Seleccione la que desea controlar</h6>
                    </div>
                </div>              
                 
                <hr/>
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"/></strong>            
                    </div>
                    </c:if>
                    
                    <div class="card">
                        <div class="card-header">
                             Detalle de lectura del archivo son ${lecturaCantidad} a ${lecturaDestinos} destinos
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                           <th>Articulo</th>
											<th>Cantidad</th>
											<th>Destino</th>
											
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="a" items="${lectura}">
										<tr class="gradeD">
											<td>${a.descripcion}</td>
											<td>${a.id}</td>
											<td>${a.descripcionB}</td>
										</tr>
									</c:forEach>
                                    </tbody>
                                    <tfoot>
                                    	<tr>
                                    		<td>
                                    			&nbsp;
                                    		</td>
                                    		<td>
                                    			<a href="<%=basePath%>/v3/AsignarListaToSort.jsp"> <button type="button" class="btn btn-info">Cargar de nuevo</button></a>
                                    		</td>
                                    		<td>
                                    			<a href="<%=basePath%>/AltaSort.do"> <button type="button" class="btn btn-info">Confirmar clasificacion</button></a>
                                    		</td>
                                    		
                                    	</tr>
                                    </tfoot>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    
                </div>
            </div>
                
            </div>
             
		</div>
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
