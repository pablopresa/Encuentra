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
        
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Asociar Factura mediante Excel</h3>   
                        <h6>Seleccione el excel de las ordenes a asociar factura. </h6>
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
		                         	<h5>Filtrar desde Excel</h5>
		                        </div>
		                        <div class="card-body">
		                        	<form class="form-horizontal" role="form" enctype="MULTIPART/FORM-DATA" method="post" action="<%=basePath%>AltaFileFacturados.do">
		                            	<div class="row">		   
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
