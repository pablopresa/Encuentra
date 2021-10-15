<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script type="text/javascript"
	src="<%=basePath%>jsp/Mobile/Encuentra/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>jsp/Mobile/Encuentra/js/jquery-barcode.js"></script>
<style>
* {
	color: #7F7F7F;
	font-family: Arial, sans-serif;
	font-size: 12px;
	font-weight: normal;
}
h1 {text-align: center} 

#config {
	overflow: auto;
	margin-bottom: 10px;
}

.config {
	float: left;
	width: 210px;
	height: 105px;
	border: 1px solid #000;
	margin-left: 10px;
}

.config .title {
	font-weight: bold;
	text-align: center;
}

.config .barcode2D,#miscCanvas {
	display: none;
}

#submit {
	clear: both;
}

#barcodeTarget,#canvasTarget {
	margin-top: 5px;
}
</style>

<script type="text/javascript" src="jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../jquery-barcode.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#barcodeTarget").barcode("${eti.idOjo}", "code128",{barWidth:2, barHeight:80});
		// Handler for .ready() called.
	});
</script>

</head>
<body  style=" width: 8cm;"
	onload="document.forms['contactform']['cod'].focus()">
	
	<c:if test="${uLogeado!=null}">
			<h1 style="color:black; text-align: center">Ubicación Encuentra</h1>
			<h1><strong style="color:black; font-size: 0.7cm">${eti.idOjo}</strong></h1>
			<table style="margin-top:0px; font-size: 5px;">
				<tr style="margin-top:0px;">
					<td><div id="barcodeTarget" class="barcodeTarget"></div></td>
					<td style="margin-top:0px;">
						<table style=" color:black; font-size: 5px; margin-top:0px;">
						<tbody style=" margin-top:0px;">
							<tr style="color:black;line-height:0.8cm; margin-top:0px;">
								<td style="color:black; font-size: 0.4cm">Estanteria:<strong style="color:black;font-size: 0.4cm">${eti.idSector}</strong>
								</td>
							</tr>
							<tr style="color:black;line-height:0.8cm; margin-top:0px;">
								<td style="color:black;font-size: 0.4cm">Modulo:<strong style="color:black;font-size: 0.4cm">${eti.modulo}</strong></td>
							</tr>
							<tr style="color:black;line-height:0.8cm; margin-top:0px;">
								<td style="color:black;font-size: 0.4cm">Estante<strong style="color:black;font-size: 0.4cm">${eti.estante}</strong></td>
							</tr>
						</tbody>
						</table>
					</td>
				</tr>
			</table>

	</c:if>
</body>
</html>