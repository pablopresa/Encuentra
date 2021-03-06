<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<input type="text" value= "<%=basePath%>" id ="basePath" style="display: none">   							
                           		<table class="table table-compact table-bordered table-hover" id="tablaEnc-monitor">
									<thead class="thead-dark">
	                                	<tr>
	                                     	<th class="text-center">Pedido<br/>/Venta</th>
	                                      	<th class="text-center">Cliente</th>
	                                       	<th class="text-center">Estado<br/>Encuentra</th>
	                                       	<c:if test="${aConsolidar!='on'}">
												<th class="text-center">Articulo</th>
											</c:if>
											<c:if test="${aConsolidar!='on'}">	
												<th class="text-center">Deposito<br/>Pedido</th>
											</c:if>
											<c:if test="${aConsolidar=='on'}">	
												<th class="text-center">Cant.<br/>Depositos</th>
												<th class="text-center" style="display: none;"></th>
											</c:if>
											<th class="text-center">Unidades<br/>Procesadas</th>
											<th class="text-center">Fecha<br/>Pedido</th>
											<th class="text-center">Fecha<br/>Confirmado</th>
											<th class="text-center">Fecha<br/>Procesado</th>
											<th class="text-center">Destino</th>
											<c:if test="${aConsolidar=='on'}">	
												<th class="text-center">Consolidar</th>
											</c:if>
											<c:if test="${aConsolidar!='on'}">	
												<th class="text-center" style="display: none;"></th>
											</c:if>
											<th class="text-center">Forma Envio</th>
											<th class="text-center">Canal</th>
											<th class="text-center">Acci?n</th>
											
											
											
										</tr>
									</thead>
	                                <tbody>
	                            
									<c:forEach var="p" items="${lstPedidosConsola}">
										<c:if test="${p.change==1}">	
											<c:set var="color" value="border-collapse:collapse;border-bottom:1pxdashedblack;font-size:13px;background:#87FF8D;"></c:set> 
										</c:if>
										<c:if test="${p.artMod==1}">	
											<c:set var="color" value="border-collapse:collapse;border-bottom:1pxdashedblack;font-size:13px;background:#FFB861;"></c:set>													
										</c:if>
										<c:if test="${p.estado=='Cancelado'}">	
											<c:set var="color" value="border-collapse:collapse;border-bottom:1pxdashedblack;font-size:13px;"></c:set> 
											<c:set var="badge" value="class=\"badge badge-danger\""></c:set> 	
											<c:set var="btn" value="btn btn-danger"></c:set> 														
										</c:if>
										<c:if test="${p.estado!='Cancelado'}">	
											<c:set var="badge" value="class=\"badge badge-dark\""></c:set> 	
											<c:set var="btn" value="btn btn-secondary"></c:set> 													
										</c:if>
										<c:if test="${p.change!=1 && p.artMod!=1 && p.estado!='Cancelado'}">	
											<c:set var="color" value="border-collapse:collapse;border-bottom:1pxdashedblack;font-size:13px;"></c:set> 
										</c:if>
										<c:if test="${p.aConsolidar=='SI'}">	
											<c:set var="color" value="border-collapse:collapse;border-bottom:1pxdashedblack;font-size:13px;background:#ffeeba;"></c:set> 
										</c:if>
										
										<tr class="gradeD" style=<c:out value="${color}"></c:out>>	
										<c:if test="${p.subpedido == ''}">
											<td class="text-center"><h5><a href="<%=basePath%>EcommerceLogPedido.do?idPedido=${p.idPedido}" ${badge}>${p.idPedido}</a></h5>
											<a href="${urlMonitorEC}${p.idMS}" target="_blank">${p.idMS}</a></td>	
										</c:if>
										<c:if test="${p.subpedido != ''}">
											<td class="text-center"><h5><a href="<%=basePath%>EcommerceLogPedido.do?idPedido=${p.idPedido}" ${badge}>${p.idPedido}</a></h5>
											<a href="${urlMonitorEC}${p.idMS}" target="_blank">${p.idMS}</a><hr>${p.subpedido}</td>	
										</c:if>
											<td class="text-center">${p.descripcion}</td>													
											<td class="text-center">${p.estado}</td>		
											<c:if test="${aConsolidar!='on'}">											
												<td class="text-center">${p.articulo} <br><strong>${p.descArt}</strong></td>
											</c:if>
											<c:if test="${aConsolidar!='on'}">											
												<td class="text-center">${p.depositoNombre}</td>
											</c:if>
											<c:if test="${aConsolidar=='on'}">											
												<td class="text-center">${p.depositoNombre}</td>
												<td class="text-center" style="display: none;"></td>
											</c:if>
											
											<td class="text-center">${p.cp}/${p.cr}</td>
											<td class="text-center">${p.fechaR}</td>
											<td class="text-center">${p.fechaC}</td>
											<td class="text-center">${p.fechaP}</td>
											<td class="text-center">${p.destinoNombre}</td>
											<c:if test="${aConsolidar=='on'}">	
												<c:if test="${p.aConsolidar=='SI'}">	
													<td class="text-center"><span class="fas fa-code-branch" style="color: red;"></span><label class="hidden">${p.aConsolidar}</label></td>
												</c:if>
												<c:if test="${p.aConsolidar!='SI'}">	
													<td class="text-center"><label class="hidden">${p.aConsolidar}</label></td>
												</c:if>
											</c:if>
											<c:if test="${aConsolidar!='on'}">				
												<td class="text-center" style="display: none;"></td>
											</c:if>
											<td class="text-center">${p.shippingType.descripcion}</td>
											<td class="text-center">${p.idCanal}</td>
											
											<td class="text-center">
						                        <div class="btn-group mb-2 mr-2">
										            <button class="${btn} dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-cog"></i></button>
										            <div class="dropdown-menu">
										                <a class="dropdown-item" href="#" onclick="shippPedido(${p.idPedido},${p.idDestino},'${p.tracking}','${p.urlEriqueta}')">
														<i class="fas fa-truck"></i>Env?o</a>
														<a class="dropdown-item" href="#" onclick="editPedido(${p.idPedido})"><i class="fas fa-edit"></i>Editar</a>
								                   		<a class="dropdown-item" <c:if test="${p.urlEriqueta!=''}">href="${p.urlEriqueta}"</c:if> target="_blank"><i class="fas fa-print"></i>Etiqueta</a>     
										            </div>
										        </div>												
											</td>
											
										</tr>
										
									</c:forEach>
									</tbody>
								</table>
					
<script>
function editPedido(pedido){
	location.assign('<%=basePath%>editarPedidoEcommerce.do?idPedido='+pedido);
}
function printPedido(pedido){
	location.assign('<%=basePath%>reprintEcommerce.do?req=ok&idPedido='+pedido);
}

</script>


		