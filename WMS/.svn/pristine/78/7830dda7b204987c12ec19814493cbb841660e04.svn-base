<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:choose>
  <c:when test="${css=='IE'}">
    <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
  </c:when>
  <c:otherwise>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
  </c:otherwise>
</c:choose>




</head>
	<body id="dt_example" style="border-left: 0; width: 210px;"  onload="document.forms['contactform']['cod'].focus()">
		<c:if test="${uLogeado!=null}">
		<div class="products-main" align="center" style=" width: width: 210px; float: left;">
			<div id="page-wrap" style="float: left; width: width: 210px;">
			
				Usuario: &nbsp;<c:out value="${uLogeado.nick}"></c:out><a href="<%=basePath%>/jsp/Mobile/Encuentra/Login.jsp">Cerrar Sesion</a>
				<form id="contactform"  name="contactform"  class="rounded" method="post" action="<%=basePath%>/EncuentraBuscarAporCod.do" style="float: left; width: 210px;">
					<h3>Scanee el articulo</h3>
					<div class="field">
						<label for="cod">Codigo:</label>
					  	<input type="text" class="input" name="busqueda" id="cod" value="${arti}"/>
						
					</div>
						
					<input type="submit" name="Submit"  class="button" value="Cargar" />
				</form>
				<table style="width: width: 410px;">
					
						<tr>
							<th style="width: 150px;">Codigo de Ub.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th style="width: 50px;">Articulo</th>
							<th style="width: 50px;">Descripcion</th>
							<th style="width: 50px;">Cantidad</th>
						</tr>
					
					
					<c:forEach var="d" items="${articulos}">
						<tr>
							<td style="width: 150px;">
								<div align="center">
									<c:out value="${d.cubi}"></c:out>
									Est.&nbsp;<c:out value="${d.estnteria}"></c:out>
									Mod.&nbsp;<c:out value="${d.modulo}"></c:out>
									E.&nbsp;<c:out value="${d.estnte}"></c:out>
								</div>
							</td>
							<td style="width: 50px;"><div align="center"><c:out value="${d.idArticulo}"></c:out></div></td>
							<td style="width: 50px;"><div align="center"><c:out value="${d.descripcion}"></c:out></div></td>
							<td style="width: 50px;"><div align="center"><c:out value="${d.solicitada}"></c:out></div></td>
							
						</tr>
					</c:forEach>
					
				</table>
			</div>
		</div>
		</c:if>
		<a href="<%=basePath%>/jsp/Mobile/Encuentra/ListaArticulosCubi.jsp">Version PC</a>
	</body>
</html>	
		
		
