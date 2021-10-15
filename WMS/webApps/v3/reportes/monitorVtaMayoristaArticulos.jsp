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
                    	<h3>Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp; ${nombreReporte}</h3>   
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
										<th class="text-center">fecha</th>
										<th class="text-center">Articulo</th>
										<th class="text-center">Descripcion</th>
										<th class="text-center">C. Ped</th>
										<th class="text-center">Pick</th>
										<th class="text-center">Veri</th>
										<th class="text-center">Remi</th>
										<th class="text-center">Nro Caja</th>
										<th class="text-center">Picking</th>
										<th class="text-center">Estado</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="l" items="${listaART}">
										<tr>
											<td class="text-center">${l.fecha}</td>
											<td class="text-center">${l.articulo}</td>
											<td class="text-center">${l.justificacion}</td>
											<td class="text-center">${l.cantidad}</td>
											<td class="text-center">${l.picked}</td>
											<td class="text-center">${l.verificadas}</td>
											<td class="text-center">${l.remitidas}</td>
											<td class="text-center">${l.nroCaja}</td>
											<td class="text-center">${l.idPicking}</td>
											<td class="text-center">${l.estado}</td>
											
										</tr>
									</c:forEach>
								</tbody>
							</table>
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
