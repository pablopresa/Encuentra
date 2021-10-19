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
                		<div class="text-left col-sm-6 col-lg-6"> 
                			<div class="flex-container">	
                    			<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Detalle Inventario</h3>   
                        		<h6>Cantidad de articulos contados por codigo de ubicacion </h6>
                        	</div>
                        </div>
                        <div class="text-right col-sm-6 col-lg-6"> 
							<div class="flex-container">	
								<button onClick="window.location.reload();" class="btn btn-info">
	                       			 <span class="fas fa-sync"></span>&nbsp;Refrescar datos
	                       		</button>
                    		</div>
                    	</div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                             <h5>Totales del conteo</h5>
                        </div>
                        <div class="card-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead class="thead-dark">
                                        <tr>
                                          	<th class="text-center">Desde</th>
                                          	<th class="text-center">Hasta</th>
											<th class="text-center">Cantidad</th>
											<th class="text-center">Unidades/Hora</th>		
											<th class="text-center">Porcentaje</th>
                                        </tr>
                                    </thead>
                                 	<tbody>
                                     	<tr>
                                          	<td class="text-center">${inventarioEstado.desde}</td>
                                          	<td class="text-center">${inventarioEstado.hasta}</td>
											<td class="text-center">${inventarioEstado.cantidad}</td>
											<td class="text-center">${inventarioEstado.unidadesHora}</td>		
											<td class="text-center">${inventarioEstado.porcentaje} %</td>
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
                        	 	Detalle
                        	</h5>
                            
                        </div>
                        <div class="card-body">
                        	
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover" id="encuentra-default">
								<thead class="thead-dark">
                                        <tr>
                                          	<th class="text-center">Estanteria</th>
                                          	<th class="text-center">Cod. Ubicación</th>
											<th class="text-center">Estante</th>
											<th class="text-center">Modulo</th>		
											<th class="text-center">Cantidad</th>
											<th class="text-center">Actualizado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="i" items="${inventario}">
                                     	<tr>
                                          	<td class="text-center">${i.descEstanteria}</td>
                                          	<td class="text-center">${i.idOjo}</td>
											<td class="text-center">${i.estante}</td>
											<td class="text-center">${i.modulo}</td>		
											<td class="text-center">${i.cantidad}</td>
											<td class="text-center">${i.fecha}</td>
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
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
