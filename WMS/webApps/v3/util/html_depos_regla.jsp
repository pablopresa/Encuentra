<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
				
	            	<div class="table-responsive">
                    	<form action="<%=basePath%>EditarDepositoRegla.do" method="post" id="DeposRegla">
				            <table class="table table-compact table-bordered table-hover" id="encuentra-modal">
		                         <thead>
		                             <tr>
		                          		<th class ="text-center">Deposito</th>
		                               	<th class ="text-center">En regla</th>
									</tr>
		                       	</thead>
		                       	<tbody>
									<c:forEach var="d" items="${deposRegla}">										
										<tr class="gradeD">
												<td class="text-center">${d.desc}</td>
												<c:if test="${d.descripcion==1}">
													<td class="text-center">
														<div class="checkbox checkbox-fill d-inline">
															<input type="checkbox" name="${d.id}" id="checkbox-fill-${d.id}" checked="checked" >
															<label for="checkbox-fill-${d.id}" class="cr"></label>
														</div>
													</td>	
												</c:if>
												<c:if test="${d.descripcion!=1}">
													<td class="text-center">
														<div class="checkbox checkbox-fill d-inline">
															<input type="checkbox" name="${d.id}" id="checkbox-fill-${d.id}" >
															<label for="checkbox-fill-${d.id}" class="cr"></label>
														</div>	
													</td>
												</c:if>
											</tr>									
									</c:forEach>
								</tbody>
							<!--<tfoot>
									<tr>
		                              	<th><input type="text" value="${regSel}"  name="regSel" readonly="readonly"></th>
		                              	<th><input type="submit" value="Guardar Cambios" class="btn btn-info"/> </th>
									</tr>
								</tfoot>-->
							</table>	
							<input type="text" value="${regSel}"  name="regSel" readonly="readonly" style="display: none;">	
						</form>
					</div>	
				
				
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
