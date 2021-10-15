<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        <script type="text/javascript"> 
		 function handleEnter (field, event) 
			{  
				 var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;  
				 if (keyCode == 13) {  
					 var i;  
					 for (i = 0; i < field.form.elements.length; i++)  
						 if (field == field.form.elements[i])  
							 break;  
					 i = (i + 1) % field.form.elements.length;  

					 field.form.elements[i].focus();  
					 return false;  
				 }   
				 else  
					return true;  
	 		}
		
		
			</script>
			<script>
				function enviar(texto) 
				{
					  fnResetAllFilters();
					  
					  document.getElementById('subm').value=texto;
				      document.getElementById('f1').submit();
				  }
			</script>
				<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Ecommerce&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Recepcion entrega de Pedidos</h3>   
                        
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
                            Formulario:
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form"  action="<%=basePath%>/EcommerceDarPedidoConfirmaR_E.do" method="post">
                            	 <div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								        	<label class="col-md-4 control-label" for="pedido" class="col-md-4 control-label">&nbsp;</label>
								        	<div class="col-md-8">
									            <input type="text" name="pedido" class="form-control"/>
									            <input type="text" hidden="hidden" value="${estadoS}" name="estado">
											</div>
								          
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Buscar" />
										  	</div>											
										</div>
									</div>
								 </div>
							</form>
						
							
							<div class="row">
						    	<div class="col-md-12">
				                	<c:if test="${menError!=null}">
				                	<h6><strong>Mensaje:</strong></h6>
				                    <div class="alert alert-info">
				                    	<strong style="color: red"> ${menError}"</strong>            
				                    </div>
				                    </c:if>
				                    <!-- Advanced Tables -->
				                    <div class="card">
				                    <div class="card-header">
				                            Lista de pedidos &nbsp;
				                        </div>
				                        <div class="card-body">
				                        	<c:if test="${lstPedidosConsola!=null}">
				                            	<table class="table table-compact table-striped table-bordered table-hover" id="encuentra-default2" border=1 frame=void rules=rows cellpadding="1" >
				                                    <thead>
				                                        <tr>
				                                           	<th>Estado</th>
				                                           	<th>Pedido</th>
				                                           	<th>Descripcion</th>
				                                           	<th>Fecha<br/>Pedido</th>
				                                           	<th>Estado</th>
															<th>Articulo</th>
															<th>Unidades<br/>Pedidas</th>
															<th>Unidades<br/>Procesadas</th>
														</tr>
				                                    </thead>
				                                    <tbody>
														<c:forEach var="p" items="${lstPedidosConsola}">
															<tr class="gradeD" style="border-collapse: collapse; border-bottom: 1px dashed black; font-size: 13px">
																<c:if test="${p.cr!=p.cp}">
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">Pendiente</td>
																</c:if>
																<c:if test="${p.cr==p.cp}">
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">OK</td>
																</c:if>
																
																<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.idPedido}</td>
																<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.descripcion}</td>
																<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.fechaR}</td>
																<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.estado}</td>
																<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.articulo}</td>
																<c:if test="${p.cr==p.cp}">
																<td style="background-color:#95f9ad; padding: 2px; vertical-align: middle; text-align: center;">${p.cr}</td>
																<td style="background-color:#95f9ad; padding: 2px; vertical-align: middle; text-align: center;">${p.cp}</td>
																</c:if>
																<c:if test="${p.cr!=p.cp}">
																<td style="background-color:#f9959d; padding: 2px; vertical-align: middle; text-align: center;">${p.cr}</td>
																<td style="background-color:#f9959d; padding: 2px; vertical-align: middle; text-align: center;">${p.cp}</td>
																</c:if>
															</tr>
														</c:forEach>
													</tbody>
													<tfoot>
														<tr>
				                                          <tr>
				                                           	<th>Estado</th>
				                                           	<th>Pedido</th>
				                                           	<th>Descripcion</th>
				                                           	<th>Fecha<br/>Pedido</th>
				                                           	<th>Estado</th>
															<th>Articulo</th>
															<th>Unidades<br/>Pedidas</th>
															<th>Unidades<br/>Procesadas</th>
														</tr>
														
													</tfoot>
												</table>
					                          </c:if>
					                    </div>
				                    </div>
				                 </div>
							</div>  
						</div>
						
						            
					
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->

		


            

               
        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>