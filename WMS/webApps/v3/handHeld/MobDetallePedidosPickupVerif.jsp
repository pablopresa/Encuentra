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
<body>

<c:if test="${uLogeado!=null}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


</head>
<body id="dt_example" style="border-left: 0;"  onload="document.getElementById('base').focus();">

<c:if test="${uLogeado!=null}">
			<div class="products-main" align="center" style=" float: left;">
				<div class="container" style="float: left;">
				
				<form class="rounded" role="form" method="post"	action="<%=basePath%>pickupPedidoDetalleVerif.do" style="float: left;">
				<div align="center">
					<p>					
						<strong style="font-size: 16pt; color: navy; font-weight: bold;">Pedido N? <c:out value="${idPedido}"></c:out></strong>
					 </p>
					 <p><strong style="font-size: 14pt;"> <c:out value="Articulos"></c:out></strong></p>
				</div>
				<table id="PedidoDesc">
					<tr>
						<th style="width: 60px;">Pedido</th>
						<th style="width: 20px;">Total</th>
						<th style="width: 10px;">&nbsp</th>
					</tr>
						<c:forEach var="d" items="${detallePedido}">
							<tr class="gradeD">
								<td>
									<p style="font-size: 12pt; color: black;"><c:out value="${d.idArticulo}"></c:out></p>
								</td>
								<td>						
									<p style="font-size: 12pt; color: black;">Cantidad:<c:out value="${d.cantRequerida}"></c:out></p>
								</td>
								<td>
									<a href="<%=basePath%>/pickupRechazarPedido.do?idPedido=${idPedido}&idArticulo=${d.idArticulo}" onclick="return confirm('?No lo encontr?? se cancelara el pedido');"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/cancel.png" border="0" style="width: 20px;"></a>
								</td>
							</tr>
						</c:forEach>
					
				</table> 
					
					<div class="field">
						<input type="submit" name="confirmar" id="confirmar" class="col-100 button" value="Confirmar todos"/>
					</div>
					
					<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>pickupPedidos.do'"   value="Ir a menu"/>
					
						
					
				</form>
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
		
</c:if>	
</body>
</html>