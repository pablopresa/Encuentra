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
                    	<h3>Tareas&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Nueva Tarea</h3>   
                        <h6>Puede generar tareas de diferentes tipos </h6>
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
                             Alta de tareas paso 1
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" role="form" method="post"  action="<%=basePath%>/AltaTareaI.do">
                            	<div class="row">
	                            	<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="descripcion" class="col-md-4 control-label">Descripción:&nbsp</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="descripcion" id="descripcion" value="${descripcion}" />
								          </div>
								        </div>
								    </div>
																	
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="tipo">Tipo:</label>
											<div class="col-md-8">
										  		<select name="tipo" id="tipo" class="form-control">
											  		<c:forEach var="t" items="${tiposTarea}">
											  			<option value="${t.id}">${t.descripcion}</option>
											  		</c:forEach>
											  	</select>
										  	</div>											
										</div>
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="sup">Supervisor:</label>
											<div class="col-md-8">
												<select name="sup" id="sup" class="form-control">
											  		<c:forEach var="s" items="${supervisores}">
											  			<option value="${s.id}">${s.descripcion}</option>
											  		</c:forEach>
											  	</select>
											</div>
										 </div>
									</div>
									<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="descripcion" class="col-md-4 control-label">Unidades:&nbsp</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="unit" id="unit" value="${unit}"/>
								          </div>
								        </div>
								    </div>
									
									
									<!-- scripts y css de picker -->
										<link href="<%=basePath%>v3/assets/css/font-awesome.css" rel="stylesheet" />
								        <link href="<%=basePath%>v3/assets/Picker/assets/bootstrap-datetimepicker.css" rel="stylesheet">
								        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
								        <!--[if lt IE 9]>
								            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
								            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
								        <![endif]-->
										
										<script type="text/javascript" src="<%=basePath%>v3/assets/Picker/assets/bootstrap.js"></script>
										<script src="<%=basePath%>v3/assets/Picker/assets/moment-with-locales.js"></script>
										<script src="<%=basePath%>v3/assets/Picker/assets/bootstrap-datetimepicker.js"></script>
									<!-- fin scripts y css de picker -->
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											    <label class="col-md-4 control-label" for="fini">Fecha:&nbsp;</label>
												<div class="col-md-8">
										            <div class="form-group">
										                <div class="input-group date" id="datetimepicker2">
										                    <input class="form-control" type="text" name="fini">
										                    <span class="input-group-addon">
										                        <span class="fa fa-calendar"></span>
										                    </span>
										                </div>
										            </div>
										        </div>
										        <script type="text/javascript">
										        var d = new Date();
										            $(function () {
										                $('#datetimepicker2').datetimepicker({
										                    locale: 'es',
										                    viewDate :d,
										                    defaultDate:d,
										                    allowInputToggle:true,
										                    icons: {
										                    time: "fa fa-clock-o",
										                    date: "fa fa-calendar",
										                    up: "fa fa-arrow-up",
										                    down: "fa fa-arrow-down"
										                    
										                }
										                });
										            });
										            
										           
										            
										            
										        </script>
										    </div>
										
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="p2">&nbsp;</label>
											<div class="col-md-8">
										  		<button type="submit" class="btn btn-info">Seleccionar Personal</button>
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
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
        
        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
