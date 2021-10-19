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
<meta http-equiv="refresh" content="0;url=<%=basePath%>/MenuMob.do?sec=M" />

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
<script>

	function myJsFunc()
	{
		window.location='<%=basePath%>/v3/handHeld/MobListaMisTareas.jsp'
	}
</script>
<script>
	function myJsFuncII()
	{
		window.location='<%=basePath%>/v3/handHeld/MobAltaMedidaArt.jsp'
	}
</script>
<script>
	function myJsFuncIII()
	{
		window.location='<%=basePath%>/v3/handHeld/MobCambiarUbicacionForm.jsp'
	}
</script>
<script>
	function myJsFuncIV()
	{
		window.location='<%=basePath%>/v3/handHeld/MobUpdateUbicacionForm.jsp'
	}
</script>

<script>
	function myJsFuncXII()
	{
		window.location='<%=basePath%>/v3/handHeld/MobBajaUbicacionForm.jsp'
	}
</script>


<script>
	function myJsFuncV()
	{
		window.location='<%=basePath%>/v3/handHeld/MobaltaUbicacionForm.jsp'
	}
</script>
<script>
	function myJsFuncVI()
	{
		window.location='<%=basePath%>DarTareasTerMob.do';
	}
</script>
<script>
	function myJsFuncVII()
	{
		window.location='<%=basePath%>GenerarVinculos.do'
	}
</script>
<script>
	function myJsFuncVIII()
	{
		window.location='<%=basePath%>DarDepositos.do'
	}
</script>
<script>
	function myJsFuncIX()
	{
		window.location='<%=basePath%>DarDepositosVerifEnvio.do'
	}
</script>
<script>
	function myJsFuncX()
	{
		window.location='<%=basePath%>/v3/handHeld/MobCantUbicacionForm.jsp'
	}
</script>

<script>
	function myJsFuncXI(destino)
	{
		window.location='<%=basePath%>/DarPickings.do?destino='+destino;
	}
</script>
<script>
	function myJsFuncXIII(destino)
	{
		window.location='<%=basePath%>/initMovStock.do'
	}
</script>
<script>
	function myJsFuncXX()
	{
		window.location='<%=basePath%>/v3/handHeld/MobVerifReqEcommerce.jsp'
	}
</script>
<script>
	function myJsFuncXXI()
	{
		window.location='<%=basePath%>/v3/handHeld/MobPacking.jsp'
	}
</script>
<script>
	function myJsFuncXXII()
	{
		window.location='<%=basePath%>/v3/handHeld/MovVerifFactEcommerce.jsp'
	}
</script>
<script>
	function myJsFuncXXX()
	{
		window.location='<%=basePath%>/initEnvioEncuentra.do'
	}
</script>
<script>
	function myJsFuncXXXI()
	{
		window.location='<%=basePath%>v3/handHeld/MovVerifFactEcommerce_SF.jsp'
	}
</script>
<script>
	function myJsFuncXIV()
	{
		window.location='<%=basePath%>/v3/handHeld/MobArtEnUbi.jsp'
	}
</script>
<script>
	function myJsFuncXV()
	{
		window.location='<%=basePath%>/v3/handHeld/ControlPaqueteEcommerce.jsp'
	}
</script>

<script>
	function myJsFuncXVI()
	{
		window.location='<%=basePath%>/v3/handHeld/MobaltaBarraForm.jsp'
	}
</script>

<script>
	function myJsFuncXVII()
	{
		window.location='<%=basePath%>/v3/handHeld/login.jsp'
	}
</script>

<script>
	function myJsFuncXXV()
	{
		window.location='<%=basePath%>/v3/handHeld/MobRecepcionMercaderiaForm.jsp'
	}
</script>

<script>
	function myJsFuncXXVI()
	{
		window.location='<%=basePath%>/v3/handHeld/MobVerifVerifEcommerce.jsp'
	}
</script>




</head>
<body>

<c:if test="${uLogeado!=null}">
			
				<div class="container">
					<c:if test="${menError!=null}">
						<script type="text/javascript">
							alert("${menError}");
						</script>
					</c:if>
					
					<div class="row">
		      			<div class="col-100">
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncX();"   value="Cantidad ojo"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncIV();"  value="Agregar a Ojo"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXII();"  value="Bajar a Ojo"/>
							<input class="col-100 button" type="button" name="1" onclick="myJsFuncV();"   value="Actualizar ojo"/>
							<input onclick="myJsFuncIII();"  class="col-100 button" type="button" name="1"   value="Mover"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXIV();"  value="Art en Ubic"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXVI();"  value="Cod. Barras"/> 
							<!-- 
							<input onclick="myJsFuncII();"  class="col-100 button" type="button" name="1"    value="Medidas Cajas"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncVI();"   value="Mis Tareas Terminadas"/>
							-->
							<input class="col-100 button" type="button" name="1"  onclick="myJsFunc();"   value="Mis Tareas"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXI('verificar');"   value="Verificar Picking"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXI('clasificar');"   value="Clasificar Picking"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXXV();"  value="Recepcion"/>
							<!-- 
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncVIII();" value="Controlar Documentos"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncIX();"   value="Controlar Envio"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXIII();" value="Movimiento Stock"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXXI();"  value="Packing"/>							
							-->
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXX();"   value="Verificar Pedido Ecommerce"/>
							<!-- 
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXV();"   value="Control Pedido Ecommerce"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXXII();" value="Etiqueta Pedido Ecommerce"/>
							-->
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXXX();"  value="Preparar Envio ecommerce"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXXVI();"   value="Verificar Verificado Ecommerce"/>
							<input class="col-100 button" type="button" name="1"  onclick="myJsFuncXVII();"  value="Cerrar Sesion"/>
						</div>
					</div>
			
				</div>
				
		</c:if>
	</body>

</html>	
		
		
</c:if>
</body>
</html>