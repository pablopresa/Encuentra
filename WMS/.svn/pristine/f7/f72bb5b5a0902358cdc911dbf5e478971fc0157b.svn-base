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
<body id="dt_example" style="border-left: 0;" >
		<div class="field" align="center">
			<table>
				<tr><td style="padding: 2px;"><h2 style="color: red">PEDIDO COMPLETO</h2></td></tr>
				
			</table>
			
			 <p style="padding: 2px; text-align: center;"><h2 style="color: red"></h2>Pedido ${paquetePedido}</p>
		     <p style="padding: 2px; text-align: center;"><h2></h2>${paqueteTotal} articulo/s</p>
			
			
			<p style="text-align: center;"><span style="color: #008000;"><strong>Empaquete los articulos</strong></span></p>
			<br>
			<a href="<%=basePath%>/v3/handHeld/ControlPaqueteEcommerce.jsp"><div class="button">Fin</div></a>
		</div>
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






























