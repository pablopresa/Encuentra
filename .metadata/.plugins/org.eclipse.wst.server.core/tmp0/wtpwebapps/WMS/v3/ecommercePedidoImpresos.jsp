<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
         
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
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
					  
					  if(texto=='tarea')
					  {
						  document.getElementById("tipoSave").value = "1";//picking+tarea
					  }
					  else
					  {
						  document.getElementById("tipoSave").value = "0";//picking
					  }
						  
					  
				      document.getElementById('f1').submit();
				  }
			</script>
		  <link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
        <!-- /. NAV SIDE  -->
        	<div id="page-wrapper" >
			  <div id="page-inner">
		    	<div class="row">
                	<div class="col-md-12">
                    	<h3>Ecommerce&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Articulos Pedidos ya Impresos</h3>   
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
                    	<strong style="color: red"> ${menError}"</strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                    <div class="card-header">
                            Confirme todos, puede negar de a uno
                        </div>
                        <div class="card-body">
                        	<form class="form-horizontal" role="form" id="f1" name="f1" action="<%=basePath%>/ConfirmarArticulosPedidos.do" method="post">
                            	<table class="table table-compact table-striped table-bordered table-hover" id="encuentra-default" border=1 frame=void rules=rows cellpadding="1" >
	                                    <thead>
	                                        <tr>
	                                           	<th>Imagen</th>
												<th>Articulo</th>
												<th>Cantidad</th>
												<th><a href="javascript:seleccionar_todo()">Seleccionar todos</a> | <a href="javascript:deseleccionar_todo()">Seleccionar ninguno</a> </th>
												<th>Pendiente<br/>desde</th>
												<th>No Enviar</th>
											</tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="a" items="${articulosImp}">
												<tr class="gradeD" style="border-collapse: collapse; border-bottom: 1px dashed black; font-size: 13px">
													<td style="padding: 2px; vertical-align: middle; text-align: center;">
														<img alt="Imagen" src="https://www.stadium.com.uy/public/ctm/visual/${a.imagen}.jpg" style="width: 100px;">
													</td>
													<td style="padding: 2px; vertical-align: middle; text-align: center;">
														<c:out value="${a.articulo}"></c:out>
													</td>
													<td style="padding: 2px; vertical-align: middle; text-align: center;">
														<c:out value="${a.cantidad}"></c:out>
													</td>
													<td  style="padding: 2px; vertical-align: middle; text-align: center;"><input type="checkbox" checked="checked" name="${a.articulo}"></td>
													<td style="padding: 2px; vertical-align: middle; text-align: center;">
														<c:out value="${a.fechaPedido}"></c:out>
													</td>
													<td style="padding: 2px; vertical-align: middle; text-align: center;">
														<input type="text" id="motiv${a.articulo}">
														<a class="btn btn-danger" href="javascript:deleteLinea('${a.idPedido}','${a.deposito}','${a.articulo}','${a.cantidad}','imp')">Rechazar</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
										<tfoot>
											
										</tfoot>
									</table>
									<div class="row" Style="display: flex;" >
										<div style="text-align: left; padding-left: 10px;">
											<a href="<%=basePath%>Guest.do" class="btn btn-info" style="font-size: 20px;">Volver al Menu</a>
										</div>
										<div style="padding-left: 520px;">
											<button type="submit"  class="btn btn-success" style="font-size: 20px;">CONFIRMAR LISTADO</button>
										</div>
									</div>
									
	                          </form>
						</div>
                    </div>
                 </div>
            </div>
                <!-- /. ROW  -->
          </div>
             <!-- /. PAGE INNER  -->
		</div>
        <!-- /. PAGE WRAPPER  -->    
        
		<script type="text/javascript">
		
		
			function subm()
			{
				document.getElementById("altaNuevo").submit();
			}
				
			function deleteLinea(idPedidoV, idDepoV, idArticuloV,cantidadV,tipo)
			{
				 if(confirm("Seguro?"))
				 {
				 //idPedido idDepo idArticulo motivo cantidad
				 	var motivoV =document.getElementById("motiv"+idArticuloV).value;
					 var req = '<%=basePath%>/RechazarArticulosPedidos.do?idPedido='+idPedidoV+'&idDepo='+idDepoV+'&idArticulo='+idArticuloV+'&motivo='+motivoV+'&cantidad='+cantidadV+'&tip='+tipo;
					 window.location.assign(req);
				 }
				
				
				
			}
			
			
		
			function SendAjaxPost(request) 
			{
			    var xmlhttp = new XMLHttpRequest();
			    //xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			    xmlhttp.onreadystatechange = function() {
			        if (xmlhttp.readyState == XMLHttpRequest.DONE ) {
			           if (xmlhttp.status == 200) 
			           {
			              
			               
			           }
			           else if (xmlhttp.status == 400) {
			              alert('There was an error 400');
			           }
			           else {
			               alert('Error en la actualización, cierre y abra el programa');
			           }
			        }
			    };

			    
			    xmlhttp.open("POST", request, true);
			    xmlhttp.send();
			    req='';
			}
		</script>        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
