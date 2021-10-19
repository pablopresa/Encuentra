<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
				
	            	<div class="table-responsive">
                    	<form action="<%=basePath%>EditarFiltroRegla.do" method="post" id="FiltroRegla">
				            <table class="table table-bordered table-hover" id="encuentra-default">
		                         <thead>
		                             <tr>
		                          		<th class ="text-center">Filtro</th>
		                               	<th class ="text-center">Valor</th>
									</tr>
		                       	</thead>
		                       	<tbody>
									<c:forEach var="f" items="${filtrosRegla}">
										<tr class="gradeD">
											<td class ="text-center"><c:out value="${f.desc}"></c:out></td>
											<td class ="text-center">
												<c:choose>
													<c:when test="${f.desc=='Clase'}">
														<select  class="form-control" name="${f.id}">
											            	<option value=""></option>
													    	<c:forEach var="c" items="${clases}">
													    		<c:if test="${c.id==f.descripcion && f.descripcion!=''}">
													    			<option value="${c.id}" selected="selected">${c.descripcion}</option>
													    		</c:if>
													    		<c:if test="${c.id!=f.descripcion}">
													    			<option value="${c.id}">${c.descripcion}</option>
													    		</c:if>													        	
													    	</c:forEach>
													  	</select>
													</c:when>
													<c:when test="${f.desc=='Marca'}">
														<select  class="form-control" name="${f.id}">
											            	<option value=""></option>
													    	<c:forEach var="m" items="${marcas}">
													    		<c:if test="${m.id==f.descripcion && f.descripcion!=''}">
													    			<option value="${m.id}" selected="selected">${m.descripcion}</option>
													    		</c:if>
													    		<c:if test="${m.id!=f.descripcion}">
													    			<option value="${m.id}">${m.descripcion}</option>
													    		</c:if>													        	
													    	</c:forEach>
													  	</select>
													</c:when>
													<c:when test="${f.desc=='Categoria'}">
														<select  class="form-control" name="${f.id}">
											            	<option value=""></option>
													    	<c:forEach var="c" items="${categorias}">
													    		<c:if test="${c.id==f.descripcion && f.descripcion!=''}">
													    			<option value="${c.id}" selected="selected">${c.descripcion}</option>
													    		</c:if>
													    		<c:if test="${c.id!=f.descripcion}">
													    			<option value="${c.id}">${c.descripcion}</option>
													    		</c:if>													        	
													    	</c:forEach>
													  	</select>
													</c:when>
													<c:when test="${f.desc=='Genero'}">
														<select  class="form-control" name="${f.id}">
											            	<option value=""></option>
													    	<c:forEach var="g" items="${generos}">
													    		<c:if test="${g.id==f.descripcion && f.descripcion!=''}">
													    			<option value="${g.id}" selected="selected">${g.descripcion}</option>
													    		</c:if>
													    		<c:if test="${g.id!=f.descripcion}">
													    			<option value="${g.id}">${g.descripcion}</option>
													    		</c:if>													        	
													    	</c:forEach>
													  	</select>
													</c:when>
													<c:when test="${f.desc=='Seccion'}">
														<select  class="form-control" name="${f.id}">
											            	<option value=""></option>
													    	<c:forEach var="s" items="${secciones}">
													    		<c:if test="${s.id==f.descripcion && f.descripcion!=''}">
													    			<option value="${s.id}" selected="selected">${s.descripcion}</option>
													    		</c:if>
													    		<c:if test="${s.id!=f.descripcion}">
													    			<option value="${s.id}">${s.descripcion}</option>
													    		</c:if>													        	
													    	</c:forEach>
													  	</select>
													</c:when>
													<c:otherwise>
														<input type="text" class="form-control" value="${f.descripcion}" name="${f.id}" />
													</c:otherwise>
												</c:choose>
												
											</td>
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
				
