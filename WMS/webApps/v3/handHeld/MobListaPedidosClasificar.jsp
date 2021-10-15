<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
       
        
        <jsp:include page="/v3/util/index_headerSM.jsp"></jsp:include>
		
		  
		  
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
		  
		  
		 
		  
		  
        <!-- /. NAV SIDE  -->
        
		    	
                
               <div class="bg-white">
                <div class="row" >
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h5><strong>Mensaje:</strong></h5>
                    <div class="alert alert-info">
                    	<strong style="color: red"> ${menError}"</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    	
                    <div class="card">
                    <div class="card-header">
                    		<input class="btn btn-info" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.C'"  value="Volver al menú"/>
                        </div>
                        <div class="card-body">
                        	<form id="formularioDocs" method="post"action="<%=basePath%>/selMovStockRecep.do">
                        		<input class="d-none" type="text" name="listaDocs" value="1">
                            	<table class="table table-compact table-bordered table-hover responsive"  width="100%" id="encuentra-mob" border=1 frame=void rules=rows cellpadding="1" >
	                                    <thead>
	                                        <tr>
	                                           <th >Pedidos</th>
												<th >Fecha.</th>
												<th >Cliente</th>
												<th >&nbsp;</th>
											</tr>
									    </thead>
	                                    <tbody>
	                                    <c:forEach var="p" items="${pedidosPickup}">
											<tr>  
												<td ><div align="center">${p.idPedido}</div></td>
												<td ><div align="center">${p.cliente}</div></td>
												<td ><div align="center">${p.fecha}</div></td>
												<td ><div align="center"><a href="<%=basePath%>DarPedidoDetalleClasificaXID.do?idPedido=${p.idPedido}&preparar=0"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
												
											</tr>
										</c:forEach>
										</tbody>
										
									</table>
	                          	</form>
	                          
                    </div>
                 </div>
            	</div>
               </div>
            </div>
            <script type="text/javascript">
            	var form = document.getElementById("formularioDocs");
				document.getElementById("envio").addEventListener("click", function () {
				  form.submit();
				});
            </script>
            <jsp:include page="/v3/util/footer_mob.jsp"></jsp:include>
