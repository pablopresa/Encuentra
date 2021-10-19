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
                    	<h3>Almacen&nbsp;<i class="fas fa-angle-double-right" aria-hidden="true"></i>&nbsp;Informes&nbsp;<i class="fas fa-angle-double-right" aria-hidden="true"></i>&nbsp;Cantidades por estanterias&nbsp;</h3>   
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
                    
                    <div class="card ">
                    	<div class="card-header">
                             <h5>Filtre por Estanteria</h5>
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/reporteCantidadXEstanteria.do">
                            	<div class="row">
	                            	<div class="col-sm-6 col-lg-4">
								        <div class="form-group">								          
								          <div class="col-md-8">
								            	<select name="estanteria" class="form-control">
													<option value="" selected="selected">Todas</option>
														<c:forEach var="e" items="${estanteriasTodas}">
															<option value="${e.id}"><c:out value="${e.descripcion}"></c:out> </option>
														</c:forEach>
												</select>
								          </div>
								        </div>
								    </div>
															
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
										  	</div>											
										</div>
									</div>
								</div>
							</form>
                            
                        </div>
                    	
                        <div class="card-header">
                             <h5>Cantidad de articulos por estanteria</h5>
                        </div>
                        <div class="card-body">
                        	
							<hr>
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                           <th>Estanteria</th>
											<th>Cantidad de articulos</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="e" items="${Ests}">
											<tr class="gradeD">											
												<td><a href="<%=basePath%>DarOjosArti.do?estanteria=${e.descripcion}">${e.descripcion}</a></td>
												<td><c:out value="${e.id}"></c:out></td>
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
		 <script type="text/javascript">
        function deleteLinea(ojo,art,cant)
			{
				 if(confirm("Seguro desea dar de baja esa linea?"))
				 {
					 var req = "<%=basePath%>/EcommerceBajarLineaUbicaciones.do?idArt="+art+"&idOjo="+ojo+"&cantidad="+cant;
					 window.location.assign(req);
				 }
				
				
				
			}
        </script>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
