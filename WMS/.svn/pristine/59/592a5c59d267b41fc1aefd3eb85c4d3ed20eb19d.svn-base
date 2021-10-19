<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


<c:forEach var="f" items="${filtros}">
	<c:choose>
		<c:when test = "${f.tipo=='text'}">
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label class="col-md-12 control-label" for="art">${f.label}:&nbsp;</label>
					<div class="col-md-10">
						<div class="input-group input-group-sm">
							<input type="text" class="form-control bg-white" aria-label="Small"
								aria-describedby="inputGroup-sizing-sm" name="${f.name}"
								 <c:if test="${f.required==true}"> required </c:if>
								 placeholder="${f.placeholder}">
						</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test = "${f.tipo=='rango'}">
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label class="col-md-12 control-label" for="art">${f.label}:&nbsp;</label>
					<div class="col-md-10">
						<input class="form-control form-control-sm bg-white rango"
							type="text" name="${f.name}" id="fini">
					</div>
				</div>
			</div>
		</c:when>
		<c:when test = "${f.tipo=='multi'}">
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label class="col-md-12 control-label" for="art">${f.label}:&nbsp;</label>
					<div class="col-md-10">
						<select class="select2-multi col-sm-12" multiple="multiple"
							placeholder="${f.placeholder}" name="${f.name}">
							<option value=""></option>
							<c:forEach var="v" items="${f.values}">
									<option value="${v.id}">${v.descripcion}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</c:when>
	</c:choose>
</c:forEach>
<div class="flex-container">
	<c:forEach var="f" items="${filtros}">
		<c:if test="${f.tipo=='switch'}">
			<label class="p-2" for="${f.id}">${f.label}:&nbsp;</label>
			<div class="switch d-inline m-r-10">
				<c:if test="${f.value=='on'}">
					<input type="checkbox" name="${f.name}" id="${f.id}" checked="checked">
					<label for="${f.id}" class="cr"></label>
				</c:if>
				<c:if test="${f.value!='on'}">
					<input type="checkbox" name="${f.name}" id="${f.id}">
					<label for="${f.id}" class="cr"></label>
				</c:if>
			</div>
		</c:if>
	</c:forEach>
</div>


<!-- 
	<label class="p-2" for="switch-2">EXPORTAR EXCEL:&nbsp;</label>
	<div class="switch d-inline m-r-10">
		<c:if test="${exportE=='on'}">
			<input type="checkbox" name="exportE" id="switch-4" checked="checked">
			<label for="switch-4" class="cr"></label>
		</c:if>
		<c:if test="${exportE!='on'}">
			<input type="checkbox" name="exportE" id="switch-4">
			<label for="switch-4" class="cr"></label>
		</c:if>
	</div>
 -->







	<!-- <script>
	function closeModal(id){
		$(id).modal('hide');
	}
</script> -->