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
<!-- Js Jquery se debe importar antes -->
	<script src="<%=basePath%>v3/assets/plugins/jquery/js/jquery.min.js"></script>
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
<style type="text/css">
	.btn-remove{
	
	}
</style>
</head>
<body>

	<c:if test="${uLogeado!=null}">

		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>

<body >
	<c:if test="${uLogeado!=null}">
		<form id="contactform" class="rounded" method="post"
			action="<%=basePath%>/MoveToExpedition.do" style="float: left;">
			<input type="text" name="idOjo" value="sort" style="display: none;">
			<div class="row">
				<div class="col-100">
					<a href="<%=basePath%>/MenuMob.do?sec="><input class="col-100 button" type="button" name="2"  value="Ir a Men?"/></a>

				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td class="text-center" style="width: 65%">Codigo de Barra</td>
						</tr>
						<tr>
							<td class="text-center" style="width: 65%"><input type="text" name="test"
								id="test" onkeypress="onTestChange(event);" /></td>
						</tr>
						<tr>
							<td class="text-center" style="width: 65%">Destino</td>
						</tr>
						<tr>
							<td class="text-center" style="width: 65%"><label ><strong
						id="lblDest"></strong></label></td>
						</tr>
						<tr>
							<td class="text-center" style="width: 65%">Ubicacion</td>
						</tr>
						<tr>
							<td class="text-center" style="width: 65%"><label ><strong
						id="lblUbi"></strong></label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-100">
					<label for="lblCantidad">Contador:&nbsp;<strong	id="lblCantidad"></strong></label>
					<table style=" border-collapse: collapse; border-spacing: 10px; ">
						<thead> 
							<tr>
								<th class="text-center">Articulo</td>
								<th class="text-center">Destino</td>
							</tr>
						</thead>
						
						<tbody id="cuerpo"></tbody>
					</table>
					
					<input style="display: none;" type="text" name="colection" id="colection" value=""/>
					<input class="col-100 button" type="submit" value="INGRESAR"/>
					<button class="col-100 button button-danger" type="button" onclick="resetF()">REINICIAR</button> 
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
	var destinos = [];
	var tracks = [];
	
	
	<c:forEach var="c" items="${ClasificadosExpedicion}">
		articulos.push('${c.descripcion}');
		razones.push('${c.id}');
		ojos.push('${c.idB}');
		destinos.push('${c.descripcionB}')
		<c:if test="${c.idB==400000}">
		tracks.push('SD${c.descripcionC}');
		</c:if>
		<c:if test="${c.idB!=400000}">
		tracks.push('${c.descripcionC}');
		</c:if>
	</c:forEach>
		
	function resetF(){
		codigos = [];
		count = 0;
		simbolo = ' + '
		articulos = [];
		ojos = [];
		razones = [];
		codRaz = [];
		destinos = [];
		tracks = [];
		
		<c:forEach var="c" items="${ClasificadosExpedicion}">
			articulos.push('${c.descripcion}');
			razones.push('${c.id}');
			ojos.push('${c.idB}');
			destinos.push('${c.descripcionB}');
			<c:if test="${c.idB==400000}">
			tracks.push('SD${c.descripcionC}');
			</c:if>
			<c:if test="${c.idB!=400000}">
			tracks.push('${c.descripcionC}');
			</c:if>
		</c:forEach>
			
		document.getElementById("colection").value = "";
		document.getElementById("lblCantidad").innerHTML = "0";	
		document.getElementById("lblDest").innerHTML = "";
		document.getElementById("lblUbi").innerHTML = "";
		$('#cuerpo').remove();
	}	

	function onTestChange(event) {
		var char = event.which || event.keyCode;

		// If the user has pressed enter
		if (char == 13) {
			var cod = document.getElementById("test").value;
			if (cod != "") {
				var indice = tracks.indexOf(cod);
				if(indice==-1){
					try {
						indice = tracks.indexOf(cod.substring(0,17));
					} catch (e) {
						indice = -1;
					}
					
				}
				if(indice!=-1)
				{			
					tracks.splice(indice, 1);	
					
					document.getElementById("test").value = "";
					//codigos.push(cod);
					count ++;	
					
					var arti = articulos[indice];
					articulos.splice(indice, 1);							
					
					var raz = razones[indice];
					razones.splice(indice, 1);
					
					var dest = destinos[indice];
					destinos.splice(indice, 1);
					document.getElementById("lblDest").innerHTML = dest;			
					
					var ojoUbi = ojos[indice];
					ojos.splice(indice, 1);
					document.getElementById("lblUbi").innerHTML = ojoUbi+'E';					
					
					
					codRaz.push({id:parseInt(raz),descripcion:arti,idB:parseInt(ojoUbi)});	
					
					document.getElementById("test").focus();
					document.getElementById("lblCantidad").innerHTML = count;
					//document.getElementById("destino").value = codigos.toString();
					document.getElementById("colection").value = "{collection:"+JSON.stringify(codRaz)+"}";
					
					//$('.odd').closest('tr').remove();   
                    $('#cuerpo').append('<tr><td class="text-center">'+arti+'</td><td class="text-center">'+ojoUbi+'E</td></tr>');
				}
				else
				{				
					document.getElementById("test").value = "";
					var alarma = document.getElementById('xyz');
					alarma.play().catch(function() {
					});
					alarma.addEventListener('ended', showAlert("Este codigo de barras no se reconoce para mover al area de expedicion"));				
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