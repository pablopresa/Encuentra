<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
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
	
	function nextel(event) 
	{ 
		if(event==13)
		{  
			document.getElementById("test").focus(); 
			
		} 
	}
	
	
	 
</script>
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
	
	function Validar(cod,quant){
        	
				cod = cod.trim();
        		var xmlhttp = new XMLHttpRequest();
			    //xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			    xmlhttp.onreadystatechange = function() {
			        if (xmlhttp.readyState == XMLHttpRequest.DONE ) 
			        {
			           if (xmlhttp.status == 200) 
			           {
			        	   var salida = JSON.parse(xmlhttp.responseText);	        	   
			           		document.getElementById('artValido').value = salida.existe;
			           		if(document.getElementById('artValido').value=='true'){

								//if( salida.ubicaciones=="" || (salida.ubicaciones!="" && confirm("Hay art?culos del tipo que desea ingresar en las siguientes ubicaciones: "+salida.ubicaciones+"\nDesea agregarlo de todos modos?"))){

									codigos.push("\n"+cod+":"+quant+":" + salida.descripcion);
									count += quant;
									
									document.getElementById("lblCantidad").innerHTML = count;
									document.getElementById("destino").value = codigos.toString();
									
								//}
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
	    if (char == 13 || char == 1) 
		{
			var cod = document.getElementById("test").value;
			var quant =  TryParseInt(document.getElementById("canti").value,1);
			if(cod!="")
			{
				
				document.getElementById("test").value = "";
				if(simbolo==' + ')
				{
					if(document.getElementById('valida').checked){
						Validar(cod,quant);						
					}
					else{
						codigos.push(cod+":"+quant);
						count+=quant;
					}
					
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
	
	function subm(event) 
	{
	
		 
		var operation = document.querySelector('input[name="opperation"]:checked').value;
		var queHacemos="";
		switch(operation){
		case "add":
			queHacemos="Agregar a ojo";
		break;
		
		case "del":
			queHacemos="Bajar de ojo";
		break;
		
		case "act":
			queHacemos="Actualizar ojo";
		break;
		}
		
		if(confirm("Desea confirmar "+queHacemos+"?"))
		{
			
			
			for(i=0;i<operation.length;i++){
				if(operation[i].checked){
					operation = operation[i].value;
					break;
				}
			}
			
			var form = document.forms["contactform"];
			
			
			switch(operation){
			case "add":
				form.setAttribute('action', '<%=basePath%>/AgregaraUbicacion.do');
			break;
			
			case "del":
				form.setAttribute('action','<%=basePath%>/BajarUbicacion.do');
				
			break;
			
			case "act":
				form.setAttribute('action','<%=basePath%>/updateUbicacion.do');
				
			break;
			}
			
			
			form.submit();
		}	
		
		
	}
	
	
	function subm3() 
	{
		if(confirm("Desea Reiniciar  ("+count+")?"))
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
<body onload=" document.getElementById('base').focus();">
	<c:if test="${uLogeado!=null}">
		<c:forEach var="d" items="${uLogeado.seguridadUI}">
			<c:if test="${d=='radio_actualizar_ojo'}">
				<c:set var="puede" scope="request" value="1" />
			</c:if>
		</c:forEach>
	<form id="contactform" class="rounded" method="post"  style="float: left;">
		
		<div class="row">
			<div class="col-100">
			<input type="text" name="artValido" id="artValido" style="display: none;">
				<table>
					<tr>
						<td>Codigo de Ubicacion</td>
						<td>Validacion en Tiempo Real</td>
					</tr>
					<tr>
						<td><input type="text" class="inputCH" name="idOjo" id="base" onkeypress="nextel(event.keyCode)" /></td>					
						<td><input type="checkbox" name="valida" id="valida" checked></td>
					</tr>
				</table>
				<table>
					<tr>
						<c:if test="${puede==1}">
						<td style="width: 33%; text-align: center;" >Actualizar</td>
						</c:if>
						<td style="width: 33%; text-align: center;">Agregar</td>
						<td style="width: 33%; text-align: center;">Bajar</td>
					</tr>
					<tr>
						<c:if test="${puede==1}">
						<td style="width: 33%"><input style="border: 0px;  width: 100%; height: 2em;" type="radio" id="Act" name="opperation" value="act" ></td>
						</c:if>
						<td style="width: 33%"><input style="border: 0px;  width: 100%; height: 2em;" type="radio" id="Add" name="opperation" value="add" checked></td>
						<td style="width: 33%"><input style="border: 0px;  width: 100%; height: 2em;" type="radio" id="Del" name="opperation" value="del"></td>
					</tr>
				</table>
				<table>
					<tr>
						<td style="width: 80%">Barra</td>
						<td></td>
						<td style="width: 15%">Cant</td>
						<td style="width: 5%">+-</td>
					</tr>
					<tr>
						<td style="width: 85%"><input type="text" name="test" id="test"  onkeypress="onTestChange(event);"/></td>
						<td><input type="button" class="col-100 button" onclick="onTestChange(event);" value=">>" /></td>
						<td ><input  style="padding: 1px;" type="number" min="1"  name="canti" id="canti"  value=1 /></td>
						<td style="width: 5%"><div style="text-align: center;" id="sim" onclick="ChangeSim(); return false;"> + </div></td>
						
					</tr>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-100">
				<label for="lblCantidad">Contador: ${cantRecepSaved}&nbsp;<strong id="lblCantidad"></strong></label>
				<textarea name="articulos" id="destino" readonly="readonly"></textarea>
				
				<input type="button" class="col-100 button" onclick="subm(event,1)" value="Confirmar" />
			
				<input type="button" class="col-100 button" onclick="subm3()" value="Reiniciar"/>
				<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.A'"  value="Ir a menu"/>
						
				
			</div>
		</div>
		<input type="text" id="sessionViva" style="display: none;">
	</form>
	</c:if>
</body>

</html>	

<script type="text/javascript">
	
            
          function traerDatos(){
          	try{
	           jQuery.ajax({
	                url:'<%=basePath%>ValidateSession.do',
	                dataType:'text'
	             }).then(function(data) {
	          
	          		console.log('session viva?');
	         		console.log(data);
	         		document.getElementById('sessionViva').value=data;
	         		
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
           setInterval(traerDatos, 10000);
       });
        
</script>


		
</c:if>
</body>
</html>