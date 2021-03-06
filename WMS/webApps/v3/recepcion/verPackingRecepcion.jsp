<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		
		<script type="text/javascript">
				if (document.cookie != "") {
            		//if (confirm("Eliminar todas las Cookies?")) {
                		la_cookie = document.cookie.split("; ")
                		fecha_fin = new Date();
                		fecha_fin.setDate(fecha_fin.getDate()-1)
						for (i=0; i<la_cookie.length; i++) {
                    		mi_cookie = la_cookie[i].split("=")[0]
                    		document.cookie = mi_cookie + "=;expires=" + fecha_fin.toGMTString()
                		}
                		//document.write("Se han eliminado: " + la_cookie.length + " Cookies ")
            		//}
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
			
			//La funci?n seleccionar_todo() realiza un recorrido por todos los elementos del formulario. Para hacer un recorrido por todos los campos se utiliza el array "elements", que guarda una referencia con cada elemento que haya dentro del formulario.
			
			//En el recorrido comprueba si el elemento actual es de tipo "checkbox" (recordar que el array elements contiene todos los elementos, pero s?lo deseamos operar con los que sean checkbox) y en ese caso, simplemente se actualiza el atributo "checked" al valor 1, con lo que el chekbox se marcar?.
			
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
			
			
			
			
			
			function seleccionarIgual(idPadre,idArticulo)
			{
			
			  if(document.getElementById('checkbox-fill-'+idPadre+'-'+idArticulo).checked)
			  {
			  		for (i=0;i<document.f1.elements.length;i++)
					   {
					      var name =document.f1.elements[i].name;
					      if(document.f1.elements[i].type == "checkbox" && name.startsWith(idPadre))
					         document.f1.elements[i].checked="1"
					   }
			    	
			  }
			  else
			  {
				  for (i=0;i<document.f1.elements.length;i++)
				   {
				   	  var name =document.f1.elements[i].name;
				      if(document.f1.elements[i].type == "checkbox" && name.startsWith(idPadre))
				         document.f1.elements[i].checked=0
				   }
			  		
			  }
			}
			
			
			</script>
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .bg-enc{
		    	background-color: rgb(244,247,250)
		    };
		    .th-aut{
		    	max-width: 100px;
		    }
		  </style>
		  
        <!-- /. NAV SIDE  -->
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<a href="${urlPDF}" target="blank"><h6><strong> ${menError}</strong></h6></a>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    <div class="card">
                    <div class="card-header">
                    		<h5>
                            	 Imprimir etiquetas de packing
                             </h5>
                        </div>
                        <div class="card-body">
							
							<form action="<%=basePath%>PrintPackingRecepcionII.do" method="post" id="f1" name="f1">
								<div class="table-responsive">
	                            	<table class="display table nowrap table-striped table-hover dataTable" id="encuentra-default">
	                                    <thead>
	                                        <tr>
	                                        	<th class="text-center">Art?culo</th>
												<th class="text-center">Packing</th>
									            <th class="text-center">Total</th>
												<th class="text-center"><a href="javascript:seleccionar_todo()">Todos</a><br/><a href="javascript:deseleccionar_todo()">Ninguno</a> </th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
											<c:forEach var="ar" items="${listaPacking}">
												<tr class="gradeD">
													<td class="text-center">Art. ${ar.idArticulo}</td>
										            <td class="text-center"><c:out value="${ar.cantidadPacking}x${ar.cantidadBultos}"></c:out></td>
										            <c:set var="ttl" value="${ar.cantidadPacking*ar.cantidadBultos}"></c:set>
										            <c:if test="${ttl==ar.cantidadTotal}">
											            <td class="text-center">
											            	<c:out value="${ar.cantidadTotal}"></c:out>
											            </td>
									            	</c:if>
									            	<c:if test="${ttl!=ar.cantidadTotal}">
											            <td class="table-warning text-center">
											            	<c:out value="${ar.cantidadTotal}"></c:out>
											            </td>
									            	</c:if>
													<td class="text-center">
														<div class="checkbox checkbox-fill d-inline">
															<input type="checkbox" name="${ar.idArticulo}" id="checkbox-fill-${ar.idArticulo}" checked="checked" >
															<label for="checkbox-fill-${ar.idArticulo}" class="cr"></label>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
	                                </table>
	                                </div>
	                                <br>
	                                <div class="row">
	                                	<div class="text-right col-sm-12 col-lg-12"> 
	                                 		<div class="flex-container">
		                                 		<input type="text" name="tipoSave" id="tipoSave" value="" style="display: none;">
												<button class="btn btn-info" name="verEnPDF" type="submit" value="Imprimir">Imprimir Etiquetas</button>
												<button class="btn btn-info" name="verEnPDF" type="submit" value="PDF">Ver en PDF</button>
											</div>
										</div> 
	                                </div>
	                               </form>
	                          </div>
                        </div>
                        
                    <!--End Advanced Tables -->
            </div>
                <!-- /. ROW  -->
            </div>
        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
