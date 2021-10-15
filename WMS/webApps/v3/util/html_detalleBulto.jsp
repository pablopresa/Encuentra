<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<div id="dive" >
	<div class='modal-dialog'>
		<!-- Modal content-->
		<div class='modal-content'>
			<div class='modal-header'>				
				<h4 class='modal-title'>BULTO 
					&nbsp;${bulto}</h4>
					<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>
			<div class='modal-body'>
				<table class="table table-striped table-bordered table-hover">
					<tr>
						<th style="text-align: center;">Articulo</th>
						<th style="text-align: center;">Cantidad</th>
					</tr>
					<c:forEach var='d' items='${OTAdetalle}'>
						<tr>
							<td style="text-align: center;">${d.idArticulo}</td>
							<td style="text-align: center;">${d.cantidad}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class='modal-footer'>
				<button type='button' class='btn btn-info' data-dismiss='modal'>Close</button>
			</div>
		</div>
	</div>
</div>
