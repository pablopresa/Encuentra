<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
        
		<jsp:include page="/v3/util/menu.jsp"></jsp:include>
		    <div class="row">
            	<div class="card">
					 <div class="card-body">
            			<div class="arbol">
            		</div>
            	</div>
        		     
            </div>
                <!-- /. ROW  -->
           
        

        
		<jsp:include page="/v3/util/footer.jsp"></jsp:include>
			<script>
			    // prettier-ignore
			    let data = [{ "id":"A","text":"Mantenimiento","check":false,"children":[{ "id":"A.A","text":"Depósitos","check":true},{ "id":"A.B","text":"Estanterías","children":[{ "id":"A.B.A","text":"Tipos de estantería"},{ "id":"A.B.B","text":"Alta Estantería"},{ "id":"A.B.C","text":"Etiqueta Estantería"},{ "id":"A.B.D","text":"Estanterías"}]},{ "id":"A.D","text":"Usuarios","children":[{ "id":"A.D.A","text":" Alta Usuarios"},{ "id":"A.D.B","text":"Grupos de Usuarios"},{ "id":"A.D.C","text":"Permisos de Grupo"},{ "id":"A.D.D","text":"Cambiar Contraseña"},{ "id":"A.D.E","text":"Cambiar Deposito"},{ "id":"A.D.F","text":"Administrar Usuarios"}]},{ "id":"A.E","text":"Base de datos","children":[{ "id":"A.E.A","text":"Sincronizar"}]},{ "id":"A.F","text":"SKU","children":[{ "id":"A.F.A","text":"Tipos de SKU"},{ "id":"A.F.B","text":"Artículos"},{ "id":"A.F.D","text":"Articulos sin Medidas"}]},{ "id":"A.G","text":"Etiquetas Tontas"}]},{ "id":"B","text":"Almacén","children":[{ "id":"B.A","text":"Ubicaciones","children":[{ "id":"B.A.B","text":"Artículos en ubicaciones"},{ "id":"B.A.C","text":"Articulos Recepcionados Sin movimiento"}]},{ "id":"B.B","text":"Informes","children":[{ "id":"B.B.B","text":"Cantidades por estanteria"}]},{ "id":"B.C","text":"Reporte de Consolidacion"},{ "id":"B.D","text":"Avance Inventario","children":[{ "id":"B.D.A","text":"Detalle Inventario"},{ "id":"B.D.B","text":"Ubicaciones en Cero"}]}]},{ "id":"C","text":"Expedición","children":[{ "id":"C.A","text":"Entregas","children":[{ "id":"C.A.A","text":"Nueva"},{ "id":"C.A.B","text":"Ver Parciales"},{ "id":"C.A.C","text":"Ver Enviados"}]},{ "id":"C.B","text":"Informes","children":[{ "id":"C.B.A","text":"Matriz Unidades por deposito"}]}]},{ "id":"D","text":"Recepción","children":[{ "id":"D.A","text":"Agendar Entregas","children":[{ "id":"D.A.A","text":"Proveedor Local"},{ "id":"D.A.B","text":"Importación"}]},{ "id":"D.C","text":"Consultar Agenda"},{ "id":"D.D","text":"Confirmar Cantidades"},{ "id":"D.E","text":"Pendientes de Factura"}]},{ "id":"E","text":"Picking","children":[{ "id":"E.A","text":"Procesar Ventas "},{ "id":"E.C","text":"Ver Reposiciones pendientes","children":[{ "id":"E.C.A","text":"Reposiciones Locales"},{ "id":"E.C.B","text":"Pedidos Web"}]},{ "id":"E.F","text":"Ventas Mayorista","children":[{ "id":"E.F.A","text":"Pendientes de Picking"},{ "id":"E.F.B","text":"Para Pasar a Picking"},{ "id":"E.F.C","text":"Marcar todo Pickeado"},{ "id":"E.F.D","text":"Marcar todo Verificado"},{ "id":"E.F.F","text":"Forzar Sincronización"}]},{ "id":"E.G","text":"Pickings Verificados"},{ "id":"E.H","text":"Ver Diferencias"},{ "id":"E.I","text":"Borrar Entregadas - Para Verificar"},{ "id":"E.J","text":"Agregar lineas Manuales"},{ "id":"E.K","text":"Reglas de Reposición"},{ "id":"E.M","text":"Reporte Distribuciones"}]},{ "id":"H","text":"Tareas","children":[{ "id":"H.A","text":"Nueva"},{ "id":"H.B","text":"Tipos de tarea"},{ "id":"H.C","text":"Orden de Almacen"},{ "id":"H.D","text":"Sin iniciar"},{ "id":"H.E","text":"Productividad en Picking"}]},{ "id":"I","text":"Informes","children":[{ "id":"I.A","text":"Tareas","children":[{ "id":"I.A.A","text":"Sin Terminar"}]},{ "id":"I.B","text":"Distribuciones Visual"}]},{ "id":"J","text":"Ecommerce","children":[{ "id":"J.A","text":"Monitor de pedidos"},{ "id":"J.C","text":"Log de pedidos"},{ "id":"J.P","text":"Pedidos - Tracking"}]},{ "id":"M","text":"Colector","children":[{ "id":"M.A","text":"Almacen","children":[{ "id":"M.A.A","text":"Cantidad Ojo"},{ "id":"M.A.B","text":"Actualizar ojo"},{ "id":"M.A.C","text":"Agregar a Ojo"},{ "id":"M.A.D","text":"Bajar a Ojo"},{ "id":"M.A.E","text":"Mover"},{ "id":"M.A.G","text":"Art en Ubic"},{ "id":"M.A.H","text":"Cod. Barras"},{ "id":"M.A.I","text":"Desarmar Bulto"},{ "id":"M.A.J","text":"Crear Bulto"}]},{ "id":"M.B","text":"Tareas & Operaciones","children":[{ "id":"M.B.A","text":"Mis Tareas"},{ "id":"M.B.C","text":"Verificar Picking"},{ "id":"M.B.D","text":"Clasificar Picking"},{ "id":"M.B.I","text":"Expedicion"},{ "id":"M.B.J","text":"Cajas Abiertas"}]},{ "id":"M.C","text":"Ecommerce","children":[{ "id":"M.C.A","text":"Verificar Pedido Ecommerce"},{ "id":"M.C.D","text":"Preparar Envio ecommerce"},{ "id":"M.C.E","text":"Verificar Verificado Ecommerce"}]},{ "id":"M.D","text":"Recepcion","children":[{ "id":"M.D.A","text":"Recepcionar"},{ "id":"M.D.B","text":"Recibir Remitos"},{ "id":"M.D.C","text":"Registrar Transito"}]},{ "id":"M.Z","text":"Opciones","children":[{ "id":"M.Z.A","text":"Cambiar Deposito"},{ "id":"M.Z.B","text":"Cambiar Contraseña"},{ "id":"M.Z.C","text":"Cambiar Equipo de Impresion"},{ "id":"M.Z.D","text":"Cambiar Grupo de Trabajo"},{ "id":"M.Z.E","text":"Cerrar Sesion"}]}]}]
			
			
				var i;
				var valores = [];
				for (i = 0; i < data.length; i++) 
				{
					var padre = data[i];
					console.log(padre.text)
					var y;
					for (y = 0; y < padre.children.length; y++) 
					{
						var hijo = padre.children[y];
						if(hijo!=null)
						{
							console.log('\t'+ hijo.text)
							if(hijo.children!=null)
							{
								var z;
								for (z = 0; z < hijo.children.length; z++) 
								{
									var nieto = hijo.children[y];
									if(nieto!=null)
									{
										console.log('\t\t'+ nieto.text)
									}
									
								}
							}
						}
					}
				}
				
			    let tree = new Tree('.arbol', {
			        data: [{ id: '-1', text: 'Menu', children: data }],
			        closeDepth: 0,
			        loaded: function () 
			        {
			        
			        	
			           //this.values = valores; 
			            //console.log(this.selectedNodes)
			            //console.log(this.values)
			            
			        },
			        onChange: function () 
			        {
			        	var i;
						for (i = 0; i < this.selectedNodes.length; i++) 
						{
						  var nodo = this.selectedNodes[i];
						
						  console.log(nodo.id);  
						  
						  
						}
						console.log(this.values); 
			            
			        }
			    })
			   	//tree.values = ['A','A.A','A.B','A.B.A','A.B.B','A.B.C','A.B.D','A.D','A.D.A','A.D.B','A.D.C','A.D.D','A.D.E','A.D.F','A.E','A.E.A','A.F','A.F.A','A.F.B','A.F.D','A.G','B','B.A','B.A.B','B.A.C','B.B','B.B.B','B.C','B.D','B.D.A','B.D.B','C','C.A','C.A.A','C.A.B','C.A.C','D','D.A','D.A.B','D.C','D.D','D.E','E','E.A','E.C','E.C.A','E.C.B','E.F','E.F.A','E.F.B','E.F.C','E.F.D','E.F.F','E.G','E.H','E.I','E.J','E.K','E.M','H','H.A','H.B','H.C','H.D','H.E','I','I.A','I.A.A','I.B','J','J.A','J.C','J.P','M','M.A','M.A.A','M.A.B','M.A.C','M.A.D','M.A.E','M.A.G','M.A.H','M.A.I','M.A.J','M.B','M.B.A','M.B.C','M.B.D','M.B.I','M.B.J','M.C','M.C.A','M.C.D','M.C.E','M.D','M.D.A','M.D.B','M.D.C','M.Z','M.Z.A','M.Z.B','M.Z.C','M.Z.D','M.Z.E'];
			</script>
		
		
