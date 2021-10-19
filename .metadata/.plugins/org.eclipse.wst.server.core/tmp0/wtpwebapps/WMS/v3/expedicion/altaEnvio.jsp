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
                    	<h3>Expedición&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Alta Envío</h3>   
                        <h6>Complete todos los campos </h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                             <h5>Alta de envío Paso 1</h5>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" role="form" method="post" id="formExp"  action="<%=basePath%>/AltaEnvioI.do">
                            	<div class="row">
	                            	<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="trans" class="col-md-4 control-label">Transporte:&nbsp</label>
								          <div class="col-md-8">
								            	<select name="trans" id="trans" class="form-control">
											  		<c:forEach var="t" items="${transportes}">
											  			<option value="${t.id}">${t.descripcion}</option>
											  		</c:forEach>
											  	</select>
								          </div>
								        </div>
								    </div>
																	
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="chofer">Chofer:</label>
											<div class="col-md-8">
										  		
										  		<select class="form-control" name="chofer" id="chofer" >
											  		<c:forEach var="s" items="${choferes}">
												  		<c:if test="${s.id==99}">	
												  			<option value="${s.id}" selected="selected">${s.descripcion}</option>
														</c:if>
														<c:if test="${s.id!=99}">	
															<option value="${s.id}">${s.descripcion}</option>
														</c:if>
											  		</c:forEach>
											  	</select>
										  	</div>											
										</div>
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-6 control-label" for="acom">Acompañante:</label>
											<div class="col-md-8">
												<select name="acom" id="acom" class="form-control">
											  		<option value="-10">Encomienda</option>
											  		<c:forEach var="s" items="${acompaniantes}">
											  			<c:if test="${s.id==100}">	
												  			<option value="${s.id}" selected="selected">${s.descripcion}</option>
														</c:if>
														<c:if test="${s.id!=100}">	
															<option value="${s.id}">${s.descripcion}</option>
														</c:if>
											  		</c:forEach>
											  	</select>
											</div>
										 </div>
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											    <label class="col-md-4 control-label" for="fini">Fecha:&nbsp;</label>
											    <div class="col-md-8">
													<input type="text" class="form-control no-range" name="fini" >
												</div>
										    </div>
										
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="p2">&nbsp;</label>
											<div class="col-md-8">
										  		<input type="submit" class="btn btn-info" value="Seleccionar Deposito">
											</div>
											<input type="hidden" id="rutas" name="rutas" value=""> 
											<div class="col-md-8">
									
										  		<a href="javascript:esRutas()" class="btn btn-info">Seleccionar Ruta</a>
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
		
		<script type="text/javascript">
        
        	function esRutas(){
        		
        		document.getElementById('rutas').value = "Rutas";
    			
    			document.getElementById('formExp').submit();
        	}
			
		</script>
