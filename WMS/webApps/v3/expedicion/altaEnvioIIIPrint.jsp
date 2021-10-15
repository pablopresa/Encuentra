<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String idDep = request.getParameter("idDepo");
		request.setAttribute("deep", idDep);


%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
			<jsp:include page="/v3/util/menu.jsp"></jsp:include>
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Expedición&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Alta Envío&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Detalles del Envío</h3> 
                        
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
                    <c:forEach var="depo" items="${envio.depositos}">
                    	<c:if test="${depo.idDeposito==deep}">
                    	<button class="btn btn-success" onclick="printReporte('printP');"><i class="fas fa-file-excel"></i> Imprimir Remito</button>
	                    <div class="card" id="printP">
	                        <div class="card-header">
	                        	<h5>
	                            	Detalle de unidades del remito de entrega ${envio.idEnvio} a destino ${depo.descripcion} del ${envio.fechaVis}
	                            </h5>	
	                        </div>
	                        <div class="card-body">
	                        <form class="form-horizontal" role="form">
                            	<div class="row">
                            		<div class="col-sm-6 col-lg-4">
	                            	    <div class="form-group">
									          <label class="col-md-4 control-label" for="idEsta" class="col-md-4 control-label">Transporte:&nbsp;</label>
									          <div class="col-md-8">
									            	<input required class="form-control" name="idEsta" id="idEsta" readonly="readonly" value="${envio.transporte.descripcion}" />
									          </div>
									    </div>
								    </div>
                            		
	                            	<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="idEsta" class="col-md-4 control-label">Transporte:&nbsp;</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="idEsta" id="idEsta" readonly="readonly" value="${envio.transporte.descripcion}" />
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="idEsta" class="col-md-4 control-label">Marca:&nbsp;</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="idEsta" id="idEsta" readonly="readonly" value="${envio.transporte.marca}" />
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="idEsta" class="col-md-4 control-label">Matricula:&nbsp;</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="idEsta" id="idEsta" readonly="readonly" value="${envio.transporte.matricula}" />
								          </div>
								        </div>
								    </div>
								    <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="idEsta" class="col-md-4 control-label">Chofer:&nbsp;</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="idEsta" id="idEsta" readonly="readonly" value="${envio.chofer.descripcion}" />
								          </div>
								        </div>
								    </div>
								     <div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          <label class="col-md-4 control-label" for="idEsta" class="col-md-6 control-label">Acompañante:&nbsp;</label>
								          <div class="col-md-8">
								            	<input required class="form-control" name="idEsta" id="idEsta" readonly="readonly" value="${envio.acompaniante.descripcion}" />
								          </div>
								        </div>
								    </div>
								     
								</div>
	                        </form>
	                        
	                        <div class="alert alert-info">
			                    	Detalle de la entrega            
			                    </div>
			                     <div class="table-responsive">
	                                <table class="no-sort table table-striped table-bordered table-hover ">
	                                    <thead>
	                                        <tr>
	                                           <th style="text-align: center;">Tipo</th>
												<th style="text-align: center;">Observacion</th>
												<th style="text-align: center;">Detalle</th>
												<th style="text-align: center;">Unidades</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    	<c:forEach var="docTi" items="${depo.documentos}">
												<c:forEach var="doc" items="${docTi.documentos}">
													<tr>
														<td style="text-align: center;">${doc.razon.descripcion} - DOC ${doc.numeroDoc}</td>
														<td style="text-align: center;">${doc.comentario}</td>
														<td style="text-align: center;"></td>
														<td style="text-align: center;"></td>
														<c:forEach var="it" items="${doc.listaDocs}">
															<tr>
																<td style="text-align: center;"></td>
																<td style="text-align: center;"></td>
																<td style="text-align: center;">${it.descripcion}</td>
																<td style="text-align: center;">${it.id}</td>
															</tr>
														</c:forEach>
														
													</tr>
												</c:forEach>
													<tr>
														<td>&nbsp;</td>
														<td style="text-align: center;"><strong>SUB-TOTAL:&nbsp;${docTi.cantidad}</strong></td>
														<td></td>
														<td></td>
													</tr>
													<tr>
														<td><div align="center">&nbsp;</div></td>
														<td><div align="center">&nbsp;</div></td>
														<td><div align="center">&nbsp;</div></td>
														<td><div align="center">&nbsp;</div></td>
													</tr>
											</c:forEach>
	                                    </tbody>
	                                    <tfoot>
									    	<tr>
									    		<td style="text-align: center;"><strong>Total entrega:</strong></td>
									        	<td style="text-align: center;"><strong>${depo.totalBultos} Bultos</strong></td>
									        	<td colspan="2" style="text-align: center;"><strong>${depo.totalU} Unidades</strong></td>
									        </tr>
									        <tr>
									    		<td colspan="4">&nbsp;</td>
									    		
									        </tr>
									        <tr>
									    		<td colspan="2" style="text-align: right;">Firma de Conformidad</td>
									    		<td colspan="2"></td>
									        </tr>
									        
									    </tfoot>
	                                </table>
	                            </div>
	                           
								
								
	                            
	                        </div>
	                    </div>
	                    </c:if>
	                  </c:forEach>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
          		<script type="text/javascript">
	                function printReporte(divName)
	                {
	                	var printContents = document.getElementById(divName).innerHTML;
	        			var originalContents = document.body.innerHTML;

	        			document.body.innerHTML = printContents;
	        			window.print();
	        			document.body.innerHTML = originalContents;
	                }
				</script>
		</div>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
