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
                    	<h3>Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Reporte de prueba </h3>   
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
                		<form class="form-horizontal" id="buscarPeds" role="form" action="<%=basePath%>/FiltrarTEST.do" method="post">
							<div class="row">
								
								<div class="col-sm-6 col-lg-4">
									<div class="form-group">
										<label class="col-md-12 control-label" for="art">Desde:&nbsp;</label>
										<div class="col-md-10">
											<div class="input-group input-group-sm">
												<input type="number" class="form-control bg-white" aria-label="Small"
													aria-describedby="inputGroup-sizing-sm" name="vDesde"
												>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-sm-6 col-lg-4">
									<div class="form-group">
										<label class="col-md-12 control-label" for="art">Hasta:&nbsp;</label>
										<div class="col-md-10">
											<div class="input-group input-group-sm">
												<input type="number" class="form-control bg-white" aria-label="Small"
													aria-describedby="inputGroup-sizing-sm" name="vHasta"
												>
											</div>
										</div>
									</div>
								</div>
								
								
								<div class="col-sm-12 col-lg-12">
									<div class="text-center">
										<button type="submit" name="Submit" class="btn btn-success" id="btnBuscar" value="filtrar">Fitrar</button>
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
	                    	
	                    <div class="table-responsive">
						     <table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
								<thead class="thead-dark">
						            <tr>
						             	<th class="text-center">ID</th>
						               	<th class="text-center">DescripcionB</th>
						               	<th class="text-center">Valor</th>
						               	<th class="text-center">Descripcion</th>
									</tr>
								</thead>
						        <tbody>
						        	<c:forEach var="linea" items="${tabla}">
						            <tr>
						            	<td class="text-center">${linea.id}</td>
										<td class="text-center">${linea.descripcionB}</td>
										<td class="text-center">${linea.valor}</td>
										<td class="text-center">${linea.descripcion}</td>
									</tr>
							  		</c:forEach>
						   		</tbody>
							</table>
						</div> 	 
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
