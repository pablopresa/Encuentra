'use strict';
$(document).ready(function() {
    // [ Zero-configuration ] start
    var tablaEnc =$('#zero-configuration').DataTable(
    {
    	  lengthChange: true,
    	  dom: 'Bfrtip',
    	  buttons: [
              'copyHtml5',
              'excelHtml5',
              'csvHtml5',
              'pdfHtml5'
          ]
      } );
   
    tablaEnc.buttons().container()
          .appendTo( $('div.eight.column:eq(0)', tablaEnc.table().container()) );

	var table = $('#tablaEnc-monitor-ElRey').dataTable(
    {
		pageLength: 150,
		orderFixed: [[ 6, 'asc' ]],
    	rowsGroup: [0,1,2,9,11,12], 
    	fixedHeader: true, 
    	dom: 'Bfrtip',
   	  	buttons: [
             'copyHtml5',
             'csvHtml5',
			{
                text:      '<i class="fas fa-file-excel"></i>',
                titleAttr: 'Excel',
                className: 'btn btn-success',
                action: 	
					function(){
						var cons = document.getElementById('switch-3').checked;
						var basePath = document.getElementById('basePath').value;
	    				var req = basePath+'monitorPedidosExport.do?aConsolidar='+cons;
						window.location.assign(req);
                	}
            }
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
		  }
    }
    );

	var table = $('#tablaEnc-bultosRepo').dataTable(
    {
		pageLength: 150,
		orderFixed: [[ 6, 'asc' ]],
    	rowsGroup: [0,1,2,9,11,12], 
    	fixedHeader: true, 
    	dom: 'Bfrtip',
   	  	buttons: [
             'copyHtml5',
             'csvHtml5',
			{
                text:      '<i class="fas fa-file-excel"></i>',
                titleAttr: 'Excel',
                className: 'btn btn-success',
                action: 	
					function(){
						var cons = document.getElementById('switch-3').checked;
						var basePath = document.getElementById('basePath').value;
	    				var req = basePath+'monitorPedidosExport.do?aConsolidar='+cons;
						window.location.assign(req);
                	}
            }
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
		  }
    }
    );

var table = $('#tablaEnc-monitor').dataTable(
    {
		pageLength: 150,
    	rowsGroup: [0,1,2,9,11,12,13], 
    	fixedHeader: true, 
    	dom: 'Bfrtip',
   	  	buttons: [
             'copyHtml5',
             'csvHtml5',
			{
                text:      '<i class="fas fa-file-excel"></i>',
                titleAttr: 'Excel',
                className: 'btn btn-success',
                action: 	
					function(){
						var cons = document.getElementById('switch-3').checked;
						var basePath = document.getElementById('basePath').value;
	    				var req = basePath+'monitorPedidosExport.do?aConsolidar='+cons;
						window.location.assign(req);
                	}
            }
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
		  }
    }
    );

	var table = $('#tablaEnc-monitor-Std').dataTable(
	    {
			pageLength: 150,
	    	rowsGroup: [0,1,2,3,4,5,12,13,14], 
	    	fixedHeader: true, 
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
			  }
	    }
	    );

	$('#encuentra-ordenada').DataTable( {
	    order: [[ 0, "desc" ]],
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
		  }
	 });

    // [ HTML5-Export ] start
    $('#key-act-button').DataTable({
        dom: 'Bfrtip',
        buttons: [
            'copyHtml5',
            'excelHtml5',
            'csvHtml5',
            'pdfHtml5'
        ]
    });

    // [ Columns-Reorder ] start
    $('#col-reorder').DataTable({
        colReorder: true
    });

    // [ Fixed-Columns ] start
    $('#fixed-columns-left').DataTable({
        scrollY: "300px",
        scrollX: true,
        scrollCollapse: true,
        paging: false,
        fixedColumns: true,
    });
    $('#fixed-columns-left-right').DataTable({
        scrollY: "300px",
        scrollX: true,
        scrollCollapse: true,
        paging: false,
        fixedColumns: true,
        fixedColumns: {
            leftColumns: 1,
            rightColumns: 1
        }
    });
    $('#fixed-header').DataTable({
        fixedHeader: true
    });
    
    $('#encuentra-mob').DataTable({
        fixedHeader: true,
        pageLength: 5000
        
    });

	$('#encuentra-export').DataTable({
		searching: false, 
		paging: false, 
		info: false
	});
	
	$('#encuentra-operarios').DataTable({
		paging: false, 
		info: false,
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
		  }
	});


    // [ Scrolling-table ] start
    $('#scrolling-table').DataTable({
        scrollY: 300,
        paging: false,
        keys: true
    });

    // [ Responsive-table ] start
    $('#responsive-table').DataTable({
    });

    $('#responsive-table-model').DataTable({
        responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal({
                    header: function(row) {
                        var data = row.data();
                        return 'Details for ' + data[0] + ' ' + data[1];
                    }
                }),
                renderer: $.fn.dataTable.Responsive.renderer.tableAll({
                    tableClass: 'table'
                })
            }
        }
    });

	
    
    // [ ENCUENTRA] start
    $('#encuentra-default').DataTable({
        fixedHeader: true,
        
    	//colReorder: true,
    	dom: 'Bfrtip',
        buttons: [
            'copyHtml5',
            'excelHtml5',
            'csvHtml5',
            'pdfHtml5'
        ],
        pageLength: 1000,language: {
		    decimal: ",",
		    thousands: ".",
		    sSearch: "Buscar:",
		    sProcessing: "Procesando...",
		    sLengthMenu: "Mostrar _MENU_ registros",
		    sZeroRecords: "No se encontraron resultados",
		    sEmptyTable: "No se encontraron resultados",
		    sInfo: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		    sInfoEmpty: "Mostrando registros del 0 al 0 de un total de 0 registros"
		  }
    });

	$('#encuentra-default-no-order').DataTable({
	        fixedHeader: false,
	    	colReorder: false,
			ordering: false,
	    	dom: 'Bfrtip',
	        buttons: [
	            'copyHtml5',
	            'excelHtml5',
	            'csvHtml5'
	        ],
	        paging: false,
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
			  }
	    });
	 
    
});
