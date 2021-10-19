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
<body id="dt_example" style="border-left: 0;" onload=" document.getElementById('barra').focus();">
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/ReprocesarPedidoEcommerceSF.do" style="float: left;">
		<div class="field" align="center">
			<div class="field" align="center">
				<label for="lblArticulo">Barra/Articulo/Pedido</label>
				<input type="text" name="barra" id="barra" />
			</div>
			<br />
			<div class="field" align="center">
				<input type="submit" class="button" value="Verificar" style="width: 191px; "/>
			</div>
			<div class="field" align="center">
				<table style="width: width: 210px;">
					<tr>
						<th style="width: 105px;">Pedido</th>
						<th style="width: 105px;">Imprimir</th>
					</tr>
					<c:forEach var="d" items="${pedidosF}">
					<tr>
						<td style="width: 105px;"><div align="center"><c:out value="${d.descripcion}"></c:out></div></td>
						<td style="width: 105px;">
							<div align="center">
								<a href="<%=basePath%>/EcommerceprintLabelPedido.do?idPedido=${d.id}">
									<img alt="imprimir" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;">
								</a>
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
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






























