package com.osgi.coffeemanagerconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.osgi.coffeeserviceproducer.Item;
import com.osgi.coffeeserviceproducer.ManagerService;

public class ServiceActivator implements BundleActivator {

	ServiceReference ManagerServiceReference;
	Scanner input = new Scanner(System.in);

	boolean exit = false;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("============Coffee manager consumer started.============");
		ManagerServiceReference = context.getServiceReference(ManagerService.class.getName());
		@SuppressWarnings("unchecked")
		ManagerService managerService = (ManagerService) context.getService(ManagerServiceReference); //Instance of managerService
		
		do {
		int selection = 5;
		do {
		System.out.println("----------------------------Welcome to Coffee Items Management-------------------------------");
	
		System.out.println("Please Select an option to continue.....");
		System.out.println("Options");
		System.out.println("1.Add a new item ");
		System.out.println("2.Update an existing  item");
		System.out.println("3.Delete an existing item");
		System.out.println("4.List Items");
		System.out.println("5.Exit");
		
		System.out.println("Enter your selection...");
		 selection = input.nextInt();
		
		 input.nextLine();
		 if(selection == 5) {
			 exit = true;
		 }
		 
		 if(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5) {
			 System.out.println("Please enter a valid selection");
		 }
		}while(selection !=1 && selection !=2 && selection !=3 && selection !=4 && selection !=5);
		
	
        String  backToHome = null;
		if (selection == 1) {//Handles the adding process of new items to the list
			
			System.out.println("Item Name");
			String itemName = input.nextLine();

			System.out.println("Item Price");
			double itemPrice = input.nextDouble();

			int result =managerService.addItems(itemName, itemPrice);//Consumes the ManagerService addItems()
			 String msg = (result ==1) ? "Successfully added the item!" :"please enter a valid name";
			   System.out.println(msg);
			
			
			backToHome=input.nextLine();
			
		

		}else if (selection == 2) {//Handles the updating process of an item in the list
			
				System.out.println("Enter the Item Name");
				String updatedItemName = input.nextLine();

				System.out.println("Item Price");
				double updatedItemPrice = input.nextDouble();

			
			int result =managerService.updateItems(updatedItemName,updatedItemPrice);//Consumes the ManagerService updateItems()
			  String msg = (result ==1) ? "Successfully updated the item!" :"please enter a valid name";
			   System.out.println(msg);
			
			backToHome=input.nextLine();
			
			
			
		}
		else if (selection == 3) {//Handles the removing process of an existing item in the list 
			do {
			System.out.println("Enter the item name:");

			String itemName = input.nextLine();
			int result =managerService.removeItems(itemName);//Consumes the ManagerService removeItems()
			   String msg = (result ==1) ? "Successfully Removed the item!" :"please enter a valid name";
			   System.out.println(msg);
              System.out.println("Press 0 to navigate back to home or press any other key to continue....");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}
		else if(selection == 4) {//Handles displaying all items in the list
			do {
				List<Item> itemsList =managerService.listItems();//Consumes the ManagerService listItems()
				System.out.println("-----------------------------------Coffe Menu Item list--------------------------------------------");
				System.out.println("Item Name:"+"\t" + "Item Price:"+"\t");
				
			for(Item tempItem: itemsList ) {
				System.out.println(tempItem.getItemName()+"\t         "+tempItem.getItemPrice()+"\t"+"\n");
								
			}
			System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Press 0 to navigate back to home or press any other key to continue....");
			
			backToHome=input.nextLine();
			
			}
			
			while(!(backToHome.equals("0")));

		}
		
		else if(selection == 5) {//Exits form the Manager consumer program
			return;
		}
	}while(!exit);
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("============Coffee manager consumer stopped.============");
		context.ungetService(ManagerServiceReference);
	}


}
