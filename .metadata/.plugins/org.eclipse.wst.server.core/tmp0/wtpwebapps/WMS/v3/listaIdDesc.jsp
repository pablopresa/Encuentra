<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		<link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
        <!-- /. NAV SIDE  -->
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Detalle&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;${lstDetalil}</h3>   
                        
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
                     <c:if test="${tarje!=22}">
		                    <div class="card-header">
		                             Filtros para aplicar en la lista
		                    </div>
		                    <div class="card-body">                    	
		                           <div class="row">
		                           		<div class="col-sm-6 col-lg-4" style="margin-bottom: 10px;">
										   <div class="form-group">
										        <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Seccion:&nbsp;</label>
										       <div class="col-md-8">
										            <select data-placeholder="Puede Seleccionar Varios"  class="chosen-select form-control" multiple tabindex="6" name="seccion" id="seccion">
												        <option value=""></option>
												    	<c:forEach var="d" items="${secciones}">
												        	<option value="${d.id}">${d.descripcion}</option>
												    	</c:forEach>
												  	</select>
										          </div>
										        </div>
										    </div>
										    
										    
										  <script src="<%=basePath%>v3/assets/select/chosen.jquery.js" type="text/javascript"></script>
										<script type="text/javascript">
									    var config = {
									      '.chosen-select'           : {},
									      '.chosen-select-deselect'  : {allow_single_deselect:true},
									      '.chosen-select-no-single' : {disable_search_threshold:10},
									      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
									      '.chosen-select-width'     : {width:"95%"}
									    }
									    for (var selector in config) {
									      $(selector).chosen(config[selector]);
									    }
									  </script>
										
									
								</div>
								</div>	
						</c:if>                    
                        <div class="card-header">
                             Lista al Detalle 
                        </div>
                        <div class="card-body">
                        	<div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                    		<th>${descripcion}</th>
											<th>${Id}</th>
									    </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="d" items="${lista}">
											<tr class="gradeD">
												<c:if test="${tarje==5}">
                									<td><a  onclick="DarRepos(${d.idB}, 0)"><c:out value="${d.descripcion}"></c:out></a></td>
													<td><c:out value="${d.id}"></c:out></td>
                    							</c:if>
                    							<c:if test="${tarje==6}">
                									<td><a  onclick="DarRepos(${d.idB}, 1)"><c:out value="${d.descripcion}"></c:out></a></td>
													<td><c:out value="${d.id}"></c:out></td>
                    							</c:if>
                    							<c:if test="${tarje!=6 && tarje!=5}">
                									<td><c:out value="${d.descripcion}"></c:out></td>
													<td><c:out value="${d.id}"></c:out></td>
                    							</c:if>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                        
                    
                    <!--End Advanced Tables -->
                
                 </div>
            </div>
                <!-- /. ROW  -->
            </div>
             <!-- /. PAGE INNER  -->
		</div>
		
		<script type="text/javascript">
			function DarRepos(deposito,mayorista){
				console.log("entre");
				var select ="";
				console.log(deposito);
				console.log(mayorista);
				
				for (i = 0; i < document.getElementById("seccion").length; i++) {
					console.log(document.getElementById("seccion").options[i].text);
					if(document.getElementById("seccion").options[i].selected){
						console.log("entre3");
    					var id=document.getElementById("seccion").options[i].value;    
    					select+="&seccion="+id;				
    				}    				
    			}
    			
    			var req = "<%=basePath%>/DarListaArtPendientesRepo.do?deposito="+deposito+"&central=1&Submit=Filtrar&mayorista="+mayorista+select;
    			window.location.assign(req);
				}
		</script>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
