<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var = "colapsar" scope = "request" value = "yes"/>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		
		<script type="text/javascript">
				if (document.cookie != "") {
            		//if (confirm("Eliminar todas las Cookies?")) {
                		la_cookie = document.cookie.split("; ")
                		fecha_fin = new Date();
                		fecha_fin.setDate(fecha_fin.getDate()-1)
						for (i=0; i<la_cookie.length; i++) {
                    		mi_cookie = la_cookie[i].split("=")[0]
                    		document.cookie = mi_cookie + "=;expires=" + fecha_fin.toGMTString()
                		}
                		//document.write("Se han eliminado: " + la_cookie.length + " Cookies ")
            		//}
        		}
    	</script> 
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
			
			
			
			
			
			function seleccionarIgual(idPadre,idArticulo)
			{
			
			  if(document.getElementById('checkbox-fill-'+idPadre+'-'+idArticulo).checked)
			  {
			  		for (i=0;i<document.f1.elements.length;i++)
					   {
					      var name =document.f1.elements[i].name;
					      if(document.f1.elements[i].type == "checkbox" && name.startsWith(idPadre))
					         document.f1.elements[i].checked="1"
					   }
			    	
			  }
			  else
			  {
				  for (i=0;i<document.f1.elements.length;i++)
				   {
				   	  var name =document.f1.elements[i].name;
				      if(document.f1.elements[i].type == "checkbox" && name.startsWith(idPadre))
				         document.f1.elements[i].checked=0
				   }
			  		
			  }
			}
			
			
			</script>
			<script>
				function fnResetAllFilters() 
				{
					
					  var table = $('#encuentra-default').dataTable();
		 
				       // Remove all filtering
				      //table.fnFilterClear();
				      table.fnFilter('');
				    
				  }
			</script>
			<script>
				function enviar(texto) 
				{
					  fnResetAllFilters();
				      
				      
				      
				      var seleccionado = false;
				      for (i=0;i<document.f1.elements.length;i++)
					   {
					      if(document.f1.elements[i].type == "checkbox" && document.f1.elements[i].checked == 1){
					    	  seleccionado = true;
					    	  break;
					      }
					      
					   }
				      if(!seleccionado){
				    	  alert("Seleccione al menos un art?culo");
				      }else {
				    	  
				    	  if(texto=='lista')
						  {
							  document.getElementById("tipoSave").value = "2";//Lista de picking +tarea al usuario 0
						  }
						  else if (texto=='lista_bultos')
						  {
							  document.getElementById("tipoSave").value = "3";//lista de bultos
						  }
						  else if(texto=='tarea')
						  {
							  
							  document.getElementById("tipoSave").value = "1";//picking+tarea
						  }
						  else
						  {
							  document.getElementById("tipoSave").value = "0";//picking
						  }
				    	  
				    	  document.getElementById('f1').submit();
				    	  
				      }
				      
				      
					    
				  }
			</script>
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .bg-enc{
		    	background-color: rgb(244,247,250)
		    };
		    .th-aut{
		    	max-width: 100px;
		    }
		  </style>
		  
        <!-- /. NAV SIDE  -->
        
        <c:set var = "chkGroup" scope = "page" value = "0"/>
        <c:set var = "btnExpPick" scope = "page" value = "0"/>
        <c:set var = "chkSorter" scope = "page" value = "0"/>
        <c:set var = "setPrioridad" scope = "page" value = "0"/>
        <c:forEach var="d" items="${uLogeado.seguridadUI}">
			<c:if test="${d=='chk_picking_group_articulos'}">
				<c:set var = "chkGroup" scope = "page" value = "1"/>
			</c:if>
			<c:if test="${d=='btn_picking_lista_wms'}">
				<c:set var = "btnExpPick" scope = "page" value = "1"/>
			</c:if>
			<c:if test="${d=='chk_sorter_pedidos'}">
				<c:set var = "chkSorter" scope = "page" value = "1"/>
			</c:if>
			<c:if test="${d=='filtro prioridad'}">
				<c:set var = "setPrioridad" scope = "page" value = "1"/>
			</c:if>
		</c:forEach>
		<!-- parametro seleccion lineas de picking -->
		<c:set var = "paramchk" scope = "page" value = ""/>        
		<c:if test="${param_seleccion_lineas == true}">
			<c:set var = "paramchk" scope = "page" value = "checked"/>
		</c:if>
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Reposicion&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Lista de articulos para reponer</h3>   
                        <h6>Puede filtrar y seleccionar los que quiera enviar </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red"> ${menError}"</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                    <div class="card-header">
                    		<h5>
                             	Filtros para aplicar en la lista
                             </h5>
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" autocomplete="off" action="<%=basePath%>/DarListaArtPendientesRepo.do">
                            	<div class="row">
                            	<c:if test="${tipoRe!=2}">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Deposito:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios" class="select2-multi bg-enc" multiple="multiple" name="deposito">
										        <option value=""></option>
										    	<c:forEach var="d" items="${depositos}">
										        	<option value="${d.id}">${d.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								       </div> 
								    </div>
                            	</c:if>                            		
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Marca:&nbsp;</label>
								          <div class="col-md-8">	
								            <select data-placeholder="Puede Seleccionar Varios"   class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="marca">
										        <option value=""></option>
										    	<c:forEach var="d" items="${marcas}">
										        	<option value="${d.id}">${d.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								    
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Genero:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios"   class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="genero">
										        <option value=""></option>
										    	<c:forEach var="d" items="${generos}">
										        	<option value="${d.id}">${d.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Categoria:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios"   class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="categoria">
										        <option value=""></option>
										    	<c:forEach var="d" items="${categorias}">
										        	<option value="${d.id}">${d.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								     <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Seccion:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios"   class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="seccion">
										        <option value=""></option>
										    	<c:forEach var="d" items="${secciones}">
										        	<option value="${d.id}">${d.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Clase:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios"   class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="clase">
										        <option value=""></option>
										    	<c:forEach var="d" items="${clases}">
										        	<option value="${d.id}">${d.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								   <c:if test="${tipoRe!=2}">
								     <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="distr" class="col-md-4 control-label">Distribucion:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios"   class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="distr">
										        <option value=""></option>
										    	<c:forEach var="d" items="${distribuciones}">
										        	<option value="${d.id}">${d.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								   </c:if>
								   <c:if test="${tipoRe==2}">
								     <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="canal" class="col-md-4 control-label">Canal:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios" class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="canales">
										        <option value=""></option>
										    	<c:forEach var="c" items="${canales}">
										        	<option value="${c.id}">${c.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								   </c:if>
								   <c:if test="${tipoRe==2}">
								     <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="courier" class="col-md-4 control-label">Courier:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios" class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="courier">
										        <option value=""></option>
										    	<c:forEach var="d" items="${couriers}">
										        	<option value="${d.id}">${d.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								   </c:if>							   
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Tope:&nbsp;</label>
								          <div class="col-md-8">
								            <select  class="select2-multi bg-enc"   name="tope">
								            	<option value=""></option>
										        <option value="25">25</option>
										    	<option value="50">50</option>
										    	<option value="100">100</option>
										  	</select>
								          </div>
								        </div>
								    </div>

								    
								    <c:if test="${tipoRe==3}">
									<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="courier" class="col-md-4 control-label">Zonas:&nbsp;</label>
								          <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios" class="select2-multi bg-enc" multiple="multiple" tabindex="6" name="zonas">
										        <option value=""></option>
										    	<c:forEach var="gr" items="${lstZonas}">
													<option value="${gr.id}"><c:out value="${gr.descripcion}"></c:out> </option>
												</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
									</c:if>
									
									
									
									<c:if test="${setPrioridad == 1}">
										<div class="col-sm-6 col-lg-4">
									        <div class="form-group">
									          <label class="col-md-4 control-label" for="spick" class="col-md-4 control-label">Prioridad:&nbsp;</label>
									          <!--  <div class="col-md-8">
									            <select  class="form-control"   name="spick">
									            	<option value=""></option>
									            	<option value="si">Solo Pickup</option>
											   </select>
									          </div>-->
									          <div class="switch d-inline m-r-10">
												<input type="checkbox" id="switch-2" name="sprioridad" value="1"> <label for="switch-2" class="cr"></label>
											</div>
									        </div>
									    </div>
									</c:if>
								
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="spick" class="col-md-4 control-label">Solo Pickup:&nbsp;</label>
								          <!--  <div class="col-md-8">
								            <select  class="form-control"   name="spick">
								            	<option value=""></option>
								            	<option value="si">Solo Pickup</option>
										   </select>
								          </div>-->
								          <div class="switch d-inline m-r-10">
											<input type="checkbox" id="switch-1" name="spick" value="1"> <label for="switch-1" class="cr"></label>
										</div>
								        </div>
								    </div>
								    				    
							     
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											    <label class="col-md-4 control-label" for="fini">Fecha:&nbsp;</label>
												<div class="col-md-8">
										           <input class="form-control rango bg-white" type="text" name="fini" id="fini"/>
										        </div>
												
										    </div>
										
									</div>
                            		
								</div>
								
							  
								<div class="col-sm-6 col-lg-4">
									<div style="width: 1px; height: 1px; visibility: hidden;">
										<input type="text" name="central" value="${central}">
									</div>
								</div>
								<div class="col-sm-12 col-lg-12">
									<div class="text-center"> 
										<button type="submit" name="Submit" class="btn btn-info" value="Filtrar"><i class="feather icon-search"></i>Filtrar</button>
									</div>
									
								</div>
								
								</form>
						</div>
                    <div class="card-header">
                    		<h5>
                            	 Lista de articulos a reponer, Marque los que desea reponer<br/>Total de Lineas: ${cantidadLineas}<br/>Total de Unidades: ${cantidadUnidades}
                             </h5>
                        </div>
                        <div class="card-body">
							
								<form action="<%=basePath%>AltaRepo.do" method="post" id="f1" name="f1">
								<div class="table-responsive">
	                            	<table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                        	<th>Pedido</th>
												<th>Fecha</th>
												<th><a href="javascript:seleccionar_todo()">Todos</a><br/><a href="javascript:deseleccionar_todo()">Ninguno</a> </th>
												<th>Articulo</th>
												<th>Ubicacion Origen</th>
												<th>Dep. Destino</th>
												<c:if test="${tipoRe!=2}">
													<th>Unidades</th>
												</c:if>												
												<th>U/O</th>
												<th><div align="center">Justificacion</div></th>												
												<th></th>
												
												
												
	                                        </tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="a" items="${articulosRepo}">
															
												<tr id="${a.sinc.id}-${a.pedido}-${a.idArticulo}-${a.destino.id}" class="gradeD" <c:if test = "${fn:contains(a.justificacion, '(NO ENCONTRADO)')}">style="color: #ed3b3b"</c:if>>
													<td>${a.pedido}</td>
													<td>${a.sinc.descripcion}</td>
														<c:if test="${a.ubicaciones=='Sin Asignar'}">
															<c:if test="${tipoRe!=2}">
																<td style="text-align: center;">
																	<div class="checkbox checkbox-fill d-inline">
																		<input type="checkbox" name="${a.sinc.id}-${a.idArticulo}-${a.destino.id}-${a.solicitud}" id="checkbox-fill-${a.sinc.id}-${a.idArticulo}-${a.destino.id}-${a.solicitud}" ${paramchk} >
																		<label for="checkbox-fill-${a.sinc.id}-${a.idArticulo}-${a.destino.id}-${a.solicitud}" class="cr"></label>
																	</div>
																</td>
																<!--<td><input type="checkbox" name="${a.sinc.id}-${a.idArticulo}-${a.destino.id}" checked="checked"></td>-->
															</c:if>
															<c:if test="${tipoRe==2}">
															<td style="text-align: center;">
																<div class="checkbox checkbox-fill d-inline">
																	<input type="checkbox" id="checkbox-fill-${a.pedido}-${a.idArticulo}" name="${a.pedido}-${a.idArticulo}" ${paramchk} onchange="seleccionarIgual('${a.pedido}','${a.idArticulo}')">
																	<label for="checkbox-fill-${a.pedido}-${a.idArticulo}" class="cr"></label>
																</div>
															</td>
																<!--<td><input type="checkbox" id="${a.pedido}-${a.idArticulo}" name="${a.pedido}-${a.idArticulo}" checked="checked" onchange="seleccionarIgual('${a.pedido}','${a.idArticulo}')"></td>  -->
															</c:if>															
														</c:if>
														<c:if test="${a.ubicaciones!='Sin Asignar'}">
															<c:if test="${tipoRe!=2}">
															<td style="text-align: center;">
																<div class="checkbox checkbox-fill d-inline">
																	<input type="checkbox" name="${a.sinc.id}-${a.idArticulo}-${a.destino.id}-${a.solicitud}" id="checkbox-fill-${a.sinc.id}-${a.idArticulo}-${a.destino.id}-${a.solicitud}" ${paramchk}>
																	<label for="checkbox-fill-${a.sinc.id}-${a.idArticulo}-${a.destino.id}-${a.solicitud}" class="cr"></label>
																</div>
															</td>		
																<!--<td><input type="checkbox" name="${a.sinc.id}-${a.idArticulo}-${a.destino.id}" checked="checked"></td>  -->
															</c:if>
															<c:if test="${tipoRe==2}">
															<td style="text-align: center;">
																<div class="checkbox checkbox-fill d-inline">
																	<input type="checkbox" id="checkbox-fill-${a.pedido}-${a.idArticulo}" name="${a.pedido}-${a.idArticulo}" ${paramchk} onchange="seleccionarIgual('${a.pedido}','${a.idArticulo}')">
																	<label for="checkbox-fill-${a.pedido}-${a.idArticulo}" class="cr"></label>
																</div>
															</td>		
																<!--<td><input type="checkbox" id="${a.pedido}-${a.idArticulo}" name="${a.pedido}-${a.idArticulo}" checked="checked" onchange="seleccionarIgual('${a.pedido}','${a.idArticulo}')"></td>  -->
															</c:if>
															
														</c:if>
													
													<td style="text-align: justify; max-width: 300px !important; white-space: initial;">${a.idArticulo} <br>${a.artDesc}</td>
													<td>${a.ubicaciones}</td>
													<td>${a.destino.descripcion}</td>
													<c:if test="${tipoRe!=2}">
														<td style="text-align: center;"><input class="form-control" type="number" id="${a.sinc.id}-${a.idArticulo}-${a.origen.id}-${a.destino.id}" value="${a.cantidad}" onchange="editCant(${a.sinc.id},'${a.idArticulo}',${a.origen.id},${a.destino.id},${a.idPick})" style="width: 80px"></td>
													</c:if>													
													<td style="text-align: center;">${a.cantidad}</td>
													<td><div align="center">${a.justificacion}</div></td>
													
													<td><button class="btn btn-icon btn-outline-danger" onclick="javascript:deleteLinea(${a.sinc.id},'${a.idArticulo}',${a.origen.id},${a.destino.id},${a.pedido},${a.cantidad},${a.idPick});"><span class="feather icon-slash"></span></button></td>
													
													
													
													
												</tr>
											</c:forEach>
										</tbody>
										<!-- <tfoot>
											<tr style="text-align: center">
												<c:if test="${ecommerce!=1}">
													<td colspan="7"><input type="text" name="tipoSave" id="tipoSave" value="" style="visibility: hidden;"> </td>
													<td colspan="2"><a class="btn btn-info" href="javascript:enviar('tarea');" >Tarea Picking</a></td>
												</c:if>
												<c:if test="${ecommerce==1}">
													<td colspan="6"><input type="text" name="tipoSave" id="tipoSave" value="" style="visibility: hidden;"> </td>
													<td colspan="2"><a class="btn btn-info" href="javascript:enviar('tarea');" >Tarea Picking</a></td>
												</c:if>
												
											</tr>
										</tfoot> -->
	                                </table>
	                                </div>
	                                <br>
	                                <div class="row">
	                                	<div class="text-right col-sm-12 col-lg-12"> 
	                                 		<div class="flex-container">
	                                 	
	                                 			<c:if test="${chkGroup == 1}">
													<label class="col-md-4 control-label" for="spick" class="col-md-4 control-label">Agrupar por articulo:&nbsp;</label>
										          <div class="switch d-inline m-r-10">
													<input type="checkbox" id="switch-2" name="agra" value="1"> <label for="switch-2" class="cr"></label>
												</div>	
												</c:if>
												<c:if test="${chkSorter == 1}">
													<label class="col-md-4 control-label" style="width:auto" for="spick" class="col-md-4 control-label">Clasificar por pedidos:
													&nbsp;</label>
										          	<div class="switch d-inline m-r-10">
														<input type="checkbox" id="switch-3" name="chkSorter" value="1"> <label for="switch-3" class="cr"></label>
													</div>	
												</c:if>
													                                 	
		                                 		<input type="text" name="tipoSave" id="tipoSave" value="" style="display: none;">
												<a class="btn btn-info" href="javascript:enviar('tarea');" >Tarea Picking</a>
												
												<c:if test="${btnExpPick == 1}">
													<a class="btn btn-info" href="javascript:enviar('lista');" >Lista para WMS</a>
												</c:if>
												
												
		                                 		<c:if test="${uLogeado.idEmpresa == 4}">
													<a class="btn btn-info" href="javascript:enviar('tarea_pedido');" >Tarea por Pedido</a>
												</c:if>
												<c:if test="${uLogeado.idEmpresa == 8}">
													<a class="btn btn-info" href="javascript:enviar('tarea_pedido');" >Tarea por Pedido</a>
												</c:if>
												
		                                 		<c:if test="${uLogeado.idEmpresa == 5 or uLogeado.idEmpresa == 8}">
													<a class="btn btn-info" href="javascript:enviar('lista_bultos');" >Lista de bultos</a>
												</c:if>
												
											</div>
										</div> 
	                                </div>
	                               </form>
								
	                          </div>
                            
                        </div>
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                        
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
        
        
		<script type="text/javascript">
		
		
			function subm()
			{
				document.getElementById("altaNuevo").submit();
			}
			
			function editCant(idSincro,idArticulo,origen,destino,pick)
			{
				
				var valuer = document.getElementById(idSincro+'-'+idArticulo+'-'+origen+'-'+destino).value
				
				var req = '<%=basePath%>/EditValueLineaRepo.do?borrar=0&idSincro='+idSincro+'&articulo='+idArticulo+'&origenID='+origen+'&destinoID='+destino+'&cant='+valuer+'&pedido='+0+'&idpick='+pick;
				SendAjaxPost(req)
				//window.location.assign(req);
			}
		
			
			function deleteLinea(idSincro,idArticulo,origen,destino,pedido,cantidad,pick)
			{
				 if(confirm("Seguro?"))
				 {
				 	 var id=idSincro+'-'+pedido+'-'+idArticulo+'-'+destino;
				 	 var row = document.getElementById(id);
				 	 var cells = row.cells.length;
				 	 for(var i=0;i<cells;i++){
				 	 	row.deleteCell(0);
				 	 }
    				 
					 var req = '<%=basePath%>/EditValueLineaRepo.do?borrar=1&idSincro='+idSincro+'&articulo='+idArticulo+'&origenID='+origen+'&destinoID='+destino+'&cant='+cantidad+'&pedido='+pedido+'&idpick='+pick;
					 //window.location.assign(req);
					 SendAjaxPost(req);
				 }
				
				
				
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
			               alert('Error en la actualizaci?n, cierre y abra el programa');
			           }
			        }
			    };

			    
			    xmlhttp.open("POST", request, true);
			    xmlhttp.send();
			    req='';
			}
		</script>    
	   
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
