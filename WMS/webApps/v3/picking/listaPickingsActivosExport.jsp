<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

	<c:set var = "sinMenu" scope = "request" value = "yes"/>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        <!-- /. NAV SIDE  -->
	<!--  <style>
        @media print{@page {size: landscape}}
	</style>
	-->		
                <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="display table-striped table-bordered" style="width:100%; color: black;">
                                    <thead>
                                        <tr>
                                            <th class="text-center">Pedido</th>
											<th class="text-center">Imagen</th>
											<th class="text-center">Cantidad</th>
											<th class="text-center">Barra</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="p" items="${listaPicking}">											
											<tr>
												<td class="text-center" width="128"><c:out value="${p.idPedido}"></c:out><br><c:out value="${p.justificacion}"></c:out><br><c:out value="${p.idArticulo}"></c:out></td>
												<td class="text-center" width="128" height="128"><img src="<c:out value="${p.url}"></c:out>" class="img-rounded" width="96" height="96"></img></td>
												<td class="text-center" width="128" height="128"><c:out value="${p.cantidad}"></c:out></td>
												<td class="text-center" style="font-family: 'Libre Barcode 39 Text';font-size: 42px;">*<c:out value="${p.barra}"></c:out>*</td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
            
            <script type="text/javascript">
           		window.onload = function() {
           			window.print();
            	};
			</script>
	
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
