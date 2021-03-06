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
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		
            <div class="row">   
            	<div class="col-xs-12 col-xl-12 col-md-12">
            	 	<c:if test="${tablaMatrizEstados!=null}">
                     <div class="card">
                        <div class="card-header">
                        	 <h5>Hist?rico Pedidos ?ltimos ${cantDiasMatriz} d?as</h5>
                             <div class="card-header-right">
					            <div class="btn-group card-option">
					                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					                    <i class="feather icon-more-horizontal"></i>
					                </button>
					                <ul class="list-unstyled card-option dropdown-menu dropdown-menu-right">
					                    <li class="dropdown-item full-card"><a href="#!"><span><i class="feather icon-maximize"></i> Maximizar</span><span style="display:none"><i class="feather icon-minimize"></i> Restaurar</span></a></li>
					               		<li class="dropdown-item minimize-card"><a href="#!"><span><i class="feather icon-minus"></i> Colapsar</span><span style="display:none"><i class="feather icon-plus"></i> Expandir</span></a></li>
				                   		<li class="dropdown-item reload-card"><a href="<%=basePath%>recargarDashboard.do"><i class="feather icon-refresh-cw"></i> Recargar</a></li>
				                    	<li class="dropdown-item close-card"><a href="#!"><i class="feather icon-trash"></i> Remover</a></li>
					                </ul>
					            </div>
					        </div>
                        </div>
                       
                        <div class="card-body">
                             <div class="table-responsive">
                             	${tablaMatrizEstados}
                            </div>
                        </div>
                    </div> 
                   </c:if>  
             </div> 
             <div class="row">  
			    <c:forEach var="t" items="${tarjetas}">
			    	<c:choose>
			    		<c:when test = "${t.tipo==1}">
					    	<div class="<c:if test="${t.cantidadSTR=='0'}">d-none </c:if> col-sm-12 col-md-6 col-xl-4">
			                       <div class="card rounded">
			                           <div class="card-block">
			                               <h6 class="mb-4">${t.titulo}</h6>
			                               <div class="row d-flex align-items-center">
			                               
			                                   <div class="col-9" style="max-width: 70%;">
			                                       <h3 class="f-w-300 d-flex align-items-center m-b-0" style="max-width: 20%;"><i class="${t.icon}  ${t.bgcolor} f-30 m-r-10"></i>${t.cantidadSTR}</h3>
			                                   </div>
			
			                                   <div class="col-3 text-right" style="max-width: 30%;">
			                                       <p class="m-b-0">${t.porcentaje}%</p>
			                                   </div>
			                              
			                               </div>
			                               <div class="progress m-t-30" style="height: 7px;">
			                                   <div class="progress-bar progress-c-theme" role="progressbar" style="width: ${t.porcentaje}%;" aria-valuenow="${t.porcentaje}" aria-valuemin="0" aria-valuemax="100"></div>
			                               </div>
			                           </div>
			                       </div>
			                   </div>
			            </c:when>
			            <c:when test = "${t.tipo==2}">
			            	<c:if test="${t.idTarjeta==2}">
				            <div class="col-sm-12 col-md-12 col-xl-8">
							    <div class="card">
								    <div class="card-header">
								        <h5>${t.titulo}</h5>
								        <div class="card-header-right">
								            <div class="btn-group card-option">
								                <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								                    <i class="feather icon-more-horizontal"></i>
								                </button>
								                <ul class="list-unstyled card-option dropdown-menu dropdown-menu-right">
								                    <li class="dropdown-item full-card"><a href="#!"><span><i class="feather icon-maximize"></i> Maximizar</span><span style="display:none"><i class="feather icon-minimize"></i> Restaurar</span></a></li>
								                	<li class="dropdown-item minimize-card"><a href="#!"><span><i class="feather icon-minus"></i> Colapsar</span><span style="display:none"><i class="feather icon-plus"></i> Expandir</span></a></li>
							                   		<li class="dropdown-item reload-card"><a href="<%=basePath%>recargarDashboard.do"><i class="feather icon-refresh-cw"></i> Recargar</a></li>
							                    	<li class="dropdown-item close-card"><a href="#!"><i class="feather icon-trash"></i> Remover</a></li>
								                </ul>
								            </div>
								        </div>
								    </div>
								    <div class="card-block">
								    	<h5>&emsp;&emsp;&emsp;Vendidos &emsp; Procesados</h5>
								        <div id="line-area2" class="lineAreaDashboard" style="height:350px;"></div>
								    </div>
								</div>
							</div>  
							</c:if>
			            </c:when>
			            <c:when test = "${t.tipo==3}">
			            	<div class="<c:if test="${t.cantidadSTR==0}">d-none </c:if> col-sm-6 col-md-6 col-xl-4">
							    <div class="card text-white rounded bg-secondary">
								    <div class="card-block">
								        <h5 class="text-center text-white">${t.titulo}</h5>
								        <div class="row align-items-center justify-content-center">
								        	<div class="col text-left">
								                <i class="${t.icon} ${t.bgcolor} f-30 m-r-10"></i>
								            </div>
								            <div class="col-auto text-center label">
								                <h3 class="text-white f-w-300 m-t-20">${t.cantidadSTR}</h3>
								                <span>PENDIENTES</span>
								            </div>
								            <div class="col text-right">
								             	<h4 class="text-white">${t.porcentaje}%</h4>
								             </div>
								        </div>
								        <div class="progress m-t-30" style="height: 7px;">
						                      <div class="progress-bar progress-c-theme" role="progressbar" style="width: ${t.porcentaje}%;" aria-valuenow="${t.porcentaje}" aria-valuemin="0" aria-valuemax="100"></div>
						                </div>
								        <div class="leads-progress mt-3">
								        	<c:forEach var="in" items="${tarjetas}">
								        		<c:if test = "${in.tipo==30}">
								        			<h6 class="<c:if test="${in.cantidadSTR==0}">d-none </c:if> text-white f-w-300 mt-4 "><a href="<%=basePath%>${in.href}" class="text-white">${in.titulo}<strong><label class="label ${in.bgcolor} float-right" >${in.cantidadSTR}</label></strong></a></h6>
								        		</c:if>
								        	</c:forEach>
								        </div>
								    </div>
								</div>
							</div>
			            </c:when>
			            <c:when test = "${t.tipo==4}">
			            	<div class="<c:if test="${t.cantidadSTR==0}">d-none </c:if> col-sm-6 col-md-6 col-xl-4">
							    <div class="card text-white rounded bg-secondary">
								    <div class="card-block">
								        <h5 class="text-center text-white">${t.titulo}</h5>
								        <div class="row align-items-center justify-content-center">
								        	<div class="col text-left">
								                <i class="${t.icon} ${t.bgcolor} f-30 m-r-10"></i>
								            </div>
								            <div class="col-auto text-center label">
								                <h3 class="text-white f-w-300 m-t-20">${t.cantidadSTR}</h3>
								                <span>PENDIENTES</span>
								            </div>
								            <div class="col text-right">
								             	<h4 class="text-white">${t.porcentaje}%</h4>
								             </div>
								        </div>
								        <div class="progress m-t-30" style="height: 7px;">
						                      <div class="progress-bar progress-c-theme" role="progressbar" style="width: ${t.porcentaje}%;" aria-valuenow="${t.porcentaje}" aria-valuemin="0" aria-valuemax="100"></div>
						                </div>
								        <div class="leads-progress mt-3">
								        	<c:forEach var="in" items="${tarjetas}">
								        		<c:if test = "${in.tipo==40}">
								        			<h6 class="<c:if test="${in.cantidadSTR==0}">d-none </c:if> text-white f-w-300 mt-4 "><a href="<%=basePath%>${in.href}" class="text-white">${in.titulo}<strong><label class="label ${in.bgcolor} float-right" >${in.cantidadSTR}</label></strong></a></h6>
								        		</c:if>
								        	</c:forEach>
								        </div>
								    </div>
								</div>
							</div>
			            </c:when>
			            <c:when test = "${t.tipo==5}">
			            
			            	<div class="col-xl-4 col-md-6">
                                    <div class="card statistial-visit">
                                        <div class="card-header">
                                            <h5 style="font-size:24px;">${t.titulo}</h5>
                                        </div>
                                        <div class="card-block">
                                            
                                            <c:forEach var="in" items="${tarjetas}">
								        		<c:if test = "${in.tipo==50 && in.padre==t.idTarjeta}">
								        		<div class="media mt-4">
	                                                <div class="photo-table" style="width: 80%;">
	                                                    <h3 style="font-size:17px;"> ${in.titulo}</h3>
	                                                    <div class="progress">
	                                                        <div class="progress-bar progress-c-theme" role="progressbar" style="width:${in.cantidad}%;height:6px;" aria-valuenow="${in.cantidad}" aria-valuemin="0" aria-valuemax="100"></div>
	                                                    </div>
	                                                </div>
	                                                <div class="media-body">
	                                                    <label class="label theme-bg text-white f-18 f-w-400 float-right" style="margin-top: 10px;">${in.cantidadSTR}%</label>
	                                                </div>
                                            	</div>
                                            </c:if>
								        	</c:forEach>   
								        	<span>&nbsp;</span>
								        	<div class="media mt-4">
									        	<span class="d-block"><strong style="font-size: 17px; color: #333333;">${t.cantidadSTR}</strong> Unidades en los ultimos 30 dias </span> 
                                            </div>                                 
                                        </div>
                                    </div>
                                </div>
			            </c:when>
	                </c:choose>
			    </c:forEach>
			    
			    
			   	 				
			    
			    
           	</div>
           </div>
                <!-- /. ROW  -->
  <script type="text/javascript">
			$(document)
					.ready(
							function() {

								var chart = AmCharts
										.makeChart(
												"line-area2",
												{
													"type" : "serial",
													"theme" : "light",
													"marginTop" : 10,
													"marginRight" : 0,
													"dataProvider" : [
		<%   
                ArrayList<DataIDDescripcion> estadisticaVenta = (ArrayList<DataIDDescripcion>) session.getAttribute("statsVenta");
            	if (estadisticaVenta!=null)
            	{
	                for (DataIDDescripcion ev : estadisticaVenta) {
	                	out.print("{");
	        			out.print("\"year\": \""+ev.getDescripcion()+"\",");
	        	    	out.print("\"value\": "+ev.getId()+",");
	        	    	out.print("\"value2\": "+ev.getIdB()+",");
	        	    	out.print("}, ");
	                }
            	}
        	%>  
        	],
            "valueAxes": [{
                "axisAlpha": 0,
                "position": "left"
            }],
            "graphs": [{
                "id": "g1",
                "balloonText": "[[category]]<br><b><span style='font-size:14px;'>[[value]]</span></b>",
                "bullet": "round",
                "lineColor": "#1de9b6",
                "lineThickness": 3,
                "negativeLineColor": "#1de9b6",
                "valueField": "value"
            }, {
                "id": "g2",
                "balloonText": "[[category]]<br><b><span style='font-size:14px;'>[[value]]</span></b>",
                "bullet": "round",
                "lineColor": "#10adf5",
                "lineThickness": 3,
                "negativeLineColor": "#10adf5",
                "valueField": "value2"
            }],
            "chartCursor": {
                "cursorAlpha": 0,
                "valueLineEnabled": true,
                "valueLineBalloonEnabled": true,
                "valueLineAlpha": 0.3,
                "fullWidth": true
            },
            "categoryField": "year",
            "categoryAxis": {
                "minorGridAlpha": 0,
                "minorGridEnabled": true,
                "gridAlpha": 0,
                "axisAlpha": 0,
                "lineAlpha": 0
            },
            "legend": {
                "useGraphSettings": true,
                "position": "top"
            },
        });
    });
</script> 
                
	      					
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
