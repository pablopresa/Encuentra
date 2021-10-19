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
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/MoveToExpedition.do" style="float: left;">
		<div class="field" align="center">			
			<table style="padding: 2px;">
				<tr>
					<th style="text-align: center;">DESTINOS</td>
					<th style="text-align: center;">PAQUETES</td>
					<th style="text-align: center;">ACCION</td>
				</tr>				
				<c:forEach var="d" items="${listaDestinosExpedicion}">
					<tr>
						<td style="text-align: center;">${d.descripcion}</td>
						<td style="text-align: center;">${d.id}</td>
						<td style="text-align: center;"><a href="<%=basePath%>/MoveToExpedition.do?depo=${d.descripcionB}">
									<img alt="Despachar" src="<%=basePath%>imagenes/icons/box.png" border="0" style="width: 20px;">
							</a>
						</td>
					</tr>
				</c:forEach>			
			</table>
			<a href="<%=basePath%>pausarTarea.do?sale=si">
				<input class="col-100 button" type="button" value="Ir a menú"/>
			</a>
			
			
			
			
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
	<c:remove var="menError" scope="session" />
</body>
</html>






























