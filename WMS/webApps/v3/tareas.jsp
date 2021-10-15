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
                    	<h3>Tareas&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Sin Terminar</h3>   
                        <h6> </h6>
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
                             Tareas
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th>Master</th>
											<th>Comentario</th>
											<th>Tipo de Tarea</th>
											<th>Estado</th>
											<th>Porcentaje</th>
											<th>Ejecutor</th>
											<th>Responsable</th>
											<th>hora inicio</th>
											<th>Hora Fin</th>
											<th>Reasignar</th>
											<th>Calificar</th>
											<th>Forzar fin</th>
											<th>Eliminar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="t" items="${tareas}">
											<tr class="gradeD">
												<td><c:out value="${t.main}"></c:out></td>
												<td><div align="center"><c:out value="${t.observacion}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.tipo.descripcion}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.estado.descripcion}"></c:out></div></td>
												<td>
												<div class="progress progress-striped active">
       												<div class="progress-bar" role="progressbar" aria-valuenow="${t.porcentaje}" aria-valuemin="0" aria-valuemax="100" style="width:${t.porcentaje}%">${t.porcentaje}%</div>
        										</div>
												
												</td>
												<td><div align="center"><c:out value="${t.usuario.nombre}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.responsable.descripcion}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.fechaInicio}"></c:out></div></td>
												<td><div align="center"><c:out value="${t.fechaFin}"></c:out></div></td>
												<td><div align="center">reasignar</div></td><td>
												
												
												<td><div align="center"><a href="#"
												
												onClick="if(confirm('Seguro que desea Forzar Fin?'))
															{
																window.location='<%=basePath%>ActionEventTarea.do?idTarea=${t.id}&action=1&state=2&devolverA=terminadas'
																
																
															}
															else {
																alert('Sin Cambios')
															}">
												<img alt="Eliminar" src="<%=basePath%>/imagenes/icons/package_delete.png"></a> </div></td>
												<td><div align="center"><a href="#"
												
												onClick="if(confirm('Seguro que desea Eliminar la tarea ya asignada?'))
															{
																window.location='<%=basePath%>ActionEventTarea.do?idTarea=${t.id}&action=0&state=2&devolverA=terminadas'
																
																
															}
															else {
																alert('Sin Cambios')
															}">
												<img alt="Eliminar" src="<%=basePath%>/imagenes/icons/package_delete.png"></a> </div></td>
										
												
											</tr>
										
										
													
									</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
		<script>
		function createCORSRequest(method, url) {
		  var xhr = new XMLHttpRequest();
		  if ("withCredentials" in xhr) {
		    // XHR for Chrome/Firefox/Opera/Safari.
		    xhr.open(method, url, true);
		  } else if (typeof XDomainRequest != "undefined") {
		    // XDomainRequest for IE.
		    xhr = new XDomainRequest();
		    xhr.open(method, url);
		  } else {
		    // CORS not supported.
		    xhr = null;
		  }
		  return xhr;
		}
		
		
		
			setInterval(function() 
			{
	         	js_fun();
	        }, 10000);

			function js_fun() 
			{
	
				var json;
				var url = '<%=basePath%>JSONavTareas.do';
			 	var xhr = createCORSRequest('GET', url);
				if (!xhr) 
				{
					alert('CORS not supported');
					return;
				}
				// Response handlers.
				xhr.onload = function() 
				{
					var text =xhr.responseText;
					recorrer2(text);
				};
				
				xhr.onerror = function() 
				{
					alert('Atención: parece que el Servidor no está disponible');
				};
				
				xhr.send();
				
			}
		
			function recorrer2(text) 
			{
				var arr = JSON.parse(text);
				var i;
				for(i = 0; i < arr.length; i++) 
				{
					//hay que actualizar el avance
					
					var bars = document.getElementsByClassName("progress-bar");
					bars[i].style.width = arr[i].Porcent+"%";
					bars[i].innerHTML = arr[i].Porcent+"%";
				}	
			}
		
		
		
		
		
		
		
		/*
		$(document).ready(function()
		{
		  var progresspump = setInterval(function()
		  {
		    
		  
		  
		  }, 1000);});
		  
			*/
		</script>
