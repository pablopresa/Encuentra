<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		<link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
        <!-- /. NAV SIDE  -->
        <script type="text/javascript">
        
        
        
        
        
        function Facturar(articulo) 
		{
			 var cant = document.getElementById('cc'+articulo).value;
    		 document.getElementById(articulo).value = cant;
    	     
		}
        
        
        function ContarNuevamente(barrasIn) 
		{
			if(confirm("Seguro que desea contar nuevamente este articulo?"))
			{
				location.replace("<%=basePath%>ContarDenuevo.do?rec=${idRecepcion}&bar="+barrasIn)
			
			}
			
		}
        
        
        function GuardarCantidades(fin) 
		{
        	document.getElementById('terminar').value = fin;
        	var table = $('#encuentra-default').DataTable();
    		table
    		 .search( '' )
    		 .columns().search( '' )
    		 .draw();
        	if(document.getElementById("formulario").checkValidity())
        	{
        		
        		document.getElementById("formulario").submit();
        	}
        	else
        	{
        		alert("Complete Datos del Documemto");
        	}
			
			
			
		}
        
        
        
        </script>
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Recepción&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Asociar Documento a Articulos Contados</h3>   
                        <h6>Puede ingresar varios documentos</h6>
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
                        		Articulos
                        	</h5>
                             
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form id="formulario"  action="<%=basePath%>/AsociarDocRecep.do" method="post" class="form-horizontal">
                            	<div class="row" >  
                            		<div class="col-sm-4 col-lg-2">
											<div class="form-group">	
												<label class="p-2">&nbsp</label>										
													<div class="col-md-8">
												  		
												  	</div>											
											</div>
										</div>
										                              	
									<c:if test="${not recSel.importa}">
										<div class="col-sm-4 col-lg-1">
											<div class="form-group">	
												<label class="p-2" >Serie</label>										
													<div class="col-md-8">
												  		<input required class="form-control" id="trans" name="serie" value="A"  style="width: 60px;"/>
												  	</div>											
											</div>	
										</div>											
										<div class="col-sm-4 col-lg-3">
											<div class="form-group">	
												<label class="p-2" >Documento</label>										
													<div class="col-md-8">
												  		<input type="number" required class="form-control" id="trans" name="factura" value="${recSel.id}" />
												  	</div>											
											</div>
										</div>	
									</c:if>
									<c:if test="${recSel.importa}">
										<div class="col-sm-4 col-lg-1">
											<div class="form-group">	
												<label class="p-2">Serie</label>										
													<div class="col-md-8">
												  		<input required class="form-control" id="trans" name="serie" value="IMP" style="width: 60px;"/>
												  	</div>											
											</div>
										</div>		
										
										<div class="col-sm-4 col-lg-3">
											<div class="form-group">	
												<label class="p-2">Documento</label>										
													<div class="col-md-8">
												  		<input type="number" required class="form-control" id="trans" value="${recSel.id}" name="factura" />
												  	</div>											
											</div>
										</div>
										
									</c:if>
									<div class="col-sm-4 col-lg-2">
											<div class="form-group">	
												<label class="p-2">&nbsp</label>										
													<div class="col-md-8">
													<div class="form-group">	
												  		<button href="#" onclick="GuardarCantidades(0);return false;" class="btn btn-danger">Guardar</button>
												  		</div>
												  	</div>											
											</div>
										</div>
										<div class="col-sm-4 col-lg-1">
											<div class="form-group">	
												<label class="p-2">&nbsp</label>										
													<div class="col-md-8">
												  		<button href="#" onclick="GuardarCantidades(1);return false;" class="btn btn-success">Terminar</button>
												  	</div>											
											</div>
										</div>
									
                                </div>
                                
                                
                                
                            	<input type="number" style="display:none; width: 1px; height: 1px;" id="terminar" name="terminar"/>
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                            		<thead>
                                        <tr>
											<th>Folio</th>
											<th>OC</th>
                                            <th>Articulo</th>
											<th>Cantidad Base</th>
											<th>Cantidad Contada</th>
											<th>Cantidad Factura</th>
											
											
										</tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="a" items="${artFacturables}">
                                    			<tr class="gradeD">
												<td>${a.folio}</td>	
                                    			<td>${a.idOC}</td>
												<td><div align="center"><c:out value="${a.articulo}"></c:out></div></td>
												<td><div align="center"><c:out value="${a.cantBase}"></c:out></div></td>
												<td>
													<div align="center">
													<c:out value="${a.cantidadContada}"></c:out>
													<input type="number" style="visibility: hidden; width: 1px; height: 1px;" id="cc${a.articulo}" value="${a.cantidadContada}"/>
													<a id="${a.barra}Link" title="Facturar" href="#" onclick="Facturar('${a.articulo}');return false;"> <i class="fa fa-angle-double-right fa-lg"></i></a>
													</div>
												</td>
												<td><input type="text"  required class="arrow-togglable form-control" id="${a.idOC}_${a.articulo}" name="${a.idOC}_${a.articulo}" style="width: 100px;" value="${a.cantidadContada}"/></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</form>
                            </div>
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
