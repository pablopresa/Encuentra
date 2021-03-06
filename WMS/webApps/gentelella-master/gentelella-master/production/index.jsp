<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-72" data-genuitec-path="/encuentra/webApps/gentelella-master/gentelella-master/production/index.jsp">
  <head>
    
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Meta, title, CSS, favicons, etc. -->
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="<%=basePath%>/gentelella-master/gentelella-master/production/images/favicon.ico" type="image/ico" />

    <title> Dashboad Encuentra </title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap -toggle-->
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/nprogress/nprogress.css" rel="stylesheet">
    
    <!-- iCheck -->
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
	
    <!-- bootstrap-progressbar -->
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

	   <!-- Datatables -->
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-bs/css/dataTables.bootstrap.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../build/css/custom.css" rel="stylesheet">
    <link href="<%=basePath%>/gentelella-master/gentelella-master/production/../build/css/tablePrintcss.css" rel="stylesheet" media="print">
   
   
   
   <style type="text/css">
		
		
		
		#marcasList li{ display:none;
		}
		.visible{ display:inline-block;
		}
		.visible2{ display:inline-block;
		}
		#loadMore {
		    cursor:pointer;
		    
		}
		
		#showLess {
		    
		    cursor:pointer;
		}
		
		
		#deposList li{ display:none;
		}
		.visibleC{ display:inline-block;
		}
		.visibleC2{ display:inline-block;
		}
		#loadMoreC {
		    cursor:pointer;
		    
		}
		
		#showLessC {
		    
		    cursor:pointer;
		}
		
		
		.ui-datatable .ui-column-filter {
			  width: 20px;
			  box-sizing: border-box;
			}
		.div.dataTables_wrapper div.dataTables_filter
		{
			 width: 20px;
		}
   </style>
   
   
   
   
  
  </head>
