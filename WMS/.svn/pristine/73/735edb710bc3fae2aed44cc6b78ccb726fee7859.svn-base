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
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Reporte de Consolidacion</h3>   
                        <h6>Articulos en diferentes ubicaciones</h6>
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
                        	<h5>Listado de Articulos</h5>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="<%=basePath%>/consolidar.do">
                            	<input type="text" style="visibility: hidden;" name = "save" value ="true">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th style="text-align: center; width: auto;">Articulo</th>
                                            <th style="text-align: center; width: auto;">Distintas ubicaciones</th>
											<th style="text-align: center; width: auto;">Ojos</th>
											<th style="text-align: center; width: auto;">Cantidad</th>
											<th style="text-align: center; width: auto;"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="c" items="${consolidar}">
											<tr class="gradeD">
												
												<td style="text-align: center; width: auto;">${c.articulo}</td>
												<td style="text-align: center; width: auto;">${c.ojosCantidad}</td>
												<td style="text-align: center; width: auto;">${c.ojos}</td>
												<td style="text-align: center; width: auto;">${c.total}</td>
												<td style="text-align: center; width: auto;"> <a href="#" onclick="mostrar('${c.articulo}')">ver</a> </td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                                
                                </form>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
        
        <div id="mymodal" class="modal fade" role="dialog">
		
		</div>
	
        <c:forEach var="c" items="${consolidar}">
	        <div id="dive${c.articulo}" style="display: none;">
			<div class='modal-dialog'>
		    <!-- Modal content-->
				<div class='modal-content'>
					<div class='modal-header'>
						<button type='button' class='close' data-dismiss='modal'>&times;</button>
		    			<h4 class='modal-title'>Articulo &nbsp;${c.articulo}</h4>
		    		</div>
		    		<form action="<%=basePath%>/consolidar.do">
					<div class='modal-body'>
					<table class="table table-striped table-bordered table-hover">
						<tr>
							<th>Ubicacion</th>
							<th>Cantidad</th>
							<th></th>
						</tr>
					<c:forEach var='a' items='${c.listaArticulos}'>
						<tr>
							<td>${a.ubicacion}</td>
							<td>${a.cantidad}</td>
							<td><input type="checkbox" name="${a.sku}-${a.ubicacion}"></td>
						</tr>
					</c:forEach>
					</table>
					</div>
	      			<div class='modal-footer'>
	        			<button type='button' class='btn btn-info' data-dismiss='modal'>Cerrar</button>
	        			<input type="submit" class="btn btn-info" value="Tarea de Consolidacion">
	        			<input type="text" name="save" value="true" style="display: none;">
	      			</div>
	      			</form>
	    		</div>
			</div>
		</div>
	</c:forEach>
        
        <script type="text/javascript">
			function mostrar(idmodal){
				  var firstDivContent = document.getElementById('mymodal');
				  firstDivContent.innerHTML="";
				  var secondDivContent = document.getElementById('dive'+idmodal);
				    
				  firstDivContent.innerHTML = secondDivContent.innerHTML
				
				
				$("#mymodal").modal();
			}
		</script>
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
