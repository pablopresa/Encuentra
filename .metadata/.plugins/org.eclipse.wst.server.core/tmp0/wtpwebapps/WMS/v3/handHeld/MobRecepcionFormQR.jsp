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
<script type="text/javascript">
	
	function loadPage() 
	{
		document.getElementById('lblCantidad').innerHTML = ("${cantidad}");
		document.getElementById('codigo').focus();
	}
	
	function handleEnter(field, event) {
		var keyCode = event.keyCode ? event.keyCode : event.which ? event.which
				: event.charCode;
		if (keyCode == 13) {
			document.contactform.accion.value = 1;
			document.contactform.recepciones.value = field.value;
			document.forms["contactform"].submit();
		}
		}
		function subm() {
		document.contactform.accion.value = 4;
		document.forms["contactform"].submit();
		}
		function subm2() {
		document.contactform.accion.value = 2;
		document.forms["contactform"].submit();
		}
	
		function subm3() {
		document.contactform.accion.value = 3;
		document.forms["contactform"].submit();
		}
	
	
	

</script>
</head>
<body id="dt_example" style="border-left: 0;" onload="loadPage()">
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/confirmarRecepcionQR.do" style="float: left;">
		<div class="field" align="center">
		
			<div class="field" align="center"></div>
			<div class="field" align="center">
				 <input type="text" class="input"
					name="codigo" id="codigo"
					onkeypress="return handleEnter(this, event)" style="height: 110px; width: 200px; "/>
			</div>
			<div class="field" align="center">
				<label for="lblCantidad">Contador: &nbsp;<strong
					id="lblCantidad"></strong></label>
			</div>
			

			<div class="field" align="center">
				<input type="button" class="button" onclick="subm()"
					value="Confirmar Recepción"  style="width: 191px; "/>
			</div>
			<div class="field" align="center">
				
		
				<div class="field" align="center">
				<input type="button" class="button" onclick="subm2()"
					value="Pausar Recepción" style="width: 191px; "/>
				</div>
			<div class="field" align="center">
				

			</div>
			</div>
		
			<input type="hidden" name="recepciones" id="recepciones" value="">
			<input type="hidden" name="accion" id="accion" value="">
	</div>
	</form>
</body>
</html>