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




<script type="text/javascript"> 
	/*
	function nextel(event) 
	{ 
		if(event==13)
		{  
			//document.forms[0].elements[1].focus();
			
			//document.getElementById('articulos').focus() 
			event.keyCode=9; 
			ret
			
		} 
	}
	*/
	
	function handleEnter (field, event) {  
     var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;  
     if (keyCode == 13) {  
         var i;  
         for (i = 0; i < field.form.elements.length; i++)  
             if (field == field.form.elements[i])  
                 break;  
         i = (i + 1) % field.form.elements.length;  
         field.form.elements[i].focus();  
         return false;  
     }   
     else  
     return true;
     }
	 
</script>
<script type="text/javascript">
	function Textarea_Sin_Enter($char, $mozChar, $id){
	   //alert ($char+" "+$mozChar);
	   $textarea = document.getElementById($id);
	   
	    
	   if($char == 13) 
	   {
	   		$textarea.value += ",";
	   		
	   		
	   		var texto = Number(document.getElementById('lbltipAddedComment').innerHTML);
	   		document.getElementById('lbltipAddedComment').innerHTML = texto +1;
	   		
	   }
	}
	
	
	
</script>
<script type="text/javascript">
	
	function subm(coso)
	{
		
		$textarea = document.getElementById('articulo');
		$cubi =	document.getElementById('base');
		
		if(coso==1)
		{
			document.contactform.submit()
		}
		 
		   
		//document.location='/Arreglos/updateUbicacion.do?idOjo='+$cubi.value+'&articulos='+$textarea.value;
		
	}
</script>



</head>
<body onload=" document.getElementById('base').focus();">


	<c:if test="${uLogeado!=null}">
		<div class="container" style="float: left;">
			<form id="contactform" name="contactform"  action="<%=basePath%>/asociarBarras.do?barraManual=true" method="post">
				<div class="row">
					<input class="col-100 button" type="button" name="1"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.A'"  value="Ir a menu"/>
				</div>	
				<div class="row col-100">	
					<label for="base">Ingrese cod. Articulo</label>
				</div>
				<div class="row col-100">
					<input type="text" class="input" name="art" id="base" value="${articuloCod}" onkeypress="return handleEnter(this, event)" required/>
				</div>
				<div class="row col-100">	
					<label for="bar">Ingrese cod. Barra</label>
				</div>
				<div class="row col-100">
					<input type="text" class="input" name="bar" id="bar" onkeypress="return handleEnter(this, event)" required/>
				</div> 
				<div class="row col-100">
					<input type="button"  class="button col-100"  onclick="subm(1)" value="Guardar informaci?n"/>
				</div>

			</form>
				
		</div>
				
		</c:if>
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
	</body>

</html>	
		
		
</c:if>
</body>
</html>