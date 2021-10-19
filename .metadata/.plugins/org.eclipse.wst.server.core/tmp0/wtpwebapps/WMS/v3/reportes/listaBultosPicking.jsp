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
                    	<h3>Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp; Lista de bultos</h3>   
                        <h6></h6>
                    </div>
                </div>              
        <!-- /. ROW  -->
         <hr />
         
                
                <div class="row bg-white" >
                	 <div class="col-md-12">
		                	<c:if test="${menError!=null}">
		                		<h6><strong>Mensaje:</strong></h6>
		                   		<div class="alert alert-info">
		                   			<strong style="color: red"> ${menError}</strong>            
		                   		</div>
		                   	</c:if>
						<div class="table-responsive">
							<table class="display table nowrap table-striped table-hover dataTable"
								id="encuentra-default">
								<thead class="thead-dark">
									<tr>
							
										<th class="text-center">Pedido</th>
										<th class="text-center">Bulto</th>
										<th class="text-center">Articulo</th>
										<th class="text-center">Descripcion</th>
										<th class="text-center">Cantidad requerida</th>
										<th class="text-center">Estanteria</th>
										<th class="text-center">Modulo</th>
										<th class="text-center">Estante</th>
										<th class="text-center">Ojo</th>
										<th class="text-center">Recorrido</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="l" items="${bultos}">
										<tr>
											<td class="text-center">${l.pedidoSTR}</td>
											<td class="text-center">${l.descripcion}</td>
											<td class="text-center">${l.idArticulo}</td>
											<td class="text-center">${l.justificacion}</td>
											<td class="text-center">${l.solicitada}</td>
											<td class="text-center">${l.descEstanteria}</td>
											<td class="text-center">${l.modulo}</td>
											<td class="text-center">${l.estnte}</td>
											<td class="text-center">${l.cubi}</td>
											<td class="text-center">${l.recorrido}</td>
											
											
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						
						<div class="row">
	                    	<div class="text-right col-sm-12 col-lg-12"> 
	                            <div class="flex-container">

									<a class="btn btn-info" href="<%=basePath%>AltaTareaRepo.do" Style="margin-top:15px">Crear Tarea</a>
												
								</div>
							</div> 
						</div>
							
						<div class="md-overlay"></div>  
	                    	 
                    </div><!-- row -->
                </div>
		
		
		<script type="text/javascript">
			$(document).ready(function() {
			    $("#btnBuscar").click(function() {
			      // disable button
			      $(this).prop("disabled", true); 
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
