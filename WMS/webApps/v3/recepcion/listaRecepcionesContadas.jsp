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
                    	<h3>Recepción&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Confirmar cantidades</h3>   
                        <h6>Seleccione la recepcion que desea controlar</h6>
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
                             	Recepciones y conteos
                             </h5>	
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<input id="json" type="text" value = "${recepcionesJson}" style="display: none;">
                                <table class=" display table nowrap table-striped table-hover dataTable" id="encuentra-default">
                                    <thead>
                                        <tr>
                                           <th class="text-center">Proveedor</th>
											<th class="text-center">Fecha (AAAA-MM-DD)</th>
											<th class="text-center">Esperadas</th>
											<th class="text-center">Contadas</th>
											<th class="text-center">Recibidas</th>
											<th class="text-center">Seleccionar</th>
											<c:if test="${!sf}">
						                		<th class="text-center">Recepcionar todo</th>
						                    </c:if>											
											<c:if test="${sf}">
						                		<th class="text-center"></th>
						                    </c:if>
											
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="d" items="${recepciones}">
										<tr class="gradeD">
											<td class="text-center"><c:out value="${d.proveedor.descripcion}"></c:out></td>
											<td class="text-center"><c:out value="${d.agenda}"></c:out></td>
											<td class="text-center"><c:out value="${d.cantidadEsperada}"></c:out></td>
											<td class="text-center"><c:out value="${d.cantidadContada}"></c:out></td>
											<td class="text-center"><c:out value="${d.cantidadRecepcionada}"></c:out></td>
											<td class="text-center">
												<c:if test="${uLogeado.idEmpresa==5}">
												<div><a href="<%=basePath%>PrintPackingRecepcion.do?idRecepcion=${d.id}&proveedorSel=${d.proveedor.id}" type="button" class="btn btn-icon btn-rounded btn-success"  title="Imprimir etiquetas packing"><i class="feather icon-printer"></i></a></div>
												<div><a href="<%=basePath%>PrintArticulosBarrasRecepcion.do?idRecepcion=${d.id}&proveedorSel=${d.proveedor.id}" type="button" class="btn btn-icon btn-rounded btn-warning" title="Imprimir etiquetas articulos"><i class="feather icon-printer"></i></a></div>
												</c:if>
												<div><a href="<%=basePath%>DetalleArticulosRecepcion.do?idRecepcion=${d.id}&proveedorSel=${d.proveedor.id}" type="button" class="btn btn-icon btn-rounded btn-info" title="Ver Articulos"><i class="feather icon-eye"></i></a></div>
											</td>
											<c:if test="${!sf}">
						                		<td class="text-center">
													<c:if test="${d.cantidadContada==0 && d.cantidadRecepcionada==0}">
													<a href="<%=basePath%>ConfirmarTodoRecepcion.do?idRecepcion=${d.id}"><input type="button"  class="btn btn-danger" value="Recepcionar"/></a>
												</c:if>
											</td>
						                    </c:if>											
											<c:if test="${sf}">
						                		<td class="text-center">
						                			<button class="btn btn-icon btn-outline-danger" onclick="BorrarLinea(${d.id})" >
													<span class="feather icon-slash"></span>
													</button>
						                			
						                		</td>
						                    </c:if>
											
										
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
        <script>
        	
        	function BorrarLinea(id)
        	{
        		if (confirm('Seguro que desea borrar esta linea?')){
        			
        			 window.location.assign('<%=basePath%>RecepcionesSFborrar.do?idRecepcion='+id);
        		}
        	}
        </script>
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
