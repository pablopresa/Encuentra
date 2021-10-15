<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!doctype html>
<html ng-app="mwl.calendar.docs">
  <head>
   
  </head>
  <body>
 <script src="<%=basePath%>v3/assets/agenda/assets/moment.min.js"></script>
    <script src="<%=basePath%>v3/assets/agenda/assets/interact.min.js"></script>
    
    <script src="<%=basePath%>v3/assets/agenda/assets/angular.js"></script>
    <script src="<%=basePath%>v3/assets/agenda/assets/angular-animate.js"></script>
    <script src="<%=basePath%>v3/assets/agenda/assets/angular-locale_es-es.js"></script>
    <script src="<%=basePath%>v3/assets/agenda/assets/ui-bootstrap-tpls.min.js"></script>
    <script src="<%=basePath%>v3/assets/agenda/assets/angular-bootstrap-calendar-tpls.min.js"></script>
    <script>
    	angular.module('mwl.calendar.docs', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap']);
		angular
		  .module('mwl.calendar.docs') //you will need to declare your module with the dependencies ['mwl.calendar', 'ui.bootstrap', 'ngAnimate']
		  .controller('KitchenSinkCtrl', function(moment, alert) {
		
		    var vm = this;
		
		    //These variables MUST be set as a minimum for the calendar to work
		    vm.calendarView = 'month';
		    vm.viewDate = new Date();
		    
		    
		   
		    
		    
		    
		    
		    
		    
		    
		    vm.events = [
				<c:forEach var="a" items="${recepciones}">
					{
			        title: '${a.proveedor.descripcion}',
			        type: 'warning',
			        startsAt: '${a.agenda}',
			        endsAt: '${a.agenda}',
			        draggable: true,
			        deletable: false,
			        resizable: true,
			        idAgenda: '${a.id}',
			        cantidadEsperada: '${a.cantidadEsperada}',
			        vehiculo: '${a.vehiculo}'
			        
			      	},
				</c:forEach>
		     
		    ];
		
			   
		
		    vm.isCellOpen = true;
		
		    vm.eventClicked = function(event) {
		      alert.show('Clicked', event);
		    };
		
		    vm.eventEdited = function(event) {
		      alert.show('Edited', event);
		    };
		
		    vm.eventDeleted = function(event) {
		      alert.show('Deleted', event);
		    };
		
		    vm.eventTimesChanged = function(event) {
		      alert.show('Dropped or resized', event);
		    };
		
		    vm.toggle = function($event, field, event) {
		      $event.preventDefault();
		      $event.stopPropagation();
		      event[field] = !event[field];
		    };
		
		  });
    	
    	
    	
    	
    	
    </script>
    <script>
    	angular
			  .module('mwl.calendar.docs')
			  .factory('alert', function($uibModal) {
			
			    function show(action, event) {
			      return $uibModal.open({
			        templateUrl: '<%=basePath%>v3/assets/agenda/modalContent.jsp',
			        controller: function() {
			          var vm = this;
			          vm.action = action;
			          vm.event = event;
			        },
			        controllerAs: 'vm'
			      });
			    }
			
			    return {
			      show: show
			    };
			
			  });
    	    </script>
    <link href="<%=basePath%>v3/assets/agenda/assets/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>v3/assets/agenda/assets/angular-bootstrap-calendar.min.css" rel="stylesheet">
<div ng-controller="KitchenSinkCtrl as vm">
  <h2 class="text-center">{{ vm.calendarTitle }}</h2>

	
  <div class="row">
	
    <div class="col-md-6 text-center">
      <div class="btn-group">

        <button
          class="btn btn-primary"
          mwl-date-modifier
          date="vm.viewDate"
          decrement="vm.calendarView">
          Anterior
        </button>
        <button
          class="btn btn-default"
          mwl-date-modifier
          date="vm.viewDate"
          set-to-today>
          Hoy
        </button>
        <button
          class="btn btn-primary"
          mwl-date-modifier
          date="vm.viewDate"
          increment="vm.calendarView">
          Proximo
        </button>
      </div>
    </div>

    <br class="visible-xs visible-sm">

    <div class="col-md-6 text-center">
      <div class="btn-group">
        <label class="btn btn-primary" ng-model="vm.calendarView" uib-btn-radio="'year'">Año</label>
        <label class="btn btn-primary" ng-model="vm.calendarView" uib-btn-radio="'month'">Mes</label>
        <label class="btn btn-primary" ng-model="vm.calendarView" uib-btn-radio="'week'">Semana</label>
        <label class="btn btn-primary" ng-model="vm.calendarView" uib-btn-radio="'day'">Dia</label>
      </div>
    </div>

  </div>

  <br>

  <mwl-calendar
    events="vm.events"
    view="vm.calendarView"
    view-title="vm.calendarTitle"
    view-date="vm.viewDate"
    on-event-click="vm.eventClicked(calendarEvent)"
    on-event-times-changed="vm.eventTimesChanged(calendarEvent); calendarEvent.startsAt = calendarNewEventStart; calendarEvent.endsAt = calendarNewEventEnd"
    edit-event-html="'<i class=\'glyphicon glyphicon-pencil\'></i>'"
    delete-event-html="'<i class=\'glyphicon glyphicon-remove\'></i>'"
    on-edit-event-click="vm.eventEdited(calendarEvent)"
    on-delete-event-click="vm.eventDeleted(calendarEvent)"
    cell-is-open="vm.isCellOpen"
    day-view-start="06:00"
    day-view-end="22:00"
    day-view-split="30"
    cell-modifier="vm.modifyCell(calendarCell)">
  </mwl-calendar>

  <br><br><br>

  <h3 id="event-editor">
    Editar Fechas
     <form action="<%=basePath%>guardarCambiosAgenda.do" method="post" target="_parent" >
		<textarea name="agendas" ng-model="vm.events | json" cols="1" rows="1"  style="visibility: hidden;"></textarea>
		<button class="btn btn-primary pull-right" type="submit">
	      Guardar Cambios de agendas
	    </button>
  	</form>
    
    <div class="clearfix"></div>
  </h3>

  <table class="table table-bordered">

    <thead>
      <tr>
        <th>Proveedor</th>
        <th>Fecha</th>
        <th>Eliminar</th>
      </tr>
    </thead>

    <tbody>
      <tr ng-repeat="event in vm.events track by $index">
        <td>
        	<input type="text" name"idR" class="form-control" ng-model="event.idAgenda">
          <input type="text" class="form-control" ng-model="event.title">
        </td>
        <td>
          <p class="input-group" style="width: 450px;">
          	<br>
          	<input
              type="text"
              class="form-control"
              readonly
              uib-datepicker-popup="dd MMMM yyyy"
              ng-model="event.startsAt"
              is-open="event.startOpen"
              close-text="Close" />
            <span class="input-group-btn">
              <button
                type="button"
                class="btn btn-default"
                ng-click="vm.toggle($event, 'startOpen', event)">
                <i class="glyphicon glyphicon-calendar"></i>
              </button>
              
            </span>
	          <uib-timepicker
	            ng-model="event.startsAt"
	            hour-step="1"
	            minute-step="15"
	            show-meridian="true">
	          </uib-timepicker>
          </p>
        </td>
        
        <td>
          <button
            class="btn btn-danger"
            ng-click="vm.events.splice($index, 1)" onclick='deleteEvent(vm.events[$index].idAgenda)'>
            Eliminar Agenda
          </button>
        </td>
        
      </tr>
    </tbody>

  </table>
  
  

 
</div>
	<script type="text/javascript">
	function deleteEvent(id){
		location.assign("<%=basePath%>deleteReception.do?idR="+id);
	}
	</script>
  </body>
</html>
