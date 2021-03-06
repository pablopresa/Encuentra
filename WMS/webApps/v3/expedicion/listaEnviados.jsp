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
                    	<h3>Expedici?n&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Lista de Env?os Finalizados</h3>   
                        <h6>Puede consultar los documentos </h6>
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
                        	<h5>
                        		Envios Finalizados
                        	</h5>
                             
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" method="post" action="<%=basePath%>/enviadosFecha.do">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          
								          <label class="col-md-4 control-label" for="art" class="col-md-4 control-label">Deposito:&nbsp</label>
								          <div class="col-md-8">
								            	<select name="depo" id="depo" class="form-control">
								            		<c:forEach var="s" items="${depositosSel}">
								  						<c:if test="${s.idDeposito==depositoSelected}">
								  							<option value="${s.idDeposito}" selected="selected">${s.descripcion}</option>
								  						</c:if>
								  						<c:if test="${s.idDeposito!=depositoSelected}">
								  							<option value="${s.idDeposito}">${s.descripcion}</option>
								  						</c:if>
								  						</c:forEach>
								  				</select>
								          </div>
								        </div>
								    </div>

									<div class="col-sm-8 col-lg-6">
										<div class="form-group">
											<label class="col-md-4 control-label" for="estanteria"
												class="col-md-4 control-label">Fechas:&nbsp</label>
											<div class="col-md-8">
												<input type="text" class="form-control rango" name="fini" >
											</div>
										</div>
									</div>
									<div class="col-sm-4 col-lg-2">
										<div class="form-group">
											<label class="col-md-4 control-label" for="estanteria"
												class="col-md-4 control-label"></label>
											<div class="col-md-8">
										  		<input type="submit" name="Submit"  class="btn btn-info" value="Filtrar" />
										  	</div>											
										</div>
									</div>									
								</div>
							</form>
                        </div>
                </div>
                        
                        <div class="card">
                        	<div class="card-header">
                        		<h5>
                        			Resultado de filtro
                        		</h5>                             
                        	</div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th style="text-align: center;">Envio</th>
											<th style="text-align: center;">Fecha</th>
											<th style="text-align: center;">Transporte</th>
											<th style="text-align: center;">Chofer</th>
											<th style="text-align: center;">Acompa?ante</th>
											<th style="text-align: center;">Deps</th>
											<th style="text-align: center;">Docs</th>
											<th style="text-align: center;">Manifiesto</th>
											<th style="text-align: center;"></th>
										</tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="en" items="${envios}">
											<tr class="gradeD">
												<td style="text-align: center;" class="center"><c:out value="${en.idEnvio}"></c:out></td>
												<td style="text-align: center;" class="center"><c:out value="${en.fechaVis}"></c:out></td>
												<td style="text-align: center;" class="center"><c:out value="${en.transporte.descripcion}"></c:out></td>
												<td style="text-align: center;" class="center"><c:out value="${en.chofer.descripcion}"></c:out></td>
												<td style="text-align: center;" class="center"><c:out value="${en.acompaniante.descripcion}"></c:out></td>
												<td style="text-align: center;" class="center">
													<c:forEach var="de" items="${en.depositos}">
														&nbsp;-&nbsp;<a href="<%=basePath%>/printEnvioDeposito.do?idDepo=${de.idDeposito}&idEnvio=${en.idEnvio}" target="_BLANK">${de.idDeposito}</a>
													</c:forEach>
												</td>
												<td style="text-align: center;" class="center"><a href="<%=basePath%>/AccionEnvio.do?idEnvio=${en.idEnvio}&accion=1" target="_BLANK">Ver</a></td>
												<td style="text-align: center;" class="center"><a href="<%=basePath%>/AccionEnvio.do?idEnvio=${en.idEnvio}&accion=5" target="_BLANK">Ver</a></td>
												<td style="text-align: center;"><a href="<%=basePath%>/mailEnvio.do?idEnvio=${en.idEnvio}"> Comunicar a depositos</a><br/></td>
											</tr>
										</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                          </div>
                        </div>
                    
            </div>
                <!-- /. ROW  -->
                           
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
