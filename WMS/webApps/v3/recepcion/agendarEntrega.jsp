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
		 function handleEnter (field, event) 
			{  
				 var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;  
				 if (keyCode == 13) {  
					 var i;  
					 for (i = 0; i < field.form.elements.length; i++)  
						 if (field == field.form.elements[i])  
							 break;  
					 i = (i + 1) % field.form.elements.length;  

					 field.form.elements[i].focus();  
					 return false;  
				 }   
				 else  
					return true;  
	 		}
		
		
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
			
			//La funci?n seleccionar_todo() realiza un recorrido por todos los elementos del formulario. Para hacer un recorrido por todos los campos se utiliza el array "elements", que guarda una referencia con cada elemento que haya dentro del formulario.
			
			//En el recorrido comprueba si el elemento actual es de tipo "checkbox" (recordar que el array elements contiene todos los elementos, pero s?lo deseamos operar con los que sean checkbox) y en ese caso, simplemente se actualiza el atributo "checked" al valor 1, con lo que el chekbox se marcar?.
			
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
			
			
			function seleccionarIgual(idPadre)
			{
			  if(!document.getElementById(idPadre).checked)
			  {
			    	document.getElementById(idPadre).checked = "checked";
			  }
			  else
			  {
			  		document.getElementById(idPadre).checked = "";
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
				      document.getElementById('f1').submit();
				  }
			</script>
			
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Recepci?n de Mercader?as&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;ver ?rdenes de compra</h3>   
                        <h6>Elija el proveedor y se cargar?n las ?rdenes de compra abiertas. </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="col-sm-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
               </div>
                    <!-- Advanced Tables -->
               <div class="container">
				    <div class="row">
				        <div class="col">
		                    <div class="card">
		                        <div class="card-header">
		                        	<h5>
		                            	 ?rdenes de compra
		                             </h5>
		                        </div>
		                        <div class="card-body">
		                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>DarOrdenesDeCompra.do">
		                            	<div class="row">
		                            		<div class="col-sm-12 col-lg-12">
										        <div class="form-group">
										          
										          <label class="col-md-4 control-label" for="art">Proveedor:&nbsp</label>
										          <div class="col-md-8">
										            	<select name="provedor" style="width: 95%" class="form-control" required>
															<c:forEach var="prov" items="${proveedores}">
																<c:if test="${provedor==prov.id}">
																	<option value="${prov.id}" selected><c:out value="${prov.descripcion}"></c:out></option>
																</c:if>
																<c:if test="${provedor!=prov.id}">
																	<option value="${prov.id}"><c:out value="${prov.descripcion}"></c:out></option>
																</c:if>
															</c:forEach>
														</select>
										          </div>
										        </div>
										    </div>
		                            		 <div class="col-sm-6 col-lg-6">
												<div class="form-group">	
													<label class="col-md-4 control-label" for="art"></label>										
													<div class="col-md-4">
														<div style="width: 1px; height: 1px; visibility: hidden;"><input type="text" name="recTipo" value="${recTipo}"> </div>
												  		<input type="submit" name="Submit"  class="btn btn-info" value="Buscar" />
												  	</div>											
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					<div class="col">
						<div class="card">
		                         <div class="card-header">
		                         	<h5>Filtrar desde Excel</h5>
		                        </div>
		                        <div class="card-body">
		                        	<form class="form-horizontal" role="form" enctype="MULTIPART/FORM-DATA" method="post" action="<%=basePath%>AltaFileRecepcion.do">
		                            	<div class="row">		   
		                            		<div class="col-sm-12 col-lg-12">
										        <div class="form-group">
										          
										          <label class="col-md-4 control-label" for="art">Proveedor:&nbsp</label>
										          <div class="col-md-8">
										            	<select name="provedor" style="width: 95%" class="form-control" required>
															<c:forEach var="prov" items="${proveedores}">
																<c:if test="${provedor==prov.id}">
																	<option value="${prov.id}" selected><c:out value="${prov.descripcion}"></c:out></option>
																</c:if>
																<c:if test="${provedor!=prov.id}">
																	<option value="${prov.id}"><c:out value="${prov.descripcion}"></c:out></option>
																</c:if>
															</c:forEach>
														</select>
										          </div>
										        </div>
										    </div>                         		
		                            		<div class="col-sm-6 col-lg-6">
		                            			<label class="col-md-4 control-label" for="archi"></label>
										        <div class="input-group mb-3">
		                                             <div class="custom-file">                                             
		                                                 <input type="file" class="custom-file-input" id="inputGroupFile02" name="archi">
		                                                 <label class="custom-file-label" for="inputGroupFile02">Subir archivo</label>
		                                             </div>
										        </div>
										    </div>
											<div class="col-sm-6 col-lg-6">									
												<div class="form-group">
													<label class="col-md-4 control-label" for="archi"></label>
													<div class="col-md-8">
												  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
												  	</div>											
												</div>
											</div>
											<input type="text" name="provedor" value = "${ordenes.get(0).proveedor.id}" style="display: none;">
										</div>
									</form>
		                    </div>
		                </div>
		             </div>
		           <div class="col-sm-12">
		             <div class="card">
						<div class="card-header">
							<h5>
                            	 Lista de ?rdenes de compra abiertas
                            </h5>	 
                        </div>
                        <div class="card-body">			
                        					
                            <div class="table-responsive">
                            	<form action="AgendarEntrega.do" id="f1" name="f1" method="post">
                            	<table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th>Orden</th>
								            <th>Articulo</th>
								            <th>Descripcion</th>
								            <th>Color</th>
								            <th>Incliur</th>
								            <th>Talles</th>
								            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="oc" items="${ordenes}">
								    		<c:forEach var="ar" items="${oc.articulos}">
								    			<c:forEach var="co" items="${ar.colores}">
								    				<c:if test="${co.mostrar}">
										    	 	 <tr class="odd_gradeX" id="2">
											            <td class="read_only text-center">ORDEN ${oc.numeroDocumento}</td>
											            <td class="read_only text-center">Art. ${ar.idArticulo}</td>
											            <td class="read_only text-center">${ar.descripcionCorta}</td>
											            <td class="text-center">Color ${co.id} / ${co.descripcion}</td>
											            <td class="center text-center">
											            	<div class="checkbox checkbox-fill d-inline">
																<input type="checkbox" name="${oc.numeroDocumento}${ar.idArticulo}${co.id}" 
																id="checkbox-fill-${oc.numeroDocumento}${ar.idArticulo}${co.id}">
																<label for="checkbox-fill-${oc.numeroDocumento}${ar.idArticulo}${co.id}" 
																class="cr"></label>
															</div>											            	
											            </td>
											            <td class="text-center">
											            	<c:set var="ttl" value="0"></c:set>
											            	<table style="text-align: center;">
																<tr>
																	<c:forEach var="ta" items="${co.talles}">
												                    	<td>${ta.talle}</td>
												                    </c:forEach>
																</tr>
																<tr>
																	<c:forEach var="ta" items="${co.talles}">
																	<c:set var="ttl" value="${ttl+ta.aRecibir}"></c:set>
												                    	<td><input style="width: 70px;" type="number" value="${ta.aRecibir}" name="${oc.numeroDocumento}${ar.idArticulo}${co.id}${ta.talle}"  onfocus="this.select()" onkeypress="return handleEnter(this, event)" onClick="return handleClic(this, event)" value="1"></td>
												                    </c:forEach>
																</tr>
															</table> 
											            
											            </td>
											            <td class="text-center"><c:out value="${ttl}"></c:out></td>
										            
										        	</tr>
										           </c:if>
										    	</c:forEach>
											</c:forEach>
								        </c:forEach>
                                    </tbody>
                                    <tfoot>
								      	<tr>								            
								            <th colspan="4"><a href="javascript:seleccionar_todo()">Incluir todos</a> | <a href="javascript:deseleccionar_todo()">Incluir ninguno</a></th>
								            <th></th>
								            <th style="text-align: center;"><input type="submit" class="btn btn-info" onclick="javascript:enviar('Seleccionar fechas!')" value="Seleccionar fechas!"/></th>								            
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
                <!-- /. ROW  -->
                </div>
                
                <script>
				// Add the following code if you want the name of the file appear on select
				$(".custom-file-input").on("change", function() {
				  var fileName = $(this).val().split("\\").pop();
				  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
				});
				</script>
                
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
