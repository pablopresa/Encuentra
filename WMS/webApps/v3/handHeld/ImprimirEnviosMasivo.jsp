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
<link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css" media="handheld" />
</head>
<body>

<c:if test="${uLogeado!=null}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>


<link rel="stylesheet" id="smthemenewprint-css" href="<%=basePath%>v3/assets/handheld/formsHandHel.css" type="text/css"/>


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
		
	}
</script>



</head>
<body id="dt_example" style="border-left: 0;"  onload=" document.getElementById('base').focus();">


	<c:if test="${uLogeado!=null}">
			<div class="products-main" align="center" style=" float: left;">
				<div class="container" style="float: left;">
				
				
				<form id="contactform" name="contactform" class="rounded"  action="<%=basePath%>/ImpresionMasivaEtiquetasEnvio.do" method="post" style="float: left;">
					
					
					<div class="field" align="center">
						<label for="base">Ingrese pedidos</label>
						<textarea rows="4" cols="20" name="articulos" id="articulos"  onkeyup="Textarea_Sin_Enter(event.keyCode, event.which, 'articulos');"></textarea>
					</div>
					<div class="field">
						Pedidos escaneados &nbsp;<label id="lbltipAddedComment">0</label>
					</div>
					
					<input type="button"  class="button"  onclick="subm(1)" value="Imprimir etiquetas"/>
					
				</form>
				
				</div>
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