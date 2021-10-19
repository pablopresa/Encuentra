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
					  
					  document.getElementById('subm').value=texto;
				      document.getElementById('f1').submit();
				  }
			</script>
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Tareas&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Nueva Tarea&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Seleccionar Articulos</h3>   
                        <h6>Marque los que desea agregar, puede filtrar por marca </h6>
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
                             Lista de articulos del documento seleccionado
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/FiltraRepo.do">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="marca" class="col-md-4 control-label">Selecione la marca:&nbsp</label>
								          <div class="col-md-8">
								            	<select name="marca" id="marca" class="form-control">
								  					<c:forEach var="m" items="${marcas}">
														<option value="${m}">${m}</option>	
													</c:forEach>
								  				</select>
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
										  	</div>											
										</div>
									</div>
                            	</div>
							</form>
						
                        	<div class="table-responsive">
                        		<form action="<%=basePath%>AltaTareaIII.do"  id="f1" name="f1">
								<div style="visibility: hidden; height: 1px; width: 1px;"><input type="text" name="idDoc" value="${idDoc}"> </div>
	                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                           <th>Recorrido</th>
												<th>Articulo</th>
												<th>Descripción</th>
												<th>Cod. Ubicación</th>
												<th>Cant Solicitada</th>
												<th>Dep Destino</th>
												<th>Dep Destino</th>
												<th>Stock</th>
												<th><a href="javascript:seleccionar_todo()">Incluir todos</a> | <a href="javascript:deseleccionar_todo()">Incluir ninguno</a> </th>
												
	                                        </tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="d" items="${repoArt}">
											
												<tr class="gradeD">
												
													<td><c:out value="${d.recorrido}"></c:out></td>
													<td><div align="center"><c:out value="${d.idArticulo}"></c:out></div></td>
													<td><div align="center"><c:out value="${d.descripcion}"></c:out></div></td>
													<td>
														<div align="center">
															E.<c:out value="${d.estnteria}"></c:out>&nbsp; M.<c:out value="${d.modulo}"></c:out>&nbsp;e.<c:out value="${d.estnte}"></c:out>
															(<c:out value="${d.cubi}"></c:out>)
														</div>
													</td>
													<td><div align="center"><c:out value="${d.solicitada}"></c:out></div></td>
													<td><div align="center"><c:out value="${d.idDepDestino}"></c:out></div></td>
													<td><div align="center"><c:out value="${d.descDeposito}"></c:out></div></td>
													<td><div align="center"><c:out value="${d.sotck}"></c:out></div></td>
													<td><div align="center"><input type="checkbox" name="${d.idArticulo}" checked="checked"> </div></td>
												</tr>
											
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="8"></td>
												<td><input type="submit" name="Submit"  class="btn btn-info"  value="Asignar y Guardar" /></td>
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
            </div>
                <!-- /. ROW  -->
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
  




            

               
        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
