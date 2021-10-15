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
				Usuario: &nbsp;<c:out value="${uLogeado.nick}"></c:out><a href="<%=basePath%>/v3/handHeld/login.jsp">Cerrar Sesion</a>
				<table>
					
						<tr>
							<th style="width: 50px;">Pedido</th>
							<th style="width: 50px;">Fecha</th>
							<th style="width: 50px;">Estado<br/>Fenicio</th>
							<th style="width: 50px;">Cantidad Pedido<br/>Cantidad Procesada</th>
							<th style="width: 50px;">Ojo</th>
							<th style="width: 50px;">Acción</th>
						</tr>
					
					 <c:set var = "estadoF" scope = "page" value = "Preparando pedido"/>
					<c:forEach var="p" items="${PedidosVer}">
						<tr  <c:if test="${p.estadoEcommerce!=estadoF}">style="background-color: #f5b3b6"</c:if>>
							<td style="width: 50px;"><div align="center">${p.idPedido}</div></td>
							<td style="width: 50px;"><div align="center">${p.fecha}</div></td>
							<td style="width: 50px;"><div align="center">${p.estadoEcommerce}</div></td>
							<td style="width: 50px;"><div align="center">${p.totalProcesado}&nbsp;De&nbsp;${p.unidadesTotal}</div></td>
							<td style="width: 50px;"><div align="center">${p.ojo}</div></td>
							<td style="width: 50px;"><div align="center">
							<c:if test="${p.estadoEcommerce==estadoF}">
								<a href="<%=basePath%>DeralleVerifVerifEcommerce.do?idPedido=${p.idPedido}&accion=ver">
									<img alt="Iniciar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;">
								</a>
							</c:if>
							<c:if test="${p.estadoEcommerce!=estadoF}">
								<a href="<%=basePath%>DeralleVerifVerifEcommerce.do?idPedido=${p.idPedido}&accion=despachar">
									<img alt="Despachar" src="<%=basePath%>imagenes/icons/cancel.png" border="0" style="width: 20px;">
								</a>
							</c:if>
							</div></td>
							
						</tr>
					</c:forEach>
					
				</table>
				<div style="height: 5px;"></div>
					<a href="<%=basePath%>pausarTarea.do?sale=si">
						<div class="button">Ir a menú</div>		
					</a> 	
						
			</div>
		</div>
		</c:if>
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
		
		
