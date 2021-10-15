<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-82" data-genuitec-path="/encuentra/webApps/gentelella-master/gentelella-master/production/menu.jsp">
  
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <form action="<%=basePath%>DarDash.do" id="filtros">
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> Depositos <span class="fa fa-chevron-down"></span></a>
                  	
                    <ul class="nav child_menu" id="deposList">
                    	<li> <table><tr><td><div id="allInD" class="btn btn-primary">All ON</div></td><td><div id="allOffD" class="btn btn-primary">All OFF</div></td><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
                    	<c:forEach var="t" items="${depositosD}">
                    		
	                    	<li>
	                      		<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  								<input checked data-toggle="toggle"  name="d${t.id}" class="swTienda" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  								<input data-toggle="toggle"  name="d${t.id}" class="swTienda" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
							</li>
                    	</c:forEach>
                    	<li id="visibleC"> <table><tr><td><div id="loadMoreC" class="btn btn-primary">Ver mas</div></td><td><div id="showLessC" class="btn btn-primary">Ver Menos</div></td></tr></table></li>
                    	<li id="visibleC2"> <table><tr><td><div id="allInD" class="btn btn-primary">All ON</div></td><td><div id="allOffD" class="btn btn-primary">All OFF</div></td><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
                     
                    </ul>
                   </li>
                   <li><a><i class="fa fa-star"></i> Marcas <span class="fa fa-chevron-down"></span></a>
                  	<ul class="nav child_menu" id="marcasList">
                    	<li> <table><tr><td><div id="allInM" class="btn btn-primary">All ON</div></td><td><div id="allOffM" class="btn btn-primary">All OFF</div></td><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
                    	<c:forEach var="t" items="${marcas}">
                    		
	                    	<li>
	                      		<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  								<input checked data-toggle="toggle"  name="m${t.id}" class="swMarca" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  								<input data-toggle="toggle"  name="m${t.id}" class="swMarca" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
							</li>
                    	</c:forEach>
                    	<li id="visible"> <table><tr><td><div id="loadMore" class="btn btn-primary">Ver mas</div></td><td><div id="showLess" class="btn btn-primary">Ver Menos</div></td></tr></table></li>
                    	<li id="visible2"> <table><tr><td><div id="allInM2" class="btn btn-primary">All ON</div></td><td><div id="allOffM2" class="btn btn-primary">All OFF</div></td><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
                     
                    </ul>
                   </li>
                   <li><a><i class="fa fa-pie-chart"></i> Clases <span class="fa fa-chevron-down"></span></a>
                  	<ul class="nav child_menu" id="clasesList">
                    	<li> <table><tr><td><div id="allInC" class="btn btn-primary">All ON</div></td><td><div id="allOffC" class="btn btn-primary">All OFF</div></td><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
                    	<c:forEach var="t" items="${clases}">
                    		
	                    	<li>
	                      		<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  								<input checked data-toggle="toggle"  name="c${t.id}" class="swClase" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  								<input data-toggle="toggle"  name="c${t.id}" class="swClase" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
							</li>
                    	</c:forEach>
                    </ul>
                   </li>
                   <li><a><i class="fa fa-user"></i> Usuarios <span class="fa fa-chevron-down"></span></a>
                  	<ul class="nav child_menu" id="usuariosList">
                    	<li> <table><tr><td><div id="allInO" class="btn btn-primary">All ON</div></td><td><div id="allOffO" class="btn btn-primary">All OFF</div></td><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
                    	<c:forEach var="t" items="${usuarios}">
                    		
	                    	<li>
	                      		<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  								<input checked data-toggle="toggle"  name="u${t.id}" class="swUsuario" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  								<input data-toggle="toggle"  name="u${t.id}" class="swUsuario" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
							</li>
                    	</c:forEach>
                    	
                    	<li id="visible2"> <table><tr><td><div id="allInU2" class="btn btn-primary">All ON</div></td><td><div id="allOffU2" class="btn btn-primary">All OFF</div></td><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
                     
                    </ul>
                   </li>
                   <li><a><i class="fa fa-cubes"></i> Modulos <span class="fa fa-chevron-down"></span></a>
                  	<ul class="nav child_menu" id="modulosList">
                    	<li> <table><tr><td><div id="allInMO" class="btn btn-primary">All ON</div></td><td><div id="allOffMO" class="btn btn-primary">All OFF</div></td><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
                    	<c:forEach var="t" items="${modulos}">
                    		
	                    	<li>
	                      		<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  								<input checked data-toggle="toggle"  name="mo${t.id}" class="swModulo" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  								<input data-toggle="toggle"  name="mo${t.id}" class="swModulo" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
							</li>
                    	</c:forEach>
                    	
                     
                    </ul>
                   </li>		
				
					
					
				</ul>
				
				<input type="text" id="fechas" name="fechas" style="height: 1px; width: 1px; visibility: hidden;">
			</form>
		</div>
	</div>
</html>