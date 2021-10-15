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
        
        
		
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Reglas de reposición&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Filtros</h3>   
                        <h6>Edite los que quiera </h6>
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
                             Lista de Filtros
                        </div>
                        <div class="card-body">
                        	<div class="table-responsive">
                        			<form action="<%=basePath%>EditarFiltroRegla.do" method="post">
                        			<table class="table table-striped table-bordered table-hover" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                           	<th>Filtro</th>
	                                           	<th>Valor</th>
											</tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="f" items="${filtrosRegla}">
												<tr class="gradeD">
													<td><c:out value="${f.desc}"></c:out></td>
													<td><input type="text" value="${f.descripcion}" name="${f.id}" /></td>
												</tr>
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
	                                           	<th><input type="text" value="${regSel}"  name="regSel" readonly="readonly"></th>
	                                           	<th><input type="submit" value="Guardar Cambios" class="btn btn-info"/> </th>
											</tr>
										</tfoot>
									</table>
	                            	</form>
								</div>
	                            
                            </div>
                            
                        </div>
                        
                        
                        
                        
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
             <!-- /. PAGE INNER  -->
		
        <!-- /. PAGE WRAPPER  -->
  




            

               
        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>

			
		
		
		<script type="text/javascript">
		
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