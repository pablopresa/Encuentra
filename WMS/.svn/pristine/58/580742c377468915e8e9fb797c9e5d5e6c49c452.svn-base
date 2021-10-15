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
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Nueva Tarea&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Clasificacion</h3>   
                        <h6>Cargar un excel siguiendo el formato especificado</h6>
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
		                             Ingresar Excel
		                        </div>
		                        <div class="card-body">
		                        	<form class="form-horizontal" role="form" enctype="MULTIPART/FORM-DATA" method="post" action="<%=basePath%>ingresarExcelSort.do">
		                            	<div class="row">
		                            		<div class="col-sm-6 col-lg-4">
										        <div class="form-group">								          
										          <label class="col-md-4 control-label" for="archi" class="col-md-4 control-label">Archivo:</label>
										          <div class="col-md-8">
										            	<input type="file" name="archi" id="archi"/>
										          </div>
										        </div>
										    </div>
											<div class="col-sm-6 col-lg-4">									
												<div class="form-group">
													<label class="col-md-4 control-label" for="archi" class="col-md-4 control-label"></label>
													<div class="col-md-8">
												  		<input type="submit" name="Submit"  class="btn btn-info" value="VistaPrevia" />
												  	</div>											
												</div>
											</div>
										</div>
									</form>
									<hr>
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
