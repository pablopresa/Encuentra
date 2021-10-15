<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Etiqueta de estanteria</h3>   
                        <h6>Código de ubicación </h6>
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
                             <h5>Formulario</h5>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" role="form" method="post" action="<%=basePath%>/DarEtToPrint.do" target="_BLANK">
                            	<div class="row">
	                            	<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <div class="col-md-8">
								            	<input required class="form-control" name="cod" placeholder="Código de Ubicacion:" id="idEsta" />
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
										<div class="form-group d-inline">
                   							 <div class="checkbox checkbox-fill d-inline">
								                 <input type="checkbox" name="idEtiqueta" id="checkbox-1" value="Etiqueta Grande" checked>
								                 <label for="checkbox-1" class="cr">Etiqueta Grande</label>
								             </div>
								         </div>
									</div>							
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">											
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Imprimir etiquetas" />
										  	</div>											
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
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
