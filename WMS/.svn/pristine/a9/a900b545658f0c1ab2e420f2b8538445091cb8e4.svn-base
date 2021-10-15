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
<body onload=" document.getElementById('canti').focus();">
	<c:if test="${uLogeado!=null}">
		<form id="contactform" class="rounded" method="post"
			action="<%=basePath%>/AltaBultoSA.do" style="float: left;">
			<div class="row">
				<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.A'"  value="Ir a menu"/>
			</div>
			
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td style="width: 100%">Ojo de origen</td>
							
						</tr>
						<tr>
							<td style="width: 100%"><input type="text" name="idOjoO" id="idOjoO" value="0" required /></td>
							
						</tr>
					</table>
				</div>
			</div>
			
			<div class="row">
				<div class="col-100">
					<table>
						<tr>
							<td style="width: 15%">Cant</td>
							<td style="width: 60%">Barra</td>
							<td style="width: 10%">+-</td>
						</tr>
						<tr>
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
					
					<input type="button" class="col-100 button" onclick="subm(1)" value="Bulto" />												
					<input class="col-100 button" type="button" name="1"  onclick="subm3()"  value="Reiniciar"/>
				</div>
			</div>
			<input type="text" name="AltaBulto" id="AltaBulto"  value="0" style="visibility: hidden;">
		</form>
		<input type="text" name="artValido" id="artValido" style="display: none;">
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


	function Validar(cod,quant){
        	
        		var xmlhttp = new XMLHttpRequest();
			    //xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			    xmlhttp.onreadystatechange = function() {
			        if (xmlhttp.readyState == XMLHttpRequest.DONE ) 
			        {
			           if (xmlhttp.status == 200) 
			           {
			        	  	var salida = JSON.parse(xmlhttp.responseText);
			           		document.getElementById('artValido').value = salida.existe
			           		if(document.getElementById('artValido').value=='true'){
								codigos.push(cod+":"+quant);
								count+=quant;
								
								document.getElementById("lblCantidad").innerHTML = count;
								document.getElementById("destino").value = codigos.toString();
							}
							else{
								alert('No se reconoce el articulo '+cod);
							}
			           }
			           else if (xmlhttp.status == 400) 
			           {
			              alert('There was an error 400');
			           }
			           else 
			           {
			               alert('something else other than 200 was returned');
			           }
			        }
			    };
		    	 xmlhttp.open("POST", "<%=basePath%>/ValidateRealTime.do?codigo="+cod, true);
			     xmlhttp.send();
			     req='';			    			    
        	}
	
	function onTestChange(event) 
	{
	    var char = event.which || event.keyCode;
	
	    // If the user has pressed enter
	    if (char == 13) 
		{
			var cod = document.getElementById("test").value;
			var quant =  TryParseInt(document.getElementById("canti").value,1);
			if(cod!="")
			{
				
				document.getElementById("test").value = "";
				if(simbolo==' + ')
				{
					Validar(cod,quant);						
					
				}
				else
				{
					codigos.push(cod+":"+(quant*-1));
					count-=quant;
				}
				
				document.getElementById("lblCantidad").innerHTML = count;
				document.getElementById("destino").value = codigos.toString();
				event.preventDefault();
			}
	        return false;
	    }
	    else 
		{
	        return true;
	    }
	}



	function subm(bultON) {
		if (confirm("Desea confirmar ?"))
		{
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