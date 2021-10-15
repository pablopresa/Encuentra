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
				location.replace("<%=basePath%>CorregirCantEncPicking.do?articulo="+articulo+"&destino="+destino+"&cant="+cant);
			}
			
		}
        
        function cambiar() 
		{
			change = true;
			
		}
      
        
        
        
        </script>
        
        <script type="text/javascript"> 
			function seleccionar_todo(){
			   for (i=0;i<document.f1.elements.length;i++)
			   {
			      if(document.f1.elements[i].type == "checkbox")
			         document.f1.elements[i].checked=1;
			   }
			   for (i=0;i<document.f0.elements.length;i++)
			   {
			      if(document.f0.elements[i].type == "checkbox")
			         document.f0.elements[i].checked=1
			   }      
			}
			
			//La función seleccionar_todo() realiza un recorrido por todos los elementos del formulario. Para hacer un recorrido por todos los campos se utiliza el array "elements", que guarda una referencia con cada elemento que haya dentro del formulario.
			
			//En el recorrido comprueba si el elemento actual es de tipo "checkbox" (recordar que el array elements contiene todos los elementos, pero sólo deseamos operar con los que sean checkbox) y en ese caso, simplemente se actualiza el atributo "checked" al valor 1, con lo que el chekbox se marcará.
			
			function deseleccionar_todo(){
			   for (i=0;i<document.f1.elements.length;i++)
			   {
			      if(document.f1.elements[i].type == "checkbox")
			         document.f1.elements[i].checked=0
			   }
			   for (i=0;i<document.f0.elements.length;i++)
			   {
			      if(document.f0.elements[i].type == "checkbox")
			         document.f0.elements[i].checked=0
			   }
			}
			
			
			function seleccionarIgual(idPadre)
			{
			  if(!document.getElementById(idPadre).checked)
			  {
			    	document.getElementById(idPadre).checked = "checked";
			  }
			  else
			  {
			  		document.getElementById(idPadre).checked = "";
			  }
			}
			
			
			</script>
			<script>
				function fnResetAllFilters() 
				{
					
					  var table = $('#encuentra-default').dataTable();
		 
				       // Remove all filtering
				      //table.fnFilterClear();
				      table.fnFilter('');
				    
				  }
			</script>
			<script>
				function enviar(texto) 
				{
					  fnResetAllFilters();
					  
					  if(texto=='descartar')
					  {
						  document.getElementById("desca").value = "1";//descartar
					  }
					  else
					  {
						  document.getElementById("desca").value = "0";//reprocesar
					  }
						  
					  
				      document.getElementById('f1').submit();
				  }
			</script>
        
        
		<div id="page-wrapper" >
			<div id="page-inner">
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Recepción&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Diferencias en picking</h3>   
                        <h6></h6>
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
                            	<form action="<%=basePath%>descartarNoEncontrados.do"  id="f1" name="f1">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead>
                                        <tr>
                                            <th>Usuario</th>
                                            <th>Articulo</th>
                                            <th>Descripción</th>
											<th>Destino</th>
											<th>Stock</th>
											<th>Solicitadas (${totSol})</th>
											<th>Encontradas (${totEnc})</th>
											<th>Corregir</th>
											<th><a href="javascript:seleccionar_todo()">Todos</a><br/><a href="javascript:deseleccionar_todo()">Ninguno</a> </th>
										</tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="p" items="${pickings}">
                                    		<c:if test="${p.sol!=p.pick}">
												<tr>
													<td style="vertical-align: middle;"><div align="center"><c:out value="${p.usuario.descripcion}"></c:out></div></td>
													<td style="vertical-align: middle;"><div align="center"><c:out value="${p.articulo}"></c:out></div></td>
													<td style="vertical-align: middle;"><div align="center"><c:out value="${p.descripcion}"></c:out></div></td>
													<td style="vertical-align: middle;"><div align="center"><c:out value="${p.destino.descripcion}"></c:out></div></td>
													<td style="vertical-align: middle;"><div align="center"><c:out value="${p.stockOrigen}"></c:out></div></td>
													<td style="vertical-align: middle;"><div align="center"><c:out value="${p.sol}"></c:out></div></td>
													<td style="text-align:center; vertical-align: middle; background-color: #ffcccc";><input class="form-control" style="width: 80px;" type="number" value="${p.pick}" onchange="cambiar();return false;" name="${p.articulo}-${p.destino.id}" id="${p.articulo}-${p.destino.id}"></td>
													<td><a href="#" onclick="corregir('${p.articulo}',${p.destino.id});return false;" class="btn btn-info">Corregir</a></td>
													<td><input type="checkbox" name="CHK${p.articulo}-${p.destino.id}"></td>
												</tr>
											</c:if>
											
										</c:forEach>
									</tbody>
									<tfoot>
										<tr style="text-align: center">
											<td colspan="6">&nbsp;</td>
											<td><a class="btn btn-info" href="<%=basePath%>/v3/listaArticulosCantPickingEncPr.jsp">Imprimir diferencias</a></td>
											<td><a class="btn btn-info" href="javascript:enviar('descartar');" >Descartar</a></td>
											<td><a class="btn btn-info" href="javascript:enviar('reprocesar');" >Reprocesar</a></td>
										</tr>
									</tfoot>	
                                </table>
                                <input type="text" name="desca" id="desca" value="1" style="visibility: hidden;">
                                <script src="<%=basePath%>v3/assets/select/chosen.jquery.js" type="text/javascript"></script>
								</form>
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
