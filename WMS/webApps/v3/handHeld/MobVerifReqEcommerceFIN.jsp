<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<body id="dt_example" style="border-left: 0;" >
		<div class="field" align="center">
			<table>
				<tr><td class="text-center"><h2 style="color: red">PEDIDO COMPLETADO</h2></td></tr>
				<tr><td class="text-center"><h2>${cliente}</h2></td></tr>
				<tr><td class="text-center"><h2 style="color: red"></h2>Nro. ${pedido}</td></tr>
				<tr><td class="text-center"><h2></h2>${cantidad} Articulo/s</td></tr>
			</table>
			
			<c:if test="${paramCantEtis}">
				<form action="<%=basePath%>PrintEtiquetasEC.do" id="formEtiquetas">
					<table>
						<tr><td class="text-center" colspan="4"><h2 style="color: red">CANTIDAD DE ETIQUETAS</h2></td></tr>
						<tr>
							<th class="text-center">S</th>
							<th class="text-center">M</th>
							<th class="text-center">L</th>
							<th class="text-center">XL</th>
						</tr>
						<tr>
							<td class="text-center"><input type="number" min="0" name="numEtisS" value="0" style="width: 90%;" id="numEtisS"></td>
							<td class="text-center"><input type="number" min="0" name="numEtisM" value="0" style="width: 90%;" id="numEtisM"></td>
							<td class="text-center"><input type="number" min="0" name="numEtisL" value="0" style="width: 90%;" id="numEtisL"></td>
							<td class="text-center"><input type="number" min="0" name="numEtisXL" value="0" style="width: 90%;" id="numEtisXL"></td>
						</tr>					
					</table>
					<a class="col-100 button" onclick="Verificar()">IMPRIMIR ETIQUETA/S</a>
					<a href="<%=basePath%>/MenuMob.do?sec=M.C"><input class="col-100 button" type="button" value="Volver al menu"></a>
				</form>
			</c:if>
			<c:if test="${!paramCantEtis}">
				<p style="text-align: center;"><span style="color: #008000;"><strong>Revise la Etiqueta impresa</strong></span></p>		
				<div class="col-100">
					<a href="<%=basePath%>/v3/handHeld/MobVerifReqEcommerce.jsp"><input class="col-100 button" type="button" name="2" value="Volver"></a>
				</div>					
			</c:if>
			
		</div>
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
	<script type="text/javascript">

function Verificar(){
	
	
	let inputS = parseInt(document.getElementById("numEtisS").value); 
	let inputM = parseInt(document.getElementById("numEtisM").value); 
	let inputL = parseInt(document.getElementById("numEtisL").value); 
	let inputXL = parseInt(document.getElementById("numEtisXL").value); 
	
	if(inputS <= 0 && inputM <= 0 && inputL <= 0 && inputXL <= 0){
		alert("Se debe de pedir al menos una etiqueta.");
	}else{
		let form = document.getElementById("formEtiquetas");
		form.submit();
	}
		
	
	
}

</script>
</body>
</html>






























