package com.osgi.coffeeserviceproducer;

import java.util.List;

public interface CustomerService {

	public List<Item> displayItems();//return the item list with item details
	public int generateBill(int id,int qty);//calculates the on going bill continuously
	public double displayFinalBillAmount();//displays the final bill amount
	public String[][] dispalybillDetails();//returns the purchased item list wit details
}
