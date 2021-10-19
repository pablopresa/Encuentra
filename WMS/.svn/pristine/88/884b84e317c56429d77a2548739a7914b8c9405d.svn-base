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
        
        
        var change = false;
        
        
     
        
        
        function corregir(articulo, destino) 
		{
			var cant = document.getElementById(articulo+'-'+destino).value;
			if(confirm("Seguro que desea corregir a "+cant+" las cantidades de este articulo?"))
			{
				location.replace("<%=basePath%>CorregirCantVPicking.do?articulo="+articulo+"&destino="+destino+"&cant="+cant);
			}
			
		}
        
        function cambiar() 
		{
			change = true;
			
		}
      
        
        function Transferir() 
		{
			if(change)
			{
				if(confirm("REFLEXIONE: ha hecho cambios en las cantidades que no guardó, si continua, esos cambios se perderán. ¿desea continuar?"))
				{
					location.replace("<%=basePath%>RemitirPicking.do");
				}
			}
			else
			{
				if(confirm("Se procederá a grabar las transferencias de stock. ¿desea Continuar?"))
				{
					location.replace("<%=basePath%>RemitirPicking.do");
				}
			}
			
			
		}
        
        </script>
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Recepción&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Articulos Contados</h3>   
                        <h6>.</h6>
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
                             Articulos
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th>Usuario</th>
                                            <th>Articulo</th>
                                            <th>Descripción</th>
                                            <th>Stock</th>
                                            <th>StockSAP</th>
											<th>Destino</th>
											<th>Solicitadas (${totSol})</th>
											<th>Encontradas (${totEnc})</th>
											<th>Verificadas (${totSol})</th>
											<th>Corregir</th>
										</tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="p" items="${pickings}">
                                    		<c:if test="${p.sol!=p.pick}">
												<c:set var="coloPI" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${p.sol==p.pick}">
												<c:set var="coloPI" scope="page" value="#adebad"></c:set>
											</c:if>
											<c:if test="${p.verificada!=p.pick}">
												<c:set var="coloVE" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${p.verificada!=p.pick}">
												<c:set var="coloVE" scope="page" value="#adebad"></c:set>
											</c:if>
											
											<tr>
												<td style="vertical-align: middle;"><div align="center"><c:out value="${p.usuario.descripcion}"></c:out></div></td>
												<td style="vertical-align: middle;"><div align="center"><c:out value="${p.articulo}"></c:out></div></td>
												<td style="vertical-align: middle;"><div align="center"><c:out value="${p.descripcion}"></c:out></div></td>
												<td style="vertical-align: middle;"><div align="center"><c:out value="${p.stockOrigen}"></c:out></div></td>
												
												<c:if test="${p.stockOSAP>=p.verificada}">
													<td style="vertical-align: middle;"><div align="center"><c:out value="${p.stockOSAP}"></c:out></div></td>
												</c:if>
												<c:if test="${p.stockOSAP<p.verificada}">
													<td style="vertical-align: middle; background-color:ffcccc"><div align="center"><c:out value="${p.stockOSAP}"></c:out></div></td>
												</c:if>
												
												
												<td style="vertical-align: middle;"><div align="center"><c:out value="${p.destino.descripcion}"></c:out></div></td>
												<td style="vertical-align: middle;"><div align="center"><c:out value="${p.sol}"></c:out></div></td>
												<td style="vertical-align: middle; background-color: ${coloPI}"><div align="center"><c:out value="${p.pick}"></c:out></div></td>
												<td style="vertical-align: middle; background-color: ${coloVE}"><div align="center"><input style="background-color: ${coloVE};" type="number" value="${p.verificada}" onchange="cambiar();return false;" name="${p.articulo}-${p.destino.id}" id="${p.articulo}-${p.destino.id}"></div></td>
												<td><a href="#" onclick="corregir('${p.articulo}',${p.destino.id});return false;" class="btn btn-danger">Corregir</a></td>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<th>&nbsp;</th>
											<th>&nbsp;</th>
											<th>&nbsp;</th>
											<th>&nbsp;</th>
											<th><a href="#" onclick="Transferir(),this.addEventListener('click', clickStopper, false);return false;" class="btn btn-danger">Grabar Transferencias de Stock</a></th>
											
										</tr>
									</tfoot>
                                </table>
                                 <script src="<%=basePath%>v3/assets/select/chosen.jquery.js" type="text/javascript"></script>
							
                            </div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
                <!-- /. ROW  -->
            </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
