package webservices;

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
		resources.add(DarDatosPutOrders.class);
		resources.add(SaveOrders.class);
		resources.add(SaveCustomers.class);
		resources.add(OrderFunctions.class);
		resources.add(Views.class);
		resources.add(Synchronizer.class);
		resources.add(Customs.class);
	}
}