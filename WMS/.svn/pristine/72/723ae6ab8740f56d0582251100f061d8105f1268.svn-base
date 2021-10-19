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
				
				
				<form id="contactform" class="rounded"  action="<%=basePath%>/EmpaquetarPedido.do" style="float: left;">
				<div align="center">
					<p>					
						<strong style="font-size: 13pt; color: navy; font-weight: bold;">Pedido Nº <c:out value="${paquetePedido}"></c:out></strong>
					 </p>
					 <p><strong style="font-size: 10pt;"> <c:out value="Articulos"></c:out></strong></p>
				</div>
				<table>
					<tr>
						<td>
							
							<c:forEach var="p" items="${paquetes}">
								<c:if test="${p.id==1}">
									<p style="font-size: 8pt; color: red;"><c:out value="${p.descripcion}"></c:out></p>
								</c:if>
								<c:if test="${p.id==0}">
									<p style="font-size: 8pt; color: grey;"><c:out value="${p.descripcion}"></c:out></p>
								</c:if>
							</c:forEach>
						</td>
						<td>
							
						</td>
					</tr>
				</table> 
					
									
					<div class="field">
						<input type="text" class="input" name="base" id="base" value="${articulo.codBase}"/>
					</div>
					
					<div class="field" align="center">
						<p> Cantidad <c:out value="${paqueteParcial}"></c:out></font> de <c:out value="${paqueteTotal}"></c:out></font></p>
						
						
						<div style="height: 5px;"></div>
						
						
					</div>
						
					 
					<a href="<%=basePath%>pausarTarea.do?sale=si">
						<div class="button">
							Volver
						</div>
						
					</a>
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