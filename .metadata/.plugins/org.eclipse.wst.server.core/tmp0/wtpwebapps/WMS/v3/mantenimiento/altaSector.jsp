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
                    	<h3>Mantenimiento&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Alta Estanteria</h3>   
                        <h6>Complete todos los campos </h6>
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
                             Formulario de alta
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" role="form" method="post" action="<%=basePath%>/AltaSectorI.do">
                            	<div class="row">
	                            	<c:if test="${para!=null && para==4}">
										<div style="width: 1px; height: 1px; visibility: hidden;">
											<input required class="form-control" name="id"  value="${id}"/>
											<input required class="form-control" name="para"  value="${para}"/>
										</div>
									</c:if>
									<div class="col-sm-6 col-lg-4">
								        <div class="form-group">
								          
								          <label class="col-md-4 control-label" for="id" class="col-md-4 control-label">Código:&nbsp</label>
								          <div class="col-md-8">
								            	<input readonly="readonly" required class="form-control" name="id" id="id" value="${id}" />
								          </div>
								        </div>
								    </div>
																	
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="descripcion">Descripción:</label>
											<div class="col-md-8">
										  		<input required class="form-control" name="descripcion" id="descripcion" value="${descripcion}" />
										  	</div>											
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="tipo">Depósito:</label>
												
													<div class="col-md-8">
												  	 	<select name="depo" id="depo" class="form-control">
												  		<c:forEach var="t" items="${depositosAS}">
												  			<c:if test="${t.id==dep}">
												  				<option value="${t.id}" selected="selected">${t.nombre}</option>
												  			</c:if>
												  			<c:if test="${t.id!=dep}">
												  				<option value="${t.id}" >${t.nombre}</option>
												  			</c:if>
												  		</c:forEach>
												  		</select>
												  	</div>
										  		
										  		
											
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="piso">Piso:</label>
										  	<div class="col-md-8">
											  	<select name="piso" id="piso" class="form-control">
											  	
											    <c:if test="${piso=='1'}" >
											  		<option value="1" selected="selected">1</option>
											  	</c:if>
											  	<c:if test="${piso!='1'}" >
											  		<option value="1" >1</option>
											  	</c:if>
											  	
											  	<c:if test="${piso=='2'}" >
											  		<option value="2" selected="selected">2</option>
											  	</c:if>
											  	<c:if test="${piso!='2'}" >
											  		<option value="2" >2</option>
											  	</c:if>
											  	
											  	<c:if test="${piso=='3'}" >
											  		<option value="3" selected="selected">3</option>
											  	</c:if>
											  	<c:if test="${piso!='3'}" >
											  		<option value="3" >3</option>
											  	</c:if>
											  	
											  	</select>
											  </div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="color">Color Ref.:</label>
											<div class="col-md-8">  	
											  	<select name="color" id="color" class="form-control">
											  	
											  	<c:if test="${color=='green'}" >
											  		<option value="green" selected="selected">Verde</option>
											  	</c:if>
											  	<c:if test="${color!='green'}" >
											  		<option value="green" >Verde</option>
											  	</c:if>
											  	
											  	<c:if test="${color=='yellow'}" >
											  		<option value="yellow" selected="selected">Amarillo</option>
											  	</c:if>
											  	<c:if test="${color!='yellow'}" >
											  		<option value="yellow" >Amarillo</option>
											  	</c:if>
											  	
		  										<c:if test="${color=='blue'}" >
											  		<option value="blue" selected="selected">Azul</option>
											  	</c:if>
											  	<c:if test="${color!='blue'}" >
											  		<option value="blue" >Azul</option>
											  	</c:if>
											  	</select>
											</div>
										</div>
									</div>
									
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="tipo">Tipo Estanteria:</label>
												
													<div class="col-md-8">
												  	 	<select name="tipo" id="tipo" class="form-control">
												  		<c:forEach var="t" items="${tiposS}">
												  			<c:if test="${t.idTipo==tipo}">
												  				<option value="${t.idTipo}" selected="selected">${t.descripcion}</option>
												  			</c:if>
												  			<c:if test="${t.idTipo!=tipo}">
												  				<option value="${t.idTipo}">${t.descripcion}</option>
												  			</c:if>
												  		</c:forEach>
												  		</select>
												  	</div>
										</div>
									</div>
									<div class="col-sm-6 col-lg-4">
										<div class="form-group">
											<label class="col-md-4 control-label" for="cubi">Cod. Ubicación:</label>
											<c:if test="${para!=null && para==4}">
												<div class="col-md-8">
													<input readonly="readonly" required class="form-control" name="cubi" id="cubi" value="${cubi}" readonly="readonly"/>
												</div>
											</c:if>
											<c:if test="${para==null || para==3}">
												<div class="col-md-8">
													<input readonly="readonly" required class="form-control" name="cubi" id="cubi" value="${cubi}" />
												</div>	
											</c:if>
										 </div>
									</div>
								<div class="col-sm-6 col-lg-4">
									<div class="form-group">
										<label class="col-md-4 control-label" for="estantes">Estantes:</label>
										<div class="col-md-8">
											<input required class="form-control" name="estantes"
												id="estantes" value="${estantes}" />
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-lg-4">
									<div class="form-group">
										<label class="col-md-4 control-label" for="modulos">Modulos:</label>
										<div class="col-md-8">
											<input required class="form-control" name="modulos"
												id="modulos" value="${modulos}" />
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-lg-4">
									<div class="form-group">
										<label class="col-md-4 control-label" for="senti">Sentido:</label>
										<div class="col-md-8">
											<select name="senti" id="senti" class="form-control">

												<c:if test="${senti=='ID'}">
													<option value="ID" selected="selected">Izquierda a Derecha</option>
												</c:if>
												<c:if test="${senti!='ID'}">
													<option value="ID">Izquierda a Derecha</option>
												</c:if>

												<c:if test="${senti=='DI'}">
													<option value="DI" selected="selected">Derecha a Izquierda</option>
												</c:if>
												<c:if test="${senti!='DI'}">
													<option value="DI">Derecha a Izquierda</option>
													
												</c:if> 
											</select> 
										</div>
									</div>
								</div>
								<div class="col-sm-6 col-lg-4">
									<div class="form-group">
										<label class="col-md-4 control-label" for="uso">Uso:</label>
										<div class="col-md-8">
											<select name="uso" id="uso" class="form-control">
												<c:forEach var="u" items="${usos}">
													<c:if test="${u.id==uso}">
														<option value="${u.id}" selected="selected">${u.descripcion}</option>
													</c:if>
													<c:if test="${u.id!=dep}">
														<option value="${u.id}">${u.descripcion}</option>
													</c:if>
												</c:forEach>
											</select>	
										</div>
									</div>
								</div>
								<c:if test="${trs == null}">
									<div class="col-sm-6 col-lg-4">
										<label class="col-md-4 control-label " for="senti"></label>
										<div class="col-md-8">
											<div class="form-group">
												<button type="submit" class="btn btn-info btn-lg">Crear Estanteria</button>
											</div>
										</div>
									</div>
								</c:if>


							</div>
	                        </form>
                        </div>
                        <div class="card-header">
                             Dibujo de la estenteria
                        </div>
                        <div class="card-body">
                        	<div class="table-responsive">
                        		<table class="table table-striped table-bordered table-hover" >
									<thead style="border: 1px solid; vertical-align: middle;">
										<c:forEach var="t" items="${trs}">
										<tr class="gradeU odd">
											<c:forEach var="s" items="${t}">
												<th><c:out value="${s}"></c:out> </th>
											</c:forEach>										
										</tr>
										</c:forEach>
									</thead>
									<tbody>
									
										
											<tr class="gradeD">
												<td><div align="center"></div></td>
												<td><div align="center"></div></td>
												<td><div align="center"></div></td>
											
											</tr>
										
									
									</tbody>
								</table>
								<c:if test="${trs!=null}">
									<table class="table table-striped table-bordered table-hover">
										<tr>
											<td style="text-align: right;">
													<form id="contactform2" class="form-horizontal" role="form" method="post" action="<%=basePath%>/BajaSector.do?id=${secInt}">
														<input type="submit" name="Submit"  class="btn btn-info" value="Cancelar" />
													</form>		
											</td>
											<td style="text-align: right;">
													<form id="contactform3" class="form-horizontal" role="form" method="post" action="<%=basePath%>/AltaSectorII.do">
														<input type="submit" name="Submit"  class="btn btn-info" value="Confirmar Alta" />
													</form>	
											</td>
											<form id="contactform4" class="form-horizontal" role="form" target="_BLANK" method="post" action="<%=basePath%>/ImprimirEtSector.do">
											<td style="text-align: right;">
												<div class="checkbox checkbox-fill d-inline">
								                 <input type="checkbox" name="idEtiqueta" id="checkbox-1" value="Etiqueta Grande" checked>
								                 <label for="checkbox-1" class="cr">Etiqueta Grande</label>
								             </div>
											</td>
											<td style="text-align: right;">
												<input type="submit" name="Submit"  class="btn btn-info" value="Imprimir etiquetas" />			
											</td>
											</form>
											
										</tr>
									</table>
									
								</c:if>
							</div>
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
