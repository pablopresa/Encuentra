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
				Usuario: &nbsp;<c:out value="${uLogeado.nick}"></c:out><a href="<%=basePath%>/jsp/Mobile/Encuentra/Login.jsp">Cerrar Sesion</a>
				<table style="width: width: 210px;">
					
						<tr>
							<th style="width: 50%;">Destino</th>
							<th style="width: 50%;">Preparar</th>
						</tr>
					
					
					<c:forEach var="d" items="${depoEnvio}">
						<tr>
							<td style="width: 50%;"><div align="center"><c:out value="${d.descripcion}"></c:out></div></td>
							<td style="width: 50%;"><div align="center"><a href="<%=basePath%>//initEnvioEncuentraSelDepo.do?idDepo=${d.id}"><img alt="Enviar" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a></div></td>
						
						</tr>
					</c:forEach>
					
				</table>
				<div style="height: 5px;"></div>
					<a href="<%=basePath%>pausarTarea.do?sale=si">
						<div class="button">Ir a men?</div>		
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
		
		
