package com.osgi.coffeeserviceproducer;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {

	@Override
	public synchronized int addItems(String itemName, double itemPrice) {
		
		Item newItem = new Item(DataStore.itemsList.size() + 1, itemName, itemPrice);
		DataStore.itemsList.add(newItem);
		
		return 1;
	}

	@Override
	public synchronized int updateItems(String updatedItemName, double updatedItemPrice) {
		Item itemToBeUpdated = null;
		boolean invalid = true;
		int count = -1;
		for (Item tempItem : DataStore.itemsList) {
			count++;
			if (tempItem.getItemName().equalsIgnoreCase(updatedItemName)) {

				itemToBeUpdated = tempItem;
				invalid = false;
				break;
			}
		
		}
		if (!invalid) {

			itemToBeUpdated.setItemName(updatedItemName);
			itemToBeUpdated.setItemPrice(updatedItemPrice);
			DataStore.itemsList.set(count, itemToBeUpdated);
			return 1;

		} else {
			return -1;
		}
	}

	@Override
	public synchronized int removeItems(String itemName) {
		boolean invalid = true;
		int count = -1;
		for (Item tempItem :DataStore.itemsList) {
			count++;
			if (tempItem.getItemName().equalsIgnoreCase(itemName)) {
			
				invalid = false;
				break;
			}
		}
		if (!invalid) {

			DataStore.itemsList.remove(count);
			return 1;

		} else {
			return -1;
		}
	}

	@Override
	public List<Item> listItems() {
		
		return DataStore.itemsList;
	}

}
