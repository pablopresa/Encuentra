<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dataTypes.DataIDDescripcion"%>


<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
  
  	<!-- Js Jquery se debe importar antes -->
	
	
	<head>
<script src="<%=basePath%>v3/assets/plugins/jquery/js/jquery.min.js"></script>
<script>
function darCodEan(dataMat)
{
    $.get("<%=basePath%>CallWSRFID.do?ItemId="+dataMat, function(data, status){
      alert(data);
    });

}
</script>
</head>
<body>
<button onclick="darCodEan('4B43358D17AA5D00005E49EB')">probar</button>

</body>     
		
