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
							<th style="width: 50px;">Articulo</th>
							<th style="width: 50px;">Depo.</th>
							<th style="width: 50px;">Ojo</th>
							<th style="width: 50px;">Cantidades</th>
						</tr>
					
					 
					<c:forEach var="p" items="${PedidosVerDetalle}">
						<tr  <c:if test="${p.cantidadR!=p.cantidadPro}">style="background-color: #f5b3b6"</c:if>>
							<td style="width: 50px;"><div align="center">${p.idArticulo}</div></td>
							<td style="width: 50px;"><div align="center">${p.deposito}</div></td>
							<td style="width: 50px;"><div align="center">${p.idOjo}</div></td>
							<td style="width: 50px;"><div align="center">${p.cantidadPro}&nbsp;De&nbsp;${p.cantidadR}</div></td>
						</tr>
					</c:forEach>
						<tr>
							<c:if test="${accion=='despachar'}">
								<td colspan="4" style="width: 50px;"><a href="<%=basePath%>/despacharPedidoComoFenicio.do?idPedido=${pedDespachar}"><div class="button" style="background-color: #f5b3b6" align="center">liberar del clasificador (despachar)</div></a></td>
							</c:if>
							<c:if test="${accion=='ver'}">
								<td colspan="4" style="width: 50px;"><a href="<%=basePath%>/v3/handHeld/MobVerifVerifEcommerce.jsp"> <div class="button" align="center">Ok, volver</div></a></td>
							</c:if>
							<c:if test="${accion=='volver'}">
								<td colspan="4" style="width: 50px;"><a href="<%=basePath%>/v3/handHeld/MobVerifReqEcommerceListado.jsp"> <div class="button" align="center">Ok, volver</div></a></td>
							</c:if>
						</tr>
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
		
		
