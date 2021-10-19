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
			<script language="Javascript" type="text/javascript">
				if (document.cookie != "") {
            		//if (confirm("Eliminar todas las Cookies?")) {
                		la_cookie = document.cookie.split("; ")
                		fecha_fin = new Date
                		fecha_fin.setDate(fecha_fin.getDate()-1)
						for (i=0; i<la_cookie.length; i++) {
                    		mi_cookie = la_cookie[i].split("=")[0]
                    		document.cookie = mi_cookie + "=;expires=" + fecha_fin.toGMTString()
                		}
                		//document.write("Se han eliminado: " + la_cookie.length + " Cookies ")
            		//}
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
									
									 <div class="col-sm-6 col-lg-4" >
										<div class="form-group">
											<div class="col-md-8">
										  		<a href="<%=basePath%>pdf/formularioAcuerdaEntrega.pdf" target="_blank">Formulario Acuerda Entrega</a>
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
				                            	<table class="table table-compact table-striped table-bordered table-hover" id="encuentra-default" border=1 frame=void rules=rows cellpadding="1" >
					                                    <thead>
					                                        <tr>
					                                           	<th>Pedido</th>
					                                           	<th>Cliente</th>
					                                           	<th>Estado</th>
																<th>órden</th>
																<th>Factura</th>
																<th>F. Pago</th>
																<th>Articulo</th>
																<th>Fecha de despacho</th>
																<th>Retira</th> 
																<th>Acción</th>
															</tr>
					                                    </thead>
					                                    <tbody>
															<c:forEach var="p" items="${lstPedidosConsola}">
																<tr class="gradeD" style="border-collapse: collapse; border-bottom: 1px dashed black; font-size: 13px">
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.idPedido}</td>
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.descripcion}</td>
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.estado}</td>
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.idOrden}</td>
																	<td style="padding: 2px; vertical-align: middle; text-align: center;"><a href="${p.urlFactura}"><button class="btn btn-success">${p.idFactura}</button></a></td>
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.formaPago}</td>
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.articulo}</td>
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.fechaDespacho}</td>
																	<td style="padding: 2px; vertical-align: middle; text-align: center;">${p.personaRetira}</td>
																	<c:if test="${estadoS==4}">
																		<td style="padding: 2px; vertical-align: middle; text-align: center;"><a href="<%=basePath%>AccionPedido.do?idPedido=${p.idPedido}&estadoActual=4&estadoNuevo=5"><button class="btn btn-info">RECEPCIONAR</button></a></td>
																	</c:if>
																	<c:if test="${estadoS==5 && p.retiraFormulario==0}">
																		<td style="padding: 2px; vertical-align: middle; text-align: center;"><button class="btn btn-warning" onclick="if (confirm('Seguro que desea entregar a cliente?, esta acción no tiene forma de deshacerse')) {window.location.href = '<%=basePath%>AccionPedido.do?idPedido=${p.idPedido}&estadoActual=5&estadoNuevo=6'} ">ENTREGAR A CLIENTE</button></td>
																	</c:if>
																	<c:if test="${estadoS==5 && p.retiraFormulario==1}">
																		<td style="padding: 2px; vertical-align: middle; text-align: center;"><button class="btn btn-warning" onclick="if (confirm('Seguro que desea entregar a cliente?, esta acción no tiene forma de deshacerse \n \n ATENCION!!! ESTE CLIENTE DEBE RELLENAR UN FORMULARIO PARA PODER RETIRAR SU PEDIDO')) {window.location.href = '<%=basePath%>AccionPedido.do?idPedido=${p.idPedido}&estadoActual=5&estadoNuevo=6'} ">ENTREGAR A CLIENTE</button></td>
																	</c:if>
																</tr>
															</c:forEach>
														</tbody>
														
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
