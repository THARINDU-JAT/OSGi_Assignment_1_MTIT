package com.osgi.coffeecustomerconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.osgi.coffeeserviceproducer.CustomerService;
import com.osgi.coffeeserviceproducer.Item;

public class ServiceActivator implements BundleActivator {

	ServiceReference custServiceReference;
	private boolean exit =false;
	Scanner input = new Scanner(System.in);

	@Override
	public void start(BundleContext context) throws Exception {//Life cycle method-start
		System.out.println("============Coffee customer consumer started.============");
		custServiceReference = context.getServiceReference(CustomerService.class.getName());
		@SuppressWarnings("unchecked")
		CustomerService custService =  (CustomerService)context.getService(custServiceReference);		//Instance of CashierService
		do {
		int selection = 4;
		do {
			System.out.println("----------------------------Welcome to Billing-------------------------------");
		
		System.out.println("Please Select an option to continue.....");
		System.out.println("Options");
		System.out.println("1.View coffee items List");
		System.out.println("2.Generate customer bills");
		System.out.println("3.Exit");
		
		System.out.println("Enter your selection...");
		 selection = input.nextInt();
		
		 input.nextLine();
		 if(selection == 3) {//Exits from the cashier consumer program
			 exit = true;
		 }
		 
		 if(selection !=1 && selection !=2 && selection !=3 ) {
			 System.out.println("Please enter a valid selection");
		 }
		}while(selection !=1 && selection !=2 && selection !=3);
		
		   String  backToHome = null;
		   String calculateFinalBill = null;
		   int itemCount = -1;
			if (selection == 1) {//Handles the viewing process of item list
				do {
					
				List<Item> itemsList =custService.displayItems();//Consumes the CustomerService displayItems()
				System.out.println("-----------------------------------Coffe Menu list--------------------------------------------");
				System.out.println("Item ID:"+"\t" +"Item Name:"+"\t" + "Item Price:"+"\t");
				
			for(Item tempItem: itemsList ) {
				System.out.println(tempItem.getItemId()+"\t         "+tempItem.getItemName()+"\t          "+tempItem.getItemPrice()+"\t"+"\n");
						
			
			System.out.println("-----------------------------------------------------------------------------------------");
			
				}

			System.out.println("Press 0 to navigate back to home or press any other key to continue....");
				
				backToHome=input.nextLine();
				}
				
				while(!(backToHome.equals("0")));
				
			}
			
			else if(selection == 2) {//Handles the billing process 
				do {
					do {
						
				System.out.println("---------------------Welcome to customer's billing --------------------" + "\n");
				System.out.println("Enter the item id");
				int id = input.nextInt();
				
				System.out.println("Enter the quantity");
				int qty = input.nextInt();
					
				int result =custService.generateBill(id, qty);//Consumes the CustomerService geenrateBill()
				if(result == -1) {
					 System.out.println("Please enter a valid item number!!");
				}
				else {
					itemCount++;
				}
				input.nextLine();
				System.out.println("Press y to calclate the totoal bill or any other key to continue the billing....");	
				calculateFinalBill=input.nextLine();
				
				
						}while(!(calculateFinalBill.equals("y")));
					
						System.out.println("------------------------------------------Receipt----------------------------------------");
						String[][] billDetails= custService.dispalybillDetails();//Consumes the cashierService displaybillDetails()
						
						String format = "%-20s";
						System.out.printf(format, "Item ID:");
						System.out.printf(format, "Item Name:");
						System.out.printf(format, "Item Qty:");
						System.out.printf(format,"Total:");
						System.out.println("");
						for (int i=0; i<=itemCount; i++) {
							  for (int j=0; j<billDetails[i].length; j++) {
						
							System.out.printf(format,billDetails[i][j]);
							 
							  }
							  System.out.println("");
							  }
						System.out.println("                                                          ----------");
						System.out.println("Net total:                                                  " + custService.displayFinalBillAmount());//Consumes the cashierService displayFinalBillAmount()
						System.out.println("                                                          ----------");	  
						System.out.println("                                                          ----------");
						System.out.println("-------------------------------------------------------------------------------------------");
						//for()
				  
						itemCount=-1;
						
				System.out.println("Press 0 to navigate back to home or press any other key to continue....");
				
				backToHome=input.nextLine();
				
				}
				
				while(!(backToHome.equals("0")));
			}
			else if(selection == 3) {
				return;
			}
		}while(!exit);
		
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {//Life cycle method -stop
		System.out.println("============Coffee customer consumer stoped.============");
		context.ungetService(custServiceReference);
	}

}
