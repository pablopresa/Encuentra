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
                    	<h3>Recepción&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Detalle de recepción</h3>   
                        <h6>Puede editar las cantidades  </h6>
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
                             Recepciones
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/PersisitirRecepcion.do">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          
								          <label class="col-md-4 control-label" for="serie" class="col-md-4 control-label">Serie:&nbsp</label>
								          <div class="col-md-8">
								            	<input required class="form-control" style="width: 47px; " name="serie" />
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								         <label class="col-md-4 control-label" for="remito" class="col-md-4 control-label">Remito:&nbsp</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="remito" />
								            	<input type="text" style="visibility: hidden; width: 1px;" name="idRecepcion" value="${idRecepcion}"/>
												<input type="text" style="visibility: hidden; width: 1px;" name="parcial" value="1"/>
								          </div>
								        </div>
								    </div>
                            		 <div class="col-sm-6 col-lg-4">
										<div class="form-group">
											
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Guardar" />
										  	</div>											
										</div>
									</div>
								</div>
							</form>
						</div>
						
						<hr/>
						
                        <div class="card-body">
							
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                           <th>Articulo</th>
											<th>Color</th>
											<th>Foto</th>
											<th>Talles</th>
											<th>Editar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="rec" items="${recepciones}">
											<c:forEach var="col" items="${rec.colores}">
											 <c:set var="myVar" value="${rec.articulo}${col.cod}" /> 
											<c:if test="${myVar!=idEdita}">										
													<tr class="gradeD">
													<td><strong><c:out value="${rec.articulo}"></c:out></strong></td>
													<td><strong><c:out value="${col.cod}"></c:out></strong></td>
													<td><img
														src="/../../../../../Imagenes/${rec.articulo}${col.cod}CH.jpg"
														style="width: 2cm;" /></td>
													<td align="left">
														<table style="border: 1px solid; ">
															<tr style="border: 1px solid;">
																<c:forEach var="tal" items="${col.talles}">
																	<td style="border: 1px solid; " align="center" colspan=2><c:out value="${tal}"></c:out></td>
																</c:forEach>
															</tr>
															<tr style="border: 1px solid;">
																<c:forEach var="tal" items="${col.talles}">
																	<td style="border: 1px solid; "><c:out
																			value="Rec"></c:out></td>
																	<td style="border: 1px solid; "><c:out
																			value="Fac"></c:out></td>
																</c:forEach>
															</tr>
															<tr style="border: 1px solid;">
																<c:forEach var="c" items="${col.cantidades}">
																	<td style="border: 1px solid;" align="center"><strong>
																			<c:out value="${c.recibidas}"></c:out>
																	</strong></td>
																	<td style="border: 1px solid;" align="center"><strong>
																			<c:out value="${c.afacturar}"></c:out>
																	</strong></td>
																</c:forEach>
	
															</tr>
														</table>
													</td>
													<td><div align="center"><a href="<%=basePath%>editaRecepcion.do?articuloBase=${rec.articulo}${col.cod}"><img alt="Editar" src="<%=basePath%>/imagenes/icons/package_go.png"></a> </div></td>
												</tr>
											</c:if>
											<c:if test="${myVar==idEdita}">
												<tr class="gradeD">
													<form action="<%=basePath%>modificarRecepcion.do?" method="post">
													<td><strong><c:out value="${rec.articulo}"></c:out></strong></td>
													<td><strong><c:out value="${col.cod}"></c:out></strong></td>
													<td><img
														src="/../../../../../Imagenes/${rec.articulo}${col.cod}CH.jpg"
														style="width: 2cm;" /></td>
													<td align="left">
														<table style="border: 1px solid; ">
															<tr style="border: 1px solid;">
																<c:forEach var="tal" items="${col.talles}">
																	<td style="border: 1px solid; " align="center" colspan=2><c:out
																			value="${tal}"></c:out></td>
																</c:forEach>
	
															</tr>
															<tr style="border: 1px solid;">
																<c:forEach var="tal" items="${col.talles}">
																	<td style="border: 1px solid; "><c:out
																			value="Rec"></c:out></td>
																	<td style="border: 1px solid; "><c:out
																			value="Fac"></c:out></td>
																</c:forEach>
	
															</tr>
															<tr style="border: 1px solid;">
																<c:forEach var="c" items="${col.cantidades}" varStatus="i">
																	<td style="border: 1px solid;" align="center"><strong>
																			<c:out value="${c.recibidas}"></c:out>
																	</strong></td>
																	<td><input type="text" name="${col.talles[i.index]}" style="width: 95%" value="${c.afacturar}"/>
																	<c:set var="myVar2" value="${myVar2}/${col.talles[i.index]}" />
																</c:forEach>
	
															</tr>
														</table>
													</td>
													
													<td><input type="submit" value="GUARDAR" style="width: 95%" > </td>													
													<input type="hidden" name="talles2" value="${myVar2}">
													</form>	
												</tr>
											</c:if>			
											</c:forEach>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
  




            

               
        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
