<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"/>
<jsp:directive.page isELIgnored="false"/>
<jsp:scriptlet>
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
</jsp:scriptlet>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        <jsp:include page="/v3/util/footer.jsp"></jsp:include>
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        
		<div id="page-wrapper">
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Ecommerce <i class="fa fa-angle-double-right" aria-hidden="true"></i> Alta Pedido Paso 1 de 3</h3>   
                        <h6>Alta de cliente</h6>
                    </div>
                </div>              
                 
                <hr/>
                <div class="row">
	                <div class="col-md-12">
	                	<c:if test="${menError!=null}">
	                	<h6><strong>Mensaje:</strong></h6>
	                    <div class="alert alert-info">
	                    	<strong style="color: red"> <c:out value="${menError}"/></strong>            
	                    </div>
	                    </c:if>
	                    
	                    <div class="card">
	                        <div class="card-header">
	                             Formulario de alta
	                        </div>
	                        <div class="card-body">
	                            <form class="form-horizontal" role="form"  action="<%=basePath%>/EcommerceAltaPedidoI.do">
	                            	<div class="row">
		                            	<div class="col-sm-6 col-lg-4">
									        <div class="form-group">
									          <label class="col-md-4 control-label" for="documento" class="col-md-4 control-label">Documento:&nbsp</label>
									          <div class="col-md-8">
									          		<table>
									          			<tr>
									          				<td><input style="width: 120px;" required="required" class="form-control" name="documento" type="number" id="documento" value="${documento}"/></td>
									          				<td><input style="width: 80px;" class="form-control" name="documentoG" id="documentoG" type="number" value="${documentoG}"/></td>
									          			</tr>
									          		</table>
									            	
									          </div>
									        </div>
									    </div>
		                            	
		                            	<div class="col-sm-6 col-lg-4">
									        <div class="form-group">
									          <label class="col-md-4 control-label" for="documentoE" class="col-md-4 control-label">Documento Extranjerod:&nbsp</label>
									          <div class="col-md-8">
									            	<input class="form-control" name="documentoE" id="documentoE" value="${documentoE}"/>
									          </div>
									        </div>
									    </div>
		                            	
		                            	<div class="col-sm-6 col-lg-4">
									        <div class="form-group">
									          <label class="col-md-4 control-label" for="nombre" class="col-md-4 control-label">Nombre:&nbsp</label>
									          <div class="col-md-8">
									            	<input required="" class="form-control" name="nombre" id="nombre" value="${nombre}"/>
									          </div>
									        </div>
									    </div>
																		
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="apellido">Apellido:</label>
												<div class="col-md-8">
											  		<input required="" class="form-control" name="apellido" id="apellido" value="${apellido}"/>
											  	</div>											
											</div>
										</div>
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="direccion">Dirección:</label>
												<div class="col-md-8">
											  		<input required="" class="form-control" name="direccion" id="direccion" value="${direccion}"/>
											  	</div>											
											</div>
										</div>
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="ciudad">Ciudad:</label>
												<div class="col-md-8">
											  		<input required="" class="form-control" name="ciudad" id="ciudad" value="${ciudad}"/>
											  	</div>											
											</div>
										</div>
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="departamento">Departamento:</label>
											  	<div class="col-md-8">
												  	<select name="departamento" id="departamento" class="form-control">
												  		<option value="Artigas">Artigas</option>
														<option value="Canelones">Canelones</option>
														<option value="Cerro Largo">Cerro Largo</option>
														<option value="Colonia">Colonia</option>
														<option value="Durazno">Durazno</option>
														<option value="Flores">Flores</option>
														<option value="Florida">Florida</option>
														<option value="Lavalleja">Lavalleja</option>
														<option value="Maldonado">Maldonado</option>
														<option value="Montevideo">Montevideo</option>
														<option value="Paysandú">Paysandú</option>
														<option value="Río Negro">Río Negro</option>
														<option value="Rivera">Rivera</option>
														<option value="Rocha">Rocha</option>
														<option value="Salto">Salto</option>
														<option value="San José">San José</option>
														<option value="Soriano">Soriano</option>
														<option value="Tacuarembó">Tacuarembó</option>
														<option value="Treinta y Tres">Treinta y Tres</option>
												  	</select>
												 </div>
											</div>
										</div>
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="cpostal">Cod. Postal:</label>
												<div class="col-md-8">
											  		<input required="" class="form-control" name="cpostal" id="cpostal" value="${cpostal}"/>
											  	</div>											
											</div>
										</div>
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="telefono">Teléfono:</label>
												<div class="col-md-8">
											  		<input required="" class="form-control" name="telefono" id="telefono" value="${telefono}"/>
											  	</div>											
											</div>
										</div>
										
										<div class="col-sm-6 col-lg-4">
											<div class="form-group">
												<label class="col-md-4 control-label" for="mail">Mail:</label>
												<div class="col-md-8">
											  		<input required="" class="form-control" name="mail" id="mail" value="${mail}"/>
											  	</div>											
											</div>
										</div>
										
										
										<div class="col-sm-6 col-lg-4">
											
											 <div class="col-md-8">
												<div class="form-group">
													<button type="submit" class="btn btn-info">Siguiente Paso</button>
												</div>
											</div>	
										</div>
										
		                                
		                        	</div>
		                        </form>
	                        </div>
	                   </div>
	                </div>
            	</div>
                
            </div>
             
		</div>
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
