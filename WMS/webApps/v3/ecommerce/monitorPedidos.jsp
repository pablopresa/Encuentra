<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
		
		
		<c:set var = "colapsar" scope = "request" value = "yes"/>
		
		<link rel="stylesheet" href="<%=basePath%>v3/assets/css/modal.css">
		
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		
        <!-- /. NAV SIDE  -->
        	   	<div class="row">
                	<div class="col-md-12">
                    	<h3>Ecommerce&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Detalles de Pedidos</h3>   
                        <h6></h6>
                    </div>
                </div>              
        <!-- /. ROW  -->
         <hr />
         <div class="row" >
            <div class="col-md-12">
                <div class="card">
					<div class="card-header">
						<h5> Filtros </h5>
					</div>
                	<div class="card-body">
                		<form class="form-horizontal" id="buscarPeds" role="form" action="<%=basePath%>/EcommerceDarPedidos.do">
                                 <div class="row">
	                				 <div class="col-sm-6 col-lg-4">
									    <div class="form-group">
									          <label class="col-md-12 control-label" for="art">Nro. Pedido:&nbsp;</label>
									            <div class="col-md-10">
									          		<div class="input-group input-group-sm">
									            		<input type="text" class="form-control bg-white" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="pedido" value="${pedido}">
												
													</div>
												</div>
									    </div>
									 </div>	
	                				 <div class="col-sm-6 col-lg-4">
									    <div class="form-group">
									      <label class="col-md-12 control-label" for="art">Vta. Marketplace:&nbsp;</label>
									        <div class="col-md-10">
									          <div class="input-group input-group-sm">
									            <input type="text" class="form-control bg-white" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="fenicio" value="${fenicio}">
											  </div>
											</div>
									    </div>
									 </div>
									<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-12 control-label" for="art">Articulo:&nbsp;</label>
								          <div class="col-md-10">
									          <div class="input-group input-group-sm">
									            <input type="text" class="form-control bg-white" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="articulo" value="${articulo}">
											  </div>
										  </div>
								        </div>
								    </div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
										    <label class="col-md-12 control-label" for="art">Fecha:&nbsp;</label>
											<div class="col-md-10">
									           <input class="form-control form-control-sm bg-white rango" type="text" name="fini" id="fini">
									        </div>
									        
									    </div>										
									</div>
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-12 control-label" for="art">Estado:&nbsp;</label>
								          <div class="col-md-10">
								            <select class="select2-multi col-sm-12" multiple="multiple" placeholder="Estanterías" name="estado">
										        <option value=""></option>
										    	<c:forEach var="d" items="${estadosL}">
										    		<c:if test="${estado==d.id}">	
														 <option value="${d.id}" selected="selected">${d.descripcion}</option>
													</c:if>
													<c:if test="${estado!=d.id}">	
														 <option value="${d.id}">${d.descripcion}</option>
													</c:if>
										        	
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-12 control-label" for="art">Forma de pago:&nbsp;</label>
								          <div class="col-md-10">
								            <select class="form-control bg-white" tabindex="6" name="forma">
										        <option value=""></option>
										    	<c:forEach var="d" items="${formasPago}">
										    		<c:if test="${forma==d.descripcion}">	
														 <option value="${d.descripcion}" selected="selected">${d.descripcion}</option>
													</c:if>
													<c:if test="${forma!=d.descripcion}">	
														<option value="${d.descripcion}">${d.descripcion}</option> 
													</c:if>
										        	
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								   <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-12 control-label" for="art">Courier/Destino:&nbsp;</label>
								          <div class="col-md-10">
								            <select data-placeholder="Puede Seleccionar Varios" class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="couriers">
										        <option value=""></option>
										    	<c:forEach var="c" items="${couriers}">
										        	<option value="${c.id}">${c.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
										<div class="flex-container">
											
											<label class="p-2" for="switch-0">Sin Etiqueta:&nbsp;</label>
											<div class="switch d-inline m-r-10">
												<c:if test="${sinEtqt=='on'}">	
													<input type="checkbox" name="sinEtqt" id="switch-0" checked="checked"><label for="switch-0" class="cr"></label>
												</c:if>
											    <c:if test="${sinEtqt!='on'}">	
													<input type="checkbox" name="sinEtqt" id="switch-0" ><label for="switch-0" class="cr"></label>
												</c:if>
											</div>
											
											<label class="p-2" for="switch-1">Sin Stock:&nbsp;</label>
											<div class="switch d-inline m-r-10">
												<c:if test="${DepCero=='on'}">	
													<input type="checkbox" name="DepCero" id="switch-1" checked="checked"><label for="switch-1" class="cr"></label>
												</c:if>
											    <c:if test="${DepCero!='on'}">	
													<input type="checkbox" name="DepCero" id="switch-1" ><label for="switch-1" class="cr"></label>
												</c:if>
											</div>
											
											<label class="p-2" for="switch-2">Sin Factura:&nbsp;</label>
											<div class="switch d-inline m-r-10">
												<c:if test="${agrpTienda=='on'}">	
													<input type="checkbox" name="agrpTienda" id="switch-2" checked="checked"><label for="switch-2" class="cr"></label>
												</c:if>
											    <c:if test="${agrpTienda!='on'}">	
													<input type="checkbox" name="agrpTienda" id="switch-2" ><label for="switch-2" class="cr"></label>
												</c:if>
											</div>
											
											<label class="p-2" for="switch-2">A consolidar:&nbsp;</label>
											<div class="switch d-inline m-r-10">
												<c:if test="${aConsolidar=='on'}">	
													<input type="checkbox" name="aConsolidar" id="switch-3" checked="checked"><label for="switch-3" class="cr"></label>
												</c:if>
											    <c:if test="${aConsolidar!='on'}">	
													<input type="checkbox" name="aConsolidar" id="switch-3" ><label for="switch-3" class="cr"></label>
												</c:if>
											</div>
											
											<label class="p-2" for="switch-2">EXPORTAR EXCEL:&nbsp;</label>
											<div class="switch d-inline m-r-10">
												<c:if test="${exportE=='on'}">	
													<input type="checkbox" name="exportE" id="switch-4" checked="checked"><label for="switch-4" class="cr"></label>
												</c:if>
											    <c:if test="${exportE!='on'}">	
													<input type="checkbox" name="exportE" id="switch-4" ><label for="switch-4" class="cr"></label>
												</c:if>
											</div>
											
											
										</div>

								
								
								
								<div class="col-sm-12 col-lg-12">
									<div class="text-center"> 
										<button type="submit" name="Submit" class="btn btn-success" id="btnBuscar">Ver Pedidos</button>
									</div>
								</div>
							</div>
						</form>
						</div>
						</div>
					</div>	
                </div>
                
                <div class="row bg-white" >
                	 <div class="col-md-12">
		                	<c:if test="${menError!=null}">
		                		<h6><strong>Mensaje:</strong></h6>
		                   		<div class="alert alert-info">
		                   			<strong style="color: red"> ${menError}</strong>            
		                   		</div>
		                   	</c:if>
	                    	<c:if test="${lstPedidosConsola!=null}">
	                    		<c:choose>
	                    		
			    					<c:when test = "${uLogeado.idEmpresa==2}">
	                    				<jsp:include page="/v3/ecommerce/TablaMonitorGenerica.jsp"></jsp:include> 
	                    	 		</c:when>
	                    	 		
	                    	 		<c:when test = "${uLogeado.idEmpresa==4}">
	                    				<jsp:include page="/ElRey/TablaMonitorElRey.jsp"></jsp:include> 
	                    	 		</c:when>
	                    	 		
	                    			<c:when test = "${uLogeado.idEmpresa==1}">
	                    				<jsp:include page="/Std/TablaMonitorStadium.jsp"></jsp:include> 
	                    	 		</c:when>
	                    	 		
	                    	 		<c:otherwise>
							            <jsp:include page="/v3/ecommerce/TablaMonitorGenerica.jsp"></jsp:include> 
							        </c:otherwise>
	                    	 		
	                    		</c:choose>
	                        </c:if>
                    </div><!-- row -->
                    </div>
		<div class="md-overlay"></div> 
		
		<div class="md-modal md-effect-1" id="modal-shippEcommerce">
	        <div class="md-content">		        
		             <h3 class="theme-bg2" id="shippTitle"></h3>	             
			            <div class="row" >
			            <div class="col-sm-12 col-lg-12">
				            <form action="<%=basePath%>updateShippEcommerce.do" enctype="multipart/form-data" method="post" id="shippEcommerce">
			            		<input style="display: none" id="shippPedido" name="idPedido" value="">				            	
				            	<div class="form-group">
									<label class="col-md-12 control-label" for="perfil">Destino:</label>
									<div class="col-md-12">
								  		<select class="form-control md-12" name="shippDestino" id="shippDestino" >
								  			<option value=""></option>
								  			<c:forEach var="c" items="${couriers}">
									        	<option value="${c.id}" id="shippDestino${c.id}">${c.descripcion}</option>
									    	</c:forEach>										  		
									  	</select>
								  	</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-12 control-label" for="p1">Tracking:</label>
									<div class="col-md-12">
								  		<input class="form-control md-12" name="tracking" id="shippTrack"/>
									</div>
								</div>
								
								<label class="col-md-12 control-label">Etiqueta:</label>
								<div class="col-sm-12 col-lg-12">
                           			<label class="col-md-4 control-label" for="archi"></label>
							        <div class="input-group mb-3">
                                            <div class="custom-file">                                             
                                                <input type="file" class="custom-file-input" id="inputGroupFile02" name="archi">
                                                <label class="custom-file-label" for="inputGroupFile02">Subir archivo</label>
                                            </div>
							        </div>
							    </div>		
							</form>     													                
						     <div class="row">											
								<button class="btn btn-primary md-close" onclick="CloseShippPedido()">Cerrar</button>
								<button onclick="sendShippPedido()" class="btn btn-success md-close">Guardar Cambios</button>
							</div>
								
					  	  
					  	</div>  
						
				</div>							         			            
	        </div>
	    </div> 
		
		<script>
			// OPRERACIONES FILTROS
		    function CloseShippPedido(){
		    	$("#modal-shippEcommerce").removeClass("md-show");
		    	document.getElementById("md-ov").style.opacity = 0;
				document.getElementById("md-ov").style.visibility = "hidden";
		    }
			function shippPedido(pedido,destino,track,url){
				
				document.getElementById("md-ov").style.opacity = 1;
				document.getElementById("md-ov").style.visibility = "visible";
				$("#modal-shippEcommerce").addClass("md-show");
				document.getElementById("shippTitle").innerHTML = 'Shipping Pedido'+pedido;
				try {
					document.getElementById("shippDestino"+destino).selected = "selected";
				} catch (e) {
					document.getElementById("shippTrack").value = track;
					document.getElementById("shippPedido").value = pedido;
				}
				
				document.getElementById("shippTrack").value = track;
				document.getElementById("shippPedido").value = pedido;								
			}
			function sendShippPedido(){
				$("modal-shippEcommerce").removeClass("md-show");
				
				document.getElementById("shippEcommerce").submit();
			}
		</script>
		
		<script type="text/javascript">
			$(document).ready(function() {
			    $("#btnBuscar").click(function() {
			      // disable button
			     <!-- $(this).prop("disabled", true); -->
			      // add spinner to button
			      $(this).html(
			    		  `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Buscando...`
			      );
			      document.getElementById("buscarPeds").submit();
			    });
			 });  		      
		</script>
				
			
		    <c:if test="${rango!=null}">
				<!-- scripts y css de picker -->
				<script type="text/javascript">
					document.getElementById("fini").value = "${rango}"; 
				</script>
				<!-- fin scripts y css de picker -->
			</c:if>
				
        <jsp:include page="/v3/util/footer.jsp"></jsp:include>
		
<!-- <script>
	function closeModal(id){
		$(id).modal('hide');
	}
</script> -->		
