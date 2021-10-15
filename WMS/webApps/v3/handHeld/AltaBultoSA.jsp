<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:choose>
	<c:when test="${css=='IE'}">
		<link rel="stylesheet"
			href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
	</c:when>
	<c:otherwise>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
	</c:otherwise>
</c:choose>
</head>
<body>

	<c:if test="${uLogeado!=null}">

		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>



<head>



<c:if test="${menError!=null}">
	<script type="text/javascript">
	
		alert("${menError}");
	</script>

</c:if>


<script type="text/javascript">
	
	function subm(accion) 
	{
		document.getElementById("accion").value = accion;
		switch (accion)
		{
			
			case 0:
				if (confirm("Desea confirmar el alta?"))
				{
					document.forms["contactform"].submit();
				}
			break;
			case 1:
				if (confirm("Desea crear de nuevo el bulto?"))
				{
					document.forms["contactform"].submit();
				}
			break;
			case 2:
				if (confirm("Desea descartar el bulto?"))
				{
					document.forms["contactform"].submit();
				}
			break;
			default:
			break;
		
		
		}
		


	}

	
</script>




</head>
<body onload=" document.getElementById('clonarBultoQty').focus();">
	<c:if test="${uLogeado!=null}">
		<form id="contactform" class="rounded" method="post"
			action="<%=basePath%>/CopiarBultoSA.do" style="float: left;">
			<div class="row">
				<div class="col-100">
					<a href="<%=basePath%>pausarTarea.do?sale=si"><div class="button">Ir a menú</div></a>
				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td colspan="2">Detalle de bulto</td>
						</tr>
						
						<tr>
							<td style="width: 75%">Articulo</td>
							<td style="width: 25%">Cant</td>
						</tr>
						
							<c:forEach var="b" items="${currentBulto.contenidoList}">
							<tr>
								<td style="width: 75%">${b.idArticulo}</td>
								<td style="width: 25%">${b.cantidad}</td>
							</tr>
							</c:forEach>
						
					</table>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td colspan="2"><strong>Leer Et. Tonta</strong></td>
							<td><input type="text" value="" name="DummyLabel"></td>
						</tr>
						<tr>
							<td><strong>Copiar a</strong></td>
							<td><input type="number" min=0 value="0" id="clonarBultoQty" name="clonarBultoQty"></td>
							<td><strong>(1 + cantidad especificada)</strong></td>
						</tr>
					</table>
					<table>
						<tr>
							<td><input type="button" class="button"	onclick="subm(0)" value="Guardar" /></td>
							<td><input type="button" class="button" onclick="subm(1)" value="De nuevo"/></td>
							<td><input type="button" class="button"	onclick="subm(2)" value="Descartar" /></td>
						</tr>
					</table>
				</div>
			</div>
			<input type="text" name="idBulto" id="idBulto"  value="${currentBulto.idBulto}" style="visibility: hidden;">
			<input type="text" name="accion" id="accion"  value="${currentBulto.idBulto}" style="visibility: hidden;">
		</form>
	</c:if>
</body>

		</html>


	</c:if>
</body>
</html>