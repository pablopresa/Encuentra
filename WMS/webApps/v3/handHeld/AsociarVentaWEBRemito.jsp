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
		<form onsubmit="return validate(this);" id="contactform" class="rounded" method="post" action="<%=basePath%>/AsocRemitoPWE.do" style="float: left;">
			<div class="row">
				<div class="col-100">
					<a href="<%=basePath%>pausarTarea.do?sale=si"><div class="button">Ir a menú</div></a>

				</div>
			</div>
			</div>
			<div class="row">
				<div class="col-100">
					<table style=" border-collapse: collapse; border-spacing: 10px; ">
						<tr>
							<td>Doc.</td>
							<td>Articulos</td>
							<td>Venta</td>
						</tr>
						 <c:forEach var="d" items="${remitosWEBNO}">
						 	<tr>
						 		<td>${d.numeroDoc} </td>
						 		<td>
							 	<c:forEach var="a" items="${d.lineas}">
							 		${a.idArticulo}<br/> 
							 	</c:forEach>
						 		</td>
						 		<td><input type="text" name="${d.numeroDoc}"></td>
						 	<tr>
						</c:forEach>
					</table>
					<input class="col-100 button" type="submit" value="asociar"/>
				</div>
			</div>
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

 <script type="text/javascript">
       window.onload = function() {
         document.getElementById("test").focus();
       };
 </script>
 
 <script type="text/javascript">
	

	
	function showAlert(msj) {
		alert(msj);
	}
	
	
	function validate(form) 
	{


    		return confirm('confirma?');	

       
    }
    else 
    {
     	return true;   
    }
}
	
</script>
 
 
</html>