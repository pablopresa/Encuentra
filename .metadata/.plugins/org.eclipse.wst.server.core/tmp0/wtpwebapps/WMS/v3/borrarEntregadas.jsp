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
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Borrar Lineas Entregadas</h3>   
                        <h6></h6>
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
                             Digite el numero de picking
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/borrarLineasPick.do">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          
								          <label class="col-md-4 control-label" for="idOjo" class="col-md-4 control-label">Picking:&nbsp</label>
								          <div class="col-md-8">
								            	<input  type="number" class="form-control" name="idPick" id="idPick" value=""/>
								          </div>
								        </div>
								    	<div class="form-group">
											<label class="col-md-4 control-label" for="archi" class="col-md-4 control-label"></label>
											<div class="col-md-8">
												<input type="submit" name="Submit"  class="btn btn-info" value="Eliminar" />
											</div>											
										</div>
								    </div>
                            		
								</div>
							</form>
						</div>
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
