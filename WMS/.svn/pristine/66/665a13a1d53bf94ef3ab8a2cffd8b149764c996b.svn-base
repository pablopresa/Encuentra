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
		<c:if test="${menError!=null}">
			<script type="text/javascript">			
				alert("${menError}");
			</script>

		</c:if>
		<div class="products-main" align="center" style=" width: width: 210px; float: left;">
			<div id="page-wrap" style="float: left; width: width: 210px;">
			
				
				
				<h3>Articulos Movidos Correctamente <strong><c:out value="${cantArticulosOk}"></c:out> </strong></h3>
				<table style="width: width: 210px;">
					
						<tr>
							<th style="width: 50px;">Articulo</th>
							<th style="width: 50px;">Cantidad</th>
							<th style="width: 50px;">Ubicacion</th>
						</tr>
					
					
					<c:forEach var="m" items="${movidos}">
						<tr>
							<td style="width: 50px;"><div align="center"><c:out value="${m.descripcion}"></c:out></div></td>
							<td style="width: 50px;"><div align="center"><c:out value="${m.id}"></c:out></div></td>
							<td style="width: 50px;"><div align="center"><c:out value="${m.descripcionB}"></c:out></div></td>
							
						</tr>
					</c:forEach>
					
				</table>
				
				
				<c:if test="${cantNoBajados >= 1}">
					<h3>Articulos reservados que no pudieron moverse <strong><c:out value="${cantArticulosOk}"></c:out> </strong></h3>
					<table style="width: width: 210px;">
						
							<tr>
								<th style="width: 50px;">Articulo</th>
								<th style="width: 50px;">Cantidad</th>
								<th style="width: 50px;">Ubicacion</th>
							</tr>
						
						
						<c:forEach var="m" items="${no_movidos}">
							<tr>
								<td style="width: 50px;"><div align="center"><c:out value="${m.descripcion}"></c:out></div></td>
								<td style="width: 50px;"><div align="center"><c:out value="${m.id}"></c:out></div></td>
								<td style="width: 50px;"><div align="center"><c:out value="${m.descripcionB}"></c:out></div></td>
								
							</tr>
						</c:forEach>
						
					</table>
				</c:if>
				
				
				
				
				<c:if test="${cantArticulosNuevos>0}">
					Los siguientes <strong><c:out value="${cantArticulosOk}"></c:out> </strong> Articulos se
					dieron de alta en <strong><c:out value=" ${destino} "></c:out>
					porque no tenian ubicacion asignada
					
					<table style="width: width: 210px;">
					
							<tr>
								<th style="width: 50px;">Articulo</th>
								<th style="width: 50px;">Cantidad</th>
								<th style="width: 50px;">Origen</th>
								<th style="width: 50px;">Destino</th>
							</tr>
						
						
						<c:forEach var="t" items="${ArticulosNuevos}">
							<tr>
								<td style="width: 50px;"><div align="center"><c:out value="${t.articulo}"></c:out></div></td>
								<td style="width: 50px;"><div align="center"><c:out value="${t.cantidad}"></c:out></div></td>
								<td style="width: 50px;"><div align="center">Sin</div></td>
								<td style="width: 50px;"><div align="center"><c:out value="${destino}"></c:out></div></td>
								
							</tr>
						</c:forEach>
						
					</table>  
					  
					  
				</c:if>
				
				<c:if test="${cantArticulosEnOtroLado>0}">
					Los siguientes  Articulos
					No se encontraban en <strong><c:out value=" ${origen} "></c:out>
					<br/>
					Ubicaciones sugeridas:
					
					<table style="width: width: 210px;">
					
							<tr>
								<th style="width: 50px;">Articulo<br/>Ubicacion</th>
								
								<th style="width: 50px;">Ctd.</th>
								<th style="width: 50px;">Chek</th>
							</tr>
						
						<form id="contactform"  name="contactform"  class="rounded" method="post" action="<%=basePath%>/EncuentraBuscarAporCod.do" style="float: left; width: 210px;">
					
							<c:forEach var="t" items="${ArticulosEnOtroLado}">
								<tr>
									<td style="width: 50px;">
										<div align="center">
											<c:out value="${t.articulo}"></c:out><br/> 
											<c:out value="${t.idOjo}"></c:out>&nbsp;|&nbsp;E${t.idSector}&nbsp;|&nbsp;M${t.estante}&nbsp;|&nbsp;E${t.modulo}
										</div>
									</td>
									<td style="width: 50px;"><div align="center"><c:out value="${t.cantidad}"></c:out></div></td>
									
									<td><input type="checkbox" name="${t.idOjo}"/></td>
									
								</tr>
							</c:forEach>
								<tr>
									<td colspan="5" align="right"><input type="submit" name="Submit"  class="button" value="Cargar" /></td>
								</tr>
						
					</form>
						
					</table>  
					  
					  
				</c:if>
				<div style="height: 5px;"></div>
					<a href="<%=basePath%>v3/handHeld/MobCambiarUbicacionForm.jsp"> <input class="col-100 button" type="button" name="1"  value="Ir a menu"/></a>
			</div>
		</div>
		</c:if>
			
						
						
	<!-- 	<a href="<%=basePath%>/jsp/Mobile/Encuentra/ListaArticulosCubi.jsp">Version PC</a>  -->
	</body>
</html>	
		
		
