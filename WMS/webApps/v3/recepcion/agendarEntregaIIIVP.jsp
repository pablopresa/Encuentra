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
                    	<h3>Recepción de Mercaderías&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Vista Previa de Agenda</h3>   
                      	<h6>Mercaderia a Recepcionar</h6>
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
                        	<h5>
                             	Detalle de la recepción
                             </h5>	
                        </div>
                        <div class="card-body">
							
                            
                            	<form action="<%=basePath%>AgendarEntregaIII.do" id="f1" name="f1" method="post">
                            	<table class="no-sort table table-striped table-bordered table-hover ">
                                    <thead>
                                        <tr>
                                           <th>O de C</th>
											<th>Articulo</th>
											<th>Color</th> 
											<th>Foto</th>
											<th>Talles</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="oc" items="${recepcion.ordenes}">
											<c:forEach var="art" items="${oc.articulos}">
												<c:forEach var="col" items="${art.colores}">
												<tr class="gradeD">
													<td><strong><c:out value="${oc.numeroDocumento}"></c:out></strong></td>
													<td><strong><c:out value="${art.idArticulo}"></c:out></strong></td>
													<td><c:out value="${col.descripcion}"></c:out></td>
													<td><img src="/../../../../../Imagenes/${art.idArticulo}${col.id}CH.jpg" style="width: 2cm;"/></td>
													<td>
														<div class="table-responsive">
														<table class="table table-striped table-bordered table-hover">
															<tr style="border: 1px solid;">
																<c:forEach var="h" items="${col.talles}">
																		<td style="border: 1px solid;"><c:out value="${h.talle}"></c:out></td>
																</c:forEach>
																<td style="border: 1px solid;">TOTAL</td>
															</tr>
															<tr style="border: 1px solid;">
																<c:forEach var="r" items="${col.talles}">
																		<td style="border: 1px solid;" align="right"><strong> <c:out value="${r.solicitada}"></c:out></strong></td>
																		<c:set var="totalS" value="${totalS+r.solicitada}"/>
																</c:forEach>
																<td style="border: 1px solid;"><c:out value="${totalS}"></c:out></td>
															</tr>
														</table>
														</div>
													</td>
												</tr>
											
												</c:forEach>
											</c:forEach>			
										</c:forEach>
                                    </tbody>
                                </table>
                                
                                <div class="row" >
                                 	<div class="col-md-8"></div>
                                 	<div class="col-md-4">
	                                 	<a href="<%=basePath%>/DarOrdenesDeCompra.do?recTipo=IMP" class="btn btn-info">Editar</a>
									 	<input type="submit" value="Agendar" class="btn btn-success"/>
									</div> 
                                </div>
                                	
                               	</form>
                            </div>
                        
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
                
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
