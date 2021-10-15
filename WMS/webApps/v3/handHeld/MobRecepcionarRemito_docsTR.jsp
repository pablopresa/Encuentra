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
		<div class="rounded">
			<div class="row">
				<div class="col-100">
					<a href="<%=basePath%>pausarTarea.do?sale=si"><div class="button">Ir a menú</div></a>
				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<form method="post"action="<%=basePath%>/selMovStockRecepTR.do">
						<table>
							<tr>
								<td colspan="3"><h2> Registrando transitos a deposito <strong style="color: red"> <c:out value="${depoTR.id}"></c:out> </strong></h2></td>
							</tr>
							<tr>
								<td colspan="3"><a href="<%=basePath%>/v3/handHeld/MobRecepcionListaDocsTR.jsp"><div class="button">Elegir Documentos</div></a></td>
							</tr>
							
							<tr>
								<td style="width: 50%">Ingresar numero</td>
								<td style="width: 40%"><input type="number" name="nroDoc"></td>
								<td style="width: 10%"><input type="submit" value="+"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-100">
					<table>
						<thead>
							<tr>
								<th colspan="4"><strong>Lista de documentos</strong></th>
							</tr>
						    <tr>
	                    		<th>Orgn.</th>
	                            <th>Documento</th>
	                            <th>Ctd.</th>
								<th>Elim.</th>
							</tr>
						</thead>
	                    <tbody>
	                    <c:forEach var="d" items="${remitosTRIn}">
							<tr>						
								<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.idOrigen}</td>
								<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.numeroDoc}</td>
								<td style="padding: 1px; vertical-align: middle; text-align: center;">${d.unidades}</td>
								<td style="padding: 1px; vertical-align: middle; text-align: center;">
								<a href="#" onClick="if(confirm('Seguro que desea este Doc de la lista?'))
															{
																window.location='<%=basePath%>delMovStockRecepTR.do?nroDoc=${d.idOrigen}_${d.numeroDoc}'
																
																
															}
															else {
																alert('Sin Cambios')
																}">
								
								
								<img alt="Eliminar" src="<%=basePath%>imagenes/icons/cross.png" border="0" style="width: 20px;"></a> </td>
							</tr>
						</c:forEach>
	                   	</tbody>
					</table>
				</div>
			</div>
			<div class="row">
					<a href="<%=basePath%>ConfirmarRecepcionTR.do"><input class="col-100 button button-warning" type="button" value="Confirmar sin verificar"/></a>
					<a href="<%=basePath%>MenuMob.do?sec=M.D"> <input class="col-100 button button-danger" type="button" value="Cancelar"/></a>
			</div>
			
			
		</div>	
	</c:if>
	
</body>

		</html>


	</c:if>
</body>
</html>