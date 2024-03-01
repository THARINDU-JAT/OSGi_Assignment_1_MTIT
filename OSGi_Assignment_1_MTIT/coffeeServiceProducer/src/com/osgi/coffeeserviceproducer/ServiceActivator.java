package com.osgi.coffeeserviceproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration serviceRegisterer;

	public void start(BundleContext context) throws Exception {
		System.out.println("============Coffee service started.============");
		ManagerService adminSer = new ManagerServiceImpl();
		//registering the adminService
		serviceRegisterer = context.registerService(ManagerService.class.getName(), adminSer, null);
		
		CustomerService custSer = new CustomerServiceImpl();
		//registering the customerService
		serviceRegisterer = context.registerService(CustomerService.class.getName().toString(), custSer, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("==============Coffee service closed.=============");
		serviceRegisterer.unregister();
	}

}
