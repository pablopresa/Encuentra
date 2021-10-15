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
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Artículos Recepcionados Sin movimiento</h3>   
                        
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
                             	Lista al Detalle
                             </h5> 
                        </div>
                        <div class="card-body">
                        	<div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                    		<th>Articulos</th>
											<th>Cantidad</th>
											<th>Dias</th>
									    </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="d" items="${art}">
											<tr class="gradeD">
											<td>${d.descripcion}</td>
											<td>${d.id}</td>
											<td>${d.idB}</td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                        
                    
                    <!--End Advanced Tables -->
                
                 </div>
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
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
