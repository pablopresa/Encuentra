
		
		
		
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
        
        <link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
		  <meta name="viewport" content="width=device-width, initial-scale=1">       
    
        <link href="<%=basePath%>v3/bootstrap-treeview-master/dist/bootstrap-treeview.min.css" rel="stylesheet">
		  
        
		
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Expedicion&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Informes&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Matriz de depositos&nbsp;</h3>   
                        <h6>Seleccione fechas, y tipos de documentos </h6>
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
                             Filtros
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" action="<%=basePath%>/MatrizUnidadesDeposito.do" target="_blank">
                            	<div class="row">           
                            	
                            		<!-- scripts y css de picker -->
										<link href="<%=basePath%>v3/assets/css/font-awesome.css" rel="stylesheet" />
								        <link href="<%=basePath%>v3/assets/Picker/assets/daterangepicker.css" rel="stylesheet">
								        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
								        <!--[if lt IE 9]>
								            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
								            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
								        <![endif]-->
										
										<script type="text/javascript" src="<%=basePath%>v3/assets/Picker/assets/bootstrap.js"></script>
										<script src="<%=basePath%>v3/assets/Picker/assets/moment-with-locales.js"></script>
										<script src="<%=basePath%>v3/assets/Picker/assets/daterangepicker.js"></script>
									<!-- fin scripts y css de picker -->
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											    <label class="col-md-4 control-label" for="fini">Fecha:&nbsp;</label>
												<div class="col-md-8">
										           <input class="form-control" type="text" name="fini" id="fini">
										        </div>
										        <script type="text/javascript">
										            $(function () {
										                $('input[name="fini"]').daterangepicker
										                (
										                		{
										                			 locale: {
										                			      format: 'YYYY-MM-DD'
										                			}
										                		}
										                );
										            });
										            document.getElementById("fini").value = "${rango}"; 
										        </script>
										    </div>
										
									</div>
								    <div class="col-sm-6 col-lg-4" style="margin-bottom: 10px;">
								   <div class="form-group">
								        <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Razon de Documento:&nbsp;</label>
								       <div class="col-md-8">
								            <select data-placeholder="Puede Seleccionar Varios"  class="chosen-select form-control" multiple tabindex="6" name="razones" id="razones">
										        
										    	<c:forEach var="r" items="${razones}">
										        	<option value="${r.id}">${r.descripcion}</option>
										    	</c:forEach>
										  	</select>
								          </div>
								        </div>
								    </div>
								    
								    
								  <script src="<%=basePath%>v3/assets/select/chosen.jquery.js" type="text/javascript"></script>
								<script type="text/javascript">
							    var config = {
							      '.chosen-select'           : {},
							      '.chosen-select-deselect'  : {allow_single_deselect:true},
							      '.chosen-select-no-single' : {disable_search_threshold:10},
							      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
							      '.chosen-select-width'     : {width:"95%"}
							    }
							    for (var selector in config) {
							      $(selector).chosen(config[selector]);
							    }
							  </script>
													
													
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Buscar" />
										  	</div>											
										</div>
									</div>
									
									
									</div>	     
							</form>
						</div>
						
						<hr/>
						<div class="card-header">
					    	
						</div>
                        <div class="card-body">
							
                            <div class="table-responsive" >
                            	
                            </div>
                        </div>
                                     
                        <hr/>
						
                        </div>
                        
              


                       
                        
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
            
  
        
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
