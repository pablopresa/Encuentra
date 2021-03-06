<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<c:set var="browser" value="${header['User-Agent']}" scope="page"/>
		<c:choose>
		  <c:when test="${fn:contains(browser, 'MSIE')}">
		    <c:set var="css" value="IE" scope="session"/>
		    <link rel="stylesheet" href="<%=basePath%>v3/assets/handheld/buscador.css" media="screen" />
		    
		  </c:when>
		  <c:otherwise>
		    <c:set var="css" value="NOTIE" scope="session"/>
		    <meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="stylesheet" href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
		  </c:otherwise>
		</c:choose>
		
		<link rel="stylesheet" href="<%=basePath%>v3/assets/css/responsiveForms.css" type="text/css">
		<script src="<%=basePath%>script/bloqueoEnter.js" type="text/javascript"></script>
		
		
		<script type="text/javascript">
	    //var dispositivo = navigator.userAgent.toLowerCase();
		      //if( dispositivo.search(/iphone|ipod|ipad|android/) > -1 )
				//{
	      			//document.location = '<%=basePath%>jsp/Mobile/Encuentra/JQM/MobLogin.jsp';  
				//}
				
	  </script>
		
	<c:if test="${idEmpresa!=null}">
		<c:set var = "idEmpresa" scope = "session" value = "${idEmpresa}"/>
	</c:if>
	<c:if test="${idEmpresa==null}">
		<c:set var = "idEmpresa" scope = "session" value = "6"/>
	</c:if>
	</head>
	<body onload="document.forms['contactform']['cod'].focus()">
		  <div class="container">
		  	<div class="row"><h3>Acceso al sistema</h3></div>
		  <form id="login" method="post" action="<%=basePath%>loginEncuentra.do" name="login">
		      <div class="col-100">
		        <label for="nombreUsuario"><img  src="<%=basePath%>imagenes/icons/user_gray.png"> Nombre:</label>
		      </div>
		     <div class="row">
		      <div class="col-100">
		        <input type="text" name="nombreUsuario" value= "${nombre}" onkeypress="return handleEnter(this, event)">
		      </div>
		    </div>
		    <div class="row">
		      <div class="col-100">
		        <label for="password"><img  src="<%=basePath%>imagenes/icons/key_go.png"> Password:</label>
		      </div>
		     </div>
		     <div class="row">
		      <div class="col-100">
		        <input type="password" name="password" value="${pass}" onkeypress="return handleEnter(this, event)">
		      </div>
		    </div>
		    <br/>
		   	<input type="submit" name="Submit" class="col-100 button" value="Entrar"/>
		    <input style="visibility: hidden;" type="text" maxlength="2" name="pc" value="0" >
		  </form>
		  <font color="#ff0000"><c:out value="${mensaje}"></c:out></font>
		</div> 
	
	
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
		
		

