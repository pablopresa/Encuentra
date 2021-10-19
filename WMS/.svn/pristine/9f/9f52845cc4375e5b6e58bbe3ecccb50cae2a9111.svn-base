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

       
<jsp:include page="/v3/util/index_headerSM.jsp"></jsp:include>

	<div class="bg-white">
    	<div class="row" >
			<div class="col-md-12">
             	<c:if test="${menError!=null}">
              	<h5><strong>Mensaje:</strong></h5>
				<div class="alert alert-info" id="divMensaje">
                	<strong style="color: red"> ${menError}</strong>            
                 </div>
                 </c:if>
                 <div class="panel panel-default">
                    <div class="panel-heading">
                 	   &nbsp;Lista de Cajas Abiertas &nbsp;
                    </div>
                    <div class="panel-body">
                      	<div>
                      		<c:set var = "estadoF" scope = "page" value = "Preparando pedido"/>
                        	<table class="table table-compact table-bordered table-hover responsive"  width="100%" id="encuentra-mob" border=1 frame=void rules=rows cellpadding="1" >
                        		<thead>
                        		<tr>
									<th style="width: 50px;">Datos</th>
									<th style="width: 50px;">Cerrar</th>
									
								</tr>
								</thead>
					 			<tbody>
									<c:forEach var="b" items="${boxes}">
									<tr>
										<td style="width: 50px;"><div align="center">${b.destino}${b.descDestino}<br/>Bul: ${b.idBulto} <br/> ${b.equipo_trabajo} </div></td>
										<td style="width: 50px;">
											<div align="center">							
												<a href="#" onclick="CloseBox('${b.idBulto}')">
													<img alt="Despachar" src="<%=basePath%>imagenes/icons/box.png" border="0" style="width: 20px;">
													<div style="display: none;" align="center" id="destino${b.idBulto}">${b.posSort}</div>
												</a>
											</div>
										</td>
										
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div><!-- ANtestabla -->
					</div><!-- panel body -->
				</div><!-- panel -->
			</div><!-- col 12 -->
		</div><!-- ROW -->
		<input class="col-100 button" type="button" name="volverMenu"  onclick="window.location='<%=basePath%>MenuMob.do?sec=M.B'"  value="Ir a menu"/>	
		<c:remove var = "menError" scope="session"/>
	</div><!-- BG WHITE -->
 			
 			
 	<audio id='xyz' src='<%=basePath%>/audio/error.mp3' preload='auto'></audio>
 	
 	<script type="text/javascript">
 	

	 	function CloseBox(b)
		{
			var dest = document.getElementById('destino'+b).innerHTML;
			if(confirm('Esta seguro que quiere cerrar esta caja? Esto generara un movimiento de stock'))
			{
				location.replace('<%=basePath%>CerrarCaja.do?idDestino='+dest+'&boxes=true');
			}	
		}
		
		<c:if test="${menError!=null}">
			var alarma = document.getElementById('xyz');
			alarma.play().catch(function() {
		    document.getElementById('xyz').play();
			});
			alarma.addEventListener('ended', showAlert);

			function showAlert() {
			 alert('${menError}');
			}			
		</c:if>		
	</script>	
	
	<jsp:include page="/v3/util/footer_mob.jsp"></jsp:include>
</html>	
		
		
