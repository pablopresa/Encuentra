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
				<table style="width: width: 210px;">
					
						<tr>
							<th style="width: 50px;">Picking</th>
							<th style="width: 50px;">Destino/s</th>
							<th style="width: 50px;">So</th>
							<th style="width: 50px;">En</th>
							<th style="width: 50px;">Ve</th>
							<th style="width: 50px;">&nbsp;</th>
						</tr>
					
					
					<c:forEach var="p" items="${pickingsS}">
						<c:if test="${p.encontrada!=p.verificada && p.solicitada!=p.verificada}">
						<tr>
							<td><div align="center"><c:out value="${p.id}"></c:out></div></td>
							<td>
								<div align="center">
									<c:forEach var="d" items="${p.destinos}">
										<c:out value="${d.descripcion}"></c:out><br/>
									</c:forEach>
								</div>
							</td>
							<td><div align="center"><c:out value="${p.solicitada}"></c:out></div></td>
							<td><div align="center"><c:out value="${p.encontrada}"></c:out></div></td>
							<td><div align="center"><c:out value="${p.verificada}"></c:out></div></td>
							<td>
							<c:if test="${!p.clasificar}">
								<a href="<%=basePath%>/DarPickingVC.do?idPick=${p.id}&accion=verifica"><img alt="verifica" src="<%=basePath%>imagenes/icons/arrow_right.png" border="0" style="width: 20px;"></a>
							</c:if>
							<c:if test="${p.clasificar}">
								<a href="<%=basePath%>/DarPickingVC.do?idPick=${p.id}&accion=clasifica"><img alt="clasifica" src="<%=basePath%>imagenes/icons/arrow_out.png" border="0" style="width: 20px;"></a>
							</c:if>
							</td>
						</tr>
						</c:if>
					</c:forEach>
					
				</table>
				<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.B'"  value="Ir a menu"/>	 	
						
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
	<c:remove var="menError" scope="session" />
	</body>
</html>	
		
		
