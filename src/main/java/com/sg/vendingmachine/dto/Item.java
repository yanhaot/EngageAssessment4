package com.sg.vendingmachine.dto;

import java.util.Objects;

public class Item {
	
	private String id;
	private String count;
	private String itemName;
	private String price;
	
	public Item(String id) {
		this.id = id;
	}
	
	public Item(String id, String itemName, String price, String count) {
		this.id = id;
		this.count = count;
		this.itemName = itemName;
		this.price = price;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(count, id, itemName, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(count, other.count) && Objects.equals(id, other.id)
				&& Objects.equals(itemName, other.itemName) && Objects.equals(price, other.price);
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCount() {
		return count;
	}
	
	public void setCount(String count) {
		this.count = count;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", count=" + count + ", itemName=" + itemName + ", price=" + price + "]";
	}

	
}
