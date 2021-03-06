<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        <jsp:include page="/v3/util/menu.jsp"></jsp:include>
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
			
			function seleccionarIgualEC(idPadre,idArticulo)
			{
			
			  if(document.getElementById(idPadre+'-'+idArticulo).checked)
			  {
			  		for (i=0;i<document.f1.elements.length;i++)
					   {
					      var name =document.f1.elements[i].name;
					      if(document.f1.elements[i].type == "checkbox" && name.includes(idPadre))
					         document.f1.elements[i].checked="1"
					   }
			    	
			  }
			  else
			  {
				  for (i=0;i<document.f1.elements.length;i++)
				   {
				   	  var name =document.f1.elements[i].name;
				      if(document.f1.elements[i].type == "checkbox" && name.includes(idPadre))
				         document.f1.elements[i].checked=0
				   }
			  		
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
					  
					  if(texto=='tarea')
					  {
						  document.getElementById("tipoSave").value = "1";//picking+tarea
					  }
					  else
					  {
						  document.getElementById("tipoSave").value = "0";//picking
					  }
						  
					  
				      document.getElementById('f1').submit();
				  }
			</script>
		  <link rel="stylesheet" href="<%=basePath%>v2/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
        <!-- /. NAV SIDE  -->
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Conteos&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Lista de articulos para controlar</h3>   
                        <h6>Puede filtrar y seleccionar los que quiera controlar </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> ${menError}"</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                    <div class="card-header">
                    	<h5> Filtros para aplicar en la lista</h5>                            
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form"  action="<%=basePath%>/DarArticulosConteoStore.do">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="art">Articulo:&nbsp;</label>
								          <div class="col-md-8">
								          <div class="input-group input-group-sm"> 
								            <input type="text" class="form-control bg-white" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="articulo" value="">
								           </div> 
										  </div>
								        </div>
								    </div>
									   
                            		<div class="col-sm-6 col-lg-4">
										<div class="form-group">
									    	<label class="col-md-4 control-label" for="Marca" class="col-md-4 control-label">Marcas:&nbsp;</label>
									        <div class="col-md-8">
									        <select data-placeholder="Puede Seleccionar Varios"  class="select2-multi bg-enc" multiple tabindex="6" name="marca">
												<option value=""></option>
											    <c:forEach var="d" items="${marcas}">
											     	<option value="${d.id}">${d.id} &nbsp; ${d.descripcion}</option>
											    </c:forEach>
											 </select>
											</div>
									    </div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
									    	<label class="col-md-4 control-label" for="genero" class="col-md-4 control-label">Generos:&nbsp;</label>
									        <div class="col-md-8">
									        <select data-placeholder="Puede Seleccionar Varios"  class="select2-multi bg-enc" multiple tabindex="6" name="genero">
												<option value=""></option>
											    <c:forEach var="d" items="${generos}">
											     	<option value="${d.id}">${d.id} &nbsp; ${d.descripcion}</option>
											    </c:forEach>
											 </select>
											</div>
									    </div>
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
									    	<label class="col-md-4 control-label" for="seccion" class="col-md-4 control-label">Secciones:&nbsp;</label>
									        <div class="col-md-8">
									        <select data-placeholder="Puede Seleccionar Varios"  class="select2-multi bg-enc" multiple tabindex="6" name="seccion">
												<option value=""></option>
											    <c:forEach var="d" items="${secciones}">
											     	<option value="${d.id}">${d.id} &nbsp; ${d.descripcion}</option>
											    </c:forEach>
											 </select>
											</div>
									    </div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
									    	<label class="col-md-4 control-label" for="categoria" class="col-md-4 control-label">Categorias:&nbsp;</label>
									        <div class="col-md-8">
									        <select data-placeholder="Puede Seleccionar Varios"  class="select2-multi bg-enc" multiple tabindex="6" name="categoria">
												<option value=""></option>
											    <c:forEach var="d" items="${categorias}">
											     	<option value="${d.id}">${d.id} &nbsp; ${d.descripcion}</option>
											    </c:forEach>
											 </select>
											</div>
									    </div>
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
									    	<label class="col-md-4 control-label" for="clase" class="col-md-4 control-label">Clases:&nbsp;</label>
									        <div class="col-md-8">
									        <select data-placeholder="Puede Seleccionar Varios"  class="select2-multi bg-enc" multiple tabindex="6" name="clase">
												<option value=""></option>
											    <c:forEach var="d" items="${clases}">
											     	<option value="${d.id}">${d.id} &nbsp; ${d.descripcion}</option>
											    </c:forEach>
											 </select>
											</div>
									    </div>
									</div>
							
									   
                            		<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<div style="width: 1px; height: 1px; visibility: hidden;"><input type="text" name="central" value="${central}"> </div>
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
										  	</div>											
										</div>
									</div>
								</div>
								  <script src="<%=basePath%>v2/assets/select/chosen.jquery.js" type="text/javascript"></script>
								<script type="text/javascript">
							    var config = {
							      '.chosen-select'           : {},
							      '.chosen-select-deselect'  : {allow_single_deselect:true},
							      '.chosen-select-no-single' : {disable_search_threshold:10},
							      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
							      '.chosen-select-width'     : {width:"95%"}
							    }
							    for (var selector in config) {
							      $(selector).chosen(config[selector]);
							    }
							  </script>
								
							</form>
						</div>
					</div>	
					<div class="card">	
                    <div class="card-header">
                    		<h5>
                             Lista de articulos a controlar, Marque los que desea
                             <br/>
                             Son :${totalSKU} articulos
                             <br/>
                             Son :${total} unidades
                             </h5>
                        </div>
                        <div class="card-body">
                        	<form action="<%=basePath%>AltaConteoTienda.do" method="post" id="f1" name="f1">
								<div class="table-responsive">								
	                            	<table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                          <th class="text-center"><a href="javascript:seleccionar_todo()">Todos</a><br/><a href="javascript:deseleccionar_todo()">Ninguno</a> </th>
												<th class="text-center">Articulo</th>
												<th class="text-center">Descripcion</th>
												<th class="text-center">Stock</th>
											</tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="a" items="${articuloC}">
												<tr class="gradeD">
													<td class="text-center">
														<div class="checkbox checkbox-fill d-inline">
															<input type="checkbox" name="${a.articulo}" id="checkbox-fill-${a.articulo}" checked="checked" >
															<label for="checkbox-fill-${a.articulo}" class="cr"></label>
														</div>
													</td>
													<td class="text-center">${a.articulo}</td>
													<td class="text-center">${a.descripcion}</td>
													<td class="text-center">${a.stock}</td>
												</tr>
											</c:forEach>
										</tbody>										
	                                </table>
	                                </div>
	                                <br>
	                                <div class="row">
	                                	<div class="text-right col-sm-12 col-lg-12"> 
	                                 		<div class="flex-container">	                           
	                                 			<input type="text" name="tipoSave" id="tipoSave" value="" style="display: none;">      	
												<a class="btn btn-info" href="javascript:enviar('tarea');" >Generar Conteo</a>											
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
        
		<script type="text/javascript">
		
		
			function subm()
			{
				document.getElementById("altaNuevo").submit();
			}
			
			function editCant(idSincro,idArticulo,origen,destino)
			{
				
				var valuer = document.getElementById(idSincro+'-'+idArticulo+'-'+origen+'-'+destino).value
				
				var req = '<%=basePath%>/EditValueLineaRepo.do?borrar=0&idSincro='+idSincro+'&articulo='+idArticulo+'&origenID='+origen+'&destinoID='+destino+'&cant='+valuer;
				SendAjaxPost(req)
				//window.location.assign(req);
			}
		
			
			function deleteLinea(idSincro,idArticulo,origen,destino)
			{
				 if(confirm("Seguro?"))
				 {
					 var req = '<%=basePath%>/EditValueLineaRepo.do?borrar=1&idSincro='+idSincro+'&articulo='+idArticulo+'&origenID='+origen+'&destinoID='+destino+'&cant='+0;
					 window.location.assign(req);
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
