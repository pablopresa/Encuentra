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

	function nextel(event) {
		if (event == 13) {
			document.getElementById("test").focus();

		}
	}
</script>





</head>
<body onload=" document.getElementById('bul').focus();">
	<c:if test="${uLogeado!=null}">
		<form id="contactform" class="rounded" method="post"
			action="<%=basePath%>/MobRecepcion.do" style="float: left;">
			<div class="row">
				<div class="col-100">
					<a href="<%=basePath%>pausarTarea.do?sale=si"><div class="button">Ir a menú</div></a>

				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td>Codigo de Ubicacion</td>
						</tr>
						<tr>
						<c:if test="${uLogeado.idEmpresa==2}">
							<td><input readonly="readonly" type="text" name="idOjo" id="idOjo"  value="0"></td>
						</c:if>
						<c:if test="${uLogeado.idEmpresa!=2}">
							<td><input type="text" name="idOjo" id="idOjo"  value="0"></td>
						</c:if>
						</tr>
					</table>
					<table>
						<tr>
							<td style="width: 15%">Bul.</td>
							<td style="width: 15%">Cant</td>
							<td style="width: 60%">Barra</td>
							<td style="width: 10%">+-</td>
						</tr>
						<tr>
							<td style="width: 15%"><input type="number" min="1" name="bul" id="bul" value=1 style="width: 45px;" /></td>
							<td style="width: 15%"><input type="number" min="1" name="canti" id="canti" value=1 style="width: 45px;"/></td>
							<td style="width: 60%"><input type="text" name="test" id="test" onkeypress="onTestChange(event);" /></td>
							<td style="width: 10%"><div style="text-align: center;" id="sim" onclick="ChangeSim(); return false;">+</div></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<label for="lblCantidad">Contador: ${cantRecepSaved}&nbsp;<strong
						id="lblCantidad"></strong></label>
					<textarea name="articulos" id="destino" readonly="readonly"></textarea>
					<table>
						<tr>
							<td><input type="button" class="button"	onclick="subm(0)" value="Confirmar" /></td>
							<td><input type="button" class="button" onclick="subm3()" value="Reiniciar"/></td>
							<td><input type="button" class="button"	onclick="subm(1)" value="Bulto" /></td>
						</tr>
					</table>
				</div>
			</div>
			<input type="text" name="AltaBulto" id="AltaBulto"  value="0" style="visibility: hidden;">
		</form>
	</c:if>
	<audio id="xyz" src="<%=basePath%>audio/errors.mp3" preload="auto"></audio>
</body>

		</html>


	</c:if>
</body>

<script type="text/javascript">
	var codigos = [];
	var count = 0;
	var simbolo = ' + '
	var barrasBarras = [];
	var barrasArticulos = [];
	
	<c:forEach var="ab" items="${barrasRecepcionables}">
		barrasArticulos.push('${ab.id}');
		barrasBarras.push('${ab.descripcion}');
	</c:forEach>

	function TryParseInt(str, defaultValue) {
		var retValue = defaultValue;
		if (str !== null) {
			str = str.replace(" ", "")
			if (str.length > 0) {
				if (!isNaN(str)) {
					retValue = parseInt(str);
				}
			}
		}
		return retValue;
	}


	function onTestChange(event) 
	{
		var char = event.which || event.keyCode;
		// If the user has pressed enter
		if (char == 13) 
		{
			var cod = document.getElementById("test").value;
			
			
			var articuloDecodificado = '';
			if (cod != "") 
			{
				var indice = barrasBarras.indexOf(cod);
				
				if(indice!=-1)
				{
					articuloDecodificado = barrasArticulos[indice];//busco el cod de barra
					
					
					var quant = TryParseInt(document.getElementById("canti").value, 1);
					var bultos = TryParseInt(document.getElementById("bul").value, 1);
					
					document.getElementById("test").value = "";
					if (simbolo == ' + ') 
					{
						codigos.push(articuloDecodificado + ":" + quant*bultos);
						count += quant*bultos;
					} 
					else 
					{
						codigos.push(articuloDecodificado + ":" + (quant*bultos * -1));
						count -= quant*bultos;
					}
		
					document.getElementById("lblCantidad").innerHTML = count;
					document.getElementById("destino").value = codigos.toString();
					event.preventDefault();
					return false;
					
				}
				else
				{				
					document.getElementById("test").value = "";
					var alarma = document.getElementById('xyz');
					alarma.play().catch(function() {
					});
					alarma.addEventListener('ended', showAlert("Este codigo de barras no se reconoce para ningun articulo de los que debe recepcionar"));
					event.preventDefault()
					return false;				
				}
			}				
			else
			{				
				var alarma = document.getElementById('xyz');
				alarma.play().catch(function() {
				});
				alarma.addEventListener('ended', showAlert("Ingrese un codigo de barras"));
				event.preventDefault()
				return false;					
			}	
		}
		else 
		{
			return true;
		}
	}




	function subm(bultON) {
		if (confirm("Desea confirmar ?"))
		{
			var cod = document.getElementById("test").value;
			
			
			var articuloDecodificado = '';
			if (cod != "") 
			{
				var indice = barrasBarras.indexOf(cod);
				
				if(indice!=-1)
				{
					articuloDecodificado = barrasArticulos[indice];//busco el cod de barra
					
					
					var quant = TryParseInt(document.getElementById("canti").value, 1);
					var bultos = TryParseInt(document.getElementById("bul").value, 1);
					
					document.getElementById("test").value = "";
					if (simbolo == ' + ') 
					{
						codigos.push(articuloDecodificado + ":" + quant*bultos);
						count += quant*bultos;
					} 
					else 
					{
						codigos.push(articuloDecodificado + ":" + (quant*bultos * -1));
						count -= quant*bultos;
					}
		
					document.getElementById("lblCantidad").innerHTML = count;
					document.getElementById("destino").value = codigos.toString();
					event.preventDefault();
					
					
				}
				else
				{				
					document.getElementById("test").value = "";
					var alarma = document.getElementById('xyz');
					alarma.play().catch(function() {
					});
					alarma.addEventListener('ended', showAlert("Este codigo de barras no se reconoce para ningun articulo de los que debe recepcionar"));
					event.preventDefault()
					return false;				
				}
			}				
			
			
			document.getElementById("AltaBulto").value = bultON;
			document.forms["contactform"].submit();
		}


	}

	function subm3() {
		if (confirm("Desea Reiniciar  (" + count + ")?")) {
			codigos = [];
			count = 0;
			simbolo = ' + '
			document.getElementById("lblCantidad").innerHTML = count;
			document.getElementById("destino").value = codigos.toString();
			var color = '#d3d3ab';
			document.getElementById("sim").innerHTML = simbolo;
			document.getElementById("sim").style.backgroundColor = color;
		}
	}

	function ChangeSim() {
		var color;
		if (simbolo == ' + ') {
			simbolo = ' - ';
			color = '#ff6666';
		} else {
			simbolo = ' + ';
			color = '#d3d3ab';
		}

		document.getElementById("sim").innerHTML = simbolo;
		document.getElementById("sim").style.backgroundColor = color;

	}
</script>


</html>