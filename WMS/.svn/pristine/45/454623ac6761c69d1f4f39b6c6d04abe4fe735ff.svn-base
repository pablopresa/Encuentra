<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<body id="dt_example" style="border-left: 0;" onload=" document.getElementById('CUBI').focus();">
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/VerifReqEcommerceADDtoOjo.do" style="float: left;">
		<div class="field" align="center">
			<table style="padding: 2px;">
				<tr><td class="text-center"><h2 style="text-align: center;"><span style="color: #ff0000;"><strong>PEDIDO COMPLEATADO SIN ETIQUETA</strong></span></h2></td></tr>
				<tr><td class="text-center" style="color: red;"><h2>Nro.: ${pedido}</h2></td></tr>
				<tr><td class="text-center"><h2>${cliente}</h2></td></tr>
				<tr><td class="text-center"><h2>${cantidad} Articulo/s</h2></td></tr>
			</table>
			
			<!-- Ingrese Cód. Ubicación: 
			<div class="field" align="center">
				<input type="text" name="CUBI" id="CUBI" />
			</div>
			<div style="width: 1px; height: 1px; visibility: hidden;"><input style="width: 1px; height: 1px; visibility: hidden;" type="text" name="idPedido" id="idPedido" value="${pedido}"/></div>
			<div style="width: 1px; height: 1px; visibility: hidden;"><input style="width: 1px; height: 1px; visibility: hidden;" type="text" name="idArticulo" id="idArticulo" value="${articuloV}"/></div>
			<br />
			<div class="field" align="center">
				<input type="submit" class="button" value="Asignar" style="width: 191px; "/>
			</div>-->
		</div>
		<c:if test="${cantidad>1}">
			<div class="field" align="center">
				<table>
				<tr><td colspan="2" class="text-center"><h2 style="color: red">ATENCION, hay mas Articulos</h2></td></tr>
				<tr><td colspan="2" class="text-center"><h2>PEDIDO EN OJOS:</h2> </td></tr>
					<c:forEach var="a" items="${mesa}">
						<tr><td class="text-center">${a.descripcion}</td><td class="text-center">${a.id}</td><td class="text-center">${a.descripcionB}</td></tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
		
		<div class="col-100">
			<a href="<%=basePath%>/v3/handHeld/MobVerifReqEcommerce.jsp"><input class="col-100 button" type="button" name="2" value="Volver"></a>
		</div>
	</form>
	<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
	<c:if test="${menError!=null}">
		<script type="text/javascript"> 
			var alarma = document.getElementById('xyz');
			alarma.play().catch(function() {
		    document.getElementById('xyz').play();
			});
			alarma.addEventListener('ended', showAlert);

			function showAlert() {
			 alert('${menError}');
			}			
			
		</script>	
	</c:if>
</body>
</html>