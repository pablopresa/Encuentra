<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style type="text/css">
		table 
		{
    		border-collapse: collapse;
    		width: 150px;
    		margin-left:10px;
    		margin-bottom:10px;
    		overflow:hidden;
    		float: left;
    		
		}
		table, th 
		{
			border: 1px solid black;
			padding: 15px;
			font-size: 20pt;
			width: 150px;
			text-align: center;
		}
		table, td 
		{
			border: 1px solid black;
			padding: 15px;
			width: 150px;
			font-size: 40pt;
			text-align: center;
		}
		
	</style>
</head>
<body>

<c:forEach var="i" items="${indicadores}">
	<table class="tablas">
		<tr class="indicador" style="background-color:#d1d5d6;"><th>${i.descripcion}</th></tr>
		<c:if test="${i.id<=500}">
			<tr style="background-color:#ebffce"><td>${i.id}</td></tr>
		</c:if>
		<c:if test="${i.id>500 && i.id<=1000}">
			<tr style="background-color:#f3f9ae"><td>${i.id}</td></tr>
		</c:if>
		<c:if test="${i.id>1000}">
			<tr style="background-color:#f9aeae"><td>${i.id}</td></tr>
		</c:if>
		
	</table>
</c:forEach>

</body>
</html>    
