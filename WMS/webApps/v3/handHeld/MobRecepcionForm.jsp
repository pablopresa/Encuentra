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
	var codigos = [];
	var count = 0;
	var simbolo = ' + '
	
	function TryParseInt(str,defaultValue) 
	{
		 var retValue = defaultValue;
		 if(str !== null) 
		 {
		 	str = str.replace(" ", "")
			 if(str.length > 0) {
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
			var quant =  TryParseInt(document.getElementById("canti").value,1);
			if(cod!="")
			{
				
				document.getElementById("test").value = "";
				if(simbolo==' + ')
				{
					codigos.push(cod+":"+quant);
					count+=quant;
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
	
	

	
	function subm(event,type) 
	{
		document.getElementById("typeSave").value = type
		
		if(type==0)
		{
			if(confirm("Desea confirmar las cantidades de la recepci?n ("+count+")?"))
			{
				document.forms["contactform"].submit();
			}	
		}
		else
		{
			document.forms["contactform"].submit();
		}
		
	}
	
	function subm3() 
	{
		if(confirm("Desea Reiniciar las cantidades de la recepci?n ("+count+")?"))
		{
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
	
	function ChangeSim() 
	{
		var color;
	    if(simbolo==' + ')
		{
			simbolo = ' - ';
			 color = '#ff6666';
		}
		else
		{
			simbolo = ' + ';
			color ='#d3d3ab';
		}
		
		document.getElementById("sim").innerHTML = simbolo;
		document.getElementById("sim").style.backgroundColor = color;
		
	}
	
	
</script>
</head>
<body id="dt_example" style="border-left: 0;" >
	<form id="contactform" name="contactform" class="rounded"	method="post" action="<%=basePath%>/confirmarRecepcion.do" style="float: left;">
		<div class="field" align="center">
			<div class="field" align="center">
				<label for="lblArticulo">Cantidad</label>
				<input type="canti" name="canti" id="canti"  value=1 style="width: 33px; margin-top: 5px;"/>
			</div>
			<div class="field" align="center">
				<label for="lblArticulo">Barra</label>
				<input type="text" name="test" id="test"  onkeypress="onTestChange(event);"/>
				<button id="sim" onclick="ChangeSim(); return false;"> + </button>
			</div>
			<br />
			<div class="field" align="center">
				<label for="lblCantidad">Contador: ${cantRecepSaved}&nbsp;<strong id="lblCantidad"></strong></label>
			</div>
			<br/>
			<div class="field" align="center">
				<table style="width: 195px;">
					<tr>
						<td>
							<input type="button" class="button" onclick="subm(event,1)" value="guardar" />
						</td>
						<td>
							<input type="button" class="button" onclick="subm(event,0)" value="Confirmar" />
						</td>
					</tr>
				</table>
			</div>
				
				<div class="field" align="center">
					<input type="button" class="button" onclick="subm3()"
						value="Reiniciar Recepci?n" style="width: 191px; "/>
					<input type="text" id="typeSave" name="typeSave" style="visibility: hidden;"/>
				</div>

			</div>
			
			<textarea name="destino" id="destino"></textarea>
			
	</form>
</body>
</html>






























