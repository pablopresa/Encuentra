



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<jsp:include page="/v3/util/menu.jsp"></jsp:include>
<!-- /. NAV SIDE  -->

<link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
<style type="text/css" media="all">
/* fix rtl for demo */
.chosen-rtl .chosen-drop {
	left: -9000px;
}
</style>
<div class="row">
	<div class="col-md-12">
		<h3>
			Expedición&nbsp;<i class="fa fa-angle-double-right"
				aria-hidden="true"></i>&nbsp;Rutas&nbsp;<i
				class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Editar
			Ruta&nbsp;
		</h3>
		<h6></h6>
	</div>
</div>
<!-- /. ROW  -->
<hr />
<div class="row">
	<div class="col-md-12">
		<c:if test="${menError!=null}">
			<h6>
				<strong>Mensaje:</strong>
			</h6>
			<div class="alert alert-info">
				<strong style="color: red"> <c:out value="${menError}"></c:out></strong>
			</div>
		</c:if>


		<div class="card-header">
			<h5>Actualizando Ruta ${nombreVariable}</h5>
		</div>
		<div class="card-body">
			<div class="alert alert-info">
				<strong style="color: grey">Listado de Depósitos</strong>

			</div>
			<form class="form-horizontal" id="f1" name="frm" role="form"
				method="post" action="<%=basePath%>/editarRutasDepos.do">
				<div class="row">
					<div class="col-md-4">
						<label for="sel2">Todos los Depósitos</label> <select multiple
							class="form-control" id="sel2" name="sellist2"
							style="height: 500px;">
							<c:forEach var="dep" items="${deposAll}">
								<option value="${dep.id}"><c:out
										value="${dep.descripcion}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-2"
						style="margin-top: 20%; margin-left: 9%; margin-right: 3%;">
						<div class="row">
							<a href="javascript:addRemoveDepo('add');"><button
									type="button" class="btn btn-info btn-lg" style="width: 150px;">Agregar</button></a>
						</div>
						<div class="row">
							<a href="javascript:addRemoveDepo('remove');"><button
									type="button" class="btn btn-info btn-lg" style="width: 150px;">Quitar</button></a>
						</div>
					</div>

					<div class="col-md-4">
						<label for="sel2">Depósitos de la Ruta</label> <select multiple
							class="form-control" id="sel2" name="sellist3"
							style="height: 500px;">
							<c:forEach var="gu" items="${grupoUsuarios}">
								<option value="${gu.id}"><c:out
										value="${gu.descripcion}"></c:out>
								</option>
							</c:forEach>
						</select>
					</div>
				</div>

			</form>
		</div>

		<hr />

	</div>



</div>
<!--End Advanced Tables -->
</div>

<script type="text/javascript">
function openM()
{
	  var firstDivContent = document.getElementById('mymodal');
	  firstDivContent.innerHTML="";
	  var secondDivContent = document.getElementById('dive');
	    
	  firstDivContent.innerHTML = secondDivContent.innerHTML
	
	
	$("#mymodal").modal();
}
</script>


<script type="text/javascript">
function addRemoveDepo(opcion) 
				{	
					var valores="";	
					var req = '';
					if(opcion=='add'){
						for (i = 0; i < document.frm.sellist2.length; i++) { 
 				 	
 				 	  		if(document.frm.sellist2.options[i].selected){
 				 	  			console.log("entro");
					  
					  			console.log("indice " +i);
					  			var id = document.frm.sellist2.options[i].value;
					  			console.log("valor " +id);	
					  			var txt =	document.frm.sellist2.options[i].text;
					  			console.log("txt "+ txt); 
						
								console.log("creo opcion"); 
					  			var selOpcion=new Option(txt, id);
					  			console.log("hice opcion"); 
     				  			eval(document.frm.sellist3.options[document.frm.sellist3.length]=selOpcion);
					  			console.log("termina cliente");					  
					  
					  			
 				 	  		}
 				 							  
					  	}
					  
					 	 
					  }
					  if(opcion=='remove'){
					  
					 	 for (i = 0; i < document.frm.sellist3.length; i++) { 
 				 	
 				 	  		if(document.frm.sellist3.options[i].selected){
 				 	  			console.log("entro");
					  
					  			console.log("indice " +i);
					  			var id = document.frm.sellist3.options[i].value;
					  			console.log("valor " +id);	
					  			var txt =	document.frm.sellist3.options[i].text;
					  			console.log("txt "+ txt); 
										  
					  
					  			
 				 	  		}
 				 							  
					  	}
					  	
					  }
						
				     
				  }

</script>



<jsp:include page="/v3/util/footer.jsp"></jsp:include>