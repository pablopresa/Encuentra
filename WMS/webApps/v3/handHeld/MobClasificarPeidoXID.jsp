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
<body id="dt_example" style="border-left: 0;"  onload="document.getElementById('barra').focus();">

<c:if test="${uLogeado!=null}">
		<c:forEach var="d" items="${uLogeado.seguridadUI}">
				<c:if test="${d=='box_cant_clasif_ec'}">
					<c:set var = "puede" scope = "page" value = "1"/>
				</c:if>
			</c:forEach>
			<div class="products-main" align="center" style=" float: left;">
				<div class="container" style="float: left;">
				
				<form class="rounded" role="form" method="post" id="ecform"	action="<%=basePath%>/ClasificarPedidoXID.do?idPedido=${idPedido}" style="float: left;">
				<div align="center">
					<p>					
						<strong style="font-size: 16pt; color: navy; font-weight: bold;">Pedido N? <c:out value="${idPedido}"></c:out></strong>
					 </p>
					 <p><strong style="font-size: 14pt;"> <c:out value="Articulos"></c:out></strong></p>
				</div>
				<table >
					<tr>
						<th class="text-center">Articulos</th>
						<th class="text-center">Cantidad</th>
						<th class="text-center">Procesados</th>
						<th class="text-center">&nbsp</th>
					</tr>
						<c:forEach var="d" items="${detallePedido}">
							<tr class="gradeD">
								<td class="text-center">
									${d.idArticulo}
								</td>
									<c:if test="${d.cant>0}"> 
										<td class="text-center" style="color: red;">						
											${d.cantRequerida}
										</td>
										<td class="text-center" style="color: red;">						
											${d.cantRequerida-d.cant}
										</td>
										<td class="text-center">
										<!-- <a href="<%=basePath%>/pickupRechazarPedido.do?idPedido=${idPedido}" onclick="return confirm('?No lo encontr?? se cancelara el pedido');"><img alt="Iniciar" src="<%=basePath%>imagenes/icons/cancel.png" border="0" style="width: 20px;"></a> -->
										</td>
									</c:if>
									
									<c:if test="${d.cant==0}">  
										<td class="text-center" style="color: green;">
										${d.cantRequerida}
										</td>
										<td class="text-center" style="color: green;">						
											${d.cantRequerida-d.cant}
										</td>
										<td class="text-center">
										<img alt="Iniciar" src="<%=basePath%>imagenes/icons/accept.png" border="0" style="width: 20px;">
										</td>
									</c:if>
							</tr>
						</c:forEach>
					
				</table> 
					<br>
					<div class="col-100">
						<c:if test="${puede==1}">
							<div class="field">Cantidad</div>
							<input type="number" name="qty" id="box_cant_clasif_ec" value="1" min="1"/>
						</c:if>						
						<div class="field">Confirme leyendo los articulos</div>
						<input type="text" name="barra" id="barra" onchange="document.getElementById('ecform').submit();" />
					</div>
					
					<div class="col-100">
						<a href="<%=basePath%>/MenuMob.do?sec=M.C"><input class="col-100 button" type="button" name="2"  value="Ir a Men?"/></a>
					</div>
						
					
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