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
                    	<h3>Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Inventario por ubicacion</h3>   
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
								<!-- Comienzo de los filtros -->
	                			<jsp:include page="/v3/reportes/filtros.jsp"></jsp:include>
	                			<!-- fin filtros -->
	                			<div class="col-sm-12 col-lg-12">
									<div class="text-center">
										<button type="submit" name="Submit" class="btn btn-success" id="btnBuscar">Fitrar</button>
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
				document.getElementById("shippDestino"+destino).selected = "selected";
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
