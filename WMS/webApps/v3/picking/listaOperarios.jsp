<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dataTypes.DataIDDescripcion"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
        <!-- /. NAV SIDE  -->
        <c:set var = "paramRemito" scope = "page" value = "0"/>
        <c:set var = "puede" scope = "page" value = ""/>
        <c:set var = "pickingBulto" scope = "page" value = "0"/>
        <c:set var = "puedePickingBulto" scope = "page" value = ""/>
        <c:set var = "pickingBultosGenerales" scope = "page" value = "0"/>
        <c:set var = "puedePickingBultosGenerales" scope = "page" value = ""/>
        <c:set var = "pickingArticulosAlmacen" scope = "page" value = "0"/>
        <c:set var = "puedePickingArticulosAlmacen" scope = "page" value = ""/>
        <c:set var = "priridadAlmacen" scope = "page" value = ""/>
        <c:set var = "prioridadBulto" scope = "page" value = ""/>
        <c:set var = "displaySwitchs" scope = "page" value = "d-block"/>
        <c:forEach var="d" items="${uLogeado.seguridadUI}">
				<c:if test="${d=='div_param_remito_picking'}">
					<c:set var = "paramRemito" scope = "page" value = "1"/>
				</c:if>
				<c:if test="${d=='div_param_picking_bulto'}">
					<c:set var = "pickingBulto" scope = "page" value = "1"/>
				</c:if>
				<c:if test="${d=='div_param_picking_bultos_generales'}">
					<c:set var = "pickingBultosGenerales" scope = "page" value = "1"/>
				</c:if>
				<c:if test="${d=='div_param_articulosAlmacen'}">
					<c:set var = "pickingArticulosAlmacen" scope = "page" value = "1"/>
				</c:if>
			</c:forEach>
			<c:if test="${paramRemito == 0}">
				<c:set var = "puede" scope = "page" value = "style='display: none;'"/>
			</c:if>
			<c:if test="${pickingBulto == 0}">
				<c:set var = "puedePickingBulto" scope = "page" value = "style='display: none;'"/>
				<c:set var = "displaySwitchs" scope = "page" value = "d-none"/>
			</c:if>
			<c:if test="${pickingBultosGenerales == 0}">
				<c:set var = "puedePickingBultosGenerales" scope = "page" value = "style='display: none;'"/>
			</c:if>
			<c:if test="${pickingArticulosAlmacen == 0}">
				<c:set var = "puedePickingArticulosAlmacen" scope = "page" value = "style='display: none;'"/>
			</c:if>
			<c:if test="${paramPrioridadBulto == false}">
				<c:set var = "prioridadAlmacen" scope = "page" value = "checked"/>
			</c:if>
			<c:if test="${paramPrioridadBulto == true}">
				<c:set var = "prioridadBulto" scope = "page" value = "checked"/>
			</c:if>
		
            	<div class="row">
                	<div class="col-md-12">
                    	<h3>Tareas&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Nueva Tarea&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i>&nbsp;Seleccionar Usuarios</h3>   
                        <h6>Seleccione usuarios para ejecutar la tarea </h6>
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
                   <div class="card">
                   <div class="accordion" id="accordionExample"> 
                     <div class="card-header" id="headingOne">
	                     <h5 class="mb-0"><a href="#!" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" 
	                     aria-controls="collapseOne">
	                     	Distribucion de carga de trabajo</a>
	                     </h5>                     	
                     </div>
                     <div id="collapseOne" class=" card-body collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                     	<div class="card-block pb-0" style="padding-top: 0px;">
                            <div id="bar-chart22" class="bar-chart2" style="height:350px;"></div>
                        </div>
                     </div>
                     </div>
                     
                     <form id="AltaTareaII" method="post" action="<%=basePath%>/AltaTareaII.do">
                     
                     	<div class="card-header" ${puede}>
                     	<h5>
                          	<label class="p-2" for="estanteria">Remision de picking: un picking por bulto&nbsp;</label>
							<div class="switch d-inline m-r-10">
								<c:choose>
									<c:when test="${paramRemision}">
										<input type="checkbox" id="switch-1" name="checkParametro"
										value="1" checked> <label for="switch-1" class="cr"></label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="switch-1" name="checkParametro"
										value="1"> <label for="switch-1" class="cr"></label>
									</c:otherwise>
								</c:choose>
							</div>
                         </h5> 	
                     	</div>
                     	
                     	<div class="card-header" ${puedePickingBulto}>
                     	<h5>
                          	<label class="p-2" for="estanteria">Picking por Bultos&nbsp;</label>
							<div class="switch d-inline m-r-10">
								<c:choose>
									<c:when test="${paramPickingBulto}">
										<input type="checkbox" id="switch-2" name="checkParametroPB"
										value="1" checked onclick="CheckPB()"> <label for="switch-2" class="cr"></label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="switch-2" name="checkParametroPB"
										value="1" onclick="CheckPB()"> <label for="switch-2" class="cr"></label>
									</c:otherwise>
								</c:choose>
							</div>
							<br>
							<div class="controls form-inline"> 
								<label class="p-2" for="estanteria">Cantidad Minima por SKU&nbsp;</label>
								<input class="form-control bg-white" type="number" min="0" value = "${paramBultoCantMinxSku}" name="CantMinxSku" id="CantMinxSku" style="width: 20%"/>
							</div>
							<br>
							<div id="divSwitch3" class="${displaySwitchs}">
							<label class="p-2" for="estanteria">Picking en bultos abiertos&nbsp;</label>
							<div class="switch d-inline m-r-10">
								<c:choose>
									<c:when test="${paramPickingBultosGenerales}">
										<input type="checkbox" id="switch-3" name="checkParametroPBG"
										value="1" checked> <label for="switch-3" class="cr"></label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="switch-3" name="checkParametroPBG"
										value="1"> <label for="switch-3" class="cr"></label>
									</c:otherwise>
								</c:choose>
							</div>
							</div>
							
							<div id="divSwitch4" class="${displaySwitchs}">
							<label class="p-2" for="estanteria">B?squeda en articulos almacen&nbsp;</label>
							<div class="switch d-inline m-r-10">
								<c:choose>
									<c:when test="${paramPickingArticulosAlmacen}">
										<input type="checkbox" id="switch-4" name="checkParametroPAA"
										value="1" onclick="CheckBAA()" checked> <label for="switch-4" class="cr"></label>
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="switch-4" name="checkParametroPAA"
										value="1" onclick="CheckBAA()"> <label for="switch-4" class="cr"></label>
									</c:otherwise>
								</c:choose>
							</div>
							</div>
							
							<div id="divRadio" class="${displaySwitchs}">
								<label class="p-2" for="estanteria" style="float: left;">Prioridad de reserva &nbsp;</label>									
                                <div class="radio d-inline">
                                    <input type="radio" name="prioridadReserva" id="radio-1" value="0" ${prioridadAlmacen}>
                                    <label for="radio-1" class="cr">Almacen</label>
                                </div>                            
                                <div class="radio d-inline">
                                    <input type="radio" name="prioridadReserva" id="radio-2" value="1" ${prioridadBulto}>
                                    <label for="radio-2" class="cr">Bultos</label>
                                </div>                                    								
							</div>
                         </h5> 	
                     	</div>
                     
                    <div class="card">
                     <div class="card-header">
                     	<h5>
                          	Lista de Usuarios
                         </h5> 	
                     </div>
                     <div class="card-body">
                     		<div class="row">  
						     <div class="col-md-2"> </div>  
						     <div class="col-md-8">                     
	                            
	                            <div class="table-responsive">
	                            	<table class="table table-striped table-bordered table-hover" id="encuentra-operarios">
	                                	<thead>
	                                    	<tr>
	                                        	<th class="text-center">Usuario</th>
												<th class="text-center" style="width: 40px;">Seleccionar</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                    <c:forEach var="o" items="${operarios}">
											<tr class="gradeD">
												<td><div align="center"><c:out value="${o.descripcion}"></c:out></div></td>
												<td style="width: 40px; text-align: center;">
													<div class="checkbox checkbox-fill d-inline">
															<input type="checkbox" name="${o.id}" id="checkbox-fill-${o.id}">
															<label for="checkbox-fill-${o.id}" class="cr"></label>
													</div>
												</td>
												
											</tr>
										</c:forEach>
										</tbody>
										
	                                </table>
	                                
	                               </div>
									<div class="text-right col-sm-12 col-lg-12 mt-5"> 
										<div class="flex-container">
		                                 	<button type="submit" name="Submit" class="btn btn-success" id="altaUsu">Asignar usuarios</button>
										</div> 
	                                </div>
	                            
                          </div>  
                  		<div class="col-md-2"> </div>   
                  		</div>           
                  </div>  
						            
                        </div>
                        </form>
                     </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
         
        <script type="text/javascript">
     // [ Bar Chart2 ] start
     $(document).ready(function() {
        var chart = AmCharts.makeChart("bar-chart22", {
            "type": "serial",
            "theme": "light",
            "marginTop": 10,
            "marginRight": 0,
            "valueAxes": [{
                "id": "v1",
                "position": "left",
                "axisAlpha": 0,
                "lineAlpha": 0,
                "autoGridCount": false,
                "labelFunction": function(value) {
                    return +Math.round(value) ;
                }
            }],
            "graphs": [{
                "id": "g1",
                "valueAxis": "v1",
                "lineColor": ["#1de9b6", "#1dc4e9"],
                "fillColors": ["#1de9b6", "#1dc4e9"],
                "fillAlphas": 1,
                "type": "column",
                "title": "Unidade de Picking",
                "valueField": "picking",
                "columnWidth": 0.3,
                "legendValueText": "[[value]]",
                "balloonText": "[[title]]<br /><b style='font-size: 130%'>[[value]]</b>"
            }],
            "chartCursor": {
                "pan": true,
                "valueLineEnabled": true,
                "valueLineBalloonEnabled": true,
                "cursorAlpha": 0,
                "valueLineAlpha": 0.2
            },
            "categoryField": "Usuario",
            "categoryAxis": {
                "dashLength": 1,
                "gridAlpha": 0,
                "axisAlpha": 0,
                "lineAlpha": 0,
                "minorGridEnabled": true
            },
            "legend": {
                "useGraphSettings": true,
                "position": "top"
            },
            "balloon": {
                "borderThickness": 1,
                "shadowAlpha": 0
            },
            "dataProvider": [
            	<%   
                ArrayList<DataIDDescripcion> distribucionCarga = (ArrayList<DataIDDescripcion>) session.getAttribute("distribucionCarga");
                for (DataIDDescripcion dc : distribucionCarga) {
                	out.print("{");
        			out.print("\"Usuario\": \""+dc.getDescripcion()+"\",");
        	    	out.print("\"picking\": "+dc.getId()+",");
        	    	out.print("}, ");
                }
        	%>
        	]
        });
        
        
		
	    $("#altaUsu").click(function() {
	      // disable button
	      $(this).prop("disabled", true);
	      // add spinner to button
	      $(this).html(
	        `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Creando tarea...`
	      );
	      document.getElementById("AltaTareaII").submit();
	    });
		 		      
	
     });
        // [ Bar Chart2 ] end
        </script>  
        
        <script>
        	function CheckPB(){
        		var value = document.getElementById('switch-2').checked;
        		if(value){
        			document.getElementById('CantMinxSku').value = ${paramBultoCantMinxSku};
        			document.getElementById('CantMinxSku').disabled = false;
        			document.getElementById('divSwitch3').className = "d-block";
        			document.getElementById('divSwitch4').className = "d-block";
        			document.getElementById('divRadio').className = "d-block";
        		}
        		else{
        			document.getElementById('CantMinxSku').value = 0;
        			document.getElementById('CantMinxSku').disabled = true;
        			document.getElementById('divSwitch3').className = "d-none";
        			document.getElementById('divSwitch4').className = "d-none";
        			document.getElementById('divRadio').className = "d-none";
        		}
        	}
        	
        	function CheckBAA(){
        		var value = document.getElementById('switch-4').checked;
        		if(value){
        			document.getElementById('divRadio').className = "d-block";
        		}
        		else{
        			document.getElementById('divRadio').className = "d-none";
        		}
        	}
        </script>  
           
		    
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
