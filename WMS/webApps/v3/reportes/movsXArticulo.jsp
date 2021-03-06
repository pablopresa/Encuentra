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
                    	<h3>Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp; ${reporte}</h3>   
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
                		<form class="form-horizontal" id="buscarPeds" role="form" action="<%=basePath%>/FiltrarReportes.do">
							<div class="row">
								<!-- Comienzo de los filtros -->
	                			<jsp:include page="/v3/reportes/filtros.jsp"></jsp:include>
	                			<!-- fin filtros -->
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
	                    	<!--	<c:choose> <c:when test = "${reporte=='movsXArt'}"> -->
	                    		
			    			  		
	                    				
	                    	
	                    	 		
	                    	<!--  		</c:when>	</c:choose>  -->
	                    	<jsp:include page="/v3/reportes/TablaMovsXArticulo.jsp"></jsp:include> 
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
