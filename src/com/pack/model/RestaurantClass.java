package com.pack.model;

public class RestaurantClass {
	
	String restoName;
	String restoAddress;
	
	public String getRestoName() {
		return restoName;
	}
	public void setRestoName(String restoName) {
		this.restoName = restoName;
	}
	public String getRestoAddress() {
		return restoAddress;
	}
	public void setRestoAddress(String restoAddress) {
		this.restoAddress = restoAddress;
	}
	
	@Override
	public String toString() {
		return "RestaurantClass [restoName=" + restoName + ", restoAddress="
				+ restoAddress + "]";
	}
	
	
	
	

}
