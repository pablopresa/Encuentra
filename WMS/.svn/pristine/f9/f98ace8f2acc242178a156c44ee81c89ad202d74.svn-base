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
			<script>
				function fnResetAllFilters() 
				{
					
					  var table = $('#dataTables-example').dataTable();
		 
				       // Remove all filtering
				      //table.fnFilterClear();
				      table.fnFilter('');
				    
				  }
			</script>
			<script>
				function enviar(texto) 
				{
					  fnResetAllFilters();
					  
					  document.getElementById('subm').value=texto;
					  document.getElementById('f1').setAttribute("target", "_blank");
				      document.getElementById('f1').submit();
				  }
			</script>
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Expedición&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Alta Envío&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Seleccionar Ruta</h3>   
                        <h6>Seleccione destino, elija documentos </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                        	<h5>
                        		Documentos a enviar
                        	</h5>
                             
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/DarDocumentosEnvio.do">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">								          
								          <label class="col-md-4 control-label" for="art" class="col-md-6 control-label">Ruta:&nbsp</label>
								          <div class="col-md-8">
								            	<select name="depo" id="depo" class="form-control">
								  					<c:forEach var="s" items="${RutasLista}">
								  						<c:if test="${s.idRuta==depositoSelected}">
								  							<option value="${s.idRuta}" selected="selected">${s.descripcion}</option>
								  						</c:if>
								  						<c:if test="${s.idRuta!=depositoSelected}">
								  							<option value="${s.idRuta}">${s.descripcion}</option>
								  						</c:if>
								  						</c:forEach>
								  				</select>
								          </div>
								        </div>
								    </div>
								      
									<div class="col-sm-7 col-lg-5">
								       <div class="form-group">
								          <label class="col-md-4 control-label" for="estanteria" class="col-md-4 control-label">Fechas:&nbsp</label>
								          <div class="col-md-8">
								            	<input type="text" class="form-control rango" name="fini" >
                                            </div>
								          </div>
								        </div>
								                               	
													
									<div class="col-sm-4 col-lg-2">
										<div class="form-group">	
											<label class="col-md-4 control-label" for="art" class="col-md-4 control-label"></label>										
												<div class="col-md-8">
											  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
											  	</div>											
										</div>
									</div>
								</div>
								</form>
								 </div>	
							
						
						<div class="alert alert-info">
                    		<strong>
                    			Orden de Carga del transporte 
								<c:forEach var="de" items="${envio.depositos}">
									${de.idDeposito}&nbsp;&nbsp;
								</c:forEach>
						<!--  <a href="<%=basePath%>/v3/altaEnvioModOrden.jsp">Modificar</a>-->
                    		</strong>            
                    	</div>
						
						<!--
						<div class="panel-heading">
                             Lista de Documentos para la entrega, para agregar complete los obligatorios (*)
                        </div>
                        <div class="panel-body">
							
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" >
                                    <thead>
                                        <tr>
                                           <th>(*)Numero</th>
											<th>(*)Razon</th>
											<th>(*)Cantidad de unidades</th>
											<th>(*)Dep. Origen</th>
											<th>Usuario</th>
											<th>observaciones</th>
											<th>&nbsp;</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<tr class="gradeD">
											<form action="<%=basePath%>AgrAEnvio.do"  id="f0" name="f0">
												<div style="width: 1px; height: 1px; visibility: hidden;">
													<c:forEach var="d" items="${documentosSel}">
														<c:if test="${d.incluir}">
															<input type="checkbox" name="${d.numeroDoc}" id="${d.numeroDoc}"  checked="checked">
														</c:if>
														<c:if test="${!d.incluir}">
															<input type="checkbox" name="${d.numeroDoc}" id="${d.numeroDoc}">
														</c:if>
													</c:forEach>
												</div>
												
												<td><input type="text" name="frmAgrNumero" style="width: 100%"/></td>
												<td>
													<select name="frmAgrRazon" style="width: 100%">
									  					<c:forEach var="r" items="${razonesDoc}">
									  						<option value="${r.id}">${r.descripcion}</option>
									  					</c:forEach>
									  				</select>
												</td>
												<td><input type="text" name="frmAgrUnidades" style="width: 100%"/></td>
												<td>
													<select name="frmAgrDepOr" id="frmAgrDepOr" class="select" style="width: 100%">
										  				<c:forEach var="s" items="${depositosSel}">
										  					<c:if test="${s.idDeposito==99}">
										  						<option value="${s.idDeposito}" selected="selected">${s.descripcion}</option>
										  					</c:if>
										  					<c:if test="${s.idDeposito!=99}">
										  						<option value="${s.idDeposito}">${s.descripcion}</option>
										  					</c:if>
										  				</c:forEach>
										  			</select>
												</td>
												<td><input type="text" name="usur" value="${uLogeado.nombre} ${uLogeado.apellido}" readonly="readonly" style="width: 100%"/></td>
												<td><input type="text" name="frmAgrObservaciones" style="width: 100%"/></td>
												<td><input type="submit" name="Submit"  class="button" value="Agregar a la lista" style="width: 100%"/></td>
											</form>													
										</tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                          -->
                        
                        
                        
                        
                        </div>
                        <hr/>
                        <div class="card">
						<div class="card-header">
							<h5>
                            	Lista de Documentos a incluir, Marque los que desea enviar
                           	</h5>
                        </div>
                        <div class="card-body">
							<div class="table-responsive">
								<form action="<%=basePath%>AltaEnvioIII.do" method="post" id="f1" name="f1">
	                            	<table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                           <th style="text-align: center; display: table-cell; vertical-align: middle;">Numero</th>
												<th style="text-align: center; display: table-cell; vertical-align: middle;">
												<a href="javascript:seleccionar_todo()">Incluir todos</a> <br> <a href="javascript:deseleccionar_todo()">Incluir ninguno</a> </th>
												<th style="text-align: center; display: table-cell; vertical-align: middle;">Razon</th>
												<th style="text-align: center; display: table-cell; vertical-align: middle;">Cantidad</th>
												<th style="text-align: center; display: table-cell; vertical-align: middle;">Origen</th>
												<th style="text-align: center; display: table-cell; vertical-align: middle;">Destino</th>
												<th style="text-align: center; display: table-cell; vertical-align: middle;">Usuario</th>
												<th style="text-align: center; display: table-cell; vertical-align: middle;">Observaciones</th>
												<th style="text-align: center; display: table-cell; vertical-align: middle;">Docs</th>
												
	                                        </tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="d" items="${documentosSel}">
												<tr class="gradeD">												
													<td style="text-align: center; display: table-cell; vertical-align: middle;">
													<a style="color: white" class="btn btn-primary md-trigger" data-modal="modal-${d.numeroDoc}" >${d.numeroDoc}</a></td>
													<c:if test="${d.incluir}">
														<td style="text-align: center; display: table-cell; vertical-align: middle;">
															<div class="checkbox checkbox-fill d-inline">
																<input type="checkbox" name="${d.numeroDoc}" id="checkbox-fill-${d.numeroDoc}" checked="checked" >
																<label for="checkbox-fill-${d.numeroDoc}" class="cr"></label>
															</div>
														</td>
													</c:if>
													<c:if test="${!d.incluir}">
														<td style="text-align: center; display: table-cell; vertical-align: middle;">
														
														<div class="checkbox checkbox-fill d-inline">
																<input type="checkbox" name="${d.numeroDoc}" id="checkbox-fill-${d.numeroDoc}" checked >
																<label for="checkbox-fill-${d.numeroDoc}" class="cr"></label>
															</div>
														</td>
													</c:if>
													<td style="text-align: center; display: table-cell; vertical-align: middle;">${d.razon.descripcion}</td>
													<td style="text-align: center; display: table-cell; vertical-align: middle;">${d.cantidad}</td>
													<td style="text-align: center; display: table-cell; vertical-align: middle;">${d.depositoO.id}</td>
													<td style="text-align: center; display: table-cell; vertical-align: middle;">${d.depositoD.descripcion}</td>
													<td style="text-align: center; display: table-cell; vertical-align: middle;">${d.usuario.descripcion}</td>
													<td style="text-align: center; display: table-cell; vertical-align: middle;">
														<div class="input-group input-group-sm mb-3">
															<input type="text" name="com${d.numeroDoc}" value="${d.comentario}" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"/>
														</div>
													</td>
													<td style="text-align: center; display: table-cell; vertical-align: middle;">
														<c:if test="${d.razon.id==3}">
															<c:if test="${d.listaDocs!=null}">
																<c:forEach var="l" items="${d.listaDocs}">
																	<a target="_BLANK" href="<%=basePath%>/pdf/r${l.descripcion}.pdf">${l.descripcion}</a>
																	<br/>
																</c:forEach>				
															</c:if> 
														</c:if> 
													</td>
													
													
												</tr>
											</c:forEach>
										</tbody>
	                                </table>
								</div>
	                            <div style="width: 1px;height: 1px; visibility: hidden;">
									<input type="text" name="subm" id="subm">
								</div>
								<br>
								<div class="row">
	                            	<div class="col-sm-6 col-lg-3">
								        <div class="form-group">								          
								          <div class="col-md-8" style="text-align: center;">
												<c:if test="${depositoSelected==null}">
													<a class="btn btn-info"
														href="<%=basePath%>AccionEnvio.do?idEnvio=${envio.idEnvio}&accion=1"
														target="_BLANK">Vista Previa</a>
												</c:if>
												<c:if test="${depositoSelected!=null}">
													<!-- <input type="submit" name="Submit"  class="button" value="Vista Previa"/> -->
													<a class="btn btn-info"
														href="javascript:enviar('Vista Previa');">Vista Previa</a>
												</c:if>
										</div>
								        </div>
								    </div>
								   
								    <div class="col-sm-6 col-lg-3">
								        <div class="form-group">
								          <div class="col-md-8" style="text-align: center;">
								            	<a class="btn btn-success" href="javascript:enviar('Asignar y Guardar');" >Asignar y Guardar</a>
								          </div>
								        </div>
								    </div>
								
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
                
     

<c:forEach var="d" items="${documentosSel}">
	<div class="md-modal md-effect-1" id="modal-${d.numeroDoc}">
        <div class="md-content">
            <h3 class="theme-bg2">${d.razon.descripcion} &nbsp;${d.numeroDoc}</h3>
            <div>
                <table class="table table-striped table-bordered table-hover">
					<tr>
						<th style="text-align: center;">Articulo</th>
						<th style="text-align: center;">Cantidad</th>
					</tr>
				<c:forEach var='p' items='${d.articulos}'>
					<tr>
						<td style="text-align: center;">${p.id}</td>
						<td style="text-align: center;">${p.cantidad}</td>
					</tr>
				</c:forEach>
				</table>
				<button class="btn btn-primary md-close" style="display: none">Cerrar</button>
                <!--<a class="btn btn-primary md-close" onclick="closeModal('modal-${d.numeroDoc}')" href="#">Cerrar</a>  -->
                
            </div>
        </div>
    </div>
</c:forEach>           
<div class="md-overlay"></div>  
        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
		
<!-- <script>
	function closeModal(id){
		$(id).modal('hide');
	}
</script> -->		
