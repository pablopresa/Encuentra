<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div class="clearfix" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-96" data-genuitec-path="/encuentra/webApps/gentelella-master/gentelella-master/production/tablas.jsp"></div>
            <div class="row">
            
           
            
            <c:if test="${tablaDepos!=null}">
            <!-- DEPOSITOS -->
            
              <div class="col-md-6 col-sm-6 col-xs-12" id="divTablaDepos">
                <div class="x_panel">
                  <div class="x_title">
                    <h4>Picking por deposito Desde ${fini} Hasta ${ffin}</h4>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li><a id="cerrartablaDepos"><i class="fa fa-close"></i></a></li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaDepos']}',1,'divTablaDepos')">Fijar a mi Dashboard</a>
                          </li>
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaDepos']}',0,'divTablaDepos')">Quitar de mi Dashboard</a>
                          </li>
                        </ul>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable-fixed-header" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>Deposito</th>
                          <th>Sol.</th>
                          <th>Pick.</th>
                          <th>Precisión</th>
                        </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="a" items="${tablaDepos}">
	                        <tr>
	                          <td>${a.idString1}</td>
	                          <td>${a.idInt3S}</td>
	                          <td>${a.idInt2S}</td>
	                          <td>
	                          		<c:if test="${a.valorDouble1<95}"><i class="red"><i class="fa fa-sort-desc"></i><c:out value="${a.valorDouble1S}"></c:out>% </i></c:if>
	                          		<c:if test="${a.valorDouble1>=95}"><i class="green"><i class="fa fa-sort-asc"></i><c:out value="${a.valorDouble1S}"></c:out>% </i></c:if>
	                          </td>
	                        </tr>
                        </c:forEach>
                      </tbody>
                      <tfoot>
                        <tr>
                          <th><c:out value="${ttlTblDepo.idString1}"></c:out></th>
                          <th><c:out value="${ttlTblDepo.idInt3S}"></c:out></th>
                          <th><c:out value="${ttlTblDepo.idInt2S}"></c:out></th>
                          <th>
	                      	<c:if test="${ttlTblDepo.valorDouble1<95}"><i class="red"><i class="fa fa-sort-desc"></i><c:out value="${ttlTblDepo.valorDouble1S}"></c:out>% </i></c:if>
	                        <c:if test="${ttlTblDepo.valorDouble1>=95}"><i class="green"><i class="fa fa-sort-asc"></i><c:out value="${ttlTblDepo.valorDouble1S}"></c:out>% </i></c:if>
	                      </th>
                        </tr>
                      </tfoot>
                    </table>

                  </div>
                </div>
              </div>
			</c:if>
			<!-- END DEPOSITOS -->
              
              <!-- marcas -->
 			<c:if test="${tablaMarcas!=null}">
            
            
              <div class="col-md-6 col-sm-6 col-xs-12" id="divTablaMarcas">
                <div class="x_panel">
                  <div class="x_title">
                    <h4>Picking por Marca Desde ${fini} Hasta ${ffin}</h4>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
	                   <li><a id="cerrartablaMarcas"><i class="fa fa-close"></i></a></li>
	                   <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaMarcas']}',1,'divTablaMarcas')">Fijar a mi Dashboard</a>
                          </li>
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaMarcas']}',0,'divTablaMarcas')">Quitar de mi Dashboard</a>
                          </li>
                        </ul>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable-fixed-header2" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>Marca</th>
                          <th>Sol.</th>
                          <th>Pick.</th>
                          <th>Precisión</th>
                        </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="a" items="${tablaMarcas}">
	                        <tr>
	                          <td>${a.idString1}</td>
	                          <td>${a.idInt3S}</td>
	                          <td>${a.idInt2S}</td>
	                          <td>
	                          		<c:if test="${a.valorDouble1<95}"><i class="red"><i class="fa fa-sort-desc"></i><c:out value="${a.valorDouble1S}"></c:out>% </i></c:if>
	                          		<c:if test="${a.valorDouble1>=95}"><i class="green"><i class="fa fa-sort-asc"></i><c:out value="${a.valorDouble1S}"></c:out>% </i></c:if>
	                          </td>
	                        </tr>
                        </c:forEach>
                      </tbody>
                       <tfoot>
                        <tr>
                          <th><c:out value="${ttlTblMarca.idString1}"></c:out></th>
                          <th><c:out value="${ttlTblMarca.idInt3S}"></c:out></th>
                          <th><c:out value="${ttlTblMarca.idInt2S}"></c:out></th>
                          <th>
	                      	<c:if test="${ttlTblMarca.valorDouble1<95}"><i class="red"><i class="fa fa-sort-desc"></i><c:out value="${ttlTblMarca.valorDouble1S}"></c:out>% </i></c:if>
	                        <c:if test="${ttlTblMarca.valorDouble1>=95}"><i class="green"><i class="fa fa-sort-asc"></i><c:out value="${ttlTblMarca.valorDouble1S}"></c:out>% </i></c:if>
	                      </th>
                        </tr>
                      </tfoot>
                    </table>

                  </div>	
                </div>
              </div>
			</c:if>
			
			<!-- end marcas -->

			
               <c:if test="${tablaUsuarios!=null}">
            <!-- usuarios -->
            
              <div class="col-md-6 col-sm-6 col-xs-12" id="divTablaUsuarios">
                <div class="x_panel">
                  <div class="x_title">
                    <h4>Picking por Usuario Desde ${fini} Hasta ${ffin}</h4>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li><a id="cerrartablaUsers"><i class="fa fa-close"></i></a></li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaUsuarios']}',1,'divTablaUsuarios')">Fijar a mi Dashboard</a>
                          </li>
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaUsuarios']}',0,'divTablaUsuarios')">Quitar de mi Dashboard</a>
                          </li>
                        </ul>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable-fixed-header3" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>Usuario</th>
                          <th>Sol.</th>
                          <th>Pick.</th>
                          <th>Precisión</th>
                        </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="a" items="${tablaUsuarios}">
	                        <tr>
	                          <td>${a.idString1}</td>
	                          <td>${a.idInt3S}</td>
	                          <td>${a.idInt2S}</td>
	                          <td>
	                          		<c:if test="${a.valorDouble1<95}"><i class="red"><i class="fa fa-sort-desc"></i><c:out value="${a.valorDouble1S}"></c:out>% </i></c:if>
	                          		<c:if test="${a.valorDouble1>=95}"><i class="green"><i class="fa fa-sort-asc"></i><c:out value="${a.valorDouble1S}"></c:out>% </i></c:if>
	                          </td>
	                        </tr>
                        </c:forEach>
                      </tbody>
                      <tfoot>
                        <tr>
                          <th><c:out value="${ttlTblUsuarios.idString1}"></c:out></th>
                          <th><c:out value="${ttlTblUsuarios.idInt3S}"></c:out></th>
                          <th><c:out value="${ttlTblUsuarios.idInt2S}"></c:out></th>
                          <th>
	                      	<c:if test="${ttlTblUsuarios.valorDouble1<95}"><i class="red"><i class="fa fa-sort-desc"></i><c:out value="${ttlTblUsuarios.valorDouble1S}"></c:out>% </i></c:if>
	                        <c:if test="${ttlTblUsuarios.valorDouble1>=95}"><i class="green"><i class="fa fa-sort-asc"></i><c:out value="${ttlTblUsuarios.valorDouble1S}"></c:out>% </i></c:if>
	                      </th>
                        </tr>
                      </tfoot>
                    </table>

                  </div>
                </div>
              </div>
			</c:if>
			<!-- END usuarios -->

               <!-- clases -->
 			<c:if test="${tablaClases!=null}">
            
            
              <div class="col-md-6 col-sm-6 col-xs-12" id="divTablaClase">
                <div class="x_panel">
                  <div class="x_title">
                    <h4>Picking por Clase Desde ${fini} Hasta ${ffin}</h4>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
	                   <li><a id="cerrartablaClase"><i class="fa fa-close"></i></a></li>
	                   <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaClases']}',1,'divTablaClase')">Fijar a mi Dashboard</a>
                          </li>
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaClases']}',0,'divTablaClase')">Quitar de mi Dashboard</a>
                          </li>
                        </ul>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable-fixed-header4" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>Clase</th>
                          <th>Sol.</th>
                          <th>Pick.</th>
                          <th>Precisión</th>
                        </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="a" items="${tablaClases}">
	                        <tr>
	                          <td>${a.idString1}</td>
	                          <td>${a.idInt3S}</td>
	                          <td>${a.idInt2S}</td>
	                          <td>
	                          		<c:if test="${a.valorDouble1<95}"><i class="red"><i class="fa fa-sort-desc"></i><c:out value="${a.valorDouble1S}"></c:out>% </i></c:if>
	                          		<c:if test="${a.valorDouble1>=95}"><i class="green"><i class="fa fa-sort-asc"></i><c:out value="${a.valorDouble1S}"></c:out>% </i></c:if>
	                          </td>
	                        </tr>
                        </c:forEach>
                      </tbody>
                       <tfoot>
                        <tr>
                          <th><c:out value="${ttlTblClase.idString1}"></c:out></th>
                          <th><c:out value="${ttlTblClase.idInt3S}"></c:out></th>
                          <th><c:out value="${ttlTblClase.idInt2S}"></c:out></th>
                          <th>
	                      	<c:if test="${ttlTblClase.valorDouble1<95}"><i class="red"><i class="fa fa-sort-desc"></i><c:out value="${ttlTblClase.valorDouble1S}"></c:out>% </i></c:if>
	                        <c:if test="${ttlTblClase.valorDouble1>=95}"><i class="green"><i class="fa fa-sort-asc"></i><c:out value="${ttlTblClase.valorDouble1S}"></c:out>% </i></c:if>
	                      </th>
                        </tr>
                      </tfoot>
                    </table>

                  </div>
                </div>
              </div>
			</c:if>
			
			     <!-- TotalesPicking -->
 			<c:if test="${tablaDatosPicking!=null}">
            
            
              <div class="col-md-12 col-sm-12 col-xs-12" id="divDatosPicking">
                <div class="x_panel">
                  <div class="x_title">
                    <h4>Detalles de pickings Desde ${fini} Hasta ${ffin}</h4>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
	                   <li><a id="cerrartablaDatosPicking"><i class="fa fa-close"></i></a></li>
	                   <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaDatosPicking']}',1,'divDatosPicking')">Fijar a mi Dashboard</a>
                          </li>
                          <li><a href="#" onclick="adRemoveTabla('${tablasAct['tablaDatosPicking']}',0,'divDatosPicking')">Quitar de mi Dashboard</a>
                          </li>
                        </ul>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable-fixed-header4" class="table table-striped table-bordered">
                      <thead>
                         <tr>
				          <th>idPicking</th>
				          <th>fecha</th>
				          <th>destinos</th>
				          <th>Usuario</th>
				          <th>Sol.</th>
				          <th>Pic.</th>
				          <th>Ver.</th>
				          <th>T. Pic.</th>
				          <th>T. Ver.</th>
				          <th>Unidades Por Hora</th>
				          <th>Unidades Hora verificacion</th>
				          <th>Tiempo Pausado</th>
				          <th>Inicio Picking</th>
				          <th>Fin Picking</th>
				          <th>Inicio Verificacion</th>
				          <th>Fin Verificacion</th>
				        </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="a" items="${tablaDatosPicking}">
                      	
	                        <tr>
	                          <td><a href='javascript:filtrar(${a.id})'>${a.id}</a></td>
	                          <td>${a.fecha}</td>
					          <td>${a.destinos}</td>
					          <td>${a.nickname}</td>
					          <td>${a.solicitada}</td>
					          <td>${a.pickeadas}</td>
					          <td>${a.verificadas}</td>
					          <td>${a.tiempoPicking}</td>
					          <td>${a.tiempoVerificacion}</td>
					          <td>${a.unidadesXHora}</td>
					          <td>${a.unidadesXHora_verif}</td>
					          <td>${a.tiempoPausa}</td>
					          <td>${a.inicioPicking}</td>
					          <td>${a.finPicking}</td>
					          <td>${a.inicioVerificacion}</td>
					          <td>${a.finVerificacion}</td>
	                        </tr>
	                    
                        </c:forEach>
                      </tbody>
                       
                    </table>

                  </div>
                </div>
                
                
                <!-- aca sigue el detalle -->
                <div class="x_panel">
                  <div class="x_title">
                    <h4>Detalles de Lineas de pickings Desde ${fini} Hasta ${ffin}</h4>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
	                   <li><a id="cerrartablaDatosPicking"><i class="fa fa-close"></i></a></li>
	                   <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <table id="datatable-fixed-header5" class="table table-striped table-bordered">
                      <thead>
                         <tr>
				          <th>idPicking</th>
						  <th>idPedido</th>
				          <th>nickname</th>
						  <th>marca</th>
				          <th>clase</th>
				          <th>categoria</th>
				          <th>idArticulo</th>
				          <th>oJOs</th>
				          <th>destino</th>
				          <th>cantidad</th>
						  <th>Picked</th>
				          <th>Verif</th>
						  <th>picked_at</th>
				          <th>verif_at</th>
				        </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="c" items="${tablaDatosPicking}">
                      		<c:forEach var="a" items="${c.lineas}">
	                      	    <tr onclick="filtrar(${a.idPicking})">
		                          <td>${a.idPicking}</td>
		                          <td>${a.idPedido}</td>
						          <td>${a.nickname}</td>
						          <td>${a.marca}</td>
						          <td>${a.clase}</td>
						          <td>${a.categoria}</td>
						          <td>${a.idArticulo}</td>
						          <td>${a.oJOs}</td>
						          <td>${a.destino}</td>
						          <td>${a.cantidad}</td>
						          <td>${a.picked}</td>
						          <td>${a.verif}</td>
						          <td>${a.pickedAt}</td>
						          <td>${a.verifAt}</td>
						        </tr>
	                        </c:forEach>
                        </c:forEach>
                      </tbody>
                       
                    </table>

                  </div>
                </div>
                
              </div>
			</c:if>
			
			<!-- end TotalesPicking -->
              
            </div>
            <script type="text/javascript">
            
            	
            	
		    	window.onload = function() 
				 {
				 	//showMore();
					
					$('#datatable-fixed-header2').dataTable().dataTable().fnDestroy();
			        $('#datatable-fixed-header2').DataTable({
						  fixedHeader: true,
						  aLengthMenu: [[30, 50, 75, -1], [30, 50, 75, "Todo"]],
					      iDisplayLength: 30
						});
			        
					
				};
		    </script>