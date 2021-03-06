<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		
		<link href="<%=basePath%>v3/assets/css/bootstrap-switch.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=basePath%>v3/assets/js/switch/bootstrap-switch.js"></script>
        <!-- /. NAV SIDE  -->
            	<div class="row">
                    	<div class="text-left col-sm-6 col-lg-6"> 
							<div class="flex-container">	
                       			 <h3> Editando Pedido #${pedidoUpdate.get(0).idPedido} </h3>
                    		</div>
                    	</div>
                    	<div class="text-right col-sm-6 col-lg-6"> 
							<div class="flex-container">	
                       			 <a href="<%=basePath%>consolaPedidosEcommerce.do" class="btn btn-info"><span class="fas fa-arrow-left"></span>&nbsp;Atr?s</a>
                    		</div>
                    	</div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red">${menError}</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="accordion" id="accordionExample">
						<div class="card">
					        <div class="card-header" id="headingOne">
					            <h5 class="mb-0"><a href="#!" class="collapsed" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">Formulario de edici?n de pedido</a></h5>
					        </div>
					        <div id="collapseOne" class=" card-body collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
					        	<div class="card-body">
	                            <form class="form-horizontal" role="form" method="post"  action="<%=basePath%>/editarPedidoEcommerceII.do">
	                            	<div class="row">
		                            	<div class="col-sm-6 col-lg-4">
									        <div class="form-group">								          
									            <label class="col-md-12 control-label" for="art">Nro. Pedido:&nbsp;</label>
		        								<div class="col-md-8">
		            								<input type="text" class="form-control" name="pedido" value ="${pedidoUpdate.get(0).idPedido}" readonly>
		       									</div>
									        </div>
									    </div>
																		
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-12 control-label" for="art">Cliente:&nbsp;</label>
	        										<div class="col-md-8">
	            										<input type="text" class="form-control" name="cliente" value = "${pedidoUpdate.get(0).descripcion }" readonly>
	        										</div>										
											</div>
										</div>
										
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-12 control-label" for="art">Orden:&nbsp;</label>
	        										<div class="col-md-8">
	            										<input type="text" class="form-control" name="orden" value="${pedidoUpdate.get(0).idOrden}" readonly>
	        										</div>
											</div>
										</div>
										
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-12 control-label" for="art">Factura:&nbsp;</label>
	       											 <div class="col-md-8">
	            										<input type="text" class="form-control" name="factura" value="${pedidoUpdate.get(0).idFactura}">
	        										</div>
											</div>
										</div>
										
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-12 control-label" for="art">Forma de Pago:&nbsp;</label>
												<div class="col-md-8">
	           										 <input type="text" class="form-control" name="formaP" value="${pedidoUpdate.get(0).formaPago}" readonly>
	       										</div>
											</div>
										</div>
										
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
											  <label class="col-md-12 control-label" for="art">Despachado:&nbsp;</label>
									          <div class="col-md-8">
									           		<c:if test="${pedidoUpdate.get(0).idEstado==0 || pedidoUpdate.get(0).idEstado==1 || pedidoUpdate.get(0).idEstado==2 || pedidoUpdate.get(0).idEstado==3 || pedidoUpdate.get(0).idEstado==10 || pedidoUpdate.get(0).idEstado==20 || pedidoUpdate.get(0).idEstado==25 || pedidoUpdate.get(0).idEstado==30 || pedidoUpdate.get(0).idEstado==33 || pedidoUpdate.get(0).idEstado==34 || pedidoUpdate.get(0).idEstado==44}"> 
											        	<input type="checkbox" name="estado" >
											        </c:if>
											        <c:if test="${pedidoUpdate.get(0).idEstado==4 || pedidoUpdate.get(0).idEstado==5 || pedidoUpdate.get(0).idEstado==6 || pedidoUpdate.get(0).idEstado==40}"> 
											        	<input type="checkbox" name="estado" disabled="disabled" >
											        </c:if>
									          </div>										
											</div>
										</div>
										
										
										
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Canal:&nbsp;</label>
												<div class="col-md-8">
	           										 <input type="text" class="form-control" name="formaP" value="${pedidoUpdate.get(0).idCanal}" readonly>
	       										</div>
											</div>
										</div>
								</div>	
									
	                    	<div class="card-header">
	                            <h5>Articulos &nbsp;</h5>
	                        </div>
	                        <div class="card-body">
	                        		<c:if test="${pedidoUpdate!=null}">
	                            	<table class="table table-compact table-striped table-bordered table-hover" id="encuentra-default" >
		                                    <thead>
		                                        <tr>
													<th class="text-center">Articulo</th>
													<th class="text-center">Deposito Pedido</th>
													<th class="text-center">Unidades<br/>Pedidas</th>
													<th class="text-center">Unidades<br/>Procesadas</th>
													<th class="text-center">Unidades<br/>Confirmadas</th>
													<th class="text-center">Cambio</th>
													<th class="text-center">Eliminar</th>
													<th class="text-center">Confirmar</th>
												</tr>
		                                    </thead>
		                                    <tbody>
												<c:forEach var="p" items="${pedidoUpdate}">
													<tr class="gradeD" style="border-collapse: collapse; border-bottom: 1px dashed black; font-size: 13px ">
														<td class="text-center" style="width: 80px;">
															<input type="text" class="form-control" name="${p.articulo}" value="${p.articulo}" ">
														</td>
														<td class="text-center">
