<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		<script type="text/javascript"> 
			function seleccionar_todo(){
			   for (i=0;i<document.f1.elements.length;i++)
			   {
			      if(document.f1.elements[i].type == "checkbox")
			         document.f1.elements[i].checked=1;
			   }
			   for (i=0;i<document.f0.elements.length;i++)
			   {
			      if(document.f0.elements[i].type == "checkbox")
			         document.f0.elements[i].checked=1
			   }      
			}
			
			//La función seleccionar_todo() realiza un recorrido por todos los elementos del formulario. Para hacer un recorrido por todos los campos se utiliza el array "elements", que guarda una referencia con cada elemento que haya dentro del formulario.
			
			//En el recorrido comprueba si el elemento actual es de tipo "checkbox" (recordar que el array elements contiene todos los elementos, pero sólo deseamos operar con los que sean checkbox) y en ese caso, simplemente se actualiza el atributo "checked" al valor 1, con lo que el chekbox se marcará.
			
			function deseleccionar_todo(){
			   for (i=0;i<document.f1.elements.length;i++)
			   {
			      if(document.f1.elements[i].type == "checkbox")
			         document.f1.elements[i].checked=0
			   }
			   for (i=0;i<document.f0.elements.length;i++)
			   {
			      if(document.f0.elements[i].type == "checkbox")
			         document.f0.elements[i].checked=0
			   }
			}
			
			
			</script>
        <!-- /. NAV SIDE  -->
        
        
		
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Reglas de reposición&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Depositos</h3>   
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
                             Lista de Depositos
                        </div>
                        <div class="card-body">
                        	<div class="table-responsive">
                        			<form action="<%=basePath%>EditarDepositoRegla.do" method="post" id="f1" name="f1">
                        			<table class="table table-striped table-bordered table-hover" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                           	<th>Deposito</th>
	                                           	<th><a href="javascript:seleccionar_todo()">Todos</a><br/><a href="javascript:deseleccionar_todo()">Ninguno</a> </th>
											</tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="d" items="${deposRegla}">
												<tr class="gradeD">
													<td><c:out value="${d.desc}"></c:out></td>
													<c:if test="${d.descripcion==1}">
														<td><input type="checkbox" checked="checked" name="${d.id}" /></td>
													</c:if>
													<c:if test="${d.descripcion!=1}">
														<td><input type="checkbox" name="${d.id}" /></td>
													</c:if>
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