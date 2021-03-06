package endpoints;

import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("Integraciones")
public class ApplicationConfig extends Application
{

	@Override
	public Set<Class<?>>getClasses()
	{
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) 
	{
		resources.add(endpoints.Pedidos.class); 
		resources.add(UnilamSetMovStock.class);
		resources.add(BanteyPushOrder.class);
		resources.add(Couriers.class);
		resources.add(PrintSpooler.class);
		resources.add(MailSpooler.class);
		resources.add(ERPintegrations.class);
		resources.add(ApiEntities.class);
		resources.add(SaveCallBack.class);
		resources.add(productosFenicio.class);
		
		
	}
}