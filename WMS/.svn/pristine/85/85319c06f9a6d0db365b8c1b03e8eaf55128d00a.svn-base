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
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Informe&nbsp;
                    	<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Articulos sin medidas </h3>   
                        
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
                             Articulos sin medidas
                        </div>
                        <div class="card-body">
                        	
							<hr>
                            <div class="table-responsive">
                            <form class="form-horizontal" role="form" method="post" action="<%=basePath%>/ArticulosAsignarMedidas.do?art=">
                            	<table class="table table-bordered table-hover" id="encuentra-defaultSM">
                                    <thead>
                                        <tr>
                                           <th style="padding: 10px; vertical-align: middle; text-align: center;">Articulo</th>											
											<th style="padding: 10px; vertical-align: middle; text-align: center;">Cod ubicacion</th>
											<th style="padding: 10px; vertical-align: middle; text-align: center;">Estanteria</th>
											<th style="padding: 10px; vertical-align: middle; text-align: center;">Modulo</th>
											<th style="padding: 10px; vertical-align: middle; text-align: center;">Estante</th>
											<th style="padding: 10px; vertical-align: middle; text-align: center;">Ancho</th>
											<th style="padding: 10px; vertical-align: middle; text-align: center;">Alto</th>
											<th style="padding: 10px; vertical-align: middle; text-align: center;">Profundidad</th>
											<th style="padding: 10px; vertical-align: middle; text-align: center; width: 5%">
												<input type="submit" class="btn btn-info" name="AsignarTodos" style="text-align:center;" value="Asignar Todos"/>
											</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="sm" items="${sinmedidas}">
											<tr class="gradeD">
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${sm.articulo}</td>
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${sm.idOjo}</td>
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${sm.idSector}</td> 
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${sm.modulo}</td> 
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${sm.estante}</td> 	
												<td style="padding: 2px; vertical-align: middle; text-align: center; width: 8%;">
													<input type="number" name="ancho${sm.articulo}" style="text-align:center; width: 100%" value="0"/>
												</td> 	
												<td style="padding: 2px; vertical-align: middle; text-align: center; width: 8%;">
													<input type="number" name="alto${sm.articulo}" style="text-align:center; width: 100%" value="0"/>
												</td> 	
												<td style="padding: 2px; vertical-align: middle; text-align: center; width: 1%;">
													<input type="number" name="profundidad${sm.articulo}" style="text-align:center; width: 100%" value="0"/>
												</td> 	
												<td style="padding: 2px; vertical-align: middle; text-align: center;">
													<input type="submit" class="btn btn-info" name="Asignar" style="text-align:center;" value="Asignar"/>
													<!--<a type="button" class="btn btn-info" href="<%=basePath%>/ArticulosAsignarMedidas.do?art=${sm.articulo}">Asignar</a>-->
												</td> 												
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
            </div>
             <!-- /. PAGE INNER  -->
		</div>
		
        <!-- /. PAGE WRAPPER  -->
        
        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
		
		
		
