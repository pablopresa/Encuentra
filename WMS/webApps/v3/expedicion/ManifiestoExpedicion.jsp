<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        <!-- /. NAV SIDE  -->
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Expedici?n&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Alta Env?o&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Detalles del Env?o</h3>   
                        <h6>Puede imprimir cada envio</h6>
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
								          <label class="col-md-4 control-label" for="idEsta" class="col-md-6 control-label">Acompa?ante:&nbsp;</label>
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
	                                <table class="no-sort table table-striped table-bordered table-hover">
	                                    <thead>
	                                        <tr>
	                                           <th style="text-align: center;">Deposito</th>
	                                           <c:forEach var="ti" items="${envio.depositos.get(0).cantidadesXTipoDocs}">
	                                           		<th style="text-align: center;">${ti.descripcion}</th>
												</c:forEach>	
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    	<c:forEach var="deps" items="${envio.depositos}">
	                                    		<tr>
	                                    			<td style="text-align: center;">${deps.idDeposito} - ${deps.descripcion}</td>	
													<c:forEach var="ti" items="${deps.cantidadesXTipoDocs}">													
														<td style="text-align: center;">${ti.idB}</td>							
													</c:forEach>
												</tr>
											</c:forEach>
	                                    </tbody>
	                                    <tfoot>
									        <tr>
									    		<td colspan="2" style="text-align: right;"><br>Firma de Conformidad<br></td>
									    		<td ></td>
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
              
            
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
