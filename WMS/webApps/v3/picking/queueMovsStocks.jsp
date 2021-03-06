<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
		
		
		<c:set var = "colapsar" scope = "request" value = "yes"/>
		
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		
        <!-- /. NAV SIDE  -->
        	   	<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Movimientos</h3>   
                        <h6>Puede reintentar el envio de los fallidos</h6>
                    </div>
                </div>              
        <!-- /. ROW  -->
         <hr />
         <div class="row" >
            <div class="col-md-12">
            	<c:if test="${menError!=null}">
	                    <div class="alert alert-info">
	                    	<strong style="color: red">${menError}</strong>            
	                    </div>
                </c:if>
                <div class="card">
					<div class="card-header">
						<h5>Lista de los ultimos 1000 Movimientos </h5>
					</div>
                	<div class="card-body">
						<div class="table-responsive">
	                            	<table class="display table nowrap table-striped table-hover dataTable" id="encuentra-ordenada">
	                                    <thead class="thead-dark">
	                                        <tr>
	                                        	<th class="text-center">Fecha</th>
												<th class="text-center">Usuario</th>												
												<th class="text-center">Destino</th>
												<th class="text-center">Cantidad</th>
												<th class="text-center">Observacion</th>
												<th class="text-center">Intentos</th>
												<th class="text-center">Estado</th>												
	                                        </tr>
	                                    </thead>
	                                    <tbody id="showMovStock">
											<c:forEach var="m" items="${MovStock}">															
												<tr>													
													<td class="text-center">${m.fecha}</td>
													<td class="text-center">${m.usuario}</td>
													<td class="text-center">${m.destino} - ${m.nombreDestino}</td>	
													<td class="text-center">${m.cantidad}</td>	
													<td class="text-center">${m.msjErp}</td>	
													<td class="text-center">${m.intentos}</td>	
													<c:if test="${m.estado==1}">
														<c:if test="${m.destino!=1200}">
															<td class="text-center">
																<a target="_BLANK" class="btn btn-success" href="<%=basePath%>/pdf/r${m.doc}.pdf">${m.doc}</a>
															</td>
														</c:if>
														<c:if test="${m.destino==1200}">
															<td class="text-center">
																<a target="_BLANK" class="btn btn-success" href="<%=basePath%>/pdf/rp${m.origenDoc}.pdf">${m.doc}</a>
															</td>
														</c:if>
													</c:if>
													<c:if test="${m.estado!=1}">
														<td class="text-center"><button class="btn btn-danger" onclick="re_send(${m.id})" id="re_send${m.id}">Re-Enviar</button></td>
													</c:if>								
												</tr>
											</c:forEach>
										</tbody>
	                                </table>
	                                </div>
					</div>
				</div>
			</div>	
        </div>
        
        <script>
        
        var in_send = false;
       
    	function re_send(id){
    		  var btn = document.getElementById("re_send"+id);
    	      // disable button
    	      btn.disabled = true; 
    	      // add spinner to button
    	      btn.innerHTML ='<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Re-Enviando...';
    	      in_send = true;
    	      location.assign('<%=basePath%>/MovStock_reSend.do?id='+id);
    	}
   
        
        function doRefresh()
		 {
        	if(!in_send){
        		$("#showMovStock").load("<%=basePath%>MovsStock.do?monitor=true");
        	}
       		
	    }
	    $(function() 
	    {
	        setInterval(doRefresh, 60000);
	    });
        </script>
        		
        <jsp:include page="/v3/util/footer.jsp"></jsp:include>
		