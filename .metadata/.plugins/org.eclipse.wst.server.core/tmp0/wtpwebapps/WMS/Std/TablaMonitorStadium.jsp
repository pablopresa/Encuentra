<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

    
                           		<table class="table table-compact table-bordered table-hover" id="tablaEnc-monitor-Std" border=1 frame=void rules=rows cellpadding="1" >
	                                    <thead class="thead-dark">
	                                        <tr>
	                                           	<th class="text-center">Pedido</th>
	                                           	<th class="text-center">Canal</th>
	                                           	<th class="text-center">Cliente</th>
	                                           	<th class="text-center">Estado</th>
												<th class="text-center">Órden/<br/>Factura</th>
												<th class="text-center">F. Pago</th>
												<th class="text-center">Articulo</th>
												<th class="text-center">Dep.<br/>Pedido</th>
												<th class="text-center">Cant</th>
												<th class="text-center">Fecha<br/>Pedido</th>
												<th class="text-center">Fecha<br/>Conf.</th>
												<th class="text-center">Fecha<br/>Proc.</th>
												<th class="text-center">Destino</th>
												<th class="text-center">Evento</th>
												<th class="text-center">Etiqueta<br/>/Editar</th>
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
													<c:set var="color" value="border-collapse:collapse;border-bottom:1pxdashedblack;font-size:13px;background:#FF6961;"></c:set> 													
												</c:if>
												<c:if test="${p.change!=1 && p.artMod!=1 && p.estado!='Cancelado'}">	
													 <c:set var="color" value="border-collapse:collapse;border-bottom:1pxdashedblack;font-size:13px;"></c:set> 
												</c:if>
												<c:if test="${p.idDestino==801 && p.estado!='Cancelado'}">	
													 <c:set var="color" value="border-collapse:collapse;border-bottom:1pxdashedblack;font-size:13px;background:#ffeeba"></c:set> 
												</c:if>
												
												<tr class="gradeD" style=<c:out value="${color}"></c:out>>											
													<td class="text-center"><a href="<%=basePath%>EcommerceLogPedido.do?idPedido=${p.idPedido}">${p.idPedido}</a></td>
													<td class="text-center">${p.idCanal}</td>
													<td class="text-center">${p.descripcion}</td>													
													<td class="text-center">${p.estado}</td>													
													<td class="text-center">${p.idOrden}<br/>
													<a target="_BLANK" href="${p.urlFactura}">${p.idFactura}</a></td>
													<c:if test="${p.idCanal=='ML Std' || p.idCanal=='ML Promo' || p.idCanal=='Clks'}">
														<td class="text-center">MercadoPago</td>
													</c:if>
													<c:if test="${p.idCanal=='Std' || p.idCanal=='MC'}">
														<td class="text-center">${p.formaPago}</td>
													</c:if>
													<c:if test="${p.idCanal!='ML Std' && p.idCanal!='ML Promo' && p.idCanal!='Clks' && p.idCanal!='Std' && p.idCanal!='MC'}">
														<td class="text-center"> - </td>
													</c:if>
													<td class="text-center">${p.articulo}</td>
													<td class="text-center">${p.deposito}</td>
													<td class="text-center">${p.cp} / ${p.cr}</td>
													<td class="text-center">${p.fechaR}</td>
													<td class="text-center">${p.fechaC}</td>
													<td class="text-center">${p.fechaP}</td>
													<td class="text-center">${p.idDestino}</td>
													<td class="text-center">
													
													<c:if test="${p.change==1}">
														<span class="fas fa-sync-alt"></span>
													</c:if>
													<c:if test="${p.artMod==1}">
														 <span class="fas fa-pencil-alt"></span>
													</c:if>
													<c:if test="${p.estado=='Cancelado'}">
														<span class="fas fa-window-close"></span>
													</c:if>	
													<c:if test="${p.idDestino==801}">
														<span class="fas fa-bolt" style="color: red;"></span>
													</c:if>	
													</td>
													<td class="text-center">
														<div class="btn-group">
															<c:if test="${p.etiqueta==true}">
																<button type="button" onclick="changeLabel(${p.idPedido},${p.idDestino},'${p.estado}', '${p.urlEriqueta}')" class="btn btn-success btn-sm" data-toggle="modal" data-target=".bd-example-modal-lg"><span class="fas fa-tag"></span></button>
															</c:if>
															<c:if test="${p.etiqueta!=true}">
																<button type="button" onclick="changeLabel(${p.idPedido},${p.idDestino},'${p.estado}','')" class="btn btn-warning btn-sm" data-toggle="modal" data-target=".bd-example-modal-lg"><span class="fas fa-tag"></span></button>
															</c:if>
															<a href="<%=basePath%>editarPedidoEcommerce.do?idPedido=${p.idPedido}"><button type="button" class="btn btn-dark btn-sm"><span class="far fa-edit"></span></button></a>
														</div>
													</td>
													
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									 <script type="text/javascript">
        // Get the modal
           var modal = document.getElementById('myModal');

           // Get the button that opens the modal
           var btn = document.getElementById("myBtn");

           // Get the <span> element that closes the modal
           var span = document.getElementsByClassName("close")[0];

           // When the user clicks on the button, open the modal
           btn.onclick = function() {
        	   var modal = document.getElementById('myModal');
        	   modal.style.display = "block";
           }

           // When the user clicks on <span> (x), close the modal
           span.onclick = function() {
        	   var modal = document.getElementById('myModal');
        	   modal.style.display = "none";
           }

           // When the user clicks anywhere outside of the modal, close it
           window.onclick = function(event) {
               if (event.target == modal) {
            	   var modal = document.getElementById('myModal');
            	   modal.style.display = "none";
               }
           } 
           		function changeLabel(pedido, destino,estado, urlE)
           		{
           			var modal = document.getElementById('myModal');
               			document.getElementById("pedidoEt").value = pedido;
               			document.getElementById("destinoEt").value = destino;
               			document.getElementById("estadoEt").value = estado;
               			
               			
               			modal.style.display = "block";
           			
           		}
           		
           		function cerrar()
           		{
           			var modal = document.getElementById('myModal');
           			modal.style.display = "none";
           		}
           </script>
						    <!-- Modal content -->
			<div id="myModal" class="modal">
			<div class="modal-content">
			  <div class="panel-footer">
			  <p style="font-size: 14pt; color: grey;">Agregar URL etiqueta</p> 
			  </div>
			  <div class="modal-body" style="height: 200px; padding: 25px;">
			  	<form class="form-horizontal" role="form" enctype="MULTIPART/FORM-DATA" method="post"  action="<%=basePath%>/EcommerceAsociariqueta.do">
			  	 <div class="form-row">
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="art" class="col-md-4 control-label">N. Pedido:&nbsp;</label>
							<div class="col-md-8">
								<input type="text" readonly="readonly"  class="form-control" tabindex="6" name="pedido" id="pedidoEt"/>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="p2">&nbsp;</label>
							<div class="col-md-8">
							<input type="hidden" class="form-control" tabindex="6" name="estado" id="estadoEt"/>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="dtn" class="col-md-4 control-label">Nuevo destino:&nbsp;</label>
							<div class="col-md-8">
								<select class="form-control" tabindex="6" name="destino" >
									<c:forEach var="p" items="${listadtn}">
										<option value="${p.id}">${p.id}&nbsp-&nbsp ${p.descripcion}</option>
									</c:forEach>
								</select>
								
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="art" class="col-md-4 control-label">etiqueta:&nbsp;</label>
							<div class="col-md-8">
								<input type="file" class="form-control" tabindex="6" name="url" id="urlET"/>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="p2">&nbsp;</label>
							<div class="col-md-8">								
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="des" class="col-md-4 control-label">Destino actual:&nbsp;</label>
							<div class="col-md-8">
								<input type="text" readonly="readonly"  class="form-control" tabindex="6" name="destinoI" id="destinoEt"/>
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="p2">&nbsp;</label>
							<div class="col-md-8">								
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="p2">&nbsp;</label>
							<div class="col-md-8">								
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-md-4 control-label" for="p2">&nbsp;</label>
							<div class="col-md-8">
								<button type="submit" class="btn btn-success">Asociar etiqueta</button>
							</div>
						</div>
				</div>	
				<div class="panel-footer" style="text-align: right: ;">
			    	<h3 onclick="cerrar()" style="text-align: left; width: 100; cursor: pointer;">Cerrar</h3>
			  	</div>
			    </form>
			  </div>

			</div> 
		       </div>
		       