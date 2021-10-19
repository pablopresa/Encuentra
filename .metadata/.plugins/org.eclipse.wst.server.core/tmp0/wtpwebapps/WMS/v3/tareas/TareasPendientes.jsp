<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>          
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Tareas&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Tareas Pendientes</h3>   
                        <h6>Estas son todas las tareas que aun no se han iniciado</h6>
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
                        		Listado de Tareas
                        	</h5>	
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	
                            	<input type="text" style="visibility: hidden;" name = "save" value ="true">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="text-center">IdTarea</th>
											<th class="text-center">Fecha</th>
											<th class="text-center">Unidades</th>
											<th class="text-center">Usuario</th>
											<th class="text-center">Estado</th>
											<th class="text-center">Documentos</th>
											<th class="text-center"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="a" items="${tarPendientes}">
											<tr class="gradeD">
												
												<td class="text-center">${a.idTarea}</td>
												<td class="text-center">${a.fecha}</td>
												<td class="text-center">${a.unidades}</td>
												<td class="text-center">${a.usuario}</td>
												<td class="text-center">${a.descripcionEstado} <br> ${a.avance}%
													<div class="progress m-t-30" style="height: 7px; margin-top: 5px; margin-right: 10px; margin-left: 10px;">
		                                                <div class="progress-bar progress-c-theme" role="progressbar" style="width: ${a.avance}%;" 
		                                                aria-valuenow="${a.avance}" aria-valuemin="0" aria-valuemax="100"></div>
		                                            </div>
												</td>
												<c:if test="${fn:length(a.lista)==1}">
													<c:if test="${a.lista.get(0).idLong!=0}">
														<td class="text-center"><button class="btn btn-primary btn-sm">${a.lista.get(0).idLong}</button></td>
													</c:if>
													<c:if test="${a.lista.get(0).idLong==0}">
														<td class="text-center"><button class="btn btn-primary btn-sm">${a.lista.get(0).descripcion}</button></td>
													</c:if>													
												</c:if>
												<c:if test="${fn:length(a.lista)!=1}">
													<td class="text-center"><button class="btn btn-primary btn-sm" onclick="omTarea(${a.idTarea})">VER</button></td>
												</c:if>
												
												<td class="text-center">
												<c:if test="${a.descripcionEstado=='Asignada'}">
													<a href="<%=basePath%>/TareasPendientes.do?idPick=${a.idPicking}" class="btn btn-danger btn-sm">DESHACER</a>	
												</c:if>			
												</td>																				
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
            
            <c:forEach var="t" items="${tarPendientes}">
				<div class="md-modal md-effect-1" id="modal-${t.idTarea}">
			        <div class="md-content">
			            <h3 class="theme-bg2">Documentos en Tarea &nbsp;${t.idTarea}</h3>
			            <div>
			                <table class="table table-striped table-bordered table-hover">
								<tr>
									<th style="text-align: center;">Pedido</th>
									<th style="text-align: center;">Documento a afectar</th>
								</tr>
							<c:forEach var='p' items='${t.lista}'>
								<tr>
									<td style="text-align: center;">${p.idLong}</td>
									<td style="text-align: center;">${p.descripcion}</td>
								</tr>
							</c:forEach>
							</table>
							<button class="btn btn-primary md-close" onclick="cmTarea(${t.idTarea})">Cerrar</button>
			                
			            </div>
			        </div>
			    </div>
			</c:forEach>
	    
	    <script>
			// ops modal
		    function cmTarea(tarea){
		    	$("#modal-"+tarea).removeClass("md-show");
		    	document.getElementById("md-ov").style.opacity = 0;
				document.getElementById("md-ov").style.visibility = "hidden";
		    }
			function omTarea(tarea){
				
				document.getElementById("md-ov").style.opacity = 1;
				document.getElementById("md-ov").style.visibility = "visible";
				$("#modal-"+tarea).addClass("md-show");						
			}
		</script>
	    
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
