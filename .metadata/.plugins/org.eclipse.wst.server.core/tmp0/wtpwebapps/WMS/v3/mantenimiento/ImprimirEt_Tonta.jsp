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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Etiquetas para bultos</h3>   
                        <h6> </h6>
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
                             	Formulario
                             </h5>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" role="form" method="post" action="<%=basePath%>/PrintDummy.do" target="_BLANK">
                            	<div class="row">
	                            	<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          
								          <label for="cant" class="col-md-6 control-label">Especifique Cantidad:&nbsp</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="cant" id="cant" type="number"/>
								          </div>
								        </div>
								    </div>
								   <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          
								          <label class="col-md-4 control-label" for="name" class="col-md-4 control-label">Descripción:&nbsp</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="name" id="name" type="text"/>
								          </div>
								        </div>
								    </div>					
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">		
										<label class="col-md-4 control-label" for="name" class="col-md-4 control-label">&nbsp</label>									
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Generar etiquetas" />
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
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
