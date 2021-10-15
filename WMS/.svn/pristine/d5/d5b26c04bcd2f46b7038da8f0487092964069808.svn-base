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
                    	<h3>Expedicion&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Preparar envios EC masivo</h3>   
                        <h6>Filtre por destino y rango de fechas</h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red"> ${menError}"</strong>            
                    </div>
                    </c:if>
                    <div class="card">
                        <div class="card-header">
                             Tareas
                        </div>
					<div class="card-body">
						<form class="form-horizontal" role="form" method="post"	action="<%=basePath%>/expedicionMasivaEC.do">
						
								<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Deposito:&nbsp</label>
								          <div class="col-md-8">
								            	<select name="depo" id="depo" class="form-control">
								            		<option value=""></option>
								  					<c:forEach var="s" items="${depositosSel}">
							  							<option value="${s.idDeposito}">${s.descripcion}</option>								  						
							  						</c:forEach>
								  				</select>
								          </div>
								        </div>
								    </div>
								    <script language="Javascript" type="text/javascript">
								    	if (document.cookie != "") {
								    		//if (confirm("Eliminar todas las Cookies?")) {
								    		la_cookie = document.cookie.split("; ")
								    		fecha_fin = new Date
								    		fecha_fin.setDate(fecha_fin.getDate() - 1)
								    		for (i = 0; i < la_cookie.length; i++) {
								    			mi_cookie = la_cookie[i].split("=")[0]
								    			document.cookie = mi_cookie + "=;expires=" + fecha_fin.toGMTString()
								    		}
								    	//document.write("Se han eliminado: " + la_cookie.length + " Cookies ")
								    	//}
								    	}
								    </script> 


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
										        	$(function() {
										        		$('input[name="fini"]').daterangepicker(
										        			{
										        				locale : {
										        					format : 'YYYY-MM-DD'
										        				}
										        			}
										        		);
										        	});
										        	document.getElementById("fini").value = "${rango}";
										        </script>
										    </div>
									</div>	    								   
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
										  	</div>											
										</div>
									</div>
										
									</div>
							</form>
							<hr>

					<form class="form-horizontal" role="form" method="post"
						action="<%=basePath%>/expedicionMasivaEC.do?exp=1">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="encuentra-default">
								<thead>
									<tr>
										<th>Pedido</th>
										<th>Nº Tracking</th>
										<th><a href="javascript:seleccionar_todo()">Todos</a><br />
										<a href="javascript:deseleccionar_todo()">Ninguno</a></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="t" items="${pedidosT}">
										<tr class="gradeD">
											<td>${t.id}</td>
											<td>${t.descripcion}</a></td>
											<td><input type="checkbox" name="${t.id}" value="true"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div>
							
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="form-group">
								<div class="col-md-8">
									
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="form-group">
								<div class="col-md-8">
									
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-lg-4">
							<div class="form-group">
								<div class="col-md-8">
									<input type="submit" name="Submit" class="btn btn-info"
										value="Generar Documento" />
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
        
        <script type="text/javascript"> 
			function seleccionar_todo(){
			   for (i=0;i<document.f1.elements.length;i++)
			   {
			      if(document.f1.elements[i].type == "checkbox")
			         document.f1.elements[i].checked=1;
			   }
			   for (i=0;i<document.f0.elements.length;i++)
			   {
			      if(document.f0.elements[i].type == "checkbox")
			         document.f0.elements[i].checked=1
			   }      
			}
			
			//La función seleccionar_todo() realiza un recorrido por todos los elementos del formulario. Para hacer un recorrido por todos los campos se utiliza el array "elements", que guarda una referencia con cada elemento que haya dentro del formulario.
			
			//En el recorrido comprueba si el elemento actual es de tipo "checkbox" (recordar que el array elements contiene todos los elementos, pero sólo deseamos operar con los que sean checkbox) y en ese caso, simplemente se actualiza el atributo "checked" al valor 1, con lo que el chekbox se marcará.
			
			function deseleccionar_todo(){
			   for (i=0;i<document.f1.elements.length;i++)
			   {
			      if(document.f1.elements[i].type == "checkbox")
			         document.f1.elements[i].checked=0
			   }
			   for (i=0;i<document.f0.elements.length;i++)
			   {
			      if(document.f0.elements[i].type == "checkbox")
			         document.f0.elements[i].checked=0
			   }
			}
			</script>
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
