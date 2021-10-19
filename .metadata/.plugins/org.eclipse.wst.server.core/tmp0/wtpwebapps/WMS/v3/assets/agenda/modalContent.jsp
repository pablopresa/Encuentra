<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
 <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <div class="modal-header">
        <h3 class="modal-title">Detalle de recepcion agendada</h3>
      </div>
      <div class="modal-body">
     	<p>Proveedor:
        <pre>{{ vm.event.title}}</pre>
        </p>
        <p>Unidades:
        <pre>{{ vm.event.cantidadEsperada}}</pre>
        </p>
        <p>Vehiculo:
        <pre>{{ vm.event.vehiculo}}</pre>
        </p>
        <p class="input-group" style="max-width: 250px">
            <input
              type="text"
              class="form-control"
              readonly
              uib-datepicker-popup="dd MMMM yyyy"
              ng-model="vm.event.startsAt"
              is-open="vm.event.startOpen"
              close-text="Close" >
            <span class="input-group-btn">
              <button
                type="button"
                class="btn btn-default"
                ng-click="vm.toggle($event, 'startOpen', vm.event)">
                <i class="glyphicon glyphicon-calendar"></i>
              </button>
            </span>
          </p>
          <uib-timepicker
            ng-model="vm.event.startsAt"
            hour-step="1"
            minute-step="15"
            show-meridian="true">
          </uib-timepicker>
       
        							 
      </div>
   
      
   
      <div class="modal-footer">
        <button class="btn btn-primary" ng-click="$close()">Cerrar</button>
      </div>
    
   <jsp:include page="/v3/util/footer.jsp"></jsp:include>
