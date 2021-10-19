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
                    	<h3>Recepción de Mercaderías&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Desde Archivo</h3>   
                        <h6>Elija el excel con las cantidades a recepcionar. Formato de 4 columnas (Artículo, cant. X Packing, cant. bultos, Total)</h6>
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
				    <div class="row">
				        <div class="col">
							<div class="card">
		                         <div class="card-header">
		                         	<h5>Filtrar desde Excel</h5>
		                        </div>
		                        <div class="card-body">
		                        	<form class="form-horizontal" role="form" enctype="MULTIPART/FORM-DATA" method="post" action="<%=basePath%>AltaFileRecepcionSinOrdenes.do">
		                            	<div class="row">		   
		                            		<div class="col-sm-12 col-lg-12">
									          	<div class="col-sm-6 col-lg-4">
												    <div class="form-group d-none">
												      <label class="col-md-4 control-label" for="art">Proveedor:&nbsp</label>
												        <div class="col-md-10">
												          <div class="input-group input-group-sm">
												            <input type="text" class="form-control bg-white" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="provedor" value="Importacion">
														  </div>
														</div>
												    </div>
												 </div>
										    </div>                         		
		                            		<div class="col-sm-4 col-lg-4">
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
											<input type="text" name="provedor" value = "1" style="display: none;">
										</div>
									</form>
		                    </div>
		                </div>
		             </div>
		           <div class="col-sm-12">
		             <div class="card">
						<div class="card-header">
							<h5>
                            	 Artículos a recepcionar
                            </h5>	 
                        </div>
                        <div class="card-body">			
                        					
                            <div class="table-responsive">
                            	<form action="<%=basePath%>AgendarEntregaSinOrden.do" id="f1" name="f1" method="post">
	                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
	                                    <thead>
	                                        <tr>
									            <th class="text-center">Articulo</th>
									            <th class="text-center">Packing</th>
									            <th class="text-center">Total</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
							    			<c:forEach var="ar" items="${ordenesAux}">
									    	 	 <tr class="odd_gradeX" id="2">
										            <td class="text-center">Art. ${ar.idArticulo}</td>
										            <td class="text-center"><c:out value="${ar.cantidadPacking}x${ar.cantidadBultos}"></c:out></td>
										            <c:set var="ttl" value="${ar.cantidadPacking*ar.cantidadBultos}"></c:set>
										            <c:if test="${ttl==ar.cantidadTotal}">
											            <td class="text-center">
											            	<c:out value="${ar.cantidadTotal}"></c:out>
											            </td>
									            	</c:if>
									            	<c:if test="${ttl!=ar.cantidadTotal}">
											            <td class="table-warning text-center">
											            	<c:out value="${ar.cantidadTotal}"></c:out>
											            </td>
									            	</c:if>
									        	</tr>
									    	</c:forEach>
	                                    </tbody>
	                                    <tfoot>
									      	<tr>								            
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
