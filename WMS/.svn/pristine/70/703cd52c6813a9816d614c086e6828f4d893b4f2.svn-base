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
                    	<h3>Almacen&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;informe de inventario</h3>   
                        <h6>Puede buscar dentro del listado tambien </h6>
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
                             suma de cantidades de Articulos en ubicaciones
                        </div>
                        <div class="card-body">
                        	<hr>
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                          	<th>idArticulo</th>
                                           	<th>Descripción</th>
											<th>Cantidad</th>
										</tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="a" items="${listaInvent}">
											<tr class="gradeD">
												<td><div align="center"><a href="<%=basePath%>DarOjosArti.do?art=${a.descripcion}"> <c:out value="${a.descripcion}"></c:out></a> </div></td>
												<td><c:out value="${a.descripcionB}"></c:out></td>
												<td><c:out value="${a.id}"></c:out></td>
											</tr>
										</c:forEach>
                                    </tbody>
                                    <tfoot>
                                    	<tr class="gradeD">
											<td colspan="2" style="text-align: right; font-size: 16pt"> TOTAL Inventario</td>
											 <td style="font-size: 16pt"><strong> ${ttlInvent}</strong></td>
										</tr>
                                    	<tr class="gradeD">
											<td>&nbsp;</td>
											 <td colspan="2"><a href="<%=basePath%>xlsx/${pathPlanilla}"><button class="btn btn-success">exportar reporte para Visual</button> </a></td>
										</tr>
                                    </tfoot>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
             <!-- /. PAGE INNER  -->
		</div>
		 <script type="text/javascript">
        function deleteLinea(ojo,art,cant)
			{
				 if(confirm("Seguro desea dar de baja esa linea?"))
				 {
					 var req = "<%=basePath%>/EcommerceBajarLineaUbicaciones.do?idArt="+art+"&idOjo="+ojo+"&cantidad="+cant;
					 window.location.assign(req);
				 }
				
				
				
			}
        </script>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
