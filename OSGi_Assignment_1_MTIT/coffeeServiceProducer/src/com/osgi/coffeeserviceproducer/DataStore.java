package com.osgi.coffeeserviceproducer;
import java.util.ArrayList;
import java.util.List;


public class DataStore {

	//Store the shared data among the producer and the consumers
	
	public static List<Item> itemsList = new ArrayList<Item>();
}
