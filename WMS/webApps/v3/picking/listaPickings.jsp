<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

	<c:set var = "colapsar" scope = "request" value = "yes"/>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        <!-- /. NAV SIDE  -->
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Confirmar/verificar cantidades</h3>   
                        <h6>Seleccione lo que desea controlar</h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red">${menError}</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-header">
                        	<h5>
                        		Pickings finalizados sin remitir
                        	</h5>                             
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                           <th class="text-center">Picking</th>
											<th class="text-center">Destino/s</th>
											<th class="text-center">Solicitada</th>
											<th class="text-center">Encontrada</th>
											<th class="text-center">Verificada</th>
											<th class="text-center">Seleccionar</th>
											<th class="text-center">Limpiar</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="p" items="${pickingsS}">
                                    		<c:if test="${p.solicitada!=p.encontrada}">
												<c:set var="coloPI" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${p.solicitada==p.encontrada}">
												<c:set var="coloPI" scope="page" value="#adebad"></c:set>
											</c:if>
											<c:if test="${p.verificada!=p.encontrada}">
												<c:set var="coloVE" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${p.verificada==p.encontrada}">
												<c:set var="coloVE" scope="page" value="#adebad"></c:set>
											</c:if>
											
											<tr>
												<td class="text-center"><c:out value="${p.id}"></c:out><br><c:out value="${p.usuarios}"></c:out></td>
												<td class="text-center">
														<c:forEach var="d" items="${p.destinos}">
															<c:out value="${d.descripcion}"></c:out><br/>
														</c:forEach>
												</td>
												<td class="text-center"><c:out value="${p.solicitada}"></c:out></td>
												<td style="background-color: ${coloPI}" class="text-center"><c:out value="${p.encontrada}"></c:out></td>
												<td style="background-color: ${coloVE}" class="text-center"><c:out value="${p.verificada}"></c:out></td>
												<td class="text-center">
													<a href="<%=basePath%>/DarPickingVC.do?idPick=${p.id}&accion=${accio}" class="btn btn-info">GRABAR</a>
												</td>
												<td class="text-center">
													<button onclick="Finish(${p.id})" class="btn btn-danger">LIMPIAR</button>
												</td>
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
		
			function Finish(pick){
				if(confirm('Seguro que quiere limpiar este picking?')){
					location.replace("<%=basePath%>FinDescartarPick.do?idPick="+pick+"&pag=verifica");
				}
			}
		</script>
	
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
