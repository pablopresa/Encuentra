<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-84" data-genuitec-path="/encuentra/webApps/gentelella-master/gentelella-master/production/menu3.jsp">
  
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                
                <form action="<%=basePath%>DarDashVersusFiltrar.do" id="filtros" method="get">
                <ul class="nav side-menu">
                	<li><a><i class="fa fa-home"></i> Tiendas <span class="fa fa-chevron-down"></span></a>
                  	<ul class="nav child_menu">
                    	<li> <table><tr><td><div id="allInT" class="btn btn-primary">All ON</div></td><td><div id="allOffT" class="btn btn-primary">All OFF</div></td></tr></table></li>
                    	<c:forEach var="t" items="${tiendas}">
                    		<li>
	                      		<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  								<input checked data-toggle="toggle"  name="t${t.id}" class="swTienda" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  								<input data-toggle="toggle"  name="t${t.id}" class="swTienda" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
							</li>
                    	</c:forEach>
                    	<li> <table><tr><td><div id="allInTB" class="btn btn-primary">All ON</div></td><td><div id="allOffTB" class="btn btn-primary">All OFF</div></td></tr></table></li>
                     
                    </ul>
                   </li>
                   <li><a><i class="fa fa-university"></i> Grupos de tiendas <span class="fa fa-chevron-down"></span></a>
	                    <ul class="nav child_menu">
	                    	<c:forEach var="t" items="${tiendasG}">
		                    	<li>
		                      		<label class="checkbox-inline">
		  								
									</label>
									<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  									<input checked data-toggle="toggle" name="gt${t.id}" type="checkbox" > <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  									<input data-toggle="toggle" name="gt${t.id}" type="checkbox" > <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
								</li>
	                    	</c:forEach>
	                    	<li> <table><tr><td><input type="submit" value="Filtrar" class="btn btn-success"></td></tr></table></li>
	                   </ul>
					</li>
                  <li><a><i class="fa fa-star"></i> Marcas <span class="fa fa-chevron-down"></span></a>
                  	
                    <ul class="nav child_menu" id="marcasList">
                    	<li> <table><tr><td><div id="allInM" class="btn btn-primary">All ON</div></td><td><div id="allOffM" class="btn btn-primary">All OFF</div></td></tr></table></li>
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
                    	<li id="visible2"> <table><tr><td><div id="allInMB" class="btn btn-primary">All ON</div></td><td><div id="allOffMB" class="btn btn-primary">All OFF</div></td></tr></table></li>
                     
                    </ul>
                   </li>
                   <li><a><i class="fa fa-pie-chart"></i> Clases <span class="fa fa-chevron-down"></span></a>
                  	
                    <ul class="nav child_menu" id="clasesList">
                    	<li> <table><tr><td><div id="allInC" class="btn btn-primary">All ON</div></td><td><div id="allOffC" class="btn btn-primary">All OFF</div></td></tr></table></li>
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
                    	<li id="visibleC"> <table><tr><td><div id="loadMoreC" class="btn btn-primary">Ver mas</div></td><td><div id="showLessC" class="btn btn-primary">Ver Menos</div></td></tr></table></li>
                    	<li id="visibleC2"> <table><tr><td><div id="allInCB" class="btn btn-primary">All ON</div></td><td><div id="allOffCB" class="btn btn-primary">All OFF</div></td></tr></table></li>
                     
                    </ul>
                   </li>	
					<li><a><i class="fa fa-users"></i> Generos <span class="fa fa-chevron-down"></span></a>
                  	
                    <ul class="nav child_menu" id="generosList">
                    	<li> <table><tr><td><div id="allInG" class="btn btn-primary">All ON</div></td><td><div id="allOffG" class="btn btn-primary">All OFF</div></td></tr></table></li>
                    	<c:forEach var="t" items="${generos}">
                    		<li>
	                      		<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  								<input checked data-toggle="toggle"  name="g${t.id}" class="swGenero" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  								<input data-toggle="toggle"  name="g${t.id}" class="swGenero" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
							</li>
                    	</c:forEach>
                    </ul>
                   </li>
					<li><a><i class="fa fa-tag"></i> Tags <span class="fa fa-chevron-down"></span></a>
                  	
                    <ul class="nav child_menu" id="tagsList">
                    	<li> <table><tr><td><div id="allInTA" class="btn btn-primary">All ON</div></td><td><div id="allOffTA" class="btn btn-primary">All OFF</div></td></tr></table></li>
                    	<c:forEach var="t" items="${tags}">
                    		
	                    	<li>
	                      		<label class="checkbox-inline">
	                      			<c:if test="${t.idB==1}">
	  								<input checked data-toggle="toggle"  name="ta${t.descripcion}" class="swTag" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
	  								<c:if test="${t.idB!=1}">
	  								<input data-toggle="toggle"  name="ta${t.descripcion}" class="swTag" type="checkbox"> <c:out value="${t.descripcion}"></c:out>
	  								</c:if>
								</label>
							</li>
                    	</c:forEach>
                    	<li id="visibleTA"> <table><tr><td><div id="loadMoreTA" class="btn btn-primary">Ver mas</div></td><td><div id="showLessTA" class="btn btn-primary">Ver Menos</div></td></tr></table></li>
                    	<li id="visibleTA2"> <table><tr><td><div id="allInTAB" class="btn btn-primary">All ON</div></td><td><div id="allOffTAB" class="btn btn-primary">All OFF</div></td></tr></table></li>
                     
                    </ul>
                   </li>	
					
					<li><a><i class="fa fa-line-chart"></i> Agrupar por...<span class="fa fa-chevron-down"></span></a>
                		<ul class="nav child_menu">
                			<li> <table><tr><td><input type="submit" name="AgruparPor" value="marca" class="btn btn-success"></td></tr></table></li>
                			<li> <table><tr><td><input type="submit" name="AgruparPor" value="clase" class="btn btn-success"></td></tr></table></li>
                			<li> <table><tr><td><input type="submit" name="AgruparPor" value="tienda" class="btn btn-success"></td></tr></table></li>
                			<li> <table><tr><td><input type="submit" name="AgruparPor" value="Sumatoria" class="btn btn-success"></td></tr></table></li>
                			<li> <table><tr><td><input type="submit" name="AgruparPor" value="tags" class="btn btn-success"></td></tr></table></li>
                		</ul>
                	</li>
				</ul>
				
			</form>
		</div>
	</div>
<script>"undefined"==typeof CODE_LIVE&&(!function(e){var t={nonSecure:"50866",secure:"50875"},c={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=c[n]+r[n]+":"+t[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document),CODE_LIVE=!0);</script></html>