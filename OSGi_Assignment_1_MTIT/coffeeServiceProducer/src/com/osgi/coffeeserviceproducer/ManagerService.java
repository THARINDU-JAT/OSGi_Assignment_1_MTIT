package com.osgi.coffeeserviceproducer;

import java.util.List;

//Provide the services which manager consumers need
public interface ManagerService {

	public  int  addItems(String itemName,double itemPrice);//Adds the new items to the item list
	public   int  updateItems(String updatedItemName,double updatedItemPrice);//Updates the item details
	public   int removeItems(String itemName);//Removes the items from the list
	public   List<Item> listItems();//Returns the item list
	
}
