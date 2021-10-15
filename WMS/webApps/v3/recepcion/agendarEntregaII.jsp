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
                    	<h3>Recepción de Mercaderías&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Opciones para recepción</h3>   
                        <h6>Puede especificar medio de transporte y fecha de recepción </h6>
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
                             	Agenda de recepción, paso 2
                             </h5>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" role="form" method="post"  action="<%=basePath%>/AgendarEntregaII.do">
                            	<div class="row">
	                            	<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <c:if test="${uLogeado.idEmpresa==2}">
								          	<label class="col-md-4 control-label" for="trans" class="col-md-4 control-label">Folio:&nbsp</label>
								          </c:if>
								          <c:if test="${uLogeado.idEmpresa!=2}">
								          	<label class="col-md-4 control-label" for="trans" class="col-md-4 control-label">Transporte:&nbsp</label>
								          </c:if>
								          
								          <div class="col-md-8">
								            	<input class="form-control" id="trans" name="trans" placeholder="Sin definir" />
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">								          
								          	<label class="col-md-4 control-label" for="trans" class="col-md-4 control-label">Fecha:&nbsp</label>							          
								          <div class="col-md-8">
								            	<input type="text" class="form-control no-range" name="fini" >
								          </div>
								        </div>
								    </div>
																				
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="p2">&nbsp;</label>
											<div class="col-md-8">
										  		<button type="submit" class="btn btn-info">Vista Previa</button>
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
