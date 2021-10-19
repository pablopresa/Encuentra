<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<c:set var = "colapsar" scope = "request" value = "yes"/>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		<link rel="stylesheet" href="<%=basePath%>v3/assets/select/chosen.css">
		  <style type="text/css" media="all">
		    /* fix rtl for demo */
		    .chosen-rtl .chosen-drop { left: -9000px; }
		  </style>
        <!-- /. NAV SIDE  -->
        <script type="text/javascript">
        
        
        var change = false;
        var artis = '';
        changesJson =[];
        
     	function ChangeQuantity(arti,ori,des,ped,docu,lin) 
		{
			change = true;
			var cant = document.getElementById(arti+'-'+des+'-'+ped+'-'+docu).value;
			
			var idOrigen =	{id:parseInt(ori),descripcion:''};
			var idDestino = {id:parseInt(des),descripcion:''}; 
			var pedido = parseInt(ped);
			var doc = parseInt(docu);
			var verif = parseInt(cant);
			var pick = parseInt(lin);
			
			var jsonN = {articulo:arti, origen:idOrigen, destino:idDestino, idPedido:pedido, solicitud:doc, verificada:verif, idPicking:pick};
            //var jsonString = JSON.stringify(json);
            
            var index =changesJson.findIndex(json => json.articulo===arti && json.origen===idOrigen && json.destino===idDestino && 
            json.idPedido===pedido && json.solicitud===doc);
            
            if(index>=0)
            {
            	changesJson.splice(index,index);
            	console.log('repetido');
            	changesJson.push(jsonN);
            }
            else{
            	artis += arti + ', ';
            	changesJson.push(jsonN);
            }
            
		}
     	
     	function ChangeQuantityR(arti,ori,des,ped,docu,lin) 
		{
			change = true;
			var cant = document.getElementById('R'-arti+'-'+des+'-'+ped+'-'+docu).value;
			
			var idOrigen =	{id:parseInt(ori),descripcion:''};
			var idDestino = {id:parseInt(des),descripcion:''}; 
			var pedido = parseInt(ped);
			var doc = parseInt(docu);
			var remi = parseInt(cant);
			var pick = parseInt(lin);
			
			var jsonN = {articulo:arti, origen:idOrigen, destino:idDestino, idPedido:pedido, solicitud:doc, remitida:remi, idPicking:pick};
            //var jsonString = JSON.stringify(json);
            
            var index =changesJson.findIndex(json => json.articulo===arti && json.origen===idOrigen && json.destino===idDestino && 
            json.idPedido===pedido && json.solicitud===doc);
            
            if(index>=0)
            {
            	changesJson.splice(index,index);
            	console.log('repetido');
            	changesJson.push(jsonN);
            }
            else{
            	artis += arti + ', ';
            	changesJson.push(jsonN);
            }
            
		}
        
        function ColorChange(element, index, array) {
		    console.log(element);
		    var soli = document.getElementById('sol-'+element.articulo+'-'+element.destino.id+'-'+element.idPedido+'-'+element.solicitud).value;
		    var pick = document.getElementById('pick-'+element.articulo+'-'+element.destino.id+'-'+element.idPedido+'-'+element.solicitud).value;
		    var verif = document.getElementById(arti+'-'+des+'-'+ped+'-'+docu).value;
		    
		    if(soli==pick){
		    	document.getElementById('pick'+element.articulo+'-'+element.destino.id+'-'+element.idPedido+'-'+element.solicitud).style.backgroundColor = '#adebad';
		    }else{
		    	document.getElementById('pick'+element.articulo+'-'+element.destino.id+'-'+element.idPedido+'-'+element.solicitud).style.backgroundColor = '#ffcccc';
		    }
		    
		    if(pick==verif){
		    	document.getElementById(element.articulo+'-'+element.destino.id+'-'+element.idPedido+'-'+element.solicitud).style.backgroundColor = '#adebad';
		    }else{
		    	document.getElementById(element.articulo+'-'+element.destino.id+'-'+element.idPedido+'-'+element.solicitud).style.backgroundColor = '#ffcccc';
		    }
		}
        
        function corregir() 
		{
			artis = artis.substring(0,artis.length-2);
			if(confirm("Seguro que desea corregir las cantidades de los siguientes articulos? \r\n"+artis))
			{
				$.ajax({
                           url: "<%=basePath%>CorregirCantVPickingFromFile.do",
                           type: "post",
                           dataType: "html",
                           data: { 
						         datos: "{collection:"+JSON.stringify(changesJson)+"}" // look here!
						       }
                       
					 }).then(function(data){
                              	console.log("success");
					       		//changesJson.forEach(ColorChange);
					       		document.getElementById("myBody").innerHTML = data;
					       		artis = '';
								change = false;
								changesJson=[];
								window.scrollBy(0, document.getElementById('corregir').getBoundingClientRect().bottom);
                           });
			}
			
		}
		
        function Transferir() 
		{
			if(change)
			{
				if(confirm("REFLEXIONE: ha hecho cambios en las cantidades que no guardó, si continua, esos cambios se perderán. ¿desea continuar?"))
				{
					location.replace("<%=basePath%>RemitirPickingFromFile.do");
				}
			}
			else
			{
				if(confirm("Se procederá a grabar las transferencias de stock. ¿desea Continuar?"))
				{
					location.replace("<%=basePath%>RemitirPickingFromFile.do");
				}
			}
			
			
		}
        
        </script>
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Picking&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;pickings de terceros</h3>   
                        <h6>Detalle de picking</h6>
                    </div>
                </div>              
                 <!-- /. ROW  -->
                <hr />
                <div class="row">
                <div class="col-md-12">
                	<c:if test="${menError!=null}">
                	<h6><strong>Mensaje:</strong></h6>
                    <div class="alert alert-info">
                    	<strong style="color: red"> <c:out value="${menError}"></c:out></strong>            
                    </div>
                    </c:if>
                    <!-- Advanced Tables -->
                    
                    <div class="card">
                        <div class="card-header">
                        	<h5>
                             	Articulos
                            </h5> 
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<table class="table table-striped table-bordered table-hover" id="encuentra-default">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th class="text-center"> Usuario</th>
                                            <th class="text-center"> Pedido</th>
                                            <th class="text-center"> Articulo</th>
                                            <th class="text-center"> Descripción</th>
                                           <!--  <th class="text-center"> Stock</th>
                                            <th class="text-center"> Stock <br/> SAP</th> -->
											<th class="text-center"> Destino</th>
											<th class="text-center"> Solicitadas<br>(${totSol})</th>
											<th class="text-center"> Encontradas<br>(${totEnc})</th>
											<th class="text-center"> Verificadas<br>(${totSol})</th>
											<th class="text-center"> Remitidas<br>(${totSol})</th>
										</tr>
                                    </thead>
                                    <tbody id="myBody">
                                    	<c:set var="linea" scope="page" value="0"></c:set>
                                    	<fmt:parseNumber var="i" type="number" value="${linea}"/>
                                    	<c:forEach var="p" items="${pickings}">
                                    		<c:if test="${p.sol!=p.pick}">
												<c:set var="coloPI" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${p.sol==p.pick}">
												<c:set var="coloPI" scope="page" value="#adebad"></c:set>
											</c:if>
											<c:if test="${p.verificada!=p.pick}">
												<c:set var="coloVE" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${p.verificada==p.pick}">
												<c:set var="coloVE" scope="page" value="#adebad"></c:set>
											</c:if>
											<c:if test="${p.verificada!=p.remitida}">
												<c:set var="coloRE" scope="page" value="#ffcccc"></c:set>
											</c:if>
											<c:if test="${p.verificada==p.remitida}">
												<c:set var="coloRE" scope="page" value="#adebad"></c:set>
											</c:if>
											
											<fmt:parseNumber var="i" type="number" value="${i+1}"/>
											
											<tr>
												<td class="text-center"><c:out value="${p.usuario.descripcion}"></c:out></td>
												<td class="text-center"><c:out value="${p.idPedido}"></c:out></td>
												<td class="text-center"><c:out value="${p.articulo}"></c:out></td>
												<td class="text-center"><c:out value="${p.descripcion}"></c:out></td>
												<!--<td class="text-center"><c:out value="${p.stockOrigen}"></c:out></div></td>
												<c:if test="${p.stockOSAP>=p.verificada}">
													<td class="text-center"><c:out value="${p.stockOSAP}"></c:out></div></td>
												</c:if>
												<c:if test="${p.stockOSAP<p.verificada}">
													<td style="vertical-align: middle; background-color:ffcccc"><div align="center"><c:out value="${p.stockOSAP}"></c:out></div></td>
												</c:if>-->
												<td class="text-center"><c:out value="${p.destino.descripcion}"></c:out></td>
												<td style="vertical-align: middle;"><div align="center" id="sol-${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}"><c:out value="${p.sol}"></c:out></div></td>
												<td style="background-color: ${coloPI}; text-align:center; vertical-align: middle;" id="pick-${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}"><c:out value="${p.pick}"></c:out></td>
												<td style="background-color: ${coloVE};" class="text-center"><input class="form-control" style="width: 80px;" type="number" 
												value="${p.verificada}" onchange="ChangeQuantity('${p.articulo}','${p.origen.id}','${p.destino.id}','${p.idPedido}','${p.solicitud}','${p.idPicking}')" 
												name="${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}" id="${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}"/></td>
												<td style="background-color: ${coloRE};" class="text-center"><input class="form-control" style="width: 80px;" type="number" 
												value="${p.remitida}" onchange="ChangeQuantityR'${p.articulo}','${p.origen.id}','${p.destino.id}','${p.idPedido}','${p.solicitud}','${p.idPicking}')" 
												name="'R'${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}" id="'R'${p.articulo}-${p.destino.id}-${p.idPedido}-${p.solicitud}"/></td>
												
												
											</tr>
												
										</c:forEach>
									</tbody>
									
                                </table>
                                 
							
                            </div>
                            <br>
                            <div class="text-center col-sm-12 col-lg-12"> 
								<div class="flex-container">
									<a href="#" onclick="corregir()" class="btn btn-info" id="corregir">Corregir</a>
									<a href="#" onclick="Transferir(),this.addEventListener('click', clickStopper, false);return false;"  class="btn btn-danger">Grabar</a>									
								</div>
							</div>
                            
                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
            
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
