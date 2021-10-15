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

<body >
	<c:if test="${uLogeado!=null}">
		<form id="contactform" class="rounded" method="post"
			action="<%=basePath%>/MoveToExpedition.do" style="float: left;">
			<div class="row">
				<div class="col-100">
					<a href="<%=basePath%>/MenuMob.do?sec=M.E"><input class="col-100 button" type="button" name="2"  value="Ir a Menú"/></a>
				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td>Codigo de Ubicacion</td>
						</tr>
						<tr>
						<td><input type="text" name="idOjo" id="idOjo"  value="${ojoExp}" onkeypress="onTestChangeOjo(event);"></td>
						</tr>
					</table>
					<table>
						<tr>
							<td style="width: 65%">Barra</td>
						</tr>
						<tr>
							<td style="width: 65%"><input type="text" name="test"
								id="test" onkeypress="onTestChange(event);" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<label for="lblCantidad">Contador:&nbsp;<strong
						id="lblCantidad"></strong></label>
					<textarea name="destino" id="destino" readonly="readonly"></textarea>
					
					<input style="display: none;" type="text" name="colection" id="colection" value=""/>
					<input class="col-100 button" type="submit" value="INGRESAR"/>
				</div>
			</div>
			<input type="text" name="depo" value="${depoExp}" style="display: none;">
		</form>
		<audio id="xyz" src="<%=basePath%>audio/errors.mp3" preload="auto"></audio>
	</c:if>
	
</body>

		</html>


	</c:if>


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

 <script type="text/javascript">
       window.onload = function() {
         document.getElementById("test").focus();
       };
 </script>
 
 <script type="text/javascript">
	var codigos = [];
	var count = 0;
	var simbolo = ' + '
	var articulos = [];
	var ojos = [];
	var razones = [];
	var codRaz = [];
	var tracks = [];
	
	<c:forEach var="o" items="${ojosExpedicion}">
		ojos.push('${o.descripcion}');
	</c:forEach>
	
	<c:forEach var="c" items="${ClasificadosExpedicion}">
		articulos.push('${c.descripcion}');
		razones.push('${c.id}');
		ojos.push('${c.idB}');
		tracks.push('${c.descripcionC}');
	</c:forEach>
		

	function onTestChangeOjo(event) {
		
		var char = event.which || event.keyCode;

		// If the user has pressed enter
		if (char == 13) {
			var ojo = document.getElementById("idOjo").value.toUpperCase();
			if (ojo != "") {
				var indice = ojos.indexOf(ojo);
				if(indice!=-1)
				{
					document.getElementById("test").focus();
				}
				else
				{				
					var alarma = document.getElementById('xyz');
					alarma.play().catch(function() {
					});
					alarma.addEventListener('ended', showAlert("Esta ubicacion no se reconoce para mover al area de expedicion seleccionada"));				
				}
				
			}				
			else
			{				
				var alarma = document.getElementById('xyz');
				alarma.play().catch(function() {
				});
				alarma.addEventListener('ended', showAlert("Ingrese una ubicacion"));				
			}			
		 	event.preventDefault();
		  	return false;
	    }
	    else 
		{
	        return true;
	    }
		
		
	}

	function onTestChange(event) {
		var char = event.which || event.keyCode;

		// If the user has pressed enter
		if (char == 13) {
			var cod = document.getElementById("test").value;
			if (cod != "") {
				var indice = tracks.indexOf(cod);
				if(indice!=-1)
				{			
					tracks.splice(indice, 1);
					
					var arti = articulos[indice];
					articulos.splice(indice, 1);									
					
					document.getElementById("test").value = "";
					codigos.push(arti);
					count ++;
					
					var raz = razones[indice];
					razones.splice(indice, 1);

					var ojoUbi = ojos[indice];
					ojos.splice(indice, 1);
					
					codRaz.push({id:parseInt(raz),descripcion:arti,idB:parseInt(ojoUbi)});		
					
					document.getElementById("test").focus();
					document.getElementById("lblCantidad").innerHTML = count;
					document.getElementById("destino").value = codigos.toString();
					document.getElementById("colection").value = "{collection:"+JSON.stringify(codRaz)+"}";
				}
				else
				{				
					document.getElementById("test").value = "";
					var alarma = document.getElementById('xyz');
					alarma.play().catch(function() {
					});
					alarma.addEventListener('ended', showAlert("Este codigo de barras no se reconoce para mover al area de expedicion seleccionada"));				
				}
			}				
			else
			{				
				var alarma = document.getElementById('xyz');
				alarma.play().catch(function() {
				});
				alarma.addEventListener('ended', showAlert("Ingrese un codigo de barras"));				
			}			
			event.preventDefault();
		  	return false;
	    }
	    else 
		{
	        return true;
	    }
		
	}

	function subm3() {
		if (confirm("Desea Reiniciar  (" + count + ")?")) {
			codigos = [];
			count = 0;
			document.getElementById("lblCantidad").innerHTML = count;
			document.getElementById("destino").value = codigos.toString();
		}
	}
	
	function showAlert(msj) {
		alert(msj);
	}
	
	 function traerDatos(){
       	try{
	           jQuery.ajax({
	                url:'<%=basePath%>ValidateSession.do',
	                dataType:'text'
	             }).then(function(data) {
	          
	          		console.log('session viva?');
	         		console.log(data);
	         			         		
	         		if(data==null){
	         			console.log('murio la session');
	         			location.assign('<%=basePath%>v3/handHeld/login.jsp');
	         		}
	             });
	         }
      
      	catch(error){
	         	console.log('murio el server');
	         	location.assign('<%=basePath%>v3/handHeld/login.jsp');
     	 }
      }
      $(function() 
    {
        setInterval(traerDatos, 60000);
    });
      
</script>
 
 
</html>