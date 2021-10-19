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
    <link rel="stylesheet" href="<%=basePath%>v2/assets/handheld/buscador.css" media="screen" />
  </c:when>
  <c:otherwise>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<%=basePath%>v2/assets/css/responsiveForms.css" type="text/css">
  </c:otherwise>
</c:choose>
</head>
<body>


<c:if test="${uLogeado!=null}">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>


<script type="text/javascript">

	
	
	function getfocus() {
	    document.getElementById("codArt").focus();
	}
	
</script>





</head>
<body id="dt_example" style="border-left: 0; width: 210px;" >

<c:if test="${uLogeado!=null}">
			<div class="products-main" align="center" style=" width: width: 210px; float: left;">
				<div class="container" style="float: left; width: width: 210px;">
				
				<form id="contactform" class="rounded" method="post" action="<%=basePath%>DarOjosPedido.do" style="float: left; width: 210px;">
					<div style="width: 1px; height: 1px; visibility: hidden;">
						<input type="text" name="mob" value="1" style="width: 1px; height: 1px; visibility: hidden;">
					</div>
					 	
					<div class="field" align="center">
						<c:if test="${menError!=null}">
							<strong style="color: red">${menError}</strong>
						</c:if>
					</div>
							
					<div class="field" align="center" >
						<label>Numero de pedido:/Nombre Cliente</label>
						<input type="text" class="input" name="art" id="codArt" style="width: 191px; "/><br/>
						<input type="submit" name="Submit"  class="col-100 button" value="Ver Ubicaciones"/>	
						<a href="<%=basePath%>MenuMob.do?sec=M.D"> <input class="col-100 button" type="button" name="1"  value="Ir a menu"/></a>	
					</div>
				</form>
				</div>
			</div>
				
		</c:if>
		
	</body>
<script type="text/javascript">
	function VolverMenu(){
		window.location='<%=basePath%>pausarTarea.do?sale=si'
	}
</script>
		
</html>	
		
		
</c:if>
</body>
</html>