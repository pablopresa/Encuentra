<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
       
        
        <jsp:include page="/v3/util/index_headerSM.jsp"></jsp:include>
		
		  
		  
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
		  
		  
		 
		  
		  
        <!-- /. NAV SIDE  -->
        
		    	
                
               <div class="bg-white">
                <div class="row" >
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> ${menError}"</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                    <div class="panel-heading">
                            &nbsp;Lista de Documentos &nbsp;
                        </div>
                        <div class="panel-body">
                        	<div>
                        	<form id="formularioDocs" method="post"action="<%=basePath%>/selMovStockRecep.do">
                        		<input type="text" name="listaDocs" value="1" style="visibility: hidden;">
                            	<table class="table table-compact table-bordered table-hover responsive"  width="100%" id="encuentra-mob" border=1 frame=void rules=rows cellpadding="1" >
	                                    <thead>
	                                        <tr>
	                                           	<th>Orgn.</th>
	                                           	<th>Documento</th>
	                                           	<th>Pedido</th>
	                                           	<th>Ctd.</th>
												<th>Sel.</th>
												<th>Usar</th>
												<th>Fecha</th>
												<th>Comentario</th>
											</tr>
											<tr>
	                                           	<th colspan="7" style="text-align: center;">
	                                           		<button id="envio" class="button" style="width: 100%; text-align: center; ">usar seleccionados</button>
	                                           	</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    <c:if test="${remitosPos!=null}">
	                                    	<c:forEach var="d" items="${remitosPos}">
												<tr class="gradeD">						
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.descOrigen}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.numeroDoc}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.idPedidoWEB}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.unidades}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;"><input type="checkbox" name="${d.idOrigen}_${d.numeroDoc}"> </td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;"><a href="<%=basePath%>selMovStockRecep.do?nroDoc=${d.idOrigen}_${d.numeroDoc}"><img alt="Usar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a> </td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.fecha}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.comentario}</td>
												</tr>
											</c:forEach>
	                                    </c:if>
	                                     <c:if test="${remitosPos==null}">
											<c:forEach var="d" items="${remitosPen}">
												<tr class="gradeD">						
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.descOrigen}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.numeroDoc}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.idPedidoWEB}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.unidades}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;"><input type="checkbox" name="${d.idOrigen}_${d.numeroDoc}"> </td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;"><a href="<%=basePath%>selMovStockRecep.do?nroDoc=${d.idOrigen}_${d.numeroDoc}"><img alt="Usar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a> </td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.fecha}</td>
													<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.comentario}</td>
												</tr>
											</c:forEach>
										</c:if>
										</tbody>
										
									</table>
	                          	</form>
	                          
						</div>
                    </div>
                 </div>
            	</div>
               </div>
            </div>
            <script type="text/javascript">
            	var form = document.getElementById("formularioDocs");
				document.getElementById("envio").addEventListener("click", function () {
				  form.submit();
				});
            </script>
            <jsp:include page="/v3/util/footer_mob.jsp"></jsp:include>
