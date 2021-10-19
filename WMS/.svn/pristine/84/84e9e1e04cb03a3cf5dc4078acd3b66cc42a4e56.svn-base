<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Reglas de reposición</h3>   
                        <h6>Edite las que quiera </h6>
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
                        	<h5>
                             Nueva Regla
                            </h5> 
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>altaReglaRepo.do" >
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-3">
								        <div class="form-group">
								        	<label class="col-md-12 control-label" for="nombreRegla" >Nombre:&nbsp</label>
								         	<div class="col-md-8">
								         	<input type="text" name="nombreRegla" class="form-control"></div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-3">
								        <div class="form-group">
								        	<label class="col-md-12 control-label" for="priodiRegla" >Prioridad:&nbsp</label>
								         	<div class="col-md-8"><input type="number" name="priodiRegla" class="form-control"></div>
								        </div>
								     </div>
								    <div class="col-sm-6 col-lg-3">
								        <div class="form-group">
								        	<label class="col-md-12 control-label" for="minCentralRegla" >Min. Centrál:&nbsp</label>
								         	<div class="col-md-8"><input type="number" name="minCentralRegla" class="form-control"></div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-3">
								        <div class="form-group">
								        	<label class="col-md-12 control-label" for="maxLocalRegla" >Max. Local:&nbsp</label>
								         	<div class="col-md-8"><input type="number" name="maxLocalRegla" class="form-control"></div>
								        </div>
								    </div>
								    
								     </div>
								    <div class="row">
								   <div class="col-sm-6 col-lg-3">
										<div class="form-group">											
											<div class="col-md-8">
												<label class="col-md-3 control-label" for="maxLocalRegla" >&nbsp</label>
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Nueva Regla" />
										  	</div>											
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="card">
                        <div class="card-header">
                        	<h5>
                             Lista de Reglas
                            </h5> 
                        </div>
                        <div class="card-body">
                        	<div class="table-responsive">
                        			<table class="table table-striped table-bordered table-hover" id="encuentra-default">
	                                    <thead class="thead-dark">
	                                        <tr>
	                                           	<th class ="text-center">IdRegla (prioridad)</th>
	                                           	<th class ="text-center">Nombre</th>
												<th class ="text-center">Prioridad</th>
												<th class ="text-center">Min. Central</th>
												<th class ="text-center">Max. Local</th>
												<th class ="text-center">Filtros</th>
												<th class ="text-center">Locales</th>
												<th class ="text-center">Eliminar</th>
										    </tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="r" items="${reglas}">
												<tr class="gradeD">
													<td class ="text-center"><c:out value="${r.prioridad}"></c:out></td>
													<td class ="text-center"><c:out value="${r.nombre}"></c:out></td>
													<td class ="text-center">
														<input class="form-control text-center" type="number" value="${r.prioridad}" id="${r.idRegla}-Prioridad" onchange="editRegla(${r.idRegla},'Prioridad')"/></td>
													<td class ="text-center">
														<input class="form-control text-center" type="number" value="${r.minCentral}" id="${r.idRegla}-MinCentral" onchange="editRegla(${r.idRegla},'MinCentral')"/></td>
													<td class ="text-center">
														<input class="form-control text-center" type="number" value="${r.maxLocal}" id="${r.idRegla}-MaxLocal" onchange="editRegla(${r.idRegla},'MaxLocal')"/></td>
													<td class ="text-center">
														<button class="btn btn-info" onclick="viewFilters(${r.idRegla})">
															<span class="fas fa-filter"></span></button>
													</td>
													<td class ="text-center">
														<button class="btn btn-warning" onclick="viewDepos(${r.idRegla})">
															<span class="fas fa-store"></span></button>
													</td>
													<td class ="text-center">
													<button class="btn btn-danger" onclick="deleteRule(${r.idRegla})" id="btnDelete">
														<span class="fas fa-trash-alt"></span></button>
													</td>
												</tr>
											
											</c:forEach>
										</tbody>
										
									
										
	                                </table>
	                            
								</div>
	                            
                            </div>
                            
                        </div>
                        
                        
                        
                        
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
                
                
        <div class="md-modal md-effect-1" id="modal-filtros">
	        <div class="md-content">
	             <h3 class="theme-bg2">Filtros para la regla ${regSel}</h3>	             
	            <div class="row" id ="r_filtros" style="overflow-y: auto; height:600px; ">
	            		
				</div>	
				<div class="row">											
					<button class="btn btn-primary md-close" onclick="CloseFilters()">Cerrar</button>
					<button onclick="sendFilters()" class="btn btn-success md-close">Guardar Cambios</button>
				</div>		              			            
	        </div>
	    </div>
	    
	    <div class="md-modal md-effect-1" id="modal-depos">
	        <div class="md-content">
	             <h3 class="theme-bg2">Depositos incluidos en la regla ${regSel}</h3>	             
	            <div class="row" id ="r_depos" style="overflow-y: auto; height:600px; ">
	            		
				</div>	
				<div class="row">											
					<button class="btn btn-primary md-close" onclick="CloseDepos()">Cerrar</button>
					<button onclick="sendDepos()" class="btn btn-success md-close">Guardar Cambios</button>
				</div>		              			            
	        </div>
	    </div>
	      
			      
		<script>
			// OPRERACIONES FILTROS
		    function CloseFilters(){
		    	$("#modal-filtros").removeClass("md-show");
		    	document.getElementById("md-ov").style.opacity = 0;
				document.getElementById("md-ov").style.visibility = "hidden";
		    }
			function viewFilters(idRegla){
				$.ajax({
						url : "<%=basePath%>VerFilRegla.do?regSel="+idRegla,
						type : "post",
						dataType : "html"					
					}).then(function(data) {
						document.getElementById("md-ov").style.opacity = 1;
						document.getElementById("md-ov").style.visibility = "visible";
						$("#modal-filtros").addClass("md-show");
						document.getElementById("r_filtros").innerHTML = data;
					});						
			}
			function sendFilters(){
				$("#modal-filtros").removeClass("md-show");
				
				document.getElementById("FiltroRegla").submit();
			}
			
			//OPERACIONES DEPOSITOS
			function CloseDepos(){
		    	$("#modal-depos").removeClass("md-show");
		    	document.getElementById("md-ov").style.opacity = 0;
				document.getElementById("md-ov").style.visibility = "hidden";
		    }
			function viewDepos(idRegla){
				$.ajax({
						url : "<%=basePath%>VerDepRegla.do?regSel="+idRegla,
						type : "post",
						dataType : "html"					
					}).then(function(data) {
						console.log(data);
						document.getElementById("md-ov").style.opacity = 1;
						document.getElementById("md-ov").style.visibility = "visible";
						$("#modal-depos").addClass("md-show");
						document.getElementById("r_depos").innerHTML = data;
					});						
			}
			function sendDepos(){ 
				$("#modal-depos").removeClass("md-show");
				
				document.getElementById("DeposRegla").submit();
			}
						
		       
	       function deleteRule(rule) {
		        swal({
		                title: "Atencion!",
		                text: "Esta seguro que desea eliminar esta regla?",
		                icon: "warning",
		                buttons: true,
		                dangerMode: true,
		            })
		            .then((willDelete) => {
		                if (willDelete) {
		     				location.assign('<%=basePath%>EliminarRegla.do?regSel='+rule);
		                } else {
		                    
		                }
		            });
		    	};
		       
			
		</script>	
	      
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>

			
		
		
		<script type="text/javascript">
		
		
			function subm()
			{
				document.getElementById("altaNuevo").submit();
			}
			
			function editRegla(idRegla,field)
			{
				
				var valuer = document.getElementById(idRegla+'-'+field).value
				
				var req = '<%=basePath%>EditValueRegla.do?idRegla='+idRegla+'&field='+field+'&valor='+valuer
				SendAjaxPost(req);
			}
		
		
			function SendAjaxPost(request) 
			{
			    var xmlhttp = new XMLHttpRequest();
			    //xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			    xmlhttp.onreadystatechange = function() {
			        if (xmlhttp.readyState == XMLHttpRequest.DONE ) {
			           if (xmlhttp.status == 200) 
			           {
			              
			               
			           }
			           else if (xmlhttp.status == 400) {
			              alert('There was an error 400');
			           }
			           else {
			               alert('something else other than 200 was returned');
			           }
			        }
			    };

			    
			    xmlhttp.open("POST", request, true);
			    xmlhttp.send();
			    req='';
			}
		</script>