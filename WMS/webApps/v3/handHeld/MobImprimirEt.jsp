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


</head>
<body id="dt_example" style="border-left: 0; width: 210px;"  onload="document.forms['contactform']['cod'].focus()">

<c:if test="${uLogeado!=null}">
			<div class="products-main" align="center" style=" width: width: 210px; float: left;">
				<div class="container" style="float: left; width: width: 210px;">
				<form id="contactform"  name="contactform"  class="rounded" method="post" action="<%=basePath%>/DarEtToPrint.do" style="float: left; width: 210px;">
					<h3>Ingrese <br/><br/> Ubicación</h3>
					<div class="field">
						<label for="cod">Codigo:</label>
					  	<input type="text" class="input" name="cod" id="cod"/>
						<p class="hint">Scanee el codigo </p>
					</div>
						
					<div class="field">
					<input type="submit" name="Submit"  class="button" value="Imprimir" /><BR/>
										<input type="checkbox" name="idEtiqueta"/>Etiqueta Grande
					</div>
					
					<div style="height: 5px;"></div>
					<a href="<%=basePath%>pausarTarea.do">
						<div class="button">
							Ir a menú
						</div>
						
					</a> 	
				</form>
				</div>
			</div>
				
		</c:if>
	</body>

</html>	
		
		
</c:if>
</body>
</html>