<%-- 															<select class="form-control" tabindex="6" name="${p.articulo}dep">															 --%>
<%-- 												    				<c:forEach var="dep" items="${deps}"> --%>
<%-- 												    					<c:if test="${p.deposito!=dep.id}"> --%>
<%-- 												        					<option value="${dep.id}">${dep.id}</option> --%>
<%-- 												        				</c:if>	 --%>
<%-- 												        				<c:if test="${p.deposito==dep.id}"> --%>
<%-- 												        					<option value="${dep.id}"selected="selected">${dep.id} </option> --%>
<%-- 												        				</c:if>										        				 --%>
<%-- 												    				</c:forEach> --%>
<!-- 												  			</select> -->
															<input type="text" class="form-control" name="${p.articulo}dep" value="${p.deposito}" readonly>
											  			</td>
														<td class="text-center">
															<input type="text" class="form-control" name="${p.articulo}req" value="${p.cr}" readonly>
														</td>
														<td class="text-center">
															<input type="text" class="form-control" name="${p.articulo}proc" value="${p.cp}" readonly>
														</td>	
														<td class="text-center">
															<input type="text" class="form-control" name="${p.articulo}" value="${p.cc}" readonly>
														</td>	
														<c:if test="${pedidoUpdate.get(0).change==0}"> 
															<td class="text-center">
																<div class="form-group">
												                    <div class="checkbox checkbox-primary checkbox-fill d-inline m-r-10">
												                        <input type="checkbox" name="${p.articulo}change" id="checkbox-p-fill" disabled>
												                        <label for="checkbox-p-fill" class="cr"></label>
												                    </div>
												                </div>
											        			<!--<input type="checkbox" name="${p.articulo}change" > -->
											        		</td>	
											       		</c:if>
											       		 <c:if test="${pedidoUpdate.get(0).change==1}"> 
											       		 	<td class="text-center">
												       		 	<div class="form-group">
												                    <div class="checkbox checkbox-primary checkbox-fill d-inline m-r-10">
												                        <input type="checkbox" name="${p.articulo}change" id="checkbox-p-fill" disabled checked>
												                        <label for="checkbox-p-fill" class="cr"></label>
												                    </div>
												                </div>
											        			<!-- <input type="checkbox" name="${p.articulo}change" checked="checked" disabled="disabled" > -->
											        		</td>
											       		 </c:if>											
														<td class="text-center bg-white">
															<a href="javascript:deleteArt('${pedidoUpdate.get(0).idPedido}','${p.articulo}','${p.deposito}','${p.cr}')"> <button type="button" class="btn btn-icon btn-outline-danger"><i class="feather icon-slash"></i></button> </a>
														</td>
														<td class="text-center bg-white">
															<a href="javascript:confirmarArt('${pedidoUpdate.get(0).idPedido}','${p.articulo}','${p.deposito}')"> <button type="button" class="btn btn-icon btn-outline-success"><i class="feather icon-check-circle"></i></button> </a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
		                          </c:if>
		                       
							</div>
							<div class="text-center col-sm-12 col-lg-12"> 
								<div class="flex-container">			
									<a class="btn btn-danger" href="javascript:deleteLinea(${pedidoUpdate.get(0).idPedido});">Cancelar Pedido</a>
									<button type="submit" class="btn btn-success" name="update">Modificar Pedido</button>
	                        	</div>
		                    </div>
		                 </form>                   
	                        
                     </div>
					        </div>
					    </div>
					    <div class="card">
					        <div class="card-header" id="headingTwo">
					            <h5 class="mb-0"><a href="#!" class="collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Formulario de edici?n de datos del cliente</a></h5>
					        </div>
					        <div id="collapseTwo" class="collapse card-body" aria-labelledby="headingTwo" data-parent="#accordionExample">
					        	<div class="card-body">
		                            <form class="form-horizontal" role="form" method="post" action="<%=basePath%>/editarPedidoEcommerceIII.do">
		                            	<div class="row">
											<div class="col-sm-6 col-lg-4">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">Nombre:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cNombre" value="${cliente.nombre}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-6 col-lg-4">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">Apellido:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cApellido" value="${cliente.apellido}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-6 col-lg-4">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">Direccion:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cDireccion" value="${cliente.calle}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-3 col-lg-2">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">N? Puerta:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cNroPuerta" value="${cliente.nroPuerta}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-3 col-lg-2">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">N? Apto.:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cNroApto" value="${cliente.nroApto}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-6 col-lg-4">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">Ciudad:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cCiudad" value="${cliente.ciudad}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-6 col-lg-4">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">Departamento:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cDepartamento" value="${cliente.departamento}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-6 col-lg-4">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">C?digo Postal:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cCodigo" value="${cliente.cp}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-6 col-lg-4">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">Tel?fono:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cTelefono" value="${cliente.telefono}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-6 col-lg-4">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">Nro. Documento:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cDocumento" value="${cliente.documentoNro}">
	        										</div>
												</div>
											</div>
											<div class="col-sm-6 col-lg-12">
												<div class="form-group">
													<label class="col-md-12 control-label" for="art">Observaciones:&nbsp;</label>
	       											<div class="col-md-12">
	            										<input type="text" class="form-control" name="cObs" value="${cliente.obs}">
	        										</div>
												</div>
											</div>
										</div>
										<br>
										<div class="text-center col-sm-12 col-lg-12"> 
											<div class="flex-container">			
												<button type="submit" class="btn btn-success">Guardar Cambios</button>
				                        	</div>
					                    </div>
									</form>
								</div>
					        </div>
					    </div>
				    </div>
               </div>  
                <div class="col-md-12">
                	
                    <!-- Advanced Tables -->
                    <div class="card">
                    <div class="card-header">
                            <h5>NOTAS &nbsp;</h5>
                        </div>
                        <div class="card-body">
                        		<c:if test="${notas!=null}">
                            	<table class="table table-compact table-striped table-bordered table-hover" id="encuentra-default">
	                                    <thead>
	                                        <tr>
												<th style="text-align: center;">Id</th>
												<th style="text-align: center;">Usuario</th>
												<th style="text-align: center;">Nota</th>
												<th style="text-align: center;">Fecha</th>
												
											</tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="n" items="${notas}">
												<tr class="gradeD" style="border-collapse: collapse; border-bottom: 1px dashed black; font-size: 13px">
													<td style="padding: 10px; vertical-align: middle; text-align: center;">${n.idNota}</td>
													<td style="padding: 10px; vertical-align: middle; text-align: center; padding-left: 20px;">${n.usuario}</td>											
													<td style="padding: 10px; vertical-align: middle; text-align: center;">${n.txtNota}</td>
													<td style="padding: 10px; vertical-align: middle; text-align: center;">${n.fechaNota.darFechaDia_Mes_Anio_HoraBarra()}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
	                          </c:if>
	                          
						</div>
							<form class="form-horizontal" role="form" method="post"  action="<%=basePath%>/IngresarNota.do">
								<div class="text-center col-sm-12 col-lg-12"> 
									<div class="flex-container">	
										<textarea rows="5" cols="156" name="notaText"></textarea>
									</div>
								</div>
								<div class="text-center col-sm-12 col-lg-12"> 
									<div class="flex-container">	
										<button type="submit" class="btn btn-success" name="update">Ingresar Nota</button>
									</div>
								</div>
							</form>			
                    </div>
                 </div>
            </div> <!-- FIN FORM COMENTARIOS -->
		
                    <!--End Advanced Tables -->
                <!-- /. ROW  -->
        
        <script type="text/javascript">
	        function deleteArt(ped,arti,depo,canti)
				{
					 
					if (confirm('Seguro que desea eliminar este articulo?, esta acci?n no tiene forma de deshacerse'))
					{
					var req = '<%=basePath%>DeleteArtPedidoEcommerceII.do?pedido='+ped+'&arti='+arti+'&depo='+depo+'&canti='+canti;
						window.location.assign(req);
					}
					
					
				}
				
				function confirmarArt(ped,arti,depo)
				{
					 
					if (confirm('Seguro que desea confirmar este articulo?, esta acci?n no tiene forma de deshacerse'))
					{
					var req = '<%=basePath%>ConfirmarArtPedidoEcommerceII.do?pedido='+ped+'&arti='+arti+'&depo='+depo;
						window.location.assign(req);
					}
					
					
				}
        </script>    
        
        <script type="text/javascript">
        function deleteLinea(ped)
			{
				 if(confirm("Seguro?"))
				 {
					 var req = '<%=basePath%>/EcommerceCancelarPedido.do?idPedido=ped';
					 window.location.assign(req);
				 }
				
				
				
			}
        </script>
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
