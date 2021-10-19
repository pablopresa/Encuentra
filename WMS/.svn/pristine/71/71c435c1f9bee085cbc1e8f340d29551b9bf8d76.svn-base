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
<body id="dt_example" style="border-left: 0;" onload="loadPage()">
	<form id="contactform" class="rounded" action="<%=basePath%>/VerificarCantidad.do" style="float: left;">
			
			<!-- 	
			
			<div class="field" align="center">
				<input type="button" class="button" onclick="subm2()"
					value="Pausar Recepción" />
			</div>
			<div class="field" align="center">
				<div class="field" align="center">
					<input type="button" class="button" onclick="subm3()"
						value="Reiniciar Recepción" />
				</div>

			</div>
			 -->
			<div class="field" align="center"></div>
			<div class="field" align="center">
				<label for="codigo">Código</label> <input type="text" class="input" name="codigo" id="codigo" onkeypress="return handleEnter(this, event)" />
			</div>
			<br />
			<div class="field" align="center">
				<label for="lblArticulo">Artículo</label><label id="lblArticulo"></label>
			</div>
			<br />
			<div class="field" align="center">
				<label for="lblCantidad">Contador: &nbsp;<strongid="lblCantidad"></strong></label>
			</div>

			<div class="field" align="center">
				<input type="button" class="button" onclick="subm()" value="Conciliar cantidades" />
			</div>
	</form>
	
	<audio id="xyz" src="<%=basePath%>/audio/error.mp3" preload="auto"></audio>
<script type="text/javascript">
	var codigo = ("${codigos}");
	var articulos = ("${articulos}");
	var arrayString = codigo.split(",");
	var arrayArticulos = articulos.split(",");
	var stringRecepciondados = ("${articulosRecepcionados}");
	var arrayResultante = stringRecepciondados.split(",");

	function loadPage() {
		if (arrayResultante.length == 1) {
			arrayResultante = new Array();
		}
		document.getElementById('lblCantidad').innerHTML = arrayResultante.lengtht;
		document.getElementById('codigo').focus();
	}
	function handleEnter(field, event) {
		var keyCode = event.keyCode ? event.keyCode : event.which ? event.which
				: event.charCode;
		if (keyCode == 13) {
			var i;
			var appear = 0;
			var tam = arrayString.length;
			for (i = 0; i < tam; i++) {
				if (field.value == arrayString[i]) {
					arrayResultante.push(arrayArticulos[i]);
					document.getElementById('lblArticulo').innerHTML = arrayArticulos[i];
					appear = 1;
				}
			}
			if (appear == 1) {
				document.getElementById('contactform').style.backgroundColor = "#00FF00";

			}
			if (appear == 0) {
				document.getElementById('contactform').style.backgroundColor = "#FF0000";
				var alarma = document.getElementById('xyz');
				alarma.play().catch(function() {
				<!--document.getElementById('xyz').play();-->
				});
				alarma.addEventListener('ended', showAlert("El articulo no se encontro en los documentos"));
					
				//alert("el articulo no se encontro en los documentos");

			}

			document.getElementById('lblCantidad').innerHTML = arrayResultante.length;
			document.getElementById('codigo').select();
			document.getElementById('codigo').focus();
			return false;
		}
		return true;
	}
	function subm() {
		var j;
		var stringResultante = "";
		for (j = 0; j < arrayResultante.length; j++) {
			stringResultante = stringResultante + arrayResultante[j] + ",";
		}
		
		// Creamos el formulario auxiliar
			var form = document.createElement( "form" );
			
			// Le añadimos atributos como el name, action y el method
			form.setAttribute( "name", "formulario" );
			form.setAttribute( "id", "formulario" );
			form.setAttribute( "action", "VerificarCantidad.do" );
			form.setAttribute( "method", "post" );
			
			// Creamos un input para enviar el valor
			var input = document.createElement( "input" );
			
			// Le añadimos atributos como el name, type y el value
			input.setAttribute("name", "lista");//son los articulos OJO
			input.setAttribute("type", "hidden");
			input.setAttribute("value", stringResultante );
			
			// Añadimos el input al formulario
			form.appendChild( input );
			
			// Añadimos el formulario al documento
			document.getElementsByTagName("body")[0].appendChild(form);
			
			// Hacemos submit
			//document.formulario.submit();
			
			document.getElementById('formulario').submit();
		
	}
	function subm2() {
		var j;
		var stringResultante = "";
		for (j = 0; j < arrayResultante.length; j++) {
			stringResultante = stringResultante + arrayResultante[j] + ",";
		}
		document.location = '/Arreglos/confirmarRecepcion.do?accion=2&recepciones='
				+ stringResultante;
	}
	function subm3() {
		document.location = '/Arreglos/confirmarRecepcion.do?accion=3';
	}
	
	function showAlert(msj) {
		alert(msj);
	}
	
</script>	
</body>
</html>