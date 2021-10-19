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
        
        
        
        
        
        function AsociarBarras(barrasIn) 
		{
			 var x = document.getElementById(barrasIn).selectedIndex;
    		 var y = document.getElementById(barrasIn).options;
    	     var articulo = y[x].value;
    	     
			if(articulo!='??????')
			{
				if(confirm("Desea Asociar el cód de barras "+barrasIn+" al articulo "+articulo+"?"))
				{
					location.replace("<%=basePath%>asociarBarras.do?art="+articulo+"&bar="+barrasIn)
				}
			}
			else
			{
				alert("Seleccionar una opcion")
			}
		}
        
        
        function ContarNuevamente(barrasIn) 
		{
			if(confirm("Seguro que desea contar nuevamente este articulo?"))
			{
				location.replace("<%=basePath%>ContarDenuevo.do?rec=${idRecepcion}&bar="+barrasIn)
			
			}
			
		}
        
        
        function GuardarCantidades() 
		{
        	
        	var selects = document.getElementsByClassName('chosen-select');
        	if(selects.length==0)
        	{
        		location.replace("<%=basePath%>GuardarCantidadesRecep.do?rec=${idRecepcion}")	
        	}
        	else
        	{
        		alert("No puede confirmar cantidades que no están asociadas a código de barra")
        	}
			
			
			
		}
        
        
        
        </script>
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Recepcion&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Articulos Contados</h3>   
                        <h6>Controle los articulos y sus cantidades</h6>
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
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th>Articulo</th>
                                            <th>Color</th>
											<th>Barra</th>
											<th>Contadas</th>
											<th>Esperada</th>
											<th>Contar de nuevo</th>
											<th style="display:none; width: 1px;"></th>
											
										</tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="a" items="${articuloContados}">
                                    		<c:if test="${a.cantidadContada!=a.cantidadOrden}">
												<c:set var="colo" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${a.cantidadContada==a.cantidadOrden}">
												<c:set var="colo" scope="page" value="#adebad"></c:set>
											</c:if>
											<tr class="gradeD">
												<c:if test="${a.articulo=='??????'}">
													<td>
														<select data-placeholder="Puede Seleccionar Varios"  class="chosen-select" tabindex="6" name="clase" id="${a.barra}">
													        <option value="">${a.articulo}</option>
													    	<c:forEach var="op" items="${a.posiblesArt}">
													        	${op.descripcion}
													    	</c:forEach>
													  	</select>
													  	<a id="${a.barra}Link" title="Asociar el articulo a las barras" href="#" onclick="AsociarBarras('${a.barra}');return false;"> <i class="fa fa-angle-double-right fa-lg"></i> &nbsp; Asociar &nbsp;<i class="fa fa-barcode fa-lg"></i></a>
													</td>
												</c:if>
												<c:if test="${a.articulo!='??????'}">
													<td>${a.articulo}</td>
												</c:if>
												<!-- Color -->
												<c:if test="${a.articulo=='??????'}">
													<td>
														??????
													</td>
												</c:if>
												<c:if test="${a.articulo!='??????'}">
													<td>${a.color}</td>
												</c:if>
												<!-- end color -->
												<td><c:out value="${a.barra}"></c:out></td>
												<td style="background-color: ${colo}"><div align="center"><c:out value="${a.cantidadContada}"></c:out></div></td>
												<td><c:out value="${a.cantidadOrden}"></c:out></td>
												<td><a href="#"  onclick="ContarNuevamente('${a.barra}');return false;" class="btn btn-danger btn-xs">Contar de nuevo</a></td>
												<td style="display:none; width: 1px;">
													<c:if test="${a.cantidadContada!=a.cantidadOrden}">
														Diferencias
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									
                                </table>
                                </div>
                                 <br>
                                <div class="row" >
                                	<div class="text-right col-sm-12 col-lg-12"> 
										<div class="flex-container">
											<a href="#" onclick="GuardarCantidades();return false;" class="btn btn-success">Confirmar Cantidades</a>
											<a href="#" onclick="ContarNuevamente('');return false;" class="btn btn-danger">Contar todo de nuevo</a>
										</div>
									</div> 
                                </div>
                            
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
              
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
