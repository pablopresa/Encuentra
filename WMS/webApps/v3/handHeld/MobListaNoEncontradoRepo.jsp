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



<script>
	function myJsFunc()
	{
		window.location='<%=basePath%>cierreTarea.do'
	}
</script>

<script>
	function myJsFuncII(artic, cant)
	{
		
		window.location='<%=basePath%>tareaArtEnOtraUbi.do?art='+artic+'&cantidad='+cant;
	}
</script>
</head>
	<body id="dt_example" style="border-left: 0; width: 210px;"  onload="document.forms['contactform']['cod'].focus()">
		<c:if test="${uLogeado!=null}">
		<div class="products-main" align="center" style=" width: width: 210px; float: left;">
			<div id="page-wrap" style="float: left; width: width: 210px;">
			
				Usuario: &nbsp;<c:out value="${uLogeado.nick}"></c:out><a href="<%=basePath%>/jsp/Mobile/Encuentra/Login.jsp">Cerrar Sesion</a>
				<br/>
				Articulos no encontrados
				<table style="width: width: 210px;">
					
						<tr>
							<th style="width: 50px;">Articulo</th>
							<th style="width: 50px;">Ctd.</th>
							<th style="width: 50px;">Stock.</th>
							<th style="width: 50px;">Ubicacion</th>
							<th style="width: 50px;">Encontrado</th>
						</tr>
					
					
					<c:forEach var="t" items="${noNcontrados}">
						<tr>
							<td style="width: 50px;"><div align="center"><c:out value="${t.idArticulo}"></c:out></div></td>
							<td style="width: 50px;"><div align="center"><c:out value="${t.solicitada}"></c:out></div></td>
							<td style="width: 50px;"><div align="center"><c:out value="${t.sotck}"></c:out></div></td>
							<td style="width: 50px;">
								<div align="center">
									<strong style="font-size: 9pt; color: orange; font-weight: bold;">E<c:out value="${t.estnteria}"></c:out></strong> &nbsp;
									<strong style="font-size: 8pt; color: navy; font-weight: bold;">M<c:out value="${t.modulo}"></c:out></strong> &nbsp;
									<strong style="font-size: 8pt; color: navy; font-weight: bold;">E<c:out value="${t.estnte}"></c:out></strong>
									&nbsp;&nbsp;&nbsp;
									<strong style="font-size: 8pt; color: green; font-weight: bold;"><c:out value="${t.cubi}"></c:out></strong>
								</div>
							</td>
							<td> <a href="<%=basePath%>tareaArtEnOtraUbi.do?art=${t.idArticulo}&cantidad=${t.solicitada}">fuera de Ubica.</a></td>
						</tr>
					</c:forEach>
					
				</table>
				<input type="button" name="1"  onclick="myJsFunc();" class="button" style="width: 200px; margin-top: 3px;" value="Volver al men?"/>								
			</div>
		</div>
		</c:if>
		<a href="<%=basePath%>/jsp/Mobile/Encuentra/ListaArticulosCubi.jsp">Version PC</a>
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
		
		