<c:if test="${usuarioD!=null}">
  <body class="nav-sm">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="<%=basePath%>DarDashR.do" class="site_title"> <span>Dashboad  Encuentra </span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
               
              </div>
              <div class="profile_info">
                <span>Hola</span>
                <h2><c:out value="${usuarioD.nick}"></c:out></h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <jsp:include page="/gentelella-master/gentelella-master/production/menu.jsp"></jsp:include>
            
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>
              
			  

                
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
         <div class="col">
              
          <!-- top tiles -->
          <div class="row tile_count">
           
			<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
				<span class="count_top"><i class="fa fa-cubes"></i> Unidades Procesadas en picking</span>
				<ul class="nav navbar-right panel_toolbox">
					<li class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-search-plus"></i></a>
                      	<ul class="dropdown-menu" role="menu" style="position: inherit;">
                        	<li><a id="bt" class="UxTienda" href="#">Por Destino</a></li>
                        	<li><a id="bt2" class="UxMarca" href="#">Por Marca</a></li>
                        	<li><a id="bt5" class="UxClase" href="#">Por Clase</a></li>
                        	<li><a id="bt6" class="UxUsuarios" href="#">Por Usuario</a></li>
                        	<li><a id="bt7" class="UxAll" href="#">Ver Todo</a></li>
                      	</ul>
                    </li>
				</ul>
              	<div class="count">${unidadesPicked}</div>
              	<span class="count_bottom">
              		Porcentaje de precision ${porcentajeDefasaje} 
              	</span>
            </div>
            
            
          </div>
          <!-- /top tiles -->
         
         <div class="clearfix"></div>
         	

		  <div id="here"></div>
            <div class="col-md-12 col-sm-12 col-xs-12">
            	<div class="row x_title">
                  <div class="col-md-12">
                    <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                      <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                      <span></span> <b class="caret"></b>
                    </div>
                  </div>
                </div>
             </div>
          <div id="tablasD">
           <!-- tablas -->
          	<jsp:include page="/gentelella-master/gentelella-master/production/tablas.jsp"></jsp:include>
		  </div>
		
          
          
          <!-- end tablas -->
           
           
          

        <!-- footer content -->
        <footer>
         
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/nprogress/nprogress.js"></script>
    
    <!-- iCheck -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/iCheck/icheck.min.js"></script>
    <!-- Datatables -->
    
    
 <!-- datatable Js -->
    <script src="<%=basePath%>v3/assets/plugins/data-tables/js/datatables.min.js"></script>
    <script src="<%=basePath%>v3/assets/js/pages/tbl-datatable-custom.js"></script>
    <script src="<%=basePath%>v3/assets/plugins/data-tables/js/rowgroup.js"></script>
    


  <!-- bootstrap-daterangepicker -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/moment/min/moment.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    
    
    <!-- Chart.js -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/Chart.js/dist/Chart.min.js"></script>
    <!-- gauge.js -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- Skycons -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/skycons/skycons.js"></script>
    <!-- Flot -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/Flot/jquery.flot.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/Flot/jquery.flot.pie.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/Flot/jquery.flot.time.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/Flot/jquery.flot.stack.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/Flot/jquery.flot.resize.js"></script>
    <!-- Flot plugins -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/flot.curvedlines/curvedLines.js"></script>
    <!-- DateJS -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/DateJS/build/date.js"></script>
    <!-- JQVMap -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/jqvmap/dist/jquery.vmap.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/moment/min/moment.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    
     <!-- ECharts -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/echarts/dist/echarts.min.js"></script>
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../vendors/echarts/map/js/world.js"></script>
    <!-- Bootstrap -toggle-->
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    
    <!-- Custom Theme Scripts -->
    <script src="<%=basePath%>/gentelella-master/gentelella-master/production/../build/js/custom.js"></script>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <script type="text/javascript">
    
    var table;
      $(document).ready(function() {
				
		init_sparklines();
		init_flot_chart();
		init_sidebar();
		init_wysiwyg();
		init_InputMask();
		init_JQVmap();
		init_cropper();
		init_knob();
		init_IonRangeSlider();
		init_ColorPicker();
		init_TagsInput();
		init_parsley();
		init_daterangepicker();
		init_daterangepicker_right();
		init_daterangepicker_single_call();
		init_daterangepicker_reservation();
		init_SmartWizard();
		init_EasyPieChart();
		init_charts();
		init_echarts();
		init_morris_charts();
		init_skycons();
		init_select2();
		init_validator();
		init_DataTables();
		init_chart_doughnut();
		init_gauge();
		init_PNotify();
		init_starrr();
		init_calendar();
		init_compose();
		init_CustomNotification();
		init_autosize();
		init_autocomplete();
		
		
		
				 $("#cerrartablaDepos").click(function()
				 {
				 	$("#divTablaDepos").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaDepos", success: function(result){
		            
		        	}});	
				 
				  });
				 $("#cerrartablaMarcas").click(function()
				 {
				 	$("#divTablaMarcas").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaMarcas", success: function(result){
		            
		        	}});	
				 
				  });
				 $("#cerrartablaGenero").click(function()
				 {
				 	$("#divTablaGenero").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaGenero", success: function(result){
		            
		        	}});	
				 
				  });
				  $("#cerrartablaSeccion").click(function()
				 {
				 	$("#divTablaSeccion").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaSeccion", success: function(result){
		            
		        	}});	
				 
				  });
				  $("#cerrartablaClase").click(function()
				 {
				 	$("#divTablaClase").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaClase", success: function(result){
		            
		        	}});	
				 
				  });
				  $("#cerrartablaHora").click(function()
				 {
				 	$("#divTablaHora").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaHora", success: function(result){
		            
		        	}});	
				 
				  });
				  
				  $("#cerrartablaArticulo").click(function()
				 {
				 	$("#divTablaArticulo").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaArticulo", success: function(result){
		            
		        	}});	
				 
				  });	
		
				  $("#cerrartablaDatosPicking").click(function()
				 {
				 	$("#divDatosPicking").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaDatosPicking", success: function(result){
		            
		        	}});	
				 
				  });	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		$('#allInD').click(function() 
				 {
				 	
				 	$('input[class="swTienda"]').bootstrapToggle('on');
				 	
				 	
		          });
		$('#allOffD').click(function() 
				 {
				 	
				 	$('input[class="swTienda"]').bootstrapToggle('off');
				 	
				 	
		          });
		
		$('#allInM').click(function() 
				 {
				 	
				 	$('input[class="swMarca"]').bootstrapToggle('on');
				 	
				 	
		          });
		$('#allOffM').click(function() 
				 {
				 	$('input[class="swMarca"]').bootstrapToggle('off');
				 					 	
		          });
		          
		 $('#allInC').click(function() 
				 {
				 	
				 	$('input[class="swClase"]').bootstrapToggle('on');
				 	
				 	
		          });
		$('#allOffC').click(function() 
				 {
				 	$('input[class="swClase"]').bootstrapToggle('off');
				 					 	
		          });      
		          
		   $('#allInO').click(function() 
				 {
				 	
				 	$('input[class="swUsuario"]').bootstrapToggle('on');
				 	
				 	
		          });
		$('#allOffO').click(function() 
				 {
				 	
				 	$('input[class="swUsuario"]').bootstrapToggle('off');
				 	
				 	
		          });
		          
		  $('#allInMO').click(function() 
				 {
				 	
				 	$('input[class="swModulo"]').bootstrapToggle('on');
				 	
				 	
		          });
		$('#allOffMO').click(function() 
				 {
				 	$('input[class="swModulo"]').bootstrapToggle('off');
				 					 	
		          });
		          
		 /*para las de la base*/
		 
		
		 
		 $('#allInM2').click(function() 
				 {
				 	
				 	$('input[class="swMarca"]').bootstrapToggle('on');
				 	
				 	
		          });
		$('#allOffM2').click(function() 
				 {
				 	$('input[class="swMarca"]').bootstrapToggle('off');
				 					 	
		          });
		          
		 $('#allInC2').click(function() 
				 {
				 	
				 	$('input[class="swClase"]').bootstrapToggle('on');
				 	
				 	
		          });
		$('#allOffC2').click(function() 
				 {
				 	$('input[class="swClase"]').bootstrapToggle('off');
				 					 	
		          });  
		          
		 $('#allInU2').click(function() 
				 {
				 	
				 	$('input[class="swClase"]').bootstrapToggle('on');
				 	
				 	
		          });
		$('#allOffU2').click(function() 
				 {
				 	$('input[class="swClase"]').bootstrapToggle('off');
				 					 	
		          });   
		 
		
		
		 size_li = $("#marcasList li").size();
		    x=10;
		    $('#marcasList li:lt('+x+')').show();
		    $('#loadMore').click(function () {
		        x= (x+5 <= size_li) ? x+5 : size_li;
		        $('#marcasList li:lt('+x+')').show();
		        $("#visible").show();
		        $("#visible2").show();
		    });
		    $('#showLess').click(function () {
		        x=(x-5<0) ? 10 : x-5;
		        $('#marcasList li').not(':lt('+x+')').hide();
		        $("#visible").show();
		        $("#visible2").show();
		    });
		    $("#visible").show();
		    $("#visible2").show();
			
		
		 size_liC = $("#deposList li").size();
		    x=10;
		    $('#deposList li:lt('+x+')').show();
		    $('#loadMoreC').click(function () {
		        x= (x+5 <= size_li) ? x+5 : size_li;
		        $('#deposList li:lt('+x+')').show();
		        $("#visibleC").show();
		        $("#visibleC2").show();
		    });
		    $('#showLessC').click(function () {
		        x=(x-5<0) ? 10 : x-5;
		        $('#deposList li').not(':lt('+x+')').hide();
		        $("#visibleC").show();
		        $("#visibleC2").show();
		    });
		    $("#visibleC").show();
		    $("#visibleC2").show();
		 
		 
		 $('#datatable-fixed-header6, #datatable-fixed-header, #datatable-fixed-header2, #datatable-fixed-header3, #datatable-fixed-header4').DataTable({
					  aLengthMenu: [[30, 50, 75, -1], ['treinta', 50, 75, "Todo"]],
					   language: {
					    decimal: ",",
					    thousands: "."
					  },
				      iDisplayLength: 50,
				      dom: 'Bfrtip',
				      buttons: [
				             'copyHtml5',
				             'excelHtml5',
				             'csvHtml5',
				             'pdfHtml5'
				         ],
						language: {
						    decimal: ",",
						    thousands: ".",
						    sSearch: "Buscar:",
						    sProcessing: "Procesando...",
						    sLengthMenu: "Mostrar _MENU_ registros",
						    sZeroRecords: "No se encontraron resultados",
						    sEmptyTable: "No se encontraron resultados",
						    sInfo: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
						    sInfoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros"
						  },
					  responsive: true,
					  fixedHeader: true
				});
		 
		 
		 	table =  $('#datatable-fixed-header5').DataTable({
			  aLengthMenu: [[30, 50, 75, -1], ['treinta', 50, 75, "Todo"]],
			   language: {
			    decimal: ",",
			    thousands: "."
			  },
		      iDisplayLength: 50,
		      dom: 'Bfrtip',
		      buttons: [
		             'copyHtml5',
		             'excelHtml5',
		             'csvHtml5',
		             'pdfHtml5'
		         ],
				language: {
				    decimal: ",",
				    thousands: ".",
				    sSearch: "Buscar:",
				    sProcessing: "Procesando...",
				    sLengthMenu: "Mostrar _MENU_ registros",
				    sZeroRecords: "No se encontraron resultados",
				    sEmptyTable: "No se encontraron resultados",
				    sInfo: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
				    sInfoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros"
				  },
			  responsive: true,
			  fixedHeader: true
		});
     	
     	
		 
		
		 $("#bt, #bt1, #bt2,#bt3, #bt4, #bt5,#bt6, #bt7, #bt8, #bt9, #bt10, #bt11,#bt12, #bt13, #bt14,#bt15, #bt16, #bt101, #bt125").click(function()
		 {
		 	tablaSel = $(this).attr("class"); 
		 	
			if(tablaSel.includes("xTienda"))
			{
				$("#divTablaDepos").show();
			}
			else if (tablaSel.includes("xMarca"))
			{
				$("#divTablaMarcas").show();
			}
			else if (tablaSel.includes("xUsuarios"))
			{
				$("#divTablaUsuarios").show();
			}
			else if (tablaSel.includes("xSeccion"))
			{
				$("#divTablaSeccion").show();
			}
			else if (tablaSel.includes("xClase"))
			{
				$("#divTablaClase").show();
			}
			else if (tablaSel.includes("xArticulo"))
			{
				$("#divTablaArticulo").show();
			}
			else if (tablaSel.includes("xHora"))
			{
				$("#divTablaHora").show();
			}
			
			else if (tablaSel.includes("xClases"))
			{
				$("#divTablaPrecios").show();
			}
			else if (tablaSel.includes("xAll"))
			{
				$("#divDatosPicking").show();
			}
			
			 $("#tablasD").load("<%=basePath%>DarDashTablas.do?fechas=${fini}-${ffin}&tabla="+tablaSel 
			, function()
			{
				 
				 $('#datatable-fixed-header6, #datatable-fixed-header, #datatable-fixed-header2, #datatable-fixed-header3, #datatable-fixed-header4' ).DataTable({
					 aLengthMenu: [[30, 50, 75, -1], [30, 50, 75, "Todo"]],
					   language: {
					    decimal: ",",
					    thousands: "."
					  },
				      iDisplayLength: 50,
				      dom: 'Bfrtip',
				      buttons: [
				             'copyHtml5',
				             'excelHtml5',
				             'csvHtml5',
				             'pdfHtml5'
				         ],
						language: {
						    decimal: ",",
						    thousands: ".",
						    sSearch: "Buscar:",
						    sProcessing: "Procesando...",
						    sLengthMenu: "Mostrar _MENU_ registros",
						    sZeroRecords: "No se encontraron resultados",
						    sEmptyTable: "No se encontraron resultados",
						    sInfo: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
						    sInfoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros"
						  },
					  responsive: true,
					  fixedHeader: true
				});
				 
				 
				 
				 table =  $('#datatable-fixed-header5').DataTable({
					  aLengthMenu: [[30, 50, 75, -1], ['treinta', 50, 75, "Todo"]],
					   language: {
					    decimal: ",",
					    thousands: "."
					  },
				      iDisplayLength: 50,
				      dom: 'Bfrtip',
				      buttons: [
				             'copyHtml5',
				             'excelHtml5',
				             'csvHtml5',
				             'pdfHtml5'
				         ],
						language: {
						    decimal: ",",
						    thousands: ".",
						    sSearch: "Buscar:",
						    sProcessing: "Procesando...",
						    sLengthMenu: "Mostrar _MENU_ registros",
						    sZeroRecords: "No se encontraron resultados",
						    sEmptyTable: "No se encontraron resultados",
						    sInfo: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
						    sInfoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros"
						  },
					  responsive: true,
					  fixedHeader: true
				});
				
				 $("#cerrartablaDepos").click(function()
				 {
				 	$("#divTablaDepos").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaDepos", success: function(result){
		            
		        	}});	
				 
				  });
				 $("#cerrartablaMarcas").click(function()
				 {
				 	$("#divTablaMarcas").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaMarcas", success: function(result){
		            
		        	}});	
				 
				  });
				 $("#cerrartablaGenero").click(function()
				 {
				 	$("#divTablaGenero").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaGenero", success: function(result){
		            
		        	}});	
				 
				  });
				  $("#cerrartablaSeccion").click(function()
				 {
				 	$("#divTablaSeccion").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaSeccion", success: function(result){
		            
		        	}});	
				 
				  });
				  $("#cerrartablaClase").click(function()
				 {
				 	$("#divTablaClase").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaClase", success: function(result){
		            
		        	}});	
				 
				  });
				  $("#cerrartablaArticulo").click(function()
				 {
				 	$("#divTablaArticulo").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaArticulo", success: function(result){
		            
		        	}});	
				 
				  });
				    $("#cerrartablaDatosPicking").click(function()
				 {
				 	$("#divDatosPicking").hide();
				 	$.ajax({url: "<%=basePath%>/DarDashRemTablas.do?tablaD=tablaPicking", success: function(result){
		            
		        	}});	
				 
				  });
			
			
			
			
			}
	        
	     );
	        

		
			
			
				
			
	        
	        
	    });
		
				
	});
      
   	function filtrar(valor)
   	{
   		table
        .columns(0)
        .search( valor )
        .draw();
   	}
      
      
      
     
		 var theme = 
				  {
						color: 
						[
							  '#26B99A', '#34495E', '#BDC3C7', '#3498DB',
							  '#9B59B6', '#8abb6f', '#759c6a', '#bfd3b7'
						],
						title: 
						{
							itemGap: 8,
							textStyle: 
							{
								fontWeight: 'normal',
								color: '#408829'
							}
						},
						dataRange: 
						{
							color: ['#1f610a', '#97b58d']
						},
						toolbox: 
						{
							  color: ['#408829', '#408829', '#408829', '#408829']
						 },

				  tooltip: {
					  backgroundColor: 'rgba(0,0,0,0.5)',
					  axisPointer: {
						  type: 'line',
						  lineStyle: {
							  color: '#408829',
							  type: 'dashed'
						  },
						  crossStyle: {
							  color: '#408829'
						  },
						  shadowStyle: {
							  color: 'rgba(200,200,200,0.3)'
						  }
					  }
				  },

				  dataZoom: {
					  dataBackgroundColor: '#eee',
					  fillerColor: 'rgba(64,136,41,0.2)',
					  handleColor: '#408829'
				  },
				  grid: {
					  borderWidth: 0
				  },

				  categoryAxis: {
					  axisLine: {
						  lineStyle: {
							  color: '#408829'
						  }
					  },
					  splitLine: {
						  lineStyle: {
							  color: ['#eee']
						  }
					  }
				  },

				  valueAxis: {
					  axisLine: {
						  lineStyle: {
							  color: '#408829'
						  }
					  },
					  splitArea: {
						  show: true,
						  areaStyle: {
							  color: ['rgba(250,250,250,0.1)', 'rgba(200,200,200,0.1)']
						  }
					  },
					  splitLine: {
						  lineStyle: {
							  color: ['#eee']
						  }
					  }
				  },
				  timeline: {
					  lineStyle: {
						  color: '#408829'
					  },
					  controlStyle: {
						  normal: {color: '#408829'},
						  emphasis: {color: '#408829'}
					  }
				  },

				  k: {
					  itemStyle: {
						  normal: {
							  color: '#68a54a',
							  color0: '#a9cba2',
							  lineStyle: {
								  width: 1,
								  color: '#408829',
								  color0: '#86b379'
							  }
						  }
					  }
				  },
				  map: {
					  itemStyle: {
						  normal: {
							  areaStyle: {
								  color: '#ddd'
							  },
							  label: {
								  textStyle: {
									  color: '#c12e34'
								  }
							  }
						  },
						  emphasis: {
							  areaStyle: {
								  color: '#99d2dd'
							  },
							  label: {
								  textStyle: {
									  color: '#c12e34'
								  }
							  }
						  }
					  }
				  },
				  force: {
					  itemStyle: {
						  normal: {
							  linkStyle: {
								  strokeColor: '#408829'
							  }
						  }
					  }
				  },
				  chord: {
					  padding: 4,
					  itemStyle: {
						  normal: {
							  lineStyle: {
								  width: 1,
								  color: 'rgba(128, 128, 128, 0.5)'
							  },
							  chordStyle: {
								  lineStyle: {
									  width: 1,
									  color: 'rgba(128, 128, 128, 0.5)'
								  }
							  }
						  },
						  emphasis: {
							  lineStyle: {
								  width: 1,
								  color: 'rgba(128, 128, 128, 0.5)'
							  },
							  chordStyle: {
								  lineStyle: {
									  width: 1,
									  color: 'rgba(128, 128, 128, 0.5)'
								  }
							  }
						  }
					  }
				  },
				  gauge: {
					  startAngle: 225,
					  endAngle: -45,
					  axisLine: {
						  show: true,
						  lineStyle: {
							  color: [[0.2, '#86b379'], [0.8, '#68a54a'], [1, '#408829']],
							  width: 8
						  }
					  },
					  axisTick: {
						  splitNumber: 10,
						  length: 12,
						  lineStyle: {
							  color: 'auto'
						  }
					  },
					  axisLabel: {
						  textStyle: {
							  color: 'auto'
						  }
					  },
					  splitLine: {
						  length: 18,
						  lineStyle: {
							  color: 'auto'
						  }
					  },
					  pointer: {
						  length: '90%',
						  color: 'auto'
					  },
					  title: {
						  textStyle: {
							  color: '#333'
						  }
					  },
					  detail: {
						  textStyle: {
							  color: 'auto'
						  }
					  }
				  },
				  textStyle: {
					  fontFamily: 'Arial, Verdana, sans-serif'
				  }
			  };
		
		
		var echartLineU;
		if ($('#echart_Unidades').length )
		{ 
			var chartdata = 
			[
				   {
				    name: 'Anterior',
				    type: 'bar',
				    data: [
				    		<c:forEach var="g" items="${graficoUnidades}">
								${g.idInt2},
							</c:forEach>
				    	  ]
				  },
				  {
				    name: 'Actual',
				    type: 'bar',
				    data: [
							<c:forEach var="g" items="${graficoUnidades}">
								${g.idInt1},
							</c:forEach>
						  ]
				  }
				 
			];
			
			var chart = document.getElementById('echart_Unidades');
			echartLineU = echarts.init(chart,theme);
			
			var option = 
			{
			 
			  xAxis: {
			    data: [
			    		<c:forEach var="g" items="${graficoUnidades}">
							'${g.idString1}',
						</c:forEach>
			    	  ],
			    	axisLine: {
			      lineStyle: {
			        color: '#ccc'
			      }
			    },
			    axisLabel: {
			      fontSize: 10,
			      color: '#666'
			    }
			  },
			  yAxis: {
			    splitLine: {
			      lineStyle: {
			        color: '#ddd'
			      }
			    },
			    axisLine: {
			      lineStyle: {
			        color: '#ccc'
			      }
			    },
			    axisLabel: {
			      fontSize: 10,
			      color: '#666'
			    }
			  },
			  toolbox: {
				  show: true,
				  feature: {
					magicType: {
					  show: true,
					  title: {
						line: 'Line',
						bar: 'Bar',
						stack: 'Stack',
						tiled: 'Tiled'
					  },
					  type: [ 'bar','line', 'stack', 'tiled']
					},
					restore: {
					  show: true,
					  title: "Restore"
					},
					saveAsImage: {
					  show: true,
					  title: "Save Image"
					}
				  }
				},
				tooltip: {
				  trigger: 'axis'
				},
				legend: {
				  x: 220,
				  y: 10,
				  data: ['Anterior','Actual']
				},
			  series: chartdata
			};
			
			echartLineU.setOption(option);
						  
			  
			  

		}
		var echartLineI;
		if ($('#echart_Importe').length )
		{ 
			  
			  
			  var chartdata = 
			  [
				   {
				    name: 'Anterior',
				    type: 'bar',
				    data: [
				    		<c:forEach var="g" items="${graficoImportes}">
								${g.valorDouble2},
							</c:forEach>
				    	  ]
				  },
				  {
				    name: 'Actual',
				    type: 'bar',
				    data: [
							<c:forEach var="g" items="${graficoImportes}">
								${g.valorDouble1},
							</c:forEach>
						  ]
				  }
				 
			];
			
			var chart = document.getElementById('echart_Importe');
			echartLineI = echarts.init(chart,theme);
			
			var option = 
			{
			 
			  xAxis: {
			    data: [
			    		<c:forEach var="g" items="${graficoImportes}">
							'${g.idString1}',
						</c:forEach>
			    	  ],
			    	axisLine: {
			      lineStyle: {
			        color: '#ccc'
			      }
			    },
			    axisLabel: {
			      fontSize: 10,
			      color: '#666'
			    }
			  },
			  yAxis: {
			    splitLine: {
			      lineStyle: {
			        color: '#ddd'
			      }
			    },
			    axisLine: {
			      lineStyle: {
			        color: '#ccc'
			      }
			    },
			    axisLabel: {
			      fontSize: 10,
			      color: '#666'
			    }
			  },
			  toolbox: {
				  show: true,
				  feature: {
					magicType: {
					  show: true,
					  title: {
						line: 'Line',
						bar: 'Bar',
						stack: 'Stack',
						tiled: 'Tiled'
					  },
					  type: [ 'bar','line', 'stack', 'tiled']
					},
					restore: {
					  show: true,
					  title: "Restore"
					},
					saveAsImage: {
					  show: true,
					  title: "Save Image"
					}
				  }
				},
				tooltip: {
				  trigger: 'axis'
				},
				legend: {
				  x: 220,
				  y: 10,
				  data: ['Anterior','Actual']
				},
			  series: chartdata
			};
			
			echartLineI.setOption(option);
						  
			  
			  

			}
		
		
		
		
		function init_daterangepicker() {

			
			if( typeof ($.fn.daterangepicker) === 'undefined'){ return; }
			console.log('init_daterangepicker');
		
			var cb = function(start, end, label) {
			  console.log(start.toISOString(), end.toISOString(), label);
			  $('#reportrange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
			};

			var optionSet1 = {
			  startDate: moment(),
			  endDate: moment(),
			  showDropdowns: true,
			  showWeekNumbers: true,
			  timePicker: false,
			  timePickerIncrement: 1,
			  timePicker12Hour: true,
			  ranges: {
				'Hoy': [moment(), moment()],
				'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
				'Ultimos 7 Dias': [moment().subtract(6, 'days'), moment()],
				'Ultimos 90 Dias': [moment().subtract(89, 'days'), moment()],
				'Este Mes': [moment().startOf('month'), moment()],
				'Mes Pasado': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
				'Q1':[moment().startOf('year').quarters(1),moment().startOf('year').quarters(2).subtract(1, 'days')],
				'Q2':[moment().startOf('year').quarters(2),moment().startOf('year').quarters(3).subtract(1, 'days')],
				'Q3':[moment().startOf('year').quarters(3),moment().startOf('year').quarters(4).subtract(1, 'days')],
				'Q4':[moment().startOf('year').quarters(4),moment().add(1,'year').startOf('year').quarters(1).subtract(1, 'days')],
			  },
			  opens: 'left',
			  buttonClasses: ['btn btn-default'],
			  applyClass: 'btn-small btn-primary',
			  cancelClass: 'btn-small',
			  format: 'DD/MM/YYYY',
			  separator: ' a ',
			  locale: {
				applyLabel: 'Filtrar',
				cancelLabel: 'Limpiar',
				fromLabel: 'Desde',
				toLabel: 'Hasta',
				customRangeLabel: 'Fechas Especificas',
				daysOfWeek: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
				monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Augosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'],
				firstDay: 1
			  }
			};
			
			<c:if test="${fini!=null}">
				$('#reportrange span').html('${fini} - ${ffin}' );
				document.getElementById("fechas").value = '${fini}-${ffin}'; 
			</c:if>
			<c:if test="${fini==null}">
			$('#reportrange span').html(moment().format('DD/MM/YYYY') + ' - ' + moment().format('DD/MM/YYYY') );
			</c:if>
			
			$('#reportrange').daterangepicker(optionSet1, cb);
			$('#reportrange').on('show.daterangepicker', function() {
			  console.log("show event fired");
			});
			$('#reportrange').on('hide.daterangepicker', function() {
			  console.log("hide event fired");
			});
			$('#reportrange').on('apply.daterangepicker', function(ev, picker) 
			{
				document.getElementById("fechas").value = picker.startDate.format('DD/MM/YYYY') + "-" + picker.endDate.format('DD/MM/YYYY'); 
				document.getElementById("filtros").submit(); 
			}
			);
			$('#reportrange').on('cancel.daterangepicker', function(ev, picker) {
			  console.log("cancel event fired");
			});
			$('#options1').click(function() {
			  $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
			});
			$('#options2').click(function() {
			  $('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
			});
			$('#destroy').click(function() {
			  $('#reportrange').data('daterangepicker').remove();
			});
			
			
   
		}
		
		

		
		
		
		
		
		 
		 
		 
		 function adRemoveTabla(idTabla,addRemove,idDiv)
		 {
		 	
		 	$.ajax({url: "<%=basePath%>/DarDashFixTablas.do?tablaD="+idTabla+"&acction="+addRemove, success: function(result){
		    			 if(addRemove==0)
		    			 {
		    			 	$("#"+idDiv).hide();
		    			 }
		    			 
		        	}});	
		 }
		
		</script>
		
      
    
    	
  </body>
</c:if>
<c:if test="${usuarioD==null}">
	<script type="text/javascript">
		window.location.href = "<%=basePath%>/gentelella-master/gentelella-master/production/AutoLogin.jsp";		
	</script>
</c:if>


</html>
