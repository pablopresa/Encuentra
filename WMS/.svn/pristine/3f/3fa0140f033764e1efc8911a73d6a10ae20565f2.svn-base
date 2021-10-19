
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
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Movimientos dentro del deposito&nbsp;</h3>   
                        <h6>Podra rastrear como se movio un determinado articulo dentro del deposito</h6>
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
                             <h5>Elija el rango de fechas en que quiere filtrar</h5> 
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/MovimientosArticulos.do">
                            	<div class="row">                         
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											    <label class="col-md-4 control-label" for="fini">Articulo:&nbsp;</label>
												<div class="col-md-8">
										           <input class="form-control" type="text" name="articulo" >
										        </div>
										    </div>										
									</div>			       
	                            	
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											    <label class="col-md-4 control-label" for="fini">Ubicacion:&nbsp;</label>
												<div class="col-md-8">
										           <input class="form-control" type="text" name="ubicacion">
										        </div>
										    </div>										
									</div>	
													
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">											
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Ver" />
										  	</div>											
										</div>
									</div>
								</div>
							</form>
						</div>
						<hr/>
						<div class="card-header">
                             Movimientos del articulo
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<table class="table table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                           <th style="text-align: center;">Origen</th>
                                           <th style="text-align: center;">Destino</th>
                                           <th style="text-align: center;">Cantidad</th>
                                           <th style="text-align: center;">Fecha de modificacion</th>
                                           <th style="text-align: center;">Usuario</th>
                                           <th style="text-align: center;">Tipo</th>
                                        </tr>
                                    </thead>
                                    <tbody id="cuerpoTabla">
                                    	<c:forEach var="m" items="${movimientos}">
	                                    	<tr class='gradeD' style='border-collapse:collapse;border-bottom:1pxdashed3black;font-size:13px;'>
												<td style='padding: 2px; vertical-align: middle; text-align: center;'>${m.codOrigen}</td>
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${m.codDestino}</td>
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${m.cantidad}</td>
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${m.update}</td>
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${m.nombreUsuario}</td>	
												<td style="padding: 2px; vertical-align: middle; text-align: center;">${m.tipo}</td>										
											
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                        <hr/>
						
						
                        </div>
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
            
  


        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
