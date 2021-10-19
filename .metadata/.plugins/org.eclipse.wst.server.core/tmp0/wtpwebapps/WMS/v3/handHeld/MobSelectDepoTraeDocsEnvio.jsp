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
<body id="dt_example" style="border-left: 0; width: 210px;" >

<c:if test="${uLogeado!=null}">
			<div class="products-main" align="center" style=" width: width: 210px; float: left;">
				<div class="container" style="float: left; width: width: 210px;">
				<form id="contactform" class="rounded"  action="<%=basePath%>TraerDocsEnvioVerif.do" style="float: left; width: 210px;">
					<div class="field" align="center">
						<a href="<%=basePath%>pausarTarea.do?sale=si">
							<div class="button">
								Ir a menú
							</div>
						</a> 	
						
					</div>	
					<div class="field" align="center">
						<c:if test="${menError!=null}">
							<strong style="color: red">${menError}</strong>  	
						</c:if>
					</div>
							
					<div class="field" align="center" >
						<label for="envio">Numero de Envio</label>
						<input type="text" name="envio"/>
					  	<!-- <input type="checkbox" name="chkCod" id="chkCod" checked="checked"/> -->
						
					</div>
					
					
					<div class="field">
						<label for="Ddes">Deposito de Destino:</label>
						<select name="Ddes" id="Ddes">
							<c:forEach var="d" items="${depositos}">
							<option value="${d.id}">${d.descripcion}</option>
							</c:forEach>
						</select>
						
					</div>
					<input type="submit" name="Submit"  class="button" value="Ver documentos" />
				</form>
				</div>
			</div>
				
		</c:if>
	</body>

</html>	
		
		
</c:if>
</body>
</html